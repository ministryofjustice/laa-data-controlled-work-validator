package uk.gov.justice.laa.dstew.payments.claims.validation.validator;

import java.util.List;
import java.util.Map;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;

/**
 * Interface for validation rules that can be applied to claims.
 * Implementations should define specific business rules.
 */
public interface ValidationRule {

  /**
   * Applies this validation rule to the given claim.
   *
   * @param claim the claim to validate (as a Map)
   * @return a list of validation issues found, or an empty list if valid
   */
  List<ValidationIssue> apply(Map<String, Object> claim);

  /**
   * Returns the scope(s) this rule applies to.
   * Return null or empty list if this rule applies to all scopes.
   *
   * @return the list of applicable scopes
   */
  List<String> getApplicableScopes();

  /**
   * Returns a unique code identifying this rule.
   *
   * @return the rule code
   */
  String getRuleCode();
}
