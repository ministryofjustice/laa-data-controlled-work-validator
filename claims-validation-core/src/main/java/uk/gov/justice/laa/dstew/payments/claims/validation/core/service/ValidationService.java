package uk.gov.justice.laa.dstew.payments.claims.validation.core.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimValidationRequest;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules.ClaimValidator;

/**
 * Service for orchestrating claim validation. This is a stateless service that receives claim data,
 * runs all applicable validators, and returns the validation results.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ValidationService {

  private final List<ClaimValidator> validators;

  /**
   * Validates a claim based on the provided request.
   *
   * @param request the claim validation request containing claim data and scope
   * @return the validation result containing isValid flag and any issues
   */
  public ValidationResult validateClaim(ClaimValidationRequest request) {
    log.info("Validating claim request with scope: {}", request.getScope());
    Claim claim = request.getClaim();
    String scope = request.getScope();

    log.info("Starting validation for claim with scope: {}", scope);
    log.info("Received claim: {}", claim != null ? claim.toString() : "null");

    // Handle missing claim - return validation error, not 400
    if (claim == null) {
      log.warn("Validation request received with null claim");
      return buildMissingClaimResult();
    }

    // Build validation context
    ValidationContext context = buildValidationContext(request);

    // Collect unique validation issues (LinkedHashSet preserves insertion order)
    Set<ValidationIssue> issues = new LinkedHashSet<>();

    // Run all validators in priority order
    validators.stream()
        .filter(v -> v.appliesTo(scope))
        .sorted(Comparator.comparingInt(ClaimValidator::priority))
        .forEach(
            validator -> {
              log.debug("Running validator: {}", validator.getValidatorCode());
              List<ValidationIssue> validatorIssues = validator.validate(claim, context);
              issues.addAll(validatorIssues);
              log.debug(
                  "Validator {} found {} issues",
                  validator.getValidatorCode(),
                  validatorIssues.size());
            });

    // Determine if claim is valid (no ERROR severity issues)
    boolean isValid =
        issues.stream().noneMatch(issue -> ValidationSeverity.ERROR.equals(issue.getSeverity()));

    log.info("Validation completed. isValid: {}, total issues: {}", isValid, issues.size());

    ValidationResult result = new ValidationResult();
    result.setIsValid(isValid);
    result.setIssues(new ArrayList<>(issues));
    return result;
  }

  /**
   * Builds the validation context from the request.
   *
   * @param request the validation request
   * @return the validation context
   */
  private ValidationContext buildValidationContext(ClaimValidationRequest request) {
    List<Claim> relatedClaims =
        request.getRelatedClaims() != null ? request.getRelatedClaims() : List.of();

    return ValidationContext.builder()
        .scope(request.getScope())
        .relatedClaims(relatedClaims)
        .build();
  }

  /**
   * Builds a validation result for when no claim is provided.
   *
   * @return validation result with MISSING_CLAIM error
   */
  private ValidationResult buildMissingClaimResult() {
    ValidationIssue issue = ClaimValidationError.MISSING_CLAIM.toValidationIssue();

    ValidationResult result = new ValidationResult();
    result.setIsValid(false);
    result.setIssues(List.of(issue));
    return result;
  }
}
