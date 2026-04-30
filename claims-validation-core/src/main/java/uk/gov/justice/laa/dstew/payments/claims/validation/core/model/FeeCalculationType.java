package uk.gov.justice.laa.dstew.payments.claims.validation.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;

/** Enum representing the type of fee calculation. */
public enum FeeCalculationType implements Serializable {
  HOURLY("HOURLY"),
  FIXED("FIXED"),
  DISB_ONLY("DISB_ONLY");

  private final String value;

  FeeCalculationType(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    return String.valueOf(this.value);
  }

  /**
   * Creates a FeeCalculationType from its string value.
   *
   * @param value the string value
   * @return the corresponding FeeCalculationType
   * @throws IllegalArgumentException if the value does not match any enum constant
   */
  @JsonCreator
  public static FeeCalculationType fromValue(String value) {
    for (FeeCalculationType b : values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }

    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}
