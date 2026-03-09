package uk.gov.justice.laa.dstew.payments.claims.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.HashMap;
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

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-09T16:37:44.136847Z[Europe/London]", comments = "Generator version: 7.18.0")
public class ClaimValidationRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @Valid
  private Map<String, Object> claim = new HashMap<>();

  private @Nullable String scope;

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
   * Optional validation scope (e.g., \"fee\")
   * @return scope
   */
  
  @Schema(name = "scope", example = "fee", description = "Optional validation scope (e.g., \"fee\")", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("scope")
  public @Nullable String getScope() {
    return scope;
  }

  public void setScope(@Nullable String scope) {
    this.scope = scope;
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
        Objects.equals(this.scope, claimValidationRequest.scope);
  }

  @Override
  public int hashCode() {
    return Objects.hash(claim, scope);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClaimValidationRequest {\n");
    sb.append("    claim: ").append(toIndentedString(claim)).append("\n");
    sb.append("    scope: ").append(toIndentedString(scope)).append("\n");
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

