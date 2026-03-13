package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.provider.model.FirmOfficeContractAndScheduleDetails;
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
 * ProviderFirmOfficeContractAndScheduleDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T18:11:19.822784Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmOfficeContractAndScheduleDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable ProviderFirmSummary firm;

  private @Nullable ProviderFirmOfficeSummary office;

  private @Nullable Boolean pds;

  @Valid
  private List<@Valid FirmOfficeContractAndScheduleDetails> schedules = new ArrayList<>();

  public ProviderFirmOfficeContractAndScheduleDto firm(@Nullable ProviderFirmSummary firm) {
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

  public ProviderFirmOfficeContractAndScheduleDto office(@Nullable ProviderFirmOfficeSummary office) {
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

  public ProviderFirmOfficeContractAndScheduleDto pds(@Nullable Boolean pds) {
    this.pds = pds;
    return this;
  }

  /**
   * Get pds
   * @return pds
   */
  
  @Schema(name = "pds", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("pds")
  public @Nullable Boolean getPds() {
    return pds;
  }

  public void setPds(@Nullable Boolean pds) {
    this.pds = pds;
  }

  public ProviderFirmOfficeContractAndScheduleDto schedules(List<@Valid FirmOfficeContractAndScheduleDetails> schedules) {
    this.schedules = schedules;
    return this;
  }

  public ProviderFirmOfficeContractAndScheduleDto addSchedulesItem(FirmOfficeContractAndScheduleDetails schedulesItem) {
    if (this.schedules == null) {
      this.schedules = new ArrayList<>();
    }
    this.schedules.add(schedulesItem);
    return this;
  }

  /**
   * Get schedules
   * @return schedules
   */
  @Valid 
  @Schema(name = "schedules", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("schedules")
  public List<@Valid FirmOfficeContractAndScheduleDetails> getSchedules() {
    return schedules;
  }

  public void setSchedules(List<@Valid FirmOfficeContractAndScheduleDetails> schedules) {
    this.schedules = schedules;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirmOfficeContractAndScheduleDto providerFirmOfficeContractAndScheduleDto = (ProviderFirmOfficeContractAndScheduleDto) o;
    return Objects.equals(this.firm, providerFirmOfficeContractAndScheduleDto.firm) &&
        Objects.equals(this.office, providerFirmOfficeContractAndScheduleDto.office) &&
        Objects.equals(this.pds, providerFirmOfficeContractAndScheduleDto.pds) &&
        Objects.equals(this.schedules, providerFirmOfficeContractAndScheduleDto.schedules);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firm, office, pds, schedules);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmOfficeContractAndScheduleDto {\n");
    sb.append("    firm: ").append(toIndentedString(firm)).append("\n");
    sb.append("    office: ").append(toIndentedString(office)).append("\n");
    sb.append("    pds: ").append(toIndentedString(pds)).append("\n");
    sb.append("    schedules: ").append(toIndentedString(schedules)).append("\n");
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

    private ProviderFirmOfficeContractAndScheduleDto instance;

    public Builder() {
      this(new ProviderFirmOfficeContractAndScheduleDto());
    }

    protected Builder(ProviderFirmOfficeContractAndScheduleDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmOfficeContractAndScheduleDto value) { 
      this.instance.setFirm(value.firm);
      this.instance.setOffice(value.office);
      this.instance.setPds(value.pds);
      this.instance.setSchedules(value.schedules);
      return this;
    }

    public ProviderFirmOfficeContractAndScheduleDto.Builder firm(ProviderFirmSummary firm) {
      this.instance.firm(firm);
      return this;
    }
    
    public ProviderFirmOfficeContractAndScheduleDto.Builder office(ProviderFirmOfficeSummary office) {
      this.instance.office(office);
      return this;
    }
    
    public ProviderFirmOfficeContractAndScheduleDto.Builder pds(Boolean pds) {
      this.instance.pds(pds);
      return this;
    }
    
    public ProviderFirmOfficeContractAndScheduleDto.Builder schedules(List<FirmOfficeContractAndScheduleDetails> schedules) {
      this.instance.schedules(schedules);
      return this;
    }
    
    /**
    * returns a built ProviderFirmOfficeContractAndScheduleDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmOfficeContractAndScheduleDto build() {
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
  public static ProviderFirmOfficeContractAndScheduleDto.Builder builder() {
    return new ProviderFirmOfficeContractAndScheduleDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmOfficeContractAndScheduleDto.Builder toBuilder() {
    ProviderFirmOfficeContractAndScheduleDto.Builder builder = new ProviderFirmOfficeContractAndScheduleDto.Builder();
    return builder.copyOf(this);
  }

}

