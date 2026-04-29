package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import io.netty.channel.ChannelOption;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.CachedFeeSchemeClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.DataClaimsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.FeeSchemeClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.ProviderDetailsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ClaimsDataProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.HttpClaimsDataProvider;

/**
 * Configuration for WebClient and HTTP service clients used for outbound REST calls to external
 * APIs.
 */
@Slf4j
@Configuration
public class WebClientConfig {

  private static final String SERVICE_NAME_HEADER = "X-Service-Name";
  private static final String SERVICE_NAME = "laa-data-claims-validation-api";

  /**
   * Creates a {@link FeeSchemeClient} bean to communicate with the Fee Scheme Platform API.
   *
   * @param properties The configuration properties for the Fee Scheme Platform API
   * @return An instance of {@link FeeSchemeClient}
   */
  private FeeSchemeClient createRawFeeSchemeClient(
      final FeeSchemeApiConfig properties) {
    final WebClient webClient = createWebClient(properties);
    final WebClientAdapter webClientAdapter = WebClientAdapter.create(webClient);
    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(webClientAdapter).build();
    return factory.createClient(FeeSchemeClient.class);
  }

  /**
   * Expose a cached FeeSchemeClient bean using Caffeine for response caching. Cache duration is set
   * to 5 minutes for demonstration. TODO move duration to config.
   */
  @Bean
  public FeeSchemeClient feeSchemeClient(final FeeSchemeApiConfig properties) {
    FeeSchemeClient rawClient = createRawFeeSchemeClient(properties);
    return new CachedFeeSchemeClient(rawClient, 5);
  }

  /**
   * Creates a {@link ProviderDetailsClient} bean to communicate with the Provider Details API.
   *
   * @param properties The configuration properties for the Provider Details API
   * @return An instance of {@link ProviderDetailsClient}
   */
  @Bean
  public ProviderDetailsClient providerDetailsClient(
      final ProviderDetailsApiConfig properties) {
    final WebClient webClient = createWebClient(properties);
    final WebClientAdapter webClientAdapter = WebClientAdapter.create(webClient);
    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(webClientAdapter).build();
    return factory.createClient(ProviderDetailsClient.class);
  }

  /**
   * Creates a {@link DataClaimsClient} bean to communicate with the Data Claims API.
   *
   * @param properties The configuration properties for the Data Claims API
   * @return An instance of {@link DataClaimsClient}
   */
  @Bean
  public DataClaimsClient dataClaimsClient(final DataClaimsApiConfig properties) {
    final WebClient webClient = createWebClient(properties);
    final WebClientAdapter webClientAdapter = WebClientAdapter.create(webClient);
    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(webClientAdapter).build();
    return factory.createClient(DataClaimsClient.class);
  }

  /**
   * Creates a {@link ClaimsDataProvider} bean backed by HTTP, using {@link HttpClaimsDataProvider}
   * to adapt {@link DataClaimsClient} to the transport-agnostic provider interface.
   *
   * <p>This bean is only registered if no other {@link ClaimsDataProvider} bean is present in the
   * application context. Services that embed this library with direct database access (e.g. the
   * Claims API itself) should register their own {@link ClaimsDataProvider} implementation (e.g. a
   * repository-backed one), which will cause this bean to be skipped entirely — preventing any
   * self-referential HTTP call or unnecessary {@link DataClaimsClient} configuration.
   *
   * @param dataClaimsClient the HTTP REST client for the Data Claims API
   * @return an {@link HttpClaimsDataProvider} wrapping the given client
   */
  @Bean
  @ConditionalOnMissingBean(ClaimsDataProvider.class)
  public ClaimsDataProvider claimsDataProvider(final DataClaimsClient dataClaimsClient) {
    return new HttpClaimsDataProvider(dataClaimsClient);
  }

  /**
   * Creates a WebClient instance using the provided configuration properties.
   *
   * @param apiProperties The configuration properties for the API
   * @return A configured WebClient instance
   */
  private WebClient createWebClient(final ApiProperties apiProperties) {
    String url = apiProperties.getUrl();
    if (url == null || url.isBlank()) {
      log.error("API URL is not configured for {}", apiProperties.getClass().getSimpleName());
      throw new IllegalStateException(
          "API URL is not configured for " + apiProperties.getClass().getSimpleName());
    }

    log.info(
        "Creating WebClient with baseUrl: {}, authHeader: {}", url, apiProperties.getAuthHeader());

    // Configure exchange strategies with increased buffer size for large responses
    final ExchangeStrategies strategies =
        ExchangeStrategies.builder()
            .codecs(
                configurer ->
                    configurer
                        .defaultCodecs()
                        // 50 MB to cope with large bulk upload responses
                        .maxInMemorySize(50 * 1024 * 1024))
            .build();

    // Configure HTTP client with timeouts
    HttpClient httpClient =
        HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, apiProperties.getConnectTimeoutMs())
            .responseTimeout(Duration.ofMillis(apiProperties.getReadTimeoutMs()));

    return WebClient.builder()
        .baseUrl(apiProperties.getUrl())
        .defaultHeader(apiProperties.getAuthHeader(), apiProperties.getAccessToken())
        .exchangeStrategies(strategies)
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .filter(logRequest())
        .filter(logResponse())
        .build();
  }

  /**
   * Creates an ExchangeFilterFunction that logs outgoing requests.
   */
  private static ExchangeFilterFunction logRequest() {
    return ExchangeFilterFunction.ofRequestProcessor(
        clientRequest -> {
          log.info(
              "WebClient Request: {} {}",
              clientRequest.method(),
              clientRequest.url());
          // Add the requestId and service name as headers for correlation
          return Mono.just(
              ClientRequest.from(clientRequest)
                  .header(SERVICE_NAME_HEADER, SERVICE_NAME)
                  .build());
        });
  }

  /** Creates an ExchangeFilterFunction that logs responses. */
  private static ExchangeFilterFunction logResponse() {
    return ExchangeFilterFunction.ofResponseProcessor(
        clientResponse -> {
          log.info(
              "WebClient Response: Status {}",
              clientResponse.statusCode());
          return Mono.just(clientResponse);
        });
  }
}
