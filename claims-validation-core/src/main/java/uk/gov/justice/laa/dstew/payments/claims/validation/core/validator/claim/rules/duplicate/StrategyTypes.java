package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules.duplicate;

import java.util.List;
import lombok.NoArgsConstructor;

/**
 * Strategy types for duplicate claim validation.
 *
 * @author Jamie Briggs
 */
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public final class StrategyTypes {

  public static final List<String> CRIME_LOWER = List.of("CRIME LOWER");
  public static final List<String> LEGAL_HELP = List.of("LEGAL HELP");
  public static final List<String> MEDIATION = List.of("MEDIATION");
}
