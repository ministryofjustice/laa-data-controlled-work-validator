package uk.gov.justice.laa.dstew.payments.claimsdata.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;
import org.springframework.lang.Nullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * bulk submission outcome details
 */

@Schema(name = "bulk_submission_outcome", description = "bulk submission outcome details")
@JsonTypeName("bulk_submission_outcome")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T21:51:30.932283Z[Europe/London]", comments = "Generator version: 7.14.0")
public class BulkSubmissionOutcome implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String matterType;

  private @Nullable String feeCode;

  private @Nullable String caseRefNumber;

  private @Nullable String caseStartDate;

  private @Nullable String caseId;

  private @Nullable String caseStageLevel;

  private @Nullable String ufn;

  private @Nullable String procurementArea;

  private @Nullable String accessPoint;

  private @Nullable String clientForename;

  private @Nullable String clientSurname;

  private @Nullable String clientDateOfBirth;

  private @Nullable String ucn;

  private @Nullable String claRefNumber;

  private @Nullable String claExemption;

  private @Nullable String gender;

  private @Nullable String ethnicity;

  private @Nullable String disability;

  private @Nullable String clientPostCode;

  private @Nullable String workConcludedDate;

  private @Nullable Integer adviceTime;

  private @Nullable Integer travelTime;

  private @Nullable Integer waitingTime;

  private @Nullable BigDecimal profitCost;

  private @Nullable BigDecimal valueOfCosts;

  private @Nullable BigDecimal disbursementsAmount;

  private @Nullable BigDecimal counselCost;

  private @Nullable BigDecimal disbursementsVat;

  private @Nullable BigDecimal travelWaitingCosts;

  private @Nullable Boolean vatIndicator;

  private @Nullable Boolean londonNonlondonRate;

  private @Nullable String clientType;

  private @Nullable Boolean toleranceIndicator;

  private @Nullable BigDecimal travelCosts;

  private @Nullable String outcomeCode;

  private @Nullable Boolean legacyCase;

  private @Nullable String claimType;

  private @Nullable Integer adjournedHearingFee;

  private @Nullable String typeOfAdvice;

  private @Nullable Boolean postalApplAccp;

  private @Nullable String scheduleRef;

  private @Nullable String cmrhOral;

  private @Nullable String cmrhTelephone;

  private @Nullable String aitHearingCentre;

  private @Nullable Boolean substantiveHearing;

  private @Nullable Integer hoInterview;

  private @Nullable String hoUcn;

  private @Nullable String transferDate;

  private @Nullable BigDecimal detentionTravelWaitingCosts;

  private @Nullable String deliveryLocation;

  private @Nullable String priorAuthorityRef;

  private @Nullable BigDecimal jrFormFilling;

  private @Nullable Boolean additionalTravelPayment;

  private @Nullable String meetingsAttended;

  private @Nullable Integer medicalReportsClaimed;

  private @Nullable Integer desiAccRep;

  private @Nullable String mhtRefNumber;

  private @Nullable String stageReached;

  private @Nullable String followOnWork;

  private @Nullable Boolean nationalRefMechanismAdvice;

  private @Nullable String exemptionCriteriaSatisfied;

  private @Nullable String exclCaseFundingRef;

  private @Nullable Integer noOfClients;

  private @Nullable Integer noOfSurgeryClients;

  private @Nullable Boolean ircSurgery;

  private @Nullable String surgeryDate;

  private @Nullable String lineNumber;

  private @Nullable String crimeMatterType;

  private @Nullable String feeScheme;

  private @Nullable String repOrderDate;

  private @Nullable Integer noOfSuspects;

  private @Nullable Integer noOfPoliceStation;

  private @Nullable String policeStation;

  private @Nullable String dsccNumber;

  private @Nullable String maatId;

  private @Nullable Boolean dutySolicitor;

  private @Nullable Boolean youthCourt;

  private @Nullable String schemeId;

  private @Nullable Integer numberOfMediationSessions;

  private @Nullable Integer mediationTime;

  private @Nullable String outreach;

  private @Nullable String referral;

  private @Nullable Boolean clientLegallyAided;

  private @Nullable String client2Forename;

  private @Nullable String client2Surname;

  private @Nullable String client2DateOfBirth;

  private @Nullable String client2Ucn;

  private @Nullable String client2PostCode;

  private @Nullable String client2Gender;

  private @Nullable String client2Ethnicity;

  private @Nullable String client2Disability;

  private @Nullable Boolean client2LegallyAided;

  private @Nullable String uniqueCaseId;

  private @Nullable String standardFeeCat;

  private @Nullable Boolean client2PostalApplAccp;

  private @Nullable BigDecimal costsDamagesRecovered;

  private @Nullable Boolean eligibleClient;

  private @Nullable String courtLocationHpcds;

  private @Nullable String localAuthorityNumber;

  private @Nullable String paNumber;

  private @Nullable BigDecimal excessTravelCosts;

  private @Nullable String medConcludedDate;

  public BulkSubmissionOutcome matterType(@Nullable String matterType) {
    this.matterType = matterType;
    return this;
  }

  /**
   * Get matterType
   * @return matterType
   */
  
  @Schema(name = "matter_type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("matter_type")
  public @Nullable String getMatterType() {
    return matterType;
  }

  public void setMatterType(@Nullable String matterType) {
    this.matterType = matterType;
  }

  public BulkSubmissionOutcome feeCode(@Nullable String feeCode) {
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

  public BulkSubmissionOutcome caseRefNumber(@Nullable String caseRefNumber) {
    this.caseRefNumber = caseRefNumber;
    return this;
  }

  /**
   * Get caseRefNumber
   * @return caseRefNumber
   */
  
  @Schema(name = "case_ref_number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("case_ref_number")
  public @Nullable String getCaseRefNumber() {
    return caseRefNumber;
  }

  public void setCaseRefNumber(@Nullable String caseRefNumber) {
    this.caseRefNumber = caseRefNumber;
  }

  public BulkSubmissionOutcome caseStartDate(@Nullable String caseStartDate) {
    this.caseStartDate = caseStartDate;
    return this;
  }

  /**
   * Get caseStartDate
   * @return caseStartDate
   */
  
  @Schema(name = "case_start_date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("case_start_date")
  public @Nullable String getCaseStartDate() {
    return caseStartDate;
  }

  public void setCaseStartDate(@Nullable String caseStartDate) {
    this.caseStartDate = caseStartDate;
  }

  public BulkSubmissionOutcome caseId(@Nullable String caseId) {
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

  public BulkSubmissionOutcome caseStageLevel(@Nullable String caseStageLevel) {
    this.caseStageLevel = caseStageLevel;
    return this;
  }

  /**
   * Get caseStageLevel
   * @return caseStageLevel
   */
  
  @Schema(name = "case_stage_level", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("case_stage_level")
  public @Nullable String getCaseStageLevel() {
    return caseStageLevel;
  }

  public void setCaseStageLevel(@Nullable String caseStageLevel) {
    this.caseStageLevel = caseStageLevel;
  }

  public BulkSubmissionOutcome ufn(@Nullable String ufn) {
    this.ufn = ufn;
    return this;
  }

  /**
   * Get ufn
   * @return ufn
   */
  
  @Schema(name = "ufn", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ufn")
  public @Nullable String getUfn() {
    return ufn;
  }

  public void setUfn(@Nullable String ufn) {
    this.ufn = ufn;
  }

  public BulkSubmissionOutcome procurementArea(@Nullable String procurementArea) {
    this.procurementArea = procurementArea;
    return this;
  }

  /**
   * Get procurementArea
   * @return procurementArea
   */
  
  @Schema(name = "procurement_area", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("procurement_area")
  public @Nullable String getProcurementArea() {
    return procurementArea;
  }

  public void setProcurementArea(@Nullable String procurementArea) {
    this.procurementArea = procurementArea;
  }

  public BulkSubmissionOutcome accessPoint(@Nullable String accessPoint) {
    this.accessPoint = accessPoint;
    return this;
  }

  /**
   * Get accessPoint
   * @return accessPoint
   */
  
  @Schema(name = "access_point", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("access_point")
  public @Nullable String getAccessPoint() {
    return accessPoint;
  }

  public void setAccessPoint(@Nullable String accessPoint) {
    this.accessPoint = accessPoint;
  }

  public BulkSubmissionOutcome clientForename(@Nullable String clientForename) {
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

  public BulkSubmissionOutcome clientSurname(@Nullable String clientSurname) {
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

  public BulkSubmissionOutcome clientDateOfBirth(@Nullable String clientDateOfBirth) {
    this.clientDateOfBirth = clientDateOfBirth;
    return this;
  }

  /**
   * Get clientDateOfBirth
   * @return clientDateOfBirth
   */
  
  @Schema(name = "client_date_of_birth", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client_date_of_birth")
  public @Nullable String getClientDateOfBirth() {
    return clientDateOfBirth;
  }

  public void setClientDateOfBirth(@Nullable String clientDateOfBirth) {
    this.clientDateOfBirth = clientDateOfBirth;
  }

  public BulkSubmissionOutcome ucn(@Nullable String ucn) {
    this.ucn = ucn;
    return this;
  }

  /**
   * Get ucn
   * @return ucn
   */
  
  @Schema(name = "ucn", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ucn")
  public @Nullable String getUcn() {
    return ucn;
  }

  public void setUcn(@Nullable String ucn) {
    this.ucn = ucn;
  }

  public BulkSubmissionOutcome claRefNumber(@Nullable String claRefNumber) {
    this.claRefNumber = claRefNumber;
    return this;
  }

  /**
   * Get claRefNumber
   * @return claRefNumber
   */
  
  @Schema(name = "cla_ref_number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cla_ref_number")
  public @Nullable String getClaRefNumber() {
    return claRefNumber;
  }

  public void setClaRefNumber(@Nullable String claRefNumber) {
    this.claRefNumber = claRefNumber;
  }

  public BulkSubmissionOutcome claExemption(@Nullable String claExemption) {
    this.claExemption = claExemption;
    return this;
  }

  /**
   * Get claExemption
   * @return claExemption
   */
  
  @Schema(name = "cla_exemption", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cla_exemption")
  public @Nullable String getClaExemption() {
    return claExemption;
  }

  public void setClaExemption(@Nullable String claExemption) {
    this.claExemption = claExemption;
  }

  public BulkSubmissionOutcome gender(@Nullable String gender) {
    this.gender = gender;
    return this;
  }

  /**
   * Get gender
   * @return gender
   */
  
  @Schema(name = "gender", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("gender")
  public @Nullable String getGender() {
    return gender;
  }

  public void setGender(@Nullable String gender) {
    this.gender = gender;
  }

  public BulkSubmissionOutcome ethnicity(@Nullable String ethnicity) {
    this.ethnicity = ethnicity;
    return this;
  }

  /**
   * Get ethnicity
   * @return ethnicity
   */
  
  @Schema(name = "ethnicity", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ethnicity")
  public @Nullable String getEthnicity() {
    return ethnicity;
  }

  public void setEthnicity(@Nullable String ethnicity) {
    this.ethnicity = ethnicity;
  }

  public BulkSubmissionOutcome disability(@Nullable String disability) {
    this.disability = disability;
    return this;
  }

  /**
   * Get disability
   * @return disability
   */
  
  @Schema(name = "disability", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("disability")
  public @Nullable String getDisability() {
    return disability;
  }

  public void setDisability(@Nullable String disability) {
    this.disability = disability;
  }

  public BulkSubmissionOutcome clientPostCode(@Nullable String clientPostCode) {
    this.clientPostCode = clientPostCode;
    return this;
  }

  /**
   * Get clientPostCode
   * @return clientPostCode
   */
  
  @Schema(name = "client_post_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client_post_code")
  public @Nullable String getClientPostCode() {
    return clientPostCode;
  }

  public void setClientPostCode(@Nullable String clientPostCode) {
    this.clientPostCode = clientPostCode;
  }

  public BulkSubmissionOutcome workConcludedDate(@Nullable String workConcludedDate) {
    this.workConcludedDate = workConcludedDate;
    return this;
  }

  /**
   * Get workConcludedDate
   * @return workConcludedDate
   */
  
  @Schema(name = "work_concluded_date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("work_concluded_date")
  public @Nullable String getWorkConcludedDate() {
    return workConcludedDate;
  }

  public void setWorkConcludedDate(@Nullable String workConcludedDate) {
    this.workConcludedDate = workConcludedDate;
  }

  public BulkSubmissionOutcome adviceTime(@Nullable Integer adviceTime) {
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

  public BulkSubmissionOutcome travelTime(@Nullable Integer travelTime) {
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

  public BulkSubmissionOutcome waitingTime(@Nullable Integer waitingTime) {
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

  public BulkSubmissionOutcome profitCost(@Nullable BigDecimal profitCost) {
    this.profitCost = profitCost;
    return this;
  }

  /**
   * Get profitCost
   * @return profitCost
   */
  @Valid 
  @Schema(name = "profit_cost", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("profit_cost")
  public @Nullable BigDecimal getProfitCost() {
    return profitCost;
  }

  public void setProfitCost(@Nullable BigDecimal profitCost) {
    this.profitCost = profitCost;
  }

  public BulkSubmissionOutcome valueOfCosts(@Nullable BigDecimal valueOfCosts) {
    this.valueOfCosts = valueOfCosts;
    return this;
  }

  /**
   * Get valueOfCosts
   * @return valueOfCosts
   */
  @Valid 
  @Schema(name = "value_of_costs", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("value_of_costs")
  public @Nullable BigDecimal getValueOfCosts() {
    return valueOfCosts;
  }

  public void setValueOfCosts(@Nullable BigDecimal valueOfCosts) {
    this.valueOfCosts = valueOfCosts;
  }

  public BulkSubmissionOutcome disbursementsAmount(@Nullable BigDecimal disbursementsAmount) {
    this.disbursementsAmount = disbursementsAmount;
    return this;
  }

  /**
   * Get disbursementsAmount
   * @return disbursementsAmount
   */
  @Valid 
  @Schema(name = "disbursements_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("disbursements_amount")
  public @Nullable BigDecimal getDisbursementsAmount() {
    return disbursementsAmount;
  }

  public void setDisbursementsAmount(@Nullable BigDecimal disbursementsAmount) {
    this.disbursementsAmount = disbursementsAmount;
  }

  public BulkSubmissionOutcome counselCost(@Nullable BigDecimal counselCost) {
    this.counselCost = counselCost;
    return this;
  }

  /**
   * Get counselCost
   * @return counselCost
   */
  @Valid 
  @Schema(name = "counsel_cost", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("counsel_cost")
  public @Nullable BigDecimal getCounselCost() {
    return counselCost;
  }

  public void setCounselCost(@Nullable BigDecimal counselCost) {
    this.counselCost = counselCost;
  }

  public BulkSubmissionOutcome disbursementsVat(@Nullable BigDecimal disbursementsVat) {
    this.disbursementsVat = disbursementsVat;
    return this;
  }

  /**
   * Get disbursementsVat
   * @return disbursementsVat
   */
  @Valid 
  @Schema(name = "disbursements_vat", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("disbursements_vat")
  public @Nullable BigDecimal getDisbursementsVat() {
    return disbursementsVat;
  }

  public void setDisbursementsVat(@Nullable BigDecimal disbursementsVat) {
    this.disbursementsVat = disbursementsVat;
  }

  public BulkSubmissionOutcome travelWaitingCosts(@Nullable BigDecimal travelWaitingCosts) {
    this.travelWaitingCosts = travelWaitingCosts;
    return this;
  }

  /**
   * Get travelWaitingCosts
   * @return travelWaitingCosts
   */
  @Valid 
  @Schema(name = "travel_waiting_costs", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("travel_waiting_costs")
  public @Nullable BigDecimal getTravelWaitingCosts() {
    return travelWaitingCosts;
  }

  public void setTravelWaitingCosts(@Nullable BigDecimal travelWaitingCosts) {
    this.travelWaitingCosts = travelWaitingCosts;
  }

  public BulkSubmissionOutcome vatIndicator(@Nullable Boolean vatIndicator) {
    this.vatIndicator = vatIndicator;
    return this;
  }

  /**
   * Get vatIndicator
   * @return vatIndicator
   */
  
  @Schema(name = "vat_indicator", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("vat_indicator")
  public @Nullable Boolean getVatIndicator() {
    return vatIndicator;
  }

  public void setVatIndicator(@Nullable Boolean vatIndicator) {
    this.vatIndicator = vatIndicator;
  }

  public BulkSubmissionOutcome londonNonlondonRate(@Nullable Boolean londonNonlondonRate) {
    this.londonNonlondonRate = londonNonlondonRate;
    return this;
  }

  /**
   * Get londonNonlondonRate
   * @return londonNonlondonRate
   */
  
  @Schema(name = "london_nonlondon_rate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("london_nonlondon_rate")
  public @Nullable Boolean getLondonNonlondonRate() {
    return londonNonlondonRate;
  }

  public void setLondonNonlondonRate(@Nullable Boolean londonNonlondonRate) {
    this.londonNonlondonRate = londonNonlondonRate;
  }

  public BulkSubmissionOutcome clientType(@Nullable String clientType) {
    this.clientType = clientType;
    return this;
  }

  /**
   * Get clientType
   * @return clientType
   */
  
  @Schema(name = "client_type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client_type")
  public @Nullable String getClientType() {
    return clientType;
  }

  public void setClientType(@Nullable String clientType) {
    this.clientType = clientType;
  }

  public BulkSubmissionOutcome toleranceIndicator(@Nullable Boolean toleranceIndicator) {
    this.toleranceIndicator = toleranceIndicator;
    return this;
  }

  /**
   * Get toleranceIndicator
   * @return toleranceIndicator
   */
  
  @Schema(name = "tolerance_indicator", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("tolerance_indicator")
  public @Nullable Boolean getToleranceIndicator() {
    return toleranceIndicator;
  }

  public void setToleranceIndicator(@Nullable Boolean toleranceIndicator) {
    this.toleranceIndicator = toleranceIndicator;
  }

  public BulkSubmissionOutcome travelCosts(@Nullable BigDecimal travelCosts) {
    this.travelCosts = travelCosts;
    return this;
  }

  /**
   * Get travelCosts
   * @return travelCosts
   */
  @Valid 
  @Schema(name = "travel_costs", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("travel_costs")
  public @Nullable BigDecimal getTravelCosts() {
    return travelCosts;
  }

  public void setTravelCosts(@Nullable BigDecimal travelCosts) {
    this.travelCosts = travelCosts;
  }

  public BulkSubmissionOutcome outcomeCode(@Nullable String outcomeCode) {
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

  public BulkSubmissionOutcome legacyCase(@Nullable Boolean legacyCase) {
    this.legacyCase = legacyCase;
    return this;
  }

  /**
   * Get legacyCase
   * @return legacyCase
   */
  
  @Schema(name = "legacy_case", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("legacy_case")
  public @Nullable Boolean getLegacyCase() {
    return legacyCase;
  }

  public void setLegacyCase(@Nullable Boolean legacyCase) {
    this.legacyCase = legacyCase;
  }

  public BulkSubmissionOutcome claimType(@Nullable String claimType) {
    this.claimType = claimType;
    return this;
  }

  /**
   * Get claimType
   * @return claimType
   */
  
  @Schema(name = "claim_type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("claim_type")
  public @Nullable String getClaimType() {
    return claimType;
  }

  public void setClaimType(@Nullable String claimType) {
    this.claimType = claimType;
  }

  public BulkSubmissionOutcome adjournedHearingFee(@Nullable Integer adjournedHearingFee) {
    this.adjournedHearingFee = adjournedHearingFee;
    return this;
  }

  /**
   * Note: actually stores the number of times the hearing was adjourned
   * @return adjournedHearingFee
   */
  
  @Schema(name = "adjourned_hearing_fee", description = "Note: actually stores the number of times the hearing was adjourned", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("adjourned_hearing_fee")
  public @Nullable Integer getAdjournedHearingFee() {
    return adjournedHearingFee;
  }

  public void setAdjournedHearingFee(@Nullable Integer adjournedHearingFee) {
    this.adjournedHearingFee = adjournedHearingFee;
  }

  public BulkSubmissionOutcome typeOfAdvice(@Nullable String typeOfAdvice) {
    this.typeOfAdvice = typeOfAdvice;
    return this;
  }

  /**
   * Get typeOfAdvice
   * @return typeOfAdvice
   */
  
  @Schema(name = "type_of_advice", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type_of_advice")
  public @Nullable String getTypeOfAdvice() {
    return typeOfAdvice;
  }

  public void setTypeOfAdvice(@Nullable String typeOfAdvice) {
    this.typeOfAdvice = typeOfAdvice;
  }

  public BulkSubmissionOutcome postalApplAccp(@Nullable Boolean postalApplAccp) {
    this.postalApplAccp = postalApplAccp;
    return this;
  }

  /**
   * Get postalApplAccp
   * @return postalApplAccp
   */
  
  @Schema(name = "postal_appl_accp", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("postal_appl_accp")
  public @Nullable Boolean getPostalApplAccp() {
    return postalApplAccp;
  }

  public void setPostalApplAccp(@Nullable Boolean postalApplAccp) {
    this.postalApplAccp = postalApplAccp;
  }

  public BulkSubmissionOutcome scheduleRef(@Nullable String scheduleRef) {
    this.scheduleRef = scheduleRef;
    return this;
  }

  /**
   * Get scheduleRef
   * @return scheduleRef
   */
  
  @Schema(name = "schedule_ref", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("schedule_ref")
  public @Nullable String getScheduleRef() {
    return scheduleRef;
  }

  public void setScheduleRef(@Nullable String scheduleRef) {
    this.scheduleRef = scheduleRef;
  }

  public BulkSubmissionOutcome cmrhOral(@Nullable String cmrhOral) {
    this.cmrhOral = cmrhOral;
    return this;
  }

  /**
   * Get cmrhOral
   * @return cmrhOral
   */
  
  @Schema(name = "cmrh_oral", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cmrh_oral")
  public @Nullable String getCmrhOral() {
    return cmrhOral;
  }

  public void setCmrhOral(@Nullable String cmrhOral) {
    this.cmrhOral = cmrhOral;
  }

  public BulkSubmissionOutcome cmrhTelephone(@Nullable String cmrhTelephone) {
    this.cmrhTelephone = cmrhTelephone;
    return this;
  }

  /**
   * Get cmrhTelephone
   * @return cmrhTelephone
   */
  
  @Schema(name = "cmrh_telephone", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cmrh_telephone")
  public @Nullable String getCmrhTelephone() {
    return cmrhTelephone;
  }

  public void setCmrhTelephone(@Nullable String cmrhTelephone) {
    this.cmrhTelephone = cmrhTelephone;
  }

  public BulkSubmissionOutcome aitHearingCentre(@Nullable String aitHearingCentre) {
    this.aitHearingCentre = aitHearingCentre;
    return this;
  }

  /**
   * Get aitHearingCentre
   * @return aitHearingCentre
   */
  
  @Schema(name = "ait_hearing_centre", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ait_hearing_centre")
  public @Nullable String getAitHearingCentre() {
    return aitHearingCentre;
  }

  public void setAitHearingCentre(@Nullable String aitHearingCentre) {
    this.aitHearingCentre = aitHearingCentre;
  }

  public BulkSubmissionOutcome substantiveHearing(@Nullable Boolean substantiveHearing) {
    this.substantiveHearing = substantiveHearing;
    return this;
  }

  /**
   * Get substantiveHearing
   * @return substantiveHearing
   */
  
  @Schema(name = "substantive_hearing", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("substantive_hearing")
  public @Nullable Boolean getSubstantiveHearing() {
    return substantiveHearing;
  }

  public void setSubstantiveHearing(@Nullable Boolean substantiveHearing) {
    this.substantiveHearing = substantiveHearing;
  }

  public BulkSubmissionOutcome hoInterview(@Nullable Integer hoInterview) {
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

  public BulkSubmissionOutcome hoUcn(@Nullable String hoUcn) {
    this.hoUcn = hoUcn;
    return this;
  }

  /**
   * Get hoUcn
   * @return hoUcn
   */
  
  @Schema(name = "ho_ucn", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ho_ucn")
  public @Nullable String getHoUcn() {
    return hoUcn;
  }

  public void setHoUcn(@Nullable String hoUcn) {
    this.hoUcn = hoUcn;
  }

  public BulkSubmissionOutcome transferDate(@Nullable String transferDate) {
    this.transferDate = transferDate;
    return this;
  }

  /**
   * Get transferDate
   * @return transferDate
   */
  
  @Schema(name = "transfer_date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("transfer_date")
  public @Nullable String getTransferDate() {
    return transferDate;
  }

  public void setTransferDate(@Nullable String transferDate) {
    this.transferDate = transferDate;
  }

  public BulkSubmissionOutcome detentionTravelWaitingCosts(@Nullable BigDecimal detentionTravelWaitingCosts) {
    this.detentionTravelWaitingCosts = detentionTravelWaitingCosts;
    return this;
  }

  /**
   * Get detentionTravelWaitingCosts
   * @return detentionTravelWaitingCosts
   */
  @Valid 
  @Schema(name = "detention_travel_waiting_costs", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("detention_travel_waiting_costs")
  public @Nullable BigDecimal getDetentionTravelWaitingCosts() {
    return detentionTravelWaitingCosts;
  }

  public void setDetentionTravelWaitingCosts(@Nullable BigDecimal detentionTravelWaitingCosts) {
    this.detentionTravelWaitingCosts = detentionTravelWaitingCosts;
  }

  public BulkSubmissionOutcome deliveryLocation(@Nullable String deliveryLocation) {
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

  public BulkSubmissionOutcome priorAuthorityRef(@Nullable String priorAuthorityRef) {
    this.priorAuthorityRef = priorAuthorityRef;
    return this;
  }

  /**
   * Get priorAuthorityRef
   * @return priorAuthorityRef
   */
  
  @Schema(name = "prior_authority_ref", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("prior_authority_ref")
  public @Nullable String getPriorAuthorityRef() {
    return priorAuthorityRef;
  }

  public void setPriorAuthorityRef(@Nullable String priorAuthorityRef) {
    this.priorAuthorityRef = priorAuthorityRef;
  }

  public BulkSubmissionOutcome jrFormFilling(@Nullable BigDecimal jrFormFilling) {
    this.jrFormFilling = jrFormFilling;
    return this;
  }

  /**
   * Get jrFormFilling
   * @return jrFormFilling
   */
  @Valid 
  @Schema(name = "jr_form_filling", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("jr_form_filling")
  public @Nullable BigDecimal getJrFormFilling() {
    return jrFormFilling;
  }

  public void setJrFormFilling(@Nullable BigDecimal jrFormFilling) {
    this.jrFormFilling = jrFormFilling;
  }

  public BulkSubmissionOutcome additionalTravelPayment(@Nullable Boolean additionalTravelPayment) {
    this.additionalTravelPayment = additionalTravelPayment;
    return this;
  }

  /**
   * Get additionalTravelPayment
   * @return additionalTravelPayment
   */
  
  @Schema(name = "additional_travel_payment", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("additional_travel_payment")
  public @Nullable Boolean getAdditionalTravelPayment() {
    return additionalTravelPayment;
  }

  public void setAdditionalTravelPayment(@Nullable Boolean additionalTravelPayment) {
    this.additionalTravelPayment = additionalTravelPayment;
  }

  public BulkSubmissionOutcome meetingsAttended(@Nullable String meetingsAttended) {
    this.meetingsAttended = meetingsAttended;
    return this;
  }

  /**
   * Get meetingsAttended
   * @return meetingsAttended
   */
  
  @Schema(name = "meetings_attended", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("meetings_attended")
  public @Nullable String getMeetingsAttended() {
    return meetingsAttended;
  }

  public void setMeetingsAttended(@Nullable String meetingsAttended) {
    this.meetingsAttended = meetingsAttended;
  }

  public BulkSubmissionOutcome medicalReportsClaimed(@Nullable Integer medicalReportsClaimed) {
    this.medicalReportsClaimed = medicalReportsClaimed;
    return this;
  }

  /**
   * Get medicalReportsClaimed
   * @return medicalReportsClaimed
   */
  
  @Schema(name = "medical_reports_claimed", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("medical_reports_claimed")
  public @Nullable Integer getMedicalReportsClaimed() {
    return medicalReportsClaimed;
  }

  public void setMedicalReportsClaimed(@Nullable Integer medicalReportsClaimed) {
    this.medicalReportsClaimed = medicalReportsClaimed;
  }

  public BulkSubmissionOutcome desiAccRep(@Nullable Integer desiAccRep) {
    this.desiAccRep = desiAccRep;
    return this;
  }

  /**
   * designated accredited representative
   * @return desiAccRep
   */
  
  @Schema(name = "desi_acc_rep", description = "designated accredited representative", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("desi_acc_rep")
  public @Nullable Integer getDesiAccRep() {
    return desiAccRep;
  }

  public void setDesiAccRep(@Nullable Integer desiAccRep) {
    this.desiAccRep = desiAccRep;
  }

  public BulkSubmissionOutcome mhtRefNumber(@Nullable String mhtRefNumber) {
    this.mhtRefNumber = mhtRefNumber;
    return this;
  }

  /**
   * Get mhtRefNumber
   * @return mhtRefNumber
   */
  
  @Schema(name = "mht_ref_number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mht_ref_number")
  public @Nullable String getMhtRefNumber() {
    return mhtRefNumber;
  }

  public void setMhtRefNumber(@Nullable String mhtRefNumber) {
    this.mhtRefNumber = mhtRefNumber;
  }

  public BulkSubmissionOutcome stageReached(@Nullable String stageReached) {
    this.stageReached = stageReached;
    return this;
  }

  /**
   * Get stageReached
   * @return stageReached
   */
  
  @Schema(name = "stage_reached", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("stage_reached")
  public @Nullable String getStageReached() {
    return stageReached;
  }

  public void setStageReached(@Nullable String stageReached) {
    this.stageReached = stageReached;
  }

  public BulkSubmissionOutcome followOnWork(@Nullable String followOnWork) {
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

  public BulkSubmissionOutcome nationalRefMechanismAdvice(@Nullable Boolean nationalRefMechanismAdvice) {
    this.nationalRefMechanismAdvice = nationalRefMechanismAdvice;
    return this;
  }

  /**
   * Get nationalRefMechanismAdvice
   * @return nationalRefMechanismAdvice
   */
  
  @Schema(name = "national_ref_mechanism_advice", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("national_ref_mechanism_advice")
  public @Nullable Boolean getNationalRefMechanismAdvice() {
    return nationalRefMechanismAdvice;
  }

  public void setNationalRefMechanismAdvice(@Nullable Boolean nationalRefMechanismAdvice) {
    this.nationalRefMechanismAdvice = nationalRefMechanismAdvice;
  }

  public BulkSubmissionOutcome exemptionCriteriaSatisfied(@Nullable String exemptionCriteriaSatisfied) {
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

  public BulkSubmissionOutcome exclCaseFundingRef(@Nullable String exclCaseFundingRef) {
    this.exclCaseFundingRef = exclCaseFundingRef;
    return this;
  }

  /**
   * Get exclCaseFundingRef
   * @return exclCaseFundingRef
   */
  
  @Schema(name = "excl_case_funding_ref", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("excl_case_funding_ref")
  public @Nullable String getExclCaseFundingRef() {
    return exclCaseFundingRef;
  }

  public void setExclCaseFundingRef(@Nullable String exclCaseFundingRef) {
    this.exclCaseFundingRef = exclCaseFundingRef;
  }

  public BulkSubmissionOutcome noOfClients(@Nullable Integer noOfClients) {
    this.noOfClients = noOfClients;
    return this;
  }

  /**
   * Get noOfClients
   * @return noOfClients
   */
  
  @Schema(name = "no_of_clients", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("no_of_clients")
  public @Nullable Integer getNoOfClients() {
    return noOfClients;
  }

  public void setNoOfClients(@Nullable Integer noOfClients) {
    this.noOfClients = noOfClients;
  }

  public BulkSubmissionOutcome noOfSurgeryClients(@Nullable Integer noOfSurgeryClients) {
    this.noOfSurgeryClients = noOfSurgeryClients;
    return this;
  }

  /**
   * Get noOfSurgeryClients
   * @return noOfSurgeryClients
   */
  
  @Schema(name = "no_of_surgery_clients", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("no_of_surgery_clients")
  public @Nullable Integer getNoOfSurgeryClients() {
    return noOfSurgeryClients;
  }

  public void setNoOfSurgeryClients(@Nullable Integer noOfSurgeryClients) {
    this.noOfSurgeryClients = noOfSurgeryClients;
  }

  public BulkSubmissionOutcome ircSurgery(@Nullable Boolean ircSurgery) {
    this.ircSurgery = ircSurgery;
    return this;
  }

  /**
   * Get ircSurgery
   * @return ircSurgery
   */
  
  @Schema(name = "irc_surgery", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("irc_surgery")
  public @Nullable Boolean getIrcSurgery() {
    return ircSurgery;
  }

  public void setIrcSurgery(@Nullable Boolean ircSurgery) {
    this.ircSurgery = ircSurgery;
  }

  public BulkSubmissionOutcome surgeryDate(@Nullable String surgeryDate) {
    this.surgeryDate = surgeryDate;
    return this;
  }

  /**
   * Get surgeryDate
   * @return surgeryDate
   */
  
  @Schema(name = "surgery_date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("surgery_date")
  public @Nullable String getSurgeryDate() {
    return surgeryDate;
  }

  public void setSurgeryDate(@Nullable String surgeryDate) {
    this.surgeryDate = surgeryDate;
  }

  public BulkSubmissionOutcome lineNumber(@Nullable String lineNumber) {
    this.lineNumber = lineNumber;
    return this;
  }

  /**
   * Get lineNumber
   * @return lineNumber
   */
  
  @Schema(name = "line_number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("line_number")
  public @Nullable String getLineNumber() {
    return lineNumber;
  }

  public void setLineNumber(@Nullable String lineNumber) {
    this.lineNumber = lineNumber;
  }

  public BulkSubmissionOutcome crimeMatterType(@Nullable String crimeMatterType) {
    this.crimeMatterType = crimeMatterType;
    return this;
  }

  /**
   * Get crimeMatterType
   * @return crimeMatterType
   */
  
  @Schema(name = "crime_matter_type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("crime_matter_type")
  public @Nullable String getCrimeMatterType() {
    return crimeMatterType;
  }

  public void setCrimeMatterType(@Nullable String crimeMatterType) {
    this.crimeMatterType = crimeMatterType;
  }

  public BulkSubmissionOutcome feeScheme(@Nullable String feeScheme) {
    this.feeScheme = feeScheme;
    return this;
  }

  /**
   * Get feeScheme
   * @return feeScheme
   */
  
  @Schema(name = "fee_scheme", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fee_scheme")
  public @Nullable String getFeeScheme() {
    return feeScheme;
  }

  public void setFeeScheme(@Nullable String feeScheme) {
    this.feeScheme = feeScheme;
  }

  public BulkSubmissionOutcome repOrderDate(@Nullable String repOrderDate) {
    this.repOrderDate = repOrderDate;
    return this;
  }

  /**
   * Get repOrderDate
   * @return repOrderDate
   */
  
  @Schema(name = "rep_order_date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("rep_order_date")
  public @Nullable String getRepOrderDate() {
    return repOrderDate;
  }

  public void setRepOrderDate(@Nullable String repOrderDate) {
    this.repOrderDate = repOrderDate;
  }

  public BulkSubmissionOutcome noOfSuspects(@Nullable Integer noOfSuspects) {
    this.noOfSuspects = noOfSuspects;
    return this;
  }

  /**
   * Get noOfSuspects
   * @return noOfSuspects
   */
  
  @Schema(name = "no_of_suspects", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("no_of_suspects")
  public @Nullable Integer getNoOfSuspects() {
    return noOfSuspects;
  }

  public void setNoOfSuspects(@Nullable Integer noOfSuspects) {
    this.noOfSuspects = noOfSuspects;
  }

  public BulkSubmissionOutcome noOfPoliceStation(@Nullable Integer noOfPoliceStation) {
    this.noOfPoliceStation = noOfPoliceStation;
    return this;
  }

  /**
   * Get noOfPoliceStation
   * @return noOfPoliceStation
   */
  
  @Schema(name = "no_of_police_station", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("no_of_police_station")
  public @Nullable Integer getNoOfPoliceStation() {
    return noOfPoliceStation;
  }

  public void setNoOfPoliceStation(@Nullable Integer noOfPoliceStation) {
    this.noOfPoliceStation = noOfPoliceStation;
  }

  public BulkSubmissionOutcome policeStation(@Nullable String policeStation) {
    this.policeStation = policeStation;
    return this;
  }

  /**
   * Get policeStation
   * @return policeStation
   */
  
  @Schema(name = "police_station", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("police_station")
  public @Nullable String getPoliceStation() {
    return policeStation;
  }

  public void setPoliceStation(@Nullable String policeStation) {
    this.policeStation = policeStation;
  }

  public BulkSubmissionOutcome dsccNumber(@Nullable String dsccNumber) {
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

  public BulkSubmissionOutcome maatId(@Nullable String maatId) {
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

  public BulkSubmissionOutcome dutySolicitor(@Nullable Boolean dutySolicitor) {
    this.dutySolicitor = dutySolicitor;
    return this;
  }

  /**
   * Get dutySolicitor
   * @return dutySolicitor
   */
  
  @Schema(name = "duty_solicitor", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("duty_solicitor")
  public @Nullable Boolean getDutySolicitor() {
    return dutySolicitor;
  }

  public void setDutySolicitor(@Nullable Boolean dutySolicitor) {
    this.dutySolicitor = dutySolicitor;
  }

  public BulkSubmissionOutcome youthCourt(@Nullable Boolean youthCourt) {
    this.youthCourt = youthCourt;
    return this;
  }

  /**
   * Get youthCourt
   * @return youthCourt
   */
  
  @Schema(name = "youth_court", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("youth_court")
  public @Nullable Boolean getYouthCourt() {
    return youthCourt;
  }

  public void setYouthCourt(@Nullable Boolean youthCourt) {
    this.youthCourt = youthCourt;
  }

  public BulkSubmissionOutcome schemeId(@Nullable String schemeId) {
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

  public BulkSubmissionOutcome numberOfMediationSessions(@Nullable Integer numberOfMediationSessions) {
    this.numberOfMediationSessions = numberOfMediationSessions;
    return this;
  }

  /**
   * Get numberOfMediationSessions
   * @return numberOfMediationSessions
   */
  
  @Schema(name = "number_of_mediation_sessions", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("number_of_mediation_sessions")
  public @Nullable Integer getNumberOfMediationSessions() {
    return numberOfMediationSessions;
  }

  public void setNumberOfMediationSessions(@Nullable Integer numberOfMediationSessions) {
    this.numberOfMediationSessions = numberOfMediationSessions;
  }

  public BulkSubmissionOutcome mediationTime(@Nullable Integer mediationTime) {
    this.mediationTime = mediationTime;
    return this;
  }

  /**
   * Get mediationTime
   * @return mediationTime
   */
  
  @Schema(name = "mediation_time", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mediation_time")
  public @Nullable Integer getMediationTime() {
    return mediationTime;
  }

  public void setMediationTime(@Nullable Integer mediationTime) {
    this.mediationTime = mediationTime;
  }

  public BulkSubmissionOutcome outreach(@Nullable String outreach) {
    this.outreach = outreach;
    return this;
  }

  /**
   * Get outreach
   * @return outreach
   */
  
  @Schema(name = "outreach", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("outreach")
  public @Nullable String getOutreach() {
    return outreach;
  }

  public void setOutreach(@Nullable String outreach) {
    this.outreach = outreach;
  }

  public BulkSubmissionOutcome referral(@Nullable String referral) {
    this.referral = referral;
    return this;
  }

  /**
   * Get referral
   * @return referral
   */
  
  @Schema(name = "referral", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("referral")
  public @Nullable String getReferral() {
    return referral;
  }

  public void setReferral(@Nullable String referral) {
    this.referral = referral;
  }

  public BulkSubmissionOutcome clientLegallyAided(@Nullable Boolean clientLegallyAided) {
    this.clientLegallyAided = clientLegallyAided;
    return this;
  }

  /**
   * Get clientLegallyAided
   * @return clientLegallyAided
   */
  
  @Schema(name = "client_legally_aided", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client_legally_aided")
  public @Nullable Boolean getClientLegallyAided() {
    return clientLegallyAided;
  }

  public void setClientLegallyAided(@Nullable Boolean clientLegallyAided) {
    this.clientLegallyAided = clientLegallyAided;
  }

  public BulkSubmissionOutcome client2Forename(@Nullable String client2Forename) {
    this.client2Forename = client2Forename;
    return this;
  }

  /**
   * Get client2Forename
   * @return client2Forename
   */
  
  @Schema(name = "client2_forename", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client2_forename")
  public @Nullable String getClient2Forename() {
    return client2Forename;
  }

  public void setClient2Forename(@Nullable String client2Forename) {
    this.client2Forename = client2Forename;
  }

  public BulkSubmissionOutcome client2Surname(@Nullable String client2Surname) {
    this.client2Surname = client2Surname;
    return this;
  }

  /**
   * Get client2Surname
   * @return client2Surname
   */
  
  @Schema(name = "client2_surname", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client2_surname")
  public @Nullable String getClient2Surname() {
    return client2Surname;
  }

  public void setClient2Surname(@Nullable String client2Surname) {
    this.client2Surname = client2Surname;
  }

  public BulkSubmissionOutcome client2DateOfBirth(@Nullable String client2DateOfBirth) {
    this.client2DateOfBirth = client2DateOfBirth;
    return this;
  }

  /**
   * Get client2DateOfBirth
   * @return client2DateOfBirth
   */
  
  @Schema(name = "client2_date_of_birth", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client2_date_of_birth")
  public @Nullable String getClient2DateOfBirth() {
    return client2DateOfBirth;
  }

  public void setClient2DateOfBirth(@Nullable String client2DateOfBirth) {
    this.client2DateOfBirth = client2DateOfBirth;
  }

  public BulkSubmissionOutcome client2Ucn(@Nullable String client2Ucn) {
    this.client2Ucn = client2Ucn;
    return this;
  }

  /**
   * Get client2Ucn
   * @return client2Ucn
   */
  
  @Schema(name = "client2_ucn", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client2_ucn")
  public @Nullable String getClient2Ucn() {
    return client2Ucn;
  }

  public void setClient2Ucn(@Nullable String client2Ucn) {
    this.client2Ucn = client2Ucn;
  }

  public BulkSubmissionOutcome client2PostCode(@Nullable String client2PostCode) {
    this.client2PostCode = client2PostCode;
    return this;
  }

  /**
   * Get client2PostCode
   * @return client2PostCode
   */
  
  @Schema(name = "client2_post_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client2_post_code")
  public @Nullable String getClient2PostCode() {
    return client2PostCode;
  }

  public void setClient2PostCode(@Nullable String client2PostCode) {
    this.client2PostCode = client2PostCode;
  }

  public BulkSubmissionOutcome client2Gender(@Nullable String client2Gender) {
    this.client2Gender = client2Gender;
    return this;
  }

  /**
   * Get client2Gender
   * @return client2Gender
   */
  
  @Schema(name = "client2_gender", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client2_gender")
  public @Nullable String getClient2Gender() {
    return client2Gender;
  }

  public void setClient2Gender(@Nullable String client2Gender) {
    this.client2Gender = client2Gender;
  }

  public BulkSubmissionOutcome client2Ethnicity(@Nullable String client2Ethnicity) {
    this.client2Ethnicity = client2Ethnicity;
    return this;
  }

  /**
   * Get client2Ethnicity
   * @return client2Ethnicity
   */
  
  @Schema(name = "client2_ethnicity", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client2_ethnicity")
  public @Nullable String getClient2Ethnicity() {
    return client2Ethnicity;
  }

  public void setClient2Ethnicity(@Nullable String client2Ethnicity) {
    this.client2Ethnicity = client2Ethnicity;
  }

  public BulkSubmissionOutcome client2Disability(@Nullable String client2Disability) {
    this.client2Disability = client2Disability;
    return this;
  }

  /**
   * Get client2Disability
   * @return client2Disability
   */
  
  @Schema(name = "client2_disability", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client2_disability")
  public @Nullable String getClient2Disability() {
    return client2Disability;
  }

  public void setClient2Disability(@Nullable String client2Disability) {
    this.client2Disability = client2Disability;
  }

  public BulkSubmissionOutcome client2LegallyAided(@Nullable Boolean client2LegallyAided) {
    this.client2LegallyAided = client2LegallyAided;
    return this;
  }

  /**
   * Get client2LegallyAided
   * @return client2LegallyAided
   */
  
  @Schema(name = "client2_legally_aided", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client2_legally_aided")
  public @Nullable Boolean getClient2LegallyAided() {
    return client2LegallyAided;
  }

  public void setClient2LegallyAided(@Nullable Boolean client2LegallyAided) {
    this.client2LegallyAided = client2LegallyAided;
  }

  public BulkSubmissionOutcome uniqueCaseId(@Nullable String uniqueCaseId) {
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

  public BulkSubmissionOutcome standardFeeCat(@Nullable String standardFeeCat) {
    this.standardFeeCat = standardFeeCat;
    return this;
  }

  /**
   * Get standardFeeCat
   * @return standardFeeCat
   */
  
  @Schema(name = "standard_fee_cat", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("standard_fee_cat")
  public @Nullable String getStandardFeeCat() {
    return standardFeeCat;
  }

  public void setStandardFeeCat(@Nullable String standardFeeCat) {
    this.standardFeeCat = standardFeeCat;
  }

  public BulkSubmissionOutcome client2PostalApplAccp(@Nullable Boolean client2PostalApplAccp) {
    this.client2PostalApplAccp = client2PostalApplAccp;
    return this;
  }

  /**
   * Get client2PostalApplAccp
   * @return client2PostalApplAccp
   */
  
  @Schema(name = "client_2_postal_appl_accp", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("client_2_postal_appl_accp")
  public @Nullable Boolean getClient2PostalApplAccp() {
    return client2PostalApplAccp;
  }

  public void setClient2PostalApplAccp(@Nullable Boolean client2PostalApplAccp) {
    this.client2PostalApplAccp = client2PostalApplAccp;
  }

  public BulkSubmissionOutcome costsDamagesRecovered(@Nullable BigDecimal costsDamagesRecovered) {
    this.costsDamagesRecovered = costsDamagesRecovered;
    return this;
  }

  /**
   * Get costsDamagesRecovered
   * @return costsDamagesRecovered
   */
  @Valid 
  @Schema(name = "costs_damages_recovered", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("costs_damages_recovered")
  public @Nullable BigDecimal getCostsDamagesRecovered() {
    return costsDamagesRecovered;
  }

  public void setCostsDamagesRecovered(@Nullable BigDecimal costsDamagesRecovered) {
    this.costsDamagesRecovered = costsDamagesRecovered;
  }

  public BulkSubmissionOutcome eligibleClient(@Nullable Boolean eligibleClient) {
    this.eligibleClient = eligibleClient;
    return this;
  }

  /**
   * Get eligibleClient
   * @return eligibleClient
   */
  
  @Schema(name = "eligible_client", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("eligible_client")
  public @Nullable Boolean getEligibleClient() {
    return eligibleClient;
  }

  public void setEligibleClient(@Nullable Boolean eligibleClient) {
    this.eligibleClient = eligibleClient;
  }

  public BulkSubmissionOutcome courtLocationHpcds(@Nullable String courtLocationHpcds) {
    this.courtLocationHpcds = courtLocationHpcds;
    return this;
  }

  /**
   * Get courtLocationHpcds
   * @return courtLocationHpcds
   */
  
  @Schema(name = "court_location_hpcds", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("court_location_hpcds")
  public @Nullable String getCourtLocationHpcds() {
    return courtLocationHpcds;
  }

  public void setCourtLocationHpcds(@Nullable String courtLocationHpcds) {
    this.courtLocationHpcds = courtLocationHpcds;
  }

  public BulkSubmissionOutcome localAuthorityNumber(@Nullable String localAuthorityNumber) {
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

  public BulkSubmissionOutcome paNumber(@Nullable String paNumber) {
    this.paNumber = paNumber;
    return this;
  }

  /**
   * Get paNumber
   * @return paNumber
   */
  
  @Schema(name = "pa_number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pa_number")
  public @Nullable String getPaNumber() {
    return paNumber;
  }

  public void setPaNumber(@Nullable String paNumber) {
    this.paNumber = paNumber;
  }

  public BulkSubmissionOutcome excessTravelCosts(@Nullable BigDecimal excessTravelCosts) {
    this.excessTravelCosts = excessTravelCosts;
    return this;
  }

  /**
   * Get excessTravelCosts
   * @return excessTravelCosts
   */
  @Valid 
  @Schema(name = "excess_travel_costs", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("excess_travel_costs")
  public @Nullable BigDecimal getExcessTravelCosts() {
    return excessTravelCosts;
  }

  public void setExcessTravelCosts(@Nullable BigDecimal excessTravelCosts) {
    this.excessTravelCosts = excessTravelCosts;
  }

  public BulkSubmissionOutcome medConcludedDate(@Nullable String medConcludedDate) {
    this.medConcludedDate = medConcludedDate;
    return this;
  }

  /**
   * Get medConcludedDate
   * @return medConcludedDate
   */
  
  @Schema(name = "med_concluded_date", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("med_concluded_date")
  public @Nullable String getMedConcludedDate() {
    return medConcludedDate;
  }

  public void setMedConcludedDate(@Nullable String medConcludedDate) {
    this.medConcludedDate = medConcludedDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BulkSubmissionOutcome bulkSubmissionOutcome = (BulkSubmissionOutcome) o;
    return Objects.equals(this.matterType, bulkSubmissionOutcome.matterType) &&
        Objects.equals(this.feeCode, bulkSubmissionOutcome.feeCode) &&
        Objects.equals(this.caseRefNumber, bulkSubmissionOutcome.caseRefNumber) &&
        Objects.equals(this.caseStartDate, bulkSubmissionOutcome.caseStartDate) &&
        Objects.equals(this.caseId, bulkSubmissionOutcome.caseId) &&
        Objects.equals(this.caseStageLevel, bulkSubmissionOutcome.caseStageLevel) &&
        Objects.equals(this.ufn, bulkSubmissionOutcome.ufn) &&
        Objects.equals(this.procurementArea, bulkSubmissionOutcome.procurementArea) &&
        Objects.equals(this.accessPoint, bulkSubmissionOutcome.accessPoint) &&
        Objects.equals(this.clientForename, bulkSubmissionOutcome.clientForename) &&
        Objects.equals(this.clientSurname, bulkSubmissionOutcome.clientSurname) &&
        Objects.equals(this.clientDateOfBirth, bulkSubmissionOutcome.clientDateOfBirth) &&
        Objects.equals(this.ucn, bulkSubmissionOutcome.ucn) &&
        Objects.equals(this.claRefNumber, bulkSubmissionOutcome.claRefNumber) &&
        Objects.equals(this.claExemption, bulkSubmissionOutcome.claExemption) &&
        Objects.equals(this.gender, bulkSubmissionOutcome.gender) &&
        Objects.equals(this.ethnicity, bulkSubmissionOutcome.ethnicity) &&
        Objects.equals(this.disability, bulkSubmissionOutcome.disability) &&
        Objects.equals(this.clientPostCode, bulkSubmissionOutcome.clientPostCode) &&
        Objects.equals(this.workConcludedDate, bulkSubmissionOutcome.workConcludedDate) &&
        Objects.equals(this.adviceTime, bulkSubmissionOutcome.adviceTime) &&
        Objects.equals(this.travelTime, bulkSubmissionOutcome.travelTime) &&
        Objects.equals(this.waitingTime, bulkSubmissionOutcome.waitingTime) &&
        Objects.equals(this.profitCost, bulkSubmissionOutcome.profitCost) &&
        Objects.equals(this.valueOfCosts, bulkSubmissionOutcome.valueOfCosts) &&
        Objects.equals(this.disbursementsAmount, bulkSubmissionOutcome.disbursementsAmount) &&
        Objects.equals(this.counselCost, bulkSubmissionOutcome.counselCost) &&
        Objects.equals(this.disbursementsVat, bulkSubmissionOutcome.disbursementsVat) &&
        Objects.equals(this.travelWaitingCosts, bulkSubmissionOutcome.travelWaitingCosts) &&
        Objects.equals(this.vatIndicator, bulkSubmissionOutcome.vatIndicator) &&
        Objects.equals(this.londonNonlondonRate, bulkSubmissionOutcome.londonNonlondonRate) &&
        Objects.equals(this.clientType, bulkSubmissionOutcome.clientType) &&
        Objects.equals(this.toleranceIndicator, bulkSubmissionOutcome.toleranceIndicator) &&
        Objects.equals(this.travelCosts, bulkSubmissionOutcome.travelCosts) &&
        Objects.equals(this.outcomeCode, bulkSubmissionOutcome.outcomeCode) &&
        Objects.equals(this.legacyCase, bulkSubmissionOutcome.legacyCase) &&
        Objects.equals(this.claimType, bulkSubmissionOutcome.claimType) &&
        Objects.equals(this.adjournedHearingFee, bulkSubmissionOutcome.adjournedHearingFee) &&
        Objects.equals(this.typeOfAdvice, bulkSubmissionOutcome.typeOfAdvice) &&
        Objects.equals(this.postalApplAccp, bulkSubmissionOutcome.postalApplAccp) &&
        Objects.equals(this.scheduleRef, bulkSubmissionOutcome.scheduleRef) &&
        Objects.equals(this.cmrhOral, bulkSubmissionOutcome.cmrhOral) &&
        Objects.equals(this.cmrhTelephone, bulkSubmissionOutcome.cmrhTelephone) &&
        Objects.equals(this.aitHearingCentre, bulkSubmissionOutcome.aitHearingCentre) &&
        Objects.equals(this.substantiveHearing, bulkSubmissionOutcome.substantiveHearing) &&
        Objects.equals(this.hoInterview, bulkSubmissionOutcome.hoInterview) &&
        Objects.equals(this.hoUcn, bulkSubmissionOutcome.hoUcn) &&
        Objects.equals(this.transferDate, bulkSubmissionOutcome.transferDate) &&
        Objects.equals(this.detentionTravelWaitingCosts, bulkSubmissionOutcome.detentionTravelWaitingCosts) &&
        Objects.equals(this.deliveryLocation, bulkSubmissionOutcome.deliveryLocation) &&
        Objects.equals(this.priorAuthorityRef, bulkSubmissionOutcome.priorAuthorityRef) &&
        Objects.equals(this.jrFormFilling, bulkSubmissionOutcome.jrFormFilling) &&
        Objects.equals(this.additionalTravelPayment, bulkSubmissionOutcome.additionalTravelPayment) &&
        Objects.equals(this.meetingsAttended, bulkSubmissionOutcome.meetingsAttended) &&
        Objects.equals(this.medicalReportsClaimed, bulkSubmissionOutcome.medicalReportsClaimed) &&
        Objects.equals(this.desiAccRep, bulkSubmissionOutcome.desiAccRep) &&
        Objects.equals(this.mhtRefNumber, bulkSubmissionOutcome.mhtRefNumber) &&
        Objects.equals(this.stageReached, bulkSubmissionOutcome.stageReached) &&
        Objects.equals(this.followOnWork, bulkSubmissionOutcome.followOnWork) &&
        Objects.equals(this.nationalRefMechanismAdvice, bulkSubmissionOutcome.nationalRefMechanismAdvice) &&
        Objects.equals(this.exemptionCriteriaSatisfied, bulkSubmissionOutcome.exemptionCriteriaSatisfied) &&
        Objects.equals(this.exclCaseFundingRef, bulkSubmissionOutcome.exclCaseFundingRef) &&
        Objects.equals(this.noOfClients, bulkSubmissionOutcome.noOfClients) &&
        Objects.equals(this.noOfSurgeryClients, bulkSubmissionOutcome.noOfSurgeryClients) &&
        Objects.equals(this.ircSurgery, bulkSubmissionOutcome.ircSurgery) &&
        Objects.equals(this.surgeryDate, bulkSubmissionOutcome.surgeryDate) &&
        Objects.equals(this.lineNumber, bulkSubmissionOutcome.lineNumber) &&
        Objects.equals(this.crimeMatterType, bulkSubmissionOutcome.crimeMatterType) &&
        Objects.equals(this.feeScheme, bulkSubmissionOutcome.feeScheme) &&
        Objects.equals(this.repOrderDate, bulkSubmissionOutcome.repOrderDate) &&
        Objects.equals(this.noOfSuspects, bulkSubmissionOutcome.noOfSuspects) &&
        Objects.equals(this.noOfPoliceStation, bulkSubmissionOutcome.noOfPoliceStation) &&
        Objects.equals(this.policeStation, bulkSubmissionOutcome.policeStation) &&
        Objects.equals(this.dsccNumber, bulkSubmissionOutcome.dsccNumber) &&
        Objects.equals(this.maatId, bulkSubmissionOutcome.maatId) &&
        Objects.equals(this.dutySolicitor, bulkSubmissionOutcome.dutySolicitor) &&
        Objects.equals(this.youthCourt, bulkSubmissionOutcome.youthCourt) &&
        Objects.equals(this.schemeId, bulkSubmissionOutcome.schemeId) &&
        Objects.equals(this.numberOfMediationSessions, bulkSubmissionOutcome.numberOfMediationSessions) &&
        Objects.equals(this.mediationTime, bulkSubmissionOutcome.mediationTime) &&
        Objects.equals(this.outreach, bulkSubmissionOutcome.outreach) &&
        Objects.equals(this.referral, bulkSubmissionOutcome.referral) &&
        Objects.equals(this.clientLegallyAided, bulkSubmissionOutcome.clientLegallyAided) &&
        Objects.equals(this.client2Forename, bulkSubmissionOutcome.client2Forename) &&
        Objects.equals(this.client2Surname, bulkSubmissionOutcome.client2Surname) &&
        Objects.equals(this.client2DateOfBirth, bulkSubmissionOutcome.client2DateOfBirth) &&
        Objects.equals(this.client2Ucn, bulkSubmissionOutcome.client2Ucn) &&
        Objects.equals(this.client2PostCode, bulkSubmissionOutcome.client2PostCode) &&
        Objects.equals(this.client2Gender, bulkSubmissionOutcome.client2Gender) &&
        Objects.equals(this.client2Ethnicity, bulkSubmissionOutcome.client2Ethnicity) &&
        Objects.equals(this.client2Disability, bulkSubmissionOutcome.client2Disability) &&
        Objects.equals(this.client2LegallyAided, bulkSubmissionOutcome.client2LegallyAided) &&
        Objects.equals(this.uniqueCaseId, bulkSubmissionOutcome.uniqueCaseId) &&
        Objects.equals(this.standardFeeCat, bulkSubmissionOutcome.standardFeeCat) &&
        Objects.equals(this.client2PostalApplAccp, bulkSubmissionOutcome.client2PostalApplAccp) &&
        Objects.equals(this.costsDamagesRecovered, bulkSubmissionOutcome.costsDamagesRecovered) &&
        Objects.equals(this.eligibleClient, bulkSubmissionOutcome.eligibleClient) &&
        Objects.equals(this.courtLocationHpcds, bulkSubmissionOutcome.courtLocationHpcds) &&
        Objects.equals(this.localAuthorityNumber, bulkSubmissionOutcome.localAuthorityNumber) &&
        Objects.equals(this.paNumber, bulkSubmissionOutcome.paNumber) &&
        Objects.equals(this.excessTravelCosts, bulkSubmissionOutcome.excessTravelCosts) &&
        Objects.equals(this.medConcludedDate, bulkSubmissionOutcome.medConcludedDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(matterType, feeCode, caseRefNumber, caseStartDate, caseId, caseStageLevel, ufn, procurementArea, accessPoint, clientForename, clientSurname, clientDateOfBirth, ucn, claRefNumber, claExemption, gender, ethnicity, disability, clientPostCode, workConcludedDate, adviceTime, travelTime, waitingTime, profitCost, valueOfCosts, disbursementsAmount, counselCost, disbursementsVat, travelWaitingCosts, vatIndicator, londonNonlondonRate, clientType, toleranceIndicator, travelCosts, outcomeCode, legacyCase, claimType, adjournedHearingFee, typeOfAdvice, postalApplAccp, scheduleRef, cmrhOral, cmrhTelephone, aitHearingCentre, substantiveHearing, hoInterview, hoUcn, transferDate, detentionTravelWaitingCosts, deliveryLocation, priorAuthorityRef, jrFormFilling, additionalTravelPayment, meetingsAttended, medicalReportsClaimed, desiAccRep, mhtRefNumber, stageReached, followOnWork, nationalRefMechanismAdvice, exemptionCriteriaSatisfied, exclCaseFundingRef, noOfClients, noOfSurgeryClients, ircSurgery, surgeryDate, lineNumber, crimeMatterType, feeScheme, repOrderDate, noOfSuspects, noOfPoliceStation, policeStation, dsccNumber, maatId, dutySolicitor, youthCourt, schemeId, numberOfMediationSessions, mediationTime, outreach, referral, clientLegallyAided, client2Forename, client2Surname, client2DateOfBirth, client2Ucn, client2PostCode, client2Gender, client2Ethnicity, client2Disability, client2LegallyAided, uniqueCaseId, standardFeeCat, client2PostalApplAccp, costsDamagesRecovered, eligibleClient, courtLocationHpcds, localAuthorityNumber, paNumber, excessTravelCosts, medConcludedDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BulkSubmissionOutcome {\n");
    sb.append("    matterType: ").append(toIndentedString(matterType)).append("\n");
    sb.append("    feeCode: ").append(toIndentedString(feeCode)).append("\n");
    sb.append("    caseRefNumber: ").append(toIndentedString(caseRefNumber)).append("\n");
    sb.append("    caseStartDate: ").append(toIndentedString(caseStartDate)).append("\n");
    sb.append("    caseId: ").append(toIndentedString(caseId)).append("\n");
    sb.append("    caseStageLevel: ").append(toIndentedString(caseStageLevel)).append("\n");
    sb.append("    ufn: ").append(toIndentedString(ufn)).append("\n");
    sb.append("    procurementArea: ").append(toIndentedString(procurementArea)).append("\n");
    sb.append("    accessPoint: ").append(toIndentedString(accessPoint)).append("\n");
    sb.append("    clientForename: ").append(toIndentedString(clientForename)).append("\n");
    sb.append("    clientSurname: ").append(toIndentedString(clientSurname)).append("\n");
    sb.append("    clientDateOfBirth: ").append(toIndentedString(clientDateOfBirth)).append("\n");
    sb.append("    ucn: ").append(toIndentedString(ucn)).append("\n");
    sb.append("    claRefNumber: ").append(toIndentedString(claRefNumber)).append("\n");
    sb.append("    claExemption: ").append(toIndentedString(claExemption)).append("\n");
    sb.append("    gender: ").append(toIndentedString(gender)).append("\n");
    sb.append("    ethnicity: ").append(toIndentedString(ethnicity)).append("\n");
    sb.append("    disability: ").append(toIndentedString(disability)).append("\n");
    sb.append("    clientPostCode: ").append(toIndentedString(clientPostCode)).append("\n");
    sb.append("    workConcludedDate: ").append(toIndentedString(workConcludedDate)).append("\n");
    sb.append("    adviceTime: ").append(toIndentedString(adviceTime)).append("\n");
    sb.append("    travelTime: ").append(toIndentedString(travelTime)).append("\n");
    sb.append("    waitingTime: ").append(toIndentedString(waitingTime)).append("\n");
    sb.append("    profitCost: ").append(toIndentedString(profitCost)).append("\n");
    sb.append("    valueOfCosts: ").append(toIndentedString(valueOfCosts)).append("\n");
    sb.append("    disbursementsAmount: ").append(toIndentedString(disbursementsAmount)).append("\n");
    sb.append("    counselCost: ").append(toIndentedString(counselCost)).append("\n");
    sb.append("    disbursementsVat: ").append(toIndentedString(disbursementsVat)).append("\n");
    sb.append("    travelWaitingCosts: ").append(toIndentedString(travelWaitingCosts)).append("\n");
    sb.append("    vatIndicator: ").append(toIndentedString(vatIndicator)).append("\n");
    sb.append("    londonNonlondonRate: ").append(toIndentedString(londonNonlondonRate)).append("\n");
    sb.append("    clientType: ").append(toIndentedString(clientType)).append("\n");
    sb.append("    toleranceIndicator: ").append(toIndentedString(toleranceIndicator)).append("\n");
    sb.append("    travelCosts: ").append(toIndentedString(travelCosts)).append("\n");
    sb.append("    outcomeCode: ").append(toIndentedString(outcomeCode)).append("\n");
    sb.append("    legacyCase: ").append(toIndentedString(legacyCase)).append("\n");
    sb.append("    claimType: ").append(toIndentedString(claimType)).append("\n");
    sb.append("    adjournedHearingFee: ").append(toIndentedString(adjournedHearingFee)).append("\n");
    sb.append("    typeOfAdvice: ").append(toIndentedString(typeOfAdvice)).append("\n");
    sb.append("    postalApplAccp: ").append(toIndentedString(postalApplAccp)).append("\n");
    sb.append("    scheduleRef: ").append(toIndentedString(scheduleRef)).append("\n");
    sb.append("    cmrhOral: ").append(toIndentedString(cmrhOral)).append("\n");
    sb.append("    cmrhTelephone: ").append(toIndentedString(cmrhTelephone)).append("\n");
    sb.append("    aitHearingCentre: ").append(toIndentedString(aitHearingCentre)).append("\n");
    sb.append("    substantiveHearing: ").append(toIndentedString(substantiveHearing)).append("\n");
    sb.append("    hoInterview: ").append(toIndentedString(hoInterview)).append("\n");
    sb.append("    hoUcn: ").append(toIndentedString(hoUcn)).append("\n");
    sb.append("    transferDate: ").append(toIndentedString(transferDate)).append("\n");
    sb.append("    detentionTravelWaitingCosts: ").append(toIndentedString(detentionTravelWaitingCosts)).append("\n");
    sb.append("    deliveryLocation: ").append(toIndentedString(deliveryLocation)).append("\n");
    sb.append("    priorAuthorityRef: ").append(toIndentedString(priorAuthorityRef)).append("\n");
    sb.append("    jrFormFilling: ").append(toIndentedString(jrFormFilling)).append("\n");
    sb.append("    additionalTravelPayment: ").append(toIndentedString(additionalTravelPayment)).append("\n");
    sb.append("    meetingsAttended: ").append(toIndentedString(meetingsAttended)).append("\n");
    sb.append("    medicalReportsClaimed: ").append(toIndentedString(medicalReportsClaimed)).append("\n");
    sb.append("    desiAccRep: ").append(toIndentedString(desiAccRep)).append("\n");
    sb.append("    mhtRefNumber: ").append(toIndentedString(mhtRefNumber)).append("\n");
    sb.append("    stageReached: ").append(toIndentedString(stageReached)).append("\n");
    sb.append("    followOnWork: ").append(toIndentedString(followOnWork)).append("\n");
    sb.append("    nationalRefMechanismAdvice: ").append(toIndentedString(nationalRefMechanismAdvice)).append("\n");
    sb.append("    exemptionCriteriaSatisfied: ").append(toIndentedString(exemptionCriteriaSatisfied)).append("\n");
    sb.append("    exclCaseFundingRef: ").append(toIndentedString(exclCaseFundingRef)).append("\n");
    sb.append("    noOfClients: ").append(toIndentedString(noOfClients)).append("\n");
    sb.append("    noOfSurgeryClients: ").append(toIndentedString(noOfSurgeryClients)).append("\n");
    sb.append("    ircSurgery: ").append(toIndentedString(ircSurgery)).append("\n");
    sb.append("    surgeryDate: ").append(toIndentedString(surgeryDate)).append("\n");
    sb.append("    lineNumber: ").append(toIndentedString(lineNumber)).append("\n");
    sb.append("    crimeMatterType: ").append(toIndentedString(crimeMatterType)).append("\n");
    sb.append("    feeScheme: ").append(toIndentedString(feeScheme)).append("\n");
    sb.append("    repOrderDate: ").append(toIndentedString(repOrderDate)).append("\n");
    sb.append("    noOfSuspects: ").append(toIndentedString(noOfSuspects)).append("\n");
    sb.append("    noOfPoliceStation: ").append(toIndentedString(noOfPoliceStation)).append("\n");
    sb.append("    policeStation: ").append(toIndentedString(policeStation)).append("\n");
    sb.append("    dsccNumber: ").append(toIndentedString(dsccNumber)).append("\n");
    sb.append("    maatId: ").append(toIndentedString(maatId)).append("\n");
    sb.append("    dutySolicitor: ").append(toIndentedString(dutySolicitor)).append("\n");
    sb.append("    youthCourt: ").append(toIndentedString(youthCourt)).append("\n");
    sb.append("    schemeId: ").append(toIndentedString(schemeId)).append("\n");
    sb.append("    numberOfMediationSessions: ").append(toIndentedString(numberOfMediationSessions)).append("\n");
    sb.append("    mediationTime: ").append(toIndentedString(mediationTime)).append("\n");
    sb.append("    outreach: ").append(toIndentedString(outreach)).append("\n");
    sb.append("    referral: ").append(toIndentedString(referral)).append("\n");
    sb.append("    clientLegallyAided: ").append(toIndentedString(clientLegallyAided)).append("\n");
    sb.append("    client2Forename: ").append(toIndentedString(client2Forename)).append("\n");
    sb.append("    client2Surname: ").append(toIndentedString(client2Surname)).append("\n");
    sb.append("    client2DateOfBirth: ").append(toIndentedString(client2DateOfBirth)).append("\n");
    sb.append("    client2Ucn: ").append(toIndentedString(client2Ucn)).append("\n");
    sb.append("    client2PostCode: ").append(toIndentedString(client2PostCode)).append("\n");
    sb.append("    client2Gender: ").append(toIndentedString(client2Gender)).append("\n");
    sb.append("    client2Ethnicity: ").append(toIndentedString(client2Ethnicity)).append("\n");
    sb.append("    client2Disability: ").append(toIndentedString(client2Disability)).append("\n");
    sb.append("    client2LegallyAided: ").append(toIndentedString(client2LegallyAided)).append("\n");
    sb.append("    uniqueCaseId: ").append(toIndentedString(uniqueCaseId)).append("\n");
    sb.append("    standardFeeCat: ").append(toIndentedString(standardFeeCat)).append("\n");
    sb.append("    client2PostalApplAccp: ").append(toIndentedString(client2PostalApplAccp)).append("\n");
    sb.append("    costsDamagesRecovered: ").append(toIndentedString(costsDamagesRecovered)).append("\n");
    sb.append("    eligibleClient: ").append(toIndentedString(eligibleClient)).append("\n");
    sb.append("    courtLocationHpcds: ").append(toIndentedString(courtLocationHpcds)).append("\n");
    sb.append("    localAuthorityNumber: ").append(toIndentedString(localAuthorityNumber)).append("\n");
    sb.append("    paNumber: ").append(toIndentedString(paNumber)).append("\n");
    sb.append("    excessTravelCosts: ").append(toIndentedString(excessTravelCosts)).append("\n");
    sb.append("    medConcludedDate: ").append(toIndentedString(medConcludedDate)).append("\n");
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

    private BulkSubmissionOutcome instance;

    public Builder() {
      this(new BulkSubmissionOutcome());
    }

    protected Builder(BulkSubmissionOutcome instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BulkSubmissionOutcome value) { 
      this.instance.setMatterType(value.matterType);
      this.instance.setFeeCode(value.feeCode);
      this.instance.setCaseRefNumber(value.caseRefNumber);
      this.instance.setCaseStartDate(value.caseStartDate);
      this.instance.setCaseId(value.caseId);
      this.instance.setCaseStageLevel(value.caseStageLevel);
      this.instance.setUfn(value.ufn);
      this.instance.setProcurementArea(value.procurementArea);
      this.instance.setAccessPoint(value.accessPoint);
      this.instance.setClientForename(value.clientForename);
      this.instance.setClientSurname(value.clientSurname);
      this.instance.setClientDateOfBirth(value.clientDateOfBirth);
      this.instance.setUcn(value.ucn);
      this.instance.setClaRefNumber(value.claRefNumber);
      this.instance.setClaExemption(value.claExemption);
      this.instance.setGender(value.gender);
      this.instance.setEthnicity(value.ethnicity);
      this.instance.setDisability(value.disability);
      this.instance.setClientPostCode(value.clientPostCode);
      this.instance.setWorkConcludedDate(value.workConcludedDate);
      this.instance.setAdviceTime(value.adviceTime);
      this.instance.setTravelTime(value.travelTime);
      this.instance.setWaitingTime(value.waitingTime);
      this.instance.setProfitCost(value.profitCost);
      this.instance.setValueOfCosts(value.valueOfCosts);
      this.instance.setDisbursementsAmount(value.disbursementsAmount);
      this.instance.setCounselCost(value.counselCost);
      this.instance.setDisbursementsVat(value.disbursementsVat);
      this.instance.setTravelWaitingCosts(value.travelWaitingCosts);
      this.instance.setVatIndicator(value.vatIndicator);
      this.instance.setLondonNonlondonRate(value.londonNonlondonRate);
      this.instance.setClientType(value.clientType);
      this.instance.setToleranceIndicator(value.toleranceIndicator);
      this.instance.setTravelCosts(value.travelCosts);
      this.instance.setOutcomeCode(value.outcomeCode);
      this.instance.setLegacyCase(value.legacyCase);
      this.instance.setClaimType(value.claimType);
      this.instance.setAdjournedHearingFee(value.adjournedHearingFee);
      this.instance.setTypeOfAdvice(value.typeOfAdvice);
      this.instance.setPostalApplAccp(value.postalApplAccp);
      this.instance.setScheduleRef(value.scheduleRef);
      this.instance.setCmrhOral(value.cmrhOral);
      this.instance.setCmrhTelephone(value.cmrhTelephone);
      this.instance.setAitHearingCentre(value.aitHearingCentre);
      this.instance.setSubstantiveHearing(value.substantiveHearing);
      this.instance.setHoInterview(value.hoInterview);
      this.instance.setHoUcn(value.hoUcn);
      this.instance.setTransferDate(value.transferDate);
      this.instance.setDetentionTravelWaitingCosts(value.detentionTravelWaitingCosts);
      this.instance.setDeliveryLocation(value.deliveryLocation);
      this.instance.setPriorAuthorityRef(value.priorAuthorityRef);
      this.instance.setJrFormFilling(value.jrFormFilling);
      this.instance.setAdditionalTravelPayment(value.additionalTravelPayment);
      this.instance.setMeetingsAttended(value.meetingsAttended);
      this.instance.setMedicalReportsClaimed(value.medicalReportsClaimed);
      this.instance.setDesiAccRep(value.desiAccRep);
      this.instance.setMhtRefNumber(value.mhtRefNumber);
      this.instance.setStageReached(value.stageReached);
      this.instance.setFollowOnWork(value.followOnWork);
      this.instance.setNationalRefMechanismAdvice(value.nationalRefMechanismAdvice);
      this.instance.setExemptionCriteriaSatisfied(value.exemptionCriteriaSatisfied);
      this.instance.setExclCaseFundingRef(value.exclCaseFundingRef);
      this.instance.setNoOfClients(value.noOfClients);
      this.instance.setNoOfSurgeryClients(value.noOfSurgeryClients);
      this.instance.setIrcSurgery(value.ircSurgery);
      this.instance.setSurgeryDate(value.surgeryDate);
      this.instance.setLineNumber(value.lineNumber);
      this.instance.setCrimeMatterType(value.crimeMatterType);
      this.instance.setFeeScheme(value.feeScheme);
      this.instance.setRepOrderDate(value.repOrderDate);
      this.instance.setNoOfSuspects(value.noOfSuspects);
      this.instance.setNoOfPoliceStation(value.noOfPoliceStation);
      this.instance.setPoliceStation(value.policeStation);
      this.instance.setDsccNumber(value.dsccNumber);
      this.instance.setMaatId(value.maatId);
      this.instance.setDutySolicitor(value.dutySolicitor);
      this.instance.setYouthCourt(value.youthCourt);
      this.instance.setSchemeId(value.schemeId);
      this.instance.setNumberOfMediationSessions(value.numberOfMediationSessions);
      this.instance.setMediationTime(value.mediationTime);
      this.instance.setOutreach(value.outreach);
      this.instance.setReferral(value.referral);
      this.instance.setClientLegallyAided(value.clientLegallyAided);
      this.instance.setClient2Forename(value.client2Forename);
      this.instance.setClient2Surname(value.client2Surname);
      this.instance.setClient2DateOfBirth(value.client2DateOfBirth);
      this.instance.setClient2Ucn(value.client2Ucn);
      this.instance.setClient2PostCode(value.client2PostCode);
      this.instance.setClient2Gender(value.client2Gender);
      this.instance.setClient2Ethnicity(value.client2Ethnicity);
      this.instance.setClient2Disability(value.client2Disability);
      this.instance.setClient2LegallyAided(value.client2LegallyAided);
      this.instance.setUniqueCaseId(value.uniqueCaseId);
      this.instance.setStandardFeeCat(value.standardFeeCat);
      this.instance.setClient2PostalApplAccp(value.client2PostalApplAccp);
      this.instance.setCostsDamagesRecovered(value.costsDamagesRecovered);
      this.instance.setEligibleClient(value.eligibleClient);
      this.instance.setCourtLocationHpcds(value.courtLocationHpcds);
      this.instance.setLocalAuthorityNumber(value.localAuthorityNumber);
      this.instance.setPaNumber(value.paNumber);
      this.instance.setExcessTravelCosts(value.excessTravelCosts);
      this.instance.setMedConcludedDate(value.medConcludedDate);
      return this;
    }

    public BulkSubmissionOutcome.Builder matterType(String matterType) {
      this.instance.matterType(matterType);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder feeCode(String feeCode) {
      this.instance.feeCode(feeCode);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder caseRefNumber(String caseRefNumber) {
      this.instance.caseRefNumber(caseRefNumber);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder caseStartDate(String caseStartDate) {
      this.instance.caseStartDate(caseStartDate);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder caseId(String caseId) {
      this.instance.caseId(caseId);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder caseStageLevel(String caseStageLevel) {
      this.instance.caseStageLevel(caseStageLevel);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder ufn(String ufn) {
      this.instance.ufn(ufn);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder procurementArea(String procurementArea) {
      this.instance.procurementArea(procurementArea);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder accessPoint(String accessPoint) {
      this.instance.accessPoint(accessPoint);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder clientForename(String clientForename) {
      this.instance.clientForename(clientForename);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder clientSurname(String clientSurname) {
      this.instance.clientSurname(clientSurname);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder clientDateOfBirth(String clientDateOfBirth) {
      this.instance.clientDateOfBirth(clientDateOfBirth);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder ucn(String ucn) {
      this.instance.ucn(ucn);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder claRefNumber(String claRefNumber) {
      this.instance.claRefNumber(claRefNumber);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder claExemption(String claExemption) {
      this.instance.claExemption(claExemption);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder gender(String gender) {
      this.instance.gender(gender);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder ethnicity(String ethnicity) {
      this.instance.ethnicity(ethnicity);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder disability(String disability) {
      this.instance.disability(disability);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder clientPostCode(String clientPostCode) {
      this.instance.clientPostCode(clientPostCode);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder workConcludedDate(String workConcludedDate) {
      this.instance.workConcludedDate(workConcludedDate);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder adviceTime(Integer adviceTime) {
      this.instance.adviceTime(adviceTime);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder travelTime(Integer travelTime) {
      this.instance.travelTime(travelTime);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder waitingTime(Integer waitingTime) {
      this.instance.waitingTime(waitingTime);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder profitCost(BigDecimal profitCost) {
      this.instance.profitCost(profitCost);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder valueOfCosts(BigDecimal valueOfCosts) {
      this.instance.valueOfCosts(valueOfCosts);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder disbursementsAmount(BigDecimal disbursementsAmount) {
      this.instance.disbursementsAmount(disbursementsAmount);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder counselCost(BigDecimal counselCost) {
      this.instance.counselCost(counselCost);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder disbursementsVat(BigDecimal disbursementsVat) {
      this.instance.disbursementsVat(disbursementsVat);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder travelWaitingCosts(BigDecimal travelWaitingCosts) {
      this.instance.travelWaitingCosts(travelWaitingCosts);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder vatIndicator(Boolean vatIndicator) {
      this.instance.vatIndicator(vatIndicator);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder londonNonlondonRate(Boolean londonNonlondonRate) {
      this.instance.londonNonlondonRate(londonNonlondonRate);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder clientType(String clientType) {
      this.instance.clientType(clientType);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder toleranceIndicator(Boolean toleranceIndicator) {
      this.instance.toleranceIndicator(toleranceIndicator);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder travelCosts(BigDecimal travelCosts) {
      this.instance.travelCosts(travelCosts);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder outcomeCode(String outcomeCode) {
      this.instance.outcomeCode(outcomeCode);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder legacyCase(Boolean legacyCase) {
      this.instance.legacyCase(legacyCase);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder claimType(String claimType) {
      this.instance.claimType(claimType);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder adjournedHearingFee(Integer adjournedHearingFee) {
      this.instance.adjournedHearingFee(adjournedHearingFee);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder typeOfAdvice(String typeOfAdvice) {
      this.instance.typeOfAdvice(typeOfAdvice);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder postalApplAccp(Boolean postalApplAccp) {
      this.instance.postalApplAccp(postalApplAccp);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder scheduleRef(String scheduleRef) {
      this.instance.scheduleRef(scheduleRef);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder cmrhOral(String cmrhOral) {
      this.instance.cmrhOral(cmrhOral);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder cmrhTelephone(String cmrhTelephone) {
      this.instance.cmrhTelephone(cmrhTelephone);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder aitHearingCentre(String aitHearingCentre) {
      this.instance.aitHearingCentre(aitHearingCentre);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder substantiveHearing(Boolean substantiveHearing) {
      this.instance.substantiveHearing(substantiveHearing);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder hoInterview(Integer hoInterview) {
      this.instance.hoInterview(hoInterview);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder hoUcn(String hoUcn) {
      this.instance.hoUcn(hoUcn);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder transferDate(String transferDate) {
      this.instance.transferDate(transferDate);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder detentionTravelWaitingCosts(BigDecimal detentionTravelWaitingCosts) {
      this.instance.detentionTravelWaitingCosts(detentionTravelWaitingCosts);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder deliveryLocation(String deliveryLocation) {
      this.instance.deliveryLocation(deliveryLocation);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder priorAuthorityRef(String priorAuthorityRef) {
      this.instance.priorAuthorityRef(priorAuthorityRef);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder jrFormFilling(BigDecimal jrFormFilling) {
      this.instance.jrFormFilling(jrFormFilling);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder additionalTravelPayment(Boolean additionalTravelPayment) {
      this.instance.additionalTravelPayment(additionalTravelPayment);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder meetingsAttended(String meetingsAttended) {
      this.instance.meetingsAttended(meetingsAttended);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder medicalReportsClaimed(Integer medicalReportsClaimed) {
      this.instance.medicalReportsClaimed(medicalReportsClaimed);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder desiAccRep(Integer desiAccRep) {
      this.instance.desiAccRep(desiAccRep);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder mhtRefNumber(String mhtRefNumber) {
      this.instance.mhtRefNumber(mhtRefNumber);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder stageReached(String stageReached) {
      this.instance.stageReached(stageReached);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder followOnWork(String followOnWork) {
      this.instance.followOnWork(followOnWork);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder nationalRefMechanismAdvice(Boolean nationalRefMechanismAdvice) {
      this.instance.nationalRefMechanismAdvice(nationalRefMechanismAdvice);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder exemptionCriteriaSatisfied(String exemptionCriteriaSatisfied) {
      this.instance.exemptionCriteriaSatisfied(exemptionCriteriaSatisfied);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder exclCaseFundingRef(String exclCaseFundingRef) {
      this.instance.exclCaseFundingRef(exclCaseFundingRef);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder noOfClients(Integer noOfClients) {
      this.instance.noOfClients(noOfClients);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder noOfSurgeryClients(Integer noOfSurgeryClients) {
      this.instance.noOfSurgeryClients(noOfSurgeryClients);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder ircSurgery(Boolean ircSurgery) {
      this.instance.ircSurgery(ircSurgery);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder surgeryDate(String surgeryDate) {
      this.instance.surgeryDate(surgeryDate);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder lineNumber(String lineNumber) {
      this.instance.lineNumber(lineNumber);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder crimeMatterType(String crimeMatterType) {
      this.instance.crimeMatterType(crimeMatterType);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder feeScheme(String feeScheme) {
      this.instance.feeScheme(feeScheme);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder repOrderDate(String repOrderDate) {
      this.instance.repOrderDate(repOrderDate);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder noOfSuspects(Integer noOfSuspects) {
      this.instance.noOfSuspects(noOfSuspects);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder noOfPoliceStation(Integer noOfPoliceStation) {
      this.instance.noOfPoliceStation(noOfPoliceStation);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder policeStation(String policeStation) {
      this.instance.policeStation(policeStation);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder dsccNumber(String dsccNumber) {
      this.instance.dsccNumber(dsccNumber);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder maatId(String maatId) {
      this.instance.maatId(maatId);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder dutySolicitor(Boolean dutySolicitor) {
      this.instance.dutySolicitor(dutySolicitor);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder youthCourt(Boolean youthCourt) {
      this.instance.youthCourt(youthCourt);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder schemeId(String schemeId) {
      this.instance.schemeId(schemeId);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder numberOfMediationSessions(Integer numberOfMediationSessions) {
      this.instance.numberOfMediationSessions(numberOfMediationSessions);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder mediationTime(Integer mediationTime) {
      this.instance.mediationTime(mediationTime);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder outreach(String outreach) {
      this.instance.outreach(outreach);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder referral(String referral) {
      this.instance.referral(referral);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder clientLegallyAided(Boolean clientLegallyAided) {
      this.instance.clientLegallyAided(clientLegallyAided);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder client2Forename(String client2Forename) {
      this.instance.client2Forename(client2Forename);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder client2Surname(String client2Surname) {
      this.instance.client2Surname(client2Surname);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder client2DateOfBirth(String client2DateOfBirth) {
      this.instance.client2DateOfBirth(client2DateOfBirth);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder client2Ucn(String client2Ucn) {
      this.instance.client2Ucn(client2Ucn);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder client2PostCode(String client2PostCode) {
      this.instance.client2PostCode(client2PostCode);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder client2Gender(String client2Gender) {
      this.instance.client2Gender(client2Gender);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder client2Ethnicity(String client2Ethnicity) {
      this.instance.client2Ethnicity(client2Ethnicity);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder client2Disability(String client2Disability) {
      this.instance.client2Disability(client2Disability);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder client2LegallyAided(Boolean client2LegallyAided) {
      this.instance.client2LegallyAided(client2LegallyAided);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder uniqueCaseId(String uniqueCaseId) {
      this.instance.uniqueCaseId(uniqueCaseId);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder standardFeeCat(String standardFeeCat) {
      this.instance.standardFeeCat(standardFeeCat);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder client2PostalApplAccp(Boolean client2PostalApplAccp) {
      this.instance.client2PostalApplAccp(client2PostalApplAccp);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder costsDamagesRecovered(BigDecimal costsDamagesRecovered) {
      this.instance.costsDamagesRecovered(costsDamagesRecovered);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder eligibleClient(Boolean eligibleClient) {
      this.instance.eligibleClient(eligibleClient);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder courtLocationHpcds(String courtLocationHpcds) {
      this.instance.courtLocationHpcds(courtLocationHpcds);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder localAuthorityNumber(String localAuthorityNumber) {
      this.instance.localAuthorityNumber(localAuthorityNumber);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder paNumber(String paNumber) {
      this.instance.paNumber(paNumber);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder excessTravelCosts(BigDecimal excessTravelCosts) {
      this.instance.excessTravelCosts(excessTravelCosts);
      return this;
    }
    
    public BulkSubmissionOutcome.Builder medConcludedDate(String medConcludedDate) {
      this.instance.medConcludedDate(medConcludedDate);
      return this;
    }
    
    /**
    * returns a built BulkSubmissionOutcome instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BulkSubmissionOutcome build() {
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
  public static BulkSubmissionOutcome.Builder builder() {
    return new BulkSubmissionOutcome.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BulkSubmissionOutcome.Builder toBuilder() {
    BulkSubmissionOutcome.Builder builder = new BulkSubmissionOutcome.Builder();
    return builder.copyOf(this);
  }

}

