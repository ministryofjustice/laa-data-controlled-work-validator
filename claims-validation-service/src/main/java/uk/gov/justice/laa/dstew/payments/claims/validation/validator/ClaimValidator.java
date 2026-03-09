package uk.gov.justice.laa.dstew.payments.claims.validation.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;

/**
 * Validator for applying business rules to claims.
 */
@Component
@Slf4j
public class ClaimValidator {

  /**
   * Validates the given claim based on the provided scope.
   *
   * @param claim the claim to validate (as a Map)
   * @param scope the optional validation scope (e.g., "fee")
   * @return a list of validation issues found
   */
  public List<ValidationIssue> validate(Map<String, Object> claim, String scope) {
    List<ValidationIssue> issues = new ArrayList<>();

    log.debug("Running validation for scope: {}", scope);

    // TODO: Implement business rules based on scope
    // Apply fee-specific rules if scope is "fee"
    if ("fee".equalsIgnoreCase(scope)) {
      issues.addAll(validateFeeRules(claim));
    }

    // TODO: Add additional scope-based validation rules here
    // Apply general claim validation rules
    issues.addAll(validateGeneralRules(claim));

    return issues;
  }

  /**
   * Validates fee-specific business rules.
   *
   * @param claim the claim to validate
   * @return a list of fee-related validation issues
   */
  private List<ValidationIssue> validateFeeRules(Map<String, Object> claim) {
    List<ValidationIssue> issues = new ArrayList<>();

    // TODO: Implement fee validation rules
    // Example: Check for missing justification on enhancement fees
    // if (claim has enhancement fee without justification) {
    //   issues.add(ValidationIssue.builder()
    //       .code("FEE.MISSING_JUSTIFICATION")
    //       .message("Enhancement fee requires a justification.")
    //       .path(List.of("fees", 0, "justification"))
    //       .severity(ValidationIssue.SeverityEnum.ERROR)
    //       .build());
    // }

    log.debug("Fee validation completed, found {} issues", issues.size());
    return issues;
  }

  /**
   * Validates general claim business rules.
   *
   * @param claim the claim to validate
   * @return a list of general validation issues
   */
  private List<ValidationIssue> validateGeneralRules(Map<String, Object> claim) {
    List<ValidationIssue> issues = new ArrayList<>();

    // TODO: Implement general validation rules
    // Example: Check for required fields, date consistency, etc.

    log.debug("General validation completed, found {} issues", issues.size());
    return issues;
  }
}
