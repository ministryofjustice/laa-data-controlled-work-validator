package uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ValidationContext;

/**
 * Validator for stage reached code.
 * Validates that the stage reached code matches the expected pattern for the area of law.
 */
@Component
@Slf4j
public class StageReachedValidator implements ClaimValidator {

  private static final Pattern LEGAL_HELP_PATTERN = Pattern.compile("^[a-zA-Z0-9]{2}$");
  private static final Pattern CRIME_LOWER_PATTERN = Pattern.compile(
      "^(INV[A-M]|PRI[A-E]|PRO[C-FH-LP-TUVW]|APP[ABC]|AS|MSPLAS|YOU[EFKLXY]|VOID)$");

  @Override
  public List<ValidationIssue> validate(Map<String, Object> claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    Object stageReachedObj = claim.get("stageReachedCode");
    if (stageReachedObj == null) {
      return issues; // Optional field
    }

    String stageReached = stageReachedObj.toString();
    String areaOfLaw = context.getAreaOfLaw();

    log.debug("Validating stage reached: {} for area of law: {}", stageReached, areaOfLaw);

    Pattern pattern = getPattern(areaOfLaw);
    if (pattern != null && !pattern.matcher(stageReached).matches()) {
      issues.add(ValidationIssue.builder()
          .code("INVALID_STAGE_REACHED")
          .message("Stage reached code '" + stageReached
              + "' does not match expected pattern for " + areaOfLaw)
          .severity(ValidationIssue.SeverityEnum.ERROR)
          .build());
    }

    return issues;
  }

  private Pattern getPattern(String areaOfLaw) {
    if (areaOfLaw == null) {
      return null;
    }

    return switch (areaOfLaw.toUpperCase()) {
      case "LEGAL_HELP" -> LEGAL_HELP_PATTERN;
      case "CRIME_LOWER" -> CRIME_LOWER_PATTERN;
      default -> null; // No validation for other areas
    };
  }

  @Override
  public int priority() {
    return 50;
  }

  @Override
  public String getValidatorCode() {
    return "STAGE_REACHED";
  }
}

