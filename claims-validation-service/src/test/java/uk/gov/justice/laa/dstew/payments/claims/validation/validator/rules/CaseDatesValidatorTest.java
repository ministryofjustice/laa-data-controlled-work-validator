package uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ValidationContext;

class CaseDatesValidatorTest {

  private final CaseDatesValidator validator = new CaseDatesValidator();

  @Test
  void validate_returnsNoErrors_whenAllDatesValid() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("caseStartDate", "2020-01-15");
    claim.put("caseConcludedDate", "2020-06-15");
    claim.put("transferDate", "2020-03-01");
    claim.put("representationOrderDate", "2020-01-10");

    ValidationContext context = ValidationContext.builder()
        .areaOfLaw("LEGAL_HELP")
        .build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void validate_returnsError_whenCaseStartDateInFuture() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("caseStartDate", "2030-01-15");

    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).getCode()).contains("CASE_START_DATE");
  }

  @Test
  void validate_returnsError_whenCaseStartDateTooOld() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("caseStartDate", "1990-01-15");

    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).getMessage()).contains("1995");
  }

  @Test
  void validate_returnsError_whenInvalidDateFormat() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("caseStartDate", "not-a-date");

    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).getCode()).contains("FORMAT");
  }

  @Test
  void validate_returnsNoErrors_whenDatesNotProvided() {
    Map<String, Object> claim = new HashMap<>();
    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void validate_usesCorrectEarliestDate_forCrimeLower() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("caseConcludedDate", "2014-01-15"); // Before 2016 min for crime lower

    ValidationContext context = ValidationContext.builder()
        .areaOfLaw("CRIME_LOWER")
        .build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).getMessage()).contains("2016");
  }

  @Test
  void getValidatorCode_returnsCaseDates() {
    assertThat(validator.getValidatorCode()).isEqualTo("CASE_DATES");
  }

  @Test
  void priority_returns30() {
    assertThat(validator.priority()).isEqualTo(30);
  }
}

