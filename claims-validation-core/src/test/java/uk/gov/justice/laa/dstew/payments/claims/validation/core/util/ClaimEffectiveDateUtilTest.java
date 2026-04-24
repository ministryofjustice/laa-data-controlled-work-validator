package uk.gov.justice.laa.dstew.payments.claims.validation.core.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.DateTimeException;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;

@DisplayName("ClaimEffectiveDateUtil")
class ClaimEffectiveDateUtilTest {

  @Nested
  @DisplayName("effective date resolution order")
  class EffectiveDateOrder {

    @Test
    @DisplayName("PROD fee code uses case concluded date when present")
    void prodUsesCaseConcluded() {
      Claim c = new Claim();
      c.setFeeCode("PROD");
      c.setCaseConcludedDate("2020-02-03");
      assertThat(ClaimEffectiveDateUtil.getEffectiveDate(c)).isEqualTo(LocalDate.of(2020, 2, 3));
    }

    @Test
    @DisplayName("uses case start date when present for non-PROD")
    void usesCaseStartDate() {
      Claim c = new Claim();
      c.setFeeCode("OTHER");
      c.setCaseStartDate("2021-05-06");
      assertThat(ClaimEffectiveDateUtil.getEffectiveDate(c)).isEqualTo(LocalDate.of(2021, 5, 6));
    }

    @Test
    @DisplayName("falls back to representation order date")
    void fallsBackToRepresentationOrderDate() {
      Claim c = new Claim();
      c.setRepresentationOrderDate("2022-07-08");
      assertThat(ClaimEffectiveDateUtil.getEffectiveDate(c)).isEqualTo(LocalDate.of(2022, 7, 8));
    }

    @Test
    @DisplayName("parses UFN into date using 2-digit year logic")
    void parseUniqueFileNumberYears() {
      Claim c1 = new Claim();
      c1.setUniqueFileNumber("010160/001"); // 01-01-60 -> 1960
      assertThat(ClaimEffectiveDateUtil.getEffectiveDate(c1)).isEqualTo(LocalDate.of(1960, 1, 1));

      Claim c2 = new Claim();
      c2.setUniqueFileNumber("010120/001"); // 01-01-20 -> 2020
      assertThat(ClaimEffectiveDateUtil.getEffectiveDate(c2)).isEqualTo(LocalDate.of(2020, 1, 1));
    }

    @Test
    @DisplayName("throws for invalid UFN format")
    void invalidUfnThrows() {
      Claim c = new Claim();
      c.setUniqueFileNumber("bad-ufn");
      assertThatThrownBy(() -> ClaimEffectiveDateUtil.getEffectiveDate(c))
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessageContaining("Invalid format for unique file number");
    }

    @Test
    @DisplayName("PROD fee code falls back to other fields when caseConcludedDate missing")
    void prodFallbackWhenNoConcluded() {
      Claim c = new Claim();
      c.setFeeCode("PROD");
      c.setCaseStartDate("2021-01-02");
      assertThat(ClaimEffectiveDateUtil.getEffectiveDate(c)).isEqualTo(LocalDate.of(2021, 1, 2));
    }

    @Test
    @DisplayName("throws if no date fields available")
    void throwsWhenNoDateFields() {
      Claim c = new Claim();
      c.setId(java.util.UUID.randomUUID());
      assertThatThrownBy(() -> ClaimEffectiveDateUtil.getEffectiveDate(c)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("throws when date field has invalid format")
    void throwsWhenInvalidDateFormat() {
      Claim c = new Claim();
      c.setCaseStartDate("not-a-date");
      assertThatThrownBy(() -> ClaimEffectiveDateUtil.getEffectiveDate(c)).isInstanceOf(IllegalArgumentException.class);
    }
  }

  @Nested
  @DisplayName("parseUniqueFileNumber direct tests")
  class ParseUniqueFileNumberTests {

    @ParameterizedTest(name = "valid UFN {0} -> {1}")
    @DisplayName("parses valid UFNs to expected dates")
    @CsvSource({
      "010160/001,1960-01-01",
      "010120/001,2020-01-01",
      "311299/001,1999-12-31",
      "290200/001,2000-02-29"
    })
    void parsesValidUfns(String ufn, String expectedIso) {
      LocalDate res = ClaimEffectiveDateUtil.parseUniqueFileNumber(ufn);
      assertThat(res).isEqualTo(LocalDate.parse(expectedIso));
    }

    @ParameterizedTest(name = "invalid format: {0}")
    @DisplayName("throws IllegalArgumentException for malformed UFNs")
    @ValueSource(strings = {"bad-ufn", "123", "12/123", "", "abc_def/ghi"})
    void malformedUfnsThrow(String ufn) {
      assertThatThrownBy(() -> ClaimEffectiveDateUtil.parseUniqueFileNumber(ufn))
          .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "invalid date part: {0}")
    @DisplayName("throws DateTimeException for UFNs with invalid date portion")
    @ValueSource(strings = {"320120/001", "310299/001"})
    void invalidDatePortionThrows(String ufn) {
      assertThatThrownBy(() -> ClaimEffectiveDateUtil.parseUniqueFileNumber(ufn))
          .isInstanceOf(DateTimeException.class);
    }

    @Test
    @DisplayName("null UFN throws IllegalArgumentException")
    void nullUfnThrows() {
      assertThatThrownBy(() -> ClaimEffectiveDateUtil.parseUniqueFileNumber(null))
          .isInstanceOf(IllegalArgumentException.class);
    }
  }
}
