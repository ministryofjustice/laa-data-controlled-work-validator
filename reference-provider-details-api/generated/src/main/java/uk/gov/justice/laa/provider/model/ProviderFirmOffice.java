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
 * ProviderFirmOffice
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-16T20:11:03.761871Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmOffice implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable Integer firmOfficeId;

  private @Nullable Integer ccmsFirmOfficeId;

  private @Nullable String firmOfficeCode;

  private @Nullable String officeName;

  private @Nullable String officeCodeAlt;

  private @Nullable String type;

  private @Nullable String addressLine1;

  private @Nullable String addressLine2;

  private @Nullable String addressLine3;

  private @Nullable String addressLine4;

  private @Nullable String city;

  private @Nullable String county;

  private @Nullable String postCode;

  private @Nullable String dxCentre;

  private @Nullable String dxNumber;

  private @Nullable String telephoneAreaCode;

  private @Nullable String telephoneNumber;

  private @Nullable String faxAreaCode;

  private @Nullable String faxNumber;

  private @Nullable String emailAddress;

  private @Nullable String vatRegistrationNumber;

  private @Nullable String headOffice;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate creationDate;

  private @Nullable String lscRegion;

  private @Nullable String lscBidZone;

  private @Nullable String lscAreaOffice;

  private @Nullable String cjsForceName;

  private @Nullable String localAuthority;

  private @Nullable String policeStationAreaName;

  private @Nullable String dutySolicitorAreaName;

  public ProviderFirmOffice firmOfficeId(@Nullable Integer firmOfficeId) {
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

  public ProviderFirmOffice ccmsFirmOfficeId(@Nullable Integer ccmsFirmOfficeId) {
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

  public ProviderFirmOffice firmOfficeCode(@Nullable String firmOfficeCode) {
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

  public ProviderFirmOffice officeName(@Nullable String officeName) {
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

  public ProviderFirmOffice officeCodeAlt(@Nullable String officeCodeAlt) {
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

  public ProviderFirmOffice type(@Nullable String type) {
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

  public ProviderFirmOffice addressLine1(@Nullable String addressLine1) {
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

  public ProviderFirmOffice addressLine2(@Nullable String addressLine2) {
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

  public ProviderFirmOffice addressLine3(@Nullable String addressLine3) {
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

  public ProviderFirmOffice addressLine4(@Nullable String addressLine4) {
    this.addressLine4 = addressLine4;
    return this;
  }

  /**
   * Get addressLine4
   * @return addressLine4
   */
  
  @Schema(name = "addressLine4", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("addressLine4")
  public @Nullable String getAddressLine4() {
    return addressLine4;
  }

  public void setAddressLine4(@Nullable String addressLine4) {
    this.addressLine4 = addressLine4;
  }

  public ProviderFirmOffice city(@Nullable String city) {
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

  public ProviderFirmOffice county(@Nullable String county) {
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

  public ProviderFirmOffice postCode(@Nullable String postCode) {
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

  public ProviderFirmOffice dxCentre(@Nullable String dxCentre) {
    this.dxCentre = dxCentre;
    return this;
  }

  /**
   * Get dxCentre
   * @return dxCentre
   */
  
  @Schema(name = "dxCentre", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dxCentre")
  public @Nullable String getDxCentre() {
    return dxCentre;
  }

  public void setDxCentre(@Nullable String dxCentre) {
    this.dxCentre = dxCentre;
  }

  public ProviderFirmOffice dxNumber(@Nullable String dxNumber) {
    this.dxNumber = dxNumber;
    return this;
  }

  /**
   * Get dxNumber
   * @return dxNumber
   */
  
  @Schema(name = "dxNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dxNumber")
  public @Nullable String getDxNumber() {
    return dxNumber;
  }

  public void setDxNumber(@Nullable String dxNumber) {
    this.dxNumber = dxNumber;
  }

  public ProviderFirmOffice telephoneAreaCode(@Nullable String telephoneAreaCode) {
    this.telephoneAreaCode = telephoneAreaCode;
    return this;
  }

  /**
   * Get telephoneAreaCode
   * @return telephoneAreaCode
   */
  
  @Schema(name = "telephoneAreaCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("telephoneAreaCode")
  public @Nullable String getTelephoneAreaCode() {
    return telephoneAreaCode;
  }

  public void setTelephoneAreaCode(@Nullable String telephoneAreaCode) {
    this.telephoneAreaCode = telephoneAreaCode;
  }

  public ProviderFirmOffice telephoneNumber(@Nullable String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
    return this;
  }

  /**
   * Get telephoneNumber
   * @return telephoneNumber
   */
  
  @Schema(name = "telephoneNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("telephoneNumber")
  public @Nullable String getTelephoneNumber() {
    return telephoneNumber;
  }

  public void setTelephoneNumber(@Nullable String telephoneNumber) {
    this.telephoneNumber = telephoneNumber;
  }

  public ProviderFirmOffice faxAreaCode(@Nullable String faxAreaCode) {
    this.faxAreaCode = faxAreaCode;
    return this;
  }

  /**
   * Get faxAreaCode
   * @return faxAreaCode
   */
  
  @Schema(name = "faxAreaCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("faxAreaCode")
  public @Nullable String getFaxAreaCode() {
    return faxAreaCode;
  }

  public void setFaxAreaCode(@Nullable String faxAreaCode) {
    this.faxAreaCode = faxAreaCode;
  }

  public ProviderFirmOffice faxNumber(@Nullable String faxNumber) {
    this.faxNumber = faxNumber;
    return this;
  }

  /**
   * Get faxNumber
   * @return faxNumber
   */
  
  @Schema(name = "faxNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("faxNumber")
  public @Nullable String getFaxNumber() {
    return faxNumber;
  }

  public void setFaxNumber(@Nullable String faxNumber) {
    this.faxNumber = faxNumber;
  }

  public ProviderFirmOffice emailAddress(@Nullable String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

  /**
   * Get emailAddress
   * @return emailAddress
   */
  
  @Schema(name = "emailAddress", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("emailAddress")
  public @Nullable String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(@Nullable String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public ProviderFirmOffice vatRegistrationNumber(@Nullable String vatRegistrationNumber) {
    this.vatRegistrationNumber = vatRegistrationNumber;
    return this;
  }

  /**
   * Get vatRegistrationNumber
   * @return vatRegistrationNumber
   */
  
  @Schema(name = "vatRegistrationNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("vatRegistrationNumber")
  public @Nullable String getVatRegistrationNumber() {
    return vatRegistrationNumber;
  }

  public void setVatRegistrationNumber(@Nullable String vatRegistrationNumber) {
    this.vatRegistrationNumber = vatRegistrationNumber;
  }

  public ProviderFirmOffice headOffice(@Nullable String headOffice) {
    this.headOffice = headOffice;
    return this;
  }

  /**
   * Get headOffice
   * @return headOffice
   */
  
  @Schema(name = "headOffice", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("headOffice")
  public @Nullable String getHeadOffice() {
    return headOffice;
  }

  public void setHeadOffice(@Nullable String headOffice) {
    this.headOffice = headOffice;
  }

  public ProviderFirmOffice creationDate(@Nullable LocalDate creationDate) {
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

  public ProviderFirmOffice lscRegion(@Nullable String lscRegion) {
    this.lscRegion = lscRegion;
    return this;
  }

  /**
   * Get lscRegion
   * @return lscRegion
   */
  
  @Schema(name = "lscRegion", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lscRegion")
  public @Nullable String getLscRegion() {
    return lscRegion;
  }

  public void setLscRegion(@Nullable String lscRegion) {
    this.lscRegion = lscRegion;
  }

  public ProviderFirmOffice lscBidZone(@Nullable String lscBidZone) {
    this.lscBidZone = lscBidZone;
    return this;
  }

  /**
   * Get lscBidZone
   * @return lscBidZone
   */
  
  @Schema(name = "lscBidZone", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lscBidZone")
  public @Nullable String getLscBidZone() {
    return lscBidZone;
  }

  public void setLscBidZone(@Nullable String lscBidZone) {
    this.lscBidZone = lscBidZone;
  }

  public ProviderFirmOffice lscAreaOffice(@Nullable String lscAreaOffice) {
    this.lscAreaOffice = lscAreaOffice;
    return this;
  }

  /**
   * Get lscAreaOffice
   * @return lscAreaOffice
   */
  
  @Schema(name = "lscAreaOffice", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lscAreaOffice")
  public @Nullable String getLscAreaOffice() {
    return lscAreaOffice;
  }

  public void setLscAreaOffice(@Nullable String lscAreaOffice) {
    this.lscAreaOffice = lscAreaOffice;
  }

  public ProviderFirmOffice cjsForceName(@Nullable String cjsForceName) {
    this.cjsForceName = cjsForceName;
    return this;
  }

  /**
   * Get cjsForceName
   * @return cjsForceName
   */
  
  @Schema(name = "cjsForceName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("cjsForceName")
  public @Nullable String getCjsForceName() {
    return cjsForceName;
  }

  public void setCjsForceName(@Nullable String cjsForceName) {
    this.cjsForceName = cjsForceName;
  }

  public ProviderFirmOffice localAuthority(@Nullable String localAuthority) {
    this.localAuthority = localAuthority;
    return this;
  }

  /**
   * Get localAuthority
   * @return localAuthority
   */
  
  @Schema(name = "localAuthority", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("localAuthority")
  public @Nullable String getLocalAuthority() {
    return localAuthority;
  }

  public void setLocalAuthority(@Nullable String localAuthority) {
    this.localAuthority = localAuthority;
  }

  public ProviderFirmOffice policeStationAreaName(@Nullable String policeStationAreaName) {
    this.policeStationAreaName = policeStationAreaName;
    return this;
  }

  /**
   * Get policeStationAreaName
   * @return policeStationAreaName
   */
  
  @Schema(name = "policeStationAreaName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("policeStationAreaName")
  public @Nullable String getPoliceStationAreaName() {
    return policeStationAreaName;
  }

  public void setPoliceStationAreaName(@Nullable String policeStationAreaName) {
    this.policeStationAreaName = policeStationAreaName;
  }

  public ProviderFirmOffice dutySolicitorAreaName(@Nullable String dutySolicitorAreaName) {
    this.dutySolicitorAreaName = dutySolicitorAreaName;
    return this;
  }

  /**
   * Get dutySolicitorAreaName
   * @return dutySolicitorAreaName
   */
  
  @Schema(name = "dutySolicitorAreaName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("dutySolicitorAreaName")
  public @Nullable String getDutySolicitorAreaName() {
    return dutySolicitorAreaName;
  }

  public void setDutySolicitorAreaName(@Nullable String dutySolicitorAreaName) {
    this.dutySolicitorAreaName = dutySolicitorAreaName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirmOffice providerFirmOffice = (ProviderFirmOffice) o;
    return Objects.equals(this.firmOfficeId, providerFirmOffice.firmOfficeId) &&
        Objects.equals(this.ccmsFirmOfficeId, providerFirmOffice.ccmsFirmOfficeId) &&
        Objects.equals(this.firmOfficeCode, providerFirmOffice.firmOfficeCode) &&
        Objects.equals(this.officeName, providerFirmOffice.officeName) &&
        Objects.equals(this.officeCodeAlt, providerFirmOffice.officeCodeAlt) &&
        Objects.equals(this.type, providerFirmOffice.type) &&
        Objects.equals(this.addressLine1, providerFirmOffice.addressLine1) &&
        Objects.equals(this.addressLine2, providerFirmOffice.addressLine2) &&
        Objects.equals(this.addressLine3, providerFirmOffice.addressLine3) &&
        Objects.equals(this.addressLine4, providerFirmOffice.addressLine4) &&
        Objects.equals(this.city, providerFirmOffice.city) &&
        Objects.equals(this.county, providerFirmOffice.county) &&
        Objects.equals(this.postCode, providerFirmOffice.postCode) &&
        Objects.equals(this.dxCentre, providerFirmOffice.dxCentre) &&
        Objects.equals(this.dxNumber, providerFirmOffice.dxNumber) &&
        Objects.equals(this.telephoneAreaCode, providerFirmOffice.telephoneAreaCode) &&
        Objects.equals(this.telephoneNumber, providerFirmOffice.telephoneNumber) &&
        Objects.equals(this.faxAreaCode, providerFirmOffice.faxAreaCode) &&
        Objects.equals(this.faxNumber, providerFirmOffice.faxNumber) &&
        Objects.equals(this.emailAddress, providerFirmOffice.emailAddress) &&
        Objects.equals(this.vatRegistrationNumber, providerFirmOffice.vatRegistrationNumber) &&
        Objects.equals(this.headOffice, providerFirmOffice.headOffice) &&
        Objects.equals(this.creationDate, providerFirmOffice.creationDate) &&
        Objects.equals(this.lscRegion, providerFirmOffice.lscRegion) &&
        Objects.equals(this.lscBidZone, providerFirmOffice.lscBidZone) &&
        Objects.equals(this.lscAreaOffice, providerFirmOffice.lscAreaOffice) &&
        Objects.equals(this.cjsForceName, providerFirmOffice.cjsForceName) &&
        Objects.equals(this.localAuthority, providerFirmOffice.localAuthority) &&
        Objects.equals(this.policeStationAreaName, providerFirmOffice.policeStationAreaName) &&
        Objects.equals(this.dutySolicitorAreaName, providerFirmOffice.dutySolicitorAreaName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firmOfficeId, ccmsFirmOfficeId, firmOfficeCode, officeName, officeCodeAlt, type, addressLine1, addressLine2, addressLine3, addressLine4, city, county, postCode, dxCentre, dxNumber, telephoneAreaCode, telephoneNumber, faxAreaCode, faxNumber, emailAddress, vatRegistrationNumber, headOffice, creationDate, lscRegion, lscBidZone, lscAreaOffice, cjsForceName, localAuthority, policeStationAreaName, dutySolicitorAreaName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmOffice {\n");
    sb.append("    firmOfficeId: ").append(toIndentedString(firmOfficeId)).append("\n");
    sb.append("    ccmsFirmOfficeId: ").append(toIndentedString(ccmsFirmOfficeId)).append("\n");
    sb.append("    firmOfficeCode: ").append(toIndentedString(firmOfficeCode)).append("\n");
    sb.append("    officeName: ").append(toIndentedString(officeName)).append("\n");
    sb.append("    officeCodeAlt: ").append(toIndentedString(officeCodeAlt)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    addressLine1: ").append(toIndentedString(addressLine1)).append("\n");
    sb.append("    addressLine2: ").append(toIndentedString(addressLine2)).append("\n");
    sb.append("    addressLine3: ").append(toIndentedString(addressLine3)).append("\n");
    sb.append("    addressLine4: ").append(toIndentedString(addressLine4)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    county: ").append(toIndentedString(county)).append("\n");
    sb.append("    postCode: ").append(toIndentedString(postCode)).append("\n");
    sb.append("    dxCentre: ").append(toIndentedString(dxCentre)).append("\n");
    sb.append("    dxNumber: ").append(toIndentedString(dxNumber)).append("\n");
    sb.append("    telephoneAreaCode: ").append(toIndentedString(telephoneAreaCode)).append("\n");
    sb.append("    telephoneNumber: ").append(toIndentedString(telephoneNumber)).append("\n");
    sb.append("    faxAreaCode: ").append(toIndentedString(faxAreaCode)).append("\n");
    sb.append("    faxNumber: ").append(toIndentedString(faxNumber)).append("\n");
    sb.append("    emailAddress: ").append(toIndentedString(emailAddress)).append("\n");
    sb.append("    vatRegistrationNumber: ").append(toIndentedString(vatRegistrationNumber)).append("\n");
    sb.append("    headOffice: ").append(toIndentedString(headOffice)).append("\n");
    sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
    sb.append("    lscRegion: ").append(toIndentedString(lscRegion)).append("\n");
    sb.append("    lscBidZone: ").append(toIndentedString(lscBidZone)).append("\n");
    sb.append("    lscAreaOffice: ").append(toIndentedString(lscAreaOffice)).append("\n");
    sb.append("    cjsForceName: ").append(toIndentedString(cjsForceName)).append("\n");
    sb.append("    localAuthority: ").append(toIndentedString(localAuthority)).append("\n");
    sb.append("    policeStationAreaName: ").append(toIndentedString(policeStationAreaName)).append("\n");
    sb.append("    dutySolicitorAreaName: ").append(toIndentedString(dutySolicitorAreaName)).append("\n");
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

    private ProviderFirmOffice instance;

    public Builder() {
      this(new ProviderFirmOffice());
    }

    protected Builder(ProviderFirmOffice instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmOffice value) { 
      this.instance.setFirmOfficeId(value.firmOfficeId);
      this.instance.setCcmsFirmOfficeId(value.ccmsFirmOfficeId);
      this.instance.setFirmOfficeCode(value.firmOfficeCode);
      this.instance.setOfficeName(value.officeName);
      this.instance.setOfficeCodeAlt(value.officeCodeAlt);
      this.instance.setType(value.type);
      this.instance.setAddressLine1(value.addressLine1);
      this.instance.setAddressLine2(value.addressLine2);
      this.instance.setAddressLine3(value.addressLine3);
      this.instance.setAddressLine4(value.addressLine4);
      this.instance.setCity(value.city);
      this.instance.setCounty(value.county);
      this.instance.setPostCode(value.postCode);
      this.instance.setDxCentre(value.dxCentre);
      this.instance.setDxNumber(value.dxNumber);
      this.instance.setTelephoneAreaCode(value.telephoneAreaCode);
      this.instance.setTelephoneNumber(value.telephoneNumber);
      this.instance.setFaxAreaCode(value.faxAreaCode);
      this.instance.setFaxNumber(value.faxNumber);
      this.instance.setEmailAddress(value.emailAddress);
      this.instance.setVatRegistrationNumber(value.vatRegistrationNumber);
      this.instance.setHeadOffice(value.headOffice);
      this.instance.setCreationDate(value.creationDate);
      this.instance.setLscRegion(value.lscRegion);
      this.instance.setLscBidZone(value.lscBidZone);
      this.instance.setLscAreaOffice(value.lscAreaOffice);
      this.instance.setCjsForceName(value.cjsForceName);
      this.instance.setLocalAuthority(value.localAuthority);
      this.instance.setPoliceStationAreaName(value.policeStationAreaName);
      this.instance.setDutySolicitorAreaName(value.dutySolicitorAreaName);
      return this;
    }

    public ProviderFirmOffice.Builder firmOfficeId(Integer firmOfficeId) {
      this.instance.firmOfficeId(firmOfficeId);
      return this;
    }
    
    public ProviderFirmOffice.Builder ccmsFirmOfficeId(Integer ccmsFirmOfficeId) {
      this.instance.ccmsFirmOfficeId(ccmsFirmOfficeId);
      return this;
    }
    
    public ProviderFirmOffice.Builder firmOfficeCode(String firmOfficeCode) {
      this.instance.firmOfficeCode(firmOfficeCode);
      return this;
    }
    
    public ProviderFirmOffice.Builder officeName(String officeName) {
      this.instance.officeName(officeName);
      return this;
    }
    
    public ProviderFirmOffice.Builder officeCodeAlt(String officeCodeAlt) {
      this.instance.officeCodeAlt(officeCodeAlt);
      return this;
    }
    
    public ProviderFirmOffice.Builder type(String type) {
      this.instance.type(type);
      return this;
    }
    
    public ProviderFirmOffice.Builder addressLine1(String addressLine1) {
      this.instance.addressLine1(addressLine1);
      return this;
    }
    
    public ProviderFirmOffice.Builder addressLine2(String addressLine2) {
      this.instance.addressLine2(addressLine2);
      return this;
    }
    
    public ProviderFirmOffice.Builder addressLine3(String addressLine3) {
      this.instance.addressLine3(addressLine3);
      return this;
    }
    
    public ProviderFirmOffice.Builder addressLine4(String addressLine4) {
      this.instance.addressLine4(addressLine4);
      return this;
    }
    
    public ProviderFirmOffice.Builder city(String city) {
      this.instance.city(city);
      return this;
    }
    
    public ProviderFirmOffice.Builder county(String county) {
      this.instance.county(county);
      return this;
    }
    
    public ProviderFirmOffice.Builder postCode(String postCode) {
      this.instance.postCode(postCode);
      return this;
    }
    
    public ProviderFirmOffice.Builder dxCentre(String dxCentre) {
      this.instance.dxCentre(dxCentre);
      return this;
    }
    
    public ProviderFirmOffice.Builder dxNumber(String dxNumber) {
      this.instance.dxNumber(dxNumber);
      return this;
    }
    
    public ProviderFirmOffice.Builder telephoneAreaCode(String telephoneAreaCode) {
      this.instance.telephoneAreaCode(telephoneAreaCode);
      return this;
    }
    
    public ProviderFirmOffice.Builder telephoneNumber(String telephoneNumber) {
      this.instance.telephoneNumber(telephoneNumber);
      return this;
    }
    
    public ProviderFirmOffice.Builder faxAreaCode(String faxAreaCode) {
      this.instance.faxAreaCode(faxAreaCode);
      return this;
    }
    
    public ProviderFirmOffice.Builder faxNumber(String faxNumber) {
      this.instance.faxNumber(faxNumber);
      return this;
    }
    
    public ProviderFirmOffice.Builder emailAddress(String emailAddress) {
      this.instance.emailAddress(emailAddress);
      return this;
    }
    
    public ProviderFirmOffice.Builder vatRegistrationNumber(String vatRegistrationNumber) {
      this.instance.vatRegistrationNumber(vatRegistrationNumber);
      return this;
    }
    
    public ProviderFirmOffice.Builder headOffice(String headOffice) {
      this.instance.headOffice(headOffice);
      return this;
    }
    
    public ProviderFirmOffice.Builder creationDate(LocalDate creationDate) {
      this.instance.creationDate(creationDate);
      return this;
    }
    
    public ProviderFirmOffice.Builder lscRegion(String lscRegion) {
      this.instance.lscRegion(lscRegion);
      return this;
    }
    
    public ProviderFirmOffice.Builder lscBidZone(String lscBidZone) {
      this.instance.lscBidZone(lscBidZone);
      return this;
    }
    
    public ProviderFirmOffice.Builder lscAreaOffice(String lscAreaOffice) {
      this.instance.lscAreaOffice(lscAreaOffice);
      return this;
    }
    
    public ProviderFirmOffice.Builder cjsForceName(String cjsForceName) {
      this.instance.cjsForceName(cjsForceName);
      return this;
    }
    
    public ProviderFirmOffice.Builder localAuthority(String localAuthority) {
      this.instance.localAuthority(localAuthority);
      return this;
    }
    
    public ProviderFirmOffice.Builder policeStationAreaName(String policeStationAreaName) {
      this.instance.policeStationAreaName(policeStationAreaName);
      return this;
    }
    
    public ProviderFirmOffice.Builder dutySolicitorAreaName(String dutySolicitorAreaName) {
      this.instance.dutySolicitorAreaName(dutySolicitorAreaName);
      return this;
    }
    
    /**
    * returns a built ProviderFirmOffice instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmOffice build() {
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
  public static ProviderFirmOffice.Builder builder() {
    return new ProviderFirmOffice.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmOffice.Builder toBuilder() {
    ProviderFirmOffice.Builder builder = new ProviderFirmOffice.Builder();
    return builder.copyOf(this);
  }

}

