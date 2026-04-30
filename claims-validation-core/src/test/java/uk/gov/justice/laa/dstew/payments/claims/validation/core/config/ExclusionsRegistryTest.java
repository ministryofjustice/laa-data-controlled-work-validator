package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests for {@link ExclusionsRegistry}.
 *
 * <p>Verifies that the registry holds the correct set of field names excluded from
 * mandatory-field validation for disbursement-only claims, and that the list is immutable.
 */
@DisplayName("ExclusionsRegistry")
class ExclusionsRegistryTest {

  private final ExclusionsRegistry registry = new ExclusionsRegistry();

  // ─────────────────────────────────────────────────────────────────────────
  // disbursementOnlyExclusions — content
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("disbursementOnlyExclusions — content")
  class DisbursementOnlyExclusionsContent {

    @Test
    @DisplayName("List is not null")
    void listIsNotNull() {
      assertThat(registry.getDisbursementOnlyExclusions()).isNotNull();
    }

    @Test
    @DisplayName("List contains exactly 7 entries")
    void listHasCorrectSize() {
      assertThat(registry.getDisbursementOnlyExclusions()).hasSize(7);
    }

    @ParameterizedTest(name = "contains ''{0}''")
    @DisplayName("List contains all expected field names")
    @ValueSource(strings = {
        "travelWaitingCostsAmount",
        "adviceTime",
        "travelTime",
        "waitingTime",
        "netCounselCostsAmount",
        "netProfitCostsAmount",
        "isVatApplicable"
    })
    void listContainsExpectedField(String fieldName) {
      assertThat(registry.getDisbursementOnlyExclusions()).contains(fieldName);
    }

    @Test
    @DisplayName("List does not contain unexpected fields")
    void listDoesNotContainUnexpectedFields() {
      List<String> exclusions = registry.getDisbursementOnlyExclusions();
      assertThat(exclusions).doesNotContain(
          "uniqueFileNumber",
          "caseStartDate",
          "clientForename",
          "clientSurname",
          "outcomeCode"
      );
    }

    @Test
    @DisplayName("List preserves insertion order")
    void listPreservesOrder() {
      assertThat(registry.getDisbursementOnlyExclusions()).containsExactly(
          "travelWaitingCostsAmount",
          "adviceTime",
          "travelTime",
          "waitingTime",
          "netCounselCostsAmount",
          "netProfitCostsAmount",
          "isVatApplicable"
      );
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // disbursementOnlyExclusions — immutability
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("disbursementOnlyExclusions — immutability")
  class DisbursementOnlyExclusionsImmutability {

    @Test
    @DisplayName("List is unmodifiable — add throws UnsupportedOperationException")
    void listIsUnmodifiableAdd() {
      List<String> exclusions = registry.getDisbursementOnlyExclusions();
      org.junit.jupiter.api.Assertions.assertThrows(
          UnsupportedOperationException.class,
          () -> exclusions.add("newField")
      );
    }

    @Test
    @DisplayName("List is unmodifiable — remove throws UnsupportedOperationException")
    void listIsUnmodifiableRemove() {
      List<String> exclusions = registry.getDisbursementOnlyExclusions();
      org.junit.jupiter.api.Assertions.assertThrows(
          UnsupportedOperationException.class,
          () -> exclusions.remove("adviceTime")
      );
    }

    @Test
    @DisplayName("Same instance is returned on multiple calls")
    void sameInstanceReturnedOnMultipleCalls() {
      assertThat(registry.getDisbursementOnlyExclusions())
          .isSameAs(registry.getDisbursementOnlyExclusions());
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Multiple registry instances
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Multiple registry instances")
  class MultipleRegistryInstances {

    @Test
    @DisplayName("Two separate instances return equal lists")
    void twoInstancesReturnEqualLists() {
      ExclusionsRegistry other = new ExclusionsRegistry();
      assertThat(registry.getDisbursementOnlyExclusions())
          .isEqualTo(other.getDisbursementOnlyExclusions());
    }
  }
}
