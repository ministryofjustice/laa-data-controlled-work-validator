package uk.gov.justice.laa.dstew.payments.claims.validation.service.validator.rules;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.service.validator.ValidationContext;

/**
 * Validator for checking the Unique File Number (UFN) format and date validity.
 * UFN must be in format DDMMYY/NNN where the date is in the past.
 */
@Component
@Slf4j
public class UniqueFileNumberValidator implements ClaimValidator {

  private static final Pattern UFN_PATTERN = Pattern.compile("^(\\d{6})/(\\d{3})$");
  private static final DateTimeFormatter UFN_DATE_FORMAT = DateTimeFormatter.ofPattern("ddMMyy");

  @Override
  public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    String ufn = claim.getUniqueFileNumber();
    if (ufn == null || ufn.isBlank()) {
      // UFN may not be mandatory for all claim types - let MandatoryFieldValidator handle that
      return issues;
    }

    log.debug("Validating UFN: {}", ufn);

    Matcher matcher = UFN_PATTERN.matcher(ufn);
    if (!matcher.matches()) {
      issues.add(ClaimValidationError.INVALID_UNIQUE_FILE_NUMBER_FORMAT.toValidationIssue());
      return issues;
    }

    // Extract and validate the date portion
    String datePart = matcher.group(1);
    try {
      LocalDate ufnDate = LocalDate.parse(datePart, UFN_DATE_FORMAT);

      // UFN date must be in the past
      if (ufnDate.isAfter(LocalDate.now())) {
        issues.add(ClaimValidationError.INVALID_DATE_IN_UNIQUE_FILE_NUMBER.toValidationIssue());
      }
    } catch (DateTimeParseException e) {
      log.debug("Failed to parse UFN date: {}", datePart);
      issues.add(ClaimValidationError.INVALID_DATE_IN_UNIQUE_FILE_NUMBER.toValidationIssue());
    }

    return issues;
  }

  @Override
  public int priority() {
    return 20; // Run after mandatory field checks
  }

  @Override
  public String getValidatorCode() {
    return "UNIQUE_FILE_NUMBER";
  }
}
