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
 * Orchestrates claim validation by delegating to the configured {@link ClaimValidation} pipeline.
 *
 * <p>This service is the primary entry point for claim validation. It is stateless and thread-safe;
 * all mutable state is owned by the underlying validators. Callers provide a {@link Claim} and
 * optionally a scope and a list of related claims; the service returns a {@link ValidationResult}
 * indicating whether the claim is valid and detailing any issues found.
 *
 * <h2>Scopes</h2>
 *
 * <p>The {@code scope} parameter allows callers to run only a subset of validators — for example,
 * running only fee-related rules at submission time and the full rule set at processing time.
 * Passing {@code null} runs all validators that do not filter by scope.
 *
 * <h2>Related claims</h2>
 *
 * <p>The {@code relatedClaims} parameter supplies other claims in the same submission, used by
 * duplicate-detection validators. Pass an empty list (or use the overloads below) when no
 * submission context is available.
 *
 * <h2>Usage</h2>
 *
 * <pre>{@code
 * // Validate with no scope and no related claims:
 * ValidationResult result = validationService.validateClaim(claim);
 *
 * // Validate within a specific scope:
 * ValidationResult result = validationService.validateClaim(claim, "submission");
 *
 * // Validate with related claims for duplicate detection:
 * ValidationResult result = validationService.validateClaim(claim, "submission", submissionClaims);
 * }</pre>
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ValidationService {

  private final ClaimValidation claimValidation;

  /**
   * Validates the given claim using all applicable validators with no scope filter and no related
   * claims context.
   *
   * @param claim the claim to validate; a {@code null} claim produces a {@code MISSING_CLAIM} error
   * @return the validation result containing an {@code isValid} flag and any issues found
   */
  public ValidationResult validateClaim(Claim claim) {
    return validateClaim(claim, null);
  }

  /**
   * Validates the given claim using validators applicable to the specified scope, with no related
   * claims context.
   *
   * @param claim the claim to validate; a {@code null} claim produces a {@code MISSING_CLAIM} error
   * @param scope the validation scope used to filter applicable validators; {@code null} runs all
   *     scope-agnostic validators
   * @return the validation result containing an {@code isValid} flag and any issues found
   */
  public ValidationResult validateClaim(Claim claim, String scope) {
    return validateClaim(claim, scope, Collections.emptyList());
  }

  /**
   * Validates the given claim using validators applicable to the specified scope, providing related
   * claims from the same submission for duplicate-detection validators.
   *
   * @param claim the claim to validate; a {@code null} claim produces a {@code MISSING_CLAIM} error
   * @param scope the validation scope used to filter applicable validators; {@code null} runs all
   *     scope-agnostic validators
   * @param relatedClaims other claims in the same submission, used by duplicate-detection
   *     validators; must not be {@code null} — pass an empty list if no context is available
   * @return the validation result containing an {@code isValid} flag and any issues found
   */
  public ValidationResult validateClaim(Claim claim, String scope, List<Claim> relatedClaims) {
    return claimValidation.validateClaim(claim, scope, relatedClaims);
  }
}
