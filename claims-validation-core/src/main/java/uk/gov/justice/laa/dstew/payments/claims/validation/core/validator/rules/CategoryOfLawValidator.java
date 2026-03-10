package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.FeeSchemeClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.FeeSchemeClient.FeeDetailsResponse;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;

/**
 * Validator for category of law based on fee code. Checks that the fee code is valid and the
 * provider is authorized for the associated category of law.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class CategoryOfLawValidator implements ClaimValidator {

  private final FeeSchemeClient feeSchemeClient;

  @Override
  public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    String feeCode = claim.getFeeCode();
    if (feeCode == null || feeCode.isBlank()) {
      return issues; // MandatoryFieldValidator handles this
    }

    log.debug("Validating category of law for fee code: {}", feeCode);

    try {
      // Look up fee details from Fee Scheme Platform
      Optional<FeeDetailsResponse> feeDetails = feeSchemeClient.getFeeDetails(feeCode);

      if (feeDetails.isEmpty()) {
        issues.add(
            ClaimValidationError.INVALID_CATEGORY_OF_LAW_AND_FEE_CODE.toValidationIssue(feeCode));
        return issues;
      }

      // Check provider authorization for category of law
      String categoryOfLaw = feeDetails.get().categoryOfLaw();
      String officeAccountNumber = claim.getOfficeAccountNumber();

      if (officeAccountNumber != null && categoryOfLaw != null) {
        boolean authorized =
            feeSchemeClient.isProviderAuthorizedForCategoryOfLaw(
                officeAccountNumber, categoryOfLaw);

        if (!authorized) {
          issues.add(
              ClaimValidationError.INVALID_CATEGORY_OF_LAW_NOT_AUTHORISED_FOR_PROVIDER
                  .toValidationIssue());
        }
      }

    } catch (FeeSchemeClient.FeeSchemeClientException e) {
      log.error("Fee scheme service error for fee code: {}", feeCode, e);
      issues.add(ClaimValidationError.TECHNICAL_ERROR_FEE_CALCULATION_SERVICE.toValidationIssue());
    }

    return issues;
  }

  @Override
  public int priority() {
    return 70; // Run after basic field validations
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
