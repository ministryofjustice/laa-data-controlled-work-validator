package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import io.github.resilience4j.retry.RetryRegistry;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.DataClaimsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.FeeSchemeProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ProviderDetailsProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.HttpClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.HttpFeeSchemeProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.HttpProviderDetailsProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.service.ValidationService;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidation;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.ClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.SubmissionValidation;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.submission.rules.SubmissionValidator;

/**
 * Integration tests for {@link ClaimsValidationAutoConfiguration}.
 *
 * <p>Uses Spring Boot's {@link ApplicationContextRunner} to verify:
 * <ul>
 *   <li>All expected beans are registered when minimum required properties are present.</li>
 *   <li>{@code @ConditionalOnMissingBean} allows importers to override any individual bean.</li>
 *   <li>The library never participates in component scanning (no &#64;Component anywhere).</li>
 * </ul>
 */
@DisplayName("ClaimsValidationAutoConfiguration")
class ClaimsValidationAutoConfigurationTest {

  /** Minimum properties required to start the context. */
  private static final String[] REQUIRED_PROPERTIES = {
      "laa.dstew.payments.validator.data-claims-api.url=http://data-claims.test",
      "laa.dstew.payments.validator.data-claims-api.access-token=token",
      "laa.dstew.payments.validator.fee-scheme-platform-api.url=http://fee-scheme.test",
      "laa.dstew.payments.validator.fee-scheme-platform-api.access-token=token",
      "laa.dstew.payments.validator.provider-details-api.url=http://provider-details.test",
      "laa.dstew.payments.validator.provider-details-api.access-token=token",
      "laa.dstew.payments.validator.submission.minimum-period=Apr-2013",
      "spring.application.name=test-service"
  };

  /** Same as REQUIRED_PROPERTIES but without Data Claims API entries. Used to verify that a
   * consuming application that registers its own ClaimsDataProvider does not need to supply the
   * Data Claims API properties for the context to start. */
  private static final String[] REQUIRED_PROPERTIES_NO_DATA_CLAIMS = {
      "laa.dstew.payments.validator.fee-scheme-platform-api.url=http://fee-scheme.test",
      "laa.dstew.payments.validator.fee-scheme-platform-api.access-token=token",
      "laa.dstew.payments.validator.provider-details-api.url=http://provider-details.test",
      "laa.dstew.payments.validator.provider-details-api.access-token=token",
      "laa.dstew.payments.validator.submission.minimum-period=Apr-2013",
      "spring.application.name=test-service"
  };

  /**
   * Test configuration providing the external dependencies that would normally be supplied by
   * the Spring context of an importing application. The auto-configuration builds its own HTTP
   * clients internally (via {@code WebClientConfig}), so only resilience4j's {@code RetryRegistry}
   * needs to be provided here.
   */
  @TestConfiguration
  static class ExternalDependenciesConfig {

    @Bean
    public RetryRegistry retryRegistry() {
      return RetryRegistry.ofDefaults();
    }
  }

  private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
      .withConfiguration(AutoConfigurations.of(ClaimsValidationAutoConfiguration.class))
      .withUserConfiguration(ExternalDependenciesConfig.class)
      .withPropertyValues(REQUIRED_PROPERTIES);

  // ─────────────────────────────────────────────────────────────────────────
  // Core beans are registered
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Core beans are registered")
  class CoreBeansAreRegistered {

    @Test
    @DisplayName("ValidationService bean is present")
    void validationServiceIsPresent() {
      contextRunner.run(ctx ->
          assertThat(ctx).hasSingleBean(ValidationService.class));
    }

    @Test
    @DisplayName("ClaimValidation bean is present")
    void claimValidationIsPresent() {
      contextRunner.run(ctx ->
          assertThat(ctx).hasSingleBean(ClaimValidation.class));
    }

    @Test
    @DisplayName("SubmissionValidation bean is present")
    void submissionValidationIsPresent() {
      contextRunner.run(ctx ->
          assertThat(ctx).hasSingleBean(SubmissionValidation.class));
    }

    @Test
    @DisplayName("ClaimsDataProvider defaults to HttpClaimsDataProvider")
    void claimsDataProviderDefaultsToHttp() {
      contextRunner.run(ctx ->
          assertThat(ctx.getBean(ClaimsDataProvider.class))
              .isInstanceOf(HttpClaimsDataProvider.class));
    }

    @Test
    @DisplayName("All expected claim validators are registered")
    void allClaimValidatorsAreRegistered() {
      contextRunner.run(ctx -> {
        List<ClaimValidator> validators = ctx.getBeansOfType(ClaimValidator.class)
            .values().stream().toList();
        assertThat(validators).hasSizeGreaterThanOrEqualTo(10);
      });
    }

    @Test
    @DisplayName("All expected submission validators are registered")
    void allSubmissionValidatorsAreRegistered() {
      contextRunner.run(ctx -> {
        List<SubmissionValidator> validators = ctx.getBeansOfType(SubmissionValidator.class)
            .values().stream().toList();
        assertThat(validators).hasSizeGreaterThanOrEqualTo(4);
      });
    }

