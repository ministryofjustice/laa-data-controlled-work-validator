package uk.gov.justice.laa.dstew.payments.claims.validation.core.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidation;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.ClaimValidator;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

/**
 * Tests for {@link ValidationService}.
 *
 * <p>These tests verify that {@link ValidationService} correctly delegates to the
 * {@link ClaimValidation} pipeline and surfaces results to callers. Each overload is tested
 * independently to confirm default argument values are applied correctly. The pipeline's own
 * behaviour — scope filtering, priority ordering, deduplication, and context construction — is
 * tested in ClaimValidationTest.
 */
@DisplayName("ValidationService")
class ValidationServiceTest {

  private Claim testClaim;

  @BeforeEach
  void setUp() {
    testClaim = new Claim();
    testClaim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);
    testClaim.setOfficeAccountNumber("1A234B");
  }

  // ─────────────────────────────────────────────────────────────────────────
  // validateClaim(Claim)
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("validateClaim(Claim) — single-arg overload")
  class SingleArgOverload {

    @Test
    @DisplayName("Returns valid result when claim is valid and no validators raise errors")
    void returnsValidResultForValidClaim() {
      ValidationService service =
          new ValidationService(new ClaimValidation(Collections.emptyList()));

      ValidationResult result = service.validateClaim(testClaim);

      assertThat(result.getIsValid()).isTrue();
      assertThat(result.getIssues()).isEmpty();
    }

    @Test
    @DisplayName("Returns MISSING_CLAIM error when claim is null")
    void returnsMissingClaimWhenClaimIsNull() {
      ValidationService service =
          new ValidationService(new ClaimValidation(Collections.emptyList()));

      ValidationResult result = service.validateClaim((Claim) null);

      assertThat(result.getIsValid()).isFalse();
      assertThat(result.getIssues()).hasSize(1);
      assertThat(result.getIssues().getFirst().getCode()).isEqualTo("MISSING_CLAIM");
    }

    @Test
    @DisplayName("Runs all scope-agnostic validators (scope defaults to null)")
    void runsScopeAgnosticValidators() {
      AtomicReference<String> capturedScope = new AtomicReference<>("NOT_SET");

      ClaimValidator scopeCaptor = new ClaimValidator() {
        @Override
        public List<ValidationIssue> validate(Claim claim, ClaimValidationContext ctx) {
          capturedScope.set(ctx.getScope());
          return List.of();
        }
        @Override public String getValidatorCode() { return "SCOPE_CAPTOR"; }
      };

      new ValidationService(new ClaimValidation(List.of(scopeCaptor)))
          .validateClaim(testClaim);

      assertThat(capturedScope.get()).isNull();
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // validateClaim(Claim, String)
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("validateClaim(Claim, String) — two-arg overload")
  class TwoArgOverload {

    @Test
    @DisplayName("Passes scope through to the pipeline")
    void passesScopeToPipeline() {
      AtomicReference<String> capturedScope = new AtomicReference<>();

      ClaimValidator scopeCaptor = new ClaimValidator() {
        @Override
        public List<ValidationIssue> validate(Claim claim, ClaimValidationContext ctx) {
          capturedScope.set(ctx.getScope());
          return List.of();
        }
        @Override public String getValidatorCode() { return "SCOPE_CAPTOR"; }
      };

      new ValidationService(new ClaimValidation(List.of(scopeCaptor)))
          .validateClaim(testClaim, "submission");

      assertThat(capturedScope.get()).isEqualTo("submission");
    }

    @Test
    @DisplayName("Passes empty related claims list to the pipeline")
    void passesEmptyRelatedClaimsToPipeline() {
      AtomicReference<List<Claim>> capturedRelated = new AtomicReference<>();

      ClaimValidator relatedCaptor = new ClaimValidator() {
        @Override
        public List<ValidationIssue> validate(Claim claim, ClaimValidationContext ctx) {
          capturedRelated.set(ctx.getRelatedClaims());
          return List.of();
        }
        @Override public String getValidatorCode() { return "RELATED_CAPTOR"; }
      };

      new ValidationService(new ClaimValidation(List.of(relatedCaptor)))
          .validateClaim(testClaim, "fee");

      assertThat(capturedRelated.get()).isEmpty();
    }

    @Test
    @DisplayName("Returns MISSING_CLAIM error when claim is null")
    void returnsMissingClaimWhenClaimIsNull() {
      ValidationService service =
          new ValidationService(new ClaimValidation(Collections.emptyList()));

      ValidationResult result = service.validateClaim(null, "fee");

      assertThat(result.getIsValid()).isFalse();
      assertThat(result.getIssues()).hasSize(1);
      assertThat(result.getIssues().getFirst().getCode()).isEqualTo("MISSING_CLAIM");
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // validateClaim(Claim, String, List<Claim>)
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("validateClaim(Claim, String, List) — full three-arg overload")
  class ThreeArgOverload {

    @Test
    @DisplayName("Returns valid result with no issues when no validators raise errors")
    void returnsValidResultWhenNoIssues() {
      ValidationService service =
          new ValidationService(new ClaimValidation(Collections.emptyList()));

      assertThat(service.validateClaim(testClaim, "fee", List.of()).getIsValid()).isTrue();
    }

    @Test
    @DisplayName("Returns invalid result when a validator raises an ERROR issue")
    void returnsInvalidResultWhenErrorIssueFound() {
      ClaimValidator errorValidator = new ClaimValidator() {
        @Override
        public List<ValidationIssue> validate(Claim claim, ClaimValidationContext ctx) {
          return List.of(ValidationIssue.builder()
              .code("TEST_ERROR").message("error").severity(ValidationSeverity.ERROR).build());
        }
        @Override public String getValidatorCode() { return "TEST_ERROR_VALIDATOR"; }
      };

      ValidationResult result = new ValidationService(new ClaimValidation(List.of(errorValidator)))
          .validateClaim(testClaim, "fee", List.of());

      assertThat(result.getIsValid()).isFalse();
      assertThat(result.getIssues().getFirst().getCode()).isEqualTo("TEST_ERROR");
    }

    @Test
    @DisplayName("Returns valid result when validators raise only WARNING issues")
    void returnsValidResultWithWarningsOnly() {
      ClaimValidator warningValidator = new ClaimValidator() {
        @Override
        public List<ValidationIssue> validate(Claim claim, ClaimValidationContext ctx) {
          return List.of(ValidationIssue.builder()
              .code("TEST_WARNING").message("warn").severity(ValidationSeverity.WARNING).build());
        }
        @Override public String getValidatorCode() { return "TEST_WARNING_VALIDATOR"; }
      };

      ValidationResult result =
          new ValidationService(new ClaimValidation(List.of(warningValidator)))
              .validateClaim(testClaim, null, List.of());

      assertThat(result.getIsValid()).isTrue();
      assertThat(result.getIssues().getFirst().getSeverity()).isEqualTo(ValidationSeverity.WARNING);
    }

    @Test
    @DisplayName("Returns MISSING_CLAIM error when claim is null")
    void returnsMissingClaimWhenClaimIsNull() {
      ValidationService service =
          new ValidationService(new ClaimValidation(Collections.emptyList()));

      ValidationResult result = service.validateClaim(null, "fee", List.of());

      assertThat(result.getIsValid()).isFalse();
      assertThat(result.getIssues().getFirst().getCode()).isEqualTo("MISSING_CLAIM");
    }
  }
}
