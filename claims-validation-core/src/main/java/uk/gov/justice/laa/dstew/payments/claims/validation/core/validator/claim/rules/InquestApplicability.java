package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;

/**
 * Applicability rules shared by {@link InquestClaimValidator} and
 * {@link InquestClaimSchemaValidator}.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class InquestApplicability {

  /**
   * Whether the claim's fee code is an Inquest fee code and the caller supplied Inquest detail.
   * A {@code null} detail means the caller did not supply Inquest data (e.g. the Data Claims API
   * with its Inquests feature disabled), so no Inquest checks apply.
   */
  static boolean hasInquestDetail(Claim claim, ClaimValidationContext context) {
    return Boolean.TRUE.equals(context.getIsInquest()) && claim.getInquestDetail() != null;
  }

  /** Whether the Inquest field rules apply: an Inquest claim whose client is not means tested. */
  static boolean requiresFieldRules(Claim claim, ClaimValidationContext context) {
    return hasInquestDetail(claim, context)
        && Boolean.FALSE.equals(claim.getInquestDetail().getIsClientMeansTested());
  }
}
