package uk.gov.justice.laa.dstew.payments.claims.validation.core.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;

@DisplayName("DateUtils")
class DateUtilsTest {

  // ─────────────────────────────────────────────────────────────────────────
  // isValidDate
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("isValidDate")
  class IsValidDate {

    @Test
    @DisplayName("Returns false for null")
    void returnsFalseForNull() {
      assertThat(DateUtils.isValidDate(null)).isFalse();
    }

    @Test
    @DisplayName("Returns true for a non-null date")
    void returnsTrueForNonNullDate() {
      assertThat(DateUtils.isValidDate(LocalDate.now())).isTrue();
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // isValidDateOfBirth
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("isValidDateOfBirth")
  class IsValidDateOfBirth {

    static Stream<Arguments> cases() {
      return Stream.of(
          Arguments.of(null, false, "null date"),
          Arguments.of(LocalDate.of(1899, 12, 31), false, "one day before MIN_BIRTH_DATE"),
          Arguments.of(LocalDate.now().plusDays(1), false, "future date"),
          Arguments.of(LocalDate.of(1900, 1, 1), true, "exactly MIN_BIRTH_DATE"),
          Arguments.of(LocalDate.now(), true, "today"),
          Arguments.of(LocalDate.now().minusYears(30), true, "valid past date")
      );
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("cases")
    @DisplayName("Returns correct result for")
    @SuppressWarnings("unused")
    void returnsCorrectResult(LocalDate date, boolean expected, String description) {
      assertThat(DateUtils.isValidDateOfBirth(date)).isEqualTo(expected);
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // isDateWithinRange
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("isDateWithinRange")
  class IsDateWithinRange {

    private static final LocalDate START = LocalDate.of(2020, 1, 1);
    private static final LocalDate END   = LocalDate.of(2020, 1, 31);

    static Stream<Arguments> cases() {
      return Stream.of(
          Arguments.of(START,                START, END,  true,  "exactly at start boundary"),
          Arguments.of(END,                  START, END,  true,  "exactly at end boundary"),
          Arguments.of(LocalDate.of(2020,1,15), START, END, true, "midpoint"),
          Arguments.of(START.minusDays(1),   START, END,  false, "one day before start"),
          Arguments.of(END.plusDays(1),      START, END,  false, "one day after end"),
          Arguments.of(null,                 START, END,  false, "null date"),
          Arguments.of(START,                null,  END,  false, "null earliest"),
          Arguments.of(START,                START, null, false, "null latest")
      );
    }

    @ParameterizedTest(name = "{4}")
    @MethodSource("cases")
    @DisplayName("Returns correct result for")
    @SuppressWarnings("unused")
    void returnsCorrectResult(
        LocalDate date, LocalDate earliest, LocalDate latest,
        boolean expected, String description) {
      assertThat(DateUtils.isDateWithinRange(date, earliest, latest)).isEqualTo(expected);
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // submissionPeriodCutoffDate
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("submissionPeriodCutoffDate")
  class SubmissionPeriodCutoffDate {

    @Test
    @DisplayName("Returns 20th of the following month")
    void returnsTwentiethOfFollowingMonth() {
      assertThat(DateUtils.submissionPeriodCutoffDate(YearMonth.of(2025, 5)))
          .isEqualTo(LocalDate.of(2025, 6, 20));
    }

    @Test
    @DisplayName("Correctly wraps into the next year at December")
    void wrapsIntoNextYearAtDecember() {
      assertThat(DateUtils.submissionPeriodCutoffDate(YearMonth.of(2025, 12)))
          .isEqualTo(LocalDate.of(2026, 1, 20));
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // checkDateNotInFutureAndWithinAllowedPeriod
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("checkDateNotInFutureAndWithinAllowedPeriod")
  class CheckDateNotInFutureAndWithinAllowedPeriod {

    private static final LocalDate EARLIEST = LocalDate.of(1900, 1, 1);

    @Test
    @DisplayName("Returns empty for an in-range date when claim has no submission period")
    void returnsEmptyForInRangeDateWhenNoSubmissionPeriod() {
      Claim claim = Claim.builder().submissionPeriod(null).build();
      String inRange = LocalDate.of(2020, 1, 15).format(DateUtils.DATE_FORMATTER_YYYY_MM_DD);

      // Only the period-dependent upper bound is skipped when there is no submission period.
      assertThat(DateUtils.checkDateNotInFutureAndWithinAllowedPeriod(
          claim, "Case Start Date", inRange, EARLIEST)).isEmpty();
    }

    @Test
    @DisplayName("Flags a future date even when the claim has no submission period (period-independent)")
    void flagsFutureDateWhenNoSubmissionPeriod() {
      Claim claim = Claim.builder().submissionPeriod(null).build();
      String future = LocalDate.now().plusDays(10).format(DateUtils.DATE_FORMATTER_YYYY_MM_DD);

      List<ValidationIssue> issues = DateUtils.checkDateNotInFutureAndWithinAllowedPeriod(
          claim, "Case Start Date", future, EARLIEST);

      assertThat(issues).hasSize(1);
      assertThat(issues.getFirst().getTechnicalMessage()).contains("cannot be a future date");
    }

    @Test
    @DisplayName("Flags a date before the earliest allowed even with a blank/malformed submission period")
    void flagsBeforeEarliestWhenSubmissionPeriodMalformed() {
      Claim claim = Claim.builder().submissionPeriod("2020-05").build();
      String early = EARLIEST.minusDays(1).format(DateUtils.DATE_FORMATTER_YYYY_MM_DD);

      List<ValidationIssue> issues = DateUtils.checkDateNotInFutureAndWithinAllowedPeriod(
          claim, "Case Start Date", early, EARLIEST);

      assertThat(issues).hasSize(1);
      assertThat(issues.getFirst().getTechnicalMessage()).contains("cannot be before");
    }

    @Test
    @DisplayName("Flags an unparseable date even when the claim has no submission period")
    void flagsUnparseableDateWhenNoSubmissionPeriod() {
      Claim claim = Claim.builder().submissionPeriod(null).build();

      List<ValidationIssue> issues = DateUtils.checkDateNotInFutureAndWithinAllowedPeriod(
          claim, "Case Concluded Date", "not-a-date", EARLIEST);

      assertThat(issues).hasSize(1);
      assertThat(issues.getFirst().getMessage())
          .isEqualTo("Invalid date value provided for Case Concluded Date");
    }

    @ParameterizedTest(name = "Returns empty (no throw) for blank submission period [{0}]")
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    @DisplayName("Returns empty instead of throwing for a blank or whitespace submission period")
    void returnsEmptyForBlankSubmissionPeriod(String submissionPeriod) {
      Claim claim = Claim.builder().submissionPeriod(submissionPeriod).build();
      String date = LocalDate.of(2020, 5, 30).format(DateUtils.DATE_FORMATTER_YYYY_MM_DD);

      assertThat(DateUtils.checkDateNotInFutureAndWithinAllowedPeriod(
          claim, "Case Concluded Date", date, EARLIEST)).isEmpty();
    }

    @Test
    @DisplayName("Returns empty instead of throwing for a malformed submission period")
    void returnsEmptyForMalformedSubmissionPeriod() {
      Claim claim = Claim.builder().submissionPeriod("2020-05").build();
      String date = LocalDate.of(2020, 5, 30).format(DateUtils.DATE_FORMATTER_YYYY_MM_DD);

      assertThat(DateUtils.checkDateNotInFutureAndWithinAllowedPeriod(
          claim, "Case Concluded Date", date, EARLIEST)).isEmpty();
    }

    @Test
    @DisplayName("Returns empty when date value is blank — no-op")
    void returnsEmptyWhenDateIsBlank() {
      Claim claim = Claim.builder().submissionPeriod("JAN-2026").build();

      assertThat(DateUtils.checkDateNotInFutureAndWithinAllowedPeriod(
          claim, "Case Start Date", "", EARLIEST)).isEmpty();
    }

    @Test
    @DisplayName("Returns error containing 'cannot be a future date' when date is in the future")
    void returnsErrorWhenDateIsInFuture() {
      Claim claim = Claim.builder().submissionPeriod("JAN-2026").build();
      String future = LocalDate.now().plusDays(1).format(DateUtils.DATE_FORMATTER_YYYY_MM_DD);

      List<ValidationIssue> issues = DateUtils.checkDateNotInFutureAndWithinAllowedPeriod(
          claim, "Case Start Date", future, EARLIEST);

      assertThat(issues).hasSize(1);
      assertThat(issues.getFirst().getTechnicalMessage()).contains("cannot be a future date");
    }

    @Test
    @DisplayName("Returns error containing 'cannot be before' when date is before earliest allowed")
    void returnsErrorWhenDateIsBeforeEarliest() {
      Claim claim = Claim.builder().submissionPeriod("JAN-2026").build();
      String early = EARLIEST.minusDays(1).format(DateUtils.DATE_FORMATTER_YYYY_MM_DD);

      List<ValidationIssue> issues = DateUtils.checkDateNotInFutureAndWithinAllowedPeriod(
          claim, "Case Start Date", early, EARLIEST);

      assertThat(issues).hasSize(1);
      assertThat(issues.getFirst().getTechnicalMessage()).contains("cannot be before");
    }

    @Test
    @DisplayName("Returns error containing '20th of the month' when date exceeds submission period cutoff")
    void returnsErrorWhenDateExceedsSubmissionCutoff() {
      Claim claim = Claim.builder().submissionPeriod("JAN-2020").build();
      String late = LocalDate.of(2020, 3, 21).format(DateUtils.DATE_FORMATTER_YYYY_MM_DD);

      List<ValidationIssue> issues = DateUtils.checkDateNotInFutureAndWithinAllowedPeriod(
          claim, "Case Start Date", late, EARLIEST);

      assertThat(issues).hasSize(1);
      assertThat(issues.getFirst().getTechnicalMessage())
          .contains("20th of the month following the submission period");
    }

    @Test
    @DisplayName("Returns error when date value cannot be parsed")
    void returnsErrorWhenDateIsUnparseable() {
      Claim claim = Claim.builder().submissionPeriod("JAN-2026").build();

      List<ValidationIssue> issues = DateUtils.checkDateNotInFutureAndWithinAllowedPeriod(
          claim, "Case Start Date", "not-a-date", EARLIEST);

      assertThat(issues).hasSize(1);
      assertThat(issues.getFirst().getCode()).isEqualTo("INVALID_CASE_START_DATE");
    }

    @ParameterizedTest(name = "Field ''{0}'' maps to error code ''{1}''")
    @CsvSource({
        "Case Start Date,             INVALID_CASE_START_DATE",
        "Case Concluded Date,         INVALID_CASE_CONCLUDED_DATE",
        "Transfer Date,               INVALID_TRANSFER_DATE",
        "Representation Order Date,   INVALID_REPRESENTATION_ORDER_DATE",
        "Client Date of Birth,        INVALID_CLIENT_DATE_OF_BIRTH",
        "Client 2 Date of Birth,      INVALID_CLIENT_DATE_OF_BIRTH",
        "Some Other Field,            INVALID_DATE_FORMAT"
    })
    @DisplayName("Maps field name to correct error code for an early date")
    void mapsFieldNameToCorrectErrorCode(String fieldName, String expectedCode) {
      Claim claim = Claim.builder().submissionPeriod("JAN-2020").build();
      String early = EARLIEST.minusDays(1).format(DateUtils.DATE_FORMATTER_YYYY_MM_DD);

      List<ValidationIssue> issues = DateUtils.checkDateNotInFutureAndWithinAllowedPeriod(
          claim, fieldName.trim(), early, EARLIEST);

      assertThat(issues).isNotEmpty();
      assertThat(issues.getFirst().getCode()).isEqualTo(expectedCode.trim());
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // validateDateBetween (private — tested via reflection)
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("validateDateBetween")
  class ValidateDateBetween {

    private static final LocalDate BOUNDARY = LocalDate.of(2022, 2, 2);

    private static List<ValidationIssue> invokeDateBetween(
        String fieldName, LocalDate date, LocalDate earliest, LocalDate latest) throws Exception {
      Method m = DateUtils.class.getDeclaredMethod(
          "validateDateBetween", String.class, String.class, LocalDate.class, LocalDate.class);
      m.setAccessible(true);
      @SuppressWarnings("unchecked")
      List<ValidationIssue> result = (List<ValidationIssue>) m.invoke(
          null, fieldName, date.format(DateUtils.DATE_FORMATTER_YYYY_MM_DD), earliest, latest);
      return result;
    }

    @Test
    @DisplayName("Returns no issues when date falls exactly on the boundary")
    void returnsNoIssuesForDateExactlyOnBoundary() throws Exception {
      assertThat(invokeDateBetween("field", BOUNDARY, BOUNDARY, BOUNDARY)).isEmpty();
    }

    @Test
    @DisplayName("Returns an issue when date is one day outside the boundary")
    void returnsIssueWhenDateIsOneDayOutsideBoundary() throws Exception {
      assertThat(invokeDateBetween("field", BOUNDARY.plusDays(1), BOUNDARY, BOUNDARY)).hasSize(1);
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // validateDateInPast
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("validateDateInPast")
  class ValidateDateInPast {

    @Test
    @DisplayName("Returns no issues for a valid past date")
    void returnsNoIssuesForValidPastDate() {
      assertThat(DateUtils.validateDateInPast("someField", "2020-01-01", LocalDate.of(1900, 1, 1)))
          .isEmpty();
    }

    @Test
    @DisplayName("Returns INVALID_DATE_FORMAT for an unparseable date string")
    void returnsErrorForUnparseableDateString() {
      List<ValidationIssue> issues =
          DateUtils.validateDateInPast("someField", "bad", LocalDate.of(1900, 1, 1));

      assertThat(issues).hasSize(1);
      assertThat(issues.getFirst().getCode()).isEqualTo("INVALID_DATE_FORMAT");
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Parsing helpers
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("parseSubmissionPeriod")
  class ParseSubmissionPeriod {

    @ParameterizedTest(name = "Returns null for blank input [{0}]")
    @NullAndEmptySource
    @DisplayName("Returns null for null or empty input")
    void returnsNullForBlankInput(String input) {
      assertThat(DateUtils.parseSubmissionPeriod(input)).isNull();
    }

    @Test
    @DisplayName("Returns null for an unparseable value")
    void returnsNullForUnparseableValue() {
      assertThat(DateUtils.parseSubmissionPeriod("bad-format")).isNull();
    }

    @ParameterizedTest(name = "Parses ''{0}'' to JAN-2026")
    @ValueSource(strings = {"JAN-2026", "jan-2026", "Jan-2026"})
    @DisplayName("Parses case-insensitively to the correct YearMonth")
    void parsesCaseInsensitively(String input) {
      assertThat(DateUtils.parseSubmissionPeriod(input)).isEqualTo(YearMonth.of(2026, 1));
    }
  }

  @Nested
  @DisplayName("parseDate")
  class ParseDate {

    @ParameterizedTest(name = "Returns null for blank input [{0}]")
    @NullAndEmptySource
    @DisplayName("Returns null for null or empty input")
    void returnsNullForBlankInput(String input) {
      assertThat(DateUtils.parseDate(input)).isNull();
    }

    @Test
    @DisplayName("Returns null for an unparseable value")
    void returnsNullForUnparseableValue() {
      assertThat(DateUtils.parseDate("not-a-date")).isNull();
    }

    @Test
    @DisplayName("Parses a valid date string to the correct LocalDate")
    void parsesValidDateString() {
      assertThat(DateUtils.parseDate("2020-02-29")).isEqualTo(LocalDate.of(2020, 2, 29));
    }
  }

  @Nested
  @DisplayName("getTwentiethOfNextMonth (private)")
  class GetTwentiethOfNextMonth {

    private static LocalDate invoke(String input) throws Exception {
      Method m = DateUtils.class.getDeclaredMethod("getTwentiethOfNextMonth", String.class);
      m.setAccessible(true);
      return (LocalDate) m.invoke(null, input);
    }

    @Test
    @DisplayName("Returns the 20th of the following month for a valid submission period")
    void returnsTwentiethOfFollowingMonth() throws Exception {
      assertThat(invoke("Jan-2026")).isEqualTo(LocalDate.of(2026, 2, 20));
    }

    @ParameterizedTest(name = "Returns null (no throw) for blank input [{0}]")
    @NullAndEmptySource
    @DisplayName("Returns null instead of throwing for null or empty input")
    void returnsNullForBlankInput(String input) throws Exception {
      assertThat(invoke(input)).isNull();
    }

    @Test
    @DisplayName("Returns null instead of throwing for an unparseable submission period")
    void returnsNullForUnparseableInput() throws Exception {
      assertThat(invoke("2026-01")).isNull();
    }
  }
}
