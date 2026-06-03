package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Tests for {@link DataClaimsApiConfig}, {@link FeeSchemeApiConfig}, and
 * {@link ProviderDetailsApiConfig}.
 *
 * <p>Verifies:
 * <ul>
 *   <li>Default values are applied when no external configuration is provided.
 *   <li>Properties are correctly bound from application properties.
 *   <li>All three configs implement the {@link ApiProperties} contract.
 * </ul>
 */
@DisplayName("API Configuration Properties")
class ApiPropertiesConfigTest {

  // ─────────────────────────────────────────────────────────────────────────
  // DataClaimsApiConfig
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("DataClaimsApiConfig")
  class DataClaimsApiConfigTests {

    @Nested
    @DisplayName("Default values")
    class Defaults {

      @Test
      @DisplayName("authHeader defaults to 'Authorization'")
      void authHeaderDefault() {
        DataClaimsApiConfig config = new DataClaimsApiConfig();
        assertThat(config.getAuthHeader()).isEqualTo("Authorization");
      }

      @Test
      @DisplayName("connectTimeoutMs defaults to 5000")
      void connectTimeoutDefault() {
        DataClaimsApiConfig config = new DataClaimsApiConfig();
        assertThat(config.getConnectTimeoutMs()).isEqualTo(5000);
      }

      @Test
      @DisplayName("readTimeoutMs defaults to 10000")
      void readTimeoutDefault() {
        DataClaimsApiConfig config = new DataClaimsApiConfig();
        assertThat(config.getReadTimeoutMs()).isEqualTo(10000);
      }

      @Test
      @DisplayName("url is null by default")
      void urlIsNullByDefault() {
        DataClaimsApiConfig config = new DataClaimsApiConfig();
        assertThat(config.getUrl()).isNull();
      }

      @Test
      @DisplayName("accessToken is null by default")
      void accessTokenIsNullByDefault() {
        DataClaimsApiConfig config = new DataClaimsApiConfig();
        assertThat(config.getAccessToken()).isNull();
      }
    }

    @Nested
    @DisplayName("Setter / Getter round-trips")
    class SetterGetterRoundTrips {

      @ParameterizedTest(name = "url=''{0}''")
      @DisplayName("url setter and getter are consistent")
      @ValueSource(strings = {"http://localhost:8080", "https://api.example.com", ""})
      void urlRoundTrip(String url) {
        DataClaimsApiConfig config = new DataClaimsApiConfig();
        config.setUrl(url);
        assertThat(config.getUrl()).isEqualTo(url);
      }

      @ParameterizedTest(name = "accessToken=''{0}''")
      @DisplayName("accessToken setter and getter are consistent")
      @ValueSource(strings = {"token-abc", "Bearer xyz", ""})
      void accessTokenRoundTrip(String token) {
        DataClaimsApiConfig config = new DataClaimsApiConfig();
        config.setAccessToken(token);
        assertThat(config.getAccessToken()).isEqualTo(token);
      }

      @ParameterizedTest(name = "authHeader=''{0}''")
      @DisplayName("authHeader setter and getter are consistent")
      @ValueSource(strings = {"Authorization", "X-Custom-Auth", "Bearer"})
      void authHeaderRoundTrip(String header) {
        DataClaimsApiConfig config = new DataClaimsApiConfig();
        config.setAuthHeader(header);
        assertThat(config.getAuthHeader()).isEqualTo(header);
      }

      @ParameterizedTest(name = "connectTimeoutMs={0}")
      @DisplayName("connectTimeoutMs setter and getter are consistent")
      @ValueSource(ints = {0, 1, 1000, 30000, Integer.MAX_VALUE})
      void connectTimeoutRoundTrip(int timeout) {
        DataClaimsApiConfig config = new DataClaimsApiConfig();
        config.setConnectTimeoutMs(timeout);
        assertThat(config.getConnectTimeoutMs()).isEqualTo(timeout);
      }

      @ParameterizedTest(name = "readTimeoutMs={0}")
      @DisplayName("readTimeoutMs setter and getter are consistent")
      @ValueSource(ints = {0, 1, 2000, 60000, Integer.MAX_VALUE})
      void readTimeoutRoundTrip(int timeout) {
        DataClaimsApiConfig config = new DataClaimsApiConfig();
        config.setReadTimeoutMs(timeout);
        assertThat(config.getReadTimeoutMs()).isEqualTo(timeout);
      }
    }

    @Nested
    @DisplayName("Implements ApiProperties contract")
    class ApiPropertiesContract {

