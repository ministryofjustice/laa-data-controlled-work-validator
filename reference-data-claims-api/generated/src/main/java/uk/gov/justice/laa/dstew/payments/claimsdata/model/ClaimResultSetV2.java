package uk.gov.justice.laa.dstew.payments.claimsdata.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResponseV2;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ClaimResultSetV2
 */

@JsonTypeName("claim_result_set_v2")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T14:44:45.032142Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ClaimResultSetV2 implements Serializable {

  private static final long serialVersionUID = 1L;

  @Valid
  private List<ClaimResponseV2> content = new ArrayList<>();

  private @Nullable Integer totalPages;

  private @Nullable Integer totalElements;

  private @Nullable Integer number;

  private @Nullable Integer size;

  public ClaimResultSetV2 content(List<ClaimResponseV2> content) {
    this.content = content;
    return this;
  }

  public ClaimResultSetV2 addContentItem(ClaimResponseV2 contentItem) {
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
  public List<ClaimResponseV2> getContent() {
    return content;
  }

  public void setContent(List<ClaimResponseV2> content) {
    this.content = content;
  }

  public ClaimResultSetV2 totalPages(@Nullable Integer totalPages) {
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

  public ClaimResultSetV2 totalElements(@Nullable Integer totalElements) {
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

  public ClaimResultSetV2 number(@Nullable Integer number) {
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

  public ClaimResultSetV2 size(@Nullable Integer size) {
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
    ClaimResultSetV2 claimResultSetV2 = (ClaimResultSetV2) o;
    return Objects.equals(this.content, claimResultSetV2.content) &&
        Objects.equals(this.totalPages, claimResultSetV2.totalPages) &&
        Objects.equals(this.totalElements, claimResultSetV2.totalElements) &&
        Objects.equals(this.number, claimResultSetV2.number) &&
        Objects.equals(this.size, claimResultSetV2.size);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, totalPages, totalElements, number, size);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClaimResultSetV2 {\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
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

    private ClaimResultSetV2 instance;

    public Builder() {
      this(new ClaimResultSetV2());
    }

    protected Builder(ClaimResultSetV2 instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ClaimResultSetV2 value) { 
      this.instance.setContent(value.content);
      this.instance.setTotalPages(value.totalPages);
      this.instance.setTotalElements(value.totalElements);
      this.instance.setNumber(value.number);
      this.instance.setSize(value.size);
      return this;
    }

    public ClaimResultSetV2.Builder content(List<ClaimResponseV2> content) {
      this.instance.content(content);
      return this;
    }
    
    public ClaimResultSetV2.Builder totalPages(Integer totalPages) {
      this.instance.totalPages(totalPages);
      return this;
    }
    
    public ClaimResultSetV2.Builder totalElements(Integer totalElements) {
      this.instance.totalElements(totalElements);
      return this;
    }
    
    public ClaimResultSetV2.Builder number(Integer number) {
      this.instance.number(number);
      return this;
    }
    
    public ClaimResultSetV2.Builder size(Integer size) {
      this.instance.size(size);
      return this;
    }
    
    /**
    * returns a built ClaimResultSetV2 instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ClaimResultSetV2 build() {
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
  public static ClaimResultSetV2.Builder builder() {
    return new ClaimResultSetV2.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ClaimResultSetV2.Builder toBuilder() {
    ClaimResultSetV2.Builder builder = new ClaimResultSetV2.Builder();
    return builder.copyOf(this);
  }

}

