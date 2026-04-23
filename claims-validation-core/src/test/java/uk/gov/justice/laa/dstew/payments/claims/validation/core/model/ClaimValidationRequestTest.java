package uk.gov.justice.laa.dstew.payments.claims.validation.core.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ClaimValidationRequestTest {

  @Test
  void addRelatedClaimsItem_initializesWhenNull_andAdds() {
    ClaimValidationRequest req = new ClaimValidationRequest();
    req.setRelatedClaims(null);
    Claim c = Claim.builder().uniqueFileNumber("010101/001").build();
    req.addRelatedClaimsItem(c);
    assertThat(req.getRelatedClaims()).hasSize(1);
    assertThat(req.getRelatedClaims().get(0).getUniqueFileNumber()).isEqualTo("010101/001");
  }

  @Test
  void builder_andSetters_workForScopeAndClaim() {
    Claim c = Claim.builder().uniqueFileNumber("A").build();
    ClaimValidationRequest r = ClaimValidationRequest.builder().claim(c).scope("fee").build();
    assertThat(r.getClaim()).isEqualTo(c);
    assertThat(r.getScope()).isEqualTo("fee");
  }

  @Test
  void addRelatedClaimsItem_appendsWhenListAlreadyPresent() {
    ClaimValidationRequest req = ClaimValidationRequest.builder().build();
    // builder default should give an empty list
    req.addRelatedClaimsItem(Claim.builder().uniqueFileNumber("010102/002").build());
    assertThat(req.getRelatedClaims()).hasSize(1);
  }
}
