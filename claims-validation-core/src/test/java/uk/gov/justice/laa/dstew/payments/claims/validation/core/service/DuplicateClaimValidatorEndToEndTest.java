package uk.gov.justice.laa.dstew.payments.claims.validation.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ClaimValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.HttpFeeSchemeProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidation;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidatorCode;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.DuplicateClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateClaimCrimeLowerValidationServiceStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateClaimLegalHelpDisbursementValidationStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateClaimLegalHelpValidationServiceStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateClaimValidationStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateSameSubmissionLegalHelpValidationServiceStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidation;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResponse;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResultSet;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;
import uk.gov.justice.laa.fee.scheme.model.FeeDetailsResponseV2;

/**
 * End-to-end characterization tests for duplicate-claim detection driven through the public
 * {@link ValidationService#validateClaim} entry point.
 *
 * <p>Unlike the per-strategy tests (which call {@code validateDuplicateClaims(...)} directly),
 * these wire the <em>real</em> {@link DuplicateClaimValidator} and area-of-law strategies against
 * a stubbed {@link ClaimsDataProvider} and {@link HttpFeeSchemeProvider}, exercising the same path
 * a consumer uses when calling {@code validateClaim(claim, {CLAIM_DUPLICATE_CLAIM})}.
 *
 * <p>{@code RegressionGuards} lock behaviour that must not change (another-submission detection,
 * the bulk/in-memory path, and the inert Mediation path). {@code SingleClaimSameSubmissionTargets}
 * exercise the provider-backed same-submission detection on the single-claim path.
 */
@DisplayName("DuplicateClaimValidator — end-to-end via ValidationService")
class DuplicateClaimValidatorEndToEndTest {

  private static final String OFFICE = "OFICE1";
  private static final String FEE_CODE = "FEE123";
  private static final String UFN = "010125/001";
  private static final String UCN = "02021990/B/CDEF";
  private static final String NON_DISBURSEMENT_FEE_TYPE = "FIXED";

  private static final UUID SUBMISSION_S1 = UUID.randomUUID();
  private static final UUID SUBMISSION_S2 = UUID.randomUUID();
  private static final UUID CLAIM_UNDER_VALIDATION_ID = UUID.randomUUID();

  private ClaimsDataProvider claimsDataProvider;
  private ValidationService validationService;

  @BeforeEach
  void setUp() {
    claimsDataProvider = mock(ClaimsDataProvider.class);
    HttpFeeSchemeProvider feeSchemeProvider = mock(HttpFeeSchemeProvider.class);

    // Fee-scheme resolution: non-disbursement fee type so the LH previous-submission strategy runs
    // and the disbursement (Rule B) strategy short-circuits.
    when(feeSchemeProvider.getFeeDetails(anyString()))
        .thenReturn(
            Optional.of(
                FeeDetailsResponseV2.builder()
                    .feeType(NON_DISBURSEMENT_FEE_TYPE)
                    .areaOfLaw("LEGAL_HELP")
                    .build()));

    List<DuplicateClaimValidationStrategy> strategies =
        List.of(
            new DuplicateSameSubmissionLegalHelpValidationServiceStrategy(claimsDataProvider),
            new DuplicateClaimLegalHelpValidationServiceStrategy(claimsDataProvider),
            new DuplicateClaimLegalHelpDisbursementValidationStrategy(claimsDataProvider),
            new DuplicateClaimCrimeLowerValidationServiceStrategy(claimsDataProvider));

    DuplicateClaimValidator duplicateClaimValidator = new DuplicateClaimValidator(strategies);

    ClaimValidation claimValidation =
        new ClaimValidation(List.of(duplicateClaimValidator), feeSchemeProvider);
    validationService =
        new ValidationService(claimValidation, new SubmissionValidation(Collections.emptyList()));
  }

