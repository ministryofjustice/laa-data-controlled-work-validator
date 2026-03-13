package uk.gov.justice.laa.dstew.payments.claims.validation.grpc.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimValidationRequest;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.grpc.proto.ProtoClaim;
import uk.gov.justice.laa.dstew.payments.claims.validation.grpc.proto.ProtoClaimValidationRequest;
import uk.gov.justice.laa.dstew.payments.claims.validation.grpc.proto.ProtoClaimValidationResponse;

/** Unit tests for ClaimProtoMapper. */
class ClaimProtoMapperTest {

  private ClaimProtoMapper mapper;

  @BeforeEach
  void setUp() {
    mapper = new ClaimProtoMapper();
  }

  @Test
  void toDomainRequest_mapsAllFields() {
    // Given
    String claimId = "550e8400-e29b-41d4-a716-446655440000";
    ProtoClaim protoClaim =
        ProtoClaim.newBuilder()
            .setId(claimId)
            .setAreaOfLaw("LEGAL HELP")
            .setOfficeAccountNumber("1A234B")
            .setUniqueFileNumber("010125/001")
            .setCaseStartDate("2025-01-15")
            .setStatus("READY_TO_PROCESS")
            .setNetProfitCostsAmount(500.00)
            .setLineNumber(1)
            .setIsVatApplicable(true)
            .build();

    ProtoClaimValidationRequest protoRequest =
        ProtoClaimValidationRequest.newBuilder()
            .setClaim(protoClaim)
            .setScope("all")
            .build();

    // When
    ClaimValidationRequest domainRequest = mapper.toDomainRequest(protoRequest);

    // Then
    assertThat(domainRequest.getScope()).isEqualTo("all");

    Claim claim = domainRequest.getClaim();
    assertThat(claim.getId()).isEqualTo(UUID.fromString(claimId));
    assertThat(claim.getAreaOfLaw()).isEqualTo(AreaOfLaw.LEGAL_HELP);
    assertThat(claim.getOfficeAccountNumber()).isEqualTo("1A234B");
    assertThat(claim.getUniqueFileNumber()).isEqualTo("010125/001");
    assertThat(claim.getCaseStartDate()).isEqualTo("2025-01-15");
    assertThat(claim.getStatus()).isEqualTo(ClaimStatus.READY_TO_PROCESS);
    assertThat(claim.getNetProfitCostsAmount()).isEqualByComparingTo(BigDecimal.valueOf(500.00));
    assertThat(claim.getLineNumber()).isEqualTo(1);
    assertThat(claim.getIsVatApplicable()).isTrue();
  }

  @Test
  void toDomainRequest_handlesEmptyStringsAsNull() {
    // Given
    ProtoClaim protoClaim =
        ProtoClaim.newBuilder()
            .setId("")
            .setAreaOfLaw("")
            .build();

    ProtoClaimValidationRequest protoRequest =
        ProtoClaimValidationRequest.newBuilder()
            .setClaim(protoClaim)
            .setScope("")
            .build();

    // When
    ClaimValidationRequest domainRequest = mapper.toDomainRequest(protoRequest);

    // Then
    assertThat(domainRequest.getScope()).isNull();
    assertThat(domainRequest.getClaim().getId()).isNull();
    assertThat(domainRequest.getClaim().getAreaOfLaw()).isNull();
  }

  @Test
  void toProtoResponse_mapsValidResponse() {
    // Given
    ValidationResult domainResponse = new ValidationResult();
    domainResponse.setIsValid(true);
    domainResponse.setIssues(List.of());

    // When
    ProtoClaimValidationResponse protoResponse = mapper.toProtoResponse(domainResponse);

    // Then
    assertThat(protoResponse.getIsValid()).isTrue();
    assertThat(protoResponse.getIssuesList()).isEmpty();
  }

  @Test
  void toProtoResponse_mapsIssues() {
    // Given
    ValidationIssue issue =
        new ValidationIssue(
            "INVALID_DATE", "Case start date is invalid", ValidationSeverity.ERROR);
    issue.setTechnicalMessage("Date format must be YYYY-MM-DD");

    ValidationResult domainResponse = new ValidationResult();
    domainResponse.setIsValid(false);
    domainResponse.setIssues(List.of(issue));

    // When
    ProtoClaimValidationResponse protoResponse = mapper.toProtoResponse(domainResponse);

    // Then
    assertThat(protoResponse.getIsValid()).isFalse();
    assertThat(protoResponse.getIssuesList()).hasSize(1);

    uk.gov.justice.laa.dstew.payments.claims.validation.grpc.proto.ProtoValidationIssue protoIssue =
        protoResponse.getIssues(0);
    assertThat(protoIssue.getCode()).isEqualTo("INVALID_DATE");
    assertThat(protoIssue.getMessage()).isEqualTo("Case start date is invalid");
    assertThat(protoIssue.getSeverity()).isEqualTo("ERROR");
    assertThat(protoIssue.getTechnicalMessage()).isEqualTo("Date format must be YYYY-MM-DD");
  }

@Test
  void toDomainRequest_mapsRelatedClaims() {
    // Given
    String mainClaimId = "550e8400-e29b-41d4-a716-446655440001";
    String relatedClaimId = "550e8400-e29b-41d4-a716-446655440002";

    ProtoClaim mainClaim =
        ProtoClaim.newBuilder()
            .setId(mainClaimId)
            .build();

    ProtoClaim relatedClaim =
        ProtoClaim.newBuilder()
            .setId(relatedClaimId)
            .build();

    ProtoClaimValidationRequest protoRequest =
        ProtoClaimValidationRequest.newBuilder()
            .setClaim(mainClaim)
            .addRelatedClaims(relatedClaim)
            .build();

    // When
    ClaimValidationRequest domainRequest = mapper.toDomainRequest(protoRequest);

    // Then
    assertThat(domainRequest.getRelatedClaims()).hasSize(1);
    assertThat(domainRequest.getRelatedClaims().get(0).getId())
        .isEqualTo(UUID.fromString(relatedClaimId));
  }
}







