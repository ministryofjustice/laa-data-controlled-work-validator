package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

/**
 * Tests for {@link MandatoryFieldsRegistry}.
 *
 * <p>Verifies that each area-of-law list contains the correct mandatory fields,
 * that the map correctly maps areas of law to their respective field lists,
 * and that all lists are immutable.
 */
@DisplayName("MandatoryFieldsRegistry")
class MandatoryFieldsRegistryTest {

  private final MandatoryFieldsRegistry registry = new MandatoryFieldsRegistry();

  // ─────────────────────────────────────────────────────────────────────────
  // legalHelpMandatoryFields
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("legalHelpMandatoryFields")
  class LegalHelpMandatoryFields {

    @Test
    @DisplayName("List is not null")
    void listIsNotNull() {
      assertThat(registry.getLegalHelpMandatoryFields()).isNotNull();
    }

    @Test
    @DisplayName("List contains exactly 23 entries")
    void listHasCorrectSize() {
      assertThat(registry.getLegalHelpMandatoryFields()).hasSize(23);
    }

    @ParameterizedTest(name = "contains ''{0}''")
    @DisplayName("List contains all expected Legal Help mandatory fields")
    @ValueSource(strings = {
        "uniqueFileNumber",
        "caseStartDate",
        "caseConcludedDate",
        "outcomeCode",
        "travelWaitingCostsAmount",
        "clientForename",
        "clientSurname",
        "clientDateOfBirth",
        "uniqueClientNumber",
        "clientPostcode",
        "genderCode",
        "ethnicityCode",
        "disabilityCode",
        "adviceTime",
        "travelTime",
        "waitingTime",
        "netCounselCostsAmount",
        "caseId",
        "caseReferenceNumber",
        "scheduleReference",
        "matterTypeCode",
        "netProfitCostsAmount",
        "isVatApplicable"
    })
    void listContainsExpectedField(String fieldName) {
      assertThat(registry.getLegalHelpMandatoryFields()).contains(fieldName);
    }

    @Test
    @DisplayName("List does not contain Crime Lower specific fields")
    void listDoesNotContainCrimeLowerFields() {
      assertThat(registry.getLegalHelpMandatoryFields())
          .doesNotContain("stageReachedCode", "disbursementsVatAmount");
    }

