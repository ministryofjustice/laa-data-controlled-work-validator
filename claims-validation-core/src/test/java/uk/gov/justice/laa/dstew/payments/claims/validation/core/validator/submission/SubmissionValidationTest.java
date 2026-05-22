package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.NilSubmissionValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.SubmissionPeriodValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.SubmissionSchemaValidator;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionClaim;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionStatus;

/**
 * Integration tests for {@link SubmissionValidation} that verify the full pipeline wiring all
 * three production validators together:
 *
 * <ol>
 *   <li>{@link SubmissionSchemaValidator} (priority 1) — JSON schema field constraints
 *   <li>{@link NilSubmissionValidator} (priority 10) — nil/non-nil claims consistency
 *   <li>{@link SubmissionPeriodValidator} (priority 10) — submission period business rules
 * </ol>
 *
 * <p>No mocks are used. Each validator is constructed as it would be in production, with
 * {@link SubmissionSchemaValidator#init()} called manually (Spring's {@code @PostConstruct} does
 * not fire outside a container).
 *
 * <p>A fixed {@link Clock} (1 May 2025) is installed into {@link DateUtils} so that all
 * "current month" checks are deterministic. The minimum accepted period is {@code APR-2025}.
 *
 * <p><strong>Isolated failure strategy for the period field:</strong>
 * Using {@code "apr-2025"} (lowercase) causes the schema validator to reject it (the schema
 * pattern requires {@code ^(JAN|...|DEC)-[0-9]{4}$}) while the period validator accepts it
 * (its parser is case-insensitive, resolving to APR-2025 which is valid). This lets us exercise
 * the schema validator in isolation on the period field.
 */
@DisplayName("SubmissionValidation — full pipeline integration")
class SubmissionValidationTest {

  /** Fixed clock: 1 May 2025 UTC — current month is MAY-2025 for all tests. */
  private static final Clock FIXED_CLOCK =
      Clock.fixed(Instant.parse("2025-05-01T00:00:00Z"), ZoneOffset.UTC);

  private static final String MIN_PERIOD = "APR-2025";

  /** A valid APR-2025 period — passes schema (uppercase) AND period validator (before MAY-2025). */
  private static final String VALID_PERIOD = "APR-2025";

  private SubmissionValidation submissionValidation;

  @BeforeEach
  void setUp() {
    DateUtils.setClock(FIXED_CLOCK);

    SubmissionSchemaValidator schemaValidator = new SubmissionSchemaValidator();
    schemaValidator.init();

    submissionValidation = new SubmissionValidation(
        List.of(
            schemaValidator,
            new NilSubmissionValidator(),
            new SubmissionPeriodValidator(MIN_PERIOD)));
  }

