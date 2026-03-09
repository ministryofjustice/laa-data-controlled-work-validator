package uk.gov.justice.laa.dstew.payments.claims.validation.validator.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.client.DuplicateClaimClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.client.DuplicateClaimClient.DuplicateClaimInfo;
import uk.gov.justice.laa.dstew.payments.claims.validation.client.DuplicateClaimClient.DuplicateType;
import uk.gov.justice.laa.dstew.payments.claims.validation.error.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ClaimValidator;
import uk.gov.justice.laa.dstew.payments.claims.validation.validator.ValidationContext;

/**
 * Validator for checking duplicate claims.
 * Checks for duplicates within the same submission and across other submissions.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class DuplicateClaimValidator implements ClaimValidator {

  private final DuplicateClaimClient duplicateClaimClient;

  @Override
  public List<ValidationIssue> validate(Map<String, Object> claim, ValidationContext context) {
    List<ValidationIssue> issues = new ArrayList<>();

    String uniqueFileNumber = getStringValue(claim, "uniqueFileNumber");
    if (uniqueFileNumber == null) {
      return issues; // Can't check duplicates without UFN
    }

    String matterTypeCode = getStringValue(claim, "matterTypeCode");
    String claimId = getStringValue(claim, "id");

    log.debug("Checking for duplicate claims with UFN: {}", uniqueFileNumber);

    // Check for duplicates within the same submission
    List<Map<String, Object>> relatedClaims = context.getRelatedClaims();
    if (relatedClaims != null && !relatedClaims.isEmpty()) {
      int currentIndex = findClaimIndex(relatedClaims, claimId);
      if (currentIndex >= 0) {
        List<String> duplicatesInSubmission =
            duplicateClaimClient.checkForDuplicatesInSubmission(relatedClaims, currentIndex);

        if (!duplicatesInSubmission.isEmpty()) {
          log.debug("Found {} duplicates in same submission", duplicatesInSubmission.size());
          issues.add(ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_EXISTING_SUBMISSION
              .toValidationIssue());
        }
      }
    }

    // Check for duplicates in other submissions (via external API)
    try {
      Optional<DuplicateClaimInfo> externalDuplicate = duplicateClaimClient.checkForDuplicate(
          uniqueFileNumber,
          matterTypeCode,
          context.getOfficeAccountNumber(),
          claimId);

      if (externalDuplicate.isPresent()) {
        DuplicateClaimInfo duplicate = externalDuplicate.get();
        log.debug("Found duplicate in another submission: {}", duplicate.submissionId());

        if (duplicate.duplicateType() == DuplicateType.ANOTHER_SUBMISSION) {
          issues.add(ClaimValidationError.INVALID_CLAIM_HAS_DUPLICATE_IN_ANOTHER_SUBMISSION
              .toValidationIssue());
        }
      }

    } catch (DuplicateClaimClient.DuplicateClaimClientException e) {
      log.error("Error checking for duplicates", e);
      // Don't fail validation for technical errors in duplicate check
      issues.add(ValidationIssue.builder()
          .code("DUPLICATE_CHECK_WARNING")
          .message("Unable to verify duplicate claims - please check manually")
          .severity(ValidationIssue.SeverityEnum.WARNING)
          .build());
    }

    return issues;
  }

  private int findClaimIndex(List<Map<String, Object>> claims, String claimId) {
    if (claimId == null) {
      return -1;
    }
    for (int i = 0; i < claims.size(); i++) {
      String id = getStringValue(claims.get(i), "id");
      if (claimId.equals(id)) {
        return i;
      }
    }
    return -1;
  }

  private String getStringValue(Map<String, Object> map, String key) {
    Object value = map.get(key);
    return value != null ? value.toString() : null;
  }

  @Override
  public int priority() {
    return 80; // Run late - after other validations
  }

  @Override
  public String getValidatorCode() {
    return "DUPLICATE_CLAIM";
  }
}

