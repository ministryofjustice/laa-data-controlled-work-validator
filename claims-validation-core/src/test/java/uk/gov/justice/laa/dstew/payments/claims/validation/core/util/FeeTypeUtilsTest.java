package uk.gov.justice.laa.dstew.payments.claims.validation.core.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.FeeCalculationType;

@DisplayName("FeeTypeUtils")
class FeeTypeUtilsTest {

  @Test
  @DisplayName("isDisbursementClaim returns true for DISB_ONLY value and false otherwise")
  void isDisbursementClaimBehavior() {
    String disbValue = FeeCalculationType.DISB_ONLY.getValue();
    assertThat(FeeTypeUtils.isDisbursementClaim(disbValue)).isTrue();
    assertThat(FeeTypeUtils.isDisbursementClaim("OTHER")).isFalse();
    assertThat(FeeTypeUtils.isDisbursementClaim(null)).isFalse();
  }

  @Test
  @DisplayName("isDisbursementClaim is case-sensitive and rejects different case")
  void caseSensitivity() {
    String lower = FeeCalculationType.DISB_ONLY.getValue().toLowerCase();
    assertThat(FeeTypeUtils.isDisbursementClaim(lower)).isFalse();
  }
}
