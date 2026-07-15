package uk.gov.justice.laa.dstew.payments.claims.validation.core.model;

import java.io.Serial;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

/**
 * Claim-specific validation result that carries the resolved claim data discovered during
 * validation. The {@link ResolvedClaimData} is always non-null
 * (use {@link ResolvedClaimData#empty()} when no resolution was possible).
 */
@EqualsAndHashCode(callSuper = true)
@SuperBuilder(toBuilder = true)
public class ClaimValidationResult extends ValidationResult {

  @Serial
  private static final long serialVersionUID = 1L;

  @Builder.Default
  private final ResolvedClaimData resolvedData = ResolvedClaimData.empty();

  /**
   * Returns the resolved claim data. Never {@code null}: if the builder was explicitly given a
   * {@code null} value, {@link ResolvedClaimData#empty()} is returned instead so callers can rely
   * on a non-null contract.
   *
   * @return the resolved claim data; never {@code null}
   */
  public ResolvedClaimData getResolvedData() {
    return resolvedData != null ? resolvedData : ResolvedClaimData.empty();
  }
}

