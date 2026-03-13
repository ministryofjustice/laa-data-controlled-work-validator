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
 * Gets or Sets assessment_type
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T21:51:30.932283Z[Europe/London]", comments = "Generator version: 7.14.0")
public enum AssessmentType implements Serializable {
  
  ESCAPE_CASE_ASSESSMENT("ESCAPE_CASE_ASSESSMENT"),
  
  VOID("VOID");

  private final String value;

  AssessmentType(String value) {
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
  public static AssessmentType fromValue(String value) {
    for (AssessmentType b : AssessmentType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

