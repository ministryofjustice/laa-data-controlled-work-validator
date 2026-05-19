package uk.gov.justice.laa.dstew.payments.claims.validation.core.util;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
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

    // --- Identity ---
    if (response.getId() != null) {
      claim.setId(UUID.fromString(response.getId()));
    }
    if (response.getSubmissionId() != null) {
      claim.setSubmissionId(UUID.fromString(response.getSubmissionId()));
    }

    // --- Submission / Administrative ---
    claim.setStatus(response.getStatus());
    claim.setSubmissionPeriod(response.getSubmissionPeriod());
    claim.setLineNumber(response.getLineNumber());
    claim.setScheduleReference(response.getScheduleReference());
    // ClaimResponse.version is Long; Claim.version is Integer
    if (response.getVersion() != null) {
      claim.setVersion(response.getVersion().intValue());
    }

    // --- Case Identifiers ---
    claim.setCaseReferenceNumber(response.getCaseReferenceNumber());
    claim.setUniqueFileNumber(response.getUniqueFileNumber());
    claim.setCaseId(response.getCaseId());
    claim.setUniqueCaseId(response.getUniqueCaseId());

    // --- Case Dates ---
    claim.setCaseStartDate(response.getCaseStartDate());
    claim.setCaseConcludedDate(response.getCaseConcludedDate());
    claim.setRepresentationOrderDate(response.getRepresentationOrderDate());
    claim.setTransferDate(response.getTransferDate());
    claim.setSurgeryDate(response.getSurgeryDate());

    // --- Fee / Scheme ---
    claim.setFeeSchemeCode(response.getFeeSchemeCode());
    claim.setFeeCode(response.getFeeCode());
    claim.setMatterTypeCode(response.getMatterTypeCode());
    claim.setCrimeMatterTypeCode(response.getCrimeMatterTypeCode());
    claim.setSchemeId(response.getSchemeId());
    claim.setStandardFeeCategoryCode(response.getStandardFeeCategoryCode());
    claim.setProcurementAreaCode(response.getProcurementAreaCode());
    claim.setAccessPointCode(response.getAccessPointCode());
    claim.setDeliveryLocation(response.getDeliveryLocation());

    // --- Crime / Police ---
    claim.setDsccNumber(response.getDsccNumber());
    claim.setMaatId(response.getMaatId());
    claim.setPrisonLawPriorApprovalNumber(response.getPrisonLawPriorApprovalNumber());
    claim.setIsDutySolicitor(response.getIsDutySolicitor());
    claim.setIsYouthCourt(response.getIsYouthCourt());
    claim.setSuspectsDefendantsCount(response.getSuspectsDefendantsCount());
    claim.setPoliceStationCourtAttendancesCount(response.getPoliceStationCourtAttendancesCount());
    claim.setPoliceStationCourtPrisonId(response.getPoliceStationCourtPrisonId());

    // --- Case Outcome / Stage ---
    claim.setCaseStageCode(response.getCaseStageCode());
    claim.setStageReachedCode(response.getStageReachedCode());
    claim.setOutcomeCode(response.getOutcomeCode());
    claim.setFollowOnWork(response.getFollowOnWork());
    claim.setExemptionCriteriaSatisfied(response.getExemptionCriteriaSatisfied());
    claim.setExceptionalCaseFundingReference(response.getExceptionalCaseFundingReference());
    claim.setIsLegacyCase(response.getIsLegacyCase());
    claim.setIsNrmAdvice(response.getIsNrmAdvice());
    claim.setMentalHealthTribunalReference(response.getMentalHealthTribunalReference());
    claim.setDesignatedAccreditedRepresentativeCode(
        response.getDesignatedAccreditedRepresentativeCode());

    // --- Client ---
    claim.setClientForename(response.getClientForename());
    claim.setClientSurname(response.getClientSurname());
    claim.setClientDateOfBirth(response.getClientDateOfBirth());
    claim.setUniqueClientNumber(response.getUniqueClientNumber());
    claim.setClientPostcode(response.getClientPostcode());
    claim.setGenderCode(response.getGenderCode());
    claim.setEthnicityCode(response.getEthnicityCode());
    claim.setDisabilityCode(response.getDisabilityCode());
    claim.setIsLegallyAided(response.getIsLegallyAided());
    claim.setClientTypeCode(response.getClientTypeCode());
    claim.setHomeOfficeClientNumber(response.getHomeOfficeClientNumber());
    claim.setClaReferenceNumber(response.getClaReferenceNumber());
    claim.setClaExemptionCode(response.getClaExemptionCode());

    // --- Client 2 ---
    claim.setClient2Forename(response.getClient2Forename());
    claim.setClient2Surname(response.getClient2Surname());
    claim.setClient2DateOfBirth(response.getClient2DateOfBirth());
    claim.setClient2Ucn(response.getClient2Ucn());
    claim.setClient2Postcode(response.getClient2Postcode());
    claim.setClient2GenderCode(response.getClient2GenderCode());
    claim.setClient2EthnicityCode(response.getClient2EthnicityCode());
    claim.setClient2DisabilityCode(response.getClient2DisabilityCode());
    claim.setClient2IsLegallyAided(response.getClient2IsLegallyAided());

    // --- Financial ---
    claim.setNetProfitCostsAmount(response.getNetProfitCostsAmount());
    claim.setNetCounselCostsAmount(response.getNetCounselCostsAmount());
    claim.setNetDisbursementAmount(response.getNetDisbursementAmount());
    claim.setDisbursementsVatAmount(response.getDisbursementsVatAmount());
    claim.setTravelWaitingCostsAmount(response.getTravelWaitingCostsAmount());
    claim.setNetWaitingCostsAmount(response.getNetWaitingCostsAmount());
    claim.setCostsDamagesRecoveredAmount(response.getCostsDamagesRecoveredAmount());
    claim.setDetentionTravelWaitingCostsAmount(response.getDetentionTravelWaitingCostsAmount());
    claim.setJrFormFillingAmount(response.getJrFormFillingAmount());
    claim.setAdjournedHearingFeeAmount(response.getAdjournedHearingFeeAmount());
    claim.setIsVatApplicable(response.getIsVatApplicable());
    claim.setIsToleranceApplicable(response.getIsToleranceApplicable());

    // --- Time ---
    claim.setAdviceTime(response.getAdviceTime());
    claim.setTravelTime(response.getTravelTime());
    claim.setWaitingTime(response.getWaitingTime());

    // --- Hearing / Court ---
    claim.setCourtLocationCode(response.getCourtLocationCode());
    claim.setIsLondonRate(response.getIsLondonRate());
    claim.setIsAdditionalTravelPayment(response.getIsAdditionalTravelPayment());
    claim.setMeetingsAttendedCode(response.getMeetingsAttendedCode());
    claim.setCmrhOralCount(response.getCmrhOralCount());
    claim.setCmrhTelephoneCount(response.getCmrhTelephoneCount());
    claim.setAitHearingCentreCode(response.getAitHearingCentreCode());
    claim.setIsSubstantiveHearing(response.getIsSubstantiveHearing());
    claim.setHoInterview(response.getHoInterview());

    // --- Mediation ---
    claim.setMediationSessionsCount(response.getMediationSessionsCount());
    claim.setMediationTimeMinutes(response.getMediationTimeMinutes());
    claim.setOutreachLocation(response.getOutreachLocation());
    claim.setReferralSource(response.getReferralSource());

    // --- Surgery / IRC ---
    claim.setIsIrcSurgery(response.getIsIrcSurgery());
    claim.setSurgeryClientsCount(response.getSurgeryClientsCount());
    claim.setSurgeryMattersCount(response.getSurgeryMattersCount());
    claim.setMedicalReportsCount(response.getMedicalReportsCount());

    // --- Application Flags / Misc ---
    claim.setIsPostalApplicationAccepted(response.getIsPostalApplicationAccepted());
    claim.setIsClient2PostalApplicationAccepted(response.getIsClient2PostalApplicationAccepted());
    claim.setPriorAuthorityReference(response.getPriorAuthorityReference());
    claim.setIsEligibleClient(response.getIsEligibleClient());
    claim.setAdviceTypeCode(response.getAdviceTypeCode());
    claim.setLocalAuthorityNumber(response.getLocalAuthorityNumber());
    claim.setCreatedByUserId(response.getCreatedByUserId());
    claim.setIsAmended(response.getIsAmended());
    claim.setHasAssessment(response.getHasAssessment());

    return claim;
  }
}
