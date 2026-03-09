package uk.gov.justice.laa.dstew.payments.claims;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Claims Validation microservice application.
 */
@SpringBootApplication
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
