package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate;

import java.util.List;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

/** Strategy for validating duplicate claims for Crime Lower area of law. */
public interface CrimeLowerDuplicateClaimValidationStrategy
    extends DuplicateClaimValidationStrategy {

  @Override
  default List<AreaOfLaw> compatibleAreaOfLaws() {
    return List.of(AreaOfLaw.CRIME_LOWER);
  }
}
