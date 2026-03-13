package uk.gov.justice.laa.dstew.payments.claimsdata.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.math.BigDecimal;
import java.util.UUID;
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
 * AssessmentBase
 */

@JsonTypeName("assessment_base")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T21:51:30.932283Z[Europe/London]", comments = "Generator version: 7.14.0")
public class AssessmentBase implements Serializable {

  private static final long serialVersionUID = 1L;

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

  public AssessmentBase id(@Nullable UUID id) {
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

  public AssessmentBase claimId(@Nullable UUID claimId) {
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

  public AssessmentBase claimSummaryFeeId(@Nullable UUID claimSummaryFeeId) {
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

  public AssessmentBase assessmentOutcome(@Nullable AssessmentOutcome assessmentOutcome) {
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

  public AssessmentBase assessmentReason(@Nullable String assessmentReason) {
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

  public AssessmentBase fixedFeeAmount(@Nullable BigDecimal fixedFeeAmount) {
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

  public AssessmentBase netProfitCostsAmount(@Nullable BigDecimal netProfitCostsAmount) {
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

  public AssessmentBase disbursementAmount(@Nullable BigDecimal disbursementAmount) {
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

  public AssessmentBase disbursementVatAmount(@Nullable BigDecimal disbursementVatAmount) {
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

  public AssessmentBase netCostOfCounselAmount(@Nullable BigDecimal netCostOfCounselAmount) {
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

  public AssessmentBase netTravelCostsAmount(@Nullable BigDecimal netTravelCostsAmount) {
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

  public AssessmentBase netWaitingCostsAmount(@Nullable BigDecimal netWaitingCostsAmount) {
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

  public AssessmentBase detentionTravelAndWaitingCostsAmount(@Nullable BigDecimal detentionTravelAndWaitingCostsAmount) {
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

  public AssessmentBase jrFormFillingAmount(@Nullable BigDecimal jrFormFillingAmount) {
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

  public AssessmentBase boltOnAdjournedHearingFee(@Nullable BigDecimal boltOnAdjournedHearingFee) {
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

  public AssessmentBase boltOnCmrhTelephoneFee(@Nullable BigDecimal boltOnCmrhTelephoneFee) {
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

  public AssessmentBase boltOnCmrhOralFee(@Nullable BigDecimal boltOnCmrhOralFee) {
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

  public AssessmentBase boltOnHomeOfficeInterviewFee(@Nullable BigDecimal boltOnHomeOfficeInterviewFee) {
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

  public AssessmentBase boltOnSubstantiveHearingFee(@Nullable BigDecimal boltOnSubstantiveHearingFee) {
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

  public AssessmentBase isVatApplicable(@Nullable Boolean isVatApplicable) {
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

  public AssessmentBase createdByUserId(@Nullable String createdByUserId) {
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

  public AssessmentBase assessedTotalVat(@Nullable BigDecimal assessedTotalVat) {
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

  public AssessmentBase assessedTotalInclVat(@Nullable BigDecimal assessedTotalInclVat) {
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

  public AssessmentBase allowedTotalVat(@Nullable BigDecimal allowedTotalVat) {
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

  public AssessmentBase allowedTotalInclVat(@Nullable BigDecimal allowedTotalInclVat) {
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

  public AssessmentBase assessmentType(@Nullable AssessmentType assessmentType) {
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
    AssessmentBase assessmentBase = (AssessmentBase) o;
    return Objects.equals(this.id, assessmentBase.id) &&
        Objects.equals(this.claimId, assessmentBase.claimId) &&
        Objects.equals(this.claimSummaryFeeId, assessmentBase.claimSummaryFeeId) &&
        Objects.equals(this.assessmentOutcome, assessmentBase.assessmentOutcome) &&
        Objects.equals(this.assessmentReason, assessmentBase.assessmentReason) &&
        Objects.equals(this.fixedFeeAmount, assessmentBase.fixedFeeAmount) &&
        Objects.equals(this.netProfitCostsAmount, assessmentBase.netProfitCostsAmount) &&
        Objects.equals(this.disbursementAmount, assessmentBase.disbursementAmount) &&
        Objects.equals(this.disbursementVatAmount, assessmentBase.disbursementVatAmount) &&
        Objects.equals(this.netCostOfCounselAmount, assessmentBase.netCostOfCounselAmount) &&
        Objects.equals(this.netTravelCostsAmount, assessmentBase.netTravelCostsAmount) &&
        Objects.equals(this.netWaitingCostsAmount, assessmentBase.netWaitingCostsAmount) &&
        Objects.equals(this.detentionTravelAndWaitingCostsAmount, assessmentBase.detentionTravelAndWaitingCostsAmount) &&
        Objects.equals(this.jrFormFillingAmount, assessmentBase.jrFormFillingAmount) &&
        Objects.equals(this.boltOnAdjournedHearingFee, assessmentBase.boltOnAdjournedHearingFee) &&
        Objects.equals(this.boltOnCmrhTelephoneFee, assessmentBase.boltOnCmrhTelephoneFee) &&
        Objects.equals(this.boltOnCmrhOralFee, assessmentBase.boltOnCmrhOralFee) &&
        Objects.equals(this.boltOnHomeOfficeInterviewFee, assessmentBase.boltOnHomeOfficeInterviewFee) &&
        Objects.equals(this.boltOnSubstantiveHearingFee, assessmentBase.boltOnSubstantiveHearingFee) &&
        Objects.equals(this.isVatApplicable, assessmentBase.isVatApplicable) &&
        Objects.equals(this.createdByUserId, assessmentBase.createdByUserId) &&
        Objects.equals(this.assessedTotalVat, assessmentBase.assessedTotalVat) &&
        Objects.equals(this.assessedTotalInclVat, assessmentBase.assessedTotalInclVat) &&
        Objects.equals(this.allowedTotalVat, assessmentBase.allowedTotalVat) &&
        Objects.equals(this.allowedTotalInclVat, assessmentBase.allowedTotalInclVat) &&
        Objects.equals(this.assessmentType, assessmentBase.assessmentType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, claimId, claimSummaryFeeId, assessmentOutcome, assessmentReason, fixedFeeAmount, netProfitCostsAmount, disbursementAmount, disbursementVatAmount, netCostOfCounselAmount, netTravelCostsAmount, netWaitingCostsAmount, detentionTravelAndWaitingCostsAmount, jrFormFillingAmount, boltOnAdjournedHearingFee, boltOnCmrhTelephoneFee, boltOnCmrhOralFee, boltOnHomeOfficeInterviewFee, boltOnSubstantiveHearingFee, isVatApplicable, createdByUserId, assessedTotalVat, assessedTotalInclVat, allowedTotalVat, allowedTotalInclVat, assessmentType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AssessmentBase {\n");
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

    private AssessmentBase instance;

    public Builder() {
      this(new AssessmentBase());
    }

    protected Builder(AssessmentBase instance) {
      this.instance = instance;
    }

    protected Builder copyOf(AssessmentBase value) { 
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

    public AssessmentBase.Builder id(UUID id) {
      this.instance.id(id);
      return this;
    }
    
    public AssessmentBase.Builder claimId(UUID claimId) {
      this.instance.claimId(claimId);
      return this;
    }
    
    public AssessmentBase.Builder claimSummaryFeeId(UUID claimSummaryFeeId) {
      this.instance.claimSummaryFeeId(claimSummaryFeeId);
      return this;
    }
    
    public AssessmentBase.Builder assessmentOutcome(AssessmentOutcome assessmentOutcome) {
      this.instance.assessmentOutcome(assessmentOutcome);
      return this;
    }
    
    public AssessmentBase.Builder assessmentReason(String assessmentReason) {
      this.instance.assessmentReason(assessmentReason);
      return this;
    }
    
    public AssessmentBase.Builder fixedFeeAmount(BigDecimal fixedFeeAmount) {
      this.instance.fixedFeeAmount(fixedFeeAmount);
      return this;
    }
    
    public AssessmentBase.Builder netProfitCostsAmount(BigDecimal netProfitCostsAmount) {
      this.instance.netProfitCostsAmount(netProfitCostsAmount);
      return this;
    }
    
    public AssessmentBase.Builder disbursementAmount(BigDecimal disbursementAmount) {
      this.instance.disbursementAmount(disbursementAmount);
      return this;
    }
    
    public AssessmentBase.Builder disbursementVatAmount(BigDecimal disbursementVatAmount) {
      this.instance.disbursementVatAmount(disbursementVatAmount);
      return this;
    }
    
    public AssessmentBase.Builder netCostOfCounselAmount(BigDecimal netCostOfCounselAmount) {
      this.instance.netCostOfCounselAmount(netCostOfCounselAmount);
      return this;
    }
    
    public AssessmentBase.Builder netTravelCostsAmount(BigDecimal netTravelCostsAmount) {
      this.instance.netTravelCostsAmount(netTravelCostsAmount);
      return this;
    }
    
    public AssessmentBase.Builder netWaitingCostsAmount(BigDecimal netWaitingCostsAmount) {
      this.instance.netWaitingCostsAmount(netWaitingCostsAmount);
      return this;
    }
    
    public AssessmentBase.Builder detentionTravelAndWaitingCostsAmount(BigDecimal detentionTravelAndWaitingCostsAmount) {
      this.instance.detentionTravelAndWaitingCostsAmount(detentionTravelAndWaitingCostsAmount);
      return this;
    }
    
    public AssessmentBase.Builder jrFormFillingAmount(BigDecimal jrFormFillingAmount) {
      this.instance.jrFormFillingAmount(jrFormFillingAmount);
      return this;
    }
    
    public AssessmentBase.Builder boltOnAdjournedHearingFee(BigDecimal boltOnAdjournedHearingFee) {
      this.instance.boltOnAdjournedHearingFee(boltOnAdjournedHearingFee);
      return this;
    }
    
    public AssessmentBase.Builder boltOnCmrhTelephoneFee(BigDecimal boltOnCmrhTelephoneFee) {
      this.instance.boltOnCmrhTelephoneFee(boltOnCmrhTelephoneFee);
      return this;
    }
    
    public AssessmentBase.Builder boltOnCmrhOralFee(BigDecimal boltOnCmrhOralFee) {
      this.instance.boltOnCmrhOralFee(boltOnCmrhOralFee);
      return this;
    }
    
    public AssessmentBase.Builder boltOnHomeOfficeInterviewFee(BigDecimal boltOnHomeOfficeInterviewFee) {
      this.instance.boltOnHomeOfficeInterviewFee(boltOnHomeOfficeInterviewFee);
      return this;
    }
    
    public AssessmentBase.Builder boltOnSubstantiveHearingFee(BigDecimal boltOnSubstantiveHearingFee) {
      this.instance.boltOnSubstantiveHearingFee(boltOnSubstantiveHearingFee);
      return this;
    }
    
    public AssessmentBase.Builder isVatApplicable(Boolean isVatApplicable) {
      this.instance.isVatApplicable(isVatApplicable);
      return this;
    }
    
    public AssessmentBase.Builder createdByUserId(String createdByUserId) {
      this.instance.createdByUserId(createdByUserId);
      return this;
    }
    
    public AssessmentBase.Builder assessedTotalVat(BigDecimal assessedTotalVat) {
      this.instance.assessedTotalVat(assessedTotalVat);
      return this;
    }
    
    public AssessmentBase.Builder assessedTotalInclVat(BigDecimal assessedTotalInclVat) {
      this.instance.assessedTotalInclVat(assessedTotalInclVat);
      return this;
    }
    
    public AssessmentBase.Builder allowedTotalVat(BigDecimal allowedTotalVat) {
      this.instance.allowedTotalVat(allowedTotalVat);
      return this;
    }
    
    public AssessmentBase.Builder allowedTotalInclVat(BigDecimal allowedTotalInclVat) {
      this.instance.allowedTotalInclVat(allowedTotalInclVat);
      return this;
    }
    
    public AssessmentBase.Builder assessmentType(AssessmentType assessmentType) {
      this.instance.assessmentType(assessmentType);
      return this;
    }
    
    /**
    * returns a built AssessmentBase instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public AssessmentBase build() {
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
  public static AssessmentBase.Builder builder() {
    return new AssessmentBase.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public AssessmentBase.Builder toBuilder() {
    AssessmentBase.Builder builder = new AssessmentBase.Builder();
    return builder.copyOf(this);
  }

}

