package uk.gov.justice.laa.fee.scheme.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.fee.scheme.model.BoltOnFeeDetails;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * FeeCalculation
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-17T22:25:28.509841Z[Europe/London]", comments = "Generator version: 7.14.0")
public class FeeCalculation implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable Double totalAmount;

  private @Nullable Boolean vatIndicator;

  private @Nullable Double vatRateApplied;

  private @Nullable Double calculatedVatAmount;

  private @Nullable Double disbursementAmount;

  private @Nullable Double requestedNetDisbursementAmount;

  private @Nullable Double disbursementVatAmount;

  private @Nullable Double hourlyTotalAmount;

  private @Nullable Double fixedFeeAmount;

  private @Nullable Double netProfitCostsAmount;

  private @Nullable Double requestedNetProfitCostsAmount;

  private @Nullable Double netCostOfCounselAmount;

  private @Nullable Double netTravelCostsAmount;

  private @Nullable Double netWaitingCostsAmount;

  private @Nullable Double detentionTravelAndWaitingCostsAmount;

  private @Nullable Double jrFormFillingAmount;

  private @Nullable Double travelAndWaitingCostAmount;

  private @Nullable BoltOnFeeDetails boltOnFeeDetails;

  public FeeCalculation totalAmount(@Nullable Double totalAmount) {
    this.totalAmount = totalAmount;
    return this;
  }

  /**
   * The total fee amount that has been calculated.
   * @return totalAmount
   */
  
  @Schema(name = "totalAmount", description = "The total fee amount that has been calculated.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("totalAmount")
  public @Nullable Double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(@Nullable Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  public FeeCalculation vatIndicator(@Nullable Boolean vatIndicator) {
    this.vatIndicator = vatIndicator;
    return this;
  }

  /**
   * Whether Vat was requested to be added onto calculation (excluding disbursement).
   * @return vatIndicator
   */
  
  @Schema(name = "vatIndicator", description = "Whether Vat was requested to be added onto calculation (excluding disbursement).", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("vatIndicator")
  public @Nullable Boolean getVatIndicator() {
    return vatIndicator;
  }

  public void setVatIndicator(@Nullable Boolean vatIndicator) {
    this.vatIndicator = vatIndicator;
  }

  public FeeCalculation vatRateApplied(@Nullable Double vatRateApplied) {
    this.vatRateApplied = vatRateApplied;
    return this;
  }

  /**
   * The Vat rate that was used in calculation.
   * @return vatRateApplied
   */
  
  @Schema(name = "vatRateApplied", description = "The Vat rate that was used in calculation.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("vatRateApplied")
  public @Nullable Double getVatRateApplied() {
    return vatRateApplied;
  }

  public void setVatRateApplied(@Nullable Double vatRateApplied) {
    this.vatRateApplied = vatRateApplied;
  }

  public FeeCalculation calculatedVatAmount(@Nullable Double calculatedVatAmount) {
    this.calculatedVatAmount = calculatedVatAmount;
    return this;
  }

  /**
   * Total amount of Vat that was calculated (excluding disbursement).
   * @return calculatedVatAmount
   */
  
  @Schema(name = "calculatedVatAmount", description = "Total amount of Vat that was calculated (excluding disbursement).", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("calculatedVatAmount")
  public @Nullable Double getCalculatedVatAmount() {
    return calculatedVatAmount;
  }

  public void setCalculatedVatAmount(@Nullable Double calculatedVatAmount) {
    this.calculatedVatAmount = calculatedVatAmount;
  }

  public FeeCalculation disbursementAmount(@Nullable Double disbursementAmount) {
    this.disbursementAmount = disbursementAmount;
    return this;
  }

  /**
   * The disbursement amount calculated to be added.
   * @return disbursementAmount
   */
  
  @Schema(name = "disbursementAmount", description = "The disbursement amount calculated to be added.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("disbursementAmount")
  public @Nullable Double getDisbursementAmount() {
    return disbursementAmount;
  }

  public void setDisbursementAmount(@Nullable Double disbursementAmount) {
    this.disbursementAmount = disbursementAmount;
  }

  public FeeCalculation requestedNetDisbursementAmount(@Nullable Double requestedNetDisbursementAmount) {
    this.requestedNetDisbursementAmount = requestedNetDisbursementAmount;
    return this;
  }

  /**
   * The disbursement amount that was requested.
   * @return requestedNetDisbursementAmount
   */
  
  @Schema(name = "requestedNetDisbursementAmount", description = "The disbursement amount that was requested.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("requestedNetDisbursementAmount")
  public @Nullable Double getRequestedNetDisbursementAmount() {
    return requestedNetDisbursementAmount;
  }

  public void setRequestedNetDisbursementAmount(@Nullable Double requestedNetDisbursementAmount) {
    this.requestedNetDisbursementAmount = requestedNetDisbursementAmount;
  }

  public FeeCalculation disbursementVatAmount(@Nullable Double disbursementVatAmount) {
    this.disbursementVatAmount = disbursementVatAmount;
    return this;
  }

  /**
   * The disbursement Vat amount calculated to be added.
   * @return disbursementVatAmount
   */
  
  @Schema(name = "disbursementVatAmount", description = "The disbursement Vat amount calculated to be added.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("disbursementVatAmount")
  public @Nullable Double getDisbursementVatAmount() {
    return disbursementVatAmount;
  }

  public void setDisbursementVatAmount(@Nullable Double disbursementVatAmount) {
    this.disbursementVatAmount = disbursementVatAmount;
  }

  public FeeCalculation hourlyTotalAmount(@Nullable Double hourlyTotalAmount) {
    this.hourlyTotalAmount = hourlyTotalAmount;
    return this;
  }

  /**
   * Hourly total amount calculated.
   * @return hourlyTotalAmount
   */
  
  @Schema(name = "hourlyTotalAmount", description = "Hourly total amount calculated.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("hourlyTotalAmount")
  public @Nullable Double getHourlyTotalAmount() {
    return hourlyTotalAmount;
  }

  public void setHourlyTotalAmount(@Nullable Double hourlyTotalAmount) {
    this.hourlyTotalAmount = hourlyTotalAmount;
  }

  public FeeCalculation fixedFeeAmount(@Nullable Double fixedFeeAmount) {
    this.fixedFeeAmount = fixedFeeAmount;
    return this;
  }

  /**
   * Fixed Fee amount calculated.
   * @return fixedFeeAmount
   */
  
  @Schema(name = "fixedFeeAmount", description = "Fixed Fee amount calculated.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fixedFeeAmount")
  public @Nullable Double getFixedFeeAmount() {
    return fixedFeeAmount;
  }

  public void setFixedFeeAmount(@Nullable Double fixedFeeAmount) {
    this.fixedFeeAmount = fixedFeeAmount;
  }

  public FeeCalculation netProfitCostsAmount(@Nullable Double netProfitCostsAmount) {
    this.netProfitCostsAmount = netProfitCostsAmount;
    return this;
  }

  /**
   * The net profit cost amount calculated to be added.
   * @return netProfitCostsAmount
   */
  
  @Schema(name = "netProfitCostsAmount", description = "The net profit cost amount calculated to be added.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("netProfitCostsAmount")
  public @Nullable Double getNetProfitCostsAmount() {
    return netProfitCostsAmount;
  }

  public void setNetProfitCostsAmount(@Nullable Double netProfitCostsAmount) {
    this.netProfitCostsAmount = netProfitCostsAmount;
  }

  public FeeCalculation requestedNetProfitCostsAmount(@Nullable Double requestedNetProfitCostsAmount) {
    this.requestedNetProfitCostsAmount = requestedNetProfitCostsAmount;
    return this;
  }

  /**
   * The profit cost amount requested.
   * @return requestedNetProfitCostsAmount
   */
  
  @Schema(name = "requestedNetProfitCostsAmount", description = "The profit cost amount requested.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("requestedNetProfitCostsAmount")
  public @Nullable Double getRequestedNetProfitCostsAmount() {
    return requestedNetProfitCostsAmount;
  }

  public void setRequestedNetProfitCostsAmount(@Nullable Double requestedNetProfitCostsAmount) {
    this.requestedNetProfitCostsAmount = requestedNetProfitCostsAmount;
  }

  public FeeCalculation netCostOfCounselAmount(@Nullable Double netCostOfCounselAmount) {
    this.netCostOfCounselAmount = netCostOfCounselAmount;
    return this;
  }

  /**
   * The cost of counsel requested.
   * @return netCostOfCounselAmount
   */
  
  @Schema(name = "netCostOfCounselAmount", description = "The cost of counsel requested.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("netCostOfCounselAmount")
  public @Nullable Double getNetCostOfCounselAmount() {
    return netCostOfCounselAmount;
  }

  public void setNetCostOfCounselAmount(@Nullable Double netCostOfCounselAmount) {
    this.netCostOfCounselAmount = netCostOfCounselAmount;
  }

  public FeeCalculation netTravelCostsAmount(@Nullable Double netTravelCostsAmount) {
    this.netTravelCostsAmount = netTravelCostsAmount;
    return this;
  }

  /**
   * The net travel cost requested.
   * @return netTravelCostsAmount
   */
  
  @Schema(name = "netTravelCostsAmount", description = "The net travel cost requested.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("netTravelCostsAmount")
  public @Nullable Double getNetTravelCostsAmount() {
    return netTravelCostsAmount;
  }

  public void setNetTravelCostsAmount(@Nullable Double netTravelCostsAmount) {
    this.netTravelCostsAmount = netTravelCostsAmount;
  }

  public FeeCalculation netWaitingCostsAmount(@Nullable Double netWaitingCostsAmount) {
    this.netWaitingCostsAmount = netWaitingCostsAmount;
    return this;
  }

  /**
   * The net waiting cost requested.
   * @return netWaitingCostsAmount
   */
  
  @Schema(name = "netWaitingCostsAmount", description = "The net waiting cost requested.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("netWaitingCostsAmount")
  public @Nullable Double getNetWaitingCostsAmount() {
    return netWaitingCostsAmount;
  }

  public void setNetWaitingCostsAmount(@Nullable Double netWaitingCostsAmount) {
    this.netWaitingCostsAmount = netWaitingCostsAmount;
  }

  public FeeCalculation detentionTravelAndWaitingCostsAmount(@Nullable Double detentionTravelAndWaitingCostsAmount) {
    this.detentionTravelAndWaitingCostsAmount = detentionTravelAndWaitingCostsAmount;
    return this;
  }

  /**
   * The detention travel and waiting costs requested.
   * @return detentionTravelAndWaitingCostsAmount
   */
  
  @Schema(name = "detentionTravelAndWaitingCostsAmount", description = "The detention travel and waiting costs requested.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("detentionTravelAndWaitingCostsAmount")
  public @Nullable Double getDetentionTravelAndWaitingCostsAmount() {
    return detentionTravelAndWaitingCostsAmount;
  }

  public void setDetentionTravelAndWaitingCostsAmount(@Nullable Double detentionTravelAndWaitingCostsAmount) {
    this.detentionTravelAndWaitingCostsAmount = detentionTravelAndWaitingCostsAmount;
  }

  public FeeCalculation jrFormFillingAmount(@Nullable Double jrFormFillingAmount) {
    this.jrFormFillingAmount = jrFormFillingAmount;
    return this;
  }

  /**
   * The Jr form filling costs requested.
   * @return jrFormFillingAmount
   */
  
  @Schema(name = "jrFormFillingAmount", description = "The Jr form filling costs requested.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("jrFormFillingAmount")
  public @Nullable Double getJrFormFillingAmount() {
    return jrFormFillingAmount;
  }

  public void setJrFormFillingAmount(@Nullable Double jrFormFillingAmount) {
    this.jrFormFillingAmount = jrFormFillingAmount;
  }

  public FeeCalculation travelAndWaitingCostAmount(@Nullable Double travelAndWaitingCostAmount) {
    this.travelAndWaitingCostAmount = travelAndWaitingCostAmount;
    return this;
  }

  /**
   * The Travel and waiting costs requested.
   * @return travelAndWaitingCostAmount
   */
  
  @Schema(name = "travelAndWaitingCostAmount", description = "The Travel and waiting costs requested.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("travelAndWaitingCostAmount")
  public @Nullable Double getTravelAndWaitingCostAmount() {
    return travelAndWaitingCostAmount;
  }

  public void setTravelAndWaitingCostAmount(@Nullable Double travelAndWaitingCostAmount) {
    this.travelAndWaitingCostAmount = travelAndWaitingCostAmount;
  }

  public FeeCalculation boltOnFeeDetails(@Nullable BoltOnFeeDetails boltOnFeeDetails) {
    this.boltOnFeeDetails = boltOnFeeDetails;
    return this;
  }

  /**
   * Get boltOnFeeDetails
   * @return boltOnFeeDetails
   */
  @Valid 
  @Schema(name = "boltOnFeeDetails", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("boltOnFeeDetails")
  public @Nullable BoltOnFeeDetails getBoltOnFeeDetails() {
    return boltOnFeeDetails;
  }

  public void setBoltOnFeeDetails(@Nullable BoltOnFeeDetails boltOnFeeDetails) {
    this.boltOnFeeDetails = boltOnFeeDetails;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FeeCalculation feeCalculation = (FeeCalculation) o;
    return Objects.equals(this.totalAmount, feeCalculation.totalAmount) &&
        Objects.equals(this.vatIndicator, feeCalculation.vatIndicator) &&
        Objects.equals(this.vatRateApplied, feeCalculation.vatRateApplied) &&
        Objects.equals(this.calculatedVatAmount, feeCalculation.calculatedVatAmount) &&
        Objects.equals(this.disbursementAmount, feeCalculation.disbursementAmount) &&
        Objects.equals(this.requestedNetDisbursementAmount, feeCalculation.requestedNetDisbursementAmount) &&
        Objects.equals(this.disbursementVatAmount, feeCalculation.disbursementVatAmount) &&
        Objects.equals(this.hourlyTotalAmount, feeCalculation.hourlyTotalAmount) &&
        Objects.equals(this.fixedFeeAmount, feeCalculation.fixedFeeAmount) &&
        Objects.equals(this.netProfitCostsAmount, feeCalculation.netProfitCostsAmount) &&
        Objects.equals(this.requestedNetProfitCostsAmount, feeCalculation.requestedNetProfitCostsAmount) &&
        Objects.equals(this.netCostOfCounselAmount, feeCalculation.netCostOfCounselAmount) &&
        Objects.equals(this.netTravelCostsAmount, feeCalculation.netTravelCostsAmount) &&
        Objects.equals(this.netWaitingCostsAmount, feeCalculation.netWaitingCostsAmount) &&
        Objects.equals(this.detentionTravelAndWaitingCostsAmount, feeCalculation.detentionTravelAndWaitingCostsAmount) &&
        Objects.equals(this.jrFormFillingAmount, feeCalculation.jrFormFillingAmount) &&
        Objects.equals(this.travelAndWaitingCostAmount, feeCalculation.travelAndWaitingCostAmount) &&
        Objects.equals(this.boltOnFeeDetails, feeCalculation.boltOnFeeDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(totalAmount, vatIndicator, vatRateApplied, calculatedVatAmount, disbursementAmount, requestedNetDisbursementAmount, disbursementVatAmount, hourlyTotalAmount, fixedFeeAmount, netProfitCostsAmount, requestedNetProfitCostsAmount, netCostOfCounselAmount, netTravelCostsAmount, netWaitingCostsAmount, detentionTravelAndWaitingCostsAmount, jrFormFillingAmount, travelAndWaitingCostAmount, boltOnFeeDetails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FeeCalculation {\n");
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
    sb.append("    travelAndWaitingCostAmount: ").append(toIndentedString(travelAndWaitingCostAmount)).append("\n");
    sb.append("    boltOnFeeDetails: ").append(toIndentedString(boltOnFeeDetails)).append("\n");
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

    private FeeCalculation instance;

    public Builder() {
      this(new FeeCalculation());
    }

    protected Builder(FeeCalculation instance) {
      this.instance = instance;
    }

    protected Builder copyOf(FeeCalculation value) { 
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
      this.instance.setTravelAndWaitingCostAmount(value.travelAndWaitingCostAmount);
      this.instance.setBoltOnFeeDetails(value.boltOnFeeDetails);
      return this;
    }

    public FeeCalculation.Builder totalAmount(Double totalAmount) {
      this.instance.totalAmount(totalAmount);
      return this;
    }
    
    public FeeCalculation.Builder vatIndicator(Boolean vatIndicator) {
      this.instance.vatIndicator(vatIndicator);
      return this;
    }
    
    public FeeCalculation.Builder vatRateApplied(Double vatRateApplied) {
      this.instance.vatRateApplied(vatRateApplied);
      return this;
    }
    
    public FeeCalculation.Builder calculatedVatAmount(Double calculatedVatAmount) {
      this.instance.calculatedVatAmount(calculatedVatAmount);
      return this;
    }
    
    public FeeCalculation.Builder disbursementAmount(Double disbursementAmount) {
      this.instance.disbursementAmount(disbursementAmount);
      return this;
    }
    
    public FeeCalculation.Builder requestedNetDisbursementAmount(Double requestedNetDisbursementAmount) {
      this.instance.requestedNetDisbursementAmount(requestedNetDisbursementAmount);
      return this;
    }
    
    public FeeCalculation.Builder disbursementVatAmount(Double disbursementVatAmount) {
      this.instance.disbursementVatAmount(disbursementVatAmount);
      return this;
    }
    
    public FeeCalculation.Builder hourlyTotalAmount(Double hourlyTotalAmount) {
      this.instance.hourlyTotalAmount(hourlyTotalAmount);
      return this;
    }
    
    public FeeCalculation.Builder fixedFeeAmount(Double fixedFeeAmount) {
      this.instance.fixedFeeAmount(fixedFeeAmount);
      return this;
    }
    
    public FeeCalculation.Builder netProfitCostsAmount(Double netProfitCostsAmount) {
      this.instance.netProfitCostsAmount(netProfitCostsAmount);
      return this;
    }
    
    public FeeCalculation.Builder requestedNetProfitCostsAmount(Double requestedNetProfitCostsAmount) {
      this.instance.requestedNetProfitCostsAmount(requestedNetProfitCostsAmount);
      return this;
    }
    
    public FeeCalculation.Builder netCostOfCounselAmount(Double netCostOfCounselAmount) {
      this.instance.netCostOfCounselAmount(netCostOfCounselAmount);
      return this;
    }
    
    public FeeCalculation.Builder netTravelCostsAmount(Double netTravelCostsAmount) {
      this.instance.netTravelCostsAmount(netTravelCostsAmount);
      return this;
    }
    
    public FeeCalculation.Builder netWaitingCostsAmount(Double netWaitingCostsAmount) {
      this.instance.netWaitingCostsAmount(netWaitingCostsAmount);
      return this;
    }
    
    public FeeCalculation.Builder detentionTravelAndWaitingCostsAmount(Double detentionTravelAndWaitingCostsAmount) {
      this.instance.detentionTravelAndWaitingCostsAmount(detentionTravelAndWaitingCostsAmount);
      return this;
    }
    
    public FeeCalculation.Builder jrFormFillingAmount(Double jrFormFillingAmount) {
      this.instance.jrFormFillingAmount(jrFormFillingAmount);
      return this;
    }
    
    public FeeCalculation.Builder travelAndWaitingCostAmount(Double travelAndWaitingCostAmount) {
      this.instance.travelAndWaitingCostAmount(travelAndWaitingCostAmount);
      return this;
    }
    
    public FeeCalculation.Builder boltOnFeeDetails(BoltOnFeeDetails boltOnFeeDetails) {
      this.instance.boltOnFeeDetails(boltOnFeeDetails);
      return this;
    }
    
    /**
    * returns a built FeeCalculation instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public FeeCalculation build() {
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
  public static FeeCalculation.Builder builder() {
    return new FeeCalculation.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public FeeCalculation.Builder toBuilder() {
    FeeCalculation.Builder builder = new FeeCalculation.Builder();
    return builder.copyOf(this);
  }

}

