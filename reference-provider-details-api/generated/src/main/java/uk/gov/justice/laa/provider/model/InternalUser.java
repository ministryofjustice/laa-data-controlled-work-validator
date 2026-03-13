package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
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
 * InternalUser
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T14:44:48.222559Z[Europe/London]", comments = "Generator version: 7.14.0")
public class InternalUser implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String username;

  private @Nullable String fullname;

  private @Nullable String jobTitle;

  private @Nullable String emailAddress;

  private @Nullable String lscAreaOffice;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private @Nullable LocalDate enrolmentDate;

  public InternalUser username(@Nullable String username) {
    this.username = username;
    return this;
  }

  /**
   * Get username
   * @return username
   */
  
  @Schema(name = "username", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("username")
  public @Nullable String getUsername() {
    return username;
  }

  public void setUsername(@Nullable String username) {
    this.username = username;
  }

  public InternalUser fullname(@Nullable String fullname) {
    this.fullname = fullname;
    return this;
  }

  /**
   * Get fullname
   * @return fullname
   */
  
  @Schema(name = "fullname", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("fullname")
  public @Nullable String getFullname() {
    return fullname;
  }

  public void setFullname(@Nullable String fullname) {
    this.fullname = fullname;
  }

  public InternalUser jobTitle(@Nullable String jobTitle) {
    this.jobTitle = jobTitle;
    return this;
  }

  /**
   * Get jobTitle
   * @return jobTitle
   */
  
  @Schema(name = "jobTitle", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("jobTitle")
  public @Nullable String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(@Nullable String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public InternalUser emailAddress(@Nullable String emailAddress) {
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

  public InternalUser lscAreaOffice(@Nullable String lscAreaOffice) {
    this.lscAreaOffice = lscAreaOffice;
    return this;
  }

  /**
   * Get lscAreaOffice
   * @return lscAreaOffice
   */
  
  @Schema(name = "lscAreaOffice", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lscAreaOffice")
  public @Nullable String getLscAreaOffice() {
    return lscAreaOffice;
  }

  public void setLscAreaOffice(@Nullable String lscAreaOffice) {
    this.lscAreaOffice = lscAreaOffice;
  }

  public InternalUser enrolmentDate(@Nullable LocalDate enrolmentDate) {
    this.enrolmentDate = enrolmentDate;
    return this;
  }

  /**
   * Get enrolmentDate
   * @return enrolmentDate
   */
  @Valid 
  @Schema(name = "enrolmentDate", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("enrolmentDate")
  public @Nullable LocalDate getEnrolmentDate() {
    return enrolmentDate;
  }

  public void setEnrolmentDate(@Nullable LocalDate enrolmentDate) {
    this.enrolmentDate = enrolmentDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InternalUser internalUser = (InternalUser) o;
    return Objects.equals(this.username, internalUser.username) &&
        Objects.equals(this.fullname, internalUser.fullname) &&
        Objects.equals(this.jobTitle, internalUser.jobTitle) &&
        Objects.equals(this.emailAddress, internalUser.emailAddress) &&
        Objects.equals(this.lscAreaOffice, internalUser.lscAreaOffice) &&
        Objects.equals(this.enrolmentDate, internalUser.enrolmentDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, fullname, jobTitle, emailAddress, lscAreaOffice, enrolmentDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InternalUser {\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    fullname: ").append(toIndentedString(fullname)).append("\n");
    sb.append("    jobTitle: ").append(toIndentedString(jobTitle)).append("\n");
    sb.append("    emailAddress: ").append(toIndentedString(emailAddress)).append("\n");
    sb.append("    lscAreaOffice: ").append(toIndentedString(lscAreaOffice)).append("\n");
    sb.append("    enrolmentDate: ").append(toIndentedString(enrolmentDate)).append("\n");
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

    private InternalUser instance;

    public Builder() {
      this(new InternalUser());
    }

    protected Builder(InternalUser instance) {
      this.instance = instance;
    }

    protected Builder copyOf(InternalUser value) { 
      this.instance.setUsername(value.username);
      this.instance.setFullname(value.fullname);
      this.instance.setJobTitle(value.jobTitle);
      this.instance.setEmailAddress(value.emailAddress);
      this.instance.setLscAreaOffice(value.lscAreaOffice);
      this.instance.setEnrolmentDate(value.enrolmentDate);
      return this;
    }

    public InternalUser.Builder username(String username) {
      this.instance.username(username);
      return this;
    }
    
    public InternalUser.Builder fullname(String fullname) {
      this.instance.fullname(fullname);
      return this;
    }
    
    public InternalUser.Builder jobTitle(String jobTitle) {
      this.instance.jobTitle(jobTitle);
      return this;
    }
    
    public InternalUser.Builder emailAddress(String emailAddress) {
      this.instance.emailAddress(emailAddress);
      return this;
    }
    
    public InternalUser.Builder lscAreaOffice(String lscAreaOffice) {
      this.instance.lscAreaOffice(lscAreaOffice);
      return this;
    }
    
    public InternalUser.Builder enrolmentDate(LocalDate enrolmentDate) {
      this.instance.enrolmentDate(enrolmentDate);
      return this;
    }
    
    /**
    * returns a built InternalUser instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public InternalUser build() {
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
  public static InternalUser.Builder builder() {
    return new InternalUser.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public InternalUser.Builder toBuilder() {
    InternalUser.Builder builder = new InternalUser.Builder();
    return builder.copyOf(this);
  }

}

