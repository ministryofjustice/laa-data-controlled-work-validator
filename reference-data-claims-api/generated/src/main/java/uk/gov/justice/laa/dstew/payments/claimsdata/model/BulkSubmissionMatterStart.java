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
 * matter start details for a bulk submission
 */

@Schema(name = "bulk_submission_matter_start", description = "matter start details for a bulk submission")
@JsonTypeName("bulk_submission_matter_start")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T18:11:17.854857Z[Europe/London]", comments = "Generator version: 7.14.0")
public class BulkSubmissionMatterStart implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String scheduleRef;

  private @Nullable CategoryCode categoryCode;

  private @Nullable MediationType mediationType;

  private @Nullable String procurementArea;

  private @Nullable String accessPoint;

  private @Nullable String deliveryLocation;

  private @Nullable Integer numberOfMatterStarts;

  public BulkSubmissionMatterStart scheduleRef(@Nullable String scheduleRef) {
    this.scheduleRef = scheduleRef;
    return this;
  }

  /**
   * Get scheduleRef
   * @return scheduleRef
   */
  
  @Schema(name = "schedule_ref", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("schedule_ref")
  public @Nullable String getScheduleRef() {
    return scheduleRef;
  }

  public void setScheduleRef(@Nullable String scheduleRef) {
    this.scheduleRef = scheduleRef;
  }

  public BulkSubmissionMatterStart categoryCode(@Nullable CategoryCode categoryCode) {
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

  public BulkSubmissionMatterStart mediationType(@Nullable MediationType mediationType) {
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

  public BulkSubmissionMatterStart procurementArea(@Nullable String procurementArea) {
    this.procurementArea = procurementArea;
    return this;
  }

  /**
   * Get procurementArea
   * @return procurementArea
   */
  
  @Schema(name = "procurement_area", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("procurement_area")
  public @Nullable String getProcurementArea() {
    return procurementArea;
  }

  public void setProcurementArea(@Nullable String procurementArea) {
    this.procurementArea = procurementArea;
  }

  public BulkSubmissionMatterStart accessPoint(@Nullable String accessPoint) {
    this.accessPoint = accessPoint;
    return this;
  }

  /**
   * Get accessPoint
   * @return accessPoint
   */
  
  @Schema(name = "access_point", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("access_point")
  public @Nullable String getAccessPoint() {
    return accessPoint;
  }

  public void setAccessPoint(@Nullable String accessPoint) {
    this.accessPoint = accessPoint;
  }

  public BulkSubmissionMatterStart deliveryLocation(@Nullable String deliveryLocation) {
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

  public BulkSubmissionMatterStart numberOfMatterStarts(@Nullable Integer numberOfMatterStarts) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BulkSubmissionMatterStart bulkSubmissionMatterStart = (BulkSubmissionMatterStart) o;
    return Objects.equals(this.scheduleRef, bulkSubmissionMatterStart.scheduleRef) &&
        Objects.equals(this.categoryCode, bulkSubmissionMatterStart.categoryCode) &&
        Objects.equals(this.mediationType, bulkSubmissionMatterStart.mediationType) &&
        Objects.equals(this.procurementArea, bulkSubmissionMatterStart.procurementArea) &&
        Objects.equals(this.accessPoint, bulkSubmissionMatterStart.accessPoint) &&
        Objects.equals(this.deliveryLocation, bulkSubmissionMatterStart.deliveryLocation) &&
        Objects.equals(this.numberOfMatterStarts, bulkSubmissionMatterStart.numberOfMatterStarts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scheduleRef, categoryCode, mediationType, procurementArea, accessPoint, deliveryLocation, numberOfMatterStarts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BulkSubmissionMatterStart {\n");
    sb.append("    scheduleRef: ").append(toIndentedString(scheduleRef)).append("\n");
    sb.append("    categoryCode: ").append(toIndentedString(categoryCode)).append("\n");
    sb.append("    mediationType: ").append(toIndentedString(mediationType)).append("\n");
    sb.append("    procurementArea: ").append(toIndentedString(procurementArea)).append("\n");
    sb.append("    accessPoint: ").append(toIndentedString(accessPoint)).append("\n");
    sb.append("    deliveryLocation: ").append(toIndentedString(deliveryLocation)).append("\n");
    sb.append("    numberOfMatterStarts: ").append(toIndentedString(numberOfMatterStarts)).append("\n");
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

    private BulkSubmissionMatterStart instance;

    public Builder() {
      this(new BulkSubmissionMatterStart());
    }

    protected Builder(BulkSubmissionMatterStart instance) {
      this.instance = instance;
    }

    protected Builder copyOf(BulkSubmissionMatterStart value) { 
      this.instance.setScheduleRef(value.scheduleRef);
      this.instance.setCategoryCode(value.categoryCode);
      this.instance.setMediationType(value.mediationType);
      this.instance.setProcurementArea(value.procurementArea);
      this.instance.setAccessPoint(value.accessPoint);
      this.instance.setDeliveryLocation(value.deliveryLocation);
      this.instance.setNumberOfMatterStarts(value.numberOfMatterStarts);
      return this;
    }

    public BulkSubmissionMatterStart.Builder scheduleRef(String scheduleRef) {
      this.instance.scheduleRef(scheduleRef);
      return this;
    }
    
    public BulkSubmissionMatterStart.Builder categoryCode(CategoryCode categoryCode) {
      this.instance.categoryCode(categoryCode);
      return this;
    }
    
    public BulkSubmissionMatterStart.Builder mediationType(MediationType mediationType) {
      this.instance.mediationType(mediationType);
      return this;
    }
    
    public BulkSubmissionMatterStart.Builder procurementArea(String procurementArea) {
      this.instance.procurementArea(procurementArea);
      return this;
    }
    
    public BulkSubmissionMatterStart.Builder accessPoint(String accessPoint) {
      this.instance.accessPoint(accessPoint);
      return this;
    }
    
    public BulkSubmissionMatterStart.Builder deliveryLocation(String deliveryLocation) {
      this.instance.deliveryLocation(deliveryLocation);
      return this;
    }
    
    public BulkSubmissionMatterStart.Builder numberOfMatterStarts(Integer numberOfMatterStarts) {
      this.instance.numberOfMatterStarts(numberOfMatterStarts);
      return this;
    }
    
    /**
    * returns a built BulkSubmissionMatterStart instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public BulkSubmissionMatterStart build() {
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
  public static BulkSubmissionMatterStart.Builder builder() {
    return new BulkSubmissionMatterStart.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public BulkSubmissionMatterStart.Builder toBuilder() {
    BulkSubmissionMatterStart.Builder builder = new BulkSubmissionMatterStart.Builder();
    return builder.copyOf(this);
  }

}

