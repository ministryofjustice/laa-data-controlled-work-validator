package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;

class CaseDatesValidatorTest {

  private final CaseDatesValidator validator = new CaseDatesValidator();

  @Test
  void validate_returnsNoErrors_whenAllDatesValid() {
    Claim claim = new Claim();
    claim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);
    claim.setCaseStartDate("2020-01-15");
    claim.setCaseConcludedDate("2020-06-15");
    claim.setTransferDate("2020-03-01");
    claim.setRepresentationOrderDate("2020-01-10");

    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void validate_returnsError_whenCaseStartDateInFuture() {
    Claim claim = new Claim();
    claim.setCaseStartDate("2030-01-15");

    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).getCode()).contains("CASE_START_DATE");
  }

  @Test
  void validate_returnsError_whenCaseStartDateTooOld() {
    Claim claim = new Claim();
    claim.setCaseStartDate("1990-01-15");

    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).getMessage()).contains("1995");
  }

  @Test
  void validate_returnsError_whenInvalidDateFormat() {
    Claim claim = new Claim();
    claim.setCaseStartDate("not-a-date");

    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).getCode()).contains("FORMAT");
  }

  @Test
  void validate_returnsNoErrors_whenDatesNotProvided() {
    Claim claim = new Claim();
    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void validate_usesCorrectEarliestDate_forCrimeLower() {
    Claim claim = new Claim();
    claim.setAreaOfLaw(AreaOfLaw.CRIME_LOWER);
    claim.setCaseConcludedDate("2014-01-15"); // Before 2016 min for crime lower

    ValidationContext context = ValidationContext.builder().build();

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
