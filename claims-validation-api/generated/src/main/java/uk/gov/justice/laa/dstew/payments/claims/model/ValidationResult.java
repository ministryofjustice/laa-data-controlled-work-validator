package uk.gov.justice.laa.dstew.payments.claims.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ValidationResult
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-10T13:28:50.707211Z[Europe/London]", comments = "Generator version: 7.18.0")
public class ValidationResult implements Serializable {

  private static final long serialVersionUID = 1L;

  private Boolean isValid;

  @Valid
  private List<@Valid ValidationIssue> issues = new ArrayList<>();

  public ValidationResult() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ValidationResult(Boolean isValid, List<@Valid ValidationIssue> issues) {
    this.isValid = isValid;
    this.issues = issues;
  }

  public ValidationResult isValid(Boolean isValid) {
    this.isValid = isValid;
    return this;
  }

  /**
   * Whether the claim passed all validation rules
   * @return isValid
   */
  @NotNull 
  @Schema(name = "isValid", description = "Whether the claim passed all validation rules", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("isValid")
  public Boolean getIsValid() {
    return isValid;
  }

  public void setIsValid(Boolean isValid) {
    this.isValid = isValid;
  }

  public ValidationResult issues(List<@Valid ValidationIssue> issues) {
    this.issues = issues;
    return this;
  }

  public ValidationResult addIssuesItem(ValidationIssue issuesItem) {
    if (this.issues == null) {
      this.issues = new ArrayList<>();
    }
    this.issues.add(issuesItem);
    return this;
  }

  /**
   * Get issues
   * @return issues
   */
  @NotNull @Valid 
  @Schema(name = "issues", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("issues")
  public List<@Valid ValidationIssue> getIssues() {
    return issues;
  }

  public void setIssues(List<@Valid ValidationIssue> issues) {
    this.issues = issues;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidationResult validationResult = (ValidationResult) o;
    return Objects.equals(this.isValid, validationResult.isValid) &&
        Objects.equals(this.issues, validationResult.issues);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isValid, issues);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidationResult {\n");
    sb.append("    isValid: ").append(toIndentedString(isValid)).append("\n");
    sb.append("    issues: ").append(toIndentedString(issues)).append("\n");
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

    private ValidationResult instance;

    public Builder() {
      this(new ValidationResult());
    }

    protected Builder(ValidationResult instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ValidationResult value) { 
      this.instance.setIsValid(value.isValid);
      this.instance.setIssues(value.issues);
      return this;
    }

    public ValidationResult.Builder isValid(Boolean isValid) {
      this.instance.isValid(isValid);
      return this;
    }
    
    public ValidationResult.Builder issues(List<ValidationIssue> issues) {
      this.instance.issues(issues);
      return this;
    }
    
    /**
    * returns a built ValidationResult instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ValidationResult build() {
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
  public static ValidationResult.Builder builder() {
    return new ValidationResult.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ValidationResult.Builder toBuilder() {
    ValidationResult.Builder builder = new ValidationResult.Builder();
    return builder.copyOf(this);
  }

}

