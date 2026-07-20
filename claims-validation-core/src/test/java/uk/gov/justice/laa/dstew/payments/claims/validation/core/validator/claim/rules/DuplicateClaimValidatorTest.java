package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateClaimValidationStrategy;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

/** Unit tests for the {@link DuplicateClaimValidator} dispatcher. */
@DisplayName("DuplicateClaimValidator dispatcher")
class DuplicateClaimValidatorTest {

  private static ClaimValidationContext context() {
    return ClaimValidationContext.builder().build();
  }

  @Test
  @DisplayName("No compatible strategy (e.g. Mediation) ⇒ no strategy invoked, no issues")
  void noCompatibleStrategyIsInert() {
    DuplicateClaimValidationStrategy legalHelpOnly = mock(DuplicateClaimValidationStrategy.class);
    when(legalHelpOnly.compatibleAreaOfLaws()).thenReturn(List.of(AreaOfLaw.LEGAL_HELP));

    DuplicateClaimValidator validator = new DuplicateClaimValidator(List.of(legalHelpOnly));
    Claim mediationClaim = Claim.builder().areaOfLaw(AreaOfLaw.MEDIATION).build();
    ClaimValidationContext context = context();

    validator.validate(mediationClaim, context);

    assertThat(context.getIssues()).isEmpty();
    verify(legalHelpOnly, never()).validateDuplicateClaims(any(), any(), any(), any());
  }

  @Test
  @DisplayName("Null area of law ⇒ short-circuits, no strategy invoked")
  void nullAreaOfLawIsSkipped() {
    DuplicateClaimValidationStrategy strategy = mock(DuplicateClaimValidationStrategy.class);

    DuplicateClaimValidator validator = new DuplicateClaimValidator(List.of(strategy));
    Claim claim = Claim.builder().build(); // areaOfLaw == null
    ClaimValidationContext context = context();

    validator.validate(claim, context);

    assertThat(context.getIssues()).isEmpty();
    verify(strategy, never()).validateDuplicateClaims(any(), any(), any(), any());
  }

  @Test
  @DisplayName("Compatible strategy ⇒ invoked and its issues are added to the context")
  void compatibleStrategyIssuesAreAggregated() {
    ValidationIssue issue =
        ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_SAME_SUBMISSION.toValidationIssue();

    DuplicateClaimValidationStrategy legalHelp = mock(DuplicateClaimValidationStrategy.class);
    when(legalHelp.compatibleAreaOfLaws()).thenReturn(List.of(AreaOfLaw.LEGAL_HELP));
    when(legalHelp.validateDuplicateClaims(any(), any(), any(), any()))
        .thenReturn(List.of(issue));

    DuplicateClaimValidator validator = new DuplicateClaimValidator(List.of(legalHelp));
    Claim claim = Claim.builder().areaOfLaw(AreaOfLaw.LEGAL_HELP).build();
    ClaimValidationContext context = context();

    validator.validate(claim, context);

    assertThat(context.getIssues()).contains(issue);
    verify(legalHelp).validateDuplicateClaims(any(), any(), any(), any());
  }

  @Test
  @DisplayName("Identical issues from multiple strategies are de-duplicated (reported once)")
  void identicalIssuesAcrossStrategiesAreDeduplicated() {
    ValidationIssue technicalError =
        ClaimValidationError.TECHNICAL_ERROR_DATA_CLAIMS_API.toValidationIssue();

    DuplicateClaimValidationStrategy strategyA = mock(DuplicateClaimValidationStrategy.class);
    when(strategyA.compatibleAreaOfLaws()).thenReturn(List.of(AreaOfLaw.LEGAL_HELP));
    when(strategyA.validateDuplicateClaims(any(), any(), any(), any()))
        .thenReturn(List.of(technicalError));

    DuplicateClaimValidationStrategy strategyB = mock(DuplicateClaimValidationStrategy.class);
    when(strategyB.compatibleAreaOfLaws()).thenReturn(List.of(AreaOfLaw.LEGAL_HELP));
    when(strategyB.validateDuplicateClaims(any(), any(), any(), any()))
        .thenReturn(List.of(technicalError));

    DuplicateClaimValidator validator =
        new DuplicateClaimValidator(List.of(strategyA, strategyB));
    Claim claim = Claim.builder().areaOfLaw(AreaOfLaw.LEGAL_HELP).build();
    ClaimValidationContext context = context();

    validator.validate(claim, context);

    // getAllIssues() bypasses path-based de-duplication, so a count of 1 proves the dispatcher
    // itself collapsed the identical (null-path) technical errors from the two strategies.
    assertThat(context.getAllIssues()).containsExactly(technicalError);
  }
}
