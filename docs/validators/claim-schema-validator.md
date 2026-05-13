# ClaimSchemaValidator

Purpose

- Validates a Claim JSON payload against the claim JSON Schema (`/schemas/claim-fields.schema.json`).

How it works

- This validator extends `AbstractSchemaValidator<Claim>` which performs generic JSON schema validation using a configured Jackson `ObjectMapper`.
- It serialises the Java `Claim` model to snake_case JSON and omits nulls before validating against the schema. This ensures field names such as `feeCode` become `fee_code` to match the schema.
- The schema defines field types, patterns, enum constraints, and custom per-field error messages (with area-of-law discriminator support).

Priority

- Priority: 1 (runs before other claim validators). Schema validation short-circuits many format/type errors so later validators can assume syntactic correctness.

What to look for in results

- Validation issues returned by this validator will include schema-derived error codes and human-readable messages configured in `validationErrorMessages` inside the schema.

Notes for consumers

- If you need to add schema-based validation for another domain object, follow the same pattern: extend `AbstractSchemaValidator<T>` and configure the object mapper and schema path.

Examples

- Example issue: a fee code that does not match the schema pattern will produce a schema validation issue pointing at `fee_code` with a custom message if provided in the schema.
