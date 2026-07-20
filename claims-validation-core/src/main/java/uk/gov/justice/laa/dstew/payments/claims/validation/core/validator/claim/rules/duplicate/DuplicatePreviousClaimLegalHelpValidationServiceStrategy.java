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

/** Validation service for Legal Help duplicate claims within the current submission. */
@Slf4j
public final class DuplicatePreviousClaimLegalHelpValidationServiceStrategy
    extends DuplicateClaimValidation implements LegalHelpDuplicateClaimValidationStrategy {

  public DuplicatePreviousClaimLegalHelpValidationServiceStrategy(
      ClaimsDataProvider claimsDataProvider) {
    super(claimsDataProvider);
  }

  @Override
  public List<ValidationIssue> validateDuplicateClaims(
      Claim currentClaim, List<Claim> submissionClaims, String officeCode, String feeType) {
    List<ValidationIssue> issues = new ArrayList<>();

    Predicate<Claim> sameSubmissionMatch =
        candidate ->
            Objects.equals(candidate.getFeeCode(), currentClaim.getFeeCode())
                && Objects.equals(
                    candidate.getUniqueFileNumber(), currentClaim.getUniqueFileNumber())
                && Objects.equals(
                    candidate.getUniqueClientNumber(), currentClaim.getUniqueClientNumber());

    // Legal Help keys on fee code + UFN + UCN, so the provider-side UCN filter is included.
    // Fails closed on the single-claim path: a provider failure surfaces the technical error.
    DuplicateCheckResult sameSubmissionResult =
        findSameSubmissionDuplicates(currentClaim, submissionClaims, sameSubmissionMatch);
    if (sameSubmissionResult.hasError()) {
      issues.add(sameSubmissionResult.error());
      return issues;
    }
    List<Claim> duplicateClaimsInThisSubmission = sameSubmissionResult.duplicates();

    if (!duplicateClaimsInThisSubmission.isEmpty()) {
      logDuplicates(currentClaim, duplicateClaimsInThisSubmission);
      issues.add(
          ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_SAME_SUBMISSION
              .toValidationIssue());
    }

    return issues;
  }
}
