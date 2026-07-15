package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules;

import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.Validator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidatorCode;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;

/**
 * Interface for a submission validator. Implementations should be annotated with @Component.
 *
 * <p>
 * Submissions validators now extend the shared {@link Validator} interface and therefore must
 * implement all required methods (validate, priority, appliesTo and getValidatorCode).
 *
 * @author Jamie Briggs
 */
public interface SubmissionValidator extends
        Validator<SubmissionResponse, SubmissionValidationContext> {

  /**
   * Submission validators are identified by a {@link SubmissionValidatorCode}. The return type
   * narrows the generic {@link Validator#getValidatorCode()} contract so submission validators
   * cannot accidentally report a claim-scoped code.
   *
   * @return the submission validator code identifying this validator
   */
  @Override
  SubmissionValidatorCode getValidatorCode();
}
