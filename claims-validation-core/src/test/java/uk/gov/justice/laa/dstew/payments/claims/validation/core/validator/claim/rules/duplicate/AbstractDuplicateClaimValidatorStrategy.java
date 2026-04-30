package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate;

import java.util.UUID;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResponse;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;

public abstract class AbstractDuplicateClaimValidatorStrategy {

  protected Claim createClaim(
      String id,
      String submissionId,
      String feeCode,
      String uniqueFileNumber,
      String uniqueClientNumber,
      ClaimStatus status) {
    return createClaim(
        id, submissionId, feeCode, uniqueFileNumber, uniqueClientNumber, status, null, null);
  }

  protected Claim createClaim(
      String id,
      String submissionId,
      String feeCode,
      String uniqueFileNumber,
      String uniqueClientNumber,
      ClaimStatus status,
      String submissionPeriod,
      String uniqueCaseId) {
    return createClaim(
        id,
        submissionId,
        feeCode,
        uniqueFileNumber,
        uniqueClientNumber,
        status,
        submissionPeriod,
        uniqueCaseId,
        null);
  }

  protected Claim createClaim(
      String id,
      String submissionId,
      String feeCode,
      String uniqueFileNumber,
      String uniqueClientNumber,
      ClaimStatus status,
      String submissionPeriod,
      String uniqueCaseId,
      String caseConcludedDate) {
    return Claim.builder()
        .id(id != null ? UUID.nameUUIDFromBytes(id.getBytes()) : null)
        .submissionId(submissionId != null ? UUID.nameUUIDFromBytes(submissionId.getBytes()) : null)
        .feeCode(feeCode)
        .uniqueFileNumber(uniqueFileNumber)
        .uniqueClientNumber(uniqueClientNumber)
        .status(status)
        .submissionPeriod(submissionPeriod)
        .uniqueCaseId(uniqueCaseId)
        .caseConcludedDate(caseConcludedDate)
        .build();
  }

  protected ClaimResponse createClaimResponse(
          String id,
          String submissionId,
          String feeCode,
          String uniqueFileNumber,
          String uniqueClientNumber,
          ClaimStatus status) {
    return createClaimResponse(
            id, submissionId, feeCode, uniqueFileNumber, uniqueClientNumber, status, null, null);
  }

  protected ClaimResponse createClaimResponse(
          String id,
          String submissionId,
          String feeCode,
          String uniqueFileNumber,
          String uniqueClientNumber,
          ClaimStatus status,
          String submissionPeriod,
          String uniqueCaseId) {
    return createClaimResponse(
            id,
            submissionId,
            feeCode,
            uniqueFileNumber,
            uniqueClientNumber,
            status,
            submissionPeriod,
            uniqueCaseId,
            null);
  }

  protected ClaimResponse createClaimResponse(
          String id,
          String submissionId,
          String feeCode,
          String uniqueFileNumber,
          String uniqueClientNumber,
          ClaimStatus status,
          String submissionPeriod,
          String uniqueCaseId,
          String caseConcludedDate) {
    return new ClaimResponse()
            .id(id != null ? UUID.nameUUIDFromBytes(id.getBytes()).toString() : null)
            .submissionId(submissionId != null ? UUID.nameUUIDFromBytes(submissionId.getBytes()).toString() : null)
            .feeCode(feeCode)
            .uniqueFileNumber(uniqueFileNumber)
            .uniqueClientNumber(uniqueClientNumber)
            .status(status)
            .submissionPeriod(submissionPeriod)
            .uniqueCaseId(uniqueCaseId)
            .caseConcludedDate(caseConcludedDate);
  }
}
