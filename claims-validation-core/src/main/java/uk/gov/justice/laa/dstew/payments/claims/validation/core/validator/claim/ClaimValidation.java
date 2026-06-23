package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.FeeSchemeProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.ClaimValidator;
import uk.gov.justice.laa.fee.scheme.model.FeeDetailsResponseV2;

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
  private final FeeSchemeProvider feeSchemeProvider;

  /**
   * Validate the supplied {@link Claim} using the configured set of {@link ClaimValidator}s.
   *
   * <p>The method will:
   * <ol>
   *   <li>return a {@link ValidationResult} containing a {@code MISSING_CLAIM} issue if the
   *       {@code claim} parameter is {@code null},</li>
   *   <li>build a {@link ClaimValidationContext} containing the provided {@code scope} and
   *       {@code relatedClaims},</li>
   *   <li>execute all validators that {@link ClaimValidator#appliesTo apply} for the
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
  public ValidationResult validateClaim(Claim claim, Set<String> scope, List<Claim> relatedClaims) {

    // Handle missing claim - return validation error, not 400
    if (claim == null) {
      log.warn("Validation request received with a null claim");
      return buildMissingClaimResult();
    }

    log.info("Validating claim [{}] with scope: {}", claim.getId(), scope);

    // Build validation context
    ClaimValidationContext context = buildValidationContext(claim, scope, relatedClaims);

    // Run all validators in priority order, using the context as the canonical store for
    // validation issues. We capture the context size before/after each validator so we can
    // log how many NEW issues that validator contributed (avoids repeated aggregation).
    validators.stream()
            .filter(v -> v.appliesTo(scope))
            .sorted(Comparator.comparingInt(ClaimValidator::priority))
            .forEach(
                    validator -> {
                      log.debug("Running validator: {}", validator.getValidatorCode());
                      boolean debug = log.isDebugEnabled();
                      int before = debug ? context.getIssueCount() : -1;
                      validator.validate(claim, context);
                      if (debug) {
                        int after = context.getIssueCount();
                        log.debug(
                            "Validator {} added {} new issues",
                            validator.getValidatorCode(),
                            Math.max(0, after - before));
                      }
                    });

    // Determine if claim is valid (no ERROR severity issues)
    // Use the context as the canonical source for issues
    List<ValidationIssue> finalIssues = context.getIssues();
    boolean isValid = !context.hasErrors();

    log.info("Validation completed. isValid: {}, total issues: {}, total errors: {}",
            isValid, context.getIssueCount(), context.getErrorCount());

    return new ValidationResult().toBuilder().isValid(isValid).issues(finalIssues).build();
  }

  /**
   * Builds the validation context from scope and related claims.
   *
   * @param scope the validation scope
   * @param relatedClaims optional related claims
   * @return the validation context
   */
  private ClaimValidationContext buildValidationContext(
          Claim claim, Set<String> scope, List<Claim> relatedClaims) {
    List<Claim> related = relatedClaims != null ? relatedClaims : List.of();

    ClaimValidationContext context = ClaimValidationContext.builder()
            .scope(scope)
            .relatedClaims(related)
            .build();

    fetchFeeCalculationType(claim.getFeeCode(), context);

    return context;
  }

  /**
   * Fetches the fee calculation type for the given fee code by delegating to the fee scheme
   * provider.
   *
   * <p>Defensive behaviour:
   * <ul>
   *   <li>A {@code null} or blank {@code feeCode} is treated as unresolvable; {@code null} is
   *       returned immediately with a warning.</li>
   *   <li>If the provider returns an empty {@link Optional} (e.g. fee code not found / 404),
   *       {@code null} is returned and a warning is logged. The caller is responsible for
   *       deciding how to handle an unknown fee type.</li>
   *   <li>If the provider returns a response whose {@code feeType} is {@code null} or blank,
   *       {@code null} is returned and a warning is logged so the gap is visible in logs.</li>
   * </ul>
   *
   * @param feeCode the fee code to resolve; may be {@code null} or blank
   * @param context the validation context to update with the resolved fee calculation type
   *     (if found)
   */
  private void fetchFeeCalculationType(String feeCode, ClaimValidationContext context) {
    if (feeCode == null || feeCode.isBlank()) {
      log.warn("Cannot fetch fee calculation type: feeCode is null or blank");
      context.addValidationIssue(ClaimValidationError.MISSING_FEE_CODE.toValidationIssue());
      return;
    }

    Optional<FeeDetailsResponseV2> opt = feeSchemeProvider.getFeeDetails(feeCode);

    if (opt.isEmpty()) {
      log.warn("Unable to retrieve fee details for fee code: {} — fee type will be null", feeCode);
      context.addValidationIssue(
              ClaimValidationError.TECHNICAL_ERROR_FEE_SCHEME_API.toValidationIssue());
      return;
    }

    String feeType = opt.get().getFeeType();

    if (feeType == null || feeType.isBlank()) {
      log.warn("Fee details returned for fee code: {} but feeType is null or blank", feeCode);
      context.addValidationIssue(
              ClaimValidationError.TECHNICAL_ERROR_FEE_SCHEME_API.toValidationIssue());
      return;
    }
    log.debug("Resolved fee calculation type '{}' for fee code '{}'", feeType, feeCode);
    context.setFeeCalculationType(feeType);
  }

  /**
   * Builds a validation result for when no claim is provided.
   *
   * @return validation result with MISSING_CLAIM error
   */
  private ValidationResult buildMissingClaimResult() {
    return new ValidationResult()
            .toBuilder()
            .isValid(false)
            .issues(List.of(ClaimValidationError.MISSING_CLAIM.toValidationIssue()))
            .build();
  }

}
