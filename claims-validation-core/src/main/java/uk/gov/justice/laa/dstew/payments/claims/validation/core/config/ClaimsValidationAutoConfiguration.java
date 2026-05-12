package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import io.github.resilience4j.retry.RetryRegistry;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.DataClaimsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.FeeSchemeClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.ProviderDetailsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ClaimsDataProvider;
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
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate.DuplicateClaimValidationStrategy;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidation;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.DuplicateSubmissionValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.NilSubmissionValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.SubmissionPeriodValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.SubmissionSchemaValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.SubmissionStatusValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.SubmissionValidator;

/**
 * Spring Boot auto-configuration entry point for the claims-validation-core library.
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
 *   <li>Sub-configurations ({@link WebClientConfig} and {@link DuplicateClaimValidationConfig})
 *       are imported explicitly via {@link Import}, not via component scan.</li>
 * </ul>
 *
 * <h2>Required application properties</h2>
 * <pre>
 * laa.data-claims-api.url=...
 * laa.fee-scheme-platform-api.url=...
 * laa.provider-details-api.url=...
 * submission.validation.minimum-period=Apr-2013
 * claims.validation.service-name=my-service   # optional, defaults to spring.application.name
 * </pre>
 */
@AutoConfiguration
@EnableConfigurationProperties({DataClaimsApiConfig.class, FeeSchemeApiConfig.class,
    ProviderDetailsApiConfig.class})
@Import(DuplicateClaimValidationConfig.class)
public class ClaimsValidationAutoConfiguration {

  // ─────────────────────────────────────────────────────────────────────────
  // Registries
  // ─────────────────────────────────────────────────────────────────────────

  /** Provides the registry of mandatory fields per area of law. */
  @Bean
  @ConditionalOnMissingBean
  public MandatoryFieldsRegistry mandatoryFieldsRegistry() {
    return new MandatoryFieldsRegistry();
  }

  /** Provides the registry of field exclusions for disbursement-only claims. */
  @Bean
  @ConditionalOnMissingBean
  public ExclusionsRegistry exclusionsRegistry() {
    return new ExclusionsRegistry();
  }

  // ─────────────────────────────────────────────────────────────────────────
  // HTTP Providers
  // ─────────────────────────────────────────────────────────────────────────

  /**
   * HTTP-backed provider for resolving provider firm schedules.
   * Automatically skipped if the importing application registers its own bean.
   */
  @Bean
  @ConditionalOnMissingBean
  public HttpProviderDetailsProvider httpProviderDetailsProvider(
      ProviderDetailsClient providerDetailsClient, RetryRegistry retryRegistry) {
    return new HttpProviderDetailsProvider(providerDetailsClient, retryRegistry);
  }

  /**
   * HTTP-backed provider for resolving fee scheme details.
   * Automatically skipped if the importing application registers its own bean.
   */
  @Bean
  @ConditionalOnMissingBean
  public HttpFeeSchemeProvider httpFeeSchemeProvider(
      FeeSchemeClient feeSchemeClient, RetryRegistry retryRegistry) {
    return new HttpFeeSchemeProvider(feeSchemeClient, retryRegistry);
  }

  /**
   * HTTP-backed provider for accessing claims data from the Data Claims API.
   * Services that embed this library with direct database access should register their own
   * {@link ClaimsDataProvider} bean instead, which causes this bean to be skipped entirely.
   */
  @Bean
  @ConditionalOnMissingBean(ClaimsDataProvider.class)
  public ClaimsDataProvider claimsDataProvider(DataClaimsClient dataClaimsClient) {
    return new HttpClaimsDataProvider(dataClaimsClient);
  }

  // ─────────────────────────────────────────────────────────────────────────
  // WebClient configuration (service-name is configurable per importing app)
  // ─────────────────────────────────────────────────────────────────────────

  /**
   * Provides the {@link WebClientConfig} bean, injecting the configurable service name used in
   * the {@code X-Service-Name} header on all outbound HTTP calls. Defaults to the value of
   * {@code spring.application.name} if {@code claims.validation.service-name} is not set.
   */
  @Bean
  @ConditionalOnMissingBean
  public WebClientConfig webClientConfig(
      @Value("${claims.validation.service-name:${spring.application.name:claims-validation-core}}")
      String serviceName) {
    return new WebClientConfig(serviceName);
  }

  /**
   * Creates a {@link FeeSchemeClient} via the {@link WebClientConfig} factory.
   * Automatically skipped if the importing application provides its own {@link FeeSchemeClient}.
   */
  @Bean
  @ConditionalOnMissingBean
  public FeeSchemeClient feeSchemeClient(WebClientConfig webClientConfig,
      FeeSchemeApiConfig properties) {
    return webClientConfig.feeSchemeClient(properties);
  }

  /**
   * Creates a {@link ProviderDetailsClient} via the {@link WebClientConfig} factory.
   * Automatically skipped if the importing application provides its own bean.
   */
  @Bean
  @ConditionalOnMissingBean
  public ProviderDetailsClient providerDetailsClient(WebClientConfig webClientConfig,
      ProviderDetailsApiConfig properties) {
    return webClientConfig.providerDetailsClient(properties);
  }

