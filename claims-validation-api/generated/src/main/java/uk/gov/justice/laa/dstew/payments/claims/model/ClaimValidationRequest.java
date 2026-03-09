package uk.gov.justice.laa.dstew.payments.claims.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * ClaimValidationRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-09T17:50:01.801544Z[Europe/London]", comments = "Generator version: 7.18.0")
public class ClaimValidationRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @Valid
  private Map<String, Object> claim = new HashMap<>();

  private @Nullable String scope;

  /**
   * Area of law for the submission
   */
  public enum AreaOfLawEnum {
    LEGAL_HELP("LEGAL_HELP"),
    
    CRIME_LOWER("CRIME_LOWER"),
    
    MEDIATION("MEDIATION");

    private final String value;

    AreaOfLawEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static AreaOfLawEnum fromValue(String value) {
      for (AreaOfLawEnum b : AreaOfLawEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private @Nullable AreaOfLawEnum areaOfLaw;

  private @Nullable String officeAccountNumber;

  @Valid
  private List<Map<String, Object>> relatedClaims = new ArrayList<>();

  public ClaimValidationRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ClaimValidationRequest(Map<String, Object> claim) {
    this.claim = claim;
  }

  public ClaimValidationRequest claim(Map<String, Object> claim) {
    this.claim = claim;
    return this;
  }

  public ClaimValidationRequest putClaimItem(String key, Object claimItem) {
    if (this.claim == null) {
      this.claim = new HashMap<>();
    }
    this.claim.put(key, claimItem);
    return this;
  }

  /**
   * Claim object (structure TBC based on claim-fields.schema.json)
   * @return claim
   */
  @NotNull 
  @Schema(name = "claim", description = "Claim object (structure TBC based on claim-fields.schema.json)", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("claim")
  public Map<String, Object> getClaim() {
    return claim;
  }

  public void setClaim(Map<String, Object> claim) {
    this.claim = claim;
  }

  public ClaimValidationRequest scope(@Nullable String scope) {
    this.scope = scope;
    return this;
  }

  /**
   * Optional validation scope (e.g., \"fee\", \"disbursement\", \"all\")
   * @return scope
   */
  
  @Schema(name = "scope", example = "fee", description = "Optional validation scope (e.g., \"fee\", \"disbursement\", \"all\")", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("scope")
  public @Nullable String getScope() {
    return scope;
  }

  public void setScope(@Nullable String scope) {
    this.scope = scope;
  }

  public ClaimValidationRequest areaOfLaw(@Nullable AreaOfLawEnum areaOfLaw) {
    this.areaOfLaw = areaOfLaw;
    return this;
  }

  /**
   * Area of law for the submission
   * @return areaOfLaw
   */
  
  @Schema(name = "areaOfLaw", example = "LEGAL_HELP", description = "Area of law for the submission", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("areaOfLaw")
  public @Nullable AreaOfLawEnum getAreaOfLaw() {
    return areaOfLaw;
  }

  public void setAreaOfLaw(@Nullable AreaOfLawEnum areaOfLaw) {
    this.areaOfLaw = areaOfLaw;
  }

  public ClaimValidationRequest officeAccountNumber(@Nullable String officeAccountNumber) {
    this.officeAccountNumber = officeAccountNumber;
    return this;
  }

  /**
   * Provider office account number
   * @return officeAccountNumber
   */
  
  @Schema(name = "officeAccountNumber", example = "1A234B", description = "Provider office account number", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("officeAccountNumber")
  public @Nullable String getOfficeAccountNumber() {
    return officeAccountNumber;
  }

  public void setOfficeAccountNumber(@Nullable String officeAccountNumber) {
    this.officeAccountNumber = officeAccountNumber;
  }

  public ClaimValidationRequest relatedClaims(List<Map<String, Object>> relatedClaims) {
    this.relatedClaims = relatedClaims;
    return this;
  }

  public ClaimValidationRequest addRelatedClaimsItem(Map<String, Object> relatedClaimsItem) {
    if (this.relatedClaims == null) {
      this.relatedClaims = new ArrayList<>();
    }
    this.relatedClaims.add(relatedClaimsItem);
    return this;
  }

  /**
   * Other claims in the same submission (for duplicate checking)
   * @return relatedClaims
   */
  @Valid 
  @Schema(name = "relatedClaims", description = "Other claims in the same submission (for duplicate checking)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("relatedClaims")
  public List<Map<String, Object>> getRelatedClaims() {
    return relatedClaims;
  }

  public void setRelatedClaims(List<Map<String, Object>> relatedClaims) {
    this.relatedClaims = relatedClaims;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClaimValidationRequest claimValidationRequest = (ClaimValidationRequest) o;
    return Objects.equals(this.claim, claimValidationRequest.claim) &&
        Objects.equals(this.scope, claimValidationRequest.scope) &&
        Objects.equals(this.areaOfLaw, claimValidationRequest.areaOfLaw) &&
        Objects.equals(this.officeAccountNumber, claimValidationRequest.officeAccountNumber) &&
        Objects.equals(this.relatedClaims, claimValidationRequest.relatedClaims);
  }

  @Override
  public int hashCode() {
    return Objects.hash(claim, scope, areaOfLaw, officeAccountNumber, relatedClaims);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClaimValidationRequest {\n");
    sb.append("    claim: ").append(toIndentedString(claim)).append("\n");
    sb.append("    scope: ").append(toIndentedString(scope)).append("\n");
    sb.append("    areaOfLaw: ").append(toIndentedString(areaOfLaw)).append("\n");
    sb.append("    officeAccountNumber: ").append(toIndentedString(officeAccountNumber)).append("\n");
    sb.append("    relatedClaims: ").append(toIndentedString(relatedClaims)).append("\n");
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

    private ClaimValidationRequest instance;

    public Builder() {
      this(new ClaimValidationRequest());
    }

    protected Builder(ClaimValidationRequest instance) {
      this.instance = instance;
    }

    protected Builder copyOf(ClaimValidationRequest value) { 
      this.instance.setClaim(value.claim);
      this.instance.setScope(value.scope);
      this.instance.setAreaOfLaw(value.areaOfLaw);
      this.instance.setOfficeAccountNumber(value.officeAccountNumber);
      this.instance.setRelatedClaims(value.relatedClaims);
      return this;
    }

    public ClaimValidationRequest.Builder claim(Map<String, Object> claim) {
      this.instance.claim(claim);
      return this;
    }
    
    public ClaimValidationRequest.Builder scope(String scope) {
      this.instance.scope(scope);
      return this;
    }
    
    public ClaimValidationRequest.Builder areaOfLaw(AreaOfLawEnum areaOfLaw) {
      this.instance.areaOfLaw(areaOfLaw);
      return this;
    }
    
    public ClaimValidationRequest.Builder officeAccountNumber(String officeAccountNumber) {
      this.instance.officeAccountNumber(officeAccountNumber);
      return this;
    }
    
    public ClaimValidationRequest.Builder relatedClaims(List<Map<String, Object>> relatedClaims) {
      this.instance.relatedClaims(relatedClaims);
      return this;
    }
    
    /**
    * returns a built ClaimValidationRequest instance.
    *
    * The builder is not reusable (NullPointerException)
    */
    public ClaimValidationRequest build() {
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
  public static ClaimValidationRequest.Builder builder() {
    return new ClaimValidationRequest.Builder();
  }

  /**
  * Create a builder with a shallow copy of this instance.
  */
  public ClaimValidationRequest.Builder toBuilder() {
    ClaimValidationRequest.Builder builder = new ClaimValidationRequest.Builder();
    return builder.copyOf(this);
  }

}

