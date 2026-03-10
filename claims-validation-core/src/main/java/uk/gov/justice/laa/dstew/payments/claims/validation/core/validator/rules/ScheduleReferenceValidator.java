package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;

/**
 * Validator for schedule reference. Validates that the schedule reference matches the expected
 * format.
 */
@Component
@Slf4j
public class ScheduleReferenceValidator implements ClaimValidator {

  // Schedule reference format: typically alphanumeric
  private static final Pattern SCHEDULE_REF_PATTERN = Pattern.compile("^[A-Z0-9]{1,20}$");

  @Override
  public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    String scheduleRef = claim.getScheduleReference();
    if (scheduleRef == null || scheduleRef.isBlank()) {
      return issues; // Optional
    }

    scheduleRef = scheduleRef.toUpperCase();

    log.debug("Validating schedule reference: {}", scheduleRef);

    if (!SCHEDULE_REF_PATTERN.matcher(scheduleRef).matches()) {
      issues.add(ClaimValidationError.INVALID_SCHEDULE_REFERENCE.toValidationIssue());
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
