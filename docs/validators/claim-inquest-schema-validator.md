# InquestClaimSchemaValidator

Purpose

- Validates `Claim.inquestDetail` (`ClaimInquestDetail`) against the Inquest detail JSON Schema (`/schemas/inquest-detail.schema.json`).

How it works

- Extends `AbstractSchemaValidator<ClaimInquestDetail>`, serialising the detail to snake_case JSON before validating it. Issue paths are prefixed with `inquest_detail.` (e.g. `inquest_detail.deceased_forename`).
- Unlike `ClaimSchemaValidator`, the schema is only applied to non-means-tested Inquest claims: `isInquest = true`, `inquestDetail` supplied and `isClientMeansTested = false`. Otherwise the validator skips. This applicability check is shared with [InquestClaimValidator](claim-inquest-validator.md).
- Rules (all report `SCHEMA_VALIDATION_ERROR`):
  - Deceased forename and surname: letters, numbers, spaces, hyphens, apostrophes (`'` or `’`) and ampersands, maximum 30 characters.
  - Coroner's Inquest reference: letters, numbers, forward slashes, hyphens and periods, maximum 30 characters.
- Blank values pass here; whether a field is required is checked by `InquestClaimValidator`.

Priority & scope

- Priority: 1 (alongside `ClaimSchemaValidator`).
- Validator code: `CLAIM_INQUEST_SCHEMA_VALIDATOR`.

Notes

- Open item: whether the coroner's reference may contain internal spaces (currently rejected).
