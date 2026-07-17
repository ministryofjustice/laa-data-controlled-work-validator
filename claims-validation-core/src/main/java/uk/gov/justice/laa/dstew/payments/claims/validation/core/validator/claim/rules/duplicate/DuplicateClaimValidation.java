package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
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

    try {
      ClaimResultSet resultSet =
          claimsDataProvider.getClaims(
              currentClaim.getOfficeAccountNumber(),
              null, // submissionId - not filtering by submission
              SUBMISSION_STATUSES_FOR_DUPLICATE_CHECK,
              currentClaim.getFeeCode(),
              currentClaim.getUniqueFileNumber(),
              currentClaim.getUniqueClientNumber(),
              null,
              CLAIM_STATUSES_FOR_DUPLICATE_CHECK,
              null, // page
              null, // size
              null); // sort

      if (resultSet == null || resultSet.getContent() == null) {
        return new DuplicateCheckResult(Collections.emptyList(), null);
      }

      // Get the submission ID of the current submission to filter out
      String currentSubmissionId = currentClaim.getSubmissionId() == null
              ? null : currentClaim.getSubmissionId().toString();

      List<Claim> duplicates =
          resultSet.getContent().stream()
              // Filter out claims from the current submission
              .filter(
                  prevClaim ->
                      currentSubmissionId == null
                          || !currentSubmissionId.equals(prevClaim.getSubmissionId()))
              // Convert to our Claim model
              .map(ClaimMapper::fromClaimResponse)
              .toList();

      // Update and set
      duplicates.forEach(claim -> {
        claim.setAreaOfLaw(currentClaim.getAreaOfLaw());
        claim.setOfficeAccountNumber(currentClaim.getOfficeAccountNumber());
      });

      // Filter out any claims that are in the submission claims list
      List<Claim> duplicatesNotInCurrentSubmission =
              duplicates.stream()
                      .filter(claim -> !submissionClaims.contains(claim))
              .toList();

      log.info(
          "Checked for duplicate claims in previous submissions for officeCode={}, "
              + "feeCode={}, uniqueFileNumber={}, uniqueClientNumber={}, Found {} duplicates.",
          currentClaim.getOfficeAccountNumber(),
          currentClaim.getFeeCode(),
          currentClaim.getUniqueFileNumber(),
          currentClaim.getUniqueClientNumber(),
              duplicatesNotInCurrentSubmission.size());

      return new DuplicateCheckResult(duplicatesNotInCurrentSubmission, null);
    } catch (Exception e) {
      log.error(
          "Unable to check for duplicate claims in previous submissions. "
              + "Data Claims API may be unavailable: {}",
          e.getMessage());

      ValidationIssue error =
          ClaimValidationError.TECHNICAL_ERROR_DATA_CLAIMS_API
              .toValidationIssueWithTechnicalMessage("Data Claims API error: " + e.getMessage());

      return new DuplicateCheckResult(null, error);
    }
  }

  /**
   * Find duplicates in the claim's <em>own</em> submission by querying the
   * {@link ClaimsDataProvider}, scoped to {@code currentClaim.getSubmissionId()}.
   *
   * <p>Used on the single-claim validation path, where sibling claims are not supplied in-memory
   * (an empty {@code submissionClaims} list). The current claim is excluded by id so a claim is
   * never its own duplicate. Any provider failure is swallowed and returns an empty list — the
   * previous-submission lookup remains responsible for surfacing technical errors.
   *
   * @param currentClaim the claim being validated; must contain office code, submission id, and the
   *     keys used by {@code matchPredicate}
   * @param matchPredicate area-of-law specific predicate identifying a duplicate (e.g. fee + UFN)
   * @return the matching same-submission claims, excluding the current claim; never {@code null}
   */
  protected List<Claim> getDuplicateClaimsInSameSubmission(
      Claim currentClaim, Predicate<Claim> matchPredicate) {

    if (currentClaim.getSubmissionId() == null) {
      return Collections.emptyList();
    }

    try {
      ClaimResultSet resultSet =
          claimsDataProvider.getClaims(
              currentClaim.getOfficeAccountNumber(),
              currentClaim.getSubmissionId().toString(), // scope to the claim's own submission
              SUBMISSION_STATUSES_FOR_DUPLICATE_CHECK,
              currentClaim.getFeeCode(),
              currentClaim.getUniqueFileNumber(),
              currentClaim.getUniqueClientNumber(),
              null,
              CLAIM_STATUSES_FOR_DUPLICATE_CHECK,
              null, // page
              null, // size
              null); // sort

      if (resultSet == null || resultSet.getContent() == null) {
        return Collections.emptyList();
      }

      return resultSet.getContent().stream()
          .map(ClaimMapper::fromClaimResponse)
          // Exclude self by id — a claim must never be its own duplicate.
          .filter(candidate -> !Objects.equals(candidate.getId(), currentClaim.getId()))
          // Only VALID / READY_TO_PROCESS claims can be duplicates (VOID/others ignored).
          .filter(candidate -> candidate.getStatus() == null
              || LIST_OF_VALID_STATUS.contains(candidate.getStatus()))
          .filter(matchPredicate)
          .toList();
    } catch (Exception e) {
      log.error(
          "Unable to check for duplicate claims in the current submission. "
              + "Data Claims API may be unavailable: {}",
          e.getMessage());
      return Collections.emptyList();
    }
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
