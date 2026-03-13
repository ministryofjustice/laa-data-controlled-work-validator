package uk.gov.justice.laa.dstew.payments.claimsdata.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.BulkSubmissionMatterStart;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.BulkSubmissionOutcome;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.GetBulkSubmission200ResponseDetailsOffice;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.GetBulkSubmission200ResponseDetailsSchedule;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * The object storing all the details of the bulk submission
 */

@Schema(name = "getBulkSubmission_200_response_details", description = "The object storing all the details of the bulk submission")
@JsonTypeName("getBulkSubmission_200_response_details")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T18:11:17.854857Z[Europe/London]", comments = "Generator version: 7.14.0")
public class GetBulkSubmission200ResponseDetails implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable GetBulkSubmission200ResponseDetailsOffice office;

  private @Nullable GetBulkSubmission200ResponseDetailsSchedule schedule;

  @Valid
  private List<@Valid BulkSubmissionOutcome> outcomes = new ArrayList<>();

  @Valid
  private List<@Valid BulkSubmissionMatterStart> matterStarts = new ArrayList<>();

  @Valid
  private List<Map<String, String>> immigrationClr = new ArrayList<>();

  public GetBulkSubmission200ResponseDetails office(@Nullable GetBulkSubmission200ResponseDetailsOffice office) {
    this.office = office;
    return this;
  }

  /**
   * Get office
   * @return office
   */
  @Valid 
  @Schema(name = "office", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("office")
  public @Nullable GetBulkSubmission200ResponseDetailsOffice getOffice() {
    return office;
  }

  public void setOffice(@Nullable GetBulkSubmission200ResponseDetailsOffice office) {
    this.office = office;
  }

  public GetBulkSubmission200ResponseDetails schedule(@Nullable GetBulkSubmission200ResponseDetailsSchedule schedule) {
    this.schedule = schedule;
    return this;
  }

  /**
   * Get schedule
   * @return schedule
   */
  @Valid 
  @Schema(name = "schedule", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("schedule")
  public @Nullable GetBulkSubmission200ResponseDetailsSchedule getSchedule() {
    return schedule;
  }

  public void setSchedule(@Nullable GetBulkSubmission200ResponseDetailsSchedule schedule) {
    this.schedule = schedule;
  }

  public GetBulkSubmission200ResponseDetails outcomes(List<@Valid BulkSubmissionOutcome> outcomes) {
    this.outcomes = outcomes;
    return this;
  }

  public GetBulkSubmission200ResponseDetails addOutcomesItem(BulkSubmissionOutcome outcomesItem) {
    if (this.outcomes == null) {
      this.outcomes = new ArrayList<>();
    }
    this.outcomes.add(outcomesItem);
    return this;
  }

  /**
   * List of outcome objects for the bulk submission
   * @return outcomes
   */
  @Valid 
  @Schema(name = "outcomes", description = "List of outcome objects for the bulk submission", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("outcomes")
  public List<@Valid BulkSubmissionOutcome> getOutcomes() {
    return outcomes;
  }

  public void setOutcomes(List<@Valid BulkSubmissionOutcome> outcomes) {
    this.outcomes = outcomes;
  }

  public GetBulkSubmission200ResponseDetails matterStarts(List<@Valid BulkSubmissionMatterStart> matterStarts) {
    this.matterStarts = matterStarts;
    return this;
  }

  public GetBulkSubmission200ResponseDetails addMatterStartsItem(BulkSubmissionMatterStart matterStartsItem) {
    if (this.matterStarts == null) {
      this.matterStarts = new ArrayList<>();
    }
    this.matterStarts.add(matterStartsItem);
    return this;
  }

  /**
   * List of matter start objects for the bulk submission
   * @return matterStarts
   */
  @Valid 
  @Schema(name = "matter_starts", description = "List of matter start objects for the bulk submission", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("matter_starts")
  public List<@Valid BulkSubmissionMatterStart> getMatterStarts() {
    return matterStarts;
  }

  public void setMatterStarts(List<@Valid BulkSubmissionMatterStart> matterStarts) {
    this.matterStarts = matterStarts;
  }

  public GetBulkSubmission200ResponseDetails immigrationClr(List<Map<String, String>> immigrationClr) {
    this.immigrationClr = immigrationClr;
    return this;
  }

  public GetBulkSubmission200ResponseDetails addImmigrationClrItem(Map<String, String> immigrationClrItem) {
    if (this.immigrationClr == null) {
      this.immigrationClr = new ArrayList<>();
    }
    this.immigrationClr.add(immigrationClrItem);
    return this;
  }

  /**
   * List of immigration CLR rows captured from the bulk submission
   * @return immigrationClr
   */
  @Valid 
  @Schema(name = "immigration_clr", description = "List of immigration CLR rows captured from the bulk submission", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("immigration_clr")
  public List<Map<String, String>> getImmigrationClr() {
    return immigrationClr;
  }

  public void setImmigrationClr(List<Map<String, String>> immigrationClr) {
    this.immigrationClr = immigrationClr;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetBulkSubmission200ResponseDetails getBulkSubmission200ResponseDetails = (GetBulkSubmission200ResponseDetails) o;
    return Objects.equals(this.office, getBulkSubmission200ResponseDetails.office) &&
        Objects.equals(this.schedule, getBulkSubmission200ResponseDetails.schedule) &&
        Objects.equals(this.outcomes, getBulkSubmission200ResponseDetails.outcomes) &&
        Objects.equals(this.matterStarts, getBulkSubmission200ResponseDetails.matterStarts) &&
        Objects.equals(this.immigrationClr, getBulkSubmission200ResponseDetails.immigrationClr);
  }

  @Override
  public int hashCode() {
    return Objects.hash(office, schedule, outcomes, matterStarts, immigrationClr);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetBulkSubmission200ResponseDetails {\n");
    sb.append("    office: ").append(toIndentedString(office)).append("\n");
    sb.append("    schedule: ").append(toIndentedString(schedule)).append("\n");
    sb.append("    outcomes: ").append(toIndentedString(outcomes)).append("\n");
    sb.append("    matterStarts: ").append(toIndentedString(matterStarts)).append("\n");
    sb.append("    immigrationClr: ").append(toIndentedString(immigrationClr)).append("\n");
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

    private GetBulkSubmission200ResponseDetails instance;

    public Builder() {
      this(new GetBulkSubmission200ResponseDetails());
    }

    protected Builder(GetBulkSubmission200ResponseDetails instance) {
      this.instance = instance;
    }

    protected Builder copyOf(GetBulkSubmission200ResponseDetails value) { 
      this.instance.setOffice(value.office);
      this.instance.setSchedule(value.schedule);
      this.instance.setOutcomes(value.outcomes);
      this.instance.setMatterStarts(value.matterStarts);
      this.instance.setImmigrationClr(value.immigrationClr);
      return this;
    }

    public GetBulkSubmission200ResponseDetails.Builder office(GetBulkSubmission200ResponseDetailsOffice office) {
      this.instance.office(office);
      return this;
    }
    
    public GetBulkSubmission200ResponseDetails.Builder schedule(GetBulkSubmission200ResponseDetailsSchedule schedule) {
      this.instance.schedule(schedule);
      return this;
    }
    
    public GetBulkSubmission200ResponseDetails.Builder outcomes(List<BulkSubmissionOutcome> outcomes) {
      this.instance.outcomes(outcomes);
      return this;
    }
    
    public GetBulkSubmission200ResponseDetails.Builder matterStarts(List<BulkSubmissionMatterStart> matterStarts) {
      this.instance.matterStarts(matterStarts);
      return this;
    }
    
    public GetBulkSubmission200ResponseDetails.Builder immigrationClr(List<Map<String, String>> immigrationClr) {
      this.instance.immigrationClr(immigrationClr);
      return this;
    }
    
    /**
    * returns a built GetBulkSubmission200ResponseDetails instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public GetBulkSubmission200ResponseDetails build() {
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
  public static GetBulkSubmission200ResponseDetails.Builder builder() {
    return new GetBulkSubmission200ResponseDetails.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public GetBulkSubmission200ResponseDetails.Builder toBuilder() {
    GetBulkSubmission200ResponseDetails.Builder builder = new GetBulkSubmission200ResponseDetails.Builder();
    return builder.copyOf(this);
  }

}

