package uk.gov.justice.laa.dstew.payments.claimsdata.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.UUID;
import org.springframework.lang.Nullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CreateMatterStart201Response
 */

@JsonTypeName("createMatterStart_201_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T21:51:30.932283Z[Europe/London]", comments = "Generator version: 7.14.0")
public class CreateMatterStart201Response implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable UUID id;

  public CreateMatterStart201Response id(@Nullable UUID id) {
    this.id = id;
    return this;
  }

  /**
   * UUID of the created Matter Start
   * @return id
   */
  @Valid 
  @Schema(name = "id", description = "UUID of the created Matter Start", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public @Nullable UUID getId() {
    return id;
  }

  public void setId(@Nullable UUID id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateMatterStart201Response createMatterStart201Response = (CreateMatterStart201Response) o;
    return Objects.equals(this.id, createMatterStart201Response.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateMatterStart201Response {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

    private CreateMatterStart201Response instance;

    public Builder() {
      this(new CreateMatterStart201Response());
    }

    protected Builder(CreateMatterStart201Response instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CreateMatterStart201Response value) { 
      this.instance.setId(value.id);
      return this;
    }

    public CreateMatterStart201Response.Builder id(UUID id) {
      this.instance.id(id);
      return this;
    }
    
    /**
    * returns a built CreateMatterStart201Response instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CreateMatterStart201Response build() {
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
  public static CreateMatterStart201Response.Builder builder() {
    return new CreateMatterStart201Response.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CreateMatterStart201Response.Builder toBuilder() {
    CreateMatterStart201Response.Builder builder = new CreateMatterStart201Response.Builder();
    return builder.copyOf(this);
  }

}

