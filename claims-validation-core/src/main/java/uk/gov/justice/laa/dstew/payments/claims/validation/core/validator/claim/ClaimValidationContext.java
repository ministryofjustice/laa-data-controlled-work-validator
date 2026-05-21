package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.AbstractValidationContext;

/**
 * Context object containing request-level data needed for claim validation. This provides
 * validators with access to data that is not part of the Claim itself.
 *
 * <p>Extends {@link AbstractValidationContext} to gain the issue-accumulation capability
 * ({@link #addValidationIssue} and {@link #hasErrors()}), and inherits the {@code scope} field
 * which is common to all validation contexts.
 */
@Getter
@SuperBuilder
public class ClaimValidationContext extends AbstractValidationContext {

  /** The type of fee being claimed (e.g., "FEE", "DISBURSEMENT"). */
  private final String feeType;

  /** The fee calculation type (e.g., "HOURLY", "FIXED", "DISB_ONLY"). */
  @Setter
  private String feeCalculationType;

  @Setter
  private String authorisedCategoryOfLawCode;

  /** Other claims in the same submission (for duplicate checking). */
  @lombok.Builder.Default
  private final List<Claim> relatedClaims = List.of();
}
