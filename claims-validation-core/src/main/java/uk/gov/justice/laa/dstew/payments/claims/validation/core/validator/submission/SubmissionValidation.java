package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.SubmissionValidator;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;

/**
 * Orchestrates the execution of a collection of {@link SubmissionValidator}s against a single
 * {@link SubmissionResponse}.
 *
 * <p>This class is responsible for:
 *
 * <ul>
 *   <li>building the {@link SubmissionValidationContext} used by individual validators,
 *   <li>executing validators that {@link SubmissionValidator#appliesTo apply} for the
 *       given {@code scope} in {@link SubmissionValidator#priority() priority} order,
 *   <li>collecting validation issues from each validator into the shared context, and
 *   <li>producing a {@link ValidationResult} that is considered valid when no issue with
 *       {@link ValidationSeverity#ERROR}
 *       severity is present.
 * </ul>
 *
 * <p>Instances of this class are immutable and thread-safe provided the supplied
 * {@code submissionValidatorList} is not modified after construction.
 */
@RequiredArgsConstructor
@Slf4j
public class SubmissionValidation {

  /**
   * The ordered list of validators executed for each validation request. Validators are filtered
   * by scope and sorted by priority before execution; the list itself is not mutated.
   */
  private final List<SubmissionValidator> submissionValidatorList;

  /**
   * Validates the supplied {@link SubmissionResponse} using the configured set of
   * {@link SubmissionValidator}s.
   *
   * <p>The method:
   * <ol>
   *   <li>Creates a fresh {@link SubmissionValidationContext} to accumulate issues.
   *   <li>Filters validators to those whose {@link SubmissionValidator#appliesTo appliesTo} returns
   *       {@code true} for the given {@code scope}.
   *   <li>Executes the filtered validators in ascending {@link SubmissionValidator#priority()}
   *       order, each writing issues into the shared context.
   *   <li>Returns a {@link ValidationResult} whose {@code isValid} flag is {@code true} when no
   *       {@code ERROR}-severity issue was recorded.
   * </ol>
   *
   * @param submission the submission to validate; must not be {@code null}
   * @param scope an optional set of scope identifiers used to filter applicable validators;
   *     {@code null} runs all scope-agnostic validators
   * @return a {@link ValidationResult} describing whether the submission is valid and listing
   *     any {@link uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue}s
   *     discovered
   */
  public ValidationResult validateSubmission(
      final SubmissionResponse submission, Set<SubmissionValidatorCode> scope) {
    log.debug("Starting validation for submission {}", submission.getSubmissionId());
    SubmissionValidationContext context = SubmissionValidationContext.create();

    submissionValidatorList.stream()
        .filter(validator -> validator.appliesTo(scope))
        .sorted(Comparator.comparingInt(SubmissionValidator::priority))
        .forEach(validator -> validator.validate(submission, context));

    ValidationResult result = new ValidationResult()
            .toBuilder()
            .isValid(!context.hasErrors())
            .issues(context.getIssues())
            .build();

    log.debug(
        "Completed validation for submission {} with result: {}",
        submission.getSubmissionId(),
        result);
    return result;

  }

}
