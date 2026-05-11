package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import io.github.resilience4j.retry.RetryRegistry;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.FeeSchemeClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.ProviderDetailsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.HttpFeeSchemeProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.HttpProviderDetailsProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;
import uk.gov.justice.laa.fee.scheme.model.FeeDetailsResponseV2;
import uk.gov.justice.laadata.providers.model.FirmOfficeContractAndScheduleDetails;
import uk.gov.justice.laadata.providers.model.FirmOfficeContractAndScheduleLine;
import uk.gov.justice.laadata.providers.model.ProviderFirmOfficeContractAndScheduleDto;

@ExtendWith(MockitoExtension.class)
@DisplayName("Effective category of law claim validator test")
class EffectiveCategoryOfLawClaimValidationTest {

  EffectiveCategoryOfLawClaimValidator validator;

  @Mock
  HttpFeeSchemeProvider feeSchemeClient;
  @Mock
  HttpProviderDetailsProvider httpProviderDetailsProvider;

  @BeforeEach
  void beforeEach() {
    validator = new EffectiveCategoryOfLawClaimValidator(
            feeSchemeClient, httpProviderDetailsProvider);
  }

  @Test
  @DisplayName("Validates category of law with provider and fee scheme clients")
  void validatesCategoryOfLawWithProviderAndFeeSchemeClients() {
    UUID claimId = new UUID(1, 1);
    Claim claim = Claim.builder()
        .id(claimId)
        .feeCode("feeCode1")
        .caseStartDate("2025-08-14")
        .status(ClaimStatus.READY_TO_PROCESS)
        .officeAccountNumber("officeAccountNumber")
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .build();

    ProviderFirmOfficeContractAndScheduleDto data =
        new ProviderFirmOfficeContractAndScheduleDto()
            .addSchedulesItem(
                new FirmOfficeContractAndScheduleDetails()
                    .addScheduleLinesItem(
                        new FirmOfficeContractAndScheduleLine().categoryOfLaw("categoryOfLaw1")));
    when(httpProviderDetailsProvider.getProviderFirmSchedules(
            eq("officeAccountNumber"), any(LocalDate.class)))
        .thenReturn(Mono.just(data));
    FeeDetailsResponseV2 feeDetailsResponse = new FeeDetailsResponseV2();
    feeDetailsResponse.setCategoryOfLawCodes(List.of("categoryOfLaw1"));
    when(feeSchemeClient.getFeeDetails("feeCode1"))
        .thenReturn(Mono.just(feeDetailsResponse));
    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);
    assertThat(context.getIssues()).isEmpty();
  }

  @Test
  @DisplayName("Should add invalid fee/category issue when fee scheme returns empty")
  void shouldHandleEmptyFeeSchemeResponse() {
    UUID claimId = new UUID(1, 1);
    Claim claim = Claim.builder()
        .id(claimId)
        .feeCode("feeCode1")
        .caseStartDate("2025-08-14")
        .status(ClaimStatus.READY_TO_PROCESS)
        .officeAccountNumber("officeAccountNumber")
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .build();

    ProviderFirmOfficeContractAndScheduleDto data =
        new ProviderFirmOfficeContractAndScheduleDto()
            .addSchedulesItem(
                new FirmOfficeContractAndScheduleDetails()
                    .addScheduleLinesItem(
                        new FirmOfficeContractAndScheduleLine().categoryOfLaw("categoryOfLaw1")));
    when(httpProviderDetailsProvider.getProviderFirmSchedules(
            eq("officeAccountNumber"), any(LocalDate.class)))
        .thenReturn(Mono.just(data));

    // Fee scheme returns empty -> treated as invalid fee/category
    when(feeSchemeClient.getFeeDetails("feeCode1")).thenReturn(Mono.empty());

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);
    assertThat(context.getIssues()).hasSize(1);
    assertThat(context.getIssues().getFirst().toString())
        .contains(ClaimValidationError.INVALID_CATEGORY_OF_LAW_AND_FEE_CODE.name());
  }

  @ParameterizedTest(name = "Should handle fee scheme API {0} exception")
  @MethodSource("exceptionProvider")
  @DisplayName("Should handle fee scheme API errors by adding technical error")
  void shouldHandleFeeSchemeApiError(Exception exception) {
    UUID claimId = new UUID(1, 1);
    Claim claim = Claim.builder()
        .id(claimId)
        .feeCode("feeCode1")
        .caseStartDate("2025-08-14")
        .status(ClaimStatus.READY_TO_PROCESS)
        .officeAccountNumber("officeAccountNumber")
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .build();

    ProviderFirmOfficeContractAndScheduleDto data =
        new ProviderFirmOfficeContractAndScheduleDto()
            .addSchedulesItem(
                new FirmOfficeContractAndScheduleDetails()
                    .addScheduleLinesItem(
                        new FirmOfficeContractAndScheduleLine().categoryOfLaw("categoryOfLaw1")));
    when(httpProviderDetailsProvider.getProviderFirmSchedules(
            eq("officeAccountNumber"), any(LocalDate.class)))
        .thenReturn(Mono.just(data));

    when(feeSchemeClient.getFeeDetails("feeCode1")).thenReturn(Mono.error(exception));

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);
    assertThat(context.getIssues()).hasSize(1);
    assertThat(context.getIssues().getFirst().toString())
        .contains(ClaimValidationError.TECHNICAL_ERROR_FEE_SCHEME_API.name());
  }

  static Stream<Arguments> exceptionProvider() {
    return Stream.of(
        Arguments.of(new WebClientResponseException(401, "Unauthorised", null, null, null)),
        Arguments.of(new WebClientResponseException(404, "Not Found", null, null, null)),
        Arguments.of(new WebClientResponseException(500, "Server Error", null, null, null)),
        Arguments.of(new RuntimeException("Unexpected error")));
  }

  @ParameterizedTest(name = "Should handle {0} exception")
  @MethodSource("exceptionProvider")
  @DisplayName("Should handle exceptions by adding error message")
  void shouldHandleExceptions(Exception exception) {
    UUID claimId = new UUID(1, 1);
    Claim claim = Claim.builder()
        .id(claimId)
        .feeCode("feeCode1")
        .caseStartDate("2025-08-14")
        .status(ClaimStatus.READY_TO_PROCESS)
        .officeAccountNumber("officeAccountNumber")
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .build();
    when(httpProviderDetailsProvider.getProviderFirmSchedules(
            eq("officeAccountNumber"), any(LocalDate.class)))
        .thenReturn(Mono.error(exception));
    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);
    assertThat(context.getIssues()).hasSize(1);
    assertThat(context.getIssues().getFirst().toString())
        .contains(ClaimValidationError.TECHNICAL_ERROR_PROVIDER_DETAILS_API.name());
  }

  @Test
  @DisplayName("EffectiveCategoryOfLawClaimValidator - priority, appliesTo and validator code")
  void effectiveCategoryOfLawValidatorMetadata() {
    assertThat(validator.priority()).isEqualTo(1000);
    // appliesTo: null, fee and all should be true; other scopes false
    assertThat(validator.appliesTo(null)).isTrue();
    assertThat(validator.appliesTo("fee")).isTrue();
    assertThat(validator.appliesTo("all")).isTrue();
    assertThat(validator.appliesTo("disbursement")).isFalse();
    assertThat(validator.getValidatorCode()).isEqualTo("CLAIM_CATEGORY_OF_LAW");
  }

  @Test
  @DisplayName("Should select first matching authorised category of law from fee details order")
  void shouldSelectFirstAuthorisedCategoryOfLaw() {
    UUID claimId = new UUID(1, 2);
    Claim claim = Claim.builder()
        .id(claimId)
        .feeCode("feeCode2")
        .caseStartDate("2025-08-14")
        .status(ClaimStatus.READY_TO_PROCESS)
        .officeAccountNumber("officeAccountNumber")
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .build();

    ProviderFirmOfficeContractAndScheduleDto data =
        new ProviderFirmOfficeContractAndScheduleDto()
            .addSchedulesItem(
                new FirmOfficeContractAndScheduleDetails()
                    .addScheduleLinesItem(
                        new FirmOfficeContractAndScheduleLine().categoryOfLaw("A"))
                    .addScheduleLinesItem(
                        new FirmOfficeContractAndScheduleLine().categoryOfLaw("B")));

    when(httpProviderDetailsProvider.getProviderFirmSchedules(
            eq("officeAccountNumber"), any(LocalDate.class)))
        .thenReturn(Mono.just(data));

    FeeDetailsResponseV2 feeDetailsResponse = new FeeDetailsResponseV2();
    // fee scheme codes order: X, A, B -> should pick A (first match)
    feeDetailsResponse.setCategoryOfLawCodes(List.of("X", "A", "B"));
    when(feeSchemeClient.getFeeDetails("feeCode2"))
        .thenReturn(Mono.just(feeDetailsResponse));

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);
    assertThat(context.getAuthorisedCategoryOfLawCode()).isEqualTo("A");
  }

  @Test
  @DisplayName("Should add not-authorised issue when no fee category matches provider categories")
  void shouldAddNotAuthorisedWhenNoMatch() {
    UUID claimId = new UUID(1, 3);
    Claim claim = Claim.builder()
        .id(claimId)
        .feeCode("feeCode3")
        .caseStartDate("2025-08-14")
        .status(ClaimStatus.READY_TO_PROCESS)
        .officeAccountNumber("officeAccountNumber")
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .build();

    ProviderFirmOfficeContractAndScheduleDto data =
        new ProviderFirmOfficeContractAndScheduleDto()
            .addSchedulesItem(
                new FirmOfficeContractAndScheduleDetails()
                    .addScheduleLinesItem(
                        new FirmOfficeContractAndScheduleLine().categoryOfLaw("C"))
                    .addScheduleLinesItem(
                        new FirmOfficeContractAndScheduleLine().categoryOfLaw("D")));

    when(httpProviderDetailsProvider.getProviderFirmSchedules(
            eq("officeAccountNumber"), any(LocalDate.class)))
        .thenReturn(Mono.just(data));

    FeeDetailsResponseV2 feeDetailsResponse = new FeeDetailsResponseV2();
    feeDetailsResponse.setCategoryOfLawCodes(List.of("A", "B"));
    when(feeSchemeClient.getFeeDetails("feeCode3"))
        .thenReturn(Mono.just(feeDetailsResponse));

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);
    assertThat(context.getIssues()).hasSize(1);
    assertThat(context.getIssues().getFirst().toString())
        .contains(ClaimValidationError.INVALID_CATEGORY_OF_LAW_NOT_AUTHORISED_FOR_PROVIDER.name());
  }

  @Test
  @DisplayName("Should handle bad provider schedule data (null schedules) as technical error")
  void shouldHandleBadProviderScheduleData() {
    UUID claimId = new UUID(1, 4);
    Claim claim = Claim.builder()
        .id(claimId)
        .feeCode("feeCode4")
        .caseStartDate("2025-08-14")
        .status(ClaimStatus.READY_TO_PROCESS)
        .officeAccountNumber("officeAccountNumber")
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .build();

    // Simulate provider client error -> validator should map to technical provider error
    when(httpProviderDetailsProvider.getProviderFirmSchedules(
            eq("officeAccountNumber"), any(LocalDate.class)))
        .thenReturn(Mono.error(new RuntimeException("boom")));

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);
    assertThat(context.getIssues()).hasSize(1);
    assertThat(context.getIssues().getFirst().toString())
        .contains(ClaimValidationError.TECHNICAL_ERROR_PROVIDER_DETAILS_API.name());
  }

  @Test
  @DisplayName("Should handle provider schedules with null scheduleLines gracefully")
  void shouldHandleSchedulesWithNullScheduleLines() {
    UUID claimId = new UUID(1, 5);
    Claim claim = Claim.builder()
        .id(claimId)
        .feeCode("feeCode7")
        .caseStartDate("2025-08-14")
        .status(ClaimStatus.READY_TO_PROCESS)
        .officeAccountNumber("officeAccountNumber")
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .build();

    // Provider returns a schedule element but its scheduleLines is null
    ProviderFirmOfficeContractAndScheduleDto dtoWithNullLines =
        new ProviderFirmOfficeContractAndScheduleDto()
            .addSchedulesItem(new FirmOfficeContractAndScheduleDetails());

    when(httpProviderDetailsProvider.getProviderFirmSchedules(
            eq("officeAccountNumber"), any(LocalDate.class)))
        .thenReturn(Mono.just(dtoWithNullLines));

    FeeDetailsResponseV2 feeDetailsResponse = new FeeDetailsResponseV2();
    feeDetailsResponse.setCategoryOfLawCodes(List.of("ANY"));
    when(feeSchemeClient.getFeeDetails("feeCode7")).thenReturn(Mono.just(feeDetailsResponse));

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);

    // No authorised category will be found because provider categories list is empty
    assertThat(context.getIssues()).hasSize(1);
    assertThat(context.getIssues().getFirst().toString())
        .contains(ClaimValidationError.INVALID_CATEGORY_OF_LAW_NOT_AUTHORISED_FOR_PROVIDER.name());
  }

  @Test
  @DisplayName("Should call provider and fee scheme underlying clients only once when caching is enabled")
  void shouldCacheProviderAndFeeSchemeCalls() {
    // Use real provider implementations backed by mocked REST clients so we can verify underlying calls
    ProviderDetailsClient mockProviderClient = mock(ProviderDetailsClient.class);
    FeeSchemeClient mockFeeClient = mock(FeeSchemeClient.class);
    RetryRegistry retryRegistry = RetryRegistry.ofDefaults();

    HttpProviderDetailsProvider realProvider =
        new HttpProviderDetailsProvider(mockProviderClient, retryRegistry);
    HttpFeeSchemeProvider realFeeProvider = new HttpFeeSchemeProvider(mockFeeClient, retryRegistry);

    EffectiveCategoryOfLawClaimValidator realValidator =
        new EffectiveCategoryOfLawClaimValidator(realFeeProvider, realProvider);

    UUID claimId = new UUID(2, 2);
    Claim claim = Claim.builder()
        .id(claimId)
        .feeCode("feeCode5")
        .caseStartDate("2025-08-14")
        .status(ClaimStatus.READY_TO_PROCESS)
        .officeAccountNumber("officeCache")
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .build();

    ProviderFirmOfficeContractAndScheduleDto data =
        new ProviderFirmOfficeContractAndScheduleDto()
            .addSchedulesItem(
                new FirmOfficeContractAndScheduleDetails()
                    .addScheduleLinesItem(
                        new FirmOfficeContractAndScheduleLine().categoryOfLaw("categoryOfLaw1")));

    FeeDetailsResponseV2 feeDetailsResponse = new FeeDetailsResponseV2();
    feeDetailsResponse.setCategoryOfLawCodes(List.of("categoryOfLaw1"));

    when(mockProviderClient.getProviderFirmSchedules(eq("officeCache"), any(LocalDate.class)))
        .thenReturn(Mono.just(data));
    when(mockFeeClient.getFeeDetails("feeCode5"))
        .thenReturn(ResponseEntity.ok(feeDetailsResponse));

    ClaimValidationContext context1 = ClaimValidationContext.builder().build();
    realValidator.validate(claim, context1);
    ClaimValidationContext context2 = ClaimValidationContext.builder().build();
    realValidator.validate(claim, context2);

    // underlying REST clients should only be invoked once each due to provider-level caching
    verify(mockProviderClient, times(1)).getProviderFirmSchedules(eq("officeCache"), any(LocalDate.class));
    verify(mockFeeClient, times(1)).getFeeDetails("feeCode5");
  }

  @Test
  @DisplayName("Should handle missing effective date (no date fields) by logging and not calling providers")
  void shouldHandleMissingEffectiveDateWithoutCallingProviders() {
    UUID claimId = new UUID(3, 3);
    Claim claim = Claim.builder()
        .id(claimId)
        .feeCode("feeCode6")
        // no dates set
        .status(ClaimStatus.READY_TO_PROCESS)
        .officeAccountNumber("officeAccountNumber")
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .build();

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    // validator uses mocked httpProviderDetailsProvider and feeSchemeClient from @Mock fields
    validator.validate(claim, context);

    // No provider interactions expected
    verifyNoInteractions(httpProviderDetailsProvider);
    verifyNoInteractions(feeSchemeClient);
  }
}
