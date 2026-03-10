package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;

/**
 * Validator for disbursement-related fields. Validates that disbursement VAT amounts are within
 * acceptable limits.
 */
@Component
@Slf4j
public class DisbursementsClaimValidator implements ClaimValidator {

  private static final BigDecimal MAX_VAT_LEGAL_HELP = new BigDecimal("99999.99");
  private static final BigDecimal MAX_VAT_CRIME_LOWER = new BigDecimal("999999.99");
  private static final BigDecimal MAX_VAT_MEDIATION = new BigDecimal("999999999.99");

  @Override
  public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    log.debug("Validating disbursements");

    BigDecimal vatAmount = claim.getDisbursementsVatAmount();
    if (vatAmount == null) {
      return issues; // No VAT amount to validate
    }

    AreaOfLaw areaOfLaw = claim.getAreaOfLaw();
    BigDecimal maxAllowed = getMaxVatAmount(areaOfLaw);

    if (vatAmount.compareTo(maxAllowed) > 0) {
      issues.add(ClaimValidationError.INVALID_DISBURSEMENT_VAT_AMOUNT.toValidationIssue());
    }

    log.debug("Disbursements validation completed, found {} issues", issues.size());
    return issues;
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
    // Only run for disbursement scope or all scopes
    return scope == null || "disbursement".equalsIgnoreCase(scope) || "all".equalsIgnoreCase(scope);
  }

  @Override
  public String getValidatorCode() {
    return "DISBURSEMENTS";
  }
}