      @Test
      @DisplayName("DataClaimsApiConfig is an instance of ApiProperties")
      void implementsApiProperties() {
        assertThat(new DataClaimsApiConfig()).isInstanceOf(ApiProperties.class);
      }
    }

    @Nested
    @DisplayName("Loading from properties")
    @ExtendWith(SpringExtension.class)
    @EnableConfigurationProperties(DataClaimsApiConfig.class)
    @TestPropertySource(properties = {
        "laa.dstew.payments.validator.data-claims-api.url=http://data-claims-api.test",
        "laa.dstew.payments.validator.data-claims-api.access-token=test-data-claims-token",
        "laa.dstew.payments.validator.data-claims-api.auth-header=X-Custom-Auth",
        "laa.dstew.payments.validator.data-claims-api.connect-timeout-ms=1000",
        "laa.dstew.payments.validator.data-claims-api.read-timeout-ms=2000"
    })
    class LoadedFromProperties {

      @Autowired
      private DataClaimsApiConfig config;

      @Test
      @DisplayName("url is bound from properties")
      void urlIsBound() {
        assertThat(config.getUrl()).isEqualTo("http://data-claims-api.test");
      }

      @Test
      @DisplayName("accessToken is bound from properties")
      void accessTokenIsBound() {
        assertThat(config.getAccessToken()).isEqualTo("test-data-claims-token");
      }

      @Test
      @DisplayName("authHeader is bound from properties")
      void authHeaderIsBound() {
        assertThat(config.getAuthHeader()).isEqualTo("X-Custom-Auth");
      }

      @Test
      @DisplayName("connectTimeoutMs is bound from properties")
      void connectTimeoutIsBound() {
        assertThat(config.getConnectTimeoutMs()).isEqualTo(1000);
      }

      @Test
      @DisplayName("readTimeoutMs is bound from properties")
      void readTimeoutIsBound() {
        assertThat(config.getReadTimeoutMs()).isEqualTo(2000);
      }
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // FeeSchemeApiConfig
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("FeeSchemeApiConfig")
  class FeeSchemeApiConfigTests {

    @Nested
    @DisplayName("Default values")
    class Defaults {

      @Test
      @DisplayName("authHeader defaults to 'Authorization'")
      void authHeaderDefault() {
        FeeSchemeApiConfig config = new FeeSchemeApiConfig();
        assertThat(config.getAuthHeader()).isEqualTo("Authorization");
      }

      @Test
      @DisplayName("connectTimeoutMs defaults to 5000")
      void connectTimeoutDefault() {
        FeeSchemeApiConfig config = new FeeSchemeApiConfig();
        assertThat(config.getConnectTimeoutMs()).isEqualTo(5000);
      }

      @Test
      @DisplayName("readTimeoutMs defaults to 10000")
      void readTimeoutDefault() {
        FeeSchemeApiConfig config = new FeeSchemeApiConfig();
        assertThat(config.getReadTimeoutMs()).isEqualTo(10000);
      }

      @Test
      @DisplayName("url is null by default")
      void urlIsNullByDefault() {
        FeeSchemeApiConfig config = new FeeSchemeApiConfig();
        assertThat(config.getUrl()).isNull();
      }

      @Test
      @DisplayName("accessToken is null by default")
      void accessTokenIsNullByDefault() {
        FeeSchemeApiConfig config = new FeeSchemeApiConfig();
        assertThat(config.getAccessToken()).isNull();
      }
    }

    @Nested
    @DisplayName("Setter / Getter round-trips")
    class SetterGetterRoundTrips {

      @ParameterizedTest(name = "url=''{0}''")
      @DisplayName("url setter and getter are consistent")
      @ValueSource(strings = {"http://fee-scheme.local", "https://fee.api.gov.uk", ""})
      void urlRoundTrip(String url) {
        FeeSchemeApiConfig config = new FeeSchemeApiConfig();
        config.setUrl(url);
        assertThat(config.getUrl()).isEqualTo(url);
      }

      @ParameterizedTest(name = "connectTimeoutMs={0}, readTimeoutMs={1}")
      @DisplayName("Both timeout values stored independently")
      @CsvSource({"500,1000", "1000,5000", "0,0", "30000,60000"})
      void timeoutsStoredIndependently(int connectMs, int readMs) {
        FeeSchemeApiConfig config = new FeeSchemeApiConfig();
        config.setConnectTimeoutMs(connectMs);
        config.setReadTimeoutMs(readMs);
        assertThat(config.getConnectTimeoutMs()).isEqualTo(connectMs);
        assertThat(config.getReadTimeoutMs()).isEqualTo(readMs);
      }
    }

