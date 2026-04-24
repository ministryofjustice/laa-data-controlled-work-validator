package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.rules.duplicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("StrategyTypes constants")
class StrategyTypesTest {

  @Test
  @DisplayName("CRIME_LOWER contains expected value and is immutable")
  void crimeLowerContentsAndImmutable() {
    List<String> list = StrategyTypes.CRIME_LOWER;
    assertThat(list).isNotNull().hasSize(1).containsExactly("CRIME LOWER");

    assertThatThrownBy(() -> list.add("X"))
        .isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  @DisplayName("LEGAL_HELP contains expected value and is immutable")
  void legalHelpContentsAndImmutable() {
    List<String> list = StrategyTypes.LEGAL_HELP;
    assertThat(list).isNotNull().hasSize(1).containsExactly("LEGAL HELP");

    assertThatThrownBy(list::removeFirst)
        .isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  @DisplayName("MEDIATION contains expected value and is immutable")
  void mediationContentsAndImmutable() {
    List<String> list = StrategyTypes.MEDIATION;
    assertThat(list).isNotNull().hasSize(1).containsExactly("MEDIATION");

    assertThatThrownBy(list::clear)
        .isInstanceOf(UnsupportedOperationException.class);
  }
}
