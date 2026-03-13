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
 * Category code assigned to a legal aid matter start. 
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T15:47:02.322440Z[Europe/London]", comments = "Generator version: 7.14.0")
public enum CategoryCode implements Serializable {
  
  AAP("AAP"),
  
  COM("COM"),
  
  CON("CON"),
  
  DEB("DEB"),
  
  EDU("EDU"),
  
  EMP("EMP"),
  
  ELA("ELA"),
  
  HOU("HOU"),
  
  IMMAS("IMMAS"),
  
  IMMOT("IMMOT"),
  
  MAT("MAT"),
  
  MED("MED"),
  
  MHE("MHE"),
  
  MSC("MSC"),
  
  PI("PI"),
  
  PUB("PUB"),
  
  WB("WB"),
  
  DISC("DISC");

  private final String value;

  CategoryCode(String value) {
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
  public static CategoryCode fromValue(String value) {
    for (CategoryCode b : CategoryCode.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

