package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.HttpFeeSchemeProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.HttpProviderDetailsProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.ClaimEffectiveDateUtil;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;
import uk.gov.justice.laa.fee.scheme.model.FeeDetailsResponseV2;
import uk.gov.justice.laadata.providers.model.FirmOfficeContractAndScheduleDetails;
import uk.gov.justice.laadata.providers.model.FirmOfficeContractAndScheduleLine;
import uk.gov.justice.laadata.providers.model.ProviderFirmOfficeContractAndScheduleDto;

/**
 * Validates that a claim's effective category of law is valid.
 */
@RequiredArgsConstructor
@Slf4j
public class EffectiveCategoryOfLawClaimValidator implements ClaimValidator {

  private final HttpFeeSchemeProvider httpFeeSchemeProvider;
  private final HttpProviderDetailsProvider httpProviderDetailsProvider;

  /**
   * Holds the result of a safe provider-details API fetch: the extracted categories of law (empty
   * list when the provider returned no schedules) and a flag indicating whether a technical API
   * error occurred (as opposed to a legitimate empty response).
   */
  private record ProviderSchedulesResult(List<String> categoriesOfLaw, boolean apiError) {}

  /**
   * Holds the result of a safe fee-scheme API fetch: the optional fee details and a flag indicating
   * whether a technical API error occurred.
   */
  private record FeeDetailsResult(Optional<FeeDetailsResponseV2> details, boolean apiError) {}

  @Override
  public int priority() {
    return 1000;
  }

  /**
   * Validates the effective category of law for the given claim.
   *
   * <p>Validation steps:
   * <ol>
   *   <li>Skip if no fee code is present (handled by {@code MandatoryFieldValidator}).</li>
   *   <li>Resolve the effective date from the claim; skip if it cannot be determined.</li>
   *   <li>Fetch the categories of law authorised for the provider office on the effective date
   *       via {@link HttpProviderDetailsProvider}; return early with a technical error issue on
   *       API failure.</li>
   *   <li>Delegate to {@link #validateCategoriesOfLaw} to check the claim fee code against the
   *       fee scheme and provider categories.</li>
   * </ol>
   *
   * @param claim   the claim to validate (must not be {@code null})
   * @param context the mutable validation context that collects issues
   */
  @Override
  public void validate(Claim claim, ClaimValidationContext context) {
    String officeCode = claim.getOfficeAccountNumber();
    String feeCode = claim.getFeeCode();

    if (feeCode == null || feeCode.isBlank()) {
      return; // MandatoryFieldValidator handles this
    }

    LocalDate effectiveDate;
    try {
      effectiveDate = ClaimEffectiveDateUtil.getEffectiveDate(claim);
    } catch (IllegalArgumentException e) {
      log.info(
          "Error getting effective date for category of law validation: {}. "
              + "Continuing with claim validation",
          e.getMessage());
      return;
    }

    ProviderSchedulesResult schedulesRes =
        fetchProviderSchedulesSafely(officeCode, effectiveDate, context);
    if (schedulesRes.apiError()) {
      return;
    }

    validateCategoriesOfLaw(claim, schedulesRes.categoriesOfLaw(), context);
  }

  /**
   * Validate the claim using provider categories of law and fee details.
   *
   * <p>Protected (rather than private) to allow targeted unit testing without going through the
   * full {@link #validate(Claim, ClaimValidationContext)} entry point.
   *
   * @param claim claim to validate
   * @param providerCategoriesOfLaw categories of law available to the provider for the effective
   *     date
   * @param context validation context to record issues
   */
  protected void validateCategoriesOfLaw(
      Claim claim, List<String> providerCategoriesOfLaw, ClaimValidationContext context) {

    log.debug("Validating categories of law for claim {}", claim.getId());
    FeeDetailsResult feeRes = fetchFeeDetailsSafely(claim.getFeeCode(), context);

    // Technical API error — issue already added to context by the helper; nothing more to do.
    if (feeRes.apiError()) {
      return;
    }

    // Legitimate empty response — fee code not found in the fee scheme.
    if (feeRes.details().isEmpty()) {
      log.error("Get fee details returned empty response for fee code: {}", claim.getFeeCode());
      context.addValidationIssue(
          ClaimValidationError.INVALID_CATEGORY_OF_LAW_AND_FEE_CODE.toValidationIssue(
              claim.getFeeCode()));
      return;
    }

    FeeDetailsResponseV2 feeDetails = feeRes.details().get();
    List<String> feeCategoryCodes =
        Optional.ofNullable(feeDetails.getCategoryOfLawCodes()).orElse(Collections.emptyList());
    validateProviderCategoriesOfLaw(claim, providerCategoriesOfLaw, feeCategoryCodes, context);
    log.debug("Categories of law validation completed for claim {}", claim.getId());
  }

