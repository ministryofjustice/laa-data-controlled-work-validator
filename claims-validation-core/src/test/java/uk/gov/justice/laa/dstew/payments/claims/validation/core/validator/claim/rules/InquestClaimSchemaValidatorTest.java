package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidatorCode;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimInquestDetail;

@DisplayName("Inquest claim schema validator")
class InquestClaimSchemaValidatorTest {

  private static final String SCHEMA_ERROR = "SCHEMA_VALIDATION_ERROR";

  private InquestClaimSchemaValidator validator;

  @BeforeEach
  void setUp() {
    validator = new InquestClaimSchemaValidator();
    validator.init();
  }

  private static ClaimInquestDetail validDetail() {
    return new ClaimInquestDetail()
        .isClientMeansTested(false)
        .deceasedForename("Anne-Marie")
        .deceasedSurname("O'Neil")
        .deceasedDateOfDeath("17/05/2020")
        .coronersInquestReference("AB/12-34.56")
        .interestedDepartments(List.of("Ministry of Justice"));
  }

  private ClaimValidationContext validate(Boolean isInquest, ClaimInquestDetail detail) {
    ClaimValidationContext context = ClaimValidationContext.builder().build();
    context.setIsInquest(isInquest);
    validator.validate(Claim.builder().inquestDetail(detail).build(), context);
    return context;
  }

  private static void assertSingleIssue(
      ClaimValidationContext context, String code, String message, String path) {
    assertThat(context.getIssues()).singleElement().satisfies(issue -> {
      assertThat(issue.getCode()).isEqualTo(code);
      assertThat(issue.getMessage()).isEqualTo(message);
      assertThat(issue.getPath()).isEqualTo(path);
    });
  }

  @Test
  void metadata() {
    assertThat(validator.getValidatorCode())
        .isEqualTo(ClaimValidatorCode.CLAIM_INQUEST_SCHEMA_VALIDATOR);
    assertThat(validator.priority()).isEqualTo(1);
  }

  @Test
  @DisplayName("Valid non-means-tested Inquest detail passes")
  void validDetailPasses() {
    assertThat(validate(true, validDetail()).getIssues()).isEmpty();
  }

  @Test
  @DisplayName("Schema is not applied unless the claim is a non-means-tested Inquest claim")
  void notApplicableClaimsSkipped() {
    ClaimInquestDetail invalid = validDetail().deceasedForename("@");

    assertThat(validate(null, invalid).getIssues()).isEmpty();
    assertThat(validate(false, invalid).getIssues()).isEmpty();
    assertThat(validate(true, null).getIssues()).isEmpty();
    assertThat(validate(true, validDetail().deceasedForename("@").isClientMeansTested(null))
        .getIssues()).isEmpty();
    assertThat(validate(true, validDetail().deceasedForename("@").isClientMeansTested(true))
        .getIssues()).isEmpty();
  }

  // ─── Schema format rules ──────────────────────────────────────────────────

  @Nested
  @DisplayName("Deceased names (schema)")
  class DeceasedNames {

    private static final String FORENAME_MESSAGE = "Deceased Forename must contain only letters, "
        + "numbers, spaces, hyphens, apostrophes, ampersands, and be a maximum of 30 characters";
    private static final String SURNAME_MESSAGE = "Deceased Surname must contain only letters, "
        + "numbers, spaces, hyphens, apostrophes, ampersands, and be a maximum of 30 characters";

    private void assertRejected(String value) {
      assertSingleIssue(validate(true, validDetail().deceasedForename(value)), SCHEMA_ERROR,
          FORENAME_MESSAGE, "inquest_detail.deceased_forename");
      assertSingleIssue(validate(true, validDetail().deceasedSurname(value)), SCHEMA_ERROR,
          SURNAME_MESSAGE, "inquest_detail.deceased_surname");
    }

    @ParameterizedTest(name = "[{index}] \"{0}\"")
    @ValueSource(strings = {"Anne-Marie", "O'Neil", "O’Neil", "A & B 2", "Élodie", "李",
        "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"})
    @DisplayName("Permitted values up to 30 characters pass")
    void permittedValuesPass(String value) {
      assertThat(validate(true, validDetail().deceasedForename(value)).getIssues()).isEmpty();
      assertThat(validate(true, validDetail().deceasedSurname(value)).getIssues()).isEmpty();
    }

    @ParameterizedTest(name = "[{index}] \"{0}\"")
    @ValueSource(strings = {"Ann@", "Ann/Marie", "Ann.", "Ann_Marie",
        "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"})
    @DisplayName("Other characters or 31 characters are rejected")
    void invalidValuesRejected(String value) {
      assertRejected(value);
    }
  }

  @Nested
  @DisplayName("Coroner's Inquest Reference (schema)")
  class InquestReference {

    private static final String MESSAGE = "Coroner's Inquest Reference must contain only letters, "
        + "numbers, forward slashes, hyphens, periods, and be a maximum of 30 characters";

    @ParameterizedTest(name = "[{index}] \"{0}\"")
    @NullAndEmptySource
    @ValueSource(strings = {" ", "AB/12-34.56", "AB/12-34.5AB/12-34.5AB/12-34.5"})
    @DisplayName("Omitted, permitted or 30-character references pass")
    void permittedReferencesPass(String reference) {
      assertThat(validate(true, validDetail().coronersInquestReference(reference)).getIssues())
          .isEmpty();
    }

    @ParameterizedTest(name = "[{index}] \"{0}\"")
    @ValueSource(strings = {"AB@12", "AB_12", "AB 12", "AB/12-34.5AB/12-34.5AB/12-34.56"})
    @DisplayName("Other characters, internal spaces or 31 characters are rejected")
    void invalidReferencesRejected(String reference) {
      assertSingleIssue(validate(true, validDetail().coronersInquestReference(reference)),
          SCHEMA_ERROR, MESSAGE, "inquest_detail.coroners_inquest_reference");
    }
  }
}
