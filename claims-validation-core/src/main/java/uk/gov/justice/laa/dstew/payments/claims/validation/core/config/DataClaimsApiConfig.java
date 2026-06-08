package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/** Configuration properties for the Data Claims API. */
@ConfigurationProperties(prefix = ValidatorProperties.DATA_CLAIMS_PREFIX)
@Getter
@Setter
public class DataClaimsApiConfig implements ApiProperties {

  /** The base URL for the Data Claims API. */
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
