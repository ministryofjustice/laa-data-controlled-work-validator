package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;

/**
 * Context object containing request-level data needed for claim validation. This provides
 * validators with access to data that is not part of the Claim itself.
 */
@Getter
@Builder
public class ValidationContext {

  /**
   * The validation scope (e.g., "fee", "disbursement", "all"). Validators can use this to determine
   * if they should run.
   */
  private final String scope;

  /** The type of fee being claimed (e.g., "FEE", "DISBURSEMENT"). */
  private final String feeType;

  /** The fee calculation type (e.g., "HOURLY", "FIXED", "DISB_ONLY"). */
  private final String feeCalculationType;

  /** Other claims in the same submission (for duplicate checking). */
  @Builder.Default private final List<Claim> relatedClaims = List.of();
  
}
