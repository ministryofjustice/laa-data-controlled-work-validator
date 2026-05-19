package uk.gov.justice.laa.dstew.payments.claims.validation.core.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResponse;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;

@DisplayName("ClaimMapper")
class ClaimMapperTest {

  // -------------------------------------------------------------------------
  // Helper: builds a fully populated ClaimResponse using the fluent API
  // -------------------------------------------------------------------------
  private static final String ID = UUID.randomUUID().toString();
  private static final String SUBMISSION_ID = UUID.randomUUID().toString();

  private static ClaimResponse fullyPopulated() {
    ClaimResponse r = new ClaimResponse();

    // Identity
    r.setId(ID);
    r.setSubmissionId(SUBMISSION_ID);

    // Submission / administrative
    r.setStatus(ClaimStatus.VALID);
    r.setSubmissionPeriod("2025-01");
    r.setLineNumber(3);
    r.setScheduleReference("SC123");
    r.setVersion(7L);

    // Case identifiers
    r.setCaseReferenceNumber("CR001");
    r.setUniqueFileNumber("UFN/2025/001");
    r.setCaseId("CASE-1");
    r.setUniqueCaseId("UCASE-1");

    // Case dates
    r.setCaseStartDate("2024-01-01");
    r.setCaseConcludedDate("2024-06-01");
    r.setRepresentationOrderDate("2024-01-15");
    r.setTransferDate("2024-02-01");
    r.setSurgeryDate("2024-03-10");

    // Fee / scheme
    r.setFeeSchemeCode("FS01");
    r.setFeeCode("FEE1");
    r.setMatterTypeCode("MT01");
    r.setCrimeMatterTypeCode("CMT01");
    r.setSchemeId("SCH01");
    r.setStandardFeeCategoryCode("SFCC1");
    r.setProcurementAreaCode("PA01");
    r.setAccessPointCode("AP01");
    r.setDeliveryLocation("DL01");

    // Crime / police
    r.setDsccNumber("DSCC123");
    r.setMaatId("MAAT99");
    r.setPrisonLawPriorApprovalNumber("PL001");
    r.setIsDutySolicitor(true);
    r.setIsYouthCourt(false);
    r.setSuspectsDefendantsCount(2);
    r.setPoliceStationCourtAttendancesCount(4);
    r.setPoliceStationCourtPrisonId("PSCP1");

    // Case outcome / stage
    r.setCaseStageCode("CST01");
    r.setStageReachedCode("SR01");
    r.setOutcomeCode("OC01");
    r.setFollowOnWork("FOW1");
    r.setExemptionCriteriaSatisfied("ECS1");
    r.setExceptionalCaseFundingReference("ECF1");
    r.setIsLegacyCase(true);
    r.setIsNrmAdvice(false);
    r.setMentalHealthTribunalReference("MHT1");
    r.setDesignatedAccreditedRepresentativeCode("DAR1");

    // Client
    r.setClientForename("Jane");
    r.setClientSurname("Doe");
    r.setClientDateOfBirth("1990-05-20");
    r.setUniqueClientNumber("UCN001");
    r.setClientPostcode("SW1A 1AA");
    r.setGenderCode("F");
    r.setEthnicityCode("A");
    r.setDisabilityCode("N");
    r.setIsLegallyAided(true);
    r.setClientTypeCode("CT1");
    r.setHomeOfficeClientNumber("HO123");
    r.setClaReferenceNumber("CLA99");
    r.setClaExemptionCode("CLAEX1");

    // Client 2
    r.setClient2Forename("John");
    r.setClient2Surname("Smith");
    r.setClient2DateOfBirth("1985-11-11");
    r.setClient2Ucn("UCN002");
    r.setClient2Postcode("EC1A 1BB");
    r.setClient2GenderCode("M");
    r.setClient2EthnicityCode("B");
    r.setClient2DisabilityCode("Y");
    r.setClient2IsLegallyAided(false);

    // Financial
    r.setNetProfitCostsAmount(new BigDecimal("100.00"));
    r.setNetCounselCostsAmount(new BigDecimal("200.00"));
    r.setNetDisbursementAmount(new BigDecimal("50.00"));
    r.setDisbursementsVatAmount(new BigDecimal("10.00"));
    r.setTravelWaitingCostsAmount(new BigDecimal("30.00"));
    r.setNetWaitingCostsAmount(new BigDecimal("20.00"));
    r.setCostsDamagesRecoveredAmount(new BigDecimal("5.00"));
    r.setDetentionTravelWaitingCostsAmount(new BigDecimal("15.00"));
    r.setJrFormFillingAmount(new BigDecimal("25.00"));
    r.setAdjournedHearingFeeAmount(99);
    r.setIsVatApplicable(true);
    r.setIsToleranceApplicable(false);

    // Time
    r.setAdviceTime(60);
    r.setTravelTime(30);
    r.setWaitingTime(15);

    // Hearing / court
    r.setCourtLocationCode("CL01");
    r.setIsLondonRate(true);
    r.setIsAdditionalTravelPayment(false);
    r.setMeetingsAttendedCode("MA1");
    r.setCmrhOralCount(3);
    r.setCmrhTelephoneCount(2);
    r.setAitHearingCentreCode("AHC1");
    r.setIsSubstantiveHearing(true);
    r.setHoInterview(1);

    // Mediation
    r.setMediationSessionsCount(5);
    r.setMediationTimeMinutes(90);
    r.setOutreachLocation("OL1");
    r.setReferralSource("RS1");

    // Surgery / IRC
    r.setIsIrcSurgery(true);
    r.setSurgeryClientsCount(8);
    r.setSurgeryMattersCount(12);
    r.setMedicalReportsCount(4);

    // Application flags / misc
    r.setIsPostalApplicationAccepted(true);
    r.setIsClient2PostalApplicationAccepted(false);
    r.setPriorAuthorityReference("PAR1");
    r.setIsEligibleClient(true);
    r.setAdviceTypeCode("ATC1");
    r.setLocalAuthorityNumber("LAN1");
    r.setCreatedByUserId("USER1");
    r.setIsAmended(false);
    r.setHasAssessment(true);

    return r;
  }

