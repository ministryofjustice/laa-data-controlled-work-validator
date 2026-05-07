package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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

  @Mock ClaimsDataProvider dataClaimsRestClient;

  @InjectMocks DuplicateClaimCrimeLowerValidationServiceStrategy duplicateClaimValidationService;

  @Nested
  @DisplayName("validateDuplicateClaims")
  class ValidateDuplicateClaimsTests {

    @Test
    @DisplayName("Crime Lower claims - successful validation does not update context")
    void crimeLowerClaimSuccessfulValidation() {
      // Given
      Claim claim1 = createClaim("claimId1", null, "feeCode1", "ufn1", null,
          ClaimStatus.READY_TO_PROCESS);

      Claim claim2 = createClaim("claimId2", null, "feeCode2", "ufn2", null,
          ClaimStatus.READY_TO_PROCESS);

      List<Claim> submissionClaims = List.of(claim1, claim2);

      when(dataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenReturn(new ClaimResultSet());

      // When
      List<ValidationIssue> validationIssues = duplicateClaimValidationService.validateDuplicateClaims(
          claim1, submissionClaims, "officeCode", FeeCalculationType.FIXED.toString());

      // Then
      assertThat(validationIssues).isEmpty();
    }

    @Test
    @DisplayName(
        "Crime Lower claims - different fee code but the same unique file number passes validation")
    void crimeLowerClaimDifferentFeeCodeButSameUfnPassesValidation() {
      // Given
      Claim claim1 = createClaim("claimId1", null, "feeCode1", "ufn", null,
          ClaimStatus.READY_TO_PROCESS);
      Claim claim2 = createClaim("claimId2", null, "feeCode2", "ufn", null,
          ClaimStatus.READY_TO_PROCESS);

      List<Claim> submissionClaims = List.of(claim1, claim2);

      when(dataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenReturn(new ClaimResultSet());

      

      // When
      List<ValidationIssue> validationIssues = duplicateClaimValidationService.validateDuplicateClaims(
          claim1, submissionClaims, "officeCode", FeeCalculationType.FIXED.toString());

      // Then
      assertThat(validationIssues).isEmpty();
    }

    @Test
    @DisplayName(
        "Crime Lower claims - different unique file number but the same fee code passes validation")
    void crimeLowerClaimDifferentUfnButSameFeeCodePassesValidation() {
      // Given
      Claim claim1 = createClaim("claimId1", null, "feeCode", "ufn1", null,
          ClaimStatus.READY_TO_PROCESS);
      Claim claim2 = createClaim("claimId2", null, "feeCode", "ufn2", null,
          ClaimStatus.READY_TO_PROCESS);

      List<Claim> submissionClaims = List.of(claim1, claim2);

      when(dataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenReturn(new ClaimResultSet());

      // When
      List<ValidationIssue> validationIssues = duplicateClaimValidationService.validateDuplicateClaims(
          claim1, submissionClaims, "officeCode", FeeCalculationType.FIXED.toString());

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
      Claim claim1 = createClaim("claimId1", null, "feeCode", "ufn", null,
          ClaimStatus.READY_TO_PROCESS);

      Claim claim2 = createClaim("claimId2", null, "feeCode", "ufn", null,
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
          claim1, submissionClaims, "officeCode", FeeCalculationType.FIXED.toString());

      // Then
      assertThat(validationIssues).isNotEmpty();
      assertThat(validationIssues)
              .extracting(ValidationIssue::getMessage)
              .containsExactly(
                      ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_EXISTING_SUBMISSION
                              .getDisplayMessage());
    }

    @Test
    @DisplayName("Crime Lower claims - duplicate validation ignores invalid claims")
    void crimeLowerClaimDuplicateValidationIgnoresInvalidClaims() {
      // Given
      Claim claim1 = createClaim("claimId1", "submissionId", "feeCode", "ufn",
          null, ClaimStatus.READY_TO_PROCESS);

      Claim claim2 = createClaim("claimId2", "submissionId", "feeCode", "ufn",
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
          claim1, submissionClaims, "officeCode", FeeCalculationType.FIXED.toString());

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
      Claim claim1 = createClaim("claimId1", "submissionId", "feeCode", "ufn",
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
          claim1, submissionClaims, "officeCode", FeeCalculationType.FIXED.toString());

      // Then
      assertThat(validationIssues).isNotEmpty();
      assertThat(validationIssues)
              .extracting(ValidationIssue::getMessage)
              .containsExactly(
                      ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION
                              .getDisplayMessage());
    }

    @Test
    @DisplayName("Crime Lower claims - does not reprocess submission claims")
    void crimeLowerClaimDuplicateDoesNotReprocessSubmissionClaims() {
      // Given
      Claim claim1 = createClaim("claimId1", "submissionId", "feeCode", "ufn",
          null, ClaimStatus.READY_TO_PROCESS);
      Claim claim2 = createClaim("claimId2", "submissionId", "feeCode", "ufn",
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
          claim1, submissionClaims, "officeCode", FeeCalculationType.FIXED.toString());

      // Then
      assertThat(validationIssues).isNotEmpty();
      assertThat(validationIssues)
              .extracting(ValidationIssue::getMessage)
              .containsExactly(
                      ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_EXISTING_SUBMISSION
                              .getDisplayMessage());
    }

    @Nested
    @DisplayName("Ignore PROD Fee Code")
    class IgnoreProdFeeCode {

      @Test
      @DisplayName("Crime lower claims - Fee Code PROD success validation")
      void crimeLowerClaimDuplicateWithProdFeeCodeSuccess() {
        // Given
        Claim claim1 = createClaim("claimId1", null, "PROD", null, null,
            ClaimStatus.READY_TO_PROCESS, null, null, "caseConcludedDate1");

        Claim claim2 = createClaim("claimId2", null, "PROD", null, null,
            ClaimStatus.READY_TO_PROCESS, null, null, "caseConcludedDate2");

        List<Claim> submissionClaims = List.of(claim1, claim2);

        // When
        List<ValidationIssue> validationIssues = duplicateClaimValidationService.validateDuplicateClaims(
            claim1, submissionClaims, "officeCode", FeeCalculationType.FIXED.toString());

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
        Claim claim1 = createClaim("claimId1", null, "PROD", null, null,
            ClaimStatus.READY_TO_PROCESS, null, null, "caseConcludedDate1");
        Claim claim2 = createClaim("claimId2", null, "PROD", null, null,
            ClaimStatus.READY_TO_PROCESS, null, null, "caseConcludedDate1");

        List<Claim> submissionClaims = List.of(claim1, claim2);

        

        // When
        List<ValidationIssue> validationIssues = duplicateClaimValidationService.validateDuplicateClaims(
            claim1, submissionClaims, "officeCode", FeeCalculationType.FIXED.toString());

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
        Claim claim1 = createClaim("claimId1", "submissionId", "PROD", null,
            null, ClaimStatus.READY_TO_PROCESS, null, null, "caseConcludedDate1");

        ClaimResponse otherClaim = createClaimResponse("claimId2", "submissionId2", "PROD",
            null, null, ClaimStatus.VALID, null, null, "caseConcludedDate1");

        List<Claim> submissionClaims = List.of(claim1);

        ClaimResultSet claimResultSet = new ClaimResultSet();
        claimResultSet.content(List.of(otherClaim));

        // When
        List<ValidationIssue> validationIssues = duplicateClaimValidationService.validateDuplicateClaims(
            claim1, submissionClaims, "officeCode", FeeCalculationType.FIXED.toString());

        // Then
        // PROD fee code should skip duplicate checks, so no validation issues are expected
        assertThat(validationIssues).isEmpty();
        verify(dataClaimsRestClient, times(0))
            .getClaims(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());
      }
    }
  }
}
