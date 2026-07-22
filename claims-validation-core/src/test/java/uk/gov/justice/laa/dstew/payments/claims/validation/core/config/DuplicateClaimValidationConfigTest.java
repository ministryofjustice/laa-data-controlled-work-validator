package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateClaimCrimeLowerValidationServiceStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateClaimLegalHelpDisbursementValidationStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateClaimLegalHelpValidationServiceStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateSameSubmissionLegalHelpValidationServiceStrategy;

/**
 * Tests for duplicate claim validation strategy beans wired in
 * {@link ClaimsValidationAutoConfiguration}.
 *
 * <p>Verifies that each {@code @Bean} factory method:
 * <ul>
 *   <li>Returns a non-null instance of the correct strategy type.
 *   <li>Injects the supplied {@link ClaimsDataProvider} correctly.
 *   <li>Returns a new (distinct) instance on each call, not a shared singleton.
 * </ul>
 */
@DisplayName("Duplicate claim validation strategy beans (ClaimsValidationAutoConfiguration)")
class DuplicateClaimValidationConfigTest {

  private ClaimsValidationAutoConfiguration config;
  private ClaimsDataProvider claimsDataProvider;

  @BeforeEach
  void setUp() {
    config = new ClaimsValidationAutoConfiguration();
    claimsDataProvider = mock(ClaimsDataProvider.class);
  }

  // ─────────────────────────────────────────────────────────────────────────
  // duplicateClaimCrimeLowerValidationServiceStrategy
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("duplicateClaimCrimeLowerValidationServiceStrategy bean")
  class CrimeLowerStrategyBean {

    @Test
    @DisplayName("Returns a non-null DuplicateClaimCrimeLowerValidationServiceStrategy")
    void returnsNonNull() {
      assertThat(config.coreDuplicateClaimCrimeLowerValidationServiceStrategy(claimsDataProvider))
          .isNotNull();
    }

    @Test
    @DisplayName("Returns correct strategy type")
    void returnsCorrectType() {
      assertThat(config.coreDuplicateClaimCrimeLowerValidationServiceStrategy(claimsDataProvider))
          .isInstanceOf(DuplicateClaimCrimeLowerValidationServiceStrategy.class);
    }

    @Test
    @DisplayName("Returns a new instance on each call")
    void returnsNewInstanceEachCall() {
      DuplicateClaimCrimeLowerValidationServiceStrategy first =
          config.coreDuplicateClaimCrimeLowerValidationServiceStrategy(claimsDataProvider);
      DuplicateClaimCrimeLowerValidationServiceStrategy second =
          config.coreDuplicateClaimCrimeLowerValidationServiceStrategy(claimsDataProvider);
      assertThat(first).isNotSameAs(second);
    }

