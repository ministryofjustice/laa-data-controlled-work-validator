package uk.gov.justice.laa.dstew.payments.claims.validation.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ClaimValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ResolvedClaimData;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.HttpFeeSchemeProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidatorCode;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidation;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidatorCode;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.ClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidation;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidatorCode;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.SubmissionValidator;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;
import uk.gov.justice.laa.fee.scheme.model.FeeDetailsResponseV2;

/**
 * Tests for {@link ValidationService}.
 *
 * <p>Verifies that {@link ValidationService} correctly delegates to the {@link ClaimValidation}
 * and {@link SubmissionValidation} pipelines and surfaces results to callers. Each overload is
 * tested independently to confirm default argument values are applied correctly.
 * Pipeline-level behaviour — scope filtering, priority ordering, and deduplication — is tested in
 * ClaimValidationTest.
 */
@DisplayName("ValidationService")
class ValidationServiceTest {

  private Claim testClaim;
  private SubmissionResponse testSubmission;
  private HttpFeeSchemeProvider feeSchemeProvider;

  @BeforeEach
  void setUp() {
    feeSchemeProvider = mock(HttpFeeSchemeProvider.class);
    // Default test provider returns both feeType and areaOfLaw so the happy-path fee-scheme
    // resolution succeeds.
    when(feeSchemeProvider.getFeeDetails(anyString()))
            .thenReturn(Optional.of(FeeDetailsResponseV2.builder()
                    .feeType("TEST_FEE_TYPE")
                    .areaOfLaw("TEST_AREA")
                    .build()));

    testClaim = new Claim();
    testClaim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);
    testClaim.setOfficeAccountNumber("1A234B");
    testClaim.setFeeCode("TEST_FEE");

