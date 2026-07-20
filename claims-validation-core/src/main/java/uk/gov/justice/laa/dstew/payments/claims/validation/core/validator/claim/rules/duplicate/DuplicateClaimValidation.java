package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.ClaimMapper;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResultSet;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionStatus;

/**
 * Base class for duplicate claim validation. Provides common methods for checking duplicates in
 * current submission and previous submissions via a {@link ClaimsDataProvider} abstraction.
 *
 * <p>
 * This allows validation logic to be reused regardless of whether data is fetched via HTTP
 * (DataClaimsClient) or direct DB access.
 */
@Slf4j
public abstract class DuplicateClaimValidation {

  protected static final List<ClaimStatus> LIST_OF_VALID_STATUS =
      List.of(ClaimStatus.READY_TO_PROCESS, ClaimStatus.VALID);

  protected static final List<SubmissionStatus> SUBMISSION_STATUSES_FOR_DUPLICATE_CHECK =
      List.of(
          SubmissionStatus.CREATED,
          SubmissionStatus.VALIDATION_IN_PROGRESS,
          SubmissionStatus.READY_FOR_VALIDATION,
          SubmissionStatus.VALIDATION_SUCCEEDED);

  protected static final List<ClaimStatus> CLAIM_STATUSES_FOR_DUPLICATE_CHECK =
      List.of(ClaimStatus.READY_TO_PROCESS, ClaimStatus.VALID);

  protected final ClaimsDataProvider claimsDataProvider;

  protected DuplicateClaimValidation(ClaimsDataProvider claimsDataProvider) {
    this.claimsDataProvider = claimsDataProvider;
  }

  /**
   * Filter the claims in the submission to contain all claims except the one currently under
   * validation, and only include claims with valid status.
   *
   * @param currentClaim the claim to filter out
   * @param submissionClaims the list of claims in the submission
   * @return a filtered list of claims in the submission, excluding the given claim
   */
  protected List<Claim> filterCurrentClaimWithValidStatus(
      Claim currentClaim, List<Claim> submissionClaims) {
    return submissionClaims.stream()
        .filter(submissionClaim -> !submissionClaim.equals(currentClaim))
        .filter(
            submissionClaim ->
                submissionClaim.getStatus() == null
                    || LIST_OF_VALID_STATUS.stream()
                        .anyMatch(
                            validStatus ->
                                validStatus.name().equals(submissionClaim.getStatus().name())))
        .toList();
  }

  /**
   * Find duplicates in a list of claims, using a predicate to determine whether the claim is a
   * duplicate.
   *
   * @param otherClaims the list of claims to compare against
   * @param duplicatePredicate predicate to determine whether a claim is a duplicate
   * @return the list of duplicate claims, as determined by the given predicate
   */
  protected List<Claim> getDuplicateClaimsInCurrentSubmission(
      List<Claim> otherClaims, Predicate<Claim> duplicatePredicate) {
    return otherClaims.stream().filter(duplicatePredicate).toList();
  }

  /**
   * Result of checking for duplicate claims in previous submissions. Contains either the list of
   * duplicates found, or an error if the check could not be completed.
   */
  public record DuplicateCheckResult(List<Claim> duplicates, ValidationIssue error) {
    public boolean hasError() {
      return error != null;
    }

    public boolean hasDuplicates() {
      return duplicates != null && !duplicates.isEmpty();
    }
  }

