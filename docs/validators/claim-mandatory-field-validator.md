# MandatoryFieldClaimValidator

Purpose

- Ensures required fields for a given `AreaOfLaw` are present on the `Claim` object.

How it works

- Uses a `MandatoryFieldsRegistry` which maps `AreaOfLaw` -> list of field names that must be present.
- For each required field it uses JavaBeans `PropertyDescriptor` to call the getter on `Claim` and checks for `null` or blank strings.
- There is an `ExclusionsRegistry` that contains fields to exclude in certain circumstances (for example when a claim is a disbursement-only claim).
- The validator checks the fee calculation type (from the `ClaimValidationContext`) to decide whether disbursement-only exclusions apply.

Priority

- Priority: 10 (runs early, just after schema validation).

Behavior

- For each missing or blank mandatory field the validator adds a `MISSING_MANDATORY_FIELD` validation issue with a display-friendly field name and area-of-law.
- If a getter cannot be accessed, the validator will throw an `IllegalStateException` (this indicates a programming/configuration error).

Configuration points

- Mandatory field lists are populated by `MandatoryFieldsRegistry`. To change mandatory fields for an area-of-law, update the registry configuration used by your hosting application.
- Exclusions used for disbursement-only validation are provided by `ExclusionsRegistry`.

Example

- If `AreaOfLaw=LEGAL_HELP` requires `feeCode` and the `Claim` has `feeCode=null`, this validator will add a `MISSING_MANDATORY_FIELD` issue referencing `Fee Code` and `LEGAL_HELP`.
