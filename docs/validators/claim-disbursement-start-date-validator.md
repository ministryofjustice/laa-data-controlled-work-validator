# DisbursementClaimStartDateValidator

Purpose

- Ensures disbursement claims are not claimed too early relative to the case start date.

How it works

- Determines if the claim is a disbursement claim via `FeeTypeUtils.isDisbursementClaim(feeType)` using the `feeCalculationType` provided in the `ClaimValidationContext`.
- Parses `submissionPeriod` from the `Claim` into a `YearMonth` and computes the submission period cutoff (end of the submission month).
- Parses `caseStartDate` and checks whether the case start date plus a configured number of months (from `DateUtils.MAXIMUM_MONTHS_DIFFERENCE`) is after the submission cutoff date. If so, the disbursement is considered too early.

Priority & scope

- Priority: 10 (runs early with other field/membership validators).
- Applies to scope: `disbursement` or `all`.

Validation outcome

- Adds a `DISBURSEMENT_TOO_EARLY` validation issue when the case start date indicates the claim was submitted earlier than allowed.

Configuration points

- The number of months used for the check is defined in `DateUtils.MAXIMUM_MONTHS_DIFFERENCE` in the core util package.

Example

- A disbursement claim with submissionPeriod `Jan-2023` and a caseStartDate of `2023-01-15` may be rejected if the configured required difference in months has not elapsed by the submission cutoff date.
