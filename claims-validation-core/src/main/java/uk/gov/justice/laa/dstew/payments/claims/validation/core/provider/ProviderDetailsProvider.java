package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider;

import java.time.LocalDate;
import java.util.Optional;
import uk.gov.justice.laadata.providers.model.ProviderFirmOfficeContractAndScheduleDto;

/**
 * Transport-agnostic abstraction for resolving provider firm office contract and schedule details
 * required by validation logic.
 *
 * <p>Decouples validation rules from the data source, enabling the same validation library to be
 * used in two distinct deployment contexts:
 *
 * <ul>
 *   <li><b>External consumer (HTTP)</b> — wire in {@code HttpProviderDetailsProvider}, which
 *       delegates to {@code ProviderDetailsClient} over REST and adds positive/negative caching,
 *       coverage-window merging and in-flight deduplication.
 *   <li><b>Bring-your-own source</b> — a consumer that already integrates with the Provider Details
 *       Platform (e.g. with its own client and local cache) can register its own implementation,
 *       which causes the HTTP-backed default to be skipped entirely.
 * </ul>
 *
 * <p>Implementations must not leak transport concerns (e.g. HTTP status codes) through this
 * interface. Caching is an implementation detail and is not part of this contract.
 *
 * <h2>Usage example — HTTP (external service)</h2>
 *
 * <pre>{@code
 * @Bean
 * public ProviderDetailsProvider providerDetailsProvider(
 *     ProviderDetailsClient client, RetryRegistry retryRegistry) {
 *     return new HttpProviderDetailsProvider(client, retryRegistry);
 * }
 * }</pre>
 *
 * <h2>Usage example — custom source</h2>
 *
 * <pre>{@code
 * @Bean
 * public ProviderDetailsProvider providerDetailsProvider(MyProviderGateway gateway) {
 *     return new MyProviderDetailsProvider(gateway);
 * }
 * }</pre>
 */
public interface ProviderDetailsProvider {

  /**
   * Retrieves provider firm office contract and schedule information for the given office and
   * effective date.
   *
   * @param officeCode the office account number
   * @param effectiveDate the date used to filter provider schedules
   * @return the matching provider schedules, or an empty {@link Optional} when the provider has no
   *     schedules for the given parameters; implementations should throw on technical failure
   *     rather than returning {@code null}
   */
  Optional<ProviderFirmOfficeContractAndScheduleDto> getProviderFirmSchedules(
      String officeCode, LocalDate effectiveDate);
}
