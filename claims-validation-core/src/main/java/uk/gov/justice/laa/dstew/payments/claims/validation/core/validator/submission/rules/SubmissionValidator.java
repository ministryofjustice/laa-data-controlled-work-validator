package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules;

import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.Validator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationContext;
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

}
