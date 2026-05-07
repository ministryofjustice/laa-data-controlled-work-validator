package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import java.time.DateTimeException;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;

/**
 * Validator for checking the Unique File Number (UFN) date validity. UFN must be in format
 * DDMMYY/NNN where the date is in the past.
 */
@Component
@Slf4j
public class UniqueFileNumberClaimValidator implements ClaimValidator {

  private static final String UFN_PATTERN = "\\d{6}/\\d{3}";

  @Override
  public void validate(Claim claim, ClaimValidationContext context) {

    String uniqueFileNumber = claim.getUniqueFileNumber();

    if (!StringUtils.hasText(uniqueFileNumber)) {
      return; // Not present, skip validation
    }

    if (!isValidFormat(uniqueFileNumber)) {
      context.addValidationIssue(
              ClaimValidationError.INVALID_DATE_IN_UNIQUE_FILE_NUMBER.toValidationIssue());
      return;
    }

    if (!isValidDateInPast(uniqueFileNumber)) {
      context.addValidationIssue(
              ClaimValidationError.INVALID_DATE_IN_UNIQUE_FILE_NUMBER.toValidationIssue());
    }
  }

  /**
   * Checks if the unique file number matches the expected format DDMMYY/NNN.
   *
   * @param uniqueFileNumber the unique file number to check
   * @return true if format is valid, false otherwise
   */
  private boolean isValidFormat(String uniqueFileNumber) {
    return uniqueFileNumber.matches(UFN_PATTERN);
  }

  /**
   * Checks if the date portion of the unique file number is valid and in the past.
   *
   * @param uniqueFileNumber the unique file number to check
   * @return true if date is valid and in the past, false otherwise
   */
  private boolean isValidDateInPast(String uniqueFileNumber) {
    try {
      LocalDate date = parseDate(uniqueFileNumber);
      return !date.isAfter(DateUtils.now());
    } catch (DateTimeException e) {
      return false;
    }
  }

  /**
   * Parses the date portion of a unique file number.
   *
   * @param uniqueFileNumber the unique file number in format DDMMYY/NNN
   * @return the parsed LocalDate
   * @throws DateTimeException if the date is invalid
   */
  private LocalDate parseDate(String uniqueFileNumber) {
    String datePart = uniqueFileNumber.split("/")[0];

    int day = Integer.parseInt(datePart.substring(0, 2));
    int month = Integer.parseInt(datePart.substring(2, 4));
    int yearTwoDigits = Integer.parseInt(datePart.substring(4, 6));

    // TODO should be fine but this will be a problem in 24 years time
    // - consider using a more robust approach if this code is expected to be in use for a long time
    int year = (yearTwoDigits > 50) ? 1900 + yearTwoDigits : 2000 + yearTwoDigits;

    return LocalDate.of(year, month, day);
  }

  @Override
  public int priority() {
    return 100; // Standard field validation priority
  }

  @Override
  public String getValidatorCode() {
    return "CLAIM_UNIQUE_FILE_NUMBER";
  }

  @Override
  public boolean appliesTo(String scope) {
    return true;
  }
}