  /**
   * Creates a {@link DataClaimsClient} via the {@link WebClientConfig} factory.
   * Automatically skipped if the importing application provides its own bean.
   */
  @Bean
  @ConditionalOnMissingBean
  public DataClaimsClient dataClaimsClient(WebClientConfig webClientConfig,
      DataClaimsApiConfig properties) {
    return webClientConfig.dataClaimsClient(properties);
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Claim validators
  // ─────────────────────────────────────────────────────────────────────────

  @Bean
  @ConditionalOnMissingBean
  public ClaimSchemaValidator claimSchemaValidator() {
    return new ClaimSchemaValidator();
  }

  @Bean
  @ConditionalOnMissingBean
  public MandatoryFieldClaimValidator mandatoryFieldClaimValidator(
      MandatoryFieldsRegistry mandatoryFieldsRegistry, ExclusionsRegistry exclusionsRegistry) {
    return new MandatoryFieldClaimValidator(mandatoryFieldsRegistry, exclusionsRegistry);
  }

  @Bean
  @ConditionalOnMissingBean
  public CaseDatesClaimValidator caseDatesClaimValidator() {
    return new CaseDatesClaimValidator();
  }

  @Bean
  @ConditionalOnMissingBean
  public ClientDateOfBirthClaimValidator clientDateOfBirthClaimValidator() {
    return new ClientDateOfBirthClaimValidator();
  }

  @Bean
  @ConditionalOnMissingBean
  public DisbursementClaimStartDateValidator disbursementClaimStartDateValidator() {
    return new DisbursementClaimStartDateValidator();
  }

  @Bean
  @ConditionalOnMissingBean
  public DisbursementsClaimValidator disbursementsClaimValidator() {
    return new DisbursementsClaimValidator();
  }

  @Bean
  @ConditionalOnMissingBean
  public MatterTypeClaimValidator matterTypeClaimValidator() {
    return new MatterTypeClaimValidator();
  }

  @Bean
  @ConditionalOnMissingBean
  public OutcomeCodeClaimValidator outcomeCodeClaimValidator() {
    return new OutcomeCodeClaimValidator();
  }

  @Bean
  @ConditionalOnMissingBean
  public ScheduleReferenceClaimValidator scheduleReferenceClaimValidator() {
    return new ScheduleReferenceClaimValidator();
  }

  @Bean
  @ConditionalOnMissingBean
  public StageReachedClaimValidator stageReachedClaimValidator() {
    return new StageReachedClaimValidator();
  }

  @Bean
  @ConditionalOnMissingBean
  public UniqueFileNumberClaimValidator uniqueFileNumberClaimValidator() {
    return new UniqueFileNumberClaimValidator();
  }

  @Bean
  @ConditionalOnMissingBean
  public EffectiveCategoryOfLawClaimValidator effectiveCategoryOfLawClaimValidator(
      HttpFeeSchemeProvider httpFeeSchemeProvider,
      HttpProviderDetailsProvider httpProviderDetailsProvider) {
    return new EffectiveCategoryOfLawClaimValidator(httpFeeSchemeProvider,
        httpProviderDetailsProvider);
  }

  @Bean
  @ConditionalOnMissingBean
  public DuplicateClaimValidator duplicateClaimValidator(
      List<DuplicateClaimValidationStrategy> strategyList) {
    return new DuplicateClaimValidator(strategyList);
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Submission validators
  // ─────────────────────────────────────────────────────────────────────────

  @Bean
  @ConditionalOnMissingBean
  public SubmissionSchemaValidator submissionSchemaValidator() {
    return new SubmissionSchemaValidator();
  }

  @Bean
  @ConditionalOnMissingBean
  public SubmissionStatusValidator submissionStatusValidator() {
    return new SubmissionStatusValidator();
  }

  @Bean
  @ConditionalOnMissingBean
  public NilSubmissionValidator nilSubmissionValidator() {
    return new NilSubmissionValidator();
  }

  @Bean
  @ConditionalOnMissingBean
  public SubmissionPeriodValidator submissionPeriodValidator(
      @Value("${submission.validation.minimum-period}") String minimumPeriod) {
    return new SubmissionPeriodValidator(minimumPeriod);
  }

  @Bean
  @ConditionalOnMissingBean
  public DuplicateSubmissionValidator duplicateSubmissionValidator(
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
  @Bean
  @ConditionalOnMissingBean
  public ClaimValidation claimValidation(List<ClaimValidator> claimValidators) {
    return new ClaimValidation(claimValidators);
  }

  /**
   * Assembles the submission validation pipeline from all registered {@link SubmissionValidator}
   * beans.
   */
  @Bean
  @ConditionalOnMissingBean
  public SubmissionValidation submissionValidation(List<SubmissionValidator> submissionValidators) {
    return new SubmissionValidation(submissionValidators);
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Primary service façade
  // ─────────────────────────────────────────────────────────────────────────

  /**
   * The primary entry point for validation. Importers that need to wrap or decorate
   * {@link ValidationService} can register their own bean; this one will be skipped.
   */
  @Bean
  @ConditionalOnMissingBean
  public ValidationService validationService(ClaimValidation claimValidation,
      SubmissionValidation submissionValidation) {
    return new ValidationService(claimValidation, submissionValidation);
  }
}
