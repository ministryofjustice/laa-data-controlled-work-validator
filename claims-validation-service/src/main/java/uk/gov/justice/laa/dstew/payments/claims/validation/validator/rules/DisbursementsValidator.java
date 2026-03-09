package uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ValidationContext;

/**
 * Validator for disbursement-related fields.
 * Validates that disbursement VAT amounts are within acceptable limits.
 */
@Component
@Slf4j
public class DisbursementsValidator implements ClaimValidator {

  private static final BigDecimal MAX_VAT_LEGAL_HELP = new BigDecimal("99999.99");
  private static final BigDecimal MAX_VAT_CRIME_LOWER = new BigDecimal("999999.99");
  private static final BigDecimal MAX_VAT_MEDIATION = new BigDecimal("999999999.99");

  @Override
  public List<ValidationIssue> validate(Map<String, Object> claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    log.debug("Validating disbursements");

    Object vatAmountObj = claim.get("disbursementsVatAmount");
    if (vatAmountObj == null) {
      return issues; // No VAT amount to validate
    }

    BigDecimal vatAmount = toBigDecimal(vatAmountObj);
    if (vatAmount == null) {
      issues.add(ValidationIssue.builder()
          .code("INVALID_DISBURSEMENT_VAT_FORMAT")
          .message("Disbursements VAT Amount has an invalid format")
          .severity(ValidationIssue.SeverityEnum.ERROR)
          .build());
      return issues;
    }

    BigDecimal maxAllowed = getMaxVatAmount(context.getAreaOfLaw());

    if (vatAmount.compareTo(maxAllowed) > 0) {
      issues.add(ValidationIssue.builder()
          .code("INVALID_DISBURSEMENT_VAT_AMOUNT")
          .message("Disbursements VAT Amount has exceeded the maximum accepted value")
          .severity(ValidationIssue.SeverityEnum.ERROR)
          .build());
    }

    log.debug("Disbursements validation completed, found {} issues", issues.size());
    return issues;
  }

  private BigDecimal getMaxVatAmount(String areaOfLaw) {
    if (areaOfLaw == null) {
      return MAX_VAT_LEGAL_HELP; // Default
    }

    return switch (areaOfLaw.toUpperCase()) {
      case "CRIME_LOWER" -> MAX_VAT_CRIME_LOWER;
      case "MEDIATION" -> MAX_VAT_MEDIATION;
      default -> MAX_VAT_LEGAL_HELP;
    };
  }

  private BigDecimal toBigDecimal(Object value) {
    try {
      if (value instanceof BigDecimal) {
        return (BigDecimal) value;
      } else if (value instanceof Number) {
        return BigDecimal.valueOf(((Number) value).doubleValue());
      } else if (value instanceof String) {
        return new BigDecimal((String) value);
      }
    } catch (NumberFormatException e) {
      log.debug("Failed to convert to BigDecimal: {}", value);
    }
    return null;
  }

  @Override
  public int priority() {
    return 40;
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

