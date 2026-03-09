package uk.gov.justice.laa.dstew.payments.claims.validation.validator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules.MandatoryFieldValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules.UniqueFileNumberValidator;

class ClaimValidatorTest {

  private final MandatoryFieldValidator mandatoryFieldValidator = new MandatoryFieldValidator();
  private final UniqueFileNumberValidator uniqueFileNumberValidator =
      new UniqueFileNumberValidator();

  @Test
  void mandatoryFieldValidator_returnsErrorWhenFeeCodeMissing() {
    Claim claim = new Claim();
    // Missing areaOfLaw, officeAccountNumber, and feeCode
    ValidationContext context = ValidationContext.builder().scope("fee").build();

    List<ValidationIssue> issues = mandatoryFieldValidator.validate(claim, context);

    assertThat(issues).hasSize(3);
    assertThat(issues.getFirst().getCode()).isEqualTo("MISSING_MANDATORY_FIELD");
    assertThat(issues.getFirst().getSeverity()).isEqualTo(ValidationSeverity.ERROR);
  }

  @Test
  void mandatoryFieldValidator_returnsNoErrorsWhenFeeCodePresent() {
    Claim claim = new Claim();
    claim.setFeeCode("ABC123");
    claim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);
    claim.setOfficeAccountNumber("1A234B");
    ValidationContext context = ValidationContext.builder().scope("fee").build();

    List<ValidationIssue> issues = mandatoryFieldValidator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void uniqueFileNumberValidator_returnsNoErrorsWhenUfnValid() {
    Claim claim = new Claim();
    claim.setUniqueFileNumber("010120/001");
    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = uniqueFileNumberValidator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void uniqueFileNumberValidator_returnsErrorWhenUfnFormatInvalid() {
    Claim claim = new Claim();
    claim.setUniqueFileNumber("invalid-format");
    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = uniqueFileNumberValidator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.getFirst().getCode()).isEqualTo("INVALID_UNIQUE_FILE_NUMBER_FORMAT");
  }

  @Test
  void uniqueFileNumberValidator_returnsErrorWhenUfnDateInFuture() {
    Claim claim = new Claim();
    // Use a date far in the future
    claim.setUniqueFileNumber("010199/001");
    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = uniqueFileNumberValidator.validate(claim, context);

    // Date 01/01/99 is interpreted as 2099 which is in the future
    assertThat(issues).hasSize(1);
    assertThat(issues.getFirst().getCode()).isEqualTo("INVALID_DATE_IN_UNIQUE_FILE_NUMBER");
  }

  @Test
  void uniqueFileNumberValidator_returnsNoErrorsWhenUfnMissing() {
    Claim claim = new Claim();
    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = uniqueFileNumberValidator.validate(claim, context);

    // UFN is optional - MandatoryFieldValidator handles required check
    assertThat(issues).isEmpty();
  }

  @Test
  void validators_haveDifferentPriorities() {
    assertThat(mandatoryFieldValidator.priority()).isEqualTo(10);
    assertThat(uniqueFileNumberValidator.priority()).isEqualTo(20);
  }

  @Test
  void validators_haveUniqueValidatorCodes() {
    assertThat(mandatoryFieldValidator.getValidatorCode()).isEqualTo("MANDATORY_FIELD");
    assertThat(uniqueFileNumberValidator.getValidatorCode()).isEqualTo("UNIQUE_FILE_NUMBER");
  }
}
