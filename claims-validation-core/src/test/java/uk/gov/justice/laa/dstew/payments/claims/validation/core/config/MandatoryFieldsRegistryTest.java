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
 * <p>Verifies that each area-of-law constant contains the correct mandatory fields,
 * that the map correctly maps areas of law to their respective field lists,
 * and that all lists are immutable.
 */
@DisplayName("MandatoryFieldsRegistry")
class MandatoryFieldsRegistryTest {

  // ─────────────────────────────────────────────────────────────────────────
  // LEGAL_HELP_MANDATORY_FIELDS
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("LEGAL_HELP_MANDATORY_FIELDS")
  class LegalHelpMandatoryFields {

    @Test
    @DisplayName("List is not null")
    void listIsNotNull() {
      assertThat(MandatoryFieldsRegistry.LEGAL_HELP_MANDATORY_FIELDS).isNotNull();
    }

    @Test
    @DisplayName("List contains exactly 23 entries")
    void listHasCorrectSize() {
      assertThat(MandatoryFieldsRegistry.LEGAL_HELP_MANDATORY_FIELDS).hasSize(23);
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
      assertThat(MandatoryFieldsRegistry.LEGAL_HELP_MANDATORY_FIELDS).contains(fieldName);
    }

    @Test
    @DisplayName("List does not contain Crime Lower specific fields")
    void listDoesNotContainCrimeLowerFields() {
      assertThat(MandatoryFieldsRegistry.LEGAL_HELP_MANDATORY_FIELDS)
          .doesNotContain("stageReachedCode", "disbursementsVatAmount");
    }

