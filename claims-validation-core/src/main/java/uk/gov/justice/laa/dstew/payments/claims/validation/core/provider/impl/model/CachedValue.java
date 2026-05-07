package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.model;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

/** Simple cached wrapper with TTL and negative marker. */
public final class CachedValue<T> {
  private final T value;
  private final Instant expiry;
  private final boolean negative;

  private CachedValue(T value, Instant expiry, boolean negative) {
    this.value = value;
    this.expiry = expiry;
    this.negative = negative;
  }

  public static <T> CachedValue<T> positive(T value, Duration ttl) {
    return new CachedValue<>(Objects.requireNonNull(value), Instant.now().plus(ttl), false);
  }

  public static <T> CachedValue<T> negative(Duration ttl) {
    return new CachedValue<>(null, Instant.now().plus(ttl), true);
  }

  public boolean isValid() {
    return Instant.now().isBefore(expiry);
  }

  public boolean isNegative() {
    return negative;
  }

  public T value() {
    return value;
  }

  public CachedValue<T> refresh(Duration ttl) {
    return new CachedValue<>(value, Instant.now().plus(ttl), negative);
  }
}
