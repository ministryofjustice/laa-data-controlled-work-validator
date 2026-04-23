package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

/**
 * Validator that validates claims against a JSON schema.
 *
 * <p>This validator runs first (priority 0) and checks that the claim data conforms to the expected
 * schema defined in {@code claim-fields.schema.json}. The schema defines:
 *
 * <ul>
 *   <li>Field types (string, integer, boolean, etc.)
 *   <li>Pattern constraints (regex patterns for field values)
 *   <li>Min/max constraints for numbers
 *   <li>Length constraints for strings
 *   <li>Custom validation error messages per field and area of law (via validationErrorMessages)
 * </ul>
 *
 * <p>Schema validation errors are returned with the code {@code SCHEMA_VALIDATION_ERROR}.
 */
@Component
@Slf4j
public class ClaimSchemaValidator implements ClaimValidator {

  private static final String SCHEMA_PATH = "/schemas/claim-fields.schema.json";
  private static final String REQUIRED_TYPE = "required";
  private static final String VALIDATION_ERROR_MESSAGES_KEY = "validationErrorMessages";
  private static final String KEY_FIELD = "key";
  private static final String VALUE_FIELD = "value";
  private static final String ALL_KEY = "ALL";
  private static final String UNKNOWN = "unknown";

  private final ObjectMapper objectMapper;
  private JsonSchema schema;
  private JsonNode schemaNode;

  /** Constructor - creates its own ObjectMapper for JSON serialization. */
  public ClaimSchemaValidator() {
    this.objectMapper = new ObjectMapper();
    this.objectMapper.findAndRegisterModules();

    // Exclude null fields from serialization so the schema only validates fields with actual
    // values.
    // This prevents pattern/format validation errors for optional fields that are null.
    // Required fields will still fail validation if missing MandatoryFieldClaimValidator.
    this.objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);

