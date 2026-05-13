# ClientDateOfBirthClaimValidator

Purpose

- Validates client(s) date of birth fields on the `Claim` model.

How it works

- Validates client 1 and client 2 DOB (if present) using `DateUtils.parseDate` and helper methods.
- Checks that the date parses successfully and that it is within the allowed DOB constraints (not in the future and after a minimum year, 1900 in business logic).
- Adds a specific validation issue for client 1 and client 2 when invalid.

Priority & scope

- Priority: 100.
- Applies to all claim scopes.

Validation outcome

- Adds `INVALID_CLIENT_DATE_OF_BIRTH` or `INVALID_CLIENT_2_DATE_OF_BIRTH` with the offending value when parsing fails or DOB is not in allowed range.

Notes

- Date parsing and range checks are delegated to `DateUtils` which centralises date handling and formatting used by multiple validators.
