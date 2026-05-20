package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import lombok.extern.slf4j.Slf4j;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

/**
 * Validator for matter type code. Validates that the matter type code is valid for the area of law
 * using regex patterns.
 */
@Slf4j
public class MatterTypeClaimValidator implements ClaimValidator {

  private static final String MATTER_TYPE_LEGAL_HELP_PATTERN =
      "^[a-zA-Z0-9]{1,4}[-:][a-zA-Z0-9]{1,4}$";
  private static final String MATTER_TYPE_MEDIATION_PATTERN = "^[A-Z]{4}[-:][A-Z]{4}$";

  @Override
  public void validate(Claim claim, ClaimValidationContext context) {

    String matterType = claim.getMatterTypeCode();
    AreaOfLaw areaOfLaw = claim.getAreaOfLaw();

    if (matterType == null || areaOfLaw == null) {
      return; // Skip if no matter type or area of law
    }

    String regex = getRegexForAreaOfLaw(areaOfLaw);
    if (regex == null) {
      return; // No regex defined for this area of law
    }

    log.debug("Validating matter type: {} for area of law: {}", matterType, areaOfLaw);

    if (!matterType.matches(regex)) {
      String technicalMessage =
          String.format(
              "matter_type_code (%s): does not match the regex pattern %s (provided value: %s)",
              areaOfLaw, regex, matterType);
      String displayMessage;
      // Set display message to match legacy expectations
      if (AreaOfLaw.LEGAL_HELP.equals(areaOfLaw)) {
        displayMessage = "Each Matter Type Code 1 and 2 must be 4 characters";
      } else if (AreaOfLaw.MEDIATION.equals(areaOfLaw)) {
        displayMessage = "Each Matter Type Code 1 and 2 must be 4 uppercase characters";
      } else {
        displayMessage = String.format("Invalid matter type code: %s", matterType);
      }
      ValidationIssue issue =
          ClaimValidationError.INVALID_MATTER_TYPE_CODE.toValidationIssueWithTechnicalMessage(
              technicalMessage, matterType);
      issue.setMessage(displayMessage);
      context.addValidationIssue(issue);
    }
  }

  private String getRegexForAreaOfLaw(AreaOfLaw areaOfLaw) {
    return switch (areaOfLaw) {
      case LEGAL_HELP -> MATTER_TYPE_LEGAL_HELP_PATTERN;
      case MEDIATION -> MATTER_TYPE_MEDIATION_PATTERN;
      default -> null;
    };
  }

  @Override
  public int priority() {
    return 100; // Standard field validation priority
  }

  @Override
  public String getValidatorCode() {
    return "CLAIM_MATTER_TYPE";
  }
}
