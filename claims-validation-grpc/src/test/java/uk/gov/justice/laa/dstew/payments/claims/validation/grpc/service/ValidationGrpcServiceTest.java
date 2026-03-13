package uk.gov.justice.laa.dstew.payments.claims.validation.grpc.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.grpc.stub.StreamObserver;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.justice.laa.dstew.payments.claims.model.ClaimValidationRequest;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationResult;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationSeverity;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.service.ValidationService;
import uk.gov.justice.laa.dstew.payments.claims.validation.grpc.mapper.ClaimProtoMapper;
import uk.gov.justice.laa.dstew.payments.claims.validation.grpc.proto.ProtoClaim;
import uk.gov.justice.laa.dstew.payments.claims.validation.grpc.proto.ProtoClaimValidationRequest;
import uk.gov.justice.laa.dstew.payments.claims.validation.grpc.proto.ProtoClaimValidationResponse;

/** Unit tests for ValidationGrpcService. */
@ExtendWith(MockitoExtension.class)
class ValidationGrpcServiceTest {

  @Mock private ValidationService validationService;

  @Mock
  private StreamObserver<ProtoClaimValidationResponse> responseObserver;

  @Captor
  private ArgumentCaptor<ProtoClaimValidationResponse> responseCaptor;

  private ValidationGrpcService grpcService;
  private ClaimProtoMapper claimProtoMapper;

  @BeforeEach
  void setUp() {
    claimProtoMapper = new ClaimProtoMapper();
    grpcService = new ValidationGrpcService(validationService, claimProtoMapper);
  }

  @Test
  void validateClaim_returnsValidResponse_whenNoIssues() {
    // Given
    ProtoClaimValidationRequest request =
        ProtoClaimValidationRequest.newBuilder()
            .setClaim(
                ProtoClaim.newBuilder()
                    .setAreaOfLaw("LEGAL HELP")
                    .setOfficeAccountNumber("1A234B")
                    .build())
            .setScope("all")
            .build();

    ValidationResult domainResponse = new ValidationResult();
    domainResponse.setIsValid(true);
    domainResponse.setIssues(Collections.emptyList());

    when(validationService.validateClaim(any(ClaimValidationRequest.class)))
        .thenReturn(domainResponse);

    // When
    grpcService.validateClaim(request, responseObserver);

    // Then
    verify(responseObserver).onNext(responseCaptor.capture());
    verify(responseObserver).onCompleted();

    ProtoClaimValidationResponse response = responseCaptor.getValue();
    assertThat(response.getIsValid()).isTrue();
    assertThat(response.getIssuesList()).isEmpty();
  }

  @Test
  void validateClaim_returnsInvalidResponse_withIssues() {
    // Given
    ProtoClaimValidationRequest request =
        ProtoClaimValidationRequest.newBuilder()
            .setClaim(ProtoClaim.newBuilder().setAreaOfLaw("LEGAL HELP").build())
            .setScope("all")
            .build();

    ValidationIssue issue =
        new ValidationIssue(
            "MISSING_FIELD", "Office account number is required", ValidationSeverity.ERROR);

    ValidationResult domainResponse = new ValidationResult();
    domainResponse.setIsValid(false);
    domainResponse.setIssues(List.of(issue));

    when(validationService.validateClaim(any(ClaimValidationRequest.class)))
        .thenReturn(domainResponse);

    // When
    grpcService.validateClaim(request, responseObserver);

    // Then
    verify(responseObserver).onNext(responseCaptor.capture());
    verify(responseObserver).onCompleted();

    ProtoClaimValidationResponse response = responseCaptor.getValue();
    assertThat(response.getIsValid()).isFalse();
    assertThat(response.getIssuesList()).hasSize(1);
    assertThat(response.getIssues(0).getCode()).isEqualTo("MISSING_FIELD");
    assertThat(response.getIssues(0).getMessage()).isEqualTo("Office account number is required");
    assertThat(response.getIssues(0).getSeverity()).isEqualTo("ERROR");
  }

  @Test
  void validateClaim_returnsError_whenExceptionOccurs() {
    // Given
    ProtoClaimValidationRequest request =
        ProtoClaimValidationRequest.newBuilder()
            .setClaim(ProtoClaim.newBuilder().build())
            .build();

    when(validationService.validateClaim(any(ClaimValidationRequest.class)))
        .thenThrow(new RuntimeException("Unexpected error"));

    // When
    grpcService.validateClaim(request, responseObserver);

    // Then
    verify(responseObserver).onError(any(Throwable.class));
  }
}




