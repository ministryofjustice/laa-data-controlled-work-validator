package uk.gov.justice.laa.dstew.payments.claims.validation.service.validator.rules.duplicate;

import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.ClaimsApiClient;

/**
 * Duplicate claims Validation Mediation Strategy.
 *
 * <p>This was originally implemented but removed as part of BC-423. This class has been kept for
 * auditing purposes of the previous implementation, and for potentially implementing a different
 * duplicate claim strategy in the future.
 */
@Deprecated(since = "0.0.122")
// @Service - Unregistered as Spring Bean
@Slf4j
public final class DuplicateClaimMediationValidationStrategy extends DuplicateClaimValidation
    implements MediationDuplicateClaimValidationStrategy {

  public DuplicateClaimMediationValidationStrategy(ClaimsApiClient claimsApiClient) {
    super(claimsApiClient);
  }

  @Override
  public List<ValidationIssue> validateDuplicateClaims(
      Claim claim, List<Claim> submissionClaims, String officeCode, String feeType) {
    log.debug(
        "Duplicate check for Legal Help Mediation claim {} not performed as it has been removed.",
        claim.getId());
    return Collections.emptyList();
  }
}
