package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
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
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.HttpFeeSchemeProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.HttpProviderDetailsProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;
import uk.gov.justice.laa.fee.scheme.model.FeeDetailsResponseV1;
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
    FeeDetailsResponseV1 feeDetailsResponse = new FeeDetailsResponseV1();
    feeDetailsResponse.setCategoryOfLawCode("categoryOfLaw1");
    when(feeSchemeClient.getFeeDetails("feeCode1"))
        .thenReturn(Mono.just(feeDetailsResponse));
    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);
    assertThat(context.getIssues()).isEmpty();
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
}
