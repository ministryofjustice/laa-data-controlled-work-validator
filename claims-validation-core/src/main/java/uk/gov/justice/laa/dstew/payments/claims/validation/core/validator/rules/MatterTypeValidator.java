package uk.gov.justice.laa.dstew.payments.claims.validation.service.validator.rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.service.validator.ValidationContext;

/**
 * Validator for matter type code. Validates that the matter type code is valid for the area of law.
 */
@Component
@Slf4j
public class MatterTypeValidator implements ClaimValidator {

  // TODO: These should be loaded from reference data or configuration
  private static final Set<String> VALID_LEGAL_HELP_MATTER_TYPES =
      Set.of(
          "FAMA", "FAMB", "FAMC", "FAMD", "FAME", "FAMF", "FAMG", "FAMH", "IMMA", "IMMB", "IMMC",
          "IMMD", "IMME", "IMMF", "IMMG", "IMMH", "HOUS", "DEBT", "WELF", "EDUC", "PUBL", "MENT",
          "COMM", "CLIN");

  private static final Set<String> VALID_CRIME_LOWER_MATTER_TYPES = Set.of("CRIM");

  @Override
  public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    String matterType = claim.getMatterTypeCode();
    if (matterType == null || matterType.isBlank()) {
      return issues; // Optional - mandatory validator handles if required
    }

    matterType = matterType.toUpperCase();
    AreaOfLaw areaOfLaw = claim.getAreaOfLaw();

    log.debug("Validating matter type: {} for area of law: {}", matterType, areaOfLaw);

    Set<String> validTypes = getValidMatterTypes(areaOfLaw);
    if (validTypes != null && !validTypes.contains(matterType)) {
      issues.add(
          ClaimValidationError.INVALID_MATTER_TYPE_CODE.toValidationIssue(
              matterType + " for area of law: " + areaOfLaw));
    }

    return issues;
  }

  private Set<String> getValidMatterTypes(AreaOfLaw areaOfLaw) {
    if (areaOfLaw == null) {
      return Collections.emptySet(); // No validation without area of law
    }

    return switch (areaOfLaw) {
      case LEGAL_HELP -> VALID_LEGAL_HELP_MATTER_TYPES;
      case CRIME_LOWER -> VALID_CRIME_LOWER_MATTER_TYPES;
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
