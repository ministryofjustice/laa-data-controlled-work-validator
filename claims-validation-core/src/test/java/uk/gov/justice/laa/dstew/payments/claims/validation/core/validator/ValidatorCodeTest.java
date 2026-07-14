package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidatorCode;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidatorCode;

@DisplayName("Validator codes")
class ValidatorCodeTest {

  @Nested
  @DisplayName("ClaimValidatorCode")
  class Claim {

    @Test
    @DisplayName("code() equals the constant name for every value")
    void codeEqualsName() {
      for (ClaimValidatorCode value : ClaimValidatorCode.values()) {
        assertThat(value.code()).isEqualTo(value.name());
      }
    }

    @Test
    @DisplayName("fromCode round-trips every constant")
    void fromCodeRoundTrips() {
      for (ClaimValidatorCode value : ClaimValidatorCode.values()) {
        assertThat(ClaimValidatorCode.fromCode(value.code())).contains(value);
      }
    }

    @Test
    @DisplayName("fromCode returns empty for unknown or null codes")
    void fromCodeUnknown() {
      assertThat(ClaimValidatorCode.fromCode("NOPE")).isEmpty();
      assertThat(ClaimValidatorCode.fromCode(null)).isEmpty();
      // A submission code is not a claim code.
      assertThat(ClaimValidatorCode.fromCode("SUBMISSION_NIL_VALIDATOR")).isEmpty();
    }
  }

  @Nested
  @DisplayName("SubmissionValidatorCode")
  class Submission {

    @Test
    @DisplayName("code() equals the constant name for every value")
    void codeEqualsName() {
      for (SubmissionValidatorCode value : SubmissionValidatorCode.values()) {
        assertThat(value.code()).isEqualTo(value.name());
      }
    }

    @Test
    @DisplayName("fromCode round-trips every constant")
    void fromCodeRoundTrips() {
      for (SubmissionValidatorCode value : SubmissionValidatorCode.values()) {
        assertThat(SubmissionValidatorCode.fromCode(value.code())).contains(value);
      }
    }

    @Test
    @DisplayName("fromCode returns empty for unknown or null codes")
    void fromCodeUnknown() {
      assertThat(SubmissionValidatorCode.fromCode("NOPE")).isEmpty();
      assertThat(SubmissionValidatorCode.fromCode(null)).isEmpty();
      // A claim code is not a submission code.
      assertThat(SubmissionValidatorCode.fromCode("CLAIM_SCHEMA")).isEmpty();
    }
  }

  @Nested
  @DisplayName("ValidatorCode.fromCode(String)")
  class SharedFromCode {

    @Test
    @DisplayName("Resolves claim codes")
    void resolvesClaimCodes() {
      assertThat(ValidatorCode.fromCode("CLAIM_SCHEMA")).contains(ClaimValidatorCode.CLAIM_SCHEMA);
    }

    @Test
    @DisplayName("Resolves submission codes")
    void resolvesSubmissionCodes() {
      assertThat(ValidatorCode.fromCode("SUBMISSION_NIL_VALIDATOR"))
          .contains(SubmissionValidatorCode.SUBMISSION_NIL_VALIDATOR);
    }

    @Test
    @DisplayName("Returns empty for unknown or null codes")
    void unknown() {
      assertThat(ValidatorCode.fromCode("SOME_EXTERNAL_CODE")).isEmpty();
      assertThat(ValidatorCode.fromCode(null)).isEmpty();
    }
  }

  @Test
  @DisplayName("Claim and submission codes do not overlap")
  void codesDoNotOverlap() {
    var claimCodes =
        Arrays.stream(ClaimValidatorCode.values())
            .map(ClaimValidatorCode::code)
            .collect(Collectors.toSet());
    var submissionCodes =
        Arrays.stream(SubmissionValidatorCode.values())
            .map(SubmissionValidatorCode::code)
            .collect(Collectors.toSet());
    assertThat(claimCodes).doesNotContainAnyElementsOf(submissionCodes);
  }
}
