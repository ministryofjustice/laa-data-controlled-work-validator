package uk.gov.justice.laa.dstew.payments.claims.validation.validator.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationSeverity;

/**
 * Utility class for date validation operations.
 * Provides common date validation methods used by multiple validators.
 */
@Slf4j
public final class DateValidationUtils {

  public static final DateTimeFormatter DATE_FORMATTER_YYYY_MM_DD =
      DateTimeFormatter.ofPattern("yyyy-MM-dd");

  private DateValidationUtils() {
    // Utility class
  }

  /**
   * Checks if a date string is in the past and after the minimum allowed date.
   *
   * @param fieldName the name of the field being validated
   * @param dateValue the date string to validate (format: yyyy-MM-dd)
   * @param minDate the minimum allowed date
   * @return a list of validation issues, empty if valid
   */
  public static List<ValidationIssue> checkDateInPast(
      String fieldName, String dateValue, LocalDate minDate) {
    List<ValidationIssue> issues = new ArrayList<>();

    if (dateValue == null || dateValue.isBlank()) {
      return issues; // Optional field - let mandatory validator handle if required
    }

    try {
      LocalDate date = LocalDate.parse(dateValue, DATE_FORMATTER_YYYY_MM_DD);

      if (date.isAfter(LocalDate.now())) {
        issues.add(new ValidationIssue(
            "INVALID_" + toErrorCode(fieldName),
            fieldName + " must be in the past",
            ValidationSeverity.ERROR));
      } else if (date.isBefore(minDate)) {
        issues.add(new ValidationIssue(
            "INVALID_" + toErrorCode(fieldName),
            fieldName + " must be after " + minDate,
            ValidationSeverity.ERROR));
      }
    } catch (DateTimeParseException e) {
      log.debug("Failed to parse date for {}: {}", fieldName, dateValue);
      issues.add(new ValidationIssue(
          "INVALID_" + toErrorCode(fieldName) + "_FORMAT",
          fieldName + " has an invalid date format",
          ValidationSeverity.ERROR));
    }

    return issues;
  }

  /**
   * Checks if a date is not in the future and is within an allowed period.
   *
   * @param fieldName the name of the field being validated
   * @param dateValue the date string to validate
   * @param earliestAllowed the earliest allowed date
   * @return a list of validation issues, empty if valid
   */
  public static List<ValidationIssue> checkDateNotInFutureAndWithinAllowedPeriod(
      String fieldName, String dateValue, LocalDate earliestAllowed) {
    List<ValidationIssue> issues = new ArrayList<>();

    if (dateValue == null || dateValue.isBlank()) {
      return issues;
    }

    try {
      LocalDate date = LocalDate.parse(dateValue, DATE_FORMATTER_YYYY_MM_DD);

      if (date.isAfter(LocalDate.now())) {
        issues.add(new ValidationIssue(
            "INVALID_" + toErrorCode(fieldName),
            fieldName + " cannot be in the future",
            ValidationSeverity.ERROR));
      } else if (date.isBefore(earliestAllowed)) {
        issues.add(new ValidationIssue(
            "INVALID_" + toErrorCode(fieldName),
            fieldName + " must be on or after " + earliestAllowed,
            ValidationSeverity.ERROR));
      }
    } catch (DateTimeParseException e) {
      log.debug("Failed to parse date for {}: {}", fieldName, dateValue);
      issues.add(new ValidationIssue(
          "INVALID_" + toErrorCode(fieldName) + "_FORMAT",
          fieldName + " has an invalid date format",
          ValidationSeverity.ERROR));
    }

    return issues;
  }

  /**
   * Gets a string value from a claim map.
   *
   * @param claim the claim map
   * @param fieldName the field name
   * @return the string value or null
   */
  public static String getStringValue(Map<String, Object> claim, String fieldName) {
    Object value = claim.get(fieldName);
    return value != null ? value.toString() : null;
  }

  /**
   * Converts a field name to an error code format.
   * e.g., "Case Start Date" -> "CASE_START_DATE"
   */
  private static String toErrorCode(String fieldName) {
    return fieldName.toUpperCase().replaceAll("\\s+", "_");
  }
}
