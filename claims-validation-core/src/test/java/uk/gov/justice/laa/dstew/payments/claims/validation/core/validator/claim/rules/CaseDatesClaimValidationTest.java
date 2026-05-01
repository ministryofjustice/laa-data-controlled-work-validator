package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;

@DisplayName("Case dates claim validator test")
class CaseDatesClaimValidationTest {

  private static final String
      CASE_CONCLUDED_DATE_LATER_VALIDATION_ERROR_MESSAGE_LEGAL_HELP_AND_MEDIATION =
          "Case Concluded Date cannot be later than the 20th of the month following the submission period";
  private static final String CASE_CONCLUDED_DATE_LATER_VALIDATION_ERROR_MESSAGE_CRIME_LOWER =
      "Case Concluded Date cannot be later than the 20th of the month following the submission period";
  private static final String
      CASE_CONCLUDED_DATE_EARLY_VALIDATION_ERROR_MESSAGE_LEGAL_HELP_AND_MEDIATION =
          "Case Concluded Date cannot be before 01/04/2013";
  private static final String CASE_CONCLUDED_DATE_EARLY_VALIDATION_ERROR_MESSAGE_CRIME_LOWER =
      "Case Concluded Date cannot be before 01/04/2016";
  private final CaseDatesClaimValidator validator = new CaseDatesClaimValidator();

  @Test
  void validatePastDatesOne() {
    UUID claimId = new UUID(1, 1);
    Claim claim =
        Claim.builder()
            .id(claimId)
            .status(ClaimStatus.READY_TO_PROCESS)
            .feeCode("feeCode1")
            .caseStartDate("2003-13-34")
            .transferDate("2090-12-02")
            .caseConcludedDate("2026-01-01")
            .representationOrderDate("2090-01-01")
            .matterTypeCode("a:b")
            .build();

    claim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);

    ClaimValidationContext context = ClaimValidationContext.builder().build();

    validator.validate(claim, context);

    System.out.println(context.getIssues());

