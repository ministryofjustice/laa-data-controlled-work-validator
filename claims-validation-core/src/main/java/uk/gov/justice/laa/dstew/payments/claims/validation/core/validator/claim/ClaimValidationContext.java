package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.AbstractValidationContext;

/**
 * Context object containing request-level data needed for claim validation. This provides
 * validators with access to data that is not part of the Claim itself.
 *
 * <p>Extends {@link AbstractValidationContext} to gain the issue-accumulation capability
 * ({@link #addValidationIssue} and {@link #hasErrors()}), which will be used from Stage 2
 * of the context-based validation refactor onwards.
 */
@Getter
@Builder
public class ClaimValidationContext extends AbstractValidationContext {

  /**
   * The validation scope (e.g., "fee", "disbursement", "all"). Validators can use this to determine
   * if they should run.
   */
  private final String scope;

  /** The type of fee being claimed (e.g., "FEE", "DISBURSEMENT"). */
  private final String feeType;

  /** The fee calculation type (e.g., "HOURLY", "FIXED", "DISB_ONLY"). */
  @Setter
  private String feeCalculationType;

  @Setter
  private String authorisedCategoryOfLawCode;

  /** Other claims in the same submission (for duplicate checking). */
  @Builder.Default
  private final List<Claim> relatedClaims = List.of();
}
