package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules;

import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationContext;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;

/**
 * Interface for a submission validator. Implementations should be annotated with @Component.
 *
 * @author Jamie Briggs
 */
public interface SubmissionValidator {

  /**
   * Validates a submission.
   *
   * @param submission the submission to validate
   * @param context the validation context to add errors to
   */
  void validate(final SubmissionResponse submission, SubmissionValidationContext context);

  /**
   * The priority of the validator. Lower values are run first.
   *
   * @return the priority
   */
  int priority();
}
