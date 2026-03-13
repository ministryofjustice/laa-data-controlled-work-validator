package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

/**
 * Interface for API configuration properties. Each external API client should have a corresponding
 * properties class implementing this interface.
 */
public interface ApiProperties {

  /**
   * Gets the base URL for the API.
   *
   * @return the base URL
   */
  String getUrl();

  /**
   * Gets the access token for API authentication.
   *
   * @return the access token
   */
  String getAccessToken();

  /**
   * Gets the name of the authorization header.
   *
   * @return the auth header name
   */
  String getAuthHeader();

  /**
   * Gets the connection timeout in milliseconds.
   *
   * @return the connection timeout
   */
  int getConnectTimeoutMs();

  /**
   * Gets the read/response timeout in milliseconds.
   *
   * @return the read timeout
   */
  int getReadTimeoutMs();
}
