package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static uk.gov.justice.laa.dstew.payments.claims.validation.core.service.ValidationServiceTestUtils.assertContextClaimError;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
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
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidatorCode;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionBase;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionStatus;

@DisplayName("DuplicateSubmissionValidator")
@ExtendWith(MockitoExtension.class)
class DuplicateSubmissionValidatorTest {
  private static final String OFFICE_CODE = "office1";
  private static final AreaOfLaw AREA_OF_LAW = AreaOfLaw.LEGAL_HELP;
  private static final String SUBMISSION_PERIOD = "2025-07";
  private static final OffsetDateTime EARLIER = OffsetDateTime.parse("2025-07-01T09:00:00Z");
  private static final OffsetDateTime LATER = OffsetDateTime.parse("2025-07-01T10:00:00Z");

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
    Assertions.assertTrue(
        validator.appliesTo(Set.of(SubmissionValidatorCode.SUBMISSION_DUPLICATE_VALIDATOR)));
    Assertions.assertEquals(
        SubmissionValidatorCode.SUBMISSION_DUPLICATE_VALIDATOR, validator.getValidatorCode());
  }

  private SubmissionResponse submissionUnderValidation(
      UUID submissionId, OffsetDateTime submitted) {
    return SubmissionResponse.builder()
        .submissionId(submissionId)
        .officeAccountNumber(OFFICE_CODE)
        .areaOfLaw(AREA_OF_LAW)
        .submissionPeriod(SUBMISSION_PERIOD)
        .submitted(submitted)
        .build();
  }

  private SubmissionBase existingSubmission(
      UUID submissionId, SubmissionStatus status, OffsetDateTime submitted) {
    return new SubmissionBase()
        .submissionId(submissionId)
        .officeAccountNumber(OFFICE_CODE)
        .areaOfLaw(AREA_OF_LAW)
        .submissionPeriod(SUBMISSION_PERIOD)
        .submitted(submitted)
        .status(status);
  }

  @Nested
  @DisplayName("validate")
  class Validate {

    @DisplayName(
        "Should accept a submission when there is no previous submission with the same combination"
            + " of Office, Area of law and Submission period")
    @Test
    void shouldAcceptSubmissionWhenNoPreviousSubmissionExists() {
      when(mockClaimsDataProvider.getSubmissions(any(), any(), any()))
          .thenReturn(Collections.emptyList());

      var submissionValidationContext = SubmissionValidationContext.create();
      SubmissionResponse submissionResponse =
          submissionUnderValidation(UUID.randomUUID(), LATER);

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
        "Should reject a submission when an older live submission exists with the same combination"
            + " of Office, Area of law and Submission period")
    @ParameterizedTest(name = "blocking status {0}")
    @EnumSource(
        value = SubmissionStatus.class,
        names = {
          "CREATED",
          "READY_FOR_VALIDATION",
          "VALIDATION_IN_PROGRESS",
          "VALIDATION_SUCCEEDED"
        })
    void shouldRejectSubmissionWhenOlderLiveSubmissionExists(final SubmissionStatus status) {
      var previousExistingSubmission =
          existingSubmission(UUID.randomUUID(), status, EARLIER);
      when(mockClaimsDataProvider.getSubmissions(any(), any(), any()))
          .thenReturn(List.of(previousExistingSubmission));

      var submissionValidationContext = SubmissionValidationContext.create();
      SubmissionResponse submissionResponse =
          submissionUnderValidation(UUID.randomUUID(), LATER);

      validator.validate(submissionResponse, submissionValidationContext);

      assertThat(submissionValidationContext.hasErrors()).as("status %s", status).isTrue();
      assertContextClaimError(
          submissionValidationContext.getIssues(),
          SubmissionValidationError.SUBMISSION_ALREADY_EXISTS.toValidationIssue(
              OFFICE_CODE, AREA_OF_LAW, SUBMISSION_PERIOD));
    }

    @DisplayName(
        "Should accept a submission when the only previous submission has a non-blocking status")
    @ParameterizedTest(name = "non-blocking status {0}")
    @EnumSource(
        value = SubmissionStatus.class,
        names = {"VALIDATION_FAILED", "REPLACED"})
    void shouldAcceptSubmissionWhenPreviousSubmissionHasNonBlockingStatus(
        final SubmissionStatus status) {
      var previousExistingSubmission =
          existingSubmission(UUID.randomUUID(), status, EARLIER);
      when(mockClaimsDataProvider.getSubmissions(any(), any(), any()))
          .thenReturn(List.of(previousExistingSubmission));

      var submissionValidationContext = SubmissionValidationContext.create();
      SubmissionResponse submissionResponse =
          submissionUnderValidation(UUID.randomUUID(), LATER);

      validator.validate(submissionResponse, submissionValidationContext);

      assertThat(submissionValidationContext.hasErrors()).as("status %s", status).isFalse();
    }

    @DisplayName(
        "Should accept a submission when the only matching submission is the submission itself")
    @Test
    void shouldAcceptSubmissionWhenOnlyMatchIsItself() {
      UUID submissionId = UUID.randomUUID();
      var itself = existingSubmission(submissionId, SubmissionStatus.VALIDATION_SUCCEEDED, EARLIER);
      when(mockClaimsDataProvider.getSubmissions(any(), any(), any()))
          .thenReturn(List.of(itself));

      var submissionValidationContext = SubmissionValidationContext.create();
      SubmissionResponse submissionResponse = submissionUnderValidation(submissionId, LATER);

      validator.validate(submissionResponse, submissionValidationContext);

      assertThat(submissionValidationContext.hasErrors()).isFalse();
    }

    @DisplayName(
        "Should accept a submission when the existing live submission was created after it")
    @Test
    void shouldAcceptSubmissionWhenExistingSubmissionCreatedLater() {
      var laterExistingSubmission =
          existingSubmission(UUID.randomUUID(), SubmissionStatus.VALIDATION_SUCCEEDED, LATER);
      when(mockClaimsDataProvider.getSubmissions(any(), any(), any()))
          .thenReturn(List.of(laterExistingSubmission));

      var submissionValidationContext = SubmissionValidationContext.create();
      SubmissionResponse submissionResponse =
          submissionUnderValidation(UUID.randomUUID(), EARLIER);

      validator.validate(submissionResponse, submissionValidationContext);

      assertThat(submissionValidationContext.hasErrors()).isFalse();
    }

    @DisplayName(
        "Should accept a submission when the existing live submission was created at the same time")
    @Test
    void shouldAcceptSubmissionWhenExistingSubmissionCreatedAtSameTime() {
      var sameTimeExistingSubmission =
          existingSubmission(UUID.randomUUID(), SubmissionStatus.VALIDATION_SUCCEEDED, EARLIER);
      when(mockClaimsDataProvider.getSubmissions(any(), any(), any()))
          .thenReturn(List.of(sameTimeExistingSubmission));

      var submissionValidationContext = SubmissionValidationContext.create();
      SubmissionResponse submissionResponse =
          submissionUnderValidation(UUID.randomUUID(), EARLIER);

      validator.validate(submissionResponse, submissionValidationContext);

      assertThat(submissionValidationContext.hasErrors()).isFalse();
    }

    @DisplayName(
        "Should reject a submission (fail-safe) when the existing live submission has no created"
            + " timestamp")
    @Test
    void shouldRejectSubmissionWhenExistingSubmissionUndated() {
      var undatedExistingSubmission =
          existingSubmission(UUID.randomUUID(), SubmissionStatus.VALIDATION_SUCCEEDED, null);
      when(mockClaimsDataProvider.getSubmissions(any(), any(), any()))
          .thenReturn(List.of(undatedExistingSubmission));

      var submissionValidationContext = SubmissionValidationContext.create();
      SubmissionResponse submissionResponse =
          submissionUnderValidation(UUID.randomUUID(), LATER);

      validator.validate(submissionResponse, submissionValidationContext);

      assertThat(submissionValidationContext.hasErrors()).isTrue();
    }

    @DisplayName(
        "Should reject a submission (fail-safe) when the submission under validation has no created"
            + " timestamp")
    @Test
    void shouldRejectSubmissionWhenSubmissionUnderValidationUndated() {
      var previousExistingSubmission =
          existingSubmission(UUID.randomUUID(), SubmissionStatus.VALIDATION_SUCCEEDED, EARLIER);
      when(mockClaimsDataProvider.getSubmissions(any(), any(), any()))
          .thenReturn(List.of(previousExistingSubmission));

      var submissionValidationContext = SubmissionValidationContext.create();
      SubmissionResponse submissionResponse =
          submissionUnderValidation(UUID.randomUUID(), null);

      validator.validate(submissionResponse, submissionValidationContext);

      assertThat(submissionValidationContext.hasErrors()).isTrue();
    }
  }
}
