package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.ClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.MandatoryFieldClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.UniqueFileNumberClaimValidator;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

/**
 * Tests for {@link ClaimValidation} and the individual {@link ClaimValidator} implementations it
 * orchestrates.
 *
 * <p>Pipeline-level behaviour — scope filtering, priority ordering, issue deduplication, null
 * claim handling, and validation context construction — is covered by
 * {@link ClaimValidationPipeline}. Individual validator behaviour is covered by
 * {@link MandatoryFieldValidation} and {@link UniqueFileNumberValidation}.
 */
@DisplayName("ClaimValidation")
class ClaimValidationTest {

  // ─────────────────────────────────────────────────────────────────────────
  // Pipeline tests
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Pipeline — scope filtering, priority ordering, and deduplication")
  class ClaimValidationPipeline {

    @Test
    @DisplayName("Runs validators in priority order and deduplicates identical context.getIssues()")
    void runValidatorsInPriorityOrderAndDeduplicateIssues() {
      List<String> callOrder = new ArrayList<>();

      ValidationIssue sharedIssue = ValidationIssue.builder()
          .code("DUPLICATE_CODE")
          .message("duplicate")
          .severity(ValidationSeverity.WARNING)
          .technicalMessage(null)
          .build();

      ClaimValidator lowPriority = new ClaimValidator() {
        @Override
        public void validate(Claim claim, ClaimValidationContext context) {
          callOrder.add("low");
        }

        @Override
        public int priority() { return 20; }

        @Override
        public boolean appliesTo(String scope) { return true; }

        @Override
        public String getValidatorCode() { return "LOW"; }
      };

      ClaimValidator highPriority = new ClaimValidator() {
        @Override
        public void validate(Claim claim, ClaimValidationContext context) {
          callOrder.add("high");
          context.addValidationIssue(sharedIssue);
        }

        @Override
        public int priority() { return 10; }

        @Override
        public boolean appliesTo(String scope) { return true; }

        @Override
        public String getValidatorCode() { return "HIGH"; }
      };

      ClaimValidation pipeline = new ClaimValidation(List.of(lowPriority, highPriority));
      pipeline.validateClaim(Claim.builder().build(), "fee", List.of());

      assertThat(callOrder).containsExactly("high", "low");
    }

    @Test
    @DisplayName("Excludes validators whose appliesTo returns false for the given scope")
    void excludesValidatorsNotApplicableToScope() {
      List<String> called = new ArrayList<>();

      ClaimValidator excluded = new ClaimValidator() {
        @Override
        public void validate(Claim claim, ClaimValidationContext context) {
          called.add("excluded");
        }

        @Override
        public int priority() {
          return 0;
        }

        @Override
        public boolean appliesTo(String scope) { return false; }

        @Override
        public String getValidatorCode() { return "EXCLUDED"; }
      };

      ClaimValidation pipeline = new ClaimValidation(List.of(excluded));
      pipeline.validateClaim(Claim.builder().build(), "fee", List.of());

      assertThat(called).isEmpty();
    }

    @Test
    @DisplayName("Deduplicates identical context.getIssues() across validators, preserving insertion order")
    void deduplicatesIdenticalIssuesPreservingOrder() {
      ValidationIssue sharedIssue = ValidationIssue.builder()
          .code("DUPLICATE_CODE").message("duplicate").severity(ValidationSeverity.WARNING)
          .technicalMessage(null).build();

      ClaimValidator v1 = new ClaimValidator() {
        @Override
        public void validate(Claim c, ClaimValidationContext ctx) {
          ctx.addValidationIssue(sharedIssue);
        }

        @Override
        public int priority() {
          return 0;
        }

        @Override
        public boolean appliesTo(String scope) {
          return true;
        }

        @Override public String getValidatorCode() { return "V1"; }
      };
      ClaimValidator v2 = new ClaimValidator() {
        @Override
        public void validate(Claim c, ClaimValidationContext ctx) {
          ctx.addValidationIssue(sharedIssue);
        }

        @Override
        public int priority() {
          return 0;
        }

        @Override
        public boolean appliesTo(String scope) {
          return false;
        }

        @Override public String getValidatorCode() { return "V2"; }
      };

      ClaimValidation pipeline = new ClaimValidation(List.of(v1, v2));
      var result = pipeline.validateClaim(Claim.builder().build(), "fee", List.of());

      assertThat(result.getIssues()).hasSize(1);
      assertThat(result.getIsValid()).isTrue(); // WARNING only → still valid
    }

