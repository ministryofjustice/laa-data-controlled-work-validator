package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;

/**
 * Validator for client date of birth fields. Validates that client DOB is in the past and after
 * 1900.
 */
@Component
@Slf4j
public class ClientDateOfBirthClaimValidator implements ClaimValidator {

  @Override
  public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    log.debug("Validating client dates of birth");

    // Client 1 Date of Birth
    validateDateOfBirth(
        claim.getClientDateOfBirth(), ClaimValidationError.INVALID_CLIENT_DATE_OF_BIRTH, issues);

    // Client 2 Date of Birth (if present)
    validateDateOfBirth(
        claim.getClient2DateOfBirth(), ClaimValidationError.INVALID_CLIENT_2_DATE_OF_BIRTH, issues);

    log.debug("Client DOB validation completed, found {} issues", issues.size());
    return issues;
  }

  /**
   * Validates a date of birth field and adds an issue to the list if invalid.
   *
   * @param dateOfBirthString the date of birth string to validate
   * @param error the ClaimValidationError to use if validation fails
   * @param issues the list to add validation issues to
   */
  private void validateDateOfBirth(
      String dateOfBirthString, ClaimValidationError error, List<ValidationIssue> issues) {

    if (!StringUtils.hasText(dateOfBirthString)) {
      return; // Not present, skip validation
    }

    LocalDate dateOfBirth = DateUtils.parseDate(dateOfBirthString);

    if (!DateUtils.isValidDate(dateOfBirth)) {
      issues.add(error.toValidationIssue(dateOfBirthString));
      return;
    }

    if (!DateUtils.isValidDateOfBirth(dateOfBirth)) {
      issues.add(error.toValidationIssue(dateOfBirthString));
    }
  }

  @Override
  public int priority() {
    return 100; // Standard field validation priority
  }

  @Override
  public String getValidatorCode() {
    return "CLIENT_DATE_OF_BIRTH";
  }
}
