package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;

/**
 * Validation errors specific to submissions (as opposed to individual claims).
 */
@Getter
@RequiredArgsConstructor
public enum SubmissionValidationError implements ValidationError {

  INCORRECT_SUBMISSION_STATUS_FOR_VALIDATION(
          "Submission cannot be validated in state %s",
          null,
          ValidationSeverity.ERROR,
          null
  ),

  SUBMISSION_STATUS_IS_NULL(
          "The submission state is null",
          null,
          ValidationSeverity.ERROR,
          null
  ),

  INVALID_NIL_SUBMISSION_CONTAINS_CLAIMS(
          "Submission is marked as nil submission, but contains claims",
          null,
          ValidationSeverity.ERROR,
          null
  ),

  NON_NIL_SUBMISSION_CONTAINS_NO_CLAIMS(
          "Submission is marked as nil submission, "
                  + "but contains claims",
          null,
          ValidationSeverity.ERROR,
          null
  ),

  SUBMISSION_PERIOD_MISSING(
          "Submission period is required. Please provide "
                  + "a submission period in the format MMM-YYYY",
          null,
          ValidationSeverity.ERROR,
          null
  ),

  SUBMISSION_PERIOD_INVALID_FORMAT(
          "Submission period wrong format, should be in the format MMM-YYYY",
          null,
          ValidationSeverity.ERROR,
          null
  ),

  SUBMISSION_PERIOD_SAME_MONTH(
          "Submissions for the current month (%s) are not accepted. "
                  + "Please submit for a previous month.",
          null,
          ValidationSeverity.ERROR,
          null
  ),

  SUBMISSION_PERIOD_FUTURE_MONTH(
          "Submissions for after the current month (%s) are not accepted. "
                  + "Please submit for a previous month.",
          null,
          ValidationSeverity.ERROR,
          null
  ),

  SUBMISSION_VALIDATION_MINIMUM_PERIOD(
          "Submissions for periods before %s are not accepted. "
                  + "Please submit for a period on or after %s.",
          "null",
          ValidationSeverity.ERROR,
          null
  ),

  SUBMISSION_ALREADY_EXISTS(
          "Submission already exists for Office (%s), Area of Law (%s), Period (%s)",
          null,
          ValidationSeverity.ERROR,
          null
  );
  
  private final String displayMessage;
  private final String technicalMessage;
  private final ValidationSeverity severity;
  private final String field;
}
