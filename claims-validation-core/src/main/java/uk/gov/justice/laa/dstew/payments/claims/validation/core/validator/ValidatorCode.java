package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator;

import java.util.Optional;
import java.util.function.Function;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidatorCode;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidatorCode;

/**
 * Shared contract for validator codes.
 *
 * <p>{@link Validator#getValidatorCode()} returns a {@code ValidatorCode}, and scope filtering
 * operates on {@code Set<? extends ValidatorCode>}. Codes are separated by validation type into two
 * enums — {@link ClaimValidatorCode} and {@link SubmissionValidatorCode} — because a claim code is
 * not meaningful for submission validation and vice versa. Public entry points are typed to the
 * relevant enum (for example {@code Set<ClaimValidatorCode>}) so mismatched codes cannot be passed.
 * The validator sub-interfaces narrow the return type further: {@code ClaimValidator} returns a
 * {@link ClaimValidatorCode} and {@code SubmissionValidator} returns a
 * {@link SubmissionValidatorCode}, so an implementation cannot report a code from the wrong type.
 *
 * <p>{@link #code()} and {@link #fromCode(String)} support the serialization boundary — for example
 * an HTTP adapter deserialising a {@code "scope"} field of string codes into codes, rejecting
 * anything unknown. The string form is simply the enum constant name.
 */
public interface ValidatorCode {

  /**
   * The stable string form of this validator code (the enum constant name), used at serialization
   * boundaries.
   *
   * @return the string validator code
   */
  String code();

  /**
   * Looks up a built-in {@link ValidatorCode} for the given string code, searching both claim and
   * submission codes.
   *
   * @param code the string code to look up
   * @return the matching {@link ValidatorCode}, or empty if the code is not a built-in code
   */
  static Optional<ValidatorCode> fromCode(String code) {
    return ClaimValidatorCode.fromCode(code)
        .<ValidatorCode>map(Function.identity())
        .or(() -> SubmissionValidatorCode.fromCode(code).map(Function.identity()));
  }
}
