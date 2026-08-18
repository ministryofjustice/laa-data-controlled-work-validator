package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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
  
  /**
   * Statuses that leave a submission "live" for duplicate-detection purposes. A submission counts
   * as a potential duplicate unless it has reached one of these terminal states. This mirrors the
   * partial unique index on the Data Claims API database, which enforces uniqueness of (office,
   * area of law, submission period) across every status except these.
   */
  private static final Set<SubmissionStatus> NON_BLOCKING_STATUSES =
      Set.of(SubmissionStatus.VALIDATION_FAILED, SubmissionStatus.REPLACED);

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

    final List<SubmissionBase> duplicates =
        claimsDataProvider
            .getSubmissions(
                List.of(submission.getOfficeAccountNumber()),
                submission.getAreaOfLaw(),
                submission.getSubmissionPeriod())
            .stream()
            .filter(candidate -> isDifferentSubmission(candidate, submission))
            .filter(this::isLiveSubmission)
            .filter(candidate -> isCreatedBeforeOrUndated(candidate, submission))
            .toList();

    log.debug("Found {} duplicates for submission {}", duplicates.size(), submission);

    return !duplicates.isEmpty();
  }

  private boolean isDifferentSubmission(SubmissionBase candidate, SubmissionResponse submission) {
    return !Objects.equals(candidate.getSubmissionId(), submission.getSubmissionId());
  }

  private boolean isLiveSubmission(SubmissionBase candidate) {
    return !NON_BLOCKING_STATUSES.contains(candidate.getStatus());
  }

  /**
   * Determines whether an existing submission should block the one under validation based on
   * creation order.
   *
   * <p>The {@code submitted} timestamp is the value persisted as {@code created_on} on the
   * submission record. Submissions created strictly earlier are treated as duplicates, which lets
   * the earliest of a set of concurrently submitted duplicates proceed while the later ones are
   * rejected.
   *
   * <p>If either {@code created_on} is missing the candidate is treated as a blocking duplicate
   * (fail-safe): we cannot establish that the candidate was created later, and the partial unique
   * index on the Data Claims API database would reject this submission regardless of ordering.
   * Accepting on a missing timestamp would only defer the failure to a harder-to-diagnose database
   * constraint violation.
   */
  private boolean isCreatedBeforeOrUndated(
      SubmissionBase candidate, SubmissionResponse submission) {
    OffsetDateTime candidateCreatedOn = candidate.getSubmitted();
    OffsetDateTime submissionCreatedOn = submission.getSubmitted();
    if (candidateCreatedOn == null || submissionCreatedOn == null) {
      return true;
    }
    return candidateCreatedOn.isBefore(submissionCreatedOn);
  }
}
