package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
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
   * @param officeCode the unique identifier for the office
   * @param feeCode the fee code
   * @param uniqueFileNumber the unique file number for the claim
   * @param uniqueClientNumber the unique client number for the claim
   * @param submissionId the submission id to exclude
   * @return a DuplicateCheckResult containing either duplicates or an error
   */
  protected DuplicateCheckResult getDuplicateClaimsInPreviousSubmission(
      String officeCode,
      String feeCode,
      String uniqueFileNumber,
      String uniqueClientNumber,
      UUID submissionId) {

    try {
      ResponseEntity<ClaimResultSet> response =
          claimsDataProvider.getClaims(
              officeCode,
              null, // submissionId - not filtering by submission
              SUBMISSION_STATUSES_FOR_DUPLICATE_CHECK,
              feeCode,
              uniqueFileNumber,
              uniqueClientNumber,
              null,
              CLAIM_STATUSES_FOR_DUPLICATE_CHECK,
              null, // page
              null, // size
              null); // sort

      if (response == null
          || response.getBody() == null
          || response.getBody().getContent() == null) {
        return new DuplicateCheckResult(Collections.emptyList(), null);
      }

      // Get the submission ID of the current submission to filter out
      String currentSubmissionId = submissionId == null ? null : submissionId.toString();

      List<Claim> duplicates =
          response.getBody().getContent().stream()
              // Filter out claims from the current submission
              .filter(
                  prevClaim ->
                      currentSubmissionId == null
                          || !currentSubmissionId.equals(prevClaim.getSubmissionId()))
              // Convert to our Claim model
              .map(ClaimMapper::fromClaimResponse)
              // Filter out any claims that are in the submission claims list: NOTE this is
              // redundant we do 1 claim at a time and have already filtered by submission id
              // .filter(claim -> !submissionClaims.contains(claim))
              .toList();

      log.info(
          "Checked for duplicate claims in previous submissions for officeCode={}, "
              + "feeCode={}, uniqueFileNumber={}, uniqueClientNumber={}, Found {} duplicates.",
          officeCode,
          feeCode,
          uniqueFileNumber,
          uniqueClientNumber,
          duplicates.size());

      return new DuplicateCheckResult(duplicates, null);
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
