package uk.gov.justice.laa.dstew.payments.claimsdata.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.UUID;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Claim UUID and status
 */

@Schema(name = "submission_claim", description = "Claim UUID and status")
@JsonTypeName("submission_claim")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T15:47:02.322440Z[Europe/London]", comments = "Generator version: 7.14.0")
public class SubmissionClaim implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable UUID claimId;

  private @Nullable ClaimStatus status;

  public SubmissionClaim claimId(@Nullable UUID claimId) {
    this.claimId = claimId;
    return this;
  }

  /**
   * Get claimId
   * @return claimId
   */
  @Valid 
  @Schema(name = "claim_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("claim_id")
  public @Nullable UUID getClaimId() {
    return claimId;
  }

  public void setClaimId(@Nullable UUID claimId) {
    this.claimId = claimId;
  }

  public SubmissionClaim status(@Nullable ClaimStatus status) {
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
  public @Nullable ClaimStatus getStatus() {
    return status;
  }

  public void setStatus(@Nullable ClaimStatus status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SubmissionClaim submissionClaim = (SubmissionClaim) o;
    return Objects.equals(this.claimId, submissionClaim.claimId) &&
        Objects.equals(this.status, submissionClaim.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(claimId, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubmissionClaim {\n");
    sb.append("    claimId: ").append(toIndentedString(claimId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
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

    private SubmissionClaim instance;

    public Builder() {
      this(new SubmissionClaim());
    }

    protected Builder(SubmissionClaim instance) {
      this.instance = instance;
    }

    protected Builder copyOf(SubmissionClaim value) { 
      this.instance.setClaimId(value.claimId);
      this.instance.setStatus(value.status);
      return this;
    }

    public SubmissionClaim.Builder claimId(UUID claimId) {
      this.instance.claimId(claimId);
      return this;
    }
    
    public SubmissionClaim.Builder status(ClaimStatus status) {
      this.instance.status(status);
      return this;
    }
    
    /**
    * returns a built SubmissionClaim instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public SubmissionClaim build() {
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
  public static SubmissionClaim.Builder builder() {
    return new SubmissionClaim.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public SubmissionClaim.Builder toBuilder() {
    SubmissionClaim.Builder builder = new SubmissionClaim.Builder();
    return builder.copyOf(this);
  }

}

