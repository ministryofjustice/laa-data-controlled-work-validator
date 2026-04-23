package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.config.ExclusionsRegistry;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.config.MandatoryFieldsRegistry;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.FeeCalculationType;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.StringCaseUtil;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

/**
 * Checks if all mandatory fields for a given area of law are populated in the provided Claim
 * object. If a mandatory field is missing or invalid, an error is added to the validation issues.
 */
@Component
@Slf4j
public class MandatoryFieldClaimValidator implements ClaimValidator {

  private final MandatoryFieldsRegistry mandatoryFieldsRegistry;
  private final ExclusionsRegistry exclusionsRegistry;

  public MandatoryFieldClaimValidator(
      MandatoryFieldsRegistry mandatoryFieldsRegistry, ExclusionsRegistry exclusionsRegistry) {
    this.exclusionsRegistry = exclusionsRegistry;
    this.mandatoryFieldsRegistry = mandatoryFieldsRegistry;
  }

  @Override
  public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    AreaOfLaw areaOfLaw = claim.getAreaOfLaw();
    if (areaOfLaw == null) {
      return issues; // No area of law - no mandatory fields to check
    }

    String feeCalculationType = context.getFeeCalculationType();

    Map<AreaOfLaw, List<String>> mandatoryFieldsByAreaOfLaw =
        mandatoryFieldsRegistry.getMandatoryFieldsByAreaOfLaw();
    List<String> mandatoryFields = mandatoryFieldsByAreaOfLaw.get(areaOfLaw);
    if (Objects.isNull(mandatoryFields)) {
      return issues;
    }
    boolean isDisbursementLegalHelpClaim =
        FeeCalculationType.DISB_ONLY.getValue().equals(feeCalculationType)
            && AreaOfLaw.LEGAL_HELP.equals(areaOfLaw);
    List<String> disbursementExclusions = exclusionsRegistry.getDisbursementOnlyExclusions();

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
          issues.add(
              ClaimValidationError.MISSING_MANDATORY_FIELD.toValidationIssue(
                  StringCaseUtil.toTitleCase(fieldName),
                  StringCaseUtil.toTitleCase(areaOfLaw.name())));
        }

      } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
        throw new IllegalStateException("Error accessing property in Claim: " + fieldName, e);
      }
    }

    return issues;
  }

  @Override
  public int priority() {
    return 10;
  }

  @Override
  public String getValidatorCode() {
    return "MANDATORY_FIELD";
  }
}
