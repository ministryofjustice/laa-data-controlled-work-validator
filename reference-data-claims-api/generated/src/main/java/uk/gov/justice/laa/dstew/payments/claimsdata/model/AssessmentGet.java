package uk.gov.justice.laa.dstew.payments.claimsdata.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AssessmentOutcome;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AssessmentType;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AssessmentGet
 */

@JsonTypeName("assessment_get")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T18:11:17.854857Z[Europe/London]", comments = "Generator version: 7.14.0")
public class AssessmentGet implements Serializable {

  private static final long serialVersionUID = 1L;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime createdOn;

  private @Nullable UUID id;

  private @Nullable UUID claimId;

  private @Nullable UUID claimSummaryFeeId;

  private @Nullable AssessmentOutcome assessmentOutcome;

  private @Nullable String assessmentReason;

  private @Nullable BigDecimal fixedFeeAmount;

  private @Nullable BigDecimal netProfitCostsAmount;

  private @Nullable BigDecimal disbursementAmount;

  private @Nullable BigDecimal disbursementVatAmount;

  private @Nullable BigDecimal netCostOfCounselAmount;

  private @Nullable BigDecimal netTravelCostsAmount;

  private @Nullable BigDecimal netWaitingCostsAmount;

  private @Nullable BigDecimal detentionTravelAndWaitingCostsAmount;

  private @Nullable BigDecimal jrFormFillingAmount;

  private @Nullable BigDecimal boltOnAdjournedHearingFee;

  private @Nullable BigDecimal boltOnCmrhTelephoneFee;

  private @Nullable BigDecimal boltOnCmrhOralFee;

  private @Nullable BigDecimal boltOnHomeOfficeInterviewFee;

  private @Nullable BigDecimal boltOnSubstantiveHearingFee;

  private @Nullable Boolean isVatApplicable;

  private @Nullable String createdByUserId;

  private @Nullable BigDecimal assessedTotalVat;

  private @Nullable BigDecimal assessedTotalInclVat;

  private @Nullable BigDecimal allowedTotalVat;

  private @Nullable BigDecimal allowedTotalInclVat;

  private @Nullable AssessmentType assessmentType;

  public AssessmentGet createdOn(@Nullable OffsetDateTime createdOn) {
    this.createdOn = createdOn;
    return this;
  }

