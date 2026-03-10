package uk.gov.justice.laa.dstew.payments.claims.validation.service.validator.rules;

import static uk.gov.justice.laa.dstew.payments.claims.validation.service.validator.util.DateValidationUtils.checkDateInPast;
import static uk.gov.justice.laa.dstew.payments.claims.validation.service.validator.util.DateValidationUtils.checkDateNotInFutureAndWithinAllowedPeriod;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.service.validator.ValidationContext;

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
  public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    log.debug("Validating case dates");

    // Case Start Date - must be in the past and after 1995
    issues.addAll(checkDateInPast("Case Start Date",
           claim.getCaseStartDate(), OLDEST_DATE_ALLOWED));

    // Case Concluded Date - depends on area of law
    AreaOfLaw areaOfLaw = claim.getAreaOfLaw();
    LocalDate earliestConcludedDate = getEarliestCaseConcludedDate(areaOfLaw);
    issues.addAll(checkDateNotInFutureAndWithinAllowedPeriod(
        "Case Concluded Date", claim.getCaseConcludedDate(), earliestConcludedDate));

    // Transfer Date - must be in the past and after 1995
    issues.addAll(checkDateInPast("Transfer Date", claim.getTransferDate(), OLDEST_DATE_ALLOWED));

    // Representation Order Date - must be in the past and after 2016
    issues.addAll(checkDateInPast(
        "Representation Order Date", claim.getRepresentationOrderDate(), MIN_REP_ORDER_DATE));

    log.debug("Case dates validation completed, found {} issues", issues.size());
    return issues;
  }

  private LocalDate getEarliestCaseConcludedDate(AreaOfLaw areaOfLaw) {
    if (areaOfLaw == AreaOfLaw.CRIME_LOWER) {
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
