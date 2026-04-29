package uk.gov.justice.laa.dstew.payments.claims.validation.core.util;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;

/**
 * Utility class for date validation and parsing operations used by multiple claim validators.
 *
 * <p>Provides methods for:
 *
 * <ul>
 *   <li>Validating date ranges and formats
 *   <li>Checking if dates are in the past, not in the future, or within a submission period
 *   <li>Parsing submission periods and date strings
 *   <li>Formatting dates for display
 * </ul>
 *
 * <p>All methods are static and the class cannot be instantiated.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtils {

  private static final LocalDate MIN_BIRTH_DATE = LocalDate.of(1900, 1, 1);

  /**
   * The number of calendar months that must separate two submission periods for a disbursement
   * claim to fall outside the duplicate-detection window.
   */
  public static final int MAXIMUM_MONTHS_DIFFERENCE = 3;

  /**
   * The number of months added to the base period when calculating the submission cutoff date. The
   * cutoff falls in the month <em>following</em> the base period.
   */
  private static final int CUTOFF_MONTH_OFFSET = 1;

  /**
   * The day of the month on which the submission cutoff falls. Disbursement claims must be
   * submitted by the {@value}th of the cutoff month.
   */
  private static final int CUTOFF_DAY_OF_MONTH = 20;

  public static final DateTimeFormatter DATE_FORMATTER_YYYY_MM_DD =
      DateTimeFormatter.ofPattern("yyyy-MM-dd");
  public static final DateTimeFormatter DATE_FORMATTER_FOR_DISPLAY_MESSAGE =
      DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static final DateTimeFormatter SUBMISSION_PERIOD_FORMATTER =
      new DateTimeFormatterBuilder()
          .parseCaseInsensitive()
          .appendPattern("MMM-yyyy")
          .toFormatter(Locale.ENGLISH);

  /**
   * Checks if the given date is non-null.
   *
   * @param date the date to check
   * @return true if the date is not null, false otherwise
   */
  public static boolean isValidDate(LocalDate date) {
    return date != null;
  }

  /**
   * Checks if the given date of birth is valid (not null and within allowed range).
   *
   * @param date the date of birth to check
   * @return true if the date is valid and within the allowed range, false otherwise
   */
  public static boolean isValidDateOfBirth(LocalDate date) {
    return isValidDate(date) && isDateWithinRange(date, MIN_BIRTH_DATE, LocalDate.now());
  }

  /**
   * Checks if a date is within the specified range (inclusive).
   *
   * @param date the date to check
   * @param earliestDateAllowed the earliest allowed date (inclusive)
   * @param latestDateAllowed the latest allowed date (inclusive)
   * @return true if the date is within the range, false otherwise
   */
  public static boolean isDateWithinRange(
      LocalDate date, LocalDate earliestDateAllowed, LocalDate latestDateAllowed) {
    return isValidDate(date)
        && isValidDate(earliestDateAllowed)
        && isValidDate(latestDateAllowed)
        && !date.isBefore(earliestDateAllowed)
        && !date.isAfter(latestDateAllowed);
  }

  /**
   * Validates whether the provided date value is between the earliest date allowed and today's
   * date. If the date is invalid or falls outside the range, an error is added.
   *
   * @param fieldName The name of the field associated with the date being validated.
   * @param dateValueToCheck The date value to validate in the format "yyyy-MM-dd".
   * @param earliestDateAllowed the earliest date to check the date value against
   * @return a list of validation issues, empty if valid
   */
  public static List<ValidationIssue> validateDateInPast(
      String fieldName, String dateValueToCheck, LocalDate earliestDateAllowed) {

    return validateDateBetween(fieldName, dateValueToCheck, earliestDateAllowed, LocalDate.now());
  }

  /**
   * Validates a date string against rules tied to a claim's submission period. When the claim has a
   * submission period and the date string is not blank, this method:
   *
   * <ul>
   *   <li>Parses the value as a {@code yyyy-MM-dd} date.
   *   <li>Reports an error if the date is in the future.
   *   <li>Reports an error if the date is before {@code earliestDateAllowed}.
   *   <li>Reports an error if the date is after the 20th of the month following the submission
   *       period.
   *   <li>Reports an error if the date cannot be parsed.
   * </ul>
   *
   * @param claim the claim whose submission period determines the valid date range
   * @param fieldName the name of the field being validated
   * @param dateValueToCheck the date value to validate, in {@code yyyy-MM-dd} format
   * @param earliestDateAllowed the earliest date permitted
   * @return a list of validation issues, empty if valid
   */
  public static List<ValidationIssue> checkDateNotInFutureAndWithinAllowedPeriod(
      Claim claim, String fieldName, String dateValueToCheck, LocalDate earliestDateAllowed) {
    List<ValidationIssue> issues = new ArrayList<>();

    if (claim.getSubmissionPeriod() != null) {
      LocalDate twentiethOfNextMonth = getTwentiethOfNextMonth(claim.getSubmissionPeriod());

      if (StringUtils.hasText(dateValueToCheck)) {
        try {
          LocalDate date = LocalDate.parse(dateValueToCheck, DATE_FORMATTER_YYYY_MM_DD);

          if (date.isAfter(LocalDate.now())) {
            // Set technicalMessage for future date error
            ClaimValidationError error = getDateError(fieldName);
            issues.add(
                error.toValidationIssueWithTechnicalMessage(
                    String.format("%s cannot be a future date", fieldName),
                    String.format("%s cannot be a future date", fieldName)));
          } else if (date.isBefore(earliestDateAllowed)) {
            // Set technicalMessage for early date error
            ClaimValidationError error = getDateError(fieldName);
            String msg =
                String.format(
                    "%s cannot be before %s",
                    fieldName, earliestDateAllowed.format(DATE_FORMATTER_FOR_DISPLAY_MESSAGE));
            issues.add(error.toValidationIssueWithTechnicalMessage(msg, msg));
          } else if (date.isAfter(twentiethOfNextMonth)) {
            // Set technicalMessage for late date error
            ClaimValidationError error = getDateError(fieldName);
            String msg =
                String.format(
                    "%s cannot be later than the 20th of the month following the submission period",
                    fieldName);
            issues.add(error.toValidationIssueWithTechnicalMessage(msg, msg));
          }
        } catch (DateTimeParseException e) {
          issues.add(
              createDateIssue(
                  fieldName, String.format("Invalid date value provided for %s", fieldName)));
        }
      }
    }

    return issues;
  }

  /**
   * Validates whether a given date value falls within an allowed date range. If the date is invalid
   * or outside the specified range, an error is added to the validation context.
   *
   * @param fieldName The name of the field being validated (used in error messages)
   * @param dateValueToCheck The date value to validate in the format "yyyy-MM-dd"
   * @param earliestDateAllowed The earliest allowed date
   * @param latestDateAllowed The latest allowed date
   * @return a list of validation issues, empty if valid
   */
  private static List<ValidationIssue> validateDateBetween(
      String fieldName,
      String dateValueToCheck,
      LocalDate earliestDateAllowed,
      LocalDate latestDateAllowed) {

    LocalDate date = parseDate(dateValueToCheck);
    if (date == null) {
      return List.of(ClaimValidationError.INVALID_DATE_FORMAT.toValidationIssue(fieldName));
    }

    if (!isDateWithinRange(date, earliestDateAllowed, latestDateAllowed)) {
      return List.of(
          createDateIssue(
              fieldName, earliestDateAllowed.format(DATE_FORMATTER_FOR_DISPLAY_MESSAGE)));
    }

    return Collections.emptyList();
  }

  /**
   * Given a string describing the submission period, parses and returns the local date of the
   * twentieth day of the following month. If the submission period is Jan 2026 then the latest Case
   * Concluded Date allowed is the 20 Feb 2026.
   *
   * @param submissionPeriod The submission period in format "MMM-yyyy" (e.g. "JAN-2026")
   * @return The twentieth day of the following month as a LocalDate
   * @throws DateTimeParseException if the submissionPeriod string cannot be parsed
   */
  private static LocalDate getTwentiethOfNextMonth(String submissionPeriod) {
    if (!StringUtils.hasText(submissionPeriod)) {
      throw new IllegalArgumentException("Submission period cannot be null or empty");
    }
    YearMonth yearMonth = YearMonth.parse(submissionPeriod, SUBMISSION_PERIOD_FORMATTER);
    return yearMonth.plusMonths(1).atDay(20);
  }

  /**
   * Creates a date validation issue for the given field and message.
   *
   * @param fieldName the name of the field
   * @param message the error message
   * @return a ValidationIssue for the error
   */
  private static ValidationIssue createDateIssue(String fieldName, String message) {
    ClaimValidationError error = getDateError(fieldName);
    return error.toValidationIssue(message);
  }

  /**
   * Gets the appropriate ClaimValidationError for a date field name.
   *
   * @param fieldName the field name
   * @return the corresponding ClaimValidationError
   */
  private static ClaimValidationError getDateError(String fieldName) {
    return switch (fieldName) {
      case "Case Start Date" -> ClaimValidationError.INVALID_CASE_START_DATE;
      case "Case Concluded Date" -> ClaimValidationError.INVALID_CASE_CONCLUDED_DATE;
      case "Transfer Date" -> ClaimValidationError.INVALID_TRANSFER_DATE;
      case "Representation Order Date" -> ClaimValidationError.INVALID_REPRESENTATION_ORDER_DATE;
      case "Client Date of Birth", "Client 2 Date of Birth" ->
          ClaimValidationError.INVALID_CLIENT_DATE_OF_BIRTH;
      default -> ClaimValidationError.INVALID_DATE_FORMAT;
    };
  }

  /**
   * Parses a submission period string (e.g., "JAN-2026", "jan-2026", "Jan-2026") into a YearMonth.
   * The parsing is case-insensitive.
   *
   * @param submissionPeriod the submission period string in format "MMM-yyyy"
   * @return the parsed YearMonth, or null if parsing fails or input is blank
   */
  public static YearMonth parseSubmissionPeriod(String submissionPeriod) {
    if (!StringUtils.hasText(submissionPeriod)) {
      return null;
    }
    try {
      return YearMonth.parse(submissionPeriod, SUBMISSION_PERIOD_FORMATTER);
    } catch (DateTimeParseException e) {
      log.debug("Could not parse submission period: {}", submissionPeriod);
      return null;
    }
  }

  /**
   * Parses a date string in yyyy-MM-dd format to a LocalDate.
   *
   * @param dateValue the date string to parse
   * @return the parsed LocalDate, or null if parsing fails or input is blank
   */
  public static LocalDate parseDate(String dateValue) {
    if (!StringUtils.hasText(dateValue)) {
      return null;
    }
    try {
      return LocalDate.parse(dateValue, DATE_FORMATTER_YYYY_MM_DD);
    } catch (DateTimeParseException e) {
      log.debug("Could not parse date value: {}", dateValue);
      return null;
    }
  }

  /**
   * Calculates the submission cutoff date for a given disbursement submission period. The cutoff is
   * the {@value CUTOFF_DAY_OF_MONTH}th day of the month following the given period, and represents
   * the deadline by which a disbursement claim for that period must be submitted.
   *
   * <p>For example, a submission period of MAY-2025 yields a cutoff of 20 JUN-2025.
   *
   * @param submissionPeriod the submission period for which the cutoff is calculated
   * @return the cutoff date ({@value CUTOFF_DAY_OF_MONTH}th of the month following {@code
   *     submissionPeriod})
   */
  public static LocalDate submissionPeriodCutoffDate(YearMonth submissionPeriod) {
    return submissionPeriod.plusMonths(CUTOFF_MONTH_OFFSET).atDay(CUTOFF_DAY_OF_MONTH);
  }
}
