package uk.gov.justice.laa.dstew.payments.claims.validation.service.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.justice.laa.dstew.payments.claims.api.ValidationApi;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimValidationRequest;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.service.ValidationService;

/** Controller for handling claim validation requests. */
@RestController
@RequiredArgsConstructor
@Slf4j
public class ValidationController implements ValidationApi {

  private final ValidationService validationService;

  @Override
  public ResponseEntity<ValidationResult> validateClaim(ClaimValidationRequest request) {
    log.info("Received claim validation request with scope: {}", request.getScope());

    ValidationResult result = validationService.validateClaim(request);

    log.info(
        "Validation completed. isValid: {}, issues count: {}",
        result.getIsValid(),
        result.getIssues().size());

    return ResponseEntity.ok(result);
  }
}
