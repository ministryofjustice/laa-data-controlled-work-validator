package uk.gov.justice.laa.dstew.payments.claims.validation.core.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidation;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.ClaimValidator;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

class ValidationServiceTest {

  private Claim createTestClaim() {
    Claim claim = new Claim();
    claim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);
    claim.setOfficeAccountNumber("1A234B");
    return claim;
  }

  @Test
  void validateClaim_returnsValidResultWhenNoIssues() {
    ValidationService service = new ValidationService(new ClaimValidation(Collections.emptyList()));

    ValidationResult result = service.validateClaim(createTestClaim(), "fee");

    assertThat(result.getIsValid()).isTrue();
    assertThat(result.getIssues()).isEmpty();
  }

  @Test
  void validateClaim_returnsInvalidResultWhenErrorIssuesFound() {

    ClaimValidator mockValidator =
        new ClaimValidator() {
          @Override
          public List<ValidationIssue> validate(Claim claim, ClaimValidationContext ctx) {
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

    ValidationService service = new ValidationService(new uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidation(List.of(mockValidator)));

    ValidationResult result = service.validateClaim(createTestClaim(), "fee");

    assertThat(result.getIsValid()).isFalse();
    assertThat(result.getIssues()).hasSize(1);
    assertThat(result.getIssues().getFirst().getCode()).isEqualTo("TEST_ERROR");
  }

  @Test
  void validateClaim_returnsValidResultWithWarningsOnly() {

    ClaimValidator mockValidator =
        new ClaimValidator() {
          @Override
          public List<ValidationIssue> validate(Claim claim, ClaimValidationContext ctx) {
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

    ValidationService service = new ValidationService(new uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidation(List.of(mockValidator)));

    ValidationResult result = service.validateClaim(createTestClaim(), null);

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
      public List<ValidationIssue> validate(Claim claim, ClaimValidationContext context) {
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
      public List<ValidationIssue> validate(Claim claim, ClaimValidationContext context) {
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
      public List<ValidationIssue> validate(Claim claim, ClaimValidationContext context) {
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

    ValidationService service = new ValidationService(new uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidation(List.of(lowPriority, highPriority, excluded)));

    var result = service.validateClaim(Claim.builder().build(), "fee");

    // Both validators that apply should have been called in priority order (high then low)
    assertThat(callOrder).containsExactly("high", "low");

    // Shared identical issues should be deduplicated -> only one issue present
    assertThat(result.getIssues()).hasSize(1);

    // The single issue is a WARNING so overall validation should be considered valid
    assertThat(result.getIsValid()).isTrue();
  }

  @Test
  void buildValidationContext_passesRelatedClaims_and_handlesNullRelatedClaims() {
    AtomicReference<ClaimValidationContext> captured = new AtomicReference<>();

    ClaimValidator capturingValidator = new ClaimValidator() {
      @Override
      public List<ValidationIssue> validate(Claim claim, ClaimValidationContext context) {
        captured.set(context);
        return List.of();
      }

      @Override
      public String getValidatorCode() {
        return "CAPTURE";
      }
    };

    ValidationService service = new ValidationService(new uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidation(List.of(capturingValidator)));

    List<Claim> related = List.of(Claim.builder().uniqueFileNumber("010101/001").build());

    service.validateClaim(Claim.builder().build(),"fee", related);

    ClaimValidationContext ctx = captured.get();
    assertThat(ctx).isNotNull();
    assertThat(ctx.getRelatedClaims()).isEqualTo(related);

    // Now test when relatedClaims is null on the request object
    captured.set(null);

    service.validateClaim(Claim.builder().build(), "fee", null);

    ClaimValidationContext ctx2 = captured.get();
    assertThat(ctx2).isNotNull();
    // Should be converted to an empty list (List.of()) when null
    assertThat(ctx2.getRelatedClaims()).isEmpty();
  }

  @Test
  void privateBuildValidationContext_invokedViaReflection_coversBothBranches() throws Exception {
    uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidation service = new uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidation(List.of());

    var method = uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidation.class.getDeclaredMethod("buildValidationContext", String.class, List.class);
    method.setAccessible(true);

    ClaimValidationContext ctx1 = (ClaimValidationContext) method.invoke(service, "fee", List.of(Claim.builder().build()));
    ClaimValidationContext ctx2 = (ClaimValidationContext) method.invoke(service, "fee", null);

    assertThat(ctx1.getRelatedClaims()).isNotEmpty();
    assertThat(ctx2.getRelatedClaims()).isEmpty();
  }

  @Test
  void validateClaim_returnsMissingClaimResultWhenClaimIsNull() {

    ValidationService service = new ValidationService(new uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidation(List.of()));

    ValidationResult result = service.validateClaim(null, "fee");

    assertThat(result.getIsValid()).isFalse();
    assertThat(result.getIssues()).hasSize(1);
    assertThat(result.getIssues().getFirst().getCode()).isEqualTo("MISSING_CLAIM");
  }
}
