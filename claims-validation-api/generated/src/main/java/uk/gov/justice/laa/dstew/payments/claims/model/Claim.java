package uk.gov.justice.laa.dstew.payments.claims.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimStatus;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Claim data for validation
 */

@Schema(name = "Claim", description = "Claim data for validation")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-10T19:17:34.176544Z[Europe/London]", comments = "Generator version: 7.18.0")
public class Claim implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable AreaOfLaw areaOfLaw;

  private @Nullable String officeAccountNumber;

  private @Nullable UUID id;

  private @Nullable UUID submissionId;

  private @Nullable ClaimStatus status;

  private @Nullable Integer lineNumber;

  private @Nullable String scheduleReference;

  private @Nullable String submissionPeriod;

  private @Nullable String caseReferenceNumber;

  private @Nullable String uniqueFileNumber;

  private @Nullable String caseStartDate;

  private @Nullable String caseConcludedDate;

  private @Nullable String caseId;

  private @Nullable String uniqueCaseId;

  private @Nullable String caseStageCode;

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

  private @Nullable String createdByUserId;

  private @Nullable Boolean isAmended;

  private @Nullable Boolean hasAssessment;

  private @Nullable Integer version;

  public Claim areaOfLaw(@Nullable AreaOfLaw areaOfLaw) {
    this.areaOfLaw = areaOfLaw;
    return this;
  }

  /**
   * Get areaOfLaw
   * @return areaOfLaw
   */
  @Valid 
  @Schema(name = "areaOfLaw", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("areaOfLaw")
  public @Nullable AreaOfLaw getAreaOfLaw() {
    return areaOfLaw;
  }

  public void setAreaOfLaw(@Nullable AreaOfLaw areaOfLaw) {
    this.areaOfLaw = areaOfLaw;
  }

  public Claim officeAccountNumber(@Nullable String officeAccountNumber) {
    this.officeAccountNumber = officeAccountNumber;
    return this;
  }

  /**
   * The unique account number assigned to the provider office by the LAA
   * @return officeAccountNumber
   */
  
  @Schema(name = "officeAccountNumber", example = "1A234B", description = "The unique account number assigned to the provider office by the LAA", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("officeAccountNumber")
  public @Nullable String getOfficeAccountNumber() {
    return officeAccountNumber;
  }

  public void setOfficeAccountNumber(@Nullable String officeAccountNumber) {
    this.officeAccountNumber = officeAccountNumber;
  }

  public Claim id(@Nullable UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Unique identifier for the claim
   * @return id
   */
  @Valid 
  @Schema(name = "id", description = "Unique identifier for the claim", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public @Nullable UUID getId() {
    return id;
  }

  public void setId(@Nullable UUID id) {
    this.id = id;
  }

  public Claim submissionId(@Nullable UUID submissionId) {
    this.submissionId = submissionId;
    return this;
  }

  /**
   * UUID of the parent submission
   * @return submissionId
   */
  @Valid 
  @Schema(name = "submissionId", description = "UUID of the parent submission", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("submissionId")
  public @Nullable UUID getSubmissionId() {
    return submissionId;
  }

  public void setSubmissionId(@Nullable UUID submissionId) {
    this.submissionId = submissionId;
  }

  public Claim status(@Nullable ClaimStatus status) {
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

  public Claim lineNumber(@Nullable Integer lineNumber) {
    this.lineNumber = lineNumber;
    return this;
  }

  /**
   * Line number within the submission
   * @return lineNumber
   */
  
  @Schema(name = "lineNumber", description = "Line number within the submission", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lineNumber")
  public @Nullable Integer getLineNumber() {
    return lineNumber;
  }

  public void setLineNumber(@Nullable Integer lineNumber) {
    this.lineNumber = lineNumber;
  }

  public Claim scheduleReference(@Nullable String scheduleReference) {
    this.scheduleReference = scheduleReference;
    return this;
  }

  /**
   * Reference to the schedule
   * @return scheduleReference
   */
  
  @Schema(name = "scheduleReference", description = "Reference to the schedule", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("scheduleReference")
  public @Nullable String getScheduleReference() {
    return scheduleReference;
  }

  public void setScheduleReference(@Nullable String scheduleReference) {
    this.scheduleReference = scheduleReference;
  }

  public Claim submissionPeriod(@Nullable String submissionPeriod) {
    this.submissionPeriod = submissionPeriod;
    return this;
  }

  /**
   * Submission period (e.g., \"JUL-2025\")
   * @return submissionPeriod
   */
  
  @Schema(name = "submissionPeriod", description = "Submission period (e.g., \"JUL-2025\")", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("submissionPeriod")
  public @Nullable String getSubmissionPeriod() {
    return submissionPeriod;
  }

  public void setSubmissionPeriod(@Nullable String submissionPeriod) {
    this.submissionPeriod = submissionPeriod;
  }

  public Claim caseReferenceNumber(@Nullable String caseReferenceNumber) {
    this.caseReferenceNumber = caseReferenceNumber;
    return this;
  }

  /**
   * Case reference number
   * @return caseReferenceNumber
   */
  
  @Schema(name = "caseReferenceNumber", description = "Case reference number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("caseReferenceNumber")
  public @Nullable String getCaseReferenceNumber() {
    return caseReferenceNumber;
  }

  public void setCaseReferenceNumber(@Nullable String caseReferenceNumber) {
    this.caseReferenceNumber = caseReferenceNumber;
  }

  public Claim uniqueFileNumber(@Nullable String uniqueFileNumber) {
    this.uniqueFileNumber = uniqueFileNumber;
    return this;
  }

  /**
   * Provider's unique case reference number for controlled work billing (format DDMMYY/NNN)
   * @return uniqueFileNumber
   */
  
  @Schema(name = "uniqueFileNumber", description = "Provider's unique case reference number for controlled work billing (format DDMMYY/NNN)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("uniqueFileNumber")
  public @Nullable String getUniqueFileNumber() {
    return uniqueFileNumber;
  }

  public void setUniqueFileNumber(@Nullable String uniqueFileNumber) {
    this.uniqueFileNumber = uniqueFileNumber;
  }

  public Claim caseStartDate(@Nullable String caseStartDate) {
    this.caseStartDate = caseStartDate;
    return this;
  }

  /**
   * Date the case was started (format YYYY-MM-DD)
   * @return caseStartDate
   */
  
  @Schema(name = "caseStartDate", description = "Date the case was started (format YYYY-MM-DD)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("caseStartDate")
  public @Nullable String getCaseStartDate() {
    return caseStartDate;
  }

  public void setCaseStartDate(@Nullable String caseStartDate) {
    this.caseStartDate = caseStartDate;
  }

  public Claim caseConcludedDate(@Nullable String caseConcludedDate) {
    this.caseConcludedDate = caseConcludedDate;
    return this;
  }

  /**
   * Date the case was concluded (format YYYY-MM-DD)
   * @return caseConcludedDate
   */
  
  @Schema(name = "caseConcludedDate", description = "Date the case was concluded (format YYYY-MM-DD)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("caseConcludedDate")
  public @Nullable String getCaseConcludedDate() {
    return caseConcludedDate;
  }

  public void setCaseConcludedDate(@Nullable String caseConcludedDate) {
    this.caseConcludedDate = caseConcludedDate;
  }

  public Claim caseId(@Nullable String caseId) {
    this.caseId = caseId;
    return this;
  }

  /**
   * Case identifier
   * @return caseId
   */
  
  @Schema(name = "caseId", description = "Case identifier", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("caseId")
  public @Nullable String getCaseId() {
    return caseId;
  }

  public void setCaseId(@Nullable String caseId) {
    this.caseId = caseId;
  }

  public Claim uniqueCaseId(@Nullable String uniqueCaseId) {
    this.uniqueCaseId = uniqueCaseId;
    return this;
  }

  /**
   * Unique case identifier
   * @return uniqueCaseId
   */
  
  @Schema(name = "uniqueCaseId", description = "Unique case identifier", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("uniqueCaseId")
  public @Nullable String getUniqueCaseId() {
    return uniqueCaseId;
  }

  public void setUniqueCaseId(@Nullable String uniqueCaseId) {
    this.uniqueCaseId = uniqueCaseId;
  }

  public Claim caseStageCode(@Nullable String caseStageCode) {
    this.caseStageCode = caseStageCode;
    return this;
  }

  /**
   * Code indicating the stage of the case
   * @return caseStageCode
   */
  
  @Schema(name = "caseStageCode", description = "Code indicating the stage of the case", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("caseStageCode")
  public @Nullable String getCaseStageCode() {
    return caseStageCode;
  }

  public void setCaseStageCode(@Nullable String caseStageCode) {
    this.caseStageCode = caseStageCode;
  }

  public Claim matterTypeCode(@Nullable String matterTypeCode) {
    this.matterTypeCode = matterTypeCode;
    return this;
  }

  /**
   * Matter type code
   * @return matterTypeCode
   */
  
  @Schema(name = "matterTypeCode", description = "Matter type code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("matterTypeCode")
  public @Nullable String getMatterTypeCode() {
    return matterTypeCode;
  }

  public void setMatterTypeCode(@Nullable String matterTypeCode) {
    this.matterTypeCode = matterTypeCode;
  }

  public Claim crimeMatterTypeCode(@Nullable String crimeMatterTypeCode) {
    this.crimeMatterTypeCode = crimeMatterTypeCode;
    return this;
  }

  /**
   * Crime-specific matter type code
   * @return crimeMatterTypeCode
   */
  
  @Schema(name = "crimeMatterTypeCode", description = "Crime-specific matter type code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("crimeMatterTypeCode")
  public @Nullable String getCrimeMatterTypeCode() {
    return crimeMatterTypeCode;
  }

  public void setCrimeMatterTypeCode(@Nullable String crimeMatterTypeCode) {
    this.crimeMatterTypeCode = crimeMatterTypeCode;
  }

  public Claim feeSchemeCode(@Nullable String feeSchemeCode) {
    this.feeSchemeCode = feeSchemeCode;
    return this;
  }

  /**
   * Fee scheme code
   * @return feeSchemeCode
   */
  
  @Schema(name = "feeSchemeCode", description = "Fee scheme code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("feeSchemeCode")
  public @Nullable String getFeeSchemeCode() {
    return feeSchemeCode;
  }

  public void setFeeSchemeCode(@Nullable String feeSchemeCode) {
    this.feeSchemeCode = feeSchemeCode;
  }

  public Claim feeCode(@Nullable String feeCode) {
    this.feeCode = feeCode;
    return this;
  }

  /**
   * Alphanumeric code identifying the specific fee type or category
   * @return feeCode
   */
  
  @Schema(name = "feeCode", description = "Alphanumeric code identifying the specific fee type or category", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("feeCode")
  public @Nullable String getFeeCode() {
    return feeCode;
  }

  public void setFeeCode(@Nullable String feeCode) {
    this.feeCode = feeCode;
  }

  public Claim procurementAreaCode(@Nullable String procurementAreaCode) {
    this.procurementAreaCode = procurementAreaCode;
    return this;
  }

  /**
   * Procurement area code
   * @return procurementAreaCode
   */
  
  @Schema(name = "procurementAreaCode", description = "Procurement area code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("procurementAreaCode")
  public @Nullable String getProcurementAreaCode() {
    return procurementAreaCode;
  }

  public void setProcurementAreaCode(@Nullable String procurementAreaCode) {
    this.procurementAreaCode = procurementAreaCode;
  }

  public Claim accessPointCode(@Nullable String accessPointCode) {
    this.accessPointCode = accessPointCode;
    return this;
  }

  /**
   * Access point code
   * @return accessPointCode
   */
  
  @Schema(name = "accessPointCode", description = "Access point code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("accessPointCode")
  public @Nullable String getAccessPointCode() {
    return accessPointCode;
  }

  public void setAccessPointCode(@Nullable String accessPointCode) {
    this.accessPointCode = accessPointCode;
  }

  public Claim deliveryLocation(@Nullable String deliveryLocation) {
    this.deliveryLocation = deliveryLocation;
    return this;
  }

  /**
   * Location where service was delivered
   * @return deliveryLocation
   */
  
  @Schema(name = "deliveryLocation", description = "Location where service was delivered", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("deliveryLocation")
  public @Nullable String getDeliveryLocation() {
    return deliveryLocation;
  }

  public void setDeliveryLocation(@Nullable String deliveryLocation) {
    this.deliveryLocation = deliveryLocation;
  }

  public Claim representationOrderDate(@Nullable String representationOrderDate) {
    this.representationOrderDate = representationOrderDate;
    return this;
  }

  /**
   * Date the representation order was created (format YYYY-MM-DD)
   * @return representationOrderDate
   */
  
  @Schema(name = "representationOrderDate", description = "Date the representation order was created (format YYYY-MM-DD)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("representationOrderDate")
  public @Nullable String getRepresentationOrderDate() {
    return representationOrderDate;
  }

  public void setRepresentationOrderDate(@Nullable String representationOrderDate) {
    this.representationOrderDate = representationOrderDate;
  }

  public Claim suspectsDefendantsCount(@Nullable Integer suspectsDefendantsCount) {
    this.suspectsDefendantsCount = suspectsDefendantsCount;
    return this;
  }

  /**
   * Number of suspects/defendants
   * @return suspectsDefendantsCount
   */
  
  @Schema(name = "suspectsDefendantsCount", description = "Number of suspects/defendants", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("suspectsDefendantsCount")
  public @Nullable Integer getSuspectsDefendantsCount() {
    return suspectsDefendantsCount;
  }

  public void setSuspectsDefendantsCount(@Nullable Integer suspectsDefendantsCount) {
    this.suspectsDefendantsCount = suspectsDefendantsCount;
  }

  public Claim policeStationCourtAttendancesCount(@Nullable Integer policeStationCourtAttendancesCount) {
    this.policeStationCourtAttendancesCount = policeStationCourtAttendancesCount;
    return this;
  }

  /**
   * Number of police station or court attendances
   * @return policeStationCourtAttendancesCount
   */
  
  @Schema(name = "policeStationCourtAttendancesCount", description = "Number of police station or court attendances", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("policeStationCourtAttendancesCount")
  public @Nullable Integer getPoliceStationCourtAttendancesCount() {
    return policeStationCourtAttendancesCount;
  }

  public void setPoliceStationCourtAttendancesCount(@Nullable Integer policeStationCourtAttendancesCount) {
    this.policeStationCourtAttendancesCount = policeStationCourtAttendancesCount;
  }

  public Claim policeStationCourtPrisonId(@Nullable String policeStationCourtPrisonId) {
    this.policeStationCourtPrisonId = policeStationCourtPrisonId;
    return this;
  }

  /**
   * Police station, court, or prison identifier
   * @return policeStationCourtPrisonId
   */
  
  @Schema(name = "policeStationCourtPrisonId", description = "Police station, court, or prison identifier", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("policeStationCourtPrisonId")
  public @Nullable String getPoliceStationCourtPrisonId() {
    return policeStationCourtPrisonId;
  }

  public void setPoliceStationCourtPrisonId(@Nullable String policeStationCourtPrisonId) {
    this.policeStationCourtPrisonId = policeStationCourtPrisonId;
  }

  public Claim dsccNumber(@Nullable String dsccNumber) {
    this.dsccNumber = dsccNumber;
    return this;
  }

  /**
   * DSCC number
   * @return dsccNumber
   */
  
  @Schema(name = "dsccNumber", description = "DSCC number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dsccNumber")
  public @Nullable String getDsccNumber() {
    return dsccNumber;
  }

  public void setDsccNumber(@Nullable String dsccNumber) {
    this.dsccNumber = dsccNumber;
  }

  public Claim maatId(@Nullable String maatId) {
    this.maatId = maatId;
    return this;
  }

  /**
   * MAAT identifier
   * @return maatId
   */
  
  @Schema(name = "maatId", description = "MAAT identifier", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("maatId")
  public @Nullable String getMaatId() {
    return maatId;
  }

  public void setMaatId(@Nullable String maatId) {
    this.maatId = maatId;
  }

  public Claim prisonLawPriorApprovalNumber(@Nullable String prisonLawPriorApprovalNumber) {
    this.prisonLawPriorApprovalNumber = prisonLawPriorApprovalNumber;
    return this;
  }

  /**
   * Prison law prior approval number
   * @return prisonLawPriorApprovalNumber
   */
  
  @Schema(name = "prisonLawPriorApprovalNumber", description = "Prison law prior approval number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prisonLawPriorApprovalNumber")
  public @Nullable String getPrisonLawPriorApprovalNumber() {
    return prisonLawPriorApprovalNumber;
  }

  public void setPrisonLawPriorApprovalNumber(@Nullable String prisonLawPriorApprovalNumber) {
    this.prisonLawPriorApprovalNumber = prisonLawPriorApprovalNumber;
  }

  public Claim isDutySolicitor(@Nullable Boolean isDutySolicitor) {
    this.isDutySolicitor = isDutySolicitor;
    return this;
  }

  /**
   * Whether this is a duty solicitor case
   * @return isDutySolicitor
   */
  
  @Schema(name = "isDutySolicitor", description = "Whether this is a duty solicitor case", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isDutySolicitor")
  public @Nullable Boolean getIsDutySolicitor() {
    return isDutySolicitor;
  }

  public void setIsDutySolicitor(@Nullable Boolean isDutySolicitor) {
    this.isDutySolicitor = isDutySolicitor;
  }

  public Claim isYouthCourt(@Nullable Boolean isYouthCourt) {
    this.isYouthCourt = isYouthCourt;
    return this;
  }

  /**
   * Whether this is a youth court case
   * @return isYouthCourt
   */
  
  @Schema(name = "isYouthCourt", description = "Whether this is a youth court case", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isYouthCourt")
  public @Nullable Boolean getIsYouthCourt() {
    return isYouthCourt;
  }

  public void setIsYouthCourt(@Nullable Boolean isYouthCourt) {
    this.isYouthCourt = isYouthCourt;
  }

  public Claim schemeId(@Nullable String schemeId) {
    this.schemeId = schemeId;
    return this;
  }

  /**
   * Scheme identifier
   * @return schemeId
   */
  
  @Schema(name = "schemeId", description = "Scheme identifier", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("schemeId")
  public @Nullable String getSchemeId() {
    return schemeId;
  }

  public void setSchemeId(@Nullable String schemeId) {
    this.schemeId = schemeId;
  }

  public Claim mediationSessionsCount(@Nullable Integer mediationSessionsCount) {
    this.mediationSessionsCount = mediationSessionsCount;
    return this;
  }

  /**
   * Number of mediation sessions
   * @return mediationSessionsCount
   */
  
  @Schema(name = "mediationSessionsCount", description = "Number of mediation sessions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mediationSessionsCount")
  public @Nullable Integer getMediationSessionsCount() {
    return mediationSessionsCount;
  }

  public void setMediationSessionsCount(@Nullable Integer mediationSessionsCount) {
    this.mediationSessionsCount = mediationSessionsCount;
  }

  public Claim mediationTimeMinutes(@Nullable Integer mediationTimeMinutes) {
    this.mediationTimeMinutes = mediationTimeMinutes;
    return this;
  }

  /**
   * Total mediation time in minutes
   * @return mediationTimeMinutes
   */
  
  @Schema(name = "mediationTimeMinutes", description = "Total mediation time in minutes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mediationTimeMinutes")
  public @Nullable Integer getMediationTimeMinutes() {
    return mediationTimeMinutes;
  }

  public void setMediationTimeMinutes(@Nullable Integer mediationTimeMinutes) {
    this.mediationTimeMinutes = mediationTimeMinutes;
  }

  public Claim outreachLocation(@Nullable String outreachLocation) {
    this.outreachLocation = outreachLocation;
    return this;
  }

  /**
   * Outreach location
   * @return outreachLocation
   */
  
  @Schema(name = "outreachLocation", description = "Outreach location", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("outreachLocation")
  public @Nullable String getOutreachLocation() {
    return outreachLocation;
  }

  public void setOutreachLocation(@Nullable String outreachLocation) {
    this.outreachLocation = outreachLocation;
  }

  public Claim referralSource(@Nullable String referralSource) {
    this.referralSource = referralSource;
    return this;
  }

  /**
   * Source of referral
   * @return referralSource
   */
  
  @Schema(name = "referralSource", description = "Source of referral", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("referralSource")
  public @Nullable String getReferralSource() {
    return referralSource;
  }

  public void setReferralSource(@Nullable String referralSource) {
    this.referralSource = referralSource;
  }

  public Claim clientForename(@Nullable String clientForename) {
    this.clientForename = clientForename;
    return this;
  }

  /**
   * Client's first name
   * @return clientForename
   */
  
  @Schema(name = "clientForename", description = "Client's first name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("clientForename")
  public @Nullable String getClientForename() {
    return clientForename;
  }

  public void setClientForename(@Nullable String clientForename) {
    this.clientForename = clientForename;
  }

  public Claim clientSurname(@Nullable String clientSurname) {
    this.clientSurname = clientSurname;
    return this;
  }

  /**
   * Client's surname
   * @return clientSurname
   */
  
  @Schema(name = "clientSurname", description = "Client's surname", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("clientSurname")
  public @Nullable String getClientSurname() {
    return clientSurname;
  }

  public void setClientSurname(@Nullable String clientSurname) {
    this.clientSurname = clientSurname;
  }

  public Claim clientDateOfBirth(@Nullable String clientDateOfBirth) {
    this.clientDateOfBirth = clientDateOfBirth;
    return this;
  }

  /**
   * Client's date of birth (format YYYY-MM-DD)
   * @return clientDateOfBirth
   */
  
  @Schema(name = "clientDateOfBirth", description = "Client's date of birth (format YYYY-MM-DD)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("clientDateOfBirth")
  public @Nullable String getClientDateOfBirth() {
    return clientDateOfBirth;
  }

  public void setClientDateOfBirth(@Nullable String clientDateOfBirth) {
    this.clientDateOfBirth = clientDateOfBirth;
  }

  public Claim uniqueClientNumber(@Nullable String uniqueClientNumber) {
    this.uniqueClientNumber = uniqueClientNumber;
    return this;
  }

  /**
   * Unique client number
   * @return uniqueClientNumber
   */
  
  @Schema(name = "uniqueClientNumber", description = "Unique client number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("uniqueClientNumber")
  public @Nullable String getUniqueClientNumber() {
    return uniqueClientNumber;
  }

  public void setUniqueClientNumber(@Nullable String uniqueClientNumber) {
    this.uniqueClientNumber = uniqueClientNumber;
  }

  public Claim clientPostcode(@Nullable String clientPostcode) {
    this.clientPostcode = clientPostcode;
    return this;
  }

  /**
   * Client's postcode
   * @return clientPostcode
   */
  
  @Schema(name = "clientPostcode", description = "Client's postcode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("clientPostcode")
  public @Nullable String getClientPostcode() {
    return clientPostcode;
  }

  public void setClientPostcode(@Nullable String clientPostcode) {
    this.clientPostcode = clientPostcode;
  }

  public Claim genderCode(@Nullable String genderCode) {
    this.genderCode = genderCode;
    return this;
  }

  /**
   * Gender code
   * @return genderCode
   */
  
  @Schema(name = "genderCode", description = "Gender code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("genderCode")
  public @Nullable String getGenderCode() {
    return genderCode;
  }

  public void setGenderCode(@Nullable String genderCode) {
    this.genderCode = genderCode;
  }

  public Claim ethnicityCode(@Nullable String ethnicityCode) {
    this.ethnicityCode = ethnicityCode;
    return this;
  }

  /**
   * Ethnicity code
   * @return ethnicityCode
   */
  
  @Schema(name = "ethnicityCode", description = "Ethnicity code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ethnicityCode")
  public @Nullable String getEthnicityCode() {
    return ethnicityCode;
  }

  public void setEthnicityCode(@Nullable String ethnicityCode) {
    this.ethnicityCode = ethnicityCode;
  }

  public Claim disabilityCode(@Nullable String disabilityCode) {
    this.disabilityCode = disabilityCode;
    return this;
  }

  /**
   * Disability code
   * @return disabilityCode
   */
  
  @Schema(name = "disabilityCode", description = "Disability code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("disabilityCode")
  public @Nullable String getDisabilityCode() {
    return disabilityCode;
  }

  public void setDisabilityCode(@Nullable String disabilityCode) {
    this.disabilityCode = disabilityCode;
  }

  public Claim isLegallyAided(@Nullable Boolean isLegallyAided) {
    this.isLegallyAided = isLegallyAided;
    return this;
  }

  /**
   * Whether client is legally aided
   * @return isLegallyAided
   */
  
  @Schema(name = "isLegallyAided", description = "Whether client is legally aided", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isLegallyAided")
  public @Nullable Boolean getIsLegallyAided() {
    return isLegallyAided;
  }

  public void setIsLegallyAided(@Nullable Boolean isLegallyAided) {
    this.isLegallyAided = isLegallyAided;
  }

  public Claim clientTypeCode(@Nullable String clientTypeCode) {
    this.clientTypeCode = clientTypeCode;
    return this;
  }

  /**
   * Client type code
   * @return clientTypeCode
   */
  
  @Schema(name = "clientTypeCode", description = "Client type code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("clientTypeCode")
  public @Nullable String getClientTypeCode() {
    return clientTypeCode;
  }

  public void setClientTypeCode(@Nullable String clientTypeCode) {
    this.clientTypeCode = clientTypeCode;
  }

  public Claim homeOfficeClientNumber(@Nullable String homeOfficeClientNumber) {
    this.homeOfficeClientNumber = homeOfficeClientNumber;
    return this;
  }

  /**
   * Home Office client number (for immigration cases)
   * @return homeOfficeClientNumber
   */
  
  @Schema(name = "homeOfficeClientNumber", description = "Home Office client number (for immigration cases)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("homeOfficeClientNumber")
  public @Nullable String getHomeOfficeClientNumber() {
    return homeOfficeClientNumber;
  }

  public void setHomeOfficeClientNumber(@Nullable String homeOfficeClientNumber) {
    this.homeOfficeClientNumber = homeOfficeClientNumber;
  }

  public Claim claReferenceNumber(@Nullable String claReferenceNumber) {
    this.claReferenceNumber = claReferenceNumber;
    return this;
  }

  /**
   * CLA reference number
   * @return claReferenceNumber
   */
  
  @Schema(name = "claReferenceNumber", description = "CLA reference number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("claReferenceNumber")
  public @Nullable String getClaReferenceNumber() {
    return claReferenceNumber;
  }

  public void setClaReferenceNumber(@Nullable String claReferenceNumber) {
    this.claReferenceNumber = claReferenceNumber;
  }

  public Claim claExemptionCode(@Nullable String claExemptionCode) {
    this.claExemptionCode = claExemptionCode;
    return this;
  }

  /**
   * CLA exemption code
   * @return claExemptionCode
   */
  
  @Schema(name = "claExemptionCode", description = "CLA exemption code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("claExemptionCode")
  public @Nullable String getClaExemptionCode() {
    return claExemptionCode;
  }

  public void setClaExemptionCode(@Nullable String claExemptionCode) {
    this.claExemptionCode = claExemptionCode;
  }

  public Claim client2Forename(@Nullable String client2Forename) {
    this.client2Forename = client2Forename;
    return this;
  }

  /**
   * Second client's first name
   * @return client2Forename
   */
  
  @Schema(name = "client2Forename", description = "Second client's first name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client2Forename")
  public @Nullable String getClient2Forename() {
    return client2Forename;
  }

  public void setClient2Forename(@Nullable String client2Forename) {
    this.client2Forename = client2Forename;
  }

  public Claim client2Surname(@Nullable String client2Surname) {
    this.client2Surname = client2Surname;
    return this;
  }

  /**
   * Second client's surname
   * @return client2Surname
   */
  
  @Schema(name = "client2Surname", description = "Second client's surname", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client2Surname")
  public @Nullable String getClient2Surname() {
    return client2Surname;
  }

  public void setClient2Surname(@Nullable String client2Surname) {
    this.client2Surname = client2Surname;
  }

  public Claim client2DateOfBirth(@Nullable String client2DateOfBirth) {
    this.client2DateOfBirth = client2DateOfBirth;
    return this;
  }

  /**
   * Second client's date of birth (format YYYY-MM-DD)
   * @return client2DateOfBirth
   */
  
  @Schema(name = "client2DateOfBirth", description = "Second client's date of birth (format YYYY-MM-DD)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client2DateOfBirth")
  public @Nullable String getClient2DateOfBirth() {
    return client2DateOfBirth;
  }

  public void setClient2DateOfBirth(@Nullable String client2DateOfBirth) {
    this.client2DateOfBirth = client2DateOfBirth;
  }

  public Claim client2Ucn(@Nullable String client2Ucn) {
    this.client2Ucn = client2Ucn;
    return this;
  }

  /**
   * Second client's unique client number
   * @return client2Ucn
   */
  
  @Schema(name = "client2Ucn", description = "Second client's unique client number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client2Ucn")
  public @Nullable String getClient2Ucn() {
    return client2Ucn;
  }

  public void setClient2Ucn(@Nullable String client2Ucn) {
    this.client2Ucn = client2Ucn;
  }

  public Claim client2Postcode(@Nullable String client2Postcode) {
    this.client2Postcode = client2Postcode;
    return this;
  }

  /**
   * Second client's postcode
   * @return client2Postcode
   */
  
  @Schema(name = "client2Postcode", description = "Second client's postcode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client2Postcode")
  public @Nullable String getClient2Postcode() {
    return client2Postcode;
  }

  public void setClient2Postcode(@Nullable String client2Postcode) {
    this.client2Postcode = client2Postcode;
  }

  public Claim client2GenderCode(@Nullable String client2GenderCode) {
    this.client2GenderCode = client2GenderCode;
    return this;
  }

  /**
   * Second client's gender code
   * @return client2GenderCode
   */
  
  @Schema(name = "client2GenderCode", description = "Second client's gender code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client2GenderCode")
  public @Nullable String getClient2GenderCode() {
    return client2GenderCode;
  }

  public void setClient2GenderCode(@Nullable String client2GenderCode) {
    this.client2GenderCode = client2GenderCode;
  }

  public Claim client2EthnicityCode(@Nullable String client2EthnicityCode) {
    this.client2EthnicityCode = client2EthnicityCode;
    return this;
  }

  /**
   * Second client's ethnicity code
   * @return client2EthnicityCode
   */
  
  @Schema(name = "client2EthnicityCode", description = "Second client's ethnicity code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client2EthnicityCode")
  public @Nullable String getClient2EthnicityCode() {
    return client2EthnicityCode;
  }

  public void setClient2EthnicityCode(@Nullable String client2EthnicityCode) {
    this.client2EthnicityCode = client2EthnicityCode;
  }

  public Claim client2DisabilityCode(@Nullable String client2DisabilityCode) {
    this.client2DisabilityCode = client2DisabilityCode;
    return this;
  }

  /**
   * Second client's disability code
   * @return client2DisabilityCode
   */
  
  @Schema(name = "client2DisabilityCode", description = "Second client's disability code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client2DisabilityCode")
  public @Nullable String getClient2DisabilityCode() {
    return client2DisabilityCode;
  }

  public void setClient2DisabilityCode(@Nullable String client2DisabilityCode) {
    this.client2DisabilityCode = client2DisabilityCode;
  }

  public Claim client2IsLegallyAided(@Nullable Boolean client2IsLegallyAided) {
    this.client2IsLegallyAided = client2IsLegallyAided;
    return this;
  }

  /**
   * Whether second client is legally aided
   * @return client2IsLegallyAided
   */
  
  @Schema(name = "client2IsLegallyAided", description = "Whether second client is legally aided", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client2IsLegallyAided")
  public @Nullable Boolean getClient2IsLegallyAided() {
    return client2IsLegallyAided;
  }

  public void setClient2IsLegallyAided(@Nullable Boolean client2IsLegallyAided) {
    this.client2IsLegallyAided = client2IsLegallyAided;
  }

  public Claim stageReachedCode(@Nullable String stageReachedCode) {
    this.stageReachedCode = stageReachedCode;
    return this;
  }

  /**
   * Stage reached code
   * @return stageReachedCode
   */
  
  @Schema(name = "stageReachedCode", description = "Stage reached code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("stageReachedCode")
  public @Nullable String getStageReachedCode() {
    return stageReachedCode;
  }

  public void setStageReachedCode(@Nullable String stageReachedCode) {
    this.stageReachedCode = stageReachedCode;
  }

  public Claim standardFeeCategoryCode(@Nullable String standardFeeCategoryCode) {
    this.standardFeeCategoryCode = standardFeeCategoryCode;
    return this;
  }

  /**
   * Standard fee category code
   * @return standardFeeCategoryCode
   */
  
  @Schema(name = "standardFeeCategoryCode", description = "Standard fee category code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("standardFeeCategoryCode")
  public @Nullable String getStandardFeeCategoryCode() {
    return standardFeeCategoryCode;
  }

  public void setStandardFeeCategoryCode(@Nullable String standardFeeCategoryCode) {
    this.standardFeeCategoryCode = standardFeeCategoryCode;
  }

  public Claim outcomeCode(@Nullable String outcomeCode) {
    this.outcomeCode = outcomeCode;
    return this;
  }

  /**
   * Outcome code
   * @return outcomeCode
   */
  
  @Schema(name = "outcomeCode", description = "Outcome code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("outcomeCode")
  public @Nullable String getOutcomeCode() {
    return outcomeCode;
  }

  public void setOutcomeCode(@Nullable String outcomeCode) {
    this.outcomeCode = outcomeCode;
  }

  public Claim designatedAccreditedRepresentativeCode(@Nullable String designatedAccreditedRepresentativeCode) {
    this.designatedAccreditedRepresentativeCode = designatedAccreditedRepresentativeCode;
    return this;
  }

  /**
   * Designated accredited representative code
   * @return designatedAccreditedRepresentativeCode
   */
  
  @Schema(name = "designatedAccreditedRepresentativeCode", description = "Designated accredited representative code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("designatedAccreditedRepresentativeCode")
  public @Nullable String getDesignatedAccreditedRepresentativeCode() {
    return designatedAccreditedRepresentativeCode;
  }

  public void setDesignatedAccreditedRepresentativeCode(@Nullable String designatedAccreditedRepresentativeCode) {
    this.designatedAccreditedRepresentativeCode = designatedAccreditedRepresentativeCode;
  }

  public Claim isPostalApplicationAccepted(@Nullable Boolean isPostalApplicationAccepted) {
    this.isPostalApplicationAccepted = isPostalApplicationAccepted;
    return this;
  }

  /**
   * Whether postal application was accepted
   * @return isPostalApplicationAccepted
   */
  
  @Schema(name = "isPostalApplicationAccepted", description = "Whether postal application was accepted", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isPostalApplicationAccepted")
  public @Nullable Boolean getIsPostalApplicationAccepted() {
    return isPostalApplicationAccepted;
  }

  public void setIsPostalApplicationAccepted(@Nullable Boolean isPostalApplicationAccepted) {
    this.isPostalApplicationAccepted = isPostalApplicationAccepted;
  }

  public Claim isClient2PostalApplicationAccepted(@Nullable Boolean isClient2PostalApplicationAccepted) {
    this.isClient2PostalApplicationAccepted = isClient2PostalApplicationAccepted;
    return this;
  }

  /**
   * Whether second client's postal application was accepted
   * @return isClient2PostalApplicationAccepted
   */
  
  @Schema(name = "isClient2PostalApplicationAccepted", description = "Whether second client's postal application was accepted", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isClient2PostalApplicationAccepted")
  public @Nullable Boolean getIsClient2PostalApplicationAccepted() {
    return isClient2PostalApplicationAccepted;
  }

  public void setIsClient2PostalApplicationAccepted(@Nullable Boolean isClient2PostalApplicationAccepted) {
    this.isClient2PostalApplicationAccepted = isClient2PostalApplicationAccepted;
  }

  public Claim mentalHealthTribunalReference(@Nullable String mentalHealthTribunalReference) {
    this.mentalHealthTribunalReference = mentalHealthTribunalReference;
    return this;
  }

  /**
   * Mental health tribunal reference
   * @return mentalHealthTribunalReference
   */
  
  @Schema(name = "mentalHealthTribunalReference", description = "Mental health tribunal reference", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mentalHealthTribunalReference")
  public @Nullable String getMentalHealthTribunalReference() {
    return mentalHealthTribunalReference;
  }

  public void setMentalHealthTribunalReference(@Nullable String mentalHealthTribunalReference) {
    this.mentalHealthTribunalReference = mentalHealthTribunalReference;
  }

  public Claim isNrmAdvice(@Nullable Boolean isNrmAdvice) {
    this.isNrmAdvice = isNrmAdvice;
    return this;
  }

  /**
   * Whether NRM advice was provided
   * @return isNrmAdvice
   */
  
  @Schema(name = "isNrmAdvice", description = "Whether NRM advice was provided", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isNrmAdvice")
  public @Nullable Boolean getIsNrmAdvice() {
    return isNrmAdvice;
  }

  public void setIsNrmAdvice(@Nullable Boolean isNrmAdvice) {
    this.isNrmAdvice = isNrmAdvice;
  }

  public Claim followOnWork(@Nullable String followOnWork) {
    this.followOnWork = followOnWork;
    return this;
  }

  /**
   * Follow-on work details
   * @return followOnWork
   */
  
  @Schema(name = "followOnWork", description = "Follow-on work details", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("followOnWork")
  public @Nullable String getFollowOnWork() {
    return followOnWork;
  }

  public void setFollowOnWork(@Nullable String followOnWork) {
    this.followOnWork = followOnWork;
  }

  public Claim transferDate(@Nullable String transferDate) {
    this.transferDate = transferDate;
    return this;
  }

  /**
   * Transfer date (format YYYY-MM-DD)
   * @return transferDate
   */
  
  @Schema(name = "transferDate", description = "Transfer date (format YYYY-MM-DD)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("transferDate")
  public @Nullable String getTransferDate() {
    return transferDate;
  }

  public void setTransferDate(@Nullable String transferDate) {
    this.transferDate = transferDate;
  }

  public Claim exemptionCriteriaSatisfied(@Nullable String exemptionCriteriaSatisfied) {
    this.exemptionCriteriaSatisfied = exemptionCriteriaSatisfied;
    return this;
  }

  /**
   * Exemption criteria satisfied
   * @return exemptionCriteriaSatisfied
   */
  
  @Schema(name = "exemptionCriteriaSatisfied", description = "Exemption criteria satisfied", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("exemptionCriteriaSatisfied")
  public @Nullable String getExemptionCriteriaSatisfied() {
    return exemptionCriteriaSatisfied;
  }

  public void setExemptionCriteriaSatisfied(@Nullable String exemptionCriteriaSatisfied) {
    this.exemptionCriteriaSatisfied = exemptionCriteriaSatisfied;
  }

  public Claim exceptionalCaseFundingReference(@Nullable String exceptionalCaseFundingReference) {
    this.exceptionalCaseFundingReference = exceptionalCaseFundingReference;
    return this;
  }

  /**
   * Exceptional case funding reference
   * @return exceptionalCaseFundingReference
   */
  
  @Schema(name = "exceptionalCaseFundingReference", description = "Exceptional case funding reference", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("exceptionalCaseFundingReference")
  public @Nullable String getExceptionalCaseFundingReference() {
    return exceptionalCaseFundingReference;
  }

  public void setExceptionalCaseFundingReference(@Nullable String exceptionalCaseFundingReference) {
    this.exceptionalCaseFundingReference = exceptionalCaseFundingReference;
  }

  public Claim isLegacyCase(@Nullable Boolean isLegacyCase) {
    this.isLegacyCase = isLegacyCase;
    return this;
  }

  /**
   * Whether this is a legacy case
   * @return isLegacyCase
   */
  
  @Schema(name = "isLegacyCase", description = "Whether this is a legacy case", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isLegacyCase")
  public @Nullable Boolean getIsLegacyCase() {
    return isLegacyCase;
  }

  public void setIsLegacyCase(@Nullable Boolean isLegacyCase) {
    this.isLegacyCase = isLegacyCase;
  }

  public Claim adviceTime(@Nullable Integer adviceTime) {
    this.adviceTime = adviceTime;
    return this;
  }

  /**
   * Advice time in minutes
   * @return adviceTime
   */
  
  @Schema(name = "adviceTime", description = "Advice time in minutes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adviceTime")
  public @Nullable Integer getAdviceTime() {
    return adviceTime;
  }

  public void setAdviceTime(@Nullable Integer adviceTime) {
    this.adviceTime = adviceTime;
  }

  public Claim travelTime(@Nullable Integer travelTime) {
    this.travelTime = travelTime;
    return this;
  }

  /**
   * Travel time in minutes
   * @return travelTime
   */
  
  @Schema(name = "travelTime", description = "Travel time in minutes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("travelTime")
  public @Nullable Integer getTravelTime() {
    return travelTime;
  }

  public void setTravelTime(@Nullable Integer travelTime) {
    this.travelTime = travelTime;
  }

  public Claim waitingTime(@Nullable Integer waitingTime) {
    this.waitingTime = waitingTime;
    return this;
  }

  /**
   * Waiting time in minutes
   * @return waitingTime
   */
  
  @Schema(name = "waitingTime", description = "Waiting time in minutes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("waitingTime")
  public @Nullable Integer getWaitingTime() {
    return waitingTime;
  }

  public void setWaitingTime(@Nullable Integer waitingTime) {
    this.waitingTime = waitingTime;
  }

  public Claim netProfitCostsAmount(@Nullable BigDecimal netProfitCostsAmount) {
    this.netProfitCostsAmount = netProfitCostsAmount;
    return this;
  }

  /**
   * Net profit costs amount
   * @return netProfitCostsAmount
   */
  @Valid 
  @Schema(name = "netProfitCostsAmount", description = "Net profit costs amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("netProfitCostsAmount")
  public @Nullable BigDecimal getNetProfitCostsAmount() {
    return netProfitCostsAmount;
  }

  public void setNetProfitCostsAmount(@Nullable BigDecimal netProfitCostsAmount) {
    this.netProfitCostsAmount = netProfitCostsAmount;
  }

  public Claim netDisbursementAmount(@Nullable BigDecimal netDisbursementAmount) {
    this.netDisbursementAmount = netDisbursementAmount;
    return this;
  }

  /**
   * Net disbursement amount
   * @return netDisbursementAmount
   */
  @Valid 
  @Schema(name = "netDisbursementAmount", description = "Net disbursement amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("netDisbursementAmount")
  public @Nullable BigDecimal getNetDisbursementAmount() {
    return netDisbursementAmount;
  }

  public void setNetDisbursementAmount(@Nullable BigDecimal netDisbursementAmount) {
    this.netDisbursementAmount = netDisbursementAmount;
  }

  public Claim netCounselCostsAmount(@Nullable BigDecimal netCounselCostsAmount) {
    this.netCounselCostsAmount = netCounselCostsAmount;
    return this;
  }

  /**
   * Net counsel costs amount
   * @return netCounselCostsAmount
   */
  @Valid 
  @Schema(name = "netCounselCostsAmount", description = "Net counsel costs amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("netCounselCostsAmount")
  public @Nullable BigDecimal getNetCounselCostsAmount() {
    return netCounselCostsAmount;
  }

  public void setNetCounselCostsAmount(@Nullable BigDecimal netCounselCostsAmount) {
    this.netCounselCostsAmount = netCounselCostsAmount;
  }

  public Claim disbursementsVatAmount(@Nullable BigDecimal disbursementsVatAmount) {
    this.disbursementsVatAmount = disbursementsVatAmount;
    return this;
  }

  /**
   * Disbursements VAT amount
   * @return disbursementsVatAmount
   */
  @Valid 
  @Schema(name = "disbursementsVatAmount", description = "Disbursements VAT amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("disbursementsVatAmount")
  public @Nullable BigDecimal getDisbursementsVatAmount() {
    return disbursementsVatAmount;
  }

  public void setDisbursementsVatAmount(@Nullable BigDecimal disbursementsVatAmount) {
    this.disbursementsVatAmount = disbursementsVatAmount;
  }

  public Claim travelWaitingCostsAmount(@Nullable BigDecimal travelWaitingCostsAmount) {
    this.travelWaitingCostsAmount = travelWaitingCostsAmount;
    return this;
  }

  /**
   * Travel and waiting costs amount
   * @return travelWaitingCostsAmount
   */
  @Valid 
  @Schema(name = "travelWaitingCostsAmount", description = "Travel and waiting costs amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("travelWaitingCostsAmount")
  public @Nullable BigDecimal getTravelWaitingCostsAmount() {
    return travelWaitingCostsAmount;
  }

  public void setTravelWaitingCostsAmount(@Nullable BigDecimal travelWaitingCostsAmount) {
    this.travelWaitingCostsAmount = travelWaitingCostsAmount;
  }

  public Claim netWaitingCostsAmount(@Nullable BigDecimal netWaitingCostsAmount) {
    this.netWaitingCostsAmount = netWaitingCostsAmount;
    return this;
  }

  /**
   * Net waiting costs amount
   * @return netWaitingCostsAmount
   */
  @Valid 
  @Schema(name = "netWaitingCostsAmount", description = "Net waiting costs amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("netWaitingCostsAmount")
  public @Nullable BigDecimal getNetWaitingCostsAmount() {
    return netWaitingCostsAmount;
  }

  public void setNetWaitingCostsAmount(@Nullable BigDecimal netWaitingCostsAmount) {
    this.netWaitingCostsAmount = netWaitingCostsAmount;
  }

  public Claim isVatApplicable(@Nullable Boolean isVatApplicable) {
    this.isVatApplicable = isVatApplicable;
    return this;
  }

  /**
   * Whether VAT is applicable
   * @return isVatApplicable
   */
  
  @Schema(name = "isVatApplicable", description = "Whether VAT is applicable", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isVatApplicable")
  public @Nullable Boolean getIsVatApplicable() {
    return isVatApplicable;
  }

  public void setIsVatApplicable(@Nullable Boolean isVatApplicable) {
    this.isVatApplicable = isVatApplicable;
  }

  public Claim isToleranceApplicable(@Nullable Boolean isToleranceApplicable) {
    this.isToleranceApplicable = isToleranceApplicable;
    return this;
  }

  /**
   * Whether tolerance is applicable
   * @return isToleranceApplicable
   */
  
  @Schema(name = "isToleranceApplicable", description = "Whether tolerance is applicable", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isToleranceApplicable")
  public @Nullable Boolean getIsToleranceApplicable() {
    return isToleranceApplicable;
  }

  public void setIsToleranceApplicable(@Nullable Boolean isToleranceApplicable) {
    this.isToleranceApplicable = isToleranceApplicable;
  }

  public Claim priorAuthorityReference(@Nullable String priorAuthorityReference) {
    this.priorAuthorityReference = priorAuthorityReference;
    return this;
  }

  /**
   * Prior authority reference
   * @return priorAuthorityReference
   */
  
  @Schema(name = "priorAuthorityReference", description = "Prior authority reference", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("priorAuthorityReference")
  public @Nullable String getPriorAuthorityReference() {
    return priorAuthorityReference;
  }

  public void setPriorAuthorityReference(@Nullable String priorAuthorityReference) {
    this.priorAuthorityReference = priorAuthorityReference;
  }

  public Claim isLondonRate(@Nullable Boolean isLondonRate) {
    this.isLondonRate = isLondonRate;
    return this;
  }

  /**
   * Whether London rate applies
   * @return isLondonRate
   */
  
  @Schema(name = "isLondonRate", description = "Whether London rate applies", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isLondonRate")
  public @Nullable Boolean getIsLondonRate() {
    return isLondonRate;
  }

  public void setIsLondonRate(@Nullable Boolean isLondonRate) {
    this.isLondonRate = isLondonRate;
  }

  public Claim adjournedHearingFeeAmount(@Nullable Integer adjournedHearingFeeAmount) {
    this.adjournedHearingFeeAmount = adjournedHearingFeeAmount;
    return this;
  }

  /**
   * Number of times the hearing was adjourned
   * @return adjournedHearingFeeAmount
   */
  
  @Schema(name = "adjournedHearingFeeAmount", description = "Number of times the hearing was adjourned", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adjournedHearingFeeAmount")
  public @Nullable Integer getAdjournedHearingFeeAmount() {
    return adjournedHearingFeeAmount;
  }

  public void setAdjournedHearingFeeAmount(@Nullable Integer adjournedHearingFeeAmount) {
    this.adjournedHearingFeeAmount = adjournedHearingFeeAmount;
  }

  public Claim isAdditionalTravelPayment(@Nullable Boolean isAdditionalTravelPayment) {
    this.isAdditionalTravelPayment = isAdditionalTravelPayment;
    return this;
  }

  /**
   * Whether additional travel payment applies
   * @return isAdditionalTravelPayment
   */
  
  @Schema(name = "isAdditionalTravelPayment", description = "Whether additional travel payment applies", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isAdditionalTravelPayment")
  public @Nullable Boolean getIsAdditionalTravelPayment() {
    return isAdditionalTravelPayment;
  }

  public void setIsAdditionalTravelPayment(@Nullable Boolean isAdditionalTravelPayment) {
    this.isAdditionalTravelPayment = isAdditionalTravelPayment;
  }

  public Claim costsDamagesRecoveredAmount(@Nullable BigDecimal costsDamagesRecoveredAmount) {
    this.costsDamagesRecoveredAmount = costsDamagesRecoveredAmount;
    return this;
  }

  /**
   * Costs/damages recovered amount
   * @return costsDamagesRecoveredAmount
   */
  @Valid 
  @Schema(name = "costsDamagesRecoveredAmount", description = "Costs/damages recovered amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("costsDamagesRecoveredAmount")
  public @Nullable BigDecimal getCostsDamagesRecoveredAmount() {
    return costsDamagesRecoveredAmount;
  }

  public void setCostsDamagesRecoveredAmount(@Nullable BigDecimal costsDamagesRecoveredAmount) {
    this.costsDamagesRecoveredAmount = costsDamagesRecoveredAmount;
  }

  public Claim meetingsAttendedCode(@Nullable String meetingsAttendedCode) {
    this.meetingsAttendedCode = meetingsAttendedCode;
    return this;
  }

  /**
   * Meetings attended code
   * @return meetingsAttendedCode
   */
  
  @Schema(name = "meetingsAttendedCode", description = "Meetings attended code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("meetingsAttendedCode")
  public @Nullable String getMeetingsAttendedCode() {
    return meetingsAttendedCode;
  }

  public void setMeetingsAttendedCode(@Nullable String meetingsAttendedCode) {
    this.meetingsAttendedCode = meetingsAttendedCode;
  }

  public Claim detentionTravelWaitingCostsAmount(@Nullable BigDecimal detentionTravelWaitingCostsAmount) {
    this.detentionTravelWaitingCostsAmount = detentionTravelWaitingCostsAmount;
    return this;
  }

  /**
   * Detention travel and waiting costs amount
   * @return detentionTravelWaitingCostsAmount
   */
  @Valid 
  @Schema(name = "detentionTravelWaitingCostsAmount", description = "Detention travel and waiting costs amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("detentionTravelWaitingCostsAmount")
  public @Nullable BigDecimal getDetentionTravelWaitingCostsAmount() {
    return detentionTravelWaitingCostsAmount;
  }

  public void setDetentionTravelWaitingCostsAmount(@Nullable BigDecimal detentionTravelWaitingCostsAmount) {
    this.detentionTravelWaitingCostsAmount = detentionTravelWaitingCostsAmount;
  }

  public Claim jrFormFillingAmount(@Nullable BigDecimal jrFormFillingAmount) {
    this.jrFormFillingAmount = jrFormFillingAmount;
    return this;
  }

  /**
   * JR form filling amount
   * @return jrFormFillingAmount
   */
  @Valid 
  @Schema(name = "jrFormFillingAmount", description = "JR form filling amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("jrFormFillingAmount")
  public @Nullable BigDecimal getJrFormFillingAmount() {
    return jrFormFillingAmount;
  }

  public void setJrFormFillingAmount(@Nullable BigDecimal jrFormFillingAmount) {
    this.jrFormFillingAmount = jrFormFillingAmount;
  }

  public Claim isEligibleClient(@Nullable Boolean isEligibleClient) {
    this.isEligibleClient = isEligibleClient;
    return this;
  }

  /**
   * Whether client is eligible
   * @return isEligibleClient
   */
  
  @Schema(name = "isEligibleClient", description = "Whether client is eligible", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isEligibleClient")
  public @Nullable Boolean getIsEligibleClient() {
    return isEligibleClient;
  }

  public void setIsEligibleClient(@Nullable Boolean isEligibleClient) {
    this.isEligibleClient = isEligibleClient;
  }

  public Claim courtLocationCode(@Nullable String courtLocationCode) {
    this.courtLocationCode = courtLocationCode;
    return this;
  }

  /**
   * Court location code
   * @return courtLocationCode
   */
  
  @Schema(name = "courtLocationCode", description = "Court location code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("courtLocationCode")
  public @Nullable String getCourtLocationCode() {
    return courtLocationCode;
  }

  public void setCourtLocationCode(@Nullable String courtLocationCode) {
    this.courtLocationCode = courtLocationCode;
  }

  public Claim adviceTypeCode(@Nullable String adviceTypeCode) {
    this.adviceTypeCode = adviceTypeCode;
    return this;
  }

  /**
   * Advice type code
   * @return adviceTypeCode
   */
  
  @Schema(name = "adviceTypeCode", description = "Advice type code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adviceTypeCode")
  public @Nullable String getAdviceTypeCode() {
    return adviceTypeCode;
  }

  public void setAdviceTypeCode(@Nullable String adviceTypeCode) {
    this.adviceTypeCode = adviceTypeCode;
  }

  public Claim medicalReportsCount(@Nullable Integer medicalReportsCount) {
    this.medicalReportsCount = medicalReportsCount;
    return this;
  }

  /**
   * Number of medical reports
   * @return medicalReportsCount
   */
  
  @Schema(name = "medicalReportsCount", description = "Number of medical reports", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("medicalReportsCount")
  public @Nullable Integer getMedicalReportsCount() {
    return medicalReportsCount;
  }

  public void setMedicalReportsCount(@Nullable Integer medicalReportsCount) {
    this.medicalReportsCount = medicalReportsCount;
  }

  public Claim isIrcSurgery(@Nullable Boolean isIrcSurgery) {
    this.isIrcSurgery = isIrcSurgery;
    return this;
  }

  /**
   * Whether IRC surgery applies
   * @return isIrcSurgery
   */
  
  @Schema(name = "isIrcSurgery", description = "Whether IRC surgery applies", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isIrcSurgery")
  public @Nullable Boolean getIsIrcSurgery() {
    return isIrcSurgery;
  }

  public void setIsIrcSurgery(@Nullable Boolean isIrcSurgery) {
    this.isIrcSurgery = isIrcSurgery;
  }

  public Claim surgeryDate(@Nullable String surgeryDate) {
    this.surgeryDate = surgeryDate;
    return this;
  }

  /**
   * Surgery date (format YYYY-MM-DD)
   * @return surgeryDate
   */
  
  @Schema(name = "surgeryDate", description = "Surgery date (format YYYY-MM-DD)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("surgeryDate")
  public @Nullable String getSurgeryDate() {
    return surgeryDate;
  }

  public void setSurgeryDate(@Nullable String surgeryDate) {
    this.surgeryDate = surgeryDate;
  }

  public Claim surgeryClientsCount(@Nullable Integer surgeryClientsCount) {
    this.surgeryClientsCount = surgeryClientsCount;
    return this;
  }

  /**
   * Number of surgery clients
   * @return surgeryClientsCount
   */
  
  @Schema(name = "surgeryClientsCount", description = "Number of surgery clients", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("surgeryClientsCount")
  public @Nullable Integer getSurgeryClientsCount() {
    return surgeryClientsCount;
  }

  public void setSurgeryClientsCount(@Nullable Integer surgeryClientsCount) {
    this.surgeryClientsCount = surgeryClientsCount;
  }

  public Claim surgeryMattersCount(@Nullable Integer surgeryMattersCount) {
    this.surgeryMattersCount = surgeryMattersCount;
    return this;
  }

  /**
   * Number of surgery matters
   * @return surgeryMattersCount
   */
  
  @Schema(name = "surgeryMattersCount", description = "Number of surgery matters", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("surgeryMattersCount")
  public @Nullable Integer getSurgeryMattersCount() {
    return surgeryMattersCount;
  }

  public void setSurgeryMattersCount(@Nullable Integer surgeryMattersCount) {
    this.surgeryMattersCount = surgeryMattersCount;
  }

  public Claim cmrhOralCount(@Nullable Integer cmrhOralCount) {
    this.cmrhOralCount = cmrhOralCount;
    return this;
  }

  /**
   * CMRH oral count
   * @return cmrhOralCount
   */
  
  @Schema(name = "cmrhOralCount", description = "CMRH oral count", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cmrhOralCount")
  public @Nullable Integer getCmrhOralCount() {
    return cmrhOralCount;
  }

  public void setCmrhOralCount(@Nullable Integer cmrhOralCount) {
    this.cmrhOralCount = cmrhOralCount;
  }

  public Claim cmrhTelephoneCount(@Nullable Integer cmrhTelephoneCount) {
    this.cmrhTelephoneCount = cmrhTelephoneCount;
    return this;
  }

  /**
   * CMRH telephone count
   * @return cmrhTelephoneCount
   */
  
  @Schema(name = "cmrhTelephoneCount", description = "CMRH telephone count", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cmrhTelephoneCount")
  public @Nullable Integer getCmrhTelephoneCount() {
    return cmrhTelephoneCount;
  }

  public void setCmrhTelephoneCount(@Nullable Integer cmrhTelephoneCount) {
    this.cmrhTelephoneCount = cmrhTelephoneCount;
  }

  public Claim aitHearingCentreCode(@Nullable String aitHearingCentreCode) {
    this.aitHearingCentreCode = aitHearingCentreCode;
    return this;
  }

  /**
   * AIT hearing centre code
   * @return aitHearingCentreCode
   */
  
  @Schema(name = "aitHearingCentreCode", description = "AIT hearing centre code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("aitHearingCentreCode")
  public @Nullable String getAitHearingCentreCode() {
    return aitHearingCentreCode;
  }

  public void setAitHearingCentreCode(@Nullable String aitHearingCentreCode) {
    this.aitHearingCentreCode = aitHearingCentreCode;
  }

  public Claim isSubstantiveHearing(@Nullable Boolean isSubstantiveHearing) {
    this.isSubstantiveHearing = isSubstantiveHearing;
    return this;
  }

  /**
   * Whether this is a substantive hearing
   * @return isSubstantiveHearing
   */
  
  @Schema(name = "isSubstantiveHearing", description = "Whether this is a substantive hearing", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isSubstantiveHearing")
  public @Nullable Boolean getIsSubstantiveHearing() {
    return isSubstantiveHearing;
  }

  public void setIsSubstantiveHearing(@Nullable Boolean isSubstantiveHearing) {
    this.isSubstantiveHearing = isSubstantiveHearing;
  }

  public Claim hoInterview(@Nullable Integer hoInterview) {
    this.hoInterview = hoInterview;
    return this;
  }

  /**
   * Number of Home Office interviews
   * @return hoInterview
   */
  
  @Schema(name = "hoInterview", description = "Number of Home Office interviews", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("hoInterview")
  public @Nullable Integer getHoInterview() {
    return hoInterview;
  }

  public void setHoInterview(@Nullable Integer hoInterview) {
    this.hoInterview = hoInterview;
  }

  public Claim localAuthorityNumber(@Nullable String localAuthorityNumber) {
    this.localAuthorityNumber = localAuthorityNumber;
    return this;
  }

  /**
   * Local authority number
   * @return localAuthorityNumber
   */
  
  @Schema(name = "localAuthorityNumber", description = "Local authority number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("localAuthorityNumber")
  public @Nullable String getLocalAuthorityNumber() {
    return localAuthorityNumber;
  }

  public void setLocalAuthorityNumber(@Nullable String localAuthorityNumber) {
    this.localAuthorityNumber = localAuthorityNumber;
  }

  public Claim createdByUserId(@Nullable String createdByUserId) {
    this.createdByUserId = createdByUserId;
    return this;
  }

  /**
   * ID of the user who created the claim
   * @return createdByUserId
   */
  
  @Schema(name = "createdByUserId", description = "ID of the user who created the claim", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("createdByUserId")
  public @Nullable String getCreatedByUserId() {
    return createdByUserId;
  }

  public void setCreatedByUserId(@Nullable String createdByUserId) {
    this.createdByUserId = createdByUserId;
  }

  public Claim isAmended(@Nullable Boolean isAmended) {
    this.isAmended = isAmended;
    return this;
  }

  /**
   * Whether the claim has been amended
   * @return isAmended
   */
  
  @Schema(name = "isAmended", description = "Whether the claim has been amended", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("isAmended")
  public @Nullable Boolean getIsAmended() {
    return isAmended;
  }

  public void setIsAmended(@Nullable Boolean isAmended) {
    this.isAmended = isAmended;
  }

  public Claim hasAssessment(@Nullable Boolean hasAssessment) {
    this.hasAssessment = hasAssessment;
    return this;
  }

  /**
   * Whether the claim has an assessment
   * @return hasAssessment
   */
  
  @Schema(name = "hasAssessment", description = "Whether the claim has an assessment", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("hasAssessment")
  public @Nullable Boolean getHasAssessment() {
    return hasAssessment;
  }

  public void setHasAssessment(@Nullable Boolean hasAssessment) {
    this.hasAssessment = hasAssessment;
  }

  public Claim version(@Nullable Integer version) {
    this.version = version;
    return this;
  }

  /**
   * Version number for optimistic locking
   * @return version
   */
  
  @Schema(name = "version", description = "Version number for optimistic locking", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("version")
  public @Nullable Integer getVersion() {
    return version;
  }

  public void setVersion(@Nullable Integer version) {
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
    Claim claim = (Claim) o;
    return Objects.equals(this.areaOfLaw, claim.areaOfLaw) &&
        Objects.equals(this.officeAccountNumber, claim.officeAccountNumber) &&
        Objects.equals(this.id, claim.id) &&
        Objects.equals(this.submissionId, claim.submissionId) &&
        Objects.equals(this.status, claim.status) &&
        Objects.equals(this.lineNumber, claim.lineNumber) &&
        Objects.equals(this.scheduleReference, claim.scheduleReference) &&
        Objects.equals(this.submissionPeriod, claim.submissionPeriod) &&
        Objects.equals(this.caseReferenceNumber, claim.caseReferenceNumber) &&
        Objects.equals(this.uniqueFileNumber, claim.uniqueFileNumber) &&
        Objects.equals(this.caseStartDate, claim.caseStartDate) &&
        Objects.equals(this.caseConcludedDate, claim.caseConcludedDate) &&
        Objects.equals(this.caseId, claim.caseId) &&
        Objects.equals(this.uniqueCaseId, claim.uniqueCaseId) &&
        Objects.equals(this.caseStageCode, claim.caseStageCode) &&
        Objects.equals(this.matterTypeCode, claim.matterTypeCode) &&
        Objects.equals(this.crimeMatterTypeCode, claim.crimeMatterTypeCode) &&
        Objects.equals(this.feeSchemeCode, claim.feeSchemeCode) &&
        Objects.equals(this.feeCode, claim.feeCode) &&
        Objects.equals(this.procurementAreaCode, claim.procurementAreaCode) &&
        Objects.equals(this.accessPointCode, claim.accessPointCode) &&
        Objects.equals(this.deliveryLocation, claim.deliveryLocation) &&
        Objects.equals(this.representationOrderDate, claim.representationOrderDate) &&
        Objects.equals(this.suspectsDefendantsCount, claim.suspectsDefendantsCount) &&
        Objects.equals(this.policeStationCourtAttendancesCount, claim.policeStationCourtAttendancesCount) &&
        Objects.equals(this.policeStationCourtPrisonId, claim.policeStationCourtPrisonId) &&
        Objects.equals(this.dsccNumber, claim.dsccNumber) &&
        Objects.equals(this.maatId, claim.maatId) &&
        Objects.equals(this.prisonLawPriorApprovalNumber, claim.prisonLawPriorApprovalNumber) &&
        Objects.equals(this.isDutySolicitor, claim.isDutySolicitor) &&
        Objects.equals(this.isYouthCourt, claim.isYouthCourt) &&
        Objects.equals(this.schemeId, claim.schemeId) &&
        Objects.equals(this.mediationSessionsCount, claim.mediationSessionsCount) &&
        Objects.equals(this.mediationTimeMinutes, claim.mediationTimeMinutes) &&
        Objects.equals(this.outreachLocation, claim.outreachLocation) &&
        Objects.equals(this.referralSource, claim.referralSource) &&
        Objects.equals(this.clientForename, claim.clientForename) &&
        Objects.equals(this.clientSurname, claim.clientSurname) &&
        Objects.equals(this.clientDateOfBirth, claim.clientDateOfBirth) &&
        Objects.equals(this.uniqueClientNumber, claim.uniqueClientNumber) &&
        Objects.equals(this.clientPostcode, claim.clientPostcode) &&
        Objects.equals(this.genderCode, claim.genderCode) &&
        Objects.equals(this.ethnicityCode, claim.ethnicityCode) &&
        Objects.equals(this.disabilityCode, claim.disabilityCode) &&
        Objects.equals(this.isLegallyAided, claim.isLegallyAided) &&
        Objects.equals(this.clientTypeCode, claim.clientTypeCode) &&
        Objects.equals(this.homeOfficeClientNumber, claim.homeOfficeClientNumber) &&
        Objects.equals(this.claReferenceNumber, claim.claReferenceNumber) &&
        Objects.equals(this.claExemptionCode, claim.claExemptionCode) &&
        Objects.equals(this.client2Forename, claim.client2Forename) &&
        Objects.equals(this.client2Surname, claim.client2Surname) &&
        Objects.equals(this.client2DateOfBirth, claim.client2DateOfBirth) &&
        Objects.equals(this.client2Ucn, claim.client2Ucn) &&
        Objects.equals(this.client2Postcode, claim.client2Postcode) &&
        Objects.equals(this.client2GenderCode, claim.client2GenderCode) &&
        Objects.equals(this.client2EthnicityCode, claim.client2EthnicityCode) &&
        Objects.equals(this.client2DisabilityCode, claim.client2DisabilityCode) &&
        Objects.equals(this.client2IsLegallyAided, claim.client2IsLegallyAided) &&
        Objects.equals(this.stageReachedCode, claim.stageReachedCode) &&
        Objects.equals(this.standardFeeCategoryCode, claim.standardFeeCategoryCode) &&
        Objects.equals(this.outcomeCode, claim.outcomeCode) &&
        Objects.equals(this.designatedAccreditedRepresentativeCode, claim.designatedAccreditedRepresentativeCode) &&
        Objects.equals(this.isPostalApplicationAccepted, claim.isPostalApplicationAccepted) &&
        Objects.equals(this.isClient2PostalApplicationAccepted, claim.isClient2PostalApplicationAccepted) &&
        Objects.equals(this.mentalHealthTribunalReference, claim.mentalHealthTribunalReference) &&
        Objects.equals(this.isNrmAdvice, claim.isNrmAdvice) &&
        Objects.equals(this.followOnWork, claim.followOnWork) &&
        Objects.equals(this.transferDate, claim.transferDate) &&
        Objects.equals(this.exemptionCriteriaSatisfied, claim.exemptionCriteriaSatisfied) &&
        Objects.equals(this.exceptionalCaseFundingReference, claim.exceptionalCaseFundingReference) &&
        Objects.equals(this.isLegacyCase, claim.isLegacyCase) &&
        Objects.equals(this.adviceTime, claim.adviceTime) &&
        Objects.equals(this.travelTime, claim.travelTime) &&
        Objects.equals(this.waitingTime, claim.waitingTime) &&
        Objects.equals(this.netProfitCostsAmount, claim.netProfitCostsAmount) &&
        Objects.equals(this.netDisbursementAmount, claim.netDisbursementAmount) &&
        Objects.equals(this.netCounselCostsAmount, claim.netCounselCostsAmount) &&
        Objects.equals(this.disbursementsVatAmount, claim.disbursementsVatAmount) &&
        Objects.equals(this.travelWaitingCostsAmount, claim.travelWaitingCostsAmount) &&
        Objects.equals(this.netWaitingCostsAmount, claim.netWaitingCostsAmount) &&
        Objects.equals(this.isVatApplicable, claim.isVatApplicable) &&
        Objects.equals(this.isToleranceApplicable, claim.isToleranceApplicable) &&
        Objects.equals(this.priorAuthorityReference, claim.priorAuthorityReference) &&
        Objects.equals(this.isLondonRate, claim.isLondonRate) &&
        Objects.equals(this.adjournedHearingFeeAmount, claim.adjournedHearingFeeAmount) &&
        Objects.equals(this.isAdditionalTravelPayment, claim.isAdditionalTravelPayment) &&
        Objects.equals(this.costsDamagesRecoveredAmount, claim.costsDamagesRecoveredAmount) &&
        Objects.equals(this.meetingsAttendedCode, claim.meetingsAttendedCode) &&
        Objects.equals(this.detentionTravelWaitingCostsAmount, claim.detentionTravelWaitingCostsAmount) &&
        Objects.equals(this.jrFormFillingAmount, claim.jrFormFillingAmount) &&
        Objects.equals(this.isEligibleClient, claim.isEligibleClient) &&
        Objects.equals(this.courtLocationCode, claim.courtLocationCode) &&
        Objects.equals(this.adviceTypeCode, claim.adviceTypeCode) &&
        Objects.equals(this.medicalReportsCount, claim.medicalReportsCount) &&
        Objects.equals(this.isIrcSurgery, claim.isIrcSurgery) &&
        Objects.equals(this.surgeryDate, claim.surgeryDate) &&
        Objects.equals(this.surgeryClientsCount, claim.surgeryClientsCount) &&
        Objects.equals(this.surgeryMattersCount, claim.surgeryMattersCount) &&
        Objects.equals(this.cmrhOralCount, claim.cmrhOralCount) &&
        Objects.equals(this.cmrhTelephoneCount, claim.cmrhTelephoneCount) &&
        Objects.equals(this.aitHearingCentreCode, claim.aitHearingCentreCode) &&
        Objects.equals(this.isSubstantiveHearing, claim.isSubstantiveHearing) &&
        Objects.equals(this.hoInterview, claim.hoInterview) &&
        Objects.equals(this.localAuthorityNumber, claim.localAuthorityNumber) &&
        Objects.equals(this.createdByUserId, claim.createdByUserId) &&
        Objects.equals(this.isAmended, claim.isAmended) &&
        Objects.equals(this.hasAssessment, claim.hasAssessment) &&
        Objects.equals(this.version, claim.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(areaOfLaw, officeAccountNumber, id, submissionId, status, lineNumber, scheduleReference, submissionPeriod, caseReferenceNumber, uniqueFileNumber, caseStartDate, caseConcludedDate, caseId, uniqueCaseId, caseStageCode, matterTypeCode, crimeMatterTypeCode, feeSchemeCode, feeCode, procurementAreaCode, accessPointCode, deliveryLocation, representationOrderDate, suspectsDefendantsCount, policeStationCourtAttendancesCount, policeStationCourtPrisonId, dsccNumber, maatId, prisonLawPriorApprovalNumber, isDutySolicitor, isYouthCourt, schemeId, mediationSessionsCount, mediationTimeMinutes, outreachLocation, referralSource, clientForename, clientSurname, clientDateOfBirth, uniqueClientNumber, clientPostcode, genderCode, ethnicityCode, disabilityCode, isLegallyAided, clientTypeCode, homeOfficeClientNumber, claReferenceNumber, claExemptionCode, client2Forename, client2Surname, client2DateOfBirth, client2Ucn, client2Postcode, client2GenderCode, client2EthnicityCode, client2DisabilityCode, client2IsLegallyAided, stageReachedCode, standardFeeCategoryCode, outcomeCode, designatedAccreditedRepresentativeCode, isPostalApplicationAccepted, isClient2PostalApplicationAccepted, mentalHealthTribunalReference, isNrmAdvice, followOnWork, transferDate, exemptionCriteriaSatisfied, exceptionalCaseFundingReference, isLegacyCase, adviceTime, travelTime, waitingTime, netProfitCostsAmount, netDisbursementAmount, netCounselCostsAmount, disbursementsVatAmount, travelWaitingCostsAmount, netWaitingCostsAmount, isVatApplicable, isToleranceApplicable, priorAuthorityReference, isLondonRate, adjournedHearingFeeAmount, isAdditionalTravelPayment, costsDamagesRecoveredAmount, meetingsAttendedCode, detentionTravelWaitingCostsAmount, jrFormFillingAmount, isEligibleClient, courtLocationCode, adviceTypeCode, medicalReportsCount, isIrcSurgery, surgeryDate, surgeryClientsCount, surgeryMattersCount, cmrhOralCount, cmrhTelephoneCount, aitHearingCentreCode, isSubstantiveHearing, hoInterview, localAuthorityNumber, createdByUserId, isAmended, hasAssessment, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Claim {\n");
    sb.append("    areaOfLaw: ").append(toIndentedString(areaOfLaw)).append("\n");
    sb.append("    officeAccountNumber: ").append(toIndentedString(officeAccountNumber)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    submissionId: ").append(toIndentedString(submissionId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    lineNumber: ").append(toIndentedString(lineNumber)).append("\n");
    sb.append("    scheduleReference: ").append(toIndentedString(scheduleReference)).append("\n");
    sb.append("    submissionPeriod: ").append(toIndentedString(submissionPeriod)).append("\n");
    sb.append("    caseReferenceNumber: ").append(toIndentedString(caseReferenceNumber)).append("\n");
    sb.append("    uniqueFileNumber: ").append(toIndentedString(uniqueFileNumber)).append("\n");
    sb.append("    caseStartDate: ").append(toIndentedString(caseStartDate)).append("\n");
    sb.append("    caseConcludedDate: ").append(toIndentedString(caseConcludedDate)).append("\n");
    sb.append("    caseId: ").append(toIndentedString(caseId)).append("\n");
    sb.append("    uniqueCaseId: ").append(toIndentedString(uniqueCaseId)).append("\n");
    sb.append("    caseStageCode: ").append(toIndentedString(caseStageCode)).append("\n");
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

    private Claim instance;

    public Builder() {
      this(new Claim());
    }

    protected Builder(Claim instance) {
      this.instance = instance;
    }

    protected Builder copyOf(Claim value) { 
      this.instance.setAreaOfLaw(value.areaOfLaw);
      this.instance.setOfficeAccountNumber(value.officeAccountNumber);
      this.instance.setId(value.id);
      this.instance.setSubmissionId(value.submissionId);
      this.instance.setStatus(value.status);
      this.instance.setLineNumber(value.lineNumber);
      this.instance.setScheduleReference(value.scheduleReference);
      this.instance.setSubmissionPeriod(value.submissionPeriod);
      this.instance.setCaseReferenceNumber(value.caseReferenceNumber);
      this.instance.setUniqueFileNumber(value.uniqueFileNumber);
      this.instance.setCaseStartDate(value.caseStartDate);
      this.instance.setCaseConcludedDate(value.caseConcludedDate);
      this.instance.setCaseId(value.caseId);
      this.instance.setUniqueCaseId(value.uniqueCaseId);
      this.instance.setCaseStageCode(value.caseStageCode);
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
      this.instance.setCreatedByUserId(value.createdByUserId);
      this.instance.setIsAmended(value.isAmended);
      this.instance.setHasAssessment(value.hasAssessment);
      this.instance.setVersion(value.version);
      return this;
    }

    public Claim.Builder areaOfLaw(AreaOfLaw areaOfLaw) {
      this.instance.areaOfLaw(areaOfLaw);
      return this;
    }
    
    public Claim.Builder officeAccountNumber(String officeAccountNumber) {
      this.instance.officeAccountNumber(officeAccountNumber);
      return this;
    }
    
    public Claim.Builder id(UUID id) {
      this.instance.id(id);
      return this;
    }
    
    public Claim.Builder submissionId(UUID submissionId) {
      this.instance.submissionId(submissionId);
      return this;
    }
    
    public Claim.Builder status(ClaimStatus status) {
      this.instance.status(status);
      return this;
    }
    
    public Claim.Builder lineNumber(Integer lineNumber) {
      this.instance.lineNumber(lineNumber);
      return this;
    }
    
    public Claim.Builder scheduleReference(String scheduleReference) {
      this.instance.scheduleReference(scheduleReference);
      return this;
    }
    
    public Claim.Builder submissionPeriod(String submissionPeriod) {
      this.instance.submissionPeriod(submissionPeriod);
      return this;
    }
    
    public Claim.Builder caseReferenceNumber(String caseReferenceNumber) {
      this.instance.caseReferenceNumber(caseReferenceNumber);
      return this;
    }
    
    public Claim.Builder uniqueFileNumber(String uniqueFileNumber) {
      this.instance.uniqueFileNumber(uniqueFileNumber);
      return this;
    }
    
    public Claim.Builder caseStartDate(String caseStartDate) {
      this.instance.caseStartDate(caseStartDate);
      return this;
    }
    
    public Claim.Builder caseConcludedDate(String caseConcludedDate) {
      this.instance.caseConcludedDate(caseConcludedDate);
      return this;
    }
    
    public Claim.Builder caseId(String caseId) {
      this.instance.caseId(caseId);
      return this;
    }
    
    public Claim.Builder uniqueCaseId(String uniqueCaseId) {
      this.instance.uniqueCaseId(uniqueCaseId);
      return this;
    }
    
    public Claim.Builder caseStageCode(String caseStageCode) {
      this.instance.caseStageCode(caseStageCode);
      return this;
    }
    
    public Claim.Builder matterTypeCode(String matterTypeCode) {
      this.instance.matterTypeCode(matterTypeCode);
      return this;
    }
    
    public Claim.Builder crimeMatterTypeCode(String crimeMatterTypeCode) {
      this.instance.crimeMatterTypeCode(crimeMatterTypeCode);
      return this;
    }
    
    public Claim.Builder feeSchemeCode(String feeSchemeCode) {
      this.instance.feeSchemeCode(feeSchemeCode);
      return this;
    }
    
    public Claim.Builder feeCode(String feeCode) {
      this.instance.feeCode(feeCode);
      return this;
    }
    
    public Claim.Builder procurementAreaCode(String procurementAreaCode) {
      this.instance.procurementAreaCode(procurementAreaCode);
      return this;
    }
    
    public Claim.Builder accessPointCode(String accessPointCode) {
      this.instance.accessPointCode(accessPointCode);
      return this;
    }
    
    public Claim.Builder deliveryLocation(String deliveryLocation) {
      this.instance.deliveryLocation(deliveryLocation);
      return this;
    }
    
    public Claim.Builder representationOrderDate(String representationOrderDate) {
      this.instance.representationOrderDate(representationOrderDate);
      return this;
    }
    
    public Claim.Builder suspectsDefendantsCount(Integer suspectsDefendantsCount) {
      this.instance.suspectsDefendantsCount(suspectsDefendantsCount);
      return this;
    }
    
    public Claim.Builder policeStationCourtAttendancesCount(Integer policeStationCourtAttendancesCount) {
      this.instance.policeStationCourtAttendancesCount(policeStationCourtAttendancesCount);
      return this;
    }
    
    public Claim.Builder policeStationCourtPrisonId(String policeStationCourtPrisonId) {
      this.instance.policeStationCourtPrisonId(policeStationCourtPrisonId);
      return this;
    }
    
    public Claim.Builder dsccNumber(String dsccNumber) {
      this.instance.dsccNumber(dsccNumber);
      return this;
    }
    
    public Claim.Builder maatId(String maatId) {
      this.instance.maatId(maatId);
      return this;
    }
    
    public Claim.Builder prisonLawPriorApprovalNumber(String prisonLawPriorApprovalNumber) {
      this.instance.prisonLawPriorApprovalNumber(prisonLawPriorApprovalNumber);
      return this;
    }
    
    public Claim.Builder isDutySolicitor(Boolean isDutySolicitor) {
      this.instance.isDutySolicitor(isDutySolicitor);
      return this;
    }
    
    public Claim.Builder isYouthCourt(Boolean isYouthCourt) {
      this.instance.isYouthCourt(isYouthCourt);
      return this;
    }
    
    public Claim.Builder schemeId(String schemeId) {
      this.instance.schemeId(schemeId);
      return this;
    }
    
    public Claim.Builder mediationSessionsCount(Integer mediationSessionsCount) {
      this.instance.mediationSessionsCount(mediationSessionsCount);
      return this;
    }
    
    public Claim.Builder mediationTimeMinutes(Integer mediationTimeMinutes) {
      this.instance.mediationTimeMinutes(mediationTimeMinutes);
      return this;
    }
    
    public Claim.Builder outreachLocation(String outreachLocation) {
      this.instance.outreachLocation(outreachLocation);
      return this;
    }
    
    public Claim.Builder referralSource(String referralSource) {
      this.instance.referralSource(referralSource);
      return this;
    }
    
    public Claim.Builder clientForename(String clientForename) {
      this.instance.clientForename(clientForename);
      return this;
    }
    
    public Claim.Builder clientSurname(String clientSurname) {
      this.instance.clientSurname(clientSurname);
      return this;
    }
    
    public Claim.Builder clientDateOfBirth(String clientDateOfBirth) {
      this.instance.clientDateOfBirth(clientDateOfBirth);
      return this;
    }
    
    public Claim.Builder uniqueClientNumber(String uniqueClientNumber) {
      this.instance.uniqueClientNumber(uniqueClientNumber);
      return this;
    }
    
    public Claim.Builder clientPostcode(String clientPostcode) {
      this.instance.clientPostcode(clientPostcode);
      return this;
    }
    
    public Claim.Builder genderCode(String genderCode) {
      this.instance.genderCode(genderCode);
      return this;
    }
    
    public Claim.Builder ethnicityCode(String ethnicityCode) {
      this.instance.ethnicityCode(ethnicityCode);
      return this;
    }
    
    public Claim.Builder disabilityCode(String disabilityCode) {
      this.instance.disabilityCode(disabilityCode);
      return this;
    }
    
    public Claim.Builder isLegallyAided(Boolean isLegallyAided) {
      this.instance.isLegallyAided(isLegallyAided);
      return this;
    }
    
    public Claim.Builder clientTypeCode(String clientTypeCode) {
      this.instance.clientTypeCode(clientTypeCode);
      return this;
    }
    
    public Claim.Builder homeOfficeClientNumber(String homeOfficeClientNumber) {
      this.instance.homeOfficeClientNumber(homeOfficeClientNumber);
      return this;
    }
    
    public Claim.Builder claReferenceNumber(String claReferenceNumber) {
      this.instance.claReferenceNumber(claReferenceNumber);
      return this;
    }
    
    public Claim.Builder claExemptionCode(String claExemptionCode) {
      this.instance.claExemptionCode(claExemptionCode);
      return this;
    }
    
    public Claim.Builder client2Forename(String client2Forename) {
      this.instance.client2Forename(client2Forename);
      return this;
    }
    
    public Claim.Builder client2Surname(String client2Surname) {
      this.instance.client2Surname(client2Surname);
      return this;
    }
    
    public Claim.Builder client2DateOfBirth(String client2DateOfBirth) {
      this.instance.client2DateOfBirth(client2DateOfBirth);
      return this;
    }
    
    public Claim.Builder client2Ucn(String client2Ucn) {
      this.instance.client2Ucn(client2Ucn);
      return this;
    }
    
    public Claim.Builder client2Postcode(String client2Postcode) {
      this.instance.client2Postcode(client2Postcode);
      return this;
    }
    
    public Claim.Builder client2GenderCode(String client2GenderCode) {
      this.instance.client2GenderCode(client2GenderCode);
      return this;
    }
    
    public Claim.Builder client2EthnicityCode(String client2EthnicityCode) {
      this.instance.client2EthnicityCode(client2EthnicityCode);
      return this;
    }
    
    public Claim.Builder client2DisabilityCode(String client2DisabilityCode) {
      this.instance.client2DisabilityCode(client2DisabilityCode);
      return this;
    }
    
    public Claim.Builder client2IsLegallyAided(Boolean client2IsLegallyAided) {
      this.instance.client2IsLegallyAided(client2IsLegallyAided);
      return this;
    }
    
    public Claim.Builder stageReachedCode(String stageReachedCode) {
      this.instance.stageReachedCode(stageReachedCode);
      return this;
    }
    
    public Claim.Builder standardFeeCategoryCode(String standardFeeCategoryCode) {
      this.instance.standardFeeCategoryCode(standardFeeCategoryCode);
      return this;
    }
    
    public Claim.Builder outcomeCode(String outcomeCode) {
      this.instance.outcomeCode(outcomeCode);
      return this;
    }
    
    public Claim.Builder designatedAccreditedRepresentativeCode(String designatedAccreditedRepresentativeCode) {
      this.instance.designatedAccreditedRepresentativeCode(designatedAccreditedRepresentativeCode);
      return this;
    }
    
    public Claim.Builder isPostalApplicationAccepted(Boolean isPostalApplicationAccepted) {
      this.instance.isPostalApplicationAccepted(isPostalApplicationAccepted);
      return this;
    }
    
    public Claim.Builder isClient2PostalApplicationAccepted(Boolean isClient2PostalApplicationAccepted) {
      this.instance.isClient2PostalApplicationAccepted(isClient2PostalApplicationAccepted);
      return this;
    }
    
    public Claim.Builder mentalHealthTribunalReference(String mentalHealthTribunalReference) {
      this.instance.mentalHealthTribunalReference(mentalHealthTribunalReference);
      return this;
    }
    
    public Claim.Builder isNrmAdvice(Boolean isNrmAdvice) {
      this.instance.isNrmAdvice(isNrmAdvice);
      return this;
    }
    
    public Claim.Builder followOnWork(String followOnWork) {
      this.instance.followOnWork(followOnWork);
      return this;
    }
    
    public Claim.Builder transferDate(String transferDate) {
      this.instance.transferDate(transferDate);
      return this;
    }
    
    public Claim.Builder exemptionCriteriaSatisfied(String exemptionCriteriaSatisfied) {
      this.instance.exemptionCriteriaSatisfied(exemptionCriteriaSatisfied);
      return this;
    }
    
    public Claim.Builder exceptionalCaseFundingReference(String exceptionalCaseFundingReference) {
      this.instance.exceptionalCaseFundingReference(exceptionalCaseFundingReference);
      return this;
    }
    
    public Claim.Builder isLegacyCase(Boolean isLegacyCase) {
      this.instance.isLegacyCase(isLegacyCase);
      return this;
    }
    
    public Claim.Builder adviceTime(Integer adviceTime) {
      this.instance.adviceTime(adviceTime);
      return this;
    }
    
    public Claim.Builder travelTime(Integer travelTime) {
      this.instance.travelTime(travelTime);
      return this;
    }
    
    public Claim.Builder waitingTime(Integer waitingTime) {
      this.instance.waitingTime(waitingTime);
      return this;
    }
    
    public Claim.Builder netProfitCostsAmount(BigDecimal netProfitCostsAmount) {
      this.instance.netProfitCostsAmount(netProfitCostsAmount);
      return this;
    }
    
    public Claim.Builder netDisbursementAmount(BigDecimal netDisbursementAmount) {
      this.instance.netDisbursementAmount(netDisbursementAmount);
      return this;
    }
    
    public Claim.Builder netCounselCostsAmount(BigDecimal netCounselCostsAmount) {
      this.instance.netCounselCostsAmount(netCounselCostsAmount);
      return this;
    }
    
    public Claim.Builder disbursementsVatAmount(BigDecimal disbursementsVatAmount) {
      this.instance.disbursementsVatAmount(disbursementsVatAmount);
      return this;
    }
    
    public Claim.Builder travelWaitingCostsAmount(BigDecimal travelWaitingCostsAmount) {
      this.instance.travelWaitingCostsAmount(travelWaitingCostsAmount);
      return this;
    }
    
    public Claim.Builder netWaitingCostsAmount(BigDecimal netWaitingCostsAmount) {
      this.instance.netWaitingCostsAmount(netWaitingCostsAmount);
      return this;
    }
    
    public Claim.Builder isVatApplicable(Boolean isVatApplicable) {
      this.instance.isVatApplicable(isVatApplicable);
      return this;
    }
    
    public Claim.Builder isToleranceApplicable(Boolean isToleranceApplicable) {
      this.instance.isToleranceApplicable(isToleranceApplicable);
      return this;
    }
    
    public Claim.Builder priorAuthorityReference(String priorAuthorityReference) {
      this.instance.priorAuthorityReference(priorAuthorityReference);
      return this;
    }
    
    public Claim.Builder isLondonRate(Boolean isLondonRate) {
      this.instance.isLondonRate(isLondonRate);
      return this;
    }
    
    public Claim.Builder adjournedHearingFeeAmount(Integer adjournedHearingFeeAmount) {
      this.instance.adjournedHearingFeeAmount(adjournedHearingFeeAmount);
      return this;
    }
    
    public Claim.Builder isAdditionalTravelPayment(Boolean isAdditionalTravelPayment) {
      this.instance.isAdditionalTravelPayment(isAdditionalTravelPayment);
      return this;
    }
    
    public Claim.Builder costsDamagesRecoveredAmount(BigDecimal costsDamagesRecoveredAmount) {
      this.instance.costsDamagesRecoveredAmount(costsDamagesRecoveredAmount);
      return this;
    }
    
    public Claim.Builder meetingsAttendedCode(String meetingsAttendedCode) {
      this.instance.meetingsAttendedCode(meetingsAttendedCode);
      return this;
    }
    
    public Claim.Builder detentionTravelWaitingCostsAmount(BigDecimal detentionTravelWaitingCostsAmount) {
      this.instance.detentionTravelWaitingCostsAmount(detentionTravelWaitingCostsAmount);
      return this;
    }
    
    public Claim.Builder jrFormFillingAmount(BigDecimal jrFormFillingAmount) {
      this.instance.jrFormFillingAmount(jrFormFillingAmount);
      return this;
    }
    
    public Claim.Builder isEligibleClient(Boolean isEligibleClient) {
      this.instance.isEligibleClient(isEligibleClient);
      return this;
    }
    
    public Claim.Builder courtLocationCode(String courtLocationCode) {
      this.instance.courtLocationCode(courtLocationCode);
      return this;
    }
    
    public Claim.Builder adviceTypeCode(String adviceTypeCode) {
      this.instance.adviceTypeCode(adviceTypeCode);
      return this;
    }
    
    public Claim.Builder medicalReportsCount(Integer medicalReportsCount) {
      this.instance.medicalReportsCount(medicalReportsCount);
      return this;
    }
    
    public Claim.Builder isIrcSurgery(Boolean isIrcSurgery) {
      this.instance.isIrcSurgery(isIrcSurgery);
      return this;
    }
    
    public Claim.Builder surgeryDate(String surgeryDate) {
      this.instance.surgeryDate(surgeryDate);
      return this;
    }
    
    public Claim.Builder surgeryClientsCount(Integer surgeryClientsCount) {
      this.instance.surgeryClientsCount(surgeryClientsCount);
      return this;
    }
    
    public Claim.Builder surgeryMattersCount(Integer surgeryMattersCount) {
      this.instance.surgeryMattersCount(surgeryMattersCount);
      return this;
    }
    
    public Claim.Builder cmrhOralCount(Integer cmrhOralCount) {
      this.instance.cmrhOralCount(cmrhOralCount);
      return this;
    }
    
    public Claim.Builder cmrhTelephoneCount(Integer cmrhTelephoneCount) {
      this.instance.cmrhTelephoneCount(cmrhTelephoneCount);
      return this;
    }
    
    public Claim.Builder aitHearingCentreCode(String aitHearingCentreCode) {
      this.instance.aitHearingCentreCode(aitHearingCentreCode);
      return this;
    }
    
    public Claim.Builder isSubstantiveHearing(Boolean isSubstantiveHearing) {
      this.instance.isSubstantiveHearing(isSubstantiveHearing);
      return this;
    }
    
    public Claim.Builder hoInterview(Integer hoInterview) {
      this.instance.hoInterview(hoInterview);
      return this;
    }
    
    public Claim.Builder localAuthorityNumber(String localAuthorityNumber) {
      this.instance.localAuthorityNumber(localAuthorityNumber);
      return this;
    }
    
    public Claim.Builder createdByUserId(String createdByUserId) {
      this.instance.createdByUserId(createdByUserId);
      return this;
    }
    
    public Claim.Builder isAmended(Boolean isAmended) {
      this.instance.isAmended(isAmended);
      return this;
    }
    
    public Claim.Builder hasAssessment(Boolean hasAssessment) {
      this.instance.hasAssessment(hasAssessment);
      return this;
    }
    
    public Claim.Builder version(Integer version) {
      this.instance.version(version);
      return this;
    }
    
    /**
    * returns a built Claim instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public Claim build() {
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
  public static Claim.Builder builder() {
    return new Claim.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public Claim.Builder toBuilder() {
    Claim.Builder builder = new Claim.Builder();
    return builder.copyOf(this);
  }

}

