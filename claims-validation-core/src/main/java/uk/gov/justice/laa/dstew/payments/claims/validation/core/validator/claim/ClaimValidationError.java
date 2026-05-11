
package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;


/**
 * Enumeration of all claim validation errors. Each error contains a display message, optional
 * technical message, source, and severity.
 */
@Getter
@RequiredArgsConstructor
public enum ClaimValidationError implements ValidationError {

  // JSON/HTTP parsing errors (used by REST controller advice)
  INVALID_JSON_SYNTAX(
      "The request body could not be parsed",
      null,
      ValidationSeverity.ERROR
  ),
  INVALID_FIELD_TYPE(
      "Field '%s' has an invalid value",
      null,
      ValidationSeverity.ERROR
  ),

  // Schema validation errors
  // Note: SCHEMA_VALIDATION_ERROR and SCHEMA_CONFIG_WARNING have been moved to the shared
  // SchemaValidationError enum in validator/schema/ — use that for all schema validation codes.
  INVALID_JSON_SCHEMA(
      "The claim does not conform to the expected schema",
      "JSON schema validation failed",
      ValidationSeverity.ERROR
  ),

  // Date validation errors
  INVALID_CASE_START_DATE(
      "Case Start Date must be between %s and today",
      null,
      ValidationSeverity.ERROR
  ),
  INVALID_CASE_CONCLUDED_DATE(
      "%s",
      null,
      ValidationSeverity.ERROR
  ),
  INVALID_TRANSFER_DATE(
      "Transfer Date must be between %s and today",
      null,
      ValidationSeverity.ERROR
  ),
  INVALID_REPRESENTATION_ORDER_DATE(
      "Representation Order Date must be between %s and today",
      null,
      ValidationSeverity.ERROR
  ),
  INVALID_CLIENT_DATE_OF_BIRTH(
      "Client Date of Birth must be between 01/01/1900 and today",
      null,
      ValidationSeverity.ERROR
  ),
  INVALID_CLIENT_2_DATE_OF_BIRTH(
      "Client 2 Date of Birth must be between 01/01/1900 and today",
      null,
      ValidationSeverity.ERROR
  ),
  INVALID_DATE_FORMAT(
      "Invalid date value provided for %s",
      null,
      ValidationSeverity.ERROR
  ),

  // Unique File Number (UFN) errors
  INVALID_UNIQUE_FILE_NUMBER_FORMAT(
      "Unique File Number (UFN) must be in the format DDMMYY/NNN",
      null,
      ValidationSeverity.ERROR
  ),
  INVALID_DATE_IN_UNIQUE_FILE_NUMBER(
      "Unique File Number (UFN) must be in the format DDMMYY/NNN with a date in the past",
      null,
      ValidationSeverity.ERROR
  ),

  // Mandatory field errors
  MISSING_MANDATORY_FIELD(
      "%s is required for %s claims",
      null,
      ValidationSeverity.ERROR
  ),
  MISSING_CLAIM(
      "No claim data provided for validation",
      null,
      ValidationSeverity.ERROR
  ),

  // Category of law / fee code errors
  INVALID_CATEGORY_OF_LAW_AND_FEE_CODE(
      "A category of law could not be found for the provided fee code: %s",
      null,
      ValidationSeverity.ERROR
  ),
  INVALID_CATEGORY_OF_LAW_NOT_AUTHORISED_FOR_PROVIDER(
      "The provider is not contracted for the category of law associated with the fee code",
      null,
      ValidationSeverity.ERROR
  ),
  INVALID_FEE_CALCULATION_VALIDATION_FAILED(
      "A validation error occurred when attempting to calculate the fee for this claim",
      null,
      ValidationSeverity.ERROR
  ),

  // Technical errors
  TECHNICAL_ERROR_FEE_CALCULATION_SERVICE(
      "A technical error occurred, please try again after some time",
      "A technical error occurred when attempting to make a request to the fee calculation service",
      ValidationSeverity.ERROR
  ),
  TECHNICAL_ERROR_PROVIDER_DETAILS_API(
      "A technical error occurred, please try again after some time",
      "A technical error occurred when attempting to make a request to the Provider Details API",
      ValidationSeverity.ERROR
  ),
  TECHNICAL_ERROR_FEE_SCHEME_API(
      "A technical error occurred, please try again after some time",
      "A technical error occurred when attempting to make a request to the Fee Scheme API",
      ValidationSeverity.ERROR
  ),
  TECHNICAL_ERROR_DATA_CLAIMS_API(
      "Unable to complete duplicate claim check due to a technical error. Please try again later.",
      "Data Claims API error",
      ValidationSeverity.ERROR
  ),

  // Duplicate claim errors
  INVALID_CLAIM_HAS_DUPLICATE_IN_EXISTING_SUBMISSION(
      "A duplicate claim was found within the same submission",
      null,
      ValidationSeverity.ERROR
  ),
  INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION(
      "A duplicate claim was found in another submission",
      null,
      ValidationSeverity.ERROR
  ),

  // Stage reached errors
  INVALID_STAGE_REACHED_LEGAL_HELP(
      "Stage Reached Code must be exactly 2 alphanumeric characters for Legal Help claims",
      null,
      ValidationSeverity.ERROR
  ),
  INVALID_STAGE_REACHED_CRIME_LOWER(
      "Stage Reached Code must be one of the allowed values for Crime Lower claims",
      null,
      ValidationSeverity.ERROR
  ),
  INVALID_STAGE_REACHED(
      "Invalid stage reached value",
      null,
      ValidationSeverity.ERROR
  ),

  // Disbursement errors
  DISBURSEMENT_TOO_EARLY(
      "Disbursement claims can only be submitted "
              + "at least %d calendar months after the Case Start Date %s",
      null,
      ValidationSeverity.ERROR
  ),

  // Matter type errors
  INVALID_MATTER_TYPE_CODE(
      "Invalid matter type code: %s",
      null,
      ValidationSeverity.ERROR
  ),

  // Outcome code errors
  INVALID_OUTCOME_CODE(
      "%s",
      null,
      ValidationSeverity.ERROR
  ),

  // Disbursement errors
  INVALID_DISBURSEMENT_VAT_AMOUNT(
      "Disbursements VAT Amount has exceeded the maximum accepted value",
      null,
      ValidationSeverity.ERROR
  ),
  INVALID_DISBURSEMENT_START_DATE(
      "Disbursement claim start date is invalid",
      null,
      ValidationSeverity.ERROR
  ),

  // Schedule reference errors
  INVALID_SCHEDULE_REFERENCE(
      "Schedule Reference must be a maximum of 20 characters "
              + "and contain only letters, numbers, forward slashes, periods, and hyphens",
      null,
      ValidationSeverity.ERROR
  ),

  // Warning level issues
  CLAIM_DATA_INCOMPLETE(
      "Some optional claim data is missing",
      null,
      ValidationSeverity.WARNING
  );

  private final String displayMessage;
  private final String technicalMessage;
  private final ValidationSeverity severity;
}
