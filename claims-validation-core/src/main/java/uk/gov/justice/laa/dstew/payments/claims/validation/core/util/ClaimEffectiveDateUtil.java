package uk.gov.justice.laa.dstew.payments.claims.validation.core.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;

/** Utility class for calculating the effective date of a claim. */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ClaimEffectiveDateUtil {

  private static final String PROD_FEE_CODE = "PROD";
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  /**
   * Gets the effective date for a claim based on what fields are available.
   *
   * <p>For claims with a fee code of {@value #PROD_FEE_CODE}, the effective date is resolved in the
   * following order:
   *
   * <ol>
   *   <li>Case Concluded Date, if present.
   * </ol>
   *
   * <p>For all other claims, the effective date is resolved in the following order:
   *
   * <ol>
   *   <li>Case Start Date, if present.
   *   <li>Representation Order Date, if present.
   *   <li>Date derived from the Unique File Number.
   * </ol>
   *
   * @param claim the claim to calculate the effective date for
   * @return the effective date for the claim
   * @throws IllegalArgumentException if no date fields are available, or if a date field is present
   *     but cannot be parsed
   */
  public static LocalDate getEffectiveDate(final Claim claim) throws IllegalArgumentException {

    if (Objects.equals(claim.getFeeCode(), PROD_FEE_CODE)
        && StringUtils.hasText(claim.getCaseConcludedDate())) {
      return parseDate(claim.getCaseConcludedDate(), "case concluded date");
    }

    if (StringUtils.hasText(claim.getCaseStartDate())) {
      return parseDate(claim.getCaseStartDate(), "case start date");
    }

    if (StringUtils.hasText(claim.getRepresentationOrderDate())) {
      return parseDate(claim.getRepresentationOrderDate(), "representation order date");
    }

    if (StringUtils.hasText(claim.getUniqueFileNumber())) {
      return parseUniqueFileNumber(claim.getUniqueFileNumber());
    }

    throw new IllegalArgumentException(
        "No fields available to determine effective date of claim ID: " + claim.getId());
  }

  /**
   * Parses a date string in yyyy-MM-dd format.
   *
   * @param dateString the date string to parse
   * @param fieldName the name of the field (for error messages)
   * @return the parsed LocalDate
   * @throws IllegalArgumentException if the date cannot be parsed
   */
  private static LocalDate parseDate(String dateString, String fieldName) {
    try {
      return LocalDate.parse(dateString, DATE_FORMATTER);
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException(
          String.format("Invalid %s format: %s", fieldName, dateString), e);
    }
  }

  /**
   * Parses a unique file number into a LocalDate.
   *
   * @param uniqueFileNumber the unique file number in format ddMMyy/NNN
   * @return the parsed LocalDate
   * @throws IllegalArgumentException if the UFN cannot be parsed
   */
  private static LocalDate parseUniqueFileNumber(String uniqueFileNumber) {
    if (uniqueFileNumber == null || !uniqueFileNumber.matches("\\d{6}/\\d{3}")) {
      throw new IllegalArgumentException(
          String.format(
              "Invalid format for unique file number: %s. Expected format: ddMMyy/NNN",
              uniqueFileNumber));
    }

    String datePart = uniqueFileNumber.split("/")[0];

    int day = Integer.parseInt(datePart.substring(0, 2));
    int month = Integer.parseInt(datePart.substring(2, 4));
    int yearTwoDigits = Integer.parseInt(datePart.substring(4, 6));

    int year = (yearTwoDigits > 50) ? 1900 + yearTwoDigits : 2000 + yearTwoDigits;

    return LocalDate.of(year, month, day);
  }
}
