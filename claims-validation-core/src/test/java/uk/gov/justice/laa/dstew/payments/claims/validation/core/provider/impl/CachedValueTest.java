package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.model.CachedValue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils;

@DisplayName("CachedValue - expiry and refresh behaviour")
class CachedValueTest {

  @AfterEach
  void afterEach() {
    DateUtils.resetClock();
  }

  @Test
  @DisplayName("positive value is valid until ttl expires and refresh extends expiry")
  void positiveValueExpiryAndRefresh() {
    Instant base = Instant.parse("2025-01-01T00:00:00Z");
    DateUtils.setClock(Clock.fixed(base, ZoneOffset.UTC));

    CachedValue<String> cv = CachedValue.positive("x", Duration.ofMinutes(10));
    assertThat(cv.isValid()).isTrue();

    // advance beyond ttl
    DateUtils.setClock(Clock.fixed(base.plus(Duration.ofMinutes(11)), ZoneOffset.UTC));
    assertThat(cv.isValid()).isFalse();

    // refresh should update expiry relative to new now
    CachedValue<String> refreshed = cv.refresh(Duration.ofMinutes(5));
    assertThat(refreshed.isValid()).isTrue();
  }

  @Test
  @DisplayName("negative cache is marked negative and expires")
  void negativeCacheExpires() {
    Instant base = Instant.parse("2025-01-01T00:00:00Z");
    DateUtils.setClock(Clock.fixed(base, ZoneOffset.UTC));

    CachedValue<Object> neg = CachedValue.negative(Duration.ofMinutes(5));
    assertThat(neg.isNegative()).isTrue();
    assertThat(neg.isValid()).isTrue();

    DateUtils.setClock(Clock.fixed(base.plus(Duration.ofMinutes(6)), ZoneOffset.UTC));
    assertThat(neg.isValid()).isFalse();
  }
}
