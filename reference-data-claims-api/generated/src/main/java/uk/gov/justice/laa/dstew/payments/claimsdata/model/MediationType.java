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
 * The type of mediation for matter start. 
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-03-13T14:44:45.032142Z[Europe/London]", comments = "Generator version: 7.14.0")
public enum MediationType implements Serializable {
  
  MDCS_CHILD_ONLY_SOLE("MDCS Child Only Sole"),
  
  MDCC_CHILD_ONLY_CO("MDCC Child Only Co"),
  
  MDPS_PROPERTY_FINANCE_SOLE("MDPS Property & Finance Sole"),
  
  MDPC_PROPERTY_FINANCE_CO("MDPC Property & Finance Co"),
  
  MDAS_ALL_ISSUES_SOLE("MDAS All Issues Sole"),
  
  MDAC_ALL_ISSUES_CO("MDAC All Issues Co");

  private final String value;

  MediationType(String value) {
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
  public static MediationType fromValue(String value) {
    for (MediationType b : MediationType.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

