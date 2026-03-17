package uk.gov.justice.laa.fee.scheme.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * BoltOnFeeDetails
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-17T18:38:01.044961Z[Europe/London]", comments = "Generator version: 7.14.0")
public class BoltOnFeeDetails implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable Double boltOnTotalFeeAmount;

  private @Nullable Integer boltOnAdjournedHearingCount;

  private @Nullable Double boltOnAdjournedHearingFee;

  private @Nullable Integer boltOnCmrhTelephoneCount;

  private @Nullable Double boltOnCmrhTelephoneFee;

  private @Nullable Integer boltOnCmrhOralCount;

  private @Nullable Double boltOnCmrhOralFee;

  private @Nullable Integer boltOnHomeOfficeInterviewCount;

  private @Nullable Double boltOnHomeOfficeInterviewFee;

  private @Nullable Double boltOnSubstantiveHearingFee;

  public BoltOnFeeDetails boltOnTotalFeeAmount(@Nullable Double boltOnTotalFeeAmount) {
    this.boltOnTotalFeeAmount = boltOnTotalFeeAmount;
    return this;
  }

  /**
   * Get boltOnTotalFeeAmount
   * @return boltOnTotalFeeAmount
   */
  
  @Schema(name = "boltOnTotalFeeAmount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("boltOnTotalFeeAmount")
  public @Nullable Double getBoltOnTotalFeeAmount() {
    return boltOnTotalFeeAmount;
  }

  public void setBoltOnTotalFeeAmount(@Nullable Double boltOnTotalFeeAmount) {
    this.boltOnTotalFeeAmount = boltOnTotalFeeAmount;
  }

  public BoltOnFeeDetails boltOnAdjournedHearingCount(@Nullable Integer boltOnAdjournedHearingCount) {
    this.boltOnAdjournedHearingCount = boltOnAdjournedHearingCount;
    return this;
  }

  /**
   * Get boltOnAdjournedHearingCount
   * @return boltOnAdjournedHearingCount
   */
  
  @Schema(name = "boltOnAdjournedHearingCount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("boltOnAdjournedHearingCount")
  public @Nullable Integer getBoltOnAdjournedHearingCount() {
    return boltOnAdjournedHearingCount;
  }

  public void setBoltOnAdjournedHearingCount(@Nullable Integer boltOnAdjournedHearingCount) {
    this.boltOnAdjournedHearingCount = boltOnAdjournedHearingCount;
  }

  public BoltOnFeeDetails boltOnAdjournedHearingFee(@Nullable Double boltOnAdjournedHearingFee) {
    this.boltOnAdjournedHearingFee = boltOnAdjournedHearingFee;
    return this;
  }

  /**
   * Get boltOnAdjournedHearingFee
   * @return boltOnAdjournedHearingFee
   */
  
  @Schema(name = "boltOnAdjournedHearingFee", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("boltOnAdjournedHearingFee")
  public @Nullable Double getBoltOnAdjournedHearingFee() {
    return boltOnAdjournedHearingFee;
  }

  public void setBoltOnAdjournedHearingFee(@Nullable Double boltOnAdjournedHearingFee) {
    this.boltOnAdjournedHearingFee = boltOnAdjournedHearingFee;
  }

  public BoltOnFeeDetails boltOnCmrhTelephoneCount(@Nullable Integer boltOnCmrhTelephoneCount) {
    this.boltOnCmrhTelephoneCount = boltOnCmrhTelephoneCount;
    return this;
  }

  /**
   * Get boltOnCmrhTelephoneCount
   * @return boltOnCmrhTelephoneCount
   */
  
  @Schema(name = "boltOnCmrhTelephoneCount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("boltOnCmrhTelephoneCount")
  public @Nullable Integer getBoltOnCmrhTelephoneCount() {
    return boltOnCmrhTelephoneCount;
  }

  public void setBoltOnCmrhTelephoneCount(@Nullable Integer boltOnCmrhTelephoneCount) {
    this.boltOnCmrhTelephoneCount = boltOnCmrhTelephoneCount;
  }

  public BoltOnFeeDetails boltOnCmrhTelephoneFee(@Nullable Double boltOnCmrhTelephoneFee) {
    this.boltOnCmrhTelephoneFee = boltOnCmrhTelephoneFee;
    return this;
  }

  /**
   * Get boltOnCmrhTelephoneFee
   * @return boltOnCmrhTelephoneFee
   */
  
  @Schema(name = "boltOnCmrhTelephoneFee", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("boltOnCmrhTelephoneFee")
  public @Nullable Double getBoltOnCmrhTelephoneFee() {
    return boltOnCmrhTelephoneFee;
  }

  public void setBoltOnCmrhTelephoneFee(@Nullable Double boltOnCmrhTelephoneFee) {
    this.boltOnCmrhTelephoneFee = boltOnCmrhTelephoneFee;
  }

  public BoltOnFeeDetails boltOnCmrhOralCount(@Nullable Integer boltOnCmrhOralCount) {
    this.boltOnCmrhOralCount = boltOnCmrhOralCount;
    return this;
  }

  /**
   * Get boltOnCmrhOralCount
   * @return boltOnCmrhOralCount
   */
  
  @Schema(name = "boltOnCmrhOralCount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("boltOnCmrhOralCount")
  public @Nullable Integer getBoltOnCmrhOralCount() {
    return boltOnCmrhOralCount;
  }

  public void setBoltOnCmrhOralCount(@Nullable Integer boltOnCmrhOralCount) {
    this.boltOnCmrhOralCount = boltOnCmrhOralCount;
  }

  public BoltOnFeeDetails boltOnCmrhOralFee(@Nullable Double boltOnCmrhOralFee) {
    this.boltOnCmrhOralFee = boltOnCmrhOralFee;
    return this;
  }

  /**
   * Get boltOnCmrhOralFee
   * @return boltOnCmrhOralFee
   */
  
  @Schema(name = "boltOnCmrhOralFee", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("boltOnCmrhOralFee")
  public @Nullable Double getBoltOnCmrhOralFee() {
    return boltOnCmrhOralFee;
  }

  public void setBoltOnCmrhOralFee(@Nullable Double boltOnCmrhOralFee) {
    this.boltOnCmrhOralFee = boltOnCmrhOralFee;
  }

  public BoltOnFeeDetails boltOnHomeOfficeInterviewCount(@Nullable Integer boltOnHomeOfficeInterviewCount) {
    this.boltOnHomeOfficeInterviewCount = boltOnHomeOfficeInterviewCount;
    return this;
  }

  /**
   * Get boltOnHomeOfficeInterviewCount
   * @return boltOnHomeOfficeInterviewCount
   */
  
  @Schema(name = "boltOnHomeOfficeInterviewCount", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("boltOnHomeOfficeInterviewCount")
  public @Nullable Integer getBoltOnHomeOfficeInterviewCount() {
    return boltOnHomeOfficeInterviewCount;
  }

  public void setBoltOnHomeOfficeInterviewCount(@Nullable Integer boltOnHomeOfficeInterviewCount) {
    this.boltOnHomeOfficeInterviewCount = boltOnHomeOfficeInterviewCount;
  }

  public BoltOnFeeDetails boltOnHomeOfficeInterviewFee(@Nullable Double boltOnHomeOfficeInterviewFee) {
    this.boltOnHomeOfficeInterviewFee = boltOnHomeOfficeInterviewFee;
    return this;
  }

  /**
   * Get boltOnHomeOfficeInterviewFee
   * @return boltOnHomeOfficeInterviewFee
   */
  
  @Schema(name = "boltOnHomeOfficeInterviewFee", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("boltOnHomeOfficeInterviewFee")
  public @Nullable Double getBoltOnHomeOfficeInterviewFee() {
    return boltOnHomeOfficeInterviewFee;
  }

  public void setBoltOnHomeOfficeInterviewFee(@Nullable Double boltOnHomeOfficeInterviewFee) {
    this.boltOnHomeOfficeInterviewFee = boltOnHomeOfficeInterviewFee;
  }

  public BoltOnFeeDetails boltOnSubstantiveHearingFee(@Nullable Double boltOnSubstantiveHearingFee) {
    this.boltOnSubstantiveHearingFee = boltOnSubstantiveHearingFee;
    return this;
  }

  /**
   * Get boltOnSubstantiveHearingFee
   * @return boltOnSubstantiveHearingFee
   */
  
  @Schema(name = "boltOnSubstantiveHearingFee", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("boltOnSubstantiveHearingFee")
  public @Nullable Double getBoltOnSubstantiveHearingFee() {
    return boltOnSubstantiveHearingFee;
  }

  public void setBoltOnSubstantiveHearingFee(@Nullable Double boltOnSubstantiveHearingFee) {
    this.boltOnSubstantiveHearingFee = boltOnSubstantiveHearingFee;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BoltOnFeeDetails boltOnFeeDetails = (BoltOnFeeDetails) o;
    return Objects.equals(this.boltOnTotalFeeAmount, boltOnFeeDetails.boltOnTotalFeeAmount) &&
        Objects.equals(this.boltOnAdjournedHearingCount, boltOnFeeDetails.boltOnAdjournedHearingCount) &&
        Objects.equals(this.boltOnAdjournedHearingFee, boltOnFeeDetails.boltOnAdjournedHearingFee) &&
        Objects.equals(this.boltOnCmrhTelephoneCount, boltOnFeeDetails.boltOnCmrhTelephoneCount) &&
        Objects.equals(this.boltOnCmrhTelephoneFee, boltOnFeeDetails.boltOnCmrhTelephoneFee) &&
        Objects.equals(this.boltOnCmrhOralCount, boltOnFeeDetails.boltOnCmrhOralCount) &&
        Objects.equals(this.boltOnCmrhOralFee, boltOnFeeDetails.boltOnCmrhOralFee) &&
        Objects.equals(this.boltOnHomeOfficeInterviewCount, boltOnFeeDetails.boltOnHomeOfficeInterviewCount) &&
        Objects.equals(this.boltOnHomeOfficeInterviewFee, boltOnFeeDetails.boltOnHomeOfficeInterviewFee) &&
        Objects.equals(this.boltOnSubstantiveHearingFee, boltOnFeeDetails.boltOnSubstantiveHearingFee);
  }

  @Override
  public int hashCode() {
    return Objects.hash(boltOnTotalFeeAmount, boltOnAdjournedHearingCount, boltOnAdjournedHearingFee, boltOnCmrhTelephoneCount, boltOnCmrhTelephoneFee, boltOnCmrhOralCount, boltOnCmrhOralFee, boltOnHomeOfficeInterviewCount, boltOnHomeOfficeInterviewFee, boltOnSubstantiveHearingFee);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BoltOnFeeDetails {\n");
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

    private BoltOnFeeDetails instance;

    public Builder() {
      this(new BoltOnFeeDetails());
    }

    protected Builder(BoltOnFeeDetails instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BoltOnFeeDetails value) { 
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
      return this;
    }

    public BoltOnFeeDetails.Builder boltOnTotalFeeAmount(Double boltOnTotalFeeAmount) {
      this.instance.boltOnTotalFeeAmount(boltOnTotalFeeAmount);
      return this;
    }
    
    public BoltOnFeeDetails.Builder boltOnAdjournedHearingCount(Integer boltOnAdjournedHearingCount) {
      this.instance.boltOnAdjournedHearingCount(boltOnAdjournedHearingCount);
      return this;
    }
    
    public BoltOnFeeDetails.Builder boltOnAdjournedHearingFee(Double boltOnAdjournedHearingFee) {
      this.instance.boltOnAdjournedHearingFee(boltOnAdjournedHearingFee);
      return this;
    }
    
    public BoltOnFeeDetails.Builder boltOnCmrhTelephoneCount(Integer boltOnCmrhTelephoneCount) {
      this.instance.boltOnCmrhTelephoneCount(boltOnCmrhTelephoneCount);
      return this;
    }
    
    public BoltOnFeeDetails.Builder boltOnCmrhTelephoneFee(Double boltOnCmrhTelephoneFee) {
      this.instance.boltOnCmrhTelephoneFee(boltOnCmrhTelephoneFee);
      return this;
    }
    
    public BoltOnFeeDetails.Builder boltOnCmrhOralCount(Integer boltOnCmrhOralCount) {
      this.instance.boltOnCmrhOralCount(boltOnCmrhOralCount);
      return this;
    }
    
    public BoltOnFeeDetails.Builder boltOnCmrhOralFee(Double boltOnCmrhOralFee) {
      this.instance.boltOnCmrhOralFee(boltOnCmrhOralFee);
      return this;
    }
    
    public BoltOnFeeDetails.Builder boltOnHomeOfficeInterviewCount(Integer boltOnHomeOfficeInterviewCount) {
      this.instance.boltOnHomeOfficeInterviewCount(boltOnHomeOfficeInterviewCount);
      return this;
    }
    
    public BoltOnFeeDetails.Builder boltOnHomeOfficeInterviewFee(Double boltOnHomeOfficeInterviewFee) {
      this.instance.boltOnHomeOfficeInterviewFee(boltOnHomeOfficeInterviewFee);
      return this;
    }
    
    public BoltOnFeeDetails.Builder boltOnSubstantiveHearingFee(Double boltOnSubstantiveHearingFee) {
      this.instance.boltOnSubstantiveHearingFee(boltOnSubstantiveHearingFee);
      return this;
    }
    
    /**
    * returns a built BoltOnFeeDetails instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BoltOnFeeDetails build() {
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
  public static BoltOnFeeDetails.Builder builder() {
    return new BoltOnFeeDetails.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BoltOnFeeDetails.Builder toBuilder() {
    BoltOnFeeDetails.Builder builder = new BoltOnFeeDetails.Builder();
    return builder.copyOf(this);
  }

}

