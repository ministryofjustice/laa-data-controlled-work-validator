package uk.gov.justice.laa.dstew.payments.claims.validation.core.error;

import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;

/**
 * Common interface for validation error enums. Provides default helpers to convert an enum
 * constant into a {@link ValidationIssue}.
 */
public interface ValidationError {

  /**
   * The human-facing message template (may include printf-style placeholders).
   */
  String getDisplayMessage();

  /**
   * Optional technical message for internal diagnostics.
   */
  String getTechnicalMessage();

  /**
   * The severity of this error.
   */
  ValidationSeverity getSeverity();

  /**
   * field name associated with this validation error enum value.
   */
  String getField();

  /**
   * Convert this error to a {@link ValidationIssue} using optional format params.
   */
  default ValidationIssue toValidationIssue(Object... params) {
    String code = ((Enum<?>) this).name();
    String message = String.format(getDisplayMessage(), params);
    return ValidationIssue.builder()
        .code(code)
        .message(message)
        .path(getField())
        .severity(getSeverity())
        .technicalMessage(getTechnicalMessage())
        .build();
  }

  /**
   * Convert this error to a {@link ValidationIssue} using a custom technical message.
   */
  default ValidationIssue toValidationIssueWithTechnicalMessage(String technicalMsg,
                                                                Object... params) {
    String code = ((Enum<?>) this).name();
    String message = String.format(getDisplayMessage(), params);
    return ValidationIssue.builder()
        .code(code)
        .message(message)
        .path(getField())
        .severity(getSeverity())
        .technicalMessage(technicalMsg)
        .build();
  }
}
