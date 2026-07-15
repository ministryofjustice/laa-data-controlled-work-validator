package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uk.gov.justice.laa.dstew.payments.claims.validation.core.service.ValidationServiceTestUtils.assertContextClaimError;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidatorCode;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionStatus;

/**
 * Tests for {@link SubmissionSchemaValidator}.
 *
 * <p>Coverage mirrors the rules declared in {@code submission-fields.schema.json}:
 * <ul>
 *   <li>Always-required fields (6 fields, each independently tested)
 *   <li>Field format constraints (pattern, maxLength, enum, minimum)
 *   <li>Conditional required fields driven by area of law
 *   <li>Multiple simultaneous validation errors
 * </ul>
 *
 * <p>The helper {@link #errors(SubmissionValidationContext)} filters to
 * {@link ValidationSeverity#ERROR} only — schema config warnings (unknown fields) are intentionally
 * excluded from format assertions.
 */
@DisplayName("SubmissionSchemaValidator")
class SubmissionSchemaValidatorTest {

  private SubmissionSchemaValidator submissionSchemaValidator;

  @BeforeEach
  void beforeEach() {
    submissionSchemaValidator = new SubmissionSchemaValidator();
    submissionSchemaValidator.init(); // @PostConstruct is not called outside a Spring context
  }

