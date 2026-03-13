package uk.gov.justice.laa.dstew.payments.claimsdata.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.CategoryCode;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.MediationType;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * MatterStartPost
 */

@JsonTypeName("matter_start_post")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T21:51:30.932283Z[Europe/London]", comments = "Generator version: 7.14.0")
public class MatterStartPost implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String scheduleReference;

  private @Nullable CategoryCode categoryCode;

  private @Nullable String procurementAreaCode;

  private @Nullable String accessPointCode;

  private @Nullable String deliveryLocation;

  private @Nullable MediationType mediationType;

  private @Nullable Integer numberOfMatterStarts;

  private @Nullable String createdByUserId;

  public MatterStartPost scheduleReference(@Nullable String scheduleReference) {
    this.scheduleReference = scheduleReference;
    return this;
  }

  /**
   * Get scheduleReference
   * @return scheduleReference
   */
  
  @Schema(name = "schedule_reference", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("schedule_reference")
  public @Nullable String getScheduleReference() {
    return scheduleReference;
  }

  public void setScheduleReference(@Nullable String scheduleReference) {
    this.scheduleReference = scheduleReference;
  }

  public MatterStartPost categoryCode(@Nullable CategoryCode categoryCode) {
    this.categoryCode = categoryCode;
    return this;
  }

  /**
   * Get categoryCode
   * @return categoryCode
   */
  @Valid 
  @Schema(name = "category_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("category_code")
  public @Nullable CategoryCode getCategoryCode() {
    return categoryCode;
  }

  public void setCategoryCode(@Nullable CategoryCode categoryCode) {
    this.categoryCode = categoryCode;
  }

  public MatterStartPost procurementAreaCode(@Nullable String procurementAreaCode) {
    this.procurementAreaCode = procurementAreaCode;
    return this;
  }

  /**
   * Get procurementAreaCode
   * @return procurementAreaCode
   */
  
  @Schema(name = "procurement_area_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("procurement_area_code")
  public @Nullable String getProcurementAreaCode() {
    return procurementAreaCode;
  }

  public void setProcurementAreaCode(@Nullable String procurementAreaCode) {
    this.procurementAreaCode = procurementAreaCode;
  }

  public MatterStartPost accessPointCode(@Nullable String accessPointCode) {
    this.accessPointCode = accessPointCode;
    return this;
  }

  /**
   * Get accessPointCode
   * @return accessPointCode
   */
  
  @Schema(name = "access_point_code", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("access_point_code")
  public @Nullable String getAccessPointCode() {
    return accessPointCode;
  }

  public void setAccessPointCode(@Nullable String accessPointCode) {
    this.accessPointCode = accessPointCode;
  }

  public MatterStartPost deliveryLocation(@Nullable String deliveryLocation) {
    this.deliveryLocation = deliveryLocation;
    return this;
  }

  /**
   * Get deliveryLocation
   * @return deliveryLocation
   */
  
  @Schema(name = "delivery_location", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("delivery_location")
  public @Nullable String getDeliveryLocation() {
    return deliveryLocation;
  }

  public void setDeliveryLocation(@Nullable String deliveryLocation) {
    this.deliveryLocation = deliveryLocation;
  }

  public MatterStartPost mediationType(@Nullable MediationType mediationType) {
    this.mediationType = mediationType;
    return this;
  }

  /**
   * Get mediationType
   * @return mediationType
   */
  @Valid 
  @Schema(name = "mediation_type", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mediation_type")
  public @Nullable MediationType getMediationType() {
    return mediationType;
  }

  public void setMediationType(@Nullable MediationType mediationType) {
    this.mediationType = mediationType;
  }

  public MatterStartPost numberOfMatterStarts(@Nullable Integer numberOfMatterStarts) {
    this.numberOfMatterStarts = numberOfMatterStarts;
    return this;
  }

  /**
   * Get numberOfMatterStarts
   * @return numberOfMatterStarts
   */
  
  @Schema(name = "number_of_matter_starts", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("number_of_matter_starts")
  public @Nullable Integer getNumberOfMatterStarts() {
    return numberOfMatterStarts;
  }

  public void setNumberOfMatterStarts(@Nullable Integer numberOfMatterStarts) {
    this.numberOfMatterStarts = numberOfMatterStarts;
  }

  public MatterStartPost createdByUserId(@Nullable String createdByUserId) {
    this.createdByUserId = createdByUserId;
    return this;
  }

  /**
   * The id of the user who created the matter start.
   * @return createdByUserId
   */
  
  @Schema(name = "created_by_user_id", description = "The id of the user who created the matter start.", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("created_by_user_id")
  public @Nullable String getCreatedByUserId() {
    return createdByUserId;
  }

  public void setCreatedByUserId(@Nullable String createdByUserId) {
    this.createdByUserId = createdByUserId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MatterStartPost matterStartPost = (MatterStartPost) o;
    return Objects.equals(this.scheduleReference, matterStartPost.scheduleReference) &&
        Objects.equals(this.categoryCode, matterStartPost.categoryCode) &&
        Objects.equals(this.procurementAreaCode, matterStartPost.procurementAreaCode) &&
        Objects.equals(this.accessPointCode, matterStartPost.accessPointCode) &&
        Objects.equals(this.deliveryLocation, matterStartPost.deliveryLocation) &&
        Objects.equals(this.mediationType, matterStartPost.mediationType) &&
        Objects.equals(this.numberOfMatterStarts, matterStartPost.numberOfMatterStarts) &&
        Objects.equals(this.createdByUserId, matterStartPost.createdByUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scheduleReference, categoryCode, procurementAreaCode, accessPointCode, deliveryLocation, mediationType, numberOfMatterStarts, createdByUserId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MatterStartPost {\n");
    sb.append("    scheduleReference: ").append(toIndentedString(scheduleReference)).append("\n");
    sb.append("    categoryCode: ").append(toIndentedString(categoryCode)).append("\n");
    sb.append("    procurementAreaCode: ").append(toIndentedString(procurementAreaCode)).append("\n");
    sb.append("    accessPointCode: ").append(toIndentedString(accessPointCode)).append("\n");
    sb.append("    deliveryLocation: ").append(toIndentedString(deliveryLocation)).append("\n");
    sb.append("    mediationType: ").append(toIndentedString(mediationType)).append("\n");
    sb.append("    numberOfMatterStarts: ").append(toIndentedString(numberOfMatterStarts)).append("\n");
    sb.append("    createdByUserId: ").append(toIndentedString(createdByUserId)).append("\n");
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

    private MatterStartPost instance;

    public Builder() {
      this(new MatterStartPost());
    }

    protected Builder(MatterStartPost instance) {
      this.instance = instance;
    }

    protected Builder copyOf(MatterStartPost value) { 
      this.instance.setScheduleReference(value.scheduleReference);
      this.instance.setCategoryCode(value.categoryCode);
      this.instance.setProcurementAreaCode(value.procurementAreaCode);
      this.instance.setAccessPointCode(value.accessPointCode);
      this.instance.setDeliveryLocation(value.deliveryLocation);
      this.instance.setMediationType(value.mediationType);
      this.instance.setNumberOfMatterStarts(value.numberOfMatterStarts);
      this.instance.setCreatedByUserId(value.createdByUserId);
      return this;
    }

    public MatterStartPost.Builder scheduleReference(String scheduleReference) {
      this.instance.scheduleReference(scheduleReference);
      return this;
    }
    
    public MatterStartPost.Builder categoryCode(CategoryCode categoryCode) {
      this.instance.categoryCode(categoryCode);
      return this;
    }
    
    public MatterStartPost.Builder procurementAreaCode(String procurementAreaCode) {
      this.instance.procurementAreaCode(procurementAreaCode);
      return this;
    }
    
    public MatterStartPost.Builder accessPointCode(String accessPointCode) {
      this.instance.accessPointCode(accessPointCode);
      return this;
    }
    
    public MatterStartPost.Builder deliveryLocation(String deliveryLocation) {
      this.instance.deliveryLocation(deliveryLocation);
      return this;
    }
    
    public MatterStartPost.Builder mediationType(MediationType mediationType) {
      this.instance.mediationType(mediationType);
      return this;
    }
    
    public MatterStartPost.Builder numberOfMatterStarts(Integer numberOfMatterStarts) {
      this.instance.numberOfMatterStarts(numberOfMatterStarts);
      return this;
    }
    
    public MatterStartPost.Builder createdByUserId(String createdByUserId) {
      this.instance.createdByUserId(createdByUserId);
      return this;
    }
    
    /**
    * returns a built MatterStartPost instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public MatterStartPost build() {
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
  public static MatterStartPost.Builder builder() {
    return new MatterStartPost.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public MatterStartPost.Builder toBuilder() {
    MatterStartPost.Builder builder = new MatterStartPost.Builder();
    return builder.copyOf(this);
  }

}

