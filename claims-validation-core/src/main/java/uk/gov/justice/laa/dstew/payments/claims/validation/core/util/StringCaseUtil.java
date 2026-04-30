package uk.gov.justice.laa.dstew.payments.claims.validation.core.util;

import java.util.Locale;
import java.util.Set;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/** Utility class for string case conversions. */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringCaseUtil {

  private static final Set<String> STOP_WORDS =
      Set.of("of", "and", "the", "in", "on", "at", "for", "to", "with");

  private static final Set<String> ALWAYS_UPPERCASE = Set.of("NIL");

  /**
   * Converts a string to Title Case.
   *
   * @param input the input string
   * @return the converted Title Case string
   */
  public static String toTitleCase(String input) {
    if (input == null || input.isEmpty()) {
      return "";
    }

    // Normalize snake_case and camelCase, trim whitespace and collapse multiple spaces
    String normalized = input.replace("_", " ").replaceAll("([a-z])([A-Z])", "$1 $2").trim();
    if (normalized.isEmpty()) {
      return "";
    }

    StringBuilder result = new StringBuilder();
    String[] words = normalized.split("\\s+"); // split on one or more whitespace
    boolean first = true;

    for (String word : words) {
      String lowerWord = word.toLowerCase(Locale.ENGLISH);

      if (ALWAYS_UPPERCASE.contains(word.toUpperCase(Locale.ENGLISH))) {
        if (!first) {
          result.append(" ");
        }
        result.append(word.toUpperCase(Locale.ENGLISH));
      } else if (first || !STOP_WORDS.contains(lowerWord)) {
        if (!first) {
          result.append(" ");
        }
        result.append(Character.toUpperCase(lowerWord.charAt(0))).append(lowerWord.substring(1));
      } else {
        result.append(" ");
        result.append(lowerWord);
      }

      first = false;
    }

    return result.toString();
  }
}