  /**
   * Calls the Provider Details API and extracts categories of law from the returned schedules.
   *
   * <p>On success returns a {@link ProviderSchedulesResult} with {@code apiError = false} and the
   * extracted category-of-law codes (empty list when the provider returned no schedules for the
   * office and effective date). On any exception, adds a
   * {@link ClaimValidationError#TECHNICAL_ERROR_PROVIDER_DETAILS_API} issue to the context and
   * returns a result with {@code apiError = true} so the caller can short-circuit without
   * propagating the exception.
   *
   * @param officeCode    the office account number used to query the provider details API
   * @param effectiveDate the date used to filter provider schedules
   * @param context       the validation context to record technical error issues
   * @return a {@link ProviderSchedulesResult} containing the category codes and an error flag
   */
  private ProviderSchedulesResult fetchProviderSchedulesSafely(
      String officeCode, LocalDate effectiveDate, ClaimValidationContext context) {
    try {
      List<String> categories =
          httpProviderDetailsProvider
              .getProviderFirmSchedules(officeCode, effectiveDate)
              .map(this::extractCategoriesFromSchedules)
              .orElse(Collections.emptyList());
      return new ProviderSchedulesResult(categories, false);
    } catch (Exception ex) {
      if (ex instanceof WebClientResponseException wcre) {
        log.error(
            "Error calling provider details API for office {}: Status={}, Message={}",
            officeCode, wcre.getStatusCode(), wcre.getMessage(), wcre);
      } else {
        log.error(
            "Unexpected error calling provider details API for office {}: {}",
            officeCode, ex.getMessage(), ex);
      }
      context.addValidationIssue(
          ClaimValidationError.TECHNICAL_ERROR_PROVIDER_DETAILS_API
              .toValidationIssueWithTechnicalMessage(ex.getMessage()));
      return new ProviderSchedulesResult(Collections.emptyList(), true);
    }
  }

  /**
   * Calls the Fee Scheme API and returns the optional fee details.
   *
   * <p>On success returns a {@link FeeDetailsResult} with {@code apiError = false} and the
   * (possibly empty) fee details. An empty {@link Optional} indicates the fee code was not found
   * (negative cache / 404). On any exception, adds a
   * {@link ClaimValidationError#TECHNICAL_ERROR_FEE_SCHEME_API} issue to the context and returns a
   * result with {@code apiError = true} so the caller can distinguish a technical failure from a
   * legitimate not-found response.
   *
   * @param feeCode the fee code to look up in the fee scheme API
   * @param context the validation context to record technical error issues
   * @return a {@link FeeDetailsResult} containing the optional fee details and an error flag
   */
  private FeeDetailsResult fetchFeeDetailsSafely(String feeCode, ClaimValidationContext context) {
    try {
      Optional<FeeDetailsResponseV2> opt = httpFeeSchemeProvider.getFeeDetails(feeCode);
      return new FeeDetailsResult(opt, false);
    } catch (Exception ex) {
      if (ex instanceof WebClientResponseException wcre) {
        log.error(
            "Error calling fee scheme API for fee code {}: Status={}, Message={}",
            feeCode, wcre.getStatusCode(), wcre.getMessage(), wcre);
      } else {
        log.error(
            "Unexpected error calling fee scheme API for fee code {}: {}",
            feeCode, ex.getMessage(), ex);
      }
      context.addValidationIssue(
          ClaimValidationError.TECHNICAL_ERROR_FEE_SCHEME_API
              .toValidationIssueWithTechnicalMessage(ex.getMessage()));
      return new FeeDetailsResult(Optional.empty(), true);
    }
  }

