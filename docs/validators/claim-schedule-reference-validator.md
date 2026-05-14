# ScheduleReferenceClaimValidator

Purpose

- Validates the `scheduleReference` field for `LEGAL_HELP` claims to ensure it contains only allowed characters and does not exceed the maximum length.

How it works

- For `AreaOfLaw.LEGAL_HELP` the validator checks `scheduleReference` against the regex `^[a-zA-Z0-9/.\-]{1,20}$` (letters, numbers, forward slash, period, hyphen; max length 20).
- If the value does not match the pattern the validator adds `INVALID_SCHEDULE_REFERENCE` with a technical and display message.

Priority & scope

- Priority: 100.
- Applies to all claim scopes but only validates for LEGAL_HELP area-of-law.

Notes

- The validator enforces legacy formatting rules. If the allowed character set or length changes, update the regex constant in the validator.
