package uk.gov.justice.laa.dstew.payments.claims.validation.core.model;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;

/**
 * Internal representation of claim data for validation logic.
 *
 * <p>
 * This class is for internal use only and is not intended for API serialization or external
 * consumption.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Claim implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private AreaOfLaw areaOfLaw;

  private String officeAccountNumber;

  private UUID id;

  private UUID submissionId;

  private ClaimStatus status;

  private Integer lineNumber;

  private String scheduleReference;

  private String submissionPeriod;

  private String caseReferenceNumber;

  private String uniqueFileNumber;

  private String caseStartDate;

  private String caseConcludedDate;

  private String caseId;

  private String uniqueCaseId;

  private String caseStageCode;

  private String matterTypeCode;

  private String crimeMatterTypeCode;

  private String feeSchemeCode;

  private String feeCode;

  private String procurementAreaCode;

  private String accessPointCode;

  private String deliveryLocation;

  private String representationOrderDate;

  private Integer suspectsDefendantsCount;

  private Integer policeStationCourtAttendancesCount;

  private String policeStationCourtPrisonId;

  private String dsccNumber;

  private String maatId;

  private String prisonLawPriorApprovalNumber;

  private Boolean isDutySolicitor;

  private Boolean isYouthCourt;

  private String schemeId;

  private Integer mediationSessionsCount;

  private Integer mediationTimeMinutes;

  private String outreachLocation;

  private String referralSource;

  private String clientForename;

  private String clientSurname;

  private String clientDateOfBirth;

  private String uniqueClientNumber;

  private String clientPostcode;

  private String genderCode;

  private String ethnicityCode;

  private String disabilityCode;

  private Boolean isLegallyAided;

  private String clientTypeCode;

  private String homeOfficeClientNumber;

  private String claReferenceNumber;

  private String claExemptionCode;

  private String client2Forename;

  private String client2Surname;

  private String client2DateOfBirth;

  private String client2Ucn;

  private String client2Postcode;

  private String client2GenderCode;

  private String client2EthnicityCode;

  private String client2DisabilityCode;

  private Boolean client2IsLegallyAided;

  private String stageReachedCode;

  private String standardFeeCategoryCode;

  private String outcomeCode;

  private String designatedAccreditedRepresentativeCode;

  private Boolean isPostalApplicationAccepted;

  private Boolean isClient2PostalApplicationAccepted;

  private String mentalHealthTribunalReference;

  private Boolean isNrmAdvice;

  private String followOnWork;

  private String transferDate;

  private String exemptionCriteriaSatisfied;

  private String exceptionalCaseFundingReference;

  private Boolean isLegacyCase;

  private Integer adviceTime;

  private Integer travelTime;

  private Integer waitingTime;

  private BigDecimal netProfitCostsAmount;

  private BigDecimal netDisbursementAmount;

  private BigDecimal netCounselCostsAmount;

  private BigDecimal disbursementsVatAmount;

  private BigDecimal travelWaitingCostsAmount;

  private BigDecimal netWaitingCostsAmount;

  private Boolean isVatApplicable;

  private Boolean isToleranceApplicable;

  private String priorAuthorityReference;

  private Boolean isLondonRate;

  private Integer adjournedHearingFeeAmount;

  private Boolean isAdditionalTravelPayment;

  private BigDecimal costsDamagesRecoveredAmount;

  private String meetingsAttendedCode;

  private BigDecimal detentionTravelWaitingCostsAmount;

  private BigDecimal jrFormFillingAmount;

  private Boolean isEligibleClient;

  private String courtLocationCode;

  private String adviceTypeCode;

  private Integer medicalReportsCount;

  private Boolean isIrcSurgery;

  private String surgeryDate;

  private Integer surgeryClientsCount;

  private Integer surgeryMattersCount;

  private Integer cmrhOralCount;

  private Integer cmrhTelephoneCount;

  private String aitHearingCentreCode;

  private Boolean isSubstantiveHearing;

  private Integer hoInterview;

  private String localAuthorityNumber;

  private String createdByUserId;

  private Boolean isAmended;

  private Boolean hasAssessment;

  private Integer version;

}
