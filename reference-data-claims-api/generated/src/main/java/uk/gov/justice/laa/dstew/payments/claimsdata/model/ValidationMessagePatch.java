package uk.gov.justice.laa.dstew.payments.claimsdata.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ValidationMessageType;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Validation message
 */

@Schema(name = "validation_message_patch", description = "Validation message")
@JsonTypeName("validation_message_patch")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T14:44:45.032142Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ValidationMessagePatch implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable ValidationMessageType type;

  private @Nullable String source;

  private @Nullable String displayMessage;

  private @Nullable String technicalMessage;

  public ValidationMessagePatch type(@Nullable ValidationMessageType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  @Valid 
  @Schema(name = "type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type")
  public @Nullable ValidationMessageType getType() {
    return type;
  }

  public void setType(@Nullable ValidationMessageType type) {
    this.type = type;
  }

  public ValidationMessagePatch source(@Nullable String source) {
    this.source = source;
    return this;
  }

  /**
   * Get source
   * @return source
   */
  
  @Schema(name = "source", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("source")
  public @Nullable String getSource() {
    return source;
  }

  public void setSource(@Nullable String source) {
    this.source = source;
  }

  public ValidationMessagePatch displayMessage(@Nullable String displayMessage) {
    this.displayMessage = displayMessage;
    return this;
  }

  /**
   * Get displayMessage
   * @return displayMessage
   */
  
  @Schema(name = "display_message", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("display_message")
  public @Nullable String getDisplayMessage() {
    return displayMessage;
  }

  public void setDisplayMessage(@Nullable String displayMessage) {
    this.displayMessage = displayMessage;
  }

  public ValidationMessagePatch technicalMessage(@Nullable String technicalMessage) {
    this.technicalMessage = technicalMessage;
    return this;
  }

  /**
   * Get technicalMessage
   * @return technicalMessage
   */
  
  @Schema(name = "technical_message", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("technical_message")
  public @Nullable String getTechnicalMessage() {
    return technicalMessage;
  }

  public void setTechnicalMessage(@Nullable String technicalMessage) {
    this.technicalMessage = technicalMessage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidationMessagePatch validationMessagePatch = (ValidationMessagePatch) o;
    return Objects.equals(this.type, validationMessagePatch.type) &&
        Objects.equals(this.source, validationMessagePatch.source) &&
        Objects.equals(this.displayMessage, validationMessagePatch.displayMessage) &&
        Objects.equals(this.technicalMessage, validationMessagePatch.technicalMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, source, displayMessage, technicalMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidationMessagePatch {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
    sb.append("    displayMessage: ").append(toIndentedString(displayMessage)).append("\n");
    sb.append("    technicalMessage: ").append(toIndentedString(technicalMessage)).append("\n");
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

    private ValidationMessagePatch instance;

    public Builder() {
      this(new ValidationMessagePatch());
    }

    protected Builder(ValidationMessagePatch instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ValidationMessagePatch value) { 
      this.instance.setType(value.type);
      this.instance.setSource(value.source);
      this.instance.setDisplayMessage(value.displayMessage);
      this.instance.setTechnicalMessage(value.technicalMessage);
      return this;
    }

    public ValidationMessagePatch.Builder type(ValidationMessageType type) {
      this.instance.type(type);
      return this;
    }
    
    public ValidationMessagePatch.Builder source(String source) {
      this.instance.source(source);
      return this;
    }
    
    public ValidationMessagePatch.Builder displayMessage(String displayMessage) {
      this.instance.displayMessage(displayMessage);
      return this;
    }
    
    public ValidationMessagePatch.Builder technicalMessage(String technicalMessage) {
      this.instance.technicalMessage(technicalMessage);
      return this;
    }
    
    /**
    * returns a built ValidationMessagePatch instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ValidationMessagePatch build() {
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
  public static ValidationMessagePatch.Builder builder() {
    return new ValidationMessagePatch.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ValidationMessagePatch.Builder toBuilder() {
    ValidationMessagePatch.Builder builder = new ValidationMessagePatch.Builder();
    return builder.copyOf(this);
  }

}

