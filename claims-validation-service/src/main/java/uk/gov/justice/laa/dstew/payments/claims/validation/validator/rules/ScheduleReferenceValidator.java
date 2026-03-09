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
 * Validator for schedule reference.
 * Validates that the schedule reference matches the expected format.
 */
@Component
@Slf4j
public class ScheduleReferenceValidator implements ClaimValidator {

  // Schedule reference format: typically alphanumeric
  private static final Pattern SCHEDULE_REF_PATTERN = Pattern.compile("^[A-Z0-9]{1,20}$");

  @Override
  public List<ValidationIssue> validate(Map<String, Object> claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    Object scheduleRefObj = claim.get("scheduleReference");
    if (scheduleRefObj == null) {
      return issues; // Optional
    }

    String scheduleRef = scheduleRefObj.toString().toUpperCase();

    log.debug("Validating schedule reference: {}", scheduleRef);

    if (!SCHEDULE_REF_PATTERN.matcher(scheduleRef).matches()) {
      issues.add(ValidationIssue.builder()
          .code("INVALID_SCHEDULE_REFERENCE")
          .message("Schedule reference '" + scheduleRef + "' has an invalid format")
          .severity(ValidationIssue.SeverityEnum.ERROR)
          .build());
    }

    return issues;
  }

  @Override
  public int priority() {
    return 65;
  }

  @Override
  public String getValidatorCode() {
    return "SCHEDULE_REFERENCE";
  }
}

