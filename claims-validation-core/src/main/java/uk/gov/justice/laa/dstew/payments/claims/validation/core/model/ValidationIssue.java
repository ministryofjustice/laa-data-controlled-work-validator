package uk.gov.justice.laa.dstew.payments.claims.validation.core.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Represents a single validation issue found during internal validation.
 *
 * <p>
 * This class is for internal use only and is not intended for API serialization.
 * It contains a unique code, a human-readable message, the path to the field (if applicable),
 * and the severity of the issue.
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ValidationIssue implements Serializable {

  /**
   * Serial version UID for serialization compatibility.
   */
  @Serial
  private static final long serialVersionUID = 1L;

  /**
   * Unique code identifying the validation issue.
   */
  private String code;

  /**
   * Human-readable description of the issue.
   */
  private String message;

  /**
   * Path to the field causing the issue (if applicable).
   */
  @Builder.Default
  private List<ValidationIssuePathInner> path = new ArrayList<>();

  /**
   * Severity of the validation issue.
   */
  private ValidationSeverity severity;

  /**
   * Technical message for internal debugging or logging (not for end users).
   */
  private String technicalMessage;

  /**
   * Adds a path item to the list of field paths causing the issue.
   *
   * @param pathItem the path item to add
   * @return this ValidationIssue instance
   */
  public ValidationIssue addPathItem(ValidationIssuePathInner pathItem) {
    if (this.path == null) {
      this.path = new ArrayList<>();
    }
    this.path.add(pathItem);
    return this;
  }
}
