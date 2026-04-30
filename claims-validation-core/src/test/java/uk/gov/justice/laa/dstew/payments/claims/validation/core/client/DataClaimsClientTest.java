package uk.gov.justice.laa.dstew.payments.claims.validation.core.client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResultSet;

/**
 * Tests for the {@code getClaims(Pageable)} default method on {@link DataClaimsClient}.
 *
 * <p>The {@link DataClaimsClient} is a Spring {@code @HttpExchange} interface. The annotation-
 * driven HTTP transport is handled by the Spring framework and is not tested here. What IS tested
 * is the custom default method that bridges {@link Pageable} to the underlying 11-argument
 * {@code getClaims} method, as this contains logic we own.
 */
@DisplayName("DataClaimsClient — getClaims(Pageable) default method")
class DataClaimsClientTest {

  /**
   * A Mockito mock configured with {@code CALLS_REAL_METHODS} so that the default method runs its
   * real code while the abstract {@code getClaims(11-args)} is stubbed.
   */
  private DataClaimsClient client;

  @BeforeEach
  void setUp() {
    client = mock(DataClaimsClient.class, CALLS_REAL_METHODS);
    when(client.getClaims(any(), any(), any(), any(), any(), any(), any(), any(),
        any(), any(), any()))
        .thenReturn(ResponseEntity.ok(new ClaimResultSet()));
  }

  @Nested
  @DisplayName("When Pageable is null")
  class NullPageable {

    @Test
    @DisplayName("Passes null for page, size, and sort")
    void passesNullsForAllPaginationParams() {
      client.getClaims("OFFICE1", null, null, "FEE1", null, null, null, null, (Pageable) null);

      verify(client).getClaims(
          eq("OFFICE1"), isNull(), isNull(), eq("FEE1"), isNull(), isNull(), isNull(), isNull(),
          isNull(),   // page
          isNull(),   // size
          isNull());  // sort
    }
  }

  @Nested
  @DisplayName("When Pageable has page, size, and sort")
  class PopulatedPageable {

    @Test
    @DisplayName("Extracts page number, page size, and sort string correctly")
    void extractsPageNumberPageSizeAndSortString() {
      Pageable pageable = PageRequest.of(2, 25, Sort.by(Sort.Direction.DESC, "createdAt"));

      client.getClaims("OFFICE1", null, null, null, null, null, null, null, pageable);

      verify(client).getClaims(
          eq("OFFICE1"), isNull(), isNull(), isNull(), isNull(), isNull(), isNull(), isNull(),
          eq(2),                           // page number
          eq(25),                          // page size
          eq("createdAt: DESC"));          // sort string
    }

    @Test
    @DisplayName("Passes page number zero correctly (not treated as null)")
    void passesPageNumberZeroCorrectly() {
      Pageable pageable = PageRequest.of(0, 10);

      client.getClaims("OFFICE1", null, null, null, null, null, null, null, pageable);

      verify(client).getClaims(
          eq("OFFICE1"), isNull(), isNull(), isNull(), isNull(), isNull(), isNull(), isNull(),
          eq(0),   // page number zero is valid
          eq(10),
          isNull()); // no sort
    }
  }
}
