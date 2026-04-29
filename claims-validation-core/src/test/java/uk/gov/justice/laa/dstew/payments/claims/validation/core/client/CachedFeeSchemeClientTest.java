package uk.gov.justice.laa.dstew.payments.claims.validation.core.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.fee.scheme.model.FeeCalculationRequest;
import uk.gov.justice.laa.fee.scheme.model.FeeCalculationResponse;
import uk.gov.justice.laa.fee.scheme.model.FeeDetailsResponse;

/**
 * Tests for {@link CachedFeeSchemeClient}.
 *
 * <p>Verifies that the Caffeine-backed caching decorator correctly deduplicates calls to the
 * underlying {@link FeeSchemeClient} delegate — the same delegate response must be returned on
 * subsequent calls for the same key without making a second network call.
 */
@DisplayName("CachedFeeSchemeClient")
class CachedFeeSchemeClientTest {

  private FeeSchemeClient delegate;
  private CachedFeeSchemeClient cachedClient;

  @BeforeEach
  void setUp() {
    delegate = mock(FeeSchemeClient.class);
    // Long TTL so entries never expire during a test
    cachedClient = new CachedFeeSchemeClient(delegate, 60);
  }

  // ─────────────────────────────────────────────────────────────────────────
  // getFeeDetails
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("getFeeDetails")
  class GetFeeDetails {

    @Test
    @DisplayName("Delegates to the underlying client on the first call")
    void delegatesOnFirstCall() {
      ResponseEntity<FeeDetailsResponse> expected =
          ResponseEntity.ok(new FeeDetailsResponse());
      when(delegate.getFeeDetails("CIV123")).thenReturn(expected);

      ResponseEntity<FeeDetailsResponse> result = cachedClient.getFeeDetails("CIV123");

      assertThat(result).isSameAs(expected);
      verify(delegate, times(1)).getFeeDetails("CIV123");
    }

    @Test
    @DisplayName("Returns cached response on second call without hitting the delegate again")
    void returnsCachedResponseOnSecondCall() {
      ResponseEntity<FeeDetailsResponse> expected =
          ResponseEntity.ok(new FeeDetailsResponse());
      when(delegate.getFeeDetails("CIV123")).thenReturn(expected);

      cachedClient.getFeeDetails("CIV123");
      ResponseEntity<FeeDetailsResponse> second = cachedClient.getFeeDetails("CIV123");

      assertThat(second).isSameAs(expected);
      verify(delegate, times(1)).getFeeDetails("CIV123");
    }

    @Test
    @DisplayName("Makes separate delegate calls for different fee codes")
    void makesSeparateCallsForDifferentFeeCodes() {
      when(delegate.getFeeDetails("CIV123")).thenReturn(ResponseEntity.ok(new FeeDetailsResponse()));
      when(delegate.getFeeDetails("DISB01")).thenReturn(ResponseEntity.ok(new FeeDetailsResponse()));

      cachedClient.getFeeDetails("CIV123");
      cachedClient.getFeeDetails("DISB01");

      verify(delegate, times(1)).getFeeDetails("CIV123");
      verify(delegate, times(1)).getFeeDetails("DISB01");
    }

    @Test
    @DisplayName("Returns the same cached instance across multiple calls")
    void returnsSameInstanceAcrossMultipleCalls() {
      ResponseEntity<FeeDetailsResponse> expected =
          ResponseEntity.ok(new FeeDetailsResponse());
      when(delegate.getFeeDetails("CIV123")).thenReturn(expected);

      ResponseEntity<FeeDetailsResponse> first  = cachedClient.getFeeDetails("CIV123");
      ResponseEntity<FeeDetailsResponse> second = cachedClient.getFeeDetails("CIV123");
      ResponseEntity<FeeDetailsResponse> third  = cachedClient.getFeeDetails("CIV123");

      assertThat(first).isSameAs(second).isSameAs(third);
      verify(delegate, times(1)).getFeeDetails("CIV123");
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // calculateFee
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("calculateFee")
  class CalculateFee {

    private FeeCalculationRequest request;

    @BeforeEach
    void setUp() {
      request = new FeeCalculationRequest();
    }

    @Test
    @DisplayName("Delegates to the underlying client on the first call")
    void delegatesOnFirstCall() {
      ResponseEntity<FeeCalculationResponse> expected =
          ResponseEntity.ok(new FeeCalculationResponse());
      when(delegate.calculateFee(request)).thenReturn(expected);

      ResponseEntity<FeeCalculationResponse> result = cachedClient.calculateFee(request);

      assertThat(result).isSameAs(expected);
      verify(delegate, times(1)).calculateFee(request);
    }

    @Test
    @DisplayName("Returns cached response on second call without hitting the delegate again")
    void returnsCachedResponseOnSecondCall() {
      ResponseEntity<FeeCalculationResponse> expected =
          ResponseEntity.ok(new FeeCalculationResponse());
      when(delegate.calculateFee(request)).thenReturn(expected);

      cachedClient.calculateFee(request);
      ResponseEntity<FeeCalculationResponse> second = cachedClient.calculateFee(request);

      assertThat(second).isSameAs(expected);
      verify(delegate, times(1)).calculateFee(request);
    }

    @Test
    @DisplayName("Makes separate delegate calls for requests with different toString values")
    void makesSeparateCallsForDifferentRequests() {
      // Two distinct request objects will have different identity-based toString keys
      FeeCalculationRequest requestA = new FeeCalculationRequest();
      FeeCalculationRequest requestB = new FeeCalculationRequest();

      when(delegate.calculateFee(requestA)).thenReturn(ResponseEntity.ok(new FeeCalculationResponse()));
      when(delegate.calculateFee(requestB)).thenReturn(ResponseEntity.ok(new FeeCalculationResponse()));

      cachedClient.calculateFee(requestA);
      cachedClient.calculateFee(requestB);

      verify(delegate, times(1)).calculateFee(requestA);
      verify(delegate, times(1)).calculateFee(requestB);
    }
  }

  // ─────────────────────────────────────────────────────────────────────────
  // Cache independence
  // ─────────────────────────────────────────────────────────────────────────

  @Nested
  @DisplayName("Cache independence")
  class CacheIndependence {

    @Test
    @DisplayName("getFeeDetails and calculateFee caches are independent of each other")
    void feeDetailsAndCalculationCachesAreIndependent() {
      ResponseEntity<FeeDetailsResponse> feeDetailsResponse =
          ResponseEntity.ok(new FeeDetailsResponse());
      ResponseEntity<FeeCalculationResponse> calcResponse =
          ResponseEntity.ok(new FeeCalculationResponse());
      FeeCalculationRequest request = new FeeCalculationRequest();

      when(delegate.getFeeDetails("CIV123")).thenReturn(feeDetailsResponse);
      when(delegate.calculateFee(request)).thenReturn(calcResponse);

      // Call both once each
      cachedClient.getFeeDetails("CIV123");
      cachedClient.calculateFee(request);

      // Call both again — each should still only have hit the delegate once
      cachedClient.getFeeDetails("CIV123");
      cachedClient.calculateFee(request);

      verify(delegate, times(1)).getFeeDetails("CIV123");
      verify(delegate, times(1)).calculateFee(request);
    }
  }
}
