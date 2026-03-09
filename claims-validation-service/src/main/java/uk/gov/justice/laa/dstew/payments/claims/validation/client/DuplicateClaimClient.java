package uk.gov.justice.laa.dstew.payments.claims.validation.client;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

/**
 * Client for checking duplicate claims against the Data Claims API.
 */
@Component
@Slf4j
public class DuplicateClaimClient {

  private final WebClient dataClaimsWebClient;

  public DuplicateClaimClient(@Qualifier("dataClaimsWebClient") WebClient dataClaimsWebClient) {
    this.dataClaimsWebClient = dataClaimsWebClient;
  }

  /**
   * Checks if a duplicate claim exists.
   *
   * @param uniqueFileNumber the UFN to check
   * @param matterTypeCode the matter type code
   * @param officeAccountNumber the office account number
   * @param excludeClaimId optional claim ID to exclude from duplicate check
   * @return optional containing duplicate claim info if found
   */
  public Optional<DuplicateClaimInfo> checkForDuplicate(
      String uniqueFileNumber,
      String matterTypeCode,
      String officeAccountNumber,
      String excludeClaimId) {

    if (uniqueFileNumber == null || officeAccountNumber == null) {
      return Optional.empty();
    }

    log.debug("Checking for duplicate claim with UFN: {}, office: {}",
        uniqueFileNumber, officeAccountNumber);

    try {
      DuplicateCheckRequest request = new DuplicateCheckRequest(
          uniqueFileNumber, matterTypeCode, officeAccountNumber, excludeClaimId);

      DuplicateClaimInfo response = dataClaimsWebClient
          .post()
          .uri("/api/v1/claims/duplicate-check")
          .bodyValue(request)
          .retrieve()
          .bodyToMono(DuplicateClaimInfo.class)
          .block();

      return Optional.ofNullable(response);

    } catch (WebClientResponseException.NotFound e) {
      log.debug("No duplicate found");
      return Optional.empty();
    } catch (Exception e) {
      log.error("Error checking for duplicate claims", e);
      throw new DuplicateClaimClientException("Failed to check for duplicates", e);
    }
  }

  /**
   * Checks for duplicates within a list of claims (same submission).
   *
   * @param claims the list of claims to check
   * @param currentClaimIndex the index of the current claim being validated
   * @return list of duplicate claim IDs found
   */
  public List<String> checkForDuplicatesInSubmission(
      List<Map<String, Object>> claims, int currentClaimIndex) {

    if (claims == null || claims.isEmpty() || currentClaimIndex < 0) {
      return List.of();
    }

    Map<String, Object> currentClaim = claims.get(currentClaimIndex);
    String currentUfn = getStringValue(currentClaim, "uniqueFileNumber");
    String currentMatterType = getStringValue(currentClaim, "matterTypeCode");

    if (currentUfn == null) {
      return List.of();
    }

    // Check for duplicates in earlier claims (to avoid double-reporting)
    return claims.stream()
        .limit(currentClaimIndex)
        .filter(claim -> {
          String ufn = getStringValue(claim, "uniqueFileNumber");
          String matterType = getStringValue(claim, "matterTypeCode");
          return currentUfn.equals(ufn)
              && (currentMatterType == null || currentMatterType.equals(matterType));
        })
        .map(claim -> getStringValue(claim, "id"))
        .filter(id -> id != null)
        .toList();
  }

  private String getStringValue(Map<String, Object> map, String key) {
    Object value = map.get(key);
    return value != null ? value.toString() : null;
  }

  /**
   * Request object for duplicate check.
   */
  public record DuplicateCheckRequest(
      String uniqueFileNumber,
      String matterTypeCode,
      String officeAccountNumber,
      String excludeClaimId
  ) {}

  /**
   * Information about a duplicate claim.
   */
  public record DuplicateClaimInfo(
      String claimId,
      String submissionId,
      String uniqueFileNumber,
      DuplicateType duplicateType
  ) {}

  /**
   * Type of duplicate found.
   */
  public enum DuplicateType {
    SAME_SUBMISSION,
    ANOTHER_SUBMISSION,
    PREVIOUS_SUBMISSION
  }

  /**
   * Exception thrown when duplicate check fails.
   */
  public static class DuplicateClaimClientException extends RuntimeException {
    public DuplicateClaimClientException(String message, Throwable cause) {
      super(message, cause);
    }
  }
}
