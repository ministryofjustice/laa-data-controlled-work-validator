package uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.rules;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.Claim;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.model.ValidationIssue;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.provider.GovernmentDepartmentProvider;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.util.DateUtils;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationContext;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidationError;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.claim.ClaimValidatorCode;
import uk.gov.justice.laa.dstew.payments.claims.validation.core.validator.schema.AbstractSchemaValidator;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.ClaimInquestDetail;

/**
 * Validates the Inquest information supplied with a claim.
 *
 * <p>Applicability is driven by the fee-scheme platform's {@code isInquest} flag and the client
 * means-tested value:
 *
 * <ul>
 *   <li>{@code isInquest} unresolved or {@code false}: no checks.
 *   <li>{@code inquestDetail} null: no checks (caller did not supply Inquest data).
 *   <li>{@code isInquest = true}: the means-tested value is required. When {@code false}, the
 *       field rules are applied; when {@code true}, no further checks.
 * </ul>
 *
 * <p>Field length and character rules live in {@code /schemas/inquest-detail.schema.json} and
 * are only applied to non-means-tested Inquest claims. Null, empty and whitespace-only values
 * are treated as absent.
 */
@Slf4j
@RequiredArgsConstructor
public class InquestClaimValidator extends AbstractSchemaValidator<ClaimInquestDetail>
    implements ClaimValidator {

  private static final String SCHEMA_PATH = "/schemas/inquest-detail.schema.json";
  private static final String PATH_PREFIX = "inquest_detail.";
  private static final String DEPARTMENTS_PATH = PATH_PREFIX + "interested_departments";
  private static final int MAX_GOVERNMENT_DEPARTMENTS = 10;
  private static final LocalDate EARLIEST_DATE_OF_DEATH = LocalDate.of(1900, 1, 1);
  /** Data Claims API returns {@code deceased_date_of_death} as DD/MM/YYYY. */
  private static final DateTimeFormatter DATE_OF_DEATH_FORMATTER =
      DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

  private final GovernmentDepartmentProvider governmentDepartmentProvider;

  @Override
  public void validate(Claim claim, ClaimValidationContext context) {
    if (!Boolean.TRUE.equals(context.getIsInquest())) {
      return;
    }
    ClaimInquestDetail detail = claim.getInquestDetail();
    if (detail == null) {
      log.debug("Claim [{}] has an Inquest fee code but no Inquest detail was supplied; skipping",
          claim.getId());
      return;
    }

    if (detail.getIsClientMeansTested() == null) {
      context.addValidationIssue(
          ClaimValidationError.INQUEST_CLIENT_MEANS_TESTED_REQUIRED.toValidationIssue());
      return;
    }
    if (detail.getIsClientMeansTested()) {
      return;
    }

    requireText(detail.getDeceasedForename(),
        ClaimValidationError.INQUEST_DECEASED_FORENAME_REQUIRED, context);
    requireText(detail.getDeceasedSurname(),
        ClaimValidationError.INQUEST_DECEASED_SURNAME_REQUIRED, context);
    validateFieldFormats(detail, context);
    validateDateOfDeath(detail.getDeceasedDateOfDeath(), claim.getCaseStartDate(), context);
    validateGovernmentDepartments(detail.getInterestedDepartments(), context);
  }

  private static void requireText(
      String value, ClaimValidationError error, ClaimValidationContext context) {
    if (!StringUtils.hasText(value)) {
      context.addValidationIssue(error.toValidationIssue());
    }
  }

  private void validateFieldFormats(ClaimInquestDetail detail, ClaimValidationContext context) {
    for (ValidationIssue issue : validateSubject(detail)) {
      if (issue.getPath() != null) {
        issue.setPath(PATH_PREFIX + issue.getPath());
      }
      context.addValidationIssue(issue);
    }
  }

  private static void validateDateOfDeath(
      String value, String caseStartDateValue, ClaimValidationContext context) {
    if (!StringUtils.hasText(value)) {
      context.addValidationIssue(
          ClaimValidationError.INQUEST_DATE_OF_DEATH_REQUIRED.toValidationIssue());
      return;
    }
    LocalDate dateOfDeath = parseStrict(value, DATE_OF_DEATH_FORMATTER);
    if (dateOfDeath == null) {
      context.addValidationIssue(
          ClaimValidationError.INQUEST_DATE_OF_DEATH_INVALID.toValidationIssue());
      return;
    }

    LocalDate caseStartDate = parseStrict(caseStartDateValue, DateTimeFormatter.ISO_LOCAL_DATE);
    if (caseStartDate != null
        && !DateUtils.isDateWithinRange(dateOfDeath, EARLIEST_DATE_OF_DEATH, caseStartDate)) {
      context.addValidationIssue(
          ClaimValidationError.INQUEST_DATE_OF_DEATH_OUT_OF_RANGE.toValidationIssue());
    }
  }

  private void validateGovernmentDepartments(
      List<String> departments, ClaimValidationContext context) {
    List<Integer> populated = populatedSlots(departments);
    if (populated.isEmpty()) {
      context.addValidationIssue(
          ClaimValidationError.INQUEST_GOVERNMENT_DEPARTMENT_REQUIRED.toValidationIssue());
      return;
    }
    if (populated.size() > MAX_GOVERNMENT_DEPARTMENTS) {
      context.addValidationIssue(
          ClaimValidationError.INQUEST_GOVERNMENT_DEPARTMENT_TOO_MANY.toValidationIssue());
    }
    Set<String> activeDepartments;
    try {
      activeDepartments =
          governmentDepartmentProvider.getActiveGovernmentDepartments().stream()
              .map(InquestClaimValidator::normaliseDepartment)
              .collect(Collectors.toUnmodifiableSet());
    } catch (Exception ex) {
      log.error("Unable to retrieve Government Departments: {}", ex.getMessage(), ex);
      context.addValidationIssue(
          ClaimValidationError.TECHNICAL_ERROR_GOVERNMENT_DEPARTMENTS
              .toValidationIssueWithTechnicalMessage(ex.getMessage()));
      return;
    }
    for (int index : populated) {
      if (!activeDepartments.contains(normaliseDepartment(departments.get(index)))) {
        ValidationIssue issue =
            ClaimValidationError.INQUEST_GOVERNMENT_DEPARTMENT_UNKNOWN.toValidationIssue(index + 1);
        issue.setPath(DEPARTMENTS_PATH + "[" + index + "]");
        context.addValidationIssue(issue);
      }
    }
  }

  private static List<Integer> populatedSlots(List<String> departments) {
    List<Integer> populated = new ArrayList<>();
    if (departments != null) {
      for (int i = 0; i < departments.size(); i++) {
        if (StringUtils.hasText(departments.get(i))) {
          populated.add(i);
        }
      }
    }
    return populated;
  }

  /** Collapses whitespace, treats ’ as ' and ignores case, for exact full-name matching. */
  private static String normaliseDepartment(String name) {
    return name.replaceAll("(?U)\\s+", " ").strip().replace('’', '\'').toLowerCase(Locale.ROOT);
  }

  private static LocalDate parseStrict(String value, DateTimeFormatter formatter) {
    if (!StringUtils.hasText(value)) {
      return null;
    }
    try {
      return LocalDate.parse(value, formatter);
    } catch (DateTimeParseException e) {
      return null;
    }
  }

  @Override
  protected String getSchemaPath() {
    return SCHEMA_PATH;
  }

  @Override
  protected void configureObjectMapper(ObjectMapper mapper) {
    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
  }

  @Override
  protected String extractDiscriminator(ClaimInquestDetail detail) {
    return null;
  }

  @Override
  public int priority() {
    return 100;
  }

  @Override
  public ClaimValidatorCode getValidatorCode() {
    return ClaimValidatorCode.CLAIM_INQUEST_VALIDATOR;
  }
}
