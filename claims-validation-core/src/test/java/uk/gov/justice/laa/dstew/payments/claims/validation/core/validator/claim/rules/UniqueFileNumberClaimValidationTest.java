package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;

@DisplayName("Unique file number claim validator test")
class UniqueFileNumberClaimValidationTest {

  UniqueFileNumberClaimValidator validator = new UniqueFileNumberClaimValidator();

  @Test
  @DisplayName("Should have no errors")
  void shouldHaveNoErrors() {
    // Given
    String uniqueFileNumber = "010101/123";
    Claim claim = Claim.builder().uniqueFileNumber(uniqueFileNumber).build();
    ClaimValidationContext context = ClaimValidationContext.builder().build();

    // When
    validator.validate(claim, context);

    // Then
    assertThat(context.getIssues()).isEmpty();
  }

  @ParameterizedTest
  @NullAndEmptySource
  @DisplayName(
      "Should have no errors if ufn is null or empty (Handled in "
          + "MandatoryFieldClaimValidator)")
  void shouldHaveErrorsIfUfnIsEmpty(String ufn) {
    // Given
    Claim claim = Claim.builder().uniqueFileNumber(ufn).build();
    ClaimValidationContext context = ClaimValidationContext.builder().build();

    // When
    validator.validate(claim, context);

    // Then
    assertThat(context.getIssues()).isEmpty();
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
    ClaimValidationContext context = ClaimValidationContext.builder().build();

    // When
    validator.validate(claim, context);

    // Then
    assertThat(context.getIssues()).hasSize(1);
    assertThat(context.getIssues().getFirst().getCode())
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
    ClaimValidationContext context = ClaimValidationContext.builder().build();

    // When
    validator.validate(claim, context);

    // Then
    assertThat(context.getIssues()).hasSize(1);
    assertThat(context.getIssues().getFirst().getCode())
        .isEqualTo(ClaimValidationError.INVALID_DATE_IN_UNIQUE_FILE_NUMBER.name());
  }

  @Test
  @DisplayName("Should not add duplicate errors if the field is already in error")
  void shouldNotAddDuplicateErrorsIfTheFieldIsAlreadyInError() {
    // Given
    Claim claim = Claim.builder().uniqueFileNumber("01010124/123").build(); // invalid date
    ClaimValidationContext context = ClaimValidationContext.builder().build();

    // When
    validator.validate(claim, context);

    // Then
    assertThat(context.getIssues()).hasSize(1);
    assertThat(context.getIssues().getFirst().getCode())
        .isEqualTo(ClaimValidationError.INVALID_DATE_IN_UNIQUE_FILE_NUMBER.name());
  }

  @ParameterizedTest
  @ValueSource(strings = {"999999/001", "320101/001", "000000/001", "311102/001", "290225/001"})
  @DisplayName("Should have errors if date is not correct")
  void shouldHaveErrorsIfDateCantBeParsed(String ufn) {
    // Given
    Claim claim = Claim.builder().uniqueFileNumber(ufn).build();
    ClaimValidationContext context = ClaimValidationContext.builder().build();

    // When
    validator.validate(claim, context);

    // Then
    assertThat(context.getIssues()).hasSize(1);
    assertThat(context.getIssues().getFirst().getCode())
        .isEqualTo(ClaimValidationError.INVALID_DATE_IN_UNIQUE_FILE_NUMBER.name());
  }

  @Test
  @DisplayName("UniqueFileNumberClaimValidator - priority, appliesTo and validator code")
  void uniqueFileNumberValidatorMetadata() {
    assertThat(validator.priority()).isEqualTo(100);
    assertThat(validator.appliesTo("any-scope")).isTrue();
    assertThat(validator.getValidatorCode()).isEqualTo("CLAIM_UNIQUE_FILE_NUMBER");
  }
}
