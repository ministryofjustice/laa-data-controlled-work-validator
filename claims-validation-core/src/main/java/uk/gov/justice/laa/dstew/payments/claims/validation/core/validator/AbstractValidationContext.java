package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;

/**
 * Base class for all validation contexts.
 *
 * <p>Provides the shared accumulator behaviour used across both submission and claim validation:
 * recording {@link ValidationIssue}s via {@link #addValidationIssue(ValidationIssue)} or
 * {@link #addValidationIssues(List)}, and interrogating the outcome via {@link #hasErrors()} and
 * {@link #getIssues()}.
 *
 * <p>The issues list is initialised eagerly to an empty {@link ArrayList}; {@link #getIssues()}
 * will never return {@code null}.
 *
 * <p>Subclasses add domain-specific fields appropriate to their validation context:
 * <ul>
 *   <li>{@code SubmissionValidationContext} — accumulates issues during submission validation.
 *   <li>{@code ClaimValidationContext} — carries request-scoped input data for claim validation;
 *       accumulates issues from Stage 2 of the context-based validation refactor onwards.
 * </ul>
 */
public abstract class AbstractValidationContext {

  /**
   * Internal set storing unique validation issues in insertion order. Using a set ensures
   * duplicates are automatically de-duplicated while preserving the order in which issues
   * were recorded.
   */
  private final Set<ValidationIssue> issuesSet = new LinkedHashSet<>();

  /**
   * Appends a single validation issue to the context.
   *
   * <p>Delegates to {@link #addValidationIssues(List)}.
   *
   * @param validationIssue the issue to record; must not be {@code null}
   */
  public void addValidationIssue(final ValidationIssue validationIssue) {
    this.addValidationIssues(List.of(validationIssue));
  }

  /**
   * Appends multiple validation issues to the context in one call.
   *
   * <p>Useful when a validator produces several issues at once (e.g. the result of a schema
   * validation pass) and avoids the overhead of calling
   * {@link #addValidationIssue(ValidationIssue)} in a loop.
   *
   * @param validationIssues the issues to record; must not be {@code null}, may be empty
   */
  public void addValidationIssues(final List<ValidationIssue> validationIssues) {
    if (validationIssues == null || validationIssues.isEmpty()) {
      return;
    }
    this.issuesSet.addAll(validationIssues);
  }

  /**
   * Returns {@code true} if at least one issue with {@link ValidationSeverity#ERROR} severity has
   * been recorded.
   *
   * <p>Issues with {@link ValidationSeverity#WARNING} or {@link ValidationSeverity#INFO} severity
   * do not cause this method to return {@code true}.
   *
   * @return {@code true} if any ERROR-severity issue is present, {@code false} otherwise
   */
  public boolean hasErrors() {
    return !issuesSet.isEmpty()
            && issuesSet.stream()
            .anyMatch(issue -> issue.getSeverity() == ValidationSeverity.ERROR);
  }

  /**
   * Returns the accumulated validation issues as a list preserving insertion order. A new list
   * is returned on each call to avoid leaking the internal mutable set to callers.
   *
   * @return ordered list of accumulated validation issues (never null)
   */
  public List<ValidationIssue> getIssues() {
    return new ArrayList<>(issuesSet);
  }

  /**
   * Returns the number of unique validation issues recorded in this context. This is an O(1)
   * operation backed by the internal set and avoids creating a snapshot when only a count is
   * required (useful for debug logging).
   *
   * @return the number of unique issues recorded
   */
  public int getIssueCount() {
    return issuesSet.size();
  }
}
