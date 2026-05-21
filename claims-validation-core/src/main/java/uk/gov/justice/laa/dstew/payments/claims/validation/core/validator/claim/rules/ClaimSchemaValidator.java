package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import lombok.extern.slf4j.Slf4j;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.schema.AbstractSchemaValidator;

/**
 * Validates a {@link Claim} against the claim JSON schema.
 *
 * <p>Schema validation runs first (priority 1) and checks that the claim data conforms to the
 * rules defined in {@code /schemas/claim-fields.schema.json}. The schema defines:
 *
 * <ul>
 *   <li>Field types, patterns, min/max and length constraints
 *   <li>Custom per-field, per-area-of-law error messages via {@code validationErrorMessages}
 * </ul>
 *
 * <p>All shared validation logic lives in {@link AbstractSchemaValidator}. This class only
 * provides claim-specific configuration: schema path, serialisation strategy and discriminator
 * extraction.
 *
 * <p>To add a schema validator for a different domain object, extend
 * {@link AbstractSchemaValidator} in the same way and implement the relevant validator
 * interface.
 */
@Slf4j
public class ClaimSchemaValidator
    extends AbstractSchemaValidator<Claim>
    implements ClaimValidator {

  private static final String SCHEMA_PATH = "/schemas/claim-fields.schema.json";

  // ─────────────────────────────────────────────────────────────────────────
  // AbstractSchemaValidator hook methods
  // ─────────────────────────────────────────────────────────────────────────

  @Override
  protected String getSchemaPath() {
    return SCHEMA_PATH;
  }

  /**
   * Applies snake_case naming and NON_NULL inclusion to match the claim schema's property names.
   * Field names in the Java model (e.g. {@code feeCode}) are serialised as {@code fee_code}
   * before validation. Null fields are omitted so optional fields do not produce spurious errors.
   */
  @Override
  protected void configureObjectMapper(ObjectMapper mapper) {
    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    //mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
  }

  /**
   * Returns the area-of-law value as the discriminator for custom error message resolution.
   * {@code null} is returned when no area of law is set; the schema's {@code "ALL"} fallback
   * entry will be used in that case.
   */
  @Override
  protected String extractDiscriminator(Claim claim) {
    return claim.getAreaOfLaw() != null ? claim.getAreaOfLaw().getValue() : null;
  }

  @Override
  public int priority() {
    return 1; // Schema validation runs before all other claim validators
  }

  @Override
  public String getValidatorCode() {
    return "CLAIM_SCHEMA";
  }

  @Override
  public boolean appliesTo(String scope) {
    return true;
  }

  // ─────────────────────────────────────────────────────────────────────────
  // ClaimValidator interface
  // ─────────────────────────────────────────────────────────────────────────

  @Override
  public void validate(Claim claim, ClaimValidationContext context) {
    context.addValidationIssues(validateSubject(claim));
  }
}
