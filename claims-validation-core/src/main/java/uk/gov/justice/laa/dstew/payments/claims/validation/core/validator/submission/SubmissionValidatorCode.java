package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission;

import java.util.Optional;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidatorCode;

/**
 * Validator codes for the built-in submission validators.
 *
 * <p>Submission scopes are expressed as {@code Set<SubmissionValidatorCode>} so that only codes
 * meaningful to submission validation can be supplied. See {@link ValidatorCode} for the shared
 * contract and the serialization seam.
 */
public enum SubmissionValidatorCode implements ValidatorCode {
  SUBMISSION_SCHEMA_VALIDATOR,
  SUBMISSION_NIL_VALIDATOR,
  SUBMISSION_STATUS_VALIDATOR,
  SUBMISSION_PERIOD_VALIDATOR,
  SUBMISSION_DUPLICATE_VALIDATOR;

  @Override
  public String code() {
    return name();
  }

  /**
   * Looks up the {@link SubmissionValidatorCode} for a given string code.
   *
   * @param code the string code to look up
   * @return the matching constant, or empty if none matches
   */
  public static Optional<SubmissionValidatorCode> fromCode(String code) {
    try {
      return code == null ? Optional.empty() : Optional.of(valueOf(code));
    } catch (IllegalArgumentException ex) {
      return Optional.empty();
    }
  }
}
