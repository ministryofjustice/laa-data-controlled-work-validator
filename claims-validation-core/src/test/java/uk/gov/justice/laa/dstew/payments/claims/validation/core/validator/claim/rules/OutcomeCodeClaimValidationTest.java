package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.OutcomeCodeClaimValidator.*;

import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;

@DisplayName("Outcome code claim validator test")
class OutcomeCodeClaimValidationTest {

  private final OutcomeCodeClaimValidator validator = new OutcomeCodeClaimValidator();

  private static final Map<AreaOfLaw, String> outcomeCodePatterns =
      Map.of(
          AreaOfLaw.LEGAL_HELP, OUTCOME_CODE_LEGAL_HELP_PATTERN,
          AreaOfLaw.CRIME_LOWER, OUTCOME_CODE_CRIME_LOWER_PATTERN,
          AreaOfLaw.MEDIATION, OUTCOME_CODE_MEDIATION_PATTERN);

  private static final Map<AreaOfLaw, String> outcomeCodeDisplayMessages =
      Map.of(
          AreaOfLaw.LEGAL_HELP,
              "Outcome Code must be exactly 2 characters and contain only letters, numbers, and hyphens",
          AreaOfLaw.CRIME_LOWER,
              "Outcome Code must be a valid crime lower outcome code or left blank",
          AreaOfLaw.MEDIATION, "Outcome Code must be a valid mediation outcome code or left blank");

  @ParameterizedTest(
      name = "{index} => claimId={0}, outcomeCode={1}, areaOfLaw={2}, expectError={3}")
  @CsvSource({
    "1, IX, LEGAL_HELP, false",
    "2, ABCD, LEGAL_HELP, true",
    "3, C9, LEGAL_HELP, false",
    "4, --, LEGAL_HELP, false",
    "5, I@, LEGAL_HELP, true",
    "6, I, LEGAL_HELP, true",
    "7, cp01, CRIME_LOWER, false",
    "8, CP01, CRIME_LOWER, false",
    "9, CP28, CRIME_LOWER, false",
    "10, CN04, CRIME_LOWER, false",
    "11, CN13, CRIME_LOWER, false",
    "12, PL01, CRIME_LOWER, false",
    "13, PL14, CRIME_LOWER, false",
    "14, CP29, CRIME_LOWER, true",
    "15, CN14, CRIME_LOWER, true",
    "16, PL15, CRIME_LOWER, true",
    "17, XY01, CRIME_LOWER, true",
    "18, A!, MEDIATION, true",
    "19, A, MEDIATION, false",
    "20, b, MEDIATION, false",
    "21, -, MEDIATION, true",
    "22, X, MEDIATION, true",
    "23, AB, MEDIATION, true"
  })
  void checkOutcomeCode(
      int claimIdBit, String outcomeCode, AreaOfLaw areaOfLaw, boolean expectError) {
    UUID claimId = new UUID(claimIdBit, claimIdBit);
    Claim claim =
        Claim.builder()
            .id(claimId)
            .feeCode("feeCode1")
            .caseStartDate("2025-08-14")
            .status(ClaimStatus.READY_TO_PROCESS)
            .uniqueFileNumber("010101/123")
            .outcomeCode(outcomeCode)
            .areaOfLaw(areaOfLaw)
            .build();
    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);
    if (expectError) {
      String expectedTechnical =
          String.format(
              "outcome_code (%s): does not match the regex pattern %s (provided value: %s)",
              areaOfLaw, outcomeCodePatterns.get(areaOfLaw), outcomeCode);
      String expectedDisplay = outcomeCodeDisplayMessages.get(areaOfLaw);
      assertThat(context.getIssues()).hasSize(1);
      assertThat(context.getIssues().get(0).getTechnicalMessage()).isEqualTo(expectedTechnical);
      assertThat(context.getIssues().get(0).getMessage()).isEqualTo(expectedDisplay);
    } else {
      assertThat(context.getIssues()).isEmpty();
    }
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
            .outcomeCode("ABCD") // invalid — too long for LEGAL_HELP
            .areaOfLaw(AreaOfLaw.LEGAL_HELP)
            .build();
    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);
    assertThat(context.getIssues()).hasSize(1);
  }

  @Test
  @DisplayName("Should not add error when outcomeCode is null")
  void shouldNotAddErrorWhenOutcomeCodeIsNull() {
    Claim claim = Claim.builder()
        .id(new UUID(1, 1))
        .outcomeCode(null)
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .build();
    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);
    assertThat(context.getIssues()).isEmpty();
  }

  @Test
  @DisplayName("Should not add error when areaOfLaw is null")
  void shouldNotAddErrorWhenAreaOfLawIsNull() {
    Claim claim = Claim.builder()
        .id(new UUID(1, 1))
        .outcomeCode("IX")
        .areaOfLaw(null)
        .build();
    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);
    assertThat(context.getIssues()).isEmpty();
  }

  @Test
  @DisplayName("Should not add error when both outcomeCode and areaOfLaw are null")
  void shouldNotAddErrorWhenBothNull() {
    Claim claim = Claim.builder()
        .id(new UUID(1, 1))
        .outcomeCode(null)
        .areaOfLaw(null)
        .build();
    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);
    assertThat(context.getIssues()).isEmpty();
  }

  @Test
  @DisplayName("OutcomeCodeClaimValidator - priority, appliesTo and validator code")
  void outcomeCodeValidatorMetadata() {
    assertThat(validator.priority()).isEqualTo(100);
    assertThat(validator.appliesTo("any-scope")).isTrue();
    assertThat(validator.getValidatorCode()).isEqualTo("CLAIM_OUTCOME_CODE");
  }
}
