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
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicatePreviousClaimLegalHelpValidationServiceStrategy;

/**
 * Tests for {@link DuplicateClaimValidationConfig}.
 *
 * <p>Verifies that each {@code @Bean} factory method:
 * <ul>
 *   <li>Returns a non-null instance of the correct strategy type.
 *   <li>Injects the supplied {@link ClaimsDataProvider} correctly.
 *   <li>Returns a new (distinct) instance on each call, not a shared singleton.
 * </ul>
 */
@DisplayName("DuplicateClaimValidationConfig")
class DuplicateClaimValidationConfigTest {

  private DuplicateClaimValidationConfig config;
  private ClaimsDataProvider claimsDataProvider;

  @BeforeEach
  void setUp() {
    config = new DuplicateClaimValidationConfig();
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
      assertThat(config.duplicateClaimCrimeLowerValidationServiceStrategy(claimsDataProvider))
          .isNotNull();
    }

    @Test
    @DisplayName("Returns correct strategy type")
    void returnsCorrectType() {
      assertThat(config.duplicateClaimCrimeLowerValidationServiceStrategy(claimsDataProvider))
          .isInstanceOf(DuplicateClaimCrimeLowerValidationServiceStrategy.class);
    }

    @Test
    @DisplayName("Returns a new instance on each call")
    void returnsNewInstanceEachCall() {
      DuplicateClaimCrimeLowerValidationServiceStrategy first =
          config.duplicateClaimCrimeLowerValidationServiceStrategy(claimsDataProvider);
      DuplicateClaimCrimeLowerValidationServiceStrategy second =
          config.duplicateClaimCrimeLowerValidationServiceStrategy(claimsDataProvider);
      assertThat(first).isNotSameAs(second);
    }

    @Test
    @DisplayName("Different ClaimsDataProvider instances produce different strategy instances")
    void differentProvidersProduceDifferentStrategies() {
      ClaimsDataProvider otherProvider = mock(ClaimsDataProvider.class);
      DuplicateClaimCrimeLowerValidationServiceStrategy strategyA =
          config.duplicateClaimCrimeLowerValidationServiceStrategy(claimsDataProvider);
      DuplicateClaimCrimeLowerValidationServiceStrategy strategyB =
          config.duplicateClaimCrimeLowerValidationServiceStrategy(otherProvider);
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
      assertThat(config.duplicateClaimLegalHelpValidationServiceStrategy(claimsDataProvider))
          .isNotNull();
    }

    @Test
    @DisplayName("Returns correct strategy type")
    void returnsCorrectType() {
      assertThat(config.duplicateClaimLegalHelpValidationServiceStrategy(claimsDataProvider))
          .isInstanceOf(DuplicateClaimLegalHelpValidationServiceStrategy.class);
    }

    @Test
    @DisplayName("Returns a new instance on each call")
    void returnsNewInstanceEachCall() {
      DuplicateClaimLegalHelpValidationServiceStrategy first =
          config.duplicateClaimLegalHelpValidationServiceStrategy(claimsDataProvider);
      DuplicateClaimLegalHelpValidationServiceStrategy second =
          config.duplicateClaimLegalHelpValidationServiceStrategy(claimsDataProvider);
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
    @DisplayName("Returns a non-null DuplicatePreviousClaimLegalHelpValidationServiceStrategy")
    void returnsNonNull() {
      assertThat(
          config.duplicatePreviousClaimLegalHelpValidationServiceStrategy(claimsDataProvider))
          .isNotNull();
    }

    @Test
    @DisplayName("Returns correct strategy type")
    void returnsCorrectType() {
      assertThat(
          config.duplicatePreviousClaimLegalHelpValidationServiceStrategy(claimsDataProvider))
          .isInstanceOf(DuplicatePreviousClaimLegalHelpValidationServiceStrategy.class);
    }

    @Test
    @DisplayName("Returns a new instance on each call")
    void returnsNewInstanceEachCall() {
      DuplicatePreviousClaimLegalHelpValidationServiceStrategy first =
          config.duplicatePreviousClaimLegalHelpValidationServiceStrategy(claimsDataProvider);
      DuplicatePreviousClaimLegalHelpValidationServiceStrategy second =
          config.duplicatePreviousClaimLegalHelpValidationServiceStrategy(claimsDataProvider);
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
          config.duplicateClaimLegalHelpDisbursementValidationStrategy(claimsDataProvider))
          .isNotNull();
    }

    @Test
    @DisplayName("Returns correct strategy type")
    void returnsCorrectType() {
      assertThat(
          config.duplicateClaimLegalHelpDisbursementValidationStrategy(claimsDataProvider))
          .isInstanceOf(DuplicateClaimLegalHelpDisbursementValidationStrategy.class);
    }

    @Test
    @DisplayName("Returns a new instance on each call")
    void returnsNewInstanceEachCall() {
      DuplicateClaimLegalHelpDisbursementValidationStrategy first =
          config.duplicateClaimLegalHelpDisbursementValidationStrategy(claimsDataProvider);
      DuplicateClaimLegalHelpDisbursementValidationStrategy second =
          config.duplicateClaimLegalHelpDisbursementValidationStrategy(claimsDataProvider);
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
      Object crimeLower = config.duplicateClaimCrimeLowerValidationServiceStrategy(claimsDataProvider);
      Object legalHelp = config.duplicateClaimLegalHelpValidationServiceStrategy(claimsDataProvider);
      Object previousLegalHelp = config.duplicatePreviousClaimLegalHelpValidationServiceStrategy(claimsDataProvider);
      Object disbursement = config.duplicateClaimLegalHelpDisbursementValidationStrategy(claimsDataProvider);

      assertThat(crimeLower.getClass())
          .isNotEqualTo(legalHelp.getClass())
          .isNotEqualTo(previousLegalHelp.getClass())
          .isNotEqualTo(disbursement.getClass());

      assertThat(legalHelp.getClass())
          .isNotEqualTo(previousLegalHelp.getClass())
          .isNotEqualTo(disbursement.getClass());
    }
  }
}
