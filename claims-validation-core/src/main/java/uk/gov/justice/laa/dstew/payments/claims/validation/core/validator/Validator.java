package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator;

import java.util.Set;

/**
 * Generic validator interface used by concrete validator types (claim, submission, etc.).
 *
 * <p>This interface intentionally does not provide default implementations. Implementations
 * must provide all method implementations so that behaviour and metadata (priority, scope,
 * and validator code) are explicitly defined by each validator.
 *
 * @param <T> the type being validated
 * @param <C> the validation context type
 */
public interface Validator<T, C> {

  /**
   * Validate the provided object and add any validation issues to the provided context.
   *
   * @param subject the object to validate
   * @param context the validation context
   */
  void validate(T subject, C context);

  /**
   * The priority of this validator. Lower values run first. Implementations MUST return a
   * concrete integer value.
   *
   * @return priority integer
   */
  int priority();

  /**
   * Whether this validator should run for the given scope.
   *
   * @param scope the validation scope (for example, "fee" or "disbursement")
   * @return true if this validator should run for the scope
   */
  default boolean appliesTo(Set<String> scope) {
    return scope == null || scope.isEmpty() || scope.contains(getValidatorCode());
  }

  /**
   * A unique code identifying this validator.
   *
   * @return validator code
   */
  String getValidatorCode();
}
