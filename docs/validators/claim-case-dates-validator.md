# CaseDatesClaimValidator

Purpose

- Validates case-related dates present on a `Claim` (case start date, case concluded date, transfer date, representation order date).

How it works

- Validates that `caseStartDate` is in the past and after a hard lower bound (`1995-01-01`).
- `caseConcludedDate` is validated differently depending on `AreaOfLaw`:
  - For `CRIME_LOWER` the earliest allowed concluded date is `2016-04-01` (represented by `MIN_REP_ORDER_DATE`).
  - For other areas the earliest allowed concluded date is `2013-04-01`.
- Uses `DateUtils.checkDateNotInFutureAndWithinAllowedPeriod` to ensure `caseConcludedDate` is not in the future and within the allowed range relative to the submission period.
- If `transferDate` or `representationOrderDate` are present they are validated to be in the past and after `1995-01-01` / `2016-04-01` respectively.

Priority & scope

- Priority: 100.
- Applies to all claim scopes.

Validation outcome

- Adds field-specific validation issues for invalid or out-of-range dates.

Notes

- The specific lower bounds and special-casing for `CRIME_LOWER` are business rules codified in the validator (see constants at top of class).