  /**
   * Shared low-level Data Claims API query used by both the same-submission and previous-submission
   * duplicate checks. Performs the provider call using the standard duplicate-check submission and
   * claim statuses, and maps the results to the core {@link Claim} model.
   *
   * <p><strong>Fails closed.</strong> Any provider failure yields a result carrying a
   * {@code TECHNICAL_ERROR_DATA_CLAIMS_API} error rather than an empty list — a claim must never be
   * passed as "not a duplicate" simply because the duplicate check could not be completed.
   *
   * <p>No domain filtering (self-exclusion, submission scoping, match predicates) is applied here;
   * callers layer that on top of the returned claims.
   *
   * @param submissionId the submission id to scope the query to, or {@code null} for all
   *     submissions
   * @param officeCode the office account number (required)
   * @param feeCode the fee code to filter by, or {@code null} to omit it
   * @param uniqueFileNumber the unique file number to filter by, or {@code null} to omit it
   * @param uniqueClientNumber the unique client number to filter by, or {@code null} to omit it
   * @param uniqueCaseId the unique case id to filter by, or {@code null} to omit it
   * @return a DuplicateCheckResult with the mapped matching claims, or a technical error if the
   *     provider could not be reached
   */
  protected DuplicateCheckResult getDuplicateClaims(
      String submissionId,
      String officeCode,
      String feeCode,
      String uniqueFileNumber,
      String uniqueClientNumber,
      String uniqueCaseId) {
    try {
      ClaimResultSet resultSet =
          claimsDataProvider.getClaims(
              officeCode,
              submissionId,
              SUBMISSION_STATUSES_FOR_DUPLICATE_CHECK,
              feeCode,
              uniqueFileNumber,
              uniqueClientNumber,
              uniqueCaseId,
              CLAIM_STATUSES_FOR_DUPLICATE_CHECK,
              null, // page
              null, // size
              null); // sort

      if (resultSet == null || resultSet.getContent() == null) {
        return new DuplicateCheckResult(Collections.emptyList(), null);
      }

      List<Claim> claims =
          resultSet.getContent().stream().map(ClaimMapper::fromClaimResponse).toList();
      return new DuplicateCheckResult(claims, null);
    } catch (Exception e) {
      log.error(
          "Unable to check for duplicate claims. Data Claims API may be unavailable.", e);
      ValidationIssue error =
          ClaimValidationError.TECHNICAL_ERROR_DATA_CLAIMS_API
              .toValidationIssueWithTechnicalMessage("Data Claims API error: " + e.getMessage());
      return new DuplicateCheckResult(null, error);
    }
  }

  /**
   * Search for duplicates in all other claims made by this office, with the same office code, fee
   * code, and unique file number. Ignore claims within this submission as they are verified
   * separately.
   *
   * @param currentClaim the claim being validated for duplicates.
   *                     Must contain office code, fee code, unique file number,
   *                     and unique client number.
   * @param submissionClaims the list of claims in the current submission, to filter out
   *                         from results
   * @return a DuplicateCheckResult containing either duplicates or an error
   */
  protected DuplicateCheckResult getDuplicateClaimsInPreviousSubmission(
      Claim currentClaim,
      List<Claim> submissionClaims
  ) {
    return getDuplicateClaimsInPreviousSubmission(
        currentClaim, submissionClaims, currentClaim.getUniqueClientNumber());
  }

