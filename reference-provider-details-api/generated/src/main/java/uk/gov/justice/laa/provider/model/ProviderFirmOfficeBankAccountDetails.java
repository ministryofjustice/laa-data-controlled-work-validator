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
 * ProviderFirmOfficeBankAccountDetails
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T15:47:03.890223Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmOfficeBankAccountDetails implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable Long vendorSiteId;

  private @Nullable String bankName;

  private @Nullable String bankBranchName;

  private @Nullable String sortCode;

  private @Nullable String accountNumber;

  private @Nullable String bankAccountName;

  private @Nullable String currencyCode;

  private @Nullable String accountType;

  private @Nullable String primaryFlag;

  private @Nullable String addressLine1;

  private @Nullable String addressLine2;

  private @Nullable String addressLine3;

  private @Nullable String city;

  private @Nullable String county;

  private @Nullable String country;

  private @Nullable String zip;

  public ProviderFirmOfficeBankAccountDetails vendorSiteId(@Nullable Long vendorSiteId) {
    this.vendorSiteId = vendorSiteId;
    return this;
  }

  /**
   * Get vendorSiteId
   * @return vendorSiteId
   */
  
  @Schema(name = "vendorSiteId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("vendorSiteId")
  public @Nullable Long getVendorSiteId() {
    return vendorSiteId;
  }

  public void setVendorSiteId(@Nullable Long vendorSiteId) {
    this.vendorSiteId = vendorSiteId;
  }

  public ProviderFirmOfficeBankAccountDetails bankName(@Nullable String bankName) {
    this.bankName = bankName;
    return this;
  }

  /**
   * Get bankName
   * @return bankName
   */
  
  @Schema(name = "bankName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bankName")
  public @Nullable String getBankName() {
    return bankName;
  }

  public void setBankName(@Nullable String bankName) {
    this.bankName = bankName;
  }

  public ProviderFirmOfficeBankAccountDetails bankBranchName(@Nullable String bankBranchName) {
    this.bankBranchName = bankBranchName;
    return this;
  }

  /**
   * Get bankBranchName
   * @return bankBranchName
   */
  
  @Schema(name = "bankBranchName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bankBranchName")
  public @Nullable String getBankBranchName() {
    return bankBranchName;
  }

  public void setBankBranchName(@Nullable String bankBranchName) {
    this.bankBranchName = bankBranchName;
  }

  public ProviderFirmOfficeBankAccountDetails sortCode(@Nullable String sortCode) {
    this.sortCode = sortCode;
    return this;
  }

  /**
   * Get sortCode
   * @return sortCode
   */
  
  @Schema(name = "sortCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("sortCode")
  public @Nullable String getSortCode() {
    return sortCode;
  }

  public void setSortCode(@Nullable String sortCode) {
    this.sortCode = sortCode;
  }

  public ProviderFirmOfficeBankAccountDetails accountNumber(@Nullable String accountNumber) {
    this.accountNumber = accountNumber;
    return this;
  }

  /**
   * Get accountNumber
   * @return accountNumber
   */
  
  @Schema(name = "accountNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("accountNumber")
  public @Nullable String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(@Nullable String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public ProviderFirmOfficeBankAccountDetails bankAccountName(@Nullable String bankAccountName) {
    this.bankAccountName = bankAccountName;
    return this;
  }

  /**
   * Get bankAccountName
   * @return bankAccountName
   */
  
  @Schema(name = "bankAccountName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bankAccountName")
  public @Nullable String getBankAccountName() {
    return bankAccountName;
  }

  public void setBankAccountName(@Nullable String bankAccountName) {
    this.bankAccountName = bankAccountName;
  }

  public ProviderFirmOfficeBankAccountDetails currencyCode(@Nullable String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  /**
   * Get currencyCode
   * @return currencyCode
   */
  
  @Schema(name = "currencyCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("currencyCode")
  public @Nullable String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(@Nullable String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public ProviderFirmOfficeBankAccountDetails accountType(@Nullable String accountType) {
    this.accountType = accountType;
    return this;
  }

  /**
   * Get accountType
   * @return accountType
   */
  
  @Schema(name = "accountType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("accountType")
  public @Nullable String getAccountType() {
    return accountType;
  }

  public void setAccountType(@Nullable String accountType) {
    this.accountType = accountType;
  }

  public ProviderFirmOfficeBankAccountDetails primaryFlag(@Nullable String primaryFlag) {
    this.primaryFlag = primaryFlag;
    return this;
  }

  /**
   * Get primaryFlag
   * @return primaryFlag
   */
  
  @Schema(name = "primaryFlag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("primaryFlag")
  public @Nullable String getPrimaryFlag() {
    return primaryFlag;
  }

  public void setPrimaryFlag(@Nullable String primaryFlag) {
    this.primaryFlag = primaryFlag;
  }

  public ProviderFirmOfficeBankAccountDetails addressLine1(@Nullable String addressLine1) {
    this.addressLine1 = addressLine1;
    return this;
  }

  /**
   * Get addressLine1
   * @return addressLine1
   */
  
  @Schema(name = "addressLine1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("addressLine1")
  public @Nullable String getAddressLine1() {
    return addressLine1;
  }

  public void setAddressLine1(@Nullable String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public ProviderFirmOfficeBankAccountDetails addressLine2(@Nullable String addressLine2) {
    this.addressLine2 = addressLine2;
    return this;
  }

  /**
   * Get addressLine2
   * @return addressLine2
   */
  
  @Schema(name = "addressLine2", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("addressLine2")
  public @Nullable String getAddressLine2() {
    return addressLine2;
  }

  public void setAddressLine2(@Nullable String addressLine2) {
    this.addressLine2 = addressLine2;
  }

  public ProviderFirmOfficeBankAccountDetails addressLine3(@Nullable String addressLine3) {
    this.addressLine3 = addressLine3;
    return this;
  }

  /**
   * Get addressLine3
   * @return addressLine3
   */
  
  @Schema(name = "addressLine3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("addressLine3")
  public @Nullable String getAddressLine3() {
    return addressLine3;
  }

  public void setAddressLine3(@Nullable String addressLine3) {
    this.addressLine3 = addressLine3;
  }

  public ProviderFirmOfficeBankAccountDetails city(@Nullable String city) {
    this.city = city;
    return this;
  }

  /**
   * Get city
   * @return city
   */
  
  @Schema(name = "city", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("city")
  public @Nullable String getCity() {
    return city;
  }

  public void setCity(@Nullable String city) {
    this.city = city;
  }

  public ProviderFirmOfficeBankAccountDetails county(@Nullable String county) {
    this.county = county;
    return this;
  }

  /**
   * Get county
   * @return county
   */
  
  @Schema(name = "county", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("county")
  public @Nullable String getCounty() {
    return county;
  }

  public void setCounty(@Nullable String county) {
    this.county = county;
  }

  public ProviderFirmOfficeBankAccountDetails country(@Nullable String country) {
    this.country = country;
    return this;
  }

  /**
   * Get country
   * @return country
   */
  
  @Schema(name = "country", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("country")
  public @Nullable String getCountry() {
    return country;
  }

  public void setCountry(@Nullable String country) {
    this.country = country;
  }

  public ProviderFirmOfficeBankAccountDetails zip(@Nullable String zip) {
    this.zip = zip;
    return this;
  }

  /**
   * Get zip
   * @return zip
   */
  
  @Schema(name = "zip", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("zip")
  public @Nullable String getZip() {
    return zip;
  }

  public void setZip(@Nullable String zip) {
    this.zip = zip;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirmOfficeBankAccountDetails providerFirmOfficeBankAccountDetails = (ProviderFirmOfficeBankAccountDetails) o;
    return Objects.equals(this.vendorSiteId, providerFirmOfficeBankAccountDetails.vendorSiteId) &&
        Objects.equals(this.bankName, providerFirmOfficeBankAccountDetails.bankName) &&
        Objects.equals(this.bankBranchName, providerFirmOfficeBankAccountDetails.bankBranchName) &&
        Objects.equals(this.sortCode, providerFirmOfficeBankAccountDetails.sortCode) &&
        Objects.equals(this.accountNumber, providerFirmOfficeBankAccountDetails.accountNumber) &&
        Objects.equals(this.bankAccountName, providerFirmOfficeBankAccountDetails.bankAccountName) &&
        Objects.equals(this.currencyCode, providerFirmOfficeBankAccountDetails.currencyCode) &&
        Objects.equals(this.accountType, providerFirmOfficeBankAccountDetails.accountType) &&
        Objects.equals(this.primaryFlag, providerFirmOfficeBankAccountDetails.primaryFlag) &&
        Objects.equals(this.addressLine1, providerFirmOfficeBankAccountDetails.addressLine1) &&
        Objects.equals(this.addressLine2, providerFirmOfficeBankAccountDetails.addressLine2) &&
        Objects.equals(this.addressLine3, providerFirmOfficeBankAccountDetails.addressLine3) &&
        Objects.equals(this.city, providerFirmOfficeBankAccountDetails.city) &&
        Objects.equals(this.county, providerFirmOfficeBankAccountDetails.county) &&
        Objects.equals(this.country, providerFirmOfficeBankAccountDetails.country) &&
        Objects.equals(this.zip, providerFirmOfficeBankAccountDetails.zip);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vendorSiteId, bankName, bankBranchName, sortCode, accountNumber, bankAccountName, currencyCode, accountType, primaryFlag, addressLine1, addressLine2, addressLine3, city, county, country, zip);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmOfficeBankAccountDetails {\n");
    sb.append("    vendorSiteId: ").append(toIndentedString(vendorSiteId)).append("\n");
    sb.append("    bankName: ").append(toIndentedString(bankName)).append("\n");
    sb.append("    bankBranchName: ").append(toIndentedString(bankBranchName)).append("\n");
    sb.append("    sortCode: ").append(toIndentedString(sortCode)).append("\n");
    sb.append("    accountNumber: ").append(toIndentedString(accountNumber)).append("\n");
    sb.append("    bankAccountName: ").append(toIndentedString(bankAccountName)).append("\n");
    sb.append("    currencyCode: ").append(toIndentedString(currencyCode)).append("\n");
    sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
    sb.append("    primaryFlag: ").append(toIndentedString(primaryFlag)).append("\n");
    sb.append("    addressLine1: ").append(toIndentedString(addressLine1)).append("\n");
    sb.append("    addressLine2: ").append(toIndentedString(addressLine2)).append("\n");
    sb.append("    addressLine3: ").append(toIndentedString(addressLine3)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    county: ").append(toIndentedString(county)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    zip: ").append(toIndentedString(zip)).append("\n");
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

    private ProviderFirmOfficeBankAccountDetails instance;

    public Builder() {
      this(new ProviderFirmOfficeBankAccountDetails());
    }

    protected Builder(ProviderFirmOfficeBankAccountDetails instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmOfficeBankAccountDetails value) { 
      this.instance.setVendorSiteId(value.vendorSiteId);
      this.instance.setBankName(value.bankName);
      this.instance.setBankBranchName(value.bankBranchName);
      this.instance.setSortCode(value.sortCode);
      this.instance.setAccountNumber(value.accountNumber);
      this.instance.setBankAccountName(value.bankAccountName);
      this.instance.setCurrencyCode(value.currencyCode);
      this.instance.setAccountType(value.accountType);
      this.instance.setPrimaryFlag(value.primaryFlag);
      this.instance.setAddressLine1(value.addressLine1);
      this.instance.setAddressLine2(value.addressLine2);
      this.instance.setAddressLine3(value.addressLine3);
      this.instance.setCity(value.city);
      this.instance.setCounty(value.county);
      this.instance.setCountry(value.country);
      this.instance.setZip(value.zip);
      return this;
    }

    public ProviderFirmOfficeBankAccountDetails.Builder vendorSiteId(Long vendorSiteId) {
      this.instance.vendorSiteId(vendorSiteId);
      return this;
    }
    
    public ProviderFirmOfficeBankAccountDetails.Builder bankName(String bankName) {
      this.instance.bankName(bankName);
      return this;
    }
    
    public ProviderFirmOfficeBankAccountDetails.Builder bankBranchName(String bankBranchName) {
      this.instance.bankBranchName(bankBranchName);
      return this;
    }
    
    public ProviderFirmOfficeBankAccountDetails.Builder sortCode(String sortCode) {
      this.instance.sortCode(sortCode);
      return this;
    }
    
    public ProviderFirmOfficeBankAccountDetails.Builder accountNumber(String accountNumber) {
      this.instance.accountNumber(accountNumber);
      return this;
    }
    
    public ProviderFirmOfficeBankAccountDetails.Builder bankAccountName(String bankAccountName) {
      this.instance.bankAccountName(bankAccountName);
      return this;
    }
    
    public ProviderFirmOfficeBankAccountDetails.Builder currencyCode(String currencyCode) {
      this.instance.currencyCode(currencyCode);
      return this;
    }
    
    public ProviderFirmOfficeBankAccountDetails.Builder accountType(String accountType) {
      this.instance.accountType(accountType);
      return this;
    }
    
    public ProviderFirmOfficeBankAccountDetails.Builder primaryFlag(String primaryFlag) {
      this.instance.primaryFlag(primaryFlag);
      return this;
    }
    
    public ProviderFirmOfficeBankAccountDetails.Builder addressLine1(String addressLine1) {
      this.instance.addressLine1(addressLine1);
      return this;
    }
    
    public ProviderFirmOfficeBankAccountDetails.Builder addressLine2(String addressLine2) {
      this.instance.addressLine2(addressLine2);
      return this;
    }
    
    public ProviderFirmOfficeBankAccountDetails.Builder addressLine3(String addressLine3) {
      this.instance.addressLine3(addressLine3);
      return this;
    }
    
    public ProviderFirmOfficeBankAccountDetails.Builder city(String city) {
      this.instance.city(city);
      return this;
    }
    
    public ProviderFirmOfficeBankAccountDetails.Builder county(String county) {
      this.instance.county(county);
      return this;
    }
    
    public ProviderFirmOfficeBankAccountDetails.Builder country(String country) {
      this.instance.country(country);
      return this;
    }
    
    public ProviderFirmOfficeBankAccountDetails.Builder zip(String zip) {
      this.instance.zip(zip);
      return this;
    }
    
    /**
    * returns a built ProviderFirmOfficeBankAccountDetails instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmOfficeBankAccountDetails build() {
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
  public static ProviderFirmOfficeBankAccountDetails.Builder builder() {
    return new ProviderFirmOfficeBankAccountDetails.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmOfficeBankAccountDetails.Builder toBuilder() {
    ProviderFirmOfficeBankAccountDetails.Builder builder = new ProviderFirmOfficeBankAccountDetails.Builder();
    return builder.copyOf(this);
  }

}

