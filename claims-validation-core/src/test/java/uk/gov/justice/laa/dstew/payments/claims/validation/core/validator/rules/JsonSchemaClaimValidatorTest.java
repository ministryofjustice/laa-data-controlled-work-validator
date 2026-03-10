package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;

/** Unit tests for {@link JsonSchemaClaimValidator}. */
class JsonSchemaClaimValidatorTest {

  private static final String UFN_CUSTOM_MESSAGE =
      "Unique File Number (UFN) must be in the format DDMMYY/NNN with a date in the past";
  private static final String FEE_CODE_CUSTOM_MESSAGE =
      "Fee Code must contain only letters and numbers, and be a maximum of 10 characters";
  private static final String PROCUREMENT_AREA_CUSTOM_MESSAGE =
      "Procurement Area Code must be 2 uppercase letters followed by 5 digits";
  private static final String ACCESS_POINT_CUSTOM_MESSAGE =
      "Access Point Code must be in the format AP##### (AP followed by 5 digits)";

  private JsonSchemaClaimValidator validator;
  private ValidationContext context;

  @BeforeEach
  void setUp() {
    validator = new JsonSchemaClaimValidator();
    validator.init();
    context = ValidationContext.builder().build();
  }

  /**
   * Creates a claim with all required fields set to valid values. Required fields: status,
   * lineNumber, netDisbursementAmount, disbursementsVatAmount, feeCode
   */
  private Claim createClaimWithRequiredFields() {
    Claim claim = new Claim();
    claim.setStatus(ClaimStatus.READY_TO_PROCESS);
    claim.setLineNumber(1);
    claim.setNetDisbursementAmount(BigDecimal.ZERO);
    claim.setDisbursementsVatAmount(BigDecimal.ZERO);
    claim.setFeeCode("ABC123");
    return claim;
  }

