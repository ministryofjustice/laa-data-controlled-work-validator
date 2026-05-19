package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

@DisplayName("Case dates claim validator test")
class CaseDatesClaimValidationTest {

  private static final String CASE_CONCLUDED_DATE_LATER_MESSAGE =
      "Case Concluded Date cannot be later than the 20th of the month following the submission period";
  private static final String CASE_CONCLUDED_DATE_EARLY_LEGAL_HELP_MEDIATION =
      "Case Concluded Date cannot be before 01/04/2013";
  private static final String CASE_CONCLUDED_DATE_EARLY_CRIME_LOWER =
      "Case Concluded Date cannot be before 01/04/2016";

  private final CaseDatesClaimValidator validator = new CaseDatesClaimValidator();

  // ─── Happy path ────────────────────────────────────────────────────────────

  @Test
  @DisplayName("All valid dates produce no issues")
  void happyPath_allValidDates_noIssues() {
    Claim claim = Claim.builder()
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .caseStartDate("2020-06-01")
        .caseConcludedDate("2020-06-15")
        .transferDate("2020-06-10")
        .representationOrderDate("2020-06-01")
        .submissionPeriod("JUN-2020")
        .build();

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);

    assertTrue(context.getIssues().isEmpty());
  }

  // ─── Invalid date strings ──────────────────────────────────────────────────

  @Test
  @DisplayName("Invalid format for caseStartDate produces INVALID_DATE_FORMAT issue")
  void invalidFormat_caseStartDate_producesError() {
    Claim claim = Claim.builder()
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .caseStartDate("2003-13-34")   // impossible month/day
        .transferDate("2025-06-10")
        .representationOrderDate("2025-06-01")
        .submissionPeriod("JUN-2025")
        .build();

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);

    assertThat(context.getIssues().stream()
        .anyMatch(x -> x.getMessage().equals("Invalid date value provided for Case Start Date")))
        .isTrue();
  }

  @Test
  @DisplayName("Invalid format for caseConcludedDate produces INVALID_DATE_FORMAT issue")
  void invalidFormat_caseConcludedDate_producesError() {
    Claim claim = Claim.builder()
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .caseStartDate("2025-06-01")
        .caseConcludedDate("not-a-date")
        .submissionPeriod("JUN-2025")
        .build();

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);

    assertThat(context.getIssues().stream()
        .anyMatch(x -> x.getMessage().equals("Invalid date value provided for Case Concluded Date")))
        .isTrue();
  }

  // ─── Dates too far in the past ─────────────────────────────────────────────

  @Test
  @DisplayName("caseStartDate before 1995, transferDate before 1995, representationOrderDate before 2016 — all produce errors")
  void tooEarly_caseStartDate_transferDate_representationOrderDate() {
    Claim claim = Claim.builder()
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .caseStartDate("1993-01-03")
        .transferDate("1990-12-02")
        .representationOrderDate("2016-03-30")  // one day before the cutoff
        .build();

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);

    assertThat(context.getIssues().stream()
        .anyMatch(x -> x.getMessage().equals("Case Start Date must be between 01/01/1995 and today")))
        .isTrue();
    assertThat(context.getIssues().stream()
        .anyMatch(x -> x.getMessage().equals("Transfer Date must be between 01/01/1995 and today")))
        .isTrue();
    assertThat(context.getIssues().stream()
        .anyMatch(x -> x.getMessage().equals("Representation Order Date must be between 01/04/2016 and today")))
        .isTrue();
  }

  // ─── Dates too far in the future ──────────────────────────────────────────

  @Test
  @DisplayName("transferDate and representationOrderDate in the future produce range errors")
  void future_transferDate_representationOrderDate() {
    Claim claim = Claim.builder()
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .caseStartDate("2003-13-34")   // also invalid format — ensures caseStartDate is independently caught
        .transferDate("2090-12-02")
        .representationOrderDate("2090-01-01")
        .build();

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);

    assertThat(context.getIssues().stream()
        .anyMatch(x -> x.getMessage().equals("Transfer Date must be between 01/01/1995 and today")))
        .isTrue();
    assertThat(context.getIssues().stream()
        .anyMatch(x -> x.getMessage().equals("Representation Order Date must be between 01/04/2016 and today")))
        .isTrue();
  }

  // ─── Case concluded date vs submission period ──────────────────────────────

  @Test
  @DisplayName("caseConcludedDate after 20th of month following submission period produces error")
  void caseConcludedDate_afterCutoff_producesError() {
    Claim claim = Claim.builder()
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .caseStartDate("2025-05-25")
        .caseConcludedDate("2025-05-30")   // APR-2025 cutoff is 20th May
        .submissionPeriod("APR-2025")
        .build();

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);

    assertThat(context.getIssues().stream()
        .anyMatch(x -> x.getMessage().equals(CASE_CONCLUDED_DATE_LATER_MESSAGE)))
        .isTrue();
  }

  @Test
  @DisplayName("caseConcludedDate in the future produces future-date error")
  void caseConcludedDate_future_producesError() {
    String tomorrow = LocalDate.now().plusDays(1).toString();
    Claim claim = Claim.builder()
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .caseStartDate("2025-05-25")
        .caseConcludedDate(tomorrow)
        .submissionPeriod("APR-2025")
        .build();

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);

    assertThat(context.getIssues().stream()
        .anyMatch(x -> x.getMessage().equals("Case Concluded Date cannot be a future date")))
        .isTrue();
  }

  // ─── Case concluded date boundary / area-of-law parametrised ─────────────

  /*
   * Case Concluded Date earliest allowed dates vary by area of law:
   *   LEGAL_HELP / MEDIATION → 01/04/2013
   *   CRIME_LOWER            → 01/04/2016
   */
  @ParameterizedTest(name = "[{index}] {1} caseConcludedDate={2} period={3} → error={4}")
  @DisplayName("Case concluded date boundary validation across all areas of law")
  @CsvSource({
    // LEGAL_HELP happy paths
    "LEGAL_HELP, 2025-12-31, DEC-2025, false, null",
    "LEGAL_HELP, 2026-01-20, DEC-2025, false, null",        // exactly the cutoff — valid
    // LEGAL_HELP too early
    "LEGAL_HELP, 1994-08-14, AUG-2025, true, "
        + CASE_CONCLUDED_DATE_EARLY_LEGAL_HELP_MEDIATION,
    "LEGAL_HELP, 2013-03-31, DEC-2025, true, "
        + CASE_CONCLUDED_DATE_EARLY_LEGAL_HELP_MEDIATION,   // one day before cutoff
    // LEGAL_HELP too late
    "LEGAL_HELP, 2026-01-21, DEC-2025, true, "
        + CASE_CONCLUDED_DATE_LATER_MESSAGE,
    // CRIME_LOWER happy paths
    "CRIME_LOWER, 2025-07-14, JUL-2025, false, null",
    "CRIME_LOWER, 2025-08-20, JUL-2025, false, null",       // exactly the cutoff — valid
    // CRIME_LOWER too early
    "CRIME_LOWER, 2015-08-14, SEP-2017, true, "
        + CASE_CONCLUDED_DATE_EARLY_CRIME_LOWER,
    "CRIME_LOWER, 2013-03-31, JUL-2025, true, "
        + CASE_CONCLUDED_DATE_EARLY_CRIME_LOWER,
    // CRIME_LOWER too late
    "CRIME_LOWER, 2025-08-22, JUL-2025, true, "
        + CASE_CONCLUDED_DATE_LATER_MESSAGE,
    // MEDIATION happy paths
    "MEDIATION, 2025-01-03, FEB-2025, false, null",
    "MEDIATION, 2025-03-20, FEB-2025, false, null",         // exactly the cutoff — valid
    // MEDIATION too early
    "MEDIATION, 1994-08-14, AUG-2025, true, "
        + CASE_CONCLUDED_DATE_EARLY_LEGAL_HELP_MEDIATION,
    "MEDIATION, 2013-03-31, FEB-2025, true, "
        + CASE_CONCLUDED_DATE_EARLY_LEGAL_HELP_MEDIATION,
    // MEDIATION too late
    "MEDIATION, 2025-03-23, FEB-2025, true, "
        + CASE_CONCLUDED_DATE_LATER_MESSAGE,
  })
  void caseConcludedDate_boundaryValidation(
      AreaOfLaw areaOfLaw,
      String caseConcludedDate,
      String submissionPeriod,
      boolean expectError,
      String expectedErrorMsg) {

    Claim claim = Claim.builder()
        .areaOfLaw(areaOfLaw)
        .caseStartDate("2025-08-14")
        .caseConcludedDate(caseConcludedDate)
        .submissionPeriod(submissionPeriod)
        .build();

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);

    if (expectError) {
      assertThat(context.getIssues().getFirst().getTechnicalMessage()).isEqualTo(expectedErrorMsg);
    } else {
      assertTrue(context.getIssues().isEmpty());
    }
  }

  // ─── Metadata ─────────────────────────────────────────────────────────────

  @Test
  @DisplayName("CaseDatesClaimValidator - priority, appliesTo and validator code")
  void caseDatesValidatorMetadata() {
    assertThat(validator.priority()).isEqualTo(100);
    assertThat(validator.appliesTo("any")).isTrue();
    assertThat(validator.getValidatorCode()).isEqualTo("CLAIM_CASE_DATES");
  }
}
