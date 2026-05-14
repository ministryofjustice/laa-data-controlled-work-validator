package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import java.util.List;
import java.util.Map;
import uk.gov.justice.laa.dstew.payments.claimsdata.model.AreaOfLaw;

/** Compile-time constants defining mandatory fields by area of law. */
public final class MandatoryFieldsRegistry {

  public static final List<String> LEGAL_HELP_MANDATORY_FIELDS =
      List.of(
          "uniqueFileNumber",
          "caseStartDate",
          "caseConcludedDate",
          "outcomeCode",
          "travelWaitingCostsAmount",
          "clientForename",
          "clientSurname",
          "clientDateOfBirth",
          "uniqueClientNumber",
          "clientPostcode",
          "genderCode",
          "ethnicityCode",
          "disabilityCode",
          "adviceTime",
          "travelTime",
          "waitingTime",
          "netCounselCostsAmount",
          "caseId",
          "caseReferenceNumber",
          "scheduleReference",
          "matterTypeCode",
          "netProfitCostsAmount",
          "isVatApplicable");

  public static final List<String> CRIME_LOWER_MANDATORY_FIELDS =
      List.of(
          "caseConcludedDate",
          "stageReachedCode",
          "netProfitCostsAmount",
          "disbursementsVatAmount");

  public static final List<String> MEDIATION_MANDATORY_FIELDS =
      List.of(
          "outreachLocation",
          "referralSource",
          "clientForename",
          "clientSurname",
          "clientDateOfBirth",
          "uniqueClientNumber",
          "clientPostcode",
          "genderCode",
          "ethnicityCode",
          "disabilityCode",
          "isLegallyAided",
          "caseId",
          "caseStartDate",
          "caseReferenceNumber",
          "scheduleReference",
          "matterTypeCode",
          "uniqueCaseId");

  public static final Map<AreaOfLaw, List<String>> MANDATORY_FIELDS_BY_AREA_OF_LAW =
      Map.of(
          AreaOfLaw.LEGAL_HELP, LEGAL_HELP_MANDATORY_FIELDS,
          AreaOfLaw.CRIME_LOWER, CRIME_LOWER_MANDATORY_FIELDS,
          AreaOfLaw.MEDIATION, MEDIATION_MANDATORY_FIELDS);

  private MandatoryFieldsRegistry() {}
}
