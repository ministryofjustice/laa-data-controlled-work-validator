# DuplicateClaimValidator

Purpose

- Detects duplicate claims within the context of a submission by delegating to one or more `DuplicateClaimValidationStrategy` implementations tailored to specific areas of law.

How it works

- The validator gathers `areaOfLaw`, `officeAccountNumber`, `relatedClaims` (from `ClaimValidationContext`), and `feeCalculationType` and selects strategies from the autowired `List<DuplicateClaimValidationStrategy>` whose `compatibleAreaOfLaws()` contains the claim's area of law.
- Each compatible strategy's `validateDuplicateClaims(claim, submissionClaims, officeCode, feeType)` is invoked and any `ValidationIssue`s returned by the strategy are added to the context.

Priority & scope

- Priority: 10000 (runs very late after other validations so it can consider fully populated/validated claims).
- Applies to all claim scopes.

Notes for maintainers

- Duplicate detection logic is area-of-law specific and intentionally pluggable — new strategies should implement `DuplicateClaimValidationStrategy` and be registered as Spring components.
- Strategies receive the current claim and the list of related claims from the same submission and can use arbitrary matching logic (e.g. compare UFN, date ranges, fee codes, client identifiers).

Examples of strategy behaviour

- A strategy might consider two claims duplicates if they have the same Unique File Number and fee code and were submitted within the same submission period.
- Another strategy could apply only to a particular area-of-law and require additional fields to match.
