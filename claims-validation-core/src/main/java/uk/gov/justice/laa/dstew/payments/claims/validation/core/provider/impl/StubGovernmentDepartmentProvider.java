package uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.impl;

import java.util.List;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.GovernmentDepartmentProvider;

/**
 * Temporary {@link GovernmentDepartmentProvider} returning a fixed list of placeholder names.
 *
 * <p>TODO DSTEW-2358: replace with an HTTP-backed provider once the Data Claims API exposes the
 * Government Department endpoint.
 */
public class StubGovernmentDepartmentProvider implements GovernmentDepartmentProvider {

  private static final List<String> GOVERNMENT_DEPARTMENTS =
      List.of(
          "Ministry of Justice",
          "Attorney General’s Office",
          "Home Office",
          "Ministry of Defence",
          "Department of Health and Social Care",
          "Department for Work and Pensions",
          "Department for Education",
          "Department for Transport");

  @Override
  public List<String> getActiveGovernmentDepartments() {
    return GOVERNMENT_DEPARTMENTS;
  }
}
