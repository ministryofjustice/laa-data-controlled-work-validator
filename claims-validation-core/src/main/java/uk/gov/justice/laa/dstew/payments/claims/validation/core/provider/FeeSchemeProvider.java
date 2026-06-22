package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider;

import java.util.Optional;
import uk.gov.justice.laa.fee.scheme.model.FeeDetailsResponseV2;

/**
 * Transport-agnostic abstraction for resolving fee-scheme details required by validation logic.
 *
 * <p>Decouples validation rules from the data source, enabling the same validation library to be
 * used in two distinct deployment contexts:
 *
 * <ul>
 *   <li><b>External consumer (HTTP)</b> — wire in {@code HttpFeeSchemeProvider}, which delegates to
 *       {@code FeeSchemeClient} over REST and adds positive/negative caching and in-flight
 *       deduplication.
 *   <li><b>Bring-your-own source</b> — a consumer that already integrates with the Fee Scheme
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
 * public FeeSchemeProvider feeSchemeProvider(FeeSchemeClient client, RetryRegistry retryRegistry) {
 *     return new HttpFeeSchemeProvider(client, retryRegistry);
 * }
 * }</pre>
 *
 * <h2>Usage example — custom source</h2>
 *
 * <pre>{@code
 * @Bean
 * public FeeSchemeProvider feeSchemeProvider(MyFeeSchemeGateway gateway) {
 *     return new MyFeeSchemeProvider(gateway);
 * }
 * }</pre>
 */
public interface FeeSchemeProvider {

  /**
   * Returns the fee details for the given fee code.
   *
   * @param feeCode the fee code to look up
   * @return the fee details, or an empty {@link Optional} when the fee code is not found;
   *     implementations should throw on technical failure rather than returning {@code null}
   */
  Optional<FeeDetailsResponseV2> getFeeDetails(String feeCode);
}
