package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static uk.gov.justice.laa.dstew.payments.claims.validation.core.service.ValidationServiceTestUtils.assertContextClaimError;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationError;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionBase;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionStatus;

@ExtendWith(MockitoExtension.class)
class DuplicateSubmissionValidatorTest {
  private static final String OFFICE_CODE = "office1";
  private static final AreaOfLaw AREA_OF_LAW = AreaOfLaw.LEGAL_HELP;
  private static final String SUBMISSION_PERIOD = "2025-07";

  @Mock private ClaimsDataProvider mockClaimsDataProvider;

  @InjectMocks private DuplicateSubmissionValidator validator;

  @Captor private ArgumentCaptor<List<String>> officeCodeCaptor;

  @Captor private ArgumentCaptor<AreaOfLaw> areaOfLawCaptor;

  @Captor private ArgumentCaptor<String> submissionPeriodCaptor;

  @DisplayName("Should have priority of 100")
  @Test
  void priority() {
    Assertions.assertEquals(100, validator.priority());
  }

  @DisplayName("appliesTo returns true and validator code is set")
  @Test
  void metadata() {
    Assertions.assertTrue(validator.appliesTo(Set.of("SUBMISSION_DUPLICATE_VALIDATOR")));
    Assertions.assertEquals("SUBMISSION_DUPLICATE_VALIDATOR", validator.getValidatorCode());
  }

  @Nested
  class Validate {

    @DisplayName(
        "Should accept a submission when there is no previous submission with the same combination of Office, Area of law and Submission period")
    @Test
    void shouldAcceptSubmission() {
      when(mockClaimsDataProvider.getSubmissions(any(), any(), any()))
          .thenReturn(Collections.emptyList());

      var submissionValidationContext = SubmissionValidationContext.create();

      SubmissionResponse submissionResponse =
          SubmissionResponse.builder()
              .officeAccountNumber(OFFICE_CODE)
              .areaOfLaw(AREA_OF_LAW)
              .submissionPeriod(SUBMISSION_PERIOD)
              .build();

      validator.validate(submissionResponse, submissionValidationContext);

      assertThat(submissionValidationContext.hasErrors()).isFalse();
      verify(mockClaimsDataProvider)
          .getSubmissions(
              officeCodeCaptor.capture(),
              areaOfLawCaptor.capture(),
              submissionPeriodCaptor.capture());
      assertThat(officeCodeCaptor.getValue()).contains(OFFICE_CODE);
      assertThat(areaOfLawCaptor.getValue()).isEqualTo(AREA_OF_LAW);
      assertThat(submissionPeriodCaptor.getValue()).isEqualTo(SUBMISSION_PERIOD);
    }

    @DisplayName(
        "Should reject a submission when there is a previous submission of status VALIDATION_SUCCEEDED with the same combination of Office, Area of law and Submission period")
    @Test
    void shouldRejectSubmissionWhenExist() {
      var previousExistingSubmission =
          new SubmissionBase()
              .officeAccountNumber(OFFICE_CODE)
              .areaOfLaw(AREA_OF_LAW)
              .submissionPeriod(SUBMISSION_PERIOD)
              .status(SubmissionStatus.VALIDATION_SUCCEEDED);
      when(mockClaimsDataProvider.getSubmissions(any(), any(), any()))
          .thenReturn(List.of(previousExistingSubmission));

      var submissionValidationContext = SubmissionValidationContext.create();
      SubmissionResponse submissionResponse =
          SubmissionResponse.builder()
              .officeAccountNumber(OFFICE_CODE)
              .areaOfLaw(AREA_OF_LAW)
              .submissionPeriod(SUBMISSION_PERIOD)
              .build();

      validator.validate(submissionResponse, submissionValidationContext);

      assertThat(submissionValidationContext.hasErrors()).isTrue();

      assertContextClaimError(
          submissionValidationContext.getIssues(),
          SubmissionValidationError.SUBMISSION_ALREADY_EXISTS.toValidationIssue(OFFICE_CODE,
                  AREA_OF_LAW,
                  SUBMISSION_PERIOD)
          );
    }

    @DisplayName(
        "Should accept a submission even if a previous submission exists with the same combination of Office and Area of law but with a status other than VALIDATION_SUCCEEDED")
    @ParameterizedTest
    @EnumSource(
        value = SubmissionStatus.class,
        names = {
          "CREATED",
          "READY_FOR_VALIDATION",
          "VALIDATION_FAILED",
          "VALIDATION_IN_PROGRESS",
          "REPLACED"
        })
    void shouldAcceptSubmissionWhenPreviousSubmissionStatusIsNotValidatedSucceeded(
        final SubmissionStatus status) {
      var previousExistingSubmission =
          new SubmissionBase()
              .officeAccountNumber(OFFICE_CODE)
              .areaOfLaw(AREA_OF_LAW)
              .submissionPeriod(SUBMISSION_PERIOD)
              .status(status);
      when(mockClaimsDataProvider.getSubmissions(any(), any(), any()))
          .thenReturn(List.of(previousExistingSubmission));

      var submissionValidationContext = SubmissionValidationContext.create();
      SubmissionResponse submissionResponse =
          SubmissionResponse.builder()
              .officeAccountNumber(OFFICE_CODE)
              .areaOfLaw(AREA_OF_LAW)
              .submissionPeriod(SUBMISSION_PERIOD)
              .build();

      validator.validate(submissionResponse, submissionValidationContext);

      assertThat(submissionValidationContext.hasErrors()).isFalse();
    }
  }
}
