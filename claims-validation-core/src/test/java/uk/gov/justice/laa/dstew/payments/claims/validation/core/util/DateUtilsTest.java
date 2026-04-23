package uk.gov.justice.laa.dstew.payments.claims.validation.core.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;

@DisplayName("DateUtils")
class DateUtilsTest {

  @Nested
  @DisplayName("basic date checks")
  class Basic {

    @Test
    @DisplayName("isValidDate and isValidDateOfBirth behave correctly")
    void validDateChecks() {
      assertThat(DateUtils.isValidDate(null)).isFalse();
      assertThat(DateUtils.isValidDate(LocalDate.now())).isTrue();

      assertThat(DateUtils.isValidDateOfBirth(LocalDate.of(1899, 12, 31))).isFalse();
      assertThat(DateUtils.isValidDateOfBirth(LocalDate.now().minusYears(30))).isTrue();
    }

    @Test
    @DisplayName("isDateWithinRange respects inclusive bounds")
    void dateWithinRange() {
      LocalDate start = LocalDate.of(2020, 1, 1);
      LocalDate end = LocalDate.of(2020, 1, 31);
      assertThat(DateUtils.isDateWithinRange(start, start, end)).isTrue();
      assertThat(DateUtils.isDateWithinRange(end, start, end)).isTrue();
      assertThat(DateUtils.isDateWithinRange(start.minusDays(1), start, end)).isFalse();
    }
  }

  @Nested
  @DisplayName("parsing helpers")
  class Parsing {

    @Test
    @DisplayName("parseSubmissionPeriod handles case-insensitivity and invalid input")
    void parseSubmissionPeriodCases() {
      assertThat(DateUtils.parseSubmissionPeriod(null)).isNull();
      assertThat(DateUtils.parseSubmissionPeriod("")).isNull();
      assertThat(DateUtils.parseSubmissionPeriod("Jan-2026")).isEqualTo(YearMonth.of(2026, 1));
      assertThat(DateUtils.parseSubmissionPeriod("bad-format")).isNull();
    }

    @Test
    @DisplayName("parseDate returns null for blank/invalid and parses valid")
    void parseDateCases() {
      assertThat(DateUtils.parseDate(null)).isNull();
      assertThat(DateUtils.parseDate("")).isNull();
      assertThat(DateUtils.parseDate("2020-02-29")).isEqualTo(LocalDate.of(2020, 2, 29));
      assertThat(DateUtils.parseDate("not-a-date")).isNull();
    }

    @Test
    @DisplayName("getTwentiethOfNextMonth via reflection throws for blank and parses valid")
    void getTwentiethOfNextMonth_reflection() throws Exception {
      Method m = DateUtils.class.getDeclaredMethod("getTwentiethOfNextMonth", String.class);
      m.setAccessible(true);

      // valid
      Object res = m.invoke(null, "Jan-2026");
      assertThat(res).isEqualTo(LocalDate.of(2026, 2, 20));

      // blank should throw IllegalArgumentException
      try {
        m.invoke(null, "");
      } catch (java.lang.reflect.InvocationTargetException ite) {
        assertThat(ite.getCause()).isInstanceOf(IllegalArgumentException.class);
      }
    }

    @Test
    @DisplayName("submissionPeriodCutoffDate returns 20th of following month")
    void submissionPeriodCutoff() {
      YearMonth period = YearMonth.of(2025, 5);
      assertThat(DateUtils.submissionPeriodCutoffDate(period)).isEqualTo(LocalDate.of(2025, 6, 20));
    }
  }

  @Nested
  @DisplayName("validation behaviours")
  class ValidationBehaviours {

    @Test
    @DisplayName("validateDateInPast returns error for invalid format and empty for valid")
    void validateDateInPastCases() {
      List<ValidationIssue> issues =
          DateUtils.validateDateInPast("someField", "2020-01-01", LocalDate.of(1900, 1, 1));
      assertThat(issues).isEmpty();

      List<ValidationIssue> bad = DateUtils.validateDateInPast("someField", "bad", LocalDate.of(1900, 1, 1));
      assertThat(bad).hasSize(1);
      assertThat(bad.get(0).getCode()).isEqualTo("INVALID_DATE_FORMAT");
    }

    @Test
    @DisplayName("checkDateNotInFutureAndWithinAllowedPeriod checks future, early and late dates")
    void checkDateNotInFutureAndWithinAllowedPeriod() {
      Claim c = Claim.builder().submissionPeriod("JAN-2026").build();

      // future date
      String future = LocalDate.now().plusDays(1).format(DateUtils.DATE_FORMATTER_YYYY_MM_DD);
      var issues = DateUtils.checkDateNotInFutureAndWithinAllowedPeriod(c, "field", future, LocalDate.of(1900, 1, 1));
      assertThat(issues).hasSize(1);
      assertThat(issues.get(0).getTechnicalMessage()).contains("cannot be a future date");

      // early date
      Claim c2 = Claim.builder().submissionPeriod("JAN-2026").build();
      String early = LocalDate.of(1900, 1, 1).minusDays(1).format(DateUtils.DATE_FORMATTER_YYYY_MM_DD);
      var eIssues = DateUtils.checkDateNotInFutureAndWithinAllowedPeriod(c2, "field", early, LocalDate.of(1900, 1, 1));
      assertThat(eIssues).hasSize(1);
      assertThat(eIssues.get(0).getTechnicalMessage()).contains("cannot be before");

      // late date - pick a submission period and a date after 20th of next month
      Claim c3 = Claim.builder().submissionPeriod("JAN-2020").build();
      String late = LocalDate.of(2020, 3, 21).format(DateUtils.DATE_FORMATTER_YYYY_MM_DD);
      var lIssues = DateUtils.checkDateNotInFutureAndWithinAllowedPeriod(c3, "field", late, LocalDate.of(1900, 1, 1));
      assertThat(lIssues).hasSize(1);
      assertThat(lIssues.get(0).getTechnicalMessage()).contains("20th of the month following the submission period");
    }
  }
}
