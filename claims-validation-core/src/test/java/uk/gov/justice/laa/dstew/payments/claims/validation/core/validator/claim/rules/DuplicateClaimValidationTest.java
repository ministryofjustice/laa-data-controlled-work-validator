package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateClaimValidationStrategy;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

@ExtendWith(MockitoExtension.class)
class DuplicateClaimValidationTest {

  @Mock private DuplicateClaimValidationStrategy mockStrategy;

  private DuplicateClaimValidator validator;

  @BeforeEach
  void setUp() {
    // Set up strategy to be compatible with LEGAL_HELP
    // Using lenient() as not all tests use this stubbing
    lenient().when(mockStrategy.compatibleAreaOfLaws()).thenReturn(List.of(AreaOfLaw.LEGAL_HELP));
    validator = new DuplicateClaimValidator(List.of(mockStrategy));
  }

  @Test
  void validate_returnsNoErrors_whenNoDuplicatesFound() {
    Claim claim = new Claim();
    claim.setId(UUID.randomUUID());
    claim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);
    claim.setUniqueFileNumber("010120/001");
    claim.setOfficeAccountNumber("1A234B");

    ClaimValidationContext context = ClaimValidationContext.builder().build();

    when(mockStrategy.validateDuplicateClaims(any(), any(), anyString(), any()))
        .thenReturn(Collections.emptyList());

    validator.validate(claim, context);

    assertThat(context.getIssues()).isEmpty();
  }

  @Test
  void validate_returnsError_whenDuplicateFound() {
    Claim claim = new Claim();
    claim.setId(UUID.randomUUID());
    claim.setAreaOfLaw(AreaOfLaw.LEGAL_HELP);
    claim.setUniqueFileNumber("010120/001");
    claim.setOfficeAccountNumber("1A234B");

    ClaimValidationContext context = ClaimValidationContext.builder().build();

    ValidationIssue duplicateIssue =
        ValidationIssue.builder()
            .code("INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION")
            .message("Duplicate claim found")
            .severity(ValidationSeverity.ERROR)
            .build();

    when(mockStrategy.validateDuplicateClaims(any(), any(), anyString(), any()))
        .thenReturn(List.of(duplicateIssue));

    validator.validate(claim, context);

    assertThat(context.getIssues()).hasSize(1);
    assertThat(context.getIssues().getFirst().getCode())
        .isEqualTo("INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION");
  }

  @Test
  void validate_returnsNoErrors_whenNoAreaOfLaw() {
    Claim claim = new Claim();
    claim.setId(UUID.randomUUID());
    // No area of law set

    ClaimValidationContext context = ClaimValidationContext.builder().build();

    validator.validate(claim, context);

    assertThat(context.getIssues()).isEmpty();
  }

  @Test
  void validate_returnsNoErrors_whenNoCompatibleStrategy() {
    Claim claim = new Claim();
    claim.setId(UUID.randomUUID());
    claim.setAreaOfLaw(AreaOfLaw.CRIME_LOWER); // Strategy only supports LEGAL_HELP

    ClaimValidationContext context = ClaimValidationContext.builder().build();

    validator.validate(claim, context);

    assertThat(context.getIssues()).isEmpty();
  }

  @Test
  void getValidatorCode_returnsDuplicateClaim() {
    assertThat(validator.getValidatorCode()).isEqualTo("DUPLICATE_CLAIM");
  }

  @Test
  void priority_returns10000() {
    assertThat(validator.priority()).isEqualTo(10000);
  }
}
