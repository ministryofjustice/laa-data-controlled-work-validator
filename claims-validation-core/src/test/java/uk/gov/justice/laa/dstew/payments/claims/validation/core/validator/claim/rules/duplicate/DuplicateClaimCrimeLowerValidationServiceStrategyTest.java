package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResponse;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResultSet;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.FeeCalculationType;

@ExtendWith(MockitoExtension.class)
class DuplicateClaimCrimeLowerValidationServiceStrategyTest extends AbstractDuplicateClaimValidatorStrategy {

  private static final String OFFICE_CODE = "officeCode";
  
  @Mock ClaimsDataProvider dataClaimsRestClient;

  @InjectMocks DuplicateClaimCrimeLowerValidationServiceStrategy duplicateClaimValidationService;

  @Nested
  @DisplayName("validateDuplicateClaims")
  class ValidateDuplicateClaimsTests {

    @Test
    @DisplayName("Crime Lower claims - successful validation does not update context")
    void crimeLowerClaimSuccessfulValidation() {
      // Given
      Claim claim1 = createClaim("claimId1", OFFICE_CODE, null, "feeCode1", "ufn1", null,
          ClaimStatus.READY_TO_PROCESS);

      Claim claim2 = createClaim("claimId2", OFFICE_CODE, null, "feeCode2", "ufn2", null,
          ClaimStatus.READY_TO_PROCESS);

      List<Claim> submissionClaims = List.of(claim1, claim2);

      when(dataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenReturn(new ClaimResultSet());

      // When
      List<ValidationIssue> validationIssues = duplicateClaimValidationService.validateDuplicateClaims(
          claim1, submissionClaims, OFFICE_CODE, FeeCalculationType.FIXED.toString());

      // Then
      assertThat(validationIssues).isEmpty();
    }

    @Test
    @DisplayName(
        "Crime Lower claims - different fee code but the same unique file number passes validation")
    void crimeLowerClaimDifferentFeeCodeButSameUfnPassesValidation() {
      // Given
      Claim claim1 = createClaim("claimId1", OFFICE_CODE, null, "feeCode1", "ufn", null,
          ClaimStatus.READY_TO_PROCESS);
      Claim claim2 = createClaim("claimId2", OFFICE_CODE, null, "feeCode2", "ufn", null,
          ClaimStatus.READY_TO_PROCESS);

      List<Claim> submissionClaims = List.of(claim1, claim2);

      when(dataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenReturn(new ClaimResultSet());

      

      // When
      List<ValidationIssue> validationIssues = duplicateClaimValidationService.validateDuplicateClaims(
          claim1, submissionClaims, OFFICE_CODE, FeeCalculationType.FIXED.toString());

      // Then
      assertThat(validationIssues).isEmpty();
    }

    @Test
    @DisplayName(
        "Crime Lower claims - different unique file number but the same fee code passes validation")
    void crimeLowerClaimDifferentUfnButSameFeeCodePassesValidation() {
      // Given
      Claim claim1 = createClaim("claimId1", OFFICE_CODE, null, "feeCode", "ufn1", null,
          ClaimStatus.READY_TO_PROCESS);
      Claim claim2 = createClaim("claimId2", OFFICE_CODE, null, "feeCode", "ufn2", null,
          ClaimStatus.READY_TO_PROCESS);

      List<Claim> submissionClaims = List.of(claim1, claim2);

      when(dataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenReturn(new ClaimResultSet());

      // When
      List<ValidationIssue> validationIssues = duplicateClaimValidationService.validateDuplicateClaims(
          claim1, submissionClaims, OFFICE_CODE, FeeCalculationType.FIXED.toString());

      // Then
      assertThat(validationIssues).isEmpty();
    }

    @Test
    @DisplayName(
        "Crime Lower claims - duplicate claims in submission results in claim error added to "
            + "validation "
            + "context")
    void crimeLowerClaimDuplicateInSubmissionResultsInClaimErrorAddedToContext() {
      // Given
      Claim claim1 = createClaim("claimId1", OFFICE_CODE, null, "feeCode", "ufn", null,
          ClaimStatus.READY_TO_PROCESS);

      Claim claim2 = createClaim("claimId2", OFFICE_CODE, null, "feeCode", "ufn", null,
          ClaimStatus.READY_TO_PROCESS);

      List<Claim> submissionClaims = List.of(claim1, claim2);

      // note: previous behaviour set claimResultSet.content(...) with ClaimResponse objects; the
      // submissionClaims here represent core Claim objects passed to the validator and are not
      // used as the mocked response below.
      when(dataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenReturn(new ClaimResultSet());

      // When
      List<ValidationIssue> validationIssues = duplicateClaimValidationService.validateDuplicateClaims(
          claim1, submissionClaims, OFFICE_CODE, FeeCalculationType.FIXED.toString());

      // Then
      assertThat(validationIssues).isNotEmpty();
      assertThat(validationIssues)
              .extracting(ValidationIssue::getMessage)
              .containsExactly(
                      ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_SAME_SUBMISSION
                              .getDisplayMessage());
    }

    @Test
    @DisplayName("Crime Lower claims - duplicate validation ignores invalid claims")
    void crimeLowerClaimDuplicateValidationIgnoresInvalidClaims() {
      // Given
      Claim claim1 = createClaim("claimId1", OFFICE_CODE, "submissionId", "feeCode", "ufn",
          null, ClaimStatus.READY_TO_PROCESS);

      Claim claim2 = createClaim("claimId2", OFFICE_CODE, "submissionId", "feeCode", "ufn",
          null, ClaimStatus.INVALID);

      List<Claim> submissionClaims = List.of(claim1, claim2);

      // The mocked previous submissions should be ClaimResponse objects. Build equivalent
      // ClaimResponse list for the mocked response.
      ClaimResultSet claimResultSet = new ClaimResultSet();
      claimResultSet.content(List.of(
          createClaimResponse("claimId1", "submissionId", "feeCode", "ufn", null, ClaimStatus.READY_TO_PROCESS),
          createClaimResponse("claimId2", "submissionId", "feeCode", "ufn", null, ClaimStatus.INVALID)
      ));

      when(dataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenReturn(claimResultSet);

      // When
      List<ValidationIssue> validationIssues = duplicateClaimValidationService.validateDuplicateClaims(
          claim1, submissionClaims, OFFICE_CODE, FeeCalculationType.FIXED.toString());

      // Then
      assertThat(validationIssues).isEmpty();
    }

    @Test
    @DisplayName(
        "Crime Lower claims - duplicate claims in another submission results in claim error added"
            + " to "
            + "validation context")
    void crimeLowerClaimDuplicateInAnotherSubmissionResultsInClaimErrorAddedToContext() {
      // Given
      Claim claim1 = createClaim("claimId1", OFFICE_CODE, "submissionId", "feeCode", "ufn",
          null, ClaimStatus.READY_TO_PROCESS);

      ClaimResponse otherClaim = createClaimResponse("claimId2", "submissionId2", "feeCode",
          "ufn", null, ClaimStatus.VALID);

      List<Claim> submissionClaims = List.of(claim1);

      ClaimResultSet claimResultSet = new ClaimResultSet();
      claimResultSet.content(List.of(otherClaim));

      when(dataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenReturn(claimResultSet);

      // When
      List<ValidationIssue> validationIssues = duplicateClaimValidationService.validateDuplicateClaims(
          claim1, submissionClaims, OFFICE_CODE, FeeCalculationType.FIXED.toString());

      // Then
      assertThat(validationIssues).isNotEmpty();
      assertThat(validationIssues)
              .extracting(ValidationIssue::getMessage)
              .containsExactly(
                      ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION
                              .getDisplayMessage());
    }

    @Test
    @DisplayName(
        "Crime Lower claims - single-claim path detects a same-submission duplicate that shares fee "
            + "code + UFN but has a different UCN (UCN is not part of the Crime Lower key)")
    void crimeLowerSingleClaimDetectsSameSubmissionDuplicateWithDifferentClient() {
      // Given: no in-memory siblings, so the strategy must query the provider for other claims in
      // the claim's own submission. The candidate shares fee code + UFN but has a DIFFERENT UCN.
      Claim claim1 = createClaim("claimId1", OFFICE_CODE, "submissionId", "feeCode", "ufn",
          "ucnA", ClaimStatus.READY_TO_PROCESS);

      ClaimResponse sameSubmissionDuplicate = createClaimResponse(
          "claimId2", "submissionId", "feeCode", "ufn", "ucnB", ClaimStatus.VALID);

      ClaimResultSet claimResultSet = new ClaimResultSet();
      claimResultSet.content(List.of(sameSubmissionDuplicate));

      when(dataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenReturn(claimResultSet);

      // When
      List<ValidationIssue> validationIssues = duplicateClaimValidationService.validateDuplicateClaims(
          claim1, List.of(), OFFICE_CODE, FeeCalculationType.FIXED.toString());

      // Then: the different UCN must not prevent detection.
      assertThat(validationIssues)
          .extracting(ValidationIssue::getMessage)
          .contains(
              ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_SAME_SUBMISSION
                  .getDisplayMessage());

      // And: Crime Lower must never constrain the provider query by UCN (both the same-submission
      // and previous-submission lookups pass null so genuine duplicates are not filtered out).
      verify(dataClaimsRestClient, times(2))
          .getClaims(any(), any(), any(), any(), any(), isNull(), any(), any(), any(), any(), any());
    }

    @Test
    @DisplayName("Crime Lower claims - previous-submission lookup is not constrained by UCN")
    void crimeLowerPreviousSubmissionLookupDoesNotFilterByClient() {      Claim claim1 = createClaim("claimId1", OFFICE_CODE, "submissionId", "feeCode", "ufn",
          "ucnA", ClaimStatus.READY_TO_PROCESS);

      when(dataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenReturn(new ClaimResultSet());

      // submissionClaims non-empty, so only the previous-submission lookup queries the provider.
      duplicateClaimValidationService.validateDuplicateClaims(
          claim1, List.of(claim1), OFFICE_CODE, FeeCalculationType.FIXED.toString());

      verify(dataClaimsRestClient)
          .getClaims(any(), any(), any(), any(), any(), isNull(), any(), any(), any(), any(), any());
    }

    @Test
    @DisplayName(
        "Crime Lower claims - single-claim path fails closed: provider failure raises a technical "
            + "error and does not pass the claim as a non-duplicate")
    void crimeLowerSingleClaimFailsClosedWhenProviderUnavailable() {
      Claim claim1 = createClaim("claimId1", OFFICE_CODE, "submissionId", "feeCode", "ufn",
          "ucnA", ClaimStatus.READY_TO_PROCESS);

      when(dataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenThrow(new RuntimeException("Data Claims API unavailable"));

      // Single-claim path (no in-memory siblings): the same-submission lookup queries the provider.
      List<ValidationIssue> validationIssues = duplicateClaimValidationService.validateDuplicateClaims(
          claim1, List.of(), OFFICE_CODE, FeeCalculationType.FIXED.toString());

      assertThat(validationIssues)
          .extracting(ValidationIssue::getMessage)
          .contains(ClaimValidationError.TECHNICAL_ERROR_DATA_CLAIMS_API.getDisplayMessage())
          .doesNotContain(
              ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_SAME_SUBMISSION.getDisplayMessage(),
              ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION
                  .getDisplayMessage());
    }

    @Test
    @DisplayName("Crime Lower claims - does not reprocess submission claims")
    void crimeLowerClaimDuplicateDoesNotReprocessSubmissionClaims() {      // Given
      Claim claim1 = createClaim("claimId1", OFFICE_CODE, "submissionId", "feeCode", "ufn",
          null, ClaimStatus.READY_TO_PROCESS);
      Claim claim2 = createClaim("claimId2", OFFICE_CODE, "submissionId", "feeCode", "ufn",
          null, ClaimStatus.READY_TO_PROCESS);
      ClaimResponse otherClaim = createClaimResponse("claimId2", "submissionId", "feeCode", "ufn",
          null, ClaimStatus.READY_TO_PROCESS);

      List<Claim> submissionClaims = List.of(claim1, claim2);

      ClaimResultSet claimResultSet = new ClaimResultSet();
      claimResultSet.content(List.of(otherClaim));

      when(dataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenReturn(claimResultSet);

      // When
      List<ValidationIssue> validationIssues = duplicateClaimValidationService.validateDuplicateClaims(
          claim1, submissionClaims, OFFICE_CODE, FeeCalculationType.FIXED.toString());

      // Then
      assertThat(validationIssues).isNotEmpty();
      assertThat(validationIssues)
              .extracting(ValidationIssue::getMessage)
              .containsExactly(
                      ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_SAME_SUBMISSION
                              .getDisplayMessage());
    }

    @Nested
    @DisplayName("Ignore PROD Fee Code")
    class IgnoreProdFeeCode {

      @Test
      @DisplayName("Crime lower claims - Fee Code PROD success validation")
      void crimeLowerClaimDuplicateWithProdFeeCodeSuccess() {
        // Given
        Claim claim1 = createClaim("claimId1", OFFICE_CODE, null, "PROD", null, null,
            ClaimStatus.READY_TO_PROCESS, null, null, "caseConcludedDate1");

        Claim claim2 = createClaim("claimId2", OFFICE_CODE, null, "PROD", null, null,
            ClaimStatus.READY_TO_PROCESS, null, null, "caseConcludedDate2");

        List<Claim> submissionClaims = List.of(claim1, claim2);

        // When
        List<ValidationIssue> validationIssues = duplicateClaimValidationService.validateDuplicateClaims(
            claim1, submissionClaims, OFFICE_CODE, FeeCalculationType.FIXED.toString());

        // Then
        assertThat(validationIssues).isEmpty();
        verify(dataClaimsRestClient, times(0))
            .getClaims(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
      }

      @Test
      @DisplayName(
          "Crime lower claims - Fee Code PROD passes validation, duplicate in same submission")
      void crimeLowerClaimDuplicateWithProdFeeCodeDuplicateInSameSubmission() {
        // Given
        Claim claim1 = createClaim("claimId1", OFFICE_CODE, null, "PROD", null, null,
            ClaimStatus.READY_TO_PROCESS, null, null, "caseConcludedDate1");
        Claim claim2 = createClaim("claimId2", OFFICE_CODE, null, "PROD", null, null,
            ClaimStatus.READY_TO_PROCESS, null, null, "caseConcludedDate1");

        List<Claim> submissionClaims = List.of(claim1, claim2);

        

        // When
        List<ValidationIssue> validationIssues = duplicateClaimValidationService.validateDuplicateClaims(
            claim1, submissionClaims, OFFICE_CODE, FeeCalculationType.FIXED.toString());

        // Then
        assertThat(validationIssues).isEmpty();
        verify(dataClaimsRestClient, times(0))
            .getClaims(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
      }

      @Test
      @DisplayName(
          "Crime lower claims - Fee Code PROD passes validation, duplicate in another submission")
      void crimeLowerClaimDuplicateWithProdFeeCodeDuplicateInAnotherSubmission() {
        // Given
        Claim claim1 = createClaim("claimId1", OFFICE_CODE, "submissionId", "PROD", null,
            null, ClaimStatus.READY_TO_PROCESS, null, null, "caseConcludedDate1");

        ClaimResponse otherClaim = createClaimResponse("claimId2", "submissionId2", "PROD",
            null, null, ClaimStatus.VALID, null, null, "caseConcludedDate1");

        List<Claim> submissionClaims = List.of(claim1);

        ClaimResultSet claimResultSet = new ClaimResultSet();
        claimResultSet.content(List.of(otherClaim));

        // When
        List<ValidationIssue> validationIssues = duplicateClaimValidationService.validateDuplicateClaims(
            claim1, submissionClaims, OFFICE_CODE, FeeCalculationType.FIXED.toString());

        // Then
        // PROD fee code should skip duplicate checks, so no validation issues are expected
        assertThat(validationIssues).isEmpty();
        verify(dataClaimsRestClient, times(0))
            .getClaims(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
      }
    }
  }
}
