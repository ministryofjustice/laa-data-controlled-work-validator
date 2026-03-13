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
 * AssessmentPost
 */

@JsonTypeName("assessment_post")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T21:51:30.932283Z[Europe/London]", comments = "Generator version: 7.14.0")
public class AssessmentPost implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable UUID id;

  private UUID claimId;

  private UUID claimSummaryFeeId;

  private AssessmentOutcome assessmentOutcome;

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

  private String createdByUserId;

  private BigDecimal assessedTotalVat;

  private BigDecimal assessedTotalInclVat;

  private BigDecimal allowedTotalVat;

  private BigDecimal allowedTotalInclVat;

  private @Nullable AssessmentType assessmentType;

  public AssessmentPost() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public AssessmentPost(UUID claimId, UUID claimSummaryFeeId, AssessmentOutcome assessmentOutcome, String createdByUserId, BigDecimal assessedTotalVat, BigDecimal assessedTotalInclVat, BigDecimal allowedTotalVat, BigDecimal allowedTotalInclVat) {
    this.claimId = claimId;
    this.claimSummaryFeeId = claimSummaryFeeId;
    this.assessmentOutcome = assessmentOutcome;
    this.createdByUserId = createdByUserId;
    this.assessedTotalVat = assessedTotalVat;
    this.assessedTotalInclVat = assessedTotalInclVat;
    this.allowedTotalVat = allowedTotalVat;
    this.allowedTotalInclVat = allowedTotalInclVat;
  }

  public AssessmentPost id(@Nullable UUID id) {
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

  public AssessmentPost claimId(UUID claimId) {
    this.claimId = claimId;
    return this;
  }

  /**
   * Get claimId
   * @return claimId
   */
  @NotNull @Valid 
  @Schema(name = "claim_id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("claim_id")
  public UUID getClaimId() {
    return claimId;
  }

  public void setClaimId(UUID claimId) {
    this.claimId = claimId;
  }

  public AssessmentPost claimSummaryFeeId(UUID claimSummaryFeeId) {
    this.claimSummaryFeeId = claimSummaryFeeId;
    return this;
  }

  /**
   * Get claimSummaryFeeId
   * @return claimSummaryFeeId
   */
  @NotNull @Valid 
  @Schema(name = "claim_summary_fee_id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("claim_summary_fee_id")
  public UUID getClaimSummaryFeeId() {
    return claimSummaryFeeId;
  }

  public void setClaimSummaryFeeId(UUID claimSummaryFeeId) {
    this.claimSummaryFeeId = claimSummaryFeeId;
  }

  public AssessmentPost assessmentOutcome(AssessmentOutcome assessmentOutcome) {
    this.assessmentOutcome = assessmentOutcome;
    return this;
  }

  /**
   * Get assessmentOutcome
   * @return assessmentOutcome
   */
  @NotNull @Valid 
  @Schema(name = "assessment_outcome", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("assessment_outcome")
  public AssessmentOutcome getAssessmentOutcome() {
    return assessmentOutcome;
  }

  public void setAssessmentOutcome(AssessmentOutcome assessmentOutcome) {
    this.assessmentOutcome = assessmentOutcome;
  }

  public AssessmentPost assessmentReason(@Nullable String assessmentReason) {
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

  public AssessmentPost fixedFeeAmount(@Nullable BigDecimal fixedFeeAmount) {
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

  public AssessmentPost netProfitCostsAmount(@Nullable BigDecimal netProfitCostsAmount) {
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

  public AssessmentPost disbursementAmount(@Nullable BigDecimal disbursementAmount) {
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

  public AssessmentPost disbursementVatAmount(@Nullable BigDecimal disbursementVatAmount) {
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

  public AssessmentPost netCostOfCounselAmount(@Nullable BigDecimal netCostOfCounselAmount) {
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

  public AssessmentPost netTravelCostsAmount(@Nullable BigDecimal netTravelCostsAmount) {
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

  public AssessmentPost netWaitingCostsAmount(@Nullable BigDecimal netWaitingCostsAmount) {
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

  public AssessmentPost detentionTravelAndWaitingCostsAmount(@Nullable BigDecimal detentionTravelAndWaitingCostsAmount) {
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

  public AssessmentPost jrFormFillingAmount(@Nullable BigDecimal jrFormFillingAmount) {
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

  public AssessmentPost boltOnAdjournedHearingFee(@Nullable BigDecimal boltOnAdjournedHearingFee) {
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

  public AssessmentPost boltOnCmrhTelephoneFee(@Nullable BigDecimal boltOnCmrhTelephoneFee) {
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

  public AssessmentPost boltOnCmrhOralFee(@Nullable BigDecimal boltOnCmrhOralFee) {
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

  public AssessmentPost boltOnHomeOfficeInterviewFee(@Nullable BigDecimal boltOnHomeOfficeInterviewFee) {
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

  public AssessmentPost boltOnSubstantiveHearingFee(@Nullable BigDecimal boltOnSubstantiveHearingFee) {
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

  public AssessmentPost isVatApplicable(@Nullable Boolean isVatApplicable) {
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

  public AssessmentPost createdByUserId(String createdByUserId) {
    this.createdByUserId = createdByUserId;
    return this;
  }

  /**
   * Get createdByUserId
   * @return createdByUserId
   */
  @NotNull 
  @Schema(name = "created_by_user_id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("created_by_user_id")
  public String getCreatedByUserId() {
    return createdByUserId;
  }

  public void setCreatedByUserId(String createdByUserId) {
    this.createdByUserId = createdByUserId;
  }

  public AssessmentPost assessedTotalVat(BigDecimal assessedTotalVat) {
    this.assessedTotalVat = assessedTotalVat;
    return this;
  }

  /**
   * Get assessedTotalVat
   * @return assessedTotalVat
   */
  @NotNull @Valid 
  @Schema(name = "assessed_total_vat", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("assessed_total_vat")
  public BigDecimal getAssessedTotalVat() {
    return assessedTotalVat;
  }

  public void setAssessedTotalVat(BigDecimal assessedTotalVat) {
    this.assessedTotalVat = assessedTotalVat;
  }

  public AssessmentPost assessedTotalInclVat(BigDecimal assessedTotalInclVat) {
    this.assessedTotalInclVat = assessedTotalInclVat;
    return this;
  }

  /**
   * Get assessedTotalInclVat
   * @return assessedTotalInclVat
   */
  @NotNull @Valid 
  @Schema(name = "assessed_total_incl_vat", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("assessed_total_incl_vat")
  public BigDecimal getAssessedTotalInclVat() {
    return assessedTotalInclVat;
  }

  public void setAssessedTotalInclVat(BigDecimal assessedTotalInclVat) {
    this.assessedTotalInclVat = assessedTotalInclVat;
  }

  public AssessmentPost allowedTotalVat(BigDecimal allowedTotalVat) {
    this.allowedTotalVat = allowedTotalVat;
    return this;
  }

  /**
   * Get allowedTotalVat
   * @return allowedTotalVat
   */
  @NotNull @Valid 
  @Schema(name = "allowed_total_vat", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("allowed_total_vat")
  public BigDecimal getAllowedTotalVat() {
    return allowedTotalVat;
  }

  public void setAllowedTotalVat(BigDecimal allowedTotalVat) {
    this.allowedTotalVat = allowedTotalVat;
  }

  public AssessmentPost allowedTotalInclVat(BigDecimal allowedTotalInclVat) {
    this.allowedTotalInclVat = allowedTotalInclVat;
    return this;
  }

  /**
   * Get allowedTotalInclVat
   * @return allowedTotalInclVat
   */
  @NotNull @Valid 
  @Schema(name = "allowed_total_incl_vat", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("allowed_total_incl_vat")
  public BigDecimal getAllowedTotalInclVat() {
    return allowedTotalInclVat;
  }

  public void setAllowedTotalInclVat(BigDecimal allowedTotalInclVat) {
    this.allowedTotalInclVat = allowedTotalInclVat;
  }

  public AssessmentPost assessmentType(@Nullable AssessmentType assessmentType) {
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
    AssessmentPost assessmentPost = (AssessmentPost) o;
    return Objects.equals(this.id, assessmentPost.id) &&
        Objects.equals(this.claimId, assessmentPost.claimId) &&
        Objects.equals(this.claimSummaryFeeId, assessmentPost.claimSummaryFeeId) &&
        Objects.equals(this.assessmentOutcome, assessmentPost.assessmentOutcome) &&
        Objects.equals(this.assessmentReason, assessmentPost.assessmentReason) &&
        Objects.equals(this.fixedFeeAmount, assessmentPost.fixedFeeAmount) &&
        Objects.equals(this.netProfitCostsAmount, assessmentPost.netProfitCostsAmount) &&
        Objects.equals(this.disbursementAmount, assessmentPost.disbursementAmount) &&
        Objects.equals(this.disbursementVatAmount, assessmentPost.disbursementVatAmount) &&
        Objects.equals(this.netCostOfCounselAmount, assessmentPost.netCostOfCounselAmount) &&
        Objects.equals(this.netTravelCostsAmount, assessmentPost.netTravelCostsAmount) &&
        Objects.equals(this.netWaitingCostsAmount, assessmentPost.netWaitingCostsAmount) &&
        Objects.equals(this.detentionTravelAndWaitingCostsAmount, assessmentPost.detentionTravelAndWaitingCostsAmount) &&
        Objects.equals(this.jrFormFillingAmount, assessmentPost.jrFormFillingAmount) &&
        Objects.equals(this.boltOnAdjournedHearingFee, assessmentPost.boltOnAdjournedHearingFee) &&
        Objects.equals(this.boltOnCmrhTelephoneFee, assessmentPost.boltOnCmrhTelephoneFee) &&
        Objects.equals(this.boltOnCmrhOralFee, assessmentPost.boltOnCmrhOralFee) &&
        Objects.equals(this.boltOnHomeOfficeInterviewFee, assessmentPost.boltOnHomeOfficeInterviewFee) &&
        Objects.equals(this.boltOnSubstantiveHearingFee, assessmentPost.boltOnSubstantiveHearingFee) &&
        Objects.equals(this.isVatApplicable, assessmentPost.isVatApplicable) &&
        Objects.equals(this.createdByUserId, assessmentPost.createdByUserId) &&
        Objects.equals(this.assessedTotalVat, assessmentPost.assessedTotalVat) &&
        Objects.equals(this.assessedTotalInclVat, assessmentPost.assessedTotalInclVat) &&
        Objects.equals(this.allowedTotalVat, assessmentPost.allowedTotalVat) &&
        Objects.equals(this.allowedTotalInclVat, assessmentPost.allowedTotalInclVat) &&
        Objects.equals(this.assessmentType, assessmentPost.assessmentType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, claimId, claimSummaryFeeId, assessmentOutcome, assessmentReason, fixedFeeAmount, netProfitCostsAmount, disbursementAmount, disbursementVatAmount, netCostOfCounselAmount, netTravelCostsAmount, netWaitingCostsAmount, detentionTravelAndWaitingCostsAmount, jrFormFillingAmount, boltOnAdjournedHearingFee, boltOnCmrhTelephoneFee, boltOnCmrhOralFee, boltOnHomeOfficeInterviewFee, boltOnSubstantiveHearingFee, isVatApplicable, createdByUserId, assessedTotalVat, assessedTotalInclVat, allowedTotalVat, allowedTotalInclVat, assessmentType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AssessmentPost {\n");
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

    private AssessmentPost instance;

    public Builder() {
      this(new AssessmentPost());
    }

    protected Builder(AssessmentPost instance) {
      this.instance = instance;
    }

    protected Builder copyOf(AssessmentPost value) { 
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

    public AssessmentPost.Builder id(UUID id) {
      this.instance.id(id);
      return this;
    }
    
    public AssessmentPost.Builder claimId(UUID claimId) {
      this.instance.claimId(claimId);
      return this;
    }
    
    public AssessmentPost.Builder claimSummaryFeeId(UUID claimSummaryFeeId) {
      this.instance.claimSummaryFeeId(claimSummaryFeeId);
      return this;
    }
    
    public AssessmentPost.Builder assessmentOutcome(AssessmentOutcome assessmentOutcome) {
      this.instance.assessmentOutcome(assessmentOutcome);
      return this;
    }
    
    public AssessmentPost.Builder assessmentReason(String assessmentReason) {
      this.instance.assessmentReason(assessmentReason);
      return this;
    }
    
    public AssessmentPost.Builder fixedFeeAmount(BigDecimal fixedFeeAmount) {
      this.instance.fixedFeeAmount(fixedFeeAmount);
      return this;
    }
    
    public AssessmentPost.Builder netProfitCostsAmount(BigDecimal netProfitCostsAmount) {
      this.instance.netProfitCostsAmount(netProfitCostsAmount);
      return this;
    }
    
    public AssessmentPost.Builder disbursementAmount(BigDecimal disbursementAmount) {
      this.instance.disbursementAmount(disbursementAmount);
      return this;
    }
    
    public AssessmentPost.Builder disbursementVatAmount(BigDecimal disbursementVatAmount) {
      this.instance.disbursementVatAmount(disbursementVatAmount);
      return this;
    }
    
    public AssessmentPost.Builder netCostOfCounselAmount(BigDecimal netCostOfCounselAmount) {
      this.instance.netCostOfCounselAmount(netCostOfCounselAmount);
      return this;
    }
    
    public AssessmentPost.Builder netTravelCostsAmount(BigDecimal netTravelCostsAmount) {
      this.instance.netTravelCostsAmount(netTravelCostsAmount);
      return this;
    }
    
    public AssessmentPost.Builder netWaitingCostsAmount(BigDecimal netWaitingCostsAmount) {
      this.instance.netWaitingCostsAmount(netWaitingCostsAmount);
      return this;
    }
    
    public AssessmentPost.Builder detentionTravelAndWaitingCostsAmount(BigDecimal detentionTravelAndWaitingCostsAmount) {
      this.instance.detentionTravelAndWaitingCostsAmount(detentionTravelAndWaitingCostsAmount);
      return this;
    }
    
    public AssessmentPost.Builder jrFormFillingAmount(BigDecimal jrFormFillingAmount) {
      this.instance.jrFormFillingAmount(jrFormFillingAmount);
      return this;
    }
    
    public AssessmentPost.Builder boltOnAdjournedHearingFee(BigDecimal boltOnAdjournedHearingFee) {
      this.instance.boltOnAdjournedHearingFee(boltOnAdjournedHearingFee);
      return this;
    }
    
    public AssessmentPost.Builder boltOnCmrhTelephoneFee(BigDecimal boltOnCmrhTelephoneFee) {
      this.instance.boltOnCmrhTelephoneFee(boltOnCmrhTelephoneFee);
      return this;
    }
    
    public AssessmentPost.Builder boltOnCmrhOralFee(BigDecimal boltOnCmrhOralFee) {
      this.instance.boltOnCmrhOralFee(boltOnCmrhOralFee);
      return this;
    }
    
    public AssessmentPost.Builder boltOnHomeOfficeInterviewFee(BigDecimal boltOnHomeOfficeInterviewFee) {
      this.instance.boltOnHomeOfficeInterviewFee(boltOnHomeOfficeInterviewFee);
      return this;
    }
    
    public AssessmentPost.Builder boltOnSubstantiveHearingFee(BigDecimal boltOnSubstantiveHearingFee) {
      this.instance.boltOnSubstantiveHearingFee(boltOnSubstantiveHearingFee);
      return this;
    }
    
    public AssessmentPost.Builder isVatApplicable(Boolean isVatApplicable) {
      this.instance.isVatApplicable(isVatApplicable);
      return this;
    }
    
    public AssessmentPost.Builder createdByUserId(String createdByUserId) {
      this.instance.createdByUserId(createdByUserId);
      return this;
    }
    
    public AssessmentPost.Builder assessedTotalVat(BigDecimal assessedTotalVat) {
      this.instance.assessedTotalVat(assessedTotalVat);
      return this;
    }
    
    public AssessmentPost.Builder assessedTotalInclVat(BigDecimal assessedTotalInclVat) {
      this.instance.assessedTotalInclVat(assessedTotalInclVat);
      return this;
    }
    
    public AssessmentPost.Builder allowedTotalVat(BigDecimal allowedTotalVat) {
      this.instance.allowedTotalVat(allowedTotalVat);
      return this;
    }
    
    public AssessmentPost.Builder allowedTotalInclVat(BigDecimal allowedTotalInclVat) {
      this.instance.allowedTotalInclVat(allowedTotalInclVat);
      return this;
    }
    
    public AssessmentPost.Builder assessmentType(AssessmentType assessmentType) {
      this.instance.assessmentType(assessmentType);
      return this;
    }
    
    /**
    * returns a built AssessmentPost instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public AssessmentPost build() {
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
  public static AssessmentPost.Builder builder() {
    return new AssessmentPost.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public AssessmentPost.Builder toBuilder() {
    AssessmentPost.Builder builder = new AssessmentPost.Builder();
    return builder.copyOf(this);
  }

}

