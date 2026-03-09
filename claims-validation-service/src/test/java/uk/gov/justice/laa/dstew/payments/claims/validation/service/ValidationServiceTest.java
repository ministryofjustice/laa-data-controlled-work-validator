package uk.gov.justice.laa.dstew.payments.claims.validation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimValidationRequest;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.client.ExternalValidationClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ValidationContext;

@ExtendWith(MockitoExtension.class)
class ValidationServiceTest {

  @Mock
  private List<ClaimValidator> mockValidators;

  @Mock
  private ExternalValidationClient mockExternalValidationClient;

  @InjectMocks
  private ValidationService validationService;

  @Test
  void validateClaim_returnsValidResultWhenNoIssues() {
    Map<String, Object> claim = new HashMap<>();
    ClaimValidationRequest request = ClaimValidationRequest.builder()
        .claim(claim)
        .scope("fee")
        .build();

    when(mockValidators.stream()).thenReturn(Collections.<ClaimValidator>emptyList().stream());
    when(mockExternalValidationClient.validateWithExternalServices(any()))
        .thenReturn(Collections.emptyList());

    ValidationResult result = validationService.validateClaim(request);

    assertThat(result.getIsValid()).isTrue();
    assertThat(result.getIssues()).isEmpty();
  }

  @Test
  void validateClaim_returnsInvalidResultWhenErrorIssuesFound() {
    Map<String, Object> claim = new HashMap<>();
    ClaimValidationRequest request = ClaimValidationRequest.builder()
        .claim(claim)
        .scope("fee")
        .build();

    ClaimValidator mockValidator = new ClaimValidator() {
      @Override
      public List<ValidationIssue> validate(Map<String, Object> c, ValidationContext ctx) {
        return List.of(ValidationIssue.builder()
            .code("TEST_ERROR")
            .message("Test error message")
            .severity(ValidationIssue.SeverityEnum.ERROR)
            .build());
      }

      @Override
      public String getValidatorCode() {
        return "TEST";
      }
    };

    when(mockValidators.stream()).thenReturn(List.of(mockValidator).stream());
    when(mockExternalValidationClient.validateWithExternalServices(any()))
        .thenReturn(Collections.emptyList());

    ValidationResult result = validationService.validateClaim(request);

    assertThat(result.getIsValid()).isFalse();
    assertThat(result.getIssues()).hasSize(1);
    assertThat(result.getIssues().get(0).getCode()).isEqualTo("TEST_ERROR");
  }

  @Test
  void validateClaim_returnsValidResultWithWarningsOnly() {
    Map<String, Object> claim = new HashMap<>();
    ClaimValidationRequest request = ClaimValidationRequest.builder()
        .claim(claim)
        .build();

    ClaimValidator mockValidator = new ClaimValidator() {
      @Override
      public List<ValidationIssue> validate(Map<String, Object> c, ValidationContext ctx) {
        return List.of(ValidationIssue.builder()
            .code("TEST_WARNING")
            .message("Test warning message")
            .severity(ValidationIssue.SeverityEnum.WARNING)
            .build());
      }

      @Override
      public String getValidatorCode() {
        return "TEST";
      }
    };

    when(mockValidators.stream()).thenReturn(List.of(mockValidator).stream());
    when(mockExternalValidationClient.validateWithExternalServices(any()))
        .thenReturn(Collections.emptyList());

    ValidationResult result = validationService.validateClaim(request);

    assertThat(result.getIsValid()).isTrue();
    assertThat(result.getIssues()).hasSize(1);
    assertThat(result.getIssues().get(0).getSeverity())
        .isEqualTo(ValidationIssue.SeverityEnum.WARNING);
  }
}
