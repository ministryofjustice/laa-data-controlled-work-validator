package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.ProviderDetailsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils;
import uk.gov.justice.laadata.providers.model.FirmOfficeContractAndScheduleDetails;
import uk.gov.justice.laadata.providers.model.ProviderFirmOfficeContractAndScheduleDto;
import uk.gov.justice.laadata.providers.model.ProviderFirmOfficeSummary;

@ExtendWith(MockitoExtension.class)
@DisplayName("HttpProviderDetailsProvider - caching and in-flight behaviour")
class HttpProviderDetailsProviderTest {

  @Mock private ProviderDetailsClient client;

  @Mock private RetryRegistry retryRegistry;

  @InjectMocks private HttpProviderDetailsProvider service;

  @BeforeEach
  void setup() {
    Retry noOpRetry =
        Retry.of(
            "pdaRetry",
            RetryConfig.custom()
                .maxAttempts(1) // ✅ Only one attempt
                .build());
    when(retryRegistry.retry("pdaRetry")).thenReturn(noOpRetry);
  }

  @Test
  @DisplayName("getProviderFirmSchedules returns provider schedules on success")
  void testGetProviderFirmSchedules() {
    // Arrange
    String officeCode = "Office1";
    LocalDate effectiveDate = LocalDate.now();

    ProviderFirmOfficeContractAndScheduleDto expectedDto =
        ProviderFirmOfficeContractAndScheduleDto.builder()
            .office(ProviderFirmOfficeSummary.builder().firmOfficeCode(officeCode).build())
            .build();

    // Simulate first 2 calls fail, third succeeds
    when(client.getProviderFirmSchedules(anyString(), eq(effectiveDate)))
        .thenReturn(Mono.just(expectedDto));

    // Act
    Mono<ProviderFirmOfficeContractAndScheduleDto> result =
        service.fetchProviderFirmSchedules(officeCode, effectiveDate);

    // Assert
    StepVerifier.create(result)
        .expectNextMatches(
            dto -> {
              assertNotNull(dto.getOffice());
              assertNotNull(dto.getOffice().getFirmOfficeCode());
              return dto.getOffice().getFirmOfficeCode().equals(officeCode);
            })
        .verifyComplete();

    verify(client, times(1)).getProviderFirmSchedules(officeCode, effectiveDate);
  }

  @Test
  @DisplayName("getProviderFirmSchedules propagates remote errors")
  void testGetProviderFirmSchedules_withError() {
    // Arrange
    String officeCode = "OFF123";
    LocalDate effectiveDate = LocalDate.now();

    // Simulate all attempts fail
    when(client.getProviderFirmSchedules(officeCode, effectiveDate))
        .thenReturn(Mono.error(new RuntimeException("Temporary error")));

    // Act
    Mono<ProviderFirmOfficeContractAndScheduleDto> result =
        service.fetchProviderFirmSchedules(officeCode, effectiveDate);

    // Assert
    StepVerifier.create(result)
        .expectErrorMatches(
            throwable ->
                throwable instanceof RuntimeException
                    && throwable.getMessage().contains("Temporary error"))
        .verify();

    verify(client, times(1)).getProviderFirmSchedules(officeCode, effectiveDate);
  }

