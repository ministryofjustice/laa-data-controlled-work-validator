package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

@DisplayName("Disbursements claim validator test")
class DisbursementsClaimValidationTest {

  DisbursementsClaimValidator validator = new DisbursementsClaimValidator();

  @ParameterizedTest(
      name =
          "{index} => claimId={0}, disbursementVatAmount={1}, areaOfLaw={2}, maxAllowed={3}, "
              + "expectError={4}")
  @CsvSource({
    "1, 99999.99, LEGAL_HELP, 99999.99, false",
    "2, 999999.99, CRIME_LOWER, 999999.99, false",
    "3, 999999999.99, MEDIATION, 999999999.99, false",
    "4, 100000.0, LEGAL_HELP, 99999.99, true",
    "5, 1000000.0, CRIME_LOWER, 999999.99, true",
    "6, 1000000000.0, MEDIATION, 999999999.99, true",
  })
  void checkDisbursementsVatAmount(
      int claimIdBit,
      BigDecimal disbursementsVatAmount,
      AreaOfLaw areaOfLaw,
      BigDecimal maxAllowed, // unused, but kept for parameterization compatibility
      boolean expectError) {
    UUID claimId = new UUID(claimIdBit, claimIdBit);
    Claim.ClaimBuilder claimBuilder = Claim.builder().id(claimId).disbursementsVatAmount(disbursementsVatAmount).areaOfLaw(areaOfLaw);
    if (AreaOfLaw.CRIME_LOWER.equals(areaOfLaw)) {
      claimBuilder.stageReachedCode("ABCD");
    }
    Claim claim = claimBuilder.build();

    ClaimValidationContext context = ClaimValidationContext.builder().build();

    validator.validate(claim, context);

    if (expectError) {
      String expectedMessage = "Disbursements VAT Amount has exceeded the maximum accepted value";
      assertThat(context.getIssues().getFirst().getMessage()).isEqualTo(expectedMessage);
    } else {
      assertThat(context.getIssues()).isEmpty();
    }
  }

  @Test
  @DisplayName("Should not add error when disbursementsVatAmount is null")
  void shouldNotAddErrorWhenVatAmountIsNull() {
    Claim claim = Claim.builder().id(new UUID(1, 1))
        .disbursementsVatAmount(null)
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .build();
    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);
    assertThat(context.getIssues()).isEmpty();
  }

  @Test
  @DisplayName("Should use LEGAL_HELP max when areaOfLaw is null")
  void shouldUseLegalHelpMaxWhenAreaOfLawIsNull() {
    // MAX_VAT_LEGAL_HELP = 99999.99 — value below should pass, value above should fail
    Claim validClaim = Claim.builder().id(new UUID(1, 1))
        .disbursementsVatAmount(new BigDecimal("99999.99"))
        .areaOfLaw(null)
        .build();
    ClaimValidationContext validContext = ClaimValidationContext.builder().build();
    validator.validate(validClaim, validContext);
    assertThat(validContext.getIssues()).isEmpty();

    Claim invalidClaim = Claim.builder().id(new UUID(2, 2))
        .disbursementsVatAmount(new BigDecimal("100000.00"))
        .areaOfLaw(null)
        .build();
    ClaimValidationContext invalidContext = ClaimValidationContext.builder().build();
    validator.validate(invalidClaim, invalidContext);
    assertThat(invalidContext.getIssues()).hasSize(1);
    assertThat(invalidContext.getIssues().getFirst().getMessage())
        .isEqualTo("Disbursements VAT Amount has exceeded the maximum accepted value");
  }

  @Test
  @DisplayName("DisbursementsClaimValidator - priority, appliesTo and validator code")
  void disbursementsValidatorMetadata() {
    assertThat(validator.priority()).isEqualTo(100);
    assertThat(validator.appliesTo(null)).isTrue();
    assertThat(validator.appliesTo("disbursement")).isTrue();
    assertThat(validator.appliesTo("all")).isTrue();
    assertThat(validator.getValidatorCode()).isEqualTo("CLAIM_DISBURSEMENTS");
  }
}
