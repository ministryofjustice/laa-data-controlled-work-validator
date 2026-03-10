package uk.gov.justice.laa.dstew.payments.claims.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.lang.Nullable;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-10T13:28:50.707211Z[Europe/London]", comments = "Generator version: 7.18.0")
public class ClaimValidationRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private Claim claim;

  private @Nullable String scope;

  @Valid
  private List<@Valid Claim> relatedClaims = new ArrayList<>();

  public ClaimValidationRequest() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ClaimValidationRequest(Claim claim) {
    this.claim = claim;
  }

  public ClaimValidationRequest claim(Claim claim) {
    this.claim = claim;
    return this;
  }

  /**
   * Get claim
   * @return claim
   */
  @NotNull @Valid 
  @Schema(name = "claim", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("claim")
  public Claim getClaim() {
    return claim;
  }

  public void setClaim(Claim claim) {
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

  public ClaimValidationRequest relatedClaims(List<@Valid Claim> relatedClaims) {
    this.relatedClaims = relatedClaims;
    return this;
  }

  public ClaimValidationRequest addRelatedClaimsItem(Claim relatedClaimsItem) {
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
  public List<@Valid Claim> getRelatedClaims() {
    return relatedClaims;
  }

  public void setRelatedClaims(List<@Valid Claim> relatedClaims) {
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
        Objects.equals(this.relatedClaims, claimValidationRequest.relatedClaims);
  }

  @Override
  public int hashCode() {
    return Objects.hash(claim, scope, relatedClaims);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClaimValidationRequest {\n");
    sb.append("    claim: ").append(toIndentedString(claim)).append("\n");
    sb.append("    scope: ").append(toIndentedString(scope)).append("\n");
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
      this.instance.setRelatedClaims(value.relatedClaims);
      return this;
    }

    public ClaimValidationRequest.Builder claim(Claim claim) {
      this.instance.claim(claim);
      return this;
    }
    
    public ClaimValidationRequest.Builder scope(String scope) {
      this.instance.scope(scope);
      return this;
    }
    
    public ClaimValidationRequest.Builder relatedClaims(List<Claim> relatedClaims) {
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