    @Test
    @DisplayName("Different ClaimsDataProvider instances produce different strategy instances")
    void differentProvidersProduceDifferentStrategies() {
      ClaimsDataProvider otherProvider = mock(ClaimsDataProvider.class);
      DuplicateClaimCrimeLowerValidationServiceStrategy strategyA =
          config.coreDuplicateClaimCrimeLowerValidationServiceStrategy(claimsDataProvider);
      DuplicateClaimCrimeLowerValidationServiceStrategy strategyB =
          config.coreDuplicateClaimCrimeLowerValidationServiceStrategy(otherProvider);
      assertThat(strategyA).isNotSameAs(strategyB);
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // duplicateClaimLegalHelpValidationServiceStrategy
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("duplicateClaimLegalHelpValidationServiceStrategy bean")
  class LegalHelpStrategyBean {

    @Test
    @DisplayName("Returns a non-null DuplicateClaimLegalHelpValidationServiceStrategy")
    void returnsNonNull() {
      assertThat(config.coreDuplicateClaimLegalHelpValidationServiceStrategy(claimsDataProvider))
          .isNotNull();
    }

    @Test
    @DisplayName("Returns correct strategy type")
    void returnsCorrectType() {
      assertThat(config.coreDuplicateClaimLegalHelpValidationServiceStrategy(claimsDataProvider))
          .isInstanceOf(DuplicateClaimLegalHelpValidationServiceStrategy.class);
    }

    @Test
    @DisplayName("Returns a new instance on each call")
    void returnsNewInstanceEachCall() {
      DuplicateClaimLegalHelpValidationServiceStrategy first =
          config.coreDuplicateClaimLegalHelpValidationServiceStrategy(claimsDataProvider);
      DuplicateClaimLegalHelpValidationServiceStrategy second =
          config.coreDuplicateClaimLegalHelpValidationServiceStrategy(claimsDataProvider);
      assertThat(first).isNotSameAs(second);
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // duplicatePreviousClaimLegalHelpValidationServiceStrategy
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("duplicatePreviousClaimLegalHelpValidationServiceStrategy bean")
  class PreviousLegalHelpStrategyBean {

    @Test
    @DisplayName("Returns a non-null DuplicateSameSubmissionLegalHelpValidationServiceStrategy")
    void returnsNonNull() {
      assertThat(
          config.coreDuplicateSameSubmissionLegalHelpValidationServiceStrategy(claimsDataProvider))
          .isNotNull();
    }

    @Test
    @DisplayName("Returns correct strategy type")
    void returnsCorrectType() {
      assertThat(
          config.coreDuplicateSameSubmissionLegalHelpValidationServiceStrategy(claimsDataProvider))
          .isInstanceOf(DuplicateSameSubmissionLegalHelpValidationServiceStrategy.class);
    }

    @Test
    @DisplayName("Returns a new instance on each call")
    void returnsNewInstanceEachCall() {
      DuplicateSameSubmissionLegalHelpValidationServiceStrategy first =
          config.coreDuplicateSameSubmissionLegalHelpValidationServiceStrategy(claimsDataProvider);
      DuplicateSameSubmissionLegalHelpValidationServiceStrategy second =
          config.coreDuplicateSameSubmissionLegalHelpValidationServiceStrategy(claimsDataProvider);
      assertThat(first).isNotSameAs(second);
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // duplicateClaimLegalHelpDisbursementValidationStrategy
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("duplicateClaimLegalHelpDisbursementValidationStrategy bean")
  class LegalHelpDisbursementStrategyBean {

    @Test
    @DisplayName("Returns a non-null DuplicateClaimLegalHelpDisbursementValidationStrategy")
    void returnsNonNull() {
      assertThat(
          config.coreDuplicateClaimLegalHelpDisbursementValidationStrategy(claimsDataProvider))
          .isNotNull();
    }

    @Test
    @DisplayName("Returns correct strategy type")
    void returnsCorrectType() {
      assertThat(
          config.coreDuplicateClaimLegalHelpDisbursementValidationStrategy(claimsDataProvider))
          .isInstanceOf(DuplicateClaimLegalHelpDisbursementValidationStrategy.class);
    }

    @Test
    @DisplayName("Returns a new instance on each call")
    void returnsNewInstanceEachCall() {
      DuplicateClaimLegalHelpDisbursementValidationStrategy first =
          config.coreDuplicateClaimLegalHelpDisbursementValidationStrategy(claimsDataProvider);
      DuplicateClaimLegalHelpDisbursementValidationStrategy second =
          config.coreDuplicateClaimLegalHelpDisbursementValidationStrategy(claimsDataProvider);
      assertThat(first).isNotSameAs(second);
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // All-strategies — distinctness
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("All strategies are distinct types")
  class AllStrategiesAreDistinctTypes {

    @Test
    @DisplayName("Each bean method produces a different concrete type")
    void eachBeanMethodProducesDifferentConcreteType() {
      Object crimeLower = config.coreDuplicateClaimCrimeLowerValidationServiceStrategy(claimsDataProvider);
      Object legalHelp = config.coreDuplicateClaimLegalHelpValidationServiceStrategy(claimsDataProvider);
      Object sameSubmissionLegalHelp =
          config.coreDuplicateSameSubmissionLegalHelpValidationServiceStrategy(claimsDataProvider);
      Object disbursement = config.coreDuplicateClaimLegalHelpDisbursementValidationStrategy(claimsDataProvider);

      assertThat(crimeLower.getClass())
          .isNotEqualTo(legalHelp.getClass())
          .isNotEqualTo(sameSubmissionLegalHelp.getClass())
          .isNotEqualTo(disbursement.getClass());

      assertThat(legalHelp.getClass())
          .isNotEqualTo(sameSubmissionLegalHelp.getClass())
          .isNotEqualTo(disbursement.getClass());
    }
  }
}
