package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.provider.model.ProviderFirm;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ProviderFirmDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T18:11:19.822784Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable ProviderFirm firm;

  public ProviderFirmDto firm(@Nullable ProviderFirm firm) {
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
  public @Nullable ProviderFirm getFirm() {
    return firm;
  }

  public void setFirm(@Nullable ProviderFirm firm) {
    this.firm = firm;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirmDto providerFirmDto = (ProviderFirmDto) o;
    return Objects.equals(this.firm, providerFirmDto.firm);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firm);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmDto {\n");
    sb.append("    firm: ").append(toIndentedString(firm)).append("\n");
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

    private ProviderFirmDto instance;

    public Builder() {
      this(new ProviderFirmDto());
    }

    protected Builder(ProviderFirmDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmDto value) { 
      this.instance.setFirm(value.firm);
      return this;
    }

    public ProviderFirmDto.Builder firm(ProviderFirm firm) {
      this.instance.firm(firm);
      return this;
    }
    
    /**
    * returns a built ProviderFirmDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmDto build() {
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
  public static ProviderFirmDto.Builder builder() {
    return new ProviderFirmDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmDto.Builder toBuilder() {
    ProviderFirmDto.Builder builder = new ProviderFirmDto.Builder();
    return builder.copyOf(this);
  }

}

