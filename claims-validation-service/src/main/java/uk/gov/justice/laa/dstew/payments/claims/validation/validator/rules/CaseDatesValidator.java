package uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules;

import static uk.gov.justice.laa.dstew.payments.claims.validation.validator.util.DateValidationUtils.checkDateInPast;
import static uk.gov.justice.laa.dstew.payments.claims.validation.validator.util.DateValidationUtils.checkDateNotInFutureAndWithinAllowedPeriod;
import static uk.gov.justice.laa.dstew.payments.claims.validation.validator.util.DateValidationUtils.getStringValue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ValidationContext;

/**
 * Validator for case-related dates.
 * Validates case start date, case concluded date, transfer date, and representation order date.
 */
@Component
@Slf4j
public class CaseDatesValidator implements ClaimValidator {

  private static final LocalDate OLDEST_DATE_ALLOWED = LocalDate.of(1995, 1, 1);
  private static final LocalDate EARLIEST_CASE_CONCLUDED_DATE = LocalDate.of(2013, 4, 1);
  private static final LocalDate MIN_REP_ORDER_DATE = LocalDate.of(2016, 4, 1);

  @Override
  public List<ValidationIssue> validate(Map<String, Object> claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    log.debug("Validating case dates");

    // Case Start Date - must be in the past and after 1995
    String caseStartDate = getStringValue(claim, "caseStartDate");
    issues.addAll(checkDateInPast("Case Start Date", caseStartDate, OLDEST_DATE_ALLOWED));

    // Case Concluded Date - depends on area of law
    String caseConcludedDate = getStringValue(claim, "caseConcludedDate");
    LocalDate earliestConcludedDate = getEarliestCaseConcludedDate(context.getAreaOfLaw());
    issues.addAll(checkDateNotInFutureAndWithinAllowedPeriod(
        "Case Concluded Date", caseConcludedDate, earliestConcludedDate));

    // Transfer Date - must be in the past and after 1995
    String transferDate = getStringValue(claim, "transferDate");
    issues.addAll(checkDateInPast("Transfer Date", transferDate, OLDEST_DATE_ALLOWED));

    // Representation Order Date - must be in the past and after 2016
    String repOrderDate = getStringValue(claim, "representationOrderDate");
    issues.addAll(checkDateInPast("Representation Order Date", repOrderDate, MIN_REP_ORDER_DATE));

    log.debug("Case dates validation completed, found {} issues", issues.size());
    return issues;
  }

  private LocalDate getEarliestCaseConcludedDate(String areaOfLaw) {
    if ("CRIME_LOWER".equalsIgnoreCase(areaOfLaw)) {
      return MIN_REP_ORDER_DATE;
    }
    return EARLIEST_CASE_CONCLUDED_DATE;
  }

  @Override
  public int priority() {
    return 30; // Run after mandatory and UFN validation
  }

  @Override
  public String getValidatorCode() {
    return "CASE_DATES";
  }
}

