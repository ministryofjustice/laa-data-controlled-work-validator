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
 * CreateAssessment201Response
 */

@JsonTypeName("createAssessment_201_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T15:47:02.322440Z[Europe/London]", comments = "Generator version: 7.14.0")
public class CreateAssessment201Response implements Serializable {

  private static final long serialVersionUID = 1L;

  private UUID id;

  public CreateAssessment201Response() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public CreateAssessment201Response(UUID id) {
    this.id = id;
  }

  public CreateAssessment201Response id(UUID id) {
    this.id = id;
    return this;
  }

  /**
   * UUID of the created Assessment
   * @return id
   */
  @NotNull @Valid 
  @Schema(name = "id", description = "UUID of the created Assessment", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
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
    CreateAssessment201Response createAssessment201Response = (CreateAssessment201Response) o;
    return Objects.equals(this.id, createAssessment201Response.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateAssessment201Response {\n");
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

    private CreateAssessment201Response instance;

    public Builder() {
      this(new CreateAssessment201Response());
    }

    protected Builder(CreateAssessment201Response instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CreateAssessment201Response value) { 
      this.instance.setId(value.id);
      return this;
    }

    public CreateAssessment201Response.Builder id(UUID id) {
      this.instance.id(id);
      return this;
    }
    
    /**
    * returns a built CreateAssessment201Response instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CreateAssessment201Response build() {
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
  public static CreateAssessment201Response.Builder builder() {
    return new CreateAssessment201Response.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CreateAssessment201Response.Builder toBuilder() {
    CreateAssessment201Response.Builder builder = new CreateAssessment201Response.Builder();
    return builder.copyOf(this);
  }

}