    testSubmission = new SubmissionResponse();
    testSubmission.setSubmissionId(UUID.randomUUID());
  }

  /** Creates a service with the given claim pipeline and an empty submission pipeline. */
  private ValidationService withClaims(ClaimValidation claims) {
    return new ValidationService(claims, new SubmissionValidation(Collections.emptyList()));
  }

  /** Creates a service with the given submission pipeline and an empty claim pipeline. */
  private ValidationService withSubmissions(SubmissionValidation submissions) {
    return new ValidationService(
        new ClaimValidation(Collections.emptyList(), feeSchemeProvider), submissions);
  }

  /** Builds a ClaimValidation with the given validators using the shared mock provider. */
  private ClaimValidation claimValidation(List<ClaimValidator> validators) {
    return new ClaimValidation(validators, feeSchemeProvider);
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
      ClaimValidationResult result = withClaims(claimValidation(Collections.emptyList()))
          .validateClaim(testClaim);

      assertThat(result.isValid()).isTrue();
      assertThat(result.getIssues()).isEmpty();
      // Resolved data is surfaced with the values resolved from the fee-scheme provider.
      assertThat(result.getResolvedData()).isNotNull();
      assertThat(result.getResolvedData().feeCalculationType()).isEqualTo("TEST_FEE_TYPE");
      assertThat(result.getResolvedData().feeSchemeAreaOfLaw()).isEqualTo("TEST_AREA");
    }

    @Test
    @DisplayName("Returns MISSING_CLAIM error when claim is null")
    void returnsMissingClaimWhenClaimIsNull() {
      ClaimValidationResult result = withClaims(claimValidation(Collections.emptyList()))
          .validateClaim(null);

      assertThat(result.isValid()).isFalse();
      assertThat(result.getIssues()).hasSize(1);
      assertThat(result.getIssues().getFirst().getCode()).isEqualTo("MISSING_CLAIM");
      // Resolved data must be non-null (empty) even for a missing claim
      assertThat(result.getResolvedData()).isEqualTo(ResolvedClaimData.empty());
      assertThat(result.getResolvedData().feeCalculationType()).isNull();
      assertThat(result.getResolvedData().feeSchemeAreaOfLaw()).isNull();
      assertThat(result.getResolvedData().authorisedCategoryOfLawCode()).isNull();
    }

    @Test
    @DisplayName("Scope defaults to null — all scope-agnostic validators run")
    void scopeDefaultsToNull() {
      AtomicReference<Set<? extends ValidatorCode>> capturedScope = new AtomicReference<>(Set.of(ClaimValidatorCode.CLAIM_MATTER_TYPE_VALIDATOR));

      ClaimValidator captor = new ClaimValidator() {
        @Override public void validate(Claim c, ClaimValidationContext ctx) {
          capturedScope.set(ctx.getScope());
        }

        @Override
        public int priority() {
          return 0;
        }

        @Override public ClaimValidatorCode getValidatorCode() { return ClaimValidatorCode.CLAIM_SCHEMA_VALIDATOR; }
      };

      withClaims(claimValidation(List.of(captor))).validateClaim(testClaim);

      assertThat(capturedScope.get()).isNull();
    }

    @Test
    @DisplayName("Missing areaOfLaw from fee details yields TECHNICAL_ERROR_FEE_SCHEME_API")
    void missingAreaOfLawIsTechnicalError() {
      // Override provider to return a response without areaOfLaw
      when(feeSchemeProvider.getFeeDetails(anyString()))
              .thenReturn(Optional.of(FeeDetailsResponseV2.builder().feeType("TEST_FEE_TYPE").build()));

      ClaimValidationResult result = withClaims(claimValidation(Collections.emptyList()))
          .validateClaim(testClaim);

      assertThat(result.isValid()).isFalse();
      assertThat(result.getIssues()).isNotEmpty();
      assertThat(result.getIssues().getFirst().getCode()).isEqualTo("TECHNICAL_ERROR_FEE_SCHEME_API");
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // validateClaim(Claim, Set<ClaimValidatorCode>)
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("validateClaim(Claim, Set<ClaimValidatorCode>) — two-arg overload")
  class TwoArgClaimOverload {

    @Test
    @DisplayName("Passes scope through to the pipeline")
    void passesScopeToPipeline() {
      AtomicReference<Set<? extends ValidatorCode>> capturedScope = new AtomicReference<>();

      ClaimValidator captor = new ClaimValidator() {
        @Override public void validate(Claim c, ClaimValidationContext ctx) {
          capturedScope.set(ctx.getScope());
        }

        @Override
        public int priority() {
          return 0;
        }

        @Override public ClaimValidatorCode getValidatorCode() { return ClaimValidatorCode.CLAIM_SCHEMA_VALIDATOR; }
      };

      withClaims(claimValidation(List.of(captor))).validateClaim(testClaim, Set.of(ClaimValidatorCode.CLAIM_SCHEMA_VALIDATOR));

      assertThat(capturedScope.get()).isEqualTo(Set.of(ClaimValidatorCode.CLAIM_SCHEMA_VALIDATOR));
    }

    @Test
    @DisplayName("Related claims defaults to empty list")
    void relatedClaimsDefaultsToEmptyList() {
      AtomicReference<List<Claim>> capturedRelated = new AtomicReference<>();

      ClaimValidator captor = new ClaimValidator() {
        @Override public void validate(Claim c, ClaimValidationContext ctx) {
          capturedRelated.set(ctx.getRelatedClaims());
        }

        @Override
        public int priority() {
          return 0;
        }

        @Override public ClaimValidatorCode getValidatorCode() { return ClaimValidatorCode.CLAIM_SCHEMA_VALIDATOR; }
      };

      withClaims(claimValidation(List.of(captor))).validateClaim(testClaim, Set.of(ClaimValidatorCode.CLAIM_SCHEMA_VALIDATOR));

      assertThat(capturedRelated.get()).isEmpty();
    }

    @Test
    @DisplayName("Returns MISSING_CLAIM error when claim is null")
    void returnsMissingClaimWhenClaimIsNull() {
      ClaimValidationResult result = withClaims(claimValidation(Collections.emptyList()))
          .validateClaim(null, Set.of(ClaimValidatorCode.CLAIM_SCHEMA_VALIDATOR));

      assertThat(result.isValid()).isFalse();
      assertThat(result.getIssues().getFirst().getCode()).isEqualTo("MISSING_CLAIM");
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // validateClaim(Claim, Set<ClaimValidatorCode>, List<Claim>)
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("validateClaim(Claim, Set<ClaimValidatorCode>, List) — three-arg overload")
  class ThreeArgClaimOverload {

    @Test
    @DisplayName("Returns valid result when no validators raise errors")
    void returnsValidResultWhenNoIssues() {
      assertThat(withClaims(claimValidation(Collections.emptyList()))
          .validateClaim(testClaim, Set.of(ClaimValidatorCode.CLAIM_SCHEMA_VALIDATOR), List.of()).isValid()).isTrue();
    }

    @Test
    @DisplayName("Returns invalid result when a validator raises an ERROR issue")
    void returnsInvalidResultWhenErrorIssueFound() {
      ClaimValidator errorValidator = new ClaimValidator() {
        @Override public void validate(Claim c, ClaimValidationContext ctx) {
          ctx.addValidationIssue(ValidationIssue.builder()
              .code("TEST_ERROR").message("error").severity(ValidationSeverity.ERROR).build());
        }

        @Override
        public int priority() {
          return 0;
        }

        @Override public ClaimValidatorCode getValidatorCode() { return ClaimValidatorCode.CLAIM_MATTER_TYPE_VALIDATOR; }
      };

      ClaimValidationResult result = withClaims(claimValidation(List.of(errorValidator)))
          .validateClaim(testClaim, Set.of(ClaimValidatorCode.CLAIM_MATTER_TYPE_VALIDATOR), List.of());

      assertThat(result.isValid()).isFalse();
      assertThat(result.getIssues().getFirst().getCode()).isEqualTo("TEST_ERROR");
    }

    @Test
    @DisplayName("Returns valid result when validators raise only WARNING issues")
    void returnsValidResultWithWarningsOnly() {
      ClaimValidator warningValidator = new ClaimValidator() {
        @Override public void validate(Claim c, ClaimValidationContext ctx) {
          ctx.addValidationIssue(ValidationIssue.builder()
              .code("WARN").message("warn").severity(ValidationSeverity.WARNING).build());
        }

        @Override
        public int priority() {
          return 0;
        }

        @Override public ClaimValidatorCode getValidatorCode() { return ClaimValidatorCode.CLAIM_CASE_DATES_VALIDATOR; }
      };

      ClaimValidationResult result = withClaims(claimValidation(List.of(warningValidator)))
          .validateClaim(testClaim, (Set<ClaimValidatorCode>) null, List.of());

      assertThat(result.isValid()).isTrue();
      assertThat(result.getIssues().getFirst().getSeverity()).isEqualTo(ValidationSeverity.WARNING);
    }

    @Test
    @DisplayName("Returns MISSING_CLAIM error when claim is null")
    void returnsMissingClaimWhenClaimIsNull() {
      ClaimValidationResult result = withClaims(claimValidation(Collections.emptyList()))
          .validateClaim(null, Set.of(ClaimValidatorCode.CLAIM_SCHEMA_VALIDATOR), List.of());

      assertThat(result.isValid()).isFalse();
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

      assertThat(result.isValid()).isTrue();
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

        @Override
        public SubmissionValidatorCode getValidatorCode() {
          return SubmissionValidatorCode.SUBMISSION_SCHEMA_VALIDATOR;
        }
      };

      ValidationResult result = withSubmissions(new SubmissionValidation(List.of(errorValidator)))
          .validateSubmission(testSubmission);

      assertThat(result.isValid()).isFalse();
      assertThat(result.getIssues().getFirst().getCode()).isEqualTo("SUB_ERROR");
    }

    @Test
    @DisplayName("Scope defaults to null — all scope-agnostic validators run")
    void scopeDefaultsToNull() {
      AtomicReference<Set<? extends ValidatorCode>> capturedScope = new AtomicReference<>();

      SubmissionValidator captor = new SubmissionValidator() {
        @Override public void validate(SubmissionResponse s, SubmissionValidationContext ctx) {
          capturedScope.set(ctx.getScope());
        }

        @Override
        public SubmissionValidatorCode getValidatorCode() {
          return SubmissionValidatorCode.SUBMISSION_SCHEMA_VALIDATOR;
        }

        @Override public int priority() { return 0; }
      };

      withSubmissions(new SubmissionValidation(List.of(captor))).validateSubmission(testSubmission);

      assertThat(capturedScope.get()).isNull();
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // validateSubmission(SubmissionResponse, Set<SubmissionValidatorCode>)
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("validateSubmission(SubmissionResponse, Set<SubmissionValidatorCode>) — two-arg overload")
  class TwoArgSubmissionOverload {

    @Test
    @DisplayName("Passes scope through to the pipeline")
    void passesScopeToPipeline() {
      AtomicReference<Set<? extends ValidatorCode>> capturedScope = new AtomicReference<>();

      SubmissionValidator captor = new SubmissionValidator() {
        @Override public void validate(SubmissionResponse s, SubmissionValidationContext ctx) { /* no-op */ }
        @Override public boolean appliesTo(Set<? extends ValidatorCode> scope) {
          capturedScope.set(scope);
          return true;
        }

        @Override
        public SubmissionValidatorCode getValidatorCode() {
          return SubmissionValidatorCode.SUBMISSION_SCHEMA_VALIDATOR;
        }

        @Override public int priority() { return 0; }
      };

      withSubmissions(new SubmissionValidation(List.of(captor)))
          .validateSubmission(testSubmission, Set.of(SubmissionValidatorCode.SUBMISSION_STATUS_VALIDATOR));

      assertThat(capturedScope.get()).isEqualTo(Set.of(SubmissionValidatorCode.SUBMISSION_STATUS_VALIDATOR));
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

        @Override
        public SubmissionValidatorCode getValidatorCode() {
          return SubmissionValidatorCode.SUBMISSION_SCHEMA_VALIDATOR;
        }
      };

      ValidationResult result = withSubmissions(new SubmissionValidation(List.of(warningValidator)))
          .validateSubmission(testSubmission, (Set<SubmissionValidatorCode>) null);

      assertThat(result.isValid()).isTrue();
      assertThat(result.getIssues().getFirst().getSeverity()).isEqualTo(ValidationSeverity.WARNING);
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Type-safe ValidatorCode overloads
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("ValidatorCode (type-safe) overloads")
  class ValidatorCodeOverloads {

    @Test
    @DisplayName("validateClaim(Claim, Set<ClaimValidatorCode>) passes converted scope to the pipeline")
    void validateClaimTypedScope() {
      AtomicReference<Set<? extends ValidatorCode>> capturedScope = new AtomicReference<>();

      ClaimValidator captor = new ClaimValidator() {
        @Override public void validate(Claim c, ClaimValidationContext ctx) { /* no-op */ }
        @Override public boolean appliesTo(Set<? extends ValidatorCode> scope) {
          capturedScope.set(scope);
          return true;
        }
        @Override public int priority() { return 0; }
        @Override public ClaimValidatorCode getValidatorCode() {
          return ClaimValidatorCode.CLAIM_SCHEMA_VALIDATOR;
        }
      };

      withClaims(claimValidation(List.of(captor)))
          .validateClaim(testClaim,
              Set.of(ClaimValidatorCode.CLAIM_SCHEMA_VALIDATOR, ClaimValidatorCode.CLAIM_MATTER_TYPE_VALIDATOR));

      assertThat(capturedScope.get())
          .isEqualTo(Set.of(ClaimValidatorCode.CLAIM_SCHEMA_VALIDATOR, ClaimValidatorCode.CLAIM_MATTER_TYPE_VALIDATOR));
    }

    @Test
    @DisplayName("validateClaim(Claim, Set<ClaimValidatorCode>, List) passes related claims and scope")
    void validateClaimRelatedAndTypedScope() {
      AtomicReference<Set<? extends ValidatorCode>> capturedScope = new AtomicReference<>();
      AtomicReference<List<Claim>> capturedRelated = new AtomicReference<>();

      ClaimValidator captor = new ClaimValidator() {
        @Override public void validate(Claim c, ClaimValidationContext ctx) {
          capturedRelated.set(ctx.getRelatedClaims());
        }
        @Override public boolean appliesTo(Set<? extends ValidatorCode> scope) {
          capturedScope.set(scope);
          return true;
        }
        @Override public int priority() { return 0; }
        @Override public ClaimValidatorCode getValidatorCode() {
          return ClaimValidatorCode.CLAIM_DUPLICATE_VALIDATOR;
        }
      };

      List<Claim> related = List.of(new Claim());
      withClaims(claimValidation(List.of(captor)))
          .validateClaim(testClaim, Set.of(ClaimValidatorCode.CLAIM_DUPLICATE_VALIDATOR), related);

      assertThat(capturedScope.get()).isEqualTo(Set.of(ClaimValidatorCode.CLAIM_DUPLICATE_VALIDATOR));
      assertThat(capturedRelated.get()).isEqualTo(related);
    }

    @Test
    @DisplayName("validateClaim(Claim, empty Set<ClaimValidatorCode>) runs all scope-agnostic validators")
    void validateClaimEmptyTypedScope() {
      AtomicReference<Set<? extends ValidatorCode>> capturedScope = new AtomicReference<>(Set.of(ClaimValidatorCode.CLAIM_MATTER_TYPE_VALIDATOR));

      ClaimValidator captor = new ClaimValidator() {
        @Override public void validate(Claim c, ClaimValidationContext ctx) {
          capturedScope.set(ctx.getScope());
        }
        @Override public int priority() { return 0; }
        @Override public ClaimValidatorCode getValidatorCode() {
          return ClaimValidatorCode.CLAIM_SCHEMA_VALIDATOR;
        }
      };

      // Empty scope -> all scope-agnostic validators run.
      withClaims(claimValidation(List.of(captor)))
          .validateClaim(testClaim, Set.<ClaimValidatorCode>of());

      assertThat(capturedScope.get()).isEmpty();
    }

    @Test
    @DisplayName("validateClaim(Claim, (Set<ClaimValidatorCode>) null) runs all scope-agnostic validators")
    void validateClaimNullTypedScope() {
      AtomicReference<Set<? extends ValidatorCode>> capturedScope = new AtomicReference<>(Set.of(ClaimValidatorCode.CLAIM_MATTER_TYPE_VALIDATOR));

      ClaimValidator captor = new ClaimValidator() {
        @Override public void validate(Claim c, ClaimValidationContext ctx) {
          capturedScope.set(ctx.getScope());
        }
        @Override public int priority() { return 0; }
        @Override public ClaimValidatorCode getValidatorCode() {
          return ClaimValidatorCode.CLAIM_SCHEMA_VALIDATOR;
        }
      };

      // Null scope is preserved as null -> pipeline runs all scope-agnostic validators.
      withClaims(claimValidation(List.of(captor)))
          .validateClaim(testClaim, (Set<ClaimValidatorCode>) null);

      assertThat(capturedScope.get()).isNull();
    }

    @Test
    @DisplayName("validateSubmission(SubmissionResponse, Set<SubmissionValidatorCode>) passes converted scope")
    void validateSubmissionTypedScope() {
      AtomicReference<Set<? extends ValidatorCode>> capturedScope = new AtomicReference<>();

      SubmissionValidator captor = new SubmissionValidator() {
        @Override public void validate(SubmissionResponse s, SubmissionValidationContext ctx) { /* no-op */ }
        @Override public boolean appliesTo(Set<? extends ValidatorCode> scope) {
          capturedScope.set(scope);
          return true;
        }
        @Override public SubmissionValidatorCode getValidatorCode() {
          return SubmissionValidatorCode.SUBMISSION_SCHEMA_VALIDATOR;
        }
        @Override public int priority() { return 0; }
      };

      withSubmissions(new SubmissionValidation(List.of(captor)))
          .validateSubmission(testSubmission, Set.of(SubmissionValidatorCode.SUBMISSION_SCHEMA_VALIDATOR));

      assertThat(capturedScope.get())
          .isEqualTo(Set.of(SubmissionValidatorCode.SUBMISSION_SCHEMA_VALIDATOR));
    }
  }
}
