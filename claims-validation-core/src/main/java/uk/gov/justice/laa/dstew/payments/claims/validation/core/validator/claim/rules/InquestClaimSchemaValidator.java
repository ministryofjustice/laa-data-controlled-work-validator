package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidatorCode;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.schema.AbstractSchemaValidator;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimInquestDetail;

/**
 * Validates a claim's {@link ClaimInquestDetail} against the Inquest detail JSON schema.
 *
 * <p>Checks the field length and character rules defined in
 * {@code /schemas/inquest-detail.schema.json}. Unlike {@link ClaimSchemaValidator}, the schema
 * only applies to non-means-tested Inquest claims; applicability, mandatory, date and Government
 * Department rules are enforced by {@link InquestClaimValidator}.
 */
public class InquestClaimSchemaValidator extends AbstractSchemaValidator<ClaimInquestDetail>
    implements ClaimValidator {

  private static final String SCHEMA_PATH = "/schemas/inquest-detail.schema.json";
  private static final String PATH_PREFIX = "inquest_detail.";

  @Override
  protected String getSchemaPath() {
    return SCHEMA_PATH;
  }

  @Override
  protected void configureObjectMapper(ObjectMapper mapper) {
    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
  }

  @Override
  protected String extractDiscriminator(ClaimInquestDetail detail) {
    return null;
  }

  @Override
  public int priority() {
    return 1; // Schema validation runs before the other claim validators
  }

  @Override
  public ClaimValidatorCode getValidatorCode() {
    return ClaimValidatorCode.CLAIM_INQUEST_SCHEMA_VALIDATOR;
  }

  @Override
  public void validate(Claim claim, ClaimValidationContext context) {
    if (!InquestApplicability.requiresFieldRules(claim, context)) {
      return;
    }
    for (ValidationIssue issue : validateSubject(claim.getInquestDetail())) {
      if (issue.getPath() != null) {
        issue.setPath(PATH_PREFIX + issue.getPath());
      }
      context.addValidationIssue(issue);
    }
  }
}
