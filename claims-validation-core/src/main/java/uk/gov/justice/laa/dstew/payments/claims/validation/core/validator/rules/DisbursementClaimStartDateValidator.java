package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;

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

  private static final int MINIMUM_MONTHS_AFTER_CASE_START = 3;
  private static final DateTimeFormatter DATE_FORMATTER_YYYY_MM_DD =
      DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private static final DateTimeFormatter DATE_FORMATTER_FOR_DISPLAY =
      DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static final DateTimeFormatter SUBMISSION_PERIOD_FORMATTER =
      DateTimeFormatter.ofPattern("MMM-yyyy", Locale.ENGLISH);

  private static final String DISBURSEMENT_FEE_TYPE = "DISBURSEMENT";

  @Override
  public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    String feeType = context.getFeeType();
    if (!isDisbursementClaim(feeType)) {
      log.debug("Claim {} is not a disbursement claim, skipping validation", claim.getId());
      return issues;
    }

    String submissionPeriod = claim.getSubmissionPeriod();
    String caseStartDateStr = claim.getCaseStartDate();

    if (submissionPeriod == null || submissionPeriod.isBlank()) {
      log.debug("No submission period provided, skipping validation");
      return issues;
    }

    if (caseStartDateStr == null || caseStartDateStr.isBlank()) {
      log.debug("No case start date provided, skipping validation");
      return issues;
    }

    YearMonth submissionYearMonth = parseSubmissionPeriod(submissionPeriod);
    if (submissionYearMonth == null) {
      log.warn("Could not parse submission period: {}", submissionPeriod);
      return issues;
    }

    LocalDate caseStartDate;
    try {
      caseStartDate = LocalDate.parse(caseStartDateStr, DATE_FORMATTER_YYYY_MM_DD);
    } catch (DateTimeParseException e) {
      log.warn("Could not parse case start date: {}", caseStartDateStr);
      return issues;
    }

    LocalDate submissionCutoffDate = submissionYearMonth.atEndOfMonth();

    if (caseStartDate.plusMonths(MINIMUM_MONTHS_AFTER_CASE_START).isAfter(submissionCutoffDate)) {
      String displayDate = caseStartDate.format(DATE_FORMATTER_FOR_DISPLAY);
      String message =
          String.format(
              "Disbursement claims can only be submitted at least %d calendar months after "
                  + "the Case Start Date %s",
              MINIMUM_MONTHS_AFTER_CASE_START, displayDate);

      log.debug(message);

      ValidationIssue issue =
          new ValidationIssue("DISBURSEMENT_TOO_EARLY", message, ValidationSeverity.ERROR);
      issue.setTechnicalMessage(
          String.format(
              "Case start date %s plus %d months is after submission period end date %s",
              caseStartDateStr, MINIMUM_MONTHS_AFTER_CASE_START, submissionCutoffDate));

      issues.add(issue);
    }

    return issues;
  }

  /**
   * Checks if the claim is a disbursement claim based on fee type.
   *
   * @param feeType the fee type
   * @return true if this is a disbursement claim
   */
  private boolean isDisbursementClaim(String feeType) {
    return DISBURSEMENT_FEE_TYPE.equalsIgnoreCase(feeType);
  }

  /**
   * Parses a submission period string (e.g., "JUL-2025") into a YearMonth.
   *
   * @param submissionPeriod the submission period string
   * @return the parsed YearMonth, or null if parsing fails
   */
  private YearMonth parseSubmissionPeriod(String submissionPeriod) {
    try {
      return YearMonth.parse(submissionPeriod.toUpperCase(), SUBMISSION_PERIOD_FORMATTER);
    } catch (DateTimeParseException e) {
      return null;
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
