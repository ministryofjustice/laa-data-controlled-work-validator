package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.provider.model.FirmOfficeContractAndScheduleLine;
import uk.gov.justice.laa.provider.model.NmsAuthDetails;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * FirmOfficeContractAndScheduleDetails
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T21:51:32.806274Z[Europe/London]", comments = "Generator version: 7.14.0")
public class FirmOfficeContractAndScheduleDetails implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String contractType;

  private @Nullable String contractDescription;

  private @Nullable String contractNumber;

  private @Nullable String contractReference;

  private @Nullable String contractStatus;

  private @Nullable String contractAuthorizationStatus;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate contractStartDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate contractEndDate;

  private @Nullable String areaOfLaw;

  private @Nullable String scheduleType;

  private @Nullable String scheduleNumber;

  private @Nullable String scheduleContractNumber;

  private @Nullable String scheduleContractReference;

  private @Nullable String scheduleAuthorizationStatus;

  private @Nullable String scheduleStatus;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate scheduleStartDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate scheduleEndDate;

  @Valid
  private List<@Valid FirmOfficeContractAndScheduleLine> scheduleLines = new ArrayList<>();

  @Valid
  private List<@Valid NmsAuthDetails> nmsAuths = new ArrayList<>();

  public FirmOfficeContractAndScheduleDetails contractType(@Nullable String contractType) {
    this.contractType = contractType;
    return this;
  }

  /**
   * Get contractType
   * @return contractType
   */
  
  @Schema(name = "contractType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("contractType")
  public @Nullable String getContractType() {
    return contractType;
  }

  public void setContractType(@Nullable String contractType) {
    this.contractType = contractType;
  }

  public FirmOfficeContractAndScheduleDetails contractDescription(@Nullable String contractDescription) {
    this.contractDescription = contractDescription;
    return this;
  }

  /**
   * Get contractDescription
   * @return contractDescription
   */
  
  @Schema(name = "contractDescription", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("contractDescription")
  public @Nullable String getContractDescription() {
    return contractDescription;
  }

  public void setContractDescription(@Nullable String contractDescription) {
    this.contractDescription = contractDescription;
  }

  public FirmOfficeContractAndScheduleDetails contractNumber(@Nullable String contractNumber) {
    this.contractNumber = contractNumber;
    return this;
  }

  /**
   * Get contractNumber
   * @return contractNumber
   */
  
  @Schema(name = "contractNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("contractNumber")
  public @Nullable String getContractNumber() {
    return contractNumber;
  }

  public void setContractNumber(@Nullable String contractNumber) {
    this.contractNumber = contractNumber;
  }

  public FirmOfficeContractAndScheduleDetails contractReference(@Nullable String contractReference) {
    this.contractReference = contractReference;
    return this;
  }

  /**
   * Get contractReference
   * @return contractReference
   */
  
  @Schema(name = "contractReference", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("contractReference")
  public @Nullable String getContractReference() {
    return contractReference;
  }

  public void setContractReference(@Nullable String contractReference) {
    this.contractReference = contractReference;
  }

  public FirmOfficeContractAndScheduleDetails contractStatus(@Nullable String contractStatus) {
    this.contractStatus = contractStatus;
    return this;
  }

  /**
   * Get contractStatus
   * @return contractStatus
   */
  
  @Schema(name = "contractStatus", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("contractStatus")
  public @Nullable String getContractStatus() {
    return contractStatus;
  }

  public void setContractStatus(@Nullable String contractStatus) {
    this.contractStatus = contractStatus;
  }

  public FirmOfficeContractAndScheduleDetails contractAuthorizationStatus(@Nullable String contractAuthorizationStatus) {
    this.contractAuthorizationStatus = contractAuthorizationStatus;
    return this;
  }

  /**
   * Get contractAuthorizationStatus
   * @return contractAuthorizationStatus
   */
  
  @Schema(name = "contractAuthorizationStatus", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("contractAuthorizationStatus")
  public @Nullable String getContractAuthorizationStatus() {
    return contractAuthorizationStatus;
  }

  public void setContractAuthorizationStatus(@Nullable String contractAuthorizationStatus) {
    this.contractAuthorizationStatus = contractAuthorizationStatus;
  }

  public FirmOfficeContractAndScheduleDetails contractStartDate(@Nullable LocalDate contractStartDate) {
    this.contractStartDate = contractStartDate;
    return this;
  }

  /**
   * Get contractStartDate
   * @return contractStartDate
   */
  @Valid 
  @Schema(name = "contractStartDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("contractStartDate")
  public @Nullable LocalDate getContractStartDate() {
    return contractStartDate;
  }

  public void setContractStartDate(@Nullable LocalDate contractStartDate) {
    this.contractStartDate = contractStartDate;
  }

  public FirmOfficeContractAndScheduleDetails contractEndDate(@Nullable LocalDate contractEndDate) {
    this.contractEndDate = contractEndDate;
    return this;
  }

  /**
   * Get contractEndDate
   * @return contractEndDate
   */
  @Valid 
  @Schema(name = "contractEndDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("contractEndDate")
  public @Nullable LocalDate getContractEndDate() {
    return contractEndDate;
  }

  public void setContractEndDate(@Nullable LocalDate contractEndDate) {
    this.contractEndDate = contractEndDate;
  }

  public FirmOfficeContractAndScheduleDetails areaOfLaw(@Nullable String areaOfLaw) {
    this.areaOfLaw = areaOfLaw;
    return this;
  }

  /**
   * Get areaOfLaw
   * @return areaOfLaw
   */
  
  @Schema(name = "areaOfLaw", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("areaOfLaw")
  public @Nullable String getAreaOfLaw() {
    return areaOfLaw;
  }

  public void setAreaOfLaw(@Nullable String areaOfLaw) {
    this.areaOfLaw = areaOfLaw;
  }

  public FirmOfficeContractAndScheduleDetails scheduleType(@Nullable String scheduleType) {
    this.scheduleType = scheduleType;
    return this;
  }

  /**
   * Get scheduleType
   * @return scheduleType
   */
  
  @Schema(name = "scheduleType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("scheduleType")
  public @Nullable String getScheduleType() {
    return scheduleType;
  }

  public void setScheduleType(@Nullable String scheduleType) {
    this.scheduleType = scheduleType;
  }

  public FirmOfficeContractAndScheduleDetails scheduleNumber(@Nullable String scheduleNumber) {
    this.scheduleNumber = scheduleNumber;
    return this;
  }

  /**
   * Get scheduleNumber
   * @return scheduleNumber
   */
  
  @Schema(name = "scheduleNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("scheduleNumber")
  public @Nullable String getScheduleNumber() {
    return scheduleNumber;
  }

  public void setScheduleNumber(@Nullable String scheduleNumber) {
    this.scheduleNumber = scheduleNumber;
  }

  public FirmOfficeContractAndScheduleDetails scheduleContractNumber(@Nullable String scheduleContractNumber) {
    this.scheduleContractNumber = scheduleContractNumber;
    return this;
  }

  /**
   * Get scheduleContractNumber
   * @return scheduleContractNumber
   */
  
  @Schema(name = "scheduleContractNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("scheduleContractNumber")
  public @Nullable String getScheduleContractNumber() {
    return scheduleContractNumber;
  }

  public void setScheduleContractNumber(@Nullable String scheduleContractNumber) {
    this.scheduleContractNumber = scheduleContractNumber;
  }

  public FirmOfficeContractAndScheduleDetails scheduleContractReference(@Nullable String scheduleContractReference) {
    this.scheduleContractReference = scheduleContractReference;
    return this;
  }

  /**
   * Get scheduleContractReference
   * @return scheduleContractReference
   */
  
  @Schema(name = "scheduleContractReference", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("scheduleContractReference")
  public @Nullable String getScheduleContractReference() {
    return scheduleContractReference;
  }

  public void setScheduleContractReference(@Nullable String scheduleContractReference) {
    this.scheduleContractReference = scheduleContractReference;
  }

  public FirmOfficeContractAndScheduleDetails scheduleAuthorizationStatus(@Nullable String scheduleAuthorizationStatus) {
    this.scheduleAuthorizationStatus = scheduleAuthorizationStatus;
    return this;
  }

  /**
   * Get scheduleAuthorizationStatus
   * @return scheduleAuthorizationStatus
   */
  
  @Schema(name = "scheduleAuthorizationStatus", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("scheduleAuthorizationStatus")
  public @Nullable String getScheduleAuthorizationStatus() {
    return scheduleAuthorizationStatus;
  }

  public void setScheduleAuthorizationStatus(@Nullable String scheduleAuthorizationStatus) {
    this.scheduleAuthorizationStatus = scheduleAuthorizationStatus;
  }

  public FirmOfficeContractAndScheduleDetails scheduleStatus(@Nullable String scheduleStatus) {
    this.scheduleStatus = scheduleStatus;
    return this;
  }

  /**
   * Get scheduleStatus
   * @return scheduleStatus
   */
  
  @Schema(name = "scheduleStatus", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("scheduleStatus")
  public @Nullable String getScheduleStatus() {
    return scheduleStatus;
  }

  public void setScheduleStatus(@Nullable String scheduleStatus) {
    this.scheduleStatus = scheduleStatus;
  }

  public FirmOfficeContractAndScheduleDetails scheduleStartDate(@Nullable LocalDate scheduleStartDate) {
    this.scheduleStartDate = scheduleStartDate;
    return this;
  }

  /**
   * Get scheduleStartDate
   * @return scheduleStartDate
   */
  @Valid 
  @Schema(name = "scheduleStartDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("scheduleStartDate")
  public @Nullable LocalDate getScheduleStartDate() {
    return scheduleStartDate;
  }

  public void setScheduleStartDate(@Nullable LocalDate scheduleStartDate) {
    this.scheduleStartDate = scheduleStartDate;
  }

  public FirmOfficeContractAndScheduleDetails scheduleEndDate(@Nullable LocalDate scheduleEndDate) {
    this.scheduleEndDate = scheduleEndDate;
    return this;
  }

  /**
   * Get scheduleEndDate
   * @return scheduleEndDate
   */
  @Valid 
  @Schema(name = "scheduleEndDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("scheduleEndDate")
  public @Nullable LocalDate getScheduleEndDate() {
    return scheduleEndDate;
  }

  public void setScheduleEndDate(@Nullable LocalDate scheduleEndDate) {
    this.scheduleEndDate = scheduleEndDate;
  }

  public FirmOfficeContractAndScheduleDetails scheduleLines(List<@Valid FirmOfficeContractAndScheduleLine> scheduleLines) {
    this.scheduleLines = scheduleLines;
    return this;
  }

  public FirmOfficeContractAndScheduleDetails addScheduleLinesItem(FirmOfficeContractAndScheduleLine scheduleLinesItem) {
    if (this.scheduleLines == null) {
      this.scheduleLines = new ArrayList<>();
    }
    this.scheduleLines.add(scheduleLinesItem);
    return this;
  }

  /**
   * Get scheduleLines
   * @return scheduleLines
   */
  @Valid 
  @Schema(name = "scheduleLines", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("scheduleLines")
  public List<@Valid FirmOfficeContractAndScheduleLine> getScheduleLines() {
    return scheduleLines;
  }

  public void setScheduleLines(List<@Valid FirmOfficeContractAndScheduleLine> scheduleLines) {
    this.scheduleLines = scheduleLines;
  }

  public FirmOfficeContractAndScheduleDetails nmsAuths(List<@Valid NmsAuthDetails> nmsAuths) {
    this.nmsAuths = nmsAuths;
    return this;
  }

  public FirmOfficeContractAndScheduleDetails addNmsAuthsItem(NmsAuthDetails nmsAuthsItem) {
    if (this.nmsAuths == null) {
      this.nmsAuths = new ArrayList<>();
    }
    this.nmsAuths.add(nmsAuthsItem);
    return this;
  }

  /**
   * Get nmsAuths
   * @return nmsAuths
   */
  @Valid 
  @Schema(name = "nmsAuths", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nmsAuths")
  public List<@Valid NmsAuthDetails> getNmsAuths() {
    return nmsAuths;
  }

  public void setNmsAuths(List<@Valid NmsAuthDetails> nmsAuths) {
    this.nmsAuths = nmsAuths;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FirmOfficeContractAndScheduleDetails firmOfficeContractAndScheduleDetails = (FirmOfficeContractAndScheduleDetails) o;
    return Objects.equals(this.contractType, firmOfficeContractAndScheduleDetails.contractType) &&
        Objects.equals(this.contractDescription, firmOfficeContractAndScheduleDetails.contractDescription) &&
        Objects.equals(this.contractNumber, firmOfficeContractAndScheduleDetails.contractNumber) &&
        Objects.equals(this.contractReference, firmOfficeContractAndScheduleDetails.contractReference) &&
        Objects.equals(this.contractStatus, firmOfficeContractAndScheduleDetails.contractStatus) &&
        Objects.equals(this.contractAuthorizationStatus, firmOfficeContractAndScheduleDetails.contractAuthorizationStatus) &&
        Objects.equals(this.contractStartDate, firmOfficeContractAndScheduleDetails.contractStartDate) &&
        Objects.equals(this.contractEndDate, firmOfficeContractAndScheduleDetails.contractEndDate) &&
        Objects.equals(this.areaOfLaw, firmOfficeContractAndScheduleDetails.areaOfLaw) &&
        Objects.equals(this.scheduleType, firmOfficeContractAndScheduleDetails.scheduleType) &&
        Objects.equals(this.scheduleNumber, firmOfficeContractAndScheduleDetails.scheduleNumber) &&
        Objects.equals(this.scheduleContractNumber, firmOfficeContractAndScheduleDetails.scheduleContractNumber) &&
        Objects.equals(this.scheduleContractReference, firmOfficeContractAndScheduleDetails.scheduleContractReference) &&
        Objects.equals(this.scheduleAuthorizationStatus, firmOfficeContractAndScheduleDetails.scheduleAuthorizationStatus) &&
        Objects.equals(this.scheduleStatus, firmOfficeContractAndScheduleDetails.scheduleStatus) &&
        Objects.equals(this.scheduleStartDate, firmOfficeContractAndScheduleDetails.scheduleStartDate) &&
        Objects.equals(this.scheduleEndDate, firmOfficeContractAndScheduleDetails.scheduleEndDate) &&
        Objects.equals(this.scheduleLines, firmOfficeContractAndScheduleDetails.scheduleLines) &&
        Objects.equals(this.nmsAuths, firmOfficeContractAndScheduleDetails.nmsAuths);
  }

  @Override
  public int hashCode() {
    return Objects.hash(contractType, contractDescription, contractNumber, contractReference, contractStatus, contractAuthorizationStatus, contractStartDate, contractEndDate, areaOfLaw, scheduleType, scheduleNumber, scheduleContractNumber, scheduleContractReference, scheduleAuthorizationStatus, scheduleStatus, scheduleStartDate, scheduleEndDate, scheduleLines, nmsAuths);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FirmOfficeContractAndScheduleDetails {\n");
    sb.append("    contractType: ").append(toIndentedString(contractType)).append("\n");
    sb.append("    contractDescription: ").append(toIndentedString(contractDescription)).append("\n");
    sb.append("    contractNumber: ").append(toIndentedString(contractNumber)).append("\n");
    sb.append("    contractReference: ").append(toIndentedString(contractReference)).append("\n");
    sb.append("    contractStatus: ").append(toIndentedString(contractStatus)).append("\n");
    sb.append("    contractAuthorizationStatus: ").append(toIndentedString(contractAuthorizationStatus)).append("\n");
    sb.append("    contractStartDate: ").append(toIndentedString(contractStartDate)).append("\n");
    sb.append("    contractEndDate: ").append(toIndentedString(contractEndDate)).append("\n");
    sb.append("    areaOfLaw: ").append(toIndentedString(areaOfLaw)).append("\n");
    sb.append("    scheduleType: ").append(toIndentedString(scheduleType)).append("\n");
    sb.append("    scheduleNumber: ").append(toIndentedString(scheduleNumber)).append("\n");
    sb.append("    scheduleContractNumber: ").append(toIndentedString(scheduleContractNumber)).append("\n");
    sb.append("    scheduleContractReference: ").append(toIndentedString(scheduleContractReference)).append("\n");
    sb.append("    scheduleAuthorizationStatus: ").append(toIndentedString(scheduleAuthorizationStatus)).append("\n");
    sb.append("    scheduleStatus: ").append(toIndentedString(scheduleStatus)).append("\n");
    sb.append("    scheduleStartDate: ").append(toIndentedString(scheduleStartDate)).append("\n");
    sb.append("    scheduleEndDate: ").append(toIndentedString(scheduleEndDate)).append("\n");
    sb.append("    scheduleLines: ").append(toIndentedString(scheduleLines)).append("\n");
    sb.append("    nmsAuths: ").append(toIndentedString(nmsAuths)).append("\n");
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

    private FirmOfficeContractAndScheduleDetails instance;

    public Builder() {
      this(new FirmOfficeContractAndScheduleDetails());
    }

    protected Builder(FirmOfficeContractAndScheduleDetails instance) {
      this.instance = instance;
    }

    protected Builder copyOf(FirmOfficeContractAndScheduleDetails value) { 
      this.instance.setContractType(value.contractType);
      this.instance.setContractDescription(value.contractDescription);
      this.instance.setContractNumber(value.contractNumber);
      this.instance.setContractReference(value.contractReference);
      this.instance.setContractStatus(value.contractStatus);
      this.instance.setContractAuthorizationStatus(value.contractAuthorizationStatus);
      this.instance.setContractStartDate(value.contractStartDate);
      this.instance.setContractEndDate(value.contractEndDate);
      this.instance.setAreaOfLaw(value.areaOfLaw);
      this.instance.setScheduleType(value.scheduleType);
      this.instance.setScheduleNumber(value.scheduleNumber);
      this.instance.setScheduleContractNumber(value.scheduleContractNumber);
      this.instance.setScheduleContractReference(value.scheduleContractReference);
      this.instance.setScheduleAuthorizationStatus(value.scheduleAuthorizationStatus);
      this.instance.setScheduleStatus(value.scheduleStatus);
      this.instance.setScheduleStartDate(value.scheduleStartDate);
      this.instance.setScheduleEndDate(value.scheduleEndDate);
      this.instance.setScheduleLines(value.scheduleLines);
      this.instance.setNmsAuths(value.nmsAuths);
      return this;
    }

    public FirmOfficeContractAndScheduleDetails.Builder contractType(String contractType) {
      this.instance.contractType(contractType);
      return this;
    }
    
    public FirmOfficeContractAndScheduleDetails.Builder contractDescription(String contractDescription) {
      this.instance.contractDescription(contractDescription);
      return this;
    }
    
    public FirmOfficeContractAndScheduleDetails.Builder contractNumber(String contractNumber) {
      this.instance.contractNumber(contractNumber);
      return this;
    }
    
    public FirmOfficeContractAndScheduleDetails.Builder contractReference(String contractReference) {
      this.instance.contractReference(contractReference);
      return this;
    }
    
    public FirmOfficeContractAndScheduleDetails.Builder contractStatus(String contractStatus) {
      this.instance.contractStatus(contractStatus);
      return this;
    }
    
    public FirmOfficeContractAndScheduleDetails.Builder contractAuthorizationStatus(String contractAuthorizationStatus) {
      this.instance.contractAuthorizationStatus(contractAuthorizationStatus);
      return this;
    }
    
    public FirmOfficeContractAndScheduleDetails.Builder contractStartDate(LocalDate contractStartDate) {
      this.instance.contractStartDate(contractStartDate);
      return this;
    }
    
    public FirmOfficeContractAndScheduleDetails.Builder contractEndDate(LocalDate contractEndDate) {
      this.instance.contractEndDate(contractEndDate);
      return this;
    }
    
    public FirmOfficeContractAndScheduleDetails.Builder areaOfLaw(String areaOfLaw) {
      this.instance.areaOfLaw(areaOfLaw);
      return this;
    }
    
    public FirmOfficeContractAndScheduleDetails.Builder scheduleType(String scheduleType) {
      this.instance.scheduleType(scheduleType);
      return this;
    }
    
    public FirmOfficeContractAndScheduleDetails.Builder scheduleNumber(String scheduleNumber) {
      this.instance.scheduleNumber(scheduleNumber);
      return this;
    }
    
    public FirmOfficeContractAndScheduleDetails.Builder scheduleContractNumber(String scheduleContractNumber) {
      this.instance.scheduleContractNumber(scheduleContractNumber);
      return this;
    }
    
    public FirmOfficeContractAndScheduleDetails.Builder scheduleContractReference(String scheduleContractReference) {
      this.instance.scheduleContractReference(scheduleContractReference);
      return this;
    }
    
    public FirmOfficeContractAndScheduleDetails.Builder scheduleAuthorizationStatus(String scheduleAuthorizationStatus) {
      this.instance.scheduleAuthorizationStatus(scheduleAuthorizationStatus);
      return this;
    }
    
    public FirmOfficeContractAndScheduleDetails.Builder scheduleStatus(String scheduleStatus) {
      this.instance.scheduleStatus(scheduleStatus);
      return this;
    }
    
    public FirmOfficeContractAndScheduleDetails.Builder scheduleStartDate(LocalDate scheduleStartDate) {
      this.instance.scheduleStartDate(scheduleStartDate);
      return this;
    }
    
    public FirmOfficeContractAndScheduleDetails.Builder scheduleEndDate(LocalDate scheduleEndDate) {
      this.instance.scheduleEndDate(scheduleEndDate);
      return this;
    }
    
    public FirmOfficeContractAndScheduleDetails.Builder scheduleLines(List<FirmOfficeContractAndScheduleLine> scheduleLines) {
      this.instance.scheduleLines(scheduleLines);
      return this;
    }
    
    public FirmOfficeContractAndScheduleDetails.Builder nmsAuths(List<NmsAuthDetails> nmsAuths) {
      this.instance.nmsAuths(nmsAuths);
      return this;
    }
    
    /**
    * returns a built FirmOfficeContractAndScheduleDetails instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public FirmOfficeContractAndScheduleDetails build() {
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
  public static FirmOfficeContractAndScheduleDetails.Builder builder() {
    return new FirmOfficeContractAndScheduleDetails.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public FirmOfficeContractAndScheduleDetails.Builder toBuilder() {
    FirmOfficeContractAndScheduleDetails.Builder builder = new FirmOfficeContractAndScheduleDetails.Builder();
    return builder.copyOf(this);
  }

}

