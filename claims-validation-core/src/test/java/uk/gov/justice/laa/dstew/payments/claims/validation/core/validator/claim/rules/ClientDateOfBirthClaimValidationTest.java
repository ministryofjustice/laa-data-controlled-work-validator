package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.function.BiConsumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidatorCode;

@DisplayName("Client date of birth claim validator test")
class ClientDateOfBirthClaimValidationTest {

  private static final String CLIENT_DOB_ERROR =
      "Client Date of Birth must be between 01/01/1900 and today";
  private static final String CLIENT_2_DOB_ERROR =
      "Client 2 Date of Birth must be between 01/01/1900 and today";

  private final ClientDateOfBirthClaimValidator validator = new ClientDateOfBirthClaimValidator();

  // ─── Happy path ────────────────────────────────────────────────────────────

  @Test
  @DisplayName("Valid DOBs for both clients produce no issues")
  void happyPath_validDobs_noIssues() {
    Claim claim = Claim.builder()
        .clientDateOfBirth("1990-06-15")
        .client2DateOfBirth("1985-03-22")
        .build();

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);

    assertTrue(context.getIssues().isEmpty());
  }

  @Test
  @DisplayName("Absent DOB fields are silently skipped — no issues")
  void absentDobs_skipped_noIssues() {
    Claim claim = Claim.builder().build();

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);

    assertTrue(context.getIssues().isEmpty());
  }

  // ─── Invalid date strings (unparseable) ───────────────────────────────────

  @ParameterizedTest(name = "[{index}] {0} dob={1} → {2}")
  @DisplayName("Invalid date string produces error for each client field")
  @CsvSource({
    "client1, not-a-date,  " + CLIENT_DOB_ERROR,
    "client2, 32-13-2020,  " + CLIENT_2_DOB_ERROR,
  })
  void invalidFormat_dob_producesError(String client, String dateValue, String expectedMessage) {
    Claim claim = buildClaimWithDob(client, dateValue);

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);

    assertThat(context.getIssues()).hasSize(1);
    assertThat(context.getIssues().getFirst().getMessage()).isEqualTo(expectedMessage);
  }

  // ─── Date in the future ────────────────────────────────────────────────────

  @ParameterizedTest(name = "[{index}] {0} future dob → {2}")
  @DisplayName("Future DOB produces error for each client field")
  @CsvSource({
    "client1, 2099-12-31, " + CLIENT_DOB_ERROR,
    "client2, 2099-12-31, " + CLIENT_2_DOB_ERROR,
  })
  void futureDob_producesError(String client, String dateValue, String expectedMessage) {
    Claim claim = buildClaimWithDob(client, dateValue);

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);

    assertThat(context.getIssues()).hasSize(1);
    assertThat(context.getIssues().getFirst().getMessage()).isEqualTo(expectedMessage);
  }

  @Test
  @DisplayName("Both clients with future DOBs each produce their own error")
  void futureDob_bothClients_bothErrorsPresent() {
    Claim claim = Claim.builder()
        .clientDateOfBirth("2099-12-31")
        .client2DateOfBirth("2099-12-31")
        .build();

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);

    assertThat(context.getIssues().stream()
        .anyMatch(x -> x.getMessage().equals(CLIENT_DOB_ERROR))).isTrue();
    assertThat(context.getIssues().stream()
        .anyMatch(x -> x.getMessage().equals(CLIENT_2_DOB_ERROR))).isTrue();
  }

  // ─── Date before 1900 ─────────────────────────────────────────────────────

  @ParameterizedTest(name = "[{index}] {0} pre-1900 dob → {2}")
  @DisplayName("DOB before 01/01/1900 produces error for each client field")
  @CsvSource({
    "client1, 1899-12-31, " + CLIENT_DOB_ERROR,
    "client2, 1899-12-31, " + CLIENT_2_DOB_ERROR,
  })
  void tooEarlyDob_producesError(String client, String dateValue, String expectedMessage) {
    Claim claim = buildClaimWithDob(client, dateValue);

    ClaimValidationContext context = ClaimValidationContext.builder().build();
    validator.validate(claim, context);

    assertThat(context.getIssues()).hasSize(1);
    assertThat(context.getIssues().getFirst().getMessage()).isEqualTo(expectedMessage);
  }

  // ─── Metadata ─────────────────────────────────────────────────────────────

  @Test
  @DisplayName("ClientDateOfBirthClaimValidator - priority, appliesTo and validator code")
  void clientDobValidatorMetadata() {
    assertThat(validator.priority()).isEqualTo(100);
    assertThat(validator.appliesTo(Set.of(ClaimValidatorCode.CLAIM_CLIENT_DATE_OF_BIRTH_VALIDATOR))).isTrue();
    assertThat(validator.getValidatorCode()).isEqualTo(ClaimValidatorCode.CLAIM_CLIENT_DATE_OF_BIRTH_VALIDATOR);
  }

  // ─── Helpers ──────────────────────────────────────────────────────────────

  /** Builds a claim with only the named client DOB field set. */
  private Claim buildClaimWithDob(String client, String dateValue) {
    Claim.ClaimBuilder builder = Claim.builder();
    BiConsumer<Claim.ClaimBuilder, String> setter = client.equals("client1")
        ? Claim.ClaimBuilder::clientDateOfBirth
        : Claim.ClaimBuilder::client2DateOfBirth;
    setter.accept(builder, dateValue);
    return builder.build();
  }
}
