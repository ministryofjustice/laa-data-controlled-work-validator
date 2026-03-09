package uk.gov.justice.laa.dstew.payments.claims.validation.validator.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.error.ClaimValidationError;

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
        issues.add(getDateError(fieldName, "must be in the past"));
      } else if (date.isBefore(minDate)) {
        issues.add(getDateError(fieldName, "must be after " + minDate));
      }
    } catch (DateTimeParseException e) {
      log.debug("Failed to parse date for {}: {}", fieldName, dateValue);
      issues.add(ClaimValidationError.INVALID_DATE_FORMAT.toValidationIssue(fieldName));
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
        issues.add(getDateError(fieldName, "cannot be in the future"));
      } else if (date.isBefore(earliestAllowed)) {
        issues.add(getDateError(fieldName, "must be on or after " + earliestAllowed));
      }
    } catch (DateTimeParseException e) {
      log.debug("Failed to parse date for {}: {}", fieldName, dateValue);
      issues.add(ClaimValidationError.INVALID_DATE_FORMAT.toValidationIssue(fieldName));
    }

    return issues;
  }

  /**
   * Gets the appropriate date validation error for a field.
   */
  private static ValidationIssue getDateError(String fieldName, String reason) {
    return switch (fieldName) {
      case "Case Start Date" -> ClaimValidationError.INVALID_CASE_START_DATE.toValidationIssue(reason);
      case "Case Concluded Date" -> ClaimValidationError.INVALID_CASE_CONCLUDED_DATE.toValidationIssue(reason);
      case "Transfer Date" -> ClaimValidationError.INVALID_TRANSFER_DATE.toValidationIssue(reason);
      case "Representation Order Date" -> ClaimValidationError.INVALID_REPRESENTATION_ORDER_DATE.toValidationIssue(reason);
      case "Client Date of Birth", "Client 2 Date of Birth" -> ClaimValidationError.INVALID_CLIENT_DATE_OF_BIRTH.toValidationIssue(reason);
      default -> ClaimValidationError.INVALID_DATE_FORMAT.toValidationIssue(fieldName + " " + reason);
    };
  }
}
