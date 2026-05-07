package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.FeeCalculationType;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;

class DisbursementClaimStartDateValidatorTest {

  private DisbursementClaimStartDateValidator validator;
  private ClaimValidationContext context;
  private Claim claim;
  private final UUID claimId = new UUID(1, 1);
  private static final DateTimeFormatter DATE_FORMATTER_FOR_DISPLAY_MESSAGE =
      DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static final DateTimeFormatter DATE_FORMATTER_YYYY_MM_DD =
      DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @BeforeEach
  void setUp() {
    validator = new DisbursementClaimStartDateValidator();
    context =
        ClaimValidationContext.builder()
            .feeCalculationType(FeeCalculationType.DISB_ONLY.getValue())
            .build();
    claim = Claim.builder().id(claimId).build();
  }

  @ParameterizedTest
  @CsvSource({
    "2025-11-07, JAN-2026",
    "2025-01-20, MAR-2025",
    "2025-01-01, APR-2025",
    "2025-01-31, APR-2025",
    "2025-01-01, MAY-2025",
    "2025-01-10, MAY-2025",
    "2025-01-31, MAY-2025",
    "2025-02-28, MAY-2025",
    "2024-11-30, FEB-2025",
    "2024-01-20, MAR-2024",
    "2024-01-19, MAR-2024",
    "2024-01-31, APR-2024",
    "2024-01-30, APR-2024"
  })
  @DisplayName(
      "Should pass validation when claimStartDate is greater than or equals to 3 months of submission period")
  void shouldPassValidationWhenClaimStartDateIsGreaterThanOrEqualTo3MonthsOfSubmissionPeriod(
      String caseStartDate, String submissionPeriod) {
    claim = claim.toBuilder().caseStartDate(caseStartDate).submissionPeriod(submissionPeriod).build();

    validator.validate(claim, context);

    assertThat(context.getIssues()).isEmpty();
  }

  @ParameterizedTest
  @CsvSource({
    "2025-01-10, FEB-2025",
    "2025-01-21, MAR-2025",
    "2025-01-31, MAR-2025",
    "2025-02-21, APR-2025",
    "2024-12-21, FEB-2025",
    "2024-01-31, MAR-2024",
    "2024-01-21, MAR-2024",
    "2024-02-29, APR-2024",
    "2024-02-21, APR-2024"
  })
  @DisplayName(
      "Should fail validation when claimStartDate is less than 3 months of submission period end date (which is 20th of following month)")
  void shouldFailValidationWhenClaimStartDateIsLessThan3MonthsOfSubmissionPeriod(
      String caseStartDate, String submissionPeriod) {
    claim = claim.toBuilder().caseStartDate(caseStartDate).submissionPeriod(submissionPeriod).build();
    LocalDate startDate = LocalDate.parse(caseStartDate, DATE_FORMATTER_YYYY_MM_DD);

    validator.validate(claim, context);

    assertThat(context.getIssues()).isNotEmpty();
    assertThat(
            context.getIssues().stream()
                .anyMatch(
                    x ->
                        x.getMessage()
                            .equals(
                                String.format(
                                    "Disbursement claims can only be submitted at least 3 calendar months after the Case Start Date %s",
                                    startDate.format(DATE_FORMATTER_FOR_DISPLAY_MESSAGE)))))
        .isTrue();
  }

  @Test
  @DisplayName("DisbursementClaimStartDateValidator - priority, appliesTo and validator code")
  void disbursementStartDateValidatorMetadata() {
    assertThat(validator.priority()).isEqualTo(10);
    assertThat(validator.appliesTo("disbursement")).isTrue();
    assertThat(validator.appliesTo("all")).isTrue();
    assertThat(validator.appliesTo("fee")).isFalse();
    assertThat(validator.getValidatorCode()).isEqualTo("CLAIM_DISBURSEMENT_START_DATE");
  }
}
