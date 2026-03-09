package uk.gov.justice.laa.dstew.payments.claims.validation.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;

/**
 * Client for calling external validation services.
 * Implements WebClient with timeout and retry placeholders.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ExternalValidationClient {

  private final WebClient externalValidationWebClient;

  /**
   * Validates the claim with external services.
   *
   * @param claim the claim to validate (as a Map)
   * @return a list of validation issues from external services
   */
  public List<ValidationIssue> validateWithExternalServices(Map<String, Object> claim) {
    List<ValidationIssue> issues = new ArrayList<>();

    // TODO: Implement calls to external validation APIs
    // Example pattern for external call:
    // try {
    //   ExternalValidationResponse response = externalValidationWebClient
    //       .post()
    //       .uri("/validate")
    //       .bodyValue(claim)
    //       .retrieve()
    //       .bodyToMono(ExternalValidationResponse.class)
    //       .retryWhen(Retry.backoff(3, Duration.ofSeconds(1))
    //           .filter(throwable ->
    //               throwable instanceof WebClientResponseException.ServiceUnavailable))
    //       .block();
    //
    //   if (response != null && response.getIssues() != null) {
    //     issues.addAll(mapToValidationIssues(response.getIssues()));
    //   }
    // } catch (Exception e) {
    //   log.error("External validation service call failed", e);
    //   // TODO: Decide whether to fail validation or continue with warning
    // }

    log.debug("External validation completed, found {} issues", issues.size());
    return issues;
  }

  // TODO: Implement additional external service calls as needed
  // Example: Provider validation, reference data checks, etc.
}

