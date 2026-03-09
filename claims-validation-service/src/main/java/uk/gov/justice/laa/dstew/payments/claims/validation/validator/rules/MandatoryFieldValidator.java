package uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.error.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ValidationContext;

/**
 * Validator for checking mandatory fields are present in the claim.
 * This runs early in the validation chain (priority 10).
 */
@Component
@Slf4j
public class MandatoryFieldValidator implements ClaimValidator {

  @Override
  public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    log.debug("Running mandatory field validation");

    // Check always-required fields
    if (claim.getAreaOfLaw() == null) {
      issues.add(ClaimValidationError.MISSING_MANDATORY_FIELD.toValidationIssue("areaOfLaw"));
    }

    if (!hasValue(claim.getOfficeAccountNumber())) {
      issues.add(ClaimValidationError.MISSING_MANDATORY_FIELD
          .toValidationIssue("officeAccountNumber"));
    }

    // Check fee-required fields when scope includes fees
    String scope = context.getScope();
    if (scope == null || "fee".equalsIgnoreCase(scope) || "all".equalsIgnoreCase(scope)) {
      if (!hasValue(claim.getFeeCode())) {
        issues.add(ClaimValidationError.MISSING_MANDATORY_FIELD.toValidationIssue("feeCode"));
      }
    }

    // TODO: Add conditional mandatory field checks based on:
    // - areaOfLaw (context.getAreaOfLaw())
    // - feeType (from fee details)
    // - other claim properties

    log.debug("Mandatory field validation completed, found {} issues", issues.size());
    return issues;
  }

  @Override
  public int priority() {
    return 10; // Run early after schema validation
  }

  @Override
  public String getValidatorCode() {
    return "MANDATORY_FIELD";
  }

  /**
   * Checks if a string value is non-null and non-blank.
   */
  private boolean hasValue(String value) {
    return value != null && !value.isBlank();
  }
}
