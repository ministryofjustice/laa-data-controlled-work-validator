package uk.gov.justice.laa.dstew.payments.claims.validation.core.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimResponse;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimStatus;

@DisplayName("ClaimMapper")
class ClaimMapperTest {

  @Test
  @DisplayName("fromClaimResponse returns null for null input")
  void fromClaimResponse_null() {
    assertThat(ClaimMapper.fromClaimResponse(null)).isNull();
  }

  @Test
  @DisplayName("fromClaimResponse maps basic fields")
  void fromClaimResponse_mapsBasicFields() {
    ClaimResponse response = Mockito.mock(ClaimResponse.class);
    String id = UUID.randomUUID().toString();
    String subId = UUID.randomUUID().toString();

    Mockito.when(response.getId()).thenReturn(id);
    Mockito.when(response.getSubmissionId()).thenReturn(subId);
    Mockito.when(response.getStatus())
        .thenReturn(ClaimStatus.VALID);
    Mockito.when(response.getLineNumber()).thenReturn(7);
    Mockito.when(response.getFeeCode()).thenReturn("FEE1");

    Claim claim = ClaimMapper.fromClaimResponse(response);
    assertThat(claim).isNotNull();
    assertThat(claim.getId()).isEqualTo(UUID.fromString(id));
    assertThat(claim.getSubmissionId()).isEqualTo(UUID.fromString(subId));
    assertThat(claim.getStatus())
        .isEqualTo(ClaimStatus.VALID);
    assertThat(claim.getLineNumber()).isEqualTo(7);
    assertThat(claim.getFeeCode()).isEqualTo("FEE1");
  }

  @Test
  @DisplayName("fromClaimResponse handles null status and null fields")
  void fromClaimResponse_handlesNulls() {
    ClaimResponse response = Mockito.mock(ClaimResponse.class);
    Mockito.when(response.getId()).thenReturn(null);
    Mockito.when(response.getSubmissionId()).thenReturn(null);
    Mockito.when(response.getStatus()).thenReturn(null);

    Claim claim = ClaimMapper.fromClaimResponse(response);
    assertThat(claim).isNotNull();
    assertThat(claim.getId()).isNull();
    assertThat(claim.getSubmissionId()).isNull();
    assertThat(claim.getStatus()).isNull();
  }

  @Test
  @DisplayName("fromClaimResponse throws when IDs are invalid UUID strings")
  void fromClaimResponse_invalidUuidThrows() {
    ClaimResponse response = Mockito.mock(ClaimResponse.class);
    Mockito.when(response.getId()).thenReturn("not-a-uuid");

    assertThatThrownBy(() -> ClaimMapper.fromClaimResponse(response))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
