# InquestClaimValidator

Purpose

- Validates `Claim.inquestDetail` (`ClaimInquestDetail`) using the fee-scheme platform's `isInquest` flag, which `ClaimValidation` sets on `ClaimValidationContext.isInquest`.

How it works

- If `isInquest` is null (fee details unresolved) or `false` the validator skips.
- `isInquest = true` and `isClientMeansTested` null: `INQUEST_CLIENT_MEANS_TESTED_REQUIRED` only.
- `isInquest = true` and `isClientMeansTested = true`: no further checks.
- `isInquest = true` and `isClientMeansTested = false`:
  - Deceased forename, surname and date of death are mandatory (`INQUEST_DECEASED_*_REQUIRED`, `INQUEST_DATE_OF_DEATH_REQUIRED`).
  - Length and character rules for the deceased names and coroner's reference are in `schemas/inquest-detail.schema.json` and report `SCHEMA_VALIDATION_ERROR`.
  - Date of death must be a valid `yyyy-MM-dd` date (strict, so `2026-02-31` fails) between 01/01/1900 and the case start date. The range check is skipped if the case start date is missing or invalid.
  - At least one and at most 10 populated `interestedDepartments`. Each must match a name from `GovernmentDepartmentProvider.getActiveGovernmentDepartments()`, ignoring case, extra whitespace and `’` vs `'`. Errors report the original slot (`GOV_DEPT_n`).
- Null, empty and whitespace-only values count as absent.

Priority & scope

- Priority: 100.
- Validator code: `CLAIM_INQUEST_VALIDATOR`.

Notes

- The default `StubGovernmentDepartmentProvider` returns placeholder names until the Data Claims API endpoint is added (DSTEW-2358). Register your own `GovernmentDepartmentProvider` bean (e.g. repository-backed) to replace it.
- `isClientMeansTested` is a `Boolean`. Parsing `INQ_CLIENT_MEANS_TESTED` (true/false, case-insensitive) and rejecting other values is the ingesting service's job.
- Open item: whether the coroner's reference may contain internal spaces (currently rejected).