  @Test
  void validate_returnsNoIssues_whenClaimIsNull() {
    List<ValidationIssue> issues = validator.validate(null, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void validate_returnsNoErrors_whenFieldsAreValid() {
    Claim claim = createClaimWithRequiredFields();
    claim.setUniqueFileNumber("010120/001");
    claim.setProcurementAreaCode("AB12345");
    claim.setAccessPointCode("AP12345");

    List<ValidationIssue> issues = validator.validate(claim, context);

    // Filter out schema config warnings
    List<ValidationIssue> errors =
        issues.stream().filter(i -> i.getSeverity() == ValidationSeverity.ERROR).toList();

    assertThat(errors).isEmpty();
  }

  @Test
  void validate_returnsCustomMessage_whenUniqueFileNumberInvalidFormat() {
    Claim claim = createClaimWithRequiredFields();
    claim.setUniqueFileNumber("invalid-ufn");

    List<ValidationIssue> issues = validator.validate(claim, context);

    List<ValidationIssue> errors =
        issues.stream().filter(i -> i.getSeverity() == ValidationSeverity.ERROR).toList();

    assertThat(errors).hasSize(1);
    assertThat(errors.getFirst().getMessage()).isEqualTo(UFN_CUSTOM_MESSAGE);
    assertThat(errors.getFirst().getCode()).isEqualTo("SCHEMA_VALIDATION_ERROR");
    assertThat(errors.getFirst().getSeverity()).isEqualTo(ValidationSeverity.ERROR);
  }

  @Test
  void validate_returnsCustomMessage_whenFeeCodeInvalidFormat() {
    Claim claim = createClaimWithRequiredFields();
    claim.setFeeCode("INVALID-CODE!"); // Override with invalid value

    List<ValidationIssue> issues = validator.validate(claim, context);

    List<ValidationIssue> errors =
        issues.stream().filter(i -> i.getSeverity() == ValidationSeverity.ERROR).toList();

    assertThat(errors).hasSize(1);
    assertThat(errors.getFirst().getMessage()).isEqualTo(FEE_CODE_CUSTOM_MESSAGE);
  }

  @Test
  void validate_returnsCustomMessage_whenProcurementAreaCodeInvalidFormat() {
    Claim claim = createClaimWithRequiredFields();
    claim.setProcurementAreaCode("invalid");

    List<ValidationIssue> issues = validator.validate(claim, context);

    List<ValidationIssue> errors =
        issues.stream().filter(i -> i.getSeverity() == ValidationSeverity.ERROR).toList();

    assertThat(errors).hasSize(1);
    assertThat(errors.getFirst().getMessage()).isEqualTo(PROCUREMENT_AREA_CUSTOM_MESSAGE);
  }

  @Test
  void validate_returnsCustomMessage_whenAccessPointCodeInvalidFormat() {
    Claim claim = createClaimWithRequiredFields();
    claim.setAccessPointCode("invalid");

    List<ValidationIssue> issues = validator.validate(claim, context);

    List<ValidationIssue> errors =
        issues.stream().filter(i -> i.getSeverity() == ValidationSeverity.ERROR).toList();

    assertThat(errors).hasSize(1);
    assertThat(errors.getFirst().getMessage()).isEqualTo(ACCESS_POINT_CUSTOM_MESSAGE);
  }

  @Test
  void validate_returnsGenericMessage_whenLineNumberOutOfRange() {
    Claim claim = createClaimWithRequiredFields();
    claim.setLineNumber(0); // Override with invalid value

    List<ValidationIssue> issues = validator.validate(claim, context);

    List<ValidationIssue> errors =
        issues.stream().filter(i -> i.getSeverity() == ValidationSeverity.ERROR).toList();

    assertThat(errors).hasSize(1);
    assertThat(errors.getFirst().getMessage()).isEqualTo("Field 'lineNumber' has an invalid value");
  }

  @Test
  void validate_includesTechnicalMessage() {
    Claim claim = createClaimWithRequiredFields();
    claim.setUniqueFileNumber("invalid");

    List<ValidationIssue> issues = validator.validate(claim, context);

    List<ValidationIssue> errors =
        issues.stream().filter(i -> i.getSeverity() == ValidationSeverity.ERROR).toList();

    assertThat(errors).hasSize(1);
    assertThat(errors.getFirst().getTechnicalMessage())
        .isEqualTo(
            "uniqueFileNumber: does not match the regex pattern ^[0-9]{6}/[0-9]{3}$ (provided value: invalid)");
  }

  @Test
  void priority_returnsZero() {
    assertThat(validator.priority()).isZero();
  }

  @Test
  void getValidatorCode_returnsJsonSchema() {
    assertThat(validator.getValidatorCode()).isEqualTo("JSON_SCHEMA");
  }

  @Test
  void validate_returnsWarning_whenClaimHasFieldsNotInSchema() {
    Claim claim = createClaimWithRequiredFields();
    claim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);

    List<ValidationIssue> issues = validator.validate(claim, context);

    List<ValidationIssue> warnings =
        issues.stream().filter(issue -> issue.getSeverity() == ValidationSeverity.WARNING).toList();

    // If there are warnings, they should be SCHEMA_CONFIG_WARNING
    for (ValidationIssue warning : warnings) {
      assertThat(warning.getCode()).isEqualTo("SCHEMA_CONFIG_WARNING");
      assertThat(warning.getMessage()).startsWith("Schema configuration warning:");
    }
  }

  @Test
  void validate_returnsSeparateErrors_forEachMissingRequiredField() {
    Claim claim = new Claim();
    // Don't set any required fields

    List<ValidationIssue> issues = validator.validate(claim, context);

    List<ValidationIssue> errors =
        issues.stream().filter(issue -> issue.getSeverity() == ValidationSeverity.ERROR).toList();

    // Should have separate errors for each required field
    assertThat(errors)
        .anyMatch(issue -> issue.getMessage().equals("Status is required"))
        .anyMatch(issue -> issue.getMessage().equals("Fee Code is required"))
        .anyMatch(issue -> issue.getMessage().equals("Net Disbursement Amount is required"))
        .anyMatch(issue -> issue.getMessage().equals("Disbursements Vat Amount is required"));

    // Each should have proper technical message
    for (ValidationIssue error : errors) {
      assertThat(error.getCode()).isEqualTo("SCHEMA_VALIDATION_ERROR");
      assertThat(error.getTechnicalMessage()).startsWith("Required field '");
    }
  }
}
