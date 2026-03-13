package uk.gov.justice.laa.dstew.payments.claimsdata.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionClaim;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionStatus;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * SubmissionResponse
 */

@JsonTypeName("submission_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T18:11:17.854857Z[Europe/London]", comments = "Generator version: 7.14.0")
public class SubmissionResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable UUID submissionId;

  private @Nullable UUID bulkSubmissionId;

  private @Nullable String officeAccountNumber;

  private @Nullable String submissionPeriod;

  private @Nullable AreaOfLaw areaOfLaw;

  private @Nullable String providerUserId;

  private @Nullable SubmissionStatus status;

  private @Nullable String crimeLowerScheduleNumber;

  private @Nullable String legalHelpSubmissionReference;

  private @Nullable String mediationSubmissionReference;

  private @Nullable UUID previousSubmissionId;

  private @Nullable Boolean isNilSubmission;

  private @Nullable Integer numberOfClaims;

  private @Nullable BigDecimal calculatedTotalAmount;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private @Nullable OffsetDateTime submitted;

  private @Nullable String createdByUserId;

  private @Nullable String errorMessages;

  @Valid
  private List<@Valid SubmissionClaim> claims = new ArrayList<>();

  @Valid
  private List<UUID> matterStarts = new ArrayList<>();

  public SubmissionResponse submissionId(@Nullable UUID submissionId) {
    this.submissionId = submissionId;
    return this;
  }

  /**
   * UUID for submission that was returned when bulk_submission was created.
   * @return submissionId
   */
  @Valid 
  @Schema(name = "submission_id", description = "UUID for submission that was returned when bulk_submission was created.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("submission_id")
  public @Nullable UUID getSubmissionId() {
    return submissionId;
  }

  public void setSubmissionId(@Nullable UUID submissionId) {
    this.submissionId = submissionId;
  }

  public SubmissionResponse bulkSubmissionId(@Nullable UUID bulkSubmissionId) {
    this.bulkSubmissionId = bulkSubmissionId;
    return this;
  }

  /**
   * UUID of the associated bulk submission.
   * @return bulkSubmissionId
   */
  @Valid 
  @Schema(name = "bulk_submission_id", description = "UUID of the associated bulk submission.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("bulk_submission_id")
  public @Nullable UUID getBulkSubmissionId() {
    return bulkSubmissionId;
  }

  public void setBulkSubmissionId(@Nullable UUID bulkSubmissionId) {
    this.bulkSubmissionId = bulkSubmissionId;
  }

  public SubmissionResponse officeAccountNumber(@Nullable String officeAccountNumber) {
    this.officeAccountNumber = officeAccountNumber;
    return this;
  }

  /**
   * The office account number.
   * @return officeAccountNumber
   */
  
  @Schema(name = "office_account_number", description = "The office account number.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("office_account_number")
  public @Nullable String getOfficeAccountNumber() {
    return officeAccountNumber;
  }

  public void setOfficeAccountNumber(@Nullable String officeAccountNumber) {
    this.officeAccountNumber = officeAccountNumber;
  }

  public SubmissionResponse submissionPeriod(@Nullable String submissionPeriod) {
    this.submissionPeriod = submissionPeriod;
    return this;
  }

  /**
   * Submission period (e.g., \"JUL-2025\").
   * @return submissionPeriod
   */
  
  @Schema(name = "submission_period", description = "Submission period (e.g., \"JUL-2025\").", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("submission_period")
  public @Nullable String getSubmissionPeriod() {
    return submissionPeriod;
  }

  public void setSubmissionPeriod(@Nullable String submissionPeriod) {
    this.submissionPeriod = submissionPeriod;
  }

  public SubmissionResponse areaOfLaw(@Nullable AreaOfLaw areaOfLaw) {
    this.areaOfLaw = areaOfLaw;
    return this;
  }

  /**
   * Get areaOfLaw
   * @return areaOfLaw
   */
  @Valid 
  @Schema(name = "area_of_law", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("area_of_law")
  public @Nullable AreaOfLaw getAreaOfLaw() {
    return areaOfLaw;
  }

  public void setAreaOfLaw(@Nullable AreaOfLaw areaOfLaw) {
    this.areaOfLaw = areaOfLaw;
  }

  public SubmissionResponse providerUserId(@Nullable String providerUserId) {
    this.providerUserId = providerUserId;
    return this;
  }

  /**
   * The id of the provider user who created this submission (copied from bulk submission).
   * @return providerUserId
   */
  
  @Schema(name = "provider_user_id", description = "The id of the provider user who created this submission (copied from bulk submission).", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("provider_user_id")
  public @Nullable String getProviderUserId() {
    return providerUserId;
  }

  public void setProviderUserId(@Nullable String providerUserId) {
    this.providerUserId = providerUserId;
  }

  public SubmissionResponse status(@Nullable SubmissionStatus status) {
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
  public @Nullable SubmissionStatus getStatus() {
    return status;
  }

  public void setStatus(@Nullable SubmissionStatus status) {
    this.status = status;
  }

  public SubmissionResponse crimeLowerScheduleNumber(@Nullable String crimeLowerScheduleNumber) {
    this.crimeLowerScheduleNumber = crimeLowerScheduleNumber;
    return this;
  }

  /**
   * Optional crime lower schedule number.
   * @return crimeLowerScheduleNumber
   */
  
  @Schema(name = "crime_lower_schedule_number", description = "Optional crime lower schedule number.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("crime_lower_schedule_number")
  public @Nullable String getCrimeLowerScheduleNumber() {
    return crimeLowerScheduleNumber;
  }

  public void setCrimeLowerScheduleNumber(@Nullable String crimeLowerScheduleNumber) {
    this.crimeLowerScheduleNumber = crimeLowerScheduleNumber;
  }

  public SubmissionResponse legalHelpSubmissionReference(@Nullable String legalHelpSubmissionReference) {
    this.legalHelpSubmissionReference = legalHelpSubmissionReference;
    return this;
  }

  /**
   * Optional legal help submission reference.
   * @return legalHelpSubmissionReference
   */
  
  @Schema(name = "legal_help_submission_reference", description = "Optional legal help submission reference.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("legal_help_submission_reference")
  public @Nullable String getLegalHelpSubmissionReference() {
    return legalHelpSubmissionReference;
  }

  public void setLegalHelpSubmissionReference(@Nullable String legalHelpSubmissionReference) {
    this.legalHelpSubmissionReference = legalHelpSubmissionReference;
  }

  public SubmissionResponse mediationSubmissionReference(@Nullable String mediationSubmissionReference) {
    this.mediationSubmissionReference = mediationSubmissionReference;
    return this;
  }

  /**
   * Optional mediation submission reference.
   * @return mediationSubmissionReference
   */
  
  @Schema(name = "mediation_submission_reference", description = "Optional mediation submission reference.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mediation_submission_reference")
  public @Nullable String getMediationSubmissionReference() {
    return mediationSubmissionReference;
  }

  public void setMediationSubmissionReference(@Nullable String mediationSubmissionReference) {
    this.mediationSubmissionReference = mediationSubmissionReference;
  }

  public SubmissionResponse previousSubmissionId(@Nullable UUID previousSubmissionId) {
    this.previousSubmissionId = previousSubmissionId;
    return this;
  }

  /**
   * UUID of the previous submission that this replaces, if any.
   * @return previousSubmissionId
   */
  @Valid 
  @Schema(name = "previous_submission_id", description = "UUID of the previous submission that this replaces, if any.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("previous_submission_id")
  public @Nullable UUID getPreviousSubmissionId() {
    return previousSubmissionId;
  }

  public void setPreviousSubmissionId(@Nullable UUID previousSubmissionId) {
    this.previousSubmissionId = previousSubmissionId;
  }

  public SubmissionResponse isNilSubmission(@Nullable Boolean isNilSubmission) {
    this.isNilSubmission = isNilSubmission;
    return this;
  }

  /**
   * Whether the submission is a nil submission, i.e. a submission without any claims.
   * @return isNilSubmission
   */
  
  @Schema(name = "is_nil_submission", description = "Whether the submission is a nil submission, i.e. a submission without any claims.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("is_nil_submission")
  public @Nullable Boolean getIsNilSubmission() {
    return isNilSubmission;
  }

  public void setIsNilSubmission(@Nullable Boolean isNilSubmission) {
    this.isNilSubmission = isNilSubmission;
  }

  public SubmissionResponse numberOfClaims(@Nullable Integer numberOfClaims) {
    this.numberOfClaims = numberOfClaims;
    return this;
  }

  /**
   * Number of claims in this submission.
   * @return numberOfClaims
   */
  
  @Schema(name = "number_of_claims", description = "Number of claims in this submission.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("number_of_claims")
  public @Nullable Integer getNumberOfClaims() {
    return numberOfClaims;
  }

  public void setNumberOfClaims(@Nullable Integer numberOfClaims) {
    this.numberOfClaims = numberOfClaims;
  }

  public SubmissionResponse calculatedTotalAmount(@Nullable BigDecimal calculatedTotalAmount) {
    this.calculatedTotalAmount = calculatedTotalAmount;
    return this;
  }

  /**
   * Total amount calculated from the fee details of each claim.
   * @return calculatedTotalAmount
   */
  @Valid 
  @Schema(name = "calculated_total_amount", description = "Total amount calculated from the fee details of each claim.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("calculated_total_amount")
  public @Nullable BigDecimal getCalculatedTotalAmount() {
    return calculatedTotalAmount;
  }

  public void setCalculatedTotalAmount(@Nullable BigDecimal calculatedTotalAmount) {
    this.calculatedTotalAmount = calculatedTotalAmount;
  }

  public SubmissionResponse submitted(@Nullable OffsetDateTime submitted) {
    this.submitted = submitted;
    return this;
  }

  /**
   * Date and time the submission was submitted
   * @return submitted
   */
  @Valid 
  @Schema(name = "submitted", description = "Date and time the submission was submitted", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("submitted")
  public @Nullable OffsetDateTime getSubmitted() {
    return submitted;
  }

  public void setSubmitted(@Nullable OffsetDateTime submitted) {
    this.submitted = submitted;
  }

  public SubmissionResponse createdByUserId(@Nullable String createdByUserId) {
    this.createdByUserId = createdByUserId;
    return this;
  }

  /**
   * The id of the user who created the submission.
   * @return createdByUserId
   */
  
  @Schema(name = "created_by_user_id", description = "The id of the user who created the submission.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("created_by_user_id")
  public @Nullable String getCreatedByUserId() {
    return createdByUserId;
  }

  public void setCreatedByUserId(@Nullable String createdByUserId) {
    this.createdByUserId = createdByUserId;
  }

  public SubmissionResponse errorMessages(@Nullable String errorMessages) {
    this.errorMessages = errorMessages;
    return this;
  }

  /**
   * Error messages associated with this submission.
   * @return errorMessages
   */
  
  @Schema(name = "error_messages", description = "Error messages associated with this submission.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("error_messages")
  public @Nullable String getErrorMessages() {
    return errorMessages;
  }

  public void setErrorMessages(@Nullable String errorMessages) {
    this.errorMessages = errorMessages;
  }

  public SubmissionResponse claims(List<@Valid SubmissionClaim> claims) {
    this.claims = claims;
    return this;
  }

  public SubmissionResponse addClaimsItem(SubmissionClaim claimsItem) {
    if (this.claims == null) {
      this.claims = new ArrayList<>();
    }
    this.claims.add(claimsItem);
    return this;
  }

  /**
   * List of claims (ID + status) for this submission
   * @return claims
   */
  @Valid 
  @Schema(name = "claims", description = "List of claims (ID + status) for this submission", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("claims")
  public List<@Valid SubmissionClaim> getClaims() {
    return claims;
  }

  public void setClaims(List<@Valid SubmissionClaim> claims) {
    this.claims = claims;
  }

  public SubmissionResponse matterStarts(List<UUID> matterStarts) {
    this.matterStarts = matterStarts;
    return this;
  }

  public SubmissionResponse addMatterStartsItem(UUID matterStartsItem) {
    if (this.matterStarts == null) {
      this.matterStarts = new ArrayList<>();
    }
    this.matterStarts.add(matterStartsItem);
    return this;
  }

  /**
   * List of matter start IDs for this submission
   * @return matterStarts
   */
  @Valid 
  @Schema(name = "matter_starts", description = "List of matter start IDs for this submission", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("matter_starts")
  public List<UUID> getMatterStarts() {
    return matterStarts;
  }

  public void setMatterStarts(List<UUID> matterStarts) {
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
    SubmissionResponse submissionResponse = (SubmissionResponse) o;
    return Objects.equals(this.submissionId, submissionResponse.submissionId) &&
        Objects.equals(this.bulkSubmissionId, submissionResponse.bulkSubmissionId) &&
        Objects.equals(this.officeAccountNumber, submissionResponse.officeAccountNumber) &&
        Objects.equals(this.submissionPeriod, submissionResponse.submissionPeriod) &&
        Objects.equals(this.areaOfLaw, submissionResponse.areaOfLaw) &&
        Objects.equals(this.providerUserId, submissionResponse.providerUserId) &&
        Objects.equals(this.status, submissionResponse.status) &&
        Objects.equals(this.crimeLowerScheduleNumber, submissionResponse.crimeLowerScheduleNumber) &&
        Objects.equals(this.legalHelpSubmissionReference, submissionResponse.legalHelpSubmissionReference) &&
        Objects.equals(this.mediationSubmissionReference, submissionResponse.mediationSubmissionReference) &&
        Objects.equals(this.previousSubmissionId, submissionResponse.previousSubmissionId) &&
        Objects.equals(this.isNilSubmission, submissionResponse.isNilSubmission) &&
        Objects.equals(this.numberOfClaims, submissionResponse.numberOfClaims) &&
        Objects.equals(this.calculatedTotalAmount, submissionResponse.calculatedTotalAmount) &&
        Objects.equals(this.submitted, submissionResponse.submitted) &&
        Objects.equals(this.createdByUserId, submissionResponse.createdByUserId) &&
        Objects.equals(this.errorMessages, submissionResponse.errorMessages) &&
        Objects.equals(this.claims, submissionResponse.claims) &&
        Objects.equals(this.matterStarts, submissionResponse.matterStarts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(submissionId, bulkSubmissionId, officeAccountNumber, submissionPeriod, areaOfLaw, providerUserId, status, crimeLowerScheduleNumber, legalHelpSubmissionReference, mediationSubmissionReference, previousSubmissionId, isNilSubmission, numberOfClaims, calculatedTotalAmount, submitted, createdByUserId, errorMessages, claims, matterStarts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SubmissionResponse {\n");
    sb.append("    submissionId: ").append(toIndentedString(submissionId)).append("\n");
    sb.append("    bulkSubmissionId: ").append(toIndentedString(bulkSubmissionId)).append("\n");
    sb.append("    officeAccountNumber: ").append(toIndentedString(officeAccountNumber)).append("\n");
    sb.append("    submissionPeriod: ").append(toIndentedString(submissionPeriod)).append("\n");
    sb.append("    areaOfLaw: ").append(toIndentedString(areaOfLaw)).append("\n");
    sb.append("    providerUserId: ").append(toIndentedString(providerUserId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    crimeLowerScheduleNumber: ").append(toIndentedString(crimeLowerScheduleNumber)).append("\n");
    sb.append("    legalHelpSubmissionReference: ").append(toIndentedString(legalHelpSubmissionReference)).append("\n");
    sb.append("    mediationSubmissionReference: ").append(toIndentedString(mediationSubmissionReference)).append("\n");
    sb.append("    previousSubmissionId: ").append(toIndentedString(previousSubmissionId)).append("\n");
    sb.append("    isNilSubmission: ").append(toIndentedString(isNilSubmission)).append("\n");
    sb.append("    numberOfClaims: ").append(toIndentedString(numberOfClaims)).append("\n");
    sb.append("    calculatedTotalAmount: ").append(toIndentedString(calculatedTotalAmount)).append("\n");
    sb.append("    submitted: ").append(toIndentedString(submitted)).append("\n");
    sb.append("    createdByUserId: ").append(toIndentedString(createdByUserId)).append("\n");
    sb.append("    errorMessages: ").append(toIndentedString(errorMessages)).append("\n");
    sb.append("    claims: ").append(toIndentedString(claims)).append("\n");
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

    private SubmissionResponse instance;

    public Builder() {
      this(new SubmissionResponse());
    }

    protected Builder(SubmissionResponse instance) {
      this.instance = instance;
    }

    protected Builder copyOf(SubmissionResponse value) { 
      this.instance.setSubmissionId(value.submissionId);
      this.instance.setBulkSubmissionId(value.bulkSubmissionId);
      this.instance.setOfficeAccountNumber(value.officeAccountNumber);
      this.instance.setSubmissionPeriod(value.submissionPeriod);
      this.instance.setAreaOfLaw(value.areaOfLaw);
      this.instance.setProviderUserId(value.providerUserId);
      this.instance.setStatus(value.status);
      this.instance.setCrimeLowerScheduleNumber(value.crimeLowerScheduleNumber);
      this.instance.setLegalHelpSubmissionReference(value.legalHelpSubmissionReference);
      this.instance.setMediationSubmissionReference(value.mediationSubmissionReference);
      this.instance.setPreviousSubmissionId(value.previousSubmissionId);
      this.instance.setIsNilSubmission(value.isNilSubmission);
      this.instance.setNumberOfClaims(value.numberOfClaims);
      this.instance.setCalculatedTotalAmount(value.calculatedTotalAmount);
      this.instance.setSubmitted(value.submitted);
      this.instance.setCreatedByUserId(value.createdByUserId);
      this.instance.setErrorMessages(value.errorMessages);
      this.instance.setClaims(value.claims);
      this.instance.setMatterStarts(value.matterStarts);
      return this;
    }

    public SubmissionResponse.Builder submissionId(UUID submissionId) {
      this.instance.submissionId(submissionId);
      return this;
    }
    
    public SubmissionResponse.Builder bulkSubmissionId(UUID bulkSubmissionId) {
      this.instance.bulkSubmissionId(bulkSubmissionId);
      return this;
    }
    
    public SubmissionResponse.Builder officeAccountNumber(String officeAccountNumber) {
      this.instance.officeAccountNumber(officeAccountNumber);
      return this;
    }
    
    public SubmissionResponse.Builder submissionPeriod(String submissionPeriod) {
      this.instance.submissionPeriod(submissionPeriod);
      return this;
    }
    
    public SubmissionResponse.Builder areaOfLaw(AreaOfLaw areaOfLaw) {
      this.instance.areaOfLaw(areaOfLaw);
      return this;
    }
    
    public SubmissionResponse.Builder providerUserId(String providerUserId) {
      this.instance.providerUserId(providerUserId);
      return this;
    }
    
    public SubmissionResponse.Builder status(SubmissionStatus status) {
      this.instance.status(status);
      return this;
    }
    
    public SubmissionResponse.Builder crimeLowerScheduleNumber(String crimeLowerScheduleNumber) {
      this.instance.crimeLowerScheduleNumber(crimeLowerScheduleNumber);
      return this;
    }
    
    public SubmissionResponse.Builder legalHelpSubmissionReference(String legalHelpSubmissionReference) {
      this.instance.legalHelpSubmissionReference(legalHelpSubmissionReference);
      return this;
    }
    
    public SubmissionResponse.Builder mediationSubmissionReference(String mediationSubmissionReference) {
      this.instance.mediationSubmissionReference(mediationSubmissionReference);
      return this;
    }
    
    public SubmissionResponse.Builder previousSubmissionId(UUID previousSubmissionId) {
      this.instance.previousSubmissionId(previousSubmissionId);
      return this;
    }
    
    public SubmissionResponse.Builder isNilSubmission(Boolean isNilSubmission) {
      this.instance.isNilSubmission(isNilSubmission);
      return this;
    }
    
    public SubmissionResponse.Builder numberOfClaims(Integer numberOfClaims) {
      this.instance.numberOfClaims(numberOfClaims);
      return this;
    }
    
    public SubmissionResponse.Builder calculatedTotalAmount(BigDecimal calculatedTotalAmount) {
      this.instance.calculatedTotalAmount(calculatedTotalAmount);
      return this;
    }
    
    public SubmissionResponse.Builder submitted(OffsetDateTime submitted) {
      this.instance.submitted(submitted);
      return this;
    }
    
    public SubmissionResponse.Builder createdByUserId(String createdByUserId) {
      this.instance.createdByUserId(createdByUserId);
      return this;
    }
    
    public SubmissionResponse.Builder errorMessages(String errorMessages) {
      this.instance.errorMessages(errorMessages);
      return this;
    }
    
    public SubmissionResponse.Builder claims(List<SubmissionClaim> claims) {
      this.instance.claims(claims);
      return this;
    }
    
    public SubmissionResponse.Builder matterStarts(List<UUID> matterStarts) {
      this.instance.matterStarts(matterStarts);
      return this;
    }
    
    /**
    * returns a built SubmissionResponse instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public SubmissionResponse build() {
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
  public static SubmissionResponse.Builder builder() {
    return new SubmissionResponse.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public SubmissionResponse.Builder toBuilder() {
    SubmissionResponse.Builder builder = new SubmissionResponse.Builder();
    return builder.copyOf(this);
  }

}

