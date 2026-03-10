package uk.gov.justice.laa.dstew.payments.claims.validation.service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimValidationRequest;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.service.ValidationService;

@WebMvcTest(ValidationController.class)
class ValidationControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private ValidationService mockValidationService;

  @Test
  void validateClaim_returnsOkStatusAndValidResult() throws Exception {
    ValidationResult validResult = new ValidationResult();
    validResult.setIsValid(true);
    validResult.setIssues(Collections.emptyList());

    when(mockValidationService.validateClaim(any(ClaimValidationRequest.class)))
        .thenReturn(validResult);

    mockMvc
        .perform(
            post("/v1/validation/claim")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\"claim\": {\"areaOfLaw\": \"LEGAL_HELP\", \"officeAccountNumber\": \"1A234B\"}, \"scope\": \"fee\"}")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.isValid").value(true))
        .andExpect(jsonPath("$.issues").isEmpty());
  }

  @Test
  void validateClaim_returnsOkStatusWithIssues() throws Exception {
    ValidationIssue issue =
        new ValidationIssue(
            "FEE.MISSING_JUSTIFICATION",
            "Enhancement fee requires a justification.",
            ValidationSeverity.ERROR);

    ValidationResult invalidResult = new ValidationResult();
    invalidResult.setIsValid(false);
    invalidResult.setIssues(List.of(issue));

    when(mockValidationService.validateClaim(any(ClaimValidationRequest.class)))
        .thenReturn(invalidResult);

    mockMvc
        .perform(
            post("/v1/validation/claim")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\"claim\": {\"areaOfLaw\": \"LEGAL_HELP\", \"officeAccountNumber\": \"1A234B\", \"fees\": [{\"type\": \"enhancement\"}]}}")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.isValid").value(false))
        .andExpect(jsonPath("$.issues[0].code").value("FEE.MISSING_JUSTIFICATION"))
        .andExpect(jsonPath("$.issues[0].severity").value("ERROR"));
  }

  @Test
  void validateClaim_returnsBadRequestForInvalidInput() throws Exception {
    mockMvc
        .perform(
            post("/v1/validation/claim")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
  }
}
