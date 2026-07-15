package uk.gov.justice.laa.dstew.payments.claims.validation.core.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * Claim-specific validation result that carries the resolved claim data discovered during
 * validation. The {@link ResolvedClaimData} is always non-null
 * (use {@link ResolvedClaimData#empty()} when no resolution was possible).
 */
@Getter
@SuperBuilder(toBuilder = true)
public class ClaimValidationResult extends ValidationResult {

  @lombok.Builder.Default
  private final ResolvedClaimData resolvedData = ResolvedClaimData.empty();
}

