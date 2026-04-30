package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.DataClaimsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.FeeSchemeClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.ProviderDetailsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.HttpClaimsDataProvider;

/**
 * Tests for {@link WebClientConfig}.
 *
 * <p>Verifies:
 * <ul>
 *   <li>Each {@code @Bean} factory method returns a correctly-typed, non-null client.
 *   <li>{@code createWebClient} throws {@link IllegalStateException} when the URL is null or blank.
 *   <li>The {@code claimsDataProvider} bean returns an {@link HttpClaimsDataProvider} wrapping the
 *       supplied {@link DataClaimsClient}.
 * </ul>
 *
 * <p>Note: Full integration (WebClient creation, HTTP connectivity) is out of scope for unit tests.
 * Bean wiring and URL-validation guard-rails are verified here.
 */
@DisplayName("WebClientConfig")
class WebClientConfigTest {

  private WebClientConfig webClientConfig;

  @BeforeEach
  void setUp() {
    webClientConfig = new WebClientConfig();
  }

  // ─────────────────────────────────────────────────────────────────────────
  // feeSchemeClient bean
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("feeSchemeClient bean")
  class FeeSchemeClientBean {

    @Test
    @DisplayName("Returns a non-null FeeSchemeClient when URL is configured")
    void returnsNonNullClient() {
      FeeSchemeApiConfig properties = configuredFeeSchemeApiConfig();
      FeeSchemeClient client = webClientConfig.feeSchemeClient(properties);
      assertThat(client).isNotNull();
    }

    @Test
    @DisplayName("Returns a FeeSchemeClient instance")
    void returnsCorrectType() {
      FeeSchemeApiConfig properties = configuredFeeSchemeApiConfig();
      assertThat(webClientConfig.feeSchemeClient(properties))
          .isInstanceOf(FeeSchemeClient.class);
    }

