package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.FeeSchemeClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.HttpProviderDetailsProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.ClaimEffectiveDateUtil;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;
import uk.gov.justice.laa.fee.scheme.model.FeeDetailsResponse;
import uk.gov.justice.laadata.providers.model.FirmOfficeContractAndScheduleDetails;
import uk.gov.justice.laadata.providers.model.FirmOfficeContractAndScheduleLine;
import uk.gov.justice.laadata.providers.model.ProviderFirmOfficeContractAndScheduleDto;

/** Validates that a claim's effective category of law is valid. */
@Component
@RequiredArgsConstructor
@Slf4j
public class EffectiveCategoryOfLawClaimValidator implements ClaimValidator {

  private final FeeSchemeClient feeSchemeClient;
  private final HttpProviderDetailsProvider providerDetailsClient;

  @Override
  public int priority() {
    return 1000;
  }

  @Override
  public void validate(Claim claim, ClaimValidationContext context) {

    String officeCode = claim.getOfficeAccountNumber();
    String feeCode = claim.getFeeCode();

    if (feeCode == null || feeCode.isBlank()) {
      return; // MandatoryFieldValidator handles this
    }

    LocalDate effectiveDate = null;
    try {
      effectiveDate = ClaimEffectiveDateUtil.getEffectiveDate(claim);
      List<String> effectiveCategoriesOfLaw =
          getEffectiveCategoriesOfLaw(officeCode, effectiveDate);

      // Get fee details and validate category of law
      validateCategoryOfLaw(claim, feeCode, effectiveCategoriesOfLaw, context);

    } catch (IllegalArgumentException e) {
      log.info(
          "Error getting effective date for category of law validation: {}. "
              + "Continuing with claim validation",
          e.getMessage());
    } catch (WebClientResponseException ex) {
      log.error(
          "Error calling provider details API: Status={}, Message={}, officeCode={}, "
              + "effectiveDate={}, Please check if the API endpoint is "
                  + "configured correctly.",
          ex.getStatusCode(),
          ex.getMessage(),
          officeCode,
          effectiveDate);
      handleProviderDetailsApiError(context, ex);
    } catch (Exception ex) {
      log.error(
          "Unexpected error during category of law validation for officeCode={}, "
              + "effectiveDate={}",
          officeCode,
          effectiveDate,
          ex);
      handleProviderDetailsApiError(context, ex);
    }
  }

  private List<String> getEffectiveCategoriesOfLaw(
      String officeCode, LocalDate effectiveDate) {
    if (officeCode == null || effectiveDate == null) {
      return Collections.emptyList();
    }

    log.debug(
        "Calling Provider Details API: officeCode={}, effectiveDate={}",
        officeCode,
        effectiveDate);

    return providerDetailsClient
        .getProviderFirmSchedules(officeCode, effectiveDate)
        .blockOptional()
        .map(this::extractCategoriesFromSchedules)
        .orElse(Collections.emptyList());
  }

  private List<String> extractCategoriesFromSchedules(
      ProviderFirmOfficeContractAndScheduleDto schedulesDto) {
    if (schedulesDto == null || schedulesDto.getSchedules() == null) {
      return Collections.emptyList();
    }
    return schedulesDto.getSchedules().stream()
        .map(FirmOfficeContractAndScheduleDetails::getScheduleLines)
        .flatMap(List::stream)
        .map(FirmOfficeContractAndScheduleLine::getCategoryOfLaw)
        .distinct()
        .toList();
  }

  private void validateCategoryOfLaw(
      Claim claim,
      String feeCode,
      List<String> providerCategoriesOfLaw,
      ClaimValidationContext context) {

    log.debug("Validating category of law for claim {}", claim.getId());

    ResponseEntity<FeeDetailsResponse> response = feeSchemeClient.getFeeDetails(feeCode);

    if (response == null || response.getBody() == null) {
      // Fee details not found - this is an error
      context.addValidationIssue(
          ClaimValidationError.INVALID_CATEGORY_OF_LAW_AND_FEE_CODE.toValidationIssue(feeCode));
      return;
    }

    FeeDetailsResponse feeDetails = response.getBody();
    String categoryOfLaw = feeDetails.getCategoryOfLawCode();

    if (categoryOfLaw == null) {
      context.addValidationIssue(
          ClaimValidationError.INVALID_CATEGORY_OF_LAW_AND_FEE_CODE.toValidationIssue(feeCode));
    } else if (!providerCategoriesOfLaw.contains(categoryOfLaw)) {
      log.info(
          "Provider is not contracted for category of law {} associated with fee code {}. "
              + "Provider categories: {}",
          categoryOfLaw,
          feeCode,
          providerCategoriesOfLaw);
      context.addValidationIssue(
          ClaimValidationError.INVALID_CATEGORY_OF_LAW_NOT_AUTHORISED_FOR_PROVIDER
              .toValidationIssue());
    }

    log.debug("Category of law validation completed for claim {}", claim.getId());
  }

  private void handleProviderDetailsApiError(ClaimValidationContext context, Exception ex) {
    context.addValidationIssue(
        ClaimValidationError.TECHNICAL_ERROR_PROVIDER_DETAILS_API
            .toValidationIssueWithTechnicalMessage(ex.getMessage()));
  }

  @Override
  public boolean appliesTo(String scope) {
    // Only run for fee scope or all scopes
    return scope == null || "fee".equalsIgnoreCase(scope) || "all".equalsIgnoreCase(scope);
  }

  @Override
  public String getValidatorCode() {
    return "CLAIM_CATEGORY_OF_LAW";
  }
}
