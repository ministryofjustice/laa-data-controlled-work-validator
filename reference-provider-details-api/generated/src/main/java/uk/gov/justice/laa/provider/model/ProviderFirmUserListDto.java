package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.provider.model.ProviderFirmSummary;
import uk.gov.justice.laa.provider.model.ProviderFirmUser;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ProviderFirmUserListDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T18:11:19.822784Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmUserListDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable ProviderFirmSummary firm;

  @Valid
  private List<@Valid ProviderFirmUser> users = new ArrayList<>();

  public ProviderFirmUserListDto firm(@Nullable ProviderFirmSummary firm) {
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

  public ProviderFirmUserListDto users(List<@Valid ProviderFirmUser> users) {
    this.users = users;
    return this;
  }

  public ProviderFirmUserListDto addUsersItem(ProviderFirmUser usersItem) {
    if (this.users == null) {
      this.users = new ArrayList<>();
    }
    this.users.add(usersItem);
    return this;
  }

  /**
   * Get users
   * @return users
   */
  @Valid 
  @Schema(name = "users", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("users")
  public List<@Valid ProviderFirmUser> getUsers() {
    return users;
  }

  public void setUsers(List<@Valid ProviderFirmUser> users) {
    this.users = users;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirmUserListDto providerFirmUserListDto = (ProviderFirmUserListDto) o;
    return Objects.equals(this.firm, providerFirmUserListDto.firm) &&
        Objects.equals(this.users, providerFirmUserListDto.users);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firm, users);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmUserListDto {\n");
    sb.append("    firm: ").append(toIndentedString(firm)).append("\n");
    sb.append("    users: ").append(toIndentedString(users)).append("\n");
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

    private ProviderFirmUserListDto instance;

    public Builder() {
      this(new ProviderFirmUserListDto());
    }

    protected Builder(ProviderFirmUserListDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmUserListDto value) { 
      this.instance.setFirm(value.firm);
      this.instance.setUsers(value.users);
      return this;
    }

    public ProviderFirmUserListDto.Builder firm(ProviderFirmSummary firm) {
      this.instance.firm(firm);
      return this;
    }
    
    public ProviderFirmUserListDto.Builder users(List<ProviderFirmUser> users) {
      this.instance.users(users);
      return this;
    }
    
    /**
    * returns a built ProviderFirmUserListDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmUserListDto build() {
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
  public static ProviderFirmUserListDto.Builder builder() {
    return new ProviderFirmUserListDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmUserListDto.Builder toBuilder() {
    ProviderFirmUserListDto.Builder builder = new ProviderFirmUserListDto.Builder();
    return builder.copyOf(this);
  }

}

