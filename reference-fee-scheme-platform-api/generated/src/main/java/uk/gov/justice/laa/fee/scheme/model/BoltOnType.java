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
 * BoltOnType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-17T18:38:01.044961Z[Europe/London]", comments = "Generator version: 7.14.0")
public class BoltOnType implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable Integer boltOnAdjournedHearing;

  private @Nullable Integer boltOnCmrhOral;

  private @Nullable Integer boltOnCmrhTelephone;

  private @Nullable Integer boltOnHomeOfficeInterview;

  private @Nullable Boolean boltOnSubstantiveHearing;

  public BoltOnType boltOnAdjournedHearing(@Nullable Integer boltOnAdjournedHearing) {
    this.boltOnAdjournedHearing = boltOnAdjournedHearing;
    return this;
  }

  /**
   * Get boltOnAdjournedHearing
   * @return boltOnAdjournedHearing
   */
  
  @Schema(name = "boltOnAdjournedHearing", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("boltOnAdjournedHearing")
  public @Nullable Integer getBoltOnAdjournedHearing() {
    return boltOnAdjournedHearing;
  }

  public void setBoltOnAdjournedHearing(@Nullable Integer boltOnAdjournedHearing) {
    this.boltOnAdjournedHearing = boltOnAdjournedHearing;
  }

  public BoltOnType boltOnCmrhOral(@Nullable Integer boltOnCmrhOral) {
    this.boltOnCmrhOral = boltOnCmrhOral;
    return this;
  }

  /**
   * Get boltOnCmrhOral
   * @return boltOnCmrhOral
   */
  
  @Schema(name = "boltOnCmrhOral", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("boltOnCmrhOral")
  public @Nullable Integer getBoltOnCmrhOral() {
    return boltOnCmrhOral;
  }

  public void setBoltOnCmrhOral(@Nullable Integer boltOnCmrhOral) {
    this.boltOnCmrhOral = boltOnCmrhOral;
  }

  public BoltOnType boltOnCmrhTelephone(@Nullable Integer boltOnCmrhTelephone) {
    this.boltOnCmrhTelephone = boltOnCmrhTelephone;
    return this;
  }

  /**
   * Get boltOnCmrhTelephone
   * @return boltOnCmrhTelephone
   */
  
  @Schema(name = "boltOnCmrhTelephone", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("boltOnCmrhTelephone")
  public @Nullable Integer getBoltOnCmrhTelephone() {
    return boltOnCmrhTelephone;
  }

  public void setBoltOnCmrhTelephone(@Nullable Integer boltOnCmrhTelephone) {
    this.boltOnCmrhTelephone = boltOnCmrhTelephone;
  }

  public BoltOnType boltOnHomeOfficeInterview(@Nullable Integer boltOnHomeOfficeInterview) {
    this.boltOnHomeOfficeInterview = boltOnHomeOfficeInterview;
    return this;
  }

  /**
   * Get boltOnHomeOfficeInterview
   * @return boltOnHomeOfficeInterview
   */
  
  @Schema(name = "boltOnHomeOfficeInterview", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("boltOnHomeOfficeInterview")
  public @Nullable Integer getBoltOnHomeOfficeInterview() {
    return boltOnHomeOfficeInterview;
  }

  public void setBoltOnHomeOfficeInterview(@Nullable Integer boltOnHomeOfficeInterview) {
    this.boltOnHomeOfficeInterview = boltOnHomeOfficeInterview;
  }

  public BoltOnType boltOnSubstantiveHearing(@Nullable Boolean boltOnSubstantiveHearing) {
    this.boltOnSubstantiveHearing = boltOnSubstantiveHearing;
    return this;
  }

  /**
   * Get boltOnSubstantiveHearing
   * @return boltOnSubstantiveHearing
   */
  
  @Schema(name = "boltOnSubstantiveHearing", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("boltOnSubstantiveHearing")
  public @Nullable Boolean getBoltOnSubstantiveHearing() {
    return boltOnSubstantiveHearing;
  }

  public void setBoltOnSubstantiveHearing(@Nullable Boolean boltOnSubstantiveHearing) {
    this.boltOnSubstantiveHearing = boltOnSubstantiveHearing;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BoltOnType boltOnType = (BoltOnType) o;
    return Objects.equals(this.boltOnAdjournedHearing, boltOnType.boltOnAdjournedHearing) &&
        Objects.equals(this.boltOnCmrhOral, boltOnType.boltOnCmrhOral) &&
        Objects.equals(this.boltOnCmrhTelephone, boltOnType.boltOnCmrhTelephone) &&
        Objects.equals(this.boltOnHomeOfficeInterview, boltOnType.boltOnHomeOfficeInterview) &&
        Objects.equals(this.boltOnSubstantiveHearing, boltOnType.boltOnSubstantiveHearing);
  }

  @Override
  public int hashCode() {
    return Objects.hash(boltOnAdjournedHearing, boltOnCmrhOral, boltOnCmrhTelephone, boltOnHomeOfficeInterview, boltOnSubstantiveHearing);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BoltOnType {\n");
    sb.append("    boltOnAdjournedHearing: ").append(toIndentedString(boltOnAdjournedHearing)).append("\n");
    sb.append("    boltOnCmrhOral: ").append(toIndentedString(boltOnCmrhOral)).append("\n");
    sb.append("    boltOnCmrhTelephone: ").append(toIndentedString(boltOnCmrhTelephone)).append("\n");
    sb.append("    boltOnHomeOfficeInterview: ").append(toIndentedString(boltOnHomeOfficeInterview)).append("\n");
    sb.append("    boltOnSubstantiveHearing: ").append(toIndentedString(boltOnSubstantiveHearing)).append("\n");
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

    private BoltOnType instance;

    public Builder() {
      this(new BoltOnType());
    }

    protected Builder(BoltOnType instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BoltOnType value) { 
      this.instance.setBoltOnAdjournedHearing(value.boltOnAdjournedHearing);
      this.instance.setBoltOnCmrhOral(value.boltOnCmrhOral);
      this.instance.setBoltOnCmrhTelephone(value.boltOnCmrhTelephone);
      this.instance.setBoltOnHomeOfficeInterview(value.boltOnHomeOfficeInterview);
      this.instance.setBoltOnSubstantiveHearing(value.boltOnSubstantiveHearing);
      return this;
    }

    public BoltOnType.Builder boltOnAdjournedHearing(Integer boltOnAdjournedHearing) {
      this.instance.boltOnAdjournedHearing(boltOnAdjournedHearing);
      return this;
    }
    
    public BoltOnType.Builder boltOnCmrhOral(Integer boltOnCmrhOral) {
      this.instance.boltOnCmrhOral(boltOnCmrhOral);
      return this;
    }
    
    public BoltOnType.Builder boltOnCmrhTelephone(Integer boltOnCmrhTelephone) {
      this.instance.boltOnCmrhTelephone(boltOnCmrhTelephone);
      return this;
    }
    
    public BoltOnType.Builder boltOnHomeOfficeInterview(Integer boltOnHomeOfficeInterview) {
      this.instance.boltOnHomeOfficeInterview(boltOnHomeOfficeInterview);
      return this;
    }
    
    public BoltOnType.Builder boltOnSubstantiveHearing(Boolean boltOnSubstantiveHearing) {
      this.instance.boltOnSubstantiveHearing(boltOnSubstantiveHearing);
      return this;
    }
    
    /**
    * returns a built BoltOnType instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BoltOnType build() {
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
  public static BoltOnType.Builder builder() {
    return new BoltOnType.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BoltOnType.Builder toBuilder() {
    BoltOnType.Builder builder = new BoltOnType.Builder();
    return builder.copyOf(this);
  }

}

