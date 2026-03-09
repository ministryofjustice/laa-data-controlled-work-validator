package uk.gov.justice.laa.dstew.payments.claims.validation.config;

import io.netty.channel.ChannelOption;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

/**
 * Configuration for WebClient used for outbound REST calls to external validation services.
 */
@Configuration
public class WebClientConfig {

  @Value("${external.validation.base-url:http://localhost:8080}")
  private String baseUrl;

  @Value("${external.validation.connect-timeout-ms:5000}")
  private int connectTimeoutMs;

  @Value("${external.validation.read-timeout-ms:10000}")
  private int readTimeoutMs;

  /**
   * Creates a WebClient bean configured with timeouts for external validation calls.
   *
   * @return the configured WebClient
   */
  @Bean
  public WebClient externalValidationWebClient() {
    HttpClient httpClient = HttpClient.create()
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeoutMs)
        .responseTimeout(Duration.ofMillis(readTimeoutMs));

    return WebClient.builder()
        .baseUrl(baseUrl)
        .clientConnector(new ReactorClientHttpConnector(httpClient))
        .build();
  }
}

