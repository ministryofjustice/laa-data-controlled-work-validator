package uk.gov.justice.laa.fee.scheme.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.fee.scheme.model.BoltOnType;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * FeeCalculationRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T21:51:32.190887Z[Europe/London]", comments = "Generator version: 7.14.0")
public class FeeCalculationRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String feeCode;

  private @Nullable String claimId;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate startDate;

  private @Nullable String policeStationId;

  private @Nullable String policeStationSchemeId;

  private @Nullable String uniqueFileNumber;

  private @Nullable Double netProfitCosts;

  private @Nullable Double netCostOfCounsel;

  private @Nullable Double netDisbursementAmount;

  private @Nullable Double disbursementVatAmount;

  private @Nullable Boolean vatIndicator;

  private @Nullable BoltOnType boltOns;

  private @Nullable Double netTravelCosts;

  private @Nullable Double netWaitingCosts;

  private @Nullable Double travelAndWaitingCosts;

  private @Nullable Double detentionTravelAndWaitingCosts;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate caseConcludedDate;

  private @Nullable Integer numberOfMediationSessions;

  private @Nullable Double jrFormFilling;

  private @Nullable String immigrationPriorAuthorityNumber;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate representationOrderDate;

  private @Nullable Boolean londonRate;

  public FeeCalculationRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public FeeCalculationRequest(String feeCode) {
    this.feeCode = feeCode;
  }

  public FeeCalculationRequest feeCode(String feeCode) {
    this.feeCode = feeCode;
    return this;
  }

  /**
   * Fee code corresponding to a category of law.
   * @return feeCode
   */
  @NotNull 
  @Schema(name = "feeCode", description = "Fee code corresponding to a category of law.", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("feeCode")
  public String getFeeCode() {
    return feeCode;
  }

  public void setFeeCode(String feeCode) {
    this.feeCode = feeCode;
  }

  public FeeCalculationRequest claimId(@Nullable String claimId) {
    this.claimId = claimId;
    return this;
  }

  /**
   * Unique identifier for a Claim record.
   * @return claimId
   */
  
  @Schema(name = "claimId", description = "Unique identifier for a Claim record.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("claimId")
  public @Nullable String getClaimId() {
    return claimId;
  }

  public void setClaimId(@Nullable String claimId) {
    this.claimId = claimId;
  }

  public FeeCalculationRequest startDate(@Nullable LocalDate startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Legal Help: case start date.
   * @return startDate
   */
  @Valid 
  @Schema(name = "startDate", description = "Legal Help: case start date.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("startDate")
  public @Nullable LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(@Nullable LocalDate startDate) {
    this.startDate = startDate;
  }

  public FeeCalculationRequest policeStationId(@Nullable String policeStationId) {
    this.policeStationId = policeStationId;
    return this;
  }

  /**
   * Police Station ID.
   * @return policeStationId
   */
  
  @Schema(name = "policeStationId", description = "Police Station ID.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("policeStationId")
  public @Nullable String getPoliceStationId() {
    return policeStationId;
  }

  public void setPoliceStationId(@Nullable String policeStationId) {
    this.policeStationId = policeStationId;
  }

  public FeeCalculationRequest policeStationSchemeId(@Nullable String policeStationSchemeId) {
    this.policeStationSchemeId = policeStationSchemeId;
    return this;
  }

  /**
   * Police Station Scheme ID.
   * @return policeStationSchemeId
   */
  
  @Schema(name = "policeStationSchemeId", description = "Police Station Scheme ID.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("policeStationSchemeId")
  public @Nullable String getPoliceStationSchemeId() {
    return policeStationSchemeId;
  }

  public void setPoliceStationSchemeId(@Nullable String policeStationSchemeId) {
    this.policeStationSchemeId = policeStationSchemeId;
  }

  public FeeCalculationRequest uniqueFileNumber(@Nullable String uniqueFileNumber) {
    this.uniqueFileNumber = uniqueFileNumber;
    return this;
  }

  /**
   * Unique File Number (UFN) for Police Station/Prison Law category. Format DDMMYY/NNN
   * @return uniqueFileNumber
   */
  
  @Schema(name = "uniqueFileNumber", description = "Unique File Number (UFN) for Police Station/Prison Law category. Format DDMMYY/NNN", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("uniqueFileNumber")
  public @Nullable String getUniqueFileNumber() {
    return uniqueFileNumber;
  }

  public void setUniqueFileNumber(@Nullable String uniqueFileNumber) {
    this.uniqueFileNumber = uniqueFileNumber;
  }

  public FeeCalculationRequest netProfitCosts(@Nullable Double netProfitCosts) {
    this.netProfitCosts = netProfitCosts;
    return this;
  }

  /**
   * Used for hourly Fee calculation.
   * @return netProfitCosts
   */
  
  @Schema(name = "netProfitCosts", description = "Used for hourly Fee calculation.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("netProfitCosts")
  public @Nullable Double getNetProfitCosts() {
    return netProfitCosts;
  }

  public void setNetProfitCosts(@Nullable Double netProfitCosts) {
    this.netProfitCosts = netProfitCosts;
  }

  public FeeCalculationRequest netCostOfCounsel(@Nullable Double netCostOfCounsel) {
    this.netCostOfCounsel = netCostOfCounsel;
    return this;
  }

  /**
   * Value of cost of counsel.
   * @return netCostOfCounsel
   */
  
  @Schema(name = "netCostOfCounsel", description = "Value of cost of counsel.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("netCostOfCounsel")
  public @Nullable Double getNetCostOfCounsel() {
    return netCostOfCounsel;
  }

  public void setNetCostOfCounsel(@Nullable Double netCostOfCounsel) {
    this.netCostOfCounsel = netCostOfCounsel;
  }

  public FeeCalculationRequest netDisbursementAmount(@Nullable Double netDisbursementAmount) {
    this.netDisbursementAmount = netDisbursementAmount;
    return this;
  }

  /**
   * Value of disbursement requested.
   * @return netDisbursementAmount
   */
  
  @Schema(name = "netDisbursementAmount", description = "Value of disbursement requested.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("netDisbursementAmount")
  public @Nullable Double getNetDisbursementAmount() {
    return netDisbursementAmount;
  }

  public void setNetDisbursementAmount(@Nullable Double netDisbursementAmount) {
    this.netDisbursementAmount = netDisbursementAmount;
  }

  public FeeCalculationRequest disbursementVatAmount(@Nullable Double disbursementVatAmount) {
    this.disbursementVatAmount = disbursementVatAmount;
    return this;
  }

  /**
   * Vat Value of disbursement.
   * @return disbursementVatAmount
   */
  
  @Schema(name = "disbursementVatAmount", description = "Vat Value of disbursement.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("disbursementVatAmount")
  public @Nullable Double getDisbursementVatAmount() {
    return disbursementVatAmount;
  }

  public void setDisbursementVatAmount(@Nullable Double disbursementVatAmount) {
    this.disbursementVatAmount = disbursementVatAmount;
  }

  public FeeCalculationRequest vatIndicator(@Nullable Boolean vatIndicator) {
    this.vatIndicator = vatIndicator;
    return this;
  }

  /**
   * Indicates if VAT should be applied to Fee calculation.
   * @return vatIndicator
   */
  
  @Schema(name = "vatIndicator", description = "Indicates if VAT should be applied to Fee calculation.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("vatIndicator")
  public @Nullable Boolean getVatIndicator() {
    return vatIndicator;
  }

  public void setVatIndicator(@Nullable Boolean vatIndicator) {
    this.vatIndicator = vatIndicator;
  }

  public FeeCalculationRequest boltOns(@Nullable BoltOnType boltOns) {
    this.boltOns = boltOns;
    return this;
  }

  /**
   * Get boltOns
   * @return boltOns
   */
  @Valid 
  @Schema(name = "boltOns", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("boltOns")
  public @Nullable BoltOnType getBoltOns() {
    return boltOns;
  }

  public void setBoltOns(@Nullable BoltOnType boltOns) {
    this.boltOns = boltOns;
  }

  public FeeCalculationRequest netTravelCosts(@Nullable Double netTravelCosts) {
    this.netTravelCosts = netTravelCosts;
    return this;
  }

  /**
   * Value of travel costs.
   * @return netTravelCosts
   */
  
  @Schema(name = "netTravelCosts", description = "Value of travel costs.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("netTravelCosts")
  public @Nullable Double getNetTravelCosts() {
    return netTravelCosts;
  }

  public void setNetTravelCosts(@Nullable Double netTravelCosts) {
    this.netTravelCosts = netTravelCosts;
  }

  public FeeCalculationRequest netWaitingCosts(@Nullable Double netWaitingCosts) {
    this.netWaitingCosts = netWaitingCosts;
    return this;
  }

  /**
   * Value of waiting costs.
   * @return netWaitingCosts
   */
  
  @Schema(name = "netWaitingCosts", description = "Value of waiting costs.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("netWaitingCosts")
  public @Nullable Double getNetWaitingCosts() {
    return netWaitingCosts;
  }

  public void setNetWaitingCosts(@Nullable Double netWaitingCosts) {
    this.netWaitingCosts = netWaitingCosts;
  }

  public FeeCalculationRequest travelAndWaitingCosts(@Nullable Double travelAndWaitingCosts) {
    this.travelAndWaitingCosts = travelAndWaitingCosts;
    return this;
  }

  /**
   * Value of travel and waiting.
   * @return travelAndWaitingCosts
   */
  
  @Schema(name = "travelAndWaitingCosts", description = "Value of travel and waiting.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("travelAndWaitingCosts")
  public @Nullable Double getTravelAndWaitingCosts() {
    return travelAndWaitingCosts;
  }

  public void setTravelAndWaitingCosts(@Nullable Double travelAndWaitingCosts) {
    this.travelAndWaitingCosts = travelAndWaitingCosts;
  }

  public FeeCalculationRequest detentionTravelAndWaitingCosts(@Nullable Double detentionTravelAndWaitingCosts) {
    this.detentionTravelAndWaitingCosts = detentionTravelAndWaitingCosts;
    return this;
  }

  /**
   * Value of detention travel and waiting costs costs
   * @return detentionTravelAndWaitingCosts
   */
  
  @Schema(name = "detentionTravelAndWaitingCosts", description = "Value of detention travel and waiting costs costs", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("detentionTravelAndWaitingCosts")
  public @Nullable Double getDetentionTravelAndWaitingCosts() {
    return detentionTravelAndWaitingCosts;
  }

  public void setDetentionTravelAndWaitingCosts(@Nullable Double detentionTravelAndWaitingCosts) {
    this.detentionTravelAndWaitingCosts = detentionTravelAndWaitingCosts;
  }

  public FeeCalculationRequest caseConcludedDate(@Nullable LocalDate caseConcludedDate) {
    this.caseConcludedDate = caseConcludedDate;
    return this;
  }

  /**
   * Get caseConcludedDate
   * @return caseConcludedDate
   */
  @Valid 
  @Schema(name = "caseConcludedDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("caseConcludedDate")
  public @Nullable LocalDate getCaseConcludedDate() {
    return caseConcludedDate;
  }

  public void setCaseConcludedDate(@Nullable LocalDate caseConcludedDate) {
    this.caseConcludedDate = caseConcludedDate;
  }

  public FeeCalculationRequest numberOfMediationSessions(@Nullable Integer numberOfMediationSessions) {
    this.numberOfMediationSessions = numberOfMediationSessions;
    return this;
  }

  /**
   * Number of mediation sessions, where applicable this will determine fixed fee for mediation category of law.
   * @return numberOfMediationSessions
   */
  
  @Schema(name = "numberOfMediationSessions", description = "Number of mediation sessions, where applicable this will determine fixed fee for mediation category of law.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("numberOfMediationSessions")
  public @Nullable Integer getNumberOfMediationSessions() {
    return numberOfMediationSessions;
  }

  public void setNumberOfMediationSessions(@Nullable Integer numberOfMediationSessions) {
    this.numberOfMediationSessions = numberOfMediationSessions;
  }

  public FeeCalculationRequest jrFormFilling(@Nullable Double jrFormFilling) {
    this.jrFormFilling = jrFormFilling;
    return this;
  }

  /**
   * Value of form filling.
   * @return jrFormFilling
   */
  
  @Schema(name = "jrFormFilling", description = "Value of form filling.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("jrFormFilling")
  public @Nullable Double getJrFormFilling() {
    return jrFormFilling;
  }

  public void setJrFormFilling(@Nullable Double jrFormFilling) {
    this.jrFormFilling = jrFormFilling;
  }

  public FeeCalculationRequest immigrationPriorAuthorityNumber(@Nullable String immigrationPriorAuthorityNumber) {
    this.immigrationPriorAuthorityNumber = immigrationPriorAuthorityNumber;
    return this;
  }

  /**
   * Authorisation specific to immigration, used for net profit costs over limit or disbursements over limit.
   * @return immigrationPriorAuthorityNumber
   */
  
  @Schema(name = "immigrationPriorAuthorityNumber", description = "Authorisation specific to immigration, used for net profit costs over limit or disbursements over limit.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("immigrationPriorAuthorityNumber")
  public @Nullable String getImmigrationPriorAuthorityNumber() {
    return immigrationPriorAuthorityNumber;
  }

  public void setImmigrationPriorAuthorityNumber(@Nullable String immigrationPriorAuthorityNumber) {
    this.immigrationPriorAuthorityNumber = immigrationPriorAuthorityNumber;
  }

  public FeeCalculationRequest representationOrderDate(@Nullable LocalDate representationOrderDate) {
    this.representationOrderDate = representationOrderDate;
    return this;
  }

  /**
   * The official start date for claimable work carried out for Crime Lower categories
   * @return representationOrderDate
   */
  @Valid 
  @Schema(name = "representationOrderDate", description = "The official start date for claimable work carried out for Crime Lower categories", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("representationOrderDate")
  public @Nullable LocalDate getRepresentationOrderDate() {
    return representationOrderDate;
  }

  public void setRepresentationOrderDate(@Nullable LocalDate representationOrderDate) {
    this.representationOrderDate = representationOrderDate;
  }

  public FeeCalculationRequest londonRate(@Nullable Boolean londonRate) {
    this.londonRate = londonRate;
    return this;
  }

  /**
   * Indicates if London rate is applied otherwise Non London rate is applied.
   * @return londonRate
   */
  
  @Schema(name = "londonRate", description = "Indicates if London rate is applied otherwise Non London rate is applied.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("londonRate")
  public @Nullable Boolean getLondonRate() {
    return londonRate;
  }

  public void setLondonRate(@Nullable Boolean londonRate) {
    this.londonRate = londonRate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FeeCalculationRequest feeCalculationRequest = (FeeCalculationRequest) o;
    return Objects.equals(this.feeCode, feeCalculationRequest.feeCode) &&
        Objects.equals(this.claimId, feeCalculationRequest.claimId) &&
        Objects.equals(this.startDate, feeCalculationRequest.startDate) &&
        Objects.equals(this.policeStationId, feeCalculationRequest.policeStationId) &&
        Objects.equals(this.policeStationSchemeId, feeCalculationRequest.policeStationSchemeId) &&
        Objects.equals(this.uniqueFileNumber, feeCalculationRequest.uniqueFileNumber) &&
        Objects.equals(this.netProfitCosts, feeCalculationRequest.netProfitCosts) &&
        Objects.equals(this.netCostOfCounsel, feeCalculationRequest.netCostOfCounsel) &&
        Objects.equals(this.netDisbursementAmount, feeCalculationRequest.netDisbursementAmount) &&
        Objects.equals(this.disbursementVatAmount, feeCalculationRequest.disbursementVatAmount) &&
        Objects.equals(this.vatIndicator, feeCalculationRequest.vatIndicator) &&
        Objects.equals(this.boltOns, feeCalculationRequest.boltOns) &&
        Objects.equals(this.netTravelCosts, feeCalculationRequest.netTravelCosts) &&
        Objects.equals(this.netWaitingCosts, feeCalculationRequest.netWaitingCosts) &&
        Objects.equals(this.travelAndWaitingCosts, feeCalculationRequest.travelAndWaitingCosts) &&
        Objects.equals(this.detentionTravelAndWaitingCosts, feeCalculationRequest.detentionTravelAndWaitingCosts) &&
        Objects.equals(this.caseConcludedDate, feeCalculationRequest.caseConcludedDate) &&
        Objects.equals(this.numberOfMediationSessions, feeCalculationRequest.numberOfMediationSessions) &&
        Objects.equals(this.jrFormFilling, feeCalculationRequest.jrFormFilling) &&
        Objects.equals(this.immigrationPriorAuthorityNumber, feeCalculationRequest.immigrationPriorAuthorityNumber) &&
        Objects.equals(this.representationOrderDate, feeCalculationRequest.representationOrderDate) &&
        Objects.equals(this.londonRate, feeCalculationRequest.londonRate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(feeCode, claimId, startDate, policeStationId, policeStationSchemeId, uniqueFileNumber, netProfitCosts, netCostOfCounsel, netDisbursementAmount, disbursementVatAmount, vatIndicator, boltOns, netTravelCosts, netWaitingCosts, travelAndWaitingCosts, detentionTravelAndWaitingCosts, caseConcludedDate, numberOfMediationSessions, jrFormFilling, immigrationPriorAuthorityNumber, representationOrderDate, londonRate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FeeCalculationRequest {\n");
    sb.append("    feeCode: ").append(toIndentedString(feeCode)).append("\n");
    sb.append("    claimId: ").append(toIndentedString(claimId)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    policeStationId: ").append(toIndentedString(policeStationId)).append("\n");
    sb.append("    policeStationSchemeId: ").append(toIndentedString(policeStationSchemeId)).append("\n");
    sb.append("    uniqueFileNumber: ").append(toIndentedString(uniqueFileNumber)).append("\n");
    sb.append("    netProfitCosts: ").append(toIndentedString(netProfitCosts)).append("\n");
    sb.append("    netCostOfCounsel: ").append(toIndentedString(netCostOfCounsel)).append("\n");
    sb.append("    netDisbursementAmount: ").append(toIndentedString(netDisbursementAmount)).append("\n");
    sb.append("    disbursementVatAmount: ").append(toIndentedString(disbursementVatAmount)).append("\n");
    sb.append("    vatIndicator: ").append(toIndentedString(vatIndicator)).append("\n");
    sb.append("    boltOns: ").append(toIndentedString(boltOns)).append("\n");
    sb.append("    netTravelCosts: ").append(toIndentedString(netTravelCosts)).append("\n");
    sb.append("    netWaitingCosts: ").append(toIndentedString(netWaitingCosts)).append("\n");
    sb.append("    travelAndWaitingCosts: ").append(toIndentedString(travelAndWaitingCosts)).append("\n");
    sb.append("    detentionTravelAndWaitingCosts: ").append(toIndentedString(detentionTravelAndWaitingCosts)).append("\n");
    sb.append("    caseConcludedDate: ").append(toIndentedString(caseConcludedDate)).append("\n");
    sb.append("    numberOfMediationSessions: ").append(toIndentedString(numberOfMediationSessions)).append("\n");
    sb.append("    jrFormFilling: ").append(toIndentedString(jrFormFilling)).append("\n");
    sb.append("    immigrationPriorAuthorityNumber: ").append(toIndentedString(immigrationPriorAuthorityNumber)).append("\n");
    sb.append("    representationOrderDate: ").append(toIndentedString(representationOrderDate)).append("\n");
    sb.append("    londonRate: ").append(toIndentedString(londonRate)).append("\n");
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

    private FeeCalculationRequest instance;

    public Builder() {
      this(new FeeCalculationRequest());
    }

    protected Builder(FeeCalculationRequest instance) {
      this.instance = instance;
    }

    protected Builder copyOf(FeeCalculationRequest value) { 
      this.instance.setFeeCode(value.feeCode);
      this.instance.setClaimId(value.claimId);
      this.instance.setStartDate(value.startDate);
      this.instance.setPoliceStationId(value.policeStationId);
      this.instance.setPoliceStationSchemeId(value.policeStationSchemeId);
      this.instance.setUniqueFileNumber(value.uniqueFileNumber);
      this.instance.setNetProfitCosts(value.netProfitCosts);
      this.instance.setNetCostOfCounsel(value.netCostOfCounsel);
      this.instance.setNetDisbursementAmount(value.netDisbursementAmount);
      this.instance.setDisbursementVatAmount(value.disbursementVatAmount);
      this.instance.setVatIndicator(value.vatIndicator);
      this.instance.setBoltOns(value.boltOns);
      this.instance.setNetTravelCosts(value.netTravelCosts);
      this.instance.setNetWaitingCosts(value.netWaitingCosts);
      this.instance.setTravelAndWaitingCosts(value.travelAndWaitingCosts);
      this.instance.setDetentionTravelAndWaitingCosts(value.detentionTravelAndWaitingCosts);
      this.instance.setCaseConcludedDate(value.caseConcludedDate);
      this.instance.setNumberOfMediationSessions(value.numberOfMediationSessions);
      this.instance.setJrFormFilling(value.jrFormFilling);
      this.instance.setImmigrationPriorAuthorityNumber(value.immigrationPriorAuthorityNumber);
      this.instance.setRepresentationOrderDate(value.representationOrderDate);
      this.instance.setLondonRate(value.londonRate);
      return this;
    }

    public FeeCalculationRequest.Builder feeCode(String feeCode) {
      this.instance.feeCode(feeCode);
      return this;
    }
    
    public FeeCalculationRequest.Builder claimId(String claimId) {
      this.instance.claimId(claimId);
      return this;
    }
    
    public FeeCalculationRequest.Builder startDate(LocalDate startDate) {
      this.instance.startDate(startDate);
      return this;
    }
    
    public FeeCalculationRequest.Builder policeStationId(String policeStationId) {
      this.instance.policeStationId(policeStationId);
      return this;
    }
    
    public FeeCalculationRequest.Builder policeStationSchemeId(String policeStationSchemeId) {
      this.instance.policeStationSchemeId(policeStationSchemeId);
      return this;
    }
    
    public FeeCalculationRequest.Builder uniqueFileNumber(String uniqueFileNumber) {
      this.instance.uniqueFileNumber(uniqueFileNumber);
      return this;
    }
    
    public FeeCalculationRequest.Builder netProfitCosts(Double netProfitCosts) {
      this.instance.netProfitCosts(netProfitCosts);
      return this;
    }
    
    public FeeCalculationRequest.Builder netCostOfCounsel(Double netCostOfCounsel) {
      this.instance.netCostOfCounsel(netCostOfCounsel);
      return this;
    }
    
    public FeeCalculationRequest.Builder netDisbursementAmount(Double netDisbursementAmount) {
      this.instance.netDisbursementAmount(netDisbursementAmount);
      return this;
    }
    
    public FeeCalculationRequest.Builder disbursementVatAmount(Double disbursementVatAmount) {
      this.instance.disbursementVatAmount(disbursementVatAmount);
      return this;
    }
    
    public FeeCalculationRequest.Builder vatIndicator(Boolean vatIndicator) {
      this.instance.vatIndicator(vatIndicator);
      return this;
    }
    
    public FeeCalculationRequest.Builder boltOns(BoltOnType boltOns) {
      this.instance.boltOns(boltOns);
      return this;
    }
    
    public FeeCalculationRequest.Builder netTravelCosts(Double netTravelCosts) {
      this.instance.netTravelCosts(netTravelCosts);
      return this;
    }
    
    public FeeCalculationRequest.Builder netWaitingCosts(Double netWaitingCosts) {
      this.instance.netWaitingCosts(netWaitingCosts);
      return this;
    }
    
    public FeeCalculationRequest.Builder travelAndWaitingCosts(Double travelAndWaitingCosts) {
      this.instance.travelAndWaitingCosts(travelAndWaitingCosts);
      return this;
    }
    
    public FeeCalculationRequest.Builder detentionTravelAndWaitingCosts(Double detentionTravelAndWaitingCosts) {
      this.instance.detentionTravelAndWaitingCosts(detentionTravelAndWaitingCosts);
      return this;
    }
    
    public FeeCalculationRequest.Builder caseConcludedDate(LocalDate caseConcludedDate) {
      this.instance.caseConcludedDate(caseConcludedDate);
      return this;
    }
    
    public FeeCalculationRequest.Builder numberOfMediationSessions(Integer numberOfMediationSessions) {
      this.instance.numberOfMediationSessions(numberOfMediationSessions);
      return this;
    }
    
    public FeeCalculationRequest.Builder jrFormFilling(Double jrFormFilling) {
      this.instance.jrFormFilling(jrFormFilling);
      return this;
    }
    
    public FeeCalculationRequest.Builder immigrationPriorAuthorityNumber(String immigrationPriorAuthorityNumber) {
      this.instance.immigrationPriorAuthorityNumber(immigrationPriorAuthorityNumber);
      return this;
    }
    
    public FeeCalculationRequest.Builder representationOrderDate(LocalDate representationOrderDate) {
      this.instance.representationOrderDate(representationOrderDate);
      return this;
    }
    
    public FeeCalculationRequest.Builder londonRate(Boolean londonRate) {
      this.instance.londonRate(londonRate);
      return this;
    }
    
    /**
    * returns a built FeeCalculationRequest instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public FeeCalculationRequest build() {
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
  public static FeeCalculationRequest.Builder builder() {
    return new FeeCalculationRequest.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public FeeCalculationRequest.Builder toBuilder() {
    FeeCalculationRequest.Builder builder = new FeeCalculationRequest.Builder();
    return builder.copyOf(this);
  }

}

