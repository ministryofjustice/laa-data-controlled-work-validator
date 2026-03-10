package uk.gov.justice.laa.dstew.payments.claims.validation.service.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.service.ValidationService;

/**
 * Tests for {@link ValidationControllerAdvice}.
 *
 * <p>Verifies that JSON parsing errors are converted to validation responses instead of 400 Bad
 * Request errors.
 */
@WebMvcTest(ValidationController.class)
class ValidationControllerAdviceTest {

  @Autowired private MockMvc mockMvc;

  @MockitoBean private ValidationService validationService;

  @Test
  void malformedJson_returns200WithInvalidJsonSyntaxError() throws Exception {
    String malformedJson = "{ invalid json }";

    mockMvc
        .perform(
            post("/v1/validation/claim")
                .contentType(MediaType.APPLICATION_JSON)
                .content(malformedJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.isValid").value(false))
        .andExpect(jsonPath("$.issues[0].code").value("INVALID_JSON_SYNTAX"))
        .andExpect(jsonPath("$.issues[0].severity").value("ERROR"))
        .andExpect(jsonPath("$.issues[0].message").exists())
        .andExpect(jsonPath("$.issues[0].technicalMessage").exists());
  }

  @Test
  void invalidEnumValue_returns200WithInvalidFieldTypeError() throws Exception {
    String invalidEnumJson =
        """
        {
          "claim": {
            "areaOfLaw": "INVALID_VALUE",
            "officeAccountNumber": "1A234B"
          }
        }
        """;

    mockMvc
        .perform(
            post("/v1/validation/claim")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidEnumJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.isValid").value(false))
        .andExpect(jsonPath("$.issues[0].code").value("INVALID_FIELD_TYPE"))
        .andExpect(jsonPath("$.issues[0].severity").value("ERROR"))
        .andExpect(
            jsonPath("$.issues[0].message").value("Field 'claim.areaOfLaw' has an invalid value"))
        .andExpect(jsonPath("$.issues[0].technicalMessage").exists());
  }

  @Test
  void wrongType_returns200WithInvalidFieldTypeError() throws Exception {
    String wrongTypeJson =
        """
        {
          "claim": {
            "areaOfLaw": 123,
            "officeAccountNumber": "1A234B"
          }
        }
        """;

    mockMvc
        .perform(
            post("/v1/validation/claim")
                .contentType(MediaType.APPLICATION_JSON)
                .content(wrongTypeJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.isValid").value(false))
        .andExpect(jsonPath("$.issues[0].code").value("INVALID_FIELD_TYPE"))
        .andExpect(jsonPath("$.issues[0].severity").value("ERROR"));
  }

  @Test
  void emptyBody_returns200WithError() throws Exception {
    mockMvc
        .perform(post("/v1/validation/claim").contentType(MediaType.APPLICATION_JSON).content(""))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.isValid").value(false))
        .andExpect(jsonPath("$.issues[0].severity").value("ERROR"));
  }
}
