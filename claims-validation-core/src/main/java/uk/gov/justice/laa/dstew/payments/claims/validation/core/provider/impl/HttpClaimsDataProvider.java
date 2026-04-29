package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.DataClaimsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResultSet;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionStatus;

/**
 * HTTP-backed implementation of {@link ClaimsDataProvider}.
 *
 * <p>Adapts {@link DataClaimsClient} to the transport-agnostic {@link ClaimsDataProvider}
 * interface by delegating to the REST client and unwrapping the {@link ResponseEntity} so that
 * validation logic remains decoupled from HTTP concerns.
 *
 * <p>Use this implementation when the validation library is deployed as a standalone service that
 * calls the Data Claims API over HTTP:
 *
 * <pre>{@code
 * // Spring configuration for an external validation service:
 * @Bean
 * public ClaimsDataProvider claimsDataProvider(DataClaimsClient client) {
 *     return new HttpClaimsDataProvider(client);
 * }
 * }</pre>
 *
 * <p>When embedding the validation library inside the Claims API itself, use a
 * repository-backed implementation instead to avoid a self-referential HTTP call:
 *
 * <pre>{@code
 * // Spring configuration inside the Claims API:
 * @Bean
 * public ClaimsDataProvider claimsDataProvider(ClaimsRepository repo) {
 *     return new RepositoryClaimsDataProvider(repo);
 * }
 * }</pre>
 *
 * @see ClaimsDataProvider
 * @see DataClaimsClient
 */
@Slf4j
@RequiredArgsConstructor
public class HttpClaimsDataProvider implements ClaimsDataProvider {

  private final DataClaimsClient dataClaimsClient;

  /**
   * Retrieves claims by delegating to {@link DataClaimsClient} and returning the response body.
   *
   * <p>Returns an empty {@link ClaimResultSet} if the response body is {@code null}, ensuring
   * callers always receive a non-null result.
   *
   * @param officeCode the office account number (required)
   * @param submissionId filter by parent submission id
   * @param submissionStatuses filter by parent submission statuses
   * @param feeCode filter by fee code
   * @param uniqueFileNumber filter by unique file number
   * @param uniqueClientNumber filter by unique client number
   * @param uniqueCaseId filter by unique case id
   * @param claimStatuses filter by claim statuses
   * @param page zero-based page number for pagination
   * @param size page size for pagination
   * @param sort sort expression (e.g. {@code "createdAt,desc"})
   * @return the matching claims; never {@code null}
   */
  @Override
  public ClaimResultSet getClaims(
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
      String sort) {

    ResponseEntity<ClaimResultSet> response =
        dataClaimsClient.getClaims(
            officeCode,
            submissionId,
            submissionStatuses,
            feeCode,
            uniqueFileNumber,
            uniqueClientNumber,
            uniqueCaseId,
            claimStatuses,
            page,
            size,
            sort);

    if (response == null || response.getBody() == null) {
      log.debug("DataClaimsClient returned null body for officeCode={}; returning empty result set",
          officeCode);
      return new ClaimResultSet();
    }

    return response.getBody();
  }
}
