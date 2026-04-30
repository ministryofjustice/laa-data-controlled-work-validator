package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate;

import java.util.List;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

/** Strategy for validating duplicate claims for Legal Help area of law. */
public interface LegalHelpDuplicateClaimValidationStrategy
    extends DuplicateClaimValidationStrategy {

  @Override
  default List<AreaOfLaw> compatibleAreaOfLaws() {
    return List.of(AreaOfLaw.LEGAL_HELP);
  }
}
