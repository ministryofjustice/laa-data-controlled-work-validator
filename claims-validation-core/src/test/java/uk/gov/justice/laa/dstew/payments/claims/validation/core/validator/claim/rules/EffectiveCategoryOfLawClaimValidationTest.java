package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

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
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;
import uk.gov.justice.laa.fee.scheme.model.FeeDetailsResponse;
import uk.gov.justice.laadata.providers.model.FirmOfficeContractAndScheduleDetails;
import uk.gov.justice.laadata.providers.model.FirmOfficeContractAndScheduleLine;
import uk.gov.justice.laadata.providers.model.ProviderFirmOfficeContractAndScheduleDto;

@ExtendWith(MockitoExtension.class)
@DisplayName("Effective category of law claim validator test")
class EffectiveCategoryOfLawClaimValidationTest {

  EffectiveCategoryOfLawClaimValidator validator;

  @Mock FeeSchemeClient feeSchemeClient;
  @Mock ProviderDetailsClient providerDetailsClient;

  @BeforeEach
  void beforeEach() {
    validator = new EffectiveCategoryOfLawClaimValidator(feeSchemeClient, providerDetailsClient);
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
    when(providerDetailsClient.getProviderFirmSchedules(
            eq("officeAccountNumber"), eq(AreaOfLaw.LEGAL_HELP.getValue()), any(LocalDate.class)))
        .thenReturn(Mono.just(data));
    FeeDetailsResponse feeDetailsResponse = new FeeDetailsResponse();
    feeDetailsResponse.setCategoryOfLawCode("categoryOfLaw1");
    when(feeSchemeClient.getFeeDetails("feeCode1"))
        .thenReturn(ResponseEntity.ok(feeDetailsResponse));
    ClaimValidationContext context = ClaimValidationContext.builder().build();
    List<?> issues = validator.validate(claim, context);
    assertThat(issues).isEmpty();
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
    when(providerDetailsClient.getProviderFirmSchedules(
            eq("officeAccountNumber"), eq(AreaOfLaw.LEGAL_HELP.getValue()), any(LocalDate.class)))
        .thenReturn(Mono.error(exception));
    ClaimValidationContext context = ClaimValidationContext.builder().build();
    List<?> issues = validator.validate(claim, context);
    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).toString())
        .contains(ClaimValidationError.TECHNICAL_ERROR_PROVIDER_DETAILS_API.name());
  }
}
