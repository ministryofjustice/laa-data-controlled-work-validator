package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;

@DisplayName("AbstractValidationContext")
class AbstractValidationContextTest {

  private AbstractValidationContext context;

  // ── helpers ──────────────────────────────────────────────────────────────

  private static ValidationIssue error(String code, String path) {
    return ValidationIssue.builder()
        .code(code).message(code).path(path).severity(ValidationSeverity.ERROR).build();
  }

  private static ValidationIssue warning(String code, String path) {
    return ValidationIssue.builder()
        .code(code).message(code).path(path).severity(ValidationSeverity.WARNING).build();
  }

  @BeforeEach
  void beforeEach() {
    context = ClaimValidationContext.builder().build();
  }

  // ── addValidationIssues — guard clauses ──────────────────────────────────

  @Test
  @DisplayName("addValidationIssues: null list is ignored")
  void addValidationIssues_nullList_ignored() {
    context.addValidationIssues(null);
    assertThat(context.getIssues()).isEmpty();
    assertThat(context.getIssueCount()).isZero();
  }

  @Test
  @DisplayName("addValidationIssues: empty list has no effect")
  void addValidationIssues_emptyList_noEffect() {
    context.addValidationIssue(error("ERR1", "fieldA"));
    context.addValidationIssues(List.of());
    assertThat(context.getIssues()).hasSize(1);
  }

  // ── getIssues — deduplication (read-time) ────────────────────────────────

  @Test
  @DisplayName("getIssues: deduplicates by path — only first with non-null path is returned")
  void getIssues_deduplicatesByPath_onlyFirstReturned() {
    ValidationIssue first = error("ERR1", "fieldA");
    ValidationIssue second = error("ERR2", "fieldA");

    context.addValidationIssues(List.of(first, second));

    List<ValidationIssue> result = context.getIssues();
    assertThat(result).hasSize(1);
    assertThat(result.getFirst().getCode()).isEqualTo("ERR1");
  }

  @Test
  @DisplayName("getIssues: null-path issues are always returned (no deduplication)")
  void getIssues_nullPathIssues_alwaysReturned() {
    context.addValidationIssues(List.of(error("ERR1", null), error("ERR2", null)));
    assertThat(context.getIssues()).hasSize(2);
  }

  @Test
  @DisplayName("getIssues: mix of null-path and non-null-path — nulls all kept, non-nulls deduplicated")
  void getIssues_mixed_nullsKept_nonNullsDeduplicated() {
    context.addValidationIssues(List.of(
        warning("WARN1", null),
        error("ERR1", "fieldA"),
        error("ERR1_DUP", "fieldA"),   // duplicate — should be dropped
        warning("WARN2", null),
        error("ERR2", "fieldB")));

    List<ValidationIssue> result = context.getIssues();
    assertThat(result).hasSize(4);
    assertThat(result).extracting(ValidationIssue::getCode)
        .containsExactly("WARN1", "ERR1", "WARN2", "ERR2");
  }

  @Test
  @DisplayName("getIssues: deduplicates across multiple addValidationIssue calls")
  void getIssues_deduplicatesAcrossMultipleCalls() {
    context.addValidationIssue(error("ERR1", "fieldX"));
    context.addValidationIssues(List.of(error("ERR2", "fieldX"), error("ERR3", "fieldY")));

    List<ValidationIssue> result = context.getIssues();
    assertThat(result).hasSize(2);
    assertThat(result).extracting(ValidationIssue::getCode).containsExactly("ERR1", "ERR3");
  }

  @Test
  @DisplayName("getIssues: returns new list each call (defensive copy)")
  void getIssues_returnsDefensiveCopy() {
    context.addValidationIssue(error("ERR1", "fieldA"));
    List<ValidationIssue> first = context.getIssues();
    List<ValidationIssue> second = context.getIssues();
    assertThat(first).isNotSameAs(second).isEqualTo(second);
  }

  // ── getAllIssues — raw (no deduplication) ────────────────────────────────

  @Test
  @DisplayName("getAllIssues: returns all issues including duplicates by path")
  void getAllIssues_returnsAll_includingDuplicates() {
    ValidationIssue first = error("ERR1", "fieldA");
    ValidationIssue second = error("ERR2", "fieldA");

    context.addValidationIssues(List.of(first, second));

    assertThat(context.getAllIssues()).hasSize(2);
    assertThat(context.getAllIssues()).extracting(ValidationIssue::getCode)
        .containsExactly("ERR1", "ERR2");
  }

  @Test
  @DisplayName("getAllIssues: returns new list each call (defensive copy)")
  void getAllIssues_returnsDefensiveCopy() {
    context.addValidationIssue(error("ERR1", "fieldA"));
    List<ValidationIssue> first = context.getAllIssues();
    List<ValidationIssue> second = context.getAllIssues();
    assertThat(first).isNotSameAs(second).isEqualTo(second);
  }

  // ── hasErrors ────────────────────────────────────────────────────────────

  @Test
  @DisplayName("hasErrors: returns false when no issues")
  void hasErrors_noIssues_returnsFalse() {
    assertThat(context.hasErrors()).isFalse();
  }

  @Test
  @DisplayName("hasErrors: returns false when only warnings present")
  void hasErrors_onlyWarnings_returnsFalse() {
    context.addValidationIssue(warning("WARN1", null));
    assertThat(context.hasErrors()).isFalse();
  }

  @Test
  @DisplayName("hasErrors: returns true when at least one ERROR present")
  void hasErrors_withError_returnsTrue() {
    context.addValidationIssue(warning("WARN1", null));
    context.addValidationIssue(error("ERR1", "fieldA"));
    assertThat(context.hasErrors()).isTrue();
  }

