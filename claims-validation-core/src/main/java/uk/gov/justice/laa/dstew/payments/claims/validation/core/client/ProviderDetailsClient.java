package uk.gov.justice.laa.dstew.payments.claims.validation.core.client;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Client for calling the Provider Details API. Retrieves provider contract and schedule information
 * for validation.
 */
@Component
@Slf4j
public class ProviderDetailsClient {

  private final WebClient providerDetailsWebClient;

  public ProviderDetailsClient(
      @Qualifier("providerDetailsWebClient") WebClient providerDetailsWebClient) {
    this.providerDetailsWebClient = providerDetailsWebClient;
  }

  /**
   * Retrieves the effective categories of law for a provider based on office code, area of law, and
   * effective date.
   *
   * @param officeCode the unique code identifying the office
   * @param areaOfLaw the area of law for which schedules are requested
   * @param effectiveDate the date from which the schedule should be effective
   * @return list of category of law codes the provider is authorized for
   */
  public List<String> getEffectiveCategoriesOfLaw(
      String officeCode, String areaOfLaw, LocalDate effectiveDate) {

    if (officeCode == null || areaOfLaw == null || effectiveDate == null) {
      return Collections.emptyList();
    }

    log.debug(
        "Fetching effective categories for office: {}, areaOfLaw: {}, effectiveDate: {}",
        officeCode,
        areaOfLaw,
        effectiveDate);

    try {
      ProviderSchedulesResponse response =
          providerDetailsWebClient
              .get()
              .uri(
                  "/api/v1/providers/{office}/schedules?areaOfLaw={area}&effectiveDate={date}",
                  officeCode,
                  areaOfLaw,
                  effectiveDate)
              .retrieve()
              .bodyToMono(ProviderSchedulesResponse.class)
              .block();

      if (response == null || response.schedules() == null) {
        return Collections.emptyList();
      }

      return response.schedules().stream()
          .flatMap(schedule -> schedule.scheduleLines().stream())
          .map(ScheduleLine::categoryOfLaw)
          .distinct()
          .toList();

    } catch (WebClientResponseException.NotFound e) {
      log.debug("Provider schedules not found for office: {}", officeCode);
      return Collections.emptyList();
    } catch (Exception e) {
      log.error("Error fetching provider schedules for office: {}", officeCode, e);
      throw new ProviderDetailsClientException("Failed to fetch provider schedules", e);
    }
  }

  /** Response object for provider schedules. */
  public record ProviderSchedulesResponse(List<ScheduleDetails> schedules) {}

  /** Schedule details containing schedule lines. */
  public record ScheduleDetails(List<ScheduleLine> scheduleLines) {}

  /** Individual schedule line with category of law. */
  public record ScheduleLine(String categoryOfLaw) {}

  /** Exception thrown when provider details API calls fail. */
  public static class ProviderDetailsClientException extends RuntimeException {
    public ProviderDetailsClientException(String message, Throwable cause) {
      super(message, cause);
    }
  }
}
