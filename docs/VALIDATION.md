# Claim Validation Rules

This directory contains the core validation rules for LAA Data Claims. Each rule is implemented as a Java class and is responsible for checking a specific aspect of a claim. Below is an overview table of all rules, with links to detailed descriptions of each.

## Overview Table

| Rule Class | Purpose | Details |
|------------|---------|---------|
| ClaimSchemaValidator | Validates claim against JSON schema (types, patterns, constraints) | [Details](#claimschemavalidator) |
| MandatoryFieldClaimValidator | Ensures all mandatory fields for the area of law are present | [Details](#mandatoryfieldclaimvalidator) |
| DisbursementClaimStartDateValidator | Checks disbursement claims are submitted after required period from case start | [Details](#disbursementclaimstartdatevalidator) |
| UniqueFileNumberClaimValidator | Validates Unique File Number (UFN) format and date | [Details](#uniquefilenumberclaimvalidator) |
| CaseDatesClaimValidator | Validates case-related dates (start, concluded, transfer, rep order) | [Details](#casedatesclaimvalidator) |
| ClientDateOfBirthClaimValidator | Validates client date(s) of birth are in the past and after 1900 | [Details](#clientdateofbirthclaimvalidator) |
| DisbursementsClaimValidator | Validates disbursement VAT amounts are within limits | [Details](#disbursementsclaimvalidator) |
| DuplicateClaimValidator | Checks for duplicate claims using area-of-law strategies | [Details](#duplicateclaimvalidator) |
| EffectiveCategoryOfLawClaimValidator | Validates claim's effective category of law via external APIs | [Details](#effectivecategoryoflawclaimvalidator) |
| MatterTypeClaimValidator | Validates matter type code using regex for area of law | [Details](#mattertypeclaimvalidator) |
| OutcomeCodeClaimValidator | Validates outcome code using regex for area of law | [Details](#outcomecodeclaimvalidator) |
| ScheduleReferenceClaimValidator | Validates schedule reference format | [Details](#schedulereferenceclaimvalidator) |
| StageReachedClaimValidator | Validates stage reached code using regex for area of law | [Details](#stagereachedclaimvalidator) |

---

## ClaimSchemaValidator

**Purpose:**
- Validates the claim object against a JSON schema (`claim-fields.schema.json`).
- Checks field types, regex patterns, min/max constraints, string lengths, and custom error messages.

**Key Checks:**
- All fields conform to expected types and patterns.
- Required fields are present.
- Numeric and string constraints are enforced.
- Errors are reported with code `SCHEMA_VALIDATION_ERROR`.

---

## MandatoryFieldClaimValidator

**Purpose:**
- Ensures all mandatory fields for the claim's area of law are populated.

**Key Checks:**
- Uses a registry to determine required fields for each area of law.
- Skips fields excluded by configuration.
- Reports missing or invalid fields as validation issues.

---

## DisbursementClaimStartDateValidator

**Purpose:**
- Ensures disbursement claims are only submitted after a specific number of months from the case start date.

**Key Checks:**
- Only applies to claims identified as disbursements.
- Compares case start date to the last day of the allowed submission period.
- Reports errors if submitted too early.

---

## UniqueFileNumberClaimValidator

**Purpose:**
- Validates the Unique File Number (UFN) format and ensures the date is in the past.

**Key Checks:**
- UFN must match the pattern `DDMMYY/NNN`.
- The date portion must be a valid past date.
- Reports errors for invalid format or future dates.

---

## CaseDatesClaimValidator

**Purpose:**
- Validates case-related dates for logical consistency and allowed ranges.

**Key Checks:**
- Case start date must be in the past and after 1995.
- Case concluded date must be after a minimum allowed date and not in the future.
- Transfer and representation order dates are checked for validity.
- Reports errors for out-of-range or illogical dates.

---

## ClientDateOfBirthClaimValidator

**Purpose:**
- Validates that client date(s) of birth are in the past and after 1900.

**Key Checks:**
- Checks both client 1 and client 2 DOB fields.
- Ensures dates are not in the future and after 1900.
- Reports errors for invalid or missing dates.

---

## DisbursementsClaimValidator

**Purpose:**
- Validates that disbursement VAT amounts are within acceptable limits for the area of law.

**Key Checks:**
- Checks VAT amount is not null and does not exceed the maximum for the area of law.
- Reports errors for excessive VAT amounts.

---

## DuplicateClaimValidator

**Purpose:**
- Checks for duplicate claims using area-of-law specific strategies.

**Key Checks:**
- Delegates to strategy classes for each area of law.
- Compares claim fields and related claims for duplicates.
- Reports errors for detected duplicates.

---

## EffectiveCategoryOfLawClaimValidator

**Purpose:**
- Validates the claim's effective category of law using external APIs.

**Key Checks:**
- Calls Fee Scheme and Provider Details APIs to verify category.
- Checks contract and schedule details.
- Reports errors for invalid or missing categories.

---

## MatterTypeClaimValidator

**Purpose:**
- Validates the matter type code using regex patterns for the area of law.

**Key Checks:**
- Checks code format against area-of-law-specific regex.
- Reports errors for invalid codes.

---

## OutcomeCodeClaimValidator

**Purpose:**
- Validates the outcome code using regex patterns for the area of law.

**Key Checks:**
- Checks code format against area-of-law-specific regex.
- Reports errors for invalid codes.

---

## ScheduleReferenceClaimValidator

**Purpose:**
- Validates the schedule reference format.

**Key Checks:**
- Checks format against regex (alphanumeric, max 20 chars, allowed symbols).
- Reports errors for invalid references.

---

## StageReachedClaimValidator

**Purpose:**
- Validates the stage reached code using regex patterns for the area of law.

**Key Checks:**
- Checks code format against area-of-law-specific regex.
- Reports errors for invalid codes.

---

*For more details, see the JavaDoc in each rule class.*
