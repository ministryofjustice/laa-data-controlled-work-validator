package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidatorCode;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimInquestDetail;

@DisplayName("Inquest claim validator")
class InquestClaimValidatorTest {

  private static final String MOJ = "Ministry of Justice";
  private static final String CASE_START_DATE = "2026-09-10";
  private static final String SCHEMA_ERROR = "SCHEMA_VALIDATION_ERROR";

  private InquestClaimValidator validator;

  @BeforeEach
  void setUp() {
    validator = new InquestClaimValidator(
        () -> List.of(MOJ, "Home Office", "Attorney General’s Office"));
    validator.init();
  }

  private static ClaimInquestDetail validDetail() {
    return new ClaimInquestDetail()
        .isClientMeansTested(false)
        .deceasedForename("Anne-Marie")
        .deceasedSurname("O'Neil")
        .deceasedDateOfDeath("2020-05-17")
        .coronersInquestReference("AB/12-34.56")
        .interestedDepartments(departments(MOJ));
  }

  private static List<String> departments(String... values) {
    return new ArrayList<>(Arrays.asList(values));
  }

  private ClaimValidationContext validate(Boolean isInquest, ClaimInquestDetail detail) {
    return validate(isInquest, detail, CASE_START_DATE);
  }

  private ClaimValidationContext validate(
      Boolean isInquest, ClaimInquestDetail detail, String caseStartDate) {
    ClaimValidationContext context = ClaimValidationContext.builder().build();
    context.setIsInquest(isInquest);
    validator.validate(
        Claim.builder().caseStartDate(caseStartDate).inquestDetail(detail).build(), context);
    return context;
  }

  private static List<String> codes(ClaimValidationContext context) {
    return context.getIssues().stream().map(ValidationIssue::getCode).toList();
  }

  private static void assertSingleIssue(
      ClaimValidationContext context, String code, String message, String path) {
    assertThat(context.getIssues()).singleElement().satisfies(issue -> {
      assertThat(issue.getCode()).isEqualTo(code);
      assertThat(issue.getMessage()).isEqualTo(message);
      assertThat(issue.getPath()).isEqualTo(path);
    });
  }

  @Test
  void metadata() {
    assertThat(validator.getValidatorCode()).isEqualTo(ClaimValidatorCode.CLAIM_INQUEST_VALIDATOR);
    assertThat(validator.priority()).isEqualTo(100);
  }

  @Test
  @DisplayName("Valid non-means-tested Inquest claim passes")
  void validClaimPasses() {
    assertThat(validate(true, validDetail()).getIssues()).isEmpty();
  }

  @Test
  @DisplayName("Unresolved is_inquest skips all checks")
  void unresolvedInquestFlagSkipsChecks() {
    assertThat(validate(null, validDetail().deceasedForename("@")).getIssues()).isEmpty();
  }

  @Test
  @DisplayName("Non-Inquest and means-tested Inquest claims are not checked")
  void nonApplicableClaimsSkipped() {
    ClaimInquestDetail invalid = validDetail().deceasedForename("@").interestedDepartments(null);

    assertThat(validate(false, invalid).getIssues()).isEmpty();
    assertThat(validate(false, null).getIssues()).isEmpty();
    assertThat(validate(true, invalid.isClientMeansTested(true)).getIssues()).isEmpty();
  }

  @Test
  @DisplayName("Inquest claim without means-tested value produces only the required error")
  void meansTestedRequired() {
    assertSingleIssue(validate(true, validDetail().isClientMeansTested(null)),
        "INQUEST_CLIENT_MEANS_TESTED_REQUIRED",
        "Client Means Tested is required for Inquest claims",
        "inquest_detail.is_client_means_tested");
    assertThat(codes(validate(true, null)))
        .containsExactly("INQUEST_CLIENT_MEANS_TESTED_REQUIRED");
  }

  // ─── Mandatory fields ─────────────────────────────────────────────────────

  @Nested
  @DisplayName("Mandatory fields")
  class MandatoryFields {

