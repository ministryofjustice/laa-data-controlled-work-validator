package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

/**
 * Validator for stage reached code. Validates that the stage reached code matches the expected
 * pattern for the area of law.
 */
@Component
@Slf4j
public class StageReachedClaimValidator implements ClaimValidator {

  private static final Pattern LEGAL_HELP_PATTERN = Pattern.compile("^[a-zA-Z0-9]{2}$");
  private static final Pattern CRIME_LOWER_PATTERN =
      Pattern.compile(
          "^(INV[A-M]|PRI[A-E]|PRO[C-FH-LP-TUVW]|APP[ABC]|AS(MS|PL|AS)|YOU[EFKLXY]|VOID)$");

  @Override
  public List<ValidationIssue> validate(Claim claim, ClaimValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    String stageReached = claim.getStageReachedCode();
    if (stageReached == null || stageReached.isBlank()) {
      return issues; // Optional field
    }

    AreaOfLaw areaOfLaw = claim.getAreaOfLaw();

    log.debug("Validating stage reached: {} for area of law: {}", stageReached, areaOfLaw);

    Pattern pattern = getPattern(areaOfLaw);
    if (pattern != null && !pattern.matcher(stageReached).matches()) {
      String technicalMessage;
      switch (areaOfLaw) {
        case AreaOfLaw.LEGAL_HELP -> {
          technicalMessage =
              String.format(
                  "stage_reached_code (LEGAL_HELP): "
                      + "does not match the regex pattern %s (provided value: %s)",
                  LEGAL_HELP_PATTERN.pattern(), stageReached);
          issues.add(
              ClaimValidationError.INVALID_STAGE_REACHED_LEGAL_HELP
                  .toValidationIssueWithTechnicalMessage(technicalMessage));
        }
        case AreaOfLaw.CRIME_LOWER -> {
          technicalMessage =
              String.format(
                  "stage_reached_code (CRIME_LOWER): "
                      + "does not match the regex pattern %s (provided value: %s)",
                  CRIME_LOWER_PATTERN.pattern(), stageReached);
          issues.add(
              ClaimValidationError.INVALID_STAGE_REACHED_CRIME_LOWER
                  .toValidationIssueWithTechnicalMessage(technicalMessage));
        }
        default -> issues.add(ClaimValidationError.INVALID_STAGE_REACHED.toValidationIssue());
      }
    }

    return issues;
  }

  private Pattern getPattern(AreaOfLaw areaOfLaw) {
    if (areaOfLaw == null) {
      return null;
    }

    return switch (areaOfLaw) {
      case LEGAL_HELP -> LEGAL_HELP_PATTERN;
      case CRIME_LOWER -> CRIME_LOWER_PATTERN;
      default -> null; // No validation for other areas
    };
  }

  @Override
  public int priority() {
    return 100; // Standard field validation priority
  }

  @Override
  public String getValidatorCode() {
    return "STAGE_REACHED";
  }
}
