package uk.gov.justice.laa.dstew.payments.claims.validation.core.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the result of an internal validation process, including whether the validation passed
 * and a list of issues encountered.
 *
 * <p>
 * This class is for internal use only and is not intended for API serialization.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResult implements Serializable {

  /**
   * Serial version UID for serialization compatibility.
   */
  @Serial
  private static final long serialVersionUID = 1L;

  /**
   * Indicates whether the validation was successful.
   */
  private Boolean isValid;

  /**
   * List of validation issues found during the process.
   */
  @Builder.Default
  private List<ValidationIssue> issues = new ArrayList<>();

  /**
   * Adds a validation issue to the list of issues.
   *
   * @param issuesItem the validation issue to add
   * @return this ValidationResult instance
   */
  public ValidationResult addIssuesItem(ValidationIssue issuesItem) {
    if (this.issues == null) {
      this.issues = new ArrayList<>();
    }
    this.issues.add(issuesItem);
    return this;
  }
}
