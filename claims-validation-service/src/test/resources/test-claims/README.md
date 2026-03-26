# Test Claims JSON Files

This directory contains sample JSON files for testing the Claims Validation API.

## Running the Service

### Prerequisites
- Java 21+
- Gradle

### Start the service

```bash
# From the project root directory
./gradlew :claims-validation-service:bootRun
```

The service will start on `http://localhost:8081` (API) and `http://localhost:8181` (Actuator).

### Verify the service is running

```bash
curl http://localhost:8181/actuator/health
```

### Note on External Services

The validation service connects to external APIs (Data Claims API, Fee Scheme API, Provider Details API) for some validators. When running locally without these services:

- **Duplicate claim checking** will be skipped (logs a warning)
- **Category of law validation** will be skipped
- **All other validators** will work normally

This allows you to test most validation rules without needing the full environment.

## Files Overview

| File | Expected Result | Description |
|------|-----------------|-------------|
| `01-valid-legal-help-claim.json` | ✅ PASS | Complete valid Legal Help claim with all required fields |
| `02-invalid-unique-file-number-format.json` | ❌ FAIL | UFN format is invalid (not DDMMYY/NNN) |
| `03-invalid-unique-file-number-future-date.json` | ❌ FAIL | UFN date is in the future (010149 = 01/01/2049) |
| `04-invalid-case-start-date-in-future.json` | ❌ FAIL | Case start date is in the future (2030) |
| `05-invalid-client-dob-in-future.json` | ❌ FAIL | Client date of birth is in the future |
| `06-invalid-matter-type-code.json` | ❌ FAIL | Matter type code is not valid for Legal Help |
| `07-invalid-outcome-code.json` | ❌ FAIL | Outcome code "ZZ" is not in the valid set |
| `08-invalid-stage-reached-code.json` | ❌ FAIL | Stage reached code doesn't match Crime Lower pattern |
| `09-invalid-schedule-reference.json` | ❌ FAIL | Schedule reference contains invalid characters |
| `10-invalid-disbursement-vat-exceeds-limit.json` | ❌ FAIL | Disbursement VAT exceeds Legal Help limit (99999.99) |

## Usage

Send a POST request to `/v1/validation/claim` with the JSON content:

```bash
curl -X POST http://localhost:8081/v1/validation/claim \
  -H "Content-Type: application/json" \
  -d @01-valid-legal-help-claim.json
```

## Validation Rules Tested

1. **UniqueFileNumberClaimValidator** - Validates UFN format (DDMMYY/NNN) and date not in future
2. **CaseDatesClaimValidator** - Validates case dates are in past and within allowed ranges
3. **ClientDateOfBirthClaimValidator** - Validates DOB is in past and after 1900
4. **MatterTypeClaimValidator** - Validates matter type is valid for the area of law
5. **OutcomeCodeClaimValidator** - Validates outcome code is in the allowed set
6. **StageReachedClaimValidator** - Validates stage reached matches pattern for area of law
7. **ScheduleReferenceClaimValidator** - Validates schedule reference is alphanumeric (A-Z, 0-9)
8. **DisbursementsClaimValidator** - Validates VAT amounts don't exceed area of law limits



