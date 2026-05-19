package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.schema;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;

/**
 * Shared validation error codes for JSON schema validation. Used by all schema validators
 * regardless of the domain object being validated (e.g. {@code Claim}, {@code Summary}).
 *
 * <p>These codes are intentionally domain-agnostic — they describe structural/schema
 * conformance failures, not business rule failures. Domain-specific error codes belong in
 * their own enums (e.g. {@code ClaimValidationError}).
 */
@Getter
@RequiredArgsConstructor
public enum SchemaValidationError implements ValidationError {

  /**
   * A field value does not conform to the schema constraints (pattern, type, range, etc.).
   * The display and technical message are supplied dynamically per field at the call site.
   */
  SCHEMA_VALIDATION_ERROR(
      "%s",
      "%s",
      ValidationSeverity.ERROR,
      null
  ),

  /**
   * A field exists on the domain object but is not defined in the schema. This indicates the
   * schema file needs to be updated and is surfaced as a warning rather than a hard error.
   */
  SCHEMA_CONFIG_WARNING(
      "Schema configuration warning: field(s) not defined in schema",
      "Update the schema file to add validation rules for these fields",
      ValidationSeverity.WARNING,
      null
  );

  private final String displayMessage;
  private final String technicalMessage;
  private final ValidationSeverity severity;
  private final String field;
}
