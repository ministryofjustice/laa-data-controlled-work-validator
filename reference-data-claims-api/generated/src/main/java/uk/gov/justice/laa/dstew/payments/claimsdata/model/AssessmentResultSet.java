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
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AssessmentGet;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * AssessmentResultSet
 */

@JsonTypeName("assessment_result_set")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T14:44:45.032142Z[Europe/London]", comments = "Generator version: 7.14.0")
public class AssessmentResultSet implements Serializable {

  private static final long serialVersionUID = 1L;

  @Valid
  private List<@Valid AssessmentGet> assessments = new ArrayList<>();

  private @Nullable Integer totalPages;

  private @Nullable Integer totalElements;

  private @Nullable Integer number;

  private @Nullable Integer size;

  public AssessmentResultSet assessments(List<@Valid AssessmentGet> assessments) {
    this.assessments = assessments;
    return this;
  }

  public AssessmentResultSet addAssessmentsItem(AssessmentGet assessmentsItem) {
    if (this.assessments == null) {
      this.assessments = new ArrayList<>();
    }
    this.assessments.add(assessmentsItem);
    return this;
  }

  /**
   * Get assessments
   * @return assessments
   */
  @Valid 
  @Schema(name = "assessments", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("assessments")
  public List<@Valid AssessmentGet> getAssessments() {
    return assessments;
  }

  public void setAssessments(List<@Valid AssessmentGet> assessments) {
    this.assessments = assessments;
  }

  public AssessmentResultSet totalPages(@Nullable Integer totalPages) {
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

  public AssessmentResultSet totalElements(@Nullable Integer totalElements) {
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

  public AssessmentResultSet number(@Nullable Integer number) {
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

  public AssessmentResultSet size(@Nullable Integer size) {
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
    AssessmentResultSet assessmentResultSet = (AssessmentResultSet) o;
    return Objects.equals(this.assessments, assessmentResultSet.assessments) &&
        Objects.equals(this.totalPages, assessmentResultSet.totalPages) &&
        Objects.equals(this.totalElements, assessmentResultSet.totalElements) &&
        Objects.equals(this.number, assessmentResultSet.number) &&
        Objects.equals(this.size, assessmentResultSet.size);
  }

  @Override
  public int hashCode() {
    return Objects.hash(assessments, totalPages, totalElements, number, size);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AssessmentResultSet {\n");
    sb.append("    assessments: ").append(toIndentedString(assessments)).append("\n");
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

    private AssessmentResultSet instance;

    public Builder() {
      this(new AssessmentResultSet());
    }

    protected Builder(AssessmentResultSet instance) {
      this.instance = instance;
    }

    protected Builder copyOf(AssessmentResultSet value) { 
      this.instance.setAssessments(value.assessments);
      this.instance.setTotalPages(value.totalPages);
      this.instance.setTotalElements(value.totalElements);
      this.instance.setNumber(value.number);
      this.instance.setSize(value.size);
      return this;
    }

    public AssessmentResultSet.Builder assessments(List<AssessmentGet> assessments) {
      this.instance.assessments(assessments);
      return this;
    }
    
    public AssessmentResultSet.Builder totalPages(Integer totalPages) {
      this.instance.totalPages(totalPages);
      return this;
    }
    
    public AssessmentResultSet.Builder totalElements(Integer totalElements) {
      this.instance.totalElements(totalElements);
      return this;
    }
    
    public AssessmentResultSet.Builder number(Integer number) {
      this.instance.number(number);
      return this;
    }
    
    public AssessmentResultSet.Builder size(Integer size) {
      this.instance.size(size);
      return this;
    }
    
    /**
    * returns a built AssessmentResultSet instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public AssessmentResultSet build() {
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
  public static AssessmentResultSet.Builder builder() {
    return new AssessmentResultSet.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public AssessmentResultSet.Builder toBuilder() {
    AssessmentResultSet.Builder builder = new AssessmentResultSet.Builder();
    return builder.copyOf(this);
  }

}

