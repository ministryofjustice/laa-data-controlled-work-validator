package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.config.ExclusionsRegistry;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.config.MandatoryFieldsRegistry;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.MandatoryFieldClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.UniqueFileNumberClaimValidator;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

class ClaimValidationTest {

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
    Claim claim = Claim.builder()
        .areaOfLaw(AreaOfLaw.CRIME_LOWER)
        .build();
    // Missing mandatory fields for CRIME_LOWER: caseConcludedDate, stageReachedCode, etc.
    ClaimValidationContext context = ClaimValidationContext.builder().scope("fee").build();

    List<ValidationIssue> issues = mandatoryFieldClaimValidator.validate(claim, context);

    assertThat(issues).isNotEmpty();
    assertThat(issues.getFirst().getCode()).isEqualTo("MISSING_MANDATORY_FIELD");
    assertThat(issues.getFirst().getSeverity()).isEqualTo(ValidationSeverity.ERROR);
  }

  @Test
  void mandatoryFieldValidator_returnsNoErrorsWhenAllMandatoryFieldsPresent() {
    Claim claim = Claim.builder()
        .areaOfLaw(AreaOfLaw.CRIME_LOWER)
        .caseConcludedDate("2025-01-15")
        .stageReachedCode("PROA")
        .netProfitCostsAmount(new java.math.BigDecimal("100.00"))
        .disbursementsVatAmount(new java.math.BigDecimal("20.00"))
        .build();
    ClaimValidationContext context = ClaimValidationContext.builder().scope("fee").build();

    List<ValidationIssue> issues = mandatoryFieldClaimValidator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void mandatoryFieldValidator_returnsNoErrorsWhenNoAreaOfLaw() {
    Claim claim = Claim.builder().build();
    // No area of law set - no mandatory fields to check
    ClaimValidationContext context = ClaimValidationContext.builder().scope("fee").build();

    List<ValidationIssue> issues = mandatoryFieldClaimValidator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void uniqueFileNumberValidator_returnsNoErrorsWhenUfnValid() {
    Claim claim = Claim.builder()
        .uniqueFileNumber("010120/001")
        .build();
    ClaimValidationContext context = ClaimValidationContext.builder().build();

    List<ValidationIssue> issues = uniqueFileNumberClaimValidator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void uniqueFileNumberValidator_returnsErrorWhenUfnFormatInvalid() {
    Claim claim = Claim.builder()
        .uniqueFileNumber("invalid-format")
        .build();
    ClaimValidationContext context = ClaimValidationContext.builder().build();

    List<ValidationIssue> issues = uniqueFileNumberClaimValidator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.getFirst().getCode()).isEqualTo("INVALID_DATE_IN_UNIQUE_FILE_NUMBER");
  }

  @Test
  void uniqueFileNumberValidator_returnsErrorWhenUfnDateInFuture() {
    Claim claim = Claim.builder()
        .uniqueFileNumber("010149/001")
        .build();
    ClaimValidationContext context = ClaimValidationContext.builder().build();

    List<ValidationIssue> issues = uniqueFileNumberClaimValidator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.getFirst().getCode()).isEqualTo("INVALID_DATE_IN_UNIQUE_FILE_NUMBER");
  }

  @Test
  void uniqueFileNumberValidator_returnsNoErrorsWhenUfnMissing() {
    Claim claim = Claim.builder().build();
    ClaimValidationContext context = ClaimValidationContext.builder().build();

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
