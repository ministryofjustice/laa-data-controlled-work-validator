package uk.gov.justice.laa.dstew.payments.claimsdata.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.UUID;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.BulkSubmissionErrorCode;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.BulkSubmissionStatus;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.GetBulkSubmission200ResponseDetails;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * The JSON object representing the &#x60;bulk_submission&#x60; stored in the corresponding table with bulk submission id and status
 */

@Schema(name = "getBulkSubmission_200_response", description = "The JSON object representing the `bulk_submission` stored in the corresponding table with bulk submission id and status")
@JsonTypeName("getBulkSubmission_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T21:51:30.932283Z[Europe/London]", comments = "Generator version: 7.14.0")
public class GetBulkSubmission200Response implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable UUID bulkSubmissionId;

  private @Nullable BulkSubmissionStatus status;

  private @Nullable String createdByUserId;

  private @Nullable BulkSubmissionErrorCode errorCode;

  private @Nullable String errorDescription;

  private @Nullable String updatedByUserId;

  private @Nullable GetBulkSubmission200ResponseDetails details;

  public GetBulkSubmission200Response bulkSubmissionId(@Nullable UUID bulkSubmissionId) {
    this.bulkSubmissionId = bulkSubmissionId;
    return this;
  }

  /**
   * UUID of the bulk submission.
   * @return bulkSubmissionId
   */
  @Valid 
  @Schema(name = "bulk_submission_id", description = "UUID of the bulk submission.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bulk_submission_id")
  public @Nullable UUID getBulkSubmissionId() {
    return bulkSubmissionId;
  }

  public void setBulkSubmissionId(@Nullable UUID bulkSubmissionId) {
    this.bulkSubmissionId = bulkSubmissionId;
  }

  public GetBulkSubmission200Response status(@Nullable BulkSubmissionStatus status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
   */
  @Valid 
  @Schema(name = "status", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("status")
  public @Nullable BulkSubmissionStatus getStatus() {
    return status;
  }

  public void setStatus(@Nullable BulkSubmissionStatus status) {
    this.status = status;
  }

  public GetBulkSubmission200Response createdByUserId(@Nullable String createdByUserId) {
    this.createdByUserId = createdByUserId;
    return this;
  }

  /**
   * The user ID of the user who created the bulk submission.
   * @return createdByUserId
   */
  
  @Schema(name = "created_by_user_id", description = "The user ID of the user who created the bulk submission.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("created_by_user_id")
  public @Nullable String getCreatedByUserId() {
    return createdByUserId;
  }

  public void setCreatedByUserId(@Nullable String createdByUserId) {
    this.createdByUserId = createdByUserId;
  }

  public GetBulkSubmission200Response errorCode(@Nullable BulkSubmissionErrorCode errorCode) {
    this.errorCode = errorCode;
    return this;
  }

  /**
   * Get errorCode
   * @return errorCode
   */
  @Valid 
  @Schema(name = "error_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("error_code")
  public @Nullable BulkSubmissionErrorCode getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(@Nullable BulkSubmissionErrorCode errorCode) {
    this.errorCode = errorCode;
  }

  public GetBulkSubmission200Response errorDescription(@Nullable String errorDescription) {
    this.errorDescription = errorDescription;
    return this;
  }

  /**
   * Get errorDescription
   * @return errorDescription
   */
  
  @Schema(name = "error_description", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("error_description")
  public @Nullable String getErrorDescription() {
    return errorDescription;
  }

  public void setErrorDescription(@Nullable String errorDescription) {
    this.errorDescription = errorDescription;
  }

  public GetBulkSubmission200Response updatedByUserId(@Nullable String updatedByUserId) {
    this.updatedByUserId = updatedByUserId;
    return this;
  }

  /**
   * Get updatedByUserId
   * @return updatedByUserId
   */
  
  @Schema(name = "updated_by_user_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("updated_by_user_id")
  public @Nullable String getUpdatedByUserId() {
    return updatedByUserId;
  }

  public void setUpdatedByUserId(@Nullable String updatedByUserId) {
    this.updatedByUserId = updatedByUserId;
  }

  public GetBulkSubmission200Response details(@Nullable GetBulkSubmission200ResponseDetails details) {
    this.details = details;
    return this;
  }

  /**
   * Get details
   * @return details
   */
  @Valid 
  @Schema(name = "details", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("details")
  public @Nullable GetBulkSubmission200ResponseDetails getDetails() {
    return details;
  }

  public void setDetails(@Nullable GetBulkSubmission200ResponseDetails details) {
    this.details = details;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetBulkSubmission200Response getBulkSubmission200Response = (GetBulkSubmission200Response) o;
    return Objects.equals(this.bulkSubmissionId, getBulkSubmission200Response.bulkSubmissionId) &&
        Objects.equals(this.status, getBulkSubmission200Response.status) &&
        Objects.equals(this.createdByUserId, getBulkSubmission200Response.createdByUserId) &&
        Objects.equals(this.errorCode, getBulkSubmission200Response.errorCode) &&
        Objects.equals(this.errorDescription, getBulkSubmission200Response.errorDescription) &&
        Objects.equals(this.updatedByUserId, getBulkSubmission200Response.updatedByUserId) &&
        Objects.equals(this.details, getBulkSubmission200Response.details);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bulkSubmissionId, status, createdByUserId, errorCode, errorDescription, updatedByUserId, details);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetBulkSubmission200Response {\n");
    sb.append("    bulkSubmissionId: ").append(toIndentedString(bulkSubmissionId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    createdByUserId: ").append(toIndentedString(createdByUserId)).append("\n");
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("    errorDescription: ").append(toIndentedString(errorDescription)).append("\n");
    sb.append("    updatedByUserId: ").append(toIndentedString(updatedByUserId)).append("\n");
    sb.append("    details: ").append(toIndentedString(details)).append("\n");
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

    private GetBulkSubmission200Response instance;

    public Builder() {
      this(new GetBulkSubmission200Response());
    }

    protected Builder(GetBulkSubmission200Response instance) {
      this.instance = instance;
    }

    protected Builder copyOf(GetBulkSubmission200Response value) { 
      this.instance.setBulkSubmissionId(value.bulkSubmissionId);
      this.instance.setStatus(value.status);
      this.instance.setCreatedByUserId(value.createdByUserId);
      this.instance.setErrorCode(value.errorCode);
      this.instance.setErrorDescription(value.errorDescription);
      this.instance.setUpdatedByUserId(value.updatedByUserId);
      this.instance.setDetails(value.details);
      return this;
    }

    public GetBulkSubmission200Response.Builder bulkSubmissionId(UUID bulkSubmissionId) {
      this.instance.bulkSubmissionId(bulkSubmissionId);
      return this;
    }
    
    public GetBulkSubmission200Response.Builder status(BulkSubmissionStatus status) {
      this.instance.status(status);
      return this;
    }
    
    public GetBulkSubmission200Response.Builder createdByUserId(String createdByUserId) {
      this.instance.createdByUserId(createdByUserId);
      return this;
    }
    
    public GetBulkSubmission200Response.Builder errorCode(BulkSubmissionErrorCode errorCode) {
      this.instance.errorCode(errorCode);
      return this;
    }
    
    public GetBulkSubmission200Response.Builder errorDescription(String errorDescription) {
      this.instance.errorDescription(errorDescription);
      return this;
    }
    
    public GetBulkSubmission200Response.Builder updatedByUserId(String updatedByUserId) {
      this.instance.updatedByUserId(updatedByUserId);
      return this;
    }
    
    public GetBulkSubmission200Response.Builder details(GetBulkSubmission200ResponseDetails details) {
      this.instance.details(details);
      return this;
    }
    
    /**
    * returns a built GetBulkSubmission200Response instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public GetBulkSubmission200Response build() {
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
  public static GetBulkSubmission200Response.Builder builder() {
    return new GetBulkSubmission200Response.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public GetBulkSubmission200Response.Builder toBuilder() {
    GetBulkSubmission200Response.Builder builder = new GetBulkSubmission200Response.Builder();
    return builder.copyOf(this);
  }

}

