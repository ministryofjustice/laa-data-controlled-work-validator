package uk.gov.justice.laa.dstew.payments.claims.validation.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ClaimValidationRequest;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules.ClaimValidator;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

@ExtendWith(MockitoExtension.class)
class ValidationServiceTest {

  @Mock private List<ClaimValidator> mockValidators;

  @InjectMocks private ValidationService validationService;

  private Claim createTestClaim() {
    Claim claim = new Claim();
    claim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);
    claim.setOfficeAccountNumber("1A234B");
    return claim;
  }

  @Test
  void validateClaim_returnsValidResultWhenNoIssues() {
    ClaimValidationRequest request = new ClaimValidationRequest();
    request.setClaim(createTestClaim());
    request.setScope("fee");

    when(mockValidators.stream()).thenReturn(Collections.<ClaimValidator>emptyList().stream());

    ValidationResult result = validationService.validateClaim(request);

    assertThat(result.getIsValid()).isTrue();
    assertThat(result.getIssues()).isEmpty();
  }

  @Test
  void validateClaim_returnsInvalidResultWhenErrorIssuesFound() {
    ClaimValidationRequest request = new ClaimValidationRequest();
    request.setClaim(createTestClaim());
    request.setScope("fee");

    ClaimValidator mockValidator =
        new ClaimValidator() {
          @Override
          public List<ValidationIssue> validate(Claim claim, ValidationContext ctx) {
            return List.of(
                ValidationIssue.builder()
                    .code("TEST_ERROR")
                    .message("Test error message")
                    .severity(ValidationSeverity.ERROR)
                    .build()
            );
          }

          @Override
          public String getValidatorCode() {
            return "TEST";
          }
        };

    when(mockValidators.stream()).thenReturn(List.of(mockValidator).stream());

    ValidationResult result = validationService.validateClaim(request);

    assertThat(result.getIsValid()).isFalse();
    assertThat(result.getIssues()).hasSize(1);
    assertThat(result.getIssues().getFirst().getCode()).isEqualTo("TEST_ERROR");
  }

  @Test
  void validateClaim_returnsValidResultWithWarningsOnly() {
    ClaimValidationRequest request = new ClaimValidationRequest();
    request.setClaim(createTestClaim());

    ClaimValidator mockValidator =
        new ClaimValidator() {
          @Override
          public List<ValidationIssue> validate(Claim claim, ValidationContext ctx) {
            return List.of(
                ValidationIssue.builder()
                    .code("TEST_WARNING")
                    .message("Test warning message")
                    .severity(ValidationSeverity.WARNING)
                    .build()
            );
          }

          @Override
          public String getValidatorCode() {
            return "TEST";
          }
        };

    when(mockValidators.stream()).thenReturn(List.of(mockValidator).stream());

    ValidationResult result = validationService.validateClaim(request);

    assertThat(result.getIsValid()).isTrue();
    assertThat(result.getIssues()).hasSize(1);
    assertThat(result.getIssues().getFirst().getSeverity()).isEqualTo(ValidationSeverity.WARNING);
  }

  @Test
  void validatorsFilteredByScope_sortedByPriority_and_duplicatesRemoved_preserveOrder() {
    List<String> callOrder = new ArrayList<>();

    ValidationIssue sharedIssue = ValidationIssue.builder()
        .code("DUPLICATE_CODE")
        .message("duplicate")
        .severity(ValidationSeverity.WARNING)
        .technicalMessage(null)
        .build();

    ClaimValidator lowPriority = new ClaimValidator() {
      @Override
      public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
        callOrder.add("low");
        return List.of(sharedIssue);
      }

      @Override
      public int priority() {
        return 20;
      }

      @Override
      public boolean appliesTo(String scope) {
        return true;
      }

      @Override
      public String getValidatorCode() {
        return "LOW";
      }
    };

    ClaimValidator highPriority = new ClaimValidator() {
      @Override
      public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
        callOrder.add("high");
        return List.of(sharedIssue);
      }

      @Override
      public int priority() {
        return 10;
      }

      @Override
      public boolean appliesTo(String scope) {
        return true;
      }

      @Override
      public String getValidatorCode() {
        return "HIGH";
      }
    };

    ClaimValidator excluded = new ClaimValidator() {
      @Override
      public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
        callOrder.add("excluded");
        return List.of(ClaimValidationError.CLAIM_DATA_INCOMPLETE.toValidationIssue());
      }

      @Override
      public boolean appliesTo(String scope) {
        return false;
      }

      @Override
      public String getValidatorCode() {
        return "EXCLUDED";
      }
    };

    ValidationService service = new ValidationService(List.of(lowPriority, highPriority, excluded));

    ClaimValidationRequest request = ClaimValidationRequest.builder()
        .claim(Claim.builder().build())
        .scope("fee")
        .build();

    var result = service.validateClaim(request);

    // Both validators that apply should have been called in priority order (high then low)
    assertThat(callOrder).containsExactly("high", "low");

    // Shared identical issues should be deduplicated -> only one issue present
    assertThat(result.getIssues()).hasSize(1);

    // The single issue is a WARNING so overall validation should be considered valid
    assertThat(result.getIsValid()).isTrue();
  }

  @Test
  void buildValidationContext_passesRelatedClaims_and_handlesNullRelatedClaims() {
    AtomicReference<ValidationContext> captured = new AtomicReference<>();

    ClaimValidator capturingValidator = new ClaimValidator() {
      @Override
      public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
        captured.set(context);
        return List.of();
      }

      @Override
      public String getValidatorCode() {
        return "CAPTURE";
      }
    };

    ValidationService service = new ValidationService(List.of(capturingValidator));

    List<Claim> related = List.of(Claim.builder().uniqueFileNumber("010101/001").build());

    ClaimValidationRequest requestWithRelated = new ClaimValidationRequest();
    requestWithRelated.setClaim(Claim.builder().build());
    requestWithRelated.setScope("fee");
    requestWithRelated.setRelatedClaims(related);

    service.validateClaim(requestWithRelated);

    ValidationContext ctx = captured.get();
    assertThat(ctx).isNotNull();
    assertThat(ctx.getRelatedClaims()).isEqualTo(related);

    // Now test when relatedClaims is null on the request object
    captured.set(null);
    ClaimValidationRequest requestWithNullRelated = new ClaimValidationRequest();
    requestWithNullRelated.setClaim(Claim.builder().build());
    requestWithNullRelated.setScope("fee");
    requestWithNullRelated.setRelatedClaims(null);

    service.validateClaim(requestWithNullRelated);

    ValidationContext ctx2 = captured.get();
    assertThat(ctx2).isNotNull();
    // Should be converted to an empty list (List.of()) when null
    assertThat(ctx2.getRelatedClaims()).isEmpty();
  }

  @Test
  void privateBuildValidationContext_invokedViaReflection_coversBothBranches() throws Exception {
    ValidationService service = new ValidationService(List.of());

    ClaimValidationRequest withRelated = new ClaimValidationRequest();
    withRelated.setClaim(Claim.builder().build());
    withRelated.setScope("fee");
    withRelated.setRelatedClaims(List.of(Claim.builder().build()));

    ClaimValidationRequest withNullRelated = new ClaimValidationRequest();
    withNullRelated.setClaim(Claim.builder().build());
    withNullRelated.setScope("fee");
    withNullRelated.setRelatedClaims(null);

    var method = ValidationService.class.getDeclaredMethod("buildValidationContext", ClaimValidationRequest.class);
    method.setAccessible(true);

    ValidationContext ctx1 = (ValidationContext) method.invoke(service, withRelated);
    ValidationContext ctx2 = (ValidationContext) method.invoke(service, withNullRelated);

    assertThat(ctx1.getRelatedClaims()).isNotEmpty();
    assertThat(ctx2.getRelatedClaims()).isEmpty();
  }

  @Test
  void validateClaim_returnsMissingClaimResultWhenClaimIsNull() {
    ClaimValidationRequest request = new ClaimValidationRequest();
    request.setClaim(null);
    request.setScope("fee");

    ValidationResult result = validationService.validateClaim(request);

    assertThat(result.getIsValid()).isFalse();
    assertThat(result.getIssues()).hasSize(1);
    assertThat(result.getIssues().getFirst().getCode()).isEqualTo("MISSING_CLAIM");
  }
}
