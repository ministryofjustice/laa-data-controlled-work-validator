package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.FeeCalculationType;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

/**
 * Integration-style test verifying that the full "Case Start Date" business rule is enforced by the
 * collaborating claim validators working over a shared {@link ClaimValidationContext}, exactly as
 * they would inside the validation pipeline:
 *
 * <ul>
 *   <li><em>LEGAL HELP / MEDIATION</em>: mandatory (via {@link MandatoryFieldClaimValidator}) and a
 *       valid date between 01/01/1995 and today (via {@link CaseDatesClaimValidator}).</li>
 *   <li><em>CRIME LOWER</em>: Case Start Date is not applicable — no case_start_date error.</li>
 *   <li><em>DISB-ONLY</em>: case start date + 3 months must not exceed the submission cutoff (via
 *       {@link DisbursementClaimStartDateValidator}).</li>
 * </ul>
 */
@DisplayName("Case Start Date rule — cross-validator integration")
class CaseStartDateRuleIntegrationTest {

  /** Runs the given validators in priority order over one shared context (pipeline behaviour). */
  private static ClaimValidationContext runPipeline(Claim claim, ClaimValidationContext context) {
    List<ClaimValidator> validators =
        List.of(
            new MandatoryFieldClaimValidator(),
            new DisbursementClaimStartDateValidator(),
            new CaseDatesClaimValidator());
    validators.stream()
        .sorted(Comparator.comparingInt(ClaimValidator::priority))
        .forEach(v -> v.validate(claim, context));
    return context;
  }

  // ─── LEGAL HELP / MEDIATION ────────────────────────────────────────────────

  @ParameterizedTest(name = "{0}: blank Case Start Date is rejected as mandatory")
  @EnumSource(value = AreaOfLaw.class, names = {"LEGAL_HELP", "MEDIATION"})
  @DisplayName("Blank Case Start Date is rejected for LEGAL HELP / MEDIATION")
  void blankCaseStartDate_isMandatory(AreaOfLaw areaOfLaw) {
    Claim claim = Claim.builder().areaOfLaw(areaOfLaw).caseStartDate("").build();

    ClaimValidationContext context =
        runPipeline(claim, ClaimValidationContext.builder().build());

    assertThat(context.hasErrors()).isTrue();
    // The mandatory-field validator (priority 10) owns the "required" message for the field.
    assertThat(context.getAllIssues().stream()
            .anyMatch(i -> "MISSING_MANDATORY_FIELD".equals(i.getCode())
                && "case_start_date".equals(i.getPath())))
        .isTrue();
  }

  @ParameterizedTest(name = "{0}: out-of-range Case Start Date is rejected")
  @EnumSource(value = AreaOfLaw.class, names = {"LEGAL_HELP", "MEDIATION"})
  @DisplayName("Out-of-range Case Start Date is rejected for LEGAL HELP / MEDIATION")
  void outOfRangeCaseStartDate_isRejected(AreaOfLaw areaOfLaw) {
    Claim claim = Claim.builder().areaOfLaw(areaOfLaw).caseStartDate("1990-01-01").build();

    ClaimValidationContext context =
        runPipeline(claim, ClaimValidationContext.builder().build());

    assertThat(context.getAllIssues().stream()
            .anyMatch(i -> "Case Start Date must be between 01/01/1995 and today"
                .equals(i.getMessage())))
        .isTrue();
  }

  // ─── CRIME LOWER: not applicable ───────────────────────────────────────────

  @ParameterizedTest(name = "CRIME_LOWER caseStartDate=''{0}'' → no case_start_date error")
  @CsvSource({
    "''",              // blank
    "1990-01-01",      // out of range (past)
    "2999-01-01",      // out of range (future)
    "2003-13-34",      // unparseable
  })
  @DisplayName("Case Start Date is not applicable for CRIME LOWER claims")
  void caseStartDate_notApplicable_forCrimeLower(String caseStartDate) {
    Claim claim = Claim.builder().areaOfLaw(AreaOfLaw.CRIME_LOWER).caseStartDate(caseStartDate)
        .build();

    ClaimValidationContext context =
        runPipeline(claim, ClaimValidationContext.builder().build());

    assertThat(context.getAllIssues().stream()
            .noneMatch(i -> "case_start_date".equals(i.getPath())))
        .isTrue();
  }

  // ─── DISB-ONLY timing rule ─────────────────────────────────────────────────

  @Test
  @DisplayName("DISB-ONLY claim with case start date + 3 months beyond the cutoff is rejected")
  void disbOnly_caseStartTooLate_isRejected() {
    Claim claim = Claim.builder()
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .caseStartDate("2023-01-15")   // +3 months = 2023-04-15, cutoff for JAN-2023 is 2023-02-20
        .submissionPeriod("JAN-2023")
        .build();

    ClaimValidationContext context =
        runPipeline(
            claim,
            ClaimValidationContext.builder()
                .feeCalculationType(FeeCalculationType.DISB_ONLY.getValue())
                .build());

    assertThat(context.getAllIssues().stream()
            .anyMatch(i -> "DISBURSEMENT_TOO_EARLY".equals(i.getCode())))
        .isTrue();
  }

  @Test
  @DisplayName("DISB-ONLY claim with case start date within the allowed window is not flagged as too early")
  void disbOnly_caseStartWithinWindow_isNotFlagged() {
    Claim claim = Claim.builder()
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .caseStartDate("2022-10-01")   // +3 months = 2023-01-01, cutoff for JAN-2023 is 2023-02-20
        .submissionPeriod("JAN-2023")
        .build();

    ClaimValidationContext context =
        runPipeline(
            claim,
            ClaimValidationContext.builder()
                .feeCalculationType(FeeCalculationType.DISB_ONLY.getValue())
                .build());

    assertThat(context.getAllIssues().stream()
            .noneMatch(i -> "DISBURSEMENT_TOO_EARLY".equals(i.getCode())))
        .isTrue();
  }
}
