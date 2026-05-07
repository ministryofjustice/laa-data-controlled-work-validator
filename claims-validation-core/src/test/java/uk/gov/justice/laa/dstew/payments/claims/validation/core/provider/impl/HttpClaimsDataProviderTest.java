package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.DataClaimsClient;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResponse;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResultSet;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionBase;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionStatus;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.SubmissionsResultSet;

/**
 * Tests for {@link HttpClaimsDataProvider}.
 *
 * <p>Each method is tested across the full response surface:
 * <ul>
 *   <li>Happy path — valid body is unwrapped and returned.
 *   <li>Null {@link ResponseEntity} — should fall back to a safe empty result.
 *   <li>Non-null entity with null body — same safe fallback.
 *   <li>Non-null body with null content collection — same safe fallback (getSubmissions only).
 *   <li>Empty content — returns empty but non-null structure.
 *   <li>Downstream exception propagation — 4xx / 5xx / network errors are surfaced as-is so
 *       callers can decide how to handle them.
 * </ul>
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("HttpClaimsDataProvider")
class HttpClaimsDataProviderTest {

  @Mock private DataClaimsClient dataClaimsClient;

  @InjectMocks private HttpClaimsDataProvider provider;

  private static final String OFFICE_CODE = "1A234B";
  private static final List<String> OFFICES = List.of(OFFICE_CODE);
  private static final AreaOfLaw AREA_OF_LAW = AreaOfLaw.LEGAL_HELP;
  private static final String SUBMISSION_PERIOD = "2025-07";

  // ─────────────────────────────────────────────────────────────────────────
  // getClaims
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("getClaims")
  class GetClaims {

    @Nested
    @DisplayName("Happy path")
    class HappyPath {

      @Test
      @DisplayName("Returns the body from the ResponseEntity when the response is well-formed")
      void returnsBodyFromResponse() {
        ClaimResultSet expected = new ClaimResultSet();
        ClaimResponse claim = new ClaimResponse();
        expected.addContentItem(claim);
        when(dataClaimsClient.getClaims(any(), any(), any(), any(), any(), any(), any(), any(),
            any(), any(), any()))
            .thenReturn(ResponseEntity.ok(expected));

        ClaimResultSet result = provider.getClaims(OFFICE_CODE, null, null, null, null, null, null,
            null, null, null, null);

        assertThat(result).isSameAs(expected);
        assertThat(result.getContent()).hasSize(1);
      }

      @Test
      @DisplayName("Forwards all parameters to the underlying client unchanged")
      void forwardsAllParametersToClient() {
        when(dataClaimsClient.getClaims(any(), any(), any(), any(), any(), any(), any(), any(),
            any(), any(), any()))
            .thenReturn(ResponseEntity.ok(new ClaimResultSet()));

        provider.getClaims(
            OFFICE_CODE, "sub-1",
            List.of(SubmissionStatus.VALIDATION_SUCCEEDED),
            "FEE01", "UFN123", "UCN456", "UCI789",
            List.of(ClaimStatus.VALID),
            0, 20, "createdAt,desc");

        verify(dataClaimsClient).getClaims(
            OFFICE_CODE, "sub-1",
            List.of(SubmissionStatus.VALIDATION_SUCCEEDED),
            "FEE01", "UFN123", "UCN456", "UCI789",
            List.of(ClaimStatus.VALID),
            0, 20, "createdAt,desc");
      }

      @Test
      @DisplayName("Returns empty ClaimResultSet when content list is empty")
      void returnsEmptyResultSetWhenContentIsEmpty() {
        when(dataClaimsClient.getClaims(any(), any(), any(), any(), any(), any(), any(), any(),
            any(), any(), any()))
            .thenReturn(ResponseEntity.ok(new ClaimResultSet()));

        ClaimResultSet result = provider.getClaims(OFFICE_CODE, null, null, null, null, null, null,
            null, null, null, null);

        assertThat(result).isNotNull().isInstanceOf(ClaimResultSet.class);
        assertThat(result.getContent()).isNullOrEmpty();
      }
    }

    @Nested
    @DisplayName("Null / empty response handling")
    class NullResponseHandling {

      @Test
      @DisplayName("Returns empty ClaimResultSet when ResponseEntity is null")
      void returnsEmptyResultWhenResponseEntityIsNull() {
        when(dataClaimsClient.getClaims(any(), any(), any(), any(), any(), any(), any(), any(),
            any(), any(), any()))
            .thenReturn(null);

        ClaimResultSet result = provider.getClaims(OFFICE_CODE, null, null, null, null, null, null,
            null, null, null, null);

        assertThat(result).isNotNull().isInstanceOf(ClaimResultSet.class);
      }

      @Test
      @DisplayName("Returns empty ClaimResultSet when ResponseEntity body is null")
      void returnsEmptyResultWhenBodyIsNull() {
        when(dataClaimsClient.getClaims(any(), any(), any(), any(), any(), any(), any(), any(),
            any(), any(), any()))
            .thenReturn(ResponseEntity.ok(null));

        ClaimResultSet result = provider.getClaims(OFFICE_CODE, null, null, null, null, null, null,
            null, null, null, null);

        assertThat(result).isNotNull().isInstanceOf(ClaimResultSet.class);
      }

      @Test
      @DisplayName("Returns empty ClaimResultSet for a 204 No Content response (null body)")
      void returnsEmptyResultForNoContentResponse() {
        when(dataClaimsClient.getClaims(any(), any(), any(), any(), any(), any(), any(), any(),
            any(), any(), any()))
            .thenReturn(ResponseEntity.noContent().build());

        ClaimResultSet result = provider.getClaims(OFFICE_CODE, null, null, null, null, null, null,
            null, null, null, null);

        assertThat(result).isNotNull().isInstanceOf(ClaimResultSet.class);
      }
    }

    @Nested
    @DisplayName("Downstream exception propagation")
    class ExceptionPropagation {

      @Test
      @DisplayName("Propagates WebClientResponseException 404 from the client")
      void propagates404Exception() {
        when(dataClaimsClient.getClaims(any(), any(), any(), any(), any(), any(), any(), any(),
            any(), any(), any()))
            .thenThrow(WebClientResponseException.create(
                HttpStatus.NOT_FOUND.value(), "Not Found", null, null, null));

        assertThatThrownBy(() ->
            provider.getClaims(OFFICE_CODE, null, null, null, null, null, null, null, null, null, null))
            .isInstanceOf(WebClientResponseException.class)
            .satisfies(ex ->
                assertThat(((WebClientResponseException) ex).getStatusCode())
                    .isEqualTo(HttpStatus.NOT_FOUND));
      }

      @Test
      @DisplayName("Propagates WebClientResponseException 500 from the client")
      void propagates500Exception() {
        when(dataClaimsClient.getClaims(any(), any(), any(), any(), any(), any(), any(), any(),
            any(), any(), any()))
            .thenThrow(WebClientResponseException.create(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", null, null, null));

        assertThatThrownBy(() ->
            provider.getClaims(OFFICE_CODE, null, null, null, null, null, null, null, null, null, null))
            .isInstanceOf(WebClientResponseException.class)
            .satisfies(ex ->
                assertThat(((WebClientResponseException) ex).getStatusCode())
                    .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR));
      }

      @Test
      @DisplayName("Propagates WebClientResponseException 401 Unauthorized from the client")
      void propagates401Exception() {
        when(dataClaimsClient.getClaims(any(), any(), any(), any(), any(), any(), any(), any(),
            any(), any(), any()))
            .thenThrow(WebClientResponseException.create(
                HttpStatus.UNAUTHORIZED.value(), "Unauthorized", null, null, null));

        assertThatThrownBy(() ->
            provider.getClaims(OFFICE_CODE, null, null, null, null, null, null, null, null, null, null))
            .isInstanceOf(WebClientResponseException.class)
            .satisfies(ex ->
                assertThat(((WebClientResponseException) ex).getStatusCode())
                    .isEqualTo(HttpStatus.UNAUTHORIZED));
      }

      @Test
      @DisplayName("Propagates RuntimeException (e.g. network timeout) from the client")
      void propagatesRuntimeException() {
        when(dataClaimsClient.getClaims(any(), any(), any(), any(), any(), any(), any(), any(),
            any(), any(), any()))
            .thenThrow(new RuntimeException("Connection refused"));

        assertThatThrownBy(() ->
            provider.getClaims(OFFICE_CODE, null, null, null, null, null, null, null, null, null, null))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Connection refused");
      }
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // getSubmissions
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("getSubmissions")
  class GetSubmissions {

    @Nested
    @DisplayName("Happy path")
    class HappyPath {

      @Test
      @DisplayName("Returns the content list from the ResponseEntity body when well-formed")
      void returnsContentFromResponse() {
        SubmissionBase submission = new SubmissionBase()
            .officeAccountNumber(OFFICE_CODE)
            .areaOfLaw(AREA_OF_LAW)
            .submissionPeriod(SUBMISSION_PERIOD);
        SubmissionsResultSet resultSet = new SubmissionsResultSet().addContentItem(submission);
        when(dataClaimsClient.getSubmissions(OFFICES, AREA_OF_LAW, SUBMISSION_PERIOD))
            .thenReturn(ResponseEntity.ok(resultSet));

        List<SubmissionBase> result = provider.getSubmissions(OFFICES, AREA_OF_LAW, SUBMISSION_PERIOD);

        assertThat(result).hasSize(1);
        assertThat(result.getFirst().getOfficeAccountNumber()).isEqualTo(OFFICE_CODE);
      }

      @Test
      @DisplayName("Forwards all parameters to the underlying client unchanged")
      void forwardsAllParametersToClient() {
        when(dataClaimsClient.getSubmissions(any(), any(), any()))
            .thenReturn(ResponseEntity.ok(new SubmissionsResultSet()));

        provider.getSubmissions(OFFICES, AREA_OF_LAW, SUBMISSION_PERIOD);

        verify(dataClaimsClient).getSubmissions(OFFICES, AREA_OF_LAW, SUBMISSION_PERIOD);
      }

      @Test
      @DisplayName("Returns empty list when content is empty but response is valid")
      void returnsEmptyListWhenContentIsEmpty() {
        when(dataClaimsClient.getSubmissions(any(), any(), any()))
            .thenReturn(ResponseEntity.ok(new SubmissionsResultSet()));

        List<SubmissionBase> result = provider.getSubmissions(OFFICES, AREA_OF_LAW, SUBMISSION_PERIOD);

        assertThat(result).isNotNull().isEmpty();
      }

      @Test
      @DisplayName("Returns multiple items when the result set contains several submissions")
      void returnsMultipleItems() {
        SubmissionsResultSet resultSet = new SubmissionsResultSet()
            .addContentItem(new SubmissionBase().officeAccountNumber("A"))
            .addContentItem(new SubmissionBase().officeAccountNumber("B"))
            .addContentItem(new SubmissionBase().officeAccountNumber("C"));
        when(dataClaimsClient.getSubmissions(any(), any(), any()))
            .thenReturn(ResponseEntity.ok(resultSet));

        List<SubmissionBase> result = provider.getSubmissions(OFFICES, AREA_OF_LAW, SUBMISSION_PERIOD);

        assertThat(result).hasSize(3);
      }

      @Test
      @DisplayName("Passes null areaOfLaw to the client when not provided")
      void passesNullAreaOfLaw() {
        when(dataClaimsClient.getSubmissions(any(), any(), any()))
            .thenReturn(ResponseEntity.ok(new SubmissionsResultSet()));

        provider.getSubmissions(OFFICES, null, SUBMISSION_PERIOD);

        verify(dataClaimsClient).getSubmissions(OFFICES, null, SUBMISSION_PERIOD);
      }

      @Test
      @DisplayName("Passes null submissionPeriod to the client when not provided")
      void passesNullSubmissionPeriod() {
        when(dataClaimsClient.getSubmissions(any(), any(), any()))
            .thenReturn(ResponseEntity.ok(new SubmissionsResultSet()));

        provider.getSubmissions(OFFICES, AREA_OF_LAW, null);

        verify(dataClaimsClient).getSubmissions(OFFICES, AREA_OF_LAW, null);
      }
    }

    @Nested
    @DisplayName("Null / empty response handling")
    class NullResponseHandling {

      @Test
      @DisplayName("Returns empty list when ResponseEntity is null")
      void returnsEmptyListWhenResponseEntityIsNull() {
        when(dataClaimsClient.getSubmissions(any(), any(), any())).thenReturn(null);

        List<SubmissionBase> result = provider.getSubmissions(OFFICES, AREA_OF_LAW, SUBMISSION_PERIOD);

        assertThat(result).isNotNull().isEmpty();
      }

      @Test
      @DisplayName("Returns empty list when ResponseEntity body is null")
      void returnsEmptyListWhenBodyIsNull() {
        when(dataClaimsClient.getSubmissions(any(), any(), any()))
            .thenReturn(ResponseEntity.ok(null));

        List<SubmissionBase> result = provider.getSubmissions(OFFICES, AREA_OF_LAW, SUBMISSION_PERIOD);

        assertThat(result).isNotNull().isEmpty();
      }

      @Test
      @DisplayName("Returns empty list when SubmissionsResultSet.getContent() is null")
      void returnsEmptyListWhenContentIsNull() {
        // A SubmissionsResultSet with no items added has a null content list
        SubmissionsResultSet emptyResultSet = new SubmissionsResultSet();
        when(dataClaimsClient.getSubmissions(any(), any(), any()))
            .thenReturn(ResponseEntity.ok(emptyResultSet));

        List<SubmissionBase> result = provider.getSubmissions(OFFICES, AREA_OF_LAW, SUBMISSION_PERIOD);

        assertThat(result).isNotNull().isEmpty();
      }

      @Test
      @DisplayName("Returns empty list for a 204 No Content response")
      void returnsEmptyListForNoContentResponse() {
        when(dataClaimsClient.getSubmissions(any(), any(), any()))
            .thenReturn(ResponseEntity.noContent().build());

        List<SubmissionBase> result = provider.getSubmissions(OFFICES, AREA_OF_LAW, SUBMISSION_PERIOD);

        assertThat(result).isNotNull().isEmpty();
      }
    }

    // TODO: this does not seem right to me findout what we are to do when they
    // fail when getting submissions or all the claims
    @Nested
    @DisplayName("Downstream exception propagation")
    class ExceptionPropagation {

      @Test
      @DisplayName("Propagates WebClientResponseException 404 from the client")
      void propagates404Exception() {
        when(dataClaimsClient.getSubmissions(any(), any(), any()))
            .thenThrow(WebClientResponseException.create(
                HttpStatus.NOT_FOUND.value(), "Not Found", null, null, null));

        assertThatThrownBy(() -> provider.getSubmissions(OFFICES, AREA_OF_LAW, SUBMISSION_PERIOD))
            .isInstanceOf(WebClientResponseException.class)
            .satisfies(ex ->
                assertThat(((WebClientResponseException) ex).getStatusCode())
                    .isEqualTo(HttpStatus.NOT_FOUND));
      }

      @Test
      @DisplayName("Propagates WebClientResponseException 500 from the client")
      void propagates500Exception() {
        when(dataClaimsClient.getSubmissions(any(), any(), any()))
            .thenThrow(WebClientResponseException.create(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error", null, null, null));

        assertThatThrownBy(() -> provider.getSubmissions(OFFICES, AREA_OF_LAW, SUBMISSION_PERIOD))
            .isInstanceOf(WebClientResponseException.class)
            .satisfies(ex ->
                assertThat(((WebClientResponseException) ex).getStatusCode())
                    .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR));
      }

      @Test
      @DisplayName("Propagates WebClientResponseException 401 Unauthorized from the client")
      void propagates401Exception() {
        when(dataClaimsClient.getSubmissions(any(), any(), any()))
            .thenThrow(WebClientResponseException.create(
                HttpStatus.UNAUTHORIZED.value(), "Unauthorized", null, null, null));

        assertThatThrownBy(() -> provider.getSubmissions(OFFICES, AREA_OF_LAW, SUBMISSION_PERIOD))
            .isInstanceOf(WebClientResponseException.class)
            .satisfies(ex ->
                assertThat(((WebClientResponseException) ex).getStatusCode())
                    .isEqualTo(HttpStatus.UNAUTHORIZED));
      }

      @Test
      @DisplayName("Propagates WebClientResponseException 503 Service Unavailable from the client")
      void propagates503Exception() {
        when(dataClaimsClient.getSubmissions(any(), any(), any()))
            .thenThrow(WebClientResponseException.create(
                HttpStatus.SERVICE_UNAVAILABLE.value(), "Service Unavailable", null, null, null));

        assertThatThrownBy(() -> provider.getSubmissions(OFFICES, AREA_OF_LAW, SUBMISSION_PERIOD))
            .isInstanceOf(WebClientResponseException.class)
            .satisfies(ex ->
                assertThat(((WebClientResponseException) ex).getStatusCode())
                    .isEqualTo(HttpStatus.SERVICE_UNAVAILABLE));
      }

      @Test
      @DisplayName("Propagates RuntimeException (e.g. network timeout) from the client")
      void propagatesRuntimeException() {
        when(dataClaimsClient.getSubmissions(any(), any(), any()))
            .thenThrow(new RuntimeException("Read timeout"));

        assertThatThrownBy(() -> provider.getSubmissions(OFFICES, AREA_OF_LAW, SUBMISSION_PERIOD))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Read timeout");
      }
    }
  }
}
