package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests for {@link ExclusionsRegistry}.
 *
 * <p>Verifies that the constant holds the correct set of field names excluded from
 * mandatory-field validation for disbursement-only claims, and that the list is immutable.
 */
@DisplayName("ExclusionsRegistry")
class ExclusionsRegistryTest {

  // ─────────────────────────────────────────────────────────────────────────
  // DISBURSEMENT_ONLY_EXCLUSIONS — content
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("DISBURSEMENT_ONLY_EXCLUSIONS — content")
  class DisbursementOnlyExclusionsContent {

    @Test
    @DisplayName("List is not null")
    void listIsNotNull() {
      assertThat(ExclusionsRegistry.DISBURSEMENT_ONLY_EXCLUSIONS).isNotNull();
    }

    @Test
    @DisplayName("List contains exactly 7 entries")
    void listHasCorrectSize() {
      assertThat(ExclusionsRegistry.DISBURSEMENT_ONLY_EXCLUSIONS).hasSize(7);
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
      assertThat(ExclusionsRegistry.DISBURSEMENT_ONLY_EXCLUSIONS).contains(fieldName);
    }

    @Test
    @DisplayName("List does not contain unexpected fields")
    void listDoesNotContainUnexpectedFields() {
      assertThat(ExclusionsRegistry.DISBURSEMENT_ONLY_EXCLUSIONS).doesNotContain(
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
      assertThat(ExclusionsRegistry.DISBURSEMENT_ONLY_EXCLUSIONS).containsExactly(
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
  // DISBURSEMENT_ONLY_EXCLUSIONS — immutability
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("DISBURSEMENT_ONLY_EXCLUSIONS — immutability")
  class DisbursementOnlyExclusionsImmutability {

    @Test
    @DisplayName("List is unmodifiable — add throws UnsupportedOperationException")
    void listIsUnmodifiableAdd() {
      List<String> exclusions = ExclusionsRegistry.DISBURSEMENT_ONLY_EXCLUSIONS;
      assertThrows(
          UnsupportedOperationException.class,
          () -> exclusions.add("newField")
      );
    }

    @Test
    @DisplayName("List is unmodifiable — remove throws UnsupportedOperationException")
    void listIsUnmodifiableRemove() {
      List<String> exclusions = ExclusionsRegistry.DISBURSEMENT_ONLY_EXCLUSIONS;
      assertThrows(
          UnsupportedOperationException.class,
          () -> exclusions.remove("adviceTime")
      );
    }
  }
}
