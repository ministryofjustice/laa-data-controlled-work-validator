package uk.gov.justice.laa.dstew.payments.claims.validation.core.model;

import java.io.Serial;
import java.io.Serializable;

/**
 * Best-effort resolved claim data pulled as a side-effect of validation from external systems.
 *
 * <p>Always returned on a {@link ClaimValidationResult}
 * (non-null). Individual fields may be {@code null} when resolution was unsuccessful or not
 * attempted (for example when the claim was invalid).
 *
 * @param feeCalculationType the fee calculation type resolved from the fee-scheme API
 * @param feeSchemeAreaOfLaw the area of law as stored by the fee-scheme API for the fee code.
 *     This is distinct from {@link Claim#getAreaOfLaw()}, which is the area of law declared on the
 *     claim itself; this value is the one held against the fee code in the fee-scheme platform.
 * @param authorisedCategoryOfLawCode the authorised category of law code resolved during validation
 * @param feeCodeDescription the fee code description resolved during validation
 */
public record ResolvedClaimData(
    String feeCalculationType,
    String feeSchemeAreaOfLaw,
    String authorisedCategoryOfLawCode,
    String feeCodeDescription) implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  /**
   * Returns a non-null instance whose fields are all null, for use when no resolution was
   * performed or when a missing claim result is returned.
   */
  public static ResolvedClaimData empty() {
    return new ResolvedClaimData(null, null, null, null);
  }
}
