package uk.gov.justice.laa.dstew.payments.claims.validation.core.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
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
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidation;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.SubmissionValidator;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;

/**
 * Tests for {@link ValidationService}.
 *
 * <p>Verifies that {@link ValidationService} correctly delegates to the {@link ClaimValidation}
 * and {@link SubmissionValidation} pipelines and surfaces results to callers. Each overload is
 * tested independently to confirm default argument values are applied correctly. Pipeline-level
 * Pipeline-level behaviour — scope filtering, priority ordering, and deduplication — is tested in
 * ClaimValidationTest.
 */
@DisplayName("ValidationService")
class ValidationServiceTest {

  private Claim testClaim;
  private SubmissionResponse testSubmission;

  @BeforeEach
  void setUp() {
    testClaim = new Claim();
    testClaim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);
    testClaim.setOfficeAccountNumber("1A234B");

    testSubmission = new SubmissionResponse();
    testSubmission.setSubmissionId(UUID.randomUUID());
  }

  /** Creates a service with the given claim pipeline and an empty submission pipeline. */
  private static ValidationService withClaims(ClaimValidation claims) {
    return new ValidationService(claims, new SubmissionValidation(Collections.emptyList()));
  }

  /** Creates a service with the given submission pipeline and an empty claim pipeline. */
  private static ValidationService withSubmissions(SubmissionValidation submissions) {
    return new ValidationService(new ClaimValidation(Collections.emptyList()), submissions);
  }

  // ─────────────────────────────────────────────────────────────────────────
  // validateClaim(Claim)
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("validateClaim(Claim) — single-arg overload")
  class SingleArgOverload {

    @Test
    @DisplayName("Returns valid result when no validators raise errors")
    void returnsValidResultForValidClaim() {
      ValidationResult result = withClaims(new ClaimValidation(Collections.emptyList()))
          .validateClaim(testClaim);

      assertThat(result.getIsValid()).isTrue();
      assertThat(result.getIssues()).isEmpty();
    }

    @Test
    @DisplayName("Returns MISSING_CLAIM error when claim is null")
    void returnsMissingClaimWhenClaimIsNull() {
      ValidationResult result = withClaims(new ClaimValidation(Collections.emptyList()))
          .validateClaim((Claim) null);

      assertThat(result.getIsValid()).isFalse();
      assertThat(result.getIssues()).hasSize(1);
      assertThat(result.getIssues().getFirst().getCode()).isEqualTo("MISSING_CLAIM");
    }

    @Test
    @DisplayName("Scope defaults to null — all scope-agnostic validators run")
    void scopeDefaultsToNull() {
      AtomicReference<String> capturedScope = new AtomicReference<>("NOT_SET");

      ClaimValidator captor = new ClaimValidator() {
        @Override public void validate(Claim c, ClaimValidationContext ctx) {
          capturedScope.set(ctx.getScope());
          return;
        }
        @Override public String getValidatorCode() { return "CAPTOR"; }
      };

      withClaims(new ClaimValidation(List.of(captor))).validateClaim(testClaim);

      assertThat(capturedScope.get()).isNull();
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // validateClaim(Claim, String)
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("validateClaim(Claim, String) — two-arg overload")
  class TwoArgClaimOverload {

    @Test
    @DisplayName("Passes scope through to the pipeline")
    void passesScopeToPipeline() {
      AtomicReference<String> capturedScope = new AtomicReference<>();

      ClaimValidator captor = new ClaimValidator() {
        @Override public void validate(Claim c, ClaimValidationContext ctx) {
          capturedScope.set(ctx.getScope());
          return;
        }
        @Override public String getValidatorCode() { return "CAPTOR"; }
      };

      withClaims(new ClaimValidation(List.of(captor))).validateClaim(testClaim, "submission");

      assertThat(capturedScope.get()).isEqualTo("submission");
    }

    @Test
    @DisplayName("Related claims defaults to empty list")
    void relatedClaimsDefaultsToEmptyList() {
      AtomicReference<List<Claim>> capturedRelated = new AtomicReference<>();

      ClaimValidator captor = new ClaimValidator() {
        @Override public void validate(Claim c, ClaimValidationContext ctx) {
          capturedRelated.set(ctx.getRelatedClaims());
          return;
        }
        @Override public String getValidatorCode() { return "CAPTOR"; }
      };

      withClaims(new ClaimValidation(List.of(captor))).validateClaim(testClaim, "fee");

      assertThat(capturedRelated.get()).isEmpty();
    }

    @Test
    @DisplayName("Returns MISSING_CLAIM error when claim is null")
    void returnsMissingClaimWhenClaimIsNull() {
      ValidationResult result = withClaims(new ClaimValidation(Collections.emptyList()))
          .validateClaim(null, "fee");

      assertThat(result.getIsValid()).isFalse();
      assertThat(result.getIssues().getFirst().getCode()).isEqualTo("MISSING_CLAIM");
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // validateClaim(Claim, String, List<Claim>)
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("validateClaim(Claim, String, List) — three-arg overload")
  class ThreeArgClaimOverload {

    @Test
    @DisplayName("Returns valid result when no validators raise errors")
    void returnsValidResultWhenNoIssues() {
      assertThat(withClaims(new ClaimValidation(Collections.emptyList()))
          .validateClaim(testClaim, "fee", List.of()).getIsValid()).isTrue();
    }

    @Test
    @DisplayName("Returns invalid result when a validator raises an ERROR issue")
    void returnsInvalidResultWhenErrorIssueFound() {
      ClaimValidator errorValidator = new ClaimValidator() {
        @Override public void validate(Claim c, ClaimValidationContext ctx) {
          ctx.addValidationIssue(ValidationIssue.builder()
              .code("TEST_ERROR").message("error").severity(ValidationSeverity.ERROR).build());
          return;
        }
        @Override public String getValidatorCode() { return "ERROR_VALIDATOR"; }
      };

      ValidationResult result = withClaims(new ClaimValidation(List.of(errorValidator)))
          .validateClaim(testClaim, "fee", List.of());

      assertThat(result.getIsValid()).isFalse();
      assertThat(result.getIssues().getFirst().getCode()).isEqualTo("TEST_ERROR");
    }

    @Test
    @DisplayName("Returns valid result when validators raise only WARNING issues")
    void returnsValidResultWithWarningsOnly() {
      ClaimValidator warningValidator = new ClaimValidator() {
        @Override public void validate(Claim c, ClaimValidationContext ctx) {
          ctx.addValidationIssue(ValidationIssue.builder()
              .code("WARN").message("warn").severity(ValidationSeverity.WARNING).build());
          return;
        }
        @Override public String getValidatorCode() { return "WARN_VALIDATOR"; }
      };

      ValidationResult result = withClaims(new ClaimValidation(List.of(warningValidator)))
          .validateClaim(testClaim, null, List.of());

      assertThat(result.getIsValid()).isTrue();
      assertThat(result.getIssues().getFirst().getSeverity()).isEqualTo(ValidationSeverity.WARNING);
    }

    @Test
    @DisplayName("Returns MISSING_CLAIM error when claim is null")
    void returnsMissingClaimWhenClaimIsNull() {
      ValidationResult result = withClaims(new ClaimValidation(Collections.emptyList()))
          .validateClaim(null, "fee", List.of());

      assertThat(result.getIsValid()).isFalse();
      assertThat(result.getIssues().getFirst().getCode()).isEqualTo("MISSING_CLAIM");
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // validateSubmission(SubmissionResponse)
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("validateSubmission(SubmissionResponse) — single-arg overload")
  class SingleArgSubmissionOverload {

    @Test
    @DisplayName("Returns valid result when no validators raise errors")
    void returnsValidResultWhenNoIssues() {
      ValidationResult result = withSubmissions(new SubmissionValidation(Collections.emptyList()))
          .validateSubmission(testSubmission);

      assertThat(result.getIsValid()).isTrue();
    }

    @Test
    @DisplayName("Returns invalid result when a validator raises an ERROR issue")
    void returnsInvalidResultWhenErrorIssueFound() {
      SubmissionValidator errorValidator = new SubmissionValidator() {
        @Override public void validate(SubmissionResponse s, SubmissionValidationContext ctx) {
          ctx.addValidationIssue(ValidationIssue.builder()
              .code("SUB_ERROR").message("err").severity(ValidationSeverity.ERROR).build());
        }
        @Override public int priority() { return 0; }
      };

      ValidationResult result = withSubmissions(new SubmissionValidation(List.of(errorValidator)))
          .validateSubmission(testSubmission);

      assertThat(result.getIsValid()).isFalse();
      assertThat(result.getIssues().getFirst().getCode()).isEqualTo("SUB_ERROR");
    }

    @Test
    @DisplayName("Scope defaults to null — all scope-agnostic validators run")
    void scopeDefaultsToNull() {
      AtomicReference<String> capturedScope = new AtomicReference<>("NOT_SET");

      SubmissionValidator captor = new SubmissionValidator() {
        @Override public void validate(SubmissionResponse s, SubmissionValidationContext ctx) { /* no-op */ }
        @Override public boolean appliesTo(String scope) {
          capturedScope.set(scope);
          return true;
        }
        @Override public int priority() { return 0; }
      };

      withSubmissions(new SubmissionValidation(List.of(captor))).validateSubmission(testSubmission);

      assertThat(capturedScope.get()).isNull();
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // validateSubmission(SubmissionResponse, String)
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("validateSubmission(SubmissionResponse, String) — two-arg overload")
  class TwoArgSubmissionOverload {

    @Test
    @DisplayName("Passes scope through to the pipeline")
    void passesScopeToPipeline() {
      AtomicReference<String> capturedScope = new AtomicReference<>();

      SubmissionValidator captor = new SubmissionValidator() {
        @Override public void validate(SubmissionResponse s, SubmissionValidationContext ctx) { /* no-op */ }
        @Override public boolean appliesTo(String scope) {
          capturedScope.set(scope);
          return true;
        }
        @Override public int priority() { return 0; }
      };

      withSubmissions(new SubmissionValidation(List.of(captor)))
          .validateSubmission(testSubmission, "pre-process");

      assertThat(capturedScope.get()).isEqualTo("pre-process");
    }

    @Test
    @DisplayName("Returns valid result when validators raise only WARNING issues")
    void returnsValidResultWithWarningsOnly() {
      SubmissionValidator warningValidator = new SubmissionValidator() {
        @Override public void validate(SubmissionResponse s, SubmissionValidationContext ctx) {
          ctx.addValidationIssue(ValidationIssue.builder()
              .code("SUB_WARN").message("warn").severity(ValidationSeverity.WARNING).build());
        }
        @Override public int priority() { return 0; }
      };

      ValidationResult result = withSubmissions(new SubmissionValidation(List.of(warningValidator)))
          .validateSubmission(testSubmission, null);

      assertThat(result.getIsValid()).isTrue();
      assertThat(result.getIssues().getFirst().getSeverity()).isEqualTo(ValidationSeverity.WARNING);
    }
  }
}
