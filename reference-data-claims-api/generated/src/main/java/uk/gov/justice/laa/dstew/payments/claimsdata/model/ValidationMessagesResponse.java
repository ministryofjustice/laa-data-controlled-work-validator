package uk.gov.justice.laa.dstew.payments.claimsdata.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ValidationMessageBase;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ValidationMessagesResponse
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T18:11:17.854857Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ValidationMessagesResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  @Valid
  private List<@Valid ValidationMessageBase> content = new ArrayList<>();

  private @Nullable Integer totalClaims;

  private @Nullable Integer totalPages;

  private @Nullable Integer totalElements;

  private @Nullable Integer number;

  private @Nullable Integer size;

  public ValidationMessagesResponse content(List<@Valid ValidationMessageBase> content) {
    this.content = content;
    return this;
  }

  public ValidationMessagesResponse addContentItem(ValidationMessageBase contentItem) {
    if (this.content == null) {
      this.content = new ArrayList<>();
    }
    this.content.add(contentItem);
    return this;
  }

  /**
   * Get content
   * @return content
   */
  @Valid 
  @Schema(name = "content", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("content")
  public List<@Valid ValidationMessageBase> getContent() {
    return content;
  }

  public void setContent(List<@Valid ValidationMessageBase> content) {
    this.content = content;
  }

  public ValidationMessagesResponse totalClaims(@Nullable Integer totalClaims) {
    this.totalClaims = totalClaims;
    return this;
  }

  /**
   * The total number of unique claims that have validation errors
   * @return totalClaims
   */
  
  @Schema(name = "total_claims", description = "The total number of unique claims that have validation errors", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("total_claims")
  public @Nullable Integer getTotalClaims() {
    return totalClaims;
  }

  public void setTotalClaims(@Nullable Integer totalClaims) {
    this.totalClaims = totalClaims;
  }

  public ValidationMessagesResponse totalPages(@Nullable Integer totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  /**
   * Get totalPages
   * @return totalPages
   */
  
  @Schema(name = "total_pages", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("total_pages")
  public @Nullable Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(@Nullable Integer totalPages) {
    this.totalPages = totalPages;
  }

  public ValidationMessagesResponse totalElements(@Nullable Integer totalElements) {
    this.totalElements = totalElements;
    return this;
  }

  /**
   * Get totalElements
   * @return totalElements
   */
  
  @Schema(name = "total_elements", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("total_elements")
  public @Nullable Integer getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(@Nullable Integer totalElements) {
    this.totalElements = totalElements;
  }

  public ValidationMessagesResponse number(@Nullable Integer number) {
    this.number = number;
    return this;
  }

  /**
   * Get number
   * @return number
   */
  
  @Schema(name = "number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("number")
  public @Nullable Integer getNumber() {
    return number;
  }

  public void setNumber(@Nullable Integer number) {
    this.number = number;
  }

  public ValidationMessagesResponse size(@Nullable Integer size) {
    this.size = size;
    return this;
  }

  /**
   * Get size
   * @return size
   */
  
  @Schema(name = "size", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("size")
  public @Nullable Integer getSize() {
    return size;
  }

  public void setSize(@Nullable Integer size) {
    this.size = size;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidationMessagesResponse validationMessagesResponse = (ValidationMessagesResponse) o;
    return Objects.equals(this.content, validationMessagesResponse.content) &&
        Objects.equals(this.totalClaims, validationMessagesResponse.totalClaims) &&
        Objects.equals(this.totalPages, validationMessagesResponse.totalPages) &&
        Objects.equals(this.totalElements, validationMessagesResponse.totalElements) &&
        Objects.equals(this.number, validationMessagesResponse.number) &&
        Objects.equals(this.size, validationMessagesResponse.size);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, totalClaims, totalPages, totalElements, number, size);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidationMessagesResponse {\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    totalClaims: ").append(toIndentedString(totalClaims)).append("\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
    sb.append("    totalElements: ").append(toIndentedString(totalElements)).append("\n");
    sb.append("    number: ").append(toIndentedString(number)).append("\n");
    sb.append("    size: ").append(toIndentedString(size)).append("\n");
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

    private ValidationMessagesResponse instance;

    public Builder() {
      this(new ValidationMessagesResponse());
    }

    protected Builder(ValidationMessagesResponse instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ValidationMessagesResponse value) { 
      this.instance.setContent(value.content);
      this.instance.setTotalClaims(value.totalClaims);
      this.instance.setTotalPages(value.totalPages);
      this.instance.setTotalElements(value.totalElements);
      this.instance.setNumber(value.number);
      this.instance.setSize(value.size);
      return this;
    }

    public ValidationMessagesResponse.Builder content(List<ValidationMessageBase> content) {
      this.instance.content(content);
      return this;
    }
    
    public ValidationMessagesResponse.Builder totalClaims(Integer totalClaims) {
      this.instance.totalClaims(totalClaims);
      return this;
    }
    
    public ValidationMessagesResponse.Builder totalPages(Integer totalPages) {
      this.instance.totalPages(totalPages);
      return this;
    }
    
    public ValidationMessagesResponse.Builder totalElements(Integer totalElements) {
      this.instance.totalElements(totalElements);
      return this;
    }
    
    public ValidationMessagesResponse.Builder number(Integer number) {
      this.instance.number(number);
      return this;
    }
    
    public ValidationMessagesResponse.Builder size(Integer size) {
      this.instance.size(size);
      return this;
    }
    
    /**
    * returns a built ValidationMessagesResponse instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ValidationMessagesResponse build() {
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
  public static ValidationMessagesResponse.Builder builder() {
    return new ValidationMessagesResponse.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ValidationMessagesResponse.Builder toBuilder() {
    ValidationMessagesResponse.Builder builder = new ValidationMessagesResponse.Builder();
    return builder.copyOf(this);
  }

}

