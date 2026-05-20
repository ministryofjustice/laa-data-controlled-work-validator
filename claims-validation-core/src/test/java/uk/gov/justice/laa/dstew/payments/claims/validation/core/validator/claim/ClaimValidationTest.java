package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.HttpFeeSchemeProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.ClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.MandatoryFieldClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.UniqueFileNumberClaimValidator;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.fee.scheme.model.FeeDetailsResponseV2;

/**
 * Tests for {@link ClaimValidation} and the individual {@link ClaimValidator} implementations it
 * orchestrates.
 */
@DisplayName("ClaimValidation")
class ClaimValidationTest {

  /** Provider mock shared across test groups that need it. */
  private HttpFeeSchemeProvider feeSchemeProvider;

  @BeforeEach
  void setUpProvider() {
    feeSchemeProvider = mock(HttpFeeSchemeProvider.class);
    // Default: returns empty so tests that don't care about fee type don't throw
    when(feeSchemeProvider.getFeeDetails(anyString()))
            .thenReturn(Optional.of(FeeDetailsResponseV2.builder().feeType("TEST_FEE_TYPE").build()));
  }

  // Helper: build a ClaimValidation with the shared mock provider
  private ClaimValidation pipeline(ClaimValidator... validators) {
    return new ClaimValidation(List.of(validators), feeSchemeProvider);
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Pipeline tests
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Pipeline — scope filtering, priority ordering, and deduplication")
  class ClaimValidationPipeline {

    @Test
    @DisplayName("Runs validators in priority order")
    void runValidatorsInPriorityOrder() {
      List<String> callOrder = new ArrayList<>();

      ClaimValidator lowPriority = stub("LOW", 20,  (c, ctx) -> callOrder.add("low"));
      ClaimValidator highPriority = stub("HIGH", 10,  (c, ctx) -> callOrder.add("high"));

      pipeline(lowPriority, highPriority)
          .validateClaim(Claim.builder().build(), null, List.of());

      assertThat(callOrder).containsExactly("high", "low");
    }

    @Test
    @DisplayName("Excludes validators whose appliesTo returns false for the given scope")
    void excludesValidatorsNotApplicableToScope() {
      List<String> called = new ArrayList<>();

      ClaimValidator excluded = stub("EXCLUDED", 0,  (c, ctx) -> called.add("excluded"));

      pipeline(excluded).validateClaim(Claim.builder().build(), Set.of("fee"), List.of());

      assertThat(called).isEmpty();
    }

    @Test
    @DisplayName("Deduplicates identical issues across validators, preserving insertion order")
    void deduplicatesAcrossValidatorsAndPreservesOrder() {
      ValidationIssue issueA = issue("A", ValidationSeverity.WARNING);
      ValidationIssue issueB = issue("B", ValidationSeverity.WARNING);

      ClaimValidator v1 = stub("V1", 0,  (c, ctx) -> ctx.addValidationIssue(issueA));
      ClaimValidator v2 = stub("V2", 10,  (c, ctx) -> ctx.addValidationIssue(issueB));
      // v3 adds issueA again — should be deduplicated
      ClaimValidator v3 = stub("V3", 20,  (c, ctx) ->
          ctx.addValidationIssue(issue("A", ValidationSeverity.WARNING)));

      var result = pipeline(v1, v2, v3)
          .validateClaim(Claim.builder().feeCode("FEE_CODE").build(), null, List.of());

      assertThat(result.getIssues()).hasSize(2);
      assertThat(result.getIssues()).extracting(ValidationIssue::getCode)
          .containsExactly("A", "B");
    }

    @Test
    @DisplayName("Returns MISSING_CLAIM error result when claim is null")
    void returnsMissingClaimErrorWhenClaimIsNull() {
      var result = pipeline().validateClaim(null, Set.of("fee"), List.of());

      assertThat(result.getIsValid()).isFalse();
      assertThat(result.getIssues()).hasSize(1);
      assertThat(result.getIssues().getFirst().getCode()).isEqualTo("MISSING_CLAIM");
    }

    @Test
    @DisplayName("Returns valid result when no validators produce ERROR issues")
    void returnsValidWhenNoErrors() {
      ClaimValidator warnOnly = stub("WARN", 0,  (c, ctx) ->
          ctx.addValidationIssue(issue("W", ValidationSeverity.WARNING)));

      var result = pipeline(warnOnly).validateClaim(Claim.builder().feeCode("FEE_CODE").build(), null, List.of());

      assertThat(result.getIsValid()).isTrue();
      assertThat(result.getIssues()).hasSize(1);
    }

    @Test
    @DisplayName("Returns invalid result when any validator produces an ERROR issue")
    void returnsInvalidWhenErrorPresent() {
      ClaimValidator errorValidator = stub("ERR", 0,  (c, ctx) ->
          ctx.addValidationIssue(issue("E", ValidationSeverity.ERROR)));

      var result = pipeline(errorValidator).validateClaim(Claim.builder().build(), Set.of("fee"), List.of());

      assertThat(result.getIsValid()).isFalse();
    }

    @Test
    @DisplayName("Returns valid result with empty issues when no validators are registered")
    void returnsValidWithNoIssuesWhenNoValidators() {
      var result = pipeline().validateClaim(Claim.builder().feeCode("FEE_CODE").build(), Set.of("fee"), List.of());

      assertThat(result.getIsValid()).isTrue();
      assertThat(result.getIssues()).isEmpty();
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // fetchFeeCalculationType
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("fetchFeeCalculationType — fee type resolution in context")
  class FetchFeeCalculationType {

    @ParameterizedTest(name = "feeCode={0}")
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    @DisplayName("Sets feeCalculationType to null when feeCode is null, empty, or blank")
    void setsNullFeeTypeForBlankFeeCode(String feeCode) {
      AtomicReference<ClaimValidationContext> captured = new AtomicReference<>();
      ClaimValidator captor = stub("CAP", 0,  (c, ctx) -> captured.set(ctx));

      pipeline(captor).validateClaim(
          Claim.builder().feeCode(feeCode).build(), Set.of("CAP"), List.of());

      assertThat(captured.get().getFeeCalculationType()).isNull();
      verify(feeSchemeProvider, never()).getFeeDetails(anyString());
    }

    @Test
    @DisplayName("Sets feeCalculationType to null when provider returns empty Optional")
    void setsNullFeeTypeWhenProviderReturnsEmpty() {
      when(feeSchemeProvider.getFeeDetails("UNKNOWN")).thenReturn(Optional.empty());

      AtomicReference<ClaimValidationContext> captured = new AtomicReference<>();
      ClaimValidator captor = stub("CAP", 0,  (c, ctx) -> captured.set(ctx));

      pipeline(captor).validateClaim(
          Claim.builder().feeCode("UNKNOWN").build(), Set.of("CAP"), List.of());

      assertThat(captured.get().getFeeCalculationType()).isNull();
    }

    @Test
    @DisplayName("Sets feeCalculationType to null when provider returns response with null feeType")
    void setsNullFeeTypeWhenFeeTypeIsNull() {
      FeeDetailsResponseV2 response = mock(FeeDetailsResponseV2.class);
      when(response.getFeeType()).thenReturn(null);
      when(feeSchemeProvider.getFeeDetails("FEE01")).thenReturn(Optional.of(response));

      AtomicReference<ClaimValidationContext> captured = new AtomicReference<>();
      ClaimValidator captor = stub("CAP", 0,  (c, ctx) -> captured.set(ctx));

      pipeline(captor).validateClaim(
          Claim.builder().feeCode("FEE01").build(), Set.of("CAP"), List.of());

      assertThat(captured.get().getFeeCalculationType()).isNull();
    }

    @Test
    @DisplayName("Sets feeCalculationType to null when provider returns response with blank feeType")
    void setsNullFeeTypeWhenFeeTypeIsBlank() {
      FeeDetailsResponseV2 response = mock(FeeDetailsResponseV2.class);
      when(response.getFeeType()).thenReturn("   ");
      when(feeSchemeProvider.getFeeDetails("FEE01")).thenReturn(Optional.of(response));

      AtomicReference<ClaimValidationContext> captured = new AtomicReference<>();
      ClaimValidator captor = stub("CAP", 0,  (c, ctx) -> captured.set(ctx));

      pipeline(captor).validateClaim(
          Claim.builder().feeCode("FEE01").build(), Set.of("CAP"), List.of());

      assertThat(captured.get().getFeeCalculationType()).isNull();
    }

    @Test
    @DisplayName("Sets feeCalculationType from provider when feeType is present and non-blank")
    void setsFeeTypeFromProviderWhenPresent() {
      FeeDetailsResponseV2 response = mock(FeeDetailsResponseV2.class);
      when(response.getFeeType()).thenReturn("FIXED");
      when(feeSchemeProvider.getFeeDetails("FEE01")).thenReturn(Optional.of(response));

      AtomicReference<ClaimValidationContext> captured = new AtomicReference<>();
      ClaimValidator captor = stub("CAP", 0,  (c, ctx) -> captured.set(ctx));

      pipeline(captor).validateClaim(
          Claim.builder().feeCode("FEE01").build(), Set.of("CAP"), List.of());

      assertThat(captured.get().getFeeCalculationType()).isEqualTo("FIXED");
    }

    @Test
    @DisplayName("Does not call provider when claim has null feeCode")
    void doesNotCallProviderWhenFeeCodeNull() {
      pipeline().validateClaim(Claim.builder().feeCode(null).build(), Set.of("CAP"), List.of());

      verify(feeSchemeProvider, never()).getFeeDetails(anyString());
    }
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
      ClaimValidator captor = stub("CAP", 0,  (c, ctx) -> captured.set(ctx));

      List<Claim> related = List.of(Claim.builder().uniqueFileNumber("010101/001").build());
      pipeline(captor).validateClaim(Claim.builder().build(), Set.of("CAP"), related);

      assertThat(captured.get().getRelatedClaims()).isEqualTo(related);
    }

    @Test
    @DisplayName("Converts null relatedClaims to an empty list in the context")
    void convertsNullRelatedClaimsToEmptyList() {
      AtomicReference<ClaimValidationContext> captured = new AtomicReference<>();
      ClaimValidator captor = stub("CAP", 0,  (c, ctx) -> captured.set(ctx));

      pipeline(captor).validateClaim(Claim.builder().build(), Set.of("CAP"), null);

      assertThat(captured.get().getRelatedClaims()).isEmpty();
    }

    @Test
    @DisplayName("Passes scope to the context")
    void passesScopeToContext() {
      AtomicReference<ClaimValidationContext> captured = new AtomicReference<>();
      ClaimValidator captor = stub("CAP", 0,  (c, ctx) -> captured.set(ctx));

      pipeline(captor).validateClaim(Claim.builder().build(), Set.of("CAP"), List.of());

      assertThat(captured.get().getScope()).isEqualTo(Set.of("CAP"));
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
      ClaimValidationContext context = ClaimValidationContext.builder().scope(Set.of("fee")).build();

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
      ClaimValidationContext context = ClaimValidationContext.builder().scope(Set.of("fee")).build();

      validator.validate(claim, context);
      assertThat(context.getIssues()).isEmpty();
    }

    @Test
    @DisplayName("Returns no errors when no area of law is set — nothing to check")
    void returnsNoErrorsWhenNoAreaOfLaw() {
      Claim claim = Claim.builder().build();
      ClaimValidationContext context = ClaimValidationContext.builder().scope(Set.of("fee")).build();

      validator.validate(claim, context);
      assertThat(context.getIssues()).isEmpty();
    }

    @Test
    @DisplayName("Path on missing-field issue is snake_case")
    void missingFieldIssuePathIsSnakeCase() {
      Claim claim = Claim.builder().areaOfLaw(AreaOfLaw.CRIME_LOWER).build();
      ClaimValidationContext context = ClaimValidationContext.builder().scope(Set.of("fee")).build();

      validator.validate(claim, context);

      assertThat(context.getIssues())
          .allSatisfy(issue -> assertThat(issue.getPath())
              .doesNotContainPattern("[A-Z]") // no uppercase chars → snake_case
              .doesNotContain(" "));
    }

    @Test
    @DisplayName("Has validator code CLAIM_MANDATORY_FIELD")
    void hasCorrectValidatorCode() {
      assertThat(validator.getValidatorCode()).isEqualTo("CLAIM_MANDATORY_FIELD");
    }

    @Test
    @DisplayName("Priority is 10 and appliesTo any scope")
    void mandatoryFieldValidatorMetadata() {
      assertThat(validator.priority()).isEqualTo(10);
      assertThat(validator.appliesTo(null)).isTrue();
      assertThat(validator.appliesTo(new HashSet<>())).isTrue();
      assertThat(validator.appliesTo(Set.of("CLAIM_MANDATORY_FIELD"))).isTrue();
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
      assertThat(context.getIssues().getFirst().getCode())
          .isEqualTo("INVALID_DATE_IN_UNIQUE_FILE_NUMBER");
    }

    @Test
    @DisplayName("Returns INVALID_DATE_IN_UNIQUE_FILE_NUMBER when UFN date is in the future")
    void returnsErrorWhenUfnDateInFuture() {
      Claim claim = Claim.builder().uniqueFileNumber("010149/001").build();
      ClaimValidationContext context = ClaimValidationContext.builder().build();

      validator.validate(claim, context);

      assertThat(context.getIssues()).hasSize(1);
      assertThat(context.getIssues().getFirst().getCode())
          .isEqualTo("INVALID_DATE_IN_UNIQUE_FILE_NUMBER");
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

  // ─────────────────────────────────────────────────────────────────────────
  // Helpers
  // ─────────────────────────────────────────────────────────────────────────

  @FunctionalInterface
  interface ValidatorAction {
    void run(Claim claim, ClaimValidationContext context);
  }

  private static ClaimValidator stub(String code, int priority, ValidatorAction action) {
    return new ClaimValidator() {
      @Override public void validate(Claim claim, ClaimValidationContext context) { action.run(claim, context); }
      @Override public int priority() { return priority; }
      @Override public String getValidatorCode() { return code; }
    };
  }

  private static ValidationIssue issue(String code, ValidationSeverity severity) {
    return ValidationIssue.builder().code(code).message(code).severity(severity).build();
  }
}
