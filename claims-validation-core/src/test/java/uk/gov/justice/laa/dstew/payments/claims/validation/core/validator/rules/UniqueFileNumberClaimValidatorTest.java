package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.ValidationContext;

@DisplayName("Unique file number claim validator test")
class UniqueFileNumberClaimValidatorTest {

  UniqueFileNumberClaimValidator validator = new UniqueFileNumberClaimValidator();

  @Test
  @DisplayName("Should have no errors")
  void shouldHaveNoErrors() {
    // Given
    String uniqueFileNumber = "010101/123";
    Claim claim = Claim.builder().uniqueFileNumber(uniqueFileNumber).build();
    ValidationContext context = ValidationContext.builder().build();

    // When
    List<ValidationIssue> issues = validator.validate(claim, context);

    // Then
    assertThat(issues).isEmpty();
  }

  @ParameterizedTest
  @NullAndEmptySource
  @DisplayName(
      "Should have no errors if ufn is null or empty (Handled in "
          + "MandatoryFieldClaimValidator)")
  void shouldHaveErrorsIfUfnIsEmpty(String ufn) {
    // Given
    Claim claim = Claim.builder().uniqueFileNumber(ufn).build();
    ValidationContext context = ValidationContext.builder().build();

    // When
    List<ValidationIssue> issues = validator.validate(claim, context);

    // Then
    assertThat(issues).isEmpty();
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "010130",
        "123",
        "0101030/123",
        "010101/1234",
        "01010/12345",
        "abcdef/123",
        "010130/abc",
      })
  @DisplayName("Should have errors if UFN is not in correct format")
  void shouldHaveAnErrorIfUfnIsNotInCorrectFormat(String ufn) {
    // Given
    Claim claim = Claim.builder().uniqueFileNumber(ufn).build();
    ValidationContext context = ValidationContext.builder().build();

    // When
    List<ValidationIssue> issues = validator.validate(claim, context);

    // Then
    assertThat(issues).hasSize(1);
    assertThat(issues.getFirst().getCode())
        .isEqualTo(ClaimValidationError.INVALID_DATE_IN_UNIQUE_FILE_NUMBER.name());
  }

  @Test
  @DisplayName("Should have errors if date part of UFN is after today")
  void shouldHaveErrorsIfDatePartOfUfnIsAfterToday() {
    // Given
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
    String format = formatter.format(LocalDate.now().plusDays(1));
    String ufn = format + "/123";
    Claim claim = Claim.builder().uniqueFileNumber(ufn).build();
    ValidationContext context = ValidationContext.builder().build();

    // When
    List<ValidationIssue> issues = validator.validate(claim, context);

    // Then
    assertThat(issues).hasSize(1);
    assertThat(issues.getFirst().getCode())
        .isEqualTo(ClaimValidationError.INVALID_DATE_IN_UNIQUE_FILE_NUMBER.name());
  }

  // TODO: Revisit this test - our validator returns issues directly and doesn't check
  //       context for existing errors. Need to determine if duplicate error prevention
  //       should be handled at the validator level or in ValidationService.
  @Test
  @Disabled("Duplicate error prevention not implemented in this validator - see TODO")
  @DisplayName("Should not add duplicate errors if the field is already in error")
  void shouldNotAddDuplicateErrorsIfTheFieldIsAlreadyInError() {
    // Given
    Claim claim = Claim.builder().uniqueFileNumber("01010124/123").build(); // invalid date
    ValidationContext context = ValidationContext.builder().build();

    // When
    List<ValidationIssue> issues = validator.validate(claim, context);

    // Then
    assertThat(issues).hasSize(1);
    assertThat(issues.getFirst().getCode())
        .isEqualTo(ClaimValidationError.INVALID_DATE_IN_UNIQUE_FILE_NUMBER.name());
  }

  @ParameterizedTest
  @ValueSource(strings = {"999999/001", "320101/001", "000000/001", "311102/001", "290225/001"})
  @DisplayName("Should have errors if date is not correct")
  void shouldHaveErrorsIfDateCantBeParsed(String ufn) {
    // Given
    Claim claim = Claim.builder().uniqueFileNumber(ufn).build();
    ValidationContext context = ValidationContext.builder().build();

    // When
    List<ValidationIssue> issues = validator.validate(claim, context);

    // Then
    assertThat(issues).hasSize(1);
    assertThat(issues.getFirst().getCode())
        .isEqualTo(ClaimValidationError.INVALID_DATE_IN_UNIQUE_FILE_NUMBER.name());
  }
}
