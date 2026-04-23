package uk.gov.justice.laa.dstew.payments.claims.validation.core.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Internal representation of a request to validate a claim and related claims.
 *
 * <p>
 * This class is for internal use only and is not intended for API serialization or
 * external consumption. It encapsulates the claim to be validated, an optional
 * validation scope, and any related claims for duplicate checking.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClaimValidationRequest implements Serializable {

  /**
   * Serial version UID for serialization compatibility.
   */
  @Serial
  private static final long serialVersionUID = 1L;

  /**
   * The claim to be validated.
   */
  @NotNull
  @Valid
  private Claim claim;

  /**
   * Optional validation scope (e.g., "fee", "disbursement", "all").
   */
  private String scope;

  /**
   * Other claims in the same submission (for duplicate checking).
   */
  @Valid
  @Builder.Default
  private List<@Valid Claim> relatedClaims = new ArrayList<>();

  /**
   * Adds a related claim to the list.
   *
   * @param relatedClaimsItem the related claim to add
   * @return this ClaimValidationRequest instance
   */
  public ClaimValidationRequest addRelatedClaimsItem(Claim relatedClaimsItem) {
    if (this.relatedClaims == null) {
      this.relatedClaims = new ArrayList<>();
    }
    this.relatedClaims.add(relatedClaimsItem);
    return this;
  }
}
