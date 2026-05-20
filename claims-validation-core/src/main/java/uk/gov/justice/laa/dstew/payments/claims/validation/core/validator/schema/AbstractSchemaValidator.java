package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;

/**
 * Abstract base class for JSON schema validators.
 *
 * <p>Provides the full schema validation pipeline — schema loading, {@link ObjectMapper}
 * lifecycle, message bucketing, custom error-message resolution and deduplication — in a
 * domain-agnostic, reusable form.
 *
 * <p>Subclasses need only supply configuration via the abstract hook methods:
 *
 * <ul>
 *   <li>{@link #getSchemaPath()} — classpath location of the JSON schema file
 *   <li>{@link #configureObjectMapper(ObjectMapper)} — apply naming strategy, inclusion rules etc.
 *   <li>{@link #extractDiscriminator(Object)} — return the per-record discriminator string used
 *       to resolve custom {@code validationErrorMessages} entries in the schema (may return
 *       {@code null} if no discriminator applies)
 *   <li>{@link #priority()} — execution order relative to other validators
 *   <li>{@link #getValidatorCode()} — unique identifier for this validator
 * </ul>
 *
 * <p>Concrete subclasses implement their domain-specific validator interface and delegate its
 * {@code validate} method to {@link #validateSubject(Object)}. Example:
 *
 * <pre>{@code
 * public class ClaimSchemaValidator
 *     extends AbstractSchemaValidator<Claim>
 *     implements ClaimValidator {
 *
 *   @Override
 *   public List<ValidationIssue> validate(Claim claim, ClaimValidationContext ctx) {
 *     return validateSubject(claim);
 *   }
 * }
 * }</pre>
 *
 * <p><strong>Thread safety:</strong> the {@link ObjectMapper} and {@link JsonSchema} are
 * fully configured once during {@link #init()} and are thereafter read-only. Both are
 * thread-safe in this usage pattern; Spring singleton beans are safe for concurrent requests.
 *
 * <p><strong>Schema convention:</strong> all schema files must reside under
 * {@code /schemas/} on the classpath (e.g. {@code /schemas/claim-fields.schema.json}).
 *
 * @param <T> the domain object type this validator validates
 */
@Slf4j
public abstract class AbstractSchemaValidator<T> {

  private static final String REQUIRED_TYPE = "required";
  private static final String VALIDATION_ERROR_MESSAGES_KEY = "validationErrorMessages";
  private static final String KEY_FIELD = "key";
  private static final String VALUE_FIELD = "value";
  private static final String ALL_KEY = "ALL";
  private static final String UNKNOWN = "unknown";

  /** Thread-safe once fully configured in {@link #init()}. */
  private ObjectMapper objectMapper;

  /** Immutable after {@link #init()} completes. */
  private JsonSchema schema;

  /** Used to resolve custom {@code validationErrorMessages} entries from the schema JSON. */
  private JsonNode schemaNode;

  // ─────────────────────────────────────────────────────────────────────────
  // Abstract hook methods — subclasses provide domain-specific configuration
  // ─────────────────────────────────────────────────────────────────────────

  /**
   * Returns the classpath path to the JSON schema file for this validator.
   *
   * <p>By convention all schema files live under {@code /schemas/}.
   * Example: {@code "/schemas/claim-fields.schema.json"}.
   *
   * @return the schema classpath resource path
   */
  protected abstract String getSchemaPath();

  /**
   * Configures the {@link ObjectMapper} used to serialise {@code T} to JSON before schema
   * validation.
   *
   * <p>Called once during {@link #init()}. Apply naming strategy, inclusion rules, and any
   * modules specific to the domain object here. Example for snake_case:
   *
   * <pre>{@code
   * mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
   * mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
   * }</pre>
   *
   * @param mapper the mapper to configure
   */
  protected abstract void configureObjectMapper(ObjectMapper mapper);

