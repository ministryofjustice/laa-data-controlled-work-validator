package uk.gov.justice.laa.fee.scheme.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Standard error response returned for failed requests
 */

@Schema(name = "ErrorResponse", description = "Standard error response returned for failed requests")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-17T22:25:28.509841Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ErrorResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime timestamp;

  private @Nullable Integer status;

  private @Nullable String error;

  private @Nullable String message;

  public ErrorResponse timestamp(@Nullable OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Time when the error occurred
   * @return timestamp
   */
  @Valid 
  @Schema(name = "timestamp", description = "Time when the error occurred", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("timestamp")
  public @Nullable OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(@Nullable OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public ErrorResponse status(@Nullable Integer status) {
    this.status = status;
    return this;
  }

  /**
   * HTTP status code associated with the error
   * @return status
   */
  
  @Schema(name = "status", example = "404", description = "HTTP status code associated with the error", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public @Nullable Integer getStatus() {
    return status;
  }

  public void setStatus(@Nullable Integer status) {
    this.status = status;
  }

  public ErrorResponse error(@Nullable String error) {
    this.error = error;
    return this;
  }

  /**
   * Description of the HTTP error
   * @return error
   */
  
  @Schema(name = "error", example = "Not Found", description = "Description of the HTTP error", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("error")
  public @Nullable String getError() {
    return error;
  }

  public void setError(@Nullable String error) {
    this.error = error;
  }

  public ErrorResponse message(@Nullable String message) {
    this.message = message;
    return this;
  }

  /**
   * Description/explanation of the error
   * @return message
   */
  
  @Schema(name = "message", example = "Category code not found for fee code: X123", description = "Description/explanation of the error", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
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
    ErrorResponse errorResponse = (ErrorResponse) o;
    return Objects.equals(this.timestamp, errorResponse.timestamp) &&
        Objects.equals(this.status, errorResponse.status) &&
        Objects.equals(this.error, errorResponse.error) &&
        Objects.equals(this.message, errorResponse.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, status, error, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorResponse {\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    error: ").append(toIndentedString(error)).append("\n");
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

    private ErrorResponse instance;

    public Builder() {
      this(new ErrorResponse());
    }

    protected Builder(ErrorResponse instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ErrorResponse value) { 
      this.instance.setTimestamp(value.timestamp);
      this.instance.setStatus(value.status);
      this.instance.setError(value.error);
      this.instance.setMessage(value.message);
      return this;
    }

    public ErrorResponse.Builder timestamp(OffsetDateTime timestamp) {
      this.instance.timestamp(timestamp);
      return this;
    }
    
    public ErrorResponse.Builder status(Integer status) {
      this.instance.status(status);
      return this;
    }
    
    public ErrorResponse.Builder error(String error) {
      this.instance.error(error);
      return this;
    }
    
    public ErrorResponse.Builder message(String message) {
      this.instance.message(message);
      return this;
    }
    
    /**
    * returns a built ErrorResponse instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ErrorResponse build() {
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
  public static ErrorResponse.Builder builder() {
    return new ErrorResponse.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ErrorResponse.Builder toBuilder() {
    ErrorResponse.Builder builder = new ErrorResponse.Builder();
    return builder.copyOf(this);
  }

}

