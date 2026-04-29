package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate;

import static uk.gov.justice.laa.dstew.payments.claims.validation.core.util.FeeTypeUtils.isDisbursementClaim;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;

/**
 * Validation service for Legal Help duplicate claims. Checks for duplicates in previous
 * submissions. Disbursement claims are handled by
 * DuplicateClaimLegalHelpDisbursementValidationStrategy.
 */
@Slf4j
@Service
public final class DuplicateClaimLegalHelpValidationServiceStrategy extends DuplicateClaimValidation
    implements LegalHelpDuplicateClaimValidationStrategy {

  public DuplicateClaimLegalHelpValidationServiceStrategy(ClaimsDataProvider claimsDataProvider) {
    super(claimsDataProvider);
  }

  @Override
  public List<ValidationIssue> validateDuplicateClaims(
      Claim currentClaim, List<Claim> submissionClaims, String officeCode, String feeType) {
    List<ValidationIssue> issues = new ArrayList<>();

    // Disbursement claims are handled exclusively by
    // DuplicateClaimLegalHelpDisbursementValidationStrategy
    if (isDisbursementClaim(feeType)) {
      return issues;
    }

    DuplicateCheckResult result =
        getDuplicateClaimsInPreviousSubmission(
            officeCode,
            currentClaim.getFeeCode(),
            currentClaim.getUniqueFileNumber(),
            currentClaim.getUniqueClientNumber(),
            currentClaim.getSubmissionId());

    if (result.hasError()) {
      issues.add(result.error());
      return issues;
    }

    if (result.hasDuplicates()) {
      logDuplicates(currentClaim, result.duplicates());
      issues.add(
          ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION
              .toValidationIssue());
    }

    return issues;
  }
}