  /**
   * Date and time the assessment was created
   * @return createdOn
   */
  @Valid 
  @Schema(name = "created_on", description = "Date and time the assessment was created", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("created_on")
  public @Nullable OffsetDateTime getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(@Nullable OffsetDateTime createdOn) {
    this.createdOn = createdOn;
  }

  public AssessmentGet id(@Nullable UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @Valid 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public @Nullable UUID getId() {
    return id;
  }

  public void setId(@Nullable UUID id) {
    this.id = id;
  }

  public AssessmentGet claimId(@Nullable UUID claimId) {
    this.claimId = claimId;
    return this;
  }

  /**
   * Get claimId
   * @return claimId
   */
  @Valid 
  @Schema(name = "claim_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("claim_id")
  public @Nullable UUID getClaimId() {
    return claimId;
  }

  public void setClaimId(@Nullable UUID claimId) {
    this.claimId = claimId;
  }

  public AssessmentGet claimSummaryFeeId(@Nullable UUID claimSummaryFeeId) {
    this.claimSummaryFeeId = claimSummaryFeeId;
    return this;
  }

  /**
   * Get claimSummaryFeeId
   * @return claimSummaryFeeId
   */
  @Valid 
  @Schema(name = "claim_summary_fee_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("claim_summary_fee_id")
  public @Nullable UUID getClaimSummaryFeeId() {
    return claimSummaryFeeId;
  }

  public void setClaimSummaryFeeId(@Nullable UUID claimSummaryFeeId) {
    this.claimSummaryFeeId = claimSummaryFeeId;
  }

  public AssessmentGet assessmentOutcome(@Nullable AssessmentOutcome assessmentOutcome) {
    this.assessmentOutcome = assessmentOutcome;
    return this;
  }

  /**
   * Get assessmentOutcome
   * @return assessmentOutcome
   */
  @Valid 
  @Schema(name = "assessment_outcome", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("assessment_outcome")
  public @Nullable AssessmentOutcome getAssessmentOutcome() {
    return assessmentOutcome;
  }

  public void setAssessmentOutcome(@Nullable AssessmentOutcome assessmentOutcome) {
    this.assessmentOutcome = assessmentOutcome;
  }

  public AssessmentGet assessmentReason(@Nullable String assessmentReason) {
    this.assessmentReason = assessmentReason;
    return this;
  }

  /**
   * Get assessmentReason
   * @return assessmentReason
   */
  
  @Schema(name = "assessment_reason", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("assessment_reason")
  public @Nullable String getAssessmentReason() {
    return assessmentReason;
  }

  public void setAssessmentReason(@Nullable String assessmentReason) {
    this.assessmentReason = assessmentReason;
  }

  public AssessmentGet fixedFeeAmount(@Nullable BigDecimal fixedFeeAmount) {
    this.fixedFeeAmount = fixedFeeAmount;
    return this;
  }

  /**
   * Get fixedFeeAmount
   * @return fixedFeeAmount
   */
  @Valid 
  @Schema(name = "fixed_fee_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fixed_fee_amount")
  public @Nullable BigDecimal getFixedFeeAmount() {
    return fixedFeeAmount;
  }

  public void setFixedFeeAmount(@Nullable BigDecimal fixedFeeAmount) {
    this.fixedFeeAmount = fixedFeeAmount;
  }

  public AssessmentGet netProfitCostsAmount(@Nullable BigDecimal netProfitCostsAmount) {
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

  public AssessmentGet disbursementAmount(@Nullable BigDecimal disbursementAmount) {
    this.disbursementAmount = disbursementAmount;
    return this;
  }

  /**
   * Get disbursementAmount
   * @return disbursementAmount
   */
  @Valid 
  @Schema(name = "disbursement_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("disbursement_amount")
  public @Nullable BigDecimal getDisbursementAmount() {
    return disbursementAmount;
  }

  public void setDisbursementAmount(@Nullable BigDecimal disbursementAmount) {
    this.disbursementAmount = disbursementAmount;
  }

  public AssessmentGet disbursementVatAmount(@Nullable BigDecimal disbursementVatAmount) {
    this.disbursementVatAmount = disbursementVatAmount;
    return this;
  }

  /**
   * Get disbursementVatAmount
   * @return disbursementVatAmount
   */
  @Valid 
  @Schema(name = "disbursement_vat_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("disbursement_vat_amount")
  public @Nullable BigDecimal getDisbursementVatAmount() {
    return disbursementVatAmount;
  }

  public void setDisbursementVatAmount(@Nullable BigDecimal disbursementVatAmount) {
    this.disbursementVatAmount = disbursementVatAmount;
  }

  public AssessmentGet netCostOfCounselAmount(@Nullable BigDecimal netCostOfCounselAmount) {
    this.netCostOfCounselAmount = netCostOfCounselAmount;
    return this;
  }

  /**
   * Get netCostOfCounselAmount
   * @return netCostOfCounselAmount
   */
  @Valid 
  @Schema(name = "net_cost_of_counsel_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("net_cost_of_counsel_amount")
  public @Nullable BigDecimal getNetCostOfCounselAmount() {
    return netCostOfCounselAmount;
  }

  public void setNetCostOfCounselAmount(@Nullable BigDecimal netCostOfCounselAmount) {
    this.netCostOfCounselAmount = netCostOfCounselAmount;
  }

  public AssessmentGet netTravelCostsAmount(@Nullable BigDecimal netTravelCostsAmount) {
    this.netTravelCostsAmount = netTravelCostsAmount;
    return this;
  }

  /**
   * Get netTravelCostsAmount
   * @return netTravelCostsAmount
   */
  @Valid 
  @Schema(name = "net_travel_costs_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("net_travel_costs_amount")
  public @Nullable BigDecimal getNetTravelCostsAmount() {
    return netTravelCostsAmount;
  }

  public void setNetTravelCostsAmount(@Nullable BigDecimal netTravelCostsAmount) {
    this.netTravelCostsAmount = netTravelCostsAmount;
  }

  public AssessmentGet netWaitingCostsAmount(@Nullable BigDecimal netWaitingCostsAmount) {
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

  public AssessmentGet detentionTravelAndWaitingCostsAmount(@Nullable BigDecimal detentionTravelAndWaitingCostsAmount) {
    this.detentionTravelAndWaitingCostsAmount = detentionTravelAndWaitingCostsAmount;
    return this;
  }

  /**
   * Get detentionTravelAndWaitingCostsAmount
   * @return detentionTravelAndWaitingCostsAmount
   */
  @Valid 
  @Schema(name = "detention_travel_and_waiting_costs_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("detention_travel_and_waiting_costs_amount")
  public @Nullable BigDecimal getDetentionTravelAndWaitingCostsAmount() {
    return detentionTravelAndWaitingCostsAmount;
  }

  public void setDetentionTravelAndWaitingCostsAmount(@Nullable BigDecimal detentionTravelAndWaitingCostsAmount) {
    this.detentionTravelAndWaitingCostsAmount = detentionTravelAndWaitingCostsAmount;
  }

  public AssessmentGet jrFormFillingAmount(@Nullable BigDecimal jrFormFillingAmount) {
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

  public AssessmentGet boltOnAdjournedHearingFee(@Nullable BigDecimal boltOnAdjournedHearingFee) {
    this.boltOnAdjournedHearingFee = boltOnAdjournedHearingFee;
    return this;
  }

  /**
   * Get boltOnAdjournedHearingFee
   * @return boltOnAdjournedHearingFee
   */
  @Valid 
  @Schema(name = "bolt_on_adjourned_hearing_fee", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bolt_on_adjourned_hearing_fee")
  public @Nullable BigDecimal getBoltOnAdjournedHearingFee() {
    return boltOnAdjournedHearingFee;
  }

  public void setBoltOnAdjournedHearingFee(@Nullable BigDecimal boltOnAdjournedHearingFee) {
    this.boltOnAdjournedHearingFee = boltOnAdjournedHearingFee;
  }

  public AssessmentGet boltOnCmrhTelephoneFee(@Nullable BigDecimal boltOnCmrhTelephoneFee) {
    this.boltOnCmrhTelephoneFee = boltOnCmrhTelephoneFee;
    return this;
  }

  /**
   * Get boltOnCmrhTelephoneFee
   * @return boltOnCmrhTelephoneFee
   */
  @Valid 
  @Schema(name = "bolt_on_cmrh_telephone_fee", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bolt_on_cmrh_telephone_fee")
  public @Nullable BigDecimal getBoltOnCmrhTelephoneFee() {
    return boltOnCmrhTelephoneFee;
  }

  public void setBoltOnCmrhTelephoneFee(@Nullable BigDecimal boltOnCmrhTelephoneFee) {
    this.boltOnCmrhTelephoneFee = boltOnCmrhTelephoneFee;
  }

  public AssessmentGet boltOnCmrhOralFee(@Nullable BigDecimal boltOnCmrhOralFee) {
    this.boltOnCmrhOralFee = boltOnCmrhOralFee;
    return this;
  }

  /**
   * Get boltOnCmrhOralFee
   * @return boltOnCmrhOralFee
   */
  @Valid 
  @Schema(name = "bolt_on_cmrh_oral_fee", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bolt_on_cmrh_oral_fee")
  public @Nullable BigDecimal getBoltOnCmrhOralFee() {
    return boltOnCmrhOralFee;
  }

  public void setBoltOnCmrhOralFee(@Nullable BigDecimal boltOnCmrhOralFee) {
    this.boltOnCmrhOralFee = boltOnCmrhOralFee;
  }

  public AssessmentGet boltOnHomeOfficeInterviewFee(@Nullable BigDecimal boltOnHomeOfficeInterviewFee) {
    this.boltOnHomeOfficeInterviewFee = boltOnHomeOfficeInterviewFee;
    return this;
  }

  /**
   * Get boltOnHomeOfficeInterviewFee
   * @return boltOnHomeOfficeInterviewFee
   */
  @Valid 
  @Schema(name = "bolt_on_home_office_interview_fee", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bolt_on_home_office_interview_fee")
  public @Nullable BigDecimal getBoltOnHomeOfficeInterviewFee() {
    return boltOnHomeOfficeInterviewFee;
  }

  public void setBoltOnHomeOfficeInterviewFee(@Nullable BigDecimal boltOnHomeOfficeInterviewFee) {
    this.boltOnHomeOfficeInterviewFee = boltOnHomeOfficeInterviewFee;
  }

  public AssessmentGet boltOnSubstantiveHearingFee(@Nullable BigDecimal boltOnSubstantiveHearingFee) {
    this.boltOnSubstantiveHearingFee = boltOnSubstantiveHearingFee;
    return this;
  }

  /**
   * Get boltOnSubstantiveHearingFee
   * @return boltOnSubstantiveHearingFee
   */
  @Valid 
  @Schema(name = "bolt_on_substantive_hearing_fee", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bolt_on_substantive_hearing_fee")
  public @Nullable BigDecimal getBoltOnSubstantiveHearingFee() {
    return boltOnSubstantiveHearingFee;
  }

  public void setBoltOnSubstantiveHearingFee(@Nullable BigDecimal boltOnSubstantiveHearingFee) {
    this.boltOnSubstantiveHearingFee = boltOnSubstantiveHearingFee;
  }

  public AssessmentGet isVatApplicable(@Nullable Boolean isVatApplicable) {
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

  public AssessmentGet createdByUserId(@Nullable String createdByUserId) {
    this.createdByUserId = createdByUserId;
    return this;
  }

  /**
   * Get createdByUserId
   * @return createdByUserId
   */
  
  @Schema(name = "created_by_user_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("created_by_user_id")
  public @Nullable String getCreatedByUserId() {
    return createdByUserId;
  }

  public void setCreatedByUserId(@Nullable String createdByUserId) {
    this.createdByUserId = createdByUserId;
  }

  public AssessmentGet assessedTotalVat(@Nullable BigDecimal assessedTotalVat) {
    this.assessedTotalVat = assessedTotalVat;
    return this;
  }

  /**
   * Get assessedTotalVat
   * @return assessedTotalVat
   */
  @Valid 
  @Schema(name = "assessed_total_vat", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("assessed_total_vat")
  public @Nullable BigDecimal getAssessedTotalVat() {
    return assessedTotalVat;
  }

  public void setAssessedTotalVat(@Nullable BigDecimal assessedTotalVat) {
    this.assessedTotalVat = assessedTotalVat;
  }

  public AssessmentGet assessedTotalInclVat(@Nullable BigDecimal assessedTotalInclVat) {
    this.assessedTotalInclVat = assessedTotalInclVat;
    return this;
  }

  /**
   * Get assessedTotalInclVat
   * @return assessedTotalInclVat
   */
  @Valid 
  @Schema(name = "assessed_total_incl_vat", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("assessed_total_incl_vat")
  public @Nullable BigDecimal getAssessedTotalInclVat() {
    return assessedTotalInclVat;
  }

  public void setAssessedTotalInclVat(@Nullable BigDecimal assessedTotalInclVat) {
    this.assessedTotalInclVat = assessedTotalInclVat;
  }

  public AssessmentGet allowedTotalVat(@Nullable BigDecimal allowedTotalVat) {
    this.allowedTotalVat = allowedTotalVat;
    return this;
  }

  /**
   * Get allowedTotalVat
   * @return allowedTotalVat
   */
  @Valid 
  @Schema(name = "allowed_total_vat", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("allowed_total_vat")
  public @Nullable BigDecimal getAllowedTotalVat() {
    return allowedTotalVat;
  }

  public void setAllowedTotalVat(@Nullable BigDecimal allowedTotalVat) {
    this.allowedTotalVat = allowedTotalVat;
  }

  public AssessmentGet allowedTotalInclVat(@Nullable BigDecimal allowedTotalInclVat) {
    this.allowedTotalInclVat = allowedTotalInclVat;
    return this;
  }

  /**
   * Get allowedTotalInclVat
   * @return allowedTotalInclVat
   */
  @Valid 
  @Schema(name = "allowed_total_incl_vat", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("allowed_total_incl_vat")
  public @Nullable BigDecimal getAllowedTotalInclVat() {
    return allowedTotalInclVat;
  }

  public void setAllowedTotalInclVat(@Nullable BigDecimal allowedTotalInclVat) {
    this.allowedTotalInclVat = allowedTotalInclVat;
  }

  public AssessmentGet assessmentType(@Nullable AssessmentType assessmentType) {
    this.assessmentType = assessmentType;
    return this;
  }

  /**
   * Get assessmentType
   * @return assessmentType
   */
  @Valid 
  @Schema(name = "assessment_type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("assessment_type")
  public @Nullable AssessmentType getAssessmentType() {
    return assessmentType;
  }

  public void setAssessmentType(@Nullable AssessmentType assessmentType) {
    this.assessmentType = assessmentType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AssessmentGet assessmentGet = (AssessmentGet) o;
    return Objects.equals(this.createdOn, assessmentGet.createdOn) &&
        Objects.equals(this.id, assessmentGet.id) &&
        Objects.equals(this.claimId, assessmentGet.claimId) &&
        Objects.equals(this.claimSummaryFeeId, assessmentGet.claimSummaryFeeId) &&
        Objects.equals(this.assessmentOutcome, assessmentGet.assessmentOutcome) &&
        Objects.equals(this.assessmentReason, assessmentGet.assessmentReason) &&
        Objects.equals(this.fixedFeeAmount, assessmentGet.fixedFeeAmount) &&
        Objects.equals(this.netProfitCostsAmount, assessmentGet.netProfitCostsAmount) &&
        Objects.equals(this.disbursementAmount, assessmentGet.disbursementAmount) &&
        Objects.equals(this.disbursementVatAmount, assessmentGet.disbursementVatAmount) &&
        Objects.equals(this.netCostOfCounselAmount, assessmentGet.netCostOfCounselAmount) &&
        Objects.equals(this.netTravelCostsAmount, assessmentGet.netTravelCostsAmount) &&
        Objects.equals(this.netWaitingCostsAmount, assessmentGet.netWaitingCostsAmount) &&
        Objects.equals(this.detentionTravelAndWaitingCostsAmount, assessmentGet.detentionTravelAndWaitingCostsAmount) &&
        Objects.equals(this.jrFormFillingAmount, assessmentGet.jrFormFillingAmount) &&
        Objects.equals(this.boltOnAdjournedHearingFee, assessmentGet.boltOnAdjournedHearingFee) &&
        Objects.equals(this.boltOnCmrhTelephoneFee, assessmentGet.boltOnCmrhTelephoneFee) &&
        Objects.equals(this.boltOnCmrhOralFee, assessmentGet.boltOnCmrhOralFee) &&
        Objects.equals(this.boltOnHomeOfficeInterviewFee, assessmentGet.boltOnHomeOfficeInterviewFee) &&
        Objects.equals(this.boltOnSubstantiveHearingFee, assessmentGet.boltOnSubstantiveHearingFee) &&
        Objects.equals(this.isVatApplicable, assessmentGet.isVatApplicable) &&
        Objects.equals(this.createdByUserId, assessmentGet.createdByUserId) &&
        Objects.equals(this.assessedTotalVat, assessmentGet.assessedTotalVat) &&
        Objects.equals(this.assessedTotalInclVat, assessmentGet.assessedTotalInclVat) &&
        Objects.equals(this.allowedTotalVat, assessmentGet.allowedTotalVat) &&
        Objects.equals(this.allowedTotalInclVat, assessmentGet.allowedTotalInclVat) &&
        Objects.equals(this.assessmentType, assessmentGet.assessmentType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(createdOn, id, claimId, claimSummaryFeeId, assessmentOutcome, assessmentReason, fixedFeeAmount, netProfitCostsAmount, disbursementAmount, disbursementVatAmount, netCostOfCounselAmount, netTravelCostsAmount, netWaitingCostsAmount, detentionTravelAndWaitingCostsAmount, jrFormFillingAmount, boltOnAdjournedHearingFee, boltOnCmrhTelephoneFee, boltOnCmrhOralFee, boltOnHomeOfficeInterviewFee, boltOnSubstantiveHearingFee, isVatApplicable, createdByUserId, assessedTotalVat, assessedTotalInclVat, allowedTotalVat, allowedTotalInclVat, assessmentType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AssessmentGet {\n");
    sb.append("    createdOn: ").append(toIndentedString(createdOn)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    claimId: ").append(toIndentedString(claimId)).append("\n");
    sb.append("    claimSummaryFeeId: ").append(toIndentedString(claimSummaryFeeId)).append("\n");
    sb.append("    assessmentOutcome: ").append(toIndentedString(assessmentOutcome)).append("\n");
    sb.append("    assessmentReason: ").append(toIndentedString(assessmentReason)).append("\n");
    sb.append("    fixedFeeAmount: ").append(toIndentedString(fixedFeeAmount)).append("\n");
    sb.append("    netProfitCostsAmount: ").append(toIndentedString(netProfitCostsAmount)).append("\n");
    sb.append("    disbursementAmount: ").append(toIndentedString(disbursementAmount)).append("\n");
    sb.append("    disbursementVatAmount: ").append(toIndentedString(disbursementVatAmount)).append("\n");
    sb.append("    netCostOfCounselAmount: ").append(toIndentedString(netCostOfCounselAmount)).append("\n");
    sb.append("    netTravelCostsAmount: ").append(toIndentedString(netTravelCostsAmount)).append("\n");
    sb.append("    netWaitingCostsAmount: ").append(toIndentedString(netWaitingCostsAmount)).append("\n");
    sb.append("    detentionTravelAndWaitingCostsAmount: ").append(toIndentedString(detentionTravelAndWaitingCostsAmount)).append("\n");
    sb.append("    jrFormFillingAmount: ").append(toIndentedString(jrFormFillingAmount)).append("\n");
    sb.append("    boltOnAdjournedHearingFee: ").append(toIndentedString(boltOnAdjournedHearingFee)).append("\n");
    sb.append("    boltOnCmrhTelephoneFee: ").append(toIndentedString(boltOnCmrhTelephoneFee)).append("\n");
    sb.append("    boltOnCmrhOralFee: ").append(toIndentedString(boltOnCmrhOralFee)).append("\n");
    sb.append("    boltOnHomeOfficeInterviewFee: ").append(toIndentedString(boltOnHomeOfficeInterviewFee)).append("\n");
    sb.append("    boltOnSubstantiveHearingFee: ").append(toIndentedString(boltOnSubstantiveHearingFee)).append("\n");
    sb.append("    isVatApplicable: ").append(toIndentedString(isVatApplicable)).append("\n");
    sb.append("    createdByUserId: ").append(toIndentedString(createdByUserId)).append("\n");
    sb.append("    assessedTotalVat: ").append(toIndentedString(assessedTotalVat)).append("\n");
    sb.append("    assessedTotalInclVat: ").append(toIndentedString(assessedTotalInclVat)).append("\n");
    sb.append("    allowedTotalVat: ").append(toIndentedString(allowedTotalVat)).append("\n");
    sb.append("    allowedTotalInclVat: ").append(toIndentedString(allowedTotalInclVat)).append("\n");
    sb.append("    assessmentType: ").append(toIndentedString(assessmentType)).append("\n");
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

    private AssessmentGet instance;

    public Builder() {
      this(new AssessmentGet());
    }

    protected Builder(AssessmentGet instance) {
      this.instance = instance;
    }

    protected Builder copyOf(AssessmentGet value) { 
      this.instance.setCreatedOn(value.createdOn);
      this.instance.setId(value.id);
      this.instance.setClaimId(value.claimId);
      this.instance.setClaimSummaryFeeId(value.claimSummaryFeeId);
      this.instance.setAssessmentOutcome(value.assessmentOutcome);
      this.instance.setAssessmentReason(value.assessmentReason);
      this.instance.setFixedFeeAmount(value.fixedFeeAmount);
      this.instance.setNetProfitCostsAmount(value.netProfitCostsAmount);
      this.instance.setDisbursementAmount(value.disbursementAmount);
      this.instance.setDisbursementVatAmount(value.disbursementVatAmount);
      this.instance.setNetCostOfCounselAmount(value.netCostOfCounselAmount);
      this.instance.setNetTravelCostsAmount(value.netTravelCostsAmount);
      this.instance.setNetWaitingCostsAmount(value.netWaitingCostsAmount);
      this.instance.setDetentionTravelAndWaitingCostsAmount(value.detentionTravelAndWaitingCostsAmount);
      this.instance.setJrFormFillingAmount(value.jrFormFillingAmount);
      this.instance.setBoltOnAdjournedHearingFee(value.boltOnAdjournedHearingFee);
      this.instance.setBoltOnCmrhTelephoneFee(value.boltOnCmrhTelephoneFee);
      this.instance.setBoltOnCmrhOralFee(value.boltOnCmrhOralFee);
      this.instance.setBoltOnHomeOfficeInterviewFee(value.boltOnHomeOfficeInterviewFee);
      this.instance.setBoltOnSubstantiveHearingFee(value.boltOnSubstantiveHearingFee);
      this.instance.setIsVatApplicable(value.isVatApplicable);
      this.instance.setCreatedByUserId(value.createdByUserId);
      this.instance.setAssessedTotalVat(value.assessedTotalVat);
      this.instance.setAssessedTotalInclVat(value.assessedTotalInclVat);
      this.instance.setAllowedTotalVat(value.allowedTotalVat);
      this.instance.setAllowedTotalInclVat(value.allowedTotalInclVat);
      this.instance.setAssessmentType(value.assessmentType);
      return this;
    }

    public AssessmentGet.Builder createdOn(OffsetDateTime createdOn) {
      this.instance.createdOn(createdOn);
      return this;
    }
    
    public AssessmentGet.Builder id(UUID id) {
      this.instance.id(id);
      return this;
    }
    
    public AssessmentGet.Builder claimId(UUID claimId) {
      this.instance.claimId(claimId);
      return this;
    }
    
    public AssessmentGet.Builder claimSummaryFeeId(UUID claimSummaryFeeId) {
      this.instance.claimSummaryFeeId(claimSummaryFeeId);
      return this;
    }
    
    public AssessmentGet.Builder assessmentOutcome(AssessmentOutcome assessmentOutcome) {
      this.instance.assessmentOutcome(assessmentOutcome);
      return this;
    }
    
    public AssessmentGet.Builder assessmentReason(String assessmentReason) {
      this.instance.assessmentReason(assessmentReason);
      return this;
    }
    
    public AssessmentGet.Builder fixedFeeAmount(BigDecimal fixedFeeAmount) {
      this.instance.fixedFeeAmount(fixedFeeAmount);
      return this;
    }
    
    public AssessmentGet.Builder netProfitCostsAmount(BigDecimal netProfitCostsAmount) {
      this.instance.netProfitCostsAmount(netProfitCostsAmount);
      return this;
    }
    
    public AssessmentGet.Builder disbursementAmount(BigDecimal disbursementAmount) {
      this.instance.disbursementAmount(disbursementAmount);
      return this;
    }
    
    public AssessmentGet.Builder disbursementVatAmount(BigDecimal disbursementVatAmount) {
      this.instance.disbursementVatAmount(disbursementVatAmount);
      return this;
    }
    
    public AssessmentGet.Builder netCostOfCounselAmount(BigDecimal netCostOfCounselAmount) {
      this.instance.netCostOfCounselAmount(netCostOfCounselAmount);
      return this;
    }
    
    public AssessmentGet.Builder netTravelCostsAmount(BigDecimal netTravelCostsAmount) {
      this.instance.netTravelCostsAmount(netTravelCostsAmount);
      return this;
    }
    
    public AssessmentGet.Builder netWaitingCostsAmount(BigDecimal netWaitingCostsAmount) {
      this.instance.netWaitingCostsAmount(netWaitingCostsAmount);
      return this;
    }
    
    public AssessmentGet.Builder detentionTravelAndWaitingCostsAmount(BigDecimal detentionTravelAndWaitingCostsAmount) {
      this.instance.detentionTravelAndWaitingCostsAmount(detentionTravelAndWaitingCostsAmount);
      return this;
    }
    
    public AssessmentGet.Builder jrFormFillingAmount(BigDecimal jrFormFillingAmount) {
      this.instance.jrFormFillingAmount(jrFormFillingAmount);
      return this;
    }
    
    public AssessmentGet.Builder boltOnAdjournedHearingFee(BigDecimal boltOnAdjournedHearingFee) {
      this.instance.boltOnAdjournedHearingFee(boltOnAdjournedHearingFee);
      return this;
    }
    
    public AssessmentGet.Builder boltOnCmrhTelephoneFee(BigDecimal boltOnCmrhTelephoneFee) {
      this.instance.boltOnCmrhTelephoneFee(boltOnCmrhTelephoneFee);
      return this;
    }
    
    public AssessmentGet.Builder boltOnCmrhOralFee(BigDecimal boltOnCmrhOralFee) {
      this.instance.boltOnCmrhOralFee(boltOnCmrhOralFee);
      return this;
    }
    
    public AssessmentGet.Builder boltOnHomeOfficeInterviewFee(BigDecimal boltOnHomeOfficeInterviewFee) {
      this.instance.boltOnHomeOfficeInterviewFee(boltOnHomeOfficeInterviewFee);
      return this;
    }
    
    public AssessmentGet.Builder boltOnSubstantiveHearingFee(BigDecimal boltOnSubstantiveHearingFee) {
      this.instance.boltOnSubstantiveHearingFee(boltOnSubstantiveHearingFee);
      return this;
    }
    
    public AssessmentGet.Builder isVatApplicable(Boolean isVatApplicable) {
      this.instance.isVatApplicable(isVatApplicable);
      return this;
    }
    
    public AssessmentGet.Builder createdByUserId(String createdByUserId) {
      this.instance.createdByUserId(createdByUserId);
      return this;
    }
    
    public AssessmentGet.Builder assessedTotalVat(BigDecimal assessedTotalVat) {
      this.instance.assessedTotalVat(assessedTotalVat);
      return this;
    }
    
    public AssessmentGet.Builder assessedTotalInclVat(BigDecimal assessedTotalInclVat) {
      this.instance.assessedTotalInclVat(assessedTotalInclVat);
      return this;
    }
    
    public AssessmentGet.Builder allowedTotalVat(BigDecimal allowedTotalVat) {
      this.instance.allowedTotalVat(allowedTotalVat);
      return this;
    }
    
    public AssessmentGet.Builder allowedTotalInclVat(BigDecimal allowedTotalInclVat) {
      this.instance.allowedTotalInclVat(allowedTotalInclVat);
      return this;
    }
    
    public AssessmentGet.Builder assessmentType(AssessmentType assessmentType) {
      this.instance.assessmentType(assessmentType);
      return this;
    }
    
    /**
    * returns a built AssessmentGet instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public AssessmentGet build() {
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
  public static AssessmentGet.Builder builder() {
    return new AssessmentGet.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public AssessmentGet.Builder toBuilder() {
    AssessmentGet.Builder builder = new AssessmentGet.Builder();
    return builder.copyOf(this);
  }

}