    @Test
    @DisplayName("Deduplicates identical issues added by multiple validators and preserves order")
    void deduplicatesAcrossValidatorsAndPreservesOrder() {
      ValidationIssue issueA = ValidationIssue.builder().code("A").message("a").severity(ValidationSeverity.WARNING).build();
      ValidationIssue issueB = ValidationIssue.builder().code("B").message("b").severity(ValidationSeverity.WARNING).build();

      ClaimValidator v1 = new ClaimValidator() {
        @Override public void validate(Claim c, ClaimValidationContext ctx) { ctx.addValidationIssue(issueA); }
        @Override public int priority() { return 0; }
        @Override public boolean appliesTo(String scope) { return true; }
        @Override public String getValidatorCode() { return "V1"; }
      };

      ClaimValidator v2 = new ClaimValidator() {
        @Override public void validate(Claim c, ClaimValidationContext ctx) { ctx.addValidationIssue(issueB); }
        @Override public int priority() { return 10; }
        @Override public boolean appliesTo(String scope) { return true; }
        @Override public String getValidatorCode() { return "V2"; }
      };

      ClaimValidator v3 = new ClaimValidator() {
        @Override public void validate(Claim c, ClaimValidationContext ctx) { ctx.addValidationIssue(ValidationIssue.builder().code("A").message("a").severity(ValidationSeverity.WARNING).build()); }
        @Override public int priority() { return 20; }
        @Override public boolean appliesTo(String scope) { return true; }
        @Override public String getValidatorCode() { return "V3"; }
      };

      ClaimValidation pipeline = new ClaimValidation(List.of(v1, v2, v3));
      var result = pipeline.validateClaim(Claim.builder().build(), "fee", List.of());

      // Expect deduplication: only A and B should be present, in insertion order (A then B)
      assertThat(result.getIssues()).hasSize(2);
      assertThat(result.getIssues()).extracting(ValidationIssue::getCode).containsExactly("A", "B");
    }

    @Test
    @DisplayName("Returns MISSING_CLAIM error result when claim is null")
    void returnsMissingClaimErrorWhenClaimIsNull() {
      ClaimValidation pipeline = new ClaimValidation(List.of());

      var result = pipeline.validateClaim(null, "fee", List.of());

      assertThat(result.getIsValid()).isFalse();
      assertThat(result.getIssues()).hasSize(1);
      assertThat(result.getIssues().getFirst().getCode()).isEqualTo("MISSING_CLAIM");
    }
  }

    @Test
    @DisplayName("ValidationResult.isValid reflects absence of ERROR issues")
    void isValid_reflects_absenceOfErrorIssues() {
      ClaimValidator warnValidator = new ClaimValidator() {
        @Override public void validate(Claim c, ClaimValidationContext ctx) {
          ctx.addValidationIssue(ValidationIssue.builder().code("W").message("w").severity(ValidationSeverity.WARNING).build());
        }
        @Override public int priority() { return 0; }
        @Override public boolean appliesTo(String scope) { return true; }
        @Override public String getValidatorCode() { return "WARN"; }
      };

      ClaimValidator errorValidator = new ClaimValidator() {
        @Override public void validate(Claim c, ClaimValidationContext ctx) {
          ctx.addValidationIssue(ValidationIssue.builder().code("E").message("e").severity(ValidationSeverity.ERROR).build());
        }
        @Override public int priority() { return 0; }
        @Override public boolean appliesTo(String scope) { return true; }
        @Override public String getValidatorCode() { return "ERROR"; }
      };

      // warning only -> valid
      ClaimValidation pipelineWarn = new ClaimValidation(List.of(warnValidator));
      var resultWarn = pipelineWarn.validateClaim(Claim.builder().build(), "fee", List.of());
      assertThat(resultWarn.getIsValid()).isTrue();

      // error present -> invalid
      ClaimValidation pipelineError = new ClaimValidation(List.of(errorValidator));
      var resultError = pipelineError.validateClaim(Claim.builder().build(), "fee", List.of());
      assertThat(resultError.getIsValid()).isFalse();
    }

