package uk.gov.justice.laa.dstew.payments.claimsdata.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Processing status indicator tracking the lifecycle stage of the submission from receipt through to completion 
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T21:51:30.932283Z[Europe/London]", comments = "Generator version: 7.14.0")
public enum SubmissionStatus implements Serializable {
  
  CREATED("CREATED"),
  
  READY_FOR_VALIDATION("READY_FOR_VALIDATION"),
  
  VALIDATION_IN_PROGRESS("VALIDATION_IN_PROGRESS"),
  
  VALIDATION_SUCCEEDED("VALIDATION_SUCCEEDED"),
  
  VALIDATION_FAILED("VALIDATION_FAILED"),
  
  REPLACED("REPLACED");

  private final String value;

  SubmissionStatus(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static SubmissionStatus fromValue(String value) {
    for (SubmissionStatus b : SubmissionStatus.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

