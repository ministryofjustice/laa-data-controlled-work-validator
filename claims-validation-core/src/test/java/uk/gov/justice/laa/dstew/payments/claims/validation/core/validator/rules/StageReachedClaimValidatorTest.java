package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;

@DisplayName("Stage reached claim validator test")
class StageReachedClaimValidatorTest {

  private final StageReachedClaimValidator validator = new StageReachedClaimValidator();

  @ParameterizedTest(
      name =
          "{index} => claimId={0}, stageReachedCode={1}, areaOfLaw={2}, expectError={3}, expectedCode={4}, expectedMessage={5}, expectedTechnical={6}")
  @CsvSource({
    // LEGAL_HELP
    "1, AABB, LEGAL_HELP, true, INVALID_STAGE_REACHED_LEGAL_HELP, Stage Reached Code must be exactly 2 alphanumeric characters for Legal Help claims, stage_reached_code (LEGAL_HELP): does not match the regex pattern ^[a-zA-Z0-9]{2}$ (provided value: AABB)",
    "2, AZ, LEGAL_HELP, false, , , ",
    "3, C9, LEGAL_HELP, false, , , ",
    "4, A!, LEGAL_HELP, true, INVALID_STAGE_REACHED_LEGAL_HELP, Stage Reached Code must be exactly 2 alphanumeric characters for Legal Help claims, stage_reached_code (LEGAL_HELP): does not match the regex pattern ^[a-zA-Z0-9]{2}$ (provided value: A!)",
    // CRIME_LOWER
    "5, A1, CRIME_LOWER, true, INVALID_STAGE_REACHED_CRIME_LOWER, Stage Reached Code must be one of the allowed values for Crime Lower claims, stage_reached_code (CRIME_LOWER): does not match the regex pattern ^(INV[A-M]|PRI[A-E]|PRO[C-FH-LP-TUVW]|APP[ABC]|AS(MS|PL|AS)|YOU[EFKLXY]|VOID)$ (provided value: A1)",
    "6, A-CD, CRIME_LOWER, true, INVALID_STAGE_REACHED_CRIME_LOWER, Stage Reached Code must be one of the allowed values for Crime Lower claims, stage_reached_code (CRIME_LOWER): does not match the regex pattern ^(INV[A-M]|PRI[A-E]|PRO[C-FH-LP-TUVW]|APP[ABC]|AS(MS|PL|AS)|YOU[EFKLXY]|VOID)$ (provided value: A-CD)",
    "7, ABCD, CRIME_LOWER, true, INVALID_STAGE_REACHED_CRIME_LOWER, Stage Reached Code must be one of the allowed values for Crime Lower claims, stage_reached_code (CRIME_LOWER): does not match the regex pattern ^(INV[A-M]|PRI[A-E]|PRO[C-FH-LP-TUVW]|APP[ABC]|AS(MS|PL|AS)|YOU[EFKLXY]|VOID)$ (provided value: ABCD)"
  })
  void checkStageReachedCode(
      int claimIdBit,
      String stageReachedCode,
      AreaOfLaw areaOfLaw,
      boolean expectError,
      String expectedCode,
      String expectedMessage,
      String expectedTechnical) {
    UUID claimId = new UUID(claimIdBit, claimIdBit);
    Claim claim =
        new Claim()
            .id(claimId)
            .feeCode("feeCode1")
            .caseStartDate("2025-08-14")
            .status(ClaimStatus.READY_TO_PROCESS)
            .uniqueFileNumber("010101/123")
            .stageReachedCode(stageReachedCode)
            .areaOfLaw(areaOfLaw);

    ValidationContext context = ValidationContext.builder().build();
    List<ValidationIssue> issues = validator.validate(claim, context);

    if (expectError) {
      assertThat(issues).hasSize(1);
      assertThat(issues.get(0).getCode()).isEqualTo(expectedCode);
      assertThat(issues.get(0).getMessage()).isEqualTo(expectedMessage);
      assertThat(issues.get(0).getTechnicalMessage()).isEqualTo(expectedTechnical);
    } else {
      assertThat(issues).isEmpty();
    }
  }

  @ParameterizedTest(name = "stageReachedCode={0}")
  @ValueSource(
      strings = {
        "INVA", "INVB", "INVC", "INVD", "INVE", "INVF", "INVG", "INVH", "INVI", "INVJ",
        "INVK", "INVL", "INVM", "PRIA", "PRIB", "PRIC", "PRID", "PRIE", "PROC", "PROD",
        "PROE", "PROF", "PROH", "PROI", "PROJ", "PROK", "PROL", "PROP", "PROT", "PROU",
        "PROV", "PROW", "APPA", "APPB", "APPC", "ASMS", "ASPL", "ASAS", "YOUE", "YOUF",
        "YOUK", "YOUL", "YOUX", "YOUY", "VOID"
      })
  void checkStageReachedCodeForAllAllowedCodesForCrimeLower(String stageReachedCode) {
    UUID claimId = new UUID(1, 1);
    Claim claim =
        new Claim()
            .id(claimId)
            .feeCode("feeCode1")
            .caseStartDate("2025-08-14")
            .status(ClaimStatus.READY_TO_PROCESS)
            .uniqueFileNumber("010101/123")
            .stageReachedCode(stageReachedCode)
            .areaOfLaw(AreaOfLaw.CRIME_LOWER);

    ValidationContext context = ValidationContext.builder().build();
    List<ValidationIssue> issues = validator.validate(claim, context);
    assertThat(issues).isEmpty();
  }

  @Test
  @Disabled(
      "TODO: Duplicate error test not relevant in new codebase; validator returns issues, does not add to context.")
  void shouldNotAddDuplicateRegexValidationError() {
    // Not applicable in new codebase
  }

  // Removed: exceptionIsThrownForUnrecognisedAreaOfLaw (redundant in new codebase)

  // Regex patterns are private in validator; define here if needed for future assertions.
}
