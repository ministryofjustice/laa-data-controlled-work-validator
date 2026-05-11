package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.model;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils;
import uk.gov.justice.laadata.providers.model.ProviderFirmOfficeContractAndScheduleDto;

/**
 * Holder for a cached Provider Details API (PDA) response along with coverage windows and expiry
 * metadata.
 *
 * <p>The record stores the DTO value, the merged coverage windows computed from schedules, the
 * expiry instant and a flag indicating whether the entry is negative (no schedules). The time
 * source for expiry is provided by {@link DateUtils#nowInstant()},
 * which makes the class test-friendly by allowing tests to control the clock.
 */
public record ProviderDetailsCachedSchedules(
    ProviderFirmOfficeContractAndScheduleDto value,
    List<ProviderDetailsCoverageWindow> windows,
    Instant expiresAt,
    boolean negative) {

  /**
   * Creates a positive cache entry with an expiry derived from the supplied TTL.
   *
   * @param value the provider DTO to cache
   * @param windows merged coverage windows derived from the DTO schedules
   * @param timeToLive the TTL to apply to the cached entry
   * @return a positive {@link ProviderDetailsCachedSchedules} with an expiry in the future
   */
  public static ProviderDetailsCachedSchedules positive(
      ProviderFirmOfficeContractAndScheduleDto value,
      List<ProviderDetailsCoverageWindow> windows,
      Duration timeToLive) {
    return new ProviderDetailsCachedSchedules(
        value, windows,
            DateUtils.nowInstant().plus(timeToLive),
        false);
  }

  /**
   * Creates a negative cache entry (no schedules) with the given TTL.
   *
   * <p>Negative entries are used to short-circuit repeated remote calls when a provider has no
   * schedules for the given office/date.
   *
   * @param timeToLive the TTL for the negative entry
   * @return a negative {@link ProviderDetailsCachedSchedules}
   */
  public static ProviderDetailsCachedSchedules negative(Duration timeToLive) {
    return new ProviderDetailsCachedSchedules(
        null, List.of(),
        DateUtils.nowInstant().plus(timeToLive),
        true);
  }

  /**
   * Returns {@code true} when this cache entry represents a negative (not-found / empty)
   * response.
   *
   * @return {@code true} for negative cache entries
   */
  public boolean isNegative() {
    return negative;
  }

  /**
   * Returns {@code true} if the cache entry has not expired.
   *
   * @return {@code true} when the current time is before the stored expiry
   */
  public boolean isValid() {
    return expiresAt == null || DateUtils.nowInstant().isBefore(expiresAt);
  }

  /**
   * Returns {@code true} if the supplied effective date falls within any cached coverage
   * window.
   *
   * @param effectiveDate the date to check against coverage windows
   * @return {@code true} when at least one window covers the date
   */
  public boolean covers(LocalDate effectiveDate) {
    return windows.stream()
        .anyMatch(
            window ->
                !effectiveDate.isBefore(window.start()) && !effectiveDate.isAfter(window.end()));
  }

  /**
   * Refreshes the expiry for positive entries; negative entries remain unchanged.
   *
   * @param timeToLive the TTL to apply for the refreshed expiry
   * @return a refreshed {@link ProviderDetailsCachedSchedules} (or the same instance for
   *     negatives)
   */
  public ProviderDetailsCachedSchedules refresh(Duration timeToLive) {
    return negative
        ? this
        : new ProviderDetailsCachedSchedules(value, windows,
            DateUtils.nowInstant().plus(timeToLive), false);
  }
}
