package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import java.util.List;
import lombok.Getter;
import org.springframework.stereotype.Component;

/** Registry of field exclusions for specific validation scenarios. */
@Component
@Getter
public class ExclusionsRegistry {

  /**
   * List of field names that should be excluded from mandatory field validation for
   * disbursement-only claims.
   */
  private final List<String> disbursementOnlyExclusions =
      List.of(
          "travelWaitingCostsAmount",
          "adviceTime",
          "travelTime",
          "waitingTime",
          "netCounselCostsAmount",
          "netProfitCostsAmount",
          "isVatApplicable");
}