    @ParameterizedTest(name = "URL=''{0}'' → IllegalStateException")
    @DisplayName("Throws IllegalStateException when URL is null or blank")
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    void throwsWhenUrlIsNullOrBlank(String url) {
      FeeSchemeApiConfig properties = new FeeSchemeApiConfig();
      properties.setUrl(url);
      properties.setAccessToken("token");

      assertThatThrownBy(() -> webClientConfig.feeSchemeClient(properties))
          .isInstanceOf(IllegalStateException.class)
          .hasMessageContaining("API URL is not configured");
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // providerDetailsClient bean
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("providerDetailsClient bean")
  class ProviderDetailsClientBean {

    @Test
    @DisplayName("Returns a non-null ProviderDetailsClient when URL is configured")
    void returnsNonNullClient() {
      ProviderDetailsApiConfig properties = configuredProviderDetailsApiConfig();
      ProviderDetailsClient client = webClientConfig.providerDetailsClient(properties);
      assertThat(client).isNotNull();
    }

    @Test
    @DisplayName("Returns a ProviderDetailsClient instance")
    void returnsCorrectType() {
      ProviderDetailsApiConfig properties = configuredProviderDetailsApiConfig();
      assertThat(webClientConfig.providerDetailsClient(properties))
          .isInstanceOf(ProviderDetailsClient.class);
    }

    @ParameterizedTest(name = "URL=''{0}'' → IllegalStateException")
    @DisplayName("Throws IllegalStateException when URL is null or blank")
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void throwsWhenUrlIsNullOrBlank(String url) {
      ProviderDetailsApiConfig properties = new ProviderDetailsApiConfig();
      properties.setUrl(url);
      properties.setAccessToken("token");

      assertThatThrownBy(() -> webClientConfig.providerDetailsClient(properties))
          .isInstanceOf(IllegalStateException.class)
          .hasMessageContaining("API URL is not configured");
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // dataClaimsClient bean
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("dataClaimsClient bean")
  class DataClaimsClientBean {

    @Test
    @DisplayName("Returns a non-null DataClaimsClient when URL is configured")
    void returnsNonNullClient() {
      DataClaimsApiConfig properties = configuredDataClaimsApiConfig();
      DataClaimsClient client = webClientConfig.dataClaimsClient(properties);
      assertThat(client).isNotNull();
    }

    @Test
    @DisplayName("Returns a DataClaimsClient instance")
    void returnsCorrectType() {
      DataClaimsApiConfig properties = configuredDataClaimsApiConfig();
      assertThat(webClientConfig.dataClaimsClient(properties))
          .isInstanceOf(DataClaimsClient.class);
    }

    @ParameterizedTest(name = "URL=''{0}'' → IllegalStateException")
    @DisplayName("Throws IllegalStateException when URL is null or blank")
    @NullAndEmptySource
    @ValueSource(strings = {"   "})
    void throwsWhenUrlIsNullOrBlank(String url) {
      DataClaimsApiConfig properties = new DataClaimsApiConfig();
      properties.setUrl(url);
      properties.setAccessToken("token");

      assertThatThrownBy(() -> webClientConfig.dataClaimsClient(properties))
          .isInstanceOf(IllegalStateException.class)
          .hasMessageContaining("API URL is not configured");
    }

    @Test
    @DisplayName("Distinct DataClaimsClient instances are returned for different configs")
    void distinctClientsForDifferentConfigs() {
      DataClaimsApiConfig configA = configuredDataClaimsApiConfig();
      DataClaimsApiConfig configB = configuredDataClaimsApiConfig();
      configB.setUrl("http://another-api.local");

      DataClaimsClient clientA = webClientConfig.dataClaimsClient(configA);
      DataClaimsClient clientB = webClientConfig.dataClaimsClient(configB);

      assertThat(clientA).isNotSameAs(clientB);
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // claimsDataProvider bean
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("claimsDataProvider bean")
  class ClaimsDataProviderBean {

    @Test
    @DisplayName("Returns a non-null ClaimsDataProvider")
    void returnsNonNull() {
      DataClaimsClient mockClient = mock(DataClaimsClient.class);
      ClaimsDataProvider provider = webClientConfig.claimsDataProvider(mockClient);
      assertThat(provider).isNotNull();
    }

    @Test
    @DisplayName("Returns an HttpClaimsDataProvider")
    void returnsHttpClaimsDataProvider() {
      DataClaimsClient mockClient = mock(DataClaimsClient.class);
      ClaimsDataProvider provider = webClientConfig.claimsDataProvider(mockClient);
      assertThat(provider).isInstanceOf(HttpClaimsDataProvider.class);
    }

    @Test
    @DisplayName("Returns a new instance on each call")
    void returnsNewInstanceOnEachCall() {
      DataClaimsClient mockClient = mock(DataClaimsClient.class);
      ClaimsDataProvider first = webClientConfig.claimsDataProvider(mockClient);
      ClaimsDataProvider second = webClientConfig.claimsDataProvider(mockClient);
      assertThat(first).isNotSameAs(second);
    }

    @Test
    @DisplayName("Different DataClaimsClient instances produce different ClaimsDataProvider instances")
    void differentClientsProduceDifferentProviders() {
      DataClaimsClient clientA = mock(DataClaimsClient.class);
      DataClaimsClient clientB = mock(DataClaimsClient.class);

      ClaimsDataProvider providerA = webClientConfig.claimsDataProvider(clientA);
      ClaimsDataProvider providerB = webClientConfig.claimsDataProvider(clientB);

      assertThat(providerA).isNotSameAs(providerB);
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // URL validation — error message content
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("URL validation — error message")
  class UrlValidationErrorMessage {

    @Test
    @DisplayName("Error message contains the config class simple name for DataClaimsApiConfig")
    void errorMessageContainsDataClaimsApiConfigClassName() {
      DataClaimsApiConfig properties = new DataClaimsApiConfig();
      properties.setUrl(null);
      assertThatThrownBy(() -> webClientConfig.dataClaimsClient(properties))
          .isInstanceOf(IllegalStateException.class)
          .hasMessageContaining("DataClaimsApiConfig");
    }

    @Test
    @DisplayName("Error message contains the config class simple name for FeeSchemeApiConfig")
    void errorMessageContainsFeeSchemeApiConfigClassName() {
      FeeSchemeApiConfig properties = new FeeSchemeApiConfig();
      properties.setUrl(null);
      assertThatThrownBy(() -> webClientConfig.feeSchemeClient(properties))
          .isInstanceOf(IllegalStateException.class)
          .hasMessageContaining("FeeSchemeApiConfig");
    }

    @Test
    @DisplayName("Error message contains the config class simple name for ProviderDetailsApiConfig")
    void errorMessageContainsProviderDetailsApiConfigClassName() {
      ProviderDetailsApiConfig properties = new ProviderDetailsApiConfig();
      properties.setUrl(null);
      assertThatThrownBy(() -> webClientConfig.providerDetailsClient(properties))
          .isInstanceOf(IllegalStateException.class)
          .hasMessageContaining("ProviderDetailsApiConfig");
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Helpers
  // ─────────────────────────────────────────────────────────────────────────

  private static FeeSchemeApiConfig configuredFeeSchemeApiConfig() {
    FeeSchemeApiConfig config = new FeeSchemeApiConfig();
    config.setUrl("http://fee-scheme-api.local");
    config.setAccessToken("test-token");
    config.setAuthHeader("Authorization");
    config.setConnectTimeoutMs(5000);
    config.setReadTimeoutMs(10000);
    return config;
  }

  private static ProviderDetailsApiConfig configuredProviderDetailsApiConfig() {
    ProviderDetailsApiConfig config = new ProviderDetailsApiConfig();
    config.setUrl("http://provider-details-api.local");
    config.setAccessToken("test-token");
    config.setAuthHeader("Authorization");
    config.setConnectTimeoutMs(5000);
    config.setReadTimeoutMs(10000);
    return config;
  }

  private static DataClaimsApiConfig configuredDataClaimsApiConfig() {
    DataClaimsApiConfig config = new DataClaimsApiConfig();
    config.setUrl("http://data-claims-api.local");
    config.setAccessToken("test-token");
    config.setAuthHeader("Authorization");
    config.setConnectTimeoutMs(5000);
    config.setReadTimeoutMs(10000);
    return config;
  }
}