    @Test
    @DisplayName("List is unmodifiable")
    void listIsUnmodifiable() {
      List<String> fields = registry.getLegalHelpMandatoryFields();
      org.junit.jupiter.api.Assertions.assertThrows(
          UnsupportedOperationException.class,
          () -> fields.add("newField")
      );
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // crimeLowerMandatoryFields
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("crimeLowerMandatoryFields")
  class CrimeLowerMandatoryFields {

    @Test
    @DisplayName("List is not null")
    void listIsNotNull() {
      assertThat(registry.getCrimeLowerMandatoryFields()).isNotNull();
    }

    @Test
    @DisplayName("List contains exactly 4 entries")
    void listHasCorrectSize() {
      assertThat(registry.getCrimeLowerMandatoryFields()).hasSize(4);
    }

    @ParameterizedTest(name = "contains ''{0}''")
    @DisplayName("List contains all expected Crime Lower mandatory fields")
    @ValueSource(strings = {
        "caseConcludedDate",
        "stageReachedCode",
        "netProfitCostsAmount",
        "disbursementsVatAmount"
    })
    void listContainsExpectedField(String fieldName) {
      assertThat(registry.getCrimeLowerMandatoryFields()).contains(fieldName);
    }

    @Test
    @DisplayName("List does not contain Legal Help specific fields")
    void listDoesNotContainLegalHelpFields() {
      assertThat(registry.getCrimeLowerMandatoryFields())
          .doesNotContain(
              "uniqueFileNumber",
              "clientForename",
              "clientSurname",
              "outcomeCode",
              "isVatApplicable"
          );
    }

    @Test
    @DisplayName("List is unmodifiable")
    void listIsUnmodifiable() {
      List<String> fields = registry.getCrimeLowerMandatoryFields();
      org.junit.jupiter.api.Assertions.assertThrows(
          UnsupportedOperationException.class,
          () -> fields.add("newField")
      );
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // mediationMandatoryFields
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("mediationMandatoryFields")
  class MediationMandatoryFields {

    @Test
    @DisplayName("List is not null")
    void listIsNotNull() {
      assertThat(registry.getMediationMandatoryFields()).isNotNull();
    }

    @Test
    @DisplayName("List contains exactly 17 entries")
    void listHasCorrectSize() {
      assertThat(registry.getMediationMandatoryFields()).hasSize(17);
    }

    @ParameterizedTest(name = "contains ''{0}''")
    @DisplayName("List contains all expected Mediation mandatory fields")
    @ValueSource(strings = {
        "outreachLocation",
        "referralSource",
        "clientForename",
        "clientSurname",
        "clientDateOfBirth",
        "uniqueClientNumber",
        "clientPostcode",
        "genderCode",
        "ethnicityCode",
        "disabilityCode",
        "isLegallyAided",
        "caseId",
        "caseStartDate",
        "caseReferenceNumber",
        "scheduleReference",
        "matterTypeCode",
        "uniqueCaseId"
    })
    void listContainsExpectedField(String fieldName) {
      assertThat(registry.getMediationMandatoryFields()).contains(fieldName);
    }

    @Test
    @DisplayName("List does not contain Crime Lower or Legal Help specific fields")
    void listDoesNotContainOtherAreaFields() {
      assertThat(registry.getMediationMandatoryFields())
          .doesNotContain(
              "stageReachedCode",
              "disbursementsVatAmount",
              "isVatApplicable",
              "adviceTime",
              "travelTime"
          );
    }

    @Test
    @DisplayName("List is unmodifiable")
    void listIsUnmodifiable() {
      List<String> fields = registry.getMediationMandatoryFields();
      org.junit.jupiter.api.Assertions.assertThrows(
          UnsupportedOperationException.class,
          () -> fields.add("newField")
      );
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // mandatoryFieldsByAreaOfLaw map
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("mandatoryFieldsByAreaOfLaw map")
  class MandatoryFieldsByAreaOfLaw {

    @Test
    @DisplayName("Map is not null")
    void mapIsNotNull() {
      assertThat(registry.getMandatoryFieldsByAreaOfLaw()).isNotNull();
    }

    @Test
    @DisplayName("Map contains exactly 3 entries")
    void mapHasCorrectSize() {
      assertThat(registry.getMandatoryFieldsByAreaOfLaw()).hasSize(3);
    }

    @ParameterizedTest(name = "map contains key {0}")
    @DisplayName("Map contains an entry for each supported AreaOfLaw")
    @EnumSource(value = AreaOfLaw.class, names = {"LEGAL_HELP", "CRIME_LOWER", "MEDIATION"})
    void mapContainsExpectedKeys(AreaOfLaw areaOfLaw) {
      assertThat(registry.getMandatoryFieldsByAreaOfLaw()).containsKey(areaOfLaw);
    }

    @Test
    @DisplayName("LEGAL_HELP key maps to the legalHelpMandatoryFields list")
    void legalHelpKeyMapsToCorrectList() {
      assertThat(registry.getMandatoryFieldsByAreaOfLaw().get(AreaOfLaw.LEGAL_HELP))
          .isSameAs(registry.getLegalHelpMandatoryFields());
    }

    @Test
    @DisplayName("CRIME_LOWER key maps to the crimeLowerMandatoryFields list")
    void crimeLowerKeyMapsToCorrectList() {
      assertThat(registry.getMandatoryFieldsByAreaOfLaw().get(AreaOfLaw.CRIME_LOWER))
          .isSameAs(registry.getCrimeLowerMandatoryFields());
    }

    @Test
    @DisplayName("MEDIATION key maps to the mediationMandatoryFields list")
    void mediationKeyMapsToCorrectList() {
      assertThat(registry.getMandatoryFieldsByAreaOfLaw().get(AreaOfLaw.MEDIATION))
          .isSameAs(registry.getMediationMandatoryFields());
    }

    @Test
    @DisplayName("Map is unmodifiable")
    void mapIsUnmodifiable() {
      Map<AreaOfLaw, List<String>> map = registry.getMandatoryFieldsByAreaOfLaw();
      org.junit.jupiter.api.Assertions.assertThrows(
          UnsupportedOperationException.class,
          () -> map.put(AreaOfLaw.LEGAL_HELP, List.of())
      );
    }

    @Test
    @DisplayName("Areas of law have non-overlapping mandatory field sets")
    void areasOfLawHaveNonOverlappingFields() {
      List<String> legalHelp = registry.getLegalHelpMandatoryFields();
      List<String> crimeLower = registry.getCrimeLowerMandatoryFields();
      List<String> mediation = registry.getMediationMandatoryFields();

      // stageReachedCode is exclusively in crimeLower
      assertThat(legalHelp).doesNotContain("stageReachedCode");
      assertThat(mediation).doesNotContain("stageReachedCode");

      // outreachLocation is exclusively in mediation
      assertThat(legalHelp).doesNotContain("outreachLocation");
      assertThat(crimeLower).doesNotContain("outreachLocation");

      // isVatApplicable is exclusively in legalHelp
      assertThat(crimeLower).doesNotContain("isVatApplicable");
      assertThat(mediation).doesNotContain("isVatApplicable");
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Multiple registry instances
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Multiple registry instances")
  class MultipleRegistryInstances {

    @Test
    @DisplayName("Two separate instances return equal field lists for each area of law")
    void twoInstancesReturnEqualLists() {
      MandatoryFieldsRegistry other = new MandatoryFieldsRegistry();
      assertThat(registry.getLegalHelpMandatoryFields())
          .isEqualTo(other.getLegalHelpMandatoryFields());
      assertThat(registry.getCrimeLowerMandatoryFields())
          .isEqualTo(other.getCrimeLowerMandatoryFields());
      assertThat(registry.getMediationMandatoryFields())
          .isEqualTo(other.getMediationMandatoryFields());
    }
  }
}
