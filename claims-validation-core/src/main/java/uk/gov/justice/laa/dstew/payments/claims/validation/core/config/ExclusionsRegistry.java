package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import java.util.List;

/** Compile-time constants defining field exclusions for specific validation scenarios. */
public final class ExclusionsRegistry {

  /**
   * Field names excluded from mandatory field validation for disbursement-only claims.
   */
  public static final List<String> DISBURSEMENT_ONLY_EXCLUSIONS =
      List.of(
          "travelWaitingCostsAmount",
          "adviceTime",
          "travelTime",
          "waitingTime",
          "netCounselCostsAmount",
          "netProfitCostsAmount",
          "isVatApplicable");

  private ExclusionsRegistry() {}
}