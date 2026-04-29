package uk.gov.justice.laa.dstew.payments.claims.validation.core.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * Tests for {@link ValidationResult}.
 */
@DisplayName("ValidationResult")
class ValidationResultTest {

  private static ValidationIssue issue(String code) {
    return ValidationIssue.builder()
        .code(code)
        .message("msg")
        .severity(ValidationSeverity.INFO)
        .build();
  }

  @Nested
  @DisplayName("addIssuesItem")
  class AddIssuesItem {

    @Test
    @DisplayName("Initialises the issues list and adds the item when issues is null")
    void initialisesListAndAddsItemWhenIssuesIsNull() {
      ValidationResult result = new ValidationResult();
      result.setIssues(null);

      result.addIssuesItem(issue("A"));

      assertThat(result.getIssues()).hasSize(1);
      assertThat(result.getIssues().getFirst().getCode()).isEqualTo("A");
    }

    @Test
    @DisplayName("Appends to the existing non-null list without reinitialising it")
    void appendsToExistingListWithoutReinitialising() {
      ValidationResult result = new ValidationResult();
      // Default construction gives a non-null empty list — add directly to it
      result.addIssuesItem(issue("A"));
      List<ValidationIssue> listBefore = result.getIssues();

      result.addIssuesItem(issue("B"));

      // Same list instance — not replaced
      assertThat(result.getIssues()).isSameAs(listBefore);
      assertThat(result.getIssues()).extracting(ValidationIssue::getCode)
          .containsExactly("A", "B");
    }

    @Test
    @DisplayName("Appends to a pre-populated list supplied via setIssues")
    void appendsToPrePopulatedList() {
      ValidationResult result = new ValidationResult();
      result.setIssues(new ArrayList<>(List.of(issue("A"))));

      result.addIssuesItem(issue("B"));

      assertThat(result.getIssues()).hasSize(2);
      assertThat(result.getIssues()).extracting(ValidationIssue::getCode)
          .containsExactly("A", "B");
    }

    @Test
    @DisplayName("Returns this instance (fluent API)")
    void returnsThisInstance() {
      ValidationResult result = new ValidationResult();

      ValidationResult returned = result.addIssuesItem(issue("A"));

      assertThat(returned).isSameAs(result);
    }

    @Test
    @DisplayName("Supports chaining multiple items")
    void supportsChaining() {
      ValidationResult result = new ValidationResult();

      result.addIssuesItem(issue("A"))
            .addIssuesItem(issue("B"))
            .addIssuesItem(issue("C"));

      assertThat(result.getIssues()).extracting(ValidationIssue::getCode)
          .containsExactly("A", "B", "C");
    }
  }

  @Nested
  @DisplayName("Default state")
  class DefaultState {

    @Test
    @DisplayName("Issues list is initialised to empty by default (not null)")
    void issuesListIsEmptyByDefault() {
      ValidationResult result = new ValidationResult();

      assertThat(result.getIssues()).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("isValid is null by default")
    void isValidIsNullByDefault() {
      ValidationResult result = new ValidationResult();

      assertThat(result.getIsValid()).isNull();
    }
  }
}
