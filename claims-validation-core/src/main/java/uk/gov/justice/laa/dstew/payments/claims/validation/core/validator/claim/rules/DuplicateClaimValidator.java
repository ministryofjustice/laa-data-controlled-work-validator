package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidatorCode;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateClaimValidationStrategy;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

/**
 * Validator for checking duplicate claims. Delegates to area-of-law specific strategies for
 * duplicate checking logic.
 */
@RequiredArgsConstructor
@Slf4j
public class DuplicateClaimValidator implements ClaimValidator {

  private final List<DuplicateClaimValidationStrategy> strategyList;

  @Override
  public void validate(Claim claim, ClaimValidationContext context) {

    AreaOfLaw areaOfLaw = claim.getAreaOfLaw();
    if (areaOfLaw == null) {
      log.debug("No area of law set, skipping duplicate claim validation");
      return;
    }

    String officeCode = claim.getOfficeAccountNumber();
    List<Claim> submissionClaims = context.getRelatedClaims();
    String feeType = context.getFeeCalculationType();

    log.debug("Running duplicate claim validation for area of law: {}", areaOfLaw);

    // Find strategies compatible with this area of law
    Predicate<DuplicateClaimValidationStrategy> areaOfLawPredicate =
        strategy -> strategy.compatibleAreaOfLaws().contains(areaOfLaw);

    List<DuplicateClaimValidationStrategy> compatibleStrategies =
        strategyList.stream().filter(areaOfLawPredicate).toList();

    if (compatibleStrategies.isEmpty()) {
      log.debug("No duplicate claim validation strategy found for area of law: {}", areaOfLaw);
      return;
    }

    // Run each compatible strategy and collect validation issues. Several strategies can run for
    // the same area of law and independently hit the same downstream API, so identical issues
    // (e.g. a shared TECHNICAL_ERROR_DATA_CLAIMS_API) are de-duplicated to avoid reporting the
    // same problem twice for one claim.
    List<ValidationIssue> aggregatedIssues = new ArrayList<>();
    for (DuplicateClaimValidationStrategy strategy : compatibleStrategies) {
      log.debug("Running strategy: {}", strategy.getClass().getSimpleName());
      List<ValidationIssue> strategyIssues =
          strategy.validateDuplicateClaims(claim, submissionClaims, officeCode, feeType);
      for (ValidationIssue issue : strategyIssues) {
        if (!aggregatedIssues.contains(issue)) {
          aggregatedIssues.add(issue);
        }
      }
    }
    context.addValidationIssues(aggregatedIssues);

    log.debug("Duplicate claim validation completed, found {} issues", context.getIssues().size());
  }

  @Override
  public int priority() {
    return 10000; // Run late - after other validations (matching reference)
  }

  @Override
  public ClaimValidatorCode getValidatorCode() {
    return ClaimValidatorCode.CLAIM_DUPLICATE_VALIDATOR;
  }
}
