package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.FeeCalculationType;
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

  private static final int MAXIMUM_MONTHS_DIFFERENCE = 3;
  private static final DateTimeFormatter DATE_FORMATTER_YYYY_MM_DD =
      DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private static final DateTimeFormatter DATE_FORMATTER_FOR_DISPLAY =
      DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static final DateTimeFormatter SUBMISSION_PERIOD_FORMATTER =
      new DateTimeFormatterBuilder()
          .parseCaseInsensitive()
          .appendPattern("MMM-yyyy")
          .toFormatter(Locale.ENGLISH);

  @Override
  public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    String feeType = context.getFeeCalculationType();
    if (!isDisbursementClaim(feeType)) {
      log.debug("Claim {} is not a disbursement claim", claim.getId());
      return issues;
    }

    if (StringUtils.hasText(claim.getSubmissionPeriod())
        && StringUtils.hasText(claim.getCaseStartDate())) {
      YearMonth submissionPeriod = parseSubmissionPeriod(claim.getSubmissionPeriod());
      if (submissionPeriod == null) {
        return issues;
      }
      LocalDate submissionEndDate = submissionPeriod.atEndOfMonth();
      LocalDate caseStartDate =
          LocalDate.parse(claim.getCaseStartDate(), DATE_FORMATTER_YYYY_MM_DD);

      if (caseStartDate.plusMonths(MAXIMUM_MONTHS_DIFFERENCE).isAfter(submissionEndDate)) {
        log.debug(
            "Disbursement claims can only be submitted at least {} calendar months "
                + "after the Case Start Date {}",
            MAXIMUM_MONTHS_DIFFERENCE,
            caseStartDate.format(DATE_FORMATTER_FOR_DISPLAY));

        String message =
            String.format(
                "Disbursement claims can only be submitted at least %d calendar months "
                    + "after the Case Start Date %s",
                MAXIMUM_MONTHS_DIFFERENCE, caseStartDate.format(DATE_FORMATTER_FOR_DISPLAY));

        ValidationIssue issue =
            new ValidationIssue("DISBURSEMENT_TOO_EARLY", message, ValidationSeverity.ERROR);
        issues.add(issue);
      }
    }

    return issues;
  }

  /**
   * Checks if the claim is a disbursement claim based on fee calculation type.
   *
   * @param feeType the fee calculation type
   * @return true if this is a disbursement claim
   */
  private boolean isDisbursementClaim(String feeType) {
    return FeeCalculationType.DISB_ONLY.getValue().equals(feeType);
  }

  /**
   * Parses a submission period string (e.g., "JUL-2025") into a YearMonth.
   *
   * @param submissionPeriod the submission period string
   * @return the parsed YearMonth, or null if parsing fails
   */
  private YearMonth parseSubmissionPeriod(String submissionPeriod) {
    try {
      return YearMonth.parse(submissionPeriod, SUBMISSION_PERIOD_FORMATTER);
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
