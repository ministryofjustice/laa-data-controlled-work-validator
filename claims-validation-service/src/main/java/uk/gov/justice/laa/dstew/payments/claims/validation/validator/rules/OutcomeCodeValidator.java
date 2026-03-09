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
 * Validator for outcome code.
 * Validates that the outcome code is valid for the area of law.
 */
@Component
@Slf4j
public class OutcomeCodeValidator implements ClaimValidator {

  // TODO: These should be loaded from reference data or configuration
  private static final Set<String> VALID_OUTCOME_CODES = Set.of(
      "FA", "FB", "FC", "FD", "FE", "FF", "FG", "FH", "FI", "FJ",
      "FK", "FL", "FM", "FN", "FO", "FP", "FQ", "FR", "FS", "FT",
      "FU", "FV", "FW", "FX", "FY", "FZ",
      "--" // No outcome
  );

  @Override
  public List<ValidationIssue> validate(Map<String, Object> claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    Object outcomeCodeObj = claim.get("outcomeCode");
    if (outcomeCodeObj == null) {
      return issues; // Optional
    }

    String outcomeCode = outcomeCodeObj.toString().toUpperCase();

    log.debug("Validating outcome code: {}", outcomeCode);

    if (!VALID_OUTCOME_CODES.contains(outcomeCode)) {
      issues.add(ValidationIssue.builder()
          .code("INVALID_OUTCOME_CODE")
          .message("Outcome code '" + outcomeCode + "' is not valid")
          .severity(ValidationIssue.SeverityEnum.ERROR)
          .build());
    }

    return issues;
  }

  @Override
  public int priority() {
    return 60;
  }

  @Override
  public String getValidatorCode() {
    return "OUTCOME_CODE";
  }
}

