package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

import io.github.resilience4j.retry.RetryRegistry;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.FeeSchemeClient;
import uk.gov.justice.laa.fee.scheme.model.FeeDetailsResponseV1;

@ExtendWith(MockitoExtension.class)
@DisplayName("HttpFeeSchemeProvider - fee details caching")
class HttpFeeSchemeProviderTest {

  @Mock FeeSchemeClient feeSchemeClient;

  @Test
  @DisplayName("Cache miss then cache hit for fee details")
  void cacheMissThenHit() {
    RetryRegistry registry = RetryRegistry.ofDefaults();
    HttpFeeSchemeProvider provider = new HttpFeeSchemeProvider(feeSchemeClient, registry);

    FeeDetailsResponseV1 dto = new FeeDetailsResponseV1("CAT");
    Mockito.when(feeSchemeClient.getFeeDetails("F1")).thenReturn(ResponseEntity.ok(dto));

    FeeDetailsResponseV1 first = provider.getFeeDetails("F1").block();
    assertNotNull(first);

    FeeDetailsResponseV1 second = provider.getFeeDetails("F1").block();
    assertNotNull(second);

    Mockito.verify(feeSchemeClient, times(1)).getFeeDetails("F1");
  }

  @Test
  @DisplayName("404 responses are cached as negative entries")
  void negativeResponseIsCached() {
    RetryRegistry registry = RetryRegistry.ofDefaults();
    HttpFeeSchemeProvider provider = new HttpFeeSchemeProvider(feeSchemeClient, registry);

    Mockito.when(feeSchemeClient.getFeeDetails("NF")).thenReturn(ResponseEntity.status(404).build());

    assertTrue(provider.getFeeDetails("NF").blockOptional().isEmpty());
    assertTrue(provider.getFeeDetails("NF").blockOptional().isEmpty());

    Mockito.verify(feeSchemeClient, times(1)).getFeeDetails("NF");
  }

  @Test
  @DisplayName("Concurrent fee detail requests are deduplicated")
  void concurrentRequestsAreDeduped_inFlight() throws Exception {
    RetryRegistry registry = RetryRegistry.ofDefaults();
    HttpFeeSchemeProvider provider = new HttpFeeSchemeProvider(feeSchemeClient, registry);

    FeeDetailsResponseV1 dto = new FeeDetailsResponseV1("CAT");

    CountDownLatch started = new CountDownLatch(1);
    CountDownLatch release = new CountDownLatch(1);

    Mockito.when(feeSchemeClient.getFeeDetails("F2"))
        .thenAnswer(ignored -> {
          started.countDown();
          try {
            boolean ok = release.await(5, TimeUnit.SECONDS);
            if (!ok) {
              throw new RuntimeException("release latch timeout");
            }
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          return ResponseEntity.ok(dto);
        });

    // start two threads that subscribe concurrently
    Thread t1 = new Thread(() -> provider.getFeeDetails("F2").block());
    Thread t2 = new Thread(() -> provider.getFeeDetails("F2").block());
    t1.start();
    // wait until the first invocation has started
    assertTrue(started.await(2, TimeUnit.SECONDS));
    t2.start();

    // allow the remote to proceed
    release.countDown();

    t1.join(5000);
    t2.join(5000);

    // ensure both threads completed
    assertFalse(t1.isAlive(), "t1 did not finish");
    assertFalse(t2.isAlive(), "t2 did not finish");

    Mockito.verify(feeSchemeClient, times(1)).getFeeDetails("F2");
  }
}
