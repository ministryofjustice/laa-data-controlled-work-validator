package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules;

import static uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils.checkDateInPast;
import static uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils.checkDateNotInFutureAndWithinAllowedPeriod;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;

/**
 * Validator for case-related dates. Validates case start date, case concluded date, transfer date,
 * and representation order date.
 */
@Component
@Slf4j
public class CaseDatesClaimValidator implements ClaimValidator {

  private static final LocalDate OLDEST_DATE_ALLOWED = LocalDate.of(1995, 1, 1);
  private static final LocalDate EARLIEST_CASE_CONCLUDED_DATE_ALLOWED = LocalDate.of(2013, 4, 1);
  private static final LocalDate MIN_REP_ORDER_DATE = LocalDate.of(2016, 4, 1);
  private static final String CASE_CONCLUDED_DATE_FIELD_NAME = "Case Concluded Date";

  @Override
  public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    log.debug("Validating case dates");

    AreaOfLaw areaOfLaw = claim.getAreaOfLaw();

    // Case Start Date - must be in the past and after 1995
    String caseStartDate = claim.getCaseStartDate();
    issues.addAll(checkDateInPast("Case Start Date", caseStartDate, OLDEST_DATE_ALLOWED));

    // Case Concluded Date - depends on area of law and must be within submission period
    LocalDate earliestDateAllowedForCaseConcludedDate =
        AreaOfLaw.CRIME_LOWER.equals(areaOfLaw)
            ? MIN_REP_ORDER_DATE
            : EARLIEST_CASE_CONCLUDED_DATE_ALLOWED;

    issues.addAll(
        checkDateNotInFutureAndWithinAllowedPeriod(
            claim,
            CASE_CONCLUDED_DATE_FIELD_NAME,
            claim.getCaseConcludedDate(),
            earliestDateAllowedForCaseConcludedDate));

    // Transfer Date - must be in the past and after 1995
    issues.addAll(checkDateInPast("Transfer Date", claim.getTransferDate(), OLDEST_DATE_ALLOWED));

    // Representation Order Date - must be in the past and after 2016
    issues.addAll(
        checkDateInPast(
            "Representation Order Date", claim.getRepresentationOrderDate(), MIN_REP_ORDER_DATE));

    log.debug("Case dates validation completed, found {} issues", issues.size());
    return issues;
  }

  @Override
  public int priority() {
    return 100; // Standard field validation priority
  }

  @Override
  public String getValidatorCode() {
    return "CASE_DATES";
  }
}
