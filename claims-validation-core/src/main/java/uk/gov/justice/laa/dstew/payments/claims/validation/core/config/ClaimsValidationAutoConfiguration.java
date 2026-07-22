package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import io.github.resilience4j.retry.RetryRegistry;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.DataClaimsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.FeeSchemeClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.ProviderDetailsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.FeeSchemeProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ProviderDetailsProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.HttpClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.HttpFeeSchemeProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.HttpProviderDetailsProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.service.ValidationService;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidation;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.CaseDatesClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.ClaimSchemaValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.ClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.ClientDateOfBirthClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.DisbursementClaimStartDateValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.DisbursementsClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.DuplicateClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.EffectiveCategoryOfLawClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.MandatoryFieldClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.MatterTypeClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.OutcomeCodeClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.ScheduleReferenceClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.StageReachedClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.UniqueFileNumberClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateClaimCrimeLowerValidationServiceStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateClaimLegalHelpDisbursementValidationStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateClaimLegalHelpValidationServiceStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateClaimValidationStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateSameSubmissionLegalHelpValidationServiceStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidation;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.DuplicateSubmissionValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.NilSubmissionValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.SubmissionPeriodValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.SubmissionSchemaValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.SubmissionStatusValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.SubmissionValidator;

/**
 * Spring Boot autoconfiguration entry point for the claims-validation-core library.
 *
 * <p>This class is the single controlled activation point for all library beans. It replaces the
 * previous use of &#64;Component and &#64;Service annotations scattered across internal classes,
 * which caused bean collisions when the library was imported into other Spring Boot applications.
 *
 * <h2>How it works</h2>
 * <ul>
 *   <li>Registered via
 *       {@code META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports},
 *       so it is activated automatically when the library is on the classpath — without any
 *       component scanning of the library's internal packages.</li>
 *   <li>Every bean carries {@link ConditionalOnMissingBean} so importing applications can
 *       override any individual piece (e.g. provide their own {@link ClaimsDataProvider}
 *       backed by a repository rather than HTTP).</li>
 *   <li>Sub-configurations have been merged directly into this class to avoid {@code @Import}
 *       ordering issues with {@link ConditionalOnMissingBean}. All bean wiring is in one
 *       place.</li>
 *   <li>All beans use an explicit {@code core} prefix in their bean name (e.g.
 *       {@code coreMandatoryFieldsRegistry}) to avoid collisions with same-named classes in
 *       consuming services. The {@link ConditionalOnMissingBean} condition still checks by
 *       <em>type</em>, so a consuming service registering its own bean of the same type will
 *       still suppress the core one correctly.</li>
 * </ul>
 *
 * <h2>Required application properties</h2>
 * <pre>
 * laa.dstew.payments.validator.data-claims-api.url=...
 * laa.dstew.payments.validator.fee-scheme-platform-api.url=...
 * laa.dstew.payments.validator.provider-details-api.url=...
 * laa.dstew.payments.validator.submission.minimum-period=Apr-2013
 * laa.dstew.payments.validator.service-name=my-service   # optional,
 * defaults to spring.application.name
 * </pre>
 */
@AutoConfiguration
@EnableConfigurationProperties({DataClaimsApiConfig.class, FeeSchemeApiConfig.class,
    ProviderDetailsApiConfig.class})
public class ClaimsValidationAutoConfiguration {

  // ─────────────────────────────────────────────────────────────────────────
  // HTTP Providers
  // ─────────────────────────────────────────────────────────────────────────

  /**
   * HTTP-backed implementation of the {@link ProviderDetailsProvider} contract.
   *
   * <p>The underlying {@link ProviderDetailsClient} is created inline (via the
   * {@link WebClientConfig} factory) rather than exposed as a separate bean — it is an internal
   * implementation detail of this provider with no other consumers. Automatically skipped if the
   * importing application registers its own {@link ProviderDetailsProvider} bean (e.g. one backed
   * by the consumer's own client and cache).
   */
  @Bean("coreHttpProviderDetailsProvider")
  @ConditionalOnMissingBean(ProviderDetailsProvider.class)
  public ProviderDetailsProvider coreHttpProviderDetailsProvider(
      WebClientConfig webClientConfig, ProviderDetailsApiConfig properties,
      RetryRegistry retryRegistry) {
    return new HttpProviderDetailsProvider(
        webClientConfig.providerDetailsClient(properties), retryRegistry);
  }

