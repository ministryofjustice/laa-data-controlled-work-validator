package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules.duplicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.DataClaimsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ClaimValidationError;

/** Service responsible for validating whether a Crime Lower claim is a duplicate. */
@Slf4j
@Service
public final class DuplicateClaimCrimeLowerValidationServiceStrategy
    extends DuplicateClaimValidation implements CrimeLowerDuplicateClaimValidationStrategy {

  public DuplicateClaimCrimeLowerValidationServiceStrategy(DataClaimsClient dataClaimsClient) {
    super(dataClaimsClient);
  }

  @Override
  public List<ValidationIssue> validateDuplicateClaims(
      Claim claim, List<Claim> submissionClaims, String officeCode, String feeType) {

    List<ValidationIssue> issues = new ArrayList<>();

    log.debug("Validating duplicates for claim {}", claim.getId());

    List<Claim> claimsToCompare = filterCurrentClaimWithValidStatus(claim, submissionClaims);

    String feeCode = claim.getFeeCode();

    // Skipping PROD duplicate check - PROD fee code do not have a unique identifier
    // and client details are not mandatory for this fee code
    if ("PROD".equals(feeCode)) {
      log.debug("Fee code is PROD, skipping duplicate check for claim {}", claim.getId());
      return issues;
    }

    String uniqueFileNumber = claim.getUniqueFileNumber();

    // Check for duplicates within current submission
    List<Claim> submissionDuplicateClaims =
        getDuplicateClaimsInCurrentSubmission(
            claimsToCompare,
            claimToCompare ->
                Objects.equals(feeCode, claimToCompare.getFeeCode())
                    && Objects.equals(uniqueFileNumber, claimToCompare.getUniqueFileNumber()));

    // Check for duplicates in previous submissions
    DuplicateCheckResult result =
        getDuplicateClaimsInPreviousSubmission(
            officeCode,
            feeCode,
            uniqueFileNumber,
            null,
            claim.getSubmissionId()
        );

    if (result.hasError()) {
      issues.add(result.error());
      return issues;
    }

    if (!submissionDuplicateClaims.isEmpty()) {
      log.debug("Duplicate claims found in submission");
      logDuplicates(claim, submissionDuplicateClaims);
      issues.add(
          ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_EXISTING_SUBMISSION
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
