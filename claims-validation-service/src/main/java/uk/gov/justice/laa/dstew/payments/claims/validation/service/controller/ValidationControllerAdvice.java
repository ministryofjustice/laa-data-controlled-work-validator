package uk.gov.justice.laa.dstew.payments.claims.validation.service.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ClaimValidationError;

/**
 * Global exception handler for validation controller.
 *
 * <p>Catches JSON parsing and deserialization errors and converts them to validation responses
 * instead of returning 400 Bad Request. This ensures a consistent response format for all
 * detectable issues with the request.
 *
 * <p>Note: This project uses Jackson 3.x (tools.jackson namespace) at runtime. We check exception
 * types by class name since the Jackson 3.x classes aren't available at compile time.
 */
@RestControllerAdvice
@Slf4j
public class ValidationControllerAdvice {

  private static final String UNKNOWN = "unknown";

  // Patterns for extracting information from Jackson exception messages
  private static final Pattern FIELD_PATH_PATTERN = Pattern.compile("\\[\"(\\w+)\"\\]");
  private static final Pattern UNEXPECTED_VALUE_PATTERN =
      Pattern.compile("Unexpected value '([^']*)'");

  /**
   * Handles JSON parsing and deserialization errors.
   *
   * @param ex the exception thrown during message conversion
   * @return 200 OK with validation result containing the error details
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ValidationResult> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex) {

    log.warn("Request parsing error: {}", ex.getMessage());

    Throwable cause = ex.getCause();
    String causeName = cause != null ? cause.getClass().getSimpleName() : "";

    ValidationIssue issue =
        switch (causeName) {
          case "ValueInstantiationException",
                  "InvalidFormatException",
                  "MismatchedInputException" ->
              buildFieldError(cause);
          case "JsonParseException" -> buildJsonSyntaxError(cause);
          default -> buildGenericParsingError(ex);
        };

    return buildValidationResponse(issue);
  }

  private ValidationIssue buildFieldError(Throwable cause) {
    String message = cause.getMessage();
    String fieldPath = extractFieldPath(message);
    String invalidValue = extractInvalidValue(message);

    String technicalMessage =
        String.format("Invalid value '%s' for field '%s': %s", invalidValue, fieldPath, message);

    log.debug("Field error: {}", technicalMessage);

    return ClaimValidationError.INVALID_FIELD_TYPE.toValidationIssueWithTechnicalMessage(
        technicalMessage, fieldPath);
  }

  private ValidationIssue buildJsonSyntaxError(Throwable cause) {
    String technicalMessage = cause.getMessage();

    log.debug("JSON syntax error: {}", technicalMessage);

    ValidationIssue issue = ClaimValidationError.INVALID_JSON_SYNTAX.toValidationIssue();
    issue.setMessage("The request contains invalid JSON syntax");
    issue.setTechnicalMessage(technicalMessage);
    return issue;
  }

  private ValidationIssue buildGenericParsingError(HttpMessageNotReadableException ex) {
    String technicalMessage =
        ex.getMessage() != null ? ex.getMessage() : "Unable to parse request body";

    log.debug("Generic parsing error: {}", technicalMessage);

    return ClaimValidationError.INVALID_JSON_SYNTAX.toValidationIssueWithTechnicalMessage(
        technicalMessage);
  }

  private String extractFieldPath(String message) {
    if (message == null) {
      return UNKNOWN;
    }

    Matcher matcher = FIELD_PATH_PATTERN.matcher(message);
    StringBuilder path = new StringBuilder();

    while (matcher.find()) {
      if (!path.isEmpty()) {
        path.append(".");
      }
      path.append(matcher.group(1));
    }

    return !path.isEmpty() ? path.toString() : UNKNOWN;
  }

  private String extractInvalidValue(String message) {
    if (message == null) {
      return UNKNOWN;
    }

    Matcher matcher = UNEXPECTED_VALUE_PATTERN.matcher(message);
    return matcher.find() ? matcher.group(1) : UNKNOWN;
  }

  private ResponseEntity<ValidationResult> buildValidationResponse(ValidationIssue issue) {
    ValidationResult result = new ValidationResult();
    result.setIsValid(false);
    result.setIssues(List.of(issue));
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
}
