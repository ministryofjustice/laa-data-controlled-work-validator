package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;

@DisplayName("Client date of birth claim validator test")
class ClientDateOfBirthClaimValidationTest {

  private final ClientDateOfBirthClaimValidator validator = new ClientDateOfBirthClaimValidator();

  @Test
  void validateClientDateOfBirthOne() {
    UUID claimId = new UUID(1, 1);
    Claim claim =
        Claim.builder()
            .id(claimId)
            .status(ClaimStatus.READY_TO_PROCESS)
            .feeCode("feeCode1")
            .clientDateOfBirth("2099-12-31")
            .client2DateOfBirth("2099-12-31")
            .matterTypeCode("a:b")
            .build();

    ClaimValidationContext context = ClaimValidationContext.builder().build();

    validator.validate(claim, context);

    assertThat(
            context.getIssues().stream()
                .anyMatch(
                    x ->
                        x.toString()
                            .contains("Client Date of Birth must be between 01/01/1900 and today")))
        .isTrue();
    assertThat(
            context.getIssues().stream()
                .anyMatch(
                    x ->
                        x.toString()
                            .contains("Client Date of Birth must be between 01/01/1900 and today")))
        .isTrue();
  }

  @Test
  void validateClientDateOfBirthTwo() {
    UUID claimId = new UUID(2, 2);
    Claim claim =
        Claim.builder()
            .id(claimId)
            .status(ClaimStatus.READY_TO_PROCESS)
            .feeCode("feeCode2")
            .clientDateOfBirth("1899-12-31")
            .client2DateOfBirth("1899-12-31")
            .matterTypeCode("1:2")
            .build();

    ClaimValidationContext context = ClaimValidationContext.builder().build();

    validator.validate(claim, context);

    assertThat(
            context.getIssues().stream()
                .anyMatch(
                    x ->
                        x.toString()
                            .contains("Client Date of Birth must be between 01/01/1900 and today")))
        .isTrue();
    assertThat(
            context.getIssues().stream()
                .anyMatch(
                    x ->
                        x.toString()
                            .contains("Client Date of Birth must be between 01/01/1900 and today")))
        .isTrue();
    assertThat(
            context.getIssues().stream()
                .anyMatch(
                    x ->
                        x.toString()
                            .contains(
                                "Client 2 Date of Birth must be between 01/01/1900 and today")))
        .isTrue();
  }

  @Test
  @DisplayName("ClientDateOfBirthClaimValidator - priority, appliesTo and validator code")
  void clientDobValidatorMetadata() {
    assertThat(validator.priority()).isEqualTo(100);
    assertThat(validator.appliesTo("any")).isTrue();
    // Note: validator code contains a legacy typo - assert actual value to lock behaviour
    assertThat(validator.getValidatorCode()).isEqualTo("CCLAIM_LIENT_DATE_OF_BIRTH");
  }
}
