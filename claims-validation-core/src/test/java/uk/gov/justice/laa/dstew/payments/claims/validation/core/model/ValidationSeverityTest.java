package uk.gov.justice.laa.dstew.payments.claims.validation.core.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ValidationSeverityTest {

  @Test
  void enumValues_haveExpectedStringValues_andToString() {
    for (ValidationSeverity s : ValidationSeverity.values()) {
      assertThat(s.getValue()).isNotNull();
      assertThat(s.toString()).isEqualTo(s.getValue());
    }
    assertThat(ValidationSeverity.ERROR.getValue()).isEqualTo("ERROR");
    assertThat(ValidationSeverity.WARNING.getValue()).isEqualTo("WARNING");
    assertThat(ValidationSeverity.INFO.getValue()).isEqualTo("INFO");
  }
}
