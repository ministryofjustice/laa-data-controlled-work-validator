package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.ClaimValidator;

/**
 * Orchestrates the execution of a collection of {@link ClaimValidator}s against a single
 * {@link Claim}.
 *
 * <p>This class is responsible for:
 * <ul>
 *   <li>building the {@link ClaimValidationContext} used by individual validators,</li>
 *   <li>executing validators that apply to a given {@code scope} in priority order,</li>
 *   <li>collecting and de-duplicating validation issues while preserving insertion order, and</li>
 *   <li>producing a {@link ValidationResult}
 *       summarising the outcome.</li>
 * </ul>
 *
 * <p>Instances of this class are immutable and thread-safe provided the supplied {@code validators}
 * list is not modified after construction. The class itself does not retain any mutable state
 * between invocations of {@link #validateClaim}.
 */
@RequiredArgsConstructor
@Slf4j
public class ClaimValidation {

  /**
   * The set of validators that will be executed for each validation request. The list is iterated
   * in priority order after filtering by scope; duplicates in reported issues are removed while
   * preserving insertion order.
   */
  private final List<ClaimValidator> validators;

  /**
   * Validate the supplied {@link Claim} using the configured set of {@link ClaimValidator}s.
   *
   * <p>The method will:
   * <ol>
   *   <li>return a {@link ValidationResult} containing a {@code MISSING_CLAIM} issue if the
   *       {@code claim} parameter is {@code null},</li>
   *   <li>build a {@link ClaimValidationContext} containing the provided {@code scope} and
   *       {@code relatedClaims},</li>
   *   <li>execute all validators that {@link ClaimValidator#appliesTo(String) apply} for the
   *       {@code scope} in priority order, collecting and de-duplicating issues while preserving
   *       insertion order, and</li>
   *   <li>return a {@link ValidationResult} that is considered valid when no issue with
   *       {@link ValidationSeverity#ERROR}
   *       severity is present.</li>
   * </ol>
   *
   * @param claim the claim to validate; may be {@code null}
   * @param scope an optional validation scope used to filter applicable validators
   * @param relatedClaims optional related claims; if {@code null} it will be treated as an
   *     empty list
   * @return a {@link ValidationResult} describing whether the claim is valid and any
   *     {@link ValidationIssue}s discovered
   */
  public ValidationResult validateClaim(Claim claim, String scope, List<Claim> relatedClaims) {
    log.info("Validating claim with scope: {}", scope);

    // Handle missing claim - return validation error, not 400
    if (claim == null) {
      log.warn("Validation request received with null claim");
      return buildMissingClaimResult();
    }

    // Build validation context
    ClaimValidationContext context = buildValidationContext(scope, relatedClaims);

    // Collect unique validation issues (LinkedHashSet preserves insertion order)
    Set<ValidationIssue> issues = new LinkedHashSet<>();

    // Run all validators in priority order
    validators.stream()
            .filter(v -> v.appliesTo(scope))
            .sorted(Comparator.comparingInt(ClaimValidator::priority))
            .forEach(
                    validator -> {
                      log.debug("Running validator: {}", validator.getValidatorCode());
                      validator.validate(claim, context);
                      issues.addAll(context.getIssues());
                      log.debug(
                              "Validator {} found {} issues",
                              validator.getValidatorCode(),
                              context.getIssues().size());
                    });

    // Determine if claim is valid (no ERROR severity issues)
    boolean isValid =
            issues.stream()
                    .noneMatch(issue -> ValidationSeverity.ERROR.equals(issue.getSeverity()));

    log.info("Validation completed. isValid: {}, total issues: {}", isValid, issues.size());

    ValidationResult result = new ValidationResult();
    result.setIsValid(isValid);
    result.setIssues(new ArrayList<>(issues));
    return result;
  }

  /**
   * Builds the validation context from scope and related claims.
   *
   * @param scope the validation scope
   * @param relatedClaims optional related claims
   * @return the validation context
   */
  private ClaimValidationContext buildValidationContext(String scope, List<Claim> relatedClaims) {
    List<Claim> related = relatedClaims != null ? relatedClaims : List.of();

    return ClaimValidationContext.builder().scope(scope).relatedClaims(related).build();
  }

  /**
   * Builds a validation result for when no claim is provided.
   *
   * @return validation result with MISSING_CLAIM error
   */
  private ValidationResult buildMissingClaimResult() {
    ValidationResult result = new ValidationResult();
    result.setIsValid(false);
    result.setIssues(List.of(ClaimValidationError.MISSING_CLAIM.toValidationIssue()));
    return result;
  }

}
