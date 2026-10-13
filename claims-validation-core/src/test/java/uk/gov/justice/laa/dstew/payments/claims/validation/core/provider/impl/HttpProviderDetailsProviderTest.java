package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
import uk.gov.justice.laadata.providers.model.FirmOfficeContractAndScheduleDetails;
import uk.gov.justice.laadata.providers.model.ProviderFirmOfficeContractAndScheduleDto;
import uk.gov.justice.laadata.providers.model.ProviderFirmOfficeSummary;

@ExtendWith(MockitoExtension.class)
@DisplayName("HttpProviderDetailsProvider - direct request behaviour")
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
  @DisplayName("Calls Provider Details for every invocation")
  void testGetProviderFirmSchedules_callsApiForEveryInvocation() {
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

    verify(client, times(2)).getProviderFirmSchedules(officeCode, effectiveDate);
  }

  @Test
  @DisplayName("Empty responses do not prevent a later request")
  void testGetProviderFirmSchedules_emptyResponseDoesNotCache() {
    String officeCode = "Office2";
    LocalDate effectiveDate = LocalDate.of(2024, 7, 1);
    ProviderFirmOfficeContractAndScheduleDto expectedDto = dtoWithWindow(
        officeCode, effectiveDate, effectiveDate);

    when(client.getProviderFirmSchedules(officeCode, effectiveDate))
        .thenReturn(Mono.empty(), Mono.just(expectedDto));

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, effectiveDate))
        .verifyComplete();

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, effectiveDate))
        .expectNext(expectedDto)
        .verifyComplete();

    verify(client, times(2)).getProviderFirmSchedules(officeCode, effectiveDate);
  }

  @Test
  @DisplayName("Different effective dates are requested independently")
  void differentEffectiveDatesAreRequestedIndependently() {
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

    verify(client, times(2)).getProviderFirmSchedules(officeCode, negativeDate);
    verify(client, times(1)).getProviderFirmSchedules(officeCode, positiveDate);
  }

  @Test
  @DisplayName("Returns bounded and open-ended schedules without date arithmetic")
  void returnsSchedulesWithoutCalculatingCoverageDates() {
    String officeCode = "DATE_OFFICE";
    LocalDate effectiveDate = LocalDate.of(2024, 4, 15);
    FirmOfficeContractAndScheduleDetails bounded =
        FirmOfficeContractAndScheduleDetails.builder()
            .scheduleStartDate(LocalDate.of(2024, 1, 1))
            .scheduleEndDate(LocalDate.of(2024, 12, 31))
            .build();
    FirmOfficeContractAndScheduleDetails openEnded =
        FirmOfficeContractAndScheduleDetails.builder().build();
    ProviderFirmOfficeContractAndScheduleDto dto =
        ProviderFirmOfficeContractAndScheduleDto.builder()
            .office(ProviderFirmOfficeSummary.builder().firmOfficeCode(officeCode).build())
            .schedules(List.of(bounded, openEnded))
            .build();
    when(client.getProviderFirmSchedules(officeCode, effectiveDate)).thenReturn(Mono.just(dto));

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, effectiveDate))
        .expectNext(dto)
        .verifyComplete();

    assertEquals(2, dto.getSchedules().size());
    assertNull(dto.getSchedules().get(1).getScheduleStartDate());
    assertNull(dto.getSchedules().get(1).getScheduleEndDate());
    verify(client).getProviderFirmSchedules(officeCode, effectiveDate);
  }

  @Test
  @DisplayName("Preserves Provider Details retry behaviour")
  void retriesProviderDetailsFailures() {
    Retry retry =
        Retry.of(
            "pdaRetry",
            RetryConfig.custom()
                .maxAttempts(2)
                .waitDuration(Duration.ZERO)
                .retryExceptions(RuntimeException.class)
                .build());
    when(retryRegistry.retry("pdaRetry")).thenReturn(retry);

    String officeCode = "RETRY_OFFICE";
    LocalDate effectiveDate = LocalDate.of(2024, 3, 1);
    ProviderFirmOfficeContractAndScheduleDto dto =
        dtoWithWindow(officeCode, effectiveDate, effectiveDate);
    AtomicInteger subscriptions = new AtomicInteger();
    when(client.getProviderFirmSchedules(officeCode, effectiveDate))
        .thenReturn(
            Mono.defer(
                () ->
                    subscriptions.incrementAndGet() == 1
                        ? Mono.error(new RuntimeException("temporary failure"))
                        : Mono.just(dto)));

    StepVerifier.create(service.fetchProviderFirmSchedules(officeCode, effectiveDate))
        .expectNext(dto)
        .verifyComplete();

    assertEquals(2, subscriptions.get());
    verify(client).getProviderFirmSchedules(officeCode, effectiveDate);
  }

  @Test
  @DisplayName("Calls the API independently for different offices and effective dates")
  void callsAreIsolatedByOfficeAndEffectiveDate() {
    String firstOffice = "OFFICE_A";
    String secondOffice = "OFFICE_B";
    LocalDate firstDate = LocalDate.of(2024, 2, 1);
    LocalDate secondDate = LocalDate.of(2024, 4, 15);
    ProviderFirmOfficeContractAndScheduleDto firstDto =
        dtoWithWindow(firstOffice, firstDate, firstDate);
    ProviderFirmOfficeContractAndScheduleDto secondDto =
        dtoWithWindow(secondOffice, secondDate, secondDate);

    when(client.getProviderFirmSchedules(firstOffice, firstDate)).thenReturn(Mono.just(firstDto));
    when(client.getProviderFirmSchedules(secondOffice, secondDate))
        .thenReturn(Mono.just(secondDto));

    StepVerifier.create(service.fetchProviderFirmSchedules(firstOffice, firstDate))
        .expectNext(firstDto)
        .verifyComplete();
    StepVerifier.create(service.fetchProviderFirmSchedules(secondOffice, secondDate))
        .expectNext(secondDto)
        .verifyComplete();

    verify(client).getProviderFirmSchedules(firstOffice, firstDate);
    verify(client).getProviderFirmSchedules(secondOffice, secondDate);
    verifyNoMoreInteractions(client);
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
}
