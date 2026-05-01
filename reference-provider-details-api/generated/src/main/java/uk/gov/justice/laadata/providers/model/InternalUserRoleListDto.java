package uk.gov.justice.laadata.providers.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import uk.gov.justice.laadata.providers.model.AssignedApplicationRoles;
import uk.gov.justice.laadata.providers.model.InternalUser;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * InternalUserRoleListDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-01T10:19:45.265474+01:00[Europe/London]", comments = "Generator version: 7.14.0")
public class InternalUserRoleListDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable InternalUser user;

  @Valid
  private List<@Valid AssignedApplicationRoles> applicationRoles = new ArrayList<>();

  public InternalUserRoleListDto user(@Nullable InternalUser user) {
    this.user = user;
    return this;
  }

  /**
   * Get user
   * @return user
   */
  @Valid 
  @Schema(name = "user", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("user")
  public @Nullable InternalUser getUser() {
    return user;
  }

  public void setUser(@Nullable InternalUser user) {
    this.user = user;
  }

  public InternalUserRoleListDto applicationRoles(List<@Valid AssignedApplicationRoles> applicationRoles) {
    this.applicationRoles = applicationRoles;
    return this;
  }

  public InternalUserRoleListDto addApplicationRolesItem(AssignedApplicationRoles applicationRolesItem) {
    if (this.applicationRoles == null) {
      this.applicationRoles = new ArrayList<>();
    }
    this.applicationRoles.add(applicationRolesItem);
    return this;
  }

  /**
   * Get applicationRoles
   * @return applicationRoles
   */
  @Valid 
  @Schema(name = "applicationRoles", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("applicationRoles")
  public List<@Valid AssignedApplicationRoles> getApplicationRoles() {
    return applicationRoles;
  }

  public void setApplicationRoles(List<@Valid AssignedApplicationRoles> applicationRoles) {
    this.applicationRoles = applicationRoles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InternalUserRoleListDto internalUserRoleListDto = (InternalUserRoleListDto) o;
    return Objects.equals(this.user, internalUserRoleListDto.user) &&
        Objects.equals(this.applicationRoles, internalUserRoleListDto.applicationRoles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, applicationRoles);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InternalUserRoleListDto {\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    applicationRoles: ").append(toIndentedString(applicationRoles)).append("\n");
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

    private InternalUserRoleListDto instance;

    public Builder() {
      this(new InternalUserRoleListDto());
    }

    protected Builder(InternalUserRoleListDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(InternalUserRoleListDto value) { 
      this.instance.setUser(value.user);
      this.instance.setApplicationRoles(value.applicationRoles);
      return this;
    }

    public InternalUserRoleListDto.Builder user(InternalUser user) {
      this.instance.user(user);
      return this;
    }
    
    public InternalUserRoleListDto.Builder applicationRoles(List<AssignedApplicationRoles> applicationRoles) {
      this.instance.applicationRoles(applicationRoles);
      return this;
    }
    
    /**
    * returns a built InternalUserRoleListDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public InternalUserRoleListDto build() {
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
  public static InternalUserRoleListDto.Builder builder() {
    return new InternalUserRoleListDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public InternalUserRoleListDto.Builder toBuilder() {
    InternalUserRoleListDto.Builder builder = new InternalUserRoleListDto.Builder();
    return builder.copyOf(this);
  }

}

