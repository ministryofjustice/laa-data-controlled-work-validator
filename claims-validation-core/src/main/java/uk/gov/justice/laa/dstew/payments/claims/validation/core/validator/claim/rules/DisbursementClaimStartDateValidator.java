package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils.parseSubmissionPeriod;
import static uk.gov.justice.laa.dstew.payments.claims.validation.core.util.FeeTypeUtils.isDisbursementClaim;

import java.time.LocalDate;
import java.time.YearMonth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
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
public final class DisbursementClaimStartDateValidator implements ClaimValidator {

  @Override
  public void validate(Claim claim, ClaimValidationContext context) {

    if (!isDisbursementClaim(context.getFeeCalculationType())) {
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

        ValidationIssue issue = ClaimValidationError.DISBURSEMENT_TOO_EARLY.toValidationIssue(
                DateUtils.MAXIMUM_MONTHS_DIFFERENCE,
                caseStartDate.format(DateUtils.DATE_FORMATTER_FOR_DISPLAY_MESSAGE));
        issue.setTechnicalMessage(issue.getMessage());
        context.addValidationIssue(issue);
      }
    }
  }

  @Override
  public int priority() {
    return 10; // Run early with mandatory field validation
  }

  @Override
  public boolean appliesTo(String scope) {
    return true;
  }

  @Override
  public String getValidatorCode() {
    return "CLAIM_DISBURSEMENT_START_DATE";
  }
}
