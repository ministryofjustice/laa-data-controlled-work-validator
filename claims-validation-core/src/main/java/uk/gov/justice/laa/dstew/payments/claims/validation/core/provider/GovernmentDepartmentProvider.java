package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider;

import java.util.List;

/**
 * Transport-agnostic abstraction for fetching the Government Department reference data
 * ({@code claims.government_department_ref}) used by Inquest validation.
 */
public interface GovernmentDepartmentProvider {

  /**
   * Returns the display labels of the active Government Departments.
   *
   * @return the active Department names; never {@code null}
   */
  List<String> getActiveGovernmentDepartments();
}
