package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules.duplicate.DuplicateClaimCrimeLowerValidationServiceStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules.duplicate.DuplicateClaimLegalHelpDisbursementValidationStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules.duplicate.DuplicateClaimLegalHelpValidationServiceStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules.duplicate.DuplicatePreviousClaimLegalHelpValidationServiceStrategy;

/**
 * Explicit configuration for duplicate claim validation strategies, wiring in the
 * ClaimsDataProvider.
 * This makes the dependencies and wiring clear and testable.
 */
@Configuration
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