  @AfterEach
  void tearDown() {
    DateUtils.resetClock();
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Helpers
  // ─────────────────────────────────────────────────────────────────────────

  /**
   * Returns a builder pre-populated with values that pass all three validators.
   * Uses a nil submission (no claims) to keep the base builder self-contained.
   * Override individual fields to trigger specific validator failures.
   */
  private SubmissionResponse.Builder validNilBuilder() {
    return SubmissionResponse.builder()
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .officeAccountNumber("ABC123")
        .submissionPeriod(VALID_PERIOD)
        .status(SubmissionStatus.VALIDATION_SUCCEEDED)
        .isNilSubmission(true)
        .numberOfClaims(0)
        .legalHelpSubmissionReference("LHREF01")
        .crimeLowerScheduleNumber("SCHED01")
        .mediationSubmissionReference("MEDREF01");
  }

  /** Runs the full pipeline and returns the result. */
  private ValidationResult validate(SubmissionResponse submission) {
    return submissionValidation.validateSubmission(submission, null);
  }

  /** Extracts only ERROR-severity issues from a result. */
  private List<ValidationIssue> errors(ValidationResult result) {
    if (result.getIssues() == null) {
      return List.of();
    }
    return result.getIssues().stream()
        .filter(i -> i.getSeverity() == ValidationSeverity.ERROR)
        .toList();
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Passing validation — all three validators satisfied
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Passing validation — all three validators satisfied")
  class PassingValidation {

    @Test
    @DisplayName("Nil submission with no claims passes all validators")
    void nilSubmission_passesAllValidators() {
      ValidationResult result = validate(validNilBuilder().build());

      assertThat(result.isValid()).isTrue();
      assertThat(errors(result)).isEmpty();
    }

    @Test
    @DisplayName("Non-nil submission with claims passes all validators")
    void nonNilSubmissionWithClaims_passesAllValidators() {
      SubmissionResponse sub = validNilBuilder()
          .isNilSubmission(false)
          .numberOfClaims(1)
          .claims(Collections.singletonList(new SubmissionClaim()))
          .build();

      ValidationResult result = validate(sub);

      assertThat(result.isValid()).isTrue();
      assertThat(errors(result)).isEmpty();
    }

    @Test
    @DisplayName("Each area of law passes when its conditional reference field is present")
    void eachAreaOfLaw_passesWithCorrectReferenceField() {
      // CRIME LOWER
      SubmissionResponse crimeLower = validNilBuilder()
          .areaOfLaw(AreaOfLaw.CRIME_LOWER)
          .build();
      assertThat(validate(crimeLower).isValid()).isTrue();

      // MEDIATION
      SubmissionResponse mediation = validNilBuilder()
          .areaOfLaw(AreaOfLaw.MEDIATION)
          .build();
      assertThat(validate(mediation).isValid()).isTrue();
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // SubmissionSchemaValidator failures (priority 1)
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("SubmissionSchemaValidator failures — schema constraints violated")
  class SubmissionSchemaValidatorFailures {

    @Test
    @DisplayName("Invalid office account number (wrong format) → schema error, others pass")
    void invalidOfficeAccountNumber_schemaFails() {
      ValidationResult result = validate(validNilBuilder().officeAccountNumber("bad!").build());

      assertThat(result.isValid()).isFalse();
      assertThat(errors(result))
          .extracting(ValidationIssue::getCode)
          .containsOnly("SCHEMA_VALIDATION_ERROR");
      assertThat(errors(result))
          .extracting(ValidationIssue::getMessage)
          .containsOnly(
              "Office Account Number must be exactly 6 characters containing uppercase letters and numbers.");
    }

    @Test
    @DisplayName("Lowercase period 'apr-2025' → schema fails (pattern requires uppercase),"
        + " period validator accepts it (case-insensitive)")
    void lowercaseSubmissionPeriod_onlySchemaFails() {
      // "apr-2025" fails the schema pattern ^(JAN|...|DEC)-[0-9]{4}$
      // but the period validator parses it case-insensitively as APR-2025, which is valid
      ValidationResult result = validate(validNilBuilder().submissionPeriod("apr-2025").build());

      assertThat(result.isValid()).isFalse();
      assertThat(errors(result))
          .extracting(ValidationIssue::getCode)
          .containsOnly("SCHEMA_VALIDATION_ERROR");
    }

    @Test
    @DisplayName("Null numberOfClaims → schema required field error")
    void nullNumberOfClaims_schemaRequiredFieldFails() {
      ValidationResult result = validate(validNilBuilder().numberOfClaims(null).build());

      assertThat(result.isValid()).isFalse();
      assertThat(errors(result))
          .extracting(ValidationIssue::getMessage)
          .contains("Number Of Claims is required");
    }

    @Test
    @DisplayName("Missing conditional reference field for area of law → schema conditional error")
    void missingConditionalReferenceField_schemaConditionalFails() {
      SubmissionResponse sub = validNilBuilder()
          .areaOfLaw(AreaOfLaw.LEGAL_HELP)
          .legalHelpSubmissionReference(null)
          .build();

      ValidationResult result = validate(sub);

      assertThat(result.isValid()).isFalse();
      assertThat(errors(result))
          .extracting(ValidationIssue::getMessage)
          .contains("Legal Help Submission Reference is required");
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // NilSubmissionValidator failures (priority 10)
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("NilSubmissionValidator failures — nil/claims consistency violated")
  class NilSubmissionValidatorFailures {

    @Test
    @DisplayName("Nil submission with claims → INVALID_NIL_SUBMISSION_CONTAINS_CLAIMS,"
        + " schema and period pass")
    void nilFlagWithClaims_onlyNilValidatorFails() {
      SubmissionResponse sub = validNilBuilder()
          .isNilSubmission(true)
          .claims(Collections.singletonList(new SubmissionClaim()))
          .build();

      ValidationResult result = validate(sub);

      assertThat(result.isValid()).isFalse();
      assertThat(errors(result))
          .extracting(ValidationIssue::getCode)
          .containsOnly(SubmissionValidationError.INVALID_NIL_SUBMISSION_CONTAINS_CLAIMS.name());
    }

    @Test
    @DisplayName("Non-nil submission with no claims → NON_NIL_SUBMISSION_CONTAINS_NO_CLAIMS,"
        + " schema and period pass")
    void nonNilFlagWithNoClaims_onlyNilValidatorFails() {
      SubmissionResponse sub = validNilBuilder()
          .isNilSubmission(false)
          .numberOfClaims(0)
          .build(); // no claims added

      ValidationResult result = validate(sub);

      assertThat(result.isValid()).isFalse();
      assertThat(errors(result))
          .extracting(ValidationIssue::getCode)
          .containsOnly(SubmissionValidationError.NON_NIL_SUBMISSION_CONTAINS_NO_CLAIMS.name());
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // SubmissionPeriodValidator failures (priority 10)
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("SubmissionPeriodValidator failures — business period rules violated")
  class SubmissionPeriodValidatorFailures {

    @Test
    @DisplayName("Period equals current month (MAY-2025) → SUBMISSION_PERIOD_SAME_MONTH,"
        + " schema passes (valid format)")
    void currentMonthPeriod_onlyPeriodFails() {
      // MAY-2025 passes schema pattern but is rejected by period validator (same month as now)
      ValidationResult result = validate(validNilBuilder().submissionPeriod("MAY-2025").build());

      assertThat(result.isValid()).isFalse();
      assertThat(errors(result))
          .extracting(ValidationIssue::getCode)
          .containsOnly(SubmissionValidationError.SUBMISSION_PERIOD_SAME_MONTH.name());
    }

    @Test
    @DisplayName("Period is after current month (AUG-2025) → SUBMISSION_PERIOD_FUTURE_MONTH,"
        + " schema passes (valid format)")
    void futureMonthPeriod_onlyPeriodFails() {
      ValidationResult result = validate(validNilBuilder().submissionPeriod("AUG-2025").build());

      assertThat(result.isValid()).isFalse();
      assertThat(errors(result))
          .extracting(ValidationIssue::getCode)
          .containsOnly(SubmissionValidationError.SUBMISSION_PERIOD_FUTURE_MONTH.name());
    }

    @Test
    @DisplayName("Period is before minimum (MAR-2025) → SUBMISSION_VALIDATION_MINIMUM_PERIOD,"
        + " schema passes (valid format)")
    void beforeMinimumPeriod_onlyPeriodFails() {
      ValidationResult result = validate(validNilBuilder().submissionPeriod("MAR-2025").build());

      assertThat(result.isValid()).isFalse();
      assertThat(errors(result))
          .extracting(ValidationIssue::getCode)
          .containsOnly(SubmissionValidationError.SUBMISSION_VALIDATION_MINIMUM_PERIOD.name());
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Multiple validator failures
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Multiple validator failures — errors from several validators in one pass")
  class MultipleValidatorFailures {

    @Test
    @DisplayName("Schema + Period: invalid office account number AND current month period")
    void schemaAndPeriod_bothFail() {
      SubmissionResponse sub = validNilBuilder()
          .officeAccountNumber("bad!")     // fails schema
          .submissionPeriod("MAY-2025")    // fails period validator (same month)
          .build();

      ValidationResult result = validate(sub);
      List<ValidationIssue> errs = errors(result);

      assertThat(result.isValid()).isFalse();
      assertThat(errs).hasSizeGreaterThanOrEqualTo(2);
      assertThat(errs).extracting(ValidationIssue::getCode)
          .contains(
              "SCHEMA_VALIDATION_ERROR",
              SubmissionValidationError.SUBMISSION_PERIOD_SAME_MONTH.name());
    }

    @Test
    @DisplayName("Schema + Nil: invalid office account number AND nil=false with no claims")
    void schemaAndNil_bothFail() {
      SubmissionResponse sub = validNilBuilder()
          .officeAccountNumber("bad!")   // fails schema
          .isNilSubmission(false)        // fails nil validator (no claims)
          .numberOfClaims(0)
          .build();

      ValidationResult result = validate(sub);
      List<ValidationIssue> errs = errors(result);

      assertThat(result.isValid()).isFalse();
      assertThat(errs).hasSizeGreaterThanOrEqualTo(2);
      assertThat(errs).extracting(ValidationIssue::getCode)
          .contains(
              "SCHEMA_VALIDATION_ERROR",
              SubmissionValidationError.NON_NIL_SUBMISSION_CONTAINS_NO_CLAIMS.name());
    }

    @Test
    @DisplayName("Period + Nil: current month period AND non-nil with no claims")
    void periodAndNil_bothFail() {
      SubmissionResponse sub = validNilBuilder()
          .submissionPeriod("MAY-2025")  // fails period validator (same month)
          .isNilSubmission(false)        // fails nil validator (no claims)
          .numberOfClaims(0)
          .build();

      ValidationResult result = validate(sub);
      List<ValidationIssue> errs = errors(result);

      assertThat(result.isValid()).isFalse();
      assertThat(errs).hasSizeGreaterThanOrEqualTo(2);
      assertThat(errs).extracting(ValidationIssue::getCode)
          .contains(
              SubmissionValidationError.SUBMISSION_PERIOD_SAME_MONTH.name(),
              SubmissionValidationError.NON_NIL_SUBMISSION_CONTAINS_NO_CLAIMS.name());
    }

    @Test
    @DisplayName("All three fail: invalid schema field + future period + non-nil with no claims")
    void allThreeValidators_allFail() {
      SubmissionResponse sub = validNilBuilder()
          .officeAccountNumber("bad!")   // fails schema
          .submissionPeriod("AUG-2025")  // fails period validator (future month)
          .isNilSubmission(false)        // fails nil validator (no claims)
          .numberOfClaims(0)
          .build();

      ValidationResult result = validate(sub);
      List<ValidationIssue> errs = errors(result);

      assertThat(result.isValid()).isFalse();
      assertThat(errs).hasSizeGreaterThanOrEqualTo(3);
      assertThat(errs).extracting(ValidationIssue::getCode)
          .contains(
              "SCHEMA_VALIDATION_ERROR",
              SubmissionValidationError.SUBMISSION_PERIOD_FUTURE_MONTH.name(),
              SubmissionValidationError.NON_NIL_SUBMISSION_CONTAINS_NO_CLAIMS.name());
    }

    @Test
    @DisplayName("Multiple schema errors in a single pass — each invalid field is reported")
    void multipleSchemaErrors_allReported() {
      SubmissionResponse sub = validNilBuilder()
          .officeAccountNumber("bad!")    // fails schema pattern
          .submissionPeriod("apr-2025")   // fails schema pattern (lowercase)
          .build();

      ValidationResult result = validate(sub);
      List<ValidationIssue> errs = errors(result);

      assertThat(result.isValid()).isFalse();
      assertThat(errs).hasSizeGreaterThanOrEqualTo(2);
      assertThat(errs).extracting(ValidationIssue::getCode)
          .allMatch("SCHEMA_VALIDATION_ERROR"::equals);
      assertThat(errs).extracting(ValidationIssue::getMessage)
          .contains(
              "Office Account Number must be exactly 6 characters containing uppercase letters and numbers.",
              "Submission period wrong format, should be in the format MMM-YYYY");
    }
  }
}
