package uk.gov.justice.laa.dstew.payments.claims.validation.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidation;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidation;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidatorCode;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.DuplicateSubmissionValidator;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionBase;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionResponse;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionStatus;

/**
 * End-to-end characterization tests for duplicate-submission detection driven through the public
 * {@link ValidationService#validateSubmission} entry point.
 *
 * <p>These wire the <em>real</em> {@link DuplicateSubmissionValidator} against a stubbed
 * {@link ClaimsDataProvider}, exercising the same path a consumer uses when calling
 * {@code validateSubmission(submission, {SUBMISSION_DUPLICATE_VALIDATOR})}.
 */
@DisplayName("DuplicateSubmissionValidator — end-to-end via ValidationService")
class DuplicateSubmissionValidatorEndToEndTest {

  private static final String OFFICE_CODE = "office1";
  private static final AreaOfLaw AREA_OF_LAW = AreaOfLaw.LEGAL_HELP;
  private static final String SUBMISSION_PERIOD = "2025-07";
  private static final OffsetDateTime EARLIER = OffsetDateTime.parse("2025-07-01T09:00:00Z");
  private static final OffsetDateTime LATER = OffsetDateTime.parse("2025-07-01T10:00:00Z");

  private ClaimsDataProvider claimsDataProvider;
  private ValidationService validationService;

  @BeforeEach
  void setUp() {
    claimsDataProvider = mock(ClaimsDataProvider.class);

    DuplicateSubmissionValidator duplicateSubmissionValidator =
        new DuplicateSubmissionValidator(claimsDataProvider);

    ClaimValidation claimValidation = new ClaimValidation(Collections.emptyList(), null);
    SubmissionValidation submissionValidation =
        new SubmissionValidation(List.of(duplicateSubmissionValidator));
    validationService = new ValidationService(claimValidation, submissionValidation);
  }

  private SubmissionResponse submissionUnderValidation(OffsetDateTime submitted) {
    return SubmissionResponse.builder()
        .submissionId(UUID.randomUUID())
        .officeAccountNumber(OFFICE_CODE)
        .areaOfLaw(AREA_OF_LAW)
        .submissionPeriod(SUBMISSION_PERIOD)
        .submitted(submitted)
        .build();
  }

  private SubmissionBase existingSubmission(SubmissionStatus status, OffsetDateTime submitted) {
    return new SubmissionBase()
        .submissionId(UUID.randomUUID())
        .officeAccountNumber(OFFICE_CODE)
        .areaOfLaw(AREA_OF_LAW)
        .submissionPeriod(SUBMISSION_PERIOD)
        .submitted(submitted)
        .status(status);
  }

  private void stubProvider(SubmissionBase... existing) {
    when(claimsDataProvider.getSubmissions(any(), any(), any())).thenReturn(List.of(existing));
  }

  private ValidationResult validate(SubmissionResponse submission) {
    return validationService.validateSubmission(
        submission, Set.of(SubmissionValidatorCode.SUBMISSION_DUPLICATE_VALIDATOR));
  }

  @Nested
  @DisplayName("Blocking behaviour")
  class Blocking {

    @DisplayName("Older submission in a live status ⇒ SUBMISSION_ALREADY_EXISTS")
    @ParameterizedTest(name = "live status {0}")
    @EnumSource(
        value = SubmissionStatus.class,
        names = {
          "CREATED",
          "READY_FOR_VALIDATION",
          "VALIDATION_IN_PROGRESS",
          "VALIDATION_SUCCEEDED"
        })
    void olderLiveSubmissionBlocks(final SubmissionStatus status) {
      stubProvider(existingSubmission(status, EARLIER));

      ValidationResult result = validate(submissionUnderValidation(LATER));

      assertThat(result.isValid()).as("status %s", status).isFalse();
      assertThat(result.getIssues())
          .extracting(ValidationIssue::getCode)
          .contains(SubmissionValidationError.SUBMISSION_ALREADY_EXISTS.name());
    }

    @DisplayName("Older live submission with a missing created timestamp blocks (fail-safe)")
    @Test
    void undatedExistingSubmissionBlocks() {
      stubProvider(existingSubmission(SubmissionStatus.VALIDATION_SUCCEEDED, null));

      ValidationResult result = validate(submissionUnderValidation(LATER));

      assertThat(result.isValid()).isFalse();
      assertThat(result.getIssues())
          .extracting(ValidationIssue::getCode)
          .contains(SubmissionValidationError.SUBMISSION_ALREADY_EXISTS.name());
    }
  }

  @Nested
  @DisplayName("Non-blocking behaviour")
  class NonBlocking {

    @DisplayName("No existing submission ⇒ valid")
    @Test
    void noExistingSubmissionIsValid() {
      when(claimsDataProvider.getSubmissions(any(), any(), any()))
          .thenReturn(Collections.emptyList());

      ValidationResult result = validate(submissionUnderValidation(LATER));

      assertThat(result.isValid()).isTrue();
      assertThat(result.getIssues())
          .extracting(ValidationIssue::getCode)
          .doesNotContain(SubmissionValidationError.SUBMISSION_ALREADY_EXISTS.name());
    }

    @DisplayName("Existing submission in a non-blocking status ⇒ valid")
    @ParameterizedTest(name = "non-blocking status {0}")
    @EnumSource(
        value = SubmissionStatus.class,
        names = {"VALIDATION_FAILED", "REPLACED"})
    void nonBlockingStatusIsValid(final SubmissionStatus status) {
      stubProvider(existingSubmission(status, EARLIER));

      ValidationResult result = validate(submissionUnderValidation(LATER));

      assertThat(result.isValid()).as("status %s", status).isTrue();
    }

    @DisplayName("Existing live submission created later than the one under validation ⇒ valid")
    @Test
    void laterExistingSubmissionIsValid() {
      stubProvider(existingSubmission(SubmissionStatus.VALIDATION_SUCCEEDED, LATER));

      ValidationResult result = validate(submissionUnderValidation(EARLIER));

      assertThat(result.isValid()).isTrue();
    }
  }
}
