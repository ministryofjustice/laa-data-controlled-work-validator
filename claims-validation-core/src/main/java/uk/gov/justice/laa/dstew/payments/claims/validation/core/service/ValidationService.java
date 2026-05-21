package uk.gov.justice.laa.dstew.payments.claims.validation.core.service;


import java.util.Collections;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidation;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidation;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;

/**
 * Primary entry point for both claim and submission validation.
 *
 * <p>Delegates to the configured {@link ClaimValidation} and {@link SubmissionValidation}
 * pipelines respectively. The service itself is stateless and thread-safe; all mutable state
 * is owned by the underlying validators.
 *
 * <h2>Claim validation</h2>
 *
 * <p>Callers provide a {@link Claim} and optionally a scope and a list of related claims. The
 * service returns a {@link ValidationResult} indicating whether the claim is valid and detailing
 * any issues found.
 *
 * <h2>Submission validation</h2>
 *
 * <p>Callers provide a {@link SubmissionResponse} and optionally a scope. The service returns a
 * {@link ValidationResult} indicating whether the submission is valid.
 *
 * <h2>Scopes</h2>
 *
 * <p>The {@code scope} parameter on both entry points allows callers to run only a subset of
 * validators — for example, running only fee-related rules at submission time and the full rule
 * set at processing time. Passing {@code null} runs all scope-agnostic validators.
 *
 * <h2>Usage</h2>
 *
 * <pre>{@code
 * // Validate a claim with no scope and no related claims:
 * ValidationResult result = validationService.validateClaim(claim);
 *
 * // Validate a claim within a specific scope with related claims for duplicate detection:
 * ValidationResult result = validationService.validateClaim(claim, "submission", submissionClaims);
 *
 * // Validate a submission with no scope filter:
 * ValidationResult result = validationService.validateSubmission(submission);
 *
 * // Validate a submission within a specific scope:
 * ValidationResult result = validationService.validateSubmission(submission, "pre-process");
 * }</pre>
 */
@RequiredArgsConstructor
@Slf4j
public class ValidationService {

  private final ClaimValidation claimValidation;
  private final SubmissionValidation submissionValidation;

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
  public ValidationResult validateClaim(Claim claim, Set<String> scope) {
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
  public ValidationResult validateClaim(Claim claim, Set<String> scope, List<Claim> relatedClaims) {
    return claimValidation.validateClaim(claim, scope, relatedClaims);
  }

  /**
   * Validates the given submission using all applicable validators with no scope filter.
   *
   * @param submission the submission to validate; must not be {@code null}
   * @return the validation result containing an {@code isValid} flag and any issues found
   */
  public ValidationResult validateSubmission(SubmissionResponse submission) {
    return submissionValidation.validateSubmission(submission, null);
  }

  /**
   * Validates the given submission using validators applicable to the specified scope.
   *
   * @param submission the submission to validate; must not be {@code null}
   * @param scope the validation scope used to filter applicable validators; {@code null} runs all
   *     scope-agnostic validators
   * @return the validation result containing an {@code isValid} flag and any issues found
   */
  public ValidationResult validateSubmission(SubmissionResponse submission, Set<String> scope) {
    return submissionValidation.validateSubmission(submission, scope);
  }
}
