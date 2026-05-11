package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.model;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils;

/**
 * Simple cached wrapper with a time-to-live (TTL) and a flag to indicate a negative cache entry.
 *
 * <p>This class is used by HTTP caching providers to store positive (successful) and negative
 * (not-found / empty) responses along with an expiry instant. The time source for expiry is
 * provided by {@link DateUtils#nowInstant()},
 * which allows tests to control the clock via {@link
 * DateUtils#setClock(java.time.Clock)}.
 *
 * @param <T> the type of value being cached
 */
public final class CachedValue<T> {
  private final T value;
  private final Instant expiry;
  private final boolean negative;

  private CachedValue(T value, Instant expiry, boolean negative) {
    this.value = value;
    this.expiry = expiry;
    this.negative = negative;
  }

  /**
   * Create a positive cached entry containing a non-null value.
   *
   * @param value the value to cache (must not be null)
   * @param ttl the time-to-live duration after which the entry is considered expired
   * @param <T> the type of the cached value
   * @return a new positive {@link CachedValue}
   */
  public static <T> CachedValue<T> positive(T value, Duration ttl) {
    return new CachedValue<>(Objects.requireNonNull(value),
            DateUtils.nowInstant().plus(ttl), false);
  }

  /**
   * Create a negative cached entry. Negative entries represent absent or not-found responses and
   * are used to avoid repeated remote calls for keys known to have no value.
   *
   * @param ttl time-to-live for the negative entry
   * @param <T> ignored type parameter for API symmetry with {@link #positive(Object, Duration)}
   * @return a new negative {@link CachedValue}
   */
  public static <T> CachedValue<T> negative(Duration ttl) {
    return new CachedValue<>(null, DateUtils.nowInstant().plus(ttl), true);
  }

  /**
   * Returns {@code true} when the cached entry has not yet expired.
   *
   * @return {@code true} if the current time is before the expiry instant
   */
  public boolean isValid() {
    return DateUtils.nowInstant().isBefore(expiry);
  }

  /**
   * Returns {@code true} if this entry represents a negative cache (i.e. no value).
   *
   * @return {@code true} for negative cache entries, {@code false} for positive entries
   */
  public boolean isNegative() {
    return negative;
  }

  /**
   * Returns the cached value. For negative entries this will be {@code null}.
   *
   * @return the cached value or {@code null} for negative entries
   */
  public T value() {
    return value;
  }

  /**
   * Returns a refreshed cached entry with a new expiry calculated from the provided TTL.
   *
   * <p>For negative entries the same instance semantics are preserved (refresh will return a
   * negative entry with the same marker but a refreshed expiry).
   *
   * @param ttl the TTL to apply to the refreshed entry
   * @return a new {@link CachedValue} with updated expiry
   */
  public CachedValue<T> refresh(Duration ttl) {
    return new CachedValue<>(value, DateUtils.nowInstant().plus(ttl), negative);
  }
}
