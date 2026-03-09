package uk.gov.justice.laa.dstew.payments.claims.validation.validator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules.MandatoryFieldValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules.UniqueFileNumberValidator;

class ClaimValidatorTest {

  private final MandatoryFieldValidator mandatoryFieldValidator = new MandatoryFieldValidator();
  private final UniqueFileNumberValidator uniqueFileNumberValidator =
      new UniqueFileNumberValidator();

  @Test
  void mandatoryFieldValidator_returnsErrorWhenFeeCodeMissing() {
    Map<String, Object> claim = new HashMap<>();
    ValidationContext context = ValidationContext.builder().scope("fee").build();

    List<ValidationIssue> issues = mandatoryFieldValidator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).getCode()).isEqualTo("MISSING_MANDATORY_FIELD");
    assertThat(issues.get(0).getSeverity()).isEqualTo(ValidationIssue.SeverityEnum.ERROR);
  }

  @Test
  void mandatoryFieldValidator_returnsNoErrorsWhenFeeCodePresent() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("feeCode", "ABC123");
    ValidationContext context = ValidationContext.builder().scope("fee").build();

    List<ValidationIssue> issues = mandatoryFieldValidator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void uniqueFileNumberValidator_returnsNoErrorsWhenUfnValid() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("uniqueFileNumber", "010120/001");
    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = uniqueFileNumberValidator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void uniqueFileNumberValidator_returnsErrorWhenUfnFormatInvalid() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("uniqueFileNumber", "invalid-format");
    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = uniqueFileNumberValidator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).getCode()).isEqualTo("INVALID_UNIQUE_FILE_NUMBER_FORMAT");
  }

  @Test
  void uniqueFileNumberValidator_returnsErrorWhenUfnDateInFuture() {
    Map<String, Object> claim = new HashMap<>();
    // Use a date far in the future
    claim.put("uniqueFileNumber", "010199/001");
    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = uniqueFileNumberValidator.validate(claim, context);

    // Date 01/01/99 is interpreted as 2099 which is in the future
    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).getCode()).isEqualTo("INVALID_DATE_IN_UNIQUE_FILE_NUMBER");
  }

  @Test
  void uniqueFileNumberValidator_returnsNoErrorsWhenUfnMissing() {
    Map<String, Object> claim = new HashMap<>();
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
