package uk.gov.justice.laa.dstew.payments.claims.validation.service.validator.rules.duplicate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;

/**
 * Strategy interface for validating duplicate claims.
 * Implementations handle specific area-of-law duplicate checking logic.
 */
public interface DuplicateClaimValidationStrategy {

  Logger log = LoggerFactory.getLogger(DuplicateClaimValidationStrategy.class);

  /**
   * Validates a claim for duplicates.
   *
   * @param claim the claim being validated
   * @param submissionClaims other claims in the same submission
   * @param officeCode the office account number
   * @param feeType the fee type/code
   * @return list of validation issues found
   */
  default List<ValidationIssue> validateDuplicateClaims(
      Claim claim,
      List<Claim> submissionClaims,
      String officeCode,
      String feeType) {
    // Default implementation - override in concrete strategies
    return Collections.emptyList();
  }

  /**
   * Log the duplicate claims found for a given claim.
   *
   * @param claim The claim to log duplicates for.
   * @param duplicateClaims The list of duplicate claims.
   */
  default void logDuplicates(Claim claim, List<Claim> duplicateClaims) {
    String csvDuplicateClaimIds = duplicateClaims.stream()
        .map(c -> c.getId() != null ? c.getId().toString() : "unknown")
        .collect(Collectors.joining(","));
    log.debug(
        "{} duplicate claims found matching claim {}. Duplicates: {}",
        duplicateClaims.size(),
        claim.getId(),
        csvDuplicateClaimIds);
  }

  /**
   * Get the list of compatible areas of law for this strategy.
   *
   * @return List of compatible areas of law.
   */
  default List<AreaOfLaw> compatibleAreaOfLaws() {
    return List.of(
        AreaOfLaw.CRIME_LOWER,
        AreaOfLaw.LEGAL_HELP,
        AreaOfLaw.MEDIATION);
  }
}
