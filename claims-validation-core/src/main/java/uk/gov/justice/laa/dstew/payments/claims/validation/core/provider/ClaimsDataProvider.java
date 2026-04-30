package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider;

import java.util.List;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResultSet;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionBase;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionStatus;

/**
 * Transport-agnostic abstraction for fetching claims data required by validation logic.
 *
 * <p>Decouples validation rules from the data source, enabling the same validation library to be
 * used in two distinct deployment contexts:
 *
 * <ul>
 *   <li><b>External consumer (HTTP)</b> — wire in {@code HttpClaimsDataProvider}, which delegates
 *       to {@code DataClaimsClient} over REST.
 *   <li><b>In-process (direct DB)</b> — wire in a repository-backed implementation that queries
 *       the database directly, avoiding a self-referential HTTP call when the validation library
 *       is embedded inside the Claims API itself.
 * </ul>
 *
 * <p>Implementations must not leak transport concerns (e.g. HTTP status codes) through this
 * interface. The return type is the plain domain model; error handling is the responsibility of
 * each implementation.
 *
 * <h2>Usage example — HTTP (external service)</h2>
 *
 * <pre>{@code
 * // In Spring configuration of an external validation service:
 * @Bean
 * public ClaimsDataProvider claimsDataProvider(DataClaimsClient client) {
 *     return new HttpClaimsDataProvider(client);
 * }
 * // Flow: Validation → HttpClaimsDataProvider → DataClaimsClient → HTTP → Claims API
 * }</pre>
 *
 * <h2>Usage example — direct DB (Claims API itself)</h2>
 *
 * <pre>{@code
 * // In the Claims API's Spring configuration:
 * @Bean
 * public ClaimsDataProvider claimsDataProvider(ClaimsRepository repo) {
 *     return new RepositoryClaimsDataProvider(repo);
 * }
 * // Flow: Validation → RepositoryClaimsDataProvider → JPA → Database
 * }</pre>
 */
public interface ClaimsDataProvider {

  /**
   * Retrieve claims matching the given criteria.
   *
   * <p>All parameters except {@code officeCode} are optional; pass {@code null} to omit a filter.
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
   * @return the matching claims; never {@code null} — implementations should return an empty result
   *     set rather than {@code null}
   */
  ClaimResultSet getClaims(
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
      String sort);

  /**
   * Retrieve submissions matching the given criteria.
   *
   * <p>Only {@code offices} is required; all other parameters are optional and may be {@code null}
   * to omit a filter.
   *
   * @param offices the list of office account numbers to filter by (required, must not be empty)
   * @param areaOfLaw the area of law to filter by, or {@code null} to return all areas
   * @param submissionPeriod the submission period to filter by (e.g. {@code "2024-01"}), or
   *     {@code null} to return all periods
   * @return the matching submissions; never {@code null} — implementations should return an empty
   *     list rather than {@code null}
   */
  List<SubmissionBase> getSubmissions(
      List<String> offices,
      AreaOfLaw areaOfLaw,
      String submissionPeriod);

}