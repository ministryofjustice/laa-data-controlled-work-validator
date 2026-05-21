package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.AbstractValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.SubmissionValidator;

/**
 * Accumulates {@link ValidationIssue}s raised during the validation of a single submission.
 *
 * <p>Individual {@link SubmissionValidator} implementations write to this context via
 * {@link #addValidationIssue(ValidationIssue)} or {@link #addValidationIssues(java.util.List)}.
 * Callers inspect the outcome with {@link #hasErrors()} and retrieve the full issue list via
 * {@link #getIssues()}.
 *
 * <p>All accumulation behaviour is provided by {@link AbstractValidationContext}. The issues list
 * is initialised eagerly and will never be {@code null}.
 *
 * <p>Use {@link #create()} for a no-arg instance, or {@link #builder()} for a scoped instance.
 */
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class SubmissionValidationContext extends AbstractValidationContext {

  /**
   * Creates a new empty {@code SubmissionValidationContext} with no scope set.
   * Equivalent to {@code SubmissionValidationContext.builder().build()}.
   */
  public static SubmissionValidationContext create() {
    return SubmissionValidationContext.builder().build();
  }
}
