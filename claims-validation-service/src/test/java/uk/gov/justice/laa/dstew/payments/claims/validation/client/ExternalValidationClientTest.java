package uk.gov.justice.laa.dstew.payments.claims.validation.client;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;

@ExtendWith(MockitoExtension.class)
class ExternalValidationClientTest {

  @Mock
  private WebClient mockWebClient;

  @InjectMocks
  private ExternalValidationClient externalValidationClient;

  @Test
  void validateWithExternalServices_returnsEmptyListWhenNoExternalIssues() {
    Map<String, Object> claim = new HashMap<>();

    List<ValidationIssue> issues = externalValidationClient.validateWithExternalServices(claim);

    // TODO: Update this test when external service calls are implemented
    assertThat(issues).isEmpty();
  }

  // TODO: Add tests for external service call scenarios
  // - Test successful external validation response
  // - Test external service timeout handling
  // - Test retry logic
  // - Test error handling
}
