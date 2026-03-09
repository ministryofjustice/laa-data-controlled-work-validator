package uk.gov.justice.laa.dstew.payments.claims.validation.validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;

/**
 * Context object containing additional data needed for claim validation.
 * This provides validators with access to reference data, related claims,
 * and configuration without requiring database access.
 */
@Getter
@Builder
public class ValidationContext {

  /**
   * The validation scope (e.g., "fee", "disbursement", "all").
   * Validators can use this to determine if they should run.
   */
  private final String scope;

  /**
   * The area of law for the submission (e.g., "CRIME_LOWER", "LEGAL_HELP").
   */
  private final String areaOfLaw;

  /**
   * The office account number for the provider.
   */
  private final String officeAccountNumber;

  /**
   * Fee details retrieved from external fee scheme service.
   * Key is the fee code, value contains fee calculation details.
   */
  @Builder.Default
  private final Map<String, Object> feeDetails = new HashMap<>();

  /**
   * Other claims in the same submission (for duplicate checking).
   */
  @Builder.Default
  private final List<Map<String, Object>> relatedClaims = List.of();

  /**
   * Additional metadata that may be needed by specific validators.
   */
  @Builder.Default
  private final Map<String, Object> metadata = new HashMap<>();

  /**
   * Gets a value from the fee details map.
   *
   * @param feeCode the fee code to look up
   * @return the fee details or null if not found
   */
  public Object getFeeDetails(String feeCode) {
    return feeDetails.get(feeCode);
  }

  /**
   * Gets a metadata value.
   *
   * @param key the metadata key
   * @return the metadata value or null if not found
   */
  public Object getMetadata(String key) {
    return metadata.get(key);
  }
}

