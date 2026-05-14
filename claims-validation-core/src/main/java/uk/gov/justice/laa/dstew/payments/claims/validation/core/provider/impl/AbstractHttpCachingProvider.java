package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl;

import io.github.resilience4j.reactor.retry.RetryOperator;
import io.github.resilience4j.retry.RetryRegistry;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
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
 *       via {@link #fetchDeduped(String, Supplier)}, which uses {@link Sinks.One} and
 *       {@link java.util.concurrent.ConcurrentHashMap#putIfAbsent} to guarantee that only one
 *       upstream execution occurs even under high concurrency
 *   <li>Retry wiring: helper to apply a {@link io.github.resilience4j.retry.Retry} from a
 *       {@link RetryRegistry}
 * </ul>
 *
 * <p>Subclasses should use {@link #cachePositive(String, Object)}, {@link #cacheNegative(String)},
 * {@link #getPositiveCached(String)}, {@link #getNegativeCached(String)} and
 * {@link #fetchDeduped(String, Supplier)} to interact with the caches and deduplicate remote calls.
 */
@Slf4j
public abstract class AbstractHttpCachingProvider<V> {

  protected final RetryRegistry retryRegistry;
  protected final Duration positiveTtl;
  protected final Duration negativeTtl;

  // caches keyed by string cacheKey
  protected final Map<String, CachedValue<V>> positiveCache = new ConcurrentHashMap<>();
  protected final Map<String, CachedValue<V>> negativeCache = new ConcurrentHashMap<>();
  protected final Map<String, Sinks.One<V>> inFlightCalls = new ConcurrentHashMap<>();

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
   * Deduplicates concurrent remote calls for the same {@code inFlightKey}.
   *
   * <p>Uses {@link Sinks.One} + {@link ConcurrentHashMap#putIfAbsent} to guarantee that only
   * one thread executes the {@code upstreamSupplier} per key at a time. All concurrent callers
   * share the same {@link Sinks.One} and receive the result when the upstream completes.
   * The in-flight entry is removed (via {@code doFinally}) once the upstream terminates, so
   * subsequent cache-miss calls after TTL expiry correctly trigger a fresh fetch.
   *
   * <p><strong>ADR — why Sinks.One rather than FutureTask or synchronized:</strong>
   *
   * <p>Three alternatives were considered:
   * <ol>
   *   <li><em>Hand-rolled {@code synchronized} / {@code wait}/{@code notifyAll}</em> — correct but
   *       verbose, easy to get wrong, and does not propagate exceptions cleanly.
   *   <li><em>{@link java.util.concurrent.FutureTask}</em> — idiomatic Java, correct by JDK
   *       contract, and {@code FutureTask.get()} uses {@code LockSupport.park} (not
   *       {@code synchronized}), so it does <em>not</em> pin carrier threads under Project Loom
   *       virtual threads. It would be a valid and simpler choice for a purely Spring MVC /
   *       servlet-thread runtime.
   *   <li><em>{@link Sinks.One} (current choice)</em> — non-blocking inside the reactive pipeline.
   *       The sink is a broadcast channel: the winning thread emits the result, all other
   *       subscribers receive it without blocking any thread. This is the only option that remains
   *       correct if the code is ever consumed from a Netty/WebFlux event loop or another context
   *       where blocking a thread is forbidden.
   * </ol>
   *
   * <p>The public API of each provider is synchronous ({@code Optional<T>}) and the current
   * runtime is Spring MVC on servlet/virtual threads, so {@code FutureTask} would work today.
   * {@code Sinks.One} was chosen because it is safe for all threading models — including
   * {@code spring.threads.virtual.enabled=true} and a future migration to WebFlux — without
   * requiring any code change in this base class. The trade-off is that maintainers must be
   * familiar with Reactor semantics; see the class-level Javadoc and test suite for worked
   * examples.
   *
   * @param inFlightKey   the key scoped to the exact remote call parameters
   * @param upstreamSupplier a supplier producing the upstream {@link Mono} — only invoked by
   *                         the thread that wins the {@code putIfAbsent} race
   * @return a {@link Mono} that emits the result when available, shared across all concurrent
   *         callers for the same key
   */
  protected Mono<V> fetchDeduped(String inFlightKey, Supplier<Mono<V>> upstreamSupplier) {
    Sinks.One<V> newSink = Sinks.one();
    Sinks.One<V> existing = inFlightCalls.putIfAbsent(inFlightKey, newSink);
    Sinks.One<V> sink = existing != null ? existing : newSink;

    if (existing == null) {
      // This thread won the race — execute the upstream and emit into the sink.
      upstreamSupplier.get()
          .doFinally(sig -> inFlightCalls.remove(inFlightKey, sink))
          .subscribe(
              sink::tryEmitValue,
              sink::tryEmitError,
              sink::tryEmitEmpty);
    }

    return sink.asMono();
  }

  /**
   * Convenience wrapper around {@link #fetchDeduped} that applies the standard negative-cache
   * side-effect and retry operator, reducing boilerplate in subclasses.
   *
   * <p>The {@code fetchSupplier} is responsible only for making the remote call and writing to the
   * positive cache (via {@link #cachePositive} or a custom strategy). This method handles:
   * <ul>
   *   <li>In-flight deduplication (delegated to {@link #fetchDeduped})
   *   <li>Applying the named retry policy via {@link #retryOperator} — wraps only the fetch
   *   <li>Writing a negative-cache entry when the upstream is still empty after all retries
   * </ul>
   *
   * <p><strong>Operator ordering is significant:</strong> retry is applied <em>before</em>
   * {@code switchIfEmpty} so that a 404/empty response causes the fetch to be retried as
   * configured before the negative-cache entry is written. Placing {@code switchIfEmpty} before
   * retry would cause the negative cache to be populated on every retry attempt, not just the
   * final one.</p>
   *
   * @param inFlightKey   dedupe key, also used as the negative-cache key on empty
   * @param retryName     name of the retry policy in the {@link RetryRegistry}
   * @param fetchSupplier supplier that performs the remote call and populates the positive cache;
   *                      should return empty when the resource does not exist (not throw)
   * @return deduplicated, retried {@link Mono}
   */
  protected Mono<V> fetchDedupedWithCaching(
      String inFlightKey, String retryName, Supplier<Mono<V>> fetchSupplier) {
    return fetchDeduped(inFlightKey, () ->
        Mono.defer(fetchSupplier)
            .transformDeferred(retryOperator(retryName))
            .switchIfEmpty(Mono.defer(() -> {
              cacheNegative(inFlightKey);
              return Mono.empty();
            })));
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
