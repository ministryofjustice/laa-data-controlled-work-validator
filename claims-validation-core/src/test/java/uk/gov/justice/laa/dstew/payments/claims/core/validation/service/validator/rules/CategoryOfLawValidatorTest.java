package uk.gov.justice.laa.dstew.payments.claims.validation.service.validator.rules;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.justice.laa.dstew.payments.claims.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.FeeSchemeClient;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.client.FeeSchemeClient.FeeDetailsResponse;
import uk.gov.justice.laa.dstew.payments.claims.validation.service.validator.ValidationContext;

@ExtendWith(MockitoExtension.class)
class CategoryOfLawValidatorTest {

  @Mock private FeeSchemeClient mockFeeSchemeClient;

  @InjectMocks private CategoryOfLawValidator validator;

  @Test
  void validate_returnsNoErrors_whenFeeCodeValidAndProviderAuthorized() {
    Claim claim = new Claim();
    claim.setFeeCode("ABC123");
    claim.setOfficeAccountNumber("1A234B");

    ValidationContext context = ValidationContext.builder().build();

    FeeDetailsResponse feeDetails =
        new FeeDetailsResponse("ABC123", "STANDARD", "LEGAL_HELP", "Description", Map.of());

    when(mockFeeSchemeClient.getFeeDetails("ABC123")).thenReturn(Optional.of(feeDetails));
    when(mockFeeSchemeClient.isProviderAuthorizedForCategoryOfLaw("1A234B", "LEGAL_HELP"))
        .thenReturn(true);

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void validate_returnsError_whenFeeCodeNotFound() {
    Claim claim = new Claim();
    claim.setFeeCode("INVALID");

    ValidationContext context = ValidationContext.builder().build();

    when(mockFeeSchemeClient.getFeeDetails("INVALID")).thenReturn(Optional.empty());

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).getCode()).isEqualTo("INVALID_CATEGORY_OF_LAW_AND_FEE_CODE");
  }

  @Test
  void validate_returnsError_whenProviderNotAuthorized() {
    Claim claim = new Claim();
    claim.setFeeCode("ABC123");
    claim.setOfficeAccountNumber("1A234B");

    ValidationContext context = ValidationContext.builder().build();

    FeeDetailsResponse feeDetails =
        new FeeDetailsResponse("ABC123", "STANDARD", "CRIME", "Description", Map.of());

    when(mockFeeSchemeClient.getFeeDetails("ABC123")).thenReturn(Optional.of(feeDetails));
    when(mockFeeSchemeClient.isProviderAuthorizedForCategoryOfLaw("1A234B", "CRIME"))
        .thenReturn(false);

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).getCode())
        .isEqualTo("INVALID_CATEGORY_OF_LAW_NOT_AUTHORISED_FOR_PROVIDER");
  }

  @Test
  void validate_returnsNoErrors_whenNoFeeCode() {
    Claim claim = new Claim();
    ValidationContext context = ValidationContext.builder().build();

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).isEmpty();
  }

  @Test
  void validate_returnsTechnicalError_whenServiceFails() {
    Claim claim = new Claim();
    claim.setFeeCode("ABC123");

    ValidationContext context = ValidationContext.builder().build();

    when(mockFeeSchemeClient.getFeeDetails("ABC123"))
        .thenThrow(new FeeSchemeClient.FeeSchemeClientException("Service unavailable", null));

    List<ValidationIssue> issues = validator.validate(claim, context);

    assertThat(issues).hasSize(1);
    assertThat(issues.get(0).getCode()).isEqualTo("TECHNICAL_ERROR_FEE_CALCULATION_SERVICE");
  }

  @Test
  void appliesTo_returnsTrueForFeeScope() {
    assertThat(validator.appliesTo("fee")).isTrue();
    assertThat(validator.appliesTo("all")).isTrue();
    assertThat(validator.appliesTo(null)).isTrue();
  }

  @Test
  void appliesTo_returnsFalseForDisbursementScope() {
    assertThat(validator.appliesTo("disbursement")).isFalse();
  }

  @Test
  void getValidatorCode_returnsCategoryOfLaw() {
    assertThat(validator.getValidatorCode()).isEqualTo("CATEGORY_OF_LAW");
  }
}
