package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils.checkDateNotInFutureAndWithinAllowedPeriod;
import static uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils.validateDateInPast;

import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidatorCode;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

/**
 * Validator for case-related dates. Validates case start date, case concluded date, transfer date,
 * and representation order date.
 *
 * <p><strong>Case Start Date</strong> business rule (parity with the legacy event-service
 * validator):
 *
 * <ul>
 *   <li><em>LEGAL HELP / MEDIATION</em>: the date must be a valid date between {@code 1995-01-01}
 *       and today. The field is also mandatory for these areas of law — its presence is enforced by
 *       {@code MandatoryFieldClaimValidator} (it is listed in {@code MandatoryFieldsRegistry}).
 *   </li>
 *   <li><em>CRIME LOWER</em>: not applicable — the Case Start Date is not validated here.</li>
 *   <li><em>DISB-ONLY</em>: the "case start date + 3 months must not exceed the submission period
 *       cutoff" timing rule is owned by {@code DisbursementClaimStartDateValidator}, not this
 *       validator.</li>
 * </ul>
 */
@Slf4j
public class CaseDatesClaimValidator implements ClaimValidator {

  private static final LocalDate OLDEST_DATE_ALLOWED = LocalDate.of(1995, 1, 1);
  private static final LocalDate EARLIEST_CASE_CONCLUDED_DATE_ALLOWED = LocalDate.of(2013, 4, 1);
  private static final LocalDate MIN_REP_ORDER_DATE = LocalDate.of(2016, 4, 1);
  private static final String CASE_CONCLUDED_DATE_FIELD_NAME = "Case Concluded Date";

  @Override
  public void validate(Claim claim, ClaimValidationContext context) {

    log.debug("Validating case dates");

    AreaOfLaw areaOfLaw = claim.getAreaOfLaw();

    // Case Start Date - Not applicable for CRIME LOWER claims. For LEGAL HELP / MEDIATION (and any
    // non-CRIME-LOWER area, including a null area of law) it must be a valid date between
    // 1995-01-01 and today. A blank value yields an error here and the field's presence is
    // additionally enforced by the mandatory-field validator. The DISB-ONLY "case start + 3
    // months" timing rule is handled by DisbursementClaimStartDateValidator.
    if (!AreaOfLaw.CRIME_LOWER.equals(areaOfLaw)) {
      context.addValidationIssues(
          validateDateInPast("Case Start Date", claim.getCaseStartDate(), OLDEST_DATE_ALLOWED));
    }

    // Case Concluded Date - depends on area of law and must be within submission period
    LocalDate earliestDateAllowedForCaseConcludedDate =
        AreaOfLaw.CRIME_LOWER.equals(areaOfLaw)
            ? MIN_REP_ORDER_DATE
            : EARLIEST_CASE_CONCLUDED_DATE_ALLOWED;

    context.addValidationIssues(
        checkDateNotInFutureAndWithinAllowedPeriod(
            claim,
            CASE_CONCLUDED_DATE_FIELD_NAME,
            claim.getCaseConcludedDate(),
            earliestDateAllowedForCaseConcludedDate));

    // Transfer Date - must be in the past and after 1995
    if (StringUtils.hasText(claim.getTransferDate())) {
      log.debug("Validating transfer date: {}", claim.getTransferDate());
      context.addValidationIssues(
          validateDateInPast("Transfer Date", claim.getTransferDate(), OLDEST_DATE_ALLOWED));
    }

    // Representation Order Date - must be in the past and after 2016
    String repOrderDate = claim.getRepresentationOrderDate();
    if (StringUtils.hasText(repOrderDate)) {
      log.debug("Validating representation order date: {}", repOrderDate);
      context.addValidationIssues(
          validateDateInPast(
              "Representation Order Date", repOrderDate, MIN_REP_ORDER_DATE));
    }

    log.debug("Case dates validation completed, found {} issues", context.getIssues().size());
  }

  @Override
  public int priority() {
    return 100; // Standard field validation priority
  }

  @Override
  public ClaimValidatorCode getValidatorCode() {
    return ClaimValidatorCode.CLAIM_CASE_DATES_VALIDATOR;
  }
}
