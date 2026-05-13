# OutcomeCodeClaimValidator

Purpose

- Validates the `outcomeCode` on claims according to the area of law.

How it works

- If `outcomeCode` or `areaOfLaw` are null the validator returns immediately.
- Uses area-specific regex patterns:
  - LEGAL_HELP: `^[A-Za-z0-9-]{2}$` (exactly 2 characters letters/numbers/hyphen)
  - CRIME_LOWER: case-insensitive complex pattern allowing `CPxx`, `CNxx`, `PLxx` ranges
  - MEDIATION: `(?i)^(A|B|S|C|P)?$` (optional single-letter codes)
- If the supplied `outcomeCode` does not match the pattern a `INVALID_OUTCOME_CODE` issue is added with a technical message and a user-friendly display message tailored per area.

Priority & scope

- Priority: 100.
- Applies to all claim scopes.

Notes

- The CRIME_LOWER pattern is intentionally permissive to allow blank values or a set of specific tokens; review business rules before changing it.
- The validator produces both a technical message (useful in logs) and a display message for end-users.
