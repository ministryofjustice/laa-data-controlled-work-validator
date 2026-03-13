package uk.gov.justice.laa.dstew.payments.claimsdata.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.math.BigDecimal;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Claim details
 */

@Schema(name = "claim_base", description = "Claim details")
@JsonTypeName("claim_base")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T15:47:02.322440Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ClaimBase implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String id;

  private @Nullable String submissionId;

  private @Nullable ClaimStatus status;

  private @Nullable String scheduleReference;

  private @Nullable Integer lineNumber;

  private @Nullable String caseReferenceNumber;

  private @Nullable String uniqueFileNumber;

  private @Nullable String caseStartDate;

  private @Nullable String caseConcludedDate;

  private @Nullable String matterTypeCode;

  private @Nullable String crimeMatterTypeCode;

  private @Nullable String feeSchemeCode;

  private @Nullable String feeCode;

  private @Nullable String procurementAreaCode;

  private @Nullable String accessPointCode;

  private @Nullable String deliveryLocation;

  private @Nullable String representationOrderDate;

  private @Nullable Integer suspectsDefendantsCount;

  private @Nullable Integer policeStationCourtAttendancesCount;

  private @Nullable String policeStationCourtPrisonId;

  private @Nullable String dsccNumber;

  private @Nullable String maatId;

  private @Nullable String prisonLawPriorApprovalNumber;

  private @Nullable Boolean isDutySolicitor;

  private @Nullable Boolean isYouthCourt;

  private @Nullable String schemeId;

  private @Nullable Integer mediationSessionsCount;

  private @Nullable Integer mediationTimeMinutes;

  private @Nullable String outreachLocation;

  private @Nullable String referralSource;

  private @Nullable String clientForename;

  private @Nullable String clientSurname;

  private @Nullable String clientDateOfBirth;

  private @Nullable String uniqueClientNumber;

  private @Nullable String clientPostcode;

  private @Nullable String genderCode;

  private @Nullable String ethnicityCode;

  private @Nullable String disabilityCode;

  private @Nullable Boolean isLegallyAided;

  private @Nullable String clientTypeCode;

  private @Nullable String homeOfficeClientNumber;

  private @Nullable String claReferenceNumber;

  private @Nullable String claExemptionCode;

  private @Nullable String client2Forename;

  private @Nullable String client2Surname;

  private @Nullable String client2DateOfBirth;

  private @Nullable String client2Ucn;

  private @Nullable String client2Postcode;

  private @Nullable String client2GenderCode;

  private @Nullable String client2EthnicityCode;

  private @Nullable String client2DisabilityCode;

  private @Nullable Boolean client2IsLegallyAided;

  private @Nullable String caseId;

  private @Nullable String uniqueCaseId;

  private @Nullable String caseStageCode;

  private @Nullable String stageReachedCode;

  private @Nullable String standardFeeCategoryCode;

  private @Nullable String outcomeCode;

  private @Nullable String designatedAccreditedRepresentativeCode;

  private @Nullable Boolean isPostalApplicationAccepted;

  private @Nullable Boolean isClient2PostalApplicationAccepted;

  private @Nullable String mentalHealthTribunalReference;

  private @Nullable Boolean isNrmAdvice;

  private @Nullable String followOnWork;

  private @Nullable String transferDate;

  private @Nullable String exemptionCriteriaSatisfied;

  private @Nullable String exceptionalCaseFundingReference;

  private @Nullable Boolean isLegacyCase;

  private @Nullable Integer adviceTime;

  private @Nullable Integer travelTime;

  private @Nullable Integer waitingTime;

  private @Nullable BigDecimal netProfitCostsAmount;

  private @Nullable BigDecimal netDisbursementAmount;

  private @Nullable BigDecimal netCounselCostsAmount;

  private @Nullable BigDecimal disbursementsVatAmount;

  private @Nullable BigDecimal travelWaitingCostsAmount;

  private @Nullable BigDecimal netWaitingCostsAmount;

  private @Nullable Boolean isVatApplicable;

  private @Nullable Boolean isToleranceApplicable;

  private @Nullable String priorAuthorityReference;

  private @Nullable Boolean isLondonRate;

  private @Nullable Integer adjournedHearingFeeAmount;

  private @Nullable Boolean isAdditionalTravelPayment;

  private @Nullable BigDecimal costsDamagesRecoveredAmount;

  private @Nullable String meetingsAttendedCode;

  private @Nullable BigDecimal detentionTravelWaitingCostsAmount;

  private @Nullable BigDecimal jrFormFillingAmount;

  private @Nullable Boolean isEligibleClient;

  private @Nullable String courtLocationCode;

  private @Nullable String adviceTypeCode;

  private @Nullable Integer medicalReportsCount;

  private @Nullable Boolean isIrcSurgery;

  private @Nullable String surgeryDate;

  private @Nullable Integer surgeryClientsCount;

  private @Nullable Integer surgeryMattersCount;

  private @Nullable Integer cmrhOralCount;

  private @Nullable Integer cmrhTelephoneCount;

  private @Nullable String aitHearingCentreCode;

  private @Nullable Boolean isSubstantiveHearing;

  private @Nullable Integer hoInterview;

  private @Nullable String localAuthorityNumber;

  private @Nullable String submissionPeriod;

  private @Nullable String createdByUserId;

  private @Nullable Boolean isAmended;

  private @Nullable Boolean hasAssessment;

  private @Nullable Long version;

  public ClaimBase id(@Nullable String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public @Nullable String getId() {
    return id;
  }

  public void setId(@Nullable String id) {
    this.id = id;
  }

  public ClaimBase submissionId(@Nullable String submissionId) {
    this.submissionId = submissionId;
    return this;
  }

  /**
   * Get submissionId
   * @return submissionId
   */
  
  @Schema(name = "submission_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("submission_id")
  public @Nullable String getSubmissionId() {
    return submissionId;
  }

  public void setSubmissionId(@Nullable String submissionId) {
    this.submissionId = submissionId;
  }

  public ClaimBase status(@Nullable ClaimStatus status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   */
  @Valid 
  @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public @Nullable ClaimStatus getStatus() {
    return status;
  }

  public void setStatus(@Nullable ClaimStatus status) {
    this.status = status;
  }

  public ClaimBase scheduleReference(@Nullable String scheduleReference) {
    this.scheduleReference = scheduleReference;
    return this;
  }

  /**
   * Get scheduleReference
   * @return scheduleReference
   */
  
  @Schema(name = "schedule_reference", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("schedule_reference")
  public @Nullable String getScheduleReference() {
    return scheduleReference;
  }

  public void setScheduleReference(@Nullable String scheduleReference) {
    this.scheduleReference = scheduleReference;
  }

  public ClaimBase lineNumber(@Nullable Integer lineNumber) {
    this.lineNumber = lineNumber;
    return this;
  }

  /**
   * Get lineNumber
   * @return lineNumber
   */
  
  @Schema(name = "line_number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("line_number")
  public @Nullable Integer getLineNumber() {
    return lineNumber;
  }

  public void setLineNumber(@Nullable Integer lineNumber) {
    this.lineNumber = lineNumber;
  }

  public ClaimBase caseReferenceNumber(@Nullable String caseReferenceNumber) {
    this.caseReferenceNumber = caseReferenceNumber;
    return this;
  }

  /**
   * Get caseReferenceNumber
   * @return caseReferenceNumber
   */
  
  @Schema(name = "case_reference_number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("case_reference_number")
  public @Nullable String getCaseReferenceNumber() {
    return caseReferenceNumber;
  }

  public void setCaseReferenceNumber(@Nullable String caseReferenceNumber) {
    this.caseReferenceNumber = caseReferenceNumber;
  }

  public ClaimBase uniqueFileNumber(@Nullable String uniqueFileNumber) {
    this.uniqueFileNumber = uniqueFileNumber;
    return this;
  }

  /**
   * Get uniqueFileNumber
   * @return uniqueFileNumber
   */
  
  @Schema(name = "unique_file_number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("unique_file_number")
  public @Nullable String getUniqueFileNumber() {
    return uniqueFileNumber;
  }

  public void setUniqueFileNumber(@Nullable String uniqueFileNumber) {
    this.uniqueFileNumber = uniqueFileNumber;
  }

  public ClaimBase caseStartDate(@Nullable String caseStartDate) {
    this.caseStartDate = caseStartDate;
    return this;
  }

  /**
   * Date the case was started (format DD/MM/YYYY)
   * @return caseStartDate
   */
  
  @Schema(name = "case_start_date", description = "Date the case was started (format DD/MM/YYYY)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("case_start_date")
  public @Nullable String getCaseStartDate() {
    return caseStartDate;
  }

  public void setCaseStartDate(@Nullable String caseStartDate) {
    this.caseStartDate = caseStartDate;
  }

  public ClaimBase caseConcludedDate(@Nullable String caseConcludedDate) {
    this.caseConcludedDate = caseConcludedDate;
    return this;
  }

  /**
   * Date the case was concluded (format DD/MM/YYYY)
   * @return caseConcludedDate
   */
  
  @Schema(name = "case_concluded_date", description = "Date the case was concluded (format DD/MM/YYYY)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("case_concluded_date")
  public @Nullable String getCaseConcludedDate() {
    return caseConcludedDate;
  }

  public void setCaseConcludedDate(@Nullable String caseConcludedDate) {
    this.caseConcludedDate = caseConcludedDate;
  }

  public ClaimBase matterTypeCode(@Nullable String matterTypeCode) {
    this.matterTypeCode = matterTypeCode;
    return this;
  }

  /**
   * Get matterTypeCode
   * @return matterTypeCode
   */
  
  @Schema(name = "matter_type_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("matter_type_code")
  public @Nullable String getMatterTypeCode() {
    return matterTypeCode;
  }

  public void setMatterTypeCode(@Nullable String matterTypeCode) {
    this.matterTypeCode = matterTypeCode;
  }

  public ClaimBase crimeMatterTypeCode(@Nullable String crimeMatterTypeCode) {
    this.crimeMatterTypeCode = crimeMatterTypeCode;
    return this;
  }

  /**
   * Get crimeMatterTypeCode
   * @return crimeMatterTypeCode
   */
  
  @Schema(name = "crime_matter_type_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("crime_matter_type_code")
  public @Nullable String getCrimeMatterTypeCode() {
    return crimeMatterTypeCode;
  }

  public void setCrimeMatterTypeCode(@Nullable String crimeMatterTypeCode) {
    this.crimeMatterTypeCode = crimeMatterTypeCode;
  }

  public ClaimBase feeSchemeCode(@Nullable String feeSchemeCode) {
    this.feeSchemeCode = feeSchemeCode;
    return this;
  }

  /**
   * Get feeSchemeCode
   * @return feeSchemeCode
   */
  
  @Schema(name = "fee_scheme_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fee_scheme_code")
  public @Nullable String getFeeSchemeCode() {
    return feeSchemeCode;
  }

  public void setFeeSchemeCode(@Nullable String feeSchemeCode) {
    this.feeSchemeCode = feeSchemeCode;
  }

  public ClaimBase feeCode(@Nullable String feeCode) {
    this.feeCode = feeCode;
    return this;
  }

  /**
   * Get feeCode
   * @return feeCode
   */
  
  @Schema(name = "fee_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fee_code")
  public @Nullable String getFeeCode() {
    return feeCode;
  }

  public void setFeeCode(@Nullable String feeCode) {
    this.feeCode = feeCode;
  }

  public ClaimBase procurementAreaCode(@Nullable String procurementAreaCode) {
    this.procurementAreaCode = procurementAreaCode;
    return this;
  }

  /**
   * Get procurementAreaCode
   * @return procurementAreaCode
   */
  
  @Schema(name = "procurement_area_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("procurement_area_code")
  public @Nullable String getProcurementAreaCode() {
    return procurementAreaCode;
  }

  public void setProcurementAreaCode(@Nullable String procurementAreaCode) {
    this.procurementAreaCode = procurementAreaCode;
  }

  public ClaimBase accessPointCode(@Nullable String accessPointCode) {
    this.accessPointCode = accessPointCode;
    return this;
  }

  /**
   * Get accessPointCode
   * @return accessPointCode
   */
  
  @Schema(name = "access_point_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("access_point_code")
  public @Nullable String getAccessPointCode() {
    return accessPointCode;
  }

  public void setAccessPointCode(@Nullable String accessPointCode) {
    this.accessPointCode = accessPointCode;
  }

  public ClaimBase deliveryLocation(@Nullable String deliveryLocation) {
    this.deliveryLocation = deliveryLocation;
    return this;
  }

  /**
   * Get deliveryLocation
   * @return deliveryLocation
   */
  
  @Schema(name = "delivery_location", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("delivery_location")
  public @Nullable String getDeliveryLocation() {
    return deliveryLocation;
  }

  public void setDeliveryLocation(@Nullable String deliveryLocation) {
    this.deliveryLocation = deliveryLocation;
  }

  public ClaimBase representationOrderDate(@Nullable String representationOrderDate) {
    this.representationOrderDate = representationOrderDate;
    return this;
  }

  /**
   * Date the rep order was created (format DD/MM/YYYY)
   * @return representationOrderDate
   */
  
  @Schema(name = "representation_order_date", description = "Date the rep order was created (format DD/MM/YYYY)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("representation_order_date")
  public @Nullable String getRepresentationOrderDate() {
    return representationOrderDate;
  }

  public void setRepresentationOrderDate(@Nullable String representationOrderDate) {
    this.representationOrderDate = representationOrderDate;
  }

  public ClaimBase suspectsDefendantsCount(@Nullable Integer suspectsDefendantsCount) {
    this.suspectsDefendantsCount = suspectsDefendantsCount;
    return this;
  }

  /**
   * Get suspectsDefendantsCount
   * @return suspectsDefendantsCount
   */
  
  @Schema(name = "suspects_defendants_count", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("suspects_defendants_count")
  public @Nullable Integer getSuspectsDefendantsCount() {
    return suspectsDefendantsCount;
  }

  public void setSuspectsDefendantsCount(@Nullable Integer suspectsDefendantsCount) {
    this.suspectsDefendantsCount = suspectsDefendantsCount;
  }

  public ClaimBase policeStationCourtAttendancesCount(@Nullable Integer policeStationCourtAttendancesCount) {
    this.policeStationCourtAttendancesCount = policeStationCourtAttendancesCount;
    return this;
  }

  /**
   * Get policeStationCourtAttendancesCount
   * @return policeStationCourtAttendancesCount
   */
  
  @Schema(name = "police_station_court_attendances_count", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("police_station_court_attendances_count")
  public @Nullable Integer getPoliceStationCourtAttendancesCount() {
    return policeStationCourtAttendancesCount;
  }

  public void setPoliceStationCourtAttendancesCount(@Nullable Integer policeStationCourtAttendancesCount) {
    this.policeStationCourtAttendancesCount = policeStationCourtAttendancesCount;
  }

  public ClaimBase policeStationCourtPrisonId(@Nullable String policeStationCourtPrisonId) {
    this.policeStationCourtPrisonId = policeStationCourtPrisonId;
    return this;
  }

  /**
   * Get policeStationCourtPrisonId
   * @return policeStationCourtPrisonId
   */
  
  @Schema(name = "police_station_court_prison_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("police_station_court_prison_id")
  public @Nullable String getPoliceStationCourtPrisonId() {
    return policeStationCourtPrisonId;
  }

  public void setPoliceStationCourtPrisonId(@Nullable String policeStationCourtPrisonId) {
    this.policeStationCourtPrisonId = policeStationCourtPrisonId;
  }

  public ClaimBase dsccNumber(@Nullable String dsccNumber) {
    this.dsccNumber = dsccNumber;
    return this;
  }

  /**
   * Get dsccNumber
   * @return dsccNumber
   */
  
  @Schema(name = "dscc_number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dscc_number")
  public @Nullable String getDsccNumber() {
    return dsccNumber;
  }

  public void setDsccNumber(@Nullable String dsccNumber) {
    this.dsccNumber = dsccNumber;
  }

  public ClaimBase maatId(@Nullable String maatId) {
    this.maatId = maatId;
    return this;
  }

  /**
   * Get maatId
   * @return maatId
   */
  
  @Schema(name = "maat_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("maat_id")
  public @Nullable String getMaatId() {
    return maatId;
  }

  public void setMaatId(@Nullable String maatId) {
    this.maatId = maatId;
  }

  public ClaimBase prisonLawPriorApprovalNumber(@Nullable String prisonLawPriorApprovalNumber) {
    this.prisonLawPriorApprovalNumber = prisonLawPriorApprovalNumber;
    return this;
  }

  /**
   * Get prisonLawPriorApprovalNumber
   * @return prisonLawPriorApprovalNumber
   */
  
  @Schema(name = "prison_law_prior_approval_number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prison_law_prior_approval_number")
  public @Nullable String getPrisonLawPriorApprovalNumber() {
    return prisonLawPriorApprovalNumber;
  }

  public void setPrisonLawPriorApprovalNumber(@Nullable String prisonLawPriorApprovalNumber) {
    this.prisonLawPriorApprovalNumber = prisonLawPriorApprovalNumber;
  }

  public ClaimBase isDutySolicitor(@Nullable Boolean isDutySolicitor) {
    this.isDutySolicitor = isDutySolicitor;
    return this;
  }

  /**
   * Get isDutySolicitor
   * @return isDutySolicitor
   */
  
  @Schema(name = "is_duty_solicitor", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_duty_solicitor")
  public @Nullable Boolean getIsDutySolicitor() {
    return isDutySolicitor;
  }

  public void setIsDutySolicitor(@Nullable Boolean isDutySolicitor) {
    this.isDutySolicitor = isDutySolicitor;
  }

  public ClaimBase isYouthCourt(@Nullable Boolean isYouthCourt) {
    this.isYouthCourt = isYouthCourt;
    return this;
  }

  /**
   * Get isYouthCourt
   * @return isYouthCourt
   */
  
  @Schema(name = "is_youth_court", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_youth_court")
  public @Nullable Boolean getIsYouthCourt() {
    return isYouthCourt;
  }

  public void setIsYouthCourt(@Nullable Boolean isYouthCourt) {
    this.isYouthCourt = isYouthCourt;
  }

  public ClaimBase schemeId(@Nullable String schemeId) {
    this.schemeId = schemeId;
    return this;
  }

  /**
   * Get schemeId
   * @return schemeId
   */
  
  @Schema(name = "scheme_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("scheme_id")
  public @Nullable String getSchemeId() {
    return schemeId;
  }

  public void setSchemeId(@Nullable String schemeId) {
    this.schemeId = schemeId;
  }

  public ClaimBase mediationSessionsCount(@Nullable Integer mediationSessionsCount) {
    this.mediationSessionsCount = mediationSessionsCount;
    return this;
  }

  /**
   * Get mediationSessionsCount
   * @return mediationSessionsCount
   */
  
  @Schema(name = "mediation_sessions_count", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mediation_sessions_count")
  public @Nullable Integer getMediationSessionsCount() {
    return mediationSessionsCount;
  }

  public void setMediationSessionsCount(@Nullable Integer mediationSessionsCount) {
    this.mediationSessionsCount = mediationSessionsCount;
  }

  public ClaimBase mediationTimeMinutes(@Nullable Integer mediationTimeMinutes) {
    this.mediationTimeMinutes = mediationTimeMinutes;
    return this;
  }

  /**
   * Get mediationTimeMinutes
   * @return mediationTimeMinutes
   */
  
  @Schema(name = "mediation_time_minutes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mediation_time_minutes")
  public @Nullable Integer getMediationTimeMinutes() {
    return mediationTimeMinutes;
  }

  public void setMediationTimeMinutes(@Nullable Integer mediationTimeMinutes) {
    this.mediationTimeMinutes = mediationTimeMinutes;
  }

  public ClaimBase outreachLocation(@Nullable String outreachLocation) {
    this.outreachLocation = outreachLocation;
    return this;
  }

  /**
   * Get outreachLocation
   * @return outreachLocation
   */
  
  @Schema(name = "outreach_location", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("outreach_location")
  public @Nullable String getOutreachLocation() {
    return outreachLocation;
  }

  public void setOutreachLocation(@Nullable String outreachLocation) {
    this.outreachLocation = outreachLocation;
  }

  public ClaimBase referralSource(@Nullable String referralSource) {
    this.referralSource = referralSource;
    return this;
  }

  /**
   * Get referralSource
   * @return referralSource
   */
  
  @Schema(name = "referral_source", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("referral_source")
  public @Nullable String getReferralSource() {
    return referralSource;
  }

  public void setReferralSource(@Nullable String referralSource) {
    this.referralSource = referralSource;
  }

  public ClaimBase clientForename(@Nullable String clientForename) {
    this.clientForename = clientForename;
    return this;
  }

  /**
   * Get clientForename
   * @return clientForename
   */
  
  @Schema(name = "client_forename", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client_forename")
  public @Nullable String getClientForename() {
    return clientForename;
  }

  public void setClientForename(@Nullable String clientForename) {
    this.clientForename = clientForename;
  }

  public ClaimBase clientSurname(@Nullable String clientSurname) {
    this.clientSurname = clientSurname;
    return this;
  }

  /**
   * Get clientSurname
   * @return clientSurname
   */
  
  @Schema(name = "client_surname", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client_surname")
  public @Nullable String getClientSurname() {
    return clientSurname;
  }

  public void setClientSurname(@Nullable String clientSurname) {
    this.clientSurname = clientSurname;
  }

  public ClaimBase clientDateOfBirth(@Nullable String clientDateOfBirth) {
    this.clientDateOfBirth = clientDateOfBirth;
    return this;
  }

  /**
   * Client's date of birth (format DD/MM/YYYY)
   * @return clientDateOfBirth
   */
  
  @Schema(name = "client_date_of_birth", description = "Client's date of birth (format DD/MM/YYYY)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client_date_of_birth")
  public @Nullable String getClientDateOfBirth() {
    return clientDateOfBirth;
  }

  public void setClientDateOfBirth(@Nullable String clientDateOfBirth) {
    this.clientDateOfBirth = clientDateOfBirth;
  }

  public ClaimBase uniqueClientNumber(@Nullable String uniqueClientNumber) {
    this.uniqueClientNumber = uniqueClientNumber;
    return this;
  }

  /**
   * Get uniqueClientNumber
   * @return uniqueClientNumber
   */
  
  @Schema(name = "unique_client_number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("unique_client_number")
  public @Nullable String getUniqueClientNumber() {
    return uniqueClientNumber;
  }

  public void setUniqueClientNumber(@Nullable String uniqueClientNumber) {
    this.uniqueClientNumber = uniqueClientNumber;
  }

  public ClaimBase clientPostcode(@Nullable String clientPostcode) {
    this.clientPostcode = clientPostcode;
    return this;
  }

  /**
   * Get clientPostcode
   * @return clientPostcode
   */
  
  @Schema(name = "client_postcode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client_postcode")
  public @Nullable String getClientPostcode() {
    return clientPostcode;
  }

  public void setClientPostcode(@Nullable String clientPostcode) {
    this.clientPostcode = clientPostcode;
  }

  public ClaimBase genderCode(@Nullable String genderCode) {
    this.genderCode = genderCode;
    return this;
  }

  /**
   * Get genderCode
   * @return genderCode
   */
  
  @Schema(name = "gender_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("gender_code")
  public @Nullable String getGenderCode() {
    return genderCode;
  }

  public void setGenderCode(@Nullable String genderCode) {
    this.genderCode = genderCode;
  }

  public ClaimBase ethnicityCode(@Nullable String ethnicityCode) {
    this.ethnicityCode = ethnicityCode;
    return this;
  }

  /**
   * Get ethnicityCode
   * @return ethnicityCode
   */
  
  @Schema(name = "ethnicity_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ethnicity_code")
  public @Nullable String getEthnicityCode() {
    return ethnicityCode;
  }

  public void setEthnicityCode(@Nullable String ethnicityCode) {
    this.ethnicityCode = ethnicityCode;
  }

  public ClaimBase disabilityCode(@Nullable String disabilityCode) {
    this.disabilityCode = disabilityCode;
    return this;
  }

  /**
   * Get disabilityCode
   * @return disabilityCode
   */
  
  @Schema(name = "disability_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("disability_code")
  public @Nullable String getDisabilityCode() {
    return disabilityCode;
  }

  public void setDisabilityCode(@Nullable String disabilityCode) {
    this.disabilityCode = disabilityCode;
  }

  public ClaimBase isLegallyAided(@Nullable Boolean isLegallyAided) {
    this.isLegallyAided = isLegallyAided;
    return this;
  }

  /**
   * Get isLegallyAided
   * @return isLegallyAided
   */
  
  @Schema(name = "is_legally_aided", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_legally_aided")
  public @Nullable Boolean getIsLegallyAided() {
    return isLegallyAided;
  }

  public void setIsLegallyAided(@Nullable Boolean isLegallyAided) {
    this.isLegallyAided = isLegallyAided;
  }

  public ClaimBase clientTypeCode(@Nullable String clientTypeCode) {
    this.clientTypeCode = clientTypeCode;
    return this;
  }

  /**
   * Get clientTypeCode
   * @return clientTypeCode
   */
  
  @Schema(name = "client_type_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client_type_code")
  public @Nullable String getClientTypeCode() {
    return clientTypeCode;
  }

  public void setClientTypeCode(@Nullable String clientTypeCode) {
    this.clientTypeCode = clientTypeCode;
  }

  public ClaimBase homeOfficeClientNumber(@Nullable String homeOfficeClientNumber) {
    this.homeOfficeClientNumber = homeOfficeClientNumber;
    return this;
  }

  /**
   * Get homeOfficeClientNumber
   * @return homeOfficeClientNumber
   */
  
  @Schema(name = "home_office_client_number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("home_office_client_number")
  public @Nullable String getHomeOfficeClientNumber() {
    return homeOfficeClientNumber;
  }

  public void setHomeOfficeClientNumber(@Nullable String homeOfficeClientNumber) {
    this.homeOfficeClientNumber = homeOfficeClientNumber;
  }

  public ClaimBase claReferenceNumber(@Nullable String claReferenceNumber) {
    this.claReferenceNumber = claReferenceNumber;
    return this;
  }

  /**
   * Get claReferenceNumber
   * @return claReferenceNumber
   */
  
  @Schema(name = "cla_reference_number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cla_reference_number")
  public @Nullable String getClaReferenceNumber() {
    return claReferenceNumber;
  }

  public void setClaReferenceNumber(@Nullable String claReferenceNumber) {
    this.claReferenceNumber = claReferenceNumber;
  }

  public ClaimBase claExemptionCode(@Nullable String claExemptionCode) {
    this.claExemptionCode = claExemptionCode;
    return this;
  }

  /**
   * Get claExemptionCode
   * @return claExemptionCode
   */
  
  @Schema(name = "cla_exemption_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cla_exemption_code")
  public @Nullable String getClaExemptionCode() {
    return claExemptionCode;
  }

  public void setClaExemptionCode(@Nullable String claExemptionCode) {
    this.claExemptionCode = claExemptionCode;
  }

  public ClaimBase client2Forename(@Nullable String client2Forename) {
    this.client2Forename = client2Forename;
    return this;
  }

  /**
   * Get client2Forename
   * @return client2Forename
   */
  
  @Schema(name = "client_2_forename", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client_2_forename")
  public @Nullable String getClient2Forename() {
    return client2Forename;
  }

  public void setClient2Forename(@Nullable String client2Forename) {
    this.client2Forename = client2Forename;
  }

  public ClaimBase client2Surname(@Nullable String client2Surname) {
    this.client2Surname = client2Surname;
    return this;
  }

  /**
   * Get client2Surname
   * @return client2Surname
   */
  
  @Schema(name = "client_2_surname", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client_2_surname")
  public @Nullable String getClient2Surname() {
    return client2Surname;
  }

  public void setClient2Surname(@Nullable String client2Surname) {
    this.client2Surname = client2Surname;
  }

  public ClaimBase client2DateOfBirth(@Nullable String client2DateOfBirth) {
    this.client2DateOfBirth = client2DateOfBirth;
    return this;
  }

  /**
   * Client 2's date of birth (format DD/MM/YYYY)
   * @return client2DateOfBirth
   */
  
  @Schema(name = "client_2_date_of_birth", description = "Client 2's date of birth (format DD/MM/YYYY)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client_2_date_of_birth")
  public @Nullable String getClient2DateOfBirth() {
    return client2DateOfBirth;
  }

  public void setClient2DateOfBirth(@Nullable String client2DateOfBirth) {
    this.client2DateOfBirth = client2DateOfBirth;
  }

  public ClaimBase client2Ucn(@Nullable String client2Ucn) {
    this.client2Ucn = client2Ucn;
    return this;
  }

  /**
   * Get client2Ucn
   * @return client2Ucn
   */
  
  @Schema(name = "client_2_ucn", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client_2_ucn")
  public @Nullable String getClient2Ucn() {
    return client2Ucn;
  }

  public void setClient2Ucn(@Nullable String client2Ucn) {
    this.client2Ucn = client2Ucn;
  }

  public ClaimBase client2Postcode(@Nullable String client2Postcode) {
    this.client2Postcode = client2Postcode;
    return this;
  }

  /**
   * Get client2Postcode
   * @return client2Postcode
   */
  
  @Schema(name = "client_2_postcode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client_2_postcode")
  public @Nullable String getClient2Postcode() {
    return client2Postcode;
  }

  public void setClient2Postcode(@Nullable String client2Postcode) {
    this.client2Postcode = client2Postcode;
  }

  public ClaimBase client2GenderCode(@Nullable String client2GenderCode) {
    this.client2GenderCode = client2GenderCode;
    return this;
  }

  /**
   * Get client2GenderCode
   * @return client2GenderCode
   */
  
  @Schema(name = "client_2_gender_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client_2_gender_code")
  public @Nullable String getClient2GenderCode() {
    return client2GenderCode;
  }

  public void setClient2GenderCode(@Nullable String client2GenderCode) {
    this.client2GenderCode = client2GenderCode;
  }

  public ClaimBase client2EthnicityCode(@Nullable String client2EthnicityCode) {
    this.client2EthnicityCode = client2EthnicityCode;
    return this;
  }

  /**
   * Get client2EthnicityCode
   * @return client2EthnicityCode
   */
  
  @Schema(name = "client_2_ethnicity_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client_2_ethnicity_code")
  public @Nullable String getClient2EthnicityCode() {
    return client2EthnicityCode;
  }

  public void setClient2EthnicityCode(@Nullable String client2EthnicityCode) {
    this.client2EthnicityCode = client2EthnicityCode;
  }

  public ClaimBase client2DisabilityCode(@Nullable String client2DisabilityCode) {
    this.client2DisabilityCode = client2DisabilityCode;
    return this;
  }

  /**
   * Get client2DisabilityCode
   * @return client2DisabilityCode
   */
  
  @Schema(name = "client_2_disability_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client_2_disability_code")
  public @Nullable String getClient2DisabilityCode() {
    return client2DisabilityCode;
  }

  public void setClient2DisabilityCode(@Nullable String client2DisabilityCode) {
    this.client2DisabilityCode = client2DisabilityCode;
  }

  public ClaimBase client2IsLegallyAided(@Nullable Boolean client2IsLegallyAided) {
    this.client2IsLegallyAided = client2IsLegallyAided;
    return this;
  }

  /**
   * Get client2IsLegallyAided
   * @return client2IsLegallyAided
   */
  
  @Schema(name = "client_2_is_legally_aided", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client_2_is_legally_aided")
  public @Nullable Boolean getClient2IsLegallyAided() {
    return client2IsLegallyAided;
  }

  public void setClient2IsLegallyAided(@Nullable Boolean client2IsLegallyAided) {
    this.client2IsLegallyAided = client2IsLegallyAided;
  }

  public ClaimBase caseId(@Nullable String caseId) {
    this.caseId = caseId;
    return this;
  }

  /**
   * Get caseId
   * @return caseId
   */
  
  @Schema(name = "case_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("case_id")
  public @Nullable String getCaseId() {
    return caseId;
  }

  public void setCaseId(@Nullable String caseId) {
    this.caseId = caseId;
  }

  public ClaimBase uniqueCaseId(@Nullable String uniqueCaseId) {
    this.uniqueCaseId = uniqueCaseId;
    return this;
  }

  /**
   * Get uniqueCaseId
   * @return uniqueCaseId
   */
  
  @Schema(name = "unique_case_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("unique_case_id")
  public @Nullable String getUniqueCaseId() {
    return uniqueCaseId;
  }

  public void setUniqueCaseId(@Nullable String uniqueCaseId) {
    this.uniqueCaseId = uniqueCaseId;
  }

  public ClaimBase caseStageCode(@Nullable String caseStageCode) {
    this.caseStageCode = caseStageCode;
    return this;
  }

  /**
   * Get caseStageCode
   * @return caseStageCode
   */
  
  @Schema(name = "case_stage_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("case_stage_code")
  public @Nullable String getCaseStageCode() {
    return caseStageCode;
  }

  public void setCaseStageCode(@Nullable String caseStageCode) {
    this.caseStageCode = caseStageCode;
  }

  public ClaimBase stageReachedCode(@Nullable String stageReachedCode) {
    this.stageReachedCode = stageReachedCode;
    return this;
  }

  /**
   * Get stageReachedCode
   * @return stageReachedCode
   */
  
  @Schema(name = "stage_reached_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("stage_reached_code")
  public @Nullable String getStageReachedCode() {
    return stageReachedCode;
  }

  public void setStageReachedCode(@Nullable String stageReachedCode) {
    this.stageReachedCode = stageReachedCode;
  }

  public ClaimBase standardFeeCategoryCode(@Nullable String standardFeeCategoryCode) {
    this.standardFeeCategoryCode = standardFeeCategoryCode;
    return this;
  }

  /**
   * Get standardFeeCategoryCode
   * @return standardFeeCategoryCode
   */
  
  @Schema(name = "standard_fee_category_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("standard_fee_category_code")
  public @Nullable String getStandardFeeCategoryCode() {
    return standardFeeCategoryCode;
  }

  public void setStandardFeeCategoryCode(@Nullable String standardFeeCategoryCode) {
    this.standardFeeCategoryCode = standardFeeCategoryCode;
  }

  public ClaimBase outcomeCode(@Nullable String outcomeCode) {
    this.outcomeCode = outcomeCode;
    return this;
  }

  /**
   * Get outcomeCode
   * @return outcomeCode
   */
  
  @Schema(name = "outcome_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("outcome_code")
  public @Nullable String getOutcomeCode() {
    return outcomeCode;
  }

  public void setOutcomeCode(@Nullable String outcomeCode) {
    this.outcomeCode = outcomeCode;
  }

  public ClaimBase designatedAccreditedRepresentativeCode(@Nullable String designatedAccreditedRepresentativeCode) {
    this.designatedAccreditedRepresentativeCode = designatedAccreditedRepresentativeCode;
    return this;
  }

  /**
   * Get designatedAccreditedRepresentativeCode
   * @return designatedAccreditedRepresentativeCode
   */
  
  @Schema(name = "designated_accredited_representative_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("designated_accredited_representative_code")
  public @Nullable String getDesignatedAccreditedRepresentativeCode() {
    return designatedAccreditedRepresentativeCode;
  }

  public void setDesignatedAccreditedRepresentativeCode(@Nullable String designatedAccreditedRepresentativeCode) {
    this.designatedAccreditedRepresentativeCode = designatedAccreditedRepresentativeCode;
  }

  public ClaimBase isPostalApplicationAccepted(@Nullable Boolean isPostalApplicationAccepted) {
    this.isPostalApplicationAccepted = isPostalApplicationAccepted;
    return this;
  }

  /**
   * Get isPostalApplicationAccepted
   * @return isPostalApplicationAccepted
   */
  
  @Schema(name = "is_postal_application_accepted", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_postal_application_accepted")
  public @Nullable Boolean getIsPostalApplicationAccepted() {
    return isPostalApplicationAccepted;
  }

  public void setIsPostalApplicationAccepted(@Nullable Boolean isPostalApplicationAccepted) {
    this.isPostalApplicationAccepted = isPostalApplicationAccepted;
  }

  public ClaimBase isClient2PostalApplicationAccepted(@Nullable Boolean isClient2PostalApplicationAccepted) {
    this.isClient2PostalApplicationAccepted = isClient2PostalApplicationAccepted;
    return this;
  }

  /**
   * Get isClient2PostalApplicationAccepted
   * @return isClient2PostalApplicationAccepted
   */
  
  @Schema(name = "is_client_2_postal_application_accepted", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_client_2_postal_application_accepted")
  public @Nullable Boolean getIsClient2PostalApplicationAccepted() {
    return isClient2PostalApplicationAccepted;
  }

  public void setIsClient2PostalApplicationAccepted(@Nullable Boolean isClient2PostalApplicationAccepted) {
    this.isClient2PostalApplicationAccepted = isClient2PostalApplicationAccepted;
  }

  public ClaimBase mentalHealthTribunalReference(@Nullable String mentalHealthTribunalReference) {
    this.mentalHealthTribunalReference = mentalHealthTribunalReference;
    return this;
  }

  /**
   * Get mentalHealthTribunalReference
   * @return mentalHealthTribunalReference
   */
  
  @Schema(name = "mental_health_tribunal_reference", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mental_health_tribunal_reference")
  public @Nullable String getMentalHealthTribunalReference() {
    return mentalHealthTribunalReference;
  }

  public void setMentalHealthTribunalReference(@Nullable String mentalHealthTribunalReference) {
    this.mentalHealthTribunalReference = mentalHealthTribunalReference;
  }

  public ClaimBase isNrmAdvice(@Nullable Boolean isNrmAdvice) {
    this.isNrmAdvice = isNrmAdvice;
    return this;
  }

  /**
   * Get isNrmAdvice
   * @return isNrmAdvice
   */
  
  @Schema(name = "is_nrm_advice", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_nrm_advice")
  public @Nullable Boolean getIsNrmAdvice() {
    return isNrmAdvice;
  }

  public void setIsNrmAdvice(@Nullable Boolean isNrmAdvice) {
    this.isNrmAdvice = isNrmAdvice;
  }

  public ClaimBase followOnWork(@Nullable String followOnWork) {
    this.followOnWork = followOnWork;
    return this;
  }

  /**
   * Get followOnWork
   * @return followOnWork
   */
  
  @Schema(name = "follow_on_work", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("follow_on_work")
  public @Nullable String getFollowOnWork() {
    return followOnWork;
  }

  public void setFollowOnWork(@Nullable String followOnWork) {
    this.followOnWork = followOnWork;
  }

  public ClaimBase transferDate(@Nullable String transferDate) {
    this.transferDate = transferDate;
    return this;
  }

  /**
   * Transfer Date (format DD/MM/YYYY)
   * @return transferDate
   */
  
  @Schema(name = "transfer_date", description = "Transfer Date (format DD/MM/YYYY)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("transfer_date")
  public @Nullable String getTransferDate() {
    return transferDate;
  }

  public void setTransferDate(@Nullable String transferDate) {
    this.transferDate = transferDate;
  }

  public ClaimBase exemptionCriteriaSatisfied(@Nullable String exemptionCriteriaSatisfied) {
    this.exemptionCriteriaSatisfied = exemptionCriteriaSatisfied;
    return this;
  }

  /**
   * Get exemptionCriteriaSatisfied
   * @return exemptionCriteriaSatisfied
   */
  
  @Schema(name = "exemption_criteria_satisfied", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("exemption_criteria_satisfied")
  public @Nullable String getExemptionCriteriaSatisfied() {
    return exemptionCriteriaSatisfied;
  }

  public void setExemptionCriteriaSatisfied(@Nullable String exemptionCriteriaSatisfied) {
    this.exemptionCriteriaSatisfied = exemptionCriteriaSatisfied;
  }

  public ClaimBase exceptionalCaseFundingReference(@Nullable String exceptionalCaseFundingReference) {
    this.exceptionalCaseFundingReference = exceptionalCaseFundingReference;
    return this;
  }

  /**
   * Get exceptionalCaseFundingReference
   * @return exceptionalCaseFundingReference
   */
  
  @Schema(name = "exceptional_case_funding_reference", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("exceptional_case_funding_reference")
  public @Nullable String getExceptionalCaseFundingReference() {
    return exceptionalCaseFundingReference;
  }

  public void setExceptionalCaseFundingReference(@Nullable String exceptionalCaseFundingReference) {
    this.exceptionalCaseFundingReference = exceptionalCaseFundingReference;
  }

  public ClaimBase isLegacyCase(@Nullable Boolean isLegacyCase) {
    this.isLegacyCase = isLegacyCase;
    return this;
  }

  /**
   * Get isLegacyCase
   * @return isLegacyCase
   */
  
  @Schema(name = "is_legacy_case", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_legacy_case")
  public @Nullable Boolean getIsLegacyCase() {
    return isLegacyCase;
  }

  public void setIsLegacyCase(@Nullable Boolean isLegacyCase) {
    this.isLegacyCase = isLegacyCase;
  }

  public ClaimBase adviceTime(@Nullable Integer adviceTime) {
    this.adviceTime = adviceTime;
    return this;
  }

  /**
   * Get adviceTime
   * @return adviceTime
   */
  
  @Schema(name = "advice_time", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("advice_time")
  public @Nullable Integer getAdviceTime() {
    return adviceTime;
  }

  public void setAdviceTime(@Nullable Integer adviceTime) {
    this.adviceTime = adviceTime;
  }

  public ClaimBase travelTime(@Nullable Integer travelTime) {
    this.travelTime = travelTime;
    return this;
  }

  /**
   * Get travelTime
   * @return travelTime
   */
  
  @Schema(name = "travel_time", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("travel_time")
  public @Nullable Integer getTravelTime() {
    return travelTime;
  }

  public void setTravelTime(@Nullable Integer travelTime) {
    this.travelTime = travelTime;
  }

  public ClaimBase waitingTime(@Nullable Integer waitingTime) {
    this.waitingTime = waitingTime;
    return this;
  }

  /**
   * Get waitingTime
   * @return waitingTime
   */
  
  @Schema(name = "waiting_time", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("waiting_time")
  public @Nullable Integer getWaitingTime() {
    return waitingTime;
  }

  public void setWaitingTime(@Nullable Integer waitingTime) {
    this.waitingTime = waitingTime;
  }

  public ClaimBase netProfitCostsAmount(@Nullable BigDecimal netProfitCostsAmount) {
    this.netProfitCostsAmount = netProfitCostsAmount;
    return this;
  }

  /**
   * Get netProfitCostsAmount
   * @return netProfitCostsAmount
   */
  @Valid 
  @Schema(name = "net_profit_costs_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("net_profit_costs_amount")
  public @Nullable BigDecimal getNetProfitCostsAmount() {
    return netProfitCostsAmount;
  }

  public void setNetProfitCostsAmount(@Nullable BigDecimal netProfitCostsAmount) {
    this.netProfitCostsAmount = netProfitCostsAmount;
  }

  public ClaimBase netDisbursementAmount(@Nullable BigDecimal netDisbursementAmount) {
    this.netDisbursementAmount = netDisbursementAmount;
    return this;
  }

  /**
   * Get netDisbursementAmount
   * @return netDisbursementAmount
   */
  @Valid 
  @Schema(name = "net_disbursement_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("net_disbursement_amount")
  public @Nullable BigDecimal getNetDisbursementAmount() {
    return netDisbursementAmount;
  }

  public void setNetDisbursementAmount(@Nullable BigDecimal netDisbursementAmount) {
    this.netDisbursementAmount = netDisbursementAmount;
  }

  public ClaimBase netCounselCostsAmount(@Nullable BigDecimal netCounselCostsAmount) {
    this.netCounselCostsAmount = netCounselCostsAmount;
    return this;
  }

  /**
   * Get netCounselCostsAmount
   * @return netCounselCostsAmount
   */
  @Valid 
  @Schema(name = "net_counsel_costs_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("net_counsel_costs_amount")
  public @Nullable BigDecimal getNetCounselCostsAmount() {
    return netCounselCostsAmount;
  }

  public void setNetCounselCostsAmount(@Nullable BigDecimal netCounselCostsAmount) {
    this.netCounselCostsAmount = netCounselCostsAmount;
  }

  public ClaimBase disbursementsVatAmount(@Nullable BigDecimal disbursementsVatAmount) {
    this.disbursementsVatAmount = disbursementsVatAmount;
    return this;
  }

  /**
   * Get disbursementsVatAmount
   * @return disbursementsVatAmount
   */
  @Valid 
  @Schema(name = "disbursements_vat_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("disbursements_vat_amount")
  public @Nullable BigDecimal getDisbursementsVatAmount() {
    return disbursementsVatAmount;
  }

  public void setDisbursementsVatAmount(@Nullable BigDecimal disbursementsVatAmount) {
    this.disbursementsVatAmount = disbursementsVatAmount;
  }

  public ClaimBase travelWaitingCostsAmount(@Nullable BigDecimal travelWaitingCostsAmount) {
    this.travelWaitingCostsAmount = travelWaitingCostsAmount;
    return this;
  }

  /**
   * Get travelWaitingCostsAmount
   * @return travelWaitingCostsAmount
   */
  @Valid 
  @Schema(name = "travel_waiting_costs_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("travel_waiting_costs_amount")
  public @Nullable BigDecimal getTravelWaitingCostsAmount() {
    return travelWaitingCostsAmount;
  }

  public void setTravelWaitingCostsAmount(@Nullable BigDecimal travelWaitingCostsAmount) {
    this.travelWaitingCostsAmount = travelWaitingCostsAmount;
  }

  public ClaimBase netWaitingCostsAmount(@Nullable BigDecimal netWaitingCostsAmount) {
    this.netWaitingCostsAmount = netWaitingCostsAmount;
    return this;
  }

  /**
   * Get netWaitingCostsAmount
   * @return netWaitingCostsAmount
   */
  @Valid 
  @Schema(name = "net_waiting_costs_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("net_waiting_costs_amount")
  public @Nullable BigDecimal getNetWaitingCostsAmount() {
    return netWaitingCostsAmount;
  }

  public void setNetWaitingCostsAmount(@Nullable BigDecimal netWaitingCostsAmount) {
    this.netWaitingCostsAmount = netWaitingCostsAmount;
  }

  public ClaimBase isVatApplicable(@Nullable Boolean isVatApplicable) {
    this.isVatApplicable = isVatApplicable;
    return this;
  }

  /**
   * Get isVatApplicable
   * @return isVatApplicable
   */
  
  @Schema(name = "is_vat_applicable", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_vat_applicable")
  public @Nullable Boolean getIsVatApplicable() {
    return isVatApplicable;
  }

  public void setIsVatApplicable(@Nullable Boolean isVatApplicable) {
    this.isVatApplicable = isVatApplicable;
  }

  public ClaimBase isToleranceApplicable(@Nullable Boolean isToleranceApplicable) {
    this.isToleranceApplicable = isToleranceApplicable;
    return this;
  }

  /**
   * Get isToleranceApplicable
   * @return isToleranceApplicable
   */
  
  @Schema(name = "is_tolerance_applicable", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_tolerance_applicable")
  public @Nullable Boolean getIsToleranceApplicable() {
    return isToleranceApplicable;
  }

  public void setIsToleranceApplicable(@Nullable Boolean isToleranceApplicable) {
    this.isToleranceApplicable = isToleranceApplicable;
  }

  public ClaimBase priorAuthorityReference(@Nullable String priorAuthorityReference) {
    this.priorAuthorityReference = priorAuthorityReference;
    return this;
  }

  /**
   * Get priorAuthorityReference
   * @return priorAuthorityReference
   */
  
  @Schema(name = "prior_authority_reference", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prior_authority_reference")
  public @Nullable String getPriorAuthorityReference() {
    return priorAuthorityReference;
  }

  public void setPriorAuthorityReference(@Nullable String priorAuthorityReference) {
    this.priorAuthorityReference = priorAuthorityReference;
  }

  public ClaimBase isLondonRate(@Nullable Boolean isLondonRate) {
    this.isLondonRate = isLondonRate;
    return this;
  }

  /**
   * Get isLondonRate
   * @return isLondonRate
   */
  
  @Schema(name = "is_london_rate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_london_rate")
  public @Nullable Boolean getIsLondonRate() {
    return isLondonRate;
  }

  public void setIsLondonRate(@Nullable Boolean isLondonRate) {
    this.isLondonRate = isLondonRate;
  }

  public ClaimBase adjournedHearingFeeAmount(@Nullable Integer adjournedHearingFeeAmount) {
    this.adjournedHearingFeeAmount = adjournedHearingFeeAmount;
    return this;
  }

  /**
   * Note: actually stores the number of times the hearing was adjourned
   * @return adjournedHearingFeeAmount
   */
  
  @Schema(name = "adjourned_hearing_fee_amount", description = "Note: actually stores the number of times the hearing was adjourned", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adjourned_hearing_fee_amount")
  public @Nullable Integer getAdjournedHearingFeeAmount() {
    return adjournedHearingFeeAmount;
  }

  public void setAdjournedHearingFeeAmount(@Nullable Integer adjournedHearingFeeAmount) {
    this.adjournedHearingFeeAmount = adjournedHearingFeeAmount;
  }

  public ClaimBase isAdditionalTravelPayment(@Nullable Boolean isAdditionalTravelPayment) {
    this.isAdditionalTravelPayment = isAdditionalTravelPayment;
    return this;
  }

  /**
   * Get isAdditionalTravelPayment
   * @return isAdditionalTravelPayment
   */
  
  @Schema(name = "is_additional_travel_payment", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_additional_travel_payment")
  public @Nullable Boolean getIsAdditionalTravelPayment() {
    return isAdditionalTravelPayment;
  }

  public void setIsAdditionalTravelPayment(@Nullable Boolean isAdditionalTravelPayment) {
    this.isAdditionalTravelPayment = isAdditionalTravelPayment;
  }

  public ClaimBase costsDamagesRecoveredAmount(@Nullable BigDecimal costsDamagesRecoveredAmount) {
    this.costsDamagesRecoveredAmount = costsDamagesRecoveredAmount;
    return this;
  }

  /**
   * Get costsDamagesRecoveredAmount
   * @return costsDamagesRecoveredAmount
   */
  @Valid 
  @Schema(name = "costs_damages_recovered_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("costs_damages_recovered_amount")
  public @Nullable BigDecimal getCostsDamagesRecoveredAmount() {
    return costsDamagesRecoveredAmount;
  }

  public void setCostsDamagesRecoveredAmount(@Nullable BigDecimal costsDamagesRecoveredAmount) {
    this.costsDamagesRecoveredAmount = costsDamagesRecoveredAmount;
  }

  public ClaimBase meetingsAttendedCode(@Nullable String meetingsAttendedCode) {
    this.meetingsAttendedCode = meetingsAttendedCode;
    return this;
  }

  /**
   * Get meetingsAttendedCode
   * @return meetingsAttendedCode
   */
  
  @Schema(name = "meetings_attended_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("meetings_attended_code")
  public @Nullable String getMeetingsAttendedCode() {
    return meetingsAttendedCode;
  }

  public void setMeetingsAttendedCode(@Nullable String meetingsAttendedCode) {
    this.meetingsAttendedCode = meetingsAttendedCode;
  }

  public ClaimBase detentionTravelWaitingCostsAmount(@Nullable BigDecimal detentionTravelWaitingCostsAmount) {
    this.detentionTravelWaitingCostsAmount = detentionTravelWaitingCostsAmount;
    return this;
  }

  /**
   * Get detentionTravelWaitingCostsAmount
   * @return detentionTravelWaitingCostsAmount
   */
  @Valid 
  @Schema(name = "detention_travel_waiting_costs_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("detention_travel_waiting_costs_amount")
  public @Nullable BigDecimal getDetentionTravelWaitingCostsAmount() {
    return detentionTravelWaitingCostsAmount;
  }

  public void setDetentionTravelWaitingCostsAmount(@Nullable BigDecimal detentionTravelWaitingCostsAmount) {
    this.detentionTravelWaitingCostsAmount = detentionTravelWaitingCostsAmount;
  }

  public ClaimBase jrFormFillingAmount(@Nullable BigDecimal jrFormFillingAmount) {
    this.jrFormFillingAmount = jrFormFillingAmount;
    return this;
  }

  /**
   * Get jrFormFillingAmount
   * @return jrFormFillingAmount
   */
  @Valid 
  @Schema(name = "jr_form_filling_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("jr_form_filling_amount")
  public @Nullable BigDecimal getJrFormFillingAmount() {
    return jrFormFillingAmount;
  }

  public void setJrFormFillingAmount(@Nullable BigDecimal jrFormFillingAmount) {
    this.jrFormFillingAmount = jrFormFillingAmount;
  }

  public ClaimBase isEligibleClient(@Nullable Boolean isEligibleClient) {
    this.isEligibleClient = isEligibleClient;
    return this;
  }

  /**
   * Get isEligibleClient
   * @return isEligibleClient
   */
  
  @Schema(name = "is_eligible_client", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_eligible_client")
  public @Nullable Boolean getIsEligibleClient() {
    return isEligibleClient;
  }

  public void setIsEligibleClient(@Nullable Boolean isEligibleClient) {
    this.isEligibleClient = isEligibleClient;
  }

  public ClaimBase courtLocationCode(@Nullable String courtLocationCode) {
    this.courtLocationCode = courtLocationCode;
    return this;
  }

  /**
   * Get courtLocationCode
   * @return courtLocationCode
   */
  
  @Schema(name = "court_location_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("court_location_code")
  public @Nullable String getCourtLocationCode() {
    return courtLocationCode;
  }

  public void setCourtLocationCode(@Nullable String courtLocationCode) {
    this.courtLocationCode = courtLocationCode;
  }

  public ClaimBase adviceTypeCode(@Nullable String adviceTypeCode) {
    this.adviceTypeCode = adviceTypeCode;
    return this;
  }

  /**
   * Get adviceTypeCode
   * @return adviceTypeCode
   */
  
  @Schema(name = "advice_type_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("advice_type_code")
  public @Nullable String getAdviceTypeCode() {
    return adviceTypeCode;
  }

  public void setAdviceTypeCode(@Nullable String adviceTypeCode) {
    this.adviceTypeCode = adviceTypeCode;
  }

  public ClaimBase medicalReportsCount(@Nullable Integer medicalReportsCount) {
    this.medicalReportsCount = medicalReportsCount;
    return this;
  }

  /**
   * Get medicalReportsCount
   * @return medicalReportsCount
   */
  
  @Schema(name = "medical_reports_count", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("medical_reports_count")
  public @Nullable Integer getMedicalReportsCount() {
    return medicalReportsCount;
  }

  public void setMedicalReportsCount(@Nullable Integer medicalReportsCount) {
    this.medicalReportsCount = medicalReportsCount;
  }

  public ClaimBase isIrcSurgery(@Nullable Boolean isIrcSurgery) {
    this.isIrcSurgery = isIrcSurgery;
    return this;
  }

  /**
   * Get isIrcSurgery
   * @return isIrcSurgery
   */
  
  @Schema(name = "is_irc_surgery", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_irc_surgery")
  public @Nullable Boolean getIsIrcSurgery() {
    return isIrcSurgery;
  }

  public void setIsIrcSurgery(@Nullable Boolean isIrcSurgery) {
    this.isIrcSurgery = isIrcSurgery;
  }

  public ClaimBase surgeryDate(@Nullable String surgeryDate) {
    this.surgeryDate = surgeryDate;
    return this;
  }

  /**
   * Surgery Date (format DD/MM/YYYY)
   * @return surgeryDate
   */
  
  @Schema(name = "surgery_date", description = "Surgery Date (format DD/MM/YYYY)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("surgery_date")
  public @Nullable String getSurgeryDate() {
    return surgeryDate;
  }

  public void setSurgeryDate(@Nullable String surgeryDate) {
    this.surgeryDate = surgeryDate;
  }

  public ClaimBase surgeryClientsCount(@Nullable Integer surgeryClientsCount) {
    this.surgeryClientsCount = surgeryClientsCount;
    return this;
  }

  /**
   * Get surgeryClientsCount
   * @return surgeryClientsCount
   */
  
  @Schema(name = "surgery_clients_count", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("surgery_clients_count")
  public @Nullable Integer getSurgeryClientsCount() {
    return surgeryClientsCount;
  }

  public void setSurgeryClientsCount(@Nullable Integer surgeryClientsCount) {
    this.surgeryClientsCount = surgeryClientsCount;
  }

  public ClaimBase surgeryMattersCount(@Nullable Integer surgeryMattersCount) {
    this.surgeryMattersCount = surgeryMattersCount;
    return this;
  }

  /**
   * Get surgeryMattersCount
   * @return surgeryMattersCount
   */
  
  @Schema(name = "surgery_matters_count", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("surgery_matters_count")
  public @Nullable Integer getSurgeryMattersCount() {
    return surgeryMattersCount;
  }

  public void setSurgeryMattersCount(@Nullable Integer surgeryMattersCount) {
    this.surgeryMattersCount = surgeryMattersCount;
  }

  public ClaimBase cmrhOralCount(@Nullable Integer cmrhOralCount) {
    this.cmrhOralCount = cmrhOralCount;
    return this;
  }

  /**
   * Get cmrhOralCount
   * @return cmrhOralCount
   */
  
  @Schema(name = "cmrh_oral_count", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cmrh_oral_count")
  public @Nullable Integer getCmrhOralCount() {
    return cmrhOralCount;
  }

  public void setCmrhOralCount(@Nullable Integer cmrhOralCount) {
    this.cmrhOralCount = cmrhOralCount;
  }

  public ClaimBase cmrhTelephoneCount(@Nullable Integer cmrhTelephoneCount) {
    this.cmrhTelephoneCount = cmrhTelephoneCount;
    return this;
  }

  /**
   * Get cmrhTelephoneCount
   * @return cmrhTelephoneCount
   */
  
  @Schema(name = "cmrh_telephone_count", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cmrh_telephone_count")
  public @Nullable Integer getCmrhTelephoneCount() {
    return cmrhTelephoneCount;
  }

  public void setCmrhTelephoneCount(@Nullable Integer cmrhTelephoneCount) {
    this.cmrhTelephoneCount = cmrhTelephoneCount;
  }

  public ClaimBase aitHearingCentreCode(@Nullable String aitHearingCentreCode) {
    this.aitHearingCentreCode = aitHearingCentreCode;
    return this;
  }

  /**
   * Get aitHearingCentreCode
   * @return aitHearingCentreCode
   */
  
  @Schema(name = "ait_hearing_centre_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ait_hearing_centre_code")
  public @Nullable String getAitHearingCentreCode() {
    return aitHearingCentreCode;
  }

  public void setAitHearingCentreCode(@Nullable String aitHearingCentreCode) {
    this.aitHearingCentreCode = aitHearingCentreCode;
  }

  public ClaimBase isSubstantiveHearing(@Nullable Boolean isSubstantiveHearing) {
    this.isSubstantiveHearing = isSubstantiveHearing;
    return this;
  }

  /**
   * Get isSubstantiveHearing
   * @return isSubstantiveHearing
   */
  
  @Schema(name = "is_substantive_hearing", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_substantive_hearing")
  public @Nullable Boolean getIsSubstantiveHearing() {
    return isSubstantiveHearing;
  }

  public void setIsSubstantiveHearing(@Nullable Boolean isSubstantiveHearing) {
    this.isSubstantiveHearing = isSubstantiveHearing;
  }

  public ClaimBase hoInterview(@Nullable Integer hoInterview) {
    this.hoInterview = hoInterview;
    return this;
  }

  /**
   * Number of Home Office Interviews
   * @return hoInterview
   */
  
  @Schema(name = "ho_interview", description = "Number of Home Office Interviews", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ho_interview")
  public @Nullable Integer getHoInterview() {
    return hoInterview;
  }

  public void setHoInterview(@Nullable Integer hoInterview) {
    this.hoInterview = hoInterview;
  }

  public ClaimBase localAuthorityNumber(@Nullable String localAuthorityNumber) {
    this.localAuthorityNumber = localAuthorityNumber;
    return this;
  }

  /**
   * Get localAuthorityNumber
   * @return localAuthorityNumber
   */
  
  @Schema(name = "local_authority_number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("local_authority_number")
  public @Nullable String getLocalAuthorityNumber() {
    return localAuthorityNumber;
  }

  public void setLocalAuthorityNumber(@Nullable String localAuthorityNumber) {
    this.localAuthorityNumber = localAuthorityNumber;
  }

  public ClaimBase submissionPeriod(@Nullable String submissionPeriod) {
    this.submissionPeriod = submissionPeriod;
    return this;
  }

  /**
   * Submission period (e.g., \"JUL-2025\").
   * @return submissionPeriod
   */
  
  @Schema(name = "submission_period", description = "Submission period (e.g., \"JUL-2025\").", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("submission_period")
  public @Nullable String getSubmissionPeriod() {
    return submissionPeriod;
  }

  public void setSubmissionPeriod(@Nullable String submissionPeriod) {
    this.submissionPeriod = submissionPeriod;
  }

  public ClaimBase createdByUserId(@Nullable String createdByUserId) {
    this.createdByUserId = createdByUserId;
    return this;
  }

  /**
   * The id of the user who created the claim.
   * @return createdByUserId
   */
  
  @Schema(name = "created_by_user_id", description = "The id of the user who created the claim.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("created_by_user_id")
  public @Nullable String getCreatedByUserId() {
    return createdByUserId;
  }

  public void setCreatedByUserId(@Nullable String createdByUserId) {
    this.createdByUserId = createdByUserId;
  }

  public ClaimBase isAmended(@Nullable Boolean isAmended) {
    this.isAmended = isAmended;
    return this;
  }

  /**
   * Indicates if the claim has been amended.
   * @return isAmended
   */
  
  @Schema(name = "is_amended", description = "Indicates if the claim has been amended.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_amended")
  public @Nullable Boolean getIsAmended() {
    return isAmended;
  }

  public void setIsAmended(@Nullable Boolean isAmended) {
    this.isAmended = isAmended;
  }

  public ClaimBase hasAssessment(@Nullable Boolean hasAssessment) {
    this.hasAssessment = hasAssessment;
    return this;
  }

  /**
   * Indicates if the claim has an associated assessment.
   * @return hasAssessment
   */
  
  @Schema(name = "has_assessment", description = "Indicates if the claim has an associated assessment.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("has_assessment")
  public @Nullable Boolean getHasAssessment() {
    return hasAssessment;
  }

  public void setHasAssessment(@Nullable Boolean hasAssessment) {
    this.hasAssessment = hasAssessment;
  }

  public ClaimBase version(@Nullable Long version) {
    this.version = version;
    return this;
  }

  /**
   * Used for optimistic locking
   * @return version
   */
  
  @Schema(name = "version", description = "Used for optimistic locking", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("version")
  public @Nullable Long getVersion() {
    return version;
  }

  public void setVersion(@Nullable Long version) {
    this.version = version;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClaimBase claimBase = (ClaimBase) o;
    return Objects.equals(this.id, claimBase.id) &&
        Objects.equals(this.submissionId, claimBase.submissionId) &&
        Objects.equals(this.status, claimBase.status) &&
        Objects.equals(this.scheduleReference, claimBase.scheduleReference) &&
        Objects.equals(this.lineNumber, claimBase.lineNumber) &&
        Objects.equals(this.caseReferenceNumber, claimBase.caseReferenceNumber) &&
        Objects.equals(this.uniqueFileNumber, claimBase.uniqueFileNumber) &&
        Objects.equals(this.caseStartDate, claimBase.caseStartDate) &&
        Objects.equals(this.caseConcludedDate, claimBase.caseConcludedDate) &&
        Objects.equals(this.matterTypeCode, claimBase.matterTypeCode) &&
        Objects.equals(this.crimeMatterTypeCode, claimBase.crimeMatterTypeCode) &&
        Objects.equals(this.feeSchemeCode, claimBase.feeSchemeCode) &&
        Objects.equals(this.feeCode, claimBase.feeCode) &&
        Objects.equals(this.procurementAreaCode, claimBase.procurementAreaCode) &&
        Objects.equals(this.accessPointCode, claimBase.accessPointCode) &&
        Objects.equals(this.deliveryLocation, claimBase.deliveryLocation) &&
        Objects.equals(this.representationOrderDate, claimBase.representationOrderDate) &&
        Objects.equals(this.suspectsDefendantsCount, claimBase.suspectsDefendantsCount) &&
        Objects.equals(this.policeStationCourtAttendancesCount, claimBase.policeStationCourtAttendancesCount) &&
        Objects.equals(this.policeStationCourtPrisonId, claimBase.policeStationCourtPrisonId) &&
        Objects.equals(this.dsccNumber, claimBase.dsccNumber) &&
        Objects.equals(this.maatId, claimBase.maatId) &&
        Objects.equals(this.prisonLawPriorApprovalNumber, claimBase.prisonLawPriorApprovalNumber) &&
        Objects.equals(this.isDutySolicitor, claimBase.isDutySolicitor) &&
        Objects.equals(this.isYouthCourt, claimBase.isYouthCourt) &&
        Objects.equals(this.schemeId, claimBase.schemeId) &&
        Objects.equals(this.mediationSessionsCount, claimBase.mediationSessionsCount) &&
        Objects.equals(this.mediationTimeMinutes, claimBase.mediationTimeMinutes) &&
        Objects.equals(this.outreachLocation, claimBase.outreachLocation) &&
        Objects.equals(this.referralSource, claimBase.referralSource) &&
        Objects.equals(this.clientForename, claimBase.clientForename) &&
        Objects.equals(this.clientSurname, claimBase.clientSurname) &&
        Objects.equals(this.clientDateOfBirth, claimBase.clientDateOfBirth) &&
        Objects.equals(this.uniqueClientNumber, claimBase.uniqueClientNumber) &&
        Objects.equals(this.clientPostcode, claimBase.clientPostcode) &&
        Objects.equals(this.genderCode, claimBase.genderCode) &&
        Objects.equals(this.ethnicityCode, claimBase.ethnicityCode) &&
        Objects.equals(this.disabilityCode, claimBase.disabilityCode) &&
        Objects.equals(this.isLegallyAided, claimBase.isLegallyAided) &&
        Objects.equals(this.clientTypeCode, claimBase.clientTypeCode) &&
        Objects.equals(this.homeOfficeClientNumber, claimBase.homeOfficeClientNumber) &&
        Objects.equals(this.claReferenceNumber, claimBase.claReferenceNumber) &&
        Objects.equals(this.claExemptionCode, claimBase.claExemptionCode) &&
        Objects.equals(this.client2Forename, claimBase.client2Forename) &&
        Objects.equals(this.client2Surname, claimBase.client2Surname) &&
        Objects.equals(this.client2DateOfBirth, claimBase.client2DateOfBirth) &&
        Objects.equals(this.client2Ucn, claimBase.client2Ucn) &&
        Objects.equals(this.client2Postcode, claimBase.client2Postcode) &&
        Objects.equals(this.client2GenderCode, claimBase.client2GenderCode) &&
        Objects.equals(this.client2EthnicityCode, claimBase.client2EthnicityCode) &&
        Objects.equals(this.client2DisabilityCode, claimBase.client2DisabilityCode) &&
        Objects.equals(this.client2IsLegallyAided, claimBase.client2IsLegallyAided) &&
        Objects.equals(this.caseId, claimBase.caseId) &&
        Objects.equals(this.uniqueCaseId, claimBase.uniqueCaseId) &&
        Objects.equals(this.caseStageCode, claimBase.caseStageCode) &&
        Objects.equals(this.stageReachedCode, claimBase.stageReachedCode) &&
        Objects.equals(this.standardFeeCategoryCode, claimBase.standardFeeCategoryCode) &&
        Objects.equals(this.outcomeCode, claimBase.outcomeCode) &&
        Objects.equals(this.designatedAccreditedRepresentativeCode, claimBase.designatedAccreditedRepresentativeCode) &&
        Objects.equals(this.isPostalApplicationAccepted, claimBase.isPostalApplicationAccepted) &&
        Objects.equals(this.isClient2PostalApplicationAccepted, claimBase.isClient2PostalApplicationAccepted) &&
        Objects.equals(this.mentalHealthTribunalReference, claimBase.mentalHealthTribunalReference) &&
        Objects.equals(this.isNrmAdvice, claimBase.isNrmAdvice) &&
        Objects.equals(this.followOnWork, claimBase.followOnWork) &&
        Objects.equals(this.transferDate, claimBase.transferDate) &&
        Objects.equals(this.exemptionCriteriaSatisfied, claimBase.exemptionCriteriaSatisfied) &&
        Objects.equals(this.exceptionalCaseFundingReference, claimBase.exceptionalCaseFundingReference) &&
        Objects.equals(this.isLegacyCase, claimBase.isLegacyCase) &&
        Objects.equals(this.adviceTime, claimBase.adviceTime) &&
        Objects.equals(this.travelTime, claimBase.travelTime) &&
        Objects.equals(this.waitingTime, claimBase.waitingTime) &&
        Objects.equals(this.netProfitCostsAmount, claimBase.netProfitCostsAmount) &&
        Objects.equals(this.netDisbursementAmount, claimBase.netDisbursementAmount) &&
        Objects.equals(this.netCounselCostsAmount, claimBase.netCounselCostsAmount) &&
        Objects.equals(this.disbursementsVatAmount, claimBase.disbursementsVatAmount) &&
        Objects.equals(this.travelWaitingCostsAmount, claimBase.travelWaitingCostsAmount) &&
        Objects.equals(this.netWaitingCostsAmount, claimBase.netWaitingCostsAmount) &&
        Objects.equals(this.isVatApplicable, claimBase.isVatApplicable) &&
        Objects.equals(this.isToleranceApplicable, claimBase.isToleranceApplicable) &&
        Objects.equals(this.priorAuthorityReference, claimBase.priorAuthorityReference) &&
        Objects.equals(this.isLondonRate, claimBase.isLondonRate) &&
        Objects.equals(this.adjournedHearingFeeAmount, claimBase.adjournedHearingFeeAmount) &&
        Objects.equals(this.isAdditionalTravelPayment, claimBase.isAdditionalTravelPayment) &&
        Objects.equals(this.costsDamagesRecoveredAmount, claimBase.costsDamagesRecoveredAmount) &&
        Objects.equals(this.meetingsAttendedCode, claimBase.meetingsAttendedCode) &&
        Objects.equals(this.detentionTravelWaitingCostsAmount, claimBase.detentionTravelWaitingCostsAmount) &&
        Objects.equals(this.jrFormFillingAmount, claimBase.jrFormFillingAmount) &&
        Objects.equals(this.isEligibleClient, claimBase.isEligibleClient) &&
        Objects.equals(this.courtLocationCode, claimBase.courtLocationCode) &&
        Objects.equals(this.adviceTypeCode, claimBase.adviceTypeCode) &&
        Objects.equals(this.medicalReportsCount, claimBase.medicalReportsCount) &&
        Objects.equals(this.isIrcSurgery, claimBase.isIrcSurgery) &&
        Objects.equals(this.surgeryDate, claimBase.surgeryDate) &&
        Objects.equals(this.surgeryClientsCount, claimBase.surgeryClientsCount) &&
        Objects.equals(this.surgeryMattersCount, claimBase.surgeryMattersCount) &&
        Objects.equals(this.cmrhOralCount, claimBase.cmrhOralCount) &&
        Objects.equals(this.cmrhTelephoneCount, claimBase.cmrhTelephoneCount) &&
        Objects.equals(this.aitHearingCentreCode, claimBase.aitHearingCentreCode) &&
        Objects.equals(this.isSubstantiveHearing, claimBase.isSubstantiveHearing) &&
        Objects.equals(this.hoInterview, claimBase.hoInterview) &&
        Objects.equals(this.localAuthorityNumber, claimBase.localAuthorityNumber) &&
        Objects.equals(this.submissionPeriod, claimBase.submissionPeriod) &&
        Objects.equals(this.createdByUserId, claimBase.createdByUserId) &&
        Objects.equals(this.isAmended, claimBase.isAmended) &&
        Objects.equals(this.hasAssessment, claimBase.hasAssessment) &&
        Objects.equals(this.version, claimBase.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, submissionId, status, scheduleReference, lineNumber, caseReferenceNumber, uniqueFileNumber, caseStartDate, caseConcludedDate, matterTypeCode, crimeMatterTypeCode, feeSchemeCode, feeCode, procurementAreaCode, accessPointCode, deliveryLocation, representationOrderDate, suspectsDefendantsCount, policeStationCourtAttendancesCount, policeStationCourtPrisonId, dsccNumber, maatId, prisonLawPriorApprovalNumber, isDutySolicitor, isYouthCourt, schemeId, mediationSessionsCount, mediationTimeMinutes, outreachLocation, referralSource, clientForename, clientSurname, clientDateOfBirth, uniqueClientNumber, clientPostcode, genderCode, ethnicityCode, disabilityCode, isLegallyAided, clientTypeCode, homeOfficeClientNumber, claReferenceNumber, claExemptionCode, client2Forename, client2Surname, client2DateOfBirth, client2Ucn, client2Postcode, client2GenderCode, client2EthnicityCode, client2DisabilityCode, client2IsLegallyAided, caseId, uniqueCaseId, caseStageCode, stageReachedCode, standardFeeCategoryCode, outcomeCode, designatedAccreditedRepresentativeCode, isPostalApplicationAccepted, isClient2PostalApplicationAccepted, mentalHealthTribunalReference, isNrmAdvice, followOnWork, transferDate, exemptionCriteriaSatisfied, exceptionalCaseFundingReference, isLegacyCase, adviceTime, travelTime, waitingTime, netProfitCostsAmount, netDisbursementAmount, netCounselCostsAmount, disbursementsVatAmount, travelWaitingCostsAmount, netWaitingCostsAmount, isVatApplicable, isToleranceApplicable, priorAuthorityReference, isLondonRate, adjournedHearingFeeAmount, isAdditionalTravelPayment, costsDamagesRecoveredAmount, meetingsAttendedCode, detentionTravelWaitingCostsAmount, jrFormFillingAmount, isEligibleClient, courtLocationCode, adviceTypeCode, medicalReportsCount, isIrcSurgery, surgeryDate, surgeryClientsCount, surgeryMattersCount, cmrhOralCount, cmrhTelephoneCount, aitHearingCentreCode, isSubstantiveHearing, hoInterview, localAuthorityNumber, submissionPeriod, createdByUserId, isAmended, hasAssessment, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClaimBase {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    submissionId: ").append(toIndentedString(submissionId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    scheduleReference: ").append(toIndentedString(scheduleReference)).append("\n");
    sb.append("    lineNumber: ").append(toIndentedString(lineNumber)).append("\n");
    sb.append("    caseReferenceNumber: ").append(toIndentedString(caseReferenceNumber)).append("\n");
    sb.append("    uniqueFileNumber: ").append(toIndentedString(uniqueFileNumber)).append("\n");
    sb.append("    caseStartDate: ").append(toIndentedString(caseStartDate)).append("\n");
    sb.append("    caseConcludedDate: ").append(toIndentedString(caseConcludedDate)).append("\n");
    sb.append("    matterTypeCode: ").append(toIndentedString(matterTypeCode)).append("\n");
    sb.append("    crimeMatterTypeCode: ").append(toIndentedString(crimeMatterTypeCode)).append("\n");
    sb.append("    feeSchemeCode: ").append(toIndentedString(feeSchemeCode)).append("\n");
    sb.append("    feeCode: ").append(toIndentedString(feeCode)).append("\n");
    sb.append("    procurementAreaCode: ").append(toIndentedString(procurementAreaCode)).append("\n");
    sb.append("    accessPointCode: ").append(toIndentedString(accessPointCode)).append("\n");
    sb.append("    deliveryLocation: ").append(toIndentedString(deliveryLocation)).append("\n");
    sb.append("    representationOrderDate: ").append(toIndentedString(representationOrderDate)).append("\n");
    sb.append("    suspectsDefendantsCount: ").append(toIndentedString(suspectsDefendantsCount)).append("\n");
    sb.append("    policeStationCourtAttendancesCount: ").append(toIndentedString(policeStationCourtAttendancesCount)).append("\n");
    sb.append("    policeStationCourtPrisonId: ").append(toIndentedString(policeStationCourtPrisonId)).append("\n");
    sb.append("    dsccNumber: ").append(toIndentedString(dsccNumber)).append("\n");
    sb.append("    maatId: ").append(toIndentedString(maatId)).append("\n");
    sb.append("    prisonLawPriorApprovalNumber: ").append(toIndentedString(prisonLawPriorApprovalNumber)).append("\n");
    sb.append("    isDutySolicitor: ").append(toIndentedString(isDutySolicitor)).append("\n");
    sb.append("    isYouthCourt: ").append(toIndentedString(isYouthCourt)).append("\n");
    sb.append("    schemeId: ").append(toIndentedString(schemeId)).append("\n");
    sb.append("    mediationSessionsCount: ").append(toIndentedString(mediationSessionsCount)).append("\n");
    sb.append("    mediationTimeMinutes: ").append(toIndentedString(mediationTimeMinutes)).append("\n");
    sb.append("    outreachLocation: ").append(toIndentedString(outreachLocation)).append("\n");
    sb.append("    referralSource: ").append(toIndentedString(referralSource)).append("\n");
    sb.append("    clientForename: ").append(toIndentedString(clientForename)).append("\n");
    sb.append("    clientSurname: ").append(toIndentedString(clientSurname)).append("\n");
    sb.append("    clientDateOfBirth: ").append(toIndentedString(clientDateOfBirth)).append("\n");
    sb.append("    uniqueClientNumber: ").append(toIndentedString(uniqueClientNumber)).append("\n");
    sb.append("    clientPostcode: ").append(toIndentedString(clientPostcode)).append("\n");
    sb.append("    genderCode: ").append(toIndentedString(genderCode)).append("\n");
    sb.append("    ethnicityCode: ").append(toIndentedString(ethnicityCode)).append("\n");
    sb.append("    disabilityCode: ").append(toIndentedString(disabilityCode)).append("\n");
    sb.append("    isLegallyAided: ").append(toIndentedString(isLegallyAided)).append("\n");
    sb.append("    clientTypeCode: ").append(toIndentedString(clientTypeCode)).append("\n");
    sb.append("    homeOfficeClientNumber: ").append(toIndentedString(homeOfficeClientNumber)).append("\n");
    sb.append("    claReferenceNumber: ").append(toIndentedString(claReferenceNumber)).append("\n");
    sb.append("    claExemptionCode: ").append(toIndentedString(claExemptionCode)).append("\n");
    sb.append("    client2Forename: ").append(toIndentedString(client2Forename)).append("\n");
    sb.append("    client2Surname: ").append(toIndentedString(client2Surname)).append("\n");
    sb.append("    client2DateOfBirth: ").append(toIndentedString(client2DateOfBirth)).append("\n");
    sb.append("    client2Ucn: ").append(toIndentedString(client2Ucn)).append("\n");
    sb.append("    client2Postcode: ").append(toIndentedString(client2Postcode)).append("\n");
    sb.append("    client2GenderCode: ").append(toIndentedString(client2GenderCode)).append("\n");
    sb.append("    client2EthnicityCode: ").append(toIndentedString(client2EthnicityCode)).append("\n");
    sb.append("    client2DisabilityCode: ").append(toIndentedString(client2DisabilityCode)).append("\n");
    sb.append("    client2IsLegallyAided: ").append(toIndentedString(client2IsLegallyAided)).append("\n");
    sb.append("    caseId: ").append(toIndentedString(caseId)).append("\n");
    sb.append("    uniqueCaseId: ").append(toIndentedString(uniqueCaseId)).append("\n");
    sb.append("    caseStageCode: ").append(toIndentedString(caseStageCode)).append("\n");
    sb.append("    stageReachedCode: ").append(toIndentedString(stageReachedCode)).append("\n");
    sb.append("    standardFeeCategoryCode: ").append(toIndentedString(standardFeeCategoryCode)).append("\n");
    sb.append("    outcomeCode: ").append(toIndentedString(outcomeCode)).append("\n");
    sb.append("    designatedAccreditedRepresentativeCode: ").append(toIndentedString(designatedAccreditedRepresentativeCode)).append("\n");
    sb.append("    isPostalApplicationAccepted: ").append(toIndentedString(isPostalApplicationAccepted)).append("\n");
    sb.append("    isClient2PostalApplicationAccepted: ").append(toIndentedString(isClient2PostalApplicationAccepted)).append("\n");
    sb.append("    mentalHealthTribunalReference: ").append(toIndentedString(mentalHealthTribunalReference)).append("\n");
    sb.append("    isNrmAdvice: ").append(toIndentedString(isNrmAdvice)).append("\n");
    sb.append("    followOnWork: ").append(toIndentedString(followOnWork)).append("\n");
    sb.append("    transferDate: ").append(toIndentedString(transferDate)).append("\n");
    sb.append("    exemptionCriteriaSatisfied: ").append(toIndentedString(exemptionCriteriaSatisfied)).append("\n");
    sb.append("    exceptionalCaseFundingReference: ").append(toIndentedString(exceptionalCaseFundingReference)).append("\n");
    sb.append("    isLegacyCase: ").append(toIndentedString(isLegacyCase)).append("\n");
    sb.append("    adviceTime: ").append(toIndentedString(adviceTime)).append("\n");
    sb.append("    travelTime: ").append(toIndentedString(travelTime)).append("\n");
    sb.append("    waitingTime: ").append(toIndentedString(waitingTime)).append("\n");
    sb.append("    netProfitCostsAmount: ").append(toIndentedString(netProfitCostsAmount)).append("\n");
    sb.append("    netDisbursementAmount: ").append(toIndentedString(netDisbursementAmount)).append("\n");
    sb.append("    netCounselCostsAmount: ").append(toIndentedString(netCounselCostsAmount)).append("\n");
    sb.append("    disbursementsVatAmount: ").append(toIndentedString(disbursementsVatAmount)).append("\n");
    sb.append("    travelWaitingCostsAmount: ").append(toIndentedString(travelWaitingCostsAmount)).append("\n");
    sb.append("    netWaitingCostsAmount: ").append(toIndentedString(netWaitingCostsAmount)).append("\n");
    sb.append("    isVatApplicable: ").append(toIndentedString(isVatApplicable)).append("\n");
    sb.append("    isToleranceApplicable: ").append(toIndentedString(isToleranceApplicable)).append("\n");
    sb.append("    priorAuthorityReference: ").append(toIndentedString(priorAuthorityReference)).append("\n");
    sb.append("    isLondonRate: ").append(toIndentedString(isLondonRate)).append("\n");
    sb.append("    adjournedHearingFeeAmount: ").append(toIndentedString(adjournedHearingFeeAmount)).append("\n");
    sb.append("    isAdditionalTravelPayment: ").append(toIndentedString(isAdditionalTravelPayment)).append("\n");
    sb.append("    costsDamagesRecoveredAmount: ").append(toIndentedString(costsDamagesRecoveredAmount)).append("\n");
    sb.append("    meetingsAttendedCode: ").append(toIndentedString(meetingsAttendedCode)).append("\n");
    sb.append("    detentionTravelWaitingCostsAmount: ").append(toIndentedString(detentionTravelWaitingCostsAmount)).append("\n");
    sb.append("    jrFormFillingAmount: ").append(toIndentedString(jrFormFillingAmount)).append("\n");
    sb.append("    isEligibleClient: ").append(toIndentedString(isEligibleClient)).append("\n");
    sb.append("    courtLocationCode: ").append(toIndentedString(courtLocationCode)).append("\n");
    sb.append("    adviceTypeCode: ").append(toIndentedString(adviceTypeCode)).append("\n");
    sb.append("    medicalReportsCount: ").append(toIndentedString(medicalReportsCount)).append("\n");
    sb.append("    isIrcSurgery: ").append(toIndentedString(isIrcSurgery)).append("\n");
    sb.append("    surgeryDate: ").append(toIndentedString(surgeryDate)).append("\n");
    sb.append("    surgeryClientsCount: ").append(toIndentedString(surgeryClientsCount)).append("\n");
    sb.append("    surgeryMattersCount: ").append(toIndentedString(surgeryMattersCount)).append("\n");
    sb.append("    cmrhOralCount: ").append(toIndentedString(cmrhOralCount)).append("\n");
    sb.append("    cmrhTelephoneCount: ").append(toIndentedString(cmrhTelephoneCount)).append("\n");
    sb.append("    aitHearingCentreCode: ").append(toIndentedString(aitHearingCentreCode)).append("\n");
    sb.append("    isSubstantiveHearing: ").append(toIndentedString(isSubstantiveHearing)).append("\n");
    sb.append("    hoInterview: ").append(toIndentedString(hoInterview)).append("\n");
    sb.append("    localAuthorityNumber: ").append(toIndentedString(localAuthorityNumber)).append("\n");
    sb.append("    submissionPeriod: ").append(toIndentedString(submissionPeriod)).append("\n");
    sb.append("    createdByUserId: ").append(toIndentedString(createdByUserId)).append("\n");
    sb.append("    isAmended: ").append(toIndentedString(isAmended)).append("\n");
    sb.append("    hasAssessment: ").append(toIndentedString(hasAssessment)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
  public static class Builder {

    private ClaimBase instance;

    public Builder() {
      this(new ClaimBase());
    }

    protected Builder(ClaimBase instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ClaimBase value) { 
      this.instance.setId(value.id);
      this.instance.setSubmissionId(value.submissionId);
      this.instance.setStatus(value.status);
      this.instance.setScheduleReference(value.scheduleReference);
      this.instance.setLineNumber(value.lineNumber);
      this.instance.setCaseReferenceNumber(value.caseReferenceNumber);
      this.instance.setUniqueFileNumber(value.uniqueFileNumber);
      this.instance.setCaseStartDate(value.caseStartDate);
      this.instance.setCaseConcludedDate(value.caseConcludedDate);
      this.instance.setMatterTypeCode(value.matterTypeCode);
      this.instance.setCrimeMatterTypeCode(value.crimeMatterTypeCode);
      this.instance.setFeeSchemeCode(value.feeSchemeCode);
      this.instance.setFeeCode(value.feeCode);
      this.instance.setProcurementAreaCode(value.procurementAreaCode);
      this.instance.setAccessPointCode(value.accessPointCode);
      this.instance.setDeliveryLocation(value.deliveryLocation);
      this.instance.setRepresentationOrderDate(value.representationOrderDate);
      this.instance.setSuspectsDefendantsCount(value.suspectsDefendantsCount);
      this.instance.setPoliceStationCourtAttendancesCount(value.policeStationCourtAttendancesCount);
      this.instance.setPoliceStationCourtPrisonId(value.policeStationCourtPrisonId);
      this.instance.setDsccNumber(value.dsccNumber);
      this.instance.setMaatId(value.maatId);
      this.instance.setPrisonLawPriorApprovalNumber(value.prisonLawPriorApprovalNumber);
      this.instance.setIsDutySolicitor(value.isDutySolicitor);
      this.instance.setIsYouthCourt(value.isYouthCourt);
      this.instance.setSchemeId(value.schemeId);
      this.instance.setMediationSessionsCount(value.mediationSessionsCount);
      this.instance.setMediationTimeMinutes(value.mediationTimeMinutes);
      this.instance.setOutreachLocation(value.outreachLocation);
      this.instance.setReferralSource(value.referralSource);
      this.instance.setClientForename(value.clientForename);
      this.instance.setClientSurname(value.clientSurname);
      this.instance.setClientDateOfBirth(value.clientDateOfBirth);
      this.instance.setUniqueClientNumber(value.uniqueClientNumber);
      this.instance.setClientPostcode(value.clientPostcode);
      this.instance.setGenderCode(value.genderCode);
      this.instance.setEthnicityCode(value.ethnicityCode);
      this.instance.setDisabilityCode(value.disabilityCode);
      this.instance.setIsLegallyAided(value.isLegallyAided);
      this.instance.setClientTypeCode(value.clientTypeCode);
      this.instance.setHomeOfficeClientNumber(value.homeOfficeClientNumber);
      this.instance.setClaReferenceNumber(value.claReferenceNumber);
      this.instance.setClaExemptionCode(value.claExemptionCode);
      this.instance.setClient2Forename(value.client2Forename);
      this.instance.setClient2Surname(value.client2Surname);
      this.instance.setClient2DateOfBirth(value.client2DateOfBirth);
      this.instance.setClient2Ucn(value.client2Ucn);
      this.instance.setClient2Postcode(value.client2Postcode);
      this.instance.setClient2GenderCode(value.client2GenderCode);
      this.instance.setClient2EthnicityCode(value.client2EthnicityCode);
      this.instance.setClient2DisabilityCode(value.client2DisabilityCode);
      this.instance.setClient2IsLegallyAided(value.client2IsLegallyAided);
      this.instance.setCaseId(value.caseId);
      this.instance.setUniqueCaseId(value.uniqueCaseId);
      this.instance.setCaseStageCode(value.caseStageCode);
      this.instance.setStageReachedCode(value.stageReachedCode);
      this.instance.setStandardFeeCategoryCode(value.standardFeeCategoryCode);
      this.instance.setOutcomeCode(value.outcomeCode);
      this.instance.setDesignatedAccreditedRepresentativeCode(value.designatedAccreditedRepresentativeCode);
      this.instance.setIsPostalApplicationAccepted(value.isPostalApplicationAccepted);
      this.instance.setIsClient2PostalApplicationAccepted(value.isClient2PostalApplicationAccepted);
      this.instance.setMentalHealthTribunalReference(value.mentalHealthTribunalReference);
      this.instance.setIsNrmAdvice(value.isNrmAdvice);
      this.instance.setFollowOnWork(value.followOnWork);
      this.instance.setTransferDate(value.transferDate);
      this.instance.setExemptionCriteriaSatisfied(value.exemptionCriteriaSatisfied);
      this.instance.setExceptionalCaseFundingReference(value.exceptionalCaseFundingReference);
      this.instance.setIsLegacyCase(value.isLegacyCase);
      this.instance.setAdviceTime(value.adviceTime);
      this.instance.setTravelTime(value.travelTime);
      this.instance.setWaitingTime(value.waitingTime);
      this.instance.setNetProfitCostsAmount(value.netProfitCostsAmount);
      this.instance.setNetDisbursementAmount(value.netDisbursementAmount);
      this.instance.setNetCounselCostsAmount(value.netCounselCostsAmount);
      this.instance.setDisbursementsVatAmount(value.disbursementsVatAmount);
      this.instance.setTravelWaitingCostsAmount(value.travelWaitingCostsAmount);
      this.instance.setNetWaitingCostsAmount(value.netWaitingCostsAmount);
      this.instance.setIsVatApplicable(value.isVatApplicable);
      this.instance.setIsToleranceApplicable(value.isToleranceApplicable);
      this.instance.setPriorAuthorityReference(value.priorAuthorityReference);
      this.instance.setIsLondonRate(value.isLondonRate);
      this.instance.setAdjournedHearingFeeAmount(value.adjournedHearingFeeAmount);
      this.instance.setIsAdditionalTravelPayment(value.isAdditionalTravelPayment);
      this.instance.setCostsDamagesRecoveredAmount(value.costsDamagesRecoveredAmount);
      this.instance.setMeetingsAttendedCode(value.meetingsAttendedCode);
      this.instance.setDetentionTravelWaitingCostsAmount(value.detentionTravelWaitingCostsAmount);
      this.instance.setJrFormFillingAmount(value.jrFormFillingAmount);
      this.instance.setIsEligibleClient(value.isEligibleClient);
      this.instance.setCourtLocationCode(value.courtLocationCode);
      this.instance.setAdviceTypeCode(value.adviceTypeCode);
      this.instance.setMedicalReportsCount(value.medicalReportsCount);
      this.instance.setIsIrcSurgery(value.isIrcSurgery);
      this.instance.setSurgeryDate(value.surgeryDate);
      this.instance.setSurgeryClientsCount(value.surgeryClientsCount);
      this.instance.setSurgeryMattersCount(value.surgeryMattersCount);
      this.instance.setCmrhOralCount(value.cmrhOralCount);
      this.instance.setCmrhTelephoneCount(value.cmrhTelephoneCount);
      this.instance.setAitHearingCentreCode(value.aitHearingCentreCode);
      this.instance.setIsSubstantiveHearing(value.isSubstantiveHearing);
      this.instance.setHoInterview(value.hoInterview);
      this.instance.setLocalAuthorityNumber(value.localAuthorityNumber);
      this.instance.setSubmissionPeriod(value.submissionPeriod);
      this.instance.setCreatedByUserId(value.createdByUserId);
      this.instance.setIsAmended(value.isAmended);
      this.instance.setHasAssessment(value.hasAssessment);
      this.instance.setVersion(value.version);
      return this;
    }

    public ClaimBase.Builder id(String id) {
      this.instance.id(id);
      return this;
    }
    
    public ClaimBase.Builder submissionId(String submissionId) {
      this.instance.submissionId(submissionId);
      return this;
    }
    
    public ClaimBase.Builder status(ClaimStatus status) {
      this.instance.status(status);
      return this;
    }
    
    public ClaimBase.Builder scheduleReference(String scheduleReference) {
      this.instance.scheduleReference(scheduleReference);
      return this;
    }
    
    public ClaimBase.Builder lineNumber(Integer lineNumber) {
      this.instance.lineNumber(lineNumber);
      return this;
    }
    
    public ClaimBase.Builder caseReferenceNumber(String caseReferenceNumber) {
      this.instance.caseReferenceNumber(caseReferenceNumber);
      return this;
    }
    
    public ClaimBase.Builder uniqueFileNumber(String uniqueFileNumber) {
      this.instance.uniqueFileNumber(uniqueFileNumber);
      return this;
    }
    
    public ClaimBase.Builder caseStartDate(String caseStartDate) {
      this.instance.caseStartDate(caseStartDate);
      return this;
    }
    
    public ClaimBase.Builder caseConcludedDate(String caseConcludedDate) {
      this.instance.caseConcludedDate(caseConcludedDate);
      return this;
    }
    
    public ClaimBase.Builder matterTypeCode(String matterTypeCode) {
      this.instance.matterTypeCode(matterTypeCode);
      return this;
    }
    
    public ClaimBase.Builder crimeMatterTypeCode(String crimeMatterTypeCode) {
      this.instance.crimeMatterTypeCode(crimeMatterTypeCode);
      return this;
    }
    
    public ClaimBase.Builder feeSchemeCode(String feeSchemeCode) {
      this.instance.feeSchemeCode(feeSchemeCode);
      return this;
    }
    
    public ClaimBase.Builder feeCode(String feeCode) {
      this.instance.feeCode(feeCode);
      return this;
    }
    
    public ClaimBase.Builder procurementAreaCode(String procurementAreaCode) {
      this.instance.procurementAreaCode(procurementAreaCode);
      return this;
    }
    
    public ClaimBase.Builder accessPointCode(String accessPointCode) {
      this.instance.accessPointCode(accessPointCode);
      return this;
    }
    
    public ClaimBase.Builder deliveryLocation(String deliveryLocation) {
      this.instance.deliveryLocation(deliveryLocation);
      return this;
    }
    
    public ClaimBase.Builder representationOrderDate(String representationOrderDate) {
      this.instance.representationOrderDate(representationOrderDate);
      return this;
    }
    
    public ClaimBase.Builder suspectsDefendantsCount(Integer suspectsDefendantsCount) {
      this.instance.suspectsDefendantsCount(suspectsDefendantsCount);
      return this;
    }
    
    public ClaimBase.Builder policeStationCourtAttendancesCount(Integer policeStationCourtAttendancesCount) {
      this.instance.policeStationCourtAttendancesCount(policeStationCourtAttendancesCount);
      return this;
    }
    
    public ClaimBase.Builder policeStationCourtPrisonId(String policeStationCourtPrisonId) {
      this.instance.policeStationCourtPrisonId(policeStationCourtPrisonId);
      return this;
    }
    
    public ClaimBase.Builder dsccNumber(String dsccNumber) {
      this.instance.dsccNumber(dsccNumber);
      return this;
    }
    
    public ClaimBase.Builder maatId(String maatId) {
      this.instance.maatId(maatId);
      return this;
    }
    
    public ClaimBase.Builder prisonLawPriorApprovalNumber(String prisonLawPriorApprovalNumber) {
      this.instance.prisonLawPriorApprovalNumber(prisonLawPriorApprovalNumber);
      return this;
    }
    
    public ClaimBase.Builder isDutySolicitor(Boolean isDutySolicitor) {
      this.instance.isDutySolicitor(isDutySolicitor);
      return this;
    }
    
    public ClaimBase.Builder isYouthCourt(Boolean isYouthCourt) {
      this.instance.isYouthCourt(isYouthCourt);
      return this;
    }
    
    public ClaimBase.Builder schemeId(String schemeId) {
      this.instance.schemeId(schemeId);
      return this;
    }
    
    public ClaimBase.Builder mediationSessionsCount(Integer mediationSessionsCount) {
      this.instance.mediationSessionsCount(mediationSessionsCount);
      return this;
    }
    
    public ClaimBase.Builder mediationTimeMinutes(Integer mediationTimeMinutes) {
      this.instance.mediationTimeMinutes(mediationTimeMinutes);
      return this;
    }
    
    public ClaimBase.Builder outreachLocation(String outreachLocation) {
      this.instance.outreachLocation(outreachLocation);
      return this;
    }
    
    public ClaimBase.Builder referralSource(String referralSource) {
      this.instance.referralSource(referralSource);
      return this;
    }
    
    public ClaimBase.Builder clientForename(String clientForename) {
      this.instance.clientForename(clientForename);
      return this;
    }
    
    public ClaimBase.Builder clientSurname(String clientSurname) {
      this.instance.clientSurname(clientSurname);
      return this;
    }
    
    public ClaimBase.Builder clientDateOfBirth(String clientDateOfBirth) {
      this.instance.clientDateOfBirth(clientDateOfBirth);
      return this;
    }
    
    public ClaimBase.Builder uniqueClientNumber(String uniqueClientNumber) {
      this.instance.uniqueClientNumber(uniqueClientNumber);
      return this;
    }
    
    public ClaimBase.Builder clientPostcode(String clientPostcode) {
      this.instance.clientPostcode(clientPostcode);
      return this;
    }
    
    public ClaimBase.Builder genderCode(String genderCode) {
      this.instance.genderCode(genderCode);
      return this;
    }
    
    public ClaimBase.Builder ethnicityCode(String ethnicityCode) {
      this.instance.ethnicityCode(ethnicityCode);
      return this;
    }
    
    public ClaimBase.Builder disabilityCode(String disabilityCode) {
      this.instance.disabilityCode(disabilityCode);
      return this;
    }
    
    public ClaimBase.Builder isLegallyAided(Boolean isLegallyAided) {
      this.instance.isLegallyAided(isLegallyAided);
      return this;
    }
    
    public ClaimBase.Builder clientTypeCode(String clientTypeCode) {
      this.instance.clientTypeCode(clientTypeCode);
      return this;
    }
    
    public ClaimBase.Builder homeOfficeClientNumber(String homeOfficeClientNumber) {
      this.instance.homeOfficeClientNumber(homeOfficeClientNumber);
      return this;
    }
    
    public ClaimBase.Builder claReferenceNumber(String claReferenceNumber) {
      this.instance.claReferenceNumber(claReferenceNumber);
      return this;
    }
    
    public ClaimBase.Builder claExemptionCode(String claExemptionCode) {
      this.instance.claExemptionCode(claExemptionCode);
      return this;
    }
    
    public ClaimBase.Builder client2Forename(String client2Forename) {
      this.instance.client2Forename(client2Forename);
      return this;
    }
    
    public ClaimBase.Builder client2Surname(String client2Surname) {
      this.instance.client2Surname(client2Surname);
      return this;
    }
    
    public ClaimBase.Builder client2DateOfBirth(String client2DateOfBirth) {
      this.instance.client2DateOfBirth(client2DateOfBirth);
      return this;
    }
    
    public ClaimBase.Builder client2Ucn(String client2Ucn) {
      this.instance.client2Ucn(client2Ucn);
      return this;
    }
    
    public ClaimBase.Builder client2Postcode(String client2Postcode) {
      this.instance.client2Postcode(client2Postcode);
      return this;
    }
    
    public ClaimBase.Builder client2GenderCode(String client2GenderCode) {
      this.instance.client2GenderCode(client2GenderCode);
      return this;
    }
    
    public ClaimBase.Builder client2EthnicityCode(String client2EthnicityCode) {
      this.instance.client2EthnicityCode(client2EthnicityCode);
      return this;
    }
    
    public ClaimBase.Builder client2DisabilityCode(String client2DisabilityCode) {
      this.instance.client2DisabilityCode(client2DisabilityCode);
      return this;
    }
    
    public ClaimBase.Builder client2IsLegallyAided(Boolean client2IsLegallyAided) {
      this.instance.client2IsLegallyAided(client2IsLegallyAided);
      return this;
    }
    
    public ClaimBase.Builder caseId(String caseId) {
      this.instance.caseId(caseId);
      return this;
    }
    
    public ClaimBase.Builder uniqueCaseId(String uniqueCaseId) {
      this.instance.uniqueCaseId(uniqueCaseId);
      return this;
    }
    
    public ClaimBase.Builder caseStageCode(String caseStageCode) {
      this.instance.caseStageCode(caseStageCode);
      return this;
    }
    
    public ClaimBase.Builder stageReachedCode(String stageReachedCode) {
      this.instance.stageReachedCode(stageReachedCode);
      return this;
    }
    
    public ClaimBase.Builder standardFeeCategoryCode(String standardFeeCategoryCode) {
      this.instance.standardFeeCategoryCode(standardFeeCategoryCode);
      return this;
    }
    
    public ClaimBase.Builder outcomeCode(String outcomeCode) {
      this.instance.outcomeCode(outcomeCode);
      return this;
    }
    
    public ClaimBase.Builder designatedAccreditedRepresentativeCode(String designatedAccreditedRepresentativeCode) {
      this.instance.designatedAccreditedRepresentativeCode(designatedAccreditedRepresentativeCode);
      return this;
    }
    
    public ClaimBase.Builder isPostalApplicationAccepted(Boolean isPostalApplicationAccepted) {
      this.instance.isPostalApplicationAccepted(isPostalApplicationAccepted);
      return this;
    }
    
    public ClaimBase.Builder isClient2PostalApplicationAccepted(Boolean isClient2PostalApplicationAccepted) {
      this.instance.isClient2PostalApplicationAccepted(isClient2PostalApplicationAccepted);
      return this;
    }
    
    public ClaimBase.Builder mentalHealthTribunalReference(String mentalHealthTribunalReference) {
      this.instance.mentalHealthTribunalReference(mentalHealthTribunalReference);
      return this;
    }
    
    public ClaimBase.Builder isNrmAdvice(Boolean isNrmAdvice) {
      this.instance.isNrmAdvice(isNrmAdvice);
      return this;
    }
    
    public ClaimBase.Builder followOnWork(String followOnWork) {
      this.instance.followOnWork(followOnWork);
      return this;
    }
    
    public ClaimBase.Builder transferDate(String transferDate) {
      this.instance.transferDate(transferDate);
      return this;
    }
    
    public ClaimBase.Builder exemptionCriteriaSatisfied(String exemptionCriteriaSatisfied) {
      this.instance.exemptionCriteriaSatisfied(exemptionCriteriaSatisfied);
      return this;
    }
    
    public ClaimBase.Builder exceptionalCaseFundingReference(String exceptionalCaseFundingReference) {
      this.instance.exceptionalCaseFundingReference(exceptionalCaseFundingReference);
      return this;
    }
    
    public ClaimBase.Builder isLegacyCase(Boolean isLegacyCase) {
      this.instance.isLegacyCase(isLegacyCase);
      return this;
    }
    
    public ClaimBase.Builder adviceTime(Integer adviceTime) {
      this.instance.adviceTime(adviceTime);
      return this;
    }
    
    public ClaimBase.Builder travelTime(Integer travelTime) {
      this.instance.travelTime(travelTime);
      return this;
    }
    
    public ClaimBase.Builder waitingTime(Integer waitingTime) {
      this.instance.waitingTime(waitingTime);
      return this;
    }
    
    public ClaimBase.Builder netProfitCostsAmount(BigDecimal netProfitCostsAmount) {
      this.instance.netProfitCostsAmount(netProfitCostsAmount);
      return this;
    }
    
    public ClaimBase.Builder netDisbursementAmount(BigDecimal netDisbursementAmount) {
      this.instance.netDisbursementAmount(netDisbursementAmount);
      return this;
    }
    
    public ClaimBase.Builder netCounselCostsAmount(BigDecimal netCounselCostsAmount) {
      this.instance.netCounselCostsAmount(netCounselCostsAmount);
      return this;
    }
    
    public ClaimBase.Builder disbursementsVatAmount(BigDecimal disbursementsVatAmount) {
      this.instance.disbursementsVatAmount(disbursementsVatAmount);
      return this;
    }
    
    public ClaimBase.Builder travelWaitingCostsAmount(BigDecimal travelWaitingCostsAmount) {
      this.instance.travelWaitingCostsAmount(travelWaitingCostsAmount);
      return this;
    }
    
    public ClaimBase.Builder netWaitingCostsAmount(BigDecimal netWaitingCostsAmount) {
      this.instance.netWaitingCostsAmount(netWaitingCostsAmount);
      return this;
    }
    
    public ClaimBase.Builder isVatApplicable(Boolean isVatApplicable) {
      this.instance.isVatApplicable(isVatApplicable);
      return this;
    }
    
    public ClaimBase.Builder isToleranceApplicable(Boolean isToleranceApplicable) {
      this.instance.isToleranceApplicable(isToleranceApplicable);
      return this;
    }
    
    public ClaimBase.Builder priorAuthorityReference(String priorAuthorityReference) {
      this.instance.priorAuthorityReference(priorAuthorityReference);
      return this;
    }
    
    public ClaimBase.Builder isLondonRate(Boolean isLondonRate) {
      this.instance.isLondonRate(isLondonRate);
      return this;
    }
    
    public ClaimBase.Builder adjournedHearingFeeAmount(Integer adjournedHearingFeeAmount) {
      this.instance.adjournedHearingFeeAmount(adjournedHearingFeeAmount);
      return this;
    }
    
    public ClaimBase.Builder isAdditionalTravelPayment(Boolean isAdditionalTravelPayment) {
      this.instance.isAdditionalTravelPayment(isAdditionalTravelPayment);
      return this;
    }
    
    public ClaimBase.Builder costsDamagesRecoveredAmount(BigDecimal costsDamagesRecoveredAmount) {
      this.instance.costsDamagesRecoveredAmount(costsDamagesRecoveredAmount);
      return this;
    }
    
    public ClaimBase.Builder meetingsAttendedCode(String meetingsAttendedCode) {
      this.instance.meetingsAttendedCode(meetingsAttendedCode);
      return this;
    }
    
    public ClaimBase.Builder detentionTravelWaitingCostsAmount(BigDecimal detentionTravelWaitingCostsAmount) {
      this.instance.detentionTravelWaitingCostsAmount(detentionTravelWaitingCostsAmount);
      return this;
    }
    
    public ClaimBase.Builder jrFormFillingAmount(BigDecimal jrFormFillingAmount) {
      this.instance.jrFormFillingAmount(jrFormFillingAmount);
      return this;
    }
    
    public ClaimBase.Builder isEligibleClient(Boolean isEligibleClient) {
      this.instance.isEligibleClient(isEligibleClient);
      return this;
    }
    
    public ClaimBase.Builder courtLocationCode(String courtLocationCode) {
      this.instance.courtLocationCode(courtLocationCode);
      return this;
    }
    
    public ClaimBase.Builder adviceTypeCode(String adviceTypeCode) {
      this.instance.adviceTypeCode(adviceTypeCode);
      return this;
    }
    
    public ClaimBase.Builder medicalReportsCount(Integer medicalReportsCount) {
      this.instance.medicalReportsCount(medicalReportsCount);
      return this;
    }
    
    public ClaimBase.Builder isIrcSurgery(Boolean isIrcSurgery) {
      this.instance.isIrcSurgery(isIrcSurgery);
      return this;
    }
    
    public ClaimBase.Builder surgeryDate(String surgeryDate) {
      this.instance.surgeryDate(surgeryDate);
      return this;
    }
    
    public ClaimBase.Builder surgeryClientsCount(Integer surgeryClientsCount) {
      this.instance.surgeryClientsCount(surgeryClientsCount);
      return this;
    }
    
    public ClaimBase.Builder surgeryMattersCount(Integer surgeryMattersCount) {
      this.instance.surgeryMattersCount(surgeryMattersCount);
      return this;
    }
    
    public ClaimBase.Builder cmrhOralCount(Integer cmrhOralCount) {
      this.instance.cmrhOralCount(cmrhOralCount);
      return this;
    }
    
    public ClaimBase.Builder cmrhTelephoneCount(Integer cmrhTelephoneCount) {
      this.instance.cmrhTelephoneCount(cmrhTelephoneCount);
      return this;
    }
    
    public ClaimBase.Builder aitHearingCentreCode(String aitHearingCentreCode) {
      this.instance.aitHearingCentreCode(aitHearingCentreCode);
      return this;
    }
    
    public ClaimBase.Builder isSubstantiveHearing(Boolean isSubstantiveHearing) {
      this.instance.isSubstantiveHearing(isSubstantiveHearing);
      return this;
    }
    
    public ClaimBase.Builder hoInterview(Integer hoInterview) {
      this.instance.hoInterview(hoInterview);
      return this;
    }
    
    public ClaimBase.Builder localAuthorityNumber(String localAuthorityNumber) {
      this.instance.localAuthorityNumber(localAuthorityNumber);
      return this;
    }
    
    public ClaimBase.Builder submissionPeriod(String submissionPeriod) {
      this.instance.submissionPeriod(submissionPeriod);
      return this;
    }
    
    public ClaimBase.Builder createdByUserId(String createdByUserId) {
      this.instance.createdByUserId(createdByUserId);
      return this;
    }
    
    public ClaimBase.Builder isAmended(Boolean isAmended) {
      this.instance.isAmended(isAmended);
      return this;
    }
    
    public ClaimBase.Builder hasAssessment(Boolean hasAssessment) {
      this.instance.hasAssessment(hasAssessment);
      return this;
    }
    
    public ClaimBase.Builder version(Long version) {
      this.instance.version(version);
      return this;
    }
    
    /**
    * returns a built ClaimBase instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ClaimBase build() {
      try {
        return this.instance;
      } finally {
        // ensure that this.instance is not reused
        this.instance = null;
      }
    }

    @Override
    public String toString() {
      return getClass() + "=(" + instance + ")";
    }
  }

  /**
  * Create a builder with no initialized field (except for the default values).
  */
  public static ClaimBase.Builder builder() {
    return new ClaimBase.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ClaimBase.Builder toBuilder() {
    ClaimBase.Builder builder = new ClaimBase.Builder();
    return builder.copyOf(this);
  }

}

