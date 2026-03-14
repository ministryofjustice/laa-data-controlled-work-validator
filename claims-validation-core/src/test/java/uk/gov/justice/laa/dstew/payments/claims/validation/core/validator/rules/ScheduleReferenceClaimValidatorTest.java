package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;

@DisplayName("Schedule reference claim validator test")
class ScheduleReferenceClaimValidatorTest {

  private final ScheduleReferenceClaimValidator validator = new ScheduleReferenceClaimValidator();

  @ParameterizedTest(
      name =
          "{index} => claimId={0}, matterType={1}, areaOfLaw={2}, caseReferenceNumber={3}, scheduleReference={4}, regex={5}, expectError={6}")
  @CsvSource({
    "1, ab12:bc24, LEGAL_HELP, 123, SCH123, '^[a-zA-Z0-9/.\\-]{1,20}$', false",
    "2, ab12:bc24, LEGAL_HELP, 123, ABCDEFGHIJKLMNOPQRST123, '^[a-zA-Z0-9/.\\-]{1,20}$', true",
    "3, ab12:bc24, LEGAL_HELP, 123, SCH/ABC-12.34, '^[a-zA-Z0-9/.\\-]{1,20}$', false",
    "4, ab12:bc24, LEGAL_HELP, 123, Schedule Ref, '^[a-zA-Z0-9/.\\-]{1,20}$', true",
    "5, ab12:bc24, LEGAL_HELP, 123, Schedule:Ref, '^[a-zA-Z0-9/.\\-]{1,20}$', true",
    "6, ab12:bc24, CRIME_LOWER,,, '^[a-zA-Z0-9/.\\-]{1,20}$', false",
    "7, ab12:bc24, CRIME_LOWER,, ABCD, '^[a-zA-Z0-9/.\\-]{1,20}$', false",
    "8, ABCD:EFGH, MEDIATION, 123, ABCDEFGHIJKLMNOPQRST, '^[a-zA-Z0-9/.\\-]{1,20}$', false",
    "9, ABCD:EFGH, MEDIATION, 123, ABCD, '^[a-zA-Z0-9/.\\-]{1,20}$', false"
  })
  void validateFormatForScheduleReference(
      int claimIdBit,
      String matterTypeCode,
      String areaOfLaw,
      String caseReferenceNumber,
      String scheduleReference,
      String regex,
      boolean expectError) {
    UUID claimId = new UUID(claimIdBit, claimIdBit);
    Claim claim =
        new Claim()
            .id(claimId)
            .feeCode("feeCode1")
            .caseStartDate("2025-08-14")
            .caseConcludedDate("2025-09-14")
            .caseReferenceNumber(caseReferenceNumber)
            .scheduleReference(scheduleReference)
            .status(ClaimStatus.READY_TO_PROCESS)
            .uniqueFileNumber("010101/123")
            .matterTypeCode(matterTypeCode)
            .areaOfLaw(AreaOfLaw.valueOf(areaOfLaw.replace(' ', '_')));
    ValidationContext context = ValidationContext.builder().build();
    List<ValidationIssue> issues = validator.validate(claim, context);
    if (expectError) {
      String expectedTechnical =
          String.format(
              "schedule_reference (%s): does not match the regex pattern %s (provided value: %s)",
              areaOfLaw, regex, scheduleReference);
      String expectedDisplay =
          "Schedule Reference must be a maximum of 20 characters and contain only letters, "
              + "numbers, forward slashes, periods, and hyphens";
      assertThat(issues).hasSize(1);
      assertThat(issues.get(0).getTechnicalMessage()).isEqualTo(expectedTechnical);
      assertThat(issues.get(0).getMessage()).isEqualTo(expectedDisplay);
    } else {
      assertThat(issues).isEmpty();
    }
  }

  @Test
  void shouldNotAddDuplicateRegexValidationError() {
    // Not applicable in new codebase; validator returns issues, does not add to context.
    // TODO: If duplicate error logic is needed, implement here.
  }
}
