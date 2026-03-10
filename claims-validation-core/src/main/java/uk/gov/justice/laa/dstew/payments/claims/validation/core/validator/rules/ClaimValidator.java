package uk.gov.justice.laa.dstew.payments.claims.validation.service.validator.rules;

import java.util.List;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.service.validator.ValidationContext;

/**
 * Interface for validation rules that can be applied to claims.
 * Implementations should define specific business rules and return
 * any validation issues found.
 *
 * <p>This is a stateless interface - validators receive all required
 * data as parameters and return results without side effects.</p>
 */
public interface ClaimValidator {

  /**
   * Validates the given claim and returns any issues found.
   *
   * @param claim the strongly-typed Claim object
   * @param context additional context needed for validation
   * @return a list of validation issues found, or an empty list if valid
   */
  List<ValidationIssue> validate(Claim claim, ValidationContext context);

  /**
   * Returns the priority of this validator.
   * Lower values run first. Schema validation should be 0,
   * basic field validation 10-50, complex business rules 100+.
   *
   * @return the priority value
   */
  default int priority() {
    return 100;
  }

  /**
   * Returns whether this validator should run for the given scope.
   *
   * @param scope the validation scope (e.g., "fee", "disbursement")
   * @return true if this validator applies to the scope
   */
  default boolean appliesTo(String scope) {
    return true; // By default, validators apply to all scopes
  }

  /**
   * Returns a unique code identifying this validator.
   *
   * @return the validator code
   */
  String getValidatorCode();
}