  /**
   * Extracts the discriminator value from the subject, used to resolve per-discriminator custom
   * {@code validationErrorMessages} entries defined in the schema.
   *
   * <p>Return {@code null} if no discriminator is applicable; the {@code "ALL"} fallback entry
   * in the schema will then be used.
   *
   * <p>Example — for a {@code Claim} the discriminator is the area-of-law code:
   * <pre>{@code
   * return claim.getAreaOfLaw() != null ? claim.getAreaOfLaw().getValue() : null;
   * }</pre>
   *
   * @param subject the domain object being validated
   * @return the discriminator string, or {@code null}
   */
  protected abstract String extractDiscriminator(T subject);

  /**
   * Returns the execution priority of this validator. Lower values run first.
   *
   * @return priority value
   */
  public abstract int priority();

  /**
   * Returns a unique code that identifies this validator.
   *
   * @return validator code
   */
  public abstract String getValidatorCode();

  /**
   * Returns the JSON Schema specification version to use when building the {@link JsonSchema}.
   *
   * <p>Defaults to {@link SpecVersion.VersionFlag#V202012}. Override when the schema file
   * declares an older dialect — for example {@link SpecVersion.VersionFlag#V7} for a schema that
   * starts with {@code "$schema": "http://json-schema.org/draft-07/schema#"}.
   *
   * @return the spec version flag
   */
  protected SpecVersion.VersionFlag getSpecVersion() {
    return SpecVersion.VersionFlag.V202012;
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Lifecycle
  // ─────────────────────────────────────────────────────────────────────────

  /**
   * Initialises the {@link ObjectMapper} and loads the JSON schema from the classpath.
   *
   * <p>Called by Spring after bean construction. Fails fast if the schema file cannot be found or
   * parsed, preventing a misconfigured validator from silently accepting all input.
   */
  @PostConstruct
  public void init() {
    objectMapper = new ObjectMapper();
    objectMapper.findAndRegisterModules();
    objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
    configureObjectMapper(objectMapper);

    String schemaPath = getSchemaPath();
    try (InputStream schemaStream = getClass().getResourceAsStream(schemaPath)) {
      if (schemaStream == null) {
        throw new IllegalStateException("Schema file not found: " + schemaPath);
      }

      // Parse as a JsonNode first so we can read the custom validationErrorMessages extension.
      // The warning about the unknown keyword is expected — it is a custom extension, not a
      // standard JSON Schema keyword.
      schemaNode = objectMapper.readTree(schemaStream);

      JsonSchemaFactory factory = JsonSchemaFactory.getInstance(getSpecVersion());
      schema = factory.getSchema(schemaNode);

      log.info("Loaded JSON schema from {}", schemaPath);
    } catch (Exception e) {
      log.error("Failed to load JSON schema from {}", schemaPath, e);
      throw new IllegalStateException("Failed to load JSON schema from " + schemaPath, e);
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Validation pipeline — sealed; not overridable
  // ─────────────────────────────────────────────────────────────────────────

  /**
   * Executes the full schema validation pipeline against {@code subject} and returns any
   * {@link ValidationIssue}s found.
   *
   * <p>This method is {@code final} — the pipeline is fixed. Domain-specific behaviour is
   * injected via the abstract hook methods, not by overriding pipeline steps.
   *
   * @param subject the domain object to validate; returns an empty list if {@code null}
   * @return list of validation issues; empty if the subject is valid
   */
  protected final List<ValidationIssue> validateSubject(T subject) {
    List<ValidationIssue> issues = new ArrayList<>();

    if (subject == null) {
      log.debug("Skipping schema validation — subject is null");
      return issues;
    }

    log.debug("Running JSON schema validation [{}]", getValidatorCode());

    JsonNode subjectJson = objectMapper.valueToTree(subject);

    Set<ValidationMessage> validationMessages = schema.validate(subjectJson);

    if (validationMessages.isEmpty()) {
      log.debug("Schema validation passed [{}]", getValidatorCode());
      return issues;
    }

    log.info("Schema validation found {} issue(s) [{}]", validationMessages.size(),
        getValidatorCode());

    // Bucket messages by type
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

    if (!additionalPropertiesErrors.isEmpty()) {
      issues.add(buildAdditionalPropertiesWarning(additionalPropertiesErrors));
    }

    for (ValidationMessage vm : requiredFieldErrors) {
      String fieldName = extractRequiredFieldName(vm);
      String display = String.format("%s is required", toTitleCase(fieldName));
      String technical = String.format(
              "$: required property '%s' not found (provided value: %s)",
              fieldName, getTextValue(subjectJson, fieldName));
      ValidationIssue issue = SchemaValidationError.SCHEMA_VALIDATION_ERROR
              .toValidationIssueWithTechnicalMessage(technical, display);
      issue.setPath(fieldName);
      issues.add(issue);
    }

    if (!validationErrors.isEmpty()) {
      String discriminator = extractDiscriminator(subject);
      Map<String, String> fieldToTechnical =
          groupTechnicalMessagesByField(new HashSet<>(validationErrors), subjectJson);

      for (ValidationMessage vm : validationErrors) {
        String fieldName = extractFieldName(vm);
        String technical = fieldToTechnical.get(fieldName);
        String display = buildDisplayMessage(fieldName, discriminator);
        ValidationIssue issue = SchemaValidationError.SCHEMA_VALIDATION_ERROR
                .toValidationIssueWithTechnicalMessage(technical, display);
        issue.setPath(fieldName);
        issues.add(issue);
      }
    }

    return issues.stream().distinct().toList();
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Protected utilities — available to subclasses but not part of public API
  // ─────────────────────────────────────────────────────────────────────────

  /**
   * Builds a {@link ValidationIssue} warning listing fields present on the domain object but
   * absent from the schema. Signals that the schema file needs updating.
   *
   * @param additionalPropertiesErrors error messages of type "additional properties"
   * @return a warning issue
   */
  protected ValidationIssue buildAdditionalPropertiesWarning(
      List<ValidationMessage> additionalPropertiesErrors) {

    List<String> unknownFields = additionalPropertiesErrors.stream()
        .map(this::extractPropertyNameFromAdditionalPropertiesError)
        .distinct()
        .toList();

    log.warn(
        "[{}] Schema is missing {} field(s) present on the domain object: {}. "
            + "Update {} to include these fields.",
        getValidatorCode(), unknownFields.size(), unknownFields, getSchemaPath());

    String display = String.format(
        "Schema configuration warning: %d field(s) not defined in schema: %s",
        unknownFields.size(), String.join(", ", unknownFields));

    String technical =
        "These fields exist on the domain object but are not defined in the JSON schema. "
            + "Update " + getSchemaPath() + " to add validation rules for these fields.";

    return SchemaValidationError.SCHEMA_CONFIG_WARNING
        .toValidationIssueWithTechnicalMessage(technical, display);
  }

  /**
   * Returns {@code true} if the message represents a missing required field.
   *
   * @param vm the validation message
   * @return true if this is a required field error
   */
  protected boolean isRequiredFieldError(ValidationMessage vm) {
    return REQUIRED_TYPE.equals(vm.getType());
  }

  /**
   * Returns {@code true} if the message represents a field present on the domain object but
   * absent from the schema ({@code additionalProperties} violation).
   *
   * @param vm the validation message
   * @return true if this is an additional properties error
   */
  protected boolean isAdditionalPropertiesError(ValidationMessage vm) {
    String message = vm.getMessage();
    return message != null && message.contains("is not defined in the schema");
  }

  /**
   * Extracts the required field name from a required-field validation message.
   *
   * @param vm the validation message
   * @return the field name, or {@code "unknown"} if extraction fails
   */
  protected String extractRequiredFieldName(ValidationMessage vm) {
    String property = vm.getProperty();
    if (property != null && !property.isEmpty()) {
      return property;
    }
    // Fallback: parse from message — "$: required property 'fieldName' not found"
    String message = vm.getMessage();
    int start = message.indexOf('\'');
    int end = message.indexOf('\'', start + 1);
    return (start >= 0 && end > start) ? message.substring(start + 1, end) : UNKNOWN;
  }

  /**
   * Extracts the property name from an additional-properties validation message.
   *
   * @param vm the validation message
   * @return the property name, or {@code "unknown"} if extraction fails
   */
  protected String extractPropertyNameFromAdditionalPropertiesError(ValidationMessage vm) {
    String message = vm.getMessage();
    // "$: property 'fieldName' is not defined in the schema..."
    int start = message.indexOf('\'');
    int end = message.indexOf('\'', start + 1);
    return (start >= 0 && end > start) ? message.substring(start + 1, end) : UNKNOWN;
  }

  /**
   * Extracts the field name from a general validation message.
   *
   * @param vm the validation message
   * @return the field name, or {@code "unknown"}
   */
  protected String extractFieldName(ValidationMessage vm) {
    String field = vm.getMessage().split(":")[0].replaceFirst("^\\$\\.", "");
    return field.isEmpty() ? UNKNOWN : field;
  }

  /**
   * Builds a human-facing display message for a field validation failure.
   *
   * <p>Checks the schema's custom {@code validationErrorMessages} array first, using the
   * supplied {@code discriminator} to find a matching entry. Falls back to the {@code "ALL"}
   * entry, then to a generic message.
   *
   * @param fieldName     the name of the failing field
   * @param discriminator the discriminator string (e.g. area-of-law code), or {@code null}
   * @return the display message
   */
  public String buildDisplayMessage(String fieldName, String discriminator) {
    String custom = getCustomValidationMessage(fieldName, discriminator);
    return custom != null ? custom : String.format("Field '%s' has an invalid value", fieldName);
  }

  /**
   * Looks up a custom validation message from the schema's {@code validationErrorMessages}
   * extension for the given field and discriminator.
   *
   * <p>Checks for an exact discriminator match first; falls back to the {@code "ALL"} entry if
   * no match is found.
   *
   * @param fieldName     the field name as it appears in the schema properties
   * @param discriminator the discriminator to match, or {@code null}
   * @return the custom message, or {@code null} if none is defined
   */
  public String getCustomValidationMessage(String fieldName, String discriminator) {
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

    String fallback = null;
    for (JsonNode entry : messagesNode) {
      String key = getTextValue(entry, KEY_FIELD);
      String value = getTextValue(entry, VALUE_FIELD);
      if (key == null || value == null) {
        continue;
      }
      if (key.equals(discriminator)) {
        return value;
      }
      if (ALL_KEY.equals(key)) {
        fallback = value;
      }
    }
    return fallback;
  }

  /**
   * Safely reads a text value from a JSON node.
   *
   * @param node      the parent node
   * @param fieldName the field to read
   * @return the text value, or {@code null} if absent
   */
  protected String getTextValue(JsonNode node, String fieldName) {
    return node.has(fieldName) ? node.get(fieldName).asText() : null;
  }

  /**
   * Builds a detailed technical message for a validation failure, including the actual value
   * that was rejected.
   *
   * @param vm          the validation message
   * @param subjectJson the serialised domain object
   * @return the technical message string
   */
  protected String buildTechnicalMessage(ValidationMessage vm, JsonNode subjectJson) {
    String message = vm.getMessage();
    String fieldName = extractFieldName(vm);
    JsonNode valueNode = subjectJson.get(fieldName);
    String value = (valueNode == null || valueNode.isNull()) ? "null" : valueNode.asText();
    String errorDesc =
        message.contains(":") ? message.substring(message.indexOf(':') + 1).trim() : message;
    return String.format("%s: %s (provided value: %s)", fieldName, errorDesc, value);
  }

  /**
   * Groups technical messages by field name, merging multiple errors for the same field.
   *
   * @param validationMessages the set of validation messages to group
   * @param subjectJson        the serialised domain object
   * @return map of field name → combined technical message
   */
  protected Map<String, String> groupTechnicalMessagesByField(
      Set<ValidationMessage> validationMessages, JsonNode subjectJson) {

    Map<String, String> result = new HashMap<>();
    for (ValidationMessage vm : validationMessages) {
      String fieldName = extractFieldName(vm);
      String technical = buildTechnicalMessage(vm, subjectJson);
      result.merge(fieldName, technical, (existing, next) -> existing + " : " + next);
    }
    return result;
  }

  /**
   * Converts a camelCase or snake_case field name to a human-readable Title Case string.
   *
   * @param input the input string
   * @return the title-cased string, or the original value if blank
   */
  public String toTitleCase(String input) {
    if (input == null || input.isEmpty()) {
      return input;
    }
    String spaced = input.replaceAll("([a-z])([A-Z])", "$1 $2").replace("_", " ");
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
