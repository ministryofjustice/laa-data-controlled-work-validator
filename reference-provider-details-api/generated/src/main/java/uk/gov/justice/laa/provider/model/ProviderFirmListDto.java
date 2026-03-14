package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
 * ProviderFirmListDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-14T10:05:02.190518Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmListDto implements Serializable {

  private static final long serialVersionUID = 1L;

  @Valid
  private List<@Valid ProviderFirm> firms = new ArrayList<>();

  public ProviderFirmListDto firms(List<@Valid ProviderFirm> firms) {
    this.firms = firms;
    return this;
  }

  public ProviderFirmListDto addFirmsItem(ProviderFirm firmsItem) {
    if (this.firms == null) {
      this.firms = new ArrayList<>();
    }
    this.firms.add(firmsItem);
    return this;
  }

  /**
   * Get firms
   * @return firms
   */
  @Valid 
  @Schema(name = "firms", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("firms")
  public List<@Valid ProviderFirm> getFirms() {
    return firms;
  }

  public void setFirms(List<@Valid ProviderFirm> firms) {
    this.firms = firms;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirmListDto providerFirmListDto = (ProviderFirmListDto) o;
    return Objects.equals(this.firms, providerFirmListDto.firms);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firms);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmListDto {\n");
    sb.append("    firms: ").append(toIndentedString(firms)).append("\n");
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

    private ProviderFirmListDto instance;

    public Builder() {
      this(new ProviderFirmListDto());
    }

    protected Builder(ProviderFirmListDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmListDto value) { 
      this.instance.setFirms(value.firms);
      return this;
    }

    public ProviderFirmListDto.Builder firms(List<ProviderFirm> firms) {
      this.instance.firms(firms);
      return this;
    }
    
    /**
    * returns a built ProviderFirmListDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmListDto build() {
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
  public static ProviderFirmListDto.Builder builder() {
    return new ProviderFirmListDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmListDto.Builder toBuilder() {
    ProviderFirmListDto.Builder builder = new ProviderFirmListDto.Builder();
    return builder.copyOf(this);
  }

}

