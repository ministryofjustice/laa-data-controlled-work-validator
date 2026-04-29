package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.SubmissionValidator;

/**
 * Accumulates {@link ValidationIssue}s raised during the validation of a single
 * {@link uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse}.
 *
 * <p>Individual {@link SubmissionValidator}
 * implementations write to this context via {@link #addValidationError(ValidationIssue)}.
 * Callers inspect the outcome with {@link #hasErrors()} and retrieve the full issue list via
 * {@link #getIssues()}.
 *
 * <p>The issues list is initialised lazily on the first call to
 * {@link #addValidationError(ValidationIssue)}; before that point {@link #getIssues()} returns
 * {@code null}. Callers that need a guaranteed non-null list should guard with
 * {@code CollectionUtils.isEmpty(context.getIssues())}.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
public class SubmissionValidationContext {

  /**
   * The validation issues collected so far. {@code null} until the first issue is added; use
   * {@link #hasErrors()} or {@code CollectionUtils.isEmpty} rather than a direct null check.
   */
  List<ValidationIssue> issues;

  /**
   * Appends a validation issue to the context.
   *
   * <p>The issues list is created lazily on the first call; subsequent calls append to the
   * existing list.
   *
   * @param validationIssue the issue to record; must not be {@code null}
   */
  public void addValidationError(ValidationIssue validationIssue) {
    if (this.issues == null) {
      this.issues = new ArrayList<>();
    }
    this.issues.add(validationIssue);
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
