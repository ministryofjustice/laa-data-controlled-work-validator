package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl;

import io.github.resilience4j.retry.RetryRegistry;
import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.FeeSchemeClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.FeeSchemeProvider;
import uk.gov.justice.laa.fee.scheme.model.FeeDetailsResponseV2;

/**
 * Caching provider for Fee Scheme API. Uses the same positive/negative cache + in-flight dedupe
 * pattern as {@link HttpProviderDetailsProvider}
 * via {@link AbstractHttpCachingProvider#fetchDeduped}.
 */
@Slf4j
public class HttpFeeSchemeProvider extends AbstractHttpCachingProvider<FeeDetailsResponseV2>
    implements FeeSchemeProvider {

  private static final Duration POSITIVE_CACHE_TTL = Duration.ofMinutes(10);
  private static final Duration NEGATIVE_CACHE_TTL = Duration.ofSeconds(10);
  private static final String RETRY_NAME = "feeSchemeRetry";

  private final FeeSchemeClient feeSchemeClient;

  public HttpFeeSchemeProvider(FeeSchemeClient feeSchemeClient, RetryRegistry retryRegistry) {
    super(retryRegistry, POSITIVE_CACHE_TTL, NEGATIVE_CACHE_TTL);
    this.feeSchemeClient = Objects.requireNonNull(feeSchemeClient);
  }

  /**
   * Returns the fee details for the given fee code, or empty if not found.
   * Uses positive/negative caching and in-flight deduplication internally.
   *
   * <p>Returns an empty {@link Optional} when the fee code is not found (404).
   * Throws on technical API failure.
   */
  @Override
  public Optional<FeeDetailsResponseV2> getFeeDetails(final String feeCode) {
    return fetchFeeDetails(feeCode).blockOptional();
  }

  /** Reactive implementation — package-private for testing. */
  Mono<FeeDetailsResponseV2> fetchFeeDetails(final String feeCode) {

    if (getNegativeCached(feeCode).isPresent()) {
      log.debug("Fee negative cache hit for {}", feeCode);
      return Mono.empty();
    }

    var pos = getPositiveCached(feeCode);
    if (pos.isPresent()) {
      log.debug("Fee positive cache hit for {}", feeCode);
      positiveCache.put(feeCode, pos.get().refresh(positiveTtl));
      return Mono.just(pos.get().value());
    }

    return fetchDedupedWithCaching(feeCode, RETRY_NAME, () ->
        feeSchemeClient.getFeeDetails(feeCode)
            .flatMap(resp -> Mono.justOrEmpty(mapResponse(resp)))
            .map(body -> {
              cachePositive(feeCode, body);
              return body;
            }));
  }

  private static FeeDetailsResponseV2 mapResponse(ResponseEntity<FeeDetailsResponseV2> resp) {
    if (resp == null) {
      return null;
    }
    if (resp.getStatusCode().is2xxSuccessful()) {
      return resp.getBody();
    }
    if (resp.getStatusCode().value() == 404) {
      return null;
    }
    throw new IllegalStateException("Unexpected response " + resp.getStatusCode());
  }
}
