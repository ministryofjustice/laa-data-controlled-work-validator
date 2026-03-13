package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.provider.model.ProviderFirmOfficeBankAccountDetails;
import uk.gov.justice.laa.provider.model.ProviderFirmOfficeSummary;
import uk.gov.justice.laa.provider.model.ProviderFirmSummary;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ProviderFirmOfficeBankAccountDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T15:47:03.890223Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmOfficeBankAccountDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable ProviderFirmSummary firm;

  private @Nullable ProviderFirmOfficeSummary office;

  private @Nullable ProviderFirmOfficeBankAccountDetails accountDetails;

  public ProviderFirmOfficeBankAccountDto firm(@Nullable ProviderFirmSummary firm) {
    this.firm = firm;
    return this;
  }

  /**
   * Get firm
   * @return firm
   */
  @Valid 
  @Schema(name = "firm", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("firm")
  public @Nullable ProviderFirmSummary getFirm() {
    return firm;
  }

  public void setFirm(@Nullable ProviderFirmSummary firm) {
    this.firm = firm;
  }

  public ProviderFirmOfficeBankAccountDto office(@Nullable ProviderFirmOfficeSummary office) {
    this.office = office;
    return this;
  }

  /**
   * Get office
   * @return office
   */
  @Valid 
  @Schema(name = "office", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("office")
  public @Nullable ProviderFirmOfficeSummary getOffice() {
    return office;
  }

  public void setOffice(@Nullable ProviderFirmOfficeSummary office) {
    this.office = office;
  }

  public ProviderFirmOfficeBankAccountDto accountDetails(@Nullable ProviderFirmOfficeBankAccountDetails accountDetails) {
    this.accountDetails = accountDetails;
    return this;
  }

  /**
   * Get accountDetails
   * @return accountDetails
   */
  @Valid 
  @Schema(name = "accountDetails", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("accountDetails")
  public @Nullable ProviderFirmOfficeBankAccountDetails getAccountDetails() {
    return accountDetails;
  }

  public void setAccountDetails(@Nullable ProviderFirmOfficeBankAccountDetails accountDetails) {
    this.accountDetails = accountDetails;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirmOfficeBankAccountDto providerFirmOfficeBankAccountDto = (ProviderFirmOfficeBankAccountDto) o;
    return Objects.equals(this.firm, providerFirmOfficeBankAccountDto.firm) &&
        Objects.equals(this.office, providerFirmOfficeBankAccountDto.office) &&
        Objects.equals(this.accountDetails, providerFirmOfficeBankAccountDto.accountDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firm, office, accountDetails);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmOfficeBankAccountDto {\n");
    sb.append("    firm: ").append(toIndentedString(firm)).append("\n");
    sb.append("    office: ").append(toIndentedString(office)).append("\n");
    sb.append("    accountDetails: ").append(toIndentedString(accountDetails)).append("\n");
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

    private ProviderFirmOfficeBankAccountDto instance;

    public Builder() {
      this(new ProviderFirmOfficeBankAccountDto());
    }

    protected Builder(ProviderFirmOfficeBankAccountDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmOfficeBankAccountDto value) { 
      this.instance.setFirm(value.firm);
      this.instance.setOffice(value.office);
      this.instance.setAccountDetails(value.accountDetails);
      return this;
    }

    public ProviderFirmOfficeBankAccountDto.Builder firm(ProviderFirmSummary firm) {
      this.instance.firm(firm);
      return this;
    }
    
    public ProviderFirmOfficeBankAccountDto.Builder office(ProviderFirmOfficeSummary office) {
      this.instance.office(office);
      return this;
    }
    
    public ProviderFirmOfficeBankAccountDto.Builder accountDetails(ProviderFirmOfficeBankAccountDetails accountDetails) {
      this.instance.accountDetails(accountDetails);
      return this;
    }
    
    /**
    * returns a built ProviderFirmOfficeBankAccountDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmOfficeBankAccountDto build() {
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
  public static ProviderFirmOfficeBankAccountDto.Builder builder() {
    return new ProviderFirmOfficeBankAccountDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmOfficeBankAccountDto.Builder toBuilder() {
    ProviderFirmOfficeBankAccountDto.Builder builder = new ProviderFirmOfficeBankAccountDto.Builder();
    return builder.copyOf(this);
  }

}

