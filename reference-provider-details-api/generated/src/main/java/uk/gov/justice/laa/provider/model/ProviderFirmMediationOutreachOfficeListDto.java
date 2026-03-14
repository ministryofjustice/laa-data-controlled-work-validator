package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.provider.model.MediationOutreachOffice;
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
 * ProviderFirmMediationOutreachOfficeListDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-14T10:05:02.190518Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmMediationOutreachOfficeListDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable ProviderFirmSummary firm;

  @Valid
  private List<@Valid MediationOutreachOffice> mediationOutreachOffices = new ArrayList<>();

  public ProviderFirmMediationOutreachOfficeListDto firm(@Nullable ProviderFirmSummary firm) {
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

  public ProviderFirmMediationOutreachOfficeListDto mediationOutreachOffices(List<@Valid MediationOutreachOffice> mediationOutreachOffices) {
    this.mediationOutreachOffices = mediationOutreachOffices;
    return this;
  }

  public ProviderFirmMediationOutreachOfficeListDto addMediationOutreachOfficesItem(MediationOutreachOffice mediationOutreachOfficesItem) {
    if (this.mediationOutreachOffices == null) {
      this.mediationOutreachOffices = new ArrayList<>();
    }
    this.mediationOutreachOffices.add(mediationOutreachOfficesItem);
    return this;
  }

  /**
   * Get mediationOutreachOffices
   * @return mediationOutreachOffices
   */
  @Valid 
  @Schema(name = "mediationOutreachOffices", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("mediationOutreachOffices")
  public List<@Valid MediationOutreachOffice> getMediationOutreachOffices() {
    return mediationOutreachOffices;
  }

  public void setMediationOutreachOffices(List<@Valid MediationOutreachOffice> mediationOutreachOffices) {
    this.mediationOutreachOffices = mediationOutreachOffices;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirmMediationOutreachOfficeListDto providerFirmMediationOutreachOfficeListDto = (ProviderFirmMediationOutreachOfficeListDto) o;
    return Objects.equals(this.firm, providerFirmMediationOutreachOfficeListDto.firm) &&
        Objects.equals(this.mediationOutreachOffices, providerFirmMediationOutreachOfficeListDto.mediationOutreachOffices);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firm, mediationOutreachOffices);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmMediationOutreachOfficeListDto {\n");
    sb.append("    firm: ").append(toIndentedString(firm)).append("\n");
    sb.append("    mediationOutreachOffices: ").append(toIndentedString(mediationOutreachOffices)).append("\n");
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

    private ProviderFirmMediationOutreachOfficeListDto instance;

    public Builder() {
      this(new ProviderFirmMediationOutreachOfficeListDto());
    }

    protected Builder(ProviderFirmMediationOutreachOfficeListDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmMediationOutreachOfficeListDto value) { 
      this.instance.setFirm(value.firm);
      this.instance.setMediationOutreachOffices(value.mediationOutreachOffices);
      return this;
    }

    public ProviderFirmMediationOutreachOfficeListDto.Builder firm(ProviderFirmSummary firm) {
      this.instance.firm(firm);
      return this;
    }
    
    public ProviderFirmMediationOutreachOfficeListDto.Builder mediationOutreachOffices(List<MediationOutreachOffice> mediationOutreachOffices) {
      this.instance.mediationOutreachOffices(mediationOutreachOffices);
      return this;
    }
    
    /**
    * returns a built ProviderFirmMediationOutreachOfficeListDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmMediationOutreachOfficeListDto build() {
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
  public static ProviderFirmMediationOutreachOfficeListDto.Builder builder() {
    return new ProviderFirmMediationOutreachOfficeListDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmMediationOutreachOfficeListDto.Builder toBuilder() {
    ProviderFirmMediationOutreachOfficeListDto.Builder builder = new ProviderFirmMediationOutreachOfficeListDto.Builder();
    return builder.copyOf(this);
  }

}

