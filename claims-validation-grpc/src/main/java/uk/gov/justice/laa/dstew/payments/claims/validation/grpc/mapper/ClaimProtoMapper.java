package uk.gov.justice.laa.dstew.payments.claims.validation.grpc.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimStatus;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimValidationRequest;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssuePathInner;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.grpc.proto.ProtoClaim;
import uk.gov.justice.laa.dstew.payments.claims.validation.grpc.proto.ProtoClaimValidationRequest;
import uk.gov.justice.laa.dstew.payments.claims.validation.grpc.proto.ProtoClaimValidationResponse;
import uk.gov.justice.laa.dstew.payments.claims.validation.grpc.proto.ProtoValidationIssue;

/**
 * Mapper for converting between Protocol Buffer messages and domain objects.
 *
 * <p>Handles the conversion between gRPC proto-generated classes and the existing domain model
 * from claims-validation-api.
 */
@Component
public class ClaimProtoMapper {

  /**
   * Converts a gRPC ProtoClaimValidationRequest to domain ClaimValidationRequest.
   *
   * @param protoRequest the gRPC request
   * @return the domain request
   */
  public ClaimValidationRequest toDomainRequest(ProtoClaimValidationRequest protoRequest) {

    ClaimValidationRequest domainRequest = new ClaimValidationRequest();
    domainRequest.setClaim(toDomainClaim(protoRequest.getClaim()));
    domainRequest.setScope(
        protoRequest.getScope().isEmpty() ? null : protoRequest.getScope());

    if (protoRequest.getRelatedClaimsCount() > 0) {
      List<Claim> relatedClaims = new ArrayList<>();
      for (ProtoClaim protoClaim : protoRequest.getRelatedClaimsList()) {
        relatedClaims.add(toDomainClaim(protoClaim));
      }
      domainRequest.setRelatedClaims(relatedClaims);
    }

    return domainRequest;
  }

  /**
   * Converts a gRPC ProtoClaim to domain Claim.
   *
   * @param protoClaim the gRPC claim
   * @return the domain claim
   */
  public Claim toDomainClaim(ProtoClaim protoClaim) {

    Claim claim = new Claim();

    // UUID fields
    claim.setId(toUuid(protoClaim.getId()));
    claim.setSubmissionId(toUuid(protoClaim.getSubmissionId()));

    // String fields
    claim.setOfficeAccountNumber(nullIfEmpty(protoClaim.getOfficeAccountNumber()));
    claim.setUniqueFileNumber(nullIfEmpty(protoClaim.getUniqueFileNumber()));
    claim.setCaseStartDate(nullIfEmpty(protoClaim.getCaseStartDate()));
    claim.setCaseConcludedDate(nullIfEmpty(protoClaim.getCaseConcludedDate()));
    claim.setClientDateOfBirth(nullIfEmpty(protoClaim.getClientDateOfBirth()));
    claim.setClientForename(nullIfEmpty(protoClaim.getClientForename()));
    claim.setClientSurname(nullIfEmpty(protoClaim.getClientSurname()));
    claim.setUniqueClientNumber(nullIfEmpty(protoClaim.getUniqueClientNumber()));
    claim.setMatterTypeCode(nullIfEmpty(protoClaim.getMatterTypeCode()));
    claim.setStageReachedCode(nullIfEmpty(protoClaim.getStageReachedCode()));
    claim.setOutcomeCode(nullIfEmpty(protoClaim.getOutcomeCode()));
    claim.setFeeCode(nullIfEmpty(protoClaim.getFeeCode()));
    claim.setScheduleReference(nullIfEmpty(protoClaim.getScheduleReference()));
    claim.setSubmissionPeriod(nullIfEmpty(protoClaim.getSubmissionPeriod()));
    claim.setClientPostcode(nullIfEmpty(protoClaim.getClientPostcode()));
    claim.setGenderCode(nullIfEmpty(protoClaim.getGenderCode()));
    claim.setEthnicityCode(nullIfEmpty(protoClaim.getEthnicityCode()));
    claim.setDisabilityCode(nullIfEmpty(protoClaim.getDisabilityCode()));
    claim.setCaseId(nullIfEmpty(protoClaim.getCaseId()));
    claim.setCaseReferenceNumber(nullIfEmpty(protoClaim.getCaseReferenceNumber()));
    claim.setPriorAuthorityReference(nullIfEmpty(protoClaim.getPriorAuthorityReference()));
    claim.setCourtLocationCode(nullIfEmpty(protoClaim.getCourtLocationCode()));
    claim.setAdviceTypeCode(nullIfEmpty(protoClaim.getAdviceTypeCode()));
    claim.setSurgeryDate(nullIfEmpty(protoClaim.getSurgeryDate()));
    claim.setAitHearingCentreCode(nullIfEmpty(protoClaim.getAitHearingCentreCode()));
    claim.setLocalAuthorityNumber(nullIfEmpty(protoClaim.getLocalAuthorityNumber()));
    claim.setCreatedByUserId(nullIfEmpty(protoClaim.getCreatedByUserId()));
    claim.setCrimeMatterTypeCode(nullIfEmpty(protoClaim.getCrimeMatterTypeCode()));
    claim.setFeeSchemeCode(nullIfEmpty(protoClaim.getFeeSchemeCode()));
    claim.setMeetingsAttendedCode(nullIfEmpty(protoClaim.getMeetingsAttendedCode()));

    // Enum fields
    if (!protoClaim.getAreaOfLaw().isEmpty()) {
      try {
        claim.setAreaOfLaw(AreaOfLaw.fromValue(protoClaim.getAreaOfLaw()));
      } catch (IllegalArgumentException e) {
        // Leave as null if invalid
      }
    }

    if (!protoClaim.getStatus().isEmpty()) {
      try {
        claim.setStatus(ClaimStatus.fromValue(protoClaim.getStatus()));
      } catch (IllegalArgumentException e) {
        // Leave as null if invalid
      }
    }

    // Numeric fields
    claim.setNetProfitCostsAmount(toBigDecimal(protoClaim.getNetProfitCostsAmount()));
    claim.setDisbursementsVatAmount(toBigDecimal(protoClaim.getDisbursementsVatAmount()));
    claim.setNetDisbursementAmount(toBigDecimal(protoClaim.getNetDisbursementAmount()));
    claim.setTravelWaitingCostsAmount(toBigDecimal(protoClaim.getTravelWaitingCostsAmount()));
    claim.setNetCounselCostsAmount(toBigDecimal(protoClaim.getNetCounselCostsAmount()));

    // Integer fields
    claim.setLineNumber(protoClaim.getLineNumber() > 0 ? protoClaim.getLineNumber() : null);
    claim.setAdviceTime(protoClaim.getAdviceTime() > 0 ? protoClaim.getAdviceTime() : null);
    claim.setTravelTime(protoClaim.getTravelTime() > 0 ? protoClaim.getTravelTime() : null);
    claim.setWaitingTime(protoClaim.getWaitingTime() > 0 ? protoClaim.getWaitingTime() : null);
    claim.setVersion(protoClaim.getVersion() > 0 ? protoClaim.getVersion() : null);

    // Boolean fields
    claim.setIsVatApplicable(protoClaim.getIsVatApplicable());
    claim.setIsAmended(protoClaim.getIsAmended());
    claim.setHasAssessment(protoClaim.getHasAssessment());

    return claim;
  }

