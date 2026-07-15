package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.Validator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidatorCode;

/**
 * Interface for validation rules that can be applied to claims. Implementations should define
 * specific business rules and return any validation issues found.
 *
 * <p>This is a stateless interface - validators receive all required data as parameters and return
 * results without side effects.
 */
public interface ClaimValidator extends Validator<Claim, ClaimValidationContext> {

  /**
   * Claim validators are identified by a {@link ClaimValidatorCode}. The return type narrows the
   * generic {@link Validator#getValidatorCode()} contract so claim validators cannot accidentally
   * report a submission-scoped code.
   *
   * @return the claim validator code identifying this validator
   */
  @Override
  ClaimValidatorCode getValidatorCode();
}
