package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.provider.model.ProviderFirmOffice;
import uk.gov.justice.laa.provider.model.ProviderFirmSummary;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ProviderFirmOfficeListDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T21:51:32.806274Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmOfficeListDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable ProviderFirmSummary firm;

  @Valid
  private List<@Valid ProviderFirmOffice> offices = new ArrayList<>();

  public ProviderFirmOfficeListDto firm(@Nullable ProviderFirmSummary firm) {
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

  public ProviderFirmOfficeListDto offices(List<@Valid ProviderFirmOffice> offices) {
    this.offices = offices;
    return this;
  }

  public ProviderFirmOfficeListDto addOfficesItem(ProviderFirmOffice officesItem) {
    if (this.offices == null) {
      this.offices = new ArrayList<>();
    }
    this.offices.add(officesItem);
    return this;
  }

  /**
   * Get offices
   * @return offices
   */
  @Valid 
  @Schema(name = "offices", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("offices")
  public List<@Valid ProviderFirmOffice> getOffices() {
    return offices;
  }

  public void setOffices(List<@Valid ProviderFirmOffice> offices) {
    this.offices = offices;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirmOfficeListDto providerFirmOfficeListDto = (ProviderFirmOfficeListDto) o;
    return Objects.equals(this.firm, providerFirmOfficeListDto.firm) &&
        Objects.equals(this.offices, providerFirmOfficeListDto.offices);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firm, offices);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmOfficeListDto {\n");
    sb.append("    firm: ").append(toIndentedString(firm)).append("\n");
    sb.append("    offices: ").append(toIndentedString(offices)).append("\n");
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

    private ProviderFirmOfficeListDto instance;

    public Builder() {
      this(new ProviderFirmOfficeListDto());
    }

    protected Builder(ProviderFirmOfficeListDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmOfficeListDto value) { 
      this.instance.setFirm(value.firm);
      this.instance.setOffices(value.offices);
      return this;
    }

    public ProviderFirmOfficeListDto.Builder firm(ProviderFirmSummary firm) {
      this.instance.firm(firm);
      return this;
    }
    
    public ProviderFirmOfficeListDto.Builder offices(List<ProviderFirmOffice> offices) {
      this.instance.offices(offices);
      return this;
    }
    
    /**
    * returns a built ProviderFirmOfficeListDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmOfficeListDto build() {
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
  public static ProviderFirmOfficeListDto.Builder builder() {
    return new ProviderFirmOfficeListDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmOfficeListDto.Builder toBuilder() {
    ProviderFirmOfficeListDto.Builder builder = new ProviderFirmOfficeListDto.Builder();
    return builder.copyOf(this);
  }

}

