# MatterTypeClaimValidator

Purpose

- Validates the `matterTypeCode` format according to `AreaOfLaw`.

How it works

- If `matterTypeCode` or `areaOfLaw` are null the validator skips.
- For `LEGAL_HELP`, expects a `^[a-zA-Z0-9]{1,4}[-:][a-zA-Z0-9]{1,4}$` pattern (two parts separated by `-` or `:` with 1–4 alphanumeric chars each).
- For `MEDIATION`, expects `^[A-Z]{4}[-:][A-Z]{4}$` (exactly 4 uppercase chars on each side).
- If the matter type does not match the regex the validator adds `INVALID_MATTER_TYPE_CODE` including a technical message and a user-friendly display message matching legacy expectations.

Priority & scope

- Priority: 100.
- Applies to all claim scopes.

Notes

- Display messages are tailored to area of law (e.g. "Each Matter Type Code 1 and 2 must be 4 characters" for LEGAL_HELP).
- If new areas-of-law require different formats add a mapping in `getRegexForAreaOfLaw`.
