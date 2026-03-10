package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules.duplicate;

import java.util.List;
import java.util.function.Predicate;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.ClaimsApiClient;

/**
 * Base class for duplicate claim validation. Provides common methods for checking duplicates in
 * current submission and previous submissions via the DuplicateClaimClient.
 */
public abstract class DuplicateClaimValidation {

  protected static final List<ClaimStatus> LIST_OF_NON_INVALID_STATUS =
      List.of(ClaimStatus.READY_TO_PROCESS, ClaimStatus.VALID);

  protected final ClaimsApiClient claimsApiClient;

  protected DuplicateClaimValidation(ClaimsApiClient claimsApiClient) {
    this.claimsApiClient = claimsApiClient;
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
   * Search for duplicates in all other claims made by this office. Uses the DuplicateClaimClient to
   * query for potential duplicates.
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

    // TODO: DuplicateClaimClient.getClaims() needs to be implemented
    // It should call the Data Claims API with these parameters and return matching claims
    // For now, return empty list as placeholder
    return claimsApiClient.getClaims(
        officeCode, feeCode, uniqueFileNumber, uniqueClientNumber, uniqueCaseId, submissionClaims);
  }
}
