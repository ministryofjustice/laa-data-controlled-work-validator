package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.DataClaimsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResultSet;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionBase;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionStatus;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionsResultSet;

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
      log.debug("DataClaimsClient getClaims returned null body for "
                      + "officeCode={}; returning empty result set",
          officeCode);
      return new ClaimResultSet();
    }

    return response.getBody();
  }

  @Override
  public List<SubmissionBase> getSubmissions(
      List<String> officeCodes,
      AreaOfLaw areaOfLaw,
      String submissionPeriod) {
    ResponseEntity<SubmissionsResultSet> response =
            dataClaimsClient.getSubmissions(officeCodes, areaOfLaw, submissionPeriod);

    if (response == null || response.getBody() == null || response.getBody().getContent() == null) {
      log.debug("DataClaimsClient getSubmissions returned null body for "
                      + "officeCode={}; returning empty result set",
              officeCodes);
      return List.of();
    }

    return response.getBody().getContent();
  }
}