  /**
   * HTTP-backed implementation of the {@link FeeSchemeProvider} contract.
   *
   * <p>The underlying {@link FeeSchemeClient} is created inline (via the {@link WebClientConfig}
   * factory) rather than exposed as a separate bean — it is an internal implementation detail of
   * this provider with no other consumers. Automatically skipped if the importing application
   * registers its own {@link FeeSchemeProvider} bean (e.g. one backed by the consumer's own client
   * and cache).
   */
  @Bean("coreHttpFeeSchemeProvider")
  @ConditionalOnMissingBean(FeeSchemeProvider.class)
  public FeeSchemeProvider coreHttpFeeSchemeProvider(
      WebClientConfig webClientConfig, FeeSchemeApiConfig properties,
      RetryRegistry retryRegistry) {
    return new HttpFeeSchemeProvider(
        webClientConfig.feeSchemeClient(properties), retryRegistry);
  }

  /**
   * HTTP-backed provider for accessing claims data from the Data Claims API.
   *
   * <p>The underlying {@link DataClaimsClient} is created inline (via the {@link WebClientConfig}
   * factory) rather than being exposed as a separate bean — it is an internal implementation detail
   * of this provider and has no other consumers. Consequently, when an importing application
   * registers its own {@link ClaimsDataProvider} (e.g. a repository-backed one), this method is
   * skipped entirely and <em>no</em> {@code WebClient} is ever configured against the Data Claims
   * API.
   *
   * <p>Importers that need a customised HTTP client should build and register their own
   * {@link ClaimsDataProvider}, wrapping a {@link DataClaimsClient} of their choosing.
   */
  @Bean("coreClaimsDataProvider")
  @ConditionalOnMissingBean(ClaimsDataProvider.class)
  public ClaimsDataProvider coreClaimsDataProvider(WebClientConfig webClientConfig,
      DataClaimsApiConfig properties) {
    return new HttpClaimsDataProvider(webClientConfig.dataClaimsClient(properties));
  }

  // ─────────────────────────────────────────────────────────────────────────
  // WebClient configuration (service-name is configurable per importing app)
  // ─────────────────────────────────────────────────────────────────────────

  /**
   * Provides the {@link WebClientConfig} bean, injecting the configurable service name used in
   * the {@code X-Service-Name} header on all outbound HTTP calls. Defaults to the value of
   * {@code spring.application.name} if {@code laa.dstew.payments.validator.service-name}
   * is not set.
   */
  @Bean("coreWebClientConfig")
  @ConditionalOnMissingBean(WebClientConfig.class)
  public WebClientConfig coreWebClientConfig(
      @Value("${" + ValidatorProperties.SERVICE_NAME_PROPERTY 
              + ":${spring.application.name:claims-validation-core}}")
      String serviceName) {
    return new WebClientConfig(serviceName);
  }



  // ─────────────────────────────────────────────────────────────────────────
  // Claim validators
  // ─────────────────────────────────────────────────────────────────────────

  @Bean("coreClaimSchemaValidator")
  @ConditionalOnMissingBean(ClaimSchemaValidator.class)
  public ClaimSchemaValidator coreClaimSchemaValidator() {
    return new ClaimSchemaValidator();
  }

  @Bean("coreMandatoryFieldClaimValidator")
  @ConditionalOnMissingBean(MandatoryFieldClaimValidator.class)
  public MandatoryFieldClaimValidator coreMandatoryFieldClaimValidator() {
    return new MandatoryFieldClaimValidator();
  }

  @Bean("coreCaseDatesClaimValidator")
  @ConditionalOnMissingBean(CaseDatesClaimValidator.class)
  public CaseDatesClaimValidator coreCaseDatesClaimValidator() {
    return new CaseDatesClaimValidator();
  }

  @Bean("coreClientDateOfBirthClaimValidator")
  @ConditionalOnMissingBean(ClientDateOfBirthClaimValidator.class)
  public ClientDateOfBirthClaimValidator coreClientDateOfBirthClaimValidator() {
    return new ClientDateOfBirthClaimValidator();
  }

