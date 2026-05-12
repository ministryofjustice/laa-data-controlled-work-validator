package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import org.springframework.context.annotation.Bean;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateClaimCrimeLowerValidationServiceStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateClaimLegalHelpDisbursementValidationStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateClaimLegalHelpValidationServiceStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicatePreviousClaimLegalHelpValidationServiceStrategy;

/**
 * Explicit configuration for duplicate claim validation strategies, wiring in the
 * ClaimsDataProvider.
 * This makes the dependencies and wiring clear and testable.
 */
public class DuplicateClaimValidationConfig {

  /**
   * Provides the DuplicateClaimCrimeLowerValidationServiceStrategy bean.
   */
  @Bean
  public DuplicateClaimCrimeLowerValidationServiceStrategy
      duplicateClaimCrimeLowerValidationServiceStrategy(
      ClaimsDataProvider claimsDataProvider
  ) {
    return new DuplicateClaimCrimeLowerValidationServiceStrategy(
        claimsDataProvider
    );
  }

  /**
   * Provides the DuplicateClaimLegalHelpValidationServiceStrategy bean.
   */
  @Bean
  public DuplicateClaimLegalHelpValidationServiceStrategy
      duplicateClaimLegalHelpValidationServiceStrategy(
      ClaimsDataProvider claimsDataProvider
  ) {
    return new DuplicateClaimLegalHelpValidationServiceStrategy(
        claimsDataProvider
    );
  }

  /**
   * Provides the DuplicatePreviousClaimLegalHelpValidationServiceStrategy bean.
   */
  @Bean
  public DuplicatePreviousClaimLegalHelpValidationServiceStrategy
      duplicatePreviousClaimLegalHelpValidationServiceStrategy(
      ClaimsDataProvider claimsDataProvider
  ) {
    return new DuplicatePreviousClaimLegalHelpValidationServiceStrategy(
        claimsDataProvider
    );
  }

  /**
   * Provides the DuplicateClaimLegalHelpDisbursementValidationStrategy bean.
   */
  @Bean
  public DuplicateClaimLegalHelpDisbursementValidationStrategy
      duplicateClaimLegalHelpDisbursementValidationStrategy(
      ClaimsDataProvider claimsDataProvider
  ) {
    return new DuplicateClaimLegalHelpDisbursementValidationStrategy(
        claimsDataProvider
    );
  }
}
