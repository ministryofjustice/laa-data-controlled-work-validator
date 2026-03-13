package uk.gov.justice.laa.dstew.payments.claims.validation.grpc.service;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimValidationRequest;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.service.ValidationService;
import uk.gov.justice.laa.dstew.payments.claims.validation.grpc.mapper.ClaimProtoMapper;
import uk.gov.justice.laa.dstew.payments.claims.validation.grpc.proto.ClaimValidationServiceGrpc;
import uk.gov.justice.laa.dstew.payments.claims.validation.grpc.proto.ProtoClaimValidationRequest;
import uk.gov.justice.laa.dstew.payments.claims.validation.grpc.proto.ProtoClaimValidationResponse;

/**
 * gRPC service implementation for claim validation.
 *
 * <p>This service delegates to the core ValidationService, converting between protobuf messages
 * and domain objects.
 */
@Slf4j
@GrpcService
@RequiredArgsConstructor
public class ValidationGrpcService
    extends ClaimValidationServiceGrpc.ClaimValidationServiceImplBase {

  private final ValidationService validationService;
  private final ClaimProtoMapper claimProtoMapper;

  /**
   * Validates a single claim via unary RPC.
   *
   * @param request the gRPC claim validation request
   * @param responseObserver the response observer to send the result
   */
  @Override
  public void validateClaim(
      ProtoClaimValidationRequest request,
      StreamObserver<ProtoClaimValidationResponse> responseObserver) {

    log.info("Received gRPC validateClaim request for scope: {}", request.getScope());

    try {
      // Convert proto request to domain model
      ClaimValidationRequest domainRequest = claimProtoMapper.toDomainRequest(request);

      // Perform validation using core service
      ValidationResult domainResponse = validationService.validateClaim(domainRequest);

      // Convert domain response to proto
      ProtoClaimValidationResponse protoResponse =
          claimProtoMapper.toProtoResponse(domainResponse);

      log.info(
          "gRPC validation completed. isValid: {}, issues: {}",
          protoResponse.getIsValid(),
          protoResponse.getIssuesCount());

      responseObserver.onNext(protoResponse);
      responseObserver.onCompleted();

    } catch (Exception e) {
      log.error("Error during gRPC claim validation", e);
      responseObserver.onError(
          io.grpc.Status.INTERNAL
              .withDescription("Validation failed: " + e.getMessage())
              .withCause(e)
              .asRuntimeException());
    }
  }

  /**
   * Validates multiple claims via bidirectional streaming.
   *
   * @param responseObserver the response observer to send results
   * @return a stream observer for incoming requests
   */
  @Override
  public StreamObserver<ProtoClaimValidationRequest> validateClaimStream(
      StreamObserver<ProtoClaimValidationResponse> responseObserver) {

    log.info("gRPC validateClaimStream started");

    return new StreamObserver<>() {
      @Override
      public void onNext(ProtoClaimValidationRequest request) {
        try {
          log.debug("Processing streamed claim validation request");

          // Convert and validate
          ClaimValidationRequest domainRequest = claimProtoMapper.toDomainRequest(request);
          ValidationResult domainResponse = validationService.validateClaim(domainRequest);

          // Send response
          ProtoClaimValidationResponse protoResponse =
              claimProtoMapper.toProtoResponse(domainResponse);
          responseObserver.onNext(protoResponse);

        } catch (Exception e) {
          log.error("Error processing streamed claim", e);
          responseObserver.onError(
              io.grpc.Status.INTERNAL
                  .withDescription("Stream validation failed: " + e.getMessage())
                  .withCause(e)
                  .asRuntimeException());
        }
      }

      @Override
      public void onError(Throwable t) {
        log.error("Error in claim validation stream", t);
      }

      @Override
      public void onCompleted() {
        log.info("gRPC validateClaimStream completed");
        responseObserver.onCompleted();
      }
    };
  }
}









