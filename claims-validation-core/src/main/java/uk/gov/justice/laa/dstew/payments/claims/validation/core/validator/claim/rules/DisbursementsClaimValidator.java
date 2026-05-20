package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

/**
 * Validator for disbursement-related fields. Validates that disbursement VAT amounts are within
 * acceptable limits.
 */
@Slf4j
public class DisbursementsClaimValidator implements ClaimValidator {

  private static final BigDecimal MAX_VAT_LEGAL_HELP = new BigDecimal("99999.99");
  private static final BigDecimal MAX_VAT_CRIME_LOWER = new BigDecimal("999999.99");
  private static final BigDecimal MAX_VAT_MEDIATION = new BigDecimal("999999999.99");

  @Override
  public void validate(Claim claim, ClaimValidationContext context) {

    log.debug("Validating disbursements");

    BigDecimal vatAmount = claim.getDisbursementsVatAmount();
    if (vatAmount == null) {
      return; // No VAT amount to validate
    }

    AreaOfLaw areaOfLaw = claim.getAreaOfLaw();
    BigDecimal maxAllowed = getMaxVatAmount(areaOfLaw);

    if (vatAmount.compareTo(maxAllowed) > 0) {
      context.addValidationIssue(
              ClaimValidationError.INVALID_DISBURSEMENT_VAT_AMOUNT.toValidationIssue());
    }

    log.debug("Disbursements validation completed, found {} issues", context.getIssues().size());
  }

  private BigDecimal getMaxVatAmount(AreaOfLaw areaOfLaw) {
    if (areaOfLaw == null) {
      return MAX_VAT_LEGAL_HELP; // Default
    }

    return switch (areaOfLaw) {
      case CRIME_LOWER -> MAX_VAT_CRIME_LOWER;
      case MEDIATION -> MAX_VAT_MEDIATION;
      default -> MAX_VAT_LEGAL_HELP;
    };
  }

  @Override
  public int priority() {
    return 100; // Standard field validation priority
  }

  @Override
  public boolean appliesTo(String scope) {
    return true;
  }

  @Override
  public String getValidatorCode() {
    return "CLAIM_DISBURSEMENTS";
  }
}
