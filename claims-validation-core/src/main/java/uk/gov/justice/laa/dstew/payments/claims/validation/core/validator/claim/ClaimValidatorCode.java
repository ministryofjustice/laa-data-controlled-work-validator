package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim;

import java.util.Optional;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidatorCode;

/**
 * Validator codes for the built-in claim validators.
 *
 * <p>Claim scopes are expressed as {@code Set<ClaimValidatorCode>} so that only codes meaningful to
 * claim validation can be supplied. See {@link ValidatorCode} for the shared contract and the
 * serialization seam.
 */
public enum ClaimValidatorCode implements ValidatorCode {
  CLAIM_SCHEMA,
  CLAIM_CASE_DATES,
  CLAIM_MATTER_TYPE,
  CLAIM_STAGE_REACHED,
  CLAIM_CLIENT_DATE_OF_BIRTH,
  CLAIM_DISBURSEMENT_START_DATE,
  CLAIM_DISBURSEMENTS,
  CLAIM_DUPLICATE_CLAIM,
  CLAIM_SCHEDULE_REFERENCE,
  CLAIM_MANDATORY_FIELD,
  CLAIM_OUTCOME_CODE,
  CLAIM_CATEGORY_OF_LAW,
  CLAIM_UNIQUE_FILE_NUMBER;

  @Override
  public String code() {
    return name();
  }

  /**
   * Looks up the {@link ClaimValidatorCode} for a given string code.
   *
   * @param code the string code to look up
   * @return the matching constant, or empty if none matches
   */
  public static Optional<ClaimValidatorCode> fromCode(String code) {
    try {
      return code == null ? Optional.empty() : Optional.of(valueOf(code));
    } catch (IllegalArgumentException ex) {
      return Optional.empty();
    }
  }
}
