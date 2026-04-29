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

  SUBMISSION_TOO_LARGE(
      "Submission contains too many claims",
      null,
      ValidationSeverity.ERROR
  ),

  SUBMISSION_MISSING_METADATA(
      "Submission metadata is missing",
      null,
      ValidationSeverity.ERROR
  );

  private final String displayMessage;
  private final String technicalMessage;
  private final ValidationSeverity severity;
}
