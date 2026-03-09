package uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ValidationContext;

class StageReachedValidatorTest {

  private final StageReachedValidator validator = new StageReachedValidator();

  @Test
  void validate_returnsNoErrors_whenValidLegalHelpCode() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("stageReachedCode", "AB");

    ValidationContext context = ValidationContext.builder()
        .areaOfLaw("LEGAL_HELP")
        .build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void validate_returnsError_whenInvalidLegalHelpCode() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("stageReachedCode", "TOOLONG");

    ValidationContext context = ValidationContext.builder()
        .areaOfLaw("LEGAL_HELP")
        .build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).getCode()).isEqualTo("INVALID_STAGE_REACHED");
  }

  @Test
  void validate_returnsNoErrors_whenValidCrimeLowerCode() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("stageReachedCode", "INVA");

    ValidationContext context = ValidationContext.builder()
        .areaOfLaw("CRIME_LOWER")
        .build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void validate_returnsNoErrors_whenNoStageReached() {
    Map<String, Object> claim = new HashMap<>();
    ValidationContext context = ValidationContext.builder()
        .areaOfLaw("LEGAL_HELP")
        .build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void validate_returnsNoErrors_whenNoAreaOfLaw() {
    Map<String, Object> claim = new HashMap<>();
    claim.put("stageReachedCode", "XX");

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

