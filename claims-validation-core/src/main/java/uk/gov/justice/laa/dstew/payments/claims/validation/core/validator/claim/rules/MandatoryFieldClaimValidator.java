package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.config.ExclusionsRegistry;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.config.MandatoryFieldsRegistry;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.FeeCalculationType;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.StringCaseUtil;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

/**
 * Checks if all mandatory fields for a given area of law are populated in the provided Claim
 * object. If a mandatory field is missing or invalid, an error is added to the validation issues.
 */
@Slf4j
public class MandatoryFieldClaimValidator implements ClaimValidator {

  @Override
  public void validate(Claim claim, ClaimValidationContext context) {

    AreaOfLaw areaOfLaw = claim.getAreaOfLaw();
    if (areaOfLaw == null) {
      return; // No area of law - no mandatory fields to check
    }

    String feeCalculationType = context.getFeeCalculationType();

    Map<AreaOfLaw, List<String>> mandatoryFieldsByAreaOfLaw =
        MandatoryFieldsRegistry.MANDATORY_FIELDS_BY_AREA_OF_LAW;
    List<String> mandatoryFields = mandatoryFieldsByAreaOfLaw.get(areaOfLaw);
    if (Objects.isNull(mandatoryFields)) {
      return;
    }
    boolean isDisbursementLegalHelpClaim =
        FeeCalculationType.DISB_ONLY.getValue().equals(feeCalculationType)
            && AreaOfLaw.LEGAL_HELP.equals(areaOfLaw);
    List<String> disbursementExclusions = ExclusionsRegistry.DISBURSEMENT_ONLY_EXCLUSIONS;

    for (String fieldName : mandatoryFields) {
      if (isDisbursementLegalHelpClaim && disbursementExclusions.contains(fieldName)) {
        // Skip validation for excluded fields when disbursement-only
        log.debug("Skipping validation for excluded field: {}", fieldName);
        continue;
      }
      try {
        // Look up getter method for the property
        PropertyDescriptor pd = new PropertyDescriptor(fieldName, Claim.class);
        Method getter = pd.getReadMethod();

        if (getter == null) {
          throw new IllegalStateException("No getter for field in Claim: " + fieldName);
        }

        Object value = getter.invoke(claim);

        if (value == null || (value instanceof String s && s.trim().isEmpty())) {
          ValidationIssue issue = ClaimValidationError.MISSING_MANDATORY_FIELD.toValidationIssue(
              StringCaseUtil.toTitleCase(fieldName),
              StringCaseUtil.toTitleCase(areaOfLaw.name()));
          issue.setTechnicalMessage(issue.getMessage());
          issue.setPath(StringCaseUtil.toSnakeCase(fieldName));
          context.addValidationIssue(issue);
        }

      } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
        throw new IllegalStateException("Error accessing property in Claim: " + fieldName, e);
      }
    }
  }

  @Override
  public int priority() {
    return 10;
  }

  @Override
  public String getValidatorCode() {
    return "CLAIM_MANDATORY_FIELD";
  }
}
