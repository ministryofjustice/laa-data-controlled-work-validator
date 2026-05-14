# StageReachedClaimValidator

Purpose

- Validates the `stageReachedCode` field on claims according to area-of-law-specific patterns.

How it works

- If the `stageReachedCode` is empty or null the validator skips (field is optional).
- For `LEGAL_HELP`, expects a 2-character alphanumeric code (`^[a-zA-Z0-9]{2}$`).
- For `CRIME_LOWER`, applies a stricter set of allowed values (complex regex containing valid tokens like INV[A-M], PRI[A-E], etc.).
- If the code does not match the expected pattern the validator adds a specific validation issue depending on area of law (`INVALID_STAGE_REACHED_LEGAL_HELP`, `INVALID_STAGE_REACHED_CRIME_LOWER`, or generic `INVALID_STAGE_REACHED`).

Priority & scope

- Priority: 100.
- Applies to all claim scopes.

Validation outcome

- Adds area-specific validation issues and includes a technical message describing the mismatched regex for debugging.

Notes

- The `CRIME_LOWER` regex encodes business-allowed stage codes; changes to allowed codes require updating the regex or replacing with a lookup-driven strategy.
