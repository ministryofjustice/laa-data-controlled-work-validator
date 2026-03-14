package uk.gov.justice.laa.fee.scheme.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.lang.Nullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ValidationMessagesInner
 */

@JsonTypeName("ValidationMessages_inner")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-14T10:05:01.697703Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ValidationMessagesInner implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * Gets or Sets type
   */
  public enum TypeEnum {
    ERROR("ERROR"),
    
    WARNING("WARNING");

    private final String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private @Nullable TypeEnum type;

  private @Nullable String code;

  private @Nullable String message;

  public ValidationMessagesInner type(@Nullable TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  
  @Schema(name = "type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type")
  public @Nullable TypeEnum getType() {
    return type;
  }

  public void setType(@Nullable TypeEnum type) {
    this.type = type;
  }

  public ValidationMessagesInner code(@Nullable String code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
   */
  
  @Schema(name = "code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("code")
  public @Nullable String getCode() {
    return code;
  }

  public void setCode(@Nullable String code) {
    this.code = code;
  }

  public ValidationMessagesInner message(@Nullable String message) {
    this.message = message;
    return this;
  }

  /**
   * Get message
   * @return message
   */
  
  @Schema(name = "message", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("message")
  public @Nullable String getMessage() {
    return message;
  }

  public void setMessage(@Nullable String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidationMessagesInner validationMessagesInner = (ValidationMessagesInner) o;
    return Objects.equals(this.type, validationMessagesInner.type) &&
        Objects.equals(this.code, validationMessagesInner.code) &&
        Objects.equals(this.message, validationMessagesInner.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, code, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidationMessagesInner {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

    private ValidationMessagesInner instance;

    public Builder() {
      this(new ValidationMessagesInner());
    }

    protected Builder(ValidationMessagesInner instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ValidationMessagesInner value) { 
      this.instance.setType(value.type);
      this.instance.setCode(value.code);
      this.instance.setMessage(value.message);
      return this;
    }

    public ValidationMessagesInner.Builder type(TypeEnum type) {
      this.instance.type(type);
      return this;
    }
    
    public ValidationMessagesInner.Builder code(String code) {
      this.instance.code(code);
      return this;
    }
    
    public ValidationMessagesInner.Builder message(String message) {
      this.instance.message(message);
      return this;
    }
    
    /**
    * returns a built ValidationMessagesInner instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ValidationMessagesInner build() {
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
  public static ValidationMessagesInner.Builder builder() {
    return new ValidationMessagesInner.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ValidationMessagesInner.Builder toBuilder() {
    ValidationMessagesInner.Builder builder = new ValidationMessagesInner.Builder();
    return builder.copyOf(this);
  }

}