  @Bean("coreDisbursementClaimStartDateValidator")
  @ConditionalOnMissingBean(DisbursementClaimStartDateValidator.class)
  public DisbursementClaimStartDateValidator coreDisbursementClaimStartDateValidator() {
    return new DisbursementClaimStartDateValidator();
  }

  @Bean("coreDisbursementsClaimValidator")
  @ConditionalOnMissingBean(DisbursementsClaimValidator.class)
  public DisbursementsClaimValidator coreDisbursementsClaimValidator() {
    return new DisbursementsClaimValidator();
  }

  @Bean("coreMatterTypeClaimValidator")
  @ConditionalOnMissingBean(MatterTypeClaimValidator.class)
  public MatterTypeClaimValidator coreMatterTypeClaimValidator() {
    return new MatterTypeClaimValidator();
  }

  @Bean("coreOutcomeCodeClaimValidator")
  @ConditionalOnMissingBean(OutcomeCodeClaimValidator.class)
  public OutcomeCodeClaimValidator coreOutcomeCodeClaimValidator() {
    return new OutcomeCodeClaimValidator();
  }

  @Bean("coreScheduleReferenceClaimValidator")
  @ConditionalOnMissingBean(ScheduleReferenceClaimValidator.class)
  public ScheduleReferenceClaimValidator coreScheduleReferenceClaimValidator() {
    return new ScheduleReferenceClaimValidator();
  }

  @Bean("coreStageReachedClaimValidator")
  @ConditionalOnMissingBean(StageReachedClaimValidator.class)
  public StageReachedClaimValidator coreStageReachedClaimValidator() {
    return new StageReachedClaimValidator();
  }

  @Bean("coreUniqueFileNumberClaimValidator")
  @ConditionalOnMissingBean(UniqueFileNumberClaimValidator.class)
  public UniqueFileNumberClaimValidator coreUniqueFileNumberClaimValidator() {
    return new UniqueFileNumberClaimValidator();
  }

  @Bean("coreEffectiveCategoryOfLawClaimValidator")
  @ConditionalOnMissingBean(EffectiveCategoryOfLawClaimValidator.class)
  public EffectiveCategoryOfLawClaimValidator coreEffectiveCategoryOfLawClaimValidator(
      FeeSchemeProvider httpFeeSchemeProvider,
      ProviderDetailsProvider httpProviderDetailsProvider) {
    return new EffectiveCategoryOfLawClaimValidator(httpFeeSchemeProvider,
        httpProviderDetailsProvider);
  }

  @Bean("coreDuplicateClaimValidator")
  @ConditionalOnMissingBean(DuplicateClaimValidator.class)
  public DuplicateClaimValidator coreDuplicateClaimValidator(
      List<DuplicateClaimValidationStrategy> strategyList) {
    return new DuplicateClaimValidator(strategyList);
  }

  // Duplicate claim strategies

  /** Crime Lower duplicate claim validation strategy. */
  @Bean("coreDuplicateClaimCrimeLowerValidationServiceStrategy")
  @ConditionalOnMissingBean(DuplicateClaimCrimeLowerValidationServiceStrategy.class)
  public DuplicateClaimCrimeLowerValidationServiceStrategy
      coreDuplicateClaimCrimeLowerValidationServiceStrategy(
          ClaimsDataProvider claimsDataProvider) {
    return new DuplicateClaimCrimeLowerValidationServiceStrategy(claimsDataProvider);
  }

  /** Legal Help duplicate claim validation strategy. */
  @Bean("coreDuplicateClaimLegalHelpValidationServiceStrategy")
  @ConditionalOnMissingBean(DuplicateClaimLegalHelpValidationServiceStrategy.class)
  public DuplicateClaimLegalHelpValidationServiceStrategy
      coreDuplicateClaimLegalHelpValidationServiceStrategy(
          ClaimsDataProvider claimsDataProvider) {
    return new DuplicateClaimLegalHelpValidationServiceStrategy(claimsDataProvider);
  }

