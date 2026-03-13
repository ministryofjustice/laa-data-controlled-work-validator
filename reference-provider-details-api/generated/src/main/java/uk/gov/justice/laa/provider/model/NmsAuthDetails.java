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
 * NmsAuthDetails
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T15:47:03.890223Z[Europe/London]", comments = "Generator version: 7.14.0")
public class NmsAuthDetails implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String description;

  private @Nullable Integer minMatterStarts;

  private @Nullable Integer maxMatterStarts;

  private @Nullable String authorisedLitigator;

  private @Nullable String supervision;

  private @Nullable String serviceCombinations;

  private @Nullable String typeOfPresence;

  private @Nullable String lawSocietyChildrenFlag;

  private @Nullable String advLawSocFamVioFlag;

  private @Nullable String advLawSocFamNoVioFlag;

  private @Nullable String resAccrSpecDomAbuseFlag;

  private @Nullable String resAccrSpecOtherFlag;

  private @Nullable String consortiaId;

  private @Nullable String authorisationStatus;

  private @Nullable String withdrawalType;

  private @Nullable String withdrawalReason;

  private @Nullable String attributeCategory;

  public NmsAuthDetails description(@Nullable String description) {
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

  public NmsAuthDetails minMatterStarts(@Nullable Integer minMatterStarts) {
    this.minMatterStarts = minMatterStarts;
    return this;
  }

  /**
   * Get minMatterStarts
   * @return minMatterStarts
   */
  
  @Schema(name = "minMatterStarts", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("minMatterStarts")
  public @Nullable Integer getMinMatterStarts() {
    return minMatterStarts;
  }

  public void setMinMatterStarts(@Nullable Integer minMatterStarts) {
    this.minMatterStarts = minMatterStarts;
  }

  public NmsAuthDetails maxMatterStarts(@Nullable Integer maxMatterStarts) {
    this.maxMatterStarts = maxMatterStarts;
    return this;
  }

  /**
   * Get maxMatterStarts
   * @return maxMatterStarts
   */
  
  @Schema(name = "maxMatterStarts", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("maxMatterStarts")
  public @Nullable Integer getMaxMatterStarts() {
    return maxMatterStarts;
  }

  public void setMaxMatterStarts(@Nullable Integer maxMatterStarts) {
    this.maxMatterStarts = maxMatterStarts;
  }

  public NmsAuthDetails authorisedLitigator(@Nullable String authorisedLitigator) {
    this.authorisedLitigator = authorisedLitigator;
    return this;
  }

  /**
   * Get authorisedLitigator
   * @return authorisedLitigator
   */
  
  @Schema(name = "authorisedLitigator", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("authorisedLitigator")
  public @Nullable String getAuthorisedLitigator() {
    return authorisedLitigator;
  }

  public void setAuthorisedLitigator(@Nullable String authorisedLitigator) {
    this.authorisedLitigator = authorisedLitigator;
  }

  public NmsAuthDetails supervision(@Nullable String supervision) {
    this.supervision = supervision;
    return this;
  }

  /**
   * Get supervision
   * @return supervision
   */
  
  @Schema(name = "supervision", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("supervision")
  public @Nullable String getSupervision() {
    return supervision;
  }

  public void setSupervision(@Nullable String supervision) {
    this.supervision = supervision;
  }

  public NmsAuthDetails serviceCombinations(@Nullable String serviceCombinations) {
    this.serviceCombinations = serviceCombinations;
    return this;
  }

  /**
   * Get serviceCombinations
   * @return serviceCombinations
   */
  
  @Schema(name = "serviceCombinations", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("serviceCombinations")
  public @Nullable String getServiceCombinations() {
    return serviceCombinations;
  }

  public void setServiceCombinations(@Nullable String serviceCombinations) {
    this.serviceCombinations = serviceCombinations;
  }

  public NmsAuthDetails typeOfPresence(@Nullable String typeOfPresence) {
    this.typeOfPresence = typeOfPresence;
    return this;
  }

  /**
   * Get typeOfPresence
   * @return typeOfPresence
   */
  
  @Schema(name = "typeOfPresence", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("typeOfPresence")
  public @Nullable String getTypeOfPresence() {
    return typeOfPresence;
  }

  public void setTypeOfPresence(@Nullable String typeOfPresence) {
    this.typeOfPresence = typeOfPresence;
  }

  public NmsAuthDetails lawSocietyChildrenFlag(@Nullable String lawSocietyChildrenFlag) {
    this.lawSocietyChildrenFlag = lawSocietyChildrenFlag;
    return this;
  }

  /**
   * Get lawSocietyChildrenFlag
   * @return lawSocietyChildrenFlag
   */
  
  @Schema(name = "lawSocietyChildrenFlag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lawSocietyChildrenFlag")
  public @Nullable String getLawSocietyChildrenFlag() {
    return lawSocietyChildrenFlag;
  }

  public void setLawSocietyChildrenFlag(@Nullable String lawSocietyChildrenFlag) {
    this.lawSocietyChildrenFlag = lawSocietyChildrenFlag;
  }

  public NmsAuthDetails advLawSocFamVioFlag(@Nullable String advLawSocFamVioFlag) {
    this.advLawSocFamVioFlag = advLawSocFamVioFlag;
    return this;
  }

  /**
   * Get advLawSocFamVioFlag
   * @return advLawSocFamVioFlag
   */
  
  @Schema(name = "advLawSocFamVioFlag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("advLawSocFamVioFlag")
  public @Nullable String getAdvLawSocFamVioFlag() {
    return advLawSocFamVioFlag;
  }

  public void setAdvLawSocFamVioFlag(@Nullable String advLawSocFamVioFlag) {
    this.advLawSocFamVioFlag = advLawSocFamVioFlag;
  }

  public NmsAuthDetails advLawSocFamNoVioFlag(@Nullable String advLawSocFamNoVioFlag) {
    this.advLawSocFamNoVioFlag = advLawSocFamNoVioFlag;
    return this;
  }

  /**
   * Get advLawSocFamNoVioFlag
   * @return advLawSocFamNoVioFlag
   */
  
  @Schema(name = "advLawSocFamNoVioFlag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("advLawSocFamNoVioFlag")
  public @Nullable String getAdvLawSocFamNoVioFlag() {
    return advLawSocFamNoVioFlag;
  }

  public void setAdvLawSocFamNoVioFlag(@Nullable String advLawSocFamNoVioFlag) {
    this.advLawSocFamNoVioFlag = advLawSocFamNoVioFlag;
  }

  public NmsAuthDetails resAccrSpecDomAbuseFlag(@Nullable String resAccrSpecDomAbuseFlag) {
    this.resAccrSpecDomAbuseFlag = resAccrSpecDomAbuseFlag;
    return this;
  }

  /**
   * Get resAccrSpecDomAbuseFlag
   * @return resAccrSpecDomAbuseFlag
   */
  
  @Schema(name = "resAccrSpecDomAbuseFlag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("resAccrSpecDomAbuseFlag")
  public @Nullable String getResAccrSpecDomAbuseFlag() {
    return resAccrSpecDomAbuseFlag;
  }

  public void setResAccrSpecDomAbuseFlag(@Nullable String resAccrSpecDomAbuseFlag) {
    this.resAccrSpecDomAbuseFlag = resAccrSpecDomAbuseFlag;
  }

  public NmsAuthDetails resAccrSpecOtherFlag(@Nullable String resAccrSpecOtherFlag) {
    this.resAccrSpecOtherFlag = resAccrSpecOtherFlag;
    return this;
  }

  /**
   * Get resAccrSpecOtherFlag
   * @return resAccrSpecOtherFlag
   */
  
  @Schema(name = "resAccrSpecOtherFlag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("resAccrSpecOtherFlag")
  public @Nullable String getResAccrSpecOtherFlag() {
    return resAccrSpecOtherFlag;
  }

  public void setResAccrSpecOtherFlag(@Nullable String resAccrSpecOtherFlag) {
    this.resAccrSpecOtherFlag = resAccrSpecOtherFlag;
  }

  public NmsAuthDetails consortiaId(@Nullable String consortiaId) {
    this.consortiaId = consortiaId;
    return this;
  }

  /**
   * Get consortiaId
   * @return consortiaId
   */
  
  @Schema(name = "consortiaId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("consortiaId")
  public @Nullable String getConsortiaId() {
    return consortiaId;
  }

  public void setConsortiaId(@Nullable String consortiaId) {
    this.consortiaId = consortiaId;
  }

  public NmsAuthDetails authorisationStatus(@Nullable String authorisationStatus) {
    this.authorisationStatus = authorisationStatus;
    return this;
  }

  /**
   * Get authorisationStatus
   * @return authorisationStatus
   */
  
  @Schema(name = "authorisationStatus", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("authorisationStatus")
  public @Nullable String getAuthorisationStatus() {
    return authorisationStatus;
  }

  public void setAuthorisationStatus(@Nullable String authorisationStatus) {
    this.authorisationStatus = authorisationStatus;
  }

  public NmsAuthDetails withdrawalType(@Nullable String withdrawalType) {
    this.withdrawalType = withdrawalType;
    return this;
  }

  /**
   * Get withdrawalType
   * @return withdrawalType
   */
  
  @Schema(name = "withdrawalType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("withdrawalType")
  public @Nullable String getWithdrawalType() {
    return withdrawalType;
  }

  public void setWithdrawalType(@Nullable String withdrawalType) {
    this.withdrawalType = withdrawalType;
  }

  public NmsAuthDetails withdrawalReason(@Nullable String withdrawalReason) {
    this.withdrawalReason = withdrawalReason;
    return this;
  }

  /**
   * Get withdrawalReason
   * @return withdrawalReason
   */
  
  @Schema(name = "withdrawalReason", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("withdrawalReason")
  public @Nullable String getWithdrawalReason() {
    return withdrawalReason;
  }

  public void setWithdrawalReason(@Nullable String withdrawalReason) {
    this.withdrawalReason = withdrawalReason;
  }

  public NmsAuthDetails attributeCategory(@Nullable String attributeCategory) {
    this.attributeCategory = attributeCategory;
    return this;
  }

  /**
   * Get attributeCategory
   * @return attributeCategory
   */
  
  @Schema(name = "attributeCategory", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("attributeCategory")
  public @Nullable String getAttributeCategory() {
    return attributeCategory;
  }

  public void setAttributeCategory(@Nullable String attributeCategory) {
    this.attributeCategory = attributeCategory;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NmsAuthDetails nmsAuthDetails = (NmsAuthDetails) o;
    return Objects.equals(this.description, nmsAuthDetails.description) &&
        Objects.equals(this.minMatterStarts, nmsAuthDetails.minMatterStarts) &&
        Objects.equals(this.maxMatterStarts, nmsAuthDetails.maxMatterStarts) &&
        Objects.equals(this.authorisedLitigator, nmsAuthDetails.authorisedLitigator) &&
        Objects.equals(this.supervision, nmsAuthDetails.supervision) &&
        Objects.equals(this.serviceCombinations, nmsAuthDetails.serviceCombinations) &&
        Objects.equals(this.typeOfPresence, nmsAuthDetails.typeOfPresence) &&
        Objects.equals(this.lawSocietyChildrenFlag, nmsAuthDetails.lawSocietyChildrenFlag) &&
        Objects.equals(this.advLawSocFamVioFlag, nmsAuthDetails.advLawSocFamVioFlag) &&
        Objects.equals(this.advLawSocFamNoVioFlag, nmsAuthDetails.advLawSocFamNoVioFlag) &&
        Objects.equals(this.resAccrSpecDomAbuseFlag, nmsAuthDetails.resAccrSpecDomAbuseFlag) &&
        Objects.equals(this.resAccrSpecOtherFlag, nmsAuthDetails.resAccrSpecOtherFlag) &&
        Objects.equals(this.consortiaId, nmsAuthDetails.consortiaId) &&
        Objects.equals(this.authorisationStatus, nmsAuthDetails.authorisationStatus) &&
        Objects.equals(this.withdrawalType, nmsAuthDetails.withdrawalType) &&
        Objects.equals(this.withdrawalReason, nmsAuthDetails.withdrawalReason) &&
        Objects.equals(this.attributeCategory, nmsAuthDetails.attributeCategory);
  }

  @Override
  public int hashCode() {
    return Objects.hash(description, minMatterStarts, maxMatterStarts, authorisedLitigator, supervision, serviceCombinations, typeOfPresence, lawSocietyChildrenFlag, advLawSocFamVioFlag, advLawSocFamNoVioFlag, resAccrSpecDomAbuseFlag, resAccrSpecOtherFlag, consortiaId, authorisationStatus, withdrawalType, withdrawalReason, attributeCategory);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NmsAuthDetails {\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    minMatterStarts: ").append(toIndentedString(minMatterStarts)).append("\n");
    sb.append("    maxMatterStarts: ").append(toIndentedString(maxMatterStarts)).append("\n");
    sb.append("    authorisedLitigator: ").append(toIndentedString(authorisedLitigator)).append("\n");
    sb.append("    supervision: ").append(toIndentedString(supervision)).append("\n");
    sb.append("    serviceCombinations: ").append(toIndentedString(serviceCombinations)).append("\n");
    sb.append("    typeOfPresence: ").append(toIndentedString(typeOfPresence)).append("\n");
    sb.append("    lawSocietyChildrenFlag: ").append(toIndentedString(lawSocietyChildrenFlag)).append("\n");
    sb.append("    advLawSocFamVioFlag: ").append(toIndentedString(advLawSocFamVioFlag)).append("\n");
    sb.append("    advLawSocFamNoVioFlag: ").append(toIndentedString(advLawSocFamNoVioFlag)).append("\n");
    sb.append("    resAccrSpecDomAbuseFlag: ").append(toIndentedString(resAccrSpecDomAbuseFlag)).append("\n");
    sb.append("    resAccrSpecOtherFlag: ").append(toIndentedString(resAccrSpecOtherFlag)).append("\n");
    sb.append("    consortiaId: ").append(toIndentedString(consortiaId)).append("\n");
    sb.append("    authorisationStatus: ").append(toIndentedString(authorisationStatus)).append("\n");
    sb.append("    withdrawalType: ").append(toIndentedString(withdrawalType)).append("\n");
    sb.append("    withdrawalReason: ").append(toIndentedString(withdrawalReason)).append("\n");
    sb.append("    attributeCategory: ").append(toIndentedString(attributeCategory)).append("\n");
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

    private NmsAuthDetails instance;

    public Builder() {
      this(new NmsAuthDetails());
    }

    protected Builder(NmsAuthDetails instance) {
      this.instance = instance;
    }

    protected Builder copyOf(NmsAuthDetails value) { 
      this.instance.setDescription(value.description);
      this.instance.setMinMatterStarts(value.minMatterStarts);
      this.instance.setMaxMatterStarts(value.maxMatterStarts);
      this.instance.setAuthorisedLitigator(value.authorisedLitigator);
      this.instance.setSupervision(value.supervision);
      this.instance.setServiceCombinations(value.serviceCombinations);
      this.instance.setTypeOfPresence(value.typeOfPresence);
      this.instance.setLawSocietyChildrenFlag(value.lawSocietyChildrenFlag);
      this.instance.setAdvLawSocFamVioFlag(value.advLawSocFamVioFlag);
      this.instance.setAdvLawSocFamNoVioFlag(value.advLawSocFamNoVioFlag);
      this.instance.setResAccrSpecDomAbuseFlag(value.resAccrSpecDomAbuseFlag);
      this.instance.setResAccrSpecOtherFlag(value.resAccrSpecOtherFlag);
      this.instance.setConsortiaId(value.consortiaId);
      this.instance.setAuthorisationStatus(value.authorisationStatus);
      this.instance.setWithdrawalType(value.withdrawalType);
      this.instance.setWithdrawalReason(value.withdrawalReason);
      this.instance.setAttributeCategory(value.attributeCategory);
      return this;
    }

    public NmsAuthDetails.Builder description(String description) {
      this.instance.description(description);
      return this;
    }
    
    public NmsAuthDetails.Builder minMatterStarts(Integer minMatterStarts) {
      this.instance.minMatterStarts(minMatterStarts);
      return this;
    }
    
    public NmsAuthDetails.Builder maxMatterStarts(Integer maxMatterStarts) {
      this.instance.maxMatterStarts(maxMatterStarts);
      return this;
    }
    
    public NmsAuthDetails.Builder authorisedLitigator(String authorisedLitigator) {
      this.instance.authorisedLitigator(authorisedLitigator);
      return this;
    }
    
    public NmsAuthDetails.Builder supervision(String supervision) {
      this.instance.supervision(supervision);
      return this;
    }
    
    public NmsAuthDetails.Builder serviceCombinations(String serviceCombinations) {
      this.instance.serviceCombinations(serviceCombinations);
      return this;
    }
    
    public NmsAuthDetails.Builder typeOfPresence(String typeOfPresence) {
      this.instance.typeOfPresence(typeOfPresence);
      return this;
    }
    
    public NmsAuthDetails.Builder lawSocietyChildrenFlag(String lawSocietyChildrenFlag) {
      this.instance.lawSocietyChildrenFlag(lawSocietyChildrenFlag);
      return this;
    }
    
    public NmsAuthDetails.Builder advLawSocFamVioFlag(String advLawSocFamVioFlag) {
      this.instance.advLawSocFamVioFlag(advLawSocFamVioFlag);
      return this;
    }
    
    public NmsAuthDetails.Builder advLawSocFamNoVioFlag(String advLawSocFamNoVioFlag) {
      this.instance.advLawSocFamNoVioFlag(advLawSocFamNoVioFlag);
      return this;
    }
    
    public NmsAuthDetails.Builder resAccrSpecDomAbuseFlag(String resAccrSpecDomAbuseFlag) {
      this.instance.resAccrSpecDomAbuseFlag(resAccrSpecDomAbuseFlag);
      return this;
    }
    
    public NmsAuthDetails.Builder resAccrSpecOtherFlag(String resAccrSpecOtherFlag) {
      this.instance.resAccrSpecOtherFlag(resAccrSpecOtherFlag);
      return this;
    }
    
    public NmsAuthDetails.Builder consortiaId(String consortiaId) {
      this.instance.consortiaId(consortiaId);
      return this;
    }
    
    public NmsAuthDetails.Builder authorisationStatus(String authorisationStatus) {
      this.instance.authorisationStatus(authorisationStatus);
      return this;
    }
    
    public NmsAuthDetails.Builder withdrawalType(String withdrawalType) {
      this.instance.withdrawalType(withdrawalType);
      return this;
    }
    
    public NmsAuthDetails.Builder withdrawalReason(String withdrawalReason) {
      this.instance.withdrawalReason(withdrawalReason);
      return this;
    }
    
    public NmsAuthDetails.Builder attributeCategory(String attributeCategory) {
      this.instance.attributeCategory(attributeCategory);
      return this;
    }
    
    /**
    * returns a built NmsAuthDetails instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public NmsAuthDetails build() {
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
  public static NmsAuthDetails.Builder builder() {
    return new NmsAuthDetails.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public NmsAuthDetails.Builder toBuilder() {
    NmsAuthDetails.Builder builder = new NmsAuthDetails.Builder();
    return builder.copyOf(this);
  }

}