  @Test
  @DisplayName("Validator metadata: priority, appliesTo and code")
  void metadata() {
    assertEquals(1, submissionSchemaValidator.priority());
    assertTrue(submissionSchemaValidator.appliesTo(Set.of(SubmissionValidatorCode.SUBMISSION_SCHEMA_VALIDATOR)));
    assertEquals(SubmissionValidatorCode.SUBMISSION_SCHEMA_VALIDATOR, submissionSchemaValidator.getValidatorCode());
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Existing tests — kept as-is
  // ─────────────────────────────────────────────────────────────────────────

  @ParameterizedTest
  @EnumSource(AreaOfLaw.class)
  @DisplayName("Should have no errors if json schema validator returns no errors")
  void shouldHaveNoErrorsIfJsonSchemaValidatorReturnsNoErrors(AreaOfLaw areaOfLaw) {
    SubmissionResponse submissionResponse =
        SubmissionResponse.builder()
            .areaOfLaw(areaOfLaw)
            .officeAccountNumber("OFF001")
            .status(SubmissionStatus.VALIDATION_SUCCEEDED)
            .isNilSubmission(true)
            .numberOfClaims(0)
            .crimeLowerScheduleNumber("crimeLowerScheduleNu")
            .crimeLowerScheduleNumber("crimeLowerScheduleNu")
            .legalHelpSubmissionReference("legalHelpSubmissionR")
            .mediationSubmissionReference("mediationSubmissionR")
            .submissionPeriod("JAN-2025")
            .build();

    SubmissionValidationContext submissionValidationContext = SubmissionValidationContext.create();

    submissionSchemaValidator.validate(submissionResponse, submissionValidationContext);

    assertFalse(submissionValidationContext.hasErrors());
  }

  @ParameterizedTest
  @EnumSource(AreaOfLaw.class)
  @DisplayName("Should have errors if json schema validator returns errors")
  void shouldHaveErrorsIfJsonSchemaValidatorReturnsErrors(AreaOfLaw areaOfLaw) {
    SubmissionResponse submissionResponse =
        SubmissionResponse.builder()
            .areaOfLaw(areaOfLaw)
            .officeAccountNumber("OFF001")
            .status(SubmissionStatus.VALIDATION_SUCCEEDED)
            .isNilSubmission(true)
            .numberOfClaims(0)
            .crimeLowerScheduleNumber("crimeLowerScheduleNu")
            .legalHelpSubmissionReference("legalHelpSubmissionR")
            .mediationSubmissionReference("mediationSubmissionR")
            .build();

    SubmissionValidationContext submissionValidationContext = SubmissionValidationContext.create();

    submissionSchemaValidator.validate(submissionResponse, submissionValidationContext);

    assertTrue(submissionValidationContext.hasErrors());
    assertContextClaimError(
        submissionValidationContext.getIssues(), "Submission Period is required");
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Helpers
  // ─────────────────────────────────────────────────────────────────────────

  /**
   * Returns a builder pre-populated with valid values for all required fields plus all three
   * conditional reference fields. Tests override only the field(s) under test.
   *
   * <p>All reference fields are always set so that only the explicitly varied field produces an
   * error — regardless of which area of law is active.
   */
  private SubmissionResponse.Builder baseBuilder() {
    return SubmissionResponse.builder()
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .officeAccountNumber("ABC123")
        .submissionPeriod("JAN-2025")
        .status(SubmissionStatus.VALIDATION_SUCCEEDED)
        .isNilSubmission(false)
        .numberOfClaims(0)
        .legalHelpSubmissionReference("LHREF01")
        .crimeLowerScheduleNumber("SCHED01")
        .mediationSubmissionReference("MEDREF01");
  }

  /** Runs the validator and returns the populated context. */
  private SubmissionValidationContext validate(SubmissionResponse submission) {
    SubmissionValidationContext ctx = SubmissionValidationContext.create();
    submissionSchemaValidator.validate(submission, ctx);
    return ctx;
  }

  /**
   * Extracts only {@link ValidationSeverity#ERROR} issues from the context. Schema-config
   * {@code WARNING}s (fields present in the Java model but absent from the schema) are excluded.
   */
  private List<ValidationIssue> errors(SubmissionValidationContext ctx) {
    if (ctx.getIssues() == null) {
      return List.of();
    }
    return ctx.getIssues().stream()
        .filter(i -> i.getSeverity() == ValidationSeverity.ERROR)
        .toList();
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Always-required fields
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Always-required fields — each produces its own error when absent")
  class AlwaysRequiredFields {

    @Test
    @DisplayName("Missing officeAccountNumber → 'Office Account Number is required'")
    void missingOfficeAccountNumber() {
      SubmissionValidationContext ctx = validate(baseBuilder().officeAccountNumber(null).build());
      assertThat(errors(ctx)).extracting(ValidationIssue::getMessage)
          .contains("Office Account Number is required");
    }

    @Test
    @DisplayName("Missing submissionPeriod → 'Submission Period is required'")
    void missingSubmissionPeriod() {
      SubmissionValidationContext ctx = validate(baseBuilder().submissionPeriod(null).build());
      assertThat(errors(ctx)).extracting(ValidationIssue::getMessage)
          .contains("Submission Period is required");
    }

    @Test
    @DisplayName("Missing areaOfLaw → 'Area Of Law is required'")
    void missingAreaOfLaw() {
      SubmissionValidationContext ctx = validate(baseBuilder().areaOfLaw(null).build());
      assertThat(errors(ctx)).extracting(ValidationIssue::getMessage)
          .contains("Area Of Law is required");
    }

    @Test
    @DisplayName("Missing status → 'Status is required'")
    void missingStatus() {
      SubmissionValidationContext ctx = validate(baseBuilder().status(null).build());
      assertThat(errors(ctx)).extracting(ValidationIssue::getMessage)
          .contains("Status is required");
    }

    @Test
    @DisplayName("Missing all nullable required fields produces a separate error per field")
    void missingAllRequiredFields_producesOneErrorPerField() {
      SubmissionResponse emptySubmission = SubmissionResponse.builder().build();
      List<ValidationIssue> errs = errors(validate(emptySubmission));

      assertThat(errs)
          .extracting(ValidationIssue::getMessage)
          .contains(
              "Office Account Number is required",
              "Submission Period is required",
              "Area Of Law is required",
              "Status is required");

      // Every error carries the shared schema error code
      assertThat(errs).allMatch(i -> "SCHEMA_VALIDATION_ERROR".equals(i.getCode()))
              .allMatch(i -> i.getTechnicalMessage().startsWith("$: required property '"));
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Office account number — pattern: ^[A-Z0-9]{6}$
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Office account number — must be exactly 6 uppercase alphanumeric characters")
  class OfficeAccountNumberFormat {

    private static final String EXPECTED_MSG =
        "Office Account Number must be exactly 6 characters containing uppercase letters and numbers.";

    @ParameterizedTest(name = "[{0}] is valid")
    @ValueSource(strings = {"ABC123", "123456", "ABCDEF", "A1B2C3", "000000", "ZZZ999"})
    @DisplayName("Valid 6-char uppercase alphanumeric values produce no error")
    void validValues(String value) {
      SubmissionValidationContext ctx = validate(baseBuilder().officeAccountNumber(value).build());
      assertThat(errors(ctx)).isEmpty();
    }

    @ParameterizedTest(name = "[{0}] is too short or too long")
    @ValueSource(strings = {"AB123", "A", "ABCDE", "ABC1234", "ABCDEFG"})
    @DisplayName("Wrong length produces the custom format error")
    void wrongLength(String value) {
      SubmissionValidationContext ctx = validate(baseBuilder().officeAccountNumber(value).build());
      assertThat(errors(ctx)).extracting(ValidationIssue::getMessage).contains(EXPECTED_MSG);
    }

    @ParameterizedTest(name = "[{0}] contains disallowed characters")
    @ValueSource(strings = {"abc123", "ABC!23", "AB C12", "AB-123", "ab1234"})
    @DisplayName("Lowercase letters or special characters produce the custom format error")
    void disallowedCharacters(String value) {
      SubmissionValidationContext ctx = validate(baseBuilder().officeAccountNumber(value).build());
      assertThat(errors(ctx)).extracting(ValidationIssue::getMessage).contains(EXPECTED_MSG);
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Submission period — pattern: ^(JAN|FEB|…|DEC)-[0-9]{4}$
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Submission period — must be MMM-YYYY with a recognised 3-letter month code")
  class SubmissionPeriodFormat {

    private static final String EXPECTED_MSG =
        "Submission period wrong format, should be in the format MMM-YYYY";

    @ParameterizedTest(name = "[{0}] is a valid period")
    @ValueSource(strings = {
        "JAN-2025", "FEB-2025", "MAR-2025", "APR-2025", "MAY-2025", "JUN-2025",
        "JUL-2025", "AUG-2025", "SEP-2025", "OCT-2025", "NOV-2025", "DEC-2025",
        "JAN-1900", "DEC-2099"   // boundary years
    })
    @DisplayName("All 12 month codes and boundary years are valid")
    void validPeriods(String period) {
      SubmissionValidationContext ctx = validate(baseBuilder().submissionPeriod(period).build());
      assertThat(errors(ctx)).isEmpty();
    }

    @ParameterizedTest(name = "[{0}] is not a valid period format")
    @ValueSource(strings = {
        "jan-2025",     // lowercase month
        "January-2025", // full month name
        "JAN2025",      // missing dash
        "JAN-25",       // 2-digit year
        "JAN-202",      // 3-digit year
        "XYZ-2025",     // invalid month code
        "13-2025",      // numeric month
        "JAN-",         // missing year
        "-2025"         // missing month
    })
    @DisplayName("Invalid period format produces the custom error message")
    void invalidFormat(String period) {
      SubmissionValidationContext ctx = validate(baseBuilder().submissionPeriod(period).build());
      assertThat(errors(ctx)).extracting(ValidationIssue::getMessage).contains(EXPECTED_MSG);
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Conditional required reference fields
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Conditional required fields — driven by area of law")
  class ConditionalRequiredFields {

    @Test
    @DisplayName("CRIME LOWER without crimeLowerScheduleNumber → 'Crime Lower Schedule Number is required'")
    void crimeLower_missingScheduleNumber() {
      SubmissionResponse sub = baseBuilder()
          .areaOfLaw(AreaOfLaw.CRIME_LOWER)
          .crimeLowerScheduleNumber(null)
          .build();
      assertThat(errors(validate(sub))).extracting(ValidationIssue::getMessage)
          .contains("Crime Lower Schedule Number is required");
    }

    @Test
    @DisplayName("CRIME LOWER with crimeLowerScheduleNumber → no conditional error")
    void crimeLower_withScheduleNumber_noError() {
      SubmissionResponse sub = baseBuilder()
          .areaOfLaw(AreaOfLaw.CRIME_LOWER)
          .crimeLowerScheduleNumber("SCHED01")
          .build();
      assertThat(errors(validate(sub))).isEmpty();
    }

    @Test
    @DisplayName("LEGAL HELP without legalHelpSubmissionReference → 'Legal Help Submission Reference is required'")
    void legalHelp_missingSubmissionReference() {
      SubmissionResponse sub = baseBuilder()
          .areaOfLaw(AreaOfLaw.LEGAL_HELP)
          .legalHelpSubmissionReference(null)
          .build();
      assertThat(errors(validate(sub))).extracting(ValidationIssue::getMessage)
          .contains("Legal Help Submission Reference is required");
    }

    @Test
    @DisplayName("LEGAL HELP with legalHelpSubmissionReference → no conditional error")
    void legalHelp_withSubmissionReference_noError() {
      SubmissionResponse sub = baseBuilder()
          .areaOfLaw(AreaOfLaw.LEGAL_HELP)
          .legalHelpSubmissionReference("LHREF01")
          .build();
      assertThat(errors(validate(sub))).isEmpty();
    }

    @Test
    @DisplayName("MEDIATION without mediationSubmissionReference → 'Mediation Submission Reference is required'")
    void mediation_missingSubmissionReference() {
      SubmissionResponse sub = baseBuilder()
          .areaOfLaw(AreaOfLaw.MEDIATION)
          .mediationSubmissionReference(null)
          .build();
      assertThat(errors(validate(sub))).extracting(ValidationIssue::getMessage)
          .contains("Mediation Submission Reference is required");
    }

    @Test
    @DisplayName("MEDIATION with mediationSubmissionReference → no conditional error")
    void mediation_withSubmissionReference_noError() {
      SubmissionResponse sub = baseBuilder()
          .areaOfLaw(AreaOfLaw.MEDIATION)
          .mediationSubmissionReference("MEDREF01")
          .build();
      assertThat(errors(validate(sub))).isEmpty();
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Reference field format — pattern: ^[a-zA-Z0-9/]+$, maxLength: 20
  // Rules are identical for all three reference field types.
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Reference field format — alphanumeric and '/' only, max 20 characters")
  class ReferenceFieldFormat {

    private static final String LH_MSG =
        "Legal Help Submission Reference must be a maximum of 20 characters and contain only letters, numbers and forward slashes";
    private static final String CL_MSG =
        "Crime Lower Schedule Number must be a maximum of 20 characters and contain only letters, numbers and forward slashes";
    private static final String MED_MSG =
        "Mediation Submission Reference must be a maximum of 20 characters and contain only letters, numbers and forward slashes";

    @Nested
    @DisplayName("legalHelpSubmissionReference")
    class LegalHelpReference {

      @ParameterizedTest(name = "[{0}] is valid")
      @ValueSource(strings = {
          "A",                     // minimum 1 char
          "ABC123",                // alphanumeric
          "ABC/123",               // forward slash allowed
          "ABCDEFGHIJ1234567890"   // exactly 20 chars (boundary)
      })
      @DisplayName("Valid formats produce no error")
      void validFormats(String value) {
        SubmissionValidationContext ctx =
            validate(baseBuilder().legalHelpSubmissionReference(value).build());
        assertThat(errors(ctx)).isEmpty();
      }

      @Test
      @DisplayName("21 characters exceeds maxLength → format error")
      void tooLong() {
        SubmissionValidationContext ctx =
            validate(baseBuilder().legalHelpSubmissionReference("ABCDEFGHIJ12345678901").build());
        assertThat(errors(ctx)).extracting(ValidationIssue::getMessage).contains(LH_MSG);
      }

      @ParameterizedTest(name = "[{0}] contains disallowed characters")
      @ValueSource(strings = {"ABC 123", "ABC-123", "ABC@123"})
      @DisplayName("Disallowed characters produce the custom format error")
      void disallowedCharacters(String value) {
        SubmissionValidationContext ctx =
            validate(baseBuilder().legalHelpSubmissionReference(value).build());
        assertThat(errors(ctx)).extracting(ValidationIssue::getMessage).contains(LH_MSG);
      }
    }

    @Nested
    @DisplayName("crimeLowerScheduleNumber")
    class CrimeLowerScheduleNumber {

      @Test
      @DisplayName("Exactly 20 alphanumeric characters is valid (boundary)")
      void exactly20Chars_isValid() {
        SubmissionValidationContext ctx =
            validate(baseBuilder().crimeLowerScheduleNumber("ABCDEFGHIJ1234567890").build());
        assertThat(errors(ctx)).isEmpty();
      }

      @Test
      @DisplayName("21 characters exceeds maxLength → format error")
      void tooLong() {
        SubmissionValidationContext ctx =
            validate(baseBuilder().crimeLowerScheduleNumber("ABCDEFGHIJ12345678901").build());
        assertThat(errors(ctx)).extracting(ValidationIssue::getMessage).contains(CL_MSG);
      }

      @Test
      @DisplayName("Special characters produce the custom format error")
      void specialCharacters() {
        SubmissionValidationContext ctx =
            validate(baseBuilder().crimeLowerScheduleNumber("SCHED-01").build());
        assertThat(errors(ctx)).extracting(ValidationIssue::getMessage).contains(CL_MSG);
      }
    }

    @Nested
    @DisplayName("mediationSubmissionReference")
    class MediationReference {

      @Test
      @DisplayName("Exactly 20 alphanumeric characters is valid (boundary)")
      void exactly20Chars_isValid() {
        SubmissionValidationContext ctx =
            validate(baseBuilder().mediationSubmissionReference("ABCDEFGHIJ1234567890").build());
        assertThat(errors(ctx)).isEmpty();
      }

      @Test
      @DisplayName("21 characters exceeds maxLength → format error")
      void tooLong() {
        SubmissionValidationContext ctx =
            validate(baseBuilder().mediationSubmissionReference("ABCDEFGHIJ12345678901").build());
        assertThat(errors(ctx)).extracting(ValidationIssue::getMessage).contains(MED_MSG);
      }

      @DisplayName("Forward slash is a valid character in the reference")
      void forwardSlashIsAllowed() {
        SubmissionValidationContext ctx =
            validate(baseBuilder().mediationSubmissionReference("MED/REF/01").build());
        assertThat(errors(ctx)).isEmpty();
      }
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Numerical constraints
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Numerical constraints")
  class NumericalConstraints {

    @Test
    @DisplayName("numberOfClaims = 0 is valid (minimum boundary)")
    void numberOfClaims_zero_isValid() {
      SubmissionValidationContext ctx = validate(baseBuilder().numberOfClaims(0).build());
      assertThat(errors(ctx)).isEmpty();
    }

    @Test
    @DisplayName("numberOfClaims = 1 is valid")
    void numberOfClaims_positive_isValid() {
      SubmissionValidationContext ctx = validate(baseBuilder().numberOfClaims(1).build());
      assertThat(errors(ctx)).isEmpty();
    }

    @Test
    @DisplayName("numberOfClaims = -1 violates minimum:0 → generic field error")
    void numberOfClaims_negative_producesError() {
      SubmissionValidationContext ctx = validate(baseBuilder().numberOfClaims(-1).build());
      assertThat(errors(ctx)).extracting(ValidationIssue::getMessage)
          .contains("Field 'number_of_claims' has an invalid value");
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Multiple simultaneous validation errors
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Multiple simultaneous errors — all failures reported in one validation pass")
  class MultipleSimultaneousErrors {

    @Test
    @DisplayName("Invalid officeAccountNumber + invalid submissionPeriod → two independent errors")
    void invalidAccountNumberAndInvalidPeriod() {
      SubmissionResponse sub = baseBuilder()
          .officeAccountNumber("bad!")     // fails pattern (wrong chars + length)
          .submissionPeriod("jan-2025")    // fails pattern (lowercase)
          .build();
      List<ValidationIssue> errs = errors(validate(sub));

      assertThat(errs).extracting(ValidationIssue::getMessage)
          .contains(
              "Office Account Number must be exactly 6 characters containing uppercase letters and numbers.",
              "Submission period wrong format, should be in the format MMM-YYYY");
    }

    @Test
    @DisplayName("Missing submissionPeriod + invalid officeAccountNumber + invalid reference → three errors")
    void missingPeriodAndInvalidAccountAndInvalidReference() {
      SubmissionResponse sub = baseBuilder()
          .submissionPeriod(null)
          .officeAccountNumber("bad!")
          .legalHelpSubmissionReference("INVALID SPACE!")   // contains space and !
          .build();
      List<ValidationIssue> errs = errors(validate(sub));

      assertThat(errs).hasSizeGreaterThanOrEqualTo(3);
      assertThat(errs).extracting(ValidationIssue::getMessage)
          .contains(
              "Submission Period is required",
              "Office Account Number must be exactly 6 characters containing uppercase letters and numbers.",
              "Legal Help Submission Reference must be a maximum of 20 characters and contain only letters, numbers and forward slashes");
    }

    @Test
    @DisplayName("Invalid period + numberOfClaims below minimum → two errors with correct codes")
    void invalidPeriodAndNegativeClaims_bothHaveSchemaErrorCode() {
      SubmissionResponse sub = baseBuilder()
          .submissionPeriod("WRONG")
          .numberOfClaims(-1)
          .build();
      List<ValidationIssue> errs = errors(validate(sub));

      assertThat(errs).hasSizeGreaterThanOrEqualTo(2)
              .allMatch(i -> "SCHEMA_VALIDATION_ERROR".equals(i.getCode()));
    }
  }
}
