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
 * FirmOfficeContractDetails
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T21:51:32.806274Z[Europe/London]", comments = "Generator version: 7.14.0")
public class FirmOfficeContractDetails implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String categoryOfLaw;

  private @Nullable String subCategoryLaw;

  private @Nullable String authorisationType;

  private @Nullable String newMatters;

  private @Nullable String contractualDevolvedPowers;

  private @Nullable String remainderAuthorisation;

  public FirmOfficeContractDetails categoryOfLaw(@Nullable String categoryOfLaw) {
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

  public FirmOfficeContractDetails subCategoryLaw(@Nullable String subCategoryLaw) {
    this.subCategoryLaw = subCategoryLaw;
    return this;
  }

  /**
   * Get subCategoryLaw
   * @return subCategoryLaw
   */
  
  @Schema(name = "subCategoryLaw", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("subCategoryLaw")
  public @Nullable String getSubCategoryLaw() {
    return subCategoryLaw;
  }

  public void setSubCategoryLaw(@Nullable String subCategoryLaw) {
    this.subCategoryLaw = subCategoryLaw;
  }

  public FirmOfficeContractDetails authorisationType(@Nullable String authorisationType) {
    this.authorisationType = authorisationType;
    return this;
  }

  /**
   * Get authorisationType
   * @return authorisationType
   */
  
  @Schema(name = "authorisationType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("authorisationType")
  public @Nullable String getAuthorisationType() {
    return authorisationType;
  }

  public void setAuthorisationType(@Nullable String authorisationType) {
    this.authorisationType = authorisationType;
  }

  public FirmOfficeContractDetails newMatters(@Nullable String newMatters) {
    this.newMatters = newMatters;
    return this;
  }

  /**
   * Get newMatters
   * @return newMatters
   */
  
  @Schema(name = "newMatters", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("newMatters")
  public @Nullable String getNewMatters() {
    return newMatters;
  }

  public void setNewMatters(@Nullable String newMatters) {
    this.newMatters = newMatters;
  }

  public FirmOfficeContractDetails contractualDevolvedPowers(@Nullable String contractualDevolvedPowers) {
    this.contractualDevolvedPowers = contractualDevolvedPowers;
    return this;
  }

  /**
   * Get contractualDevolvedPowers
   * @return contractualDevolvedPowers
   */
  
  @Schema(name = "contractualDevolvedPowers", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("contractualDevolvedPowers")
  public @Nullable String getContractualDevolvedPowers() {
    return contractualDevolvedPowers;
  }

  public void setContractualDevolvedPowers(@Nullable String contractualDevolvedPowers) {
    this.contractualDevolvedPowers = contractualDevolvedPowers;
  }

  public FirmOfficeContractDetails remainderAuthorisation(@Nullable String remainderAuthorisation) {
    this.remainderAuthorisation = remainderAuthorisation;
    return this;
  }

  /**
   * Get remainderAuthorisation
   * @return remainderAuthorisation
   */
  
  @Schema(name = "remainderAuthorisation", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("remainderAuthorisation")
  public @Nullable String getRemainderAuthorisation() {
    return remainderAuthorisation;
  }

  public void setRemainderAuthorisation(@Nullable String remainderAuthorisation) {
    this.remainderAuthorisation = remainderAuthorisation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FirmOfficeContractDetails firmOfficeContractDetails = (FirmOfficeContractDetails) o;
    return Objects.equals(this.categoryOfLaw, firmOfficeContractDetails.categoryOfLaw) &&
        Objects.equals(this.subCategoryLaw, firmOfficeContractDetails.subCategoryLaw) &&
        Objects.equals(this.authorisationType, firmOfficeContractDetails.authorisationType) &&
        Objects.equals(this.newMatters, firmOfficeContractDetails.newMatters) &&
        Objects.equals(this.contractualDevolvedPowers, firmOfficeContractDetails.contractualDevolvedPowers) &&
        Objects.equals(this.remainderAuthorisation, firmOfficeContractDetails.remainderAuthorisation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(categoryOfLaw, subCategoryLaw, authorisationType, newMatters, contractualDevolvedPowers, remainderAuthorisation);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FirmOfficeContractDetails {\n");
    sb.append("    categoryOfLaw: ").append(toIndentedString(categoryOfLaw)).append("\n");
    sb.append("    subCategoryLaw: ").append(toIndentedString(subCategoryLaw)).append("\n");
    sb.append("    authorisationType: ").append(toIndentedString(authorisationType)).append("\n");
    sb.append("    newMatters: ").append(toIndentedString(newMatters)).append("\n");
    sb.append("    contractualDevolvedPowers: ").append(toIndentedString(contractualDevolvedPowers)).append("\n");
    sb.append("    remainderAuthorisation: ").append(toIndentedString(remainderAuthorisation)).append("\n");
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

    private FirmOfficeContractDetails instance;

    public Builder() {
      this(new FirmOfficeContractDetails());
    }

    protected Builder(FirmOfficeContractDetails instance) {
      this.instance = instance;
    }

    protected Builder copyOf(FirmOfficeContractDetails value) { 
      this.instance.setCategoryOfLaw(value.categoryOfLaw);
      this.instance.setSubCategoryLaw(value.subCategoryLaw);
      this.instance.setAuthorisationType(value.authorisationType);
      this.instance.setNewMatters(value.newMatters);
      this.instance.setContractualDevolvedPowers(value.contractualDevolvedPowers);
      this.instance.setRemainderAuthorisation(value.remainderAuthorisation);
      return this;
    }

    public FirmOfficeContractDetails.Builder categoryOfLaw(String categoryOfLaw) {
      this.instance.categoryOfLaw(categoryOfLaw);
      return this;
    }
    
    public FirmOfficeContractDetails.Builder subCategoryLaw(String subCategoryLaw) {
      this.instance.subCategoryLaw(subCategoryLaw);
      return this;
    }
    
    public FirmOfficeContractDetails.Builder authorisationType(String authorisationType) {
      this.instance.authorisationType(authorisationType);
      return this;
    }
    
    public FirmOfficeContractDetails.Builder newMatters(String newMatters) {
      this.instance.newMatters(newMatters);
      return this;
    }
    
    public FirmOfficeContractDetails.Builder contractualDevolvedPowers(String contractualDevolvedPowers) {
      this.instance.contractualDevolvedPowers(contractualDevolvedPowers);
      return this;
    }
    
    public FirmOfficeContractDetails.Builder remainderAuthorisation(String remainderAuthorisation) {
      this.instance.remainderAuthorisation(remainderAuthorisation);
      return this;
    }
    
    /**
    * returns a built FirmOfficeContractDetails instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public FirmOfficeContractDetails build() {
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
  public static FirmOfficeContractDetails.Builder builder() {
    return new FirmOfficeContractDetails.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public FirmOfficeContractDetails.Builder toBuilder() {
    FirmOfficeContractDetails.Builder builder = new FirmOfficeContractDetails.Builder();
    return builder.copyOf(this);
  }

}

