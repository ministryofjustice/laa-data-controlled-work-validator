package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules;

import static uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.util.DateValidationUtils.checkDateInPast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;

/**
 * Validator for client date of birth fields. Validates that client DOB is in the past and after
 * 1900.
 */
@Component
@Slf4j
public class ClientDateOfBirthValidator implements ClaimValidator {

  private static final LocalDate MIN_BIRTH_DATE = LocalDate.of(1900, 1, 1);

  @Override
  public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    log.debug("Validating client dates of birth");

    // Client 1 Date of Birth
    issues.addAll(
        checkDateInPast("Client Date of Birth", claim.getClientDateOfBirth(), MIN_BIRTH_DATE));

    // Client 2 Date of Birth (if present)
    issues.addAll(
        checkDateInPast("Client 2 Date of Birth", claim.getClient2DateOfBirth(), MIN_BIRTH_DATE));

    log.debug("Client DOB validation completed, found {} issues", issues.size());
    return issues;
  }

  @Override
  public int priority() {
    return 35; // Run after case dates
  }

  @Override
  public String getValidatorCode() {
    return "CLIENT_DATE_OF_BIRTH";
  }
}