  @Test
  @DisplayName("Uses positive cache when schedule window covers effective date")
  void testGetProviderFirmSchedules_usesCacheWhenDateCovered() {
    String officeCode = "Office1";
    LocalDate effectiveDate = LocalDate.of(2024, 5, 1);

    ProviderFirmOfficeContractAndScheduleDto expectedDto =
        ProviderFirmOfficeContractAndScheduleDto.builder()
            .office(ProviderFirmOfficeSummary.builder().firmOfficeCode(officeCode).build())
            .schedules(
                List.of(
                    FirmOfficeContractAndScheduleDetails.builder()
                        .scheduleStartDate(LocalDate.of(2024, 1, 1))
                        .scheduleEndDate(LocalDate.of(2024, 12, 31))
                        .build()))
            .build();

    when(client.getProviderFirmSchedules(officeCode, effectiveDate))
        .thenReturn(Mono.just(expectedDto));

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, effectiveDate))
        .expectNext(expectedDto)
        .verifyComplete();

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, effectiveDate))
        .expectNext(expectedDto)
        .verifyComplete();

    verify(client, times(1)).getProviderFirmSchedules(officeCode, effectiveDate);
  }

  @Test
  @DisplayName("Negative responses are cached to avoid repeated remote calls")
  void testGetProviderFirmSchedules_negativeCache() {
    String officeCode = "Office2";
    LocalDate effectiveDate = LocalDate.of(2024, 7, 1);

    when(client.getProviderFirmSchedules(officeCode, effectiveDate)).thenReturn(Mono.empty());

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, effectiveDate))
        .verifyComplete();

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, effectiveDate))
        .verifyComplete();

    verify(client, times(1)).getProviderFirmSchedules(officeCode, effectiveDate);
  }

  @Test
  @DisplayName("Negative cache is scoped to effective date and does not block other dates")
  void negativeCacheIsCheckedBeforePositiveCacheForDifferentDates() {
    String officeCode = "Office4";
    LocalDate positiveDate = LocalDate.of(2024, 1, 15);
    LocalDate negativeDate = LocalDate.of(2024, 6, 1);

    ProviderFirmOfficeContractAndScheduleDto januaryDto =
        ProviderFirmOfficeContractAndScheduleDto.builder()
            .office(ProviderFirmOfficeSummary.builder().firmOfficeCode(officeCode).build())
            .schedules(
                List.of(
                    FirmOfficeContractAndScheduleDetails.builder()
                        .scheduleStartDate(LocalDate.of(2024, 1, 1))
                        .scheduleEndDate(LocalDate.of(2024, 1, 31))
                        .build()))
            .build();

    when(client.getProviderFirmSchedules(officeCode, negativeDate)).thenReturn(Mono.empty());
    when(client.getProviderFirmSchedules(officeCode, positiveDate))
        .thenReturn(Mono.just(januaryDto));

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, negativeDate))
        .verifyComplete();

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, positiveDate))
        .expectNext(januaryDto)
        .verifyComplete();

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, negativeDate))
        .verifyComplete();

    verify(client, times(1)).getProviderFirmSchedules(officeCode, negativeDate);
    verify(client, times(1)).getProviderFirmSchedules(officeCode, positiveDate);
  }

  @Test
  @DisplayName("Cache gap between windows requires new remote call")
  void testGetProviderFirmSchedules_gapRequiresNewCall() {
    String officeCode = "Office3";
    LocalDate firstDate = LocalDate.of(2024, 2, 1);
    LocalDate gapDate = LocalDate.of(2024, 4, 15); // Between two windows

    ProviderFirmOfficeContractAndScheduleDto firstDto =
        ProviderFirmOfficeContractAndScheduleDto.builder()
            .office(ProviderFirmOfficeSummary.builder().firmOfficeCode(officeCode).build())
            .schedules(
                List.of(
                    FirmOfficeContractAndScheduleDetails.builder()
                        .scheduleStartDate(LocalDate.of(2024, 1, 1))
                        .scheduleEndDate(LocalDate.of(2024, 3, 31))
                        .build(),
                    FirmOfficeContractAndScheduleDetails.builder()
                        .scheduleStartDate(LocalDate.of(2024, 5, 1))
                        .scheduleEndDate(LocalDate.of(2024, 12, 31))
                        .build()))
            .build();

    when(client.getProviderFirmSchedules(officeCode, firstDate)).thenReturn(Mono.just(firstDto));
    when(client.getProviderFirmSchedules(officeCode, gapDate)).thenReturn(Mono.empty());

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, firstDate))
        .expectNext(firstDto)
        .verifyComplete();

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, gapDate)).verifyComplete();

    verify(client, times(1)).getProviderFirmSchedules(officeCode, firstDate);
    verify(client, times(1)).getProviderFirmSchedules(officeCode, gapDate);
  }

  @Test
  @DisplayName("Merges schedule windows across multiple responses and reuses cached hits")
  void cacheMergesWindowsAcrossMultipleMissesAndReusesHits() {
    String officeCode = "0P322F";

    LocalDate initial = LocalDate.of(2019, 8, 20);
    LocalDate covered = LocalDate.of(2018, 11, 21);
    LocalDate extendEnd = LocalDate.of(2019, 9, 26);
    LocalDate extendStart = LocalDate.of(2017, 6, 27);
    LocalDate gapFill = LocalDate.of(2018, 7, 30);

    ProviderFirmOfficeContractAndScheduleDto sept18ToAug19 =
        dtoWithWindow(officeCode, LocalDate.of(2018, 9, 1), LocalDate.of(2019, 8, 31));
    ProviderFirmOfficeContractAndScheduleDto sept18ToAug20 =
        dtoWithWindow(officeCode, LocalDate.of(2018, 9, 1), LocalDate.of(2020, 8, 31));
    ProviderFirmOfficeContractAndScheduleDto apr17ToMar18 =
        dtoWithWindow(officeCode, LocalDate.of(2017, 4, 1), LocalDate.of(2018, 3, 31));
    ProviderFirmOfficeContractAndScheduleDto apr17ToAug20 =
        dtoWithWindow(officeCode, LocalDate.of(2017, 4, 1), LocalDate.of(2020, 8, 31));

    when(client.getProviderFirmSchedules(officeCode, initial)).thenReturn(Mono.just(sept18ToAug19));
    when(client.getProviderFirmSchedules(officeCode, extendEnd))
        .thenReturn(Mono.just(sept18ToAug20));
    when(client.getProviderFirmSchedules(officeCode, extendStart))
        .thenReturn(Mono.just(apr17ToMar18));
    when(client.getProviderFirmSchedules(officeCode, gapFill)).thenReturn(Mono.just(apr17ToAug20));

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, initial))
        .expectNext(sept18ToAug19)
        .verifyComplete();

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, covered))
        .expectNext(sept18ToAug19)
        .verifyComplete();

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, extendEnd))
        .expectNext(sept18ToAug20)
        .verifyComplete();

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, extendStart))
        .expectNext(apr17ToMar18)
        .verifyComplete();

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, gapFill))
        .expectNext(apr17ToAug20)
        .verifyComplete();

    List<LocalDate> cachedDates =
        List.of(
            LocalDate.of(2019, 8, 5),
            LocalDate.of(2019, 4, 29),
            LocalDate.of(2018, 5, 30),
            LocalDate.of(2019, 4, 12),
            LocalDate.of(2019, 1, 22),
            LocalDate.of(2018, 3, 26),
            LocalDate.of(2017, 11, 10),
            LocalDate.of(2018, 10, 1),
            LocalDate.of(2019, 2, 28));

    cachedDates.forEach(
        date ->
            StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, date))
                .expectNextMatches(dto -> dto.getSchedules().size() == 4 && covers(dto, date))
                .verifyComplete());

    verify(client, times(1)).getProviderFirmSchedules(officeCode, initial);
    verify(client, times(1)).getProviderFirmSchedules(officeCode, extendEnd);
    verify(client, times(1)).getProviderFirmSchedules(officeCode, extendStart);
    verify(client, times(1)).getProviderFirmSchedules(officeCode, gapFill);
    verifyNoMoreInteractions(client);
  }

  @Test
  @DisplayName("Negative cache keys are scoped to office and effective date")
  void negativeCacheIsScopedToEffectiveDate() {
    String officeCode = "NEG_OFFICE";
    LocalDate missingDate = LocalDate.of(2020, 1, 1);
    LocalDate otherDate = LocalDate.of(2020, 2, 1);

    ProviderFirmOfficeContractAndScheduleDto dto =
        dtoWithWindow(officeCode, otherDate, otherDate.plusDays(1));

    when(client.getProviderFirmSchedules(officeCode, missingDate)).thenReturn(Mono.empty());
    when(client.getProviderFirmSchedules(officeCode, otherDate)).thenReturn(Mono.just(dto));

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, missingDate)).verifyComplete();

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, missingDate)).verifyComplete();

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, otherDate))
        .expectNext(dto)
        .verifyComplete();

    verify(client, times(1)).getProviderFirmSchedules(officeCode, missingDate);
    verify(client, times(1)).getProviderFirmSchedules(officeCode, otherDate);
    verifyNoMoreInteractions(client);
  }

  @Test
  @DisplayName("Concurrent requests for same key are deduplicated (in-flight)")
  void concurrentRequestsAreDeduped_inFlight() throws Exception {
    String officeCode = "CONC_OFF";
    LocalDate date = LocalDate.of(2024, 1, 1);
    ProviderFirmOfficeContractAndScheduleDto dto = dtoWithWindow(officeCode, date, date);

    CountDownLatch started = new CountDownLatch(1);
    CountDownLatch t2Ready = new CountDownLatch(1);
    CountDownLatch release = new CountDownLatch(1);

    when(client.getProviderFirmSchedules(officeCode, date))
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
          return Mono.just(dto);
        });

    Thread t1 = new Thread(() -> service.fetchProviderFirmSchedules(officeCode, date).block());
    Thread t2 = new Thread(() -> {
      service.fetchProviderFirmSchedules(officeCode, date).block();
    });

    t1.start();
    // wait until t1 is inside the remote call (sink is in the map)
    assertTrue(started.await(2, TimeUnit.SECONDS));

    t2.start();
    // give t2 time to reach sink.asMono()
    Thread.sleep(100);
    t2Ready.countDown();

    release.countDown();

    t1.join(5000);
    t2.join(5000);

    assertFalse(t1.isAlive(), "t1 did not finish");
    assertFalse(t2.isAlive(), "t2 did not finish");

    verify(client, times(1)).getProviderFirmSchedules(officeCode, date);
  }

  @Test
  @DisplayName("Eviction/clear allows refreshed data to be fetched")
  void evictClearsCaches() {
    String officeCode = "EVICT_OFF";
    LocalDate date = LocalDate.of(2024, 3, 1);

    ProviderFirmOfficeContractAndScheduleDto dto1 = dtoWithWindow(officeCode, date, date);
    ProviderFirmOfficeContractAndScheduleDto dto2 = dtoWithWindow(officeCode, date, date.plusDays(1));

    when(client.getProviderFirmSchedules(officeCode, date)).thenReturn(Mono.just(dto1));

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, date))
        .expectNext(dto1)
        .verifyComplete();

    verify(client, times(1)).getProviderFirmSchedules(officeCode, date);

    // create a fresh provider instance to simulate cleared caches (new instance has empty caches)
    HttpProviderDetailsProvider fresh = new HttpProviderDetailsProvider(client, retryRegistry);

    // prepare new response
    when(client.getProviderFirmSchedules(officeCode, date)).thenReturn(Mono.just(dto2));

    StepVerifier.create(fresh.fetchProviderFirmSchedules(officeCode, date))
        .expectNext(dto2)
        .verifyComplete();

    verify(client, times(2)).getProviderFirmSchedules(officeCode, date);
  }

  @Test
  @DisplayName("Positive cache expires and triggers refetch for provider details")
  void positiveCacheExpiresAndRefetches() {
    String officeCode = "REFETCH1";
    LocalDate date = LocalDate.of(2025, 1, 1);

    Instant base = Instant.parse("2025-01-01T00:00:00Z");
    DateUtils.setClock(Clock.fixed(base, ZoneOffset.UTC));

    ProviderFirmOfficeContractAndScheduleDto dto1 = dtoWithWindow(officeCode, date, date);
    when(client.getProviderFirmSchedules(officeCode, date)).thenReturn(Mono.just(dto1));

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, date))
        .expectNext(dto1)
        .verifyComplete();

    verify(client, times(1)).getProviderFirmSchedules(officeCode, date);

    // advance beyond positive TTL (10 minutes)
    DateUtils.setClock(Clock.fixed(base.plus(Duration.ofMinutes(11)), ZoneOffset.UTC));

    ProviderFirmOfficeContractAndScheduleDto dto2 = dtoWithWindow(officeCode, date, date.plusDays(1));
    when(client.getProviderFirmSchedules(officeCode, date)).thenReturn(Mono.just(dto2));

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, date))
        .expectNext(dto2)
        .verifyComplete();

    verify(client, times(2)).getProviderFirmSchedules(officeCode, date);
    DateUtils.resetClock();
  }

  @Test
  @DisplayName("Negative cache expires and triggers refetch for provider details")
  void negativeCacheExpiresAndRefetches() {
    String officeCode = "NEGREF";
    LocalDate date = LocalDate.of(2025, 2, 1);

    Instant base = Instant.parse("2025-02-01T00:00:00Z");
    DateUtils.setClock(Clock.fixed(base, ZoneOffset.UTC));

    when(client.getProviderFirmSchedules(officeCode, date)).thenReturn(Mono.empty());
    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, date)).verifyComplete();
    verify(client, times(1)).getProviderFirmSchedules(officeCode, date);

    // advance beyond negative TTL (5 minutes)
    DateUtils.setClock(Clock.fixed(base.plus(Duration.ofMinutes(6)), ZoneOffset.UTC));

    ProviderFirmOfficeContractAndScheduleDto dto = dtoWithWindow(officeCode, date, date);
    when(client.getProviderFirmSchedules(officeCode, date)).thenReturn(Mono.just(dto));

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, date))
        .expectNext(dto)
        .verifyComplete();

    verify(client, times(2)).getProviderFirmSchedules(officeCode, date);
    DateUtils.resetClock();
  }

  private ProviderFirmOfficeContractAndScheduleDto dtoWithWindow(
      String officeCode, LocalDate start, LocalDate end) {
    return ProviderFirmOfficeContractAndScheduleDto.builder()
        .office(ProviderFirmOfficeSummary.builder().firmOfficeCode(officeCode).build())
        .schedules(
            List.of(
                FirmOfficeContractAndScheduleDetails.builder()
                    .scheduleStartDate(start)
                    .scheduleEndDate(end)
                    .build()))
        .build();
  }

  private boolean covers(ProviderFirmOfficeContractAndScheduleDto dto, LocalDate effectiveDate) {
    return dto.getSchedules().stream()
        .anyMatch(
            schedule ->
                !effectiveDate.isBefore(schedule.getScheduleStartDate())
                    && !effectiveDate.isAfter(schedule.getScheduleEndDate()));
  }
}
