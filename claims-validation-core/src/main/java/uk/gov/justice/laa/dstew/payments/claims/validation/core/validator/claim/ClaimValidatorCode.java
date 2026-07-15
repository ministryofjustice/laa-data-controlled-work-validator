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
  CLAIM_SCHEMA_VALIDATOR,
  CLAIM_CASE_DATES_VALIDATOR,
  CLAIM_MATTER_TYPE_VALIDATOR,
  CLAIM_STAGE_REACHED_VALIDATOR,
  CLAIM_CLIENT_DATE_OF_BIRTH_VALIDATOR,
  CLAIM_DISBURSEMENT_START_DATE_VALIDATOR,
  CLAIM_DISBURSEMENTS_VALIDATOR,
  CLAIM_DUPLICATE_VALIDATOR,
  CLAIM_SCHEDULE_REFERENCE_VALIDATOR,
  CLAIM_MANDATORY_FIELD_VALIDATOR,
  CLAIM_OUTCOME_CODE_VALIDATOR,
  CLAIM_CATEGORY_OF_LAW_VALIDATOR,
  CLAIM_UNIQUE_FILE_NUMBER_VALIDATOR;

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
