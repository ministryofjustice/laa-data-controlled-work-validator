package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
 * OfficeCodesDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T14:44:48.222559Z[Europe/London]", comments = "Generator version: 7.14.0")
public class OfficeCodesDto implements Serializable {

  private static final long serialVersionUID = 1L;

  @Valid
  private List<String> officeCodes = new ArrayList<>();

  public OfficeCodesDto officeCodes(List<String> officeCodes) {
    this.officeCodes = officeCodes;
    return this;
  }

  public OfficeCodesDto addOfficeCodesItem(String officeCodesItem) {
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
  
  @Schema(name = "officeCodes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("officeCodes")
  public List<String> getOfficeCodes() {
    return officeCodes;
  }

  public void setOfficeCodes(List<String> officeCodes) {
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
    OfficeCodesDto officeCodesDto = (OfficeCodesDto) o;
    return Objects.equals(this.officeCodes, officeCodesDto.officeCodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(officeCodes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OfficeCodesDto {\n");
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

    private OfficeCodesDto instance;

    public Builder() {
      this(new OfficeCodesDto());
    }

    protected Builder(OfficeCodesDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(OfficeCodesDto value) { 
      this.instance.setOfficeCodes(value.officeCodes);
      return this;
    }

    public OfficeCodesDto.Builder officeCodes(List<String> officeCodes) {
      this.instance.officeCodes(officeCodes);
      return this;
    }
    
    /**
    * returns a built OfficeCodesDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public OfficeCodesDto build() {
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
  public static OfficeCodesDto.Builder builder() {
    return new OfficeCodesDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public OfficeCodesDto.Builder toBuilder() {
    OfficeCodesDto.Builder builder = new OfficeCodesDto.Builder();
    return builder.copyOf(this);
  }

}

