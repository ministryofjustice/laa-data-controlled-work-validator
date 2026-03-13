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
 * ProviderFirmOfficeSummary
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T14:44:48.222559Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmOfficeSummary implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable Integer firmOfficeId;

  private @Nullable Integer ccmsFirmOfficeId;

  private @Nullable String firmOfficeCode;

  private @Nullable String officeName;

  private @Nullable String officeCodeAlt;

  private @Nullable String type;

  public ProviderFirmOfficeSummary firmOfficeId(@Nullable Integer firmOfficeId) {
    this.firmOfficeId = firmOfficeId;
    return this;
  }

  /**
   * Get firmOfficeId
   * @return firmOfficeId
   */
  
  @Schema(name = "firmOfficeId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("firmOfficeId")
  public @Nullable Integer getFirmOfficeId() {
    return firmOfficeId;
  }

  public void setFirmOfficeId(@Nullable Integer firmOfficeId) {
    this.firmOfficeId = firmOfficeId;
  }

  public ProviderFirmOfficeSummary ccmsFirmOfficeId(@Nullable Integer ccmsFirmOfficeId) {
    this.ccmsFirmOfficeId = ccmsFirmOfficeId;
    return this;
  }

  /**
   * Get ccmsFirmOfficeId
   * @return ccmsFirmOfficeId
   */
  
  @Schema(name = "ccmsFirmOfficeId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ccmsFirmOfficeId")
  public @Nullable Integer getCcmsFirmOfficeId() {
    return ccmsFirmOfficeId;
  }

  public void setCcmsFirmOfficeId(@Nullable Integer ccmsFirmOfficeId) {
    this.ccmsFirmOfficeId = ccmsFirmOfficeId;
  }

  public ProviderFirmOfficeSummary firmOfficeCode(@Nullable String firmOfficeCode) {
    this.firmOfficeCode = firmOfficeCode;
    return this;
  }

  /**
   * Get firmOfficeCode
   * @return firmOfficeCode
   */
  
  @Schema(name = "firmOfficeCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("firmOfficeCode")
  public @Nullable String getFirmOfficeCode() {
    return firmOfficeCode;
  }

  public void setFirmOfficeCode(@Nullable String firmOfficeCode) {
    this.firmOfficeCode = firmOfficeCode;
  }

  public ProviderFirmOfficeSummary officeName(@Nullable String officeName) {
    this.officeName = officeName;
    return this;
  }

  /**
   * Get officeName
   * @return officeName
   */
  
  @Schema(name = "officeName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("officeName")
  public @Nullable String getOfficeName() {
    return officeName;
  }

  public void setOfficeName(@Nullable String officeName) {
    this.officeName = officeName;
  }

  public ProviderFirmOfficeSummary officeCodeAlt(@Nullable String officeCodeAlt) {
    this.officeCodeAlt = officeCodeAlt;
    return this;
  }

  /**
   * Get officeCodeAlt
   * @return officeCodeAlt
   */
  
  @Schema(name = "officeCodeAlt", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("officeCodeAlt")
  public @Nullable String getOfficeCodeAlt() {
    return officeCodeAlt;
  }

  public void setOfficeCodeAlt(@Nullable String officeCodeAlt) {
    this.officeCodeAlt = officeCodeAlt;
  }

  public ProviderFirmOfficeSummary type(@Nullable String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  
  @Schema(name = "type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type")
  public @Nullable String getType() {
    return type;
  }

  public void setType(@Nullable String type) {
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirmOfficeSummary providerFirmOfficeSummary = (ProviderFirmOfficeSummary) o;
    return Objects.equals(this.firmOfficeId, providerFirmOfficeSummary.firmOfficeId) &&
        Objects.equals(this.ccmsFirmOfficeId, providerFirmOfficeSummary.ccmsFirmOfficeId) &&
        Objects.equals(this.firmOfficeCode, providerFirmOfficeSummary.firmOfficeCode) &&
        Objects.equals(this.officeName, providerFirmOfficeSummary.officeName) &&
        Objects.equals(this.officeCodeAlt, providerFirmOfficeSummary.officeCodeAlt) &&
        Objects.equals(this.type, providerFirmOfficeSummary.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firmOfficeId, ccmsFirmOfficeId, firmOfficeCode, officeName, officeCodeAlt, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmOfficeSummary {\n");
    sb.append("    firmOfficeId: ").append(toIndentedString(firmOfficeId)).append("\n");
    sb.append("    ccmsFirmOfficeId: ").append(toIndentedString(ccmsFirmOfficeId)).append("\n");
    sb.append("    firmOfficeCode: ").append(toIndentedString(firmOfficeCode)).append("\n");
    sb.append("    officeName: ").append(toIndentedString(officeName)).append("\n");
    sb.append("    officeCodeAlt: ").append(toIndentedString(officeCodeAlt)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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

    private ProviderFirmOfficeSummary instance;

    public Builder() {
      this(new ProviderFirmOfficeSummary());
    }

    protected Builder(ProviderFirmOfficeSummary instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmOfficeSummary value) { 
      this.instance.setFirmOfficeId(value.firmOfficeId);
      this.instance.setCcmsFirmOfficeId(value.ccmsFirmOfficeId);
      this.instance.setFirmOfficeCode(value.firmOfficeCode);
      this.instance.setOfficeName(value.officeName);
      this.instance.setOfficeCodeAlt(value.officeCodeAlt);
      this.instance.setType(value.type);
      return this;
    }

    public ProviderFirmOfficeSummary.Builder firmOfficeId(Integer firmOfficeId) {
      this.instance.firmOfficeId(firmOfficeId);
      return this;
    }
    
    public ProviderFirmOfficeSummary.Builder ccmsFirmOfficeId(Integer ccmsFirmOfficeId) {
      this.instance.ccmsFirmOfficeId(ccmsFirmOfficeId);
      return this;
    }
    
    public ProviderFirmOfficeSummary.Builder firmOfficeCode(String firmOfficeCode) {
      this.instance.firmOfficeCode(firmOfficeCode);
      return this;
    }
    
    public ProviderFirmOfficeSummary.Builder officeName(String officeName) {
      this.instance.officeName(officeName);
      return this;
    }
    
    public ProviderFirmOfficeSummary.Builder officeCodeAlt(String officeCodeAlt) {
      this.instance.officeCodeAlt(officeCodeAlt);
      return this;
    }
    
    public ProviderFirmOfficeSummary.Builder type(String type) {
      this.instance.type(type);
      return this;
    }
    
    /**
    * returns a built ProviderFirmOfficeSummary instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmOfficeSummary build() {
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
  public static ProviderFirmOfficeSummary.Builder builder() {
    return new ProviderFirmOfficeSummary.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmOfficeSummary.Builder toBuilder() {
    ProviderFirmOfficeSummary.Builder builder = new ProviderFirmOfficeSummary.Builder();
    return builder.copyOf(this);
  }

}

