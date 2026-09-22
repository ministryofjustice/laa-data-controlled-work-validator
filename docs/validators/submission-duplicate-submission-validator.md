# DuplicateSubmissionValidator

Purpose

- Detects whether a submission is a duplicate of an existing validated submission for the same Office × Area of Law × Submission Period.

How it works

- Uses `ClaimsDataProvider.getSubmissions(List<String> officeCodes, areaOfLaw, submissionPeriod)` to retrieve submissions matching the same office, area of law and period.
- Excludes the current submission and submissions with `SubmissionStatus.VALIDATION_FAILED` or
  `SubmissionStatus.REPLACED`; all other statuses are live for duplicate detection.
- Considers an older or undated live submission a blocking duplicate. A later submission does not
  block the submission under validation.
- If a blocking duplicate has `SubmissionStatus.VALIDATED_PENDING_APPROVAL`, the validator adds
  `SUBMISSION_AWAITING_FINAL_APPROVAL`. Otherwise it adds `SUBMISSION_ALREADY_EXISTS`. Both
  outcomes include the office, area of law and submission period in the issue.

Priority & scope

- Priority: 100.
- Applies to all submission scopes.

Notes

- This validator performs a query to the upstream claims data store; callers should ensure the `ClaimsDataProvider` is configured with the correct endpoint and credentials.
- The duplicate definition is intentionally conservative: any older or undated live submission for
  the same office/area/period marks the new submission as a duplicate.
