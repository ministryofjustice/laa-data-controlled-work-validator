package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
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
 * <p>Also carries the validation {@code scope} — an optional set of scope identifiers used by
 * validators to determine whether they should run. Moving scope here makes it available to all
 * subclasses without repetition.
 *
 * <p>The issues list is initialised eagerly to an empty {@link ArrayList}; {@link #getIssues()}
 * will never return {@code null}.
 *
 * <p>Subclasses add domain-specific fields appropriate to their validation context:
 * <ul>
 *   <li>{@code SubmissionValidationContext} — accumulates issues during submission validation.
 *   <li>{@code ClaimValidationContext} — carries request-scoped input data for claim validation.
 * </ul>
 */
@Getter
@SuperBuilder
public abstract class AbstractValidationContext {

  /**
   * The validation scope, expressed as a set of {@link ValidatorCode}s. Validators use this to
   * determine if they should run. May be {@code null} or empty when no scoping is required.
   */
  private final Set<ValidatorCode> scope;

  /**
   * Internal set storing unique validation issues in insertion order. Using a set ensures
   * duplicates are automatically de-duplicated while preserving the order in which issues
   * were recorded.
   */
  private final List<ValidationIssue> issuesList = new ArrayList<>();

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
   * Appends multiple validation issues to the context in one call, preserving insertion order.
   *
   * <p>All issues are stored as-is; no deduplication is applied at write time. Deduplication by
   * {@code path} is applied at read time by {@link #getIssues()}. Use {@link #getAllIssues()} to
   * retrieve the full unfiltered list.
   *
   * @param validationIssues the issues to record; {@code null} or empty is silently ignored
   */
  public void addValidationIssues(final List<ValidationIssue> validationIssues) {
    if (validationIssues == null || validationIssues.isEmpty()) {
      return;
    }
    issuesList.addAll(validationIssues);
  }

  /**
   * Returns {@code true} if at least one issue with {@link ValidationSeverity#ERROR} severity has
   * been recorded (across all raw issues, prior to path-based deduplication).
   *
   * <p>Issues with {@link ValidationSeverity#WARNING} or {@link ValidationSeverity#INFO} severity
   * do not cause this method to return {@code true}.
   *
   * @return {@code true} if any ERROR-severity issue is present, {@code false} otherwise
   */
  public boolean hasErrors() {
    return issuesList.stream()
        .anyMatch(issue -> issue.getSeverity() == ValidationSeverity.ERROR);
  }

  /**
   * Internal helper: iterates {@code source}, applying path-based deduplication optionally
   * filtered to a single severity. Issues with a {@code null} path are always included.
   */
  private List<ValidationIssue> deduplicated(
      List<ValidationIssue> source, ValidationSeverity severityFilter) {
    Set<String> seenPaths = new LinkedHashSet<>();
    List<ValidationIssue> result = new ArrayList<>();
    for (ValidationIssue issue : source) {
      if (severityFilter != null && issue.getSeverity() != severityFilter) {
        continue;
      }
      String path = issue.getPath();
      if (path == null || seenPaths.add(path)) {
        result.add(issue);
      }
    }
    return result;
  }

  /**
   * Returns the deduplicated validation issues in insertion order.
   *
   * <p>Deduplication rule: for issues with a non-null {@code path}, only the first occurrence of
   * each path value is included. Issues with a {@code null} path are always included.
   *
   * <p>A new list is returned on each call (defensive copy).
   *
   * @return deduplicated ordered list of validation issues; never {@code null}
   */
  public List<ValidationIssue> getIssues() {
    return deduplicated(issuesList, null);
  }

  /**
   * Returns all accumulated validation issues in insertion order, without any deduplication.
   *
   * <p>Useful for diagnostics, admin endpoints, or auditing all issues raised by validators
   * regardless of path duplication. A new list is returned on each call (defensive copy).
   *
   * @return all issues in insertion order; never {@code null}
   */
  public List<ValidationIssue> getAllIssues() {
    return new ArrayList<>(issuesList);
  }

  /**
   * Returns the count of deduplicated issues — equivalent to {@code getIssues().size()} but
   * without allocating the list.
   *
   * @return number of deduplicated issues
   */
  public int getIssueCount() {
    return getIssues().size();
  }

  /**
   * Returns deduplicated {@link ValidationSeverity#ERROR}-severity issues in insertion order.
   *
   * <p>Applies path-based deduplication independently across errors only: warnings on the same
   * path do not suppress errors. Issues with a {@code null} path are always included.
   *
   * <p>A new list is returned on each call (defensive copy).
   *
   * @return deduplicated ordered list of ERROR-severity issues; never {@code null}
   */
  public List<ValidationIssue> getErrors() {
    return deduplicated(issuesList, ValidationSeverity.ERROR);
  }

  /**
   * Returns all {@link ValidationSeverity#ERROR}-severity issues in insertion order, without any
   * path-based deduplication.
   *
   * <p>Useful for auditing every error raised by validators regardless of whether multiple
   * validators flagged the same {@code path}. Warnings and info issues are excluded.
   *
   * <p>A new list is returned on each call (defensive copy).
   *
   * @return all ERROR-severity issues in insertion order; never {@code null}
   */
  public List<ValidationIssue> getAllErrors() {
    return issuesList.stream()
        .filter(issue -> issue.getSeverity() == ValidationSeverity.ERROR)
        .toList();
  }

  /**
   * Returns the count of deduplicated ERROR-severity issues — equivalent to
   * {@code getErrors().size()} but without allocating the list.
   *
   * @return number of deduplicated ERROR-severity issues
   */
  public int getErrorCount() {
    return getErrors().size();
  }
}
