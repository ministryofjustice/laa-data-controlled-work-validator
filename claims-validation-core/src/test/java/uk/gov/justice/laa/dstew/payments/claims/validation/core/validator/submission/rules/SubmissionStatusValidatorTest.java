package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uk.gov.justice.laa.dstew.payments.claims.validation.core.service.ValidationServiceTestUtils.assertContextClaimError;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationError;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionStatus;

@ExtendWith(MockitoExtension.class)
@DisplayName("Submission status validator test")
class SubmissionStatusValidatorTest {

  private SubmissionStatusValidator validator;


  @BeforeEach
  void beforeEach() {
    validator = new SubmissionStatusValidator();
  }

  @Test
  @DisplayName("Validator metadata: priority, appliesTo and code")
  void metadata() {
    assertEquals(1, validator.priority());
    assertTrue(validator.appliesTo("ANY_SCOPE"));
    assertEquals("SUBMISSION_STATUS_VALIDATOR", validator.getValidatorCode());
  }

  @Test
  @DisplayName("Should do nothing if submission status is VALIDATION_IN_PROGRESS")
  void shouldDoNothingIfSubmissionStatusIsValidationInProgress() {
    // Given
    SubmissionStatus status = SubmissionStatus.VALIDATION_IN_PROGRESS;
    SubmissionResponse submissionResponse =
        SubmissionResponse.builder().submissionId(UUID.randomUUID()).status(status).build();
    SubmissionValidationContext submissionValidationContext = new SubmissionValidationContext();
    // When
    validator.validate(submissionResponse, submissionValidationContext);
    // Then
    assertFalse(submissionValidationContext.hasErrors());
  }

  @Test
  @DisplayName(
      "Should update the submission status to IN_PROGRESS when submission status is "
          + "READY_FOR_VALIDATION")
  void shouldUpdateTheSubmissionStatusToInProgressWhenSubmissionStatusIsReadyForValidation() {
    // Given
    SubmissionStatus status = SubmissionStatus.READY_FOR_VALIDATION;
    UUID submissionId = UUID.randomUUID();
    SubmissionResponse submissionResponse =
        SubmissionResponse.builder().submissionId(submissionId).status(status).build();
    SubmissionValidationContext submissionValidationContext = new SubmissionValidationContext();
    // When
    validator.validate(submissionResponse, submissionValidationContext);
    // Then
    assertFalse(submissionValidationContext.hasErrors());
  }

  @Test
  @DisplayName("Should add errors if submission status is NULL")
  void shouldAddErrorIfSubmissionStatusIsNull() {
    // Given
    SubmissionResponse submissionResponse =
        SubmissionResponse.builder().submissionId(UUID.randomUUID()).status(null).build();
    SubmissionValidationContext submissionValidationContext = new SubmissionValidationContext();
    // When
    validator.validate(submissionResponse, submissionValidationContext);
    // Then
    assertTrue(submissionValidationContext.hasErrors());
    assertContextClaimError(
        submissionValidationContext.getIssues(), SubmissionValidationError.SUBMISSION_STATUS_IS_NULL);

  }

  @ParameterizedTest
  @EnumSource(
      value = SubmissionStatus.class,
      names = {"READY_FOR_VALIDATION", "VALIDATION_IN_PROGRESS"},
      mode = EnumSource.Mode.EXCLUDE)
  void shouldAddErrorIfSubmissionStatusUnknownValue(SubmissionStatus status) {
    // Given
    SubmissionResponse submissionResponse =
        SubmissionResponse.builder().submissionId(UUID.randomUUID()).status(status).build();
    SubmissionValidationContext submissionValidationContext = new SubmissionValidationContext();
    // When
    validator.validate(submissionResponse, submissionValidationContext);
    // Then
    assertTrue(submissionValidationContext.hasErrors());
    assertContextClaimError(
        submissionValidationContext.getIssues(),
        "Submission cannot be validated in state " + submissionResponse.getStatus());

  }
}
