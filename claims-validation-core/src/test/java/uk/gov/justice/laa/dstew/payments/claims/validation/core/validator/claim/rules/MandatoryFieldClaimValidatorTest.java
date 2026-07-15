package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.StringCaseUtil;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidatorCode;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

@DisplayName("MandatoryFieldClaimValidator")
class MandatoryFieldClaimValidatorTest {

  private MandatoryFieldClaimValidator validator;

  @BeforeEach
  void setUp() {
    validator = new MandatoryFieldClaimValidator();
  }

  // ─────────────────────────────────────────────────────────────────────────
  // No area of law
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("No area of law")
  class NoAreaOfLaw {

    @Test
    @DisplayName("Returns no issues when claim has no area of law")
    void noIssuesWhenNoAreaOfLaw() {
      Claim claim = Claim.builder().build();
      ClaimValidationContext context = ClaimValidationContext.builder().build();

      validator.validate(claim, context);

      assertThat(context.getIssues()).isEmpty();
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // LEGAL HELP
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("LEGAL HELP mandatory fields")
  class LegalHelp {

    /** A fully-populated LEGAL_HELP claim — all mandatory fields present. */
    private Claim fullLegalHelpClaim() {
      return Claim.builder()
          .areaOfLaw(AreaOfLaw.LEGAL_HELP)
          .uniqueFileNumber("010101/001")
          .caseStartDate("2025-01-01")
          .caseConcludedDate("2025-06-01")
          .outcomeCode("DC")
          .travelWaitingCostsAmount(BigDecimal.ZERO)
          .clientForename("Jane")
          .clientSurname("Doe")
          .clientDateOfBirth("1990-01-01")
          .uniqueClientNumber("010190/F/DOE")
          .clientPostcode("SW1A 1AA")
          .genderCode("F")
          .ethnicityCode("01")
          .disabilityCode("NCD")
          .adviceTime(0)
          .travelTime(0)
          .waitingTime(0)
          .netCounselCostsAmount(BigDecimal.ZERO)
          .caseId("001")
          .caseReferenceNumber("REF001")
          .scheduleReference("SCHED001")
          .matterTypeCode("IPLB")
          .netProfitCostsAmount(BigDecimal.ZERO)
          .isVatApplicable(false)
          .build();
    }

    @Test
    @DisplayName("No issues when all mandatory fields are present")
    void noIssuesWhenAllFieldsPresent() {
      ClaimValidationContext context = ClaimValidationContext.builder().build();
      validator.validate(fullLegalHelpClaim(), context);
      assertThat(context.getIssues()).isEmpty();
    }

    @ParameterizedTest
    @DisplayName("MISSING_MANDATORY_FIELD error when individual field absent")
    @ValueSource(strings = {
        "uniqueFileNumber",
        "caseStartDate",
        "caseConcludedDate",
        "outcomeCode",
        "travelWaitingCostsAmount",
        "clientForename",
        "clientSurname",
        "clientDateOfBirth",
        "uniqueClientNumber",
        "clientPostcode",
        "genderCode",
        "ethnicityCode",
        "disabilityCode",
        "adviceTime",
        "travelTime",
        "waitingTime",
        "netCounselCostsAmount",
        "caseId",
        "caseReferenceNumber",
        "scheduleReference",
        "matterTypeCode",
        "netProfitCostsAmount",
        "isVatApplicable"
    })
    void errorWhenMandatoryFieldMissing(String fieldName) {
      // Build a complete claim then null out just the field under test
      Claim claim = withFieldCleared(fullLegalHelpClaim(), fieldName);
      ClaimValidationContext context = ClaimValidationContext.builder().build();

      validator.validate(claim, context);

      assertThat(context.getIssues())
          .as("Expected a MISSING_MANDATORY_FIELD issue for field: " + fieldName)
          .isNotEmpty();
      assertThat(context.getIssues())
          .extracting(ValidationIssue::getCode)
          .contains("MISSING_MANDATORY_FIELD");
      assertThat(context.getIssues())
          .extracting(ValidationIssue::getSeverity)
          .containsOnly(ValidationSeverity.ERROR);
      // Path must be snake_case version of the field name
      assertThat(context.getIssues())
          .extracting(ValidationIssue::getPath)
          .contains(StringCaseUtil.toSnakeCase(fieldName));
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // CRIME LOWER
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("CRIME LOWER mandatory fields")
  class CrimeLower {

    private Claim fullCrimeLowerClaim() {
      return Claim.builder()
          .areaOfLaw(AreaOfLaw.CRIME_LOWER)
          .caseConcludedDate("2025-06-01")
          .stageReachedCode("PROA")
          .netProfitCostsAmount(BigDecimal.valueOf(100.00))
          .disbursementsVatAmount(BigDecimal.valueOf(20.00))
          .build();
    }

    @Test
    @DisplayName("No issues when all mandatory fields are present")
    void noIssuesWhenAllFieldsPresent() {
      ClaimValidationContext context = ClaimValidationContext.builder().build();
      validator.validate(fullCrimeLowerClaim(), context);
      assertThat(context.getIssues()).isEmpty();
    }

    @ParameterizedTest
    @DisplayName("MISSING_MANDATORY_FIELD error when individual field absent")
    @ValueSource(strings = {
        "caseConcludedDate",
        "stageReachedCode",
        "netProfitCostsAmount",
        "disbursementsVatAmount"
    })
    void errorWhenMandatoryFieldMissing(String fieldName) {
      Claim claim = withFieldCleared(fullCrimeLowerClaim(), fieldName);
      ClaimValidationContext context = ClaimValidationContext.builder().build();

      validator.validate(claim, context);

      assertThat(context.getIssues())
          .as("Expected a MISSING_MANDATORY_FIELD issue for field: " + fieldName)
          .isNotEmpty();
      assertThat(context.getIssues())
          .extracting(ValidationIssue::getCode)
          .contains("MISSING_MANDATORY_FIELD");
      assertThat(context.getIssues())
          .extracting(ValidationIssue::getPath)
          .contains(StringCaseUtil.toSnakeCase(fieldName));
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // MEDIATION
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("MEDIATION mandatory fields")
  class Mediation {

    private Claim fullMediationClaim() {
      return Claim.builder()
          .areaOfLaw(AreaOfLaw.MEDIATION)
          .outreachLocation("XYZ")
          .referralSource("02")
          .clientForename("Jane")
          .clientSurname("Doe")
          .clientDateOfBirth("1990-01-01")
          .uniqueClientNumber("010190/F/DOE")
          .clientPostcode("SW1A 1AA")
          .genderCode("F")
          .ethnicityCode("01")
          .disabilityCode("NCD")
          .isLegallyAided(true)
          .caseId("001")
          .caseStartDate("2025-01-01")
          .caseReferenceNumber("REF001")
          .scheduleReference("SCHED001")
          .matterTypeCode("MMED")
          .uniqueCaseId("CASE001")
          .build();
    }

    @Test
    @DisplayName("No issues when all mandatory fields are present")
    void noIssuesWhenAllFieldsPresent() {
      ClaimValidationContext context = ClaimValidationContext.builder().build();
      validator.validate(fullMediationClaim(), context);
      assertThat(context.getIssues()).isEmpty();
    }

    @ParameterizedTest
    @DisplayName("MISSING_MANDATORY_FIELD error when individual field absent")
    @ValueSource(strings = {
        "outreachLocation",
        "referralSource",
        "clientForename",
        "clientSurname",
        "clientDateOfBirth",
        "uniqueClientNumber",
        "clientPostcode",
        "genderCode",
        "ethnicityCode",
        "disabilityCode",
        "isLegallyAided",
        "caseId",
        "caseStartDate",
        "caseReferenceNumber",
        "scheduleReference",
        "matterTypeCode",
        "uniqueCaseId"
    })
    void errorWhenMandatoryFieldMissing(String fieldName) {
      Claim claim = withFieldCleared(fullMediationClaim(), fieldName);
      ClaimValidationContext context = ClaimValidationContext.builder().build();

      validator.validate(claim, context);

      assertThat(context.getIssues())
          .as("Expected a MISSING_MANDATORY_FIELD issue for field: " + fieldName)
          .isNotEmpty();
      assertThat(context.getIssues())
          .extracting(ValidationIssue::getCode)
          .contains("MISSING_MANDATORY_FIELD");
      assertThat(context.getIssues())
          .extracting(ValidationIssue::getPath)
          .contains(StringCaseUtil.toSnakeCase(fieldName));
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Disbursement-only LEGAL HELP exclusions
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("LEGAL HELP disbursement-only exclusions")
  class DisbursementOnlyExclusions {

    /** Context that marks the claim as disbursement-only. */
    private ClaimValidationContext disbOnlyContext() {
      return ClaimValidationContext.builder()
          .feeCalculationType("DISB_ONLY")
          .build();
    }

    /**
     * A LEGAL_HELP claim that is missing ALL normally-excluded fields but has all other
     * mandatory fields populated.
     */
    private Claim legalHelpClaimWithoutExcludedFields() {
      return Claim.builder()
          .areaOfLaw(AreaOfLaw.LEGAL_HELP)
          .uniqueFileNumber("010101/001")
          .caseStartDate("2025-01-01")
          .caseConcludedDate("2025-06-01")
          .outcomeCode("DC")
          // travelWaitingCostsAmount — excluded, intentionally null
          .clientForename("Jane")
          .clientSurname("Doe")
          .clientDateOfBirth("1990-01-01")
          .uniqueClientNumber("010190/F/DOE")
          .clientPostcode("SW1A 1AA")
          .genderCode("F")
          .ethnicityCode("01")
          .disabilityCode("NCD")
          // adviceTime, travelTime, waitingTime — excluded, intentionally null
          // netCounselCostsAmount — excluded, intentionally null
          .caseId("001")
          .caseReferenceNumber("REF001")
          .scheduleReference("SCHED001")
          .matterTypeCode("IPLB")
          // netProfitCostsAmount — excluded, intentionally null
          // isVatApplicable — excluded, intentionally null
          .build();
    }

    @Test
    @DisplayName("No issues when all excluded fields are absent for DISB_ONLY LEGAL HELP")
    void noIssuesWhenAllExcludedFieldsAbsent() {
      validator.validate(legalHelpClaimWithoutExcludedFields(), disbOnlyContext());
      // If this passes without error the exclusion list is working correctly
      ClaimValidationContext context = disbOnlyContext();
      validator.validate(legalHelpClaimWithoutExcludedFields(), context);
      assertThat(context.getIssues()).isEmpty();
    }

    @ParameterizedTest
    @DisplayName("No issue raised for each excluded field individually when DISB_ONLY")
    @ValueSource(strings = {
        "travelWaitingCostsAmount",
        "adviceTime",
        "travelTime",
        "waitingTime",
        "netCounselCostsAmount",
        "netProfitCostsAmount",
        "isVatApplicable"
    })
    void noIssueForEachExcludedField(String excludedField) {
      ClaimValidationContext context = disbOnlyContext();
      validator.validate(legalHelpClaimWithoutExcludedFields(), context);

      // None of the issues raised (if any) should be for the excluded field
      assertThat(context.getIssues())
          .extracting(ValidationIssue::getPath)
          .as("Excluded field %s should not appear in issues for DISB_ONLY", excludedField)
          .doesNotContain(StringCaseUtil.toSnakeCase(excludedField));
    }

    @ParameterizedTest
    @DisplayName("Excluded fields ARE still required for non-disbursement LEGAL HELP")
    @ValueSource(strings = {
        "travelWaitingCostsAmount",
        "adviceTime",
        "travelTime",
        "waitingTime",
        "netCounselCostsAmount",
        "netProfitCostsAmount",
        "isVatApplicable"
    })
    void excludedFieldsStillRequiredForStandardLegalHelp(String excludedField) {
      // No feeCalculationType in context → not a DISB_ONLY claim
      ClaimValidationContext context = ClaimValidationContext.builder().build();
      validator.validate(legalHelpClaimWithoutExcludedFields(), context);

      assertThat(context.getIssues())
          .extracting(ValidationIssue::getPath)
          .as("Field %s should be required for standard LEGAL HELP", excludedField)
          .contains(StringCaseUtil.toSnakeCase(excludedField));
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Validator metadata
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Validator metadata")
  class Metadata {

    @Test
    @DisplayName("Validator code is CLAIM_MANDATORY_FIELD")
    void validatorCode() {
      assertThat(validator.getValidatorCode()).isEqualTo(ClaimValidatorCode.CLAIM_MANDATORY_FIELD_VALIDATOR);
    }

    @Test
    @DisplayName("Priority is 10")
    void priority() {
      assertThat(validator.priority()).isEqualTo(10);
    }

    @Test
    @DisplayName("Applies to any scope")
    void appliesToAnyScope() {
      assertThat(validator.appliesTo(Set.of(ClaimValidatorCode.CLAIM_MANDATORY_FIELD_VALIDATOR))).isTrue();
      assertThat(validator.appliesTo(null)).isTrue();
      assertThat(validator.appliesTo(new HashSet<>())).isTrue();
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Helper
  // ─────────────────────────────────────────────────────────────────────────

  /**
   * Returns a copy of the claim with the named field set to null (or 0 for primitives).
   * Uses the Lombok toBuilder pattern so only the target field is wiped.
   */
  private static Claim withFieldCleared(Claim claim, String fieldName) {
    Claim.ClaimBuilder b = claim.toBuilder();
    return switch (fieldName) {
      // Strings
      case "uniqueFileNumber"      -> b.uniqueFileNumber(null).build();
      case "caseStartDate"         -> b.caseStartDate(null).build();
      case "caseConcludedDate"     -> b.caseConcludedDate(null).build();
      case "outcomeCode"           -> b.outcomeCode(null).build();
      case "clientForename"        -> b.clientForename(null).build();
      case "clientSurname"         -> b.clientSurname(null).build();
      case "clientDateOfBirth"     -> b.clientDateOfBirth(null).build();
      case "uniqueClientNumber"    -> b.uniqueClientNumber(null).build();
      case "clientPostcode"        -> b.clientPostcode(null).build();
      case "genderCode"            -> b.genderCode(null).build();
      case "ethnicityCode"         -> b.ethnicityCode(null).build();
      case "disabilityCode"        -> b.disabilityCode(null).build();
      case "caseId"                -> b.caseId(null).build();
      case "caseReferenceNumber"   -> b.caseReferenceNumber(null).build();
      case "scheduleReference"     -> b.scheduleReference(null).build();
      case "matterTypeCode"        -> b.matterTypeCode(null).build();
      case "stageReachedCode"      -> b.stageReachedCode(null).build();
      case "outreachLocation"      -> b.outreachLocation(null).build();
      case "referralSource"        -> b.referralSource(null).build();
      case "uniqueCaseId"          -> b.uniqueCaseId(null).build();
      // BigDecimals
      case "travelWaitingCostsAmount"  -> b.travelWaitingCostsAmount(null).build();
      case "netCounselCostsAmount"     -> b.netCounselCostsAmount(null).build();
      case "netProfitCostsAmount"      -> b.netProfitCostsAmount(null).build();
      case "disbursementsVatAmount"    -> b.disbursementsVatAmount(null).build();
      // Integers
      case "adviceTime"    -> b.adviceTime(null).build();
      case "travelTime"    -> b.travelTime(null).build();
      case "waitingTime"   -> b.waitingTime(null).build();
      // Booleans
      case "isVatApplicable"  -> b.isVatApplicable(null).build();
      case "isLegallyAided"   -> b.isLegallyAided(null).build();
      default -> throw new IllegalArgumentException("Unknown field: " + fieldName);
    };
  }
}