  // ─────────────────────────────────────────────────────────────────────────
  // Validation context construction
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Validation context construction")
  class ValidationContextConstruction {

    @Test
    @DisplayName("Passes related claims to validators via the context")
    void passesRelatedClaimsToValidators() {
      AtomicReference<ClaimValidationContext> captured = new AtomicReference<>();

      ClaimValidator capturingValidator = new ClaimValidator() {
        @Override
        public void validate(Claim claim, ClaimValidationContext context) {
          captured.set(context);
        }

        @Override
        public int priority() {
          return 0;
        }

        @Override
        public boolean appliesTo(String scope) {
          return true;
        }

        @Override public String getValidatorCode() { return "CAPTURE"; }
      };

      List<Claim> related = List.of(Claim.builder().uniqueFileNumber("010101/001").build());
      new ClaimValidation(List.of(capturingValidator))
          .validateClaim(Claim.builder().build(), "fee", related);

      assertThat(captured.get()).isNotNull();
      assertThat(captured.get().getRelatedClaims()).isEqualTo(related);
    }

    @Test
    @DisplayName("Converts null relatedClaims to an empty list in the context")
    void convertsNullRelatedClaimsToEmptyList() {
      AtomicReference<ClaimValidationContext> captured = new AtomicReference<>();

      ClaimValidator capturingValidator = new ClaimValidator() {
        @Override
        public void validate(Claim claim, ClaimValidationContext context) {
          captured.set(context);
        }

        @Override
        public int priority() {
          return 0;
        }

        @Override
        public boolean appliesTo(String scope) {
          return true;
        }

        @Override public String getValidatorCode() { return "CAPTURE"; }
      };

      new ClaimValidation(List.of(capturingValidator))
          .validateClaim(Claim.builder().build(), "fee", null);

      assertThat(captured.get()).isNotNull();
      assertThat(captured.get().getRelatedClaims()).isEmpty();
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Individual validator tests
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("MandatoryFieldClaimValidator")
  class MandatoryFieldValidation {

    private MandatoryFieldClaimValidator validator;

    @BeforeEach
    void setUp() {
      validator = new MandatoryFieldClaimValidator();
    }

    @Test
    @DisplayName("Returns MISSING_MANDATORY_FIELD errors when mandatory fields are absent")
    void returnsErrorWhenMandatoryFieldsMissing() {
      Claim claim = Claim.builder().areaOfLaw(AreaOfLaw.CRIME_LOWER).build();
      ClaimValidationContext context = ClaimValidationContext.builder().scope("fee").build();

      validator.validate(claim, context);

      assertThat(context.getIssues()).isNotEmpty();
      assertThat(context.getIssues().getFirst().getCode()).isEqualTo("MISSING_MANDATORY_FIELD");
      assertThat(context.getIssues().getFirst().getSeverity()).isEqualTo(ValidationSeverity.ERROR);
    }

    @Test
    @DisplayName("Returns no errors when all mandatory fields are present")
    void returnsNoErrorsWhenAllMandatoryFieldsPresent() {
      Claim claim = Claim.builder()
          .areaOfLaw(AreaOfLaw.CRIME_LOWER)
          .caseConcludedDate("2025-01-15")
          .stageReachedCode("PROA")
          .netProfitCostsAmount(new java.math.BigDecimal("100.00"))
          .disbursementsVatAmount(new java.math.BigDecimal("20.00"))
          .build();
      ClaimValidationContext context = ClaimValidationContext.builder().scope("fee").build();

      validator.validate(claim, context);
      assertThat(context.getIssues()).isEmpty();
    }

    @Test
    @DisplayName("Returns no errors when no area of law is set — nothing to check")
    void returnsNoErrorsWhenNoAreaOfLaw() {
      Claim claim = Claim.builder().build();
      ClaimValidationContext context = ClaimValidationContext.builder().scope("fee").build();

      validator.validate(claim, context);
      assertThat(context.getIssues()).isEmpty();
    }

    @Test
    @DisplayName("Has validator code CLAIM_MANDATORY_FIELD")
    void hasCorrectValidatorCode() {
      assertThat(validator.getValidatorCode()).isEqualTo("CLAIM_MANDATORY_FIELD");
    }

    @Test
    @DisplayName("priority and appliesTo for MandatoryFieldClaimValidator")
    void mandatoryFieldValidatorMetadata() {
      assertThat(validator.priority()).isEqualTo(10);
      assertThat(validator.appliesTo("any")).isTrue();
    }
  }

  @Nested
  @DisplayName("UniqueFileNumberClaimValidator")
  class UniqueFileNumberValidation {

    private UniqueFileNumberClaimValidator validator;

    @BeforeEach
    void setUp() {
      validator = new UniqueFileNumberClaimValidator();
    }

    @Test
    @DisplayName("Returns no errors when UFN format is valid")
    void returnsNoErrorsWhenUfnValid() {
      Claim claim = Claim.builder().uniqueFileNumber("010120/001").build();
      ClaimValidationContext context = ClaimValidationContext.builder().build();

      validator.validate(claim, context);
      assertThat(context.getIssues()).isEmpty();
    }

    @Test
    @DisplayName("Returns INVALID_DATE_IN_UNIQUE_FILE_NUMBER when UFN format is invalid")
    void returnsErrorWhenUfnFormatInvalid() {
      Claim claim = Claim.builder().uniqueFileNumber("invalid-format").build();
      ClaimValidationContext context = ClaimValidationContext.builder().build();

      validator.validate(claim, context);

      assertThat(context.getIssues()).hasSize(1);
      assertThat(context.getIssues().getFirst().getCode()).isEqualTo("INVALID_DATE_IN_UNIQUE_FILE_NUMBER");
    }

    @Test
    @DisplayName("Returns INVALID_DATE_IN_UNIQUE_FILE_NUMBER when UFN date is in the future")
    void returnsErrorWhenUfnDateInFuture() {
      Claim claim = Claim.builder().uniqueFileNumber("010149/001").build();
      ClaimValidationContext context = ClaimValidationContext.builder().build();

      validator.validate(claim, context);

      assertThat(context.getIssues()).hasSize(1);
      assertThat(context.getIssues().getFirst().getCode()).isEqualTo("INVALID_DATE_IN_UNIQUE_FILE_NUMBER");
    }

    @Test
    @DisplayName("Returns no errors when UFN is absent — mandatory check is handled elsewhere")
    void returnsNoErrorsWhenUfnMissing() {
      Claim claim = Claim.builder().build();
      ClaimValidationContext context = ClaimValidationContext.builder().build();

      validator.validate(claim, context);
      assertThat(context.getIssues()).isEmpty();
    }

    @Test
    @DisplayName("Has validator code CLAIM_UNIQUE_FILE_NUMBER")
    void hasCorrectValidatorCode() {
      assertThat(validator.getValidatorCode()).isEqualTo("CLAIM_UNIQUE_FILE_NUMBER");
    }
  }
}
