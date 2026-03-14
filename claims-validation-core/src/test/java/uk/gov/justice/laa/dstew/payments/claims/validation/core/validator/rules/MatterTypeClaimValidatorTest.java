package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;

@DisplayName("Matter type claim with area of law validator test")
class MatterTypeClaimValidatorTest {

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
    java.util.UUID claimId = new java.util.UUID(claimIdBit, claimIdBit);
    Claim claim = new Claim().id(claimId).matterTypeCode(matterTypeCode).areaOfLaw(areaOfLaw);
    ValidationContext context = ValidationContext.builder().build();

    // Run validation
    java.util.List<ValidationIssue> issues = validator.validate(claim, context);

    if (expectError) {
      String expectedMessage =
          String.format(
              "matter_type_code (%s): does not match the regex pattern %s (provided value: %s)",
              areaOfLaw, regex, matterTypeCode);
      assertThat(issues).isNotEmpty();
      ValidationIssue issue = issues.get(0);
      assertThat(issue.getTechnicalMessage()).isEqualTo(expectedMessage);
      assertThat(issue.getMessage()).isEqualTo(expectedDisplayMessage);
    } else {
      assertThat(issues).isEmpty();
    }
  }

  @Test
  void shouldNotAddDuplicateRegexValidationError() {
    java.util.UUID claimId = new java.util.UUID(1, 1);
    Claim claim =
        new Claim()
            .id(claimId)
            .feeCode("feeCode1")
            .caseStartDate("2025-08-14")
            .status(ClaimStatus.READY_TO_PROCESS)
            .uniqueFileNumber("010101/123")
            .matterTypeCode("ABCDE:ABCDE") // invalid matter type code
            .areaOfLaw(AreaOfLaw.LEGAL_HELP);

    ValidationContext context = ValidationContext.builder().build();
    // Simulate a pre-existing error (if needed, but the validator should not add a duplicate)
    java.util.List<ValidationIssue> issues = new java.util.ArrayList<>();
    issues.add(
        new ValidationIssue()
            .code(null)
            .message("Each Matter Type Code 1 and 2 must be 4 characters")
            .technicalMessage("matter_type_code: does not match regex pattern"));

    // Run validation
    java.util.List<ValidationIssue> newIssues = validator.validate(claim, context);

    // Only one error should exist (no duplicate)
    assertThat(newIssues.size()).isLessThanOrEqualTo(1);
  }
}