  /**
   * As {@link #getDuplicateClaimsInPreviousSubmission(Claim, List)} but with an explicit unique
   * client number filter.
   *
   * <p>Strategies whose dedupe key does not include the UCN (e.g. Crime Lower, which keys on fee
   * code + unique file number only) must pass {@code null} so the provider query is not
   * over-constrained and genuine duplicates carrying a different UCN are not silently filtered out.
   *
   * @param currentClaim the claim being validated for duplicates
   * @param submissionClaims the claims in the current submission, to filter out from results
   * @param uniqueClientNumber the unique client number to filter by, or {@code null} to omit it
   * @return a DuplicateCheckResult containing either duplicates or an error
   */
  protected DuplicateCheckResult getDuplicateClaimsInPreviousSubmission(
      Claim currentClaim,
      List<Claim> submissionClaims,
      String uniqueClientNumber
  ) {

    DuplicateCheckResult result =
        getDuplicateClaims(
            null, // submissionId - not filtering by submission
            currentClaim.getOfficeAccountNumber(),
            currentClaim.getFeeCode(),
            currentClaim.getUniqueFileNumber(),
            uniqueClientNumber,
            null); // uniqueCaseId

    if (result.hasError()) {
      return result;
    }

    // Get the submission ID of the current submission to filter out
    UUID currentSubmissionId = currentClaim.getSubmissionId();

    List<Claim> duplicates =
        result.duplicates().stream()
            // Filter out claims from the current submission
            .filter(
                prevClaim ->
                    currentSubmissionId == null
                        || !currentSubmissionId.equals(prevClaim.getSubmissionId()))
            .toList();

    // Set area of law and office so equals-based exclusion against submissionClaims works
    duplicates.forEach(
        claim -> {
          claim.setAreaOfLaw(currentClaim.getAreaOfLaw());
          claim.setOfficeAccountNumber(currentClaim.getOfficeAccountNumber());
        });

    // Filter out any claims that are in the submission claims list
    List<Claim> duplicatesNotInCurrentSubmission =
        duplicates.stream().filter(claim -> !submissionClaims.contains(claim)).toList();

    log.info(
        "Checked for duplicate claims in previous submissions for officeCode={}, "
            + "feeCode={}, uniqueFileNumber={}, uniqueClientNumber={}, Found {} duplicates.",
        currentClaim.getOfficeAccountNumber(),
        currentClaim.getFeeCode(),
        currentClaim.getUniqueFileNumber(),
        currentClaim.getUniqueClientNumber(),
        duplicatesNotInCurrentSubmission.size());

    return new DuplicateCheckResult(duplicatesNotInCurrentSubmission, null);
  }

  /**
   * Find duplicates in the claim's <em>own</em> submission by querying the
   * {@link ClaimsDataProvider}, scoped to {@code currentClaim.getSubmissionId()}.
   *
   * <p>Used on the single-claim validation path, where sibling claims are not supplied in-memory
   * (an empty {@code submissionClaims} list). The current claim is excluded by id so a claim is
   * never its own duplicate.
   *
   * <p><strong>Fails closed.</strong> If the provider cannot be reached, the returned result
   * carries a {@code TECHNICAL_ERROR_DATA_CLAIMS_API} error rather than an empty match list — a
   * claim must never be passed as "not a duplicate" simply because the duplicate check could not be
   * completed.
   *
   * @param currentClaim the claim being validated; must contain office code, submission id, and the
   *     keys used by {@code matchPredicate}
   * @param matchPredicate area-of-law specific predicate identifying a duplicate (e.g. fee + UFN)
   * @return a DuplicateCheckResult with the matching same-submission claims, or an error if the
   *     provider could not be reached
   */
  protected DuplicateCheckResult getDuplicateClaimsInSameSubmission(
      Claim currentClaim, Predicate<Claim> matchPredicate) {
    return getDuplicateClaimsInSameSubmission(
        currentClaim, matchPredicate, currentClaim.getUniqueClientNumber());
  }

  /**
   * As {@link #getDuplicateClaimsInSameSubmission(Claim, Predicate)} but with an explicit unique
   * client number filter. Strategies whose dedupe key does not include the UCN (e.g. Crime Lower)
   * must pass {@code null} so the provider query is not over-constrained.
   *
   * @param currentClaim the claim being validated; must contain office code and submission id
   * @param matchPredicate area-of-law specific predicate identifying a duplicate (e.g. fee + UFN)
   * @param uniqueClientNumber the unique client number to filter by, or {@code null} to omit it
   * @return a DuplicateCheckResult with the matching same-submission claims, or an error if the
   *     provider could not be reached
   */
  protected DuplicateCheckResult getDuplicateClaimsInSameSubmission(
      Claim currentClaim, Predicate<Claim> matchPredicate, String uniqueClientNumber) {

    if (currentClaim.getSubmissionId() == null) {
      return new DuplicateCheckResult(Collections.emptyList(), null);
    }

    DuplicateCheckResult result =
        getDuplicateClaims(
            currentClaim.getSubmissionId().toString(), // scope to the claim's own submission
            currentClaim.getOfficeAccountNumber(),
            currentClaim.getFeeCode(),
            currentClaim.getUniqueFileNumber(),
            uniqueClientNumber,
            null); // uniqueCaseId

    if (result.hasError()) {
      return result;
    }

    List<Claim> matches =
        result.duplicates().stream()
            // Exclude self by id — a claim must never be its own duplicate.
            .filter(candidate -> !Objects.equals(candidate.getId(), currentClaim.getId()))
            // Only VALID / READY_TO_PROCESS claims can be duplicates (VOID/others ignored).
            .filter(candidate -> candidate.getStatus() == null
                || LIST_OF_VALID_STATUS.contains(candidate.getStatus()))
            .filter(matchPredicate)
            .toList();
    return new DuplicateCheckResult(matches, null);
  }

