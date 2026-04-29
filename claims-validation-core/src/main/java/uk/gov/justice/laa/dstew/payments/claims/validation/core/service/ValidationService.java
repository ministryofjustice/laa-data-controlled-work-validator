package uk.gov.justice.laa.dstew.payments.claims.validation.core.service;


import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidation;

/**
 * Service for orchestrating claim validation. This is a stateless service that receives claim data,
 * runs all applicable validators, and returns the validation results.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ValidationService {

  private final ClaimValidation claimValidation;

  public ValidationResult validateClaim(Claim claim) {
    return validateClaim(claim, null);
  }

  public ValidationResult validateClaim(Claim claim, String scope) {
    return validateClaim(claim, scope, Collections.emptyList());
  }

  /**
   * Validates a claim based on the provided parameters.
   *
   * @param claim the claim to validate
   * @param scope the validation scope
   * @param relatedClaims optional related claims
   * @return the validation result containing isValid flag and any issues
   */
  public ValidationResult validateClaim(Claim claim, String scope, List<Claim> relatedClaims) {
    return claimValidation.validateClaim(claim, scope, relatedClaims);
  }
}
