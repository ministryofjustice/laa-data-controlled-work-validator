package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidatorCode;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;

/** Unit tests for {@link ClaimSchemaValidator}. */
@DisplayName("ClaimSchemaValidator")
class ClaimSchemaValidatorTest {

  private static final String CLIENT_2_DOB_CUSTOM_MESSAGE =
      "Client 2 Date of Birth must be a valid date in the format DD/MM/YYYY";
  private static final String UFN_CUSTOM_MESSAGE =
      "Unique File Number (UFN) must be in the format DDMMYY/NNN with a date in the past";
  private static final String FEE_CODE_CUSTOM_MESSAGE =
      "Fee Code must contain only letters and numbers, and be a maximum of 10 characters";
  private static final String PROCUREMENT_AREA_CUSTOM_MESSAGE =
      "Procurement Area Code must be 2 uppercase letters followed by 5 digits";
  private static final String ACCESS_POINT_CUSTOM_MESSAGE =
      "Access Point Code must be in the format AP##### (AP followed by 5 digits)";
  private static final String SURGERY_MATTERS_COUNT_MESSAGE =
      "Surgery Matters Count must be between 0 and 99";
  private static final String SURGERY_CLIENTS_COUNT_MESSAGE =
      "Surgery Clients Count must be between 1 and 20";

  private ClaimSchemaValidator validator;
  private ClaimValidationContext context;

  @BeforeEach
  void setUp() {
    validator = new ClaimSchemaValidator();
    validator.init();
    context = ClaimValidationContext.builder().build();
  }

  /**
   * Creates a claim with all required fields set to valid values. Required fields: status,
   * lineNumber, netDisbursementAmount, disbursementsVatAmount, feeCode
   */
  private Claim createClaimWithRequiredFields() {
    return Claim.builder()
        .status(ClaimStatus.READY_TO_PROCESS)
        .lineNumber(1)
        .netDisbursementAmount(BigDecimal.ZERO)
        .disbursementsVatAmount(BigDecimal.ZERO)
        .feeCode("ABC123")
        .build();
  }

  private List<ValidationIssue> errorIssues() {
    return context.getIssues().stream()
        .filter(issue -> issue.getSeverity() == ValidationSeverity.ERROR)
        .toList();
  }

  private List<ValidationIssue> warningIssues() {
    return context.getIssues().stream()
        .filter(issue -> issue.getSeverity() == ValidationSeverity.WARNING)
        .toList();
  }

  @Nested
  @DisplayName("Basic validation behaviour")
  class BasicValidation {

    @Test
    @DisplayName("returns no issues when claim is null")
    void validate_returnsNoIssues_whenClaimIsNull() {
      validator.validate(null, context);

      assertThat(context.getIssues()).isEmpty();
    }

    @Test
    @DisplayName("returns no errors when required fields are valid")
    void validate_returnsNoErrors_whenFieldsAreValid() {
      Claim claim = createClaimWithRequiredFields();
      claim.setUniqueFileNumber("010120/001");
      claim.setProcurementAreaCode("AB12345");
      claim.setAccessPointCode("AP12345");

      validator.validate(claim, context);

      // Filter out schema config warnings
      List<ValidationIssue> errors = errorIssues();

      assertThat(errors).isEmpty();
    }

    @Test
    @DisplayName("priority returns 1")
    void priority_returnsOne() {
      assertThat(validator.priority()).isEqualTo(1);
    }

    @Test
    @DisplayName("validator code is CLAIM_SCHEMA")
    void getValidatorCode_returnsClaimSchema() {
      assertThat(validator.getValidatorCode()).isEqualTo(ClaimValidatorCode.CLAIM_SCHEMA_VALIDATOR);
    }

    @Test
    @DisplayName("appliesTo returns true for schema validator")
    void appliesTo_returnsTrue() {
      assertThat(validator.appliesTo(Set.of(ClaimValidatorCode.CLAIM_SCHEMA_VALIDATOR))).isTrue();
      assertThat(validator.appliesTo(null)).isTrue();
    }
  }

  @Nested
  @DisplayName("Custom format validation messages")
  class CustomFormatValidation {