  @Test
  @DisplayName("hasErrors: considers all issues including duplicates (not deduplicated view)")
  void hasErrors_considersAllIssues() {
    // Two errors on same path — both stored raw; hasErrors should still return true
    context.addValidationIssues(List.of(error("ERR1", "fieldA"), error("ERR2", "fieldA")));
    assertThat(context.hasErrors()).isTrue();
  }

  // ── getIssueCount ────────────────────────────────────────────────────────

  @Test
  @DisplayName("getIssueCount: returns zero when empty")
  void getIssueCount_empty_returnsZero() {
    assertThat(context.getIssueCount()).isZero();
  }

  @Test
  @DisplayName("getIssueCount: matches getIssues().size() (deduplicated)")
  void getIssueCount_matchesDeduplicatedSize() {
    context.addValidationIssues(List.of(error("ERR1", "fieldA"), error("ERR2", "fieldA")));
    // deduplicated = 1; raw = 2
    assertThat(context.getIssueCount()).isEqualTo(1);
    assertThat(context.getIssueCount()).isEqualTo(context.getIssues().size());
  }

  // ── getErrors ────────────────────────────────────────────────────────────

  @Test
  @DisplayName("getErrors: returns empty when no issues")
  void getErrors_empty_returnsEmpty() {
    assertThat(context.getErrors()).isEmpty();
  }

  @Test
  @DisplayName("getErrors: excludes warnings, includes only errors")
  void getErrors_excludesWarnings() {
    context.addValidationIssue(warning("WARN1", null));
    context.addValidationIssue(error("ERR1", "fieldA"));
    assertThat(context.getErrors()).hasSize(1);
    assertThat(context.getErrors().getFirst().getCode()).isEqualTo("ERR1");
  }

  @Test
  @DisplayName("getErrors: deduplicates by path (first error per path wins)")
  void getErrors_deduplicatesByPath() {
    context.addValidationIssues(List.of(
        error("ERR1", "fieldA"),
        error("ERR2", "fieldA"),  // duplicate path — dropped
        error("ERR3", "fieldB")));
    List<ValidationIssue> errors = context.getErrors();
    assertThat(errors).hasSize(2);
    assertThat(errors).extracting(ValidationIssue::getCode).containsExactly("ERR1", "ERR3");
  }

  @Test
  @DisplayName("getErrors: null-path errors are always included")
  void getErrors_nullPathErrors_alwaysIncluded() {
    context.addValidationIssues(List.of(error("ERR1", null), error("ERR2", null)));
    assertThat(context.getErrors()).hasSize(2);
  }

  @Test
  @DisplayName("getErrors: warnings interspersed do not affect error deduplication")
  void getErrors_warningsInterspersed_doNotAffectDedup() {
    context.addValidationIssues(List.of(
        warning("WARN1", "fieldA"),  // warning for fieldA — should NOT claim the path slot
        error("ERR1", "fieldA"),
        error("ERR2", "fieldA")));   // duplicate error path — dropped
    List<ValidationIssue> errors = context.getErrors();
    assertThat(errors).hasSize(1);
    assertThat(errors.getFirst().getCode()).isEqualTo("ERR1");
  }

  @Test
  @DisplayName("getErrors: returns defensive copy")
  void getErrors_returnsDefensiveCopy() {
    context.addValidationIssue(error("ERR1", "fieldA"));
    assertThat(context.getErrors()).isNotSameAs(context.getErrors());
  }

  // ── getAllErrors ──────────────────────────────────────────────────────────

  @Test
  @DisplayName("getAllErrors: returns empty when no issues")
  void getAllErrors_empty_returnsEmpty() {
    assertThat(context.getAllErrors()).isEmpty();
  }

  @Test
  @DisplayName("getAllErrors: returns all errors including path-duplicates, excludes warnings")
  void getAllErrors_includesPathDuplicates_excludesWarnings() {
    context.addValidationIssues(List.of(
        warning("WARN1", null),
        error("ERR1", "fieldA"),
        error("ERR2", "fieldA"),   // duplicate path — still returned by getAllErrors
        error("ERR3", "fieldB")));
    List<ValidationIssue> all = context.getAllErrors();
    assertThat(all).hasSize(3);
    assertThat(all).extracting(ValidationIssue::getCode).containsExactly("ERR1", "ERR2", "ERR3");
  }

  @Test
  @DisplayName("getAllErrors: returns defensive copy")
  void getAllErrors_returnsDefensiveCopy() {
    context.addValidationIssue(error("ERR1", "fieldA"));
    assertThat(context.getAllErrors()).isNotSameAs(context.getAllErrors());
  }

  // ── getErrorCount ─────────────────────────────────────────────────────────

  @Test
  @DisplayName("getErrorCount: returns zero when no issues")
  void getErrorCount_empty_returnsZero() {
    assertThat(context.getErrorCount()).isZero();
  }

  @Test
  @DisplayName("getErrorCount: excludes warnings")
  void getErrorCount_excludesWarnings() {
    context.addValidationIssue(warning("WARN1", null));
    context.addValidationIssue(warning("WARN2", "fieldA"));
    assertThat(context.getErrorCount()).isZero();
  }

  @Test
  @DisplayName("getErrorCount: matches getErrors().size() (deduplicated)")
  void getErrorCount_matchesDeduplicatedErrorSize() {
    context.addValidationIssues(List.of(
        error("ERR1", "fieldA"),
        error("ERR2", "fieldA"),   // path-duplicate — deduplicated away
        error("ERR3", "fieldB"),
        warning("WARN1", null)));
    // deduplicated errors = 2 (fieldA, fieldB); raw errors = 3
    assertThat(context.getErrorCount()).isEqualTo(2);
    assertThat(context.getErrorCount()).isEqualTo(context.getErrors().size());
  }
}