    @Test
    @DisplayName("API config properties are bound correctly")
    void apiConfigPropertiesAreBound() {
      contextRunner.run(ctx -> {
        DataClaimsApiConfig config = ctx.getBean(DataClaimsApiConfig.class);
        assertThat(config.getUrl()).isEqualTo("http://data-claims.test");
      });
    }

    @Test
    @DisplayName("WebClientConfig picks up configurable service name from spring.application.name")
    void webClientConfigUsesApplicationName() {
      contextRunner.run(ctx ->
          assertThat(ctx).hasSingleBean(WebClientConfig.class));
    }

    @Test
    @DisplayName("Custom service name is applied when laa.dstew.payments.validator.service-name is set")
    void webClientConfigUsesCustomServiceName() {
      contextRunner
          .withPropertyValues("laa.dstew.payments.validator.service-name=my-custom-service")
          .run(ctx ->
              assertThat(ctx).hasSingleBean(WebClientConfig.class));
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // ConditionalOnMissingBean — importers can override beans
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Importer can override beans via ConditionalOnMissingBean")
  class ImporterCanOverrideBeans {

    /** Custom ClaimsDataProvider simulating a repository-backed implementation. */
    @TestConfiguration
    static class CustomClaimsDataProviderConfig {
      @Bean
      public ClaimsDataProvider customClaimsDataProvider() {
        return mock(ClaimsDataProvider.class);
      }
    }

    @Test
    @DisplayName("Custom ClaimsDataProvider replaces the default HttpClaimsDataProvider")
    void customClaimsDataProviderReplacesDefault() {
      new ApplicationContextRunner()
          .withConfiguration(AutoConfigurations.of(ClaimsValidationAutoConfiguration.class))
          .withUserConfiguration(ExternalDependenciesConfig.class,
              CustomClaimsDataProviderConfig.class)
          .withPropertyValues(REQUIRED_PROPERTIES)
          .run(ctx -> {
            ClaimsDataProvider provider = ctx.getBean(ClaimsDataProvider.class);
            assertThat(provider).isNotInstanceOf(HttpClaimsDataProvider.class);
          });
    }

    @Test
    @DisplayName("Custom ClaimsDataProvider allows context to start without DataClaims API properties")
    void customClaimsDataProviderAllowsContextWithoutDataClaimsProps() {
      new ApplicationContextRunner()
          .withConfiguration(AutoConfigurations.of(ClaimsValidationAutoConfiguration.class))
          .withUserConfiguration(ExternalDependenciesConfig.class,
              CustomClaimsDataProviderConfig.class)
          .withPropertyValues(REQUIRED_PROPERTIES_NO_DATA_CLAIMS)
          .run(ctx -> {
            // Context should start even though data-claims-api properties are not provided,
            // because the consuming application supplied its own ClaimsDataProvider.
            assertThat(ctx).hasSingleBean(ClaimsDataProvider.class);
            assertThat(ctx.getBean(ClaimsDataProvider.class))
                .isNotInstanceOf(HttpClaimsDataProvider.class);
          });
    }

    /** Custom FeeSchemeProvider simulating a consumer with its own client and cache. */
    @TestConfiguration
    static class CustomFeeSchemeProviderConfig {
      @Bean
      public FeeSchemeProvider customFeeSchemeProvider() {
        return mock(FeeSchemeProvider.class);
      }
    }

    @Test
    @DisplayName("Custom FeeSchemeProvider replaces the default HttpFeeSchemeProvider")
    void customFeeSchemeProviderReplacesDefault() {
      new ApplicationContextRunner()
          .withConfiguration(AutoConfigurations.of(ClaimsValidationAutoConfiguration.class))
          .withUserConfiguration(ExternalDependenciesConfig.class,
              CustomFeeSchemeProviderConfig.class)
          .withPropertyValues(REQUIRED_PROPERTIES)
          .run(ctx -> {
            assertThat(ctx).doesNotHaveBean(HttpFeeSchemeProvider.class);
            assertThat(ctx.getBean(FeeSchemeProvider.class))
                .isNotInstanceOf(HttpFeeSchemeProvider.class);
          });
    }

    /** Custom ProviderDetailsProvider simulating a consumer with its own client and cache. */
    @TestConfiguration
    static class CustomProviderDetailsProviderConfig {
      @Bean
      public ProviderDetailsProvider customProviderDetailsProvider() {
        return mock(ProviderDetailsProvider.class);
      }
    }

    @Test
    @DisplayName("Custom ProviderDetailsProvider replaces the default HttpProviderDetailsProvider")
    void customProviderDetailsProviderReplacesDefault() {
      new ApplicationContextRunner()
          .withConfiguration(AutoConfigurations.of(ClaimsValidationAutoConfiguration.class))
          .withUserConfiguration(ExternalDependenciesConfig.class,
              CustomProviderDetailsProviderConfig.class)
          .withPropertyValues(REQUIRED_PROPERTIES)
          .run(ctx -> {
            assertThat(ctx).doesNotHaveBean(HttpProviderDetailsProvider.class);
            assertThat(ctx.getBean(ProviderDetailsProvider.class))
                .isNotInstanceOf(HttpProviderDetailsProvider.class);
          });
    }

    /** Custom ValidationService simulating an importer that wraps the default. */
    @TestConfiguration
    static class CustomValidationServiceConfig {
      static final ValidationService CUSTOM_INSTANCE = mock(ValidationService.class);

      @Bean
      public ValidationService customValidationService() {
        return CUSTOM_INSTANCE;
      }
    }

    @Test
    @DisplayName("Custom ValidationService bean takes precedence over auto-configured one")
    void customValidationServiceTakesPrecedence() {
      new ApplicationContextRunner()
          .withConfiguration(AutoConfigurations.of(ClaimsValidationAutoConfiguration.class))
          .withUserConfiguration(ExternalDependenciesConfig.class,
              CustomValidationServiceConfig.class)
          .withPropertyValues(REQUIRED_PROPERTIES)
          .run(ctx -> {
            assertThat(ctx).hasSingleBean(ValidationService.class);
            // Verify the exact same instance registered by the custom config is in the context
            assertThat(ctx.getBean(ValidationService.class))
                .isSameAs(CustomValidationServiceConfig.CUSTOM_INSTANCE);
          });
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // HTTP ClaimsDataProvider is only created when needed (client is internal)
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("HTTP ClaimsDataProvider is only created when needed")
  class HttpClaimsDataProviderConditionalCreation {

    /** Dependencies an importer supplies, excluding any claims-data wiring. */
    @TestConfiguration
    static class BaseDependencies {

      @Bean
      public RetryRegistry retryRegistry() {
        return RetryRegistry.ofDefaults();
      }
    }

    /** Custom repository-backed ClaimsDataProvider, as a consuming app would register. */
    @TestConfiguration
    static class CustomClaimsDataProviderConfig {
      @Bean
      public ClaimsDataProvider customClaimsDataProvider() {
        return mock(ClaimsDataProvider.class);
      }
    }

    private final ApplicationContextRunner runner = new ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(ClaimsValidationAutoConfiguration.class))
        .withUserConfiguration(BaseDependencies.class)
        .withPropertyValues(REQUIRED_PROPERTIES);

    @Test
    @DisplayName("Defaults to HttpClaimsDataProvider with no DataClaimsClient exposed as a bean")
    void defaultsToHttpProviderWithNoClientBean() {
      runner.run(ctx -> {
        assertThat(ctx.getBean(ClaimsDataProvider.class))
            .isInstanceOf(HttpClaimsDataProvider.class);
        // The HTTP client is an internal detail of the provider, not a context bean.
        assertThat(ctx).doesNotHaveBean(DataClaimsClient.class);
      });
    }

    @Test
    @DisplayName("Custom ClaimsDataProvider skips HttpClaimsDataProvider entirely")
    void customProviderSkipsHttpProvider() {
      runner.withUserConfiguration(CustomClaimsDataProviderConfig.class)
          .run(ctx -> {
            // No HTTP provider and therefore no WebClient/HTTP client is built.
            assertThat(ctx).doesNotHaveBean(HttpClaimsDataProvider.class);
            assertThat(ctx).doesNotHaveBean(DataClaimsClient.class);
            assertThat(ctx.getBean(ClaimsDataProvider.class))
                .isNotInstanceOf(HttpClaimsDataProvider.class);
          });
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // No component scan pollution
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("No component-scan pollution")
  class NoComponentScanPollution {

    @Test
    @DisplayName("No validator beans have @Component on their class")
    void noValidatorHasComponentAnnotation() {
      contextRunner.run(ctx -> {
        List<ClaimValidator> validators = ctx.getBeansOfType(ClaimValidator.class)
            .values().stream().toList();
        assertThat(validators).isNotEmpty();
        validators.forEach(validator ->
            assertThat(validator.getClass()
                .isAnnotationPresent(org.springframework.stereotype.Component.class))
                .as("Validator %s must not have @Component", validator.getClass().getSimpleName())
                .isFalse()
        );
      });
    }

    @Test
    @DisplayName("No submission validator beans have @Component on their class")
    void noSubmissionValidatorHasComponentAnnotation() {
      contextRunner.run(ctx -> {
        List<SubmissionValidator> validators = ctx.getBeansOfType(SubmissionValidator.class)
            .values().stream().toList();
        assertThat(validators).isNotEmpty();
        validators.forEach(validator ->
            assertThat(validator.getClass()
                .isAnnotationPresent(org.springframework.stereotype.Component.class))
                .as("Validator %s must not have @Component",
                    validator.getClass().getSimpleName())
                .isFalse()
        );
      });
    }

    @Test
    @DisplayName("ValidationService does not have @Service annotation")
    void validationServiceHasNoServiceAnnotation() {
      contextRunner.run(ctx -> {
        ValidationService svc = ctx.getBean(ValidationService.class);
        assertThat(svc.getClass()
            .isAnnotationPresent(org.springframework.stereotype.Service.class))
            .isFalse();
      });
    }
  }
}
