package uk.gov.justice.laa.dstew.payments.claims.validation.core.error;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;

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
  void toValidationIssueWithPath_returnsIssueWithEmptyPath_whenPathHandlingNotImplemented() {
    // The current implementation does not convert/attach the provided path; it should leave
    // the ValidationIssue.path as the default empty list.
    List<Object> dummyPath = List.of("$.someField", 0, "nested");

    ValidationIssue issue = ClaimValidationError.MISSING_MANDATORY_FIELD
        .toValidationIssueWithPath(dummyPath, "someField", "CRIME_LOWER");

    assertThat(issue).isNotNull();
    assertThat(issue.getCode()).isEqualTo("MISSING_MANDATORY_FIELD");
    assertThat(issue.getMessage()).isEqualTo("someField is required for CRIME_LOWER claims");
    assertThat(issue.getSeverity()).isEqualTo(ValidationSeverity.ERROR);
    // Implementation currently doesn't map the provided path into ValidationIssue.path
    assertThat(issue.getPath()).isNotNull();
    assertThat(issue.getPath()).isEmpty();
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

  @Test
  void toValidationIssueWithPath_nullPath_leavesEmptyPath() {
    // If the path argument is null, current implementation ignores it and leaves the
    // ValidationIssue.path as the default empty list.
    ValidationIssue issue = ClaimValidationError.MISSING_MANDATORY_FIELD
        .toValidationIssueWithPath(null, "someField", "CRIME_LOWER");

    assertThat(issue).isNotNull();
    assertThat(issue.getPath()).isNotNull();
    assertThat(issue.getPath()).isEmpty();
  }
}
