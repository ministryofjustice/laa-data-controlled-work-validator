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
 * RoleDetails
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-14T10:05:02.190518Z[Europe/London]", comments = "Generator version: 7.14.0")
public class RoleDetails implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String name;

  private @Nullable String role;

  public RoleDetails name(@Nullable String name) {
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

  public RoleDetails role(@Nullable String role) {
    this.role = role;
    return this;
  }

  /**
   * Get role
   * @return role
   */
  
  @Schema(name = "role", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("role")
  public @Nullable String getRole() {
    return role;
  }

  public void setRole(@Nullable String role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoleDetails roleDetails = (RoleDetails) o;
    return Objects.equals(this.name, roleDetails.name) &&
        Objects.equals(this.role, roleDetails.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, role);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoleDetails {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
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

    private RoleDetails instance;

    public Builder() {
      this(new RoleDetails());
    }

    protected Builder(RoleDetails instance) {
      this.instance = instance;
    }

    protected Builder copyOf(RoleDetails value) { 
      this.instance.setName(value.name);
      this.instance.setRole(value.role);
      return this;
    }

    public RoleDetails.Builder name(String name) {
      this.instance.name(name);
      return this;
    }
    
    public RoleDetails.Builder role(String role) {
      this.instance.role(role);
      return this;
    }
    
    /**
    * returns a built RoleDetails instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public RoleDetails build() {
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
  public static RoleDetails.Builder builder() {
    return new RoleDetails.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public RoleDetails.Builder toBuilder() {
    RoleDetails.Builder builder = new RoleDetails.Builder();
    return builder.copyOf(this);
  }

}