  /**
   * Validates that the claim's fee code maps to a category of law that is both recognised by the
   * fee scheme and authorised for the provider office.
   *
   * <p>Adds one of the following issues to the context if validation fails:
   * <ul>
   *   <li>{@link ClaimValidationError#INVALID_CATEGORY_OF_LAW_AND_FEE_CODE} — fee code not found
   *       in the fee scheme or the fee scheme returned no category codes.</li>
   *   <li>{@link ClaimValidationError#INVALID_CATEGORY_OF_LAW_NOT_AUTHORISED_FOR_PROVIDER} — the
   *       fee scheme categories do not intersect with the categories authorised for the
   *       provider.</li>
   * </ul>
   * On success, sets the authorised category of law code on the context via
   * {@link ClaimValidationContext#setAuthorisedCategoryOfLawCode(String)}.
   *
   * @param claim                  the claim whose fee code is being checked
   * @param providerCategoriesOfLaw the categories of law authorised for the provider office on the
   *                               effective date
   * @param categoryOfLawCodes     the category codes returned by the fee scheme for the fee code
   * @param context                the validation context to record issues or the authorised code
   */
  private void validateProviderCategoriesOfLaw(
      Claim claim,
      List<String> providerCategoriesOfLaw,
      List<String> categoryOfLawCodes,
      ClaimValidationContext context) {

    if (CollectionUtils.isEmpty(categoryOfLawCodes)) {
      context.addValidationIssue(
          ClaimValidationError.INVALID_CATEGORY_OF_LAW_AND_FEE_CODE.toValidationIssue(
              claim.getFeeCode()));
      return;
    }

    Optional<String> authorisedCategoryOfLaw =
        categoryOfLawCodes.stream().filter(providerCategoriesOfLaw::contains).findFirst();
    if (authorisedCategoryOfLaw.isEmpty()) {
      context.addValidationIssue(
          ClaimValidationError.INVALID_CATEGORY_OF_LAW_NOT_AUTHORISED_FOR_PROVIDER
              .toValidationIssue());
    } else {
      context.setAuthorisedCategoryOfLawCode(authorisedCategoryOfLaw.get());
    }
  }

  /**
   * Extracts the category-of-law codes from the provider schedules DTO.
   *
   * <p>Returns an empty list when the DTO is {@code null}, when it has no schedules, or when
   * individual schedules or schedule lines are {@code null} or missing a category code. Null
   * elements are filtered defensively at each level of the hierarchy.
   *
   * @param schedulesDto the provider firm office contract and schedule data (may be {@code null})
   * @return a non-null, immutable list of category-of-law codes extracted from all schedule lines
   */
  private List<String> extractCategoriesFromSchedules(
      ProviderFirmOfficeContractAndScheduleDto schedulesDto) {
    if (schedulesDto == null || schedulesDto.getSchedules() == null) {
      return Collections.emptyList();
    }
    return schedulesDto.getSchedules().stream()
        .filter(Objects::nonNull)
        .map(FirmOfficeContractAndScheduleDetails::getScheduleLines)
        .filter(Objects::nonNull)
        .flatMap(List::stream)
        .filter(Objects::nonNull)
        .map(FirmOfficeContractAndScheduleLine::getCategoryOfLaw)
        .filter(Objects::nonNull)
        .toList();
  }

  /**
   * Returns the unique code identifying this validator.
   *
   * @return {@code "CLAIM_CATEGORY_OF_LAW"}
   */
  @Override
  public String getValidatorCode() {
    return "CLAIM_CATEGORY_OF_LAW";
  }
}
