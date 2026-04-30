package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationError;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;

/**
 * Validates that a submission's nil flag is set correctly.
 *
 * <p>Validation on this component includes:
 *
 * <ul>
 *   <li>If the submission is nil, it must not contain any claims
 *   <li>If the submission is not nil, it must contain at least one claim
 * </ul>
 *
 * @author Jamie Briggs
 */
@Component
@Slf4j
public class NilSubmissionValidator implements SubmissionValidator {

  @Override
  public void validate(final SubmissionResponse submission, SubmissionValidationContext context) {
    log.debug("Validating nil submission for submission {}", submission.getSubmissionId());

    if (Boolean.TRUE.equals(submission.getIsNilSubmission())) {
      if (submission.getClaims() != null && !submission.getClaims().isEmpty()) {
        context.addValidationIssue(
            SubmissionValidationError.INVALID_NIL_SUBMISSION_CONTAINS_CLAIMS.toValidationIssue());
      }
    } else if (Boolean.FALSE.equals(submission.getIsNilSubmission())
        && (submission.getClaims() == null || submission.getClaims().isEmpty())) {
      context.addValidationIssue(
          SubmissionValidationError.NON_NIL_SUBMISSION_CONTAINS_NO_CLAIMS.toValidationIssue());
    }

    log.debug("Nil submission completed for submission {}", submission.getSubmissionId());
  }

  @Override
  public int priority() {
    return 10;
  }
}
