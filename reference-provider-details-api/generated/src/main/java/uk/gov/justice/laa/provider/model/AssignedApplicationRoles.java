package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.provider.model.RoleDetails;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AssignedApplicationRoles
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-14T10:05:02.190518Z[Europe/London]", comments = "Generator version: 7.14.0")
public class AssignedApplicationRoles implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String applicationName;

  @Valid
  private List<@Valid RoleDetails> roles = new ArrayList<>();

  public AssignedApplicationRoles applicationName(@Nullable String applicationName) {
    this.applicationName = applicationName;
    return this;
  }

  /**
   * Get applicationName
   * @return applicationName
   */
  
  @Schema(name = "applicationName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("applicationName")
  public @Nullable String getApplicationName() {
    return applicationName;
  }

  public void setApplicationName(@Nullable String applicationName) {
    this.applicationName = applicationName;
  }

  public AssignedApplicationRoles roles(List<@Valid RoleDetails> roles) {
    this.roles = roles;
    return this;
  }

  public AssignedApplicationRoles addRolesItem(RoleDetails rolesItem) {
    if (this.roles == null) {
      this.roles = new ArrayList<>();
    }
    this.roles.add(rolesItem);
    return this;
  }

  /**
   * Get roles
   * @return roles
   */
  @Valid 
  @Schema(name = "roles", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("roles")
  public List<@Valid RoleDetails> getRoles() {
    return roles;
  }

  public void setRoles(List<@Valid RoleDetails> roles) {
    this.roles = roles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AssignedApplicationRoles assignedApplicationRoles = (AssignedApplicationRoles) o;
    return Objects.equals(this.applicationName, assignedApplicationRoles.applicationName) &&
        Objects.equals(this.roles, assignedApplicationRoles.roles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationName, roles);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AssignedApplicationRoles {\n");
    sb.append("    applicationName: ").append(toIndentedString(applicationName)).append("\n");
    sb.append("    roles: ").append(toIndentedString(roles)).append("\n");
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

    private AssignedApplicationRoles instance;

    public Builder() {
      this(new AssignedApplicationRoles());
    }

    protected Builder(AssignedApplicationRoles instance) {
      this.instance = instance;
    }

    protected Builder copyOf(AssignedApplicationRoles value) { 
      this.instance.setApplicationName(value.applicationName);
      this.instance.setRoles(value.roles);
      return this;
    }

    public AssignedApplicationRoles.Builder applicationName(String applicationName) {
      this.instance.applicationName(applicationName);
      return this;
    }
    
    public AssignedApplicationRoles.Builder roles(List<RoleDetails> roles) {
      this.instance.roles(roles);
      return this;
    }
    
    /**
    * returns a built AssignedApplicationRoles instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public AssignedApplicationRoles build() {
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
  public static AssignedApplicationRoles.Builder builder() {
    return new AssignedApplicationRoles.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public AssignedApplicationRoles.Builder toBuilder() {
    AssignedApplicationRoles.Builder builder = new AssignedApplicationRoles.Builder();
    return builder.copyOf(this);
  }

}