    @ParameterizedTest(name = "[{index}] \"{0}\"")
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t"})
    @DisplayName("Absent forename, surname or date of death are each rejected")
    void absentFieldsRejected(String value) {
      assertSingleIssue(validate(true, validDetail().deceasedForename(value)),
          "INQUEST_DECEASED_FORENAME_REQUIRED",
          "Deceased Forename is required for non-means-tested Inquest claims",
          "inquest_detail.deceased_forename");
      assertSingleIssue(validate(true, validDetail().deceasedSurname(value)),
          "INQUEST_DECEASED_SURNAME_REQUIRED",
          "Deceased Surname is required for non-means-tested Inquest claims",
          "inquest_detail.deceased_surname");
      assertSingleIssue(validate(true, validDetail().deceasedDateOfDeath(value)),
          "INQUEST_DATE_OF_DEATH_REQUIRED",
          "Deceased Date of Death is required for non-means-tested Inquest claims",
          "inquest_detail.deceased_date_of_death");
    }

    static Stream<List<String>> absentDepartments() {
      return Stream.of(null, departments(), departments((String) null),
          departments("", "  "), new ArrayList<>(Collections.nCopies(10, " ")));
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("absentDepartments")
    @DisplayName("All Department occurrences absent is rejected")
    void absentDepartmentsRejected(List<String> value) {
      assertSingleIssue(validate(true, validDetail().interestedDepartments(value)),
          "INQUEST_GOVERNMENT_DEPARTMENT_REQUIRED",
          "At least one Government Department is required for non-means-tested Inquest claims",
          "inquest_detail.interested_departments");
    }
  }

  // ─── Schema format rules ──────────────────────────────────────────────────

  @Nested
  @DisplayName("Deceased names (schema)")
  class DeceasedNames {

    private static final String FORENAME_MESSAGE = "Deceased Forename must contain only letters, "
        + "numbers, spaces, hyphens, apostrophes, ampersands, and be a maximum of 30 characters";
    private static final String SURNAME_MESSAGE = "Deceased Surname must contain only letters, "
        + "numbers, spaces, hyphens, apostrophes, ampersands, and be a maximum of 30 characters";

    private void assertRejected(String value) {
      assertSingleIssue(validate(true, validDetail().deceasedForename(value)), SCHEMA_ERROR,
          FORENAME_MESSAGE, "inquest_detail.deceased_forename");
      assertSingleIssue(validate(true, validDetail().deceasedSurname(value)), SCHEMA_ERROR,
          SURNAME_MESSAGE, "inquest_detail.deceased_surname");
    }

    @ParameterizedTest(name = "[{index}] \"{0}\"")
    @ValueSource(strings = {"Anne-Marie", "O'Neil", "O’Neil", "A & B 2", "Élodie", "李",
        "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"})
    @DisplayName("Permitted values up to 30 characters pass")
    void permittedValuesPass(String value) {
      assertThat(validate(true, validDetail().deceasedForename(value)).getIssues()).isEmpty();
      assertThat(validate(true, validDetail().deceasedSurname(value)).getIssues()).isEmpty();
    }

    @ParameterizedTest(name = "[{index}] \"{0}\"")
    @ValueSource(strings = {"Ann@", "Ann/Marie", "Ann.", "Ann_Marie",
        "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"})
    @DisplayName("Other characters or 31 characters are rejected")
    void invalidValuesRejected(String value) {
      assertRejected(value);
    }
  }

  @Nested
  @DisplayName("Coroner's Inquest Reference (schema)")
  class InquestReference {

    private static final String MESSAGE = "Coroner's Inquest Reference must contain only letters, "
        + "numbers, forward slashes, hyphens, periods, and be a maximum of 30 characters";

    @ParameterizedTest(name = "[{index}] \"{0}\"")
    @NullAndEmptySource
    @ValueSource(strings = {" ", "AB/12-34.56", "AB/12-34.5AB/12-34.5AB/12-34.5"})
    @DisplayName("Omitted, permitted or 30-character references pass")
    void permittedReferencesPass(String reference) {
      assertThat(validate(true, validDetail().coronersInquestReference(reference)).getIssues())
          .isEmpty();
    }

    @ParameterizedTest(name = "[{index}] \"{0}\"")
    @ValueSource(strings = {"AB@12", "AB_12", "AB 12", "AB/12-34.5AB/12-34.5AB/12-34.56"})
    @DisplayName("Other characters, internal spaces or 31 characters are rejected")
    void invalidReferencesRejected(String reference) {
      assertSingleIssue(validate(true, validDetail().coronersInquestReference(reference)),
          SCHEMA_ERROR, MESSAGE, "inquest_detail.coroners_inquest_reference");
    }
  }

  // ─── Date of death ────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Date of death")
  class DateOfDeath {

