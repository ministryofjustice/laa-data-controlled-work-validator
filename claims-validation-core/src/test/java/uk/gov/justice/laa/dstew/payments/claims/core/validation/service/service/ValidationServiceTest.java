package uk.gov.justice.laa.dstew.payments.claims.validation.service.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimValidationRequest;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.service.validator.ValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.service.validator.rules.ClaimValidator;

@ExtendWith(MockitoExtension.class)
class ValidationServiceTest {

  @Mock
  private List<ClaimValidator> mockValidators;

  @InjectMocks
  private ValidationService validationService;

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

    ClaimValidator mockValidator = new ClaimValidator() {
      @Override
      public List<ValidationIssue> validate(Claim claim, ValidationContext ctx) {
        return List.of(new ValidationIssue(
            "TEST_ERROR",
            "Test error message",
            ValidationSeverity.ERROR));
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

    ClaimValidator mockValidator = new ClaimValidator() {
      @Override
      public List<ValidationIssue> validate(Claim claim, ValidationContext ctx) {
        return List.of(new ValidationIssue(
            "TEST_WARNING",
            "Test warning message",
            ValidationSeverity.WARNING));
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
    assertThat(result.getIssues().getFirst().getSeverity())
        .isEqualTo(ValidationSeverity.WARNING);
  }
}
