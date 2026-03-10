package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;

class StageReachedValidatorTest {

  private final StageReachedValidator validator = new StageReachedValidator();

  @Test
  void validate_returnsNoErrors_whenValidLegalHelpCode() {
    Claim claim = new Claim();
    claim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);
    claim.setStageReachedCode("AB");

    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void validate_returnsError_whenInvalidLegalHelpCode() {
    Claim claim = new Claim();
    claim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);
    claim.setStageReachedCode("TOOLONG");

    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).getCode()).isEqualTo("INVALID_STAGE_REACHED");
  }

  @Test
  void validate_returnsNoErrors_whenValidCrimeLowerCode() {
    Claim claim = new Claim();
    claim.setAreaOfLaw(AreaOfLaw.CRIME_LOWER);
    claim.setStageReachedCode("INVA");

    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void validate_returnsNoErrors_whenNoStageReached() {
    Claim claim = new Claim();
    claim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);

    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void validate_returnsNoErrors_whenNoAreaOfLaw() {
    Claim claim = new Claim();
    claim.setStageReachedCode("XX");

    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    // No validation without area of law
    assertThat(issues).isEmpty();
  }

  @Test
  void getValidatorCode_returnsStageReached() {
    assertThat(validator.getValidatorCode()).isEqualTo("STAGE_REACHED");
  }
}
