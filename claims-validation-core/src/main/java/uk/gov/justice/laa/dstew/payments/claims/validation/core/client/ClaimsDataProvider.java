package uk.gov.justice.laa.dstew.payments.claims.validation.core.client;

import java.util.List;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResultSet;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionStatus;

/**
 * Abstraction for fetching claims data for validation. Allows validation logic to be reused
 * regardless of whether data is fetched via HTTP or direct DB access.
 *
 * <p>
 * Implementations may use HTTP (e.g., DataClaimsClient) or direct DB access.
 */
public interface ClaimsDataProvider {
  /**
   * Get claims in an office, filtering on certain criteria.
   *
   * @param officeCode the office code of the claims to be retrieved
   * @param submissionId the submission id of the claims to be retrieved
   * @param submissionStatuses the statuses of the parent submissions
   * @param feeCode the fee code of the claims to be retrieved
   * @param uniqueFileNumber the unique file number of the claims to be retrieved
   * @param uniqueClientNumber the unique client number of the claims to be retrieved
   * @param uniqueCaseId the unique case id of the retrieved claims
   * @param claimStatuses the claim statuses
   * @param page the page number
   * @param size the page size
   * @param sort the sort order
   * @return 200 OK with JSON body containing the list of matched claims
   */
  ResponseEntity<ClaimResultSet> getClaims(
      String officeCode,
      String submissionId,
      List<SubmissionStatus> submissionStatuses,
      String feeCode,
      String uniqueFileNumber,
      String uniqueClientNumber,
      String uniqueCaseId,
      List<ClaimStatus> claimStatuses,
      Integer page,
      Integer size,
      String sort
  );
}