  /** The Legal Help claim under validation, in submission S1. */
  private Claim legalHelpClaimUnderValidation() {
    return Claim.builder()
        .id(CLAIM_UNDER_VALIDATION_ID)
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .officeAccountNumber(OFFICE)
        .submissionId(SUBMISSION_S1)
        .feeCode(FEE_CODE)
        .uniqueFileNumber(UFN)
        .uniqueClientNumber(UCN)
        .status(ClaimStatus.READY_TO_PROCESS)
        .build();
  }

  /** A candidate claim as returned by the provider (Data Claims API shape). */
  private ClaimResponse candidate(UUID submissionId, ClaimStatus status) {
    return candidate(UUID.randomUUID(), submissionId, FEE_CODE, UFN, UCN, status);
  }

  private ClaimResponse candidate(
      UUID id,
      UUID submissionId,
      String feeCode,
      String ufn,
      String ucn,
      ClaimStatus status) {
    return new ClaimResponse()
        .id(id.toString())
        .submissionId(submissionId.toString())
        .feeCode(feeCode)
        .uniqueFileNumber(ufn)
        .uniqueClientNumber(ucn)
        .status(status);
  }

  private void stubProvider(ClaimResponse... content) {
    when(claimsDataProvider.getClaims(
            any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
        .thenReturn(new ClaimResultSet().content(List.of(content)));
  }

  private ClaimValidationResult validate(Claim claim) {
    return validationService.validateClaim(
        claim, Set.of(ClaimValidatorCode.CLAIM_DUPLICATE_VALIDATOR));
  }

  @Nested
  @DisplayName("Regression guards (must stay green)")
  class RegressionGuards {

    @Test
    @DisplayName("Single-claim: VALID candidate in ANOTHER submission ⇒ ANOTHER_SUBMISSION")
    void anotherSubmissionDetectedOnSingleClaimPath() {
      stubProvider(candidate(SUBMISSION_S2, ClaimStatus.VALID));

      ClaimValidationResult result = validate(legalHelpClaimUnderValidation());

      assertThat(result.getIssues())
          .extracting(i -> i.getCode())
          .contains(ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION.name());
    }

    @Test
    @DisplayName("Single-claim: no matching candidate anywhere ⇒ no duplicate issue")
    void noDuplicateWhenProviderEmptyAndNoSiblings() {
      stubProvider();

      ClaimValidationResult result = validate(legalHelpClaimUnderValidation());

      assertThat(result.getIssues())
          .extracting(i -> i.getCode())
          .doesNotContain(
              ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_SAME_SUBMISSION.name(),
              ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION.name());
    }

    @Test
    @DisplayName("Bulk path: matching sibling in the SAME submission ⇒ SAME_SUBMISSION")
    void sameSubmissionDetectedViaRelatedClaims() {
      stubProvider(); // provider returns nothing; detection comes from the in-memory sibling

      Claim sibling =
          Claim.builder()
              .id(UUID.randomUUID())
              .areaOfLaw(AreaOfLaw.LEGAL_HELP)
              .officeAccountNumber(OFFICE)
              .submissionId(SUBMISSION_S1)
              .feeCode(FEE_CODE)
              .uniqueFileNumber(UFN)
              .uniqueClientNumber(UCN)
              .status(ClaimStatus.READY_TO_PROCESS)
              .build();

      ClaimValidationResult result =
          validationService.validateClaim(
              legalHelpClaimUnderValidation(),
              Set.of(ClaimValidatorCode.CLAIM_DUPLICATE_VALIDATOR),
              List.of(sibling));

      assertThat(result.getIssues())
          .extracting(i -> i.getCode())
          .contains(ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_SAME_SUBMISSION.name());
    }

    @Test
    @DisplayName("Mediation: no registered strategy ⇒ no duplicate check, no provider call")
    void mediationIsInert() {
      Claim mediationClaim =
          legalHelpClaimUnderValidation().toBuilder().areaOfLaw(AreaOfLaw.MEDIATION).build();

      ClaimValidationResult result = validate(mediationClaim);

      assertThat(result.getIssues())
          .extracting(i -> i.getCode())
          .doesNotContain(
              ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_SAME_SUBMISSION.name(),
              ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION.name());
      verify(claimsDataProvider, never())
          .getClaims(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
    }
  }

  @Nested
  @DisplayName("Single-claim same-submission (provider-backed)")
  class SingleClaimSameSubmissionTargets {

    @Test
    @DisplayName("VALID candidate, different id, SAME submission ⇒ SAME_SUBMISSION")
    void sameSubmissionDetectedViaProvider() {
      stubProvider(candidate(SUBMISSION_S1, ClaimStatus.VALID));

      ClaimValidationResult result = validate(legalHelpClaimUnderValidation());

      assertThat(result.getIssues())
          .extracting(i -> i.getCode())
          .contains(ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_SAME_SUBMISSION.name());
    }

    @Test
    @DisplayName("Candidate with the SAME id as the claim ⇒ no issue (self-exclusion by id)")
    void selfExclusionById() {
      stubProvider(
          candidate(
              CLAIM_UNDER_VALIDATION_ID, SUBMISSION_S1, FEE_CODE, UFN, UCN, ClaimStatus.VALID));

      ClaimValidationResult result = validate(legalHelpClaimUnderValidation());

      assertThat(result.getIssues())
          .extracting(i -> i.getCode())
          .doesNotContain(
              ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_SAME_SUBMISSION.name());
    }

    @Test
    @DisplayName("Same-submission candidate with VOID status ⇒ no issue")
    void voidCandidateIgnored() {
      stubProvider(candidate(SUBMISSION_S1, ClaimStatus.INVALID));

      ClaimValidationResult result = validate(legalHelpClaimUnderValidation());

      assertThat(result.getIssues())
          .extracting(i -> i.getCode())
          .doesNotContain(
              ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_SAME_SUBMISSION.name());
    }

    @Test
    @DisplayName("Same-submission candidate differing on a key (UFN) ⇒ no issue")
    void keyMismatchIgnored() {
      stubProvider(
          candidate(
              UUID.randomUUID(), SUBMISSION_S1, FEE_CODE, "020225/002", UCN, ClaimStatus.VALID));

      ClaimValidationResult result = validate(legalHelpClaimUnderValidation());

      assertThat(result.getIssues())
          .extracting(ValidationIssue::getCode)
          .doesNotContain(
              ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_SAME_SUBMISSION.name());
    }

    @Test
    @DisplayName("Same-submission lookup is scoped to own submission and valid statuses")
    void sameSubmissionQueryIsScopedToOwnSubmission() {
      stubProvider(candidate(SUBMISSION_S1, ClaimStatus.VALID));

      validate(legalHelpClaimUnderValidation());

      // The same-submission lookup queries the provider scoped to the claim's own submission id
      // and only VALID / READY_TO_PROCESS claim statuses.
      verify(claimsDataProvider)
          .getClaims(
              eq(OFFICE),
              eq(SUBMISSION_S1.toString()),
              any(),
              eq(FEE_CODE),
              eq(UFN),
              eq(UCN),
              any(),
              eq(List.of(ClaimStatus.READY_TO_PROCESS, ClaimStatus.VALID)),
              any(),
              any(),
              any());
    }

    @Test
    @DisplayName("Provider failure ⇒ no crash, surfaces TECHNICAL_ERROR, no duplicate stacking")
    void providerFailureIsHandledGracefully() {
      when(claimsDataProvider.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenThrow(new RuntimeException("Data Claims API unavailable"));

      ClaimValidationResult result = validate(legalHelpClaimUnderValidation());

      assertThat(result.getIssues())
          .extracting(ValidationIssue::getCode)
          .contains(ClaimValidationError.TECHNICAL_ERROR_DATA_CLAIMS_API.name())
          .doesNotContain(
              ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_SAME_SUBMISSION.name(),
              ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION.name());

      // Both the same-submission and previous-submission lookups fail closed, but the shared
      // technical error is de-duplicated by the dispatcher and reported only once.
      assertThat(result.getIssues())
          .extracting(ValidationIssue::getCode)
          .filteredOn(ClaimValidationError.TECHNICAL_ERROR_DATA_CLAIMS_API.name()::equals)
          .hasSize(1);
    }
  }
}
