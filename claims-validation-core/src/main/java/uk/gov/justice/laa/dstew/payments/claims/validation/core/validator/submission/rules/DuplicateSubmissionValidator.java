package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules;

import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidatorCode;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionBase;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionStatus;

/** Check for duplicates based on the combination of Office × Area of Law × Submission Period. */
@Slf4j
public class DuplicateSubmissionValidator implements SubmissionValidator {
  public final ClaimsDataProvider claimsDataProvider;

  public DuplicateSubmissionValidator(ClaimsDataProvider claimsDataProvider) {
    this.claimsDataProvider = claimsDataProvider;
  }

  @Override
  public void validate(SubmissionResponse submission, SubmissionValidationContext context) {

    log.debug("Validating duplicate submissions for submission {}", submission.getSubmissionId());

    if (isDuplicateSubmission(submission)) {
      context.addValidationIssue(
          SubmissionValidationError.SUBMISSION_ALREADY_EXISTS.toValidationIssue(
                  submission.getOfficeAccountNumber(),
                  submission.getAreaOfLaw(),
                  submission.getSubmissionPeriod())
      );
    }

    log.debug("Duplicate submissions check completed for submission {}",
            submission.getSubmissionId());
  }

  @Override
  public int priority() {
    return 100;
  }

  @Override
  public SubmissionValidatorCode getValidatorCode() {
    return SubmissionValidatorCode.SUBMISSION_DUPLICATE_VALIDATOR;
  }

  private boolean isDuplicateSubmission(SubmissionResponse submission) {

    final List<SubmissionBase> submissionBases =
            claimsDataProvider
            .getSubmissions(
                List.of(submission.getOfficeAccountNumber()),
                submission.getAreaOfLaw(),
                submission.getSubmissionPeriod())
            .stream()
            .filter(
                submissionBase ->
                    Objects.equals(
                        submissionBase.getStatus(), SubmissionStatus.VALIDATION_SUCCEEDED))
            .toList();

    log.debug("Found {} duplicates for submission {}", submissionBases.size(), submission);

    return !submissionBases.isEmpty();
  }
}