    @Test
    @DisplayName("returns custom message for invalid Unique File Number format")
    void validate_returnsCustomMessage_whenUniqueFileNumberInvalidFormat() {
      Claim claim = createClaimWithRequiredFields();
      claim.setUniqueFileNumber("invalid-ufn");

      validator.validate(claim, context);

      List<ValidationIssue> errors = errorIssues();

      assertThat(errors).hasSize(1);
      assertThat(errors.getFirst().getMessage()).isEqualTo(UFN_CUSTOM_MESSAGE);
      assertThat(errors.getFirst().getCode()).isEqualTo("SCHEMA_VALIDATION_ERROR");
      assertThat(errors.getFirst().getSeverity()).isEqualTo(ValidationSeverity.ERROR);
    }

    @Test
    @DisplayName("returns custom message for invalid Fee Code format")
    void validate_returnsCustomMessage_whenFeeCodeInvalidFormat() {
      Claim claim = createClaimWithRequiredFields();
      claim.setFeeCode("INVALID-CODE!"); // Override with invalid value

      validator.validate(claim, context);

      List<ValidationIssue> errors = errorIssues();

      assertThat(errors).hasSize(1);
      assertThat(errors.getFirst().getMessage()).isEqualTo(FEE_CODE_CUSTOM_MESSAGE);
    }

    @Test
    @DisplayName("returns custom message for invalid Procurement Area Code format")
    void validate_returnsCustomMessage_whenProcurementAreaCodeInvalidFormat() {
      Claim claim = createClaimWithRequiredFields();
      claim.setProcurementAreaCode("invalid");

      validator.validate(claim, context);

      List<ValidationIssue> errors = errorIssues();

      assertThat(errors).hasSize(1);
      assertThat(errors.getFirst().getMessage()).isEqualTo(PROCUREMENT_AREA_CUSTOM_MESSAGE);
    }

    @Test
    @DisplayName("returns custom message for invalid client_2_date_of_birth format")
    void validate_returnsCustomMessage_whenClient2DateOfBirthInvalidFormat() {
      Claim claim = createClaimWithRequiredFields();
      claim.setClient2DateOfBirth("not-a-date");

      validator.validate(claim, context);

      List<ValidationIssue> errors = errorIssues();

      assertThat(errors).hasSize(1);
      assertThat(errors.getFirst().getMessage()).isEqualTo(CLIENT_2_DOB_CUSTOM_MESSAGE);
      assertThat(errors.getFirst().getCode()).isEqualTo("SCHEMA_VALIDATION_ERROR");
      assertThat(errors.getFirst().getTechnicalMessage())
          .contains("client_2_date_of_birth")
          .contains("not-a-date");
    }

    @Test
    @DisplayName("returns custom message for invalid Access Point Code format")
    void validate_returnsCustomMessage_whenAccessPointCodeInvalidFormat() {
      Claim claim = createClaimWithRequiredFields();
      claim.setAccessPointCode("invalid");

      validator.validate(claim, context);

      List<ValidationIssue> errors = errorIssues();

      assertThat(errors).hasSize(1);
      assertThat(errors.getFirst().getMessage()).isEqualTo(ACCESS_POINT_CUSTOM_MESSAGE);
    }

    @Test
    @DisplayName("includes technical message for invalid field")
    void validate_includesTechnicalMessage() {
      Claim claim = createClaimWithRequiredFields();
      claim.setUniqueFileNumber("invalid");

      validator.validate(claim, context);

      List<ValidationIssue> errors = errorIssues();

      assertThat(errors).hasSize(1);
      assertThat(errors.getFirst().getTechnicalMessage())
          .isEqualTo(
              "unique_file_number: does not match the regex pattern ^[0-9]{6}/[0-9]{3}$ (provided value: invalid)");
    }
  }

  @Nested
  @DisplayName("Required field validation")
  class RequiredFieldValidation {

    @Test
    @DisplayName("returns generic message when line number out of range")
    void validate_returnsGenericMessage_whenLineNumberOutOfRange() {
      Claim claim = createClaimWithRequiredFields();
      claim.setLineNumber(0); // Override with invalid value

      validator.validate(claim, context);

      List<ValidationIssue> errors = errorIssues();

      assertThat(errors).hasSize(1);
      assertThat(errors.getFirst().getMessage())
          .isEqualTo("Field 'line_number' has an invalid value");
    }

