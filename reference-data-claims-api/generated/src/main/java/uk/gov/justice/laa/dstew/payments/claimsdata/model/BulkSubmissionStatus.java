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
 * Gets or Sets bulk_submission_status
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T14:44:45.032142Z[Europe/London]", comments = "Generator version: 7.14.0")
public enum BulkSubmissionStatus implements Serializable {
  
  READY_FOR_PARSING("READY_FOR_PARSING"),
  
  PARSING_COMPLETED("PARSING_COMPLETED"),
  
  PARSING_FAILED("PARSING_FAILED"),
  
  VALIDATION_FAILED("VALIDATION_FAILED"),
  
  REPLACED("REPLACED"),
  
  UNAUTHORISED("UNAUTHORISED"),
  
  VALIDATION_SUCCEEDED("VALIDATION_SUCCEEDED");

  private final String value;

  BulkSubmissionStatus(String value) {
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
  public static BulkSubmissionStatus fromValue(String value) {
    for (BulkSubmissionStatus b : BulkSubmissionStatus.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

