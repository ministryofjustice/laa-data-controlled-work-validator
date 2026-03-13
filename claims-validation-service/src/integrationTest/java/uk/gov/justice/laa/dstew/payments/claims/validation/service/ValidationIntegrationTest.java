package uk.gov.justice.laa.dstew.payments.claims.validation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.service.client.ClaimsApiClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.service.client.FeeSchemeClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.service.client.FeeSchemeClient.FeeDetailsResponse;

/**
 * Integration test for the validation endpoint. Tests the full validation flow with real validators
 * but mocked external clients.
 */
@SpringBootTest
@AutoConfigureMockMvc
class ValidationIntegrationTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private FeeSchemeClient feeSchemeClient;

  @MockitoBean private ClaimsApiClient claimsApiClient;

  @BeforeEach
  void setUp() {
    // Default mock behavior for external services
    when(feeSchemeClient.getFeeDetails(anyString()))
        .thenReturn(
            Optional.of(
                new FeeDetailsResponse(
                    "ABC123", "STANDARD", "LEGAL_HELP", "Description", Map.of())));
    when(feeSchemeClient.isProviderAuthorizedForCategoryOfLaw(anyString(), anyString()))
        .thenReturn(true);
    when(claimsApiClient.checkForDuplicate(any(), any(), any(), any()))
        .thenReturn(Optional.empty());
  }

  @Test
  void validateClaim_returnsValidResult_whenClaimIsValid() throws Exception {
    String requestBody =
        """
        {
          "claim": {
            "areaOfLaw": "LEGAL HELP",
            "officeAccountNumber": "1A234B",
            "feeCode": "ABC123",
            "uniqueFileNumber": "010120/001",
            "caseStartDate": "2020-01-15",
            "caseConcludedDate": "2020-06-15"
          },
          "scope": "fee"
        }
        """;

    mockMvc
        .perform(
            post("/v1/validation/claim")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.isValid").value(true))
        .andExpect(jsonPath("$.issues").isArray());
  }

  @Test
  void validateClaim_returnsInvalidResult_whenMandatoryFieldMissing() throws Exception {
    // Missing feeCode but has areaOfLaw and officeAccountNumber
    String requestBody =
        """
        {
          "claim": {
            "areaOfLaw": "LEGAL HELP",
            "officeAccountNumber": "1A234B",
            "caseStartDate": "2020-01-15"
          },
          "scope": "all"
        }
        """;

    mockMvc
        .perform(
            post("/v1/validation/claim")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.isValid").value(false))
        .andExpect(jsonPath("$.issues").isArray())
        .andExpect(jsonPath("$.issues[0].code").value("MISSING_MANDATORY_FIELD"));
  }

  @Test
  void validateClaim_returnsInvalidResult_whenUfnFormatInvalid() throws Exception {
    String requestBody =
        """
        {
          "claim": {
            "areaOfLaw": "LEGAL HELP",
            "officeAccountNumber": "1A234B",
            "feeCode": "ABC123",
            "uniqueFileNumber": "invalid-format"
          }
        }
        """;

    mockMvc
        .perform(
            post("/v1/validation/claim")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.isValid").value(false))
        .andExpect(jsonPath("$.issues[?(@.code == 'INVALID_UNIQUE_FILE_NUMBER_FORMAT')]").exists());
  }

  @Test
  void validateClaim_returnsInvalidResult_whenDateInFuture() throws Exception {
    String requestBody =
        """
        {
          "claim": {
            "areaOfLaw": "LEGAL HELP",
            "officeAccountNumber": "1A234B",
            "feeCode": "ABC123",
            "caseStartDate": "2030-01-15"
          }
        }
        """;

    mockMvc
        .perform(
            post("/v1/validation/claim")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.isValid").value(false))
        .andExpect(jsonPath("$.issues[?(@.code =~ /.*CASE_START_DATE.*/)]").exists());
  }

  @Test
  void validateClaim_appliesScopeFilter_forDisbursementScope() throws Exception {
    String requestBody =
        """
        {
          "claim": {
            "areaOfLaw": "CRIME LOWER",
            "officeAccountNumber": "1A234B",
            "feeCode": "ABC123",
            "disbursementsVatAmount": 500000.00
          },
          "scope": "disbursement"
        }
        """;

    MvcResult result =
        mockMvc
            .perform(
                post("/v1/validation/claim")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.isValid").value(true))
            .andReturn();

    // VAT amount of 500k is valid for CRIME_LOWER (limit is 999,999.99)
    String response = result.getResponse().getContentAsString();
    assertThat(response).contains("\"isValid\":true");
  }

  @Test
  void validateClaim_returnsBadRequest_whenClaimMissing() throws Exception {
    String requestBody =
        """
        {
          "scope": "fee"
        }
        """;

    mockMvc
        .perform(
            post("/v1/validation/claim")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
        .andExpect(status().isBadRequest());
  }
}
