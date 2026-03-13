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
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * bulk submission patch details
 */

@Schema(name = "bulk_submission_patch", description = "bulk submission patch details")
@JsonTypeName("bulk_submission_patch")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T18:11:17.854857Z[Europe/London]", comments = "Generator version: 7.14.0")
public class BulkSubmissionPatch implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable UUID bulkSubmissionId;

  private @Nullable BulkSubmissionStatus status;

  private @Nullable BulkSubmissionErrorCode errorCode;

  private @Nullable String errorDescription;

  private @Nullable String updatedByUserId;

  public BulkSubmissionPatch bulkSubmissionId(@Nullable UUID bulkSubmissionId) {
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

  public BulkSubmissionPatch status(@Nullable BulkSubmissionStatus status) {
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

  public BulkSubmissionPatch errorCode(@Nullable BulkSubmissionErrorCode errorCode) {
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

  public BulkSubmissionPatch errorDescription(@Nullable String errorDescription) {
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

  public BulkSubmissionPatch updatedByUserId(@Nullable String updatedByUserId) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BulkSubmissionPatch bulkSubmissionPatch = (BulkSubmissionPatch) o;
    return Objects.equals(this.bulkSubmissionId, bulkSubmissionPatch.bulkSubmissionId) &&
        Objects.equals(this.status, bulkSubmissionPatch.status) &&
        Objects.equals(this.errorCode, bulkSubmissionPatch.errorCode) &&
        Objects.equals(this.errorDescription, bulkSubmissionPatch.errorDescription) &&
        Objects.equals(this.updatedByUserId, bulkSubmissionPatch.updatedByUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bulkSubmissionId, status, errorCode, errorDescription, updatedByUserId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BulkSubmissionPatch {\n");
    sb.append("    bulkSubmissionId: ").append(toIndentedString(bulkSubmissionId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("    errorDescription: ").append(toIndentedString(errorDescription)).append("\n");
    sb.append("    updatedByUserId: ").append(toIndentedString(updatedByUserId)).append("\n");
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

    private BulkSubmissionPatch instance;

    public Builder() {
      this(new BulkSubmissionPatch());
    }

    protected Builder(BulkSubmissionPatch instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BulkSubmissionPatch value) { 
      this.instance.setBulkSubmissionId(value.bulkSubmissionId);
      this.instance.setStatus(value.status);
      this.instance.setErrorCode(value.errorCode);
      this.instance.setErrorDescription(value.errorDescription);
      this.instance.setUpdatedByUserId(value.updatedByUserId);
      return this;
    }

    public BulkSubmissionPatch.Builder bulkSubmissionId(UUID bulkSubmissionId) {
      this.instance.bulkSubmissionId(bulkSubmissionId);
      return this;
    }
    
    public BulkSubmissionPatch.Builder status(BulkSubmissionStatus status) {
      this.instance.status(status);
      return this;
    }
    
    public BulkSubmissionPatch.Builder errorCode(BulkSubmissionErrorCode errorCode) {
      this.instance.errorCode(errorCode);
      return this;
    }
    
    public BulkSubmissionPatch.Builder errorDescription(String errorDescription) {
      this.instance.errorDescription(errorDescription);
      return this;
    }
    
    public BulkSubmissionPatch.Builder updatedByUserId(String updatedByUserId) {
      this.instance.updatedByUserId(updatedByUserId);
      return this;
    }
    
    /**
    * returns a built BulkSubmissionPatch instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BulkSubmissionPatch build() {
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
  public static BulkSubmissionPatch.Builder builder() {
    return new BulkSubmissionPatch.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BulkSubmissionPatch.Builder toBuilder() {
    BulkSubmissionPatch.Builder builder = new BulkSubmissionPatch.Builder();
    return builder.copyOf(this);
  }

}

