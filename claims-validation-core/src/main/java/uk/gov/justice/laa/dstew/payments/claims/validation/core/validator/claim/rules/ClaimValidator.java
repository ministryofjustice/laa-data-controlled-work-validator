package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.Validator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;

/**
 * Interface for validation rules that can be applied to claims. Implementations should define
 * specific business rules and return any validation issues found.
 *
 * <p>This is a stateless interface - validators receive all required data as parameters and return
 * results without side effects.
 */
public interface ClaimValidator extends Validator<Claim, ClaimValidationContext> {

}
