package uk.gov.justice.laa.dstew.payments.claims.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssuePathInner;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationSeverity;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ValidationIssue
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-10T13:28:50.707211Z[Europe/London]", comments = "Generator version: 7.18.0")
public class ValidationIssue implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;

  private String message;

  @Valid
  private List<ValidationIssuePathInner> path = new ArrayList<>();

  private ValidationSeverity severity;

  public ValidationIssue() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ValidationIssue(String code, String message, ValidationSeverity severity) {
    this.code = code;
    this.message = message;
    this.severity = severity;
  }

  public ValidationIssue code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Unique code identifying the validation issue
   * @return code
   */
  @NotNull 
  @Schema(name = "code", example = "FEE.MISSING_JUSTIFICATION", description = "Unique code identifying the validation issue", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("code")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ValidationIssue message(String message) {
    this.message = message;
    return this;
  }

  /**
   * Human-readable description of the issue
   * @return message
   */
  @NotNull 
  @Schema(name = "message", example = "Enhancement fee requires a justification.", description = "Human-readable description of the issue", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public ValidationIssue path(List<ValidationIssuePathInner> path) {
    this.path = path;
    return this;
  }

  public ValidationIssue addPathItem(ValidationIssuePathInner pathItem) {
    if (this.path == null) {
      this.path = new ArrayList<>();
    }
    this.path.add(pathItem);
    return this;
  }

  /**
   * JSON path to the field causing the issue
   * @return path
   */
  @Valid 
  @Schema(name = "path", example = "[\"fees\",0,\"justification\"]", description = "JSON path to the field causing the issue", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("path")
  public List<ValidationIssuePathInner> getPath() {
    return path;
  }

  public void setPath(List<ValidationIssuePathInner> path) {
    this.path = path;
  }

  public ValidationIssue severity(ValidationSeverity severity) {
    this.severity = severity;
    return this;
  }

  /**
   * Get severity
   * @return severity
   */
  @NotNull @Valid 
  @Schema(name = "severity", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("severity")
  public ValidationSeverity getSeverity() {
    return severity;
  }

  public void setSeverity(ValidationSeverity severity) {
    this.severity = severity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidationIssue validationIssue = (ValidationIssue) o;
    return Objects.equals(this.code, validationIssue.code) &&
        Objects.equals(this.message, validationIssue.message) &&
        Objects.equals(this.path, validationIssue.path) &&
        Objects.equals(this.severity, validationIssue.severity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, message, path, severity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidationIssue {\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    severity: ").append(toIndentedString(severity)).append("\n");
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

    private ValidationIssue instance;

    public Builder() {
      this(new ValidationIssue());
    }

    protected Builder(ValidationIssue instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ValidationIssue value) { 
      this.instance.setCode(value.code);
      this.instance.setMessage(value.message);
      this.instance.setPath(value.path);
      this.instance.setSeverity(value.severity);
      return this;
    }

    public ValidationIssue.Builder code(String code) {
      this.instance.code(code);
      return this;
    }
    
    public ValidationIssue.Builder message(String message) {
      this.instance.message(message);
      return this;
    }
    
    public ValidationIssue.Builder path(List<ValidationIssuePathInner> path) {
      this.instance.path(path);
      return this;
    }
    
    public ValidationIssue.Builder severity(ValidationSeverity severity) {
      this.instance.severity(severity);
      return this;
    }
    
    /**
    * returns a built ValidationIssue instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ValidationIssue build() {
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
  public static ValidationIssue.Builder builder() {
    return new ValidationIssue.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ValidationIssue.Builder toBuilder() {
    ValidationIssue.Builder builder = new ValidationIssue.Builder();
    return builder.copyOf(this);
  }

}

