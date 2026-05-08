# SubmissionSchemaValidator

Purpose

- Validates a `SubmissionResponse` payload against the submission JSON Schema (`/schemas/submission-fields.schema.json`).

How it works

- Extends `AbstractSchemaValidator<SubmissionResponse>` to perform JSON schema validation.
- Configures the `ObjectMapper` to serialise to snake_case and omit nulls so field names match the schema.
- The schema enforces types, patterns, conditional required fields, enums for area-of-law and status, and custom error messages via `validationErrorMessages`.

Priority & scope

- Priority: 1 (runs before other submission validators).
- Applies to all submission scopes.

Validation outcome

- Writes schema validation issues directly to the `SubmissionValidationContext` (this validator uses the void/context mutation contract rather than returning a list).

Notes

- Currently the schema only defines `ALL` discriminator messages; the class returns `null` from `extractDiscriminator` so the `ALL` (fallback) messages are always used. If per-area-of-law messages are added to the schema update `extractDiscriminator` accordingly.
