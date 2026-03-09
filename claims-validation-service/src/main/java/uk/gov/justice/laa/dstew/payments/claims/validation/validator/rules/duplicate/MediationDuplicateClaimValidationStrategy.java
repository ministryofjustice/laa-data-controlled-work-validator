package uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules.duplicate;

import java.util.List;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;

/**
 * Strategy for validating duplicate claims for Mediation area of law.
 */
public interface MediationDuplicateClaimValidationStrategy
    extends DuplicateClaimValidationStrategy {

  @Override
  default List<AreaOfLaw> compatibleAreaOfLaws() {
    return List.of(AreaOfLaw.MEDIATION);
  }
}
