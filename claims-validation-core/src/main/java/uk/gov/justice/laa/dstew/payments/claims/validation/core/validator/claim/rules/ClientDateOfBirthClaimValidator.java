package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;

/**
 * Validator for client date of birth fields. Validates that client DOB is in the past and after
 * 1900.
 */
@Slf4j
public class ClientDateOfBirthClaimValidator implements ClaimValidator {

  @Override
  public void validate(Claim claim, ClaimValidationContext context) {

    log.debug("Validating client dates of birth");

    // Client 1 Date of Birth
    validateDateOfBirth(
        claim.getClientDateOfBirth(),
            ClaimValidationError.INVALID_CLIENT_DATE_OF_BIRTH, context);

    // Client 2 Date of Birth (if present)
    validateDateOfBirth(
        claim.getClient2DateOfBirth(),
            ClaimValidationError.INVALID_CLIENT_2_DATE_OF_BIRTH, context);

    log.debug("Client DOB validation completed, found {} issues", context.getIssues().size());
  }

  /**
   * Validates a date of birth field and adds an issue to the list if invalid.
   *
   * @param dateOfBirthString the date of birth string to validate
   * @param error the ClaimValidationError to use if validation fails
   * @param context the list to add validation issues to
   */
  private void validateDateOfBirth(
      String dateOfBirthString, ClaimValidationError error, ClaimValidationContext context) {

    if (!StringUtils.hasText(dateOfBirthString)) {
      return; // Not present, skip validation
    }

    LocalDate dateOfBirth = DateUtils.parseDate(dateOfBirthString);

    if (!DateUtils.isValidDate(dateOfBirth)) {
      context.addValidationIssue(error.toValidationIssue(dateOfBirthString));
      return;
    }

    if (!DateUtils.isValidDateOfBirth(dateOfBirth)) {
      context.addValidationIssue(error.toValidationIssue(dateOfBirthString));
    }
  }

  @Override
  public int priority() {
    return 100; // Standard field validation priority
  }

  @Override
  public String getValidatorCode() {
    return "CLAIM_CLIENT_DATE_OF_BIRTH";
  }
}
