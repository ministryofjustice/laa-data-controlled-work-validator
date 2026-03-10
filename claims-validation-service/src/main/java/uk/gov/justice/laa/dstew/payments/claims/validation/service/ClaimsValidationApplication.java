package uk.gov.justice.laa.dstew.payments.claims.validation.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/** Entry point for the Claims Validation microservice application. */
@SpringBootApplication
@ComponentScan(
    basePackages = {
      "uk.gov.justice.laa.dstew.payments.claims.validation.service",
      "uk.gov.justice.laa.dstew.payments.claims.validation.core"
    })
public class ClaimsValidationApplication {

  /**
   * The application main method.
   *
   * @param args the application arguments.
   */
  public static void main(String[] args) {
    SpringApplication.run(ClaimsValidationApplication.class, args);
  }
}
