# UniqueFileNumberClaimValidator

Purpose

- Validates the Unique File Number (UFN) format and ensures the date encoded in the UFN is a valid past date.

How it works

- Expects UFN in format `DDMMYY/NNN` (regex `\d{6}/\d{3}`).
- Splits the UFN and parses the first 6 digits into day, month, two-digit year. Years are converted using a simple pivot rule: if two-digit year > 50 then 1900+year, else 2000+year. (Note: there is a TODO in code about the long-term robustness of this rule.)
- Checks that the parsed date is not in the future by comparing to `DateUtils.now()`.

Priority & scope

- Priority: 100.
- Applies to all claim scopes.

Validation outcomes

- Adds `INVALID_DATE_IN_UNIQUE_FILE_NUMBER` if the UFN is the wrong format or the encoded date is invalid or in the future.

Notes

- The two-digit year pivot is implementation-specific and will require attention in future decades; consider using a configurable century inference or storing full year information if possible.

Example

- `010120/001` -> parsed as 2020-01-01 (valid if current date is after 2020-01-01).
- `310299/001` -> may throw DateTimeException and be marked invalid.
