package uk.gov.justice.laa.dstew.payments.claims.validation.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.FeeCalculationType;

/** Utility class for fee type related operations. */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FeeTypeUtils {

  /**
   * Checks if the fee type indicates a disbursement-only claim.
   *
   * @param feeType the fee calculation type value
   * @return true if this is a disbursement-only claim
   */
  public static boolean isDisbursementClaim(String feeType) {
    return FeeCalculationType.DISB_ONLY.getValue().equals(feeType);
  }
}
