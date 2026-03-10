package uk.gov.justice.laa.dstew.payments.claims.validation.service.validator.rules.duplicate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.ClaimsApiClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ClaimValidationError;

/**
 * Validation service for Legal Help duplicate claims. Checks for duplicates in previous
 * submissions.
 */
@Slf4j
@Service
public final class DuplicateClaimLegalHelpValidationServiceStrategy extends DuplicateClaimValidation
    implements LegalHelpDuplicateClaimValidationStrategy {

  public DuplicateClaimLegalHelpValidationServiceStrategy(ClaimsApiClient claimsApiClient) {
    super(claimsApiClient);
  }

  @Override
  public List<ValidationIssue> validateDuplicateClaims(
      Claim currentClaim, List<Claim> submissionClaims, String officeCode, String feeType) {

    List<ValidationIssue> issues = new ArrayList<>();

    // Disbursement claims are handled exclusively by
    // DuplicateClaimLegalHelpDisbursementValidationStrategy
    // TODO: Add isDisbursementClaim check when fee type logic is confirmed
    boolean isDisbursement = false; // TODO: isDisbursementClaim(feeType)

    List<Claim> duplicateClaimsInPreviousSubmission =
        isDisbursement
            ? Collections.emptyList()
            : getDuplicateClaimsInPreviousSubmission(
                officeCode,
                currentClaim.getFeeCode(),
                currentClaim.getUniqueFileNumber(),
                currentClaim.getUniqueClientNumber(),
                null,
                submissionClaims);

    if (!duplicateClaimsInPreviousSubmission.isEmpty()) {
      logDuplicates(currentClaim, duplicateClaimsInPreviousSubmission);
      issues.add(
          ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION
              .toValidationIssue());
    }

    return issues;
  }
}
