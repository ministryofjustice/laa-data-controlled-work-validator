package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl;

import io.github.resilience4j.reactor.retry.RetryOperator;
import io.github.resilience4j.retry.RetryRegistry;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.model.CachedValue;

/**
 * Lightweight reusable caching / in-flight dedupe base used by HTTP-backed providers.
 *
 * <p>This base class centralises common behaviour used by HTTP-backed provider classes in the
 * project:
 *
 * <ul>
 *   <li>Positive cache: store successful responses for a configurable TTL
 *   <li>Negative cache: short-lived marker for not-found responses to avoid hammering the
 *       remote API
 *   <li>In-flight dedupe: concurrent requests for the same cache key share a single remote call
 *   <li>Retry wiring: helper to apply a {@link io.github.resilience4j.retry.Retry} from a
 *       {@link RetryRegistry}
 * </ul>
 *
 * <p>Subclasses should use {@link #cachePositive(String, Object)}, {@link #cacheNegative(String)},
 * {@link #getPositiveCached(String)} and {@link #getNegativeCached(String)} to interact with the
 * caches and may use {@link #inFlightCalls} to implement compute-if-absent fetch patterns with
 * {@link reactor.core.publisher.Mono#cache()} for deduplication.
 */
@Slf4j
public abstract class AbstractHttpCachingProvider<V> {

  protected final RetryRegistry retryRegistry;
  protected final Duration positiveTtl;
  protected final Duration negativeTtl;

  // caches keyed by string cacheKey
  protected final Map<String, CachedValue<V>> positiveCache = new ConcurrentHashMap<>();
  protected final Map<String, CachedValue<V>> negativeCache = new ConcurrentHashMap<>();
  protected final Map<String, Mono<V>> inFlightCalls = new ConcurrentHashMap<>();

  protected AbstractHttpCachingProvider(
          RetryRegistry retryRegistry, Duration positiveTtl, Duration negativeTtl) {
    this.retryRegistry = Objects.requireNonNull(retryRegistry);
    this.positiveTtl = Objects.requireNonNull(positiveTtl);
    this.negativeTtl = Objects.requireNonNull(negativeTtl);
  }

  /**
   * Store a successful response in the positive cache for the configured positive TTL.
   *
   * @param cacheKey the string key used to identify the cached entry
   * @param value the value to cache (must not be null)
   */
  protected void cachePositive(String cacheKey, V value) {
    positiveCache.put(cacheKey, CachedValue.positive(value, positiveTtl));
  }

  /**
   * Mark the given key as negative (not found) for the configured negative TTL. Subclasses
   * typically invoke this when the remote API returns an explicit "not found" result.
   *
   * @param cacheKey the string key to mark as negative
   */
  protected void cacheNegative(String cacheKey) {
    negativeCache.put(cacheKey, CachedValue.negative(negativeTtl));
  }

  /**
   * Retrieve the positive cached entry for {@code cacheKey} if present and not expired.
   *
   * <p>Returns an {@link Optional} containing the {@link CachedValue} when a valid entry is
   * present. If the entry has expired it is removed and {@link Optional#empty()} is returned.
   *
   * @param cacheKey string cache key
   * @return optional cached value wrapper
   */
  protected Optional<CachedValue<V>> getPositiveCached(String cacheKey) {
    CachedValue<V> cached = positiveCache.get(cacheKey);
    if (cached == null) {
      return Optional.empty();
    }
    if (!cached.isValid()) {
      log.debug("Cache expired for key {}", cacheKey);
      positiveCache.remove(cacheKey);
      return Optional.empty();
    }
    return Optional.of(cached);
  }

  /**
   * Retrieve the negative cache marker for {@code cacheKey} if present and not expired.
   *
   * <p>A negative cache entry represents an explicit remote "not found" result and is used to
   * short-circuit subsequent requests for a short duration.
   *
   * @param cacheKey string cache key
   * @return optional negative cache wrapper
   */
  protected Optional<CachedValue<V>> getNegativeCached(String cacheKey) {
    CachedValue<V> cached = negativeCache.get(cacheKey);
    if (cached == null) {
      return Optional.empty();
    }
    if (!cached.isValid()) {
      log.debug("Negative cache expired for key {}", cacheKey);
      negativeCache.remove(cacheKey);
      return Optional.empty();
    }
    return Optional.of(cached);
  }

  /**
   * Create a typed {@link RetryOperator} for the configured retry name. Subclasses should pass the
   * name of the retry policy (as configured in the {@link RetryRegistry}) to apply retry/backoff
   * behaviour to the remote call flux/mono.
   *
   * @param retryName name of the retry configuration
   * @param <T> reactive element type (inferred by caller)
   * @return a configured {@link RetryOperator}
   */
  protected <T> RetryOperator<T> retryOperator(String retryName) {
    return RetryOperator.of(retryRegistry.retry(retryName));
  }

  /**
   * Remove both positive and negative cache entries for the provided key.
   *
   * <p>Useful for tests or administrative operations when an explicit cache reset is required.
   *
   * @param cacheKey key to evict
   */
  public void evict(String cacheKey) {
    positiveCache.remove(cacheKey);
    negativeCache.remove(cacheKey);
  }

  /**
   * Clear all caches and remove any in-flight-call trackers. Use with caution in production;
   * primarily intended for test and diagnostic scenarios.
   */
  public void clear() {
    positiveCache.clear();
    negativeCache.clear();
    inFlightCalls.clear();
  }
}
