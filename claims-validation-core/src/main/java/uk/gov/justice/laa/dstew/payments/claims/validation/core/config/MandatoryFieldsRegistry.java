package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.springframework.stereotype.Component;
import uk.gov.justice.laa.dstew.payments.claims.model.AreaOfLaw;

/** Registry of mandatory fields by area of law. */
@Component
@Getter
public class MandatoryFieldsRegistry {

  private final List<String> legalHelpMandatoryFields =
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

  private final List<String> crimeLowerMandatoryFields =
      List.of(
          "caseConcludedDate",
          "stageReachedCode",
          "netProfitCostsAmount",
          "disbursementsVatAmount");

  private final List<String> mediationMandatoryFields =
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

  private final Map<AreaOfLaw, List<String>> mandatoryFieldsByAreaOfLaw =
      Map.of(
          AreaOfLaw.LEGAL_HELP, legalHelpMandatoryFields,
          AreaOfLaw.CRIME_LOWER, crimeLowerMandatoryFields,
          AreaOfLaw.MEDIATION, mediationMandatoryFields);
}
