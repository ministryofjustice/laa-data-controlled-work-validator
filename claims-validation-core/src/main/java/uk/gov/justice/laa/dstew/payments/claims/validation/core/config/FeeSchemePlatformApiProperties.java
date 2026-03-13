package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/** Configuration properties for the Fee Scheme Platform API. */
@Configuration
@ConfigurationProperties(prefix = "laa.fee-scheme-platform-api")
@Getter
@Setter
public class FeeSchemePlatformApiProperties implements ApiProperties {

  /** The base URL for the Fee Scheme Platform API. */
  private String url;

  /** The access token for API authentication. */
  private String accessToken;

  /** The name of the authorization header. Defaults to "Authorization". */
  private String authHeader = "Authorization";

  /** The connection timeout in milliseconds. Defaults to 5000. */
  private int connectTimeoutMs = 5000;

  /** The read/response timeout in milliseconds. Defaults to 10000. */
  private int readTimeoutMs = 10000;
}