  /**
   * Determine same-submission duplicates for {@code currentClaim}, choosing the data source based
   * on whether in-memory sibling claims are supplied.
   *
   * <ul>
   *   <li><b>Bulk path</b> ({@code submissionClaims} non-empty): the siblings are filtered to valid
   *       status and matched by {@code matchPredicate} — no provider call, so no error is possible.
   *   <li><b>Single-claim path</b> ({@code submissionClaims} empty): the
   *       {@link ClaimsDataProvider} is queried, scoped to the claim's own submission. This
   *       <strong>fails closed</strong> — a provider failure yields an error result.
   * </ul>
   *
   * <p><strong>Consistency requirement:</strong> {@code uniqueClientNumber} is the provider-side
   * filter and MUST agree with {@code matchPredicate}. Pass {@code null} when the predicate does
   * not key on UCN (e.g. Crime Lower), otherwise the provider query would over-filter and drop
   * genuine duplicates before the predicate runs.
   *
   * @param currentClaim the claim being validated
   * @param submissionClaims the in-memory claims of the current submission (empty on the
   *     single-claim path)
   * @param matchPredicate area-of-law specific predicate identifying a duplicate
   * @param uniqueClientNumber the UCN to filter the provider query by, or {@code null} to omit it
   * @return a DuplicateCheckResult with the same-submission matches, or an error if the provider
   *     could not be reached
   */
  protected DuplicateCheckResult findSameSubmissionDuplicates(
      Claim currentClaim,
      List<Claim> submissionClaims,
      Predicate<Claim> matchPredicate,
      String uniqueClientNumber) {
    if (submissionClaims.isEmpty()) {
      return getDuplicateClaimsInSameSubmission(currentClaim, matchPredicate, uniqueClientNumber);
    }

    List<Claim> siblings = filterCurrentClaimWithValidStatus(currentClaim, submissionClaims);
    List<Claim> matches = getDuplicateClaimsInCurrentSubmission(siblings, matchPredicate);
    return new DuplicateCheckResult(matches, null);
  }

  /**
   * As {@link #findSameSubmissionDuplicates(Claim, List, Predicate, String)} but defaulting the
   * provider-side UCN filter to the claim's own unique client number. Use this when the match
   * predicate keys on UCN (e.g. Legal Help).
   *
   * @param currentClaim the claim being validated
   * @param submissionClaims the in-memory claims of the current submission
   * @param matchPredicate area-of-law specific predicate identifying a duplicate
   * @return a DuplicateCheckResult with the same-submission matches, or an error if the provider
   *     could not be reached
   */
  protected DuplicateCheckResult findSameSubmissionDuplicates(
      Claim currentClaim, List<Claim> submissionClaims, Predicate<Claim> matchPredicate) {
    return findSameSubmissionDuplicates(
        currentClaim, submissionClaims, matchPredicate, currentClaim.getUniqueClientNumber());
  }

  /**
   * Logs duplicate claims found during validation.
   *
   * @param currentClaim the claim being validated
   * @param duplicates the list of duplicate claims found
   */
  public void logDuplicates(Claim currentClaim, List<Claim> duplicates) {
    if (log.isDebugEnabled()) {
      log.debug(
          "Found {} duplicate(s) for claim {}: {}",
          duplicates.size(),
          currentClaim.getId(),
          duplicates.stream().map(c -> String.valueOf(c.getId())).toList());
    }
  }
}