    assertThat(
            context.getIssues().stream()
                .anyMatch(
                    x -> x.getMessage().equals("Invalid date value provided for Case Start Date")))
        .isTrue();
    assertThat(
            context.getIssues().stream()
                .anyMatch(
                    x ->
                        x.getMessage()
                            .equals("Transfer Date must be between 01/01/1995 and today")))
        .isTrue();
    assertThat(
            context.getIssues().stream()
                .anyMatch(
                    x ->
                        x.getMessage()
                            .equals(
                                "Representation Order Date must be between 01/04/2016 and today")))
        .isTrue();
  }

  @Test
  void validatePastDatesTwoLegalHelp() {
    UUID claimId = new UUID(2, 2);

    Claim claim =
        Claim.builder()
            .id(claimId)
            .status(ClaimStatus.READY_TO_PROCESS)
            .feeCode("feeCode2")
            .caseStartDate("1993-01-03")
            .transferDate("1990-12-02")
            .caseConcludedDate("1993-01-01")
            .representationOrderDate("2016-03-30")
            .matterTypeCode("1:2")
            .build();

    claim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);

    ClaimValidationContext context = ClaimValidationContext.builder().build();

    validator.validate(claim, context);

    assertThat(
            context.getIssues().stream()
                .anyMatch(
                    x ->
                        x.getMessage()
                            .equals("Case Start Date must be between 01/01/1995 and today")))
        .isTrue();
    assertThat(
            context.getIssues().stream()
                .anyMatch(
                    x ->
                        x.getMessage()
                            .equals("Transfer Date must be between 01/01/1995 and today")))
        .isTrue();
    assertThat(
            context.getIssues().stream()
                .anyMatch(
                    x ->
                        x.getMessage()
                            .equals(
                                "Representation Order Date must be between 01/04/2016 and today")))
        .isTrue();
  }

  @Test
  void validateCaseConcludedDateDoesNotExceedSubmissionPeriod() {
    UUID claimId = new UUID(2, 2);

    Claim claim =
        Claim.builder()
            .id(claimId)
            .status(ClaimStatus.READY_TO_PROCESS)
            .feeCode("feeCode2")
            .caseStartDate("2025-05-25")
            .transferDate("2025-05-27")
            .caseConcludedDate("2025-05-30")
            .submissionPeriod("APR-2025")
            .matterTypeCode("1:2")
            .build();

    claim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);

    ClaimValidationContext context = ClaimValidationContext.builder().build();

    validator.validate(claim, context);

    assertThat(
            context.getIssues().stream()
                .anyMatch(
                    x ->
                        x.getMessage()
                            .equals(
                                CASE_CONCLUDED_DATE_LATER_VALIDATION_ERROR_MESSAGE_LEGAL_HELP_AND_MEDIATION)))
        .isTrue();
  }

  @Test
  void validateCaseConcludedDateIsNotInFuture() {
    UUID claimId = new UUID(2, 2);

    String tomorrowsDate = LocalDate.now().plusDays(1).toString();
    Claim claim =
        Claim.builder()
            .id(claimId)
            .status(ClaimStatus.READY_TO_PROCESS)
            .feeCode("feeCode2")
            .caseStartDate("2025-05-25")
            .transferDate("2025-05-27")
            .caseConcludedDate(tomorrowsDate)
            .submissionPeriod("APR-2025")
            .matterTypeCode("1:2")
            .build();

    claim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);

    ClaimValidationContext context = ClaimValidationContext.builder().build();

    validator.validate(claim, context);

    assertThat(
            context.getIssues().stream()
                .anyMatch(
                    x -> x.getMessage().equals("Case Concluded Date cannot be a future date")))
        .isTrue();
  }

  /*
  Case Concluded Date should be mandatory for LEGAL_HELP and CRIME_LOWER, but it's optional for MEDIATION.
  Ref: https://dsdmoj.atlassian.net/browse/DSTEW-566
   */
  @ParameterizedTest(
      name = "{index} => claimId={0}, areaOfLaw={1}, caseConcludedDate={2}, expectError={3}")
  @CsvSource({
    "1, LEGAL_HELP, 2025-12-31, DEC-2025, false, null",
    "2, LEGAL_HELP, 1994-08-14, AUG-2025, true, "
        + CASE_CONCLUDED_DATE_EARLY_VALIDATION_ERROR_MESSAGE_LEGAL_HELP_AND_MEDIATION,
    "3, LEGAL_HELP, 2026-01-21, DEC-2025, true, "
        + CASE_CONCLUDED_DATE_LATER_VALIDATION_ERROR_MESSAGE_LEGAL_HELP_AND_MEDIATION,
    "4, LEGAL_HELP, 2026-01-20, DEC-2025, false, null",
    "5, LEGAL_HELP, 2013-03-31, DEC-2025, true, "
        + CASE_CONCLUDED_DATE_EARLY_VALIDATION_ERROR_MESSAGE_LEGAL_HELP_AND_MEDIATION,
    "6, CRIME_LOWER, 2025-07-14, JUL-2025, false, null",
    "7, CRIME_LOWER, 2015-08-14, SEP-2017, true, "
        + CASE_CONCLUDED_DATE_EARLY_VALIDATION_ERROR_MESSAGE_CRIME_LOWER,
    "8, CRIME_LOWER, 2025-08-22, JUL-2025, true, "
        + CASE_CONCLUDED_DATE_LATER_VALIDATION_ERROR_MESSAGE_CRIME_LOWER,
    "9, CRIME_LOWER, 2025-08-20, JUL-2025, false, null",
    "10, CRIME_LOWER, 2013-03-31, JUL-2025, true, "
        + CASE_CONCLUDED_DATE_EARLY_VALIDATION_ERROR_MESSAGE_CRIME_LOWER,
    "11, MEDIATION, 2025-01-03, FEB-2025, false, null",
    "12, MEDIATION, 1994-08-14, AUG-2025, true, "
        + CASE_CONCLUDED_DATE_EARLY_VALIDATION_ERROR_MESSAGE_LEGAL_HELP_AND_MEDIATION,
    "13, MEDIATION, 2025-03-23, FEB-2025, true, "
        + CASE_CONCLUDED_DATE_LATER_VALIDATION_ERROR_MESSAGE_LEGAL_HELP_AND_MEDIATION,
    "14, MEDIATION, 2025-03-20, FEB-2025, false, null",
    "15, MEDIATION, 2013-03-31, FEB-2025, true, "
        + CASE_CONCLUDED_DATE_EARLY_VALIDATION_ERROR_MESSAGE_LEGAL_HELP_AND_MEDIATION,
  })
  void checkMandatoryCaseConcludedDate(
      int claimIdBit,
      AreaOfLaw areaOfLaw,
      String caseConcludedDate,
      String submissionPeriod,
      boolean expectError,
      String expectedErrorMsg) {
    UUID claimId = new UUID(claimIdBit, claimIdBit);
    Claim claim =
        Claim.builder()
            .id(claimId)
            .caseStartDate("2025-08-14")
            .caseConcludedDate(caseConcludedDate)
            .submissionPeriod(submissionPeriod)
            .build();

    claim.setAreaOfLaw(areaOfLaw);

    ClaimValidationContext context = ClaimValidationContext.builder().build();

    validator.validate(claim, context);

    if (expectError) {
      assertThat(context.getIssues().getFirst().getTechnicalMessage()).isEqualTo(expectedErrorMsg);
    } else {
      assertTrue(context.getIssues().isEmpty());
    }
  }
}
