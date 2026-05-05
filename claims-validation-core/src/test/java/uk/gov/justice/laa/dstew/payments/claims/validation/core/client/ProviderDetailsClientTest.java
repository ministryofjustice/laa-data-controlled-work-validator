package uk.gov.justice.laa.dstew.payments.claims.validation.core.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Parameter;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestParam;
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
        org.mockito.ArgumentMatchers.any()))
        .thenReturn(Mono.just(new ProviderFirmOfficeContractAndScheduleDto()));
  }

  @Test
  @DisplayName("Delegates to 4-arg overload with requireOpenStatus=false")
  void delegatesWithRequireOpenStatusFalse() {
    String officeCode = "2Q286D";
    LocalDate effectiveDate = LocalDate.of(2025, 1, 1);

    client.getProviderFirmSchedules(officeCode, effectiveDate);

    verify(client).getProviderFirmSchedules(
        eq(officeCode),
        eq(effectiveDate),
        eq(false));
  }

  @Test
  @DisplayName("Delegates when optional effectiveDate is null: 2-arg default method passes false")
  void delegatesWithNullEffectiveDateViaTwoArgDefaultMethod() {
    // Call the 2-arg default method so the interface default delegates requireOpenStatus -> false
    client.getProviderFirmSchedules("2Q286D", null);

    verify(client).getProviderFirmSchedules(
        eq("2Q286D"),
        isNull(),
        eq(false));
  }

  @Test
  @DisplayName("Direct 3-arg call with null requireOpenStatus passes null (no Java-level defaulting)")
  void directThreeArgCallWithNullRequireOpenStatusPassesNull() {
    client.getProviderFirmSchedules("2Q286D", null, null);

    // When calling the 3-arg overload directly, Java does not apply the RequestParam defaultValue;
    // the call will be recorded with a null third argument.
    verify(client).getProviderFirmSchedules(
        eq("2Q286D"),
        isNull(),
        isNull());
  }

  @Test
  @DisplayName("RequestParam annotation on requireOpenStatus declares defaultValue='false'")
  void requestParamAnnotationDeclaresDefaultValueFalse() throws NoSuchMethodException {
    java.lang.reflect.Method m = ProviderDetailsClient.class.getMethod(
        "getProviderFirmSchedules", String.class, LocalDate.class, Boolean.class);
    Parameter param = m.getParameters()[2];
    RequestParam ann = param.getAnnotation(RequestParam.class);
    assertNotNull(ann, "requireOpenStatus parameter should be annotated with @RequestParam");
    assertEquals("false", ann.defaultValue(), "@RequestParam.defaultValue should be 'false'");
  }
}
