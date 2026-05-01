package uk.gov.justice.laadata.providers.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import uk.gov.justice.laadata.providers.model.ProviderFirmSummary;
import uk.gov.justice.laadata.providers.model.ProviderFirmUser;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ProviderFirmUserDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-05-01T10:19:45.265474+01:00[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmUserDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable ProviderFirmSummary firm;

  private @Nullable ProviderFirmUser user;

  @Valid
  private List<@Valid ProviderFirmSummary> associatedFirms = new ArrayList<>();

  public ProviderFirmUserDto firm(@Nullable ProviderFirmSummary firm) {
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

  public ProviderFirmUserDto user(@Nullable ProviderFirmUser user) {
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
  public @Nullable ProviderFirmUser getUser() {
    return user;
  }

  public void setUser(@Nullable ProviderFirmUser user) {
    this.user = user;
  }

  public ProviderFirmUserDto associatedFirms(List<@Valid ProviderFirmSummary> associatedFirms) {
    this.associatedFirms = associatedFirms;
    return this;
  }

  public ProviderFirmUserDto addAssociatedFirmsItem(ProviderFirmSummary associatedFirmsItem) {
    if (this.associatedFirms == null) {
      this.associatedFirms = new ArrayList<>();
    }
    this.associatedFirms.add(associatedFirmsItem);
    return this;
  }

  /**
   * Get associatedFirms
   * @return associatedFirms
   */
  @Valid 
  @Schema(name = "associatedFirms", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("associatedFirms")
  public List<@Valid ProviderFirmSummary> getAssociatedFirms() {
    return associatedFirms;
  }

  public void setAssociatedFirms(List<@Valid ProviderFirmSummary> associatedFirms) {
    this.associatedFirms = associatedFirms;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirmUserDto providerFirmUserDto = (ProviderFirmUserDto) o;
    return Objects.equals(this.firm, providerFirmUserDto.firm) &&
        Objects.equals(this.user, providerFirmUserDto.user) &&
        Objects.equals(this.associatedFirms, providerFirmUserDto.associatedFirms);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firm, user, associatedFirms);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmUserDto {\n");
    sb.append("    firm: ").append(toIndentedString(firm)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    associatedFirms: ").append(toIndentedString(associatedFirms)).append("\n");
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

    private ProviderFirmUserDto instance;

    public Builder() {
      this(new ProviderFirmUserDto());
    }

    protected Builder(ProviderFirmUserDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmUserDto value) { 
      this.instance.setFirm(value.firm);
      this.instance.setUser(value.user);
      this.instance.setAssociatedFirms(value.associatedFirms);
      return this;
    }

    public ProviderFirmUserDto.Builder firm(ProviderFirmSummary firm) {
      this.instance.firm(firm);
      return this;
    }
    
    public ProviderFirmUserDto.Builder user(ProviderFirmUser user) {
      this.instance.user(user);
      return this;
    }
    
    public ProviderFirmUserDto.Builder associatedFirms(List<ProviderFirmSummary> associatedFirms) {
      this.instance.associatedFirms(associatedFirms);
      return this;
    }
    
    /**
    * returns a built ProviderFirmUserDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmUserDto build() {
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
  public static ProviderFirmUserDto.Builder builder() {
    return new ProviderFirmUserDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmUserDto.Builder toBuilder() {
    ProviderFirmUserDto.Builder builder = new ProviderFirmUserDto.Builder();
    return builder.copyOf(this);
  }

}

