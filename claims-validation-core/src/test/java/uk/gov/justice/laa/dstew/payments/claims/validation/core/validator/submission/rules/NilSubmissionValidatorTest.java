package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uk.gov.justice.laa.dstew.payments.claims.validation.core.service.ValidationServiceTestUtils.assertContextClaimError;

import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationError;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionClaim;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;

@DisplayName("NIL Submission Validator Test")
class NilSubmissionValidatorTest {

  private final NilSubmissionValidator nilSubmissionValidator = new NilSubmissionValidator();

  @Test
  @DisplayName("Should have no errors when not flagged as NIL Submission and has claims")
  void shouldHaveNoErrorsWhenNotFlaggedAsNilSubmissionAndHasClaims() {
    // Given
    SubmissionValidationContext submissionValidationContext = new SubmissionValidationContext();
    SubmissionResponse submissionResponse =
        SubmissionResponse.builder()
            .isNilSubmission(false)
            .claims(Collections.singletonList(new SubmissionClaim()))
            .build();
    // When
    nilSubmissionValidator.validate(submissionResponse, submissionValidationContext);
    // Then
    assertFalse(submissionValidationContext.hasErrors());
  }

  @Test
  @DisplayName("Should have no errors when NIL Submission is null and has claims")
  void shouldHaveNoErrorsWhenNilSubmissionIsNullAndHasClaims() {
    // Given
    SubmissionValidationContext submissionValidationContext = new SubmissionValidationContext();
    SubmissionResponse submissionResponse =
        SubmissionResponse.builder()
            .isNilSubmission(null)
            .claims(Collections.singletonList(new SubmissionClaim()))
            .build();
    // When
    nilSubmissionValidator.validate(submissionResponse, submissionValidationContext);
    // Then
    assertFalse(submissionValidationContext.hasErrors());
  }

  @Test
  @DisplayName("Should have no errors when marked as NIL Submission and has claims")
  void shouldHaveNoErrorsWhenMarkedAsNilSubmissionAndHasNoClaims() {
    // Given
    SubmissionValidationContext submissionValidationContext = new SubmissionValidationContext();
    SubmissionResponse submissionResponse =
        SubmissionResponse.builder().isNilSubmission(true).build();
    // When
    nilSubmissionValidator.validate(submissionResponse, submissionValidationContext);
    // Then
    assertFalse(submissionValidationContext.hasErrors());
  }

  @Test
  @DisplayName("Should have errors when marked as NIL submission and has claims")
  void shouldHaveErrorsWhenMarkedAsNilSubmissionAndHasClaims() {
    // Given
    SubmissionValidationContext submissionValidationContext = new SubmissionValidationContext();
    SubmissionResponse submissionResponse =
        SubmissionResponse.builder()
            .isNilSubmission(true)
            .claims(Collections.singletonList(new SubmissionClaim()))
            .build();
    // When
    nilSubmissionValidator.validate(submissionResponse, submissionValidationContext);
    // Then
    assertTrue(submissionValidationContext.hasErrors());
    assertContextClaimError(
        submissionValidationContext.getIssues(),
        SubmissionValidationError.INVALID_NIL_SUBMISSION_CONTAINS_CLAIMS);
  }

  @Test
  @DisplayName("Should have errors when not marked as NIL submission and has no claims")
  void shouldHaveErrorsWhenNotMarkedAsNilSubmissionAndHasNoClaims() {
    // Given
    SubmissionValidationContext submissionValidationContext = new SubmissionValidationContext();
    SubmissionResponse submissionResponse =
        SubmissionResponse.builder().isNilSubmission(false).build();
    // When
    nilSubmissionValidator.validate(submissionResponse, submissionValidationContext);
    // Then
    assertTrue(submissionValidationContext.hasErrors());
    assertContextClaimError(
        submissionValidationContext.getIssues(),
        SubmissionValidationError.NON_NIL_SUBMISSION_CONTAINS_NO_CLAIMS);
  }
}
