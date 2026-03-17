package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * FeeEarner
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-16T20:11:03.761871Z[Europe/London]", comments = "Generator version: 7.14.0")
public class FeeEarner implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String subjectId;

  private @Nullable String firstName;

  private @Nullable String lastName;

  private @Nullable String emailAddress;

  private @Nullable String practitionerType;

  private @Nullable String practitionerNumber;

  private @Nullable String practitionerStartDate;

  private @Nullable String practitionerEndDate;

  private @Nullable String department;

  @Valid
  private List<String> firmOfficeCodes = new ArrayList<>();

  public FeeEarner subjectId(@Nullable String subjectId) {
    this.subjectId = subjectId;
    return this;
  }

  /**
   * Get subjectId
   * @return subjectId
   */
  
  @Schema(name = "subjectId", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("subjectId")
  public @Nullable String getSubjectId() {
    return subjectId;
  }

  public void setSubjectId(@Nullable String subjectId) {
    this.subjectId = subjectId;
  }

  public FeeEarner firstName(@Nullable String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   * @return firstName
   */
  
  @Schema(name = "firstName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("firstName")
  public @Nullable String getFirstName() {
    return firstName;
  }

  public void setFirstName(@Nullable String firstName) {
    this.firstName = firstName;
  }

  public FeeEarner lastName(@Nullable String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   * @return lastName
   */
  
  @Schema(name = "lastName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lastName")
  public @Nullable String getLastName() {
    return lastName;
  }

  public void setLastName(@Nullable String lastName) {
    this.lastName = lastName;
  }

  public FeeEarner emailAddress(@Nullable String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

  /**
   * Get emailAddress
   * @return emailAddress
   */
  
  @Schema(name = "emailAddress", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("emailAddress")
  public @Nullable String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(@Nullable String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public FeeEarner practitionerType(@Nullable String practitionerType) {
    this.practitionerType = practitionerType;
    return this;
  }

  /**
   * Get practitionerType
   * @return practitionerType
   */
  
  @Schema(name = "practitionerType", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("practitionerType")
  public @Nullable String getPractitionerType() {
    return practitionerType;
  }

  public void setPractitionerType(@Nullable String practitionerType) {
    this.practitionerType = practitionerType;
  }

  public FeeEarner practitionerNumber(@Nullable String practitionerNumber) {
    this.practitionerNumber = practitionerNumber;
    return this;
  }

  /**
   * Get practitionerNumber
   * @return practitionerNumber
   */
  
  @Schema(name = "practitionerNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("practitionerNumber")
  public @Nullable String getPractitionerNumber() {
    return practitionerNumber;
  }

  public void setPractitionerNumber(@Nullable String practitionerNumber) {
    this.practitionerNumber = practitionerNumber;
  }

  public FeeEarner practitionerStartDate(@Nullable String practitionerStartDate) {
    this.practitionerStartDate = practitionerStartDate;
    return this;
  }

  /**
   * Get practitionerStartDate
   * @return practitionerStartDate
   */
  
  @Schema(name = "practitionerStartDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("practitionerStartDate")
  public @Nullable String getPractitionerStartDate() {
    return practitionerStartDate;
  }

  public void setPractitionerStartDate(@Nullable String practitionerStartDate) {
    this.practitionerStartDate = practitionerStartDate;
  }

  public FeeEarner practitionerEndDate(@Nullable String practitionerEndDate) {
    this.practitionerEndDate = practitionerEndDate;
    return this;
  }

  /**
   * Get practitionerEndDate
   * @return practitionerEndDate
   */
  
  @Schema(name = "practitionerEndDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("practitionerEndDate")
  public @Nullable String getPractitionerEndDate() {
    return practitionerEndDate;
  }

  public void setPractitionerEndDate(@Nullable String practitionerEndDate) {
    this.practitionerEndDate = practitionerEndDate;
  }

  public FeeEarner department(@Nullable String department) {
    this.department = department;
    return this;
  }

  /**
   * Get department
   * @return department
   */
  
  @Schema(name = "department", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("department")
  public @Nullable String getDepartment() {
    return department;
  }

  public void setDepartment(@Nullable String department) {
    this.department = department;
  }

  public FeeEarner firmOfficeCodes(List<String> firmOfficeCodes) {
    this.firmOfficeCodes = firmOfficeCodes;
    return this;
  }

  public FeeEarner addFirmOfficeCodesItem(String firmOfficeCodesItem) {
    if (this.firmOfficeCodes == null) {
      this.firmOfficeCodes = new ArrayList<>();
    }
    this.firmOfficeCodes.add(firmOfficeCodesItem);
    return this;
  }

  /**
   * Get firmOfficeCodes
   * @return firmOfficeCodes
   */
  
  @Schema(name = "firmOfficeCodes", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("firmOfficeCodes")
  public List<String> getFirmOfficeCodes() {
    return firmOfficeCodes;
  }

  public void setFirmOfficeCodes(List<String> firmOfficeCodes) {
    this.firmOfficeCodes = firmOfficeCodes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FeeEarner feeEarner = (FeeEarner) o;
    return Objects.equals(this.subjectId, feeEarner.subjectId) &&
        Objects.equals(this.firstName, feeEarner.firstName) &&
        Objects.equals(this.lastName, feeEarner.lastName) &&
        Objects.equals(this.emailAddress, feeEarner.emailAddress) &&
        Objects.equals(this.practitionerType, feeEarner.practitionerType) &&
        Objects.equals(this.practitionerNumber, feeEarner.practitionerNumber) &&
        Objects.equals(this.practitionerStartDate, feeEarner.practitionerStartDate) &&
        Objects.equals(this.practitionerEndDate, feeEarner.practitionerEndDate) &&
        Objects.equals(this.department, feeEarner.department) &&
        Objects.equals(this.firmOfficeCodes, feeEarner.firmOfficeCodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subjectId, firstName, lastName, emailAddress, practitionerType, practitionerNumber, practitionerStartDate, practitionerEndDate, department, firmOfficeCodes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FeeEarner {\n");
    sb.append("    subjectId: ").append(toIndentedString(subjectId)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    emailAddress: ").append(toIndentedString(emailAddress)).append("\n");
    sb.append("    practitionerType: ").append(toIndentedString(practitionerType)).append("\n");
    sb.append("    practitionerNumber: ").append(toIndentedString(practitionerNumber)).append("\n");
    sb.append("    practitionerStartDate: ").append(toIndentedString(practitionerStartDate)).append("\n");
    sb.append("    practitionerEndDate: ").append(toIndentedString(practitionerEndDate)).append("\n");
    sb.append("    department: ").append(toIndentedString(department)).append("\n");
    sb.append("    firmOfficeCodes: ").append(toIndentedString(firmOfficeCodes)).append("\n");
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

    private FeeEarner instance;

    public Builder() {
      this(new FeeEarner());
    }

    protected Builder(FeeEarner instance) {
      this.instance = instance;
    }

    protected Builder copyOf(FeeEarner value) { 
      this.instance.setSubjectId(value.subjectId);
      this.instance.setFirstName(value.firstName);
      this.instance.setLastName(value.lastName);
      this.instance.setEmailAddress(value.emailAddress);
      this.instance.setPractitionerType(value.practitionerType);
      this.instance.setPractitionerNumber(value.practitionerNumber);
      this.instance.setPractitionerStartDate(value.practitionerStartDate);
      this.instance.setPractitionerEndDate(value.practitionerEndDate);
      this.instance.setDepartment(value.department);
      this.instance.setFirmOfficeCodes(value.firmOfficeCodes);
      return this;
    }

    public FeeEarner.Builder subjectId(String subjectId) {
      this.instance.subjectId(subjectId);
      return this;
    }
    
    public FeeEarner.Builder firstName(String firstName) {
      this.instance.firstName(firstName);
      return this;
    }
    
    public FeeEarner.Builder lastName(String lastName) {
      this.instance.lastName(lastName);
      return this;
    }
    
    public FeeEarner.Builder emailAddress(String emailAddress) {
      this.instance.emailAddress(emailAddress);
      return this;
    }
    
    public FeeEarner.Builder practitionerType(String practitionerType) {
      this.instance.practitionerType(practitionerType);
      return this;
    }
    
    public FeeEarner.Builder practitionerNumber(String practitionerNumber) {
      this.instance.practitionerNumber(practitionerNumber);
      return this;
    }
    
    public FeeEarner.Builder practitionerStartDate(String practitionerStartDate) {
      this.instance.practitionerStartDate(practitionerStartDate);
      return this;
    }
    
    public FeeEarner.Builder practitionerEndDate(String practitionerEndDate) {
      this.instance.practitionerEndDate(practitionerEndDate);
      return this;
    }
    
    public FeeEarner.Builder department(String department) {
      this.instance.department(department);
      return this;
    }
    
    public FeeEarner.Builder firmOfficeCodes(List<String> firmOfficeCodes) {
      this.instance.firmOfficeCodes(firmOfficeCodes);
      return this;
    }
    
    /**
    * returns a built FeeEarner instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public FeeEarner build() {
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
  public static FeeEarner.Builder builder() {
    return new FeeEarner.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public FeeEarner.Builder toBuilder() {
    FeeEarner.Builder builder = new FeeEarner.Builder();
    return builder.copyOf(this);
  }

}

