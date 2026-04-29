package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.FeeSchemeClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.ProviderDetailsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.ClaimEffectiveDateUtil;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
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
  private final ProviderDetailsClient providerDetailsClient;

  @Override
  public int priority() {
    return 1000;
  }

  @Override
  public List<ValidationIssue> validate(Claim claim, ClaimValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    AreaOfLaw areaOfLaw = claim.getAreaOfLaw();
    String officeCode = claim.getOfficeAccountNumber();
    String feeCode = claim.getFeeCode();

    if (feeCode == null || feeCode.isBlank()) {
      return issues; // MandatoryFieldValidator handles this
    }

    LocalDate effectiveDate = null;
    try {
      effectiveDate = ClaimEffectiveDateUtil.getEffectiveDate(claim);
      List<String> effectiveCategoriesOfLaw =
          getEffectiveCategoriesOfLaw(officeCode, areaOfLaw, effectiveDate);

      // Get fee details and validate category of law
      validateCategoryOfLaw(claim, feeCode, effectiveCategoriesOfLaw, issues);

    } catch (IllegalArgumentException e) {
      log.info(
          "Error getting effective date for category of law validation: {}. "
              + "Continuing with claim validation",
          e.getMessage());
    } catch (WebClientResponseException ex) {
      log.error(
          "Error calling provider details API: Status={}, Message={}, officeCode={}, "
              + "areaOfLaw={}, effectiveDate={}, "
              + "Please check if the API endpoint is configured correctly.",
          ex.getStatusCode(),
          ex.getMessage(),
          officeCode,
          areaOfLaw != null ? areaOfLaw.getValue() : null,
          effectiveDate);
      handleProviderDetailsApiError(issues, ex);
    } catch (Exception ex) {
      log.error(
          "Unexpected error during category of law validation for officeCode={}, "
              + "areaOfLaw={}, effectiveDate={}",
          officeCode,
          areaOfLaw != null ? areaOfLaw.getValue() : null,
          effectiveDate,
          ex);
      handleProviderDetailsApiError(issues, ex);
    }

    return issues;
  }

  private List<String> getEffectiveCategoriesOfLaw(
      String officeCode, AreaOfLaw areaOfLaw, LocalDate effectiveDate) {
    if (officeCode == null || areaOfLaw == null || effectiveDate == null) {
      return Collections.emptyList();
    }

    log.debug(
        "Calling Provider Details API: officeCode={}, areaOfLaw={}, effectiveDate={}",
        officeCode,
        areaOfLaw.getValue(),
        effectiveDate);

    return providerDetailsClient
        .getProviderFirmSchedules(officeCode, areaOfLaw.getValue(), effectiveDate)
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
      List<ValidationIssue> issues) {

    log.debug("Validating category of law for claim {}", claim.getId());

    ResponseEntity<FeeDetailsResponse> response = feeSchemeClient.getFeeDetails(feeCode);

    if (response == null || response.getBody() == null) {
      // Fee details not found - this is an error
      issues.add(
          ClaimValidationError.INVALID_CATEGORY_OF_LAW_AND_FEE_CODE.toValidationIssue(feeCode));
      return;
    }

    FeeDetailsResponse feeDetails = response.getBody();
    String categoryOfLaw = feeDetails.getCategoryOfLawCode();

    if (categoryOfLaw == null) {
      issues.add(
          ClaimValidationError.INVALID_CATEGORY_OF_LAW_AND_FEE_CODE.toValidationIssue(feeCode));
    } else if (!providerCategoriesOfLaw.contains(categoryOfLaw)) {
      log.info(
          "Provider is not contracted for category of law {} associated with fee code {}. "
              + "Provider categories: {}",
          categoryOfLaw,
          feeCode,
          providerCategoriesOfLaw);
      issues.add(
          ClaimValidationError.INVALID_CATEGORY_OF_LAW_NOT_AUTHORISED_FOR_PROVIDER
              .toValidationIssue());
    }

    log.debug("Category of law validation completed for claim {}", claim.getId());
  }

  private void handleProviderDetailsApiError(List<ValidationIssue> issues, Exception ex) {
    issues.add(
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
    return "CATEGORY_OF_LAW";
  }
}
