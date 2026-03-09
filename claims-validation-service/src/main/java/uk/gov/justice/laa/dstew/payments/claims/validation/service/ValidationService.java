package uk.gov.justice.laa.dstew.payments.claims.validation.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimValidationRequest;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.client.ExternalValidationClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ValidationContext;

/**
 * Service for orchestrating claim validation.
 * This is a stateless service that receives claim data, runs all applicable
 * validators, and returns the validation results.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ValidationService {

  private final List<ClaimValidator> validators;
  private final ExternalValidationClient externalValidationClient;

  /**
   * Validates a claim based on the provided request.
   *
   * @param request the claim validation request containing claim data and scope
   * @return the validation result containing isValid flag and any issues
   */
  public ValidationResult validateClaim(ClaimValidationRequest request) {
    Map<String, Object> claim = request.getClaim();
    String scope = request.getScope();

    log.info("Starting validation for claim with scope: {}", scope);

    // Build validation context
    ValidationContext context = buildValidationContext(request);

    // Collect all validation issues
    List<ValidationIssue> issues = new ArrayList<>();

    // Run all validators in priority order
    validators.stream()
        .filter(v -> v.appliesTo(scope))
        .sorted(Comparator.comparingInt(ClaimValidator::priority))
        .forEach(validator -> {
          log.debug("Running validator: {}", validator.getValidatorCode());
          List<ValidationIssue> validatorIssues = validator.validate(claim, context);
          issues.addAll(validatorIssues);
          log.debug("Validator {} found {} issues",
              validator.getValidatorCode(), validatorIssues.size());
        });

    // Run external validation checks
    List<ValidationIssue> externalIssues =
        externalValidationClient.validateWithExternalServices(claim);
    issues.addAll(externalIssues);

    // Determine if claim is valid (no ERROR severity issues)
    boolean isValid = issues.stream()
        .noneMatch(issue -> ValidationIssue.SeverityEnum.ERROR.equals(issue.getSeverity()));

    log.info("Validation completed. isValid: {}, total issues: {}", isValid, issues.size());

    return ValidationResult.builder()
        .isValid(isValid)
        .issues(issues)
        .build();
  }

  /**
   * Builds the validation context from the request.
   * This includes fetching any required reference data from external services.
   *
   * @param request the validation request
   * @return the validation context
   */
  private ValidationContext buildValidationContext(ClaimValidationRequest request) {
    // Extract related claims as List<Map<String, Object>>
    List<Map<String, Object>> relatedClaims = List.of();
    if (request.getRelatedClaims() != null) {
      relatedClaims = request.getRelatedClaims();
    }

    return ValidationContext.builder()
        .scope(request.getScope())
        .areaOfLaw(request.getAreaOfLaw() != null
            ? request.getAreaOfLaw().getValue() : null)
        .officeAccountNumber(request.getOfficeAccountNumber())
        .relatedClaims(relatedClaims)
        .build();
  }
}

