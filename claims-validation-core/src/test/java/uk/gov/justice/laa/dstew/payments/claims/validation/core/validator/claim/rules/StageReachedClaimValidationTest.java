package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidatorCode;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;

@DisplayName("Stage reached claim validator test")
class StageReachedClaimValidationTest {

  private final StageReachedClaimValidator validator = new StageReachedClaimValidator();

  @ParameterizedTest(
      name =
          "{index} => claimId={0}, stageReachedCode={1}, areaOfLaw={2}, expectError={3}, expectedCode={4}, expectedMessage={5}, expectedTechnical={6}")
  @CsvSource({
    // LEGAL_HELP
    "1, AABB, LEGAL_HELP, true, INVALID_STAGE_REACHED_LEGAL_HELP, Stage Reached Code must be exactly 2 alphanumeric characters for Legal Help claims, stage_reached_code (LEGAL HELP): does not match the regex pattern ^[a-zA-Z0-9]{2}$ (provided value: AABB)",
    "2, AZ, LEGAL_HELP, false, , , ",
    "3, C9, LEGAL_HELP, false, , , ",
    "4, A!, LEGAL_HELP, true, INVALID_STAGE_REACHED_LEGAL_HELP, Stage Reached Code must be exactly 2 alphanumeric characters for Legal Help claims, stage_reached_code (LEGAL HELP): does not match the regex pattern ^[a-zA-Z0-9]{2}$ (provided value: A!)",
    // CRIME_LOWER
    "5, A1, CRIME_LOWER, true, INVALID_STAGE_REACHED_CRIME_LOWER, Stage Reached Code must be one of the allowed values for Crime Lower claims, stage_reached_code (CRIME LOWER): does not match the regex pattern ^(INV[A-M]|PRI[A-E]|PRO[C-FH-LP-TUVW]|APP[ABC]|AS(MS|PL|AS)|YOU[EFKLXY]|VOID)$ (provided value: A1)",
    "6, A-CD, CRIME_LOWER, true, INVALID_STAGE_REACHED_CRIME_LOWER, Stage Reached Code must be one of the allowed values for Crime Lower claims, stage_reached_code (CRIME LOWER): does not match the regex pattern ^(INV[A-M]|PRI[A-E]|PRO[C-FH-LP-TUVW]|APP[ABC]|AS(MS|PL|AS)|YOU[EFKLXY]|VOID)$ (provided value: A-CD)",
    "7, ABCD, CRIME_LOWER, true, INVALID_STAGE_REACHED_CRIME_LOWER, Stage Reached Code must be one of the allowed values for Crime Lower claims, stage_reached_code (CRIME LOWER): does not match the regex pattern ^(INV[A-M]|PRI[A-E]|PRO[C-FH-LP-TUVW]|APP[ABC]|AS(MS|PL|AS)|YOU[EFKLXY]|VOID)$ (provided value: ABCD)"
  })
  void checkStageReachedCode(
      int claimIdBit, String stageReachedCode, AreaOfLaw areaOfLaw, boolean expectError,
      String expectedCode, String expectedMessage, String expectedTechnical) {
    UUID claimId = new UUID(claimIdBit, claimIdBit);
    Claim claim =
        Claim.builder()
            .id(claimId)
            .feeCode("feeCode1")
            .caseStartDate("2025-08-14")
            .status(ClaimStatus.READY_TO_PROCESS)
            .uniqueFileNumber("010101/123")
            .stageReachedCode(stageReachedCode)
            .areaOfLaw(areaOfLaw)
            .build();

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);

    if (expectError) {
      assertThat(context.getIssues()).hasSize(1);
      assertThat(context.getIssues().getFirst().getCode()).isEqualTo(expectedCode);
      assertThat(context.getIssues().getFirst().getMessage()).isEqualTo(expectedMessage);
      assertThat(context.getIssues().getFirst().getTechnicalMessage()).isEqualTo(expectedTechnical);
    } else {
      assertThat(context.getIssues()).isEmpty();
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
        Claim.builder()
            .id(claimId)
            .feeCode("feeCode1")
            .caseStartDate("2025-08-14")
            .status(ClaimStatus.READY_TO_PROCESS)
            .uniqueFileNumber("010101/123")
            .stageReachedCode(stageReachedCode)
            .areaOfLaw(AreaOfLaw.CRIME_LOWER)
            .build();

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);
    assertThat(context.getIssues()).isEmpty();
  }

  @Test
  @DisplayName("Should not add duplicate errors if the field is already in error")
  void shouldNotAddDuplicateRegexValidationError() {
    Claim claim =
        Claim.builder()
            .id(new UUID(1, 1))
            .feeCode("feeCode1")
            .caseStartDate("2025-08-14")
            .status(ClaimStatus.READY_TO_PROCESS)
            .uniqueFileNumber("010101/123")
            .stageReachedCode("INVALID") // invalid for both LEGAL_HELP and CRIME_LOWER
            .areaOfLaw(AreaOfLaw.CRIME_LOWER)
            .build();
    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);
    assertThat(context.getIssues()).hasSize(1);
  }

  @ParameterizedTest(name = "{index} => stageReachedCode={0}, reason={1}")
  @CsvSource(
      nullValues = "NULL",
      value = {
        "NULL,  stageReachedCode is null",
        "'   ', stageReachedCode is blank",
      })
  @DisplayName("Should skip validation when stageReachedCode is null or blank")
  void shouldSkipValidationWhenStageReachedCodeIsNullOrBlank(String stageReachedCode, String reason) {
    Claim claim = Claim.builder()
        .id(new UUID(1, 1))
        .stageReachedCode(stageReachedCode)
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .build();
    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);
    assertThat(context.getIssues()).as(reason).isEmpty();
  }

  @ParameterizedTest(name = "{index} => areaOfLaw={0}, reason={1}")
  @CsvSource(
      nullValues = "NULL",
      value = {
        "NULL,      areaOfLaw is null — no pattern to validate against",
        "MEDIATION, MEDIATION has no pattern defined — default null branch",
      })
  @DisplayName("Should skip validation when areaOfLaw has no pattern defined")
  void shouldSkipValidationWhenAreaOfLawHasNoPattern(AreaOfLaw areaOfLaw, String reason) {
    Claim claim = Claim.builder()
        .id(new UUID(1, 1))
        .stageReachedCode("ABCD")
        .areaOfLaw(areaOfLaw)
        .build();
    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);
    assertThat(context.getIssues()).as(reason).isEmpty();
  }

  @Test
  @DisplayName("StageReachedClaimValidator - priority, appliesTo and validator code")
  void stageReachedValidatorMetadata() {
    assertThat(validator.priority()).isEqualTo(100);
    assertThat(validator.appliesTo(Set.of(ClaimValidatorCode.CLAIM_STAGE_REACHED))).isTrue();
    assertThat(validator.getValidatorCode()).isEqualTo(ClaimValidatorCode.CLAIM_STAGE_REACHED);
  }
}