    @ParameterizedTest(name = "[{index}] {0}")
    @ValueSource(strings = {"1900-01-01", "2026-09-10"})
    @DisplayName("Boundary dates pass")
    void inRange(String dateOfDeath) {
      assertThat(validate(true, validDetail().deceasedDateOfDeath(dateOfDeath)).getIssues())
          .isEmpty();
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @ValueSource(strings = {"1899-12-31", "2026-09-11"})
    @DisplayName("Dates outside 01/01/1900 to the case start date are rejected")
    void outOfRange(String dateOfDeath) {
      assertSingleIssue(validate(true, validDetail().deceasedDateOfDeath(dateOfDeath)),
          "INQUEST_DATE_OF_DEATH_OUT_OF_RANGE",
          "Deceased Date of Death must be between 01/01/1900 and the Case Start Date, inclusive",
          "inquest_detail.deceased_date_of_death");
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @ValueSource(strings = {"2026-02-31", "2026-13-01", "not-a-date", "31/12/2020"})
    @DisplayName("Unparseable or impossible dates are rejected")
    void invalidDate(String dateOfDeath) {
      assertSingleIssue(validate(true, validDetail().deceasedDateOfDeath(dateOfDeath)),
          "INQUEST_DATE_OF_DEATH_INVALID", "Deceased Date of Death must be a valid date",
          "inquest_detail.deceased_date_of_death");
    }

    @ParameterizedTest(name = "[{index}] case start \"{0}\"")
    @NullAndEmptySource
    @ValueSource(strings = {"2026-02-30"})
    @DisplayName("Missing or invalid case start date skips the range check")
    void missingOrInvalidCaseStartDate(String caseStartDate) {
      assertThat(validate(true, validDetail().deceasedDateOfDeath("1800-01-01"), caseStartDate)
          .getIssues()).isEmpty();
    }
  }

  // ─── Government Departments ───────────────────────────────────────────────

  @Nested
  @DisplayName("Government Departments")
  class GovernmentDepartments {

    @ParameterizedTest(name = "[{index}] \"{0}\"")
    @ValueSource(strings = {"ministry   OF JUSTICE", " Ministry of Justice ",
        "Attorney General's Office", "Attorney General’s Office"})
    @DisplayName("Names match after normalising whitespace, apostrophes and case")
    void normalisedMatch(String name) {
      assertThat(validate(true, validDetail().interestedDepartments(departments(name)))
          .getIssues()).isEmpty();
    }

    @ParameterizedTest(name = "[{index}] \"{0}\"")
    @ValueSource(strings = {"MOJ", "Ministry", "Ministry of Magic", "Ministry of Defence"})
    @DisplayName("Codes, partial, unknown and inactive names are rejected")
    void unknownNamesRejected(String name) {
      assertSingleIssue(validate(true, validDetail().interestedDepartments(departments(name))),
          "INQUEST_GOVERNMENT_DEPARTMENT_UNKNOWN",
          "Government Department 1 must match an active Government Department name",
          "inquest_detail.interested_departments[0]");
    }

    @Test
    @DisplayName("Unknown name reports its original slot, before blanks are compacted")
    void reportsOriginalSlot() {
      ClaimValidationContext context = validate(true,
          validDetail().interestedDepartments(departments(" ", MOJ, "Unknown")));

      assertSingleIssue(context, "INQUEST_GOVERNMENT_DEPARTMENT_UNKNOWN",
          "Government Department 3 must match an active Government Department name",
          "inquest_detail.interested_departments[2]");
    }

    @Test
    @DisplayName("Blank slot 1 with populated slots 2 and 3 passes")
    void blankFirstSlotPasses() {
      assertThat(validate(true,
          validDetail().interestedDepartments(departments("", MOJ, "Home Office")))
          .getIssues()).isEmpty();
    }

    @Test
    @DisplayName("10 occurrences (including repetitions) pass; 11 are rejected")
    void maximumOccurrences() {
      assertThat(validate(true, validDetail()
          .interestedDepartments(new ArrayList<>(Collections.nCopies(10, MOJ)))).getIssues())
          .isEmpty();

      assertSingleIssue(validate(true, validDetail()
              .interestedDepartments(new ArrayList<>(Collections.nCopies(11, MOJ)))),
          "INQUEST_GOVERNMENT_DEPARTMENT_TOO_MANY",
          "A maximum of 10 Government Department occurrences can be supplied for a claim",
          "inquest_detail.interested_departments");
    }
  }
}
