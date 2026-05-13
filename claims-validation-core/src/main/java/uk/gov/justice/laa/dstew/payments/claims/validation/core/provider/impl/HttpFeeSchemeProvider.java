package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl;

import io.github.resilience4j.retry.RetryRegistry;
import java.time.Duration;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.FeeSchemeClient;
import uk.gov.justice.laa.fee.scheme.model.FeeDetailsResponseV2;

/**
 * Caching provider for Fee Scheme API. Uses the same positive/negative cache + in-flight dedupe
 * pattern established in {@code HttpProviderDetailsProvider} but specialised for fee details.
 */
@Slf4j
public class HttpFeeSchemeProvider extends AbstractHttpCachingProvider<FeeDetailsResponseV2> {

  private static final Duration POSITIVE_CACHE_TTL = Duration.ofMinutes(10);
  private static final Duration NEGATIVE_CACHE_TTL = Duration.ofSeconds(10);
  private static final String RETRY_NAME = "feeSchemeRetry";

  private final FeeSchemeClient feeSchemeClient;

  public HttpFeeSchemeProvider(FeeSchemeClient feeSchemeClient, RetryRegistry retryRegistry) {
    super(retryRegistry, POSITIVE_CACHE_TTL, NEGATIVE_CACHE_TTL);
    this.feeSchemeClient = Objects.requireNonNull(feeSchemeClient);
  }

  /**
   * Returns a cached FeeDetailsResponseV2 for the given fee code, or fetches and caches it.
   *
   * <p>Not-found responses are cached as negative entries for a short TTL.
   */
  public Mono<FeeDetailsResponseV2> getFeeDetails(final String feeCode) {

    // negative short-circuit
    if (getNegativeCached(feeCode).isPresent()) {
      log.debug("Fee negative cache hit for {}", feeCode);
      return Mono.empty();
    }

    // positive cache
    var pos = getPositiveCached(feeCode);
    if (pos.isPresent()) {
      log.debug("Fee positive cache hit for {}", feeCode);
      // refresh TTL
      positiveCache.put(feeCode, pos.get().refresh(positiveTtl));
      return Mono.just(pos.get().value());
    }

    // fetch and dedupe in-flight
    return inFlightCalls
        .computeIfAbsent(
                feeCode,
            k ->
                Mono.fromCallable(() -> feeSchemeClient.getFeeDetails(feeCode))
                    .subscribeOn(Schedulers.boundedElastic())
                    .flatMap(resp -> Mono.justOrEmpty(mapResponse(resp)))
                    .map(body -> {
                      cachePositive(feeCode, body);
                      return body;
                    })
                    .switchIfEmpty(Mono.defer(() -> {
                      cacheNegative(feeCode);
                      return Mono.empty();
                    }))
                    .transformDeferred(retryOperator(RETRY_NAME))
                    .doFinally(sig -> inFlightCalls.remove(feeCode))
                    .cache());
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
