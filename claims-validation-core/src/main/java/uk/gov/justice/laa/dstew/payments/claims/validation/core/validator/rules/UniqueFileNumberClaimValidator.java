package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;

/**
 * Validator for checking the Unique File Number (UFN) date validity. UFN must be in format
 * DDMMYY/NNN where the date is in the past.
 */
@Component
@Slf4j
public class UniqueFileNumberClaimValidator implements ClaimValidator {

  private static final String UFN_PATTERN = "\\d{6}/\\d{3}";

  @Override
  public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    String uniqueFileNumber = claim.getUniqueFileNumber();
    if (uniqueFileNumber != null && uniqueFileNumber.length() > 1) {
      try {
        LocalDate date = parseUniqueFileNumber(uniqueFileNumber);
        if (date.isAfter(LocalDate.now())) {
          issues.add(
              new ValidationIssue(
                  "INVALID_DATE_IN_UNIQUE_FILE_NUMBER",
                  "Unique File Number (UFN) must be in the format DDMMYY/NNN "
                      + "with a date in the past",
                  ValidationSeverity.ERROR));
        }
      } catch (DateTimeException | IllegalArgumentException e) {
        issues.add(
            new ValidationIssue(
                "INVALID_DATE_IN_UNIQUE_FILE_NUMBER",
                "Unique File Number (UFN) must be in the format DDMMYY/NNN "
                    + "with a date in the past",
                ValidationSeverity.ERROR));
      }
    }

    return issues;
  }

  /**
   * Parses a unique file number into a {@link LocalDate} object.
   *
   * @param uniqueFileNumber the unique file number to parse, should be in format ddMMyy/NNN.
   * @return the parsed {@link LocalDate} object.
   */
  private LocalDate parseUniqueFileNumber(String uniqueFileNumber) {
    if (uniqueFileNumber == null || !uniqueFileNumber.matches(UFN_PATTERN)) {
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

  @Override
  public int priority() {
    return 100; // Standard field validation priority
  }

  @Override
  public String getValidatorCode() {
    return "UNIQUE_FILE_NUMBER";
  }
}
