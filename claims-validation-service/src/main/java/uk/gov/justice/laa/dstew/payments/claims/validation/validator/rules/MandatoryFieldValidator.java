package uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.error.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ValidationContext;

/**
 * Validator for checking mandatory fields are present in the claim.
 * This runs early in the validation chain (priority 10).
 */
@Component
@Slf4j
public class MandatoryFieldValidator implements ClaimValidator {

  private static final List<String> ALWAYS_REQUIRED_FIELDS = List.of(
      "feeCode"
  );

  // TODO: Add more mandatory fields based on area of law and fee type

  @Override
  public List<ValidationIssue> validate(Map<String, Object> claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    log.debug("Running mandatory field validation");

    // Check always-required fields
    for (String field : ALWAYS_REQUIRED_FIELDS) {
      if (!hasValue(claim, field)) {
        issues.add(ClaimValidationError.MISSING_MANDATORY_FIELD
            .toValidationIssue(field));
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
   * Checks if a field has a non-null, non-empty value.
   */
  private boolean hasValue(Map<String, Object> claim, String field) {
    Object value = claim.get(field);
    if (value == null) {
      return false;
    }
    if (value instanceof String) {
      return !((String) value).isBlank();
    }
    return true;
  }
}

