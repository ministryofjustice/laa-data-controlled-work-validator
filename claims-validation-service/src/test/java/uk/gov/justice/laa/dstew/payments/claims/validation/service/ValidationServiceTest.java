package uk.gov.justice.laa.dstew.payments.claims.validation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
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

@ExtendWith(MockitoExtension.class)
class ValidationServiceTest {

  @Mock
  private ClaimValidator mockClaimValidator;

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

    when(mockClaimValidator.validate(any(), eq("fee")))
        .thenReturn(Collections.emptyList());
    when(mockExternalValidationClient.validateWithExternalServices(any()))
        .thenReturn(Collections.emptyList());

    ValidationResult result = validationService.validateClaim(request);

    assertThat(result.getIsValid()).isTrue();
    assertThat(result.getIssues()).isEmpty();
    verify(mockClaimValidator).validate(claim, "fee");
    verify(mockExternalValidationClient).validateWithExternalServices(claim);
  }

  @Test
  void validateClaim_returnsInvalidResultWhenErrorIssuesFound() {
    Map<String, Object> claim = new HashMap<>();
    ClaimValidationRequest request = ClaimValidationRequest.builder()
        .claim(claim)
        .scope("fee")
        .build();

    ValidationIssue errorIssue = ValidationIssue.builder()
        .code("FEE.MISSING_JUSTIFICATION")
        .message("Enhancement fee requires a justification.")
        .severity(ValidationIssue.SeverityEnum.ERROR)
        .build();

    when(mockClaimValidator.validate(any(), eq("fee")))
        .thenReturn(List.of(errorIssue));
    when(mockExternalValidationClient.validateWithExternalServices(any()))
        .thenReturn(Collections.emptyList());

    ValidationResult result = validationService.validateClaim(request);

    assertThat(result.getIsValid()).isFalse();
    assertThat(result.getIssues()).hasSize(1);
    assertThat(result.getIssues().get(0).getCode()).isEqualTo("FEE.MISSING_JUSTIFICATION");
  }

  @Test
  void validateClaim_returnsValidResultWithWarningsOnly() {
    Map<String, Object> claim = new HashMap<>();
    ClaimValidationRequest request = ClaimValidationRequest.builder()
        .claim(claim)
        .build();

    ValidationIssue warningIssue = ValidationIssue.builder()
        .code("CLAIM.INCOMPLETE_DATA")
        .message("Some optional fields are missing.")
        .severity(ValidationIssue.SeverityEnum.WARNING)
        .build();

    when(mockClaimValidator.validate(any(), eq(null)))
        .thenReturn(List.of(warningIssue));
    when(mockExternalValidationClient.validateWithExternalServices(any()))
        .thenReturn(Collections.emptyList());

    ValidationResult result = validationService.validateClaim(request);

    assertThat(result.getIsValid()).isTrue();
    assertThat(result.getIssues()).hasSize(1);
    assertThat(result.getIssues().get(0).getSeverity()).isEqualTo(ValidationIssue.SeverityEnum.WARNING);
  }
}
