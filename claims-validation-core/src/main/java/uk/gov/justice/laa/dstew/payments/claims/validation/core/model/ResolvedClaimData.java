package uk.gov.justice.laa.dstew.payments.claims.validation.core.model;

/**
 * Best-effort resolved claim data pulled as a side-effect of validation from external systems.
 *
 * <p>Always returned on a {@link ClaimValidationResult}
 * (non-null). Individual fields may be {@code null} when resolution was unsuccessful or not
 * attempted (for example when the claim was invalid).
 */
public record ResolvedClaimData(
    String feeCalculationType,
    String areaOfLaw,
    String authorisedCategoryOfLawCode) {

  /**
   * Returns a non-null instance whose fields are all null, for use when no resolution was
   * performed or when a missing claim result is returned.
   */
  public static ResolvedClaimData empty() {
    return new ResolvedClaimData(null, null, null);
  }
}
