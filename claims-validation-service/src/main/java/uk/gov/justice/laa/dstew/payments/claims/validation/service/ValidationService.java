package uk.gov.justice.laa.dstew.payments.claims.validation.service;

import java.util.ArrayList;
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

/**
 * Service for orchestrating claim validation.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ValidationService {

  private final ClaimValidator claimValidator;
  private final ExternalValidationClient externalValidationClient;

  /**
   * Validates a claim based on the provided request.
   *
   * @param request the claim validation request
   * @return the validation result containing isValid flag and any issues
   */
  public ValidationResult validateClaim(ClaimValidationRequest request) {
    Map<String, Object> claim = request.getClaim();
    String scope = request.getScope();

    log.debug("Starting validation for claim with scope: {}", scope);

    List<ValidationIssue> issues = new ArrayList<>();

    // TODO: Apply business rules based on scope
    // Run local validation rules
    List<ValidationIssue> localIssues = claimValidator.validate(claim, scope);
    issues.addAll(localIssues);

    // TODO: Implement external validation calls
    // Run external validation checks (placeholder)
    List<ValidationIssue> externalIssues =
        externalValidationClient.validateWithExternalServices(claim);
    issues.addAll(externalIssues);

    boolean isValid = issues.stream()
        .noneMatch(issue -> ValidationIssue.SeverityEnum.ERROR.equals(issue.getSeverity()));

    return ValidationResult.builder()
        .isValid(isValid)
        .issues(issues)
        .build();
  }
}

