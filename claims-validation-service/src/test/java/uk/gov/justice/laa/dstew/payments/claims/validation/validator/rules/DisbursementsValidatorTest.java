package uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ValidationContext;

class DisbursementsValidatorTest {

  private final DisbursementsValidator validator = new DisbursementsValidator();

  @Test
  void validate_returnsNoErrors_whenVatAmountWithinLimit() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("disbursementsVatAmount", new BigDecimal("1000.00"));

    ValidationContext context = ValidationContext.builder()
        .areaOfLaw("LEGAL_HELP")
        .build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void validate_returnsError_whenVatAmountExceedsLegalHelpLimit() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("disbursementsVatAmount", new BigDecimal("100000.00"));

    ValidationContext context = ValidationContext.builder()
        .areaOfLaw("LEGAL_HELP")
        .build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).getCode()).isEqualTo("INVALID_DISBURSEMENT_VAT_AMOUNT");
  }

  @Test
  void validate_allowsHigherLimit_forCrimeLower() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("disbursementsVatAmount", new BigDecimal("500000.00"));

    ValidationContext context = ValidationContext.builder()
        .areaOfLaw("CRIME_LOWER")
        .build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void validate_returnsError_whenCrimeLowerLimitExceeded() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("disbursementsVatAmount", new BigDecimal("1000000.00"));

    ValidationContext context = ValidationContext.builder()
        .areaOfLaw("CRIME_LOWER")
        .build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).hasSize(1);
  }

  @Test
  void validate_returnsNoErrors_whenNoVatAmount() {
    Map<String, Object> claim = new HashMap<>();
    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void validate_handlesStringAmount() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("disbursementsVatAmount", "5000.00");

    ValidationContext context = ValidationContext.builder()
        .areaOfLaw("LEGAL_HELP")
        .build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void validate_returnsError_whenInvalidFormat() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("disbursementsVatAmount", "not-a-number");

    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).getCode()).contains("FORMAT");
  }

  @Test
  void appliesTo_returnsTrueForDisbursementScope() {
    assertThat(validator.appliesTo("disbursement")).isTrue();
    assertThat(validator.appliesTo("all")).isTrue();
    assertThat(validator.appliesTo(null)).isTrue();
  }

  @Test
  void appliesTo_returnsFalseForFeeScope() {
    assertThat(validator.appliesTo("fee")).isFalse();
  }

  @Test
  void getValidatorCode_returnsDisbursements() {
    assertThat(validator.getValidatorCode()).isEqualTo("DISBURSEMENTS");
  }
}

