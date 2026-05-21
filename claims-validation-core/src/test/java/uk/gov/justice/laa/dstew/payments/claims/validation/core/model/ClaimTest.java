package uk.gov.justice.laa.dstew.payments.claims.validation.core.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

class ClaimTest {

  @Test
  void builder_equals_hashCode_and_toString_behaviour() {
    Claim c1 = Claim.builder()
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .officeAccountNumber("1A")
        .uniqueFileNumber("010101/001")
        .build();

    Claim c2 = Claim.builder()
        .areaOfLaw(AreaOfLaw.LEGAL_HELP)
        .officeAccountNumber("1A")
        .uniqueFileNumber("010101/001")
        .build();

    assertThat(c1).isEqualTo(c2).hasSameHashCodeAs(c2);
    assertThat(c1.toString()).contains("uniqueFileNumber");

    // setters/getters
    c1.setOfficeAccountNumber("2B");
    assertThat(c1.getOfficeAccountNumber()).isEqualTo("2B");
  }
}
