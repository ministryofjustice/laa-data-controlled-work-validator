package uk.gov.justice.laa.dstew.payments.claims.validation.core.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("StringCaseUtil")
class StringCaseUtilTest {

  @Nested
  @DisplayName("toTitleCase variations")
  class ToTitleCase {

    @Test
    @DisplayName("returns null when input is null")
    void returnsNullForNull() {
      assertThat(StringCaseUtil.toTitleCase(null)).isEmpty();
    }

    @Test
    @DisplayName("returns empty string when input is empty")
    void returnsEmptyForEmpty() {
      assertThat(StringCaseUtil.toTitleCase("")).isEmpty();
    }

    @ParameterizedTest
    @DisplayName("handles camelCase and underscores")
    @CsvSource(value = {"camelCaseField|Camel Case Field", "with_underscore_field|With Underscore Field"}, delimiter = '|')
    void handlesCamelAndUnderscore(String input, String expected) {
      assertThat(StringCaseUtil.toTitleCase(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("keeps stop words lowercase unless first")
    void keepsStopWords() {
      assertThat(StringCaseUtil.toTitleCase("the_lord_of_the_rings"))
          .isEqualTo("The Lord of the Rings");
    }

    @ParameterizedTest
    @DisplayName("always uppercase certain words like NIL")
    @CsvSource(value = {"nil value|NIL Value", "value NIL here|Value NIL Here"}, delimiter = '|')
    void alwaysUppercaseWords(String input, String expected) {
      assertThat(StringCaseUtil.toTitleCase(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("handles extra spaces and leading/trailing whitespace")
    void handlesExtraSpaces() {
      assertThat(StringCaseUtil.toTitleCase("  leading  and  multiple   spaces  "))
          .isEqualTo("Leading and Multiple Spaces");
    }

    @Test
    @DisplayName("only stop words should capitalise first and keep rest lowercase")
    void onlyStopWords() {
      assertThat(StringCaseUtil.toTitleCase("the_of_and"))
          .isEqualTo("The of and");
    }

    @ParameterizedTest
    @DisplayName("various edge cases for non-word inputs")
    @CsvSource(value = {
      "1234523453|1234523453",
      "!!!|!!!",
      "123_ABC_def|123 Abc Def",
      "__lead__trail__|Lead Trail"
    }, delimiter = '|')
    void edgeCases(String input, String expected) {
      assertThat(StringCaseUtil.toTitleCase(input)).isEqualTo(expected);
    }

    @Test
    @DisplayName("underscores-only input returns empty string")
    void underscoresOnly() {
      assertThat(StringCaseUtil.toTitleCase("___________")).isEmpty();
    }

    @Test
    @DisplayName("spaces-only input returns empty string")
    void spacesOnly() {
      assertThat(StringCaseUtil.toTitleCase("              ")).isEmpty();
    }
  }
}
