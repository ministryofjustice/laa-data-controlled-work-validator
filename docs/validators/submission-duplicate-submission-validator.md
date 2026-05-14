# DuplicateSubmissionValidator

Purpose

- Detects whether a submission is a duplicate of an existing validated submission for the same Office × Area of Law × Submission Period.

How it works

- Uses `ClaimsDataProvider.getSubmissions(List<String> officeCodes, areaOfLaw, submissionPeriod)` to retrieve submissions matching the same office, area of law and period.
- Filters results to only those with `SubmissionStatus.VALIDATION_SUCCEEDED` (i.e. previous successful validations).
- If any such submissions exist the validator adds `SUBMISSION_ALREADY_EXISTS` including office, area-of-law and submission period in the issue.

Priority & scope

- Priority: 100.
- Applies to all submission scopes.

Notes

- This validator performs a query to the upstream claims data store; callers should ensure the `ClaimsDataProvider` is configured with the correct endpoint and credentials.
- The duplicate definition is intentionally conservative: any prior successful submission for the same office/area/period marks the new submission as a duplicate.