    @Nested
    @DisplayName("Implements ApiProperties contract")
    class ApiPropertiesContract {

      @Test
      @DisplayName("FeeSchemeApiConfig is an instance of ApiProperties")
      void implementsApiProperties() {
        assertThat(new FeeSchemeApiConfig()).isInstanceOf(ApiProperties.class);
      }
    }

    @Nested
    @DisplayName("Loading from properties")
    @ExtendWith(SpringExtension.class)
    @EnableConfigurationProperties(FeeSchemeApiConfig.class)
    @TestPropertySource(properties = {
        "laa.dstew.payments.validator.fee-scheme-platform-api.url=http://fee-scheme-api.test",
        "laa.dstew.payments.validator.fee-scheme-platform-api.access-token=test-fee-scheme-token",
        "laa.dstew.payments.validator.fee-scheme-platform-api.auth-header=X-Fee-Auth",
        "laa.dstew.payments.validator.fee-scheme-platform-api.connect-timeout-ms=3000",
        "laa.dstew.payments.validator.fee-scheme-platform-api.read-timeout-ms=6000"
    })
    class LoadedFromProperties {

      @Autowired
      private FeeSchemeApiConfig config;

      @Test
      @DisplayName("url is bound from properties")
      void urlIsBound() {
        assertThat(config.getUrl()).isEqualTo("http://fee-scheme-api.test");
      }

      @Test
      @DisplayName("accessToken is bound from properties")
      void accessTokenIsBound() {
        assertThat(config.getAccessToken()).isEqualTo("test-fee-scheme-token");
      }

      @Test
      @DisplayName("authHeader is bound from properties")
      void authHeaderIsBound() {
        assertThat(config.getAuthHeader()).isEqualTo("X-Fee-Auth");
      }

      @Test
      @DisplayName("connectTimeoutMs is bound from properties")
      void connectTimeoutIsBound() {
        assertThat(config.getConnectTimeoutMs()).isEqualTo(3000);
      }

      @Test
      @DisplayName("readTimeoutMs is bound from properties")
      void readTimeoutIsBound() {
        assertThat(config.getReadTimeoutMs()).isEqualTo(6000);
      }
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // ProviderDetailsApiConfig
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("ProviderDetailsApiConfig")
  class ProviderDetailsApiConfigTests {

    @Nested
    @DisplayName("Default values")
    class Defaults {

      @Test
      @DisplayName("authHeader defaults to 'Authorization'")
      void authHeaderDefault() {
        ProviderDetailsApiConfig config = new ProviderDetailsApiConfig();
        assertThat(config.getAuthHeader()).isEqualTo("Authorization");
      }

      @Test
      @DisplayName("connectTimeoutMs defaults to 5000")
      void connectTimeoutDefault() {
        ProviderDetailsApiConfig config = new ProviderDetailsApiConfig();
        assertThat(config.getConnectTimeoutMs()).isEqualTo(5000);
      }

      @Test
      @DisplayName("readTimeoutMs defaults to 10000")
      void readTimeoutDefault() {
        ProviderDetailsApiConfig config = new ProviderDetailsApiConfig();
        assertThat(config.getReadTimeoutMs()).isEqualTo(10000);
      }

      @Test
      @DisplayName("url is null by default")
      void urlIsNullByDefault() {
        ProviderDetailsApiConfig config = new ProviderDetailsApiConfig();
        assertThat(config.getUrl()).isNull();
      }

      @Test
      @DisplayName("accessToken is null by default")
      void accessTokenIsNullByDefault() {
        ProviderDetailsApiConfig config = new ProviderDetailsApiConfig();
        assertThat(config.getAccessToken()).isNull();
      }
    }

    @Nested
    @DisplayName("Setter / Getter round-trips")
    class SetterGetterRoundTrips {

      @ParameterizedTest(name = "url=''{0}''")
      @DisplayName("url setter and getter are consistent")
      @ValueSource(strings = {"http://providers.local", "https://provider.api.gov.uk", ""})
      void urlRoundTrip(String url) {
        ProviderDetailsApiConfig config = new ProviderDetailsApiConfig();
        config.setUrl(url);
        assertThat(config.getUrl()).isEqualTo(url);
      }

