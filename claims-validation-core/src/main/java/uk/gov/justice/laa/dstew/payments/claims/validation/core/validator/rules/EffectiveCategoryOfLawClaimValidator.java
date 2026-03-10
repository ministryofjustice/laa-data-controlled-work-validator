package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.FeeSchemeClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.FeeSchemeClient.FeeDetailsResponse;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.ProviderDetailsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.ClaimEffectiveDateUtil;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;

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
  public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
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
          effectiveDate,
          ex);
      handleProviderDetailsApiError(issues);
    } catch (Exception ex) {
      log.error(
          "Unexpected error during category of law validation for officeCode={}, "
              + "areaOfLaw={}, effectiveDate={}",
          officeCode,
          areaOfLaw != null ? areaOfLaw.getValue() : null,
          effectiveDate,
          ex);
      handleProviderDetailsApiError(issues);
    }

    return issues;
  }

  private List<String> getEffectiveCategoriesOfLaw(
      String officeCode, AreaOfLaw areaOfLaw, LocalDate effectiveDate) {
    if (officeCode == null || areaOfLaw == null || effectiveDate == null) {
      return Collections.emptyList();
    }
    return providerDetailsClient.getEffectiveCategoriesOfLaw(
        officeCode, areaOfLaw.getValue(), effectiveDate);
  }

  private void validateCategoryOfLaw(
      Claim claim,
      String feeCode,
      List<String> providerCategoriesOfLaw,
      List<ValidationIssue> issues) {

    log.debug("Validating category of law for claim {}", claim.getId());

    Optional<FeeDetailsResponse> feeDetailsOpt = feeSchemeClient.getFeeDetails(feeCode);

    if (feeDetailsOpt.isEmpty()) {
      // Fee details not found - this is an error
      issues.add(
          new ValidationIssue(
              "INVALID_CATEGORY_OF_LAW_AND_FEE_CODE",
              String.format(
                  "A category of law could not be found for the provided fee code: %s", feeCode),
              ValidationSeverity.ERROR));
      return;
    }

    FeeDetailsResponse feeDetails = feeDetailsOpt.get();
    String categoryOfLaw = feeDetails.categoryOfLaw();

    if (categoryOfLaw == null) {
      issues.add(
          new ValidationIssue(
              "INVALID_CATEGORY_OF_LAW_AND_FEE_CODE",
              String.format(
                  "A category of law could not be found for the provided fee code: %s", feeCode),
              ValidationSeverity.ERROR));
    } else if (!providerCategoriesOfLaw.contains(categoryOfLaw)) {
      issues.add(
          new ValidationIssue(
              "INVALID_CATEGORY_OF_LAW_NOT_AUTHORISED_FOR_PROVIDER",
              "The provider is not contracted for the category of law associated with the fee code",
              ValidationSeverity.ERROR));
    }

    log.debug("Category of law validation completed for claim {}", claim.getId());
  }

  private void handleProviderDetailsApiError(List<ValidationIssue> issues) {
    issues.add(
        new ValidationIssue(
            "TECHNICAL_ERROR_PROVIDER_DETAILS_API",
            "A technical error occurred, please try again after some time",
            ValidationSeverity.ERROR));
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
