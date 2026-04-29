package uk.gov.justice.laa.dstew.payments.claims.validation.core.service;

import java.util.List;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;


public class ValidationServiceTestUtils {

  public static void assertContextClaimError(
          List<ValidationIssue> issues, ClaimValidationError claimValidationError) {
    AssertionsForInterfaceTypes.assertThat(issues).extracting(ValidationIssue::getMessage)
            .containsExactly(
                    claimValidationError.getDisplayMessage());
  }

}
