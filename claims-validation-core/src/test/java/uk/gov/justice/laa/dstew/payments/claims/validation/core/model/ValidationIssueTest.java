package uk.gov.justice.laa.dstew.payments.claims.validation.core.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ValidationIssueTest {

  static class SimplePath implements ValidationIssuePathInner {
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
    assertThat(v.getPath()).isNotNull();
    assertThat(v.getPath()).isEmpty();

    // Simulate path being null and ensure addPathItem initializes the list
    v.setPath(null);
    v.addPathItem(new SimplePath("$.field"));
    assertThat(v.getPath()).hasSize(1);
    assertThat(v.getPath().get(0).toString()).isEqualTo("$.field");
  }

  @Test
  void addPathItem_appendsWhenListExists() {
    ValidationIssue v = new ValidationIssue();
    // ensure default constructor gives non-null path via field initializer
    assertThat(v.getPath()).isNotNull();
    v.addPathItem(new SimplePath("a"));
    v.addPathItem(new SimplePath("b"));
    assertThat(v.getPath()).hasSize(2);
    assertThat(v.getPath().get(1).toString()).isEqualTo("b");
  }
}
