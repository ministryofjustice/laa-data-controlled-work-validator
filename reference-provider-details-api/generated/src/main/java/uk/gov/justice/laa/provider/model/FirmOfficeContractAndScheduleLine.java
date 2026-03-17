package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * FirmOfficeContractAndScheduleLine
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-16T20:11:03.761871Z[Europe/London]", comments = "Generator version: 7.14.0")
public class FirmOfficeContractAndScheduleLine implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String areaOfLaw;

  private @Nullable String categoryOfLaw;

  private @Nullable String description;

  private @Nullable String devolvedPowersStatus;

  private @Nullable String dpTypeOfChange;

  private @Nullable String dpReasonForChange;

  private @Nullable String dpDateOfChange;

  private @Nullable String remainderWorkFlag;

  private @Nullable String minimumCasesAllowedCount;

  private @Nullable String maximumCasesAllowedCount;

  private @Nullable String minimumToleranceCount;

  private @Nullable String maximumToleranceCount;

  private @Nullable String minimumLicenseCount;

  private @Nullable String maximumLicenseCount;

  private @Nullable String workInProgressCount;

  private @Nullable String outreach;

  private @Nullable String cancelFlag;

  private @Nullable String cancelReason;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate cancelDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate closedDate;

  private @Nullable String closedReason;

  public FirmOfficeContractAndScheduleLine areaOfLaw(@Nullable String areaOfLaw) {
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

  public FirmOfficeContractAndScheduleLine categoryOfLaw(@Nullable String categoryOfLaw) {
    this.categoryOfLaw = categoryOfLaw;
    return this;
  }

  /**
   * Get categoryOfLaw
   * @return categoryOfLaw
   */
  
  @Schema(name = "categoryOfLaw", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("categoryOfLaw")
  public @Nullable String getCategoryOfLaw() {
    return categoryOfLaw;
  }

  public void setCategoryOfLaw(@Nullable String categoryOfLaw) {
    this.categoryOfLaw = categoryOfLaw;
  }

  public FirmOfficeContractAndScheduleLine description(@Nullable String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
   */
  
  @Schema(name = "description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public @Nullable String getDescription() {
    return description;
  }

  public void setDescription(@Nullable String description) {
    this.description = description;
  }

  public FirmOfficeContractAndScheduleLine devolvedPowersStatus(@Nullable String devolvedPowersStatus) {
    this.devolvedPowersStatus = devolvedPowersStatus;
    return this;
  }

  /**
   * Get devolvedPowersStatus
   * @return devolvedPowersStatus
   */
  
  @Schema(name = "devolvedPowersStatus", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("devolvedPowersStatus")
  public @Nullable String getDevolvedPowersStatus() {
    return devolvedPowersStatus;
  }

  public void setDevolvedPowersStatus(@Nullable String devolvedPowersStatus) {
    this.devolvedPowersStatus = devolvedPowersStatus;
  }

  public FirmOfficeContractAndScheduleLine dpTypeOfChange(@Nullable String dpTypeOfChange) {
    this.dpTypeOfChange = dpTypeOfChange;
    return this;
  }

  /**
   * Get dpTypeOfChange
   * @return dpTypeOfChange
   */
  
  @Schema(name = "dpTypeOfChange", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dpTypeOfChange")
  public @Nullable String getDpTypeOfChange() {
    return dpTypeOfChange;
  }

  public void setDpTypeOfChange(@Nullable String dpTypeOfChange) {
    this.dpTypeOfChange = dpTypeOfChange;
  }

  public FirmOfficeContractAndScheduleLine dpReasonForChange(@Nullable String dpReasonForChange) {
    this.dpReasonForChange = dpReasonForChange;
    return this;
  }

  /**
   * Get dpReasonForChange
   * @return dpReasonForChange
   */
  
  @Schema(name = "dpReasonForChange", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dpReasonForChange")
  public @Nullable String getDpReasonForChange() {
    return dpReasonForChange;
  }

  public void setDpReasonForChange(@Nullable String dpReasonForChange) {
    this.dpReasonForChange = dpReasonForChange;
  }

  public FirmOfficeContractAndScheduleLine dpDateOfChange(@Nullable String dpDateOfChange) {
    this.dpDateOfChange = dpDateOfChange;
    return this;
  }

  /**
   * Get dpDateOfChange
   * @return dpDateOfChange
   */
  
  @Schema(name = "dpDateOfChange", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dpDateOfChange")
  public @Nullable String getDpDateOfChange() {
    return dpDateOfChange;
  }

  public void setDpDateOfChange(@Nullable String dpDateOfChange) {
    this.dpDateOfChange = dpDateOfChange;
  }

  public FirmOfficeContractAndScheduleLine remainderWorkFlag(@Nullable String remainderWorkFlag) {
    this.remainderWorkFlag = remainderWorkFlag;
    return this;
  }

  /**
   * Get remainderWorkFlag
   * @return remainderWorkFlag
   */
  
  @Schema(name = "remainderWorkFlag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("remainderWorkFlag")
  public @Nullable String getRemainderWorkFlag() {
    return remainderWorkFlag;
  }

  public void setRemainderWorkFlag(@Nullable String remainderWorkFlag) {
    this.remainderWorkFlag = remainderWorkFlag;
  }

  public FirmOfficeContractAndScheduleLine minimumCasesAllowedCount(@Nullable String minimumCasesAllowedCount) {
    this.minimumCasesAllowedCount = minimumCasesAllowedCount;
    return this;
  }

  /**
   * Get minimumCasesAllowedCount
   * @return minimumCasesAllowedCount
   */
  
  @Schema(name = "minimumCasesAllowedCount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("minimumCasesAllowedCount")
  public @Nullable String getMinimumCasesAllowedCount() {
    return minimumCasesAllowedCount;
  }

  public void setMinimumCasesAllowedCount(@Nullable String minimumCasesAllowedCount) {
    this.minimumCasesAllowedCount = minimumCasesAllowedCount;
  }

  public FirmOfficeContractAndScheduleLine maximumCasesAllowedCount(@Nullable String maximumCasesAllowedCount) {
    this.maximumCasesAllowedCount = maximumCasesAllowedCount;
    return this;
  }

  /**
   * Get maximumCasesAllowedCount
   * @return maximumCasesAllowedCount
   */
  
  @Schema(name = "maximumCasesAllowedCount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("maximumCasesAllowedCount")
  public @Nullable String getMaximumCasesAllowedCount() {
    return maximumCasesAllowedCount;
  }

  public void setMaximumCasesAllowedCount(@Nullable String maximumCasesAllowedCount) {
    this.maximumCasesAllowedCount = maximumCasesAllowedCount;
  }

  public FirmOfficeContractAndScheduleLine minimumToleranceCount(@Nullable String minimumToleranceCount) {
    this.minimumToleranceCount = minimumToleranceCount;
    return this;
  }

  /**
   * Get minimumToleranceCount
   * @return minimumToleranceCount
   */
  
  @Schema(name = "minimumToleranceCount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("minimumToleranceCount")
  public @Nullable String getMinimumToleranceCount() {
    return minimumToleranceCount;
  }

  public void setMinimumToleranceCount(@Nullable String minimumToleranceCount) {
    this.minimumToleranceCount = minimumToleranceCount;
  }

  public FirmOfficeContractAndScheduleLine maximumToleranceCount(@Nullable String maximumToleranceCount) {
    this.maximumToleranceCount = maximumToleranceCount;
    return this;
  }

  /**
   * Get maximumToleranceCount
   * @return maximumToleranceCount
   */
  
  @Schema(name = "maximumToleranceCount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("maximumToleranceCount")
  public @Nullable String getMaximumToleranceCount() {
    return maximumToleranceCount;
  }

  public void setMaximumToleranceCount(@Nullable String maximumToleranceCount) {
    this.maximumToleranceCount = maximumToleranceCount;
  }

  public FirmOfficeContractAndScheduleLine minimumLicenseCount(@Nullable String minimumLicenseCount) {
    this.minimumLicenseCount = minimumLicenseCount;
    return this;
  }

  /**
   * Get minimumLicenseCount
   * @return minimumLicenseCount
   */
  
  @Schema(name = "minimumLicenseCount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("minimumLicenseCount")
  public @Nullable String getMinimumLicenseCount() {
    return minimumLicenseCount;
  }

  public void setMinimumLicenseCount(@Nullable String minimumLicenseCount) {
    this.minimumLicenseCount = minimumLicenseCount;
  }

  public FirmOfficeContractAndScheduleLine maximumLicenseCount(@Nullable String maximumLicenseCount) {
    this.maximumLicenseCount = maximumLicenseCount;
    return this;
  }

  /**
   * Get maximumLicenseCount
   * @return maximumLicenseCount
   */
  
  @Schema(name = "maximumLicenseCount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("maximumLicenseCount")
  public @Nullable String getMaximumLicenseCount() {
    return maximumLicenseCount;
  }

  public void setMaximumLicenseCount(@Nullable String maximumLicenseCount) {
    this.maximumLicenseCount = maximumLicenseCount;
  }

  public FirmOfficeContractAndScheduleLine workInProgressCount(@Nullable String workInProgressCount) {
    this.workInProgressCount = workInProgressCount;
    return this;
  }

  /**
   * Get workInProgressCount
   * @return workInProgressCount
   */
  
  @Schema(name = "workInProgressCount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("workInProgressCount")
  public @Nullable String getWorkInProgressCount() {
    return workInProgressCount;
  }

  public void setWorkInProgressCount(@Nullable String workInProgressCount) {
    this.workInProgressCount = workInProgressCount;
  }

  public FirmOfficeContractAndScheduleLine outreach(@Nullable String outreach) {
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

  public FirmOfficeContractAndScheduleLine cancelFlag(@Nullable String cancelFlag) {
    this.cancelFlag = cancelFlag;
    return this;
  }

  /**
   * Get cancelFlag
   * @return cancelFlag
   */
  
  @Schema(name = "cancelFlag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cancelFlag")
  public @Nullable String getCancelFlag() {
    return cancelFlag;
  }

  public void setCancelFlag(@Nullable String cancelFlag) {
    this.cancelFlag = cancelFlag;
  }

  public FirmOfficeContractAndScheduleLine cancelReason(@Nullable String cancelReason) {
    this.cancelReason = cancelReason;
    return this;
  }

  /**
   * Get cancelReason
   * @return cancelReason
   */
  
  @Schema(name = "cancelReason", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cancelReason")
  public @Nullable String getCancelReason() {
    return cancelReason;
  }

  public void setCancelReason(@Nullable String cancelReason) {
    this.cancelReason = cancelReason;
  }

  public FirmOfficeContractAndScheduleLine cancelDate(@Nullable LocalDate cancelDate) {
    this.cancelDate = cancelDate;
    return this;
  }

  /**
   * Get cancelDate
   * @return cancelDate
   */
  @Valid 
  @Schema(name = "cancelDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cancelDate")
  public @Nullable LocalDate getCancelDate() {
    return cancelDate;
  }

  public void setCancelDate(@Nullable LocalDate cancelDate) {
    this.cancelDate = cancelDate;
  }

  public FirmOfficeContractAndScheduleLine closedDate(@Nullable LocalDate closedDate) {
    this.closedDate = closedDate;
    return this;
  }

  /**
   * Get closedDate
   * @return closedDate
   */
  @Valid 
  @Schema(name = "closedDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("closedDate")
  public @Nullable LocalDate getClosedDate() {
    return closedDate;
  }

  public void setClosedDate(@Nullable LocalDate closedDate) {
    this.closedDate = closedDate;
  }

  public FirmOfficeContractAndScheduleLine closedReason(@Nullable String closedReason) {
    this.closedReason = closedReason;
    return this;
  }

  /**
   * Get closedReason
   * @return closedReason
   */
  
  @Schema(name = "closedReason", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("closedReason")
  public @Nullable String getClosedReason() {
    return closedReason;
  }

  public void setClosedReason(@Nullable String closedReason) {
    this.closedReason = closedReason;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FirmOfficeContractAndScheduleLine firmOfficeContractAndScheduleLine = (FirmOfficeContractAndScheduleLine) o;
    return Objects.equals(this.areaOfLaw, firmOfficeContractAndScheduleLine.areaOfLaw) &&
        Objects.equals(this.categoryOfLaw, firmOfficeContractAndScheduleLine.categoryOfLaw) &&
        Objects.equals(this.description, firmOfficeContractAndScheduleLine.description) &&
        Objects.equals(this.devolvedPowersStatus, firmOfficeContractAndScheduleLine.devolvedPowersStatus) &&
        Objects.equals(this.dpTypeOfChange, firmOfficeContractAndScheduleLine.dpTypeOfChange) &&
        Objects.equals(this.dpReasonForChange, firmOfficeContractAndScheduleLine.dpReasonForChange) &&
        Objects.equals(this.dpDateOfChange, firmOfficeContractAndScheduleLine.dpDateOfChange) &&
        Objects.equals(this.remainderWorkFlag, firmOfficeContractAndScheduleLine.remainderWorkFlag) &&
        Objects.equals(this.minimumCasesAllowedCount, firmOfficeContractAndScheduleLine.minimumCasesAllowedCount) &&
        Objects.equals(this.maximumCasesAllowedCount, firmOfficeContractAndScheduleLine.maximumCasesAllowedCount) &&
        Objects.equals(this.minimumToleranceCount, firmOfficeContractAndScheduleLine.minimumToleranceCount) &&
        Objects.equals(this.maximumToleranceCount, firmOfficeContractAndScheduleLine.maximumToleranceCount) &&
        Objects.equals(this.minimumLicenseCount, firmOfficeContractAndScheduleLine.minimumLicenseCount) &&
        Objects.equals(this.maximumLicenseCount, firmOfficeContractAndScheduleLine.maximumLicenseCount) &&
        Objects.equals(this.workInProgressCount, firmOfficeContractAndScheduleLine.workInProgressCount) &&
        Objects.equals(this.outreach, firmOfficeContractAndScheduleLine.outreach) &&
        Objects.equals(this.cancelFlag, firmOfficeContractAndScheduleLine.cancelFlag) &&
        Objects.equals(this.cancelReason, firmOfficeContractAndScheduleLine.cancelReason) &&
        Objects.equals(this.cancelDate, firmOfficeContractAndScheduleLine.cancelDate) &&
        Objects.equals(this.closedDate, firmOfficeContractAndScheduleLine.closedDate) &&
        Objects.equals(this.closedReason, firmOfficeContractAndScheduleLine.closedReason);
  }

  @Override
  public int hashCode() {
    return Objects.hash(areaOfLaw, categoryOfLaw, description, devolvedPowersStatus, dpTypeOfChange, dpReasonForChange, dpDateOfChange, remainderWorkFlag, minimumCasesAllowedCount, maximumCasesAllowedCount, minimumToleranceCount, maximumToleranceCount, minimumLicenseCount, maximumLicenseCount, workInProgressCount, outreach, cancelFlag, cancelReason, cancelDate, closedDate, closedReason);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FirmOfficeContractAndScheduleLine {\n");
    sb.append("    areaOfLaw: ").append(toIndentedString(areaOfLaw)).append("\n");
    sb.append("    categoryOfLaw: ").append(toIndentedString(categoryOfLaw)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    devolvedPowersStatus: ").append(toIndentedString(devolvedPowersStatus)).append("\n");
    sb.append("    dpTypeOfChange: ").append(toIndentedString(dpTypeOfChange)).append("\n");
    sb.append("    dpReasonForChange: ").append(toIndentedString(dpReasonForChange)).append("\n");
    sb.append("    dpDateOfChange: ").append(toIndentedString(dpDateOfChange)).append("\n");
    sb.append("    remainderWorkFlag: ").append(toIndentedString(remainderWorkFlag)).append("\n");
    sb.append("    minimumCasesAllowedCount: ").append(toIndentedString(minimumCasesAllowedCount)).append("\n");
    sb.append("    maximumCasesAllowedCount: ").append(toIndentedString(maximumCasesAllowedCount)).append("\n");
    sb.append("    minimumToleranceCount: ").append(toIndentedString(minimumToleranceCount)).append("\n");
    sb.append("    maximumToleranceCount: ").append(toIndentedString(maximumToleranceCount)).append("\n");
    sb.append("    minimumLicenseCount: ").append(toIndentedString(minimumLicenseCount)).append("\n");
    sb.append("    maximumLicenseCount: ").append(toIndentedString(maximumLicenseCount)).append("\n");
    sb.append("    workInProgressCount: ").append(toIndentedString(workInProgressCount)).append("\n");
    sb.append("    outreach: ").append(toIndentedString(outreach)).append("\n");
    sb.append("    cancelFlag: ").append(toIndentedString(cancelFlag)).append("\n");
    sb.append("    cancelReason: ").append(toIndentedString(cancelReason)).append("\n");
    sb.append("    cancelDate: ").append(toIndentedString(cancelDate)).append("\n");
    sb.append("    closedDate: ").append(toIndentedString(closedDate)).append("\n");
    sb.append("    closedReason: ").append(toIndentedString(closedReason)).append("\n");
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

    private FirmOfficeContractAndScheduleLine instance;

    public Builder() {
      this(new FirmOfficeContractAndScheduleLine());
    }

    protected Builder(FirmOfficeContractAndScheduleLine instance) {
      this.instance = instance;
    }

    protected Builder copyOf(FirmOfficeContractAndScheduleLine value) { 
      this.instance.setAreaOfLaw(value.areaOfLaw);
      this.instance.setCategoryOfLaw(value.categoryOfLaw);
      this.instance.setDescription(value.description);
      this.instance.setDevolvedPowersStatus(value.devolvedPowersStatus);
      this.instance.setDpTypeOfChange(value.dpTypeOfChange);
      this.instance.setDpReasonForChange(value.dpReasonForChange);
      this.instance.setDpDateOfChange(value.dpDateOfChange);
      this.instance.setRemainderWorkFlag(value.remainderWorkFlag);
      this.instance.setMinimumCasesAllowedCount(value.minimumCasesAllowedCount);
      this.instance.setMaximumCasesAllowedCount(value.maximumCasesAllowedCount);
      this.instance.setMinimumToleranceCount(value.minimumToleranceCount);
      this.instance.setMaximumToleranceCount(value.maximumToleranceCount);
      this.instance.setMinimumLicenseCount(value.minimumLicenseCount);
      this.instance.setMaximumLicenseCount(value.maximumLicenseCount);
      this.instance.setWorkInProgressCount(value.workInProgressCount);
      this.instance.setOutreach(value.outreach);
      this.instance.setCancelFlag(value.cancelFlag);
      this.instance.setCancelReason(value.cancelReason);
      this.instance.setCancelDate(value.cancelDate);
      this.instance.setClosedDate(value.closedDate);
      this.instance.setClosedReason(value.closedReason);
      return this;
    }

    public FirmOfficeContractAndScheduleLine.Builder areaOfLaw(String areaOfLaw) {
      this.instance.areaOfLaw(areaOfLaw);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder categoryOfLaw(String categoryOfLaw) {
      this.instance.categoryOfLaw(categoryOfLaw);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder description(String description) {
      this.instance.description(description);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder devolvedPowersStatus(String devolvedPowersStatus) {
      this.instance.devolvedPowersStatus(devolvedPowersStatus);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder dpTypeOfChange(String dpTypeOfChange) {
      this.instance.dpTypeOfChange(dpTypeOfChange);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder dpReasonForChange(String dpReasonForChange) {
      this.instance.dpReasonForChange(dpReasonForChange);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder dpDateOfChange(String dpDateOfChange) {
      this.instance.dpDateOfChange(dpDateOfChange);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder remainderWorkFlag(String remainderWorkFlag) {
      this.instance.remainderWorkFlag(remainderWorkFlag);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder minimumCasesAllowedCount(String minimumCasesAllowedCount) {
      this.instance.minimumCasesAllowedCount(minimumCasesAllowedCount);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder maximumCasesAllowedCount(String maximumCasesAllowedCount) {
      this.instance.maximumCasesAllowedCount(maximumCasesAllowedCount);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder minimumToleranceCount(String minimumToleranceCount) {
      this.instance.minimumToleranceCount(minimumToleranceCount);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder maximumToleranceCount(String maximumToleranceCount) {
      this.instance.maximumToleranceCount(maximumToleranceCount);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder minimumLicenseCount(String minimumLicenseCount) {
      this.instance.minimumLicenseCount(minimumLicenseCount);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder maximumLicenseCount(String maximumLicenseCount) {
      this.instance.maximumLicenseCount(maximumLicenseCount);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder workInProgressCount(String workInProgressCount) {
      this.instance.workInProgressCount(workInProgressCount);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder outreach(String outreach) {
      this.instance.outreach(outreach);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder cancelFlag(String cancelFlag) {
      this.instance.cancelFlag(cancelFlag);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder cancelReason(String cancelReason) {
      this.instance.cancelReason(cancelReason);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder cancelDate(LocalDate cancelDate) {
      this.instance.cancelDate(cancelDate);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder closedDate(LocalDate closedDate) {
      this.instance.closedDate(closedDate);
      return this;
    }
    
    public FirmOfficeContractAndScheduleLine.Builder closedReason(String closedReason) {
      this.instance.closedReason(closedReason);
      return this;
    }
    
    /**
    * returns a built FirmOfficeContractAndScheduleLine instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public FirmOfficeContractAndScheduleLine build() {
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
  public static FirmOfficeContractAndScheduleLine.Builder builder() {
    return new FirmOfficeContractAndScheduleLine.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public FirmOfficeContractAndScheduleLine.Builder toBuilder() {
    FirmOfficeContractAndScheduleLine.Builder builder = new FirmOfficeContractAndScheduleLine.Builder();
    return builder.copyOf(this);
  }

}

