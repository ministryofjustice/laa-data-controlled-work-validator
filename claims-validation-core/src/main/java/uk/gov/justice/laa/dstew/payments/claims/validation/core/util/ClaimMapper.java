package uk.gov.justice.laa.dstew.payments.claims.validation.core.util;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResponse;

/** Utility class for mapping between different claim model representations. */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ClaimMapper {

  /**
   * Converts a ClaimResponse from the Data Claims API to our internal Claim model.
   *
   * @param response the ClaimResponse from the Data Claims API
   * @return the mapped Claim object
   */
  public static Claim fromClaimResponse(ClaimResponse response) {
    if (response == null) {
      return null;
    }

    Claim claim = new Claim();

    // IDs
    if (response.getId() != null) {
      claim.setId(UUID.fromString(response.getId()));
    }
    if (response.getSubmissionId() != null) {
      claim.setSubmissionId(UUID.fromString(response.getSubmissionId()));
    }

    // Status
    if (response.getStatus() != null) {
      claim.setStatus(mapClaimStatus(response.getStatus()));
    }

    // Basic fields
    claim.setLineNumber(response.getLineNumber());
    claim.setScheduleReference(response.getScheduleReference());
    claim.setCaseReferenceNumber(response.getCaseReferenceNumber());
    claim.setUniqueFileNumber(response.getUniqueFileNumber());
    claim.setCaseStartDate(response.getCaseStartDate());
    claim.setCaseConcludedDate(response.getCaseConcludedDate());
    claim.setMatterTypeCode(response.getMatterTypeCode());
    claim.setCrimeMatterTypeCode(response.getCrimeMatterTypeCode());
    claim.setFeeSchemeCode(response.getFeeSchemeCode());
    claim.setFeeCode(response.getFeeCode());
    claim.setProcurementAreaCode(response.getProcurementAreaCode());
    claim.setAccessPointCode(response.getAccessPointCode());
    claim.setDeliveryLocation(response.getDeliveryLocation());
    claim.setRepresentationOrderDate(response.getRepresentationOrderDate());
    claim.setSuspectsDefendantsCount(response.getSuspectsDefendantsCount());
    claim.setPoliceStationCourtAttendancesCount(response.getPoliceStationCourtAttendancesCount());
    claim.setPoliceStationCourtPrisonId(response.getPoliceStationCourtPrisonId());
    claim.setDsccNumber(response.getDsccNumber());
    claim.setMaatId(response.getMaatId());
    claim.setPrisonLawPriorApprovalNumber(response.getPrisonLawPriorApprovalNumber());
    claim.setIsDutySolicitor(response.getIsDutySolicitor());
    claim.setIsYouthCourt(response.getIsYouthCourt());
    claim.setSchemeId(response.getSchemeId());
    claim.setMediationSessionsCount(response.getMediationSessionsCount());
    claim.setMediationTimeMinutes(response.getMediationTimeMinutes());
    claim.setOutreachLocation(response.getOutreachLocation());
    claim.setReferralSource(response.getReferralSource());

    // Client fields
    claim.setClientForename(response.getClientForename());
    claim.setClientSurname(response.getClientSurname());
    claim.setClientDateOfBirth(response.getClientDateOfBirth());
    claim.setUniqueClientNumber(response.getUniqueClientNumber());
    claim.setClientPostcode(response.getClientPostcode());
    claim.setGenderCode(response.getGenderCode());
    claim.setEthnicityCode(response.getEthnicityCode());
    claim.setDisabilityCode(response.getDisabilityCode());
    claim.setIsLegallyAided(response.getIsLegallyAided());

    // Case fields
    claim.setCaseId(response.getCaseId());
    claim.setUniqueCaseId(response.getUniqueCaseId());
    claim.setOutcomeCode(response.getOutcomeCode());
    claim.setAdviceTypeCode(response.getAdviceTypeCode());
    claim.setCourtLocationCode(response.getCourtLocationCode());
    claim.setTransferDate(response.getTransferDate());
    claim.setPriorAuthorityReference(response.getPriorAuthorityReference());

    // Financial fields
    claim.setNetProfitCostsAmount(response.getNetProfitCostsAmount());
    claim.setNetCounselCostsAmount(response.getNetCounselCostsAmount());
    claim.setNetDisbursementAmount(response.getNetDisbursementAmount());
    claim.setTravelWaitingCostsAmount(response.getTravelWaitingCostsAmount());
    claim.setDisbursementsVatAmount(response.getDisbursementsVatAmount());
    claim.setIsVatApplicable(response.getIsVatApplicable());

    // Time fields
    claim.setAdviceTime(response.getAdviceTime());
    claim.setTravelTime(response.getTravelTime());
    claim.setWaitingTime(response.getWaitingTime());

    return claim;
  }

  /**
   * Maps ClaimStatus from Data Claims API to our internal ClaimStatus.
   *
   * @param status the status from Data Claims API
   * @return the mapped ClaimStatus
   */
  private static ClaimStatus mapClaimStatus(
      uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus status) {
    if (status == null) {
      return null;
    }
    return switch (status) {
      case READY_TO_PROCESS -> ClaimStatus.READY_TO_PROCESS;
      case VALID -> ClaimStatus.VALID;
      case INVALID -> ClaimStatus.INVALID;
      case VOID -> ClaimStatus.VOID;
    };
  }
}
