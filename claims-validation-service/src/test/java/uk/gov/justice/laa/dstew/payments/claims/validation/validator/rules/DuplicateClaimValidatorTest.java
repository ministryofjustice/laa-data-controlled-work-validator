package uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.client.DuplicateClaimClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.client.DuplicateClaimClient.DuplicateClaimInfo;
import uk.gov.justice.laa.dstew.payments.claims.validation.client.DuplicateClaimClient.DuplicateType;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ValidationContext;
@ExtendWith(MockitoExtension.class)
class DuplicateClaimValidatorTest {
  @Mock
  private DuplicateClaimClient mockDuplicateClaimClient;
  @InjectMocks
  private DuplicateClaimValidator validator;
  @Test
  void validate_returnsNoErrors_whenNoDuplicatesFound() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("id", "claim-1");
    claim.put("uniqueFileNumber", "010120/001");
    ValidationContext context = ValidationContext.builder()
        .officeAccountNumber("1A234B")
        .build();
    when(mockDuplicateClaimClient.checkForDuplicate(anyString(), any(), anyString(), any()))
        .thenReturn(Optional.empty());
    List<ValidationIssue> issues = validator.validate(claim, context);
    assertThat(issues).isEmpty();
  }
  @Test
  void validate_returnsError_whenDuplicateInAnotherSubmission() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("id", "claim-1");
    claim.put("uniqueFileNumber", "010120/001");
    ValidationContext context = ValidationContext.builder()
        .officeAccountNumber("1A234B")
        .build();
    DuplicateClaimInfo duplicate = new DuplicateClaimInfo(
        "other-claim", "other-submission", "010120/001", DuplicateType.ANOTHER_SUBMISSION);
    when(mockDuplicateClaimClient.checkForDuplicate(anyString(), any(), anyString(), any()))
        .thenReturn(Optional.of(duplicate));
    List<ValidationIssue> issues = validator.validate(claim, context);
    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).getCode())
        .isEqualTo("INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION");
  }
  @Test
  void validate_returnsNoErrors_whenNoUniqueFileNumber() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("id", "claim-1");
    ValidationContext context = ValidationContext.builder().build();
    List<ValidationIssue> issues = validator.validate(claim, context);
    assertThat(issues).isEmpty();
  }
  @Test
  void getValidatorCode_returnsDuplicateClaim() {
    assertThat(validator.getValidatorCode()).isEqualTo("DUPLICATE_CLAIM");
  }
  @Test
  void priority_returns80() {
    assertThat(validator.priority()).isEqualTo(80);
  }
}
