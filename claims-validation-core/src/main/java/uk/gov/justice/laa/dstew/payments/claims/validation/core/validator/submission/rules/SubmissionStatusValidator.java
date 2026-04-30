package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationError;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionStatus;

/**
 * TODO: Examine this class. it was not really validating anything, just logging
 * and updating the submission status. This is a state management class not a validator.
 * Consider moving the status update logic to a service and leaving this class to
 * just validate the status and log appropriately.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SubmissionStatusValidator implements SubmissionValidator {

  @Override
  public void validate(final SubmissionResponse submission, SubmissionValidationContext context) {
    SubmissionStatus currentStatus = submission.getStatus();
    UUID submissionId = submission.getSubmissionId();
    switch (currentStatus) {
      case VALIDATION_IN_PROGRESS ->
          log.debug(
              "Submission {} already under validation. Attempting to complete validation.",
              submissionId);
      case READY_FOR_VALIDATION ->
          log.debug(
              "Submission {} ready for validation. Updating status to VALIDATION_IN_PROGRESS.",
              submissionId);
      case null -> {
        log.debug("Submission {} state is null", submissionId);
        context.addValidationError(
                SubmissionValidationError.SUBMISSION_STATUS_IS_NULL
                        .toValidationIssue());
      }
      default -> {
        log.debug(
            "Submission {} cannot be validated in its current state: {}",
            submissionId,
            currentStatus);
        context.addValidationError(
            SubmissionValidationError.INCORRECT_SUBMISSION_STATUS_FOR_VALIDATION
                    .toValidationIssue(currentStatus));
      }
    }
  }

  @Override
  public int priority() {
    return 1;
  }
}
