package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.DataClaimsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.GovernmentDepartment;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.GovernmentDepartmentsList;

@ExtendWith(MockitoExtension.class)
@DisplayName("HttpGovernmentDepartmentProvider - Government Department caching")
class HttpGovernmentDepartmentProviderTest {

  private static final Instant BASE = Instant.parse("2026-01-01T00:00:00Z");

  @Mock DataClaimsClient dataClaimsClient;

  private HttpGovernmentDepartmentProvider provider;

  @BeforeEach
  void setUp() {
    DateUtils.setClock(Clock.fixed(BASE, ZoneOffset.UTC));
    // Single attempt so failure tests do not wait for retry back-off.
    RetryRegistry registry = RetryRegistry.of(RetryConfig.custom().maxAttempts(1).build());
    provider = new HttpGovernmentDepartmentProvider(dataClaimsClient, registry);
  }

  @AfterEach
  void tearDown() {
    DateUtils.resetClock();
  }

  private static Mono<ResponseEntity<GovernmentDepartmentsList>> response(String... names) {
    List<GovernmentDepartment> departments = Arrays.stream(names)
        .map(name -> GovernmentDepartment.builder().name(name).build())
        .toList();
    return Mono.just(ResponseEntity.ok(
        GovernmentDepartmentsList.builder().governmentDepartments(departments).build()));
  }

  private void advanceClock(Duration duration) {
    DateUtils.setClock(Clock.fixed(BASE.plus(duration), ZoneOffset.UTC));
  }

  @Test
  @DisplayName("Returns department names, ignoring blank names")
  void returnsNames() {
    when(dataClaimsClient.getGovernmentDepartments())
        .thenReturn(response("Ministry of Justice", " ", null, "Home Office"));

    assertThat(provider.getActiveGovernmentDepartments())
        .containsExactly("Ministry of Justice", "Home Office");
  }

  @Test
  @DisplayName("Cache miss then cache hit")
  void cacheMissThenHit() {
    when(dataClaimsClient.getGovernmentDepartments()).thenReturn(response("Home Office"));

    provider.getActiveGovernmentDepartments();
    provider.getActiveGovernmentDepartments();

    verify(dataClaimsClient, times(1)).getGovernmentDepartments();
  }

  @Test
  @DisplayName("Cache hits do not extend the TTL, so the list is refetched after 30 minutes")
  void cacheExpiresAfterFixedTtl() {
    when(dataClaimsClient.getGovernmentDepartments()).thenReturn(response("Home Office"));

    provider.getActiveGovernmentDepartments();
    advanceClock(Duration.ofMinutes(20));
    provider.getActiveGovernmentDepartments();
    verify(dataClaimsClient, times(1)).getGovernmentDepartments();

    when(dataClaimsClient.getGovernmentDepartments()).thenReturn(response("Cabinet Office"));
    advanceClock(Duration.ofMinutes(31));

    assertThat(provider.getActiveGovernmentDepartments()).containsExactly("Cabinet Office");
    verify(dataClaimsClient, times(2)).getGovernmentDepartments();
  }

  @Test
  @DisplayName("Empty list throws and is not cached")
  void emptyListThrowsAndIsNotCached() {
    when(dataClaimsClient.getGovernmentDepartments())
        .thenReturn(response())
        .thenReturn(response("Home Office"));

    assertThatThrownBy(provider::getActiveGovernmentDepartments)
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("Data Claims API returned no Government Departments");
    assertThat(provider.getActiveGovernmentDepartments()).containsExactly("Home Office");
    verify(dataClaimsClient, times(2)).getGovernmentDepartments();
  }

  @Test
  @DisplayName("Missing body throws")
  void missingBodyThrows() {
    when(dataClaimsClient.getGovernmentDepartments())
        .thenReturn(Mono.just(ResponseEntity.ok().build()));

    assertThatThrownBy(provider::getActiveGovernmentDepartments)
        .isInstanceOf(IllegalStateException.class);
  }

  @Test
  @DisplayName("Client failure propagates and is not cached")
  void clientFailurePropagatesAndIsNotCached() {
    when(dataClaimsClient.getGovernmentDepartments())
        .thenReturn(Mono.error(new RuntimeException("boom")))
        .thenReturn(response("Home Office"));

    assertThatThrownBy(provider::getActiveGovernmentDepartments).hasMessageContaining("boom");
    assertThat(provider.getActiveGovernmentDepartments()).containsExactly("Home Office");
    verify(dataClaimsClient, times(2)).getGovernmentDepartments();
  }

  @Test
  @DisplayName("Transient failure is retried and succeeds")
  void transientFailureIsRetried() {
    RetryRegistry registry = RetryRegistry.of(
        RetryConfig.custom().maxAttempts(3).waitDuration(Duration.ofMillis(10)).build());
    provider = new HttpGovernmentDepartmentProvider(dataClaimsClient, registry);
    when(dataClaimsClient.getGovernmentDepartments())
        .thenReturn(Mono.error(new RuntimeException("503 Service Unavailable")))
        .thenReturn(response("Home Office"));

    assertThat(provider.getActiveGovernmentDepartments()).containsExactly("Home Office");
    verify(dataClaimsClient, times(2)).getGovernmentDepartments();
  }

  @Test
  @DisplayName("Empty list is retried like any other failure when retries are configured")
  void emptyListIsRetried() {
    RetryRegistry registry = RetryRegistry.of(
        RetryConfig.custom().maxAttempts(3).waitDuration(Duration.ofMillis(10)).build());
    provider = new HttpGovernmentDepartmentProvider(dataClaimsClient, registry);
    when(dataClaimsClient.getGovernmentDepartments()).thenReturn(response());

    assertThatThrownBy(provider::getActiveGovernmentDepartments)
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("Data Claims API returned no Government Departments");
    verify(dataClaimsClient, times(3)).getGovernmentDepartments();
  }
}
