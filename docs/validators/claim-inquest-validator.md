# InquestClaimValidator

Purpose

- Validates `Claim.inquestDetail` (`ClaimInquestDetail`) using the fee-scheme platform's `isInquest` flag, which `ClaimValidation` sets on `ClaimValidationContext.isInquest`.

How it works

- If `isInquest` is null (fee details unresolved) or `false` the validator skips.
- If `inquestDetail` is null the validator skips: the caller did not supply Inquest data. The Data Claims API sends `null` while its Inquests feature (`inquests.enabled`) is off, and an empty (non-null) detail when the feature is on but no detail is stored, which is then validated. Note the Data Claims API amendment flow does not currently map Inquest data, so amendments are not Inquest-validated.
- `isInquest = true` and `isClientMeansTested` null: `INQUEST_CLIENT_MEANS_TESTED_REQUIRED` only.
- `isInquest = true` and `isClientMeansTested = true`: no further checks.
- `isInquest = true` and `isClientMeansTested = false`:
  - Deceased forename, surname and date of death are mandatory (`INQUEST_DECEASED_*_REQUIRED`, `INQUEST_DATE_OF_DEATH_REQUIRED`).
  - Length and character rules for the deceased names and coroner's reference are in `schemas/inquest-detail.schema.json` and report `SCHEMA_VALIDATION_ERROR`.
  - Date of death must be a valid `DD/MM/YYYY` date, the format the Data Claims API returns (strict, so `31/02/2026`, `1/5/2020` and ISO `2020-05-01` fail), between 01/01/1900 and the case start date (`yyyy-MM-dd`). The range check is skipped if the case start date is missing or invalid.
  - At least one and at most 10 populated `interestedDepartments`. Each must match a name from `GovernmentDepartmentProvider.getActiveGovernmentDepartments()`, ignoring case, extra whitespace and `’` vs `'`. Errors report the department's one-based position in the supplied list. The Data Claims API drops blank `GOV_DEPT_n` slots and renumbers the rest, so this is not necessarily the original `GOV_DEPT_n` number.
  - If the Government Departments cannot be retrieved, `TECHNICAL_ERROR_GOVERNMENT_DEPARTMENTS` is reported instead of the department checks.
- Null, empty and whitespace-only values count as absent.

Priority & scope

- Priority: 100.
- Validator code: `CLAIM_INQUEST_VALIDATOR`.

Notes

- The default `HttpGovernmentDepartmentProvider` fetches the list from the Data Claims API (`GET /api/v1/system/references/departments`) and caches it for 30 minutes (fixed TTL; failures and empty lists are not cached). It uses the `dataClaimsRetry` Resilience4j retry instance.
- It needs the `laa.dstew.payments.validator.data-claims-api.*` properties, but only when first used: its client is created lazily, so an application without them (e.g. the Data Claims API itself) still starts. In that case non-means-tested Inquest claims report `TECHNICAL_ERROR_GOVERNMENT_DEPARTMENTS` until the application registers its own `GovernmentDepartmentProvider` bean (e.g. repository-backed) or configures the URL.
- `isClientMeansTested` is a `Boolean`. Parsing `INQ_CLIENT_MEANS_TESTED` (true/false, case-insensitive) and rejecting other values is the ingesting service's job.
- Open item: whether the coroner's reference may contain internal spaces (currently rejected).
