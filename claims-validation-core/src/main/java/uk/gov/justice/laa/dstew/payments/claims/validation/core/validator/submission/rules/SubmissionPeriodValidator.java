package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules;

import java.time.YearMonth;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationError;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;

/**
 * Validates that a submission's period is valid. Submission period should be in the format
 * MMM-yyyy.
 *
 * @author Jamie Briggs
 */
@Component
@Slf4j
public class SubmissionPeriodValidator implements SubmissionValidator {

  private final String submissionValidationMinimumPeriod;
  private final YearMonth submissionValidationMinimumPeriodYearMonth;

  /**
   * Creates a new {@code SubmissionPeriodValidator}.
   *
   * @param submissionValidationMinimumPeriod the minimum submission period allowed
   */
  public SubmissionPeriodValidator(
      @Value("${submission.validation.minimum-period}") String submissionValidationMinimumPeriod) {
    this.submissionValidationMinimumPeriod = submissionValidationMinimumPeriod;
    this.submissionValidationMinimumPeriodYearMonth =
            DateUtils.parseSubmissionPeriod(submissionValidationMinimumPeriod);
  }

  /**
   * Validates that a submission's period is valid. Submission period should be in the format
   * MMM-yyyy.
   *
   * @param submission the submission to validate
   * @param context the validation context to add errors to
   */
  @Override
  public void validate(SubmissionResponse submission, SubmissionValidationContext context) {
    log.debug("Validating submission period for submission {}", submission.getSubmissionId());

    if (StringUtils.isEmpty(submission.getSubmissionPeriod())) {
      context.addValidationIssue(
              SubmissionValidationError.SUBMISSION_PERIOD_MISSING.toValidationIssue());
      return;
    }

    YearMonth enteredSubmissionPeriod =
            DateUtils.parseSubmissionPeriod(submission.getSubmissionPeriod());
    if (enteredSubmissionPeriod == null) {
      context.addValidationIssue(
              SubmissionValidationError.SUBMISSION_PERIOD_INVALID_FORMAT.toValidationIssue());
      return;
    }

    YearMonth currentMonth = DateUtils.currentYearMonth();

    if (Objects.equals(enteredSubmissionPeriod, currentMonth)) {
      context.addValidationIssue(
          SubmissionValidationError.SUBMISSION_PERIOD_SAME_MONTH
                  .toValidationIssue(DateUtils.getReadableCurrentMonth()));
    } else if (enteredSubmissionPeriod.isAfter(currentMonth)) {
      context.addValidationIssue(
          SubmissionValidationError.SUBMISSION_PERIOD_FUTURE_MONTH
                  .toValidationIssue(DateUtils.getReadableCurrentMonth()));
    } else if (enteredSubmissionPeriod.isBefore(this.submissionValidationMinimumPeriodYearMonth)) {
      context.addValidationIssue(
          SubmissionValidationError.SUBMISSION_VALIDATION_MINIMUM_PERIOD
                  .toValidationIssue(submissionValidationMinimumPeriod,
                        submissionValidationMinimumPeriod));
    }

    log.debug("Validating submission period completed for submission {}",
            submission.getSubmissionId());
  }

  /**
   * Priority of this validator (lower has higher priority).
   *
   * @return the priority
   */
  @Override
  public int priority() {
    return 10;
  }
}
