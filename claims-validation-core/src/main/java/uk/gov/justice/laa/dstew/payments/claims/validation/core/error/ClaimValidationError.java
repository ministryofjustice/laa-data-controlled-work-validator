package uk.gov.justice.laa.dstew.payments.claims.validation.core.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationSeverity;

/**
 * Enumeration of all claim validation errors. Each error contains a display message, optional
 * technical message, source, and severity.
 */
@Getter
@RequiredArgsConstructor
public enum ClaimValidationError {

  // Schema validation errors
  INVALID_JSON_SCHEMA(
      "The claim does not conform to the expected schema",
      "JSON schema validation failed",
      "SCHEMA",
      ValidationSeverity.ERROR),

  // Date validation errors
  INVALID_CASE_START_DATE("Case start date is invalid: %s", null, "DATE", ValidationSeverity.ERROR),
  INVALID_CASE_CONCLUDED_DATE(
      "Case concluded date is invalid: %s", null, "DATE", ValidationSeverity.ERROR),
  INVALID_TRANSFER_DATE("Transfer date is invalid: %s", null, "DATE", ValidationSeverity.ERROR),
  INVALID_REPRESENTATION_ORDER_DATE(
      "Representation order date is invalid: %s", null, "DATE", ValidationSeverity.ERROR),
  INVALID_CLIENT_DATE_OF_BIRTH(
      "Client date of birth is invalid: %s", null, "DATE", ValidationSeverity.ERROR),
  INVALID_DATE_FORMAT("%s has an invalid date format", null, "DATE", ValidationSeverity.ERROR),

  // Unique File Number (UFN) errors
  INVALID_UNIQUE_FILE_NUMBER_FORMAT(
      "Unique File Number (UFN) must be in the format DDMMYY/NNN",
      null,
      "UFN",
      ValidationSeverity.ERROR),
  INVALID_DATE_IN_UNIQUE_FILE_NUMBER(
      "Unique File Number (UFN) must be in the format DDMMYY/NNN with a date in the past",
      null,
      "UFN",
      ValidationSeverity.ERROR),

  // Mandatory field errors
  MISSING_MANDATORY_FIELD(
      "Required field '%s' is missing", null, "MANDATORY", ValidationSeverity.ERROR),

  // Category of law / fee code errors
  INVALID_CATEGORY_OF_LAW_AND_FEE_CODE(
      "A category of law could not be found for the provided fee code: %s",
      null, "FEE", ValidationSeverity.ERROR),
  INVALID_CATEGORY_OF_LAW_NOT_AUTHORISED_FOR_PROVIDER(
      "The provider is not contracted for the category of law associated with the fee code",
      null,
      "FEE",
      ValidationSeverity.ERROR),
  INVALID_FEE_CALCULATION_VALIDATION_FAILED(
      "A validation error occurred when attempting to calculate the fee for this claim",
      null,
      "FEE",
      ValidationSeverity.ERROR),

  // Technical errors
  TECHNICAL_ERROR_FEE_CALCULATION_SERVICE(
      "A technical error occurred, please try again after some time",
      "A technical error occurred when attempting to make a request to the fee calculation service",
      "FEE",
      ValidationSeverity.ERROR),
  TECHNICAL_ERROR_PROVIDER_DETAILS_API(
      "A technical error occurred, please try again after some time",
      "A technical error occurred when attempting to make a request to the Provider Details API",
      "PROVIDER",
      ValidationSeverity.ERROR),

  // Duplicate claim errors
  INVALID_CLAIM_HAS_DUPLICATE_IN_EXISTING_SUBMISSION(
      "A duplicate claim was found within the same submission",
      null,
      "DUPLICATE",
      ValidationSeverity.ERROR),
  INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION(
      "A duplicate claim was found in another submission",
      null,
      "DUPLICATE",
      ValidationSeverity.ERROR),

  // Stage reached errors
  INVALID_STAGE_REACHED("Invalid stage reached value", null, "STAGE", ValidationSeverity.ERROR),

  // Matter type errors
  INVALID_MATTER_TYPE_CODE(
      "Invalid matter type code: %s", null, "MATTER_TYPE", ValidationSeverity.ERROR),

  // Outcome code errors
  INVALID_OUTCOME_CODE("Invalid outcome code: %s", null, "OUTCOME", ValidationSeverity.ERROR),

  // Disbursement errors
  INVALID_DISBURSEMENT_VAT_AMOUNT(
      "Disbursement VAT amount is invalid", null, "DISBURSEMENT", ValidationSeverity.ERROR),
  INVALID_DISBURSEMENT_START_DATE(
      "Disbursement claim start date is invalid", null, "DISBURSEMENT", ValidationSeverity.ERROR),

  // Schedule reference errors
  INVALID_SCHEDULE_REFERENCE(
      "Invalid schedule reference", null, "SCHEDULE", ValidationSeverity.ERROR),

  // Warning level issues
  CLAIM_DATA_INCOMPLETE(
      "Some optional claim data is missing", null, "DATA", ValidationSeverity.WARNING);

  private final String displayMessage;
  private final String technicalMessage;
  private final String source;
  private final ValidationSeverity severity;

  /**
   * Converts this error to a ValidationIssue.
   *
   * @param params optional parameters to format into the display message
   * @return a ValidationIssue representing this error
   */
  public ValidationIssue toValidationIssue(Object... params) {
    return new ValidationIssue(this.name(), String.format(displayMessage, params), severity);
  }

  /**
   * Converts this error to a ValidationIssue with a specific path.
   *
   * @param path the JSON path to the field causing the error
   * @param params optional parameters to format into the display message
   * @return a ValidationIssue representing this error with path
   */
  public ValidationIssue toValidationIssueWithPath(java.util.List<Object> path, Object... params) {
    ValidationIssue issue =
        new ValidationIssue(this.name(), String.format(displayMessage, params), severity);
    // TODO: Handle path conversion to ValidationIssuePathInner list
    return issue;
  }
}
