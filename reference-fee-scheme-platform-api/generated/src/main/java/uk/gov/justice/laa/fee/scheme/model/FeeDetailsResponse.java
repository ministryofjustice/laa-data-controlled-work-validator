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
 * FeeDetailsResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T21:51:32.190887Z[Europe/London]", comments = "Generator version: 7.14.0")
public class FeeDetailsResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private String categoryOfLawCode;

  private @Nullable String feeCodeDescription;

  private @Nullable String feeType;

  public FeeDetailsResponse() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public FeeDetailsResponse(String categoryOfLawCode) {
    this.categoryOfLawCode = categoryOfLawCode;
  }

  public FeeDetailsResponse categoryOfLawCode(String categoryOfLawCode) {
    this.categoryOfLawCode = categoryOfLawCode;
    return this;
  }

  /**
   * Category of law corresponding to fee code
   * @return categoryOfLawCode
   */
  @NotNull 
  @Schema(name = "categoryOfLawCode", description = "Category of law corresponding to fee code", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("categoryOfLawCode")
  public String getCategoryOfLawCode() {
    return categoryOfLawCode;
  }

  public void setCategoryOfLawCode(String categoryOfLawCode) {
    this.categoryOfLawCode = categoryOfLawCode;
  }

  public FeeDetailsResponse feeCodeDescription(@Nullable String feeCodeDescription) {
    this.feeCodeDescription = feeCodeDescription;
    return this;
  }

  /**
   * Description of fee code
   * @return feeCodeDescription
   */
  
  @Schema(name = "feeCodeDescription", description = "Description of fee code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("feeCodeDescription")
  public @Nullable String getFeeCodeDescription() {
    return feeCodeDescription;
  }

  public void setFeeCodeDescription(@Nullable String feeCodeDescription) {
    this.feeCodeDescription = feeCodeDescription;
  }

  public FeeDetailsResponse feeType(@Nullable String feeType) {
    this.feeType = feeType;
    return this;
  }

  /**
   * Type of fee, Fixed, Hourly, disbursement only
   * @return feeType
   */
  
  @Schema(name = "feeType", description = "Type of fee, Fixed, Hourly, disbursement only", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("feeType")
  public @Nullable String getFeeType() {
    return feeType;
  }

  public void setFeeType(@Nullable String feeType) {
    this.feeType = feeType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FeeDetailsResponse feeDetailsResponse = (FeeDetailsResponse) o;
    return Objects.equals(this.categoryOfLawCode, feeDetailsResponse.categoryOfLawCode) &&
        Objects.equals(this.feeCodeDescription, feeDetailsResponse.feeCodeDescription) &&
        Objects.equals(this.feeType, feeDetailsResponse.feeType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(categoryOfLawCode, feeCodeDescription, feeType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FeeDetailsResponse {\n");
    sb.append("    categoryOfLawCode: ").append(toIndentedString(categoryOfLawCode)).append("\n");
    sb.append("    feeCodeDescription: ").append(toIndentedString(feeCodeDescription)).append("\n");
    sb.append("    feeType: ").append(toIndentedString(feeType)).append("\n");
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

    private FeeDetailsResponse instance;

    public Builder() {
      this(new FeeDetailsResponse());
    }

    protected Builder(FeeDetailsResponse instance) {
      this.instance = instance;
    }

    protected Builder copyOf(FeeDetailsResponse value) { 
      this.instance.setCategoryOfLawCode(value.categoryOfLawCode);
      this.instance.setFeeCodeDescription(value.feeCodeDescription);
      this.instance.setFeeType(value.feeType);
      return this;
    }

    public FeeDetailsResponse.Builder categoryOfLawCode(String categoryOfLawCode) {
      this.instance.categoryOfLawCode(categoryOfLawCode);
      return this;
    }
    
    public FeeDetailsResponse.Builder feeCodeDescription(String feeCodeDescription) {
      this.instance.feeCodeDescription(feeCodeDescription);
      return this;
    }
    
    public FeeDetailsResponse.Builder feeType(String feeType) {
      this.instance.feeType(feeType);
      return this;
    }
    
    /**
    * returns a built FeeDetailsResponse instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public FeeDetailsResponse build() {
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
  public static FeeDetailsResponse.Builder builder() {
    return new FeeDetailsResponse.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public FeeDetailsResponse.Builder toBuilder() {
    FeeDetailsResponse.Builder builder = new FeeDetailsResponse.Builder();
    return builder.copyOf(this);
  }

}

