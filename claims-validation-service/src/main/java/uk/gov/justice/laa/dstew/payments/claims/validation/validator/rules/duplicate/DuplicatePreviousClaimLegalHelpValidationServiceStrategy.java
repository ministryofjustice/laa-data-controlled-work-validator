package uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules.duplicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.client.ClaimsApiClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.error.ClaimValidationError;

/**
 * Validation service for Legal Help duplicate claims within the current submission.
 */
@Slf4j
@Service
public final class DuplicatePreviousClaimLegalHelpValidationServiceStrategy
    extends DuplicateClaimValidation implements LegalHelpDuplicateClaimValidationStrategy {

  public DuplicatePreviousClaimLegalHelpValidationServiceStrategy(
      ClaimsApiClient claimsApiClient) {
    super(claimsApiClient);
  }

  @Override
  public List<ValidationIssue> validateDuplicateClaims(
      Claim currentClaim,
      List<Claim> submissionClaims,
      String officeCode,
      String feeType) {

    List<ValidationIssue> issues = new ArrayList<>();

    List<Claim> otherClaimsWithValidStatus =
        filterCurrentClaimWithValidStatus(currentClaim, submissionClaims);

    List<Claim> duplicateClaimsInThisSubmission =
        getDuplicateClaimsInCurrentSubmission(
            otherClaimsWithValidStatus,
            candidate ->
                Objects.equals(candidate.getFeeCode(), currentClaim.getFeeCode())
                    && Objects.equals(
                        candidate.getUniqueFileNumber(),
                        currentClaim.getUniqueFileNumber())
                    && Objects.equals(
                        candidate.getUniqueClientNumber(),
                        currentClaim.getUniqueClientNumber()));

    if (!duplicateClaimsInThisSubmission.isEmpty()) {
      logDuplicates(currentClaim, duplicateClaimsInThisSubmission);
      issues.add(ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_EXISTING_SUBMISSION
          .toValidationIssue());
    }

    return issues;
  }
}
