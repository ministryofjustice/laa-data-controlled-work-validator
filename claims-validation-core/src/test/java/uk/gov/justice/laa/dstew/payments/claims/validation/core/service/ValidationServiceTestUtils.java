package uk.gov.justice.laa.dstew.payments.claims.validation.core.service;

import java.util.List;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.error.ValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;

public class ValidationServiceTestUtils {

  public static void assertContextClaimError(
          List<ValidationIssue> issues, ValidationError validationError) {
    AssertionsForInterfaceTypes.assertThat(issues).extracting(ValidationIssue::getMessage)
            .containsExactly(validationError.getDisplayMessage());
  }

  public static void assertContextClaimError(
          List<ValidationIssue> issues, ValidationIssue validationIssue) {
    AssertionsForInterfaceTypes.assertThat(issues).extracting(ValidationIssue::getMessage)
            .containsExactly(validationIssue.getMessage());
  }

  public static void assertContextClaimError(
          List<ValidationIssue> issues, String validationMessage) {
    AssertionsForInterfaceTypes.assertThat(issues).extracting(ValidationIssue::getMessage)
            .containsExactly(validationMessage);
  }

}
