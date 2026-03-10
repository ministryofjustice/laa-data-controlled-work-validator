package uk.gov.justice.laa.dstew.payments.claims.validation.core.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Utility class for string case conversions. */
public final class StringCaseUtil {

  private StringCaseUtil() {
    // Utility class
  }

  /**
   * Converts a string to Title Case.
   *
   * @param input the input string
   * @return the converted Title Case string
   */
  public static String toTitleCase(String input) {
    if (input == null || input.isEmpty()) {
      return input;
    }

    // Words to keep lowercase unless they're the first word
    Set<String> stopWords =
        new HashSet<>(List.of("of", "and", "the", "in", "on", "at", "for", "to", "with"));

    // Words to always keep uppercase
    Set<String> alwaysUppercase = new HashSet<>(List.of("NIL"));

    // Normalize snake_case and camelCase
    String normalized = input.replace("_", " ");
    normalized = normalized.replaceAll("([a-z])([A-Z])", "$1 $2");

    // Build the result
    StringBuilder result = new StringBuilder();
    String[] words = normalized.split(" ");
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      String lowerWord = word.toLowerCase();

      if (alwaysUppercase.contains(word.toUpperCase())) {
        result.append(word.toUpperCase());
      } else if (i == 0 || !stopWords.contains(lowerWord)) {
        result.append(Character.toUpperCase(lowerWord.charAt(0))).append(lowerWord.substring(1));
      } else {
        result.append(lowerWord);
      }

      result.append(" ");
    }

    return result.toString().trim();
  }
}
