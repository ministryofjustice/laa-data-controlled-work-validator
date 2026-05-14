# NilSubmissionValidator

Purpose

- Ensures that the `isNilSubmission` flag on a `SubmissionResponse` is consistent with the presence of claims.

How it works

- If `isNilSubmission` is true the submission must not contain any claims; otherwise the validator adds `INVALID_NIL_SUBMISSION_CONTAINS_CLAIMS`.
- If `isNilSubmission` is false the submission must contain at least one claim; otherwise the validator adds `NON_NIL_SUBMISSION_CONTAINS_NO_CLAIMS`.

Priority & scope

- Priority: 10.
- Applies to all submission scopes.

Notes

- This validator enforces a simple business invariant about nil submissions. It is safe to run early in the submission validation pipeline.
