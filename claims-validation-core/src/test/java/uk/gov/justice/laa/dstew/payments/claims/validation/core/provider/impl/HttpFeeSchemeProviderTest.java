package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

import io.github.resilience4j.retry.RetryRegistry;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.FeeSchemeClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils;
import uk.gov.justice.laa.fee.scheme.model.FeeDetailsResponseV2;

@ExtendWith(MockitoExtension.class)
@DisplayName("HttpFeeSchemeProvider - fee details caching")
class HttpFeeSchemeProviderTest {

  @Mock FeeSchemeClient feeSchemeClient;

  @Test
  @DisplayName("Cache miss then cache hit for fee details")
  void cacheMissThenHit() {
    RetryRegistry registry = RetryRegistry.ofDefaults();
    HttpFeeSchemeProvider provider = new HttpFeeSchemeProvider(feeSchemeClient, registry);

    FeeDetailsResponseV2 dto = new FeeDetailsResponseV2().categoryOfLawCodes(List.of("CAT"));
    Mockito.when(feeSchemeClient.getFeeDetails("F1")).thenReturn(Mono.just(ResponseEntity.ok(dto)));

    FeeDetailsResponseV2 first = provider.fetchFeeDetails("F1").block();
    assertNotNull(first);

    FeeDetailsResponseV2 second = provider.fetchFeeDetails("F1").block();
    assertNotNull(second);

    Mockito.verify(feeSchemeClient, times(1)).getFeeDetails("F1");
  }

  @Test
  @DisplayName("404 responses are cached as negative entries")
  void negativeResponseIsCached() {
    RetryRegistry registry = RetryRegistry.ofDefaults();
    HttpFeeSchemeProvider provider = new HttpFeeSchemeProvider(feeSchemeClient, registry);

    Mockito.when(feeSchemeClient.getFeeDetails("NF")).thenReturn(Mono.just(ResponseEntity.status(404).build()));

    assertTrue(provider.fetchFeeDetails("NF").blockOptional().isEmpty());
    assertTrue(provider.fetchFeeDetails("NF").blockOptional().isEmpty());

    Mockito.verify(feeSchemeClient, times(1)).getFeeDetails("NF");
  }

  @Test
  @DisplayName("Concurrent fee detail requests are deduplicated")
  void concurrentRequestsAreDeduped_inFlight() throws Exception {
    RetryRegistry registry = RetryRegistry.ofDefaults();
    HttpFeeSchemeProvider provider = new HttpFeeSchemeProvider(feeSchemeClient, registry);

    FeeDetailsResponseV2 dto = new FeeDetailsResponseV2().categoryOfLawCodes(List.of("CAT"));

    CountDownLatch started = new CountDownLatch(1);
    CountDownLatch t2Ready = new CountDownLatch(1);
    CountDownLatch release = new CountDownLatch(1);

    Mockito.when(feeSchemeClient.getFeeDetails("F2"))
        .thenAnswer(ignored -> {
          started.countDown();
          // wait until t2 has subscribed to the shared sink before releasing
          try {
            boolean ok = t2Ready.await(5, TimeUnit.SECONDS);
            if (!ok) throw new RuntimeException("t2Ready latch timeout");
            boolean rel = release.await(5, TimeUnit.SECONDS);
            if (!rel) throw new RuntimeException("release latch timeout");
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          return Mono.just(ResponseEntity.ok(dto));
        });

    // start two threads that subscribe concurrently
    Thread t1 = new Thread(() -> provider.fetchFeeDetails("F2").block());
    Thread t2 = new Thread(() -> provider.fetchFeeDetails("F2").block());

    t1.start();
    // wait until the first invocation has started (sink is in the map)
    assertTrue(started.await(2, TimeUnit.SECONDS));

    t2.start();
    // give t2 time to reach sink.asMono()
    Thread.sleep(100);
    t2Ready.countDown();

    // allow the remote to proceed
    release.countDown();

    t1.join(5000);
    t2.join(5000);

    // ensure both threads completed
    assertFalse(t1.isAlive(), "t1 did not finish");
    assertFalse(t2.isAlive(), "t2 did not finish");

    Mockito.verify(feeSchemeClient, times(1)).getFeeDetails("F2");
  }

  @Test
  @DisplayName("Positive cache expires and triggers refetch")
  void positiveCacheExpiresAndRefetches() {
    Instant base = Instant.parse("2025-01-01T00:00:00Z");
    DateUtils.setClock(Clock.fixed(base, ZoneOffset.UTC));

    RetryRegistry registry = RetryRegistry.ofDefaults();
    HttpFeeSchemeProvider provider = new HttpFeeSchemeProvider(feeSchemeClient, registry);

    FeeDetailsResponseV2 dto = new FeeDetailsResponseV2().categoryOfLawCodes(List.of("CAT"));
    Mockito.when(feeSchemeClient.getFeeDetails("FP1")).thenReturn(Mono.just(ResponseEntity.ok(dto)));

    provider.fetchFeeDetails("FP1").blockOptional();
    Mockito.verify(feeSchemeClient, times(1)).getFeeDetails("FP1");

    // advance beyond positive TTL (10 minutes)
    DateUtils.setClock(Clock.fixed(base.plus(Duration.ofMinutes(11)), ZoneOffset.UTC));

    FeeDetailsResponseV2 dto2 = new FeeDetailsResponseV2().categoryOfLawCodes(List.of("CAT2"));
    Mockito.when(feeSchemeClient.getFeeDetails("FP1")).thenReturn(Mono.just(ResponseEntity.ok(dto2)));

    provider.fetchFeeDetails("FP1").blockOptional();
    Mockito.verify(feeSchemeClient, times(2)).getFeeDetails("FP1");

    DateUtils.resetClock();
  }

  @Test
  @DisplayName("Negative cache expires and triggers refetch")
  void negativeCacheExpiresAndRefetches() {
    Instant base = Instant.parse("2025-02-01T00:00:00Z");
    DateUtils.setClock(Clock.fixed(base, ZoneOffset.UTC));

    RetryRegistry registry = RetryRegistry.ofDefaults();
    HttpFeeSchemeProvider provider = new HttpFeeSchemeProvider(feeSchemeClient, registry);

    Mockito.when(feeSchemeClient.getFeeDetails("NF1")).thenReturn(Mono.just(ResponseEntity.status(404).build()));

    assertTrue(provider.fetchFeeDetails("NF1").blockOptional().isEmpty());
    Mockito.verify(feeSchemeClient, times(1)).getFeeDetails("NF1");

    // advance beyond negative TTL (5 minutes)
    DateUtils.setClock(Clock.fixed(base.plus(Duration.ofMinutes(6)), ZoneOffset.UTC));

    FeeDetailsResponseV2 dto = new FeeDetailsResponseV2().categoryOfLawCodes(List.of("NEW"));
    Mockito.when(feeSchemeClient.getFeeDetails("NF1")).thenReturn(Mono.just(ResponseEntity.ok(dto)));

    assertTrue(provider.fetchFeeDetails("NF1").blockOptional().isPresent());
    Mockito.verify(feeSchemeClient, times(2)).getFeeDetails("NF1");

    DateUtils.resetClock();
  }
}
