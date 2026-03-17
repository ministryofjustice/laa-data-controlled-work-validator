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
 * CcmsProviderUser
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-16T20:11:03.761871Z[Europe/London]", comments = "Generator version: 7.14.0")
public class CcmsProviderUser implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String userUuid;

  private @Nullable String userLogin;

  private @Nullable Integer ccmsContactId;

  public CcmsProviderUser userUuid(@Nullable String userUuid) {
    this.userUuid = userUuid;
    return this;
  }

  /**
   * Get userUuid
   * @return userUuid
   */
  
  @Schema(name = "userUuid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("userUuid")
  public @Nullable String getUserUuid() {
    return userUuid;
  }

  public void setUserUuid(@Nullable String userUuid) {
    this.userUuid = userUuid;
  }

  public CcmsProviderUser userLogin(@Nullable String userLogin) {
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

  public CcmsProviderUser ccmsContactId(@Nullable Integer ccmsContactId) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CcmsProviderUser ccmsProviderUser = (CcmsProviderUser) o;
    return Objects.equals(this.userUuid, ccmsProviderUser.userUuid) &&
        Objects.equals(this.userLogin, ccmsProviderUser.userLogin) &&
        Objects.equals(this.ccmsContactId, ccmsProviderUser.ccmsContactId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userUuid, userLogin, ccmsContactId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CcmsProviderUser {\n");
    sb.append("    userUuid: ").append(toIndentedString(userUuid)).append("\n");
    sb.append("    userLogin: ").append(toIndentedString(userLogin)).append("\n");
    sb.append("    ccmsContactId: ").append(toIndentedString(ccmsContactId)).append("\n");
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

    private CcmsProviderUser instance;

    public Builder() {
      this(new CcmsProviderUser());
    }

    protected Builder(CcmsProviderUser instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CcmsProviderUser value) { 
      this.instance.setUserUuid(value.userUuid);
      this.instance.setUserLogin(value.userLogin);
      this.instance.setCcmsContactId(value.ccmsContactId);
      return this;
    }

    public CcmsProviderUser.Builder userUuid(String userUuid) {
      this.instance.userUuid(userUuid);
      return this;
    }
    
    public CcmsProviderUser.Builder userLogin(String userLogin) {
      this.instance.userLogin(userLogin);
      return this;
    }
    
    public CcmsProviderUser.Builder ccmsContactId(Integer ccmsContactId) {
      this.instance.ccmsContactId(ccmsContactId);
      return this;
    }
    
    /**
    * returns a built CcmsProviderUser instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CcmsProviderUser build() {
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
  public static CcmsProviderUser.Builder builder() {
    return new CcmsProviderUser.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CcmsProviderUser.Builder toBuilder() {
    CcmsProviderUser.Builder builder = new CcmsProviderUser.Builder();
    return builder.copyOf(this);
  }

}

