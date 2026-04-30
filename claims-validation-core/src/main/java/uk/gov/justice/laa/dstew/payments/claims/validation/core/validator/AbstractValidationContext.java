package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.springframework.util.CollectionUtils;
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
@Getter
public abstract class AbstractValidationContext {

  /**
   * The validation issues collected so far. Always non-null; empty until the first issue is added.
   */
  List<ValidationIssue> issues = new ArrayList<>();

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
    this.issues.addAll(validationIssues);
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
    return !CollectionUtils.isEmpty(issues)
        && issues.stream().anyMatch(issue -> issue.getSeverity() == ValidationSeverity.ERROR);
  }
}