  // -------------------------------------------------------------------------

  @Test
  @DisplayName("returns null when response is null")
  void fromClaimResponse_null() {
    assertThat(ClaimMapper.fromClaimResponse(null)).isNull();
  }

  @Test
  @DisplayName("returns non-null Claim for a populated response")
  void fromClaimResponse_returnsNonNull() {
    assertThat(ClaimMapper.fromClaimResponse(fullyPopulated())).isNotNull();
  }

  @Test
  @DisplayName("throws IllegalArgumentException when id is not a valid UUID")
  void fromClaimResponse_invalidIdThrows() {
    ClaimResponse r = new ClaimResponse();
    r.setId("not-a-uuid");
    assertThatThrownBy(() -> ClaimMapper.fromClaimResponse(r))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  @DisplayName("throws IllegalArgumentException when submissionId is not a valid UUID")
  void fromClaimResponse_invalidSubmissionIdThrows() {
    ClaimResponse r = new ClaimResponse();
    r.setSubmissionId("bad-id");
    assertThatThrownBy(() -> ClaimMapper.fromClaimResponse(r))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Nested
  @DisplayName("Identity")
  class IdentityMapping {
    @Test
    @DisplayName("maps id and submissionId as UUIDs")
    void mapsIds() {
      Claim claim = ClaimMapper.fromClaimResponse(fullyPopulated());
      assertThat(claim.getId()).isEqualTo(UUID.fromString(ID));
      assertThat(claim.getSubmissionId()).isEqualTo(UUID.fromString(SUBMISSION_ID));
    }

    @Test
    @DisplayName("leaves id and submissionId null when source is null")
    void nullIds() {
      ClaimResponse r = new ClaimResponse();
      Claim claim = ClaimMapper.fromClaimResponse(r);
      assertThat(claim.getId()).isNull();
      assertThat(claim.getSubmissionId()).isNull();
    }
  }

  @Nested
  @DisplayName("Submission / Administrative")
  class SubmissionAdministrativeMapping {
    @Test
    @DisplayName("maps status, submissionPeriod, lineNumber, scheduleReference and version")
    void mapsFields() {
      Claim claim = ClaimMapper.fromClaimResponse(fullyPopulated());
      assertThat(claim.getStatus()).isEqualTo(ClaimStatus.VALID);
      assertThat(claim.getSubmissionPeriod()).isEqualTo("2025-01");
      assertThat(claim.getLineNumber()).isEqualTo(3);
      assertThat(claim.getScheduleReference()).isEqualTo("SC123");
      assertThat(claim.getVersion()).isEqualTo(7);
    }

    @Test
    @DisplayName("leaves version null when source version is null")
    void nullVersion() {
      ClaimResponse r = new ClaimResponse();
      Claim claim = ClaimMapper.fromClaimResponse(r);
      assertThat(claim.getVersion()).isNull();
    }

    @Test
    @DisplayName("truncates Long version to Integer")
    void longVersionTruncation() {
      ClaimResponse r = new ClaimResponse();
      r.setVersion(Long.MAX_VALUE);
      Claim claim = ClaimMapper.fromClaimResponse(r);
      assertThat(claim.getVersion()).isEqualTo((int) Long.MAX_VALUE);
    }
  }

  @Nested
  @DisplayName("Case Identifiers")
  class CaseIdentifierMapping {
    @Test
    @DisplayName("maps caseReferenceNumber, uniqueFileNumber, caseId and uniqueCaseId")
    void mapsFields() {
      Claim claim = ClaimMapper.fromClaimResponse(fullyPopulated());
      assertThat(claim.getCaseReferenceNumber()).isEqualTo("CR001");
      assertThat(claim.getUniqueFileNumber()).isEqualTo("UFN/2025/001");
      assertThat(claim.getCaseId()).isEqualTo("CASE-1");
      assertThat(claim.getUniqueCaseId()).isEqualTo("UCASE-1");
    }
  }

  @Nested
  @DisplayName("Case Dates")
  class CaseDateMapping {
    @Test
    @DisplayName("maps all date fields")
    void mapsFields() {
      Claim claim = ClaimMapper.fromClaimResponse(fullyPopulated());
      assertThat(claim.getCaseStartDate()).isEqualTo("2024-01-01");
      assertThat(claim.getCaseConcludedDate()).isEqualTo("2024-06-01");
      assertThat(claim.getRepresentationOrderDate()).isEqualTo("2024-01-15");
      assertThat(claim.getTransferDate()).isEqualTo("2024-02-01");
      assertThat(claim.getSurgeryDate()).isEqualTo("2024-03-10");
    }
  }

  @Nested
  @DisplayName("Fee / Scheme")
  class FeeSchemeMapping {
    @Test
    @DisplayName("maps all fee and scheme fields")
    void mapsFields() {
      Claim claim = ClaimMapper.fromClaimResponse(fullyPopulated());
      assertThat(claim.getFeeSchemeCode()).isEqualTo("FS01");
      assertThat(claim.getFeeCode()).isEqualTo("FEE1");
      assertThat(claim.getMatterTypeCode()).isEqualTo("MT01");
      assertThat(claim.getCrimeMatterTypeCode()).isEqualTo("CMT01");
      assertThat(claim.getSchemeId()).isEqualTo("SCH01");
      assertThat(claim.getStandardFeeCategoryCode()).isEqualTo("SFCC1");
      assertThat(claim.getProcurementAreaCode()).isEqualTo("PA01");
      assertThat(claim.getAccessPointCode()).isEqualTo("AP01");
      assertThat(claim.getDeliveryLocation()).isEqualTo("DL01");
    }
  }

  @Nested
  @DisplayName("Crime / Police")
  class CrimePoliceMapping {
    @Test
    @DisplayName("maps all crime and police fields")
    void mapsFields() {
      Claim claim = ClaimMapper.fromClaimResponse(fullyPopulated());
      assertThat(claim.getDsccNumber()).isEqualTo("DSCC123");
      assertThat(claim.getMaatId()).isEqualTo("MAAT99");
      assertThat(claim.getPrisonLawPriorApprovalNumber()).isEqualTo("PL001");
      assertThat(claim.getIsDutySolicitor()).isTrue();
      assertThat(claim.getIsYouthCourt()).isFalse();
      assertThat(claim.getSuspectsDefendantsCount()).isEqualTo(2);
      assertThat(claim.getPoliceStationCourtAttendancesCount()).isEqualTo(4);
      assertThat(claim.getPoliceStationCourtPrisonId()).isEqualTo("PSCP1");
    }
  }

  @Nested
  @DisplayName("Case Outcome / Stage")
  class CaseOutcomeStageMapping {
    @Test
    @DisplayName("maps all outcome and stage fields")
    void mapsFields() {
      Claim claim = ClaimMapper.fromClaimResponse(fullyPopulated());
      assertThat(claim.getCaseStageCode()).isEqualTo("CST01");
      assertThat(claim.getStageReachedCode()).isEqualTo("SR01");
      assertThat(claim.getOutcomeCode()).isEqualTo("OC01");
      assertThat(claim.getFollowOnWork()).isEqualTo("FOW1");
      assertThat(claim.getExemptionCriteriaSatisfied()).isEqualTo("ECS1");
      assertThat(claim.getExceptionalCaseFundingReference()).isEqualTo("ECF1");
      assertThat(claim.getIsLegacyCase()).isTrue();
      assertThat(claim.getIsNrmAdvice()).isFalse();
      assertThat(claim.getMentalHealthTribunalReference()).isEqualTo("MHT1");
      assertThat(claim.getDesignatedAccreditedRepresentativeCode()).isEqualTo("DAR1");
    }
  }

  @Nested
  @DisplayName("Client")
  class ClientMapping {
    @Test
    @DisplayName("maps all primary client fields")
    void mapsFields() {
      Claim claim = ClaimMapper.fromClaimResponse(fullyPopulated());
      assertThat(claim.getClientForename()).isEqualTo("Jane");
      assertThat(claim.getClientSurname()).isEqualTo("Doe");
      assertThat(claim.getClientDateOfBirth()).isEqualTo("1990-05-20");
      assertThat(claim.getUniqueClientNumber()).isEqualTo("UCN001");
      assertThat(claim.getClientPostcode()).isEqualTo("SW1A 1AA");
      assertThat(claim.getGenderCode()).isEqualTo("F");
      assertThat(claim.getEthnicityCode()).isEqualTo("A");
      assertThat(claim.getDisabilityCode()).isEqualTo("N");
      assertThat(claim.getIsLegallyAided()).isTrue();
      assertThat(claim.getClientTypeCode()).isEqualTo("CT1");
      assertThat(claim.getHomeOfficeClientNumber()).isEqualTo("HO123");
      assertThat(claim.getClaReferenceNumber()).isEqualTo("CLA99");
      assertThat(claim.getClaExemptionCode()).isEqualTo("CLAEX1");
    }
  }

  @Nested
  @DisplayName("Client 2")
  class Client2Mapping {
    @Test
    @DisplayName("maps all secondary client fields")
    void mapsFields() {
      Claim claim = ClaimMapper.fromClaimResponse(fullyPopulated());
      assertThat(claim.getClient2Forename()).isEqualTo("John");
      assertThat(claim.getClient2Surname()).isEqualTo("Smith");
      assertThat(claim.getClient2DateOfBirth()).isEqualTo("1985-11-11");
      assertThat(claim.getClient2Ucn()).isEqualTo("UCN002");
      assertThat(claim.getClient2Postcode()).isEqualTo("EC1A 1BB");
      assertThat(claim.getClient2GenderCode()).isEqualTo("M");
      assertThat(claim.getClient2EthnicityCode()).isEqualTo("B");
      assertThat(claim.getClient2DisabilityCode()).isEqualTo("Y");
      assertThat(claim.getClient2IsLegallyAided()).isFalse();
    }
  }

  @Nested
  @DisplayName("Financial")
  class FinancialMapping {
    @Test
    @DisplayName("maps all financial amount fields and flags")
    void mapsFields() {
      Claim claim = ClaimMapper.fromClaimResponse(fullyPopulated());
      assertThat(claim.getNetProfitCostsAmount()).isEqualByComparingTo("100.00");
      assertThat(claim.getNetCounselCostsAmount()).isEqualByComparingTo("200.00");
      assertThat(claim.getNetDisbursementAmount()).isEqualByComparingTo("50.00");
      assertThat(claim.getDisbursementsVatAmount()).isEqualByComparingTo("10.00");
      assertThat(claim.getTravelWaitingCostsAmount()).isEqualByComparingTo("30.00");
      assertThat(claim.getNetWaitingCostsAmount()).isEqualByComparingTo("20.00");
      assertThat(claim.getCostsDamagesRecoveredAmount()).isEqualByComparingTo("5.00");
      assertThat(claim.getDetentionTravelWaitingCostsAmount()).isEqualByComparingTo("15.00");
      assertThat(claim.getJrFormFillingAmount()).isEqualByComparingTo("25.00");
      assertThat(claim.getAdjournedHearingFeeAmount()).isEqualTo(99);
      assertThat(claim.getIsVatApplicable()).isTrue();
      assertThat(claim.getIsToleranceApplicable()).isFalse();
    }
  }

  @Nested
  @DisplayName("Time")
  class TimeMapping {
    @Test
    @DisplayName("maps adviceTime, travelTime and waitingTime")
    void mapsFields() {
      Claim claim = ClaimMapper.fromClaimResponse(fullyPopulated());
      assertThat(claim.getAdviceTime()).isEqualTo(60);
      assertThat(claim.getTravelTime()).isEqualTo(30);
      assertThat(claim.getWaitingTime()).isEqualTo(15);
    }
  }

  @Nested
  @DisplayName("Hearing / Court")
  class HearingCourtMapping {
    @Test
    @DisplayName("maps all hearing and court fields")
    void mapsFields() {
      Claim claim = ClaimMapper.fromClaimResponse(fullyPopulated());
      assertThat(claim.getCourtLocationCode()).isEqualTo("CL01");
      assertThat(claim.getIsLondonRate()).isTrue();
      assertThat(claim.getIsAdditionalTravelPayment()).isFalse();
      assertThat(claim.getMeetingsAttendedCode()).isEqualTo("MA1");
      assertThat(claim.getCmrhOralCount()).isEqualTo(3);
      assertThat(claim.getCmrhTelephoneCount()).isEqualTo(2);
      assertThat(claim.getAitHearingCentreCode()).isEqualTo("AHC1");
      assertThat(claim.getIsSubstantiveHearing()).isTrue();
      assertThat(claim.getHoInterview()).isEqualTo(1);
    }
  }

  @Nested
  @DisplayName("Mediation")
  class MediationMapping {
    @Test
    @DisplayName("maps all mediation fields")
    void mapsFields() {
      Claim claim = ClaimMapper.fromClaimResponse(fullyPopulated());
      assertThat(claim.getMediationSessionsCount()).isEqualTo(5);
      assertThat(claim.getMediationTimeMinutes()).isEqualTo(90);
      assertThat(claim.getOutreachLocation()).isEqualTo("OL1");
      assertThat(claim.getReferralSource()).isEqualTo("RS1");
    }
  }

  @Nested
  @DisplayName("Surgery / IRC")
  class SurgeryIrcMapping {
    @Test
    @DisplayName("maps all surgery and IRC fields")
    void mapsFields() {
      Claim claim = ClaimMapper.fromClaimResponse(fullyPopulated());
      assertThat(claim.getIsIrcSurgery()).isTrue();
      assertThat(claim.getSurgeryClientsCount()).isEqualTo(8);
      assertThat(claim.getSurgeryMattersCount()).isEqualTo(12);
      assertThat(claim.getMedicalReportsCount()).isEqualTo(4);
    }
  }

  @Nested
  @DisplayName("Application Flags / Misc")
  class ApplicationFlagsMapping {
    @Test
    @DisplayName("maps all application flag and misc fields")
    void mapsFields() {
      Claim claim = ClaimMapper.fromClaimResponse(fullyPopulated());
      assertThat(claim.getIsPostalApplicationAccepted()).isTrue();
      assertThat(claim.getIsClient2PostalApplicationAccepted()).isFalse();
      assertThat(claim.getPriorAuthorityReference()).isEqualTo("PAR1");
      assertThat(claim.getIsEligibleClient()).isTrue();
      assertThat(claim.getAdviceTypeCode()).isEqualTo("ATC1");
      assertThat(claim.getLocalAuthorityNumber()).isEqualTo("LAN1");
      assertThat(claim.getCreatedByUserId()).isEqualTo("USER1");
      assertThat(claim.getIsAmended()).isFalse();
      assertThat(claim.getHasAssessment()).isTrue();
    }
  }

  @Nested
  @DisplayName("Null handling")
  class NullHandling {
    @Test
    @DisplayName("all Claim fields are null when a blank ClaimResponse is provided")
    void allNullForBlankResponse() {
      Claim claim = ClaimMapper.fromClaimResponse(new ClaimResponse());
      assertThat(claim.getId()).isNull();
      assertThat(claim.getSubmissionId()).isNull();
      assertThat(claim.getStatus()).isNull();
      assertThat(claim.getVersion()).isNull();
      assertThat(claim.getFeeCode()).isNull();
      assertThat(claim.getClientDateOfBirth()).isNull();
      assertThat(claim.getNetProfitCostsAmount()).isNull();
    }
  }
}
