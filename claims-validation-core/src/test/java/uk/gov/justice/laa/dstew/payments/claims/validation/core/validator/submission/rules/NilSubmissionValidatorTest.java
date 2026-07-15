package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uk.gov.justice.laa.dstew.payments.claims.validation.core.service.ValidationServiceTestUtils.assertContextClaimError;

import java.util.Collections;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidatorCode;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionClaim;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;

@DisplayName("NIL Submission Validator Test")
class NilSubmissionValidatorTest {

  private final NilSubmissionValidator nilSubmissionValidator = new NilSubmissionValidator();

  @DisplayName("Validator metadata: priority, appliesTo and code")
  @Test
  void metadata() {
    assertEquals(10, nilSubmissionValidator.priority());
    assertTrue(nilSubmissionValidator.appliesTo(Set.of(SubmissionValidatorCode.SUBMISSION_NIL_VALIDATOR)));
    assertEquals(SubmissionValidatorCode.SUBMISSION_NIL_VALIDATOR, nilSubmissionValidator.getValidatorCode());
  }

  @Test
  @DisplayName("Should have no errors when not flagged as NIL Submission and has claims")
  void shouldHaveNoErrorsWhenNotFlaggedAsNilSubmissionAndHasClaims() {
    // Given
    SubmissionValidationContext submissionValidationContext = SubmissionValidationContext.create();
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
    SubmissionValidationContext submissionValidationContext = SubmissionValidationContext.create();
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
  @DisplayName("Should have no errors when marked as NIL Submission and has no claims") // fixed: was "has claims"
  void shouldHaveNoErrorsWhenMarkedAsNilSubmissionAndHasNoClaims() {
    // Given
    SubmissionValidationContext submissionValidationContext = SubmissionValidationContext.create();
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
    SubmissionValidationContext submissionValidationContext = SubmissionValidationContext.create();
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
    SubmissionValidationContext submissionValidationContext = SubmissionValidationContext.create();
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

  @Test
  @DisplayName("Should have no errors when marked as NIL submission and claims list is empty")
  void shouldHaveNoErrors_nilTrue_emptyClaimsList() {
    // Branch: isNilSubmission=true, claims != null but isEmpty() → inner if is NOT entered
    SubmissionValidationContext ctx = SubmissionValidationContext.create();
    SubmissionResponse submission = SubmissionResponse.builder()
        .isNilSubmission(true)
        .claims(Collections.emptyList())
        .build();

    nilSubmissionValidator.validate(submission, ctx);

    assertFalse(ctx.hasErrors());
  }

  @Test
  @DisplayName("Should have no errors when marked as NIL submission and claims list is null")
  void shouldHaveNoErrors_nilTrue_nullClaimsList() {
    // Branch: isNilSubmission=true, claims == null
    SubmissionValidationContext ctx = SubmissionValidationContext.create();
    SubmissionResponse submission = SubmissionResponse.builder()
            .isNilSubmission(true)
            .build();

    nilSubmissionValidator.validate(submission, ctx);

    assertFalse(ctx.hasErrors());
  }

  @Test
  @DisplayName("Should have errors when not marked as NIL submission and claims list is empty")
  void shouldHaveErrors_nilFalse_emptyClaimsList() {
    // Branch: isNilSubmission=false, claims != null but isEmpty() → satisfies || isEmpty() condition
    SubmissionValidationContext ctx = SubmissionValidationContext.create();
    SubmissionResponse submission = SubmissionResponse.builder()
        .isNilSubmission(false)
        .claims(Collections.emptyList())
        .build();

    nilSubmissionValidator.validate(submission, ctx);

    assertTrue(ctx.hasErrors());
    assertContextClaimError(ctx.getIssues(),
        SubmissionValidationError.NON_NIL_SUBMISSION_CONTAINS_NO_CLAIMS);
  }

  @Test
  @DisplayName("Should have errors when not marked as NIL submission and claims list is null")
  void shouldHaveErrors_nilFalse_nullClaimsList() {
    // Branch: isNilSubmission=false, claims != null but isEmpty() → satisfies || isEmpty() condition
    SubmissionValidationContext ctx = SubmissionValidationContext.create();
    SubmissionResponse submission = SubmissionResponse.builder()
            .isNilSubmission(false)
            .build();

    nilSubmissionValidator.validate(submission, ctx);

    assertTrue(ctx.hasErrors());
    assertContextClaimError(ctx.getIssues(),
            SubmissionValidationError.NON_NIL_SUBMISSION_CONTAINS_NO_CLAIMS);
  }

  /*
    * This test covers the branch where isNilSubmission is null, which means neither the if nor the
    * else if conditions are satisfied, so no errors should be added regardless of the claims
    * state (claims can be null or not, it doesn't matter for this branch).
    * TODO: confirm if this is correct behaviour.
  */
  @Test
  @DisplayName("Should have no errors when NIL flag is null and claims is null"
      + " — neither branch is entered")
  void shouldHaveNoErrors_nilNull_noClaimsSet() {
    SubmissionValidationContext ctx = SubmissionValidationContext.create();
    SubmissionResponse submission = SubmissionResponse.builder()
        .isNilSubmission(null)
        .build(); // claims null

    nilSubmissionValidator.validate(submission, ctx);

    assertFalse(ctx.hasErrors());
  }
}