      @ParameterizedTest(name = "connectTimeoutMs={0}, readTimeoutMs={1}")
      @DisplayName("Both timeout values stored independently")
      @CsvSource({"500,1000", "1000,5000", "0,0", "30000,60000"})
      void timeoutsStoredIndependently(int connectMs, int readMs) {
        ProviderDetailsApiConfig config = new ProviderDetailsApiConfig();
        config.setConnectTimeoutMs(connectMs);
        config.setReadTimeoutMs(readMs);
        assertThat(config.getConnectTimeoutMs()).isEqualTo(connectMs);
        assertThat(config.getReadTimeoutMs()).isEqualTo(readMs);
      }
    }

    @Nested
    @DisplayName("Implements ApiProperties contract")
    class ApiPropertiesContract {

      @Test
      @DisplayName("ProviderDetailsApiConfig is an instance of ApiProperties")
      void implementsApiProperties() {
        assertThat(new ProviderDetailsApiConfig()).isInstanceOf(ApiProperties.class);
      }
    }

    @Nested
    @DisplayName("Loading from properties")
    @ExtendWith(SpringExtension.class)
    @EnableConfigurationProperties(ProviderDetailsApiConfig.class)
    @TestPropertySource(properties = {
        "laa.dstew.payments.validator.provider-details-api.url=http://provider-details-api.test",
        "laa.dstew.payments.validator.provider-details-api.access-token=test-provider-token",
        "laa.dstew.payments.validator.provider-details-api.auth-header=X-Provider-Auth",
        "laa.dstew.payments.validator.provider-details-api.connect-timeout-ms=4000",
        "laa.dstew.payments.validator.provider-details-api.read-timeout-ms=8000"
    })
    class LoadedFromProperties {

      @Autowired
      private ProviderDetailsApiConfig config;

      @Test
      @DisplayName("url is bound from properties")
      void urlIsBound() {
        assertThat(config.getUrl()).isEqualTo("http://provider-details-api.test");
      }

      @Test
      @DisplayName("accessToken is bound from properties")
      void accessTokenIsBound() {
        assertThat(config.getAccessToken()).isEqualTo("test-provider-token");
      }

      @Test
      @DisplayName("authHeader is bound from properties")
      void authHeaderIsBound() {
        assertThat(config.getAuthHeader()).isEqualTo("X-Provider-Auth");
      }

      @Test
      @DisplayName("connectTimeoutMs is bound from properties")
      void connectTimeoutIsBound() {
        assertThat(config.getConnectTimeoutMs()).isEqualTo(4000);
      }

      @Test
      @DisplayName("readTimeoutMs is bound from properties")
      void readTimeoutIsBound() {
        assertThat(config.getReadTimeoutMs()).isEqualTo(8000);
      }
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Cross-config consistency checks
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Cross-config consistency")
  class CrossConfigConsistency {

    @Test
    @DisplayName("All three API configs share the same default authHeader value")
    void allConfigsShareDefaultAuthHeader() {
      assertThat(new DataClaimsApiConfig().getAuthHeader())
          .isEqualTo(new FeeSchemeApiConfig().getAuthHeader())
          .isEqualTo(new ProviderDetailsApiConfig().getAuthHeader())
          .isEqualTo("Authorization");
    }

    @Test
    @DisplayName("All three API configs share the same default connectTimeoutMs")
    void allConfigsShareDefaultConnectTimeout() {
      assertThat(new DataClaimsApiConfig().getConnectTimeoutMs())
          .isEqualTo(new FeeSchemeApiConfig().getConnectTimeoutMs())
          .isEqualTo(new ProviderDetailsApiConfig().getConnectTimeoutMs())
          .isEqualTo(5000);
    }

    @Test
    @DisplayName("All three API configs share the same default readTimeoutMs")
    void allConfigsShareDefaultReadTimeout() {
      assertThat(new DataClaimsApiConfig().getReadTimeoutMs())
          .isEqualTo(new FeeSchemeApiConfig().getReadTimeoutMs())
          .isEqualTo(new ProviderDetailsApiConfig().getReadTimeoutMs())
          .isEqualTo(10000);
    }

