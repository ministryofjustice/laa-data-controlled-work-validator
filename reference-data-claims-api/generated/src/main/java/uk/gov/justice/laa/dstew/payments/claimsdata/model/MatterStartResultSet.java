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
import uk.gov.justice.laa.dstew.payments.claimsdata.model.MatterStartGet;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * MatterStartResultSet
 */

@JsonTypeName("matter_start_result_set")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T18:11:17.854857Z[Europe/London]", comments = "Generator version: 7.14.0")
public class MatterStartResultSet implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable UUID submissionId;

  @Valid
  private List<MatterStartGet> matterStarts = new ArrayList<>();

  public MatterStartResultSet submissionId(@Nullable UUID submissionId) {
    this.submissionId = submissionId;
    return this;
  }

  /**
   * UUID for submission.
   * @return submissionId
   */
  @Valid 
  @Schema(name = "submission_id", description = "UUID for submission.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("submission_id")
  public @Nullable UUID getSubmissionId() {
    return submissionId;
  }

  public void setSubmissionId(@Nullable UUID submissionId) {
    this.submissionId = submissionId;
  }

  public MatterStartResultSet matterStarts(List<MatterStartGet> matterStarts) {
    this.matterStarts = matterStarts;
    return this;
  }

  public MatterStartResultSet addMatterStartsItem(MatterStartGet matterStartsItem) {
    if (this.matterStarts == null) {
      this.matterStarts = new ArrayList<>();
    }
    this.matterStarts.add(matterStartsItem);
    return this;
  }

  /**
   * Get matterStarts
   * @return matterStarts
   */
  @Valid 
  @Schema(name = "matter_starts", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("matter_starts")
  public List<MatterStartGet> getMatterStarts() {
    return matterStarts;
  }

  public void setMatterStarts(List<MatterStartGet> matterStarts) {
    this.matterStarts = matterStarts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MatterStartResultSet matterStartResultSet = (MatterStartResultSet) o;
    return Objects.equals(this.submissionId, matterStartResultSet.submissionId) &&
        Objects.equals(this.matterStarts, matterStartResultSet.matterStarts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(submissionId, matterStarts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MatterStartResultSet {\n");
    sb.append("    submissionId: ").append(toIndentedString(submissionId)).append("\n");
    sb.append("    matterStarts: ").append(toIndentedString(matterStarts)).append("\n");
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

    private MatterStartResultSet instance;

    public Builder() {
      this(new MatterStartResultSet());
    }

    protected Builder(MatterStartResultSet instance) {
      this.instance = instance;
    }

    protected Builder copyOf(MatterStartResultSet value) { 
      this.instance.setSubmissionId(value.submissionId);
      this.instance.setMatterStarts(value.matterStarts);
      return this;
    }

    public MatterStartResultSet.Builder submissionId(UUID submissionId) {
      this.instance.submissionId(submissionId);
      return this;
    }
    
    public MatterStartResultSet.Builder matterStarts(List<MatterStartGet> matterStarts) {
      this.instance.matterStarts(matterStarts);
      return this;
    }
    
    /**
    * returns a built MatterStartResultSet instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public MatterStartResultSet build() {
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
  public static MatterStartResultSet.Builder builder() {
    return new MatterStartResultSet.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public MatterStartResultSet.Builder toBuilder() {
    MatterStartResultSet.Builder builder = new MatterStartResultSet.Builder();
    return builder.copyOf(this);
  }

}

