package uk.gov.justice.laa.dstew.payments.claims.validation.core.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ValidationResultTest {

  @Test
  void addIssuesItem_initializesWhenNull_andAdds() {
    ValidationResult r = new ValidationResult();
    // simulate null issues
    r.setIssues(null);
    ValidationIssue issue = ValidationIssue.builder().code("X").message("m").severity(ValidationSeverity.INFO).build();
    r.addIssuesItem(issue);
    assertThat(r.getIssues()).hasSize(1);
    assertThat(r.getIssues().get(0).getCode()).isEqualTo("X");
  }
}
