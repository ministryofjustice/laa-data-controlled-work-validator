package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.AbstractValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.SubmissionValidator;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;

/**
 * Accumulates {@link ValidationIssue}s raised during the validation of a single
 * {@link SubmissionResponse}.
 *
 * <p>Individual {@link SubmissionValidator} implementations write to this context via
 * {@link #addValidationIssue(ValidationIssue)} or {@link #addValidationIssues(java.util.List)}.
 * Callers inspect the outcome with {@link #hasErrors()} and retrieve the full issue list via
 * {@link #getIssues()}.
 *
 * <p>All behaviour is provided by {@link AbstractValidationContext}. The issues list is
 * initialised eagerly and will never be {@code null}.
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true)
public class SubmissionValidationContext extends AbstractValidationContext {
}
