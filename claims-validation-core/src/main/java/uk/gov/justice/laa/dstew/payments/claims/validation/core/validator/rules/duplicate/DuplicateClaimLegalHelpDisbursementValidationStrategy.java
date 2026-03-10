package uk.gov.justice.laa.dstew.payments.claims.validation.service.validator.rules.duplicate;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.ClaimsApiClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ClaimValidationError;

/**
 * Duplicate-claim validation strategy for Legal Help disbursement claims.
 * Implements Rule B - Case Concluded Date boundary check.
 */
@Slf4j
@Component
public class DuplicateClaimLegalHelpDisbursementValidationStrategy extends DuplicateClaimValidation
    implements LegalHelpDuplicateClaimValidationStrategy {

  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private static final int MAXIMUM_MONTHS_DIFFERENCE = 3;

  public DuplicateClaimLegalHelpDisbursementValidationStrategy(
      ClaimsApiClient claimsApiClient) {
    super(claimsApiClient);
  }

  @Override
  public List<ValidationIssue> validateDuplicateClaims(
      Claim incomingClaim,
      List<Claim> submissionClaims,
      String officeCode,
      String feeType) {

    List<ValidationIssue> issues = new ArrayList<>();

    // TODO: Check if this is a disbursement claim
    // if (!isDisbursementClaim(feeType)) {
    //   return issues;
    // }

    List<Claim> candidateDuplicateClaims =
        findEligibleDuplicateClaims(incomingClaim, submissionClaims, officeCode);
    if (candidateDuplicateClaims.isEmpty()) {
      return issues;
    }

    LocalDate incomingConcludedDate = parseConcludedDate(incomingClaim);
    if (incomingConcludedDate == null) {
      // The incoming claim has no valid Case Concluded Date; Rule B cannot be applied.
      // No duplicate error is raised as this will be handled by upstream date validation.
      return issues;
    }

    // Select anchor claim - the one closest to incoming claim's case concluded date
    Claim duplicateClaim = selectComparativeClaim(candidateDuplicateClaims, incomingConcludedDate);

    if (duplicateClaim != null && isDuplicateClaim(incomingClaim, duplicateClaim)) {
      logDuplicates(incomingClaim, List.of(duplicateClaim));
      issues.add(ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION
          .toValidationIssue());
    }

    return issues;
  }

  /**
   * Retrieves all claims from previous submissions that share the same office code, fee code,
   * unique file number, and unique client number as the incoming claim.
   */
  protected List<Claim> findEligibleDuplicateClaims(
      Claim incomingClaim, List<Claim> submissionClaims, String officeCode) {
    return getDuplicateClaimsInPreviousSubmission(
            officeCode,
            incomingClaim.getFeeCode(),
            incomingClaim.getUniqueFileNumber(),
            incomingClaim.getUniqueClientNumber(),
            null,
            submissionClaims)
        .stream()
        .filter(c -> parseConcludedDate(c) != null)
        .toList();
  }

  /**
   * Selects the anchor claim - the claim whose Case Concluded Date is closest to incomingDate.
   */
  protected Claim selectComparativeClaim(List<Claim> claims, LocalDate incomingDate) {
    return claims.stream().min(claimComparator(incomingDate)).orElse(null);
  }

  /**
   * Applies Rule B Case Concluded Date boundary check.
   */
  protected boolean isDuplicateClaim(Claim incomingClaim, Claim duplicateClaim) {
    YearMonth incomingSubmissionPeriod = parseSubmissionPeriod(incomingClaim.getSubmissionPeriod());
    LocalDate incomingConcludedDate = parseConcludedDate(incomingClaim);
    YearMonth anchorSubmissionPeriod = parseSubmissionPeriod(duplicateClaim.getSubmissionPeriod());
    LocalDate anchorConcludedDate = parseConcludedDate(duplicateClaim);

    if (incomingSubmissionPeriod == null
        || anchorSubmissionPeriod == null
        || incomingConcludedDate == null
        || anchorConcludedDate == null) {
      return false;
    }

    YearMonth cutoffPeriod = getCutoffPeriod(incomingSubmissionPeriod, anchorSubmissionPeriod);
    LocalDate cutoff = submissionPeriodCutoffDate(cutoffPeriod);

    LocalDate earlierConcludedDate =
        incomingConcludedDate.isBefore(anchorConcludedDate)
            ? incomingConcludedDate
            : anchorConcludedDate;

    return earlierConcludedDate.isAfter(cutoff);
  }

  /**
   * Determines the cutoff period - 3 months before the later submission period.
   */
  protected YearMonth getCutoffPeriod(
      YearMonth incomingSubmissionPeriod, YearMonth anchorSubmissionPeriod) {
    YearMonth anchorPeriod =
        incomingSubmissionPeriod.isAfter(anchorSubmissionPeriod)
            ? incomingSubmissionPeriod
            : anchorSubmissionPeriod;
    return anchorPeriod.minusMonths(MAXIMUM_MONTHS_DIFFERENCE);
  }

  /**
   * Calculates the cutoff date - 20th of the month following the cutoff period.
   */
  protected LocalDate submissionPeriodCutoffDate(YearMonth cutoffPeriod) {
    return cutoffPeriod.plusMonths(1).atDay(20);
  }

  /**
   * Creates a comparator for selecting the anchor claim.
   */
  private static Comparator<Claim> claimComparator(LocalDate incomingDate) {
    Comparator<Claim> byDayDistance =
        Comparator.comparing(
            c -> {
              LocalDate concluded = parseConcludedDate(c);
              return concluded != null
                  ? Math.abs(ChronoUnit.DAYS.between(concluded, incomingDate))
                  : Long.MAX_VALUE;
            },
            Comparator.nullsLast(Comparator.naturalOrder()));

    Comparator<Claim> byLaterSubmissionPeriod =
        Comparator.comparing(
            c -> parseSubmissionPeriod(c.getSubmissionPeriod()),
            Comparator.nullsLast(Comparator.<YearMonth>naturalOrder().reversed()));

    return byDayDistance.thenComparing(byLaterSubmissionPeriod);
  }

  /**
   * Parses the case concluded date from a claim.
   */
  protected static LocalDate parseConcludedDate(Claim claim) {
    String concludedCaseDate = claim.getCaseConcludedDate();
    if (concludedCaseDate == null || concludedCaseDate.isBlank()) {
      return null;
    }
    try {
      return LocalDate.parse(concludedCaseDate, DATE_FORMATTER);
    } catch (Exception e) {
      log.debug(
          "Could not parse caseConcludedDate '{}' for claim {}", concludedCaseDate, claim.getId());
      return null;
    }
  }

  /**
   * Parses submission period string to YearMonth.
   */
  protected static YearMonth parseSubmissionPeriod(String submissionPeriod) {
    if (submissionPeriod == null || submissionPeriod.isBlank()) {
      return null;
    }
    try {
      // TODO: Confirm format - assuming "MMM-yyyy" like "MAY-2025"
      return YearMonth.parse(submissionPeriod, DateTimeFormatter.ofPattern("MMM-yyyy"));
    } catch (Exception e) {
      log.debug("Could not parse submission period: {}", submissionPeriod);
      return null;
    }
  }
}
