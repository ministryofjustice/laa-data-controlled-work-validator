package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils.parseSubmissionPeriod;
import static uk.gov.justice.laa.dstew.payments.claims.validation.core.util.FeeTypeUtils.isDisbursementClaim;

import java.time.LocalDate;
import java.time.YearMonth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;

/**
 * Validator that checks if disbursement claims are submitted within the allowed timeframe relative
 * to the case start date.
 *
 * <p>Disbursement claims can only be submitted after a specific number of calendar months have
 * passed since the case start date. Validation is performed by comparing the case start date
 * against the last calendar day of the submission period month.
 */
@Slf4j
@Component
public final class DisbursementClaimStartDateValidator implements ClaimValidator {

  @Override
  public void validate(Claim claim, ClaimValidationContext context) {

    String feeType = context.getFeeCalculationType();
    if (!isDisbursementClaim(feeType)) {
      log.debug("Claim {} is not a disbursement claim", claim.getId());
      return;
    }

    if (StringUtils.hasText(claim.getSubmissionPeriod())
        && StringUtils.hasText(claim.getCaseStartDate())) {
      YearMonth submissionPeriod = parseSubmissionPeriod(claim.getSubmissionPeriod());
      if (submissionPeriod == null) {
        return;
      }
      LocalDate submissionEndDate = DateUtils.submissionPeriodCutoffDate(submissionPeriod);
      LocalDate caseStartDate =
          LocalDate.parse(claim.getCaseStartDate(), DateUtils.DATE_FORMATTER_YYYY_MM_DD);

      if (caseStartDate
          .plusMonths(DateUtils.MAXIMUM_MONTHS_DIFFERENCE)
          .isAfter(submissionEndDate)) {
        log.debug(
            "Disbursement claims can only be submitted at least {} calendar months "
                + "after the Case Start Date {}",
            DateUtils.MAXIMUM_MONTHS_DIFFERENCE,
            caseStartDate.format(DateUtils.DATE_FORMATTER_FOR_DISPLAY_MESSAGE));

        context.addValidationIssue(
            ClaimValidationError.DISBURSEMENT_TOO_EARLY.toValidationIssue(
                DateUtils.MAXIMUM_MONTHS_DIFFERENCE,
                caseStartDate.format(DateUtils.DATE_FORMATTER_FOR_DISPLAY_MESSAGE)));
      }
    }
  }

  @Override
  public int priority() {
    return 10; // Run early with mandatory field validation
  }

  @Override
  public boolean appliesTo(String scope) {
    // Only run for disbursement scope
    return "disbursement".equalsIgnoreCase(scope) || "all".equalsIgnoreCase(scope);
  }

  @Override
  public String getValidatorCode() {
    return "DISBURSEMENT_START_DATE";
  }
}
