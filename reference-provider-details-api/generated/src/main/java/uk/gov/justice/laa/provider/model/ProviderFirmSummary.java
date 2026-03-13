package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * ProviderFirmSummary
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T18:11:19.822784Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmSummary implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String firmNumber;

  private @Nullable Integer firmId;

  private @Nullable Integer ccmsFirmId;

  private @Nullable Integer parentFirmId;

  private @Nullable String firmName;

  private @Nullable String firmType;

  private @Nullable String constitutionalStatus;

  private @Nullable String solicitorAdvocateYN;

  private @Nullable String advocateLevel;

  private @Nullable String barCouncilRoll;

  private @Nullable String companyHouseNumber;

  public ProviderFirmSummary firmNumber(@Nullable String firmNumber) {
    this.firmNumber = firmNumber;
    return this;
  }

  /**
   * Get firmNumber
   * @return firmNumber
   */
  
  @Schema(name = "firmNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("firmNumber")
  public @Nullable String getFirmNumber() {
    return firmNumber;
  }

  public void setFirmNumber(@Nullable String firmNumber) {
    this.firmNumber = firmNumber;
  }

  public ProviderFirmSummary firmId(@Nullable Integer firmId) {
    this.firmId = firmId;
    return this;
  }

  /**
   * Get firmId
   * @return firmId
   */
  
  @Schema(name = "firmId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("firmId")
  public @Nullable Integer getFirmId() {
    return firmId;
  }

  public void setFirmId(@Nullable Integer firmId) {
    this.firmId = firmId;
  }

  public ProviderFirmSummary ccmsFirmId(@Nullable Integer ccmsFirmId) {
    this.ccmsFirmId = ccmsFirmId;
    return this;
  }

  /**
   * Get ccmsFirmId
   * @return ccmsFirmId
   */
  
  @Schema(name = "ccmsFirmId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ccmsFirmId")
  public @Nullable Integer getCcmsFirmId() {
    return ccmsFirmId;
  }

  public void setCcmsFirmId(@Nullable Integer ccmsFirmId) {
    this.ccmsFirmId = ccmsFirmId;
  }

  public ProviderFirmSummary parentFirmId(@Nullable Integer parentFirmId) {
    this.parentFirmId = parentFirmId;
    return this;
  }

  /**
   * Get parentFirmId
   * @return parentFirmId
   */
  
  @Schema(name = "parentFirmId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("parentFirmId")
  public @Nullable Integer getParentFirmId() {
    return parentFirmId;
  }

  public void setParentFirmId(@Nullable Integer parentFirmId) {
    this.parentFirmId = parentFirmId;
  }

  public ProviderFirmSummary firmName(@Nullable String firmName) {
    this.firmName = firmName;
    return this;
  }

  /**
   * Get firmName
   * @return firmName
   */
  
  @Schema(name = "firmName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("firmName")
  public @Nullable String getFirmName() {
    return firmName;
  }

  public void setFirmName(@Nullable String firmName) {
    this.firmName = firmName;
  }

  public ProviderFirmSummary firmType(@Nullable String firmType) {
    this.firmType = firmType;
    return this;
  }

  /**
   * Get firmType
   * @return firmType
   */
  
  @Schema(name = "firmType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("firmType")
  public @Nullable String getFirmType() {
    return firmType;
  }

  public void setFirmType(@Nullable String firmType) {
    this.firmType = firmType;
  }

  public ProviderFirmSummary constitutionalStatus(@Nullable String constitutionalStatus) {
    this.constitutionalStatus = constitutionalStatus;
    return this;
  }

  /**
   * Get constitutionalStatus
   * @return constitutionalStatus
   */
  
  @Schema(name = "constitutionalStatus", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("constitutionalStatus")
  public @Nullable String getConstitutionalStatus() {
    return constitutionalStatus;
  }

  public void setConstitutionalStatus(@Nullable String constitutionalStatus) {
    this.constitutionalStatus = constitutionalStatus;
  }

  public ProviderFirmSummary solicitorAdvocateYN(@Nullable String solicitorAdvocateYN) {
    this.solicitorAdvocateYN = solicitorAdvocateYN;
    return this;
  }

  /**
   * Get solicitorAdvocateYN
   * @return solicitorAdvocateYN
   */
  
  @Schema(name = "solicitorAdvocateYN", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("solicitorAdvocateYN")
  public @Nullable String getSolicitorAdvocateYN() {
    return solicitorAdvocateYN;
  }

  public void setSolicitorAdvocateYN(@Nullable String solicitorAdvocateYN) {
    this.solicitorAdvocateYN = solicitorAdvocateYN;
  }

  public ProviderFirmSummary advocateLevel(@Nullable String advocateLevel) {
    this.advocateLevel = advocateLevel;
    return this;
  }

  /**
   * Get advocateLevel
   * @return advocateLevel
   */
  
  @Schema(name = "advocateLevel", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("advocateLevel")
  public @Nullable String getAdvocateLevel() {
    return advocateLevel;
  }

  public void setAdvocateLevel(@Nullable String advocateLevel) {
    this.advocateLevel = advocateLevel;
  }

  public ProviderFirmSummary barCouncilRoll(@Nullable String barCouncilRoll) {
    this.barCouncilRoll = barCouncilRoll;
    return this;
  }

  /**
   * Get barCouncilRoll
   * @return barCouncilRoll
   */
  
  @Schema(name = "barCouncilRoll", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("barCouncilRoll")
  public @Nullable String getBarCouncilRoll() {
    return barCouncilRoll;
  }

  public void setBarCouncilRoll(@Nullable String barCouncilRoll) {
    this.barCouncilRoll = barCouncilRoll;
  }

  public ProviderFirmSummary companyHouseNumber(@Nullable String companyHouseNumber) {
    this.companyHouseNumber = companyHouseNumber;
    return this;
  }

  /**
   * Get companyHouseNumber
   * @return companyHouseNumber
   */
  
  @Schema(name = "companyHouseNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("companyHouseNumber")
  public @Nullable String getCompanyHouseNumber() {
    return companyHouseNumber;
  }

  public void setCompanyHouseNumber(@Nullable String companyHouseNumber) {
    this.companyHouseNumber = companyHouseNumber;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirmSummary providerFirmSummary = (ProviderFirmSummary) o;
    return Objects.equals(this.firmNumber, providerFirmSummary.firmNumber) &&
        Objects.equals(this.firmId, providerFirmSummary.firmId) &&
        Objects.equals(this.ccmsFirmId, providerFirmSummary.ccmsFirmId) &&
        Objects.equals(this.parentFirmId, providerFirmSummary.parentFirmId) &&
        Objects.equals(this.firmName, providerFirmSummary.firmName) &&
        Objects.equals(this.firmType, providerFirmSummary.firmType) &&
        Objects.equals(this.constitutionalStatus, providerFirmSummary.constitutionalStatus) &&
        Objects.equals(this.solicitorAdvocateYN, providerFirmSummary.solicitorAdvocateYN) &&
        Objects.equals(this.advocateLevel, providerFirmSummary.advocateLevel) &&
        Objects.equals(this.barCouncilRoll, providerFirmSummary.barCouncilRoll) &&
        Objects.equals(this.companyHouseNumber, providerFirmSummary.companyHouseNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firmNumber, firmId, ccmsFirmId, parentFirmId, firmName, firmType, constitutionalStatus, solicitorAdvocateYN, advocateLevel, barCouncilRoll, companyHouseNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmSummary {\n");
    sb.append("    firmNumber: ").append(toIndentedString(firmNumber)).append("\n");
    sb.append("    firmId: ").append(toIndentedString(firmId)).append("\n");
    sb.append("    ccmsFirmId: ").append(toIndentedString(ccmsFirmId)).append("\n");
    sb.append("    parentFirmId: ").append(toIndentedString(parentFirmId)).append("\n");
    sb.append("    firmName: ").append(toIndentedString(firmName)).append("\n");
    sb.append("    firmType: ").append(toIndentedString(firmType)).append("\n");
    sb.append("    constitutionalStatus: ").append(toIndentedString(constitutionalStatus)).append("\n");
    sb.append("    solicitorAdvocateYN: ").append(toIndentedString(solicitorAdvocateYN)).append("\n");
    sb.append("    advocateLevel: ").append(toIndentedString(advocateLevel)).append("\n");
    sb.append("    barCouncilRoll: ").append(toIndentedString(barCouncilRoll)).append("\n");
    sb.append("    companyHouseNumber: ").append(toIndentedString(companyHouseNumber)).append("\n");
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

    private ProviderFirmSummary instance;

    public Builder() {
      this(new ProviderFirmSummary());
    }

    protected Builder(ProviderFirmSummary instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmSummary value) { 
      this.instance.setFirmNumber(value.firmNumber);
      this.instance.setFirmId(value.firmId);
      this.instance.setCcmsFirmId(value.ccmsFirmId);
      this.instance.setParentFirmId(value.parentFirmId);
      this.instance.setFirmName(value.firmName);
      this.instance.setFirmType(value.firmType);
      this.instance.setConstitutionalStatus(value.constitutionalStatus);
      this.instance.setSolicitorAdvocateYN(value.solicitorAdvocateYN);
      this.instance.setAdvocateLevel(value.advocateLevel);
      this.instance.setBarCouncilRoll(value.barCouncilRoll);
      this.instance.setCompanyHouseNumber(value.companyHouseNumber);
      return this;
    }

    public ProviderFirmSummary.Builder firmNumber(String firmNumber) {
      this.instance.firmNumber(firmNumber);
      return this;
    }
    
    public ProviderFirmSummary.Builder firmId(Integer firmId) {
      this.instance.firmId(firmId);
      return this;
    }
    
    public ProviderFirmSummary.Builder ccmsFirmId(Integer ccmsFirmId) {
      this.instance.ccmsFirmId(ccmsFirmId);
      return this;
    }
    
    public ProviderFirmSummary.Builder parentFirmId(Integer parentFirmId) {
      this.instance.parentFirmId(parentFirmId);
      return this;
    }
    
    public ProviderFirmSummary.Builder firmName(String firmName) {
      this.instance.firmName(firmName);
      return this;
    }
    
    public ProviderFirmSummary.Builder firmType(String firmType) {
      this.instance.firmType(firmType);
      return this;
    }
    
    public ProviderFirmSummary.Builder constitutionalStatus(String constitutionalStatus) {
      this.instance.constitutionalStatus(constitutionalStatus);
      return this;
    }
    
    public ProviderFirmSummary.Builder solicitorAdvocateYN(String solicitorAdvocateYN) {
      this.instance.solicitorAdvocateYN(solicitorAdvocateYN);
      return this;
    }
    
    public ProviderFirmSummary.Builder advocateLevel(String advocateLevel) {
      this.instance.advocateLevel(advocateLevel);
      return this;
    }
    
    public ProviderFirmSummary.Builder barCouncilRoll(String barCouncilRoll) {
      this.instance.barCouncilRoll(barCouncilRoll);
      return this;
    }
    
    public ProviderFirmSummary.Builder companyHouseNumber(String companyHouseNumber) {
      this.instance.companyHouseNumber(companyHouseNumber);
      return this;
    }
    
    /**
    * returns a built ProviderFirmSummary instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmSummary build() {
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
  public static ProviderFirmSummary.Builder builder() {
    return new ProviderFirmSummary.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmSummary.Builder toBuilder() {
    ProviderFirmSummary.Builder builder = new ProviderFirmSummary.Builder();
    return builder.copyOf(this);
  }

}

