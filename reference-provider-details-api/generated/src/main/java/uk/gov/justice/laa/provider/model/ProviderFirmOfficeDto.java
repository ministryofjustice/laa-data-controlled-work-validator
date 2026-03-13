package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
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
 * ProviderFirmOfficeDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T14:44:48.222559Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmOfficeDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable ProviderFirmSummary firm;

  private @Nullable ProviderFirmOffice office;

  public ProviderFirmOfficeDto firm(@Nullable ProviderFirmSummary firm) {
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

  public ProviderFirmOfficeDto office(@Nullable ProviderFirmOffice office) {
    this.office = office;
    return this;
  }

  /**
   * Get office
   * @return office
   */
  @Valid 
  @Schema(name = "office", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("office")
  public @Nullable ProviderFirmOffice getOffice() {
    return office;
  }

  public void setOffice(@Nullable ProviderFirmOffice office) {
    this.office = office;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirmOfficeDto providerFirmOfficeDto = (ProviderFirmOfficeDto) o;
    return Objects.equals(this.firm, providerFirmOfficeDto.firm) &&
        Objects.equals(this.office, providerFirmOfficeDto.office);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firm, office);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmOfficeDto {\n");
    sb.append("    firm: ").append(toIndentedString(firm)).append("\n");
    sb.append("    office: ").append(toIndentedString(office)).append("\n");
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

    private ProviderFirmOfficeDto instance;

    public Builder() {
      this(new ProviderFirmOfficeDto());
    }

    protected Builder(ProviderFirmOfficeDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmOfficeDto value) { 
      this.instance.setFirm(value.firm);
      this.instance.setOffice(value.office);
      return this;
    }

    public ProviderFirmOfficeDto.Builder firm(ProviderFirmSummary firm) {
      this.instance.firm(firm);
      return this;
    }
    
    public ProviderFirmOfficeDto.Builder office(ProviderFirmOffice office) {
      this.instance.office(office);
      return this;
    }
    
    /**
    * returns a built ProviderFirmOfficeDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmOfficeDto build() {
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
  public static ProviderFirmOfficeDto.Builder builder() {
    return new ProviderFirmOfficeDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmOfficeDto.Builder toBuilder() {
    ProviderFirmOfficeDto.Builder builder = new ProviderFirmOfficeDto.Builder();
    return builder.copyOf(this);
  }

}