    @Test
    @DisplayName("returns separate errors for each missing required field")
    void validate_returnsSeparateErrors_forEachMissingRequiredField() {
      Claim claim = Claim.builder().build();
      // Don't set any required fields

      validator.validate(claim, context);

      List<ValidationIssue> errors = errorIssues();

      // Should have separate errors for each required field
      assertThat(errors)
          .anyMatch(issue -> issue.getMessage().equals("Status is required"))
          .anyMatch(issue -> issue.getMessage().equals("Fee Code is required"))
          .anyMatch(issue -> issue.getMessage().equals("Net Disbursement Amount is required"))
          .anyMatch(issue -> issue.getMessage().equals("Disbursements Vat Amount is required"));

      // Each should have proper technical message
      for (ValidationIssue error : errors) {
        assertThat(error.getCode()).isEqualTo("SCHEMA_VALIDATION_ERROR");
        assertThat(error.getTechnicalMessage()).startsWith("$: required property '");
      }
    }
  }

  @Nested
  @DisplayName("Schema configuration warnings")
  class SchemaConfigWarnings {

    @Test
    @DisplayName("returns warnings when claim has fields not in schema")
    void validate_returnsWarning_whenClaimHasFieldsNotInSchema() {
      Claim claim = createClaimWithRequiredFields();
      claim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);

      validator.validate(claim, context);

      List<ValidationIssue> warnings = warningIssues();

      // If there are warnings, they should be SCHEMA_CONFIG_WARNING
      for (ValidationIssue warning : warnings) {
        assertThat(warning.getCode()).isEqualTo("SCHEMA_CONFIG_WARNING");
        assertThat(warning.getMessage()).startsWith("Schema configuration warning:");
      }
    }

