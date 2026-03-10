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
 * Area of law for the claim
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-10T13:28:50.707211Z[Europe/London]", comments = "Generator version: 7.18.0")
public enum AreaOfLaw implements Serializable {
  
  CRIME_LOWER("CRIME_LOWER"),
  
  LEGAL_HELP("LEGAL_HELP"),
  
  MEDIATION("MEDIATION");

  private final String value;

  AreaOfLaw(String value) {
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
  public static AreaOfLaw fromValue(String value) {
    for (AreaOfLaw b : AreaOfLaw.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

