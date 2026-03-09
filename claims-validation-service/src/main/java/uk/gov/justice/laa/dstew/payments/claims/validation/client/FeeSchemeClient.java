package uk.gov.justice.laa.dstew.payments.claims.validation.client;

import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Client for calling the Fee Scheme Platform API.
 * Retrieves fee details and category of law information for claims.
 */
@Component
@Slf4j
public class FeeSchemeClient {

  private final WebClient feeSchemeWebClient;

  public FeeSchemeClient(@Qualifier("feeSchemeWebClient") WebClient feeSchemeWebClient) {
    this.feeSchemeWebClient = feeSchemeWebClient;
  }

  /**
   * Retrieves fee details for a given fee code.
   *
   * @param feeCode the fee code to look up
   * @return optional containing fee details, or empty if not found
   */
  public Optional<FeeDetailsResponse> getFeeDetails(String feeCode) {
    if (feeCode == null || feeCode.isBlank()) {
      return Optional.empty();
    }

    log.debug("Fetching fee details for fee code: {}", feeCode);

    try {
      FeeDetailsResponse response = feeSchemeWebClient
          .get()
          .uri("/api/v1/fees/{feeCode}", feeCode)
          .retrieve()
          .bodyToMono(FeeDetailsResponse.class)
          .block();

      return Optional.ofNullable(response);

    } catch (WebClientResponseException.NotFound e) {
      log.debug("Fee code not found: {}", feeCode);
      return Optional.empty();
    } catch (Exception e) {
      log.error("Error fetching fee details for fee code: {}", feeCode, e);
      throw new FeeSchemeClientException("Failed to fetch fee details", e);
    }
  }

  /**
   * Checks if a provider is authorized for a category of law.
   *
   * @param officeAccountNumber the provider's office account number
   * @param categoryOfLaw the category of law to check
   * @return true if authorized, false otherwise
   */
  public boolean isProviderAuthorizedForCategoryOfLaw(
      String officeAccountNumber, String categoryOfLaw) {

    if (officeAccountNumber == null || categoryOfLaw == null) {
      return false;
    }

    log.debug("Checking provider {} authorization for category: {}",
        officeAccountNumber, categoryOfLaw);

    try {
      Boolean authorized = feeSchemeWebClient
          .get()
          .uri("/api/v1/providers/{office}/categories/{category}/authorized",
              officeAccountNumber, categoryOfLaw)
          .retrieve()
          .bodyToMono(Boolean.class)
          .block();

      return Boolean.TRUE.equals(authorized);

    } catch (WebClientResponseException.NotFound e) {
      log.debug("Provider or category not found");
      return false;
    } catch (Exception e) {
      log.error("Error checking provider authorization", e);
      throw new FeeSchemeClientException("Failed to check provider authorization", e);
    }
  }

  /**
   * Response object for fee details.
   */
  public record FeeDetailsResponse(
      String feeCode,
      String feeType,
      String categoryOfLaw,
      String description,
      Map<String, Object> additionalProperties
  ) {}

  /**
   * Exception thrown when fee scheme API calls fail.
   */
  public static class FeeSchemeClientException extends RuntimeException {
    public FeeSchemeClientException(String message, Throwable cause) {
      super(message, cause);
    }
  }
}
