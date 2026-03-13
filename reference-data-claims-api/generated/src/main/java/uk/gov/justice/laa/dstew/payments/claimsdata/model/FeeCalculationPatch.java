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
import uk.gov.justice.laa.dstew.payments.claimsdata.model.BoltOnPatch;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.FeeCalculationType;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Fee calculation response from the Fee Scheme Platform
 */

@Schema(name = "fee_calculation_patch", description = "Fee calculation response from the Fee Scheme Platform")
@JsonTypeName("fee_calculation_patch")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T14:44:45.032142Z[Europe/London]", comments = "Generator version: 7.14.0")
public class FeeCalculationPatch implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String calculatedFeeDetailId;

  private @Nullable UUID claimSummaryFeeId;

  private @Nullable UUID claimId;

  private @Nullable String feeCode;

  private @Nullable String feeCodeDescription;

  private @Nullable FeeCalculationType feeType;

  private @Nullable String categoryOfLaw;

  private @Nullable BigDecimal totalAmount;

  private @Nullable Boolean vatIndicator;

  private @Nullable BigDecimal vatRateApplied;

  private @Nullable BigDecimal calculatedVatAmount;

  private @Nullable BigDecimal disbursementAmount;

  private @Nullable BigDecimal requestedNetDisbursementAmount;

  private @Nullable BigDecimal disbursementVatAmount;

  private @Nullable BigDecimal hourlyTotalAmount;

  private @Nullable BigDecimal fixedFeeAmount;

  private @Nullable BigDecimal netProfitCostsAmount;

  private @Nullable BigDecimal requestedNetProfitCostsAmount;

  private @Nullable BigDecimal netCostOfCounselAmount;

  private @Nullable BigDecimal netTravelCostsAmount;

  private @Nullable BigDecimal netWaitingCostsAmount;

  private @Nullable BigDecimal detentionTravelAndWaitingCostsAmount;

  private @Nullable BigDecimal jrFormFillingAmount;

  private @Nullable BigDecimal travelAndWaitingCostsAmount;

  private @Nullable BoltOnPatch boltOnDetails;

  public FeeCalculationPatch calculatedFeeDetailId(@Nullable String calculatedFeeDetailId) {
    this.calculatedFeeDetailId = calculatedFeeDetailId;
    return this;
  }

  /**
   * Get calculatedFeeDetailId
   * @return calculatedFeeDetailId
   */
  
  @Schema(name = "calculated_fee_detail_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("calculated_fee_detail_id")
  public @Nullable String getCalculatedFeeDetailId() {
    return calculatedFeeDetailId;
  }

  public void setCalculatedFeeDetailId(@Nullable String calculatedFeeDetailId) {
    this.calculatedFeeDetailId = calculatedFeeDetailId;
  }

  public FeeCalculationPatch claimSummaryFeeId(@Nullable UUID claimSummaryFeeId) {
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

  public FeeCalculationPatch claimId(@Nullable UUID claimId) {
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

  public FeeCalculationPatch feeCode(@Nullable String feeCode) {
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

  public FeeCalculationPatch feeCodeDescription(@Nullable String feeCodeDescription) {
    this.feeCodeDescription = feeCodeDescription;
    return this;
  }

  /**
   * Get feeCodeDescription
   * @return feeCodeDescription
   */
  
  @Schema(name = "fee_code_description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fee_code_description")
  public @Nullable String getFeeCodeDescription() {
    return feeCodeDescription;
  }

  public void setFeeCodeDescription(@Nullable String feeCodeDescription) {
    this.feeCodeDescription = feeCodeDescription;
  }

  public FeeCalculationPatch feeType(@Nullable FeeCalculationType feeType) {
    this.feeType = feeType;
    return this;
  }

  /**
   * Get feeType
   * @return feeType
   */
  @Valid 
  @Schema(name = "fee_type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fee_type")
  public @Nullable FeeCalculationType getFeeType() {
    return feeType;
  }

  public void setFeeType(@Nullable FeeCalculationType feeType) {
    this.feeType = feeType;
  }

  public FeeCalculationPatch categoryOfLaw(@Nullable String categoryOfLaw) {
    this.categoryOfLaw = categoryOfLaw;
    return this;
  }

  /**
   * Get categoryOfLaw
   * @return categoryOfLaw
   */
  
  @Schema(name = "category_of_law", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("category_of_law")
  public @Nullable String getCategoryOfLaw() {
    return categoryOfLaw;
  }

  public void setCategoryOfLaw(@Nullable String categoryOfLaw) {
    this.categoryOfLaw = categoryOfLaw;
  }

  public FeeCalculationPatch totalAmount(@Nullable BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
    return this;
  }

  /**
   * Get totalAmount
   * @return totalAmount
   */
  @Valid 
  @Schema(name = "total_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("total_amount")
  public @Nullable BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(@Nullable BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  public FeeCalculationPatch vatIndicator(@Nullable Boolean vatIndicator) {
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

  public FeeCalculationPatch vatRateApplied(@Nullable BigDecimal vatRateApplied) {
    this.vatRateApplied = vatRateApplied;
    return this;
  }

  /**
   * Get vatRateApplied
   * @return vatRateApplied
   */
  @Valid 
  @Schema(name = "vat_rate_applied", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("vat_rate_applied")
  public @Nullable BigDecimal getVatRateApplied() {
    return vatRateApplied;
  }

  public void setVatRateApplied(@Nullable BigDecimal vatRateApplied) {
    this.vatRateApplied = vatRateApplied;
  }

  public FeeCalculationPatch calculatedVatAmount(@Nullable BigDecimal calculatedVatAmount) {
    this.calculatedVatAmount = calculatedVatAmount;
    return this;
  }

  /**
   * Get calculatedVatAmount
   * @return calculatedVatAmount
   */
  @Valid 
  @Schema(name = "calculated_vat_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("calculated_vat_amount")
  public @Nullable BigDecimal getCalculatedVatAmount() {
    return calculatedVatAmount;
  }

  public void setCalculatedVatAmount(@Nullable BigDecimal calculatedVatAmount) {
    this.calculatedVatAmount = calculatedVatAmount;
  }

  public FeeCalculationPatch disbursementAmount(@Nullable BigDecimal disbursementAmount) {
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

  public FeeCalculationPatch requestedNetDisbursementAmount(@Nullable BigDecimal requestedNetDisbursementAmount) {
    this.requestedNetDisbursementAmount = requestedNetDisbursementAmount;
    return this;
  }

  /**
   * Get requestedNetDisbursementAmount
   * @return requestedNetDisbursementAmount
   */
  @Valid 
  @Schema(name = "requested_net_disbursement_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("requested_net_disbursement_amount")
  public @Nullable BigDecimal getRequestedNetDisbursementAmount() {
    return requestedNetDisbursementAmount;
  }

  public void setRequestedNetDisbursementAmount(@Nullable BigDecimal requestedNetDisbursementAmount) {
    this.requestedNetDisbursementAmount = requestedNetDisbursementAmount;
  }

  public FeeCalculationPatch disbursementVatAmount(@Nullable BigDecimal disbursementVatAmount) {
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

  public FeeCalculationPatch hourlyTotalAmount(@Nullable BigDecimal hourlyTotalAmount) {
    this.hourlyTotalAmount = hourlyTotalAmount;
    return this;
  }

  /**
   * Get hourlyTotalAmount
   * @return hourlyTotalAmount
   */
  @Valid 
  @Schema(name = "hourly_total_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("hourly_total_amount")
  public @Nullable BigDecimal getHourlyTotalAmount() {
    return hourlyTotalAmount;
  }

  public void setHourlyTotalAmount(@Nullable BigDecimal hourlyTotalAmount) {
    this.hourlyTotalAmount = hourlyTotalAmount;
  }

  public FeeCalculationPatch fixedFeeAmount(@Nullable BigDecimal fixedFeeAmount) {
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

  public FeeCalculationPatch netProfitCostsAmount(@Nullable BigDecimal netProfitCostsAmount) {
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

  public FeeCalculationPatch requestedNetProfitCostsAmount(@Nullable BigDecimal requestedNetProfitCostsAmount) {
    this.requestedNetProfitCostsAmount = requestedNetProfitCostsAmount;
    return this;
  }

  /**
   * Get requestedNetProfitCostsAmount
   * @return requestedNetProfitCostsAmount
   */
  @Valid 
  @Schema(name = "requested_net_profit_costs_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("requested_net_profit_costs_amount")
  public @Nullable BigDecimal getRequestedNetProfitCostsAmount() {
    return requestedNetProfitCostsAmount;
  }

  public void setRequestedNetProfitCostsAmount(@Nullable BigDecimal requestedNetProfitCostsAmount) {
    this.requestedNetProfitCostsAmount = requestedNetProfitCostsAmount;
  }

  public FeeCalculationPatch netCostOfCounselAmount(@Nullable BigDecimal netCostOfCounselAmount) {
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

  public FeeCalculationPatch netTravelCostsAmount(@Nullable BigDecimal netTravelCostsAmount) {
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

  public FeeCalculationPatch netWaitingCostsAmount(@Nullable BigDecimal netWaitingCostsAmount) {
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

  public FeeCalculationPatch detentionTravelAndWaitingCostsAmount(@Nullable BigDecimal detentionTravelAndWaitingCostsAmount) {
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

  public FeeCalculationPatch jrFormFillingAmount(@Nullable BigDecimal jrFormFillingAmount) {
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

  public FeeCalculationPatch travelAndWaitingCostsAmount(@Nullable BigDecimal travelAndWaitingCostsAmount) {
    this.travelAndWaitingCostsAmount = travelAndWaitingCostsAmount;
    return this;
  }

  /**
   * Get travelAndWaitingCostsAmount
   * @return travelAndWaitingCostsAmount
   */
  @Valid 
  @Schema(name = "travel_and_waiting_costs_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("travel_and_waiting_costs_amount")
  public @Nullable BigDecimal getTravelAndWaitingCostsAmount() {
    return travelAndWaitingCostsAmount;
  }

  public void setTravelAndWaitingCostsAmount(@Nullable BigDecimal travelAndWaitingCostsAmount) {
    this.travelAndWaitingCostsAmount = travelAndWaitingCostsAmount;
  }

  public FeeCalculationPatch boltOnDetails(@Nullable BoltOnPatch boltOnDetails) {
    this.boltOnDetails = boltOnDetails;
    return this;
  }

  /**
   * Get boltOnDetails
   * @return boltOnDetails
   */
  @Valid 
  @Schema(name = "bolt_on_details", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bolt_on_details")
  public @Nullable BoltOnPatch getBoltOnDetails() {
    return boltOnDetails;
  }

  public void setBoltOnDetails(@Nullable BoltOnPatch boltOnDetails) {
    this.boltOnDetails = boltOnDetails;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FeeCalculationPatch feeCalculationPatch = (FeeCalculationPatch) o;
    return Objects.equals(this.calculatedFeeDetailId, feeCalculationPatch.calculatedFeeDetailId) &&
        Objects.equals(this.claimSummaryFeeId, feeCalculationPatch.claimSummaryFeeId) &&
        Objects.equals(this.claimId, feeCalculationPatch.claimId) &&
        Objects.equals(this.feeCode, feeCalculationPatch.feeCode) &&
        Objects.equals(this.feeCodeDescription, feeCalculationPatch.feeCodeDescription) &&
        Objects.equals(this.feeType, feeCalculationPatch.feeType) &&
        Objects.equals(this.categoryOfLaw, feeCalculationPatch.categoryOfLaw) &&
        Objects.equals(this.totalAmount, feeCalculationPatch.totalAmount) &&
        Objects.equals(this.vatIndicator, feeCalculationPatch.vatIndicator) &&
        Objects.equals(this.vatRateApplied, feeCalculationPatch.vatRateApplied) &&
        Objects.equals(this.calculatedVatAmount, feeCalculationPatch.calculatedVatAmount) &&
        Objects.equals(this.disbursementAmount, feeCalculationPatch.disbursementAmount) &&
        Objects.equals(this.requestedNetDisbursementAmount, feeCalculationPatch.requestedNetDisbursementAmount) &&
        Objects.equals(this.disbursementVatAmount, feeCalculationPatch.disbursementVatAmount) &&
        Objects.equals(this.hourlyTotalAmount, feeCalculationPatch.hourlyTotalAmount) &&
        Objects.equals(this.fixedFeeAmount, feeCalculationPatch.fixedFeeAmount) &&
        Objects.equals(this.netProfitCostsAmount, feeCalculationPatch.netProfitCostsAmount) &&
        Objects.equals(this.requestedNetProfitCostsAmount, feeCalculationPatch.requestedNetProfitCostsAmount) &&
        Objects.equals(this.netCostOfCounselAmount, feeCalculationPatch.netCostOfCounselAmount) &&
        Objects.equals(this.netTravelCostsAmount, feeCalculationPatch.netTravelCostsAmount) &&
        Objects.equals(this.netWaitingCostsAmount, feeCalculationPatch.netWaitingCostsAmount) &&
        Objects.equals(this.detentionTravelAndWaitingCostsAmount, feeCalculationPatch.detentionTravelAndWaitingCostsAmount) &&
        Objects.equals(this.jrFormFillingAmount, feeCalculationPatch.jrFormFillingAmount) &&
        Objects.equals(this.travelAndWaitingCostsAmount, feeCalculationPatch.travelAndWaitingCostsAmount) &&
        Objects.equals(this.boltOnDetails, feeCalculationPatch.boltOnDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(calculatedFeeDetailId, claimSummaryFeeId, claimId, feeCode, feeCodeDescription, feeType, categoryOfLaw, totalAmount, vatIndicator, vatRateApplied, calculatedVatAmount, disbursementAmount, requestedNetDisbursementAmount, disbursementVatAmount, hourlyTotalAmount, fixedFeeAmount, netProfitCostsAmount, requestedNetProfitCostsAmount, netCostOfCounselAmount, netTravelCostsAmount, netWaitingCostsAmount, detentionTravelAndWaitingCostsAmount, jrFormFillingAmount, travelAndWaitingCostsAmount, boltOnDetails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FeeCalculationPatch {\n");
    sb.append("    calculatedFeeDetailId: ").append(toIndentedString(calculatedFeeDetailId)).append("\n");
    sb.append("    claimSummaryFeeId: ").append(toIndentedString(claimSummaryFeeId)).append("\n");
    sb.append("    claimId: ").append(toIndentedString(claimId)).append("\n");
    sb.append("    feeCode: ").append(toIndentedString(feeCode)).append("\n");
    sb.append("    feeCodeDescription: ").append(toIndentedString(feeCodeDescription)).append("\n");
    sb.append("    feeType: ").append(toIndentedString(feeType)).append("\n");
    sb.append("    categoryOfLaw: ").append(toIndentedString(categoryOfLaw)).append("\n");
    sb.append("    totalAmount: ").append(toIndentedString(totalAmount)).append("\n");
    sb.append("    vatIndicator: ").append(toIndentedString(vatIndicator)).append("\n");
    sb.append("    vatRateApplied: ").append(toIndentedString(vatRateApplied)).append("\n");
    sb.append("    calculatedVatAmount: ").append(toIndentedString(calculatedVatAmount)).append("\n");
    sb.append("    disbursementAmount: ").append(toIndentedString(disbursementAmount)).append("\n");
    sb.append("    requestedNetDisbursementAmount: ").append(toIndentedString(requestedNetDisbursementAmount)).append("\n");
    sb.append("    disbursementVatAmount: ").append(toIndentedString(disbursementVatAmount)).append("\n");
    sb.append("    hourlyTotalAmount: ").append(toIndentedString(hourlyTotalAmount)).append("\n");
    sb.append("    fixedFeeAmount: ").append(toIndentedString(fixedFeeAmount)).append("\n");
    sb.append("    netProfitCostsAmount: ").append(toIndentedString(netProfitCostsAmount)).append("\n");
    sb.append("    requestedNetProfitCostsAmount: ").append(toIndentedString(requestedNetProfitCostsAmount)).append("\n");
    sb.append("    netCostOfCounselAmount: ").append(toIndentedString(netCostOfCounselAmount)).append("\n");
    sb.append("    netTravelCostsAmount: ").append(toIndentedString(netTravelCostsAmount)).append("\n");
    sb.append("    netWaitingCostsAmount: ").append(toIndentedString(netWaitingCostsAmount)).append("\n");
    sb.append("    detentionTravelAndWaitingCostsAmount: ").append(toIndentedString(detentionTravelAndWaitingCostsAmount)).append("\n");
    sb.append("    jrFormFillingAmount: ").append(toIndentedString(jrFormFillingAmount)).append("\n");
    sb.append("    travelAndWaitingCostsAmount: ").append(toIndentedString(travelAndWaitingCostsAmount)).append("\n");
    sb.append("    boltOnDetails: ").append(toIndentedString(boltOnDetails)).append("\n");
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

    private FeeCalculationPatch instance;

    public Builder() {
      this(new FeeCalculationPatch());
    }

    protected Builder(FeeCalculationPatch instance) {
      this.instance = instance;
    }

    protected Builder copyOf(FeeCalculationPatch value) { 
      this.instance.setCalculatedFeeDetailId(value.calculatedFeeDetailId);
      this.instance.setClaimSummaryFeeId(value.claimSummaryFeeId);
      this.instance.setClaimId(value.claimId);
      this.instance.setFeeCode(value.feeCode);
      this.instance.setFeeCodeDescription(value.feeCodeDescription);
      this.instance.setFeeType(value.feeType);
      this.instance.setCategoryOfLaw(value.categoryOfLaw);
      this.instance.setTotalAmount(value.totalAmount);
      this.instance.setVatIndicator(value.vatIndicator);
      this.instance.setVatRateApplied(value.vatRateApplied);
      this.instance.setCalculatedVatAmount(value.calculatedVatAmount);
      this.instance.setDisbursementAmount(value.disbursementAmount);
      this.instance.setRequestedNetDisbursementAmount(value.requestedNetDisbursementAmount);
      this.instance.setDisbursementVatAmount(value.disbursementVatAmount);
      this.instance.setHourlyTotalAmount(value.hourlyTotalAmount);
      this.instance.setFixedFeeAmount(value.fixedFeeAmount);
      this.instance.setNetProfitCostsAmount(value.netProfitCostsAmount);
      this.instance.setRequestedNetProfitCostsAmount(value.requestedNetProfitCostsAmount);
      this.instance.setNetCostOfCounselAmount(value.netCostOfCounselAmount);
      this.instance.setNetTravelCostsAmount(value.netTravelCostsAmount);
      this.instance.setNetWaitingCostsAmount(value.netWaitingCostsAmount);
      this.instance.setDetentionTravelAndWaitingCostsAmount(value.detentionTravelAndWaitingCostsAmount);
      this.instance.setJrFormFillingAmount(value.jrFormFillingAmount);
      this.instance.setTravelAndWaitingCostsAmount(value.travelAndWaitingCostsAmount);
      this.instance.setBoltOnDetails(value.boltOnDetails);
      return this;
    }

    public FeeCalculationPatch.Builder calculatedFeeDetailId(String calculatedFeeDetailId) {
      this.instance.calculatedFeeDetailId(calculatedFeeDetailId);
      return this;
    }
    
    public FeeCalculationPatch.Builder claimSummaryFeeId(UUID claimSummaryFeeId) {
      this.instance.claimSummaryFeeId(claimSummaryFeeId);
      return this;
    }
    
    public FeeCalculationPatch.Builder claimId(UUID claimId) {
      this.instance.claimId(claimId);
      return this;
    }
    
    public FeeCalculationPatch.Builder feeCode(String feeCode) {
      this.instance.feeCode(feeCode);
      return this;
    }
    
    public FeeCalculationPatch.Builder feeCodeDescription(String feeCodeDescription) {
      this.instance.feeCodeDescription(feeCodeDescription);
      return this;
    }
    
    public FeeCalculationPatch.Builder feeType(FeeCalculationType feeType) {
      this.instance.feeType(feeType);
      return this;
    }
    
    public FeeCalculationPatch.Builder categoryOfLaw(String categoryOfLaw) {
      this.instance.categoryOfLaw(categoryOfLaw);
      return this;
    }
    
    public FeeCalculationPatch.Builder totalAmount(BigDecimal totalAmount) {
      this.instance.totalAmount(totalAmount);
      return this;
    }
    
    public FeeCalculationPatch.Builder vatIndicator(Boolean vatIndicator) {
      this.instance.vatIndicator(vatIndicator);
      return this;
    }
    
    public FeeCalculationPatch.Builder vatRateApplied(BigDecimal vatRateApplied) {
      this.instance.vatRateApplied(vatRateApplied);
      return this;
    }
    
    public FeeCalculationPatch.Builder calculatedVatAmount(BigDecimal calculatedVatAmount) {
      this.instance.calculatedVatAmount(calculatedVatAmount);
      return this;
    }
    
    public FeeCalculationPatch.Builder disbursementAmount(BigDecimal disbursementAmount) {
      this.instance.disbursementAmount(disbursementAmount);
      return this;
    }
    
    public FeeCalculationPatch.Builder requestedNetDisbursementAmount(BigDecimal requestedNetDisbursementAmount) {
      this.instance.requestedNetDisbursementAmount(requestedNetDisbursementAmount);
      return this;
    }
    
    public FeeCalculationPatch.Builder disbursementVatAmount(BigDecimal disbursementVatAmount) {
      this.instance.disbursementVatAmount(disbursementVatAmount);
      return this;
    }
    
    public FeeCalculationPatch.Builder hourlyTotalAmount(BigDecimal hourlyTotalAmount) {
      this.instance.hourlyTotalAmount(hourlyTotalAmount);
      return this;
    }
    
    public FeeCalculationPatch.Builder fixedFeeAmount(BigDecimal fixedFeeAmount) {
      this.instance.fixedFeeAmount(fixedFeeAmount);
      return this;
    }
    
    public FeeCalculationPatch.Builder netProfitCostsAmount(BigDecimal netProfitCostsAmount) {
      this.instance.netProfitCostsAmount(netProfitCostsAmount);
      return this;
    }
    
    public FeeCalculationPatch.Builder requestedNetProfitCostsAmount(BigDecimal requestedNetProfitCostsAmount) {
      this.instance.requestedNetProfitCostsAmount(requestedNetProfitCostsAmount);
      return this;
    }
    
    public FeeCalculationPatch.Builder netCostOfCounselAmount(BigDecimal netCostOfCounselAmount) {
      this.instance.netCostOfCounselAmount(netCostOfCounselAmount);
      return this;
    }
    
    public FeeCalculationPatch.Builder netTravelCostsAmount(BigDecimal netTravelCostsAmount) {
      this.instance.netTravelCostsAmount(netTravelCostsAmount);
      return this;
    }
    
    public FeeCalculationPatch.Builder netWaitingCostsAmount(BigDecimal netWaitingCostsAmount) {
      this.instance.netWaitingCostsAmount(netWaitingCostsAmount);
      return this;
    }
    
    public FeeCalculationPatch.Builder detentionTravelAndWaitingCostsAmount(BigDecimal detentionTravelAndWaitingCostsAmount) {
      this.instance.detentionTravelAndWaitingCostsAmount(detentionTravelAndWaitingCostsAmount);
      return this;
    }
    
    public FeeCalculationPatch.Builder jrFormFillingAmount(BigDecimal jrFormFillingAmount) {
      this.instance.jrFormFillingAmount(jrFormFillingAmount);
      return this;
    }
    
    public FeeCalculationPatch.Builder travelAndWaitingCostsAmount(BigDecimal travelAndWaitingCostsAmount) {
      this.instance.travelAndWaitingCostsAmount(travelAndWaitingCostsAmount);
      return this;
    }
    
    public FeeCalculationPatch.Builder boltOnDetails(BoltOnPatch boltOnDetails) {
      this.instance.boltOnDetails(boltOnDetails);
      return this;
    }
    
    /**
    * returns a built FeeCalculationPatch instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public FeeCalculationPatch build() {
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
  public static FeeCalculationPatch.Builder builder() {
    return new FeeCalculationPatch.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public FeeCalculationPatch.Builder toBuilder() {
    FeeCalculationPatch.Builder builder = new FeeCalculationPatch.Builder();
    return builder.copyOf(this);
  }

}

