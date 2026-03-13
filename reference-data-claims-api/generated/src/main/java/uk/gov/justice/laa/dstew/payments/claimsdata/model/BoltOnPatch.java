package uk.gov.justice.laa.dstew.payments.claimsdata.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.math.BigDecimal;
import org.springframework.lang.Nullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Bolt on details
 */

@Schema(name = "bolt_on_patch", description = "Bolt on details")
@JsonTypeName("bolt_on_patch")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T21:51:30.932283Z[Europe/London]", comments = "Generator version: 7.14.0")
public class BoltOnPatch implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable BigDecimal boltOnTotalFeeAmount;

  private @Nullable Integer boltOnAdjournedHearingCount;

  private @Nullable BigDecimal boltOnAdjournedHearingFee;

  private @Nullable Integer boltOnCmrhTelephoneCount;

  private @Nullable BigDecimal boltOnCmrhTelephoneFee;

  private @Nullable Integer boltOnCmrhOralCount;

  private @Nullable BigDecimal boltOnCmrhOralFee;

  private @Nullable Integer boltOnHomeOfficeInterviewCount;

  private @Nullable BigDecimal boltOnHomeOfficeInterviewFee;

  private @Nullable BigDecimal boltOnSubstantiveHearingFee;

  private @Nullable Boolean escapeCaseFlag;

  private @Nullable String schemeId;

  public BoltOnPatch boltOnTotalFeeAmount(@Nullable BigDecimal boltOnTotalFeeAmount) {
    this.boltOnTotalFeeAmount = boltOnTotalFeeAmount;
    return this;
  }

  /**
   * Get boltOnTotalFeeAmount
   * @return boltOnTotalFeeAmount
   */
  @Valid 
  @Schema(name = "bolt_on_total_fee_amount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bolt_on_total_fee_amount")
  public @Nullable BigDecimal getBoltOnTotalFeeAmount() {
    return boltOnTotalFeeAmount;
  }

  public void setBoltOnTotalFeeAmount(@Nullable BigDecimal boltOnTotalFeeAmount) {
    this.boltOnTotalFeeAmount = boltOnTotalFeeAmount;
  }

  public BoltOnPatch boltOnAdjournedHearingCount(@Nullable Integer boltOnAdjournedHearingCount) {
    this.boltOnAdjournedHearingCount = boltOnAdjournedHearingCount;
    return this;
  }

  /**
   * Get boltOnAdjournedHearingCount
   * @return boltOnAdjournedHearingCount
   */
  
  @Schema(name = "bolt_on_adjourned_hearing_count", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bolt_on_adjourned_hearing_count")
  public @Nullable Integer getBoltOnAdjournedHearingCount() {
    return boltOnAdjournedHearingCount;
  }

  public void setBoltOnAdjournedHearingCount(@Nullable Integer boltOnAdjournedHearingCount) {
    this.boltOnAdjournedHearingCount = boltOnAdjournedHearingCount;
  }

  public BoltOnPatch boltOnAdjournedHearingFee(@Nullable BigDecimal boltOnAdjournedHearingFee) {
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

  public BoltOnPatch boltOnCmrhTelephoneCount(@Nullable Integer boltOnCmrhTelephoneCount) {
    this.boltOnCmrhTelephoneCount = boltOnCmrhTelephoneCount;
    return this;
  }

  /**
   * Get boltOnCmrhTelephoneCount
   * @return boltOnCmrhTelephoneCount
   */
  
  @Schema(name = "bolt_on_cmrh_telephone_count", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bolt_on_cmrh_telephone_count")
  public @Nullable Integer getBoltOnCmrhTelephoneCount() {
    return boltOnCmrhTelephoneCount;
  }

  public void setBoltOnCmrhTelephoneCount(@Nullable Integer boltOnCmrhTelephoneCount) {
    this.boltOnCmrhTelephoneCount = boltOnCmrhTelephoneCount;
  }

  public BoltOnPatch boltOnCmrhTelephoneFee(@Nullable BigDecimal boltOnCmrhTelephoneFee) {
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

  public BoltOnPatch boltOnCmrhOralCount(@Nullable Integer boltOnCmrhOralCount) {
    this.boltOnCmrhOralCount = boltOnCmrhOralCount;
    return this;
  }

  /**
   * Get boltOnCmrhOralCount
   * @return boltOnCmrhOralCount
   */
  
  @Schema(name = "bolt_on_cmrh_oral_count", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bolt_on_cmrh_oral_count")
  public @Nullable Integer getBoltOnCmrhOralCount() {
    return boltOnCmrhOralCount;
  }

  public void setBoltOnCmrhOralCount(@Nullable Integer boltOnCmrhOralCount) {
    this.boltOnCmrhOralCount = boltOnCmrhOralCount;
  }

  public BoltOnPatch boltOnCmrhOralFee(@Nullable BigDecimal boltOnCmrhOralFee) {
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

  public BoltOnPatch boltOnHomeOfficeInterviewCount(@Nullable Integer boltOnHomeOfficeInterviewCount) {
    this.boltOnHomeOfficeInterviewCount = boltOnHomeOfficeInterviewCount;
    return this;
  }

  /**
   * Get boltOnHomeOfficeInterviewCount
   * @return boltOnHomeOfficeInterviewCount
   */
  
  @Schema(name = "bolt_on_home_office_interview_count", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bolt_on_home_office_interview_count")
  public @Nullable Integer getBoltOnHomeOfficeInterviewCount() {
    return boltOnHomeOfficeInterviewCount;
  }

  public void setBoltOnHomeOfficeInterviewCount(@Nullable Integer boltOnHomeOfficeInterviewCount) {
    this.boltOnHomeOfficeInterviewCount = boltOnHomeOfficeInterviewCount;
  }

  public BoltOnPatch boltOnHomeOfficeInterviewFee(@Nullable BigDecimal boltOnHomeOfficeInterviewFee) {
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

  public BoltOnPatch boltOnSubstantiveHearingFee(@Nullable BigDecimal boltOnSubstantiveHearingFee) {
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

  public BoltOnPatch escapeCaseFlag(@Nullable Boolean escapeCaseFlag) {
    this.escapeCaseFlag = escapeCaseFlag;
    return this;
  }

  /**
   * Get escapeCaseFlag
   * @return escapeCaseFlag
   */
  
  @Schema(name = "escape_case_flag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("escape_case_flag")
  public @Nullable Boolean getEscapeCaseFlag() {
    return escapeCaseFlag;
  }

  public void setEscapeCaseFlag(@Nullable Boolean escapeCaseFlag) {
    this.escapeCaseFlag = escapeCaseFlag;
  }

  public BoltOnPatch schemeId(@Nullable String schemeId) {
    this.schemeId = schemeId;
    return this;
  }

  /**
   * Get schemeId
   * @return schemeId
   */
  
  @Schema(name = "scheme_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("scheme_id")
  public @Nullable String getSchemeId() {
    return schemeId;
  }

  public void setSchemeId(@Nullable String schemeId) {
    this.schemeId = schemeId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BoltOnPatch boltOnPatch = (BoltOnPatch) o;
    return Objects.equals(this.boltOnTotalFeeAmount, boltOnPatch.boltOnTotalFeeAmount) &&
        Objects.equals(this.boltOnAdjournedHearingCount, boltOnPatch.boltOnAdjournedHearingCount) &&
        Objects.equals(this.boltOnAdjournedHearingFee, boltOnPatch.boltOnAdjournedHearingFee) &&
        Objects.equals(this.boltOnCmrhTelephoneCount, boltOnPatch.boltOnCmrhTelephoneCount) &&
        Objects.equals(this.boltOnCmrhTelephoneFee, boltOnPatch.boltOnCmrhTelephoneFee) &&
        Objects.equals(this.boltOnCmrhOralCount, boltOnPatch.boltOnCmrhOralCount) &&
        Objects.equals(this.boltOnCmrhOralFee, boltOnPatch.boltOnCmrhOralFee) &&
        Objects.equals(this.boltOnHomeOfficeInterviewCount, boltOnPatch.boltOnHomeOfficeInterviewCount) &&
        Objects.equals(this.boltOnHomeOfficeInterviewFee, boltOnPatch.boltOnHomeOfficeInterviewFee) &&
        Objects.equals(this.boltOnSubstantiveHearingFee, boltOnPatch.boltOnSubstantiveHearingFee) &&
        Objects.equals(this.escapeCaseFlag, boltOnPatch.escapeCaseFlag) &&
        Objects.equals(this.schemeId, boltOnPatch.schemeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(boltOnTotalFeeAmount, boltOnAdjournedHearingCount, boltOnAdjournedHearingFee, boltOnCmrhTelephoneCount, boltOnCmrhTelephoneFee, boltOnCmrhOralCount, boltOnCmrhOralFee, boltOnHomeOfficeInterviewCount, boltOnHomeOfficeInterviewFee, boltOnSubstantiveHearingFee, escapeCaseFlag, schemeId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BoltOnPatch {\n");
    sb.append("    boltOnTotalFeeAmount: ").append(toIndentedString(boltOnTotalFeeAmount)).append("\n");
    sb.append("    boltOnAdjournedHearingCount: ").append(toIndentedString(boltOnAdjournedHearingCount)).append("\n");
    sb.append("    boltOnAdjournedHearingFee: ").append(toIndentedString(boltOnAdjournedHearingFee)).append("\n");
    sb.append("    boltOnCmrhTelephoneCount: ").append(toIndentedString(boltOnCmrhTelephoneCount)).append("\n");
    sb.append("    boltOnCmrhTelephoneFee: ").append(toIndentedString(boltOnCmrhTelephoneFee)).append("\n");
    sb.append("    boltOnCmrhOralCount: ").append(toIndentedString(boltOnCmrhOralCount)).append("\n");
    sb.append("    boltOnCmrhOralFee: ").append(toIndentedString(boltOnCmrhOralFee)).append("\n");
    sb.append("    boltOnHomeOfficeInterviewCount: ").append(toIndentedString(boltOnHomeOfficeInterviewCount)).append("\n");
    sb.append("    boltOnHomeOfficeInterviewFee: ").append(toIndentedString(boltOnHomeOfficeInterviewFee)).append("\n");
    sb.append("    boltOnSubstantiveHearingFee: ").append(toIndentedString(boltOnSubstantiveHearingFee)).append("\n");
    sb.append("    escapeCaseFlag: ").append(toIndentedString(escapeCaseFlag)).append("\n");
    sb.append("    schemeId: ").append(toIndentedString(schemeId)).append("\n");
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

    private BoltOnPatch instance;

    public Builder() {
      this(new BoltOnPatch());
    }

    protected Builder(BoltOnPatch instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BoltOnPatch value) { 
      this.instance.setBoltOnTotalFeeAmount(value.boltOnTotalFeeAmount);
      this.instance.setBoltOnAdjournedHearingCount(value.boltOnAdjournedHearingCount);
      this.instance.setBoltOnAdjournedHearingFee(value.boltOnAdjournedHearingFee);
      this.instance.setBoltOnCmrhTelephoneCount(value.boltOnCmrhTelephoneCount);
      this.instance.setBoltOnCmrhTelephoneFee(value.boltOnCmrhTelephoneFee);
      this.instance.setBoltOnCmrhOralCount(value.boltOnCmrhOralCount);
      this.instance.setBoltOnCmrhOralFee(value.boltOnCmrhOralFee);
      this.instance.setBoltOnHomeOfficeInterviewCount(value.boltOnHomeOfficeInterviewCount);
      this.instance.setBoltOnHomeOfficeInterviewFee(value.boltOnHomeOfficeInterviewFee);
      this.instance.setBoltOnSubstantiveHearingFee(value.boltOnSubstantiveHearingFee);
      this.instance.setEscapeCaseFlag(value.escapeCaseFlag);
      this.instance.setSchemeId(value.schemeId);
      return this;
    }

    public BoltOnPatch.Builder boltOnTotalFeeAmount(BigDecimal boltOnTotalFeeAmount) {
      this.instance.boltOnTotalFeeAmount(boltOnTotalFeeAmount);
      return this;
    }
    
    public BoltOnPatch.Builder boltOnAdjournedHearingCount(Integer boltOnAdjournedHearingCount) {
      this.instance.boltOnAdjournedHearingCount(boltOnAdjournedHearingCount);
      return this;
    }
    
    public BoltOnPatch.Builder boltOnAdjournedHearingFee(BigDecimal boltOnAdjournedHearingFee) {
      this.instance.boltOnAdjournedHearingFee(boltOnAdjournedHearingFee);
      return this;
    }
    
    public BoltOnPatch.Builder boltOnCmrhTelephoneCount(Integer boltOnCmrhTelephoneCount) {
      this.instance.boltOnCmrhTelephoneCount(boltOnCmrhTelephoneCount);
      return this;
    }
    
    public BoltOnPatch.Builder boltOnCmrhTelephoneFee(BigDecimal boltOnCmrhTelephoneFee) {
      this.instance.boltOnCmrhTelephoneFee(boltOnCmrhTelephoneFee);
      return this;
    }
    
    public BoltOnPatch.Builder boltOnCmrhOralCount(Integer boltOnCmrhOralCount) {
      this.instance.boltOnCmrhOralCount(boltOnCmrhOralCount);
      return this;
    }
    
    public BoltOnPatch.Builder boltOnCmrhOralFee(BigDecimal boltOnCmrhOralFee) {
      this.instance.boltOnCmrhOralFee(boltOnCmrhOralFee);
      return this;
    }
    
    public BoltOnPatch.Builder boltOnHomeOfficeInterviewCount(Integer boltOnHomeOfficeInterviewCount) {
      this.instance.boltOnHomeOfficeInterviewCount(boltOnHomeOfficeInterviewCount);
      return this;
    }
    
    public BoltOnPatch.Builder boltOnHomeOfficeInterviewFee(BigDecimal boltOnHomeOfficeInterviewFee) {
      this.instance.boltOnHomeOfficeInterviewFee(boltOnHomeOfficeInterviewFee);
      return this;
    }
    
    public BoltOnPatch.Builder boltOnSubstantiveHearingFee(BigDecimal boltOnSubstantiveHearingFee) {
      this.instance.boltOnSubstantiveHearingFee(boltOnSubstantiveHearingFee);
      return this;
    }
    
    public BoltOnPatch.Builder escapeCaseFlag(Boolean escapeCaseFlag) {
      this.instance.escapeCaseFlag(escapeCaseFlag);
      return this;
    }
    
    public BoltOnPatch.Builder schemeId(String schemeId) {
      this.instance.schemeId(schemeId);
      return this;
    }
    
    /**
    * returns a built BoltOnPatch instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BoltOnPatch build() {
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
  public static BoltOnPatch.Builder builder() {
    return new BoltOnPatch.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BoltOnPatch.Builder toBuilder() {
    BoltOnPatch.Builder builder = new BoltOnPatch.Builder();
    return builder.copyOf(this);
  }

}