    @Test
    @DisplayName("List is unmodifiable")
    void listIsUnmodifiable() {
      List<String> fields = MandatoryFieldsRegistry.LEGAL_HELP_MANDATORY_FIELDS;
      org.junit.jupiter.api.Assertions.assertThrows(
          UnsupportedOperationException.class,
          () -> fields.add("newField")
      );
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // CRIME_LOWER_MANDATORY_FIELDS
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("CRIME_LOWER_MANDATORY_FIELDS")
  class CrimeLowerMandatoryFields {

    @Test
    @DisplayName("List is not null")
    void listIsNotNull() {
      assertThat(MandatoryFieldsRegistry.CRIME_LOWER_MANDATORY_FIELDS).isNotNull();
    }

    @Test
    @DisplayName("List contains exactly 4 entries")
    void listHasCorrectSize() {
      assertThat(MandatoryFieldsRegistry.CRIME_LOWER_MANDATORY_FIELDS).hasSize(4);
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
      assertThat(MandatoryFieldsRegistry.CRIME_LOWER_MANDATORY_FIELDS).contains(fieldName);
    }

    @Test
    @DisplayName("List does not contain Legal Help specific fields")
    void listDoesNotContainLegalHelpFields() {
      assertThat(MandatoryFieldsRegistry.CRIME_LOWER_MANDATORY_FIELDS)
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
      List<String> fields = MandatoryFieldsRegistry.CRIME_LOWER_MANDATORY_FIELDS;
      org.junit.jupiter.api.Assertions.assertThrows(
          UnsupportedOperationException.class,
          () -> fields.add("newField")
      );
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // MEDIATION_MANDATORY_FIELDS
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("MEDIATION_MANDATORY_FIELDS")
  class MediationMandatoryFields {

    @Test
    @DisplayName("List is not null")
    void listIsNotNull() {
      assertThat(MandatoryFieldsRegistry.MEDIATION_MANDATORY_FIELDS).isNotNull();
    }

    @Test
    @DisplayName("List contains exactly 17 entries")
    void listHasCorrectSize() {
      assertThat(MandatoryFieldsRegistry.MEDIATION_MANDATORY_FIELDS).hasSize(17);
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
      assertThat(MandatoryFieldsRegistry.MEDIATION_MANDATORY_FIELDS).contains(fieldName);
    }

    @Test
    @DisplayName("List does not contain Crime Lower or Legal Help specific fields")
    void listDoesNotContainOtherAreaFields() {
      assertThat(MandatoryFieldsRegistry.MEDIATION_MANDATORY_FIELDS)
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
      List<String> fields = MandatoryFieldsRegistry.MEDIATION_MANDATORY_FIELDS;
      org.junit.jupiter.api.Assertions.assertThrows(
          UnsupportedOperationException.class,
          () -> fields.add("newField")
      );
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // MANDATORY_FIELDS_BY_AREA_OF_LAW map
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("MANDATORY_FIELDS_BY_AREA_OF_LAW map")
  class MandatoryFieldsByAreaOfLaw {

    @Test
    @DisplayName("Map is not null")
    void mapIsNotNull() {
      assertThat(MandatoryFieldsRegistry.MANDATORY_FIELDS_BY_AREA_OF_LAW).isNotNull();
    }

    @Test
    @DisplayName("Map contains exactly 3 entries")
    void mapHasCorrectSize() {
      assertThat(MandatoryFieldsRegistry.MANDATORY_FIELDS_BY_AREA_OF_LAW).hasSize(3);
    }

    @ParameterizedTest(name = "map contains key {0}")
    @DisplayName("Map contains an entry for each supported AreaOfLaw")
    @EnumSource(value = AreaOfLaw.class, names = {"LEGAL_HELP", "CRIME_LOWER", "MEDIATION"})
    void mapContainsExpectedKeys(AreaOfLaw areaOfLaw) {
      assertThat(MandatoryFieldsRegistry.MANDATORY_FIELDS_BY_AREA_OF_LAW).containsKey(areaOfLaw);
    }

    @Test
    @DisplayName("LEGAL_HELP key maps to the LEGAL_HELP_MANDATORY_FIELDS constant")
    void legalHelpKeyMapsToCorrectList() {
      assertThat(MandatoryFieldsRegistry.MANDATORY_FIELDS_BY_AREA_OF_LAW.get(AreaOfLaw.LEGAL_HELP))
          .isSameAs(MandatoryFieldsRegistry.LEGAL_HELP_MANDATORY_FIELDS);
    }

    @Test
    @DisplayName("CRIME_LOWER key maps to the CRIME_LOWER_MANDATORY_FIELDS constant")
    void crimeLowerKeyMapsToCorrectList() {
      assertThat(MandatoryFieldsRegistry.MANDATORY_FIELDS_BY_AREA_OF_LAW.get(AreaOfLaw.CRIME_LOWER))
          .isSameAs(MandatoryFieldsRegistry.CRIME_LOWER_MANDATORY_FIELDS);
    }

    @Test
    @DisplayName("MEDIATION key maps to the MEDIATION_MANDATORY_FIELDS constant")
    void mediationKeyMapsToCorrectList() {
      assertThat(MandatoryFieldsRegistry.MANDATORY_FIELDS_BY_AREA_OF_LAW.get(AreaOfLaw.MEDIATION))
          .isSameAs(MandatoryFieldsRegistry.MEDIATION_MANDATORY_FIELDS);
    }

    @Test
    @DisplayName("Map is unmodifiable")
    void mapIsUnmodifiable() {
      Map<AreaOfLaw, List<String>> map = MandatoryFieldsRegistry.MANDATORY_FIELDS_BY_AREA_OF_LAW;
      org.junit.jupiter.api.Assertions.assertThrows(
          UnsupportedOperationException.class,
          () -> map.put(AreaOfLaw.LEGAL_HELP, List.of())
      );
    }

    @Test
    @DisplayName("Areas of law have non-overlapping mandatory field sets")
    void areasOfLawHaveNonOverlappingFields() {
      List<String> legalHelp = MandatoryFieldsRegistry.LEGAL_HELP_MANDATORY_FIELDS;
      List<String> crimeLower = MandatoryFieldsRegistry.CRIME_LOWER_MANDATORY_FIELDS;
      List<String> mediation = MandatoryFieldsRegistry.MEDIATION_MANDATORY_FIELDS;

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
}
