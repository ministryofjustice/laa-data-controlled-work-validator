package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.FeeSchemeClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResultSet;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.FeeCalculationType;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionStatus;

@ExtendWith(MockitoExtension.class)
class DuplicateClaimLegalHelpValidationServiceStrategyTest
    extends AbstractDuplicateClaimValidatorStrategy {

  private static final String DISBURSEMENT_FEE_TYPE = FeeCalculationType.DISB_ONLY.toString();

  @Mock private ClaimsDataProvider mockDataClaimsRestClient;

  @Mock private FeeSchemeClient mockFeeSchemePlatformRestClient;

  @InjectMocks
  private DuplicateClaimLegalHelpValidationServiceStrategy duplicateClaimLegalHelpValidation;

  @Captor private ArgumentCaptor<String> officeCodeArgumentCaptor;

  @Captor private ArgumentCaptor<String> feeCodeArgumentCaptor;

  @Captor private ArgumentCaptor<String> uniqueFileNumberArgumentCaptor;

  @Captor private ArgumentCaptor<String> uniqueClientNumberArgumentCaptor;

  @Captor private ArgumentCaptor<List<ClaimStatus>> claimStatusArgumentCaptor;

  @Captor private ArgumentCaptor<List<SubmissionStatus>> submissionStatusArgumentCaptor;

  @Nested
  class ValidClaim {

    @DisplayName(
        "No validation error: When there is no existing legal help claim with the same Office, UFN, "
            + "Fee Code, and UCN in the same submission or previous submission")
    @Test
    void whenNoExistingClaim() {
      var claimTobeProcessed =
          createClaim(
              "claimId1",
              "submissionId1",
              "CIV123",
              "070722/001",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var otherClaim =
          createClaim(
              "claimId2",
              "submissionId1",
              "CIV123",
              "070722/002",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var submissionClaims = List.of(claimTobeProcessed, otherClaim);

      when(mockDataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenReturn(new ClaimResultSet());

      List<ValidationIssue> validationIssues = duplicateClaimLegalHelpValidation.validateDuplicateClaims(
          claimTobeProcessed, submissionClaims, "2Q286D", "feeType");

      verify(mockDataClaimsRestClient)
          .getClaims(
              officeCodeArgumentCaptor.capture(),
              any(),
              submissionStatusArgumentCaptor.capture(),
              feeCodeArgumentCaptor.capture(),
              uniqueFileNumberArgumentCaptor.capture(),
              uniqueClientNumberArgumentCaptor.capture(),
              any(),
              claimStatusArgumentCaptor.capture(),
              any(),
              any(),
              any());

      Assertions.assertEquals("2Q286D", officeCodeArgumentCaptor.getValue());
      Assertions.assertEquals("CIV123", feeCodeArgumentCaptor.getValue());
      Assertions.assertEquals("070722/001", uniqueFileNumberArgumentCaptor.getValue());
      Assertions.assertEquals("CLI001", uniqueClientNumberArgumentCaptor.getValue());
      Assertions.assertEquals(
          List.of(ClaimStatus.READY_TO_PROCESS, ClaimStatus.VALID),
          claimStatusArgumentCaptor.getValue());
      Assertions.assertEquals(
          List.of(
              SubmissionStatus.CREATED,
              SubmissionStatus.VALIDATION_IN_PROGRESS,
              SubmissionStatus.READY_FOR_VALIDATION,
              SubmissionStatus.VALIDATION_SUCCEEDED),
          submissionStatusArgumentCaptor.getValue());

      assertThat(validationIssues).isEmpty();
    }

    @DisplayName(
        "No validation error: When current claims is of disbursement type should check again "
            + "previous submissions")
    @Test
    void whenCurrentClaimIsDisbursement() {
      var claimTobeProcessed =
          createClaim(
              "claimId1",
              "submissionId1",
              "DISB01",
              "070722/001",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var otherClaim =
          createClaim(
              "claimId2",
              "submissionId1",
              "CIV123",
              "070722/002",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var submissionClaims = List.of(claimTobeProcessed, otherClaim);

      List<ValidationIssue> validationIssues = duplicateClaimLegalHelpValidation.validateDuplicateClaims(
          claimTobeProcessed, submissionClaims, "2Q286D", DISBURSEMENT_FEE_TYPE);

      verify(mockDataClaimsRestClient, times(0))
          .getClaims(any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any());

      assertThat(validationIssues).isEmpty();
    }

    @DisplayName(
        "No validation error: when  same Office, UFN, Fee Code exists but for different client "
            + "(UCN differs)")
    @Test
    void whenDifferentClient() {
      var claimTobeProcessed =
          createClaim(
              "claimId1",
              "submissionId1",
              "CIV123",
              "070722/001",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var otherClaim =
          createClaim(
              "claimId2",
              "submissionId1",
              "CIV123",
              "070722/001",
              "CLI002",
              ClaimStatus.READY_TO_PROCESS);
      var submissionClaims = List.of(claimTobeProcessed, otherClaim);

      when(mockDataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenReturn(new ClaimResultSet());

      List<ValidationIssue> validationIssues = duplicateClaimLegalHelpValidation.validateDuplicateClaims(
          claimTobeProcessed, submissionClaims, "2Q286D", "feeType");

      assertThat(validationIssues).isEmpty();
    }

    @DisplayName(
        "No validation error: when there exists a claim with same UFN and UCN but different fee "
            + "code in same submission")
    @Test
    void whenExistingClaimInPreviousSubmission() {
      var claimTobeProcessed =
          createClaim(
              "claimId1",
              "submissionId1",
              "CIV123",
              "070722/001",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);
      var otherClaim =
          createClaim(
              "claimId2", "submissionId1", "CIV456", "070722/001", "CLI001", ClaimStatus.VALID);
      var submissionClaims = List.of(claimTobeProcessed, otherClaim);

      when(mockDataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenReturn(new ClaimResultSet());

      List<ValidationIssue> validationIssues = duplicateClaimLegalHelpValidation.validateDuplicateClaims(
          claimTobeProcessed, submissionClaims, "2Q286D", "feeType");

      assertThat(validationIssues).isEmpty();
    }

    @DisplayName("No Validation error: when same UFN with different fee code and UCN")
    @Test
    void whenDifferentFeeCodeOfficeUcn() {
      var claimTobeProcessed =
          createClaim(
              "claimId1",
              "submissionId1",
              "CIV123",
              "070722/001",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);
      var otherClaim =
          createClaim(
              "claimId2", "submissionId1", "CIV456", "070722/001", "CLI002", ClaimStatus.VALID);
      var submissionClaims = List.of(claimTobeProcessed, otherClaim);

      when(mockDataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenReturn(new ClaimResultSet());

      List<ValidationIssue> validationIssues = duplicateClaimLegalHelpValidation.validateDuplicateClaims(
          claimTobeProcessed, submissionClaims, "2Q286D", "feeType");

      assertThat(validationIssues).isEmpty();
    }
  }

  @Nested
  class InvalidClaim {

    @DisplayName(
        "Validation error: When there is an existing legal help claim with the same Office, UFN, Fee Code, "
            + "and UCN in the previous submission")
    @Test
    void whenExistingClaimInPreviousSubmission() {
      var claimTobeProcessed =
          createClaim(
              "claimId1",
              "submissionId1",
              "CIV123",
              "070722/001",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var otherClaim =
          createClaim(
              "claimId2",
              "submissionId1",
              "CIV123",
              "070722/002",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var otherClaim1 =
          createClaim(
              "claimId3",
              "submissionId1",
              "CIV123",
              "070722/003",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var claimInPreviousSubmission =
          createClaimResponse(
              "claimId4",
              "submissionIdOld",
              "CIV123",
              "070722/003",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var submissionClaims = List.of(claimTobeProcessed, otherClaim, otherClaim1);

      when(mockDataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenReturn(new ClaimResultSet().addContentItem(claimInPreviousSubmission));

      List<ValidationIssue> validationIssues = duplicateClaimLegalHelpValidation.validateDuplicateClaims(
          claimTobeProcessed, submissionClaims, "2Q286D", "feeType");

      assertThat(validationIssues).isNotEmpty();
      assertThat(validationIssues)
          .extracting(ValidationIssue::getMessage)
          .containsExactly(
              ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION
                  .getDisplayMessage());
    }

    // This test is to verify that we log a validation error only against the current claim which is
    // being validated and not against the old duplicate claims.
    @DisplayName(
        "Validation error: When there are multiple existing legal help claims with the same Office, UFN, Fee Code, "
            + "and UCN in the previous submissions.")
    @Test
    void whenExistingClaimsInPreviousSubmissions() {
      var claim1 =
          createClaim(
              "claimId1",
              "submissionId1",
              "CIV123",
              "070722/001",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var claim2 =
          createClaim(
              "claimId2",
              "submissionId1",
              "CIV123",
              "070722/002",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var claim3 =
          createClaim(
              "claimId3",
              "submissionId1",
              "CIV123",
              "070722/003",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var claim4 =
          createClaimResponse(
              "claimId4",
              "submissionId2",
              "CIV123",
              "070722/003",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var claim5 =
          createClaimResponse(
              "claimId5",
              "submissionId2",
              "CIV123",
              "070722/003",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var submissionClaims = List.of(claim1, claim2, claim3);

      when(mockDataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenReturn(
              new ClaimResultSet().addContentItem(claim4).addContentItem(claim5));

      List<ValidationIssue> validationIssues = duplicateClaimLegalHelpValidation.validateDuplicateClaims(
          claim3, submissionClaims, "2Q286D", "feeType");

      assertThat(validationIssues).isNotEmpty();

      assertThat(validationIssues)
          .extracting(ValidationIssue::getMessage)
          .containsExactly(
              ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION
                  .getDisplayMessage());
      assertThat(validationIssues).hasSize(1);
    }

    @DisplayName(
        "Validation error: When there is an existing legal help claim with the same Office, UFN, Fee Code, "
            + "and UCN in the previous and current submission")
    @Test
    void whenExistingClaimInPreviousAndCurrentSubmission() {
      var claimTobeProcessed =
          createClaim(
              "claimId1",
              "submissionIdCurrent",
              "CIV123",
              "070722/001",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var otherClaim =
          createClaim(
              "claimId2",
              "submissionIdCurrent",
              "CIV123",
              "070722/001",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var claimInPreviousSubmission =
          createClaimResponse(
              "claimId4",
              "submissionIdPrevious",
              "CIV123",
              "070722/001",
              "CLI001",
              ClaimStatus.READY_TO_PROCESS);

      var submissionClaims = List.of(claimTobeProcessed, otherClaim);

      when(mockDataClaimsRestClient.getClaims(
              any(), any(), any(), any(), any(), any(), any(), any(), any(), any(), any()))
          .thenReturn(new ClaimResultSet().addContentItem(claimInPreviousSubmission));

      List<ValidationIssue> validationIssues = duplicateClaimLegalHelpValidation.validateDuplicateClaims(
          claimTobeProcessed, submissionClaims, "2Q286D",  "feeType");

      assertThat(validationIssues).isNotEmpty();

      assertThat(validationIssues)
          .extracting(ValidationIssue::getMessage)
          .containsExactly(
              ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION
                  .getDisplayMessage());
    }
  }
}
