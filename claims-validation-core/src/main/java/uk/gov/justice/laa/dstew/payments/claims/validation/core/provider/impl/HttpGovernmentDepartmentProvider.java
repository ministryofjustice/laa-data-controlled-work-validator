package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl;

import io.github.resilience4j.retry.RetryRegistry;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.util.function.SingletonSupplier;
import reactor.core.publisher.Mono;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.DataClaimsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.GovernmentDepartmentProvider;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.GovernmentDepartment;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.GovernmentDepartmentsList;

/**
 * HTTP-backed provider for the Government Department reference data, fetched from the Data Claims
 * API. Uses the positive cache and in-flight dedupe from {@link AbstractHttpCachingProvider}.
 *
 * <p>The whole list is cached under a single key for a fixed TTL. Unlike
 * {@link HttpFeeSchemeProvider}, cache hits do not extend the TTL, so the list is reloaded
 * periodically even under constant traffic. Failures and empty lists are never cached, so the next
 * call retries rather than pinning a bad state for the TTL.
 *
 * <p>The {@link DataClaimsClient} is created on first use, so an application that never validates
 * Inquest departments does not need the Data Claims API properties.
 */
@Slf4j
public class HttpGovernmentDepartmentProvider extends AbstractHttpCachingProvider<List<String>>
    implements GovernmentDepartmentProvider {

  private static final Duration POSITIVE_CACHE_TTL = Duration.ofMinutes(30);
  // Not-found results are never negatively cached (see class Javadoc).
  private static final Duration NEGATIVE_CACHE_TTL = Duration.ZERO;
  private static final String RETRY_NAME = "dataClaimsRetry";
  private static final String CACHE_KEY = "government-departments";

  private final SingletonSupplier<DataClaimsClient> dataClaimsClient;

  public HttpGovernmentDepartmentProvider(
      DataClaimsClient dataClaimsClient, RetryRegistry retryRegistry) {
    this(SingletonSupplier.of(Objects.requireNonNull(dataClaimsClient)), retryRegistry);
  }

  public HttpGovernmentDepartmentProvider(
      Supplier<DataClaimsClient> dataClaimsClient, RetryRegistry retryRegistry) {
    super(retryRegistry, POSITIVE_CACHE_TTL, NEGATIVE_CACHE_TTL);
    this.dataClaimsClient = SingletonSupplier.of(Objects.requireNonNull(dataClaimsClient));
  }

  /**
   * Returns the active Government Department names.
   *
   * @throws IllegalStateException if the Data Claims API returns no departments
   * @throws RuntimeException on technical API failure
   */
  @Override
  public List<String> getActiveGovernmentDepartments() {
    return fetchGovernmentDepartments().blockOptional().orElseThrow();
  }

  /** Reactive implementation — package-private for testing. */
  Mono<List<String>> fetchGovernmentDepartments() {
    var cached = getPositiveCached(CACHE_KEY);
    if (cached.isPresent()) {
      log.debug("Government Departments cache hit");
      return Mono.just(cached.get().value());
    }

    // Resolved outside the retry so a configuration error (e.g. missing URL) is not retried.
    DataClaimsClient client = dataClaimsClient.obtain();
    return fetchDeduped(CACHE_KEY, () ->
        Mono.defer(client::getGovernmentDepartments)
            .map(HttpGovernmentDepartmentProvider::toNames)
            .transformDeferred(retryOperator(RETRY_NAME))
            .doOnNext(names -> cachePositive(CACHE_KEY, names)));
  }

  private static List<String> toNames(ResponseEntity<GovernmentDepartmentsList> response) {
    GovernmentDepartmentsList body = response == null ? null : response.getBody();
    List<String> names =
        body == null || body.getGovernmentDepartments() == null
            ? List.of()
            : body.getGovernmentDepartments().stream()
                .filter(Objects::nonNull)
                .map(GovernmentDepartment::getName)
                .filter(StringUtils::hasText)
                .toList();
    if (names.isEmpty()) {
      throw new IllegalStateException("Data Claims API returned no Government Departments");
    }
    return names;
  }
}
