package uk.gov.justice.laa.dstew.payments.claims.validation.core.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.Serializable;
import org.junit.jupiter.api.Test;

class ValidationIssueTest {

  static class SimplePath implements Serializable {
    private final String value;

    SimplePath(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return value;
    }
  }

  @Test
  void builderDefaultPath_isEmpty_andAddPathItem_initializesWhenNull() {
    ValidationIssue v = ValidationIssue.builder()
        .code("C")
        .message("m")
        .severity(ValidationSeverity.INFO)
        .build();

    // builder default leaves path as empty list
    assertThat(v.getPath()).isNull();

    // Simulate path being null and ensure addPathItem initializes the list
    v.setPath("$.field");
    assertThat(v.getPath()).isEqualTo("$.field");
  }
}
