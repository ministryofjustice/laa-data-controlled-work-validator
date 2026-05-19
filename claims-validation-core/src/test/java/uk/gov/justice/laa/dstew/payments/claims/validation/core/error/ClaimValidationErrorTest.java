package uk.gov.justice.laa.dstew.payments.claims.validation.core.error;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;

class ClaimValidationErrorTest {

  @Test
  void toValidationIssue_formatsDisplayMessageAndSetsCodeAndSeverityAndTechnicalMessage() {
    ValidationIssue issue = ClaimValidationError.INVALID_FIELD_TYPE.toValidationIssue("testField");

    assertThat(issue).isNotNull();
    assertThat(issue.getCode()).isEqualTo("INVALID_FIELD_TYPE");
    assertThat(issue.getMessage()).isEqualTo("Field 'testField' has an invalid value");
    assertThat(issue.getSeverity()).isEqualTo(ValidationSeverity.ERROR);
    // INVALID_FIELD_TYPE was declared with a null technical message
    assertThat(issue.getTechnicalMessage()).isNull();
  }

  @Test
  void toValidationIssueWithTechnicalMessage_overridesTechnicalMessage() {
    String customTech = "Custom technical details for debugging";
    ValidationIssue issue = ClaimValidationError.INVALID_JSON_SCHEMA
        .toValidationIssueWithTechnicalMessage(customTech);

    assertThat(issue).isNotNull();
    assertThat(issue.getCode()).isEqualTo("INVALID_JSON_SCHEMA");
    assertThat(issue.getMessage()).isEqualTo("The claim does not conform to the expected schema");
    assertThat(issue.getSeverity()).isEqualTo(ValidationSeverity.ERROR);
    assertThat(issue.getTechnicalMessage()).isEqualTo(customTech);
  }

  @Test
  void toValidationIssue_withNullVarargs_throwsMissingFormatArgumentException() {
    // Passing null as the varargs array currently results in the format treating the
    // single format specifier as a null argument (no exception). Ensure we get a
    // readable message containing the string "null" for the missing parameter.
    ValidationIssue issue = ClaimValidationError.INVALID_FIELD_TYPE
        .toValidationIssue((Object[]) null);

    assertThat(issue).isNotNull();
    assertThat(issue.getMessage()).isEqualTo("Field 'null' has an invalid value");
  }

  @Test
  void toValidationIssue_withTooFewParams_throwsMissingFormatArgumentException() {
    // DISBURSEMENT_TOO_EARLY expects two format args (%d and %s). Supplying only one
    // should result in a MissingFormatArgumentException.
    assertThrows(java.util.MissingFormatArgumentException.class,
        () -> ClaimValidationError.DISBURSEMENT_TOO_EARLY.toValidationIssue(3));
  }

  @Test
  void toValidationIssueWithTechnicalMessage_allowsNullTechnicalMessage() {
    // Passing a null technical message should override any enum technical message and
    // result in a ValidationIssue with a null technicalMessage field.
    ValidationIssue issue = ClaimValidationError.INVALID_JSON_SCHEMA
        .toValidationIssueWithTechnicalMessage(null);

    assertThat(issue).isNotNull();
    assertThat(issue.getCode()).isEqualTo("INVALID_JSON_SCHEMA");
    assertThat(issue.getTechnicalMessage()).isNull();
  }
}
