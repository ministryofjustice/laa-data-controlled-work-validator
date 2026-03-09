package uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules.duplicate.DuplicateClaimValidationStrategy;

/**
 * Validator for checking duplicate claims.
 * Delegates to area-of-law specific strategies for duplicate checking logic.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DuplicateClaimValidator implements ClaimValidator {

  private final List<DuplicateClaimValidationStrategy> strategyList;

  @Override
  public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    AreaOfLaw areaOfLaw = claim.getAreaOfLaw();
    if (areaOfLaw == null) {
      log.debug("No area of law set, skipping duplicate claim validation");
      return issues;
    }

    String officeCode = claim.getOfficeAccountNumber();
    List<Claim> submissionClaims = context.getRelatedClaims();
    String feeType = claim.getFeeCode(); // TODO: Confirm if this should be fee calculation type

    log.debug("Running duplicate claim validation for area of law: {}", areaOfLaw);

    // Find strategies compatible with this area of law
    Predicate<DuplicateClaimValidationStrategy> areaOfLawPredicate =
        strategy -> strategy.compatibleAreaOfLaws().contains(areaOfLaw);

    List<DuplicateClaimValidationStrategy> compatibleStrategies =
        strategyList.stream().filter(areaOfLawPredicate).toList();

    if (compatibleStrategies.isEmpty()) {
      log.debug("No duplicate claim validation strategy found for area of law: {}", areaOfLaw);
      return issues;
    }

    // Run each compatible strategy and collect validation issues
    for (DuplicateClaimValidationStrategy strategy : compatibleStrategies) {
      log.debug("Running strategy: {}", strategy.getClass().getSimpleName());
      List<ValidationIssue> strategyIssues = strategy.validateDuplicateClaims(
          claim, submissionClaims, officeCode, feeType);
      issues.addAll(strategyIssues);
    }

    log.debug("Duplicate claim validation completed, found {} issues", issues.size());
    return issues;
  }

  @Override
  public int priority() {
    return 10000; // Run late - after other validations (matching reference)
  }

  @Override
  public String getValidatorCode() {
    return "DUPLICATE_CLAIM";
  }
}