  /**
   * Converts a domain ValidationResult to gRPC ProtoClaimValidationResponse.
   *
   * @param domainResponse the domain response
   * @return the gRPC response
   */
  public ProtoClaimValidationResponse toProtoResponse(ValidationResult domainResponse) {

    ProtoClaimValidationResponse.Builder builder = ProtoClaimValidationResponse.newBuilder();

    builder.setIsValid(Boolean.TRUE.equals(domainResponse.getIsValid()));

    if (domainResponse.getIssues() != null) {
      for (ValidationIssue issue : domainResponse.getIssues()) {
        builder.addIssues(toProtoIssue(issue));
      }
    }

    return builder.build();
  }

  /**
   * Converts a domain ValidationIssue to gRPC ProtoValidationIssue.
   *
   * @param issue the domain issue
   * @return the gRPC issue
   */
  private ProtoValidationIssue toProtoIssue(ValidationIssue issue) {

    ProtoValidationIssue.Builder builder = ProtoValidationIssue.newBuilder();

    builder.setCode(nullToEmpty(issue.getCode()));
    builder.setMessage(nullToEmpty(issue.getMessage()));
    builder.setSeverity(issue.getSeverity() != null ? issue.getSeverity().getValue() : "");
    builder.setTechnicalMessage(nullToEmpty(issue.getTechnicalMessage()));

    if (issue.getPath() != null) {
      for (ValidationIssuePathInner pathItem : issue.getPath()) {
        if (pathItem != null) {
          builder.addPath(pathItem.toString());
        }
      }
    }

    return builder.build();
  }

  private String nullIfEmpty(String value) {
    return value == null || value.isEmpty() ? null : value;
  }

  private String nullToEmpty(String value) {
    return value == null ? "" : value;
  }

  private BigDecimal toBigDecimal(double value) {
    return value == 0.0 ? null : BigDecimal.valueOf(value);
  }

  private UUID toUuid(String value) {
    if (value == null || value.isEmpty()) {
      return null;
    }
    try {
      return UUID.fromString(value);
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
}








