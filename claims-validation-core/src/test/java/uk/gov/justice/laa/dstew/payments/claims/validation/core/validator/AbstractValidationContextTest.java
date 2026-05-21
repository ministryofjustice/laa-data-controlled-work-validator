package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;

@DisplayName("AbstractValidationContext — field-based deduplication")
class AbstractValidationContextTest {

  private AbstractValidationContext context;

  @BeforeEach
  void beforeEach() {
    context = ClaimValidationContext.builder().build();
  }

  @Test
  @DisplayName("Deduplicates by field — only first with non-null field is kept")
  void deduplicatesByField_onlyFirstNonNullFieldKept() {
    ValidationIssue issue1 = ValidationIssue.builder()
        .code("ERR1")
        .message("First error for fieldA")
        .path("fieldA")
        .severity(ValidationSeverity.ERROR)
        .build();

    ValidationIssue issue2 = ValidationIssue.builder()
        .code("ERR2")
        .message("Second error for fieldA")
        .path("fieldA")
        .severity(ValidationSeverity.ERROR)
        .build();

    context.addValidationIssues(List.of(issue1, issue2));

    List<ValidationIssue> result = context.getIssues();
    assertThat(result).hasSize(1).extracting(ValidationIssue::getPath).containsOnly("fieldA");
    assertThat(result.getFirst()).extracting(ValidationIssue::getCode).isEqualTo("ERR1");
  }

  @Test
  @DisplayName("Null field issues are always added (no deduplication)")
  void nullFieldIssues_alwaysAdded_noDuplication() {
    ValidationIssue nullField1 = ValidationIssue.builder()
        .code("ERR1")
        .message("First error with no field")
        .path(null)
        .severity(ValidationSeverity.ERROR)
        .build();

    ValidationIssue nullField2 = ValidationIssue.builder()
        .code("ERR2")
        .message("Second error with no field")
        .path(null)
        .severity(ValidationSeverity.ERROR)
        .build();

    context.addValidationIssues(List.of(nullField1, nullField2));

    List<ValidationIssue> result = context.getIssues();
    assertThat(result).hasSize(2)
            .allMatch(issue -> issue.getPath() == null);
  }

  @Test
  @DisplayName("Mix of null and non-null fields: nulls always added, non-nulls deduplicated")
  void mixedNullAndNonNull_nullsAdded_nonNullsDeduplicated() {
    ValidationIssue nullField1 = ValidationIssue.builder()
        .code("WARN1")
        .message("Warning without field")
        .path(null)
        .severity(ValidationSeverity.WARNING)
        .build();

    ValidationIssue withFieldA = ValidationIssue.builder()
        .code("ERR1")
        .message("Error for fieldA")
        .path("fieldA")
        .severity(ValidationSeverity.ERROR)
        .build();

    ValidationIssue withFieldADup = ValidationIssue.builder()
        .code("ERR1_DUP")
        .message("Duplicate error for fieldA")
        .path("fieldA")
        .severity(ValidationSeverity.ERROR)
        .build();

    ValidationIssue nullField2 = ValidationIssue.builder()
        .code("WARN2")
        .message("Another warning without field")
        .path(null)
        .severity(ValidationSeverity.WARNING)
        .build();

    ValidationIssue withFieldB = ValidationIssue.builder()
        .code("ERR2")
        .message("Error for fieldB")
        .path("fieldB")
        .severity(ValidationSeverity.ERROR)
        .build();

    context.addValidationIssues(
        List.of(nullField1, withFieldA, withFieldADup, nullField2, withFieldB));

    List<ValidationIssue> result = context.getIssues();
    // nullField1, withFieldA, nullField2, withFieldB (not withFieldADup)
    assertThat(result).hasSize(4);
    assertThat(result).extracting(ValidationIssue::getCode).containsExactly("WARN1", "ERR1", "WARN2", "ERR2");
  }

  @Test
  @DisplayName("Multiple calls accumulate and deduplicate across boundaries")
  void multipleCalls_accumulateAndDeduplicateAcrossBoundaries() {
    ValidationIssue issue1 = ValidationIssue.builder()
        .code("ERR1")
        .message("First")
        .path("fieldX")
        .severity(ValidationSeverity.ERROR)
        .build();

    context.addValidationIssue(issue1);

    ValidationIssue issue2Dup = ValidationIssue.builder()
        .code("ERR2")
        .message("Duplicate fieldX")
        .path("fieldX")
        .severity(ValidationSeverity.ERROR)
        .build();

    ValidationIssue issue3 = ValidationIssue.builder()
        .code("ERR3")
        .message("Different field")
        .path("fieldY")
        .severity(ValidationSeverity.ERROR)
        .build();

    context.addValidationIssues(List.of(issue2Dup, issue3));

    List<ValidationIssue> result = context.getIssues();
    // Only issue1 (fieldX), issue3 (fieldY) — issue2Dup is filtered out
    assertThat(result).hasSize(2);
    assertThat(result).extracting(ValidationIssue::getCode).containsExactly("ERR1", "ERR3");
  }

  @Test
  @DisplayName("Empty list has no effect")
  void emptyList_hasNoEffect() {
    ValidationIssue issue = ValidationIssue.builder()
        .code("ERR1")
        .message("Test")
        .path("fieldA")
        .severity(ValidationSeverity.ERROR)
        .build();

    context.addValidationIssue(issue);
    context.addValidationIssues(List.of());

    List<ValidationIssue> result = context.getIssues();
    assertThat(result).hasSize(1).containsOnly(issue);
  }
}
