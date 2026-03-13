package uk.gov.justice.laa.fee.scheme.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.fee.scheme.model.FeeCalculation;
import uk.gov.justice.laa.fee.scheme.model.ValidationMessagesInner;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * FeeCalculationResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T21:51:32.190887Z[Europe/London]", comments = "Generator version: 7.14.0")
public class FeeCalculationResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String feeCode;

  private @Nullable String schemeId;

  private @Nullable String claimId;

  @Valid
  private List<@Valid ValidationMessagesInner> validationMessages = new ArrayList<>();

  private @Nullable Boolean escapeCaseFlag;

  private @Nullable FeeCalculation feeCalculation;

  public FeeCalculationResponse feeCode(@Nullable String feeCode) {
    this.feeCode = feeCode;
    return this;
  }

  /**
   * Fee code that was supplied via request.
   * @return feeCode
   */
  
  @Schema(name = "feeCode", description = "Fee code that was supplied via request.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("feeCode")
  public @Nullable String getFeeCode() {
    return feeCode;
  }

  public void setFeeCode(@Nullable String feeCode) {
    this.feeCode = feeCode;
  }

  public FeeCalculationResponse schemeId(@Nullable String schemeId) {
    this.schemeId = schemeId;
    return this;
  }

  /**
   * Scheme ID that was used for calculation.
   * @return schemeId
   */
  
  @Schema(name = "schemeId", description = "Scheme ID that was used for calculation.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("schemeId")
  public @Nullable String getSchemeId() {
    return schemeId;
  }

  public void setSchemeId(@Nullable String schemeId) {
    this.schemeId = schemeId;
  }

  public FeeCalculationResponse claimId(@Nullable String claimId) {
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

  public FeeCalculationResponse validationMessages(List<@Valid ValidationMessagesInner> validationMessages) {
    this.validationMessages = validationMessages;
    return this;
  }

  public FeeCalculationResponse addValidationMessagesItem(ValidationMessagesInner validationMessagesItem) {
    if (this.validationMessages == null) {
      this.validationMessages = new ArrayList<>();
    }
    this.validationMessages.add(validationMessagesItem);
    return this;
  }

  /**
   * Get validationMessages
   * @return validationMessages
   */
  @Valid 
  @Schema(name = "validationMessages", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("validationMessages")
  public List<@Valid ValidationMessagesInner> getValidationMessages() {
    return validationMessages;
  }

  public void setValidationMessages(List<@Valid ValidationMessagesInner> validationMessages) {
    this.validationMessages = validationMessages;
  }

  public FeeCalculationResponse escapeCaseFlag(@Nullable Boolean escapeCaseFlag) {
    this.escapeCaseFlag = escapeCaseFlag;
    return this;
  }

  /**
   * For hourly fee code, If escape threshold has been passed, will be true, false otherwise.
   * @return escapeCaseFlag
   */
  
  @Schema(name = "escapeCaseFlag", description = "For hourly fee code, If escape threshold has been passed, will be true, false otherwise.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("escapeCaseFlag")
  public @Nullable Boolean getEscapeCaseFlag() {
    return escapeCaseFlag;
  }

  public void setEscapeCaseFlag(@Nullable Boolean escapeCaseFlag) {
    this.escapeCaseFlag = escapeCaseFlag;
  }

  public FeeCalculationResponse feeCalculation(@Nullable FeeCalculation feeCalculation) {
    this.feeCalculation = feeCalculation;
    return this;
  }

  /**
   * Get feeCalculation
   * @return feeCalculation
   */
  @Valid 
  @Schema(name = "feeCalculation", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("feeCalculation")
  public @Nullable FeeCalculation getFeeCalculation() {
    return feeCalculation;
  }

  public void setFeeCalculation(@Nullable FeeCalculation feeCalculation) {
    this.feeCalculation = feeCalculation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FeeCalculationResponse feeCalculationResponse = (FeeCalculationResponse) o;
    return Objects.equals(this.feeCode, feeCalculationResponse.feeCode) &&
        Objects.equals(this.schemeId, feeCalculationResponse.schemeId) &&
        Objects.equals(this.claimId, feeCalculationResponse.claimId) &&
        Objects.equals(this.validationMessages, feeCalculationResponse.validationMessages) &&
        Objects.equals(this.escapeCaseFlag, feeCalculationResponse.escapeCaseFlag) &&
        Objects.equals(this.feeCalculation, feeCalculationResponse.feeCalculation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(feeCode, schemeId, claimId, validationMessages, escapeCaseFlag, feeCalculation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FeeCalculationResponse {\n");
    sb.append("    feeCode: ").append(toIndentedString(feeCode)).append("\n");
    sb.append("    schemeId: ").append(toIndentedString(schemeId)).append("\n");
    sb.append("    claimId: ").append(toIndentedString(claimId)).append("\n");
    sb.append("    validationMessages: ").append(toIndentedString(validationMessages)).append("\n");
    sb.append("    escapeCaseFlag: ").append(toIndentedString(escapeCaseFlag)).append("\n");
    sb.append("    feeCalculation: ").append(toIndentedString(feeCalculation)).append("\n");
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

    private FeeCalculationResponse instance;

    public Builder() {
      this(new FeeCalculationResponse());
    }

    protected Builder(FeeCalculationResponse instance) {
      this.instance = instance;
    }

    protected Builder copyOf(FeeCalculationResponse value) { 
      this.instance.setFeeCode(value.feeCode);
      this.instance.setSchemeId(value.schemeId);
      this.instance.setClaimId(value.claimId);
      this.instance.setValidationMessages(value.validationMessages);
      this.instance.setEscapeCaseFlag(value.escapeCaseFlag);
      this.instance.setFeeCalculation(value.feeCalculation);
      return this;
    }

    public FeeCalculationResponse.Builder feeCode(String feeCode) {
      this.instance.feeCode(feeCode);
      return this;
    }
    
    public FeeCalculationResponse.Builder schemeId(String schemeId) {
      this.instance.schemeId(schemeId);
      return this;
    }
    
    public FeeCalculationResponse.Builder claimId(String claimId) {
      this.instance.claimId(claimId);
      return this;
    }
    
    public FeeCalculationResponse.Builder validationMessages(List<ValidationMessagesInner> validationMessages) {
      this.instance.validationMessages(validationMessages);
      return this;
    }
    
    public FeeCalculationResponse.Builder escapeCaseFlag(Boolean escapeCaseFlag) {
      this.instance.escapeCaseFlag(escapeCaseFlag);
      return this;
    }
    
    public FeeCalculationResponse.Builder feeCalculation(FeeCalculation feeCalculation) {
      this.instance.feeCalculation(feeCalculation);
      return this;
    }
    
    /**
    * returns a built FeeCalculationResponse instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public FeeCalculationResponse build() {
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
  public static FeeCalculationResponse.Builder builder() {
    return new FeeCalculationResponse.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public FeeCalculationResponse.Builder toBuilder() {
    FeeCalculationResponse.Builder builder = new FeeCalculationResponse.Builder();
    return builder.copyOf(this);
  }

}

