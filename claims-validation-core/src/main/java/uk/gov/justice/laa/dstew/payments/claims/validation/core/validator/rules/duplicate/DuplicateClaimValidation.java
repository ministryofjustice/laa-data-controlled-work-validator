package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules.duplicate;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.DataClaimsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.ClaimMapper;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResultSet;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionStatus;

/**
 * Base class for duplicate claim validation. Provides common methods for checking duplicates in
 * current submission and previous submissions via the DataClaimsClient.
 */
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
   * @return a list of duplicates across other claims by the same office
   */
  protected List<Claim> getDuplicateClaimsInPreviousSubmission(
      String officeCode,
      String feeCode,
      String uniqueFileNumber,
      String uniqueClientNumber,
      String uniqueCaseId,
      List<Claim> submissionClaims) {

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

    if (response == null || response.getBody() == null || response.getBody().getContent() == null) {
      return Collections.emptyList();
    }

    // Get the submission ID of the current submission to filter out
    String currentSubmissionId =
        submissionClaims.isEmpty() || submissionClaims.get(0).getSubmissionId() == null
            ? null
            : submissionClaims.get(0).getSubmissionId().toString();

    return response.getBody().getContent().stream()
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
  }
}
