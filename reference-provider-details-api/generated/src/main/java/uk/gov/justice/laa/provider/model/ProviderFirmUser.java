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
 * ProviderFirmUser
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T21:51:32.806274Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmUser implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable Integer userId;

  private @Nullable Integer ccmsContactId;

  private @Nullable String userLogin;

  private @Nullable String name;

  private @Nullable String emailAddress;

  private @Nullable String jobTitle;

  private @Nullable String phoneNumber;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate startDate;

  private @Nullable String attributeCode;

  public ProviderFirmUser userId(@Nullable Integer userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
   */
  
  @Schema(name = "userId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("userId")
  public @Nullable Integer getUserId() {
    return userId;
  }

  public void setUserId(@Nullable Integer userId) {
    this.userId = userId;
  }

  public ProviderFirmUser ccmsContactId(@Nullable Integer ccmsContactId) {
    this.ccmsContactId = ccmsContactId;
    return this;
  }

  /**
   * Get ccmsContactId
   * @return ccmsContactId
   */
  
  @Schema(name = "ccmsContactId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ccmsContactId")
  public @Nullable Integer getCcmsContactId() {
    return ccmsContactId;
  }

  public void setCcmsContactId(@Nullable Integer ccmsContactId) {
    this.ccmsContactId = ccmsContactId;
  }

  public ProviderFirmUser userLogin(@Nullable String userLogin) {
    this.userLogin = userLogin;
    return this;
  }

  /**
   * Get userLogin
   * @return userLogin
   */
  
  @Schema(name = "userLogin", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("userLogin")
  public @Nullable String getUserLogin() {
    return userLogin;
  }

  public void setUserLogin(@Nullable String userLogin) {
    this.userLogin = userLogin;
  }

  public ProviderFirmUser name(@Nullable String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
   */
  
  @Schema(name = "name", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public @Nullable String getName() {
    return name;
  }

  public void setName(@Nullable String name) {
    this.name = name;
  }

  public ProviderFirmUser emailAddress(@Nullable String emailAddress) {
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

  public ProviderFirmUser jobTitle(@Nullable String jobTitle) {
    this.jobTitle = jobTitle;
    return this;
  }

  /**
   * Get jobTitle
   * @return jobTitle
   */
  
  @Schema(name = "jobTitle", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("jobTitle")
  public @Nullable String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(@Nullable String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public ProviderFirmUser phoneNumber(@Nullable String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  /**
   * Get phoneNumber
   * @return phoneNumber
   */
  
  @Schema(name = "phoneNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("phoneNumber")
  public @Nullable String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(@Nullable String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public ProviderFirmUser startDate(@Nullable LocalDate startDate) {
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

  public ProviderFirmUser attributeCode(@Nullable String attributeCode) {
    this.attributeCode = attributeCode;
    return this;
  }

  /**
   * Get attributeCode
   * @return attributeCode
   */
  
  @Schema(name = "attributeCode", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("attributeCode")
  public @Nullable String getAttributeCode() {
    return attributeCode;
  }

  public void setAttributeCode(@Nullable String attributeCode) {
    this.attributeCode = attributeCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirmUser providerFirmUser = (ProviderFirmUser) o;
    return Objects.equals(this.userId, providerFirmUser.userId) &&
        Objects.equals(this.ccmsContactId, providerFirmUser.ccmsContactId) &&
        Objects.equals(this.userLogin, providerFirmUser.userLogin) &&
        Objects.equals(this.name, providerFirmUser.name) &&
        Objects.equals(this.emailAddress, providerFirmUser.emailAddress) &&
        Objects.equals(this.jobTitle, providerFirmUser.jobTitle) &&
        Objects.equals(this.phoneNumber, providerFirmUser.phoneNumber) &&
        Objects.equals(this.startDate, providerFirmUser.startDate) &&
        Objects.equals(this.attributeCode, providerFirmUser.attributeCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, ccmsContactId, userLogin, name, emailAddress, jobTitle, phoneNumber, startDate, attributeCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmUser {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    ccmsContactId: ").append(toIndentedString(ccmsContactId)).append("\n");
    sb.append("    userLogin: ").append(toIndentedString(userLogin)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    emailAddress: ").append(toIndentedString(emailAddress)).append("\n");
    sb.append("    jobTitle: ").append(toIndentedString(jobTitle)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    attributeCode: ").append(toIndentedString(attributeCode)).append("\n");
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

    private ProviderFirmUser instance;

    public Builder() {
      this(new ProviderFirmUser());
    }

    protected Builder(ProviderFirmUser instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmUser value) { 
      this.instance.setUserId(value.userId);
      this.instance.setCcmsContactId(value.ccmsContactId);
      this.instance.setUserLogin(value.userLogin);
      this.instance.setName(value.name);
      this.instance.setEmailAddress(value.emailAddress);
      this.instance.setJobTitle(value.jobTitle);
      this.instance.setPhoneNumber(value.phoneNumber);
      this.instance.setStartDate(value.startDate);
      this.instance.setAttributeCode(value.attributeCode);
      return this;
    }

    public ProviderFirmUser.Builder userId(Integer userId) {
      this.instance.userId(userId);
      return this;
    }
    
    public ProviderFirmUser.Builder ccmsContactId(Integer ccmsContactId) {
      this.instance.ccmsContactId(ccmsContactId);
      return this;
    }
    
    public ProviderFirmUser.Builder userLogin(String userLogin) {
      this.instance.userLogin(userLogin);
      return this;
    }
    
    public ProviderFirmUser.Builder name(String name) {
      this.instance.name(name);
      return this;
    }
    
    public ProviderFirmUser.Builder emailAddress(String emailAddress) {
      this.instance.emailAddress(emailAddress);
      return this;
    }
    
    public ProviderFirmUser.Builder jobTitle(String jobTitle) {
      this.instance.jobTitle(jobTitle);
      return this;
    }
    
    public ProviderFirmUser.Builder phoneNumber(String phoneNumber) {
      this.instance.phoneNumber(phoneNumber);
      return this;
    }
    
    public ProviderFirmUser.Builder startDate(LocalDate startDate) {
      this.instance.startDate(startDate);
      return this;
    }
    
    public ProviderFirmUser.Builder attributeCode(String attributeCode) {
      this.instance.attributeCode(attributeCode);
      return this;
    }
    
    /**
    * returns a built ProviderFirmUser instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmUser build() {
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
  public static ProviderFirmUser.Builder builder() {
    return new ProviderFirmUser.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmUser.Builder toBuilder() {
    ProviderFirmUser.Builder builder = new ProviderFirmUser.Builder();
    return builder.copyOf(this);
  }

}

