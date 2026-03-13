package uk.gov.justice.laa.provider.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.provider.model.FeeEarner;
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
 * ProviderFirmFeeEarnerListDto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T21:51:32.806274Z[Europe/London]", comments = "Generator version: 7.14.0")
public class ProviderFirmFeeEarnerListDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private @Nullable ProviderFirmSummary firm;

  @Valid
  private List<@Valid FeeEarner> feeEarners = new ArrayList<>();

  public ProviderFirmFeeEarnerListDto firm(@Nullable ProviderFirmSummary firm) {
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

  public ProviderFirmFeeEarnerListDto feeEarners(List<@Valid FeeEarner> feeEarners) {
    this.feeEarners = feeEarners;
    return this;
  }

  public ProviderFirmFeeEarnerListDto addFeeEarnersItem(FeeEarner feeEarnersItem) {
    if (this.feeEarners == null) {
      this.feeEarners = new ArrayList<>();
    }
    this.feeEarners.add(feeEarnersItem);
    return this;
  }

  /**
   * Get feeEarners
   * @return feeEarners
   */
  @Valid 
  @Schema(name = "feeEarners", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("feeEarners")
  public List<@Valid FeeEarner> getFeeEarners() {
    return feeEarners;
  }

  public void setFeeEarners(List<@Valid FeeEarner> feeEarners) {
    this.feeEarners = feeEarners;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProviderFirmFeeEarnerListDto providerFirmFeeEarnerListDto = (ProviderFirmFeeEarnerListDto) o;
    return Objects.equals(this.firm, providerFirmFeeEarnerListDto.firm) &&
        Objects.equals(this.feeEarners, providerFirmFeeEarnerListDto.feeEarners);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firm, feeEarners);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProviderFirmFeeEarnerListDto {\n");
    sb.append("    firm: ").append(toIndentedString(firm)).append("\n");
    sb.append("    feeEarners: ").append(toIndentedString(feeEarners)).append("\n");
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

    private ProviderFirmFeeEarnerListDto instance;

    public Builder() {
      this(new ProviderFirmFeeEarnerListDto());
    }

    protected Builder(ProviderFirmFeeEarnerListDto instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ProviderFirmFeeEarnerListDto value) { 
      this.instance.setFirm(value.firm);
      this.instance.setFeeEarners(value.feeEarners);
      return this;
    }

    public ProviderFirmFeeEarnerListDto.Builder firm(ProviderFirmSummary firm) {
      this.instance.firm(firm);
      return this;
    }
    
    public ProviderFirmFeeEarnerListDto.Builder feeEarners(List<FeeEarner> feeEarners) {
      this.instance.feeEarners(feeEarners);
      return this;
    }
    
    /**
    * returns a built ProviderFirmFeeEarnerListDto instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ProviderFirmFeeEarnerListDto build() {
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
  public static ProviderFirmFeeEarnerListDto.Builder builder() {
    return new ProviderFirmFeeEarnerListDto.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ProviderFirmFeeEarnerListDto.Builder toBuilder() {
    ProviderFirmFeeEarnerListDto.Builder builder = new ProviderFirmFeeEarnerListDto.Builder();
    return builder.copyOf(this);
  }

}

