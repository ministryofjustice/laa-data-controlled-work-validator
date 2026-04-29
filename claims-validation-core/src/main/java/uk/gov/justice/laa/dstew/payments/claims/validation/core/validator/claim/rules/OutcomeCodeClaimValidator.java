package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

/**
 * Validator for outcome code. Validates that the outcome code is valid for the area of law using
 * regex patterns.
 */
@Component
@Slf4j
public class OutcomeCodeClaimValidator implements ClaimValidator {

  protected static final String OUTCOME_CODE_LEGAL_HELP_PATTERN = "^[A-Za-z0-9-]{2}$";
  protected static final String OUTCOME_CODE_CRIME_LOWER_PATTERN =
      "(?i)^(CP(0[1-9]|1[0-9]|2[0-8])|CN(0[1-9]|1[0-3])|PL(0[1-9]|1[0-4]))?$";
  protected static final String OUTCOME_CODE_MEDIATION_PATTERN = "(?i)^(A|B|S|C|P)?$";

  @Override
  public List<ValidationIssue> validate(Claim claim, ClaimValidationContext context) {
    String outcomeCode = claim.getOutcomeCode();
    AreaOfLaw areaOfLaw = claim.getAreaOfLaw();
    if (outcomeCode == null || areaOfLaw == null) {
      return List.of();
    }
    String pattern = null;
    String displayMessage = null;
    switch (areaOfLaw) {
      case LEGAL_HELP -> {
        pattern = OUTCOME_CODE_LEGAL_HELP_PATTERN;
        displayMessage =
            "Outcome Code must be exactly 2 characters "
                + "and contain only letters, numbers, and hyphens";
      }
      case CRIME_LOWER -> {
        pattern = OUTCOME_CODE_CRIME_LOWER_PATTERN;
        displayMessage = "Outcome Code must be a valid crime lower outcome code or left blank";
      }
      case MEDIATION -> {
        pattern = OUTCOME_CODE_MEDIATION_PATTERN;
        displayMessage = "Outcome Code must be a valid mediation outcome code or left blank";
      }
      default -> {
        return List.of();
      }
    }
    if (!outcomeCode.matches(pattern)) {
      String technicalMessage =
          String.format(
              "outcome_code (%s): does not match the regex pattern %s (provided value: %s)",
              areaOfLaw.toString().replace('_', ' '), pattern, outcomeCode);
      return List.of(
          ClaimValidationError.INVALID_OUTCOME_CODE.toValidationIssueWithTechnicalMessage(
              technicalMessage, displayMessage));
    }
    return List.of();
  }

  @Override
  public int priority() {
    return 100; // Standard field validation priority
  }

  @Override
  public String getValidatorCode() {
    return "OUTCOME_CODE";
  }
}
