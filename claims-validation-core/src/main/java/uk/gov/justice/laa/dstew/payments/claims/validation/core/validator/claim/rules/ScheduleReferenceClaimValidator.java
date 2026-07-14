package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidatorCode;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

/**
 * Validator for schedule reference. Validates that the schedule reference matches the expected
 * format.
 */
@Slf4j
public class ScheduleReferenceClaimValidator implements ClaimValidator {

  // Schedule reference format: typically alphanumeric
  private static final String SCHEDULE_REF_REGEX = "^[a-zA-Z0-9/.\\-]{1,20}$";
  private static final Pattern SCHEDULE_REF_PATTERN = Pattern.compile(SCHEDULE_REF_REGEX);

  @Override
  public void validate(Claim claim, ClaimValidationContext context) {
    String scheduleReference = claim.getScheduleReference();
    if (scheduleReference != null
        && AreaOfLaw.LEGAL_HELP.equals(claim.getAreaOfLaw())
        && !SCHEDULE_REF_PATTERN.matcher(scheduleReference).matches()) {
      String errorMessage =
          "Schedule Reference must be a maximum of 20 characters and "
              + "contain only letters, numbers, forward slashes, periods, and hyphens";
      String technicalMessage =
          String.format(
              "schedule_reference (LEGAL HELP): does not "
                  + "match the regex pattern %s (provided value: %s)",
              SCHEDULE_REF_REGEX, scheduleReference);
      context.addValidationIssue(
          ClaimValidationError.INVALID_SCHEDULE_REFERENCE.toValidationIssueWithTechnicalMessage(
              technicalMessage, errorMessage));
    }
  }

  @Override
  public int priority() {
    return 100; // Standard field validation priority
  }

  @Override
  public ClaimValidatorCode getValidatorCode() {
    return ClaimValidatorCode.CLAIM_SCHEDULE_REFERENCE;
  }
}