    @Test
    @DisplayName("Configs are independent instances — mutations do not affect each other")
    void configsAreIndependentInstances() {
      DataClaimsApiConfig dataClaimsConfig = new DataClaimsApiConfig();
      FeeSchemeApiConfig feeSchemeConfig = new FeeSchemeApiConfig();

      dataClaimsConfig.setConnectTimeoutMs(1);

      assertThat(feeSchemeConfig.getConnectTimeoutMs())
          .as("FeeSchemeApiConfig should not be affected by changes to DataClaimsApiConfig")
          .isEqualTo(5000);
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // ValidatorProperties namespace constants
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("ValidatorProperties — namespace constants")
  class ValidatorPropertiesConstants {

    @Test
    @DisplayName("BASE_PREFIX is the expected root namespace")
    void basePrefixIsCorrect() {
      assertThat(ValidatorProperties.BASE_PREFIX).isEqualTo("laa.dstew.payments.validator");
    }

    @Test
    @DisplayName("FEE_SCHEME_PREFIX is derived from BASE_PREFIX")
    void feeSchemePrefix() {
      assertThat(ValidatorProperties.FEE_SCHEME_PREFIX)
          .startsWith(ValidatorProperties.BASE_PREFIX)
          .isEqualTo("laa.dstew.payments.validator.fee-scheme-platform-api");
    }

    @Test
    @DisplayName("DATA_CLAIMS_PREFIX is derived from BASE_PREFIX")
    void dataClaimsPrefix() {
      assertThat(ValidatorProperties.DATA_CLAIMS_PREFIX)
          .startsWith(ValidatorProperties.BASE_PREFIX)
          .isEqualTo("laa.dstew.payments.validator.data-claims-api");
    }

    @Test
    @DisplayName("PROVIDER_DETAILS_PREFIX is derived from BASE_PREFIX")
    void providerDetailsPrefix() {
      assertThat(ValidatorProperties.PROVIDER_DETAILS_PREFIX)
          .startsWith(ValidatorProperties.BASE_PREFIX)
          .isEqualTo("laa.dstew.payments.validator.provider-details-api");
    }

    @Test
    @DisplayName("SERVICE_NAME_PROPERTY is derived from BASE_PREFIX")
    void serviceNameProperty() {
      assertThat(ValidatorProperties.SERVICE_NAME_PROPERTY)
          .startsWith(ValidatorProperties.BASE_PREFIX)
          .isEqualTo("laa.dstew.payments.validator.service-name");
    }

    @Test
    @DisplayName("SUBMISSION_MINIMUM_PERIOD_PROPERTY is derived from BASE_PREFIX")
    void submissionMinimumPeriodProperty() {
      assertThat(ValidatorProperties.SUBMISSION_MINIMUM_PERIOD_PROPERTY)
          .startsWith(ValidatorProperties.BASE_PREFIX)
          .isEqualTo("laa.dstew.payments.validator.submission.minimum-period");
    }

    @Test
    @DisplayName("No two constants share the same value")
    void constantsAreUnique() {
      assertThat(ValidatorProperties.FEE_SCHEME_PREFIX)
          .isNotEqualTo(ValidatorProperties.DATA_CLAIMS_PREFIX)
          .isNotEqualTo(ValidatorProperties.PROVIDER_DETAILS_PREFIX)
          .isNotEqualTo(ValidatorProperties.SERVICE_NAME_PROPERTY)
          .isNotEqualTo(ValidatorProperties.SUBMISSION_MINIMUM_PERIOD_PROPERTY);
    }

    @Test
    @DisplayName("FeeSchemeApiConfig @ConfigurationProperties prefix matches FEE_SCHEME_PREFIX")
    void feeSchemeAnnotationMatchesConstant() {
      var annotation = FeeSchemeApiConfig.class.getAnnotation(
          org.springframework.boot.context.properties.ConfigurationProperties.class);
      assertThat(annotation).isNotNull();
      assertThat(annotation.prefix()).isEqualTo(ValidatorProperties.FEE_SCHEME_PREFIX);
    }

    @Test
    @DisplayName("DataClaimsApiConfig @ConfigurationProperties prefix matches DATA_CLAIMS_PREFIX")
    void dataClaimsAnnotationMatchesConstant() {
      var annotation = DataClaimsApiConfig.class.getAnnotation(
          org.springframework.boot.context.properties.ConfigurationProperties.class);
      assertThat(annotation).isNotNull();
      assertThat(annotation.prefix()).isEqualTo(ValidatorProperties.DATA_CLAIMS_PREFIX);
    }

    @Test
    @DisplayName("ProviderDetailsApiConfig @ConfigurationProperties prefix matches PROVIDER_DETAILS_PREFIX")
    void providerDetailsAnnotationMatchesConstant() {
      var annotation = ProviderDetailsApiConfig.class.getAnnotation(
          org.springframework.boot.context.properties.ConfigurationProperties.class);
      assertThat(annotation).isNotNull();
      assertThat(annotation.prefix()).isEqualTo(ValidatorProperties.PROVIDER_DETAILS_PREFIX);
    }
  }
}
