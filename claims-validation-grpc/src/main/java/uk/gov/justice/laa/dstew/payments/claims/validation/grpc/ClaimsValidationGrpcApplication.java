package uk.gov.justice.laa.dstew.payments.claims.validation.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Spring Boot application entry point for the gRPC Claims Validation Service.
 *
 * <p>This service exposes claim validation functionality via gRPC protocol, reusing the core
 * validation logic from claims-validation-core.
 */
@SpringBootApplication
@ComponentScan(
    basePackages = {
      "uk.gov.justice.laa.dstew.payments.claims.validation.grpc",
      "uk.gov.justice.laa.dstew.payments.claims.validation.core"
    })
public class ClaimsValidationGrpcApplication {

  public static void main(String[] args) {
    SpringApplication.run(ClaimsValidationGrpcApplication.class, args);
  }
}

