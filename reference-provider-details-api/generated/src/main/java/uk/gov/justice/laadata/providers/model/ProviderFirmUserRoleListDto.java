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
import uk.gov.justice.laadata.providers.model.ProviderFirmSummary;
import uk.gov.justice.laadata.providers.model.ProviderFirmUserSummary;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ProviderFirmUserRoleListDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-01T10:19:45.265474+01:00[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmUserRoleListDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable ProviderFirmSummary firm;

  private @Nullable ProviderFirmUserSummary user;

  @Valid
  private List<@Valid AssignedApplicationRoles> applicationRoles = new ArrayList<>();

  public ProviderFirmUserRoleListDto firm(@Nullable ProviderFirmSummary firm) {
    this.firm = firm;
    return this;
  }

  /**
   * Get firm
   * @return firm
   */
  @Valid 
  @Schema(name = "firm", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("firm")
  public @Nullable ProviderFirmSummary getFirm() {
    return firm;
  }

  public void setFirm(@Nullable ProviderFirmSummary firm) {
    this.firm = firm;
  }

  public ProviderFirmUserRoleListDto user(@Nullable ProviderFirmUserSummary user) {
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
  public @Nullable ProviderFirmUserSummary getUser() {
    return user;
  }

  public void setUser(@Nullable ProviderFirmUserSummary user) {
    this.user = user;
  }

  public ProviderFirmUserRoleListDto applicationRoles(List<@Valid AssignedApplicationRoles> applicationRoles) {
    this.applicationRoles = applicationRoles;
    return this;
  }

  public ProviderFirmUserRoleListDto addApplicationRolesItem(AssignedApplicationRoles applicationRolesItem) {
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
    ProviderFirmUserRoleListDto providerFirmUserRoleListDto = (ProviderFirmUserRoleListDto) o;
    return Objects.equals(this.firm, providerFirmUserRoleListDto.firm) &&
        Objects.equals(this.user, providerFirmUserRoleListDto.user) &&
        Objects.equals(this.applicationRoles, providerFirmUserRoleListDto.applicationRoles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firm, user, applicationRoles);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmUserRoleListDto {\n");
    sb.append("    firm: ").append(toIndentedString(firm)).append("\n");
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

    private ProviderFirmUserRoleListDto instance;

    public Builder() {
      this(new ProviderFirmUserRoleListDto());
    }

    protected Builder(ProviderFirmUserRoleListDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmUserRoleListDto value) { 
      this.instance.setFirm(value.firm);
      this.instance.setUser(value.user);
      this.instance.setApplicationRoles(value.applicationRoles);
      return this;
    }

    public ProviderFirmUserRoleListDto.Builder firm(ProviderFirmSummary firm) {
      this.instance.firm(firm);
      return this;
    }
    
    public ProviderFirmUserRoleListDto.Builder user(ProviderFirmUserSummary user) {
      this.instance.user(user);
      return this;
    }
    
    public ProviderFirmUserRoleListDto.Builder applicationRoles(List<AssignedApplicationRoles> applicationRoles) {
      this.instance.applicationRoles(applicationRoles);
      return this;
    }
    
    /**
    * returns a built ProviderFirmUserRoleListDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmUserRoleListDto build() {
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
  public static ProviderFirmUserRoleListDto.Builder builder() {
    return new ProviderFirmUserRoleListDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmUserRoleListDto.Builder toBuilder() {
    ProviderFirmUserRoleListDto.Builder builder = new ProviderFirmUserRoleListDto.Builder();
    return builder.copyOf(this);
  }

}

