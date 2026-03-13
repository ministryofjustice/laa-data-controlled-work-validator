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
 * ProviderFirm
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T18:11:19.822784Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirm implements Serializable {

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

  private @Nullable String indemnityReceivedDate;

  private @Nullable String highRiskSupplier;

  private @Nullable String holdAllPaymentsFlag;

  private @Nullable String holdReason;

  private @Nullable String nonProfitOrganisation;

  private @Nullable String smallBusinessFlag;

  private @Nullable String womenOwnedFlag;

  private @Nullable String websiteUrl;

  public ProviderFirm firmNumber(@Nullable String firmNumber) {
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

  public ProviderFirm firmId(@Nullable Integer firmId) {
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

  public ProviderFirm ccmsFirmId(@Nullable Integer ccmsFirmId) {
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

  public ProviderFirm parentFirmId(@Nullable Integer parentFirmId) {
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

  public ProviderFirm firmName(@Nullable String firmName) {
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

  public ProviderFirm firmType(@Nullable String firmType) {
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

  public ProviderFirm constitutionalStatus(@Nullable String constitutionalStatus) {
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

  public ProviderFirm solicitorAdvocateYN(@Nullable String solicitorAdvocateYN) {
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

  public ProviderFirm advocateLevel(@Nullable String advocateLevel) {
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

  public ProviderFirm barCouncilRoll(@Nullable String barCouncilRoll) {
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

  public ProviderFirm companyHouseNumber(@Nullable String companyHouseNumber) {
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

  public ProviderFirm indemnityReceivedDate(@Nullable String indemnityReceivedDate) {
    this.indemnityReceivedDate = indemnityReceivedDate;
    return this;
  }

  /**
   * Get indemnityReceivedDate
   * @return indemnityReceivedDate
   */
  
  @Schema(name = "indemnityReceivedDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("indemnityReceivedDate")
  public @Nullable String getIndemnityReceivedDate() {
    return indemnityReceivedDate;
  }

  public void setIndemnityReceivedDate(@Nullable String indemnityReceivedDate) {
    this.indemnityReceivedDate = indemnityReceivedDate;
  }

  public ProviderFirm highRiskSupplier(@Nullable String highRiskSupplier) {
    this.highRiskSupplier = highRiskSupplier;
    return this;
  }

  /**
   * Get highRiskSupplier
   * @return highRiskSupplier
   */
  
  @Schema(name = "highRiskSupplier", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("highRiskSupplier")
  public @Nullable String getHighRiskSupplier() {
    return highRiskSupplier;
  }

  public void setHighRiskSupplier(@Nullable String highRiskSupplier) {
    this.highRiskSupplier = highRiskSupplier;
  }

  public ProviderFirm holdAllPaymentsFlag(@Nullable String holdAllPaymentsFlag) {
    this.holdAllPaymentsFlag = holdAllPaymentsFlag;
    return this;
  }

  /**
   * Get holdAllPaymentsFlag
   * @return holdAllPaymentsFlag
   */
  
  @Schema(name = "holdAllPaymentsFlag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("holdAllPaymentsFlag")
  public @Nullable String getHoldAllPaymentsFlag() {
    return holdAllPaymentsFlag;
  }

  public void setHoldAllPaymentsFlag(@Nullable String holdAllPaymentsFlag) {
    this.holdAllPaymentsFlag = holdAllPaymentsFlag;
  }

  public ProviderFirm holdReason(@Nullable String holdReason) {
    this.holdReason = holdReason;
    return this;
  }

  /**
   * Get holdReason
   * @return holdReason
   */
  
  @Schema(name = "holdReason", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("holdReason")
  public @Nullable String getHoldReason() {
    return holdReason;
  }

  public void setHoldReason(@Nullable String holdReason) {
    this.holdReason = holdReason;
  }

  public ProviderFirm nonProfitOrganisation(@Nullable String nonProfitOrganisation) {
    this.nonProfitOrganisation = nonProfitOrganisation;
    return this;
  }

  /**
   * Get nonProfitOrganisation
   * @return nonProfitOrganisation
   */
  
  @Schema(name = "nonProfitOrganisation", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nonProfitOrganisation")
  public @Nullable String getNonProfitOrganisation() {
    return nonProfitOrganisation;
  }

  public void setNonProfitOrganisation(@Nullable String nonProfitOrganisation) {
    this.nonProfitOrganisation = nonProfitOrganisation;
  }

  public ProviderFirm smallBusinessFlag(@Nullable String smallBusinessFlag) {
    this.smallBusinessFlag = smallBusinessFlag;
    return this;
  }

  /**
   * Get smallBusinessFlag
   * @return smallBusinessFlag
   */
  
  @Schema(name = "smallBusinessFlag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("smallBusinessFlag")
  public @Nullable String getSmallBusinessFlag() {
    return smallBusinessFlag;
  }

  public void setSmallBusinessFlag(@Nullable String smallBusinessFlag) {
    this.smallBusinessFlag = smallBusinessFlag;
  }

  public ProviderFirm womenOwnedFlag(@Nullable String womenOwnedFlag) {
    this.womenOwnedFlag = womenOwnedFlag;
    return this;
  }

  /**
   * Get womenOwnedFlag
   * @return womenOwnedFlag
   */
  
  @Schema(name = "womenOwnedFlag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("womenOwnedFlag")
  public @Nullable String getWomenOwnedFlag() {
    return womenOwnedFlag;
  }

  public void setWomenOwnedFlag(@Nullable String womenOwnedFlag) {
    this.womenOwnedFlag = womenOwnedFlag;
  }

  public ProviderFirm websiteUrl(@Nullable String websiteUrl) {
    this.websiteUrl = websiteUrl;
    return this;
  }

  /**
   * Get websiteUrl
   * @return websiteUrl
   */
  
  @Schema(name = "websiteUrl", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("websiteUrl")
  public @Nullable String getWebsiteUrl() {
    return websiteUrl;
  }

  public void setWebsiteUrl(@Nullable String websiteUrl) {
    this.websiteUrl = websiteUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirm providerFirm = (ProviderFirm) o;
    return Objects.equals(this.firmNumber, providerFirm.firmNumber) &&
        Objects.equals(this.firmId, providerFirm.firmId) &&
        Objects.equals(this.ccmsFirmId, providerFirm.ccmsFirmId) &&
        Objects.equals(this.parentFirmId, providerFirm.parentFirmId) &&
        Objects.equals(this.firmName, providerFirm.firmName) &&
        Objects.equals(this.firmType, providerFirm.firmType) &&
        Objects.equals(this.constitutionalStatus, providerFirm.constitutionalStatus) &&
        Objects.equals(this.solicitorAdvocateYN, providerFirm.solicitorAdvocateYN) &&
        Objects.equals(this.advocateLevel, providerFirm.advocateLevel) &&
        Objects.equals(this.barCouncilRoll, providerFirm.barCouncilRoll) &&
        Objects.equals(this.companyHouseNumber, providerFirm.companyHouseNumber) &&
        Objects.equals(this.indemnityReceivedDate, providerFirm.indemnityReceivedDate) &&
        Objects.equals(this.highRiskSupplier, providerFirm.highRiskSupplier) &&
        Objects.equals(this.holdAllPaymentsFlag, providerFirm.holdAllPaymentsFlag) &&
        Objects.equals(this.holdReason, providerFirm.holdReason) &&
        Objects.equals(this.nonProfitOrganisation, providerFirm.nonProfitOrganisation) &&
        Objects.equals(this.smallBusinessFlag, providerFirm.smallBusinessFlag) &&
        Objects.equals(this.womenOwnedFlag, providerFirm.womenOwnedFlag) &&
        Objects.equals(this.websiteUrl, providerFirm.websiteUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firmNumber, firmId, ccmsFirmId, parentFirmId, firmName, firmType, constitutionalStatus, solicitorAdvocateYN, advocateLevel, barCouncilRoll, companyHouseNumber, indemnityReceivedDate, highRiskSupplier, holdAllPaymentsFlag, holdReason, nonProfitOrganisation, smallBusinessFlag, womenOwnedFlag, websiteUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirm {\n");
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
    sb.append("    indemnityReceivedDate: ").append(toIndentedString(indemnityReceivedDate)).append("\n");
    sb.append("    highRiskSupplier: ").append(toIndentedString(highRiskSupplier)).append("\n");
    sb.append("    holdAllPaymentsFlag: ").append(toIndentedString(holdAllPaymentsFlag)).append("\n");
    sb.append("    holdReason: ").append(toIndentedString(holdReason)).append("\n");
    sb.append("    nonProfitOrganisation: ").append(toIndentedString(nonProfitOrganisation)).append("\n");
    sb.append("    smallBusinessFlag: ").append(toIndentedString(smallBusinessFlag)).append("\n");
    sb.append("    womenOwnedFlag: ").append(toIndentedString(womenOwnedFlag)).append("\n");
    sb.append("    websiteUrl: ").append(toIndentedString(websiteUrl)).append("\n");
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

    private ProviderFirm instance;

    public Builder() {
      this(new ProviderFirm());
    }

    protected Builder(ProviderFirm instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirm value) { 
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
      this.instance.setIndemnityReceivedDate(value.indemnityReceivedDate);
      this.instance.setHighRiskSupplier(value.highRiskSupplier);
      this.instance.setHoldAllPaymentsFlag(value.holdAllPaymentsFlag);
      this.instance.setHoldReason(value.holdReason);
      this.instance.setNonProfitOrganisation(value.nonProfitOrganisation);
      this.instance.setSmallBusinessFlag(value.smallBusinessFlag);
      this.instance.setWomenOwnedFlag(value.womenOwnedFlag);
      this.instance.setWebsiteUrl(value.websiteUrl);
      return this;
    }

    public ProviderFirm.Builder firmNumber(String firmNumber) {
      this.instance.firmNumber(firmNumber);
      return this;
    }
    
    public ProviderFirm.Builder firmId(Integer firmId) {
      this.instance.firmId(firmId);
      return this;
    }
    
    public ProviderFirm.Builder ccmsFirmId(Integer ccmsFirmId) {
      this.instance.ccmsFirmId(ccmsFirmId);
      return this;
    }
    
    public ProviderFirm.Builder parentFirmId(Integer parentFirmId) {
      this.instance.parentFirmId(parentFirmId);
      return this;
    }
    
    public ProviderFirm.Builder firmName(String firmName) {
      this.instance.firmName(firmName);
      return this;
    }
    
    public ProviderFirm.Builder firmType(String firmType) {
      this.instance.firmType(firmType);
      return this;
    }
    
    public ProviderFirm.Builder constitutionalStatus(String constitutionalStatus) {
      this.instance.constitutionalStatus(constitutionalStatus);
      return this;
    }
    
    public ProviderFirm.Builder solicitorAdvocateYN(String solicitorAdvocateYN) {
      this.instance.solicitorAdvocateYN(solicitorAdvocateYN);
      return this;
    }
    
    public ProviderFirm.Builder advocateLevel(String advocateLevel) {
      this.instance.advocateLevel(advocateLevel);
      return this;
    }
    
    public ProviderFirm.Builder barCouncilRoll(String barCouncilRoll) {
      this.instance.barCouncilRoll(barCouncilRoll);
      return this;
    }
    
    public ProviderFirm.Builder companyHouseNumber(String companyHouseNumber) {
      this.instance.companyHouseNumber(companyHouseNumber);
      return this;
    }
    
    public ProviderFirm.Builder indemnityReceivedDate(String indemnityReceivedDate) {
      this.instance.indemnityReceivedDate(indemnityReceivedDate);
      return this;
    }
    
    public ProviderFirm.Builder highRiskSupplier(String highRiskSupplier) {
      this.instance.highRiskSupplier(highRiskSupplier);
      return this;
    }
    
    public ProviderFirm.Builder holdAllPaymentsFlag(String holdAllPaymentsFlag) {
      this.instance.holdAllPaymentsFlag(holdAllPaymentsFlag);
      return this;
    }
    
    public ProviderFirm.Builder holdReason(String holdReason) {
      this.instance.holdReason(holdReason);
      return this;
    }
    
    public ProviderFirm.Builder nonProfitOrganisation(String nonProfitOrganisation) {
      this.instance.nonProfitOrganisation(nonProfitOrganisation);
      return this;
    }
    
    public ProviderFirm.Builder smallBusinessFlag(String smallBusinessFlag) {
      this.instance.smallBusinessFlag(smallBusinessFlag);
      return this;
    }
    
    public ProviderFirm.Builder womenOwnedFlag(String womenOwnedFlag) {
      this.instance.womenOwnedFlag(womenOwnedFlag);
      return this;
    }
    
    public ProviderFirm.Builder websiteUrl(String websiteUrl) {
      this.instance.websiteUrl(websiteUrl);
      return this;
    }
    
    /**
    * returns a built ProviderFirm instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirm build() {
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
  public static ProviderFirm.Builder builder() {
    return new ProviderFirm.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirm.Builder toBuilder() {
    ProviderFirm.Builder builder = new ProviderFirm.Builder();
    return builder.copyOf(this);
  }

}

