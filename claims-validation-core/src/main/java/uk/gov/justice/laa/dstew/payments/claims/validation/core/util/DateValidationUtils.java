package uk.gov.justice.laa.dstew.payments.claims.validation.core.util;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationSeverity;

/**
 * Utility class for date validation operations. Provides common date validation methods used by
 * multiple validators.
 */
@Slf4j
public final class DateValidationUtils {

  public static final DateTimeFormatter DATE_FORMATTER_YYYY_MM_DD =
      DateTimeFormatter.ofPattern("yyyy-MM-dd");
  public static final DateTimeFormatter DATE_FORMATTER_FOR_DISPLAY_MESSAGE =
      DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static final DateTimeFormatter SUBMISSION_PERIOD_FORMATTER =
      new DateTimeFormatterBuilder()
          .parseCaseInsensitive()
          .appendPattern("MMM-yyyy")
          .toFormatter(Locale.ENGLISH);

  private DateValidationUtils() {
    // Utility class
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
  public static List<ValidationIssue> checkDateInPast(
      String fieldName, String dateValueToCheck, LocalDate earliestDateAllowed) {

    return checkDateAllowed(
        fieldName,
        dateValueToCheck,
        earliestDateAllowed,
        LocalDate.now(),
        "%s must be between %s and today");
  }

  /**
   * Validates a date string against the following rules tied to a claim's submission period. When
   * the claim has a submission period and the date string is not blank, this method:
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
            issues.add(
                createDateIssue(fieldName, String.format("%s cannot be a future date", fieldName)));
          } else if (date.isBefore(earliestDateAllowed)) {
            issues.add(
                createDateIssue(
                    fieldName,
                    String.format(
                        "%s cannot be before %s",
                        fieldName,
                        earliestDateAllowed.format(DATE_FORMATTER_FOR_DISPLAY_MESSAGE))));
          } else if (date.isAfter(twentiethOfNextMonth)) {
            issues.add(
                createDateIssue(
                    fieldName,
                    String.format(
                        "%s cannot be later than the 20th of the month "
                            + "following the submission period",
                        fieldName)));
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
   * @param errorMessage The error message template to use when validation fails. Should contain two
   *     '%s' placeholders: first for the field name, second for the formatted oldest allowed date
   * @return a list of validation issues, empty if valid
   */
  private static List<ValidationIssue> checkDateAllowed(
      String fieldName,
      String dateValueToCheck,
      LocalDate earliestDateAllowed,
      LocalDate latestDateAllowed,
      String errorMessage) {
    List<ValidationIssue> issues = new ArrayList<>();

    if (StringUtils.hasText(dateValueToCheck)) {
      try {
        LocalDate date = LocalDate.parse(dateValueToCheck, DATE_FORMATTER_YYYY_MM_DD);

        if (date.isBefore(earliestDateAllowed) || date.isAfter(latestDateAllowed)) {
          issues.add(
              createDateIssue(
                  fieldName,
                  String.format(
                      errorMessage,
                      fieldName,
                      earliestDateAllowed.format(DATE_FORMATTER_FOR_DISPLAY_MESSAGE))));
        }
      } catch (DateTimeParseException e) {
        issues.add(
            createDateIssue(
                fieldName, String.format("Invalid date value provided for %s", fieldName)));
      }
    }

    return issues;
  }

  /**
   * Given a string describing the submission period, it parses and returns the local date of the
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

  /** Creates a date validation issue. */
  private static ValidationIssue createDateIssue(String fieldName, String message) {
    String code = getDateErrorCode(fieldName);
    return new ValidationIssue(code, message, ValidationSeverity.ERROR);
  }

  /** Gets the appropriate error code for a date field. */
  private static String getDateErrorCode(String fieldName) {
    return switch (fieldName) {
      case "Case Start Date" -> "INVALID_CASE_START_DATE";
      case "Case Concluded Date" -> "INVALID_CASE_CONCLUDED_DATE";
      case "Transfer Date" -> "INVALID_TRANSFER_DATE";
      case "Representation Order Date" -> "INVALID_REPRESENTATION_ORDER_DATE";
      case "Client Date of Birth", "Client 2 Date of Birth" -> "INVALID_CLIENT_DATE_OF_BIRTH";
      default -> "INVALID_DATE_FORMAT";
    };
  }
}