  /** Legal Help previous claim duplicate validation strategy. */
  @Bean("coreDuplicateSameSubmissionLegalHelpValidationServiceStrategy")
  @ConditionalOnMissingBean(DuplicateSameSubmissionLegalHelpValidationServiceStrategy.class)
  public DuplicateSameSubmissionLegalHelpValidationServiceStrategy
      coreDuplicateSameSubmissionLegalHelpValidationServiceStrategy(
          ClaimsDataProvider claimsDataProvider) {
    return new DuplicateSameSubmissionLegalHelpValidationServiceStrategy(claimsDataProvider);
  }

  /** Legal Help disbursement duplicate claim validation strategy. */
  @Bean("coreDuplicateClaimLegalHelpDisbursementValidationStrategy")
  @ConditionalOnMissingBean(DuplicateClaimLegalHelpDisbursementValidationStrategy.class)
  public DuplicateClaimLegalHelpDisbursementValidationStrategy
      coreDuplicateClaimLegalHelpDisbursementValidationStrategy(
          ClaimsDataProvider claimsDataProvider) {
    return new DuplicateClaimLegalHelpDisbursementValidationStrategy(claimsDataProvider);
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Submission validators
  // ─────────────────────────────────────────────────────────────────────────

  @Bean("coreSubmissionSchemaValidator")
  @ConditionalOnMissingBean(SubmissionSchemaValidator.class)
  public SubmissionSchemaValidator coreSubmissionSchemaValidator() {
    return new SubmissionSchemaValidator();
  }

  @Bean("coreSubmissionStatusValidator")
  @ConditionalOnMissingBean(SubmissionStatusValidator.class)
  public SubmissionStatusValidator coreSubmissionStatusValidator() {
    return new SubmissionStatusValidator();
  }

  @Bean("coreNilSubmissionValidator")
  @ConditionalOnMissingBean(NilSubmissionValidator.class)
  public NilSubmissionValidator coreNilSubmissionValidator() {
    return new NilSubmissionValidator();
  }

  @Bean("coreSubmissionPeriodValidator")
  @ConditionalOnMissingBean(SubmissionPeriodValidator.class)
  public SubmissionPeriodValidator coreSubmissionPeriodValidator(
      @Value("${" + ValidatorProperties.SUBMISSION_MINIMUM_PERIOD_PROPERTY + "}")
      String minimumPeriod) {
    return new SubmissionPeriodValidator(minimumPeriod);
  }

  @Bean("coreDuplicateSubmissionValidator")
  @ConditionalOnMissingBean(DuplicateSubmissionValidator.class)
  public DuplicateSubmissionValidator coreDuplicateSubmissionValidator(
      ClaimsDataProvider claimsDataProvider) {
    return new DuplicateSubmissionValidator(claimsDataProvider);
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Validation orchestrators
  // ─────────────────────────────────────────────────────────────────────────

  /**
   * Assembles the claim validation pipeline from all registered {@link ClaimValidator} beans.
   * Importers can add or replace individual validators before this bean is constructed.
   */
  @Bean("coreClaimValidation")
  @ConditionalOnMissingBean(ClaimValidation.class)
  public ClaimValidation coreClaimValidation(
      List<ClaimValidator> claimValidators,
      FeeSchemeProvider httpFeeSchemeProvider) {
    return new ClaimValidation(claimValidators, httpFeeSchemeProvider);
  }

  /**
   * Assembles the submission validation pipeline from all registered {@link SubmissionValidator}
   * beans.
   */
  @Bean("coreSubmissionValidation")
  @ConditionalOnMissingBean(SubmissionValidation.class)
  public SubmissionValidation coreSubmissionValidation(
      List<SubmissionValidator> submissionValidators) {
    return new SubmissionValidation(submissionValidators);
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Primary service façade
  // ─────────────────────────────────────────────────────────────────────────

  /**
   * The primary entry point for validation. Importers that need to wrap or decorate
   * {@link ValidationService} can register their own bean; this one will be skipped.
   */
  @Bean("coreValidationService")
  @ConditionalOnMissingBean(ValidationService.class)
  public ValidationService coreValidationService(ClaimValidation claimValidation,
      SubmissionValidation submissionValidation) {
    return new ValidationService(claimValidation, submissionValidation);
  }
}
