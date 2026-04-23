package uk.gov.justice.laa.dstew.payments.claims.validation.core.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Represents the severity level of a validation issue within the internal validation system.
 *
 * <p>
 * This enum is for internal use only and is not intended for API serialization
 * or external consumption.
 */
@Getter
@RequiredArgsConstructor
public enum ValidationSeverity {

  /**
   * Indicates a critical validation error that must be addressed.
   */
  ERROR("ERROR"),

  /**
   * Indicates a warning that does not prevent processing but should be reviewed.
   */
  WARNING("WARNING"),

  /**
   * Provides informational feedback about the validation process.
   */
  INFO("INFO");

  /**
   * String value associated with this severity.
   */
  private final String value;

  @Override
  public String toString() {
    return value;
  }
}
