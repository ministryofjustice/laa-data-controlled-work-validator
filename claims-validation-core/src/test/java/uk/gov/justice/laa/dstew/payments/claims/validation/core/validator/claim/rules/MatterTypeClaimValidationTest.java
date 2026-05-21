package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;

@DisplayName("Matter type claim with area of law validator test")
class MatterTypeClaimValidationTest {

  MatterTypeClaimValidator validator;

  @BeforeEach
  void beforeEach() {
    validator = new MatterTypeClaimValidator();
  }

  @ParameterizedTest(
      name = "{index} => claimId={0}, matterType={1}, areaOfLaw={2}, regex={3}, expectError={4}")
  @CsvSource({
    "1, BadMatterType, LEGAL_HELP, '^[a-zA-Z0-9]{1,4}[-:][a-zA-Z0-9]{1,4}$', true, Each Matter Type Code 1 and 2 must be 4 characters",
    "2, ab12:bc24, LEGAL_HELP, '^[a-zA-Z0-9]{1,4}[-:][a-zA-Z0-9]{1,4}$', false, NA",
    "3, AB-CD, LEGAL_HELP, '^[a-zA-Z0-9]{1,4}[-:][a-zA-Z0-9]{1,4}$', false, NA",
    "4, ABCD:EFGH, MEDIATION, '^[A-Z]{4}[-:][A-Z]{4}$', false, NA",
    "5, AB12:CD34, MEDIATION, '^[A-Z]{4}[-:][A-Z]{4}$', true, Each Matter Type Code 1 and 2 must be 4 uppercase characters",
    "6, AB-CD, MEDIATION, '^[A-Z]{4}[-:][A-Z]{4}$', true, Each Matter Type Code 1 and 2 must be 4 uppercase characters",
  })
  void checkMatterType(
      int claimIdBit,
      String matterTypeCode,
      AreaOfLaw areaOfLaw,
      String regex,
      boolean expectError,
      String expectedDisplayMessage) {
    UUID claimId = new UUID(claimIdBit, claimIdBit);
    Claim claim = Claim.builder().id(claimId).matterTypeCode(matterTypeCode).areaOfLaw(areaOfLaw).build();
    ClaimValidationContext context = ClaimValidationContext.builder().build();

    validator.validate(claim, context);

    if (expectError) {
      String expectedMessage =
          String.format(
              "matter_type_code (%s): does not match the regex pattern %s (provided value: %s)",
              areaOfLaw, regex, matterTypeCode);
      assertThat(context.getIssues()).isNotEmpty();
      ValidationIssue issue = context.getIssues().get(0);
      assertThat(issue.getTechnicalMessage()).isEqualTo(expectedMessage);
      assertThat(issue.getMessage()).isEqualTo(expectedDisplayMessage);
    } else {
      assertThat(context.getIssues()).isEmpty();
    }
  }

  @Test
  void shouldNotAddDuplicateRegexValidationError() {
    UUID claimId = new UUID(1, 1);
    Claim claim =
        Claim.builder()
            .id(claimId)
            .feeCode("feeCode1")
            .caseStartDate("2025-08-14")
            .status(ClaimStatus.READY_TO_PROCESS)
            .uniqueFileNumber("010101/123")
            .matterTypeCode("ABCDE:ABCDE") // invalid matter type code
            .areaOfLaw(AreaOfLaw.LEGAL_HELP)
            .build();

    ClaimValidationContext context = ClaimValidationContext.builder().build();

    validator.validate(claim, context);

    // Only one error should exist (no duplicate)
    assertThat(context.getIssues()).hasSizeLessThanOrEqualTo(1);
  }

  @Test
  @DisplayName("MatterTypeClaimValidator - priority, appliesTo and validator code")
  void matterTypeValidatorMetadata() {
    assertThat(validator.priority()).isEqualTo(100);
    assertThat(validator.appliesTo(Set.of("CLAIM_MATTER_TYPE"))).isTrue();
    assertThat(validator.getValidatorCode()).isEqualTo("CLAIM_MATTER_TYPE");
  }
}
