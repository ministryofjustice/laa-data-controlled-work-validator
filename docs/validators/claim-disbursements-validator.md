# DisbursementsClaimValidator

Purpose

- Validates disbursement-related numeric limits such as VAT amounts.

How it works

- Reads the claim's `disbursementsVatAmount` (a BigDecimal).
- Determines the maximum allowed VAT amount by area of law:
  - LEGAL_HELP default: 99,999.99
  - CRIME_LOWER: 999,999.99
  - MEDIATION: 999,999,999.99
- Compares the supplied VAT amount to the maximum and adds `INVALID_DISBURSEMENT_VAT_AMOUNT` when it exceeds the allowed value.

Priority & scope

- Priority: 100.
- Applies to `disbursement` scope and `all`.

Validation outcome

- Adds an `INVALID_DISBURSEMENT_VAT_AMOUNT` issue when the amount is too large.

Notes

- The maximum amounts are hard-coded as constants in the validator. If business rules change these values should be moved into configuration for easier updates.
