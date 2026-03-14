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
 * MediationOutreachOffice
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-14T10:05:02.190518Z[Europe/London]", comments = "Generator version: 7.14.0")
public class MediationOutreachOffice implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String vendorSiteCode;

  private @Nullable String outreachLocNumCode;

  private @Nullable String address1;

  private @Nullable String address2;

  private @Nullable String address3;

  private @Nullable String postCode;

  private @Nullable String outreachLocStatus;

  private @Nullable String retirementReason;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate effectiveFromDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate effectiveToDate;

  private @Nullable String attributeCategory;

  public MediationOutreachOffice vendorSiteCode(@Nullable String vendorSiteCode) {
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

  public MediationOutreachOffice outreachLocNumCode(@Nullable String outreachLocNumCode) {
    this.outreachLocNumCode = outreachLocNumCode;
    return this;
  }

  /**
   * Get outreachLocNumCode
   * @return outreachLocNumCode
   */
  
  @Schema(name = "outreachLocNumCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("outreachLocNumCode")
  public @Nullable String getOutreachLocNumCode() {
    return outreachLocNumCode;
  }

  public void setOutreachLocNumCode(@Nullable String outreachLocNumCode) {
    this.outreachLocNumCode = outreachLocNumCode;
  }

  public MediationOutreachOffice address1(@Nullable String address1) {
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

  public MediationOutreachOffice address2(@Nullable String address2) {
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

  public MediationOutreachOffice address3(@Nullable String address3) {
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

  public MediationOutreachOffice postCode(@Nullable String postCode) {
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

  public MediationOutreachOffice outreachLocStatus(@Nullable String outreachLocStatus) {
    this.outreachLocStatus = outreachLocStatus;
    return this;
  }

  /**
   * Get outreachLocStatus
   * @return outreachLocStatus
   */
  
  @Schema(name = "outreachLocStatus", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("outreachLocStatus")
  public @Nullable String getOutreachLocStatus() {
    return outreachLocStatus;
  }

  public void setOutreachLocStatus(@Nullable String outreachLocStatus) {
    this.outreachLocStatus = outreachLocStatus;
  }

  public MediationOutreachOffice retirementReason(@Nullable String retirementReason) {
    this.retirementReason = retirementReason;
    return this;
  }

  /**
   * Get retirementReason
   * @return retirementReason
   */
  
  @Schema(name = "retirementReason", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("retirementReason")
  public @Nullable String getRetirementReason() {
    return retirementReason;
  }

  public void setRetirementReason(@Nullable String retirementReason) {
    this.retirementReason = retirementReason;
  }

  public MediationOutreachOffice effectiveFromDate(@Nullable LocalDate effectiveFromDate) {
    this.effectiveFromDate = effectiveFromDate;
    return this;
  }

  /**
   * Get effectiveFromDate
   * @return effectiveFromDate
   */
  @Valid 
  @Schema(name = "effectiveFromDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("effectiveFromDate")
  public @Nullable LocalDate getEffectiveFromDate() {
    return effectiveFromDate;
  }

  public void setEffectiveFromDate(@Nullable LocalDate effectiveFromDate) {
    this.effectiveFromDate = effectiveFromDate;
  }

  public MediationOutreachOffice effectiveToDate(@Nullable LocalDate effectiveToDate) {
    this.effectiveToDate = effectiveToDate;
    return this;
  }

  /**
   * Get effectiveToDate
   * @return effectiveToDate
   */
  @Valid 
  @Schema(name = "effectiveToDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("effectiveToDate")
  public @Nullable LocalDate getEffectiveToDate() {
    return effectiveToDate;
  }

  public void setEffectiveToDate(@Nullable LocalDate effectiveToDate) {
    this.effectiveToDate = effectiveToDate;
  }

  public MediationOutreachOffice attributeCategory(@Nullable String attributeCategory) {
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
    MediationOutreachOffice mediationOutreachOffice = (MediationOutreachOffice) o;
    return Objects.equals(this.vendorSiteCode, mediationOutreachOffice.vendorSiteCode) &&
        Objects.equals(this.outreachLocNumCode, mediationOutreachOffice.outreachLocNumCode) &&
        Objects.equals(this.address1, mediationOutreachOffice.address1) &&
        Objects.equals(this.address2, mediationOutreachOffice.address2) &&
        Objects.equals(this.address3, mediationOutreachOffice.address3) &&
        Objects.equals(this.postCode, mediationOutreachOffice.postCode) &&
        Objects.equals(this.outreachLocStatus, mediationOutreachOffice.outreachLocStatus) &&
        Objects.equals(this.retirementReason, mediationOutreachOffice.retirementReason) &&
        Objects.equals(this.effectiveFromDate, mediationOutreachOffice.effectiveFromDate) &&
        Objects.equals(this.effectiveToDate, mediationOutreachOffice.effectiveToDate) &&
        Objects.equals(this.attributeCategory, mediationOutreachOffice.attributeCategory);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vendorSiteCode, outreachLocNumCode, address1, address2, address3, postCode, outreachLocStatus, retirementReason, effectiveFromDate, effectiveToDate, attributeCategory);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MediationOutreachOffice {\n");
    sb.append("    vendorSiteCode: ").append(toIndentedString(vendorSiteCode)).append("\n");
    sb.append("    outreachLocNumCode: ").append(toIndentedString(outreachLocNumCode)).append("\n");
    sb.append("    address1: ").append(toIndentedString(address1)).append("\n");
    sb.append("    address2: ").append(toIndentedString(address2)).append("\n");
    sb.append("    address3: ").append(toIndentedString(address3)).append("\n");
    sb.append("    postCode: ").append(toIndentedString(postCode)).append("\n");
    sb.append("    outreachLocStatus: ").append(toIndentedString(outreachLocStatus)).append("\n");
    sb.append("    retirementReason: ").append(toIndentedString(retirementReason)).append("\n");
    sb.append("    effectiveFromDate: ").append(toIndentedString(effectiveFromDate)).append("\n");
    sb.append("    effectiveToDate: ").append(toIndentedString(effectiveToDate)).append("\n");
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

    private MediationOutreachOffice instance;

    public Builder() {
      this(new MediationOutreachOffice());
    }

    protected Builder(MediationOutreachOffice instance) {
      this.instance = instance;
    }

    protected Builder copyOf(MediationOutreachOffice value) { 
      this.instance.setVendorSiteCode(value.vendorSiteCode);
      this.instance.setOutreachLocNumCode(value.outreachLocNumCode);
      this.instance.setAddress1(value.address1);
      this.instance.setAddress2(value.address2);
      this.instance.setAddress3(value.address3);
      this.instance.setPostCode(value.postCode);
      this.instance.setOutreachLocStatus(value.outreachLocStatus);
      this.instance.setRetirementReason(value.retirementReason);
      this.instance.setEffectiveFromDate(value.effectiveFromDate);
      this.instance.setEffectiveToDate(value.effectiveToDate);
      this.instance.setAttributeCategory(value.attributeCategory);
      return this;
    }

    public MediationOutreachOffice.Builder vendorSiteCode(String vendorSiteCode) {
      this.instance.vendorSiteCode(vendorSiteCode);
      return this;
    }
    
    public MediationOutreachOffice.Builder outreachLocNumCode(String outreachLocNumCode) {
      this.instance.outreachLocNumCode(outreachLocNumCode);
      return this;
    }
    
    public MediationOutreachOffice.Builder address1(String address1) {
      this.instance.address1(address1);
      return this;
    }
    
    public MediationOutreachOffice.Builder address2(String address2) {
      this.instance.address2(address2);
      return this;
    }
    
    public MediationOutreachOffice.Builder address3(String address3) {
      this.instance.address3(address3);
      return this;
    }
    
    public MediationOutreachOffice.Builder postCode(String postCode) {
      this.instance.postCode(postCode);
      return this;
    }
    
    public MediationOutreachOffice.Builder outreachLocStatus(String outreachLocStatus) {
      this.instance.outreachLocStatus(outreachLocStatus);
      return this;
    }
    
    public MediationOutreachOffice.Builder retirementReason(String retirementReason) {
      this.instance.retirementReason(retirementReason);
      return this;
    }
    
    public MediationOutreachOffice.Builder effectiveFromDate(LocalDate effectiveFromDate) {
      this.instance.effectiveFromDate(effectiveFromDate);
      return this;
    }
    
    public MediationOutreachOffice.Builder effectiveToDate(LocalDate effectiveToDate) {
      this.instance.effectiveToDate(effectiveToDate);
      return this;
    }
    
    public MediationOutreachOffice.Builder attributeCategory(String attributeCategory) {
      this.instance.attributeCategory(attributeCategory);
      return this;
    }
    
    /**
    * returns a built MediationOutreachOffice instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public MediationOutreachOffice build() {
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
  public static MediationOutreachOffice.Builder builder() {
    return new MediationOutreachOffice.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public MediationOutreachOffice.Builder toBuilder() {
    MediationOutreachOffice.Builder builder = new MediationOutreachOffice.Builder();
    return builder.copyOf(this);
  }

}

