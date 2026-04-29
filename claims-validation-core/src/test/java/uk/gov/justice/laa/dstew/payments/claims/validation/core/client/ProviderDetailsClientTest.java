package uk.gov.justice.laa.dstew.payments.claims.validation.core.client;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import uk.gov.justice.laadata.providers.model.ProviderFirmOfficeContractAndScheduleDto;

/**
 * Tests for the {@code getProviderFirmSchedules(officeCode, areaOfLaw, effectiveDate)} default
 * method on {@link ProviderDetailsClient}.
 *
 * <p>The annotation-driven HTTP transport is handled by Spring and is not tested here. What IS
 * tested is the default method's contract: it must always delegate to the 4-argument overload
 * with {@code requireOpenStatus = false}.
 */
@DisplayName("ProviderDetailsClient — getProviderFirmSchedules(3-arg) default method")
class ProviderDetailsClientTest {

  private ProviderDetailsClient client;

  @BeforeEach
  void setUp() {
    client = mock(ProviderDetailsClient.class, CALLS_REAL_METHODS);
    when(client.getProviderFirmSchedules(
        org.mockito.ArgumentMatchers.any(),
        org.mockito.ArgumentMatchers.any(),
        org.mockito.ArgumentMatchers.any(),
        org.mockito.ArgumentMatchers.any()))
        .thenReturn(Mono.just(new ProviderFirmOfficeContractAndScheduleDto()));
  }

  @Test
  @DisplayName("Delegates to 4-arg overload with requireOpenStatus=false")
  void delegatesWithRequireOpenStatusFalse() {
    String officeCode = "2Q286D";
    String areaOfLaw = "CRIME_LOWER";
    LocalDate effectiveDate = LocalDate.of(2025, 1, 1);

    client.getProviderFirmSchedules(officeCode, areaOfLaw, effectiveDate);

    verify(client).getProviderFirmSchedules(
        eq(officeCode),
        eq(areaOfLaw),
        eq(effectiveDate),
        eq(false));
  }

  @Test
  @DisplayName("Delegates with null areaOfLaw and null effectiveDate, still passing false")
  void delegatesWithNullOptionalParamsAndRequireOpenStatusFalse() {
    client.getProviderFirmSchedules("2Q286D", null, null);

    verify(client).getProviderFirmSchedules(
        eq("2Q286D"),
        isNull(),
        isNull(),
        eq(false));
  }
}
