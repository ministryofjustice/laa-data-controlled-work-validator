package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate;

import static uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils.DATE_FORMATTER_YYYY_MM_DD;
import static uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils.MAXIMUM_MONTHS_DIFFERENCE;
import static uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils.parseSubmissionPeriod;
import static uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils.submissionPeriodCutoffDate;
import static uk.gov.justice.laa.dstew.payments.claims.validation.core.util.FeeTypeUtils.isDisbursementClaim;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;

/** Duplicate-claim validation strategy for Legal Help disbursement claims. */
@Slf4j
public class DuplicateClaimLegalHelpDisbursementValidationStrategy extends DuplicateClaimValidation
        implements LegalHelpDuplicateClaimValidationStrategy {

  /**
   * Returns a {@link Comparator} used to select the anchor claim during Rule B evaluation.
   *
   * <p>Candidates are ordered primarily by the ascending absolute difference in days between their
   * Case Concluded Date and {@code incomingDate}. Where two candidates are equally close, the
   * tie-break prefers the candidate from the later submission period, ensuring deterministic
   * selection regardless of the order in which claims are processed.
   *
   * <p>Candidates with a missing or unparseable Case Concluded Date are treated as infinitely
   * distant and sorted last. Candidates with a {@code null} submission period are also sorted last
   * during the tie-break.
   *
   * @param incomingDate the Case Concluded Date of the incoming claim
   * @return a comparator ordered by proximity to {@code incomingDate}, then by later submission
   *     period
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
   * Creates a new {@code DuplicateClaimLegalHelpDisbursementValidationStrategy}.
   *
   * @param dataClaimsRestClient the REST client used to retrieve claims from the data store
   */
  public DuplicateClaimLegalHelpDisbursementValidationStrategy(
          final ClaimsDataProvider dataClaimsRestClient) {
    super(dataClaimsRestClient);
  }

  /**
   * Validates that the incoming disbursement claim is not a duplicate of a claim already present in
   * a previous submission.
   *
   * <p>Validation is skipped immediately if the claim is not of disbursement type, as this strategy
   * is only applicable to disbursement claims.
   *
   * <p>Duplicate detection proceeds in two steps:
   *
   * <ol>
   *   <li><b>Previous-submission lookup</b> — retrieves all claims from previous submissions that
   *       share the same office code, fee code, unique file number, and unique client number.
   *   <li><b>Case Concluded Date boundary (Rule B)</b> — from the matched candidates, selects the
   *       single <em>anchor</em> claim whose Case Concluded Date is closest (in absolute days) to
   *       the incoming claim's Case Concluded Date. Where two candidates are equally close, the one
   *       from the later submission period is used as the anchor. The anchor period is the latter
   *       of the two submission periods; the cutoff is the 20th day of the month following the
   *       month that is 3 months before the anchor period (e.g. an anchor period of MAY-2025 yields
   *       a cutoff of 20 MAR-2025). The incoming claim is rejected as a duplicate when the earlier
   *       of the two Case Concluded Dates is strictly after the cutoff.
   * </ol>
   *
   * <p>Rule B is not applied, and no duplicate error is raised, when:
   *
   * <ul>
   *   <li>the incoming claim has a missing or unparseable Case Concluded Date — this condition is
   *       expected to be caught by mandatory field and date-format validation upstream; or
   *   <li>none of the matched candidates have a valid Case Concluded Date, making a boundary
   *       comparison impossible.
   * </ul>
   *
   * @param incomingClaim the claim currently being validated
   * @param submissionClaims all claims belonging to the current submission
   * @param officeCode the office code associated with the submission
   * @param feeType the fee calculation type of the incoming claim
   */
  @Override
  public List<ValidationIssue> validateDuplicateClaims(
          final Claim incomingClaim,
          final List<Claim> submissionClaims,
          final String officeCode,
          final String feeType) {

    List<ValidationIssue> issues = new ArrayList<>();

    if (!isDisbursementClaim(feeType)) {
      return issues;
    }

    List<Claim> candidateDuplicateClaim =
            findEligibleDuplicateClaims(incomingClaim, submissionClaims, officeCode);
    if (candidateDuplicateClaim.isEmpty()) {
      return issues;
    }

    LocalDate incomingConcludedDate = parseConcludedDate(incomingClaim);
    if (incomingConcludedDate == null) {
      // The incoming claim has no valid Case Concluded Date; Rule B cannot be applied.
      // No duplicate error is raised as this will be handled by upstream date validation.
      return issues;
    }

    // candidates is guaranteed non-empty at this point, so selectComparativeClaim will always
    // return a value.
    Claim duplicateClaim =
            selectComparativeClaim(candidateDuplicateClaim, incomingConcludedDate);

    if (isDuplicateClaim(incomingClaim, duplicateClaim)) {
      logDuplicates(incomingClaim, List.of(duplicateClaim));
      issues.add(
              ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION
                      .toValidationIssue());
    }
    return issues;
  }

  /**
   * Retrieves all claims from previous submissions that share the same office code, fee code,
   * unique file number, and unique client number as the incoming claim, and that carry a valid Case
   * Concluded Date eligible for Rule B evaluation.
   *
   * @param incomingClaim the claim currently being validated
   * @param submissionClaims all claims belonging to the current submission
   * @param officeCode the office code associated with the submission
   * @return a list of eligible candidate claims; empty if none are found
   */
  protected List<Claim> findEligibleDuplicateClaims(
          Claim incomingClaim, List<Claim> submissionClaims, String officeCode) {
    var previous = getDuplicateClaimsInPreviousSubmission(
        officeCode,
        incomingClaim.getFeeCode(),
        incomingClaim.getUniqueFileNumber(),
        incomingClaim.getUniqueClientNumber(),
        null);

    List<Claim> duplicates = (previous == null || previous.duplicates() == null)
        ? java.util.List.of()
        : previous.duplicates();

    return duplicates.stream().filter(c -> parseConcludedDate(c) != null).toList();
  }

  /**
   * Selects the anchor claim from the given candidates — the claim whose Case Concluded Date is
   * closest (in absolute days) to {@code incomingDate}. Where two candidates are equally close, the
   * one from the later submission period is preferred, ensuring a deterministic result regardless
   * of the order in which claims are processed.
   *
   * @param claims the eligible candidate claims, all of which have a valid Case Concluded Date
   * @param incomingDate the Case Concluded Date of the incoming claim
   * @return the selected anchor claim, or {@code null} if the candidate list is empty
   */
  protected Claim selectComparativeClaim(
          List<Claim> claims, LocalDate incomingDate) {
    return claims.stream().min(claimComparator(incomingDate)).orElse(null);
  }

  /**
   * Applies the Rule B Case Concluded Date boundary check between the incoming claim and the
   * selected anchor claim.
   *
   * <p>The anchor period is the latter of the two submission periods. The cutoff is the 20th day of
   * the month following the month that is 3 months before the anchor period (e.g. an anchor period
   * of MAY-2025 yields a cutoff of 20 MAR-2025). The incoming claim is considered a duplicate when
   * the earlier of the two Case Concluded Dates falls strictly after the cutoff.
   *
   * <p>Returns {@code false} if either submission period or either Case Concluded Date is absent,
   * as a boundary comparison cannot be completed.
   *
   * @param incomingClaim the claim currently being validated
   * @param duplicateClaim the selected anchor claim from a previous submission
   * @return {@code true} if the incoming claim should be rejected as a duplicate; {@code false}
   *     otherwise
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
      // Insufficient date data to complete Rule B; no duplicate error is raised.
      return false;
    }

    // Derive cutoff period and cutoff date from the latter of the two submission periods, ensuring
    // anchoring the boundary check in the same way regardless of which claim is incoming and which
    // is the anchor
    YearMonth cutoffPeriod = getCutoffPeriod(incomingSubmissionPeriod, anchorSubmissionPeriod);
    LocalDate cutoff = submissionPeriodCutoffDate(cutoffPeriod);

    // The claim is a duplicate only when the earlier of the two Case Concluded Dates falls
    // strictly after the cutoff.
    LocalDate earlierConcludedDate =
            incomingConcludedDate.isBefore(anchorConcludedDate)
                    ? incomingConcludedDate
                    : anchorConcludedDate;

    return earlierConcludedDate.isAfter(cutoff);
  }

  /**
   * Determines the cutoff period used in the Rule B duplicate boundary check.
   *
   * <p>The anchor period is the latter of the two submission periods, ensuring the check is
   * order-independent — the same cutoff is produced regardless of which claim arrives first. The
   * cutoff period is then derived by subtracting 3 months from the anchor period.
   *
   * <p>For example, given an anchor period of MAY-2025:
   *
   * <pre>
   *   Anchor period  = MAY-2025
   *   Cutoff period  = FEB-2025  (MAY minus 3 months)
   * </pre>ß
   *
   * @param incomingSubmissionPeriod the submission period of the incoming claim
   * @param anchorSubmissionPeriod the submission period of the matched anchor claim
   * @return the cutoff {@link YearMonth}, derived from the latter of the two submission periods
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
   * Attempts to parse the {@code caseConcludedDate} field of the given claim.
   *
   * @param claim the claim whose Case Concluded Date is to be parsed
   * @return the parsed {@link LocalDate}, or {@code null} if the value is {@code null}, blank, or
   *     not a valid date
   */
  protected static LocalDate parseConcludedDate(Claim claim) {
    String concludedCaseDate = claim.getCaseConcludedDate();
    if (concludedCaseDate == null || concludedCaseDate.isBlank()) {
      return null;
    }
    try {
      return LocalDate.parse(concludedCaseDate, DATE_FORMATTER_YYYY_MM_DD);
    } catch (Exception e) {
      log.debug(
              "Could not parse caseConcludedDate '{}' for claim {}",
              concludedCaseDate,
              claim.getId()
      );
      return null;
    }
  }
}
