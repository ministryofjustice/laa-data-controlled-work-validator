package uk.gov.justice.laa.dstew.payments.claims.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
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
 * Severity level of the validation issue
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-10T12:17:20.086625Z[Europe/London]", comments = "Generator version: 7.18.0")
public enum ValidationSeverity implements Serializable {
  
  ERROR("ERROR"),
  
  WARNING("WARNING"),
  
  INFO("INFO");

  private final String value;

  ValidationSeverity(String value) {
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
  public static ValidationSeverity fromValue(String value) {
    for (ValidationSeverity b : ValidationSeverity.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

