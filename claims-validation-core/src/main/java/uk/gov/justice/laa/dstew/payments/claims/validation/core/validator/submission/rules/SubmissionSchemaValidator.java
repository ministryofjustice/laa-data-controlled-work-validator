package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.schema.AbstractSchemaValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationContext;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;

/**
 * Validates a {@link SubmissionResponse} against the submission JSON schema.
 *
 * <p>Schema validation runs first (priority 1) and checks that the submission data conforms to
 * the rules defined in {@code /schemas/submission-fields.schema.json}. The schema defines:
 *
 * <ul>
 *   <li>Field types and format constraints (e.g. UUID, boolean)
 *   <li>Pattern constraints (e.g. office account number, submission period format)
 *   <li>Enum constraints for area of law and status
 *   <li>Conditional required fields driven by area of law
 *   <li>Custom error messages per field via {@code validationErrorMessages}
 * </ul>
 *
 * <p>All shared validation logic lives in {@link AbstractSchemaValidator}. This class only
 * provides submission-specific configuration: schema path, serialisation strategy,
 * spec version and discriminator extraction.
 *
 * <p>The {@link SubmissionValidator} contract is {@code void} — issues are written directly to
 * the {@link SubmissionValidationContext} rather than returned. The bridge is a single
 * {@code forEach} in {@link #validate(SubmissionResponse, SubmissionValidationContext)}.
 */
@Component
@Slf4j
public class SubmissionSchemaValidator
    extends AbstractSchemaValidator<SubmissionResponse>
    implements SubmissionValidator {

  private static final String SCHEMA_PATH = "/schemas/submission-fields.schema.json";

  // ─────────────────────────────────────────────────────────────────────────
  // AbstractSchemaValidator hook methods
  // ─────────────────────────────────────────────────────────────────────────

  @Override
  protected String getSchemaPath() {
    return SCHEMA_PATH;
  }

  /**
   * Applies snake_case naming and NON_NULL inclusion to match the submission schema's property
   * names. Java model fields (e.g. {@code officeAccountNumber}) are serialised as
   * {@code office_account_number} before validation. Null fields are omitted so optional fields
   * do not produce spurious errors.
   */
  @Override
  protected void configureObjectMapper(ObjectMapper mapper) {
    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
  }

  /**
   * The submission schema currently defines only {@code "ALL"} discriminator entries — there are
   * no per-area-of-law custom error messages. Returns {@code null} so the {@code "ALL"} fallback
   * is always used.
   *
   * <p>When area-of-law specific messages are added to the schema, update this method to return
   * {@code submission.getAreaOfLaw()} (or its string representation) to enable discriminated
   * lookup.
   */
  @Override
  protected String extractDiscriminator(SubmissionResponse submission) {
    return null;
  }


  @Override
  public int priority() {
    return 1; // Schema validation runs before all other submission validators
  }

  @Override
  public String getValidatorCode() {
    return "SUBMISSION_SCHEMA";
  }

  // ─────────────────────────────────────────────────────────────────────────
  // SubmissionValidator interface
  // ─────────────────────────────────────────────────────────────────────────

  /**
   * Validates the submission against the schema and writes any issues to the context.
   *
   * <p>{@link SubmissionValidator} uses a void/context-mutation contract rather than returning
   * issues. The bridge is a single {@code forEach} over the list returned by
   * {@link #validateSubject(SubmissionResponse)}).
   */
  @Override
  public void validate(SubmissionResponse submission, SubmissionValidationContext context) {
    validateSubject(submission).forEach(context::addValidationIssue);
  }
}
