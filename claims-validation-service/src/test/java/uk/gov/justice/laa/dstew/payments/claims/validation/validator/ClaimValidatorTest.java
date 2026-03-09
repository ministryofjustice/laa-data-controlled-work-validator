package uk.gov.justice.laa.dstew.payments.claims.validation.validator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;

class ClaimValidatorTest {

  private final ClaimValidator claimValidator = new ClaimValidator();

  @Test
  void validate_returnsEmptyListForValidClaim() {
    Map<String, Object> claim = new HashMap<>();

    List<ValidationIssue> issues = claimValidator.validate(claim, null);

    // TODO: Update this test when business rules are implemented
    assertThat(issues).isEmpty();
  }

  @Test
  void validate_appliesFeeRulesWhenScopeIsFee() {
    Map<String, Object> claim = new HashMap<>();

    List<ValidationIssue> issues = claimValidator.validate(claim, "fee");

    // TODO: Update this test when fee validation rules are implemented
    assertThat(issues).isNotNull();
  }

  @Test
  void validate_appliesGeneralRulesForAllScopes() {
    Map<String, Object> claim = new HashMap<>();

    List<ValidationIssue> issues = claimValidator.validate(claim, "general");

    // TODO: Update this test when general validation rules are implemented
    assertThat(issues).isNotNull();
  }
}