    // Use snake_case to match the JSON schema property names (e.g. feeCode -> fee_code).
    this.objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
  }

  /** Initializes the JSON schema from the classpath resource. */
  @PostConstruct
  public void init() {
    try (InputStream schemaStream = getClass().getResourceAsStream(SCHEMA_PATH)) {
      if (schemaStream == null) {
        throw new IllegalStateException("Schema file not found: " + SCHEMA_PATH);
      }

      // Parse schema as JsonNode to access custom validationErrorMessages
      schemaNode = objectMapper.readTree(schemaStream);

      // Note: The warning about unknown keyword 'validationErrorMessages' is expected.
      // It's a custom extension we use for custom error messages, not a validation keyword.
      JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012);
      schema = factory.getSchema(schemaNode);

      log.info("Loaded JSON schema from {}", SCHEMA_PATH);
    } catch (Exception e) {
      log.error("Failed to load JSON schema from {}", SCHEMA_PATH, e);
      throw new IllegalStateException("Failed to load JSON schema", e);
    }
  }

  @Override
  public List<ValidationIssue> validate(Claim claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    if (claim == null) {
      log.debug("Skipping schema validation - claim is null");
      return issues;
    }

    log.debug("Running JSON schema validation");

    // Convert claim to JSON
    JsonNode claimJson = objectMapper.valueToTree(claim);

    // Validate against schema
    Set<ValidationMessage> validationMessages = schema.validate(claimJson);

    if (validationMessages.isEmpty()) {
      log.debug("Schema validation passed");
      return issues;
    }

    log.info("Schema validation found {} issues", validationMessages.size());

    // Separate different types of validation errors
    List<ValidationMessage> additionalPropertiesErrors = new ArrayList<>();
    List<ValidationMessage> requiredFieldErrors = new ArrayList<>();
    List<ValidationMessage> validationErrors = new ArrayList<>();

    for (ValidationMessage vm : validationMessages) {
      if (isAdditionalPropertiesError(vm)) {
        additionalPropertiesErrors.add(vm);
      } else if (isRequiredFieldError(vm)) {
        requiredFieldErrors.add(vm);
      } else {
        validationErrors.add(vm);
      }
    }

    // Log additional properties as config warnings - schema needs updating
    if (!additionalPropertiesErrors.isEmpty()) {
      issues.add(buildAdditionalPropertiesWarning(additionalPropertiesErrors));
    }

    // Process required field errors - create one issue per missing field
    for (ValidationMessage vm : requiredFieldErrors) {
      String fieldName = extractRequiredFieldName(vm);
      String displayMessage = String.format("%s is required", toTitleCase(fieldName));
      String technicalMessage = String.format("Required field '%s' is missing", fieldName);

      ValidationIssue issue =
          ClaimValidationError.SCHEMA_VALIDATION_ERROR.toValidationIssueWithTechnicalMessage(
              technicalMessage, displayMessage);
      issues.add(issue);
    }

    // Process other validation errors
    if (!validationErrors.isEmpty()) {
      Map<String, String> fieldToTechnicalMessage =
          groupTechnicalMessagesByField(new java.util.HashSet<>(validationErrors), claimJson);

      for (ValidationMessage vm : validationErrors) {
        String fieldName = extractFieldName(vm);
        String technicalMessage = fieldToTechnicalMessage.get(fieldName);
        String displayMessage = buildDisplayMessage(fieldName, claim.getAreaOfLaw());

        ValidationIssue issue =
            ClaimValidationError.SCHEMA_VALIDATION_ERROR.toValidationIssueWithTechnicalMessage(
                technicalMessage, displayMessage);

        issues.add(issue);
      }
    }

    // Remove duplicate issues for same field
    return issues.stream().distinct().toList();
  }

  @Override
  public int priority() {
    return 1; // Run first - schema validation should happen before all other validators
  }

  @Override
  public String getValidatorCode() {
    return "CLAIM_SCHEMA";
  }

  /**
   * Builds a warning issue for fields in Claim class not defined in schema.
   *
   * @param additionalPropertiesErrors the additional properties error messages
   * @return the warning issue
   */
  private ValidationIssue buildAdditionalPropertiesWarning(
      List<ValidationMessage> additionalPropertiesErrors) {
    List<String> missingFields =
        additionalPropertiesErrors.stream()
            .map(this::extractPropertyNameFromAdditionalPropertiesError)
            .distinct()
            .toList();

    log.warn(
        "Schema is missing {} field(s) that exist in Claim class: {}. "
            + "Update claim-fields.schema.json to include these fields.",
        missingFields.size(),
        missingFields);

    String warningMessage =
        String.format(
            "Schema configuration warning: %d field(s) not defined in schema: %s",
            missingFields.size(), String.join(", ", missingFields));

    String technicalMessage =
        "These fields exist in the Claim class but are not defined in the JSON schema. "
            + "Update claim-fields.schema.json to add validation rules for these fields.";

    return ClaimValidationError.SCHEMA_CONFIG_WARNING.toValidationIssueWithTechnicalMessage(
        technicalMessage, warningMessage);
  }

  /**
   * Checks if a validation message is a "required field" error.
   *
   * @param vm the validation message
   * @return true if this is a required field error
   */
  private boolean isRequiredFieldError(ValidationMessage vm) {
    return REQUIRED_TYPE.equals(vm.getType());
  }

  /**
   * Extracts the required field name from a required field error message.
   *
   * @param vm the validation message
   * @return the field name
   */
  private String extractRequiredFieldName(ValidationMessage vm) {
    // The property field contains the missing required field name
    String property = vm.getProperty();
    if (property != null && !property.isEmpty()) {
      return property;
    }

    // Fallback: parse from message - format: "$: required property 'fieldName' not found"
    String message = vm.getMessage();
    int startQuote = message.indexOf("'");
    int endQuote = message.indexOf("'", startQuote + 1);
    if (startQuote >= 0 && endQuote > startQuote) {
      return message.substring(startQuote + 1, endQuote);
    }

    return UNKNOWN;
  }

  /**
   * Checks if a validation message is an "additional properties" error.
   *
   * <p>These occur when the Claim class has fields not defined in the schema, indicating the schema
   * needs to be updated.
   *
   * @param vm the validation message
   * @return true if this is an additional properties error
   */
  private boolean isAdditionalPropertiesError(ValidationMessage vm) {
    String message = vm.getMessage();
    return message != null && message.contains("is not defined in the schema");
  }

  /**
   * Extracts the property name from an additional properties error message.
   *
   * @param vm the validation message
   * @return the property name
   */
  private String extractPropertyNameFromAdditionalPropertiesError(ValidationMessage vm) {
    String message = vm.getMessage();
    // Message format: "$: property 'fieldName' is not defined in the schema..."
    int startQuote = message.indexOf("'");
    int endQuote = message.indexOf("'", startQuote + 1);
    if (startQuote >= 0 && endQuote > startQuote) {
      return message.substring(startQuote + 1, endQuote);
    }
    return UNKNOWN;
  }

  /**
   * Extracts the field name from a validation message.
   *
   * @param vm the validation message
   * @return the field name
   */
  private String extractFieldName(ValidationMessage vm) {
    String message = vm.getMessage();
    // Message format is usually "$.fieldName: error details"
    String field = message.split(":")[0].replaceFirst("^\\$\\.", "");
    return field.isEmpty() ? UNKNOWN : field;
  }

  /**
   * Builds a display message for the validation error.
   *
   * <p>First checks for custom validationErrorMessages in the schema for the field and area of law.
   * Falls back to a generic message if no custom message is found.
   *
   * @param fieldName the field name
   * @param areaOfLaw the area of law from the claim
   * @return the display message
   */
  private String buildDisplayMessage(String fieldName, AreaOfLaw areaOfLaw) {

    // Try to get custom message from schema
    String customMessage = getCustomValidationMessage(fieldName, areaOfLaw);
    if (customMessage != null) {
      return customMessage;
    }

    // Default display message based on field name
    return String.format("Field '%s' has an invalid value", fieldName);
  }

  /**
   * Gets a custom validation message from the schema for the given field and area of law.
   *
   * @param fieldName the field name
   * @param areaOfLaw the area of law
   * @return the custom message, or null if not found
   */
  private String getCustomValidationMessage(String fieldName, AreaOfLaw areaOfLaw) {
    JsonNode propertiesNode = schemaNode.get("properties");
    if (propertiesNode == null) {
      return null;
    }

    JsonNode fieldNode = propertiesNode.get(fieldName);
    if (fieldNode == null) {
      return null;
    }

    JsonNode messagesNode = fieldNode.get(VALIDATION_ERROR_MESSAGES_KEY);
    if (messagesNode == null || !messagesNode.isArray()) {
      return null;
    }

    String areaOfLawKey = areaOfLaw != null ? areaOfLaw.getValue() : null;
    String fallbackMessage = null;

    for (JsonNode messageEntry : messagesNode) {
      String key = getTextValue(messageEntry, KEY_FIELD);
      String value = getTextValue(messageEntry, VALUE_FIELD);

      if (key == null || value == null) {
        continue;
      }

      if (key.equals(areaOfLawKey)) {
        return value;
      }

      if (ALL_KEY.equals(key)) {
        fallbackMessage = value;
      }
    }

    return fallbackMessage;
  }

  /**
   * Safely gets a text value from a JSON node.
   *
   * @param node the parent node
   * @param fieldName the field name
   * @return the text value, or null if not found
   */
  private String getTextValue(JsonNode node, String fieldName) {
    return node.has(fieldName) ? node.get(fieldName).asText() : null;
  }

  /**
   * Builds a technical message with full details.
   *
   * @param vm the validation message
   * @param claimJson the claim as JSON
   * @return the technical message
   */
  private String buildTechnicalMessage(ValidationMessage vm, JsonNode claimJson) {
    String message = vm.getMessage();
    String fieldName = extractFieldName(vm);

    // Get the actual value that failed validation
    JsonNode valueNode = claimJson.get(fieldName);
    String value = (valueNode == null || valueNode.isNull()) ? "null" : valueNode.asText();

    // Extract the error description from the message
    String errorDescription =
        message.contains(":") ? message.substring(message.indexOf(':') + 1).trim() : message;

    return String.format("%s: %s (provided value: %s)", fieldName, errorDescription, value);
  }

  /**
   * Groups technical messages by field name, combining multiple errors for the same field.
   *
   * @param validationMessages the validation messages
   * @param claimJson the claim as JSON
   * @return map of field name to combined technical message
   */
  private Map<String, String> groupTechnicalMessagesByField(
      Set<ValidationMessage> validationMessages, JsonNode claimJson) {

    Map<String, String> fieldToTechnicalMessage = new HashMap<>();

    for (ValidationMessage vm : validationMessages) {
      String fieldName = extractFieldName(vm);
      String technicalMessage = buildTechnicalMessage(vm, claimJson);

      fieldToTechnicalMessage.merge(
          fieldName, technicalMessage, (existing, newMsg) -> existing + " : " + newMsg);
    }

    return fieldToTechnicalMessage;
  }

  /**
   * Converts a string to title case.
   *
   * @param input the input string
   * @return the title case string
   */
  private String toTitleCase(String input) {
    if (input == null || input.isEmpty()) {
      return input;
    }

    // Handle camelCase - insert space before capitals
    String spaced = input.replaceAll("([a-z])([A-Z])", "$1 $2");
    spaced = spaced.replace("_", " "); // Also replace underscores with spaces

    String[] words = spaced.split("\\s+");
    StringBuilder result = new StringBuilder();

    for (String word : words) {
      if (!word.isEmpty()) {
        if (!result.isEmpty()) {
          result.append(" ");
        }
        result.append(Character.toUpperCase(word.charAt(0)));
        if (word.length() > 1) {
          result.append(word.substring(1).toLowerCase());
        }
      }
    }

    return result.toString();
  }
}
