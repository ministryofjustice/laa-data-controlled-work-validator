package uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ValidationContext;

class DisbursementsValidatorTest {

  private final DisbursementsValidator validator = new DisbursementsValidator();

  @Test
  void validate_returnsNoErrors_whenVatAmountWithinLimit() {
    Claim claim = new Claim();
    claim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);
    claim.setDisbursementsVatAmount(new BigDecimal("1000.00"));

    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void validate_returnsError_whenVatAmountExceedsLegalHelpLimit() {
    Claim claim = new Claim();
    claim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);
    claim.setDisbursementsVatAmount(new BigDecimal("100000.00"));

    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).getCode()).isEqualTo("INVALID_DISBURSEMENT_VAT_AMOUNT");
  }

  @Test
  void validate_allowsHigherLimit_forCrimeLower() {
    Claim claim = new Claim();
    claim.setAreaOfLaw(AreaOfLaw.CRIME_LOWER);
    claim.setDisbursementsVatAmount(new BigDecimal("500000.00"));

    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void validate_returnsError_whenCrimeLowerLimitExceeded() {
    Claim claim = new Claim();
    claim.setAreaOfLaw(AreaOfLaw.CRIME_LOWER);
    claim.setDisbursementsVatAmount(new BigDecimal("1000000.00"));

    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).hasSize(1);
  }

  @Test
  void validate_returnsNoErrors_whenNoVatAmount() {
    Claim claim = new Claim();
    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).isEmpty();
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
