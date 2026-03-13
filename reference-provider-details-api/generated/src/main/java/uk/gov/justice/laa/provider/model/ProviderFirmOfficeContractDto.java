package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.provider.model.FirmOfficeContractDetails;
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
 * ProviderFirmOfficeContractDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T14:44:48.222559Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmOfficeContractDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable ProviderFirmSummary firm;

  private @Nullable ProviderFirmOfficeSummary office;

  @Valid
  private List<@Valid FirmOfficeContractDetails> contracts = new ArrayList<>();

  public ProviderFirmOfficeContractDto firm(@Nullable ProviderFirmSummary firm) {
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

  public ProviderFirmOfficeContractDto office(@Nullable ProviderFirmOfficeSummary office) {
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

  public ProviderFirmOfficeContractDto contracts(List<@Valid FirmOfficeContractDetails> contracts) {
    this.contracts = contracts;
    return this;
  }

  public ProviderFirmOfficeContractDto addContractsItem(FirmOfficeContractDetails contractsItem) {
    if (this.contracts == null) {
      this.contracts = new ArrayList<>();
    }
    this.contracts.add(contractsItem);
    return this;
  }

  /**
   * Get contracts
   * @return contracts
   */
  @Valid 
  @Schema(name = "contracts", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("contracts")
  public List<@Valid FirmOfficeContractDetails> getContracts() {
    return contracts;
  }

  public void setContracts(List<@Valid FirmOfficeContractDetails> contracts) {
    this.contracts = contracts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirmOfficeContractDto providerFirmOfficeContractDto = (ProviderFirmOfficeContractDto) o;
    return Objects.equals(this.firm, providerFirmOfficeContractDto.firm) &&
        Objects.equals(this.office, providerFirmOfficeContractDto.office) &&
        Objects.equals(this.contracts, providerFirmOfficeContractDto.contracts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firm, office, contracts);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmOfficeContractDto {\n");
    sb.append("    firm: ").append(toIndentedString(firm)).append("\n");
    sb.append("    office: ").append(toIndentedString(office)).append("\n");
    sb.append("    contracts: ").append(toIndentedString(contracts)).append("\n");
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

    private ProviderFirmOfficeContractDto instance;

    public Builder() {
      this(new ProviderFirmOfficeContractDto());
    }

    protected Builder(ProviderFirmOfficeContractDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmOfficeContractDto value) { 
      this.instance.setFirm(value.firm);
      this.instance.setOffice(value.office);
      this.instance.setContracts(value.contracts);
      return this;
    }

    public ProviderFirmOfficeContractDto.Builder firm(ProviderFirmSummary firm) {
      this.instance.firm(firm);
      return this;
    }
    
    public ProviderFirmOfficeContractDto.Builder office(ProviderFirmOfficeSummary office) {
      this.instance.office(office);
      return this;
    }
    
    public ProviderFirmOfficeContractDto.Builder contracts(List<FirmOfficeContractDetails> contracts) {
      this.instance.contracts(contracts);
      return this;
    }
    
    /**
    * returns a built ProviderFirmOfficeContractDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmOfficeContractDto build() {
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
  public static ProviderFirmOfficeContractDto.Builder builder() {
    return new ProviderFirmOfficeContractDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmOfficeContractDto.Builder toBuilder() {
    ProviderFirmOfficeContractDto.Builder builder = new ProviderFirmOfficeContractDto.Builder();
    return builder.copyOf(this);
  }

}

