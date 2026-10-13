package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl;

import io.github.resilience4j.reactor.retry.RetryOperator;
import io.github.resilience4j.retry.RetryRegistry;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.ProviderDetailsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.ProviderDetailsProvider;
import uk.gov.justice.laadata.providers.model.ProviderFirmOfficeContractAndScheduleDto;

/**
 * HTTP-backed provider for resolving provider firm schedules.
 */
@Slf4j
public class HttpProviderDetailsProvider implements ProviderDetailsProvider {

  private static final String RETRY_NAME = "pdaRetry";

  private final ProviderDetailsClient providerDetailsRestClient;
  private final RetryRegistry retryRegistry;

  public HttpProviderDetailsProvider(
      ProviderDetailsClient providerDetailsRestClient, RetryRegistry retryRegistry) {
    this.providerDetailsRestClient = Objects.requireNonNull(providerDetailsRestClient);
    this.retryRegistry = Objects.requireNonNull(retryRegistry);
  }

  /**
   * Retrieves provider firm office contract and schedule information for the given office and
   * effective date.
   *
   * <p>Returns an empty {@link Optional} when the provider has no schedules for the given
   * parameters. Throws on technical API failure.
   */
  @Override
  public Optional<ProviderFirmOfficeContractAndScheduleDto> getProviderFirmSchedules(
      String officeCode, LocalDate effectiveDate) {
    return fetchProviderFirmSchedules(officeCode, effectiveDate).blockOptional();
  }

  /**
   * Reactive implementation of the provider lookup, exposed as package-private for testing.
   *
   * <p>Each invocation calls the Provider Details client with the supplied office and effective
   * date; no response is retained or merged between invocations.
   */
  Mono<ProviderFirmOfficeContractAndScheduleDto> fetchProviderFirmSchedules(
      String officeCode, LocalDate effectiveDate) {
    log.debug(
        "Calling Provider Details API for officeCode {}, effectiveDate {}",
        officeCode,
        effectiveDate);
    return providerDetailsRestClient
        .getProviderFirmSchedules(officeCode, effectiveDate)
        .transformDeferred(RetryOperator.of(retryRegistry.retry(RETRY_NAME)));
  }

}
