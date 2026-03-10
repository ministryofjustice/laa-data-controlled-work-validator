package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.config.ExclusionsRegistry;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.config.MandatoryFieldsRegistry;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules.MandatoryFieldClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules.UniqueFileNumberClaimValidator;

class ClaimValidatorTest {

  private MandatoryFieldClaimValidator mandatoryFieldClaimValidator;
  private UniqueFileNumberClaimValidator uniqueFileNumberClaimValidator;

  @BeforeEach
  void setUp() {
    MandatoryFieldsRegistry mandatoryFieldsRegistry = new MandatoryFieldsRegistry();
    ExclusionsRegistry exclusionsRegistry = new ExclusionsRegistry();
    mandatoryFieldClaimValidator =
        new MandatoryFieldClaimValidator(mandatoryFieldsRegistry, exclusionsRegistry);
    uniqueFileNumberClaimValidator = new UniqueFileNumberClaimValidator();
  }

  @Test
  void mandatoryFieldValidator_returnsErrorWhenMandatoryFieldsMissing() {
    Claim claim = new Claim();
    claim.setAreaOfLaw(AreaOfLaw.CRIME_LOWER);
    // Missing mandatory fields for CRIME_LOWER: caseConcludedDate, stageReachedCode, etc.
    ValidationContext context = ValidationContext.builder().scope("fee").build();

    List<ValidationIssue> issues = mandatoryFieldClaimValidator.validate(claim, context);

    assertThat(issues).isNotEmpty();
    assertThat(issues.getFirst().getCode()).isEqualTo("MISSING_MANDATORY_FIELD");
    assertThat(issues.getFirst().getSeverity()).isEqualTo(ValidationSeverity.ERROR);
  }

  @Test
  void mandatoryFieldValidator_returnsNoErrorsWhenAllMandatoryFieldsPresent() {
    Claim claim = new Claim();
    claim.setAreaOfLaw(AreaOfLaw.CRIME_LOWER);
    claim.setCaseConcludedDate("2025-01-15");
    claim.setStageReachedCode("PROA");
    claim.setNetProfitCostsAmount(new java.math.BigDecimal("100.00"));
    claim.setDisbursementsVatAmount(new java.math.BigDecimal("20.00"));
    ValidationContext context = ValidationContext.builder().scope("fee").build();

    List<ValidationIssue> issues = mandatoryFieldClaimValidator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void mandatoryFieldValidator_returnsNoErrorsWhenNoAreaOfLaw() {
    Claim claim = new Claim();
    // No area of law set - no mandatory fields to check
    ValidationContext context = ValidationContext.builder().scope("fee").build();

    List<ValidationIssue> issues = mandatoryFieldClaimValidator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void uniqueFileNumberValidator_returnsNoErrorsWhenUfnValid() {
    Claim claim = new Claim();
    claim.setUniqueFileNumber("010120/001");
    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = uniqueFileNumberClaimValidator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void uniqueFileNumberValidator_returnsErrorWhenUfnFormatInvalid() {
    Claim claim = new Claim();
    claim.setUniqueFileNumber("invalid-format");
    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = uniqueFileNumberClaimValidator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.getFirst().getCode()).isEqualTo("INVALID_DATE_IN_UNIQUE_FILE_NUMBER");
  }

  @Test
  void uniqueFileNumberValidator_returnsErrorWhenUfnDateInFuture() {
    Claim claim = new Claim();
    // Use a date far in the future (49 = 2049 which is in the future)
    claim.setUniqueFileNumber("010149/001");
    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = uniqueFileNumberClaimValidator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.getFirst().getCode()).isEqualTo("INVALID_DATE_IN_UNIQUE_FILE_NUMBER");
  }

  @Test
  void uniqueFileNumberValidator_returnsNoErrorsWhenUfnMissing() {
    Claim claim = new Claim();
    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = uniqueFileNumberClaimValidator.validate(claim, context);

    // UFN is optional - MandatoryFieldValidator handles required check
    assertThat(issues).isEmpty();
  }

  @Test
  void validators_haveUniqueValidatorCodes() {
    assertThat(mandatoryFieldClaimValidator.getValidatorCode()).isEqualTo("MANDATORY_FIELD");
    assertThat(uniqueFileNumberClaimValidator.getValidatorCode()).isEqualTo("UNIQUE_FILE_NUMBER");
  }
}
