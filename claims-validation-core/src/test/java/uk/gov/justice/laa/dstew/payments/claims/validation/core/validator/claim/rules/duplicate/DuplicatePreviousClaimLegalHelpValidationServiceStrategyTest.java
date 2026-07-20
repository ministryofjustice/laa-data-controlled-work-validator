package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.FeeCalculationType;

@ExtendWith(MockitoExtension.class)
class DuplicatePreviousClaimLegalHelpValidationServiceStrategyTest
    extends AbstractDuplicateClaimValidatorStrategy {

  @Mock private ClaimsDataProvider mockDataClaimsRestClient;

  private DuplicatePreviousClaimLegalHelpValidationServiceStrategy
      duplicateClaimLegalHelpValidation;

  @BeforeEach
  void beforeEach() {
    duplicateClaimLegalHelpValidation =
        new DuplicatePreviousClaimLegalHelpValidationServiceStrategy(mockDataClaimsRestClient);
  }

  @Nested
  class ValidClaim {

    @DisplayName("Validation error: duplicate disbursement claim on the same submission")
    @Test
    void whenDuplicateDisbursementClaim() {
      var claimTobeProcessed =
          createClaim(
              "claimId1",
              "2Q286D",
              "submissionId1",
              "DISB01",
              "070722/001",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var otherClaim =
          createClaim(
              "claimId2",
              "2Q286D",
              "submissionId1",
              "DISB01",
              "070722/001",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var submissionClaims = List.of(claimTobeProcessed, otherClaim);

      List<ValidationIssue> strategyIssues = duplicateClaimLegalHelpValidation.validateDuplicateClaims(
          claimTobeProcessed,
          submissionClaims,
          "2Q286D",
          FeeCalculationType.FIXED.toString());

      assertThat(strategyIssues).isNotEmpty().size().isEqualTo(1);

      assertThat(strategyIssues).extracting(ValidationIssue::getMessage)
          .containsExactly(
              ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_SAME_SUBMISSION
                  .getDisplayMessage());
    }

    @DisplayName(
        "No validation error: sibling in the same submission shares Office, UFN and Fee Code but a"
            + " different UCN")
    @Test
    void whenSiblingHasDifferentClient() {
      var claimTobeProcessed =
          createClaim(
              "claimId1",
              "2Q286D",
              "submissionId1",
              "CIV123",
              "070722/001",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var otherClaim =
          createClaim(
              "claimId2",
              "2Q286D",
              "submissionId1",
              "CIV123",
              "070722/001",
              "CLI999",
              ClaimStatus.READY_TO_PROCESS);

      var submissionClaims = List.of(claimTobeProcessed, otherClaim);

      List<ValidationIssue> strategyIssues =
          duplicateClaimLegalHelpValidation.validateDuplicateClaims(
              claimTobeProcessed, submissionClaims, "2Q286D", FeeCalculationType.FIXED.toString());

      assertThat(strategyIssues).isEmpty();
    }
  }

  @Nested
  class ProviderFailure {

    @DisplayName(
        "Fails closed: on the single-claim path a provider failure raises a technical error rather "
            + "than passing the claim as a non-duplicate")
    @Test
    void whenProviderUnavailableOnSingleClaimPath() {
      var claimTobeProcessed =
          createClaim(
              "claimId1",
              "2Q286D",
              "submissionId1",
              "CIV123",
              "070722/001",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      when(mockDataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenThrow(new RuntimeException("Data Claims API unavailable"));

      // Single-claim path: no in-memory siblings, so the same-submission lookup queries the provider.
      List<ValidationIssue> strategyIssues =
          duplicateClaimLegalHelpValidation.validateDuplicateClaims(
              claimTobeProcessed, List.of(), "2Q286D", FeeCalculationType.FIXED.toString());

      assertThat(strategyIssues)
          .extracting(ValidationIssue::getMessage)
          .containsExactly(
              ClaimValidationError.TECHNICAL_ERROR_DATA_CLAIMS_API.getDisplayMessage());
    }
  }

  @Nested
  class InvalidClaim {

    @DisplayName(
        "Validation error: When there an exist a legal help claim with the same Office, UFN, Fee Code,"
            + " and UCN in the same submission")
    @Test
    void whenExistingClaim() {
      var claimTobeProcessed =
          createClaim(
              "claimId1",
              "2Q286D",
              "submissionId1",
              "CIV123",
              "070722/001",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var otherClaim =
          createClaim(
              "claimId2",
              "2Q286D",
              "submissionId1",
              "CIV123",
              "070722/001",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var otherClaim1 =
          createClaim(
              "claimId3",
              "2Q286D",
              "submissionId1",
              "CIV123",
              "070722/001",
              "CLI001",
              ClaimStatus.VALID);

      var submissionClaims = List.of(claimTobeProcessed, otherClaim, otherClaim1);

      List<ValidationIssue> strategyIssues = duplicateClaimLegalHelpValidation.validateDuplicateClaims(
          claimTobeProcessed,
          submissionClaims,
          "2Q286D",
          FeeCalculationType.FIXED.toString());

      assertThat(strategyIssues).isNotEmpty();

      assertThat(strategyIssues).extracting(ValidationIssue::getMessage)
          .containsExactly(
              ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_SAME_SUBMISSION
                  .getDisplayMessage());
    }
  }
}
