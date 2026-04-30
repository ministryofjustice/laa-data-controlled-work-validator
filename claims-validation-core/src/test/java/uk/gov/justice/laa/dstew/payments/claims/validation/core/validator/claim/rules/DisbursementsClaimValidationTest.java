package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
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

    // Run validation
    List<ValidationIssue> issues = validator.validate(claim, context);

    if (expectError) {
      String expectedMessage = "Disbursements VAT Amount has exceeded the maximum accepted value";
      assertThat(issues.getFirst().getMessage()).isEqualTo(expectedMessage);
    } else {
      assertThat(issues.size()).isZero();
    }
  }
}
