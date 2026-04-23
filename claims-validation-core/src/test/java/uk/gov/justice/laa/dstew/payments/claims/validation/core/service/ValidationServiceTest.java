package uk.gov.justice.laa.dstew.payments.claims.validation.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
}
