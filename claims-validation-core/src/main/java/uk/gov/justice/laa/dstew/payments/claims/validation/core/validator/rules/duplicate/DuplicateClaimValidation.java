package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules.duplicate;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.DataClaimsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.ClaimMapper;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResultSet;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionStatus;

/**
 * Base class for duplicate claim validation. Provides common methods for checking duplicates in
 * current submission and previous submissions via the DataClaimsClient.
 */
@Slf4j
public abstract class DuplicateClaimValidation {

  protected static final List<ClaimStatus> LIST_OF_NON_INVALID_STATUS =
      List.of(ClaimStatus.READY_TO_PROCESS, ClaimStatus.VALID);

  protected static final List<SubmissionStatus> SUBMISSION_STATUSES_FOR_DUPLICATE_CHECK =
      List.of(
          SubmissionStatus.CREATED,
          SubmissionStatus.VALIDATION_IN_PROGRESS,
          SubmissionStatus.READY_FOR_VALIDATION,
          SubmissionStatus.VALIDATION_SUCCEEDED);

  protected static final List<uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus>
      CLAIM_STATUSES_FOR_DUPLICATE_CHECK =
          List.of(
              uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus.READY_TO_PROCESS,
              uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus.VALID);

  protected final DataClaimsClient dataClaimsClient;

  protected DuplicateClaimValidation(DataClaimsClient dataClaimsClient) {
    this.dataClaimsClient = dataClaimsClient;
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
                    || LIST_OF_NON_INVALID_STATUS.contains(submissionClaim.getStatus()))
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
   * @param uniqueCaseId the unique case ID (optional)
   * @param submissionClaims list of claims in the current submission (to exclude)
   * @return a DuplicateCheckResult containing either duplicates or an error
   */
  protected DuplicateCheckResult getDuplicateClaimsInPreviousSubmission(
      String officeCode,
      String feeCode,
      String uniqueFileNumber,
      String uniqueClientNumber,
      String uniqueCaseId,
      List<Claim> submissionClaims) {

    try {
      ResponseEntity<ClaimResultSet> response =
          dataClaimsClient.getClaims(
              officeCode,
              null, // submissionId - not filtering by submission
              SUBMISSION_STATUSES_FOR_DUPLICATE_CHECK,
              feeCode,
              uniqueFileNumber,
              uniqueClientNumber,
              uniqueCaseId,
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
      String currentSubmissionId =
          submissionClaims.isEmpty() || submissionClaims.get(0).getSubmissionId() == null
              ? null
              : submissionClaims.get(0).getSubmissionId().toString();

      List<Claim> duplicates =
          response.getBody().getContent().stream()
              // Filter out claims from the current submission
              .filter(
                  prevClaim ->
                      currentSubmissionId == null
                          || !currentSubmissionId.equals(prevClaim.getSubmissionId()))
              // Convert to our Claim model
              .map(ClaimMapper::fromClaimResponse)
              // Filter out any claims that are in the submission claims list
              .filter(claim -> !submissionClaims.contains(claim))
              .toList();

      return new DuplicateCheckResult(duplicates, null);
    } catch (Exception e) {
      log.error(
          "Unable to check for duplicate claims in previous submissions. "
              + "Data Claims API may be unavailable: {}",
          e.getMessage());

      ValidationIssue error =
          new ValidationIssue(
              "TECHNICAL_ERROR_DATA_CLAIMS_API",
              "Unable to complete duplicate claim check due to a technical error. "
                  + "Please try again later.",
              ValidationSeverity.ERROR);
      error.setTechnicalMessage("Data Claims API error: " + e.getMessage());

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
