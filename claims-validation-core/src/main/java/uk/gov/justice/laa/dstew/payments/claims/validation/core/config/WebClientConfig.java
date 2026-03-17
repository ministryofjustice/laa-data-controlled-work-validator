package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import io.netty.channel.ChannelOption;
import java.time.Duration;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
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

/**
 * Configuration for WebClient and HTTP service clients used for outbound REST calls to external
 * APIs.
 */
@Slf4j
@Configuration
public class WebClientConfig {

  private static final String REQUEST_ID_HEADER = "X-Request-Id";
  private static final String SERVICE_NAME_HEADER = "X-Service-Name";
  private static final String SERVICE_NAME = "laa-data-claims-validation-api";

  /**
   * Creates a {@link FeeSchemeClient} bean to communicate with the Fee Scheme Platform API.
   *
   * @param properties The configuration properties for the Fee Scheme Platform API
   * @return An instance of {@link FeeSchemeClient}
   */
  private FeeSchemeClient createRawFeeSchemeClient(
      final FeeSchemePlatformApiProperties properties) {
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
  public FeeSchemeClient feeSchemeClient(final FeeSchemePlatformApiProperties properties) {
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
      final ProviderDetailsApiProperties properties) {
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
  public DataClaimsClient dataClaimsClient(final DataClaimsApiProperties properties) {
    final WebClient webClient = createWebClient(properties);
    final WebClientAdapter webClientAdapter = WebClientAdapter.create(webClient);
    HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(webClientAdapter).build();
    return factory.createClient(DataClaimsClient.class);
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
   * Creates an ExchangeFilterFunction that logs outgoing requests and adds a unique request ID
   * header.
   */
  private static ExchangeFilterFunction logRequest() {
    return ExchangeFilterFunction.ofRequestProcessor(
        clientRequest -> {
          String requestId = UUID.randomUUID().toString();
          log.info(
              "WebClient Request: {} {} [requestId={}]",
              clientRequest.method(),
              clientRequest.url(),
              requestId);
          // Add the requestId and service name as headers for correlation
          return Mono.just(
              ClientRequest.from(clientRequest)
                  .header(REQUEST_ID_HEADER, requestId)
                  .header(SERVICE_NAME_HEADER, SERVICE_NAME)
                  .build());
        });
  }

  /** Creates an ExchangeFilterFunction that logs responses, including the request ID if present. */
  private static ExchangeFilterFunction logResponse() {
    return ExchangeFilterFunction.ofResponseProcessor(
        clientResponse -> {
          String requestId =
              clientResponse.headers().header(REQUEST_ID_HEADER).stream().findFirst().orElse("N/A");
          log.info(
              "WebClient Response: Status {} Headers: {} [requestId={}]",
              clientResponse.statusCode(),
              clientResponse.headers().asHttpHeaders(),
              requestId);
          return Mono.just(clientResponse);
        });
  }
}
