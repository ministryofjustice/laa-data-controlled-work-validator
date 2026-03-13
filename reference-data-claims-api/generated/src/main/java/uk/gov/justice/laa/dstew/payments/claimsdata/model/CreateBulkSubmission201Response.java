package uk.gov.justice.laa.dstew.payments.claimsdata.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.lang.Nullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Bulk Submission details
 */

@Schema(name = "createBulkSubmission_201_response", description = "Bulk Submission details")
@JsonTypeName("createBulkSubmission_201_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T18:11:17.854857Z[Europe/London]", comments = "Generator version: 7.14.0")
public class CreateBulkSubmission201Response implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable UUID bulkSubmissionId;

  @Valid
  private List<UUID> submissionIds = new ArrayList<>();

  public CreateBulkSubmission201Response bulkSubmissionId(@Nullable UUID bulkSubmissionId) {
    this.bulkSubmissionId = bulkSubmissionId;
    return this;
  }

  /**
   * UUID of the created bulk submission record.
   * @return bulkSubmissionId
   */
  @Valid 
  @Schema(name = "bulk_submission_id", description = "UUID of the created bulk submission record.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bulk_submission_id")
  public @Nullable UUID getBulkSubmissionId() {
    return bulkSubmissionId;
  }

  public void setBulkSubmissionId(@Nullable UUID bulkSubmissionId) {
    this.bulkSubmissionId = bulkSubmissionId;
  }

  public CreateBulkSubmission201Response submissionIds(List<UUID> submissionIds) {
    this.submissionIds = submissionIds;
    return this;
  }

  public CreateBulkSubmission201Response addSubmissionIdsItem(UUID submissionIdsItem) {
    if (this.submissionIds == null) {
      this.submissionIds = new ArrayList<>();
    }
    this.submissionIds.add(submissionIdsItem);
    return this;
  }

  /**
   * list of UUIDs for the future submission records (only one submission per bulk expected), will have to be sent back in a later call.
   * @return submissionIds
   */
  @Valid 
  @Schema(name = "submission_ids", description = "list of UUIDs for the future submission records (only one submission per bulk expected), will have to be sent back in a later call.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("submission_ids")
  public List<UUID> getSubmissionIds() {
    return submissionIds;
  }

  public void setSubmissionIds(List<UUID> submissionIds) {
    this.submissionIds = submissionIds;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateBulkSubmission201Response createBulkSubmission201Response = (CreateBulkSubmission201Response) o;
    return Objects.equals(this.bulkSubmissionId, createBulkSubmission201Response.bulkSubmissionId) &&
        Objects.equals(this.submissionIds, createBulkSubmission201Response.submissionIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bulkSubmissionId, submissionIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateBulkSubmission201Response {\n");
    sb.append("    bulkSubmissionId: ").append(toIndentedString(bulkSubmissionId)).append("\n");
    sb.append("    submissionIds: ").append(toIndentedString(submissionIds)).append("\n");
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

    private CreateBulkSubmission201Response instance;

    public Builder() {
      this(new CreateBulkSubmission201Response());
    }

    protected Builder(CreateBulkSubmission201Response instance) {
      this.instance = instance;
    }

    protected Builder copyOf(CreateBulkSubmission201Response value) { 
      this.instance.setBulkSubmissionId(value.bulkSubmissionId);
      this.instance.setSubmissionIds(value.submissionIds);
      return this;
    }

    public CreateBulkSubmission201Response.Builder bulkSubmissionId(UUID bulkSubmissionId) {
      this.instance.bulkSubmissionId(bulkSubmissionId);
      return this;
    }
    
    public CreateBulkSubmission201Response.Builder submissionIds(List<UUID> submissionIds) {
      this.instance.submissionIds(submissionIds);
      return this;
    }
    
    /**
    * returns a built CreateBulkSubmission201Response instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public CreateBulkSubmission201Response build() {
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
  public static CreateBulkSubmission201Response.Builder builder() {
    return new CreateBulkSubmission201Response.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public CreateBulkSubmission201Response.Builder toBuilder() {
    CreateBulkSubmission201Response.Builder builder = new CreateBulkSubmission201Response.Builder();
    return builder.copyOf(this);
  }

}

