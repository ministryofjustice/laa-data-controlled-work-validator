package uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ValidationContext;

/**
 * Validator for matter type code.
 * Validates that the matter type code is valid for the area of law.
 */
@Component
@Slf4j
public class MatterTypeValidator implements ClaimValidator {

  // TODO: These should be loaded from reference data or configuration
  private static final Set<String> VALID_LEGAL_HELP_MATTER_TYPES = Set.of(
      "FAMA", "FAMB", "FAMC", "FAMD", "FAME", "FAMF", "FAMG", "FAMH",
      "IMMA", "IMMB", "IMMC", "IMMD", "IMME", "IMMF", "IMMG", "IMMH",
      "HOUS", "DEBT", "WELF", "EDUC", "PUBL", "MENT", "COMM", "CLIN"
  );

  private static final Set<String> VALID_CRIME_LOWER_MATTER_TYPES = Set.of(
      "CRIM"
  );

  @Override
  public List<ValidationIssue> validate(Map<String, Object> claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    Object matterTypeObj = claim.get("matterTypeCode");
    if (matterTypeObj == null) {
      return issues; // Optional - mandatory validator handles if required
    }

    String matterType = matterTypeObj.toString().toUpperCase();
    String areaOfLaw = context.getAreaOfLaw();

    log.debug("Validating matter type: {} for area of law: {}", matterType, areaOfLaw);

    Set<String> validTypes = getValidMatterTypes(areaOfLaw);
    if (validTypes != null && !validTypes.contains(matterType)) {
      issues.add(ValidationIssue.builder()
          .code("INVALID_MATTER_TYPE_CODE")
          .message("Matter type code '" + matterType
              + "' is not valid for area of law: " + areaOfLaw)
          .severity(ValidationIssue.SeverityEnum.ERROR)
          .build());
    }

    return issues;
  }

  private Set<String> getValidMatterTypes(String areaOfLaw) {
    if (areaOfLaw == null) {
      return null; // No validation without area of law
    }

    return switch (areaOfLaw.toUpperCase()) {
      case "LEGAL_HELP" -> VALID_LEGAL_HELP_MATTER_TYPES;
      case "CRIME_LOWER" -> VALID_CRIME_LOWER_MATTER_TYPES;
      default -> null;
    };
  }

  @Override
  public int priority() {
    return 55;
  }

  @Override
  public String getValidatorCode() {
    return "MATTER_TYPE";
  }
}

