package uk.gov.justice.laa.dstew.payments.claims.validation.core.client;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;

/** Client for checking duplicate claims against the Data Claims API. */
@Component
@Slf4j
public class ClaimsApiClient {

  private final WebClient dataClaimsWebClient;

  public ClaimsApiClient(@Qualifier("dataClaimsWebClient") WebClient dataClaimsWebClient) {
    this.dataClaimsWebClient = dataClaimsWebClient;
  }

  /**
   * Retrieves claims from the Data Claims API that may be duplicates. Filters out claims from the
   * current submission.
   *
   * @param officeCode the office account number
   * @param feeCode the fee code
   * @param uniqueFileNumber the unique file number
   * @param uniqueClientNumber the unique client number (optional)
   * @param uniqueCaseId the unique case ID (optional)
   * @param submissionClaims claims in current submission to exclude
   * @return list of potentially duplicate claims from previous submissions
   */
  public List<Claim> getClaims(
      String officeCode,
      String feeCode,
      String uniqueFileNumber,
      String uniqueClientNumber,
      String uniqueCaseId,
      List<Claim> submissionClaims) {

    if (officeCode == null || uniqueFileNumber == null) {
      return Collections.emptyList();
    }

    log.debug(
        "Fetching claims for duplicate check - office: {}, UFN: {}, feeCode: {}",
        officeCode,
        uniqueFileNumber,
        feeCode);

    try {
      // TODO: Implement actual API call to Data Claims API
      // The API should support filtering by:
      // - officeCode
      // - feeCode (optional)
      // - uniqueFileNumber
      // - uniqueClientNumber (optional)
      // - uniqueCaseId (optional)
      // - submissionStatus in [CREATED, VALIDATION_IN_PROGRESS, READY_FOR_VALIDATION,
      // VALIDATION_SUCCEEDED]
      // - claimStatus in [READY_TO_PROCESS, VALID]
      //
      // Example:
      // return dataClaimsWebClient
      //     .get()
      //     .uri(uriBuilder -> uriBuilder
      //         .path("/api/v1/claims")
      //         .queryParam("officeCode", officeCode)
      //         .queryParamIfPresent("feeCode", Optional.ofNullable(feeCode))
      //         .queryParam("uniqueFileNumber", uniqueFileNumber)
      //         .queryParamIfPresent("uniqueClientNumber", Optional.ofNullable(uniqueClientNumber))
      //         .queryParamIfPresent("uniqueCaseId", Optional.ofNullable(uniqueCaseId))
      //         .build())
      //     .retrieve()
      //     .bodyToFlux(Claim.class)
      //     .collectList()
      //     .block()
      //     .stream()
      //     .filter(claim -> !isInCurrentSubmission(claim, submissionClaims))
      //     .toList();

      log.debug("Data Claims API call not yet implemented - returning empty list");
      return Collections.emptyList();

    } catch (WebClientResponseException e) {
      log.error("Error fetching claims from Data Claims API: {}", e.getMessage());
      throw new DuplicateClaimClientException("Failed to fetch claims for duplicate check", e);
    } catch (Exception e) {
      log.error("Unexpected error fetching claims", e);
      throw new DuplicateClaimClientException("Failed to fetch claims for duplicate check", e);
    }
  }

  /**
   * Checks if a duplicate claim exists (legacy method).
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

    log.debug(
        "Checking for duplicate claim with UFN: {}, office: {}",
        uniqueFileNumber,
        officeAccountNumber);

    try {
      DuplicateCheckRequest request =
          new DuplicateCheckRequest(
              uniqueFileNumber, matterTypeCode, officeAccountNumber, excludeClaimId);

      DuplicateClaimInfo response =
          dataClaimsWebClient
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
  public List<String> checkForDuplicatesInSubmission(List<Claim> claims, int currentClaimIndex) {

    if (claims == null || claims.isEmpty() || currentClaimIndex < 0) {
      return List.of();
    }

    Claim currentClaim = claims.get(currentClaimIndex);
    String currentUfn = currentClaim.getUniqueFileNumber();
    String currentMatterType = currentClaim.getMatterTypeCode();

    if (currentUfn == null) {
      return List.of();
    }

    // Check for duplicates in earlier claims (to avoid double-reporting)
    return claims.stream()
        .limit(currentClaimIndex)
        .filter(
            claim -> {
              String ufn = claim.getUniqueFileNumber();
              String matterType = claim.getMatterTypeCode();
              return currentUfn.equals(ufn)
                  && (currentMatterType == null || currentMatterType.equals(matterType));
            })
        .map(
            claim -> {
              UUID id = claim.getId();
              return id != null ? id.toString() : null;
            })
        .filter(id -> id != null)
        .toList();
  }

  /**
   * Request object for duplicate check.
   *
   * @param uniqueFileNumber the unique file number
   * @param matterTypeCode the matter type code
   * @param officeAccountNumber the office account number
   * @param excludeClaimId claim ID to exclude from check
   */
  public record DuplicateCheckRequest(
      String uniqueFileNumber,
      String matterTypeCode,
      String officeAccountNumber,
      String excludeClaimId) {}

  /**
   * Information about a duplicate claim.
   *
   * @param claimId the duplicate claim ID
   * @param submissionId the submission ID containing the duplicate
   * @param uniqueFileNumber the unique file number
   * @param duplicateType the type of duplicate found
   */
  public record DuplicateClaimInfo(
      String claimId, String submissionId, String uniqueFileNumber, DuplicateType duplicateType) {}

  /** Type of duplicate found. */
  public enum DuplicateType {
    SAME_SUBMISSION,
    ANOTHER_SUBMISSION,
    PREVIOUS_SUBMISSION
  }

  /** Exception thrown when duplicate check fails. */
  public static class DuplicateClaimClientException extends RuntimeException {
    public DuplicateClaimClientException(String message, Throwable cause) {
      super(message, cause);
    }
  }
}
