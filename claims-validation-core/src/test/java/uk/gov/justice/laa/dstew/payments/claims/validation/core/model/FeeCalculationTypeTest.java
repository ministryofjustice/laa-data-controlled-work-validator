package uk.gov.justice.laa.dstew.payments.claims.validation.core.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class FeeCalculationTypeTest {

  @Test
  void getValue_andToString_returnValue() {
    assertThat(FeeCalculationType.HOURLY.getValue()).isEqualTo("HOURLY");
    assertThat(FeeCalculationType.FIXED.getValue()).isEqualTo("FIXED");
    assertThat(FeeCalculationType.DISB_ONLY.getValue()).isEqualTo("DISB_ONLY");
    assertThat(FeeCalculationType.HOURLY.toString()).isEqualTo("HOURLY");
  }

  @Test
  void fromValue_parsesKnownValues_andThrowsForUnknown() {
    assertThat(FeeCalculationType.fromValue("HOURLY")).isEqualTo(FeeCalculationType.HOURLY);
    assertThat(FeeCalculationType.fromValue("FIXED")).isEqualTo(FeeCalculationType.FIXED);

    assertThatThrownBy(() -> FeeCalculationType.fromValue("UNKNOWN"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Unexpected value");
  }
}
