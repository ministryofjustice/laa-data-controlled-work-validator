package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl;

import io.github.resilience4j.retry.RetryRegistry;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.ProviderDetailsClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.model.ProviderDetailsCachedSchedules;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl.model.ProviderDetailsCoverageWindow;
import uk.gov.justice.laadata.providers.model.FirmOfficeContractAndScheduleDetails;
import uk.gov.justice.laadata.providers.model.ProviderFirmOfficeContractAndScheduleDto;

/**
 * HTTP-backed provider for resolving provider firm schedules, with positive/negative caching
 * and in-flight deduplication via {@link AbstractHttpCachingProvider#fetchDeduped}.
 */
@Slf4j
public class HttpProviderDetailsProvider
    extends AbstractHttpCachingProvider<ProviderFirmOfficeContractAndScheduleDto> {

  private static final Duration NEGATIVE_CACHE_TIME_TO_LIVE = Duration.ofMinutes(5);
  private static final Duration POSITIVE_CACHE_TIME_TO_LIVE = Duration.ofMinutes(10);
  private static final String RETRY_NAME = "pdaRetry";

  private final ProviderDetailsClient providerDetailsRestClient;

  // Richer positive cache with date-range coverage windows — kept separate from the base
  // class positiveCache because it requires schedule merging and coverage-window logic.
  private final Map<String, ProviderDetailsCachedSchedules> scheduleCache =
      new ConcurrentHashMap<>();

  public HttpProviderDetailsProvider(
      ProviderDetailsClient providerDetailsRestClient, RetryRegistry retryRegistry) {
    super(retryRegistry, POSITIVE_CACHE_TIME_TO_LIVE, NEGATIVE_CACHE_TIME_TO_LIVE);
    this.providerDetailsRestClient = Objects.requireNonNull(providerDetailsRestClient);
  }

  /**
   * Retrieves provider firm office contract and schedule information for the given office and
   * effective date, using positive/negative caching and in-flight deduplication.
   *
   * <p>Returns an empty {@link Optional} when the provider has no schedules for the given
   * parameters. Throws on technical API failure.
   */
  public Optional<ProviderFirmOfficeContractAndScheduleDto> getProviderFirmSchedules(
      String officeCode, LocalDate effectiveDate) {
    return fetchProviderFirmSchedules(officeCode, effectiveDate).blockOptional();
  }

  /**
   * Reactive implementation of the provider lookup, exposed as package-private for testing.
   *
   * <p>The public entry-point {@link #getProviderFirmSchedules} simply calls
   * {@code .blockOptional()} on this method. The reactive layer exists because in-flight
   * deduplication is implemented in {@link AbstractHttpCachingProvider#fetchDeduped} using 
   * {@link reactor.core.publisher.Sinks.One}, which broadcasts one remote-API result to all
   * concurrent subscribers without blocking any thread. See the ADR on {@code fetchDeduped}
   * for why {@code Sinks.One} was chosen over {@link FutureTask}.
   *
   * <p>This method is package-private rather than private so that unit tests can subscribe to the
   * {@link Mono} directly and assert reactive behaviour (cache hits, deduplication, empty signals)
   * without going through the blocking {@code .blockOptional()} boundary.
   */
  Mono<ProviderFirmOfficeContractAndScheduleDto> fetchProviderFirmSchedules(
      String officeCode, LocalDate effectiveDate) {
    String cacheKey = officeCode;
    String negativeKey = officeCode + "|" + effectiveDate;

    // negative short-circuit — uses inherited negativeCache keyed by (officeCode|effectiveDate)
    if (getNegativeCached(negativeKey).isPresent()) {
      log.debug("ProviderDetails negative cache hit for key {}", negativeKey);
      return Mono.empty();
    }

    // positive short-circuit
    ProviderDetailsCachedSchedules cached = scheduleCache.get(cacheKey);
    if (cached != null) {
      if (!cached.isValid()) {
        log.debug("ProviderDetails cache expired for key {}", cacheKey);
        scheduleCache.remove(cacheKey);
      } else if (cached.isNegative()) {
        log.debug("ProviderDetails negative cache hit for key {}", cacheKey);
        return Mono.empty();
      } else if (cached.covers(effectiveDate)) {
        log.debug("ProviderDetails cache hit for key {} covering effectiveDate {}",
            cacheKey, effectiveDate);
        scheduleCache.put(cacheKey, cached.refresh(POSITIVE_CACHE_TIME_TO_LIVE));
        return Mono.just(cached.value());
      } else {
        log.debug("ProviderDetails cache miss for key {}: date {} not covered",
            cacheKey, effectiveDate);
      }
    }

    // cache miss — deduplicate concurrent remote calls scoped to (officeCode, effectiveDate)
    return fetchDedupedWithCaching(negativeKey, RETRY_NAME, () ->
        providerDetailsRestClient.getProviderFirmSchedules(officeCode, effectiveDate)
            .map(dto -> {
              cacheWindows(cacheKey, dto);
              return dto;
            }));
  }

  /**
   * Builds the coverage windows from schedule and contract dates. Uses multiple windows to avoid
   * false positives when there are gaps between contracts. Null starts/ends are treated as open
   * ranges.
   */
  private Optional<List<ProviderDetailsCoverageWindow>> computeCoverage(
      ProviderFirmOfficeContractAndScheduleDto dto) {
    if (dto.getSchedules() == null || dto.getSchedules().isEmpty()) {
      return Optional.empty();
    }

    List<ProviderDetailsCoverageWindow> windows =
        dto.getSchedules().stream()
            .map(this::toWindow)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .sorted(Comparator.comparing(ProviderDetailsCoverageWindow::start))
            .collect(ArrayList::new, this::mergeOrAdd, this::mergeLists);

    if (windows.isEmpty()) {
      return Optional.empty();
    }

    return Optional.of(windows);
  }

  /** Builds a coverage window for a schedule if end is not before start. */
  private Optional<ProviderDetailsCoverageWindow> toWindow(
      FirmOfficeContractAndScheduleDetails schedule) {
    LocalDate start =
        Stream.of(schedule.getScheduleStartDate())
            .filter(Objects::nonNull)
            .min(Comparator.naturalOrder())
            .orElse(LocalDate.MIN);
    LocalDate end =
        Stream.of(schedule.getScheduleEndDate())
            .filter(Objects::nonNull)
            .max(Comparator.naturalOrder())
            .orElse(LocalDate.MAX);
    if (end.isBefore(start)) {
      return Optional.empty();
    }
    return Optional.of(new ProviderDetailsCoverageWindow(start, end));
  }

  /** Merges an adjacent/overlapping window into the tail of the list or adds a new window. */
  private void mergeOrAdd(
      List<ProviderDetailsCoverageWindow> windows, ProviderDetailsCoverageWindow next) {
    if (windows.isEmpty()) {
      windows.add(next);
      return;
    }
    ProviderDetailsCoverageWindow last = windows.getLast();
    if (!next.start().isAfter(last.end().plusDays(1))) {
      windows.set(
          windows.size() - 1,
          new ProviderDetailsCoverageWindow(last.start(), max(last.end(), next.end())));
    } else {
      windows.add(next);
    }
  }

  /** Merges all windows from source into target in order. */
  private void mergeLists(
      List<ProviderDetailsCoverageWindow> target, List<ProviderDetailsCoverageWindow> source) {
    source.forEach(window -> mergeOrAdd(target, window));
  }

  /** Returns the max of two dates. */
  private LocalDate max(LocalDate left, LocalDate right) {
    return left.isAfter(right) ? left : right;
  }


  /** Cache the response along with its merged coverage windows. */
  private void cacheWindows(String cacheKey, ProviderFirmOfficeContractAndScheduleDto dto) {
    computeCoverage(dto)
        .ifPresent(
            newWindows -> {
              ProviderDetailsCachedSchedules existing = scheduleCache.get(cacheKey);
              if (existing != null && !existing.isNegative()) {
                List<ProviderDetailsCoverageWindow> mergedWindows =
                    mergeWindows(existing.windows(), newWindows);
                ProviderFirmOfficeContractAndScheduleDto mergedDto =
                    mergeSchedules(existing.value(), dto);
                scheduleCache.put(
                    cacheKey,
                    ProviderDetailsCachedSchedules.positive(
                        mergedDto, mergedWindows, POSITIVE_CACHE_TIME_TO_LIVE));
              } else {
                scheduleCache.put(
                    cacheKey,
                    ProviderDetailsCachedSchedules.positive(
                        dto, newWindows, POSITIVE_CACHE_TIME_TO_LIVE));
              }
            });
  }

  /** Merges cached and incoming coverage windows into a single ordered list. */
  private List<ProviderDetailsCoverageWindow> mergeWindows(
      List<ProviderDetailsCoverageWindow> existing, List<ProviderDetailsCoverageWindow> incoming) {
    return Stream.concat(existing.stream(), incoming.stream())
        .sorted(Comparator.comparing(ProviderDetailsCoverageWindow::start))
        .collect(ArrayList::new, this::mergeOrAdd, this::mergeLists);
  }

  /** Combines cached and incoming DTO data, appending schedule lists. */
  private ProviderFirmOfficeContractAndScheduleDto mergeSchedules(
      ProviderFirmOfficeContractAndScheduleDto existing,
      ProviderFirmOfficeContractAndScheduleDto incoming) {
    ProviderFirmOfficeContractAndScheduleDto merged =
        new ProviderFirmOfficeContractAndScheduleDto();
    merged.setFirm(
        incoming.getFirm() != null
            ? incoming.getFirm()
            : existing != null ? existing.getFirm() : null);
    merged.setOffice(
        incoming.getOffice() != null
            ? incoming.getOffice()
            : existing != null ? existing.getOffice() : null);
    merged.setPds(
        incoming.getPds() != null
            ? incoming.getPds()
            : existing != null ? existing.getPds() : null);

    List<FirmOfficeContractAndScheduleDetails> schedules = new ArrayList<>();
    if (existing != null && existing.getSchedules() != null) {
      schedules.addAll(existing.getSchedules());
    }
    if (incoming.getSchedules() != null) {
      schedules.addAll(incoming.getSchedules());
    }
    merged.setSchedules(schedules);
    return merged;
  }
}
