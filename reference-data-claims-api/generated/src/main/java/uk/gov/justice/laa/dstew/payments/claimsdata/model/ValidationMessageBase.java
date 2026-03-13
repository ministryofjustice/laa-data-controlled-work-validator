package uk.gov.justice.laa.dstew.payments.claimsdata.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.UUID;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ValidationMessageType;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Validation message
 */

@Schema(name = "validation_message_base", description = "Validation message")
@JsonTypeName("validation_message_base")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T14:44:45.032142Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ValidationMessageBase implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable UUID id;

  private @Nullable UUID submissionId;

  private @Nullable UUID claimId;

  private @Nullable ValidationMessageType type;

  private @Nullable String source;

  private @Nullable String displayMessage;

  public ValidationMessageBase id(@Nullable UUID id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   */
  @Valid 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public @Nullable UUID getId() {
    return id;
  }

  public void setId(@Nullable UUID id) {
    this.id = id;
  }

  public ValidationMessageBase submissionId(@Nullable UUID submissionId) {
    this.submissionId = submissionId;
    return this;
  }

  /**
   * Get submissionId
   * @return submissionId
   */
  @Valid 
  @Schema(name = "submission_id", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("submission_id")
  public @Nullable UUID getSubmissionId() {
    return submissionId;
  }

  public void setSubmissionId(@Nullable UUID submissionId) {
    this.submissionId = submissionId;
  }

  public ValidationMessageBase claimId(@Nullable UUID claimId) {
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

  public ValidationMessageBase type(@Nullable ValidationMessageType type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   */
  @Valid 
  @Schema(name = "type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("type")
  public @Nullable ValidationMessageType getType() {
    return type;
  }

  public void setType(@Nullable ValidationMessageType type) {
    this.type = type;
  }

  public ValidationMessageBase source(@Nullable String source) {
    this.source = source;
    return this;
  }

  /**
   * Get source
   * @return source
   */
  
  @Schema(name = "source", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("source")
  public @Nullable String getSource() {
    return source;
  }

  public void setSource(@Nullable String source) {
    this.source = source;
  }

  public ValidationMessageBase displayMessage(@Nullable String displayMessage) {
    this.displayMessage = displayMessage;
    return this;
  }

  /**
   * Get displayMessage
   * @return displayMessage
   */
  
  @Schema(name = "display_message", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("display_message")
  public @Nullable String getDisplayMessage() {
    return displayMessage;
  }

  public void setDisplayMessage(@Nullable String displayMessage) {
    this.displayMessage = displayMessage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ValidationMessageBase validationMessageBase = (ValidationMessageBase) o;
    return Objects.equals(this.id, validationMessageBase.id) &&
        Objects.equals(this.submissionId, validationMessageBase.submissionId) &&
        Objects.equals(this.claimId, validationMessageBase.claimId) &&
        Objects.equals(this.type, validationMessageBase.type) &&
        Objects.equals(this.source, validationMessageBase.source) &&
        Objects.equals(this.displayMessage, validationMessageBase.displayMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, submissionId, claimId, type, source, displayMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ValidationMessageBase {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    submissionId: ").append(toIndentedString(submissionId)).append("\n");
    sb.append("    claimId: ").append(toIndentedString(claimId)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    source: ").append(toIndentedString(source)).append("\n");
    sb.append("    displayMessage: ").append(toIndentedString(displayMessage)).append("\n");
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

    private ValidationMessageBase instance;

    public Builder() {
      this(new ValidationMessageBase());
    }

    protected Builder(ValidationMessageBase instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ValidationMessageBase value) { 
      this.instance.setId(value.id);
      this.instance.setSubmissionId(value.submissionId);
      this.instance.setClaimId(value.claimId);
      this.instance.setType(value.type);
      this.instance.setSource(value.source);
      this.instance.setDisplayMessage(value.displayMessage);
      return this;
    }

    public ValidationMessageBase.Builder id(UUID id) {
      this.instance.id(id);
      return this;
    }
    
    public ValidationMessageBase.Builder submissionId(UUID submissionId) {
      this.instance.submissionId(submissionId);
      return this;
    }
    
    public ValidationMessageBase.Builder claimId(UUID claimId) {
      this.instance.claimId(claimId);
      return this;
    }
    
    public ValidationMessageBase.Builder type(ValidationMessageType type) {
      this.instance.type(type);
      return this;
    }
    
    public ValidationMessageBase.Builder source(String source) {
      this.instance.source(source);
      return this;
    }
    
    public ValidationMessageBase.Builder displayMessage(String displayMessage) {
      this.instance.displayMessage(displayMessage);
      return this;
    }
    
    /**
    * returns a built ValidationMessageBase instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ValidationMessageBase build() {
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
  public static ValidationMessageBase.Builder builder() {
    return new ValidationMessageBase.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ValidationMessageBase.Builder toBuilder() {
    ValidationMessageBase.Builder builder = new ValidationMessageBase.Builder();
    return builder.copyOf(this);
  }

}

