package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.SubmissionValidator;

/**
 * Orchestrates the execution of a collection of {@link SubmissionValidator}s against a single
 * {@link Claim}.
 *
 * <p>This class is responsible for:
 * <ul>
 *   <li>building the {@link SubmissionValidationContext} used by individual validators,</li>
 *   <li>executing validators that apply to a given {@code scope} in priority order,</li>
 *   <li>collecting and de-duplicating validation issues while preserving insertion order, and</li>
 *   <li>producing a {@link ValidationResult}
 *       summarising the outcome.</li>
 * </ul>
 */
@RequiredArgsConstructor
@Slf4j
public class SubmissionValidation {
  private final List<SubmissionValidator> submissionValidatorList;

}
