package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static uk.gov.justice.laa.dstew.payments.claims.validation.core.service.ValidationServiceTestUtils.assertContextClaimError;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationError;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;

/**
 * Tests for {@link SubmissionPeriodValidator}.
 *
 * <p>A fixed {@link Clock} is installed into {@link DateUtils} in {@code @BeforeEach} so that
 * "current month" is always MAY-2025, making all assertions deterministic regardless of when the
 * tests run. The clock is reset to system default in {@code @AfterEach}.
 */
@DisplayName("SubmissionPeriodValidator")
class SubmissionPeriodValidatorTest {

  private static final String MINIMUM_PERIOD = "APR-2025";

  /**
   * Fixed clock representing 1 May 2025 00:00 UTC — current month is MAY-2025 for all tests.
   */
  private static final Clock FIXED_CLOCK = Clock.fixed(
      Instant.parse("2025-05-01T00:00:00Z"), ZoneOffset.UTC);

  private SubmissionPeriodValidator validator;

  @BeforeEach
  void setUp() {
    DateUtils.setClock(FIXED_CLOCK);
    validator = new SubmissionPeriodValidator(MINIMUM_PERIOD);
  }

  @Test
  @DisplayName("Validator metadata: priority, appliesTo and code")
  void metadata() {
    assertEquals(10, validator.priority());
    assertTrue(validator.appliesTo(Set.of("SUBMISSION_PERIOD_VALIDATOR")));
    assertEquals("SUBMISSION_PERIOD_VALIDATOR", validator.getValidatorCode());
  }

  @AfterEach
  void tearDown() {
    DateUtils.resetClock();
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Missing / unparseable submission period
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Submission period absent or invalid")
  class AbsentOrInvalid {

    @ParameterizedTest(name = "submission period is [{0}]")
    @NullAndEmptySource
    @DisplayName("Returns SUBMISSION_PERIOD_MISSING when submission period is null or empty")
    void returnsErrorWhenSubmissionPeriodIsNullOrEmpty(String submissionPeriod) {
      SubmissionValidationContext ctx = validate(submissionPeriod);

      assertThat(ctx.hasErrors()).isTrue();
      assertContextClaimError(ctx.getIssues(), SubmissionValidationError.SUBMISSION_PERIOD_MISSING);
    }

    @Test
    @DisplayName("Returns SUBMISSION_PERIOD_INVALID_FORMAT for an unparseable period")
    void returnsErrorWhenSubmissionPeriodIsUnparseable() {
      SubmissionValidationContext ctx = validate("not-a-period");

      assertThat(ctx.hasErrors()).isTrue();
      assertContextClaimError(ctx.getIssues(), SubmissionValidationError.SUBMISSION_PERIOD_INVALID_FORMAT);
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Valid period
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Valid submission period")
  class ValidPeriod {

    @Test
    @DisplayName("Returns no errors when period is exactly the minimum allowed (APR-2025)")
    void returnsNoErrorsWhenPeriodIsMinimum() {
      SubmissionValidationContext ctx = validate(MINIMUM_PERIOD);

      assertThat(ctx.hasErrors()).isFalse();
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Period relative to current month (fixed: MAY-2025)
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Period relative to current month (MAY-2025)")
  class RelativeToCurrentMonth {

    @Test
    @DisplayName("Returns SUBMISSION_PERIOD_SAME_MONTH when period equals current month")
    void returnsErrorWhenPeriodEqualsCurrentMonth() {
      SubmissionValidationContext ctx = validate("MAY-2025");

      assertThat(ctx.hasErrors()).isTrue();
      assertContextClaimError(ctx.getIssues(),
          SubmissionValidationError.SUBMISSION_PERIOD_SAME_MONTH.toValidationIssue("May 2025"));
    }

    @Test
    @DisplayName("Returns SUBMISSION_PERIOD_FUTURE_MONTH when period is after current month")
    void returnsErrorWhenPeriodIsAfterCurrentMonth() {
      SubmissionValidationContext ctx = validate("AUG-2025");

      assertThat(ctx.hasErrors()).isTrue();
      assertContextClaimError(ctx.getIssues(),
          SubmissionValidationError.SUBMISSION_PERIOD_FUTURE_MONTH.toValidationIssue("May 2025"));
    }

    @Test
    @DisplayName("Returns SUBMISSION_VALIDATION_MINIMUM_PERIOD when period is before the minimum")
    void returnsErrorWhenPeriodIsBeforeMinimum() {
      SubmissionValidationContext ctx = validate("MAR-2025");

      assertThat(ctx.hasErrors()).isTrue();
      assertContextClaimError(ctx.getIssues(),
          SubmissionValidationError.SUBMISSION_VALIDATION_MINIMUM_PERIOD
              .toValidationIssue(MINIMUM_PERIOD, MINIMUM_PERIOD));
    }

    @Test
    @DisplayName("Parsing is case-insensitive — May-2025 is treated as MAY-2025")
    void periodParsingIsCaseInsensitive() {
      SubmissionValidationContext ctx = validate("May-2025");

      assertThat(ctx.hasErrors()).isTrue();
      assertContextClaimError(ctx.getIssues(),
          SubmissionValidationError.SUBMISSION_PERIOD_SAME_MONTH.toValidationIssue("May 2025"));
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Helper
  // ─────────────────────────────────────────────────────────────────────────

  private SubmissionValidationContext validate(String submissionPeriod) {
    SubmissionResponse submission = SubmissionResponse.builder()
        .submissionPeriod(submissionPeriod).build();
    SubmissionValidationContext ctx = SubmissionValidationContext.create();
    validator.validate(submission, ctx);
    return ctx;
  }
}