    @Test
    @DisplayName("buildAdditionalPropertiesWarning produces a SCHEMA_CONFIG_WARNING issue")
    void buildAdditionalPropertiesWarning_producesWarningIssue() {
      ClaimWithExtraSchemaField claim = new ClaimWithExtraSchemaField();
      claim.setStatus(ClaimStatus.READY_TO_PROCESS);
      claim.setLineNumber(1);
      claim.setNetDisbursementAmount(BigDecimal.ZERO);
      claim.setDisbursementsVatAmount(BigDecimal.ZERO);
      claim.setFeeCode("ABC123");

      validator.validate(claim, context);

      List<ValidationIssue> warnings = warningIssues();

      // There should be at least one schema config warning
      assertThat(warnings).isNotEmpty();

      boolean hasConfig = warnings.stream().anyMatch(w -> "SCHEMA_CONFIG_WARNING".equals(w.getCode()));
      assertThat(hasConfig).isTrue();

      // Assert the specific warning issue contains the expected texts
      ValidationIssue config = warnings.stream()
          .filter(w -> "SCHEMA_CONFIG_WARNING".equals(w.getCode()))
          .findFirst()
          .orElseThrow();

      assertThat(config.getMessage()).startsWith("Schema configuration warning:");
      assertThat(config.getTechnicalMessage()).contains("These fields exist on the domain object but are not defined in the JSON schema. Update /schemas/");
    }
  }

  @Nested
  @DisplayName("private helper and display message helpers")
  class HelperTests {

    @Test
    @DisplayName("getCustomValidationMessage returns area-specific and ALL fallback messages")
    void getCustomValidationMessage_areaSpecificAndFallback() {
      // area-specific message exists for schedule_reference for LEGAL_HELP
      String msg = validator.getCustomValidationMessage("schedule_reference", AreaOfLaw.LEGAL_HELP.toString());
      assertThat(msg).isNotNull().contains("Schedule Reference must be a maximum of 20 characters");

      // fallback ALL message for office_account_number
      String fallback = validator.getCustomValidationMessage("office_account_number", null);
      assertThat(fallback).isNotNull().contains("Office Account Number must be exactly 6 alphanumeric characters");

      // field not present returns null
      String none = validator.getCustomValidationMessage("nonexistent_field", AreaOfLaw.LEGAL_HELP.toString());
      assertThat(none).isNull();
    }

    @Test
    @DisplayName("buildDisplayMessage falls back to generic message when no custom message")
    void buildDisplayMessage_fallbacks() {
      String generic = validator.buildDisplayMessage("some_field", null);
      assertThat(generic).isEqualTo("Field 'some_field' has an invalid value");
    }

    @Test
    @DisplayName("toTitleCase handles null, empty, underscores and camelCase")
    void toTitleCaseVariations() {
      assertThat(validator.toTitleCase(null)).isNull();
      assertThat(validator.toTitleCase("")).isEmpty();
      assertThat(validator.toTitleCase("camelCaseField")).isEqualTo("Camel Case Field");
      assertThat(validator.toTitleCase("with_underscore_field")).isEqualTo("With Underscore Field");
    }
  }

  @Nested
  @DisplayName("Surgery count schema validations")
  class SurgeryCountSchemaValidations {

    @ParameterizedTest(name = "surgery_matters_count={1} for {0} -> expectError={2}")
    @MethodSource(
        "uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules"
            + ".ClaimSchemaValidatorTest#surgeryMattersCountCases")
    @DisplayName("surgery_matters_count validation")
    void validate_surgeryMattersCount(
        AreaOfLaw areaOfLaw,
        Integer value,
        boolean expectError,
        String expectedTechnicalFragment) {
      Claim claim = createClaimWithRequiredFields();
      claim.setAreaOfLaw(areaOfLaw);
      claim.setSurgeryMattersCount(value);

      validator.validate(claim, context);

      List<ValidationIssue> errors = errorIssues();

      if (expectError) {
        assertThat(errors).hasSize(1);
        assertThat(errors.getFirst().getMessage())
            .isEqualTo(SURGERY_MATTERS_COUNT_MESSAGE)
            .doesNotContain("1 and 20");
        if (expectedTechnicalFragment != null) {
          assertThat(errors.getFirst().getTechnicalMessage())
              .contains("surgery_matters_count")
              .contains(expectedTechnicalFragment);
        }
      } else {
        assertThat(errors).isEmpty();
      }
    }

    @ParameterizedTest(name = "surgery_clients_count={1} for {0} -> expectError={2}")
    @MethodSource(
        "uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules"
            + ".ClaimSchemaValidatorTest#surgeryClientsCountCases")
    @DisplayName("surgery_clients_count validation")
    void validate_surgeryClientsCount(AreaOfLaw areaOfLaw, Integer value, boolean expectError) {
      Claim claim = createClaimWithRequiredFields();
      claim.setAreaOfLaw(areaOfLaw);
      claim.setSurgeryClientsCount(value);

      validator.validate(claim, context);

      List<ValidationIssue> errors = errorIssues();

      if (expectError) {
        assertThat(errors).hasSize(1);
        assertThat(errors.getFirst().getMessage()).isEqualTo(SURGERY_CLIENTS_COUNT_MESSAGE);
      } else {
        assertThat(errors).isEmpty();
      }
    }
  }

  /**
   * Cases for surgery_matters_count. Valid range is 0..99 inclusive; null is allowed. The ALL
   * fallback message mapping is exercised via the CRIME_LOWER area.
   *
   * <p>Arguments: areaOfLaw, value, expectError, expectedTechnicalFragment
   */
  static Stream<Arguments> surgeryMattersCountCases() {
    return Stream.of(
        Arguments.of(AreaOfLaw.LEGAL_HELP, 0, false, null),
        Arguments.of(AreaOfLaw.LEGAL_HELP, 1, false, null),
        Arguments.of(AreaOfLaw.LEGAL_HELP, 20, false, null),
        Arguments.of(AreaOfLaw.LEGAL_HELP, 21, false, null),
        Arguments.of(AreaOfLaw.LEGAL_HELP, 99, false, null),
        Arguments.of(AreaOfLaw.LEGAL_HELP, null, false, null),
        Arguments.of(AreaOfLaw.LEGAL_HELP, -1, true, "-1"),
        Arguments.of(AreaOfLaw.LEGAL_HELP, 100, true, "100"),
        Arguments.of(AreaOfLaw.CRIME_LOWER, -1, true, null));
  }

  /**
   * Cases for surgery_clients_count. Valid range is 1..20 inclusive.
   *
   * <p>Arguments: areaOfLaw, value, expectError
   */
  static Stream<Arguments> surgeryClientsCountCases() {
    return Stream.of(
        Arguments.of(AreaOfLaw.LEGAL_HELP, 0, true),
        Arguments.of(AreaOfLaw.LEGAL_HELP, 1, false),
        Arguments.of(AreaOfLaw.LEGAL_HELP, 20, false),
        Arguments.of(AreaOfLaw.LEGAL_HELP, 21, true));
  }

  static final class ClaimWithExtraSchemaField extends Claim {
    public Boolean getNonSchemaField() {
      return true;
    }
  }
}
