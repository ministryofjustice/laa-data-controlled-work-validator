package uk.gov.justice.laa.dstew.payments.claimsdata.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.lang.Nullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * The details of the bulk submission schedule
 */

@Schema(name = "getBulkSubmission_200_response_details_schedule", description = "The details of the bulk submission schedule")
@JsonTypeName("getBulkSubmission_200_response_details_schedule")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T18:11:17.854857Z[Europe/London]", comments = "Generator version: 7.14.0")
public class GetBulkSubmission200ResponseDetailsSchedule implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String submissionPeriod;

  private @Nullable String areaOfLaw;

  private @Nullable String scheduleNum;

  public GetBulkSubmission200ResponseDetailsSchedule submissionPeriod(@Nullable String submissionPeriod) {
    this.submissionPeriod = submissionPeriod;
    return this;
  }

  /**
   * Get submissionPeriod
   * @return submissionPeriod
   */
  
  @Schema(name = "submission_period", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("submission_period")
  public @Nullable String getSubmissionPeriod() {
    return submissionPeriod;
  }

  public void setSubmissionPeriod(@Nullable String submissionPeriod) {
    this.submissionPeriod = submissionPeriod;
  }

  public GetBulkSubmission200ResponseDetailsSchedule areaOfLaw(@Nullable String areaOfLaw) {
    this.areaOfLaw = areaOfLaw;
    return this;
  }

  /**
   * Get areaOfLaw
   * @return areaOfLaw
   */
  
  @Schema(name = "area_of_law", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("area_of_law")
  public @Nullable String getAreaOfLaw() {
    return areaOfLaw;
  }

  public void setAreaOfLaw(@Nullable String areaOfLaw) {
    this.areaOfLaw = areaOfLaw;
  }

  public GetBulkSubmission200ResponseDetailsSchedule scheduleNum(@Nullable String scheduleNum) {
    this.scheduleNum = scheduleNum;
    return this;
  }

  /**
   * Get scheduleNum
   * @return scheduleNum
   */
  
  @Schema(name = "schedule_num", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("schedule_num")
  public @Nullable String getScheduleNum() {
    return scheduleNum;
  }

  public void setScheduleNum(@Nullable String scheduleNum) {
    this.scheduleNum = scheduleNum;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetBulkSubmission200ResponseDetailsSchedule getBulkSubmission200ResponseDetailsSchedule = (GetBulkSubmission200ResponseDetailsSchedule) o;
    return Objects.equals(this.submissionPeriod, getBulkSubmission200ResponseDetailsSchedule.submissionPeriod) &&
        Objects.equals(this.areaOfLaw, getBulkSubmission200ResponseDetailsSchedule.areaOfLaw) &&
        Objects.equals(this.scheduleNum, getBulkSubmission200ResponseDetailsSchedule.scheduleNum);
  }

  @Override
  public int hashCode() {
    return Objects.hash(submissionPeriod, areaOfLaw, scheduleNum);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetBulkSubmission200ResponseDetailsSchedule {\n");
    sb.append("    submissionPeriod: ").append(toIndentedString(submissionPeriod)).append("\n");
    sb.append("    areaOfLaw: ").append(toIndentedString(areaOfLaw)).append("\n");
    sb.append("    scheduleNum: ").append(toIndentedString(scheduleNum)).append("\n");
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

    private GetBulkSubmission200ResponseDetailsSchedule instance;

    public Builder() {
      this(new GetBulkSubmission200ResponseDetailsSchedule());
    }

    protected Builder(GetBulkSubmission200ResponseDetailsSchedule instance) {
      this.instance = instance;
    }

    protected Builder copyOf(GetBulkSubmission200ResponseDetailsSchedule value) { 
      this.instance.setSubmissionPeriod(value.submissionPeriod);
      this.instance.setAreaOfLaw(value.areaOfLaw);
      this.instance.setScheduleNum(value.scheduleNum);
      return this;
    }

    public GetBulkSubmission200ResponseDetailsSchedule.Builder submissionPeriod(String submissionPeriod) {
      this.instance.submissionPeriod(submissionPeriod);
      return this;
    }
    
    public GetBulkSubmission200ResponseDetailsSchedule.Builder areaOfLaw(String areaOfLaw) {
      this.instance.areaOfLaw(areaOfLaw);
      return this;
    }
    
    public GetBulkSubmission200ResponseDetailsSchedule.Builder scheduleNum(String scheduleNum) {
      this.instance.scheduleNum(scheduleNum);
      return this;
    }
    
    /**
    * returns a built GetBulkSubmission200ResponseDetailsSchedule instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public GetBulkSubmission200ResponseDetailsSchedule build() {
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
  public static GetBulkSubmission200ResponseDetailsSchedule.Builder builder() {
    return new GetBulkSubmission200ResponseDetailsSchedule.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public GetBulkSubmission200ResponseDetailsSchedule.Builder toBuilder() {
    GetBulkSubmission200ResponseDetailsSchedule.Builder builder = new GetBulkSubmission200ResponseDetailsSchedule.Builder();
    return builder.copyOf(this);
  }

}

