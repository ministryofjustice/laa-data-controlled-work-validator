package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.provider.model.NmsAuthorisationOutreachOffice;
import uk.gov.justice.laa.provider.model.NmsAuthorisationParttimeOffice;
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
 * ProviderFirmNmsAuthorisationOutreachOfficeListDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T18:11:19.822784Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmNmsAuthorisationOutreachOfficeListDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable ProviderFirmSummary firm;

  @Valid
  private List<@Valid NmsAuthorisationOutreachOffice> nmsAuthorisationOffices = new ArrayList<>();

  @Valid
  private List<@Valid NmsAuthorisationParttimeOffice> nmsAuthorisationParttimeOffices = new ArrayList<>();

  public ProviderFirmNmsAuthorisationOutreachOfficeListDto firm(@Nullable ProviderFirmSummary firm) {
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

  public ProviderFirmNmsAuthorisationOutreachOfficeListDto nmsAuthorisationOffices(List<@Valid NmsAuthorisationOutreachOffice> nmsAuthorisationOffices) {
    this.nmsAuthorisationOffices = nmsAuthorisationOffices;
    return this;
  }

  public ProviderFirmNmsAuthorisationOutreachOfficeListDto addNmsAuthorisationOfficesItem(NmsAuthorisationOutreachOffice nmsAuthorisationOfficesItem) {
    if (this.nmsAuthorisationOffices == null) {
      this.nmsAuthorisationOffices = new ArrayList<>();
    }
    this.nmsAuthorisationOffices.add(nmsAuthorisationOfficesItem);
    return this;
  }

  /**
   * Get nmsAuthorisationOffices
   * @return nmsAuthorisationOffices
   */
  @Valid 
  @Schema(name = "nmsAuthorisationOffices", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nmsAuthorisationOffices")
  public List<@Valid NmsAuthorisationOutreachOffice> getNmsAuthorisationOffices() {
    return nmsAuthorisationOffices;
  }

  public void setNmsAuthorisationOffices(List<@Valid NmsAuthorisationOutreachOffice> nmsAuthorisationOffices) {
    this.nmsAuthorisationOffices = nmsAuthorisationOffices;
  }

  public ProviderFirmNmsAuthorisationOutreachOfficeListDto nmsAuthorisationParttimeOffices(List<@Valid NmsAuthorisationParttimeOffice> nmsAuthorisationParttimeOffices) {
    this.nmsAuthorisationParttimeOffices = nmsAuthorisationParttimeOffices;
    return this;
  }

  public ProviderFirmNmsAuthorisationOutreachOfficeListDto addNmsAuthorisationParttimeOfficesItem(NmsAuthorisationParttimeOffice nmsAuthorisationParttimeOfficesItem) {
    if (this.nmsAuthorisationParttimeOffices == null) {
      this.nmsAuthorisationParttimeOffices = new ArrayList<>();
    }
    this.nmsAuthorisationParttimeOffices.add(nmsAuthorisationParttimeOfficesItem);
    return this;
  }

  /**
   * Get nmsAuthorisationParttimeOffices
   * @return nmsAuthorisationParttimeOffices
   */
  @Valid 
  @Schema(name = "nmsAuthorisationParttimeOffices", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nmsAuthorisationParttimeOffices")
  public List<@Valid NmsAuthorisationParttimeOffice> getNmsAuthorisationParttimeOffices() {
    return nmsAuthorisationParttimeOffices;
  }

  public void setNmsAuthorisationParttimeOffices(List<@Valid NmsAuthorisationParttimeOffice> nmsAuthorisationParttimeOffices) {
    this.nmsAuthorisationParttimeOffices = nmsAuthorisationParttimeOffices;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirmNmsAuthorisationOutreachOfficeListDto providerFirmNmsAuthorisationOutreachOfficeListDto = (ProviderFirmNmsAuthorisationOutreachOfficeListDto) o;
    return Objects.equals(this.firm, providerFirmNmsAuthorisationOutreachOfficeListDto.firm) &&
        Objects.equals(this.nmsAuthorisationOffices, providerFirmNmsAuthorisationOutreachOfficeListDto.nmsAuthorisationOffices) &&
        Objects.equals(this.nmsAuthorisationParttimeOffices, providerFirmNmsAuthorisationOutreachOfficeListDto.nmsAuthorisationParttimeOffices);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firm, nmsAuthorisationOffices, nmsAuthorisationParttimeOffices);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmNmsAuthorisationOutreachOfficeListDto {\n");
    sb.append("    firm: ").append(toIndentedString(firm)).append("\n");
    sb.append("    nmsAuthorisationOffices: ").append(toIndentedString(nmsAuthorisationOffices)).append("\n");
    sb.append("    nmsAuthorisationParttimeOffices: ").append(toIndentedString(nmsAuthorisationParttimeOffices)).append("\n");
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

    private ProviderFirmNmsAuthorisationOutreachOfficeListDto instance;

    public Builder() {
      this(new ProviderFirmNmsAuthorisationOutreachOfficeListDto());
    }

    protected Builder(ProviderFirmNmsAuthorisationOutreachOfficeListDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmNmsAuthorisationOutreachOfficeListDto value) { 
      this.instance.setFirm(value.firm);
      this.instance.setNmsAuthorisationOffices(value.nmsAuthorisationOffices);
      this.instance.setNmsAuthorisationParttimeOffices(value.nmsAuthorisationParttimeOffices);
      return this;
    }

    public ProviderFirmNmsAuthorisationOutreachOfficeListDto.Builder firm(ProviderFirmSummary firm) {
      this.instance.firm(firm);
      return this;
    }
    
    public ProviderFirmNmsAuthorisationOutreachOfficeListDto.Builder nmsAuthorisationOffices(List<NmsAuthorisationOutreachOffice> nmsAuthorisationOffices) {
      this.instance.nmsAuthorisationOffices(nmsAuthorisationOffices);
      return this;
    }
    
    public ProviderFirmNmsAuthorisationOutreachOfficeListDto.Builder nmsAuthorisationParttimeOffices(List<NmsAuthorisationParttimeOffice> nmsAuthorisationParttimeOffices) {
      this.instance.nmsAuthorisationParttimeOffices(nmsAuthorisationParttimeOffices);
      return this;
    }
    
    /**
    * returns a built ProviderFirmNmsAuthorisationOutreachOfficeListDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmNmsAuthorisationOutreachOfficeListDto build() {
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
  public static ProviderFirmNmsAuthorisationOutreachOfficeListDto.Builder builder() {
    return new ProviderFirmNmsAuthorisationOutreachOfficeListDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmNmsAuthorisationOutreachOfficeListDto.Builder toBuilder() {
    ProviderFirmNmsAuthorisationOutreachOfficeListDto.Builder builder = new ProviderFirmNmsAuthorisationOutreachOfficeListDto.Builder();
    return builder.copyOf(this);
  }

}

