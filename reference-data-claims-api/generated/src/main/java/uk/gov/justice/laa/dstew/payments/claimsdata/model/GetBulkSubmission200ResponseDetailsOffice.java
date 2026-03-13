package uk.gov.justice.laa.dstew.payments.claimsdata.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.springframework.lang.Nullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * The office submitting a claim
 */

@Schema(name = "getBulkSubmission_200_response_details_office", description = "The office submitting a claim")
@JsonTypeName("getBulkSubmission_200_response_details_office")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T21:51:30.932283Z[Europe/London]", comments = "Generator version: 7.14.0")
public class GetBulkSubmission200ResponseDetailsOffice implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable String account;

  public GetBulkSubmission200ResponseDetailsOffice account(@Nullable String account) {
    this.account = account;
    return this;
  }

  /**
   * Get account
   * @return account
   */
  
  @Schema(name = "account", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("account")
  public @Nullable String getAccount() {
    return account;
  }

  public void setAccount(@Nullable String account) {
    this.account = account;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetBulkSubmission200ResponseDetailsOffice getBulkSubmission200ResponseDetailsOffice = (GetBulkSubmission200ResponseDetailsOffice) o;
    return Objects.equals(this.account, getBulkSubmission200ResponseDetailsOffice.account);
  }

  @Override
  public int hashCode() {
    return Objects.hash(account);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetBulkSubmission200ResponseDetailsOffice {\n");
    sb.append("    account: ").append(toIndentedString(account)).append("\n");
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

    private GetBulkSubmission200ResponseDetailsOffice instance;

    public Builder() {
      this(new GetBulkSubmission200ResponseDetailsOffice());
    }

    protected Builder(GetBulkSubmission200ResponseDetailsOffice instance) {
      this.instance = instance;
    }

    protected Builder copyOf(GetBulkSubmission200ResponseDetailsOffice value) { 
      this.instance.setAccount(value.account);
      return this;
    }

    public GetBulkSubmission200ResponseDetailsOffice.Builder account(String account) {
      this.instance.account(account);
      return this;
    }
    
    /**
    * returns a built GetBulkSubmission200ResponseDetailsOffice instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public GetBulkSubmission200ResponseDetailsOffice build() {
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
  public static GetBulkSubmission200ResponseDetailsOffice.Builder builder() {
    return new GetBulkSubmission200ResponseDetailsOffice.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public GetBulkSubmission200ResponseDetailsOffice.Builder toBuilder() {
    GetBulkSubmission200ResponseDetailsOffice.Builder builder = new GetBulkSubmission200ResponseDetailsOffice.Builder();
    return builder.copyOf(this);
  }

}

