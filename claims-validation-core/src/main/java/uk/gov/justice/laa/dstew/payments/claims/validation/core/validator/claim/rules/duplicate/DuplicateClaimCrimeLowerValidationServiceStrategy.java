package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import lombok.extern.slf4j.Slf4j;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;

/** Service responsible for validating whether a Crime Lower claim is a duplicate. */
@Slf4j
public final class DuplicateClaimCrimeLowerValidationServiceStrategy
    extends DuplicateClaimValidation implements CrimeLowerDuplicateClaimValidationStrategy {

  public DuplicateClaimCrimeLowerValidationServiceStrategy(ClaimsDataProvider claimsDataProvider) {
    super(claimsDataProvider);
  }

  @Override
  public List<ValidationIssue> validateDuplicateClaims(
      Claim claim, List<Claim> submissionClaims, String officeCode, String feeType) {
    List<ValidationIssue> issues = new ArrayList<>();

    log.debug("Validating duplicates for claim {}", claim.getId());

    // Skipping PROD duplicate check - PROD fee code do not have a unique identifier
    // and client details are not mandatory for this fee code
    if ("PROD".equals(claim.getFeeCode())) {
      log.debug("Fee code is PROD, skipping duplicate check for claim {}", claim.getId());
      return issues;
    }

    // Check for duplicates within current submission. Crime Lower keys on fee code + UFN only, so
    // UCN is passed as null to avoid over-filtering the provider query on the single-claim path.
    Predicate<Claim> sameSubmissionMatch =
        claimToCompare ->
            Objects.equals(claim.getFeeCode(), claimToCompare.getFeeCode())
                && Objects.equals(
                    claim.getUniqueFileNumber(), claimToCompare.getUniqueFileNumber());

    DuplicateCheckResult sameSubmissionResult =
        findSameSubmissionDuplicates(claim, submissionClaims, sameSubmissionMatch, null);
    if (sameSubmissionResult.hasError()) {
      issues.add(sameSubmissionResult.error());
      return issues;
    }
    List<Claim> submissionDuplicateClaims = sameSubmissionResult.duplicates();

    // Check for duplicates in previous submissions. UCN is null so the provider is not
    // over-constrained: Crime Lower duplicates share fee code + UFN regardless of client number.
    DuplicateCheckResult result =
        getDuplicateClaimsInPreviousSubmission(claim, submissionClaims, null);

    if (result.hasError()) {
      issues.add(result.error());
      return issues;
    }

    if (!submissionDuplicateClaims.isEmpty()) {
      log.debug("Duplicate claims found in submission");
      logDuplicates(claim, submissionDuplicateClaims);
      issues.add(
          ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_SAME_SUBMISSION
              .toValidationIssue());
    }

    if (result.hasDuplicates()) {
      log.debug("Duplicate claims found in another submission for this office");
      logDuplicates(claim, result.duplicates());
      issues.add(
          ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION
              .toValidationIssue());
    }

    log.debug("Duplicate validation completed for claim {}", claim.getId());
    return issues;
  }
}
