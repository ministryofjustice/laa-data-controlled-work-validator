| JSON Schema Field                  | Proposed Snake Case              | OpenAPI Field (if present)         | JSON Schema Type | OpenAPI Type (if present) |
|------------------------------------|----------------------------------|------------------------------------|------------------|--------------------------|
| areaOfLaw                         | area_of_law                      | area_of_law                        | string           | string (enum)            |
| officeAccountNumber                | office_account_number             | office_account_number               | string           | string                   |
| id                                 | id                               | id                                 | string           | string (uuid)            |
| status                             | status                           | status                             | string           | string (enum)            |
| submissionId                       | submission_id                    | submission_id                      | string           | string (uuid)            |
| lineNumber                         | line_number                      | line_number                        | integer          | integer                  |
| scheduleReference                  | schedule_reference               | schedule_reference                 | string           | string                   |
| submissionPeriod                   | submission_period                | submission_period                  | string           | string                   |
| caseReferenceNumber                | case_reference_number            | case_reference_number              | string           | string                   |
| uniqueFileNumber                   | unique_file_number               | unique_file_number                 | string           | string                   |
| caseStartDate                      | case_start_date                  | case_start_date                    | string           | string                   |
| caseConcludedDate                  | case_concluded_date              | case_concluded_date                | string           | string                   |
| caseId                             | case_id                          | case_id                            | string           | string                   |
| uniqueCaseId                       | unique_case_id                   | unique_case_id                     | string           | string                   |
| caseStageCode                      | case_stage_code                  | case_stage_code                    | string           | string                   |
| matterTypeCode                     | matter_type_code                 | matter_type_code                   | string           | string                   |
| crimeMatterTypeCode                | crime_matter_type_code           | crime_matter_type_code             | string           | string                   |
| feeSchemeCode                      | fee_scheme_code                  | fee_scheme_code                    | string           | string                   |
| feeCode                            | fee_code                         | fee_code                           | string           | string                   |
| procurementAreaCode                | procurement_area_code            | procurement_area_code              | string           | string                   |
| accessPointCode                    | access_point_code                | access_point_code                  | string           | string                   |
| deliveryLocation                   | delivery_location                | delivery_location                  | string           | string                   |
| representationOrderDate            | representation_order_date        | representation_order_date          | string           | string                   |
| suspectsDefendantsCount            | suspects_defendants_count        | suspects_defendants_count          | integer          | integer                  |
| policeStationCourtAttendancesCount | police_station_court_attendances_count | police_station_court_attendances_count | integer          | integer                  |
| policeStationCourtPrisonId         | police_station_court_prison_id   | police_station_court_prison_id     | string           | string                   |
| dsccNumber                         | dscc_number                      | dscc_number                        | string           | string                   |
| maatId                             | maat_id                          | maat_id                            | string           | string                   |
| prisonLawPriorApprovalNumber       | prison_law_prior_approval_number | prison_law_prior_approval_number   | string           | string                   |
| isDutySolicitor                    | is_duty_solicitor                | is_duty_solicitor                  | boolean          | boolean                  |
| isYouthCourt                       | is_youth_court                   | is_youth_court                     | boolean          | boolean                  |
| schemeId                           | scheme_id                        | scheme_id                          | string           | string                   |
| mediationSessionsCount             | mediation_sessions_count         | mediation_sessions_count           | integer          | integer                  |
| mediationTimeMinutes               | mediation_time_minutes           | mediation_time_minutes             | integer          | integer                  |
| outreachLocation                   | outreach_location                | outreach_location                  | string           | string                   |
| referralSource                     | referral_source                  | referral_source                    | string           | string                   |
| totalWarnings                      | total_warnings                   |                                    | number           |                          |
| clientForename                     | client_forename                  | client_forename                    | string           | string                   |
| clientSurname                      | client_surname                   | client_surname                     | string           | string                   |
| clientDateOfBirth                  | client_date_of_birth             | client_date_of_birth               | string           | string                   |
| uniqueClientNumber                 | unique_client_number             | unique_client_number               | string           | string                   |
| clientPostcode                     | client_postcode                  | client_postcode                    | string           | string                   |
| genderCode                         | gender_code                      | gender_code                        | string           | string                   |
| ethnicityCode                      | ethnicity_code                   | ethnicity_code                     | string           | string                   |
| disabilityCode                     | disability_code                  | disability_code                    | string           | string                   |
| isLegallyAided                     | is_legally_aided                 | is_legally_aided                   | boolean          | boolean                  |
| clientTypeCode                     | client_type_code                 | client_type_code                   | string           | string                   |
| homeOfficeClientNumber             | home_office_client_number        | home_office_client_number          | string           | string                   |
| claReferenceNumber                 | cla_reference_number             | cla_reference_number               | string           | string                   |
| claExemptionCode                   | cla_exemption_code               | cla_exemption_code                 | string           | string                   |
| client2Forename                    | client2_forename                 | client2_forename                   | string           | string                   |
| client2Surname                     | client2_surname                  | client2_surname                    | string           | string                   |
| client2DateOfBirth                 | client2_date_of_birth            | client2_date_of_birth              | string           | string                   |
| client2Ucn                         | client2_ucn                      | client2_ucn                        | string           | string                   |
| client2Postcode                    | client2_postcode                 | client2_postcode                   | string           | string                   |
| client2GenderCode                  | client2_gender_code              | client2_gender_code                | string           | string                   |
| client2EthnicityCode               | client2_ethnicity_code           | client2_ethnicity_code             | string           | string                   |
| client2DisabilityCode              | client2_disability_code          | client2_disability_code            | string           | string                   |
| client2IsLegallyAided              | client2_is_legally_aided         | client2_is_legally_aided           | boolean          | boolean                  |
| caseId                             | case_id                          | case_id                            | string           | string                   |
| uniqueCaseId                       | unique_case_id                   | unique_case_id                     | string           | string                   |
| caseStageCode                      | case_stage_code                  | case_stage_code                    | string           | string                   |
| stageReachedCode                   | stage_reached_code               | stage_reached_code                 | string           | string                   |
| standardFeeCategoryCode            | standard_fee_category_code       | standard_fee_category_code         | string           | string                   |
| outcomeCode                        | outcome_code                     | outcome_code                       | string           | string                   |
| designatedAccreditedRepresentativeCode | designated_accredited_representative_code | designated_accredited_representative_code | string           | string                   |
| isPostalApplicationAccepted        | is_postal_application_accepted   | is_postal_application_accepted     | boolean          | boolean                  |
| isClient2PostalApplicationAccepted | is_client2_postal_application_accepted | is_client2_postal_application_accepted | boolean          | boolean                  |
| mentalHealthTribunalReference      | mental_health_tribunal_reference | mental_health_tribunal_reference   | string           | string                   |
| isNrmAdvice                        | is_nrm_advice                    | is_nrm_advice                      | boolean          | boolean                  |
| followOnWork                       | follow_on_work                   | follow_on_work                     | string           | string                   |
| transferDate                       | transfer_date                    | transfer_date                      | string           | string                   |
| exemptionCriteriaSatisfied         | exemption_criteria_satisfied     | exemption_criteria_satisfied       | string           | string                   |
| exceptionalCaseFundingReference    | exceptional_case_funding_reference | exceptional_case_funding_reference | string           | string                   |
| isLegacyCase                       | is_legacy_case                   | is_legacy_case                     | boolean          | boolean                  |
| adviceTime                         | advice_time                      | advice_time                        | integer          | integer                  |
| travelTime                         | travel_time                      | travel_time                        | integer          | integer                  |
| waitingTime                        | waiting_time                     | waiting_time                       | integer          | integer                  |
| netProfitCostsAmount               | net_profit_costs_amount          | net_profit_costs_amount            | number           | number                   |
| netDisbursementAmount              | net_disbursement_amount          | net_disbursement_amount            | number           | number                   |
| netCounselCostsAmount              | net_counsel_costs_amount         | net_counsel_costs_amount           | number           | number                   |
| disbursementsVatAmount             | disbursements_vat_amount         | disbursements_vat_amount           | number           | number                   |
| travelWaitingCostsAmount           | travel_waiting_costs_amount      | travel_waiting_costs_amount        | number           | number                   |
| netWaitingCostsAmount              | net_waiting_costs_amount         | net_waiting_costs_amount           | number           | number                   |
| isVatApplicable                    | is_vat_applicable                | is_vat_applicable                  | boolean          | boolean                  |
| isToleranceApplicable              | is_tolerance_applicable          | is_tolerance_applicable            | boolean          | boolean                  |
| prior_authority_reference          | prior_authority_reference        | prior_authority_reference          | string           | string                   |
| isLondonRate                       | is_london_rate                   | is_london_rate                     | boolean          | boolean                  |
| adjournedHearingFeeAmount          | adjourned_hearing_fee_amount     | adjourned_hearing_fee_amount       | integer          | integer                  |
| isAdditionalTravelPayment          | is_additional_travel_payment     | is_additional_travel_payment       | boolean          | boolean                  |
| costsDamagesRecoveredAmount        | costs_damages_recovered_amount   | costs_damages_recovered_amount     | number           | number                   |
| meetings_attended_code             | meetings_attended_code           | meetings_attended_code             | string           | string                   |
| detentionTravelWaitingCostsAmount  | detention_travel_waiting_costs_amount | detention_travel_waiting_costs_amount | number           | number                   |
| jrFormFillingAmount                | jr_form_filling_amount           | jr_form_filling_amount             | number           | number                   |
| isEligibleClient                   | is_eligible_client               | is_eligible_client                 | boolean          | boolean                  |
| court_location_code                | court_location_code              | court_location_code                | string           | string                   |
| advice_type_code                   | advice_type_code                 | advice_type_code                   | string           | string                   |
| medicalReportsCount                | medical_reports_count            | medical_reports_count              | integer          | integer                  |
| isIrcSurgery                       | is_irc_surgery                   | is_irc_surgery                     | boolean          | boolean                  |
| surgery_date                       | surgery_date                     | surgery_date                       | string           | string                   |
| surgeryClientsCount                | surgery_clients_count            | surgery_clients_count              | integer          | integer                  |
| surgeryMattersCount                | surgery_matters_count            | surgery_matters_count              | integer          | integer                  |
| cmrhOralCount                      | cmrh_oral_count                  | cmrh_oral_count                    | integer          | integer                  |
| cmrhTelephoneCount                 | cmrh_telephone_count             | cmrh_telephone_count               | integer          | integer                  |
| ait_hearing_centre_code            | ait_hearing_centre_code          | ait_hearing_centre_code            | string           | string                   |
| isSubstantiveHearing               | is_substantive_hearing           | is_substantive_hearing             | boolean          | boolean                  |
| hoInterview                        | ho_interview                     | ho_interview                       | integer          | integer                  |
| local_authority_number             | local_authority_number           | local_authority_number             | string           | string                   |
| created_by_user_id                 | created_by_user_id               | created_by_user_id                 | string           | string                   |
|                                  | is_amended                       | is_amended                         |                  | boolean                  |
|                                  | has_assessment                   | has_assessment                     |                  | boolean                  |
|                                  | version                          | version                            |                  | integer                  |

# Fields missing from OpenAPI but present in JSON schema:
- totalWarnings

# Fields missing from JSON schema but present in OpenAPI:
- is_amended
- has_assessment
- version

# All other fields are aligned in both name and type (after snake_case conversion).
