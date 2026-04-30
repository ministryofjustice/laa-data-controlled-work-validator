package uk.gov.justice.laa.dstew.payments.claims.validation.core.client;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.fee.scheme.model.FeeCalculationRequest;
import uk.gov.justice.laa.fee.scheme.model.FeeCalculationResponse;
import uk.gov.justice.laa.fee.scheme.model.FeeDetailsResponse;

/**
 * Simple Caffeine-cached wrapper for FeeSchemeClient for testing/demo purposes.
 *
 * <p><b>Inflight Request Deduplication:</b><br>
 * This implementation uses Caffeine's {@code Cache.get(key, mappingFunction)} method, which
 * guarantees that if multiple threads request the same key concurrently, the mapping function
 * (i.e., the REST call) will only be invoked once for that key. All concurrent callers will receive
 * the same response object, and only one actual REST call will be made for that key until the cache
 * entry expires. This ensures efficient inflight deduplication and prevents duplicate network
 * requests for the same data.
 *
 * <p><b>Performance:</b><br>
 * The cache is precompiled and reused, providing high performance and low latency for repeated
 * requests.
 *
 * @see <a href="https://github.com/ben-manes/caffeine">Caffeine Cache Documentation</a>
 */
public class CachedFeeSchemeClient implements FeeSchemeClient {
  private final FeeSchemeClient delegate;
  private final Cache<String, ResponseEntity<FeeDetailsResponse>> feeDetailsCache;
  private final Cache<String, ResponseEntity<FeeCalculationResponse>> feeCalculationCache;

  /**
   * Constructs a CachedFeeSchemeClient that wraps the given delegate and caches responses for a
   * configurable duration.
   *
   * @param delegate the underlying FeeSchemeClient to delegate uncached calls to
   * @param cacheMinutes the number of minutes to cache each response
   */
  public CachedFeeSchemeClient(FeeSchemeClient delegate, long cacheMinutes) {
    this.delegate = delegate;
    this.feeDetailsCache =
        Caffeine.newBuilder().expireAfterWrite(cacheMinutes, TimeUnit.MINUTES).build();
    this.feeCalculationCache =
        Caffeine.newBuilder().expireAfterWrite(cacheMinutes, TimeUnit.MINUTES).build();
  }

  /**
   * Returns fee details for the given fee code, using a Caffeine-backed cache to avoid repeated
   * network calls for the same code within the cache duration.
   *
   * @param feeCode the fee code to look up
   * @return the response entity containing fee details
   */
  @Override
  public ResponseEntity<FeeDetailsResponse> getFeeDetails(String feeCode) {
    return feeDetailsCache.get(feeCode, delegate::getFeeDetails);
  }

  /**
   * Calculates the fee for the given request, using a Caffeine-backed cache to avoid repeated
   * network calls for the same request within the cache duration.
   *
   * @param request the fee calculation request
   * @return the response entity containing the calculation result
   */
  @Override
  public ResponseEntity<FeeCalculationResponse> calculateFee(FeeCalculationRequest request) {
    String key = feeCalculationKey(request);
    return feeCalculationCache.get(key, k -> delegate.calculateFee(request));
  }

  /**
   * Generates a cache key for fee calculation requests. In this demo, it uses the request's
   * toString, but in production, a robust hash or serialization should be used to ensure uniqueness
   * and avoid collisions.
   *
   * @param req the fee calculation request
   * @return a string key representing the request
   */
  private String feeCalculationKey(FeeCalculationRequest req) {
    // Simple key: use all fields (for demo; in production, use a robust hash or serialization)
    return Objects.toString(req, "null");
  }
}
