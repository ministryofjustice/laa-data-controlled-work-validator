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
 * ProviderFirmUserSummary
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-16T20:11:03.761871Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmUserSummary implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable Integer userId;

  private @Nullable Integer ccmsContactId;

  private @Nullable String userLogin;

  private @Nullable String name;

  private @Nullable String emailAddress;

  public ProviderFirmUserSummary userId(@Nullable Integer userId) {
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

  public ProviderFirmUserSummary ccmsContactId(@Nullable Integer ccmsContactId) {
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

  public ProviderFirmUserSummary userLogin(@Nullable String userLogin) {
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

  public ProviderFirmUserSummary name(@Nullable String name) {
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

  public ProviderFirmUserSummary emailAddress(@Nullable String emailAddress) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirmUserSummary providerFirmUserSummary = (ProviderFirmUserSummary) o;
    return Objects.equals(this.userId, providerFirmUserSummary.userId) &&
        Objects.equals(this.ccmsContactId, providerFirmUserSummary.ccmsContactId) &&
        Objects.equals(this.userLogin, providerFirmUserSummary.userLogin) &&
        Objects.equals(this.name, providerFirmUserSummary.name) &&
        Objects.equals(this.emailAddress, providerFirmUserSummary.emailAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, ccmsContactId, userLogin, name, emailAddress);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmUserSummary {\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    ccmsContactId: ").append(toIndentedString(ccmsContactId)).append("\n");
    sb.append("    userLogin: ").append(toIndentedString(userLogin)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    emailAddress: ").append(toIndentedString(emailAddress)).append("\n");
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

    private ProviderFirmUserSummary instance;

    public Builder() {
      this(new ProviderFirmUserSummary());
    }

    protected Builder(ProviderFirmUserSummary instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmUserSummary value) { 
      this.instance.setUserId(value.userId);
      this.instance.setCcmsContactId(value.ccmsContactId);
      this.instance.setUserLogin(value.userLogin);
      this.instance.setName(value.name);
      this.instance.setEmailAddress(value.emailAddress);
      return this;
    }

    public ProviderFirmUserSummary.Builder userId(Integer userId) {
      this.instance.userId(userId);
      return this;
    }
    
    public ProviderFirmUserSummary.Builder ccmsContactId(Integer ccmsContactId) {
      this.instance.ccmsContactId(ccmsContactId);
      return this;
    }
    
    public ProviderFirmUserSummary.Builder userLogin(String userLogin) {
      this.instance.userLogin(userLogin);
      return this;
    }
    
    public ProviderFirmUserSummary.Builder name(String name) {
      this.instance.name(name);
      return this;
    }
    
    public ProviderFirmUserSummary.Builder emailAddress(String emailAddress) {
      this.instance.emailAddress(emailAddress);
      return this;
    }
    
    /**
    * returns a built ProviderFirmUserSummary instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmUserSummary build() {
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
  public static ProviderFirmUserSummary.Builder builder() {
    return new ProviderFirmUserSummary.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmUserSummary.Builder toBuilder() {
    ProviderFirmUserSummary.Builder builder = new ProviderFirmUserSummary.Builder();
    return builder.copyOf(this);
  }

}

