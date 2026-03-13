package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
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
 * NmsAuthorisationOutreachOffice
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T14:44:48.222559Z[Europe/London]", comments = "Generator version: 7.14.0")
public class NmsAuthorisationOutreachOffice implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String vendorSiteCode;

  private @Nullable String address1;

  private @Nullable String address2;

  private @Nullable String address3;

  private @Nullable String postCode;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate startDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate endDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate creationDate;

  private @Nullable Long outreachLocId;

  private @Nullable String additionalAgreements;

  private @Nullable String frequency;

  private @Nullable Integer authNewMatterStarts;

  public NmsAuthorisationOutreachOffice vendorSiteCode(@Nullable String vendorSiteCode) {
    this.vendorSiteCode = vendorSiteCode;
    return this;
  }

  /**
   * Get vendorSiteCode
   * @return vendorSiteCode
   */
  
  @Schema(name = "vendorSiteCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("vendorSiteCode")
  public @Nullable String getVendorSiteCode() {
    return vendorSiteCode;
  }

  public void setVendorSiteCode(@Nullable String vendorSiteCode) {
    this.vendorSiteCode = vendorSiteCode;
  }

  public NmsAuthorisationOutreachOffice address1(@Nullable String address1) {
    this.address1 = address1;
    return this;
  }

  /**
   * Get address1
   * @return address1
   */
  
  @Schema(name = "address1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("address1")
  public @Nullable String getAddress1() {
    return address1;
  }

  public void setAddress1(@Nullable String address1) {
    this.address1 = address1;
  }

  public NmsAuthorisationOutreachOffice address2(@Nullable String address2) {
    this.address2 = address2;
    return this;
  }

  /**
   * Get address2
   * @return address2
   */
  
  @Schema(name = "address2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("address2")
  public @Nullable String getAddress2() {
    return address2;
  }

  public void setAddress2(@Nullable String address2) {
    this.address2 = address2;
  }

  public NmsAuthorisationOutreachOffice address3(@Nullable String address3) {
    this.address3 = address3;
    return this;
  }

  /**
   * Get address3
   * @return address3
   */
  
  @Schema(name = "address3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("address3")
  public @Nullable String getAddress3() {
    return address3;
  }

  public void setAddress3(@Nullable String address3) {
    this.address3 = address3;
  }

  public NmsAuthorisationOutreachOffice postCode(@Nullable String postCode) {
    this.postCode = postCode;
    return this;
  }

  /**
   * Get postCode
   * @return postCode
   */
  
  @Schema(name = "postCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("postCode")
  public @Nullable String getPostCode() {
    return postCode;
  }

  public void setPostCode(@Nullable String postCode) {
    this.postCode = postCode;
  }

  public NmsAuthorisationOutreachOffice startDate(@Nullable LocalDate startDate) {
    this.startDate = startDate;
    return this;
  }

  /**
   * Get startDate
   * @return startDate
   */
  @Valid 
  @Schema(name = "startDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("startDate")
  public @Nullable LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(@Nullable LocalDate startDate) {
    this.startDate = startDate;
  }

  public NmsAuthorisationOutreachOffice endDate(@Nullable LocalDate endDate) {
    this.endDate = endDate;
    return this;
  }

  /**
   * Get endDate
   * @return endDate
   */
  @Valid 
  @Schema(name = "endDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("endDate")
  public @Nullable LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(@Nullable LocalDate endDate) {
    this.endDate = endDate;
  }

  public NmsAuthorisationOutreachOffice creationDate(@Nullable LocalDate creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  /**
   * Get creationDate
   * @return creationDate
   */
  @Valid 
  @Schema(name = "creationDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("creationDate")
  public @Nullable LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(@Nullable LocalDate creationDate) {
    this.creationDate = creationDate;
  }

  public NmsAuthorisationOutreachOffice outreachLocId(@Nullable Long outreachLocId) {
    this.outreachLocId = outreachLocId;
    return this;
  }

  /**
   * Get outreachLocId
   * @return outreachLocId
   */
  
  @Schema(name = "outreachLocId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("outreachLocId")
  public @Nullable Long getOutreachLocId() {
    return outreachLocId;
  }

  public void setOutreachLocId(@Nullable Long outreachLocId) {
    this.outreachLocId = outreachLocId;
  }

  public NmsAuthorisationOutreachOffice additionalAgreements(@Nullable String additionalAgreements) {
    this.additionalAgreements = additionalAgreements;
    return this;
  }

  /**
   * Get additionalAgreements
   * @return additionalAgreements
   */
  
  @Schema(name = "additionalAgreements", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("additionalAgreements")
  public @Nullable String getAdditionalAgreements() {
    return additionalAgreements;
  }

  public void setAdditionalAgreements(@Nullable String additionalAgreements) {
    this.additionalAgreements = additionalAgreements;
  }

  public NmsAuthorisationOutreachOffice frequency(@Nullable String frequency) {
    this.frequency = frequency;
    return this;
  }

  /**
   * Get frequency
   * @return frequency
   */
  
  @Schema(name = "frequency", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("frequency")
  public @Nullable String getFrequency() {
    return frequency;
  }

  public void setFrequency(@Nullable String frequency) {
    this.frequency = frequency;
  }

  public NmsAuthorisationOutreachOffice authNewMatterStarts(@Nullable Integer authNewMatterStarts) {
    this.authNewMatterStarts = authNewMatterStarts;
    return this;
  }

  /**
   * Get authNewMatterStarts
   * @return authNewMatterStarts
   */
  
  @Schema(name = "authNewMatterStarts", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("authNewMatterStarts")
  public @Nullable Integer getAuthNewMatterStarts() {
    return authNewMatterStarts;
  }

  public void setAuthNewMatterStarts(@Nullable Integer authNewMatterStarts) {
    this.authNewMatterStarts = authNewMatterStarts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NmsAuthorisationOutreachOffice nmsAuthorisationOutreachOffice = (NmsAuthorisationOutreachOffice) o;
    return Objects.equals(this.vendorSiteCode, nmsAuthorisationOutreachOffice.vendorSiteCode) &&
        Objects.equals(this.address1, nmsAuthorisationOutreachOffice.address1) &&
        Objects.equals(this.address2, nmsAuthorisationOutreachOffice.address2) &&
        Objects.equals(this.address3, nmsAuthorisationOutreachOffice.address3) &&
        Objects.equals(this.postCode, nmsAuthorisationOutreachOffice.postCode) &&
        Objects.equals(this.startDate, nmsAuthorisationOutreachOffice.startDate) &&
        Objects.equals(this.endDate, nmsAuthorisationOutreachOffice.endDate) &&
        Objects.equals(this.creationDate, nmsAuthorisationOutreachOffice.creationDate) &&
        Objects.equals(this.outreachLocId, nmsAuthorisationOutreachOffice.outreachLocId) &&
        Objects.equals(this.additionalAgreements, nmsAuthorisationOutreachOffice.additionalAgreements) &&
        Objects.equals(this.frequency, nmsAuthorisationOutreachOffice.frequency) &&
        Objects.equals(this.authNewMatterStarts, nmsAuthorisationOutreachOffice.authNewMatterStarts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vendorSiteCode, address1, address2, address3, postCode, startDate, endDate, creationDate, outreachLocId, additionalAgreements, frequency, authNewMatterStarts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NmsAuthorisationOutreachOffice {\n");
    sb.append("    vendorSiteCode: ").append(toIndentedString(vendorSiteCode)).append("\n");
    sb.append("    address1: ").append(toIndentedString(address1)).append("\n");
    sb.append("    address2: ").append(toIndentedString(address2)).append("\n");
    sb.append("    address3: ").append(toIndentedString(address3)).append("\n");
    sb.append("    postCode: ").append(toIndentedString(postCode)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    outreachLocId: ").append(toIndentedString(outreachLocId)).append("\n");
    sb.append("    additionalAgreements: ").append(toIndentedString(additionalAgreements)).append("\n");
    sb.append("    frequency: ").append(toIndentedString(frequency)).append("\n");
    sb.append("    authNewMatterStarts: ").append(toIndentedString(authNewMatterStarts)).append("\n");
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

    private NmsAuthorisationOutreachOffice instance;

    public Builder() {
      this(new NmsAuthorisationOutreachOffice());
    }

    protected Builder(NmsAuthorisationOutreachOffice instance) {
      this.instance = instance;
    }

    protected Builder copyOf(NmsAuthorisationOutreachOffice value) { 
      this.instance.setVendorSiteCode(value.vendorSiteCode);
      this.instance.setAddress1(value.address1);
      this.instance.setAddress2(value.address2);
      this.instance.setAddress3(value.address3);
      this.instance.setPostCode(value.postCode);
      this.instance.setStartDate(value.startDate);
      this.instance.setEndDate(value.endDate);
      this.instance.setCreationDate(value.creationDate);
      this.instance.setOutreachLocId(value.outreachLocId);
      this.instance.setAdditionalAgreements(value.additionalAgreements);
      this.instance.setFrequency(value.frequency);
      this.instance.setAuthNewMatterStarts(value.authNewMatterStarts);
      return this;
    }

    public NmsAuthorisationOutreachOffice.Builder vendorSiteCode(String vendorSiteCode) {
      this.instance.vendorSiteCode(vendorSiteCode);
      return this;
    }
    
    public NmsAuthorisationOutreachOffice.Builder address1(String address1) {
      this.instance.address1(address1);
      return this;
    }
    
    public NmsAuthorisationOutreachOffice.Builder address2(String address2) {
      this.instance.address2(address2);
      return this;
    }
    
    public NmsAuthorisationOutreachOffice.Builder address3(String address3) {
      this.instance.address3(address3);
      return this;
    }
    
    public NmsAuthorisationOutreachOffice.Builder postCode(String postCode) {
      this.instance.postCode(postCode);
      return this;
    }
    
    public NmsAuthorisationOutreachOffice.Builder startDate(LocalDate startDate) {
      this.instance.startDate(startDate);
      return this;
    }
    
    public NmsAuthorisationOutreachOffice.Builder endDate(LocalDate endDate) {
      this.instance.endDate(endDate);
      return this;
    }
    
    public NmsAuthorisationOutreachOffice.Builder creationDate(LocalDate creationDate) {
      this.instance.creationDate(creationDate);
      return this;
    }
    
    public NmsAuthorisationOutreachOffice.Builder outreachLocId(Long outreachLocId) {
      this.instance.outreachLocId(outreachLocId);
      return this;
    }
    
    public NmsAuthorisationOutreachOffice.Builder additionalAgreements(String additionalAgreements) {
      this.instance.additionalAgreements(additionalAgreements);
      return this;
    }
    
    public NmsAuthorisationOutreachOffice.Builder frequency(String frequency) {
      this.instance.frequency(frequency);
      return this;
    }
    
    public NmsAuthorisationOutreachOffice.Builder authNewMatterStarts(Integer authNewMatterStarts) {
      this.instance.authNewMatterStarts(authNewMatterStarts);
      return this;
    }
    
    /**
    * returns a built NmsAuthorisationOutreachOffice instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public NmsAuthorisationOutreachOffice build() {
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
  public static NmsAuthorisationOutreachOffice.Builder builder() {
    return new NmsAuthorisationOutreachOffice.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public NmsAuthorisationOutreachOffice.Builder toBuilder() {
    NmsAuthorisationOutreachOffice.Builder builder = new NmsAuthorisationOutreachOffice.Builder();
    return builder.copyOf(this);
  }

}

