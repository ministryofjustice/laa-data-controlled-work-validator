package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.provider.model.ProviderFirmOfficeSummary;
import uk.gov.justice.laa.provider.model.ProviderFirmSummary;
import uk.gov.justice.laa.provider.model.ProviderFirmUserSummary;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ProviderFirmUserOfficeListDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-16T20:11:03.761871Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmUserOfficeListDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable ProviderFirmSummary firm;

  private @Nullable ProviderFirmUserSummary user;

  @Valid
  private List<@Valid ProviderFirmOfficeSummary> officeCodes = new ArrayList<>();

  public ProviderFirmUserOfficeListDto firm(@Nullable ProviderFirmSummary firm) {
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

  public ProviderFirmUserOfficeListDto user(@Nullable ProviderFirmUserSummary user) {
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

  public ProviderFirmUserOfficeListDto officeCodes(List<@Valid ProviderFirmOfficeSummary> officeCodes) {
    this.officeCodes = officeCodes;
    return this;
  }

  public ProviderFirmUserOfficeListDto addOfficeCodesItem(ProviderFirmOfficeSummary officeCodesItem) {
    if (this.officeCodes == null) {
      this.officeCodes = new ArrayList<>();
    }
    this.officeCodes.add(officeCodesItem);
    return this;
  }

  /**
   * Get officeCodes
   * @return officeCodes
   */
  @Valid 
  @Schema(name = "officeCodes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("officeCodes")
  public List<@Valid ProviderFirmOfficeSummary> getOfficeCodes() {
    return officeCodes;
  }

  public void setOfficeCodes(List<@Valid ProviderFirmOfficeSummary> officeCodes) {
    this.officeCodes = officeCodes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirmUserOfficeListDto providerFirmUserOfficeListDto = (ProviderFirmUserOfficeListDto) o;
    return Objects.equals(this.firm, providerFirmUserOfficeListDto.firm) &&
        Objects.equals(this.user, providerFirmUserOfficeListDto.user) &&
        Objects.equals(this.officeCodes, providerFirmUserOfficeListDto.officeCodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firm, user, officeCodes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmUserOfficeListDto {\n");
    sb.append("    firm: ").append(toIndentedString(firm)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    officeCodes: ").append(toIndentedString(officeCodes)).append("\n");
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

    private ProviderFirmUserOfficeListDto instance;

    public Builder() {
      this(new ProviderFirmUserOfficeListDto());
    }

    protected Builder(ProviderFirmUserOfficeListDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmUserOfficeListDto value) { 
      this.instance.setFirm(value.firm);
      this.instance.setUser(value.user);
      this.instance.setOfficeCodes(value.officeCodes);
      return this;
    }

    public ProviderFirmUserOfficeListDto.Builder firm(ProviderFirmSummary firm) {
      this.instance.firm(firm);
      return this;
    }
    
    public ProviderFirmUserOfficeListDto.Builder user(ProviderFirmUserSummary user) {
      this.instance.user(user);
      return this;
    }
    
    public ProviderFirmUserOfficeListDto.Builder officeCodes(List<ProviderFirmOfficeSummary> officeCodes) {
      this.instance.officeCodes(officeCodes);
      return this;
    }
    
    /**
    * returns a built ProviderFirmUserOfficeListDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmUserOfficeListDto build() {
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
  public static ProviderFirmUserOfficeListDto.Builder builder() {
    return new ProviderFirmUserOfficeListDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmUserOfficeListDto.Builder toBuilder() {
    ProviderFirmUserOfficeListDto.Builder builder = new ProviderFirmUserOfficeListDto.Builder();
    return builder.copyOf(this);
  }

}

