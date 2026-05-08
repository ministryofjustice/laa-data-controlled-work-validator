# SubmissionPeriodValidator

Purpose

- Validates the `submissionPeriod` field on a `SubmissionResponse` ensuring format and business constraints are met.

How it works

- Expects `submissionPeriod` to be in format `MMM-yyyy` (e.g. `Jan-2023`). Uses `DateUtils.parseSubmissionPeriod` to parse.
- Constructor accepts a `submission.validation.minimum-period` property (injected via `@Value`) which is parsed into a `YearMonth` and used as the earliest allowed period.
- Validation checks:
  - Missing submission period -> `SUBMISSION_PERIOD_MISSING`.
  - Invalid format -> `SUBMISSION_PERIOD_INVALID_FORMAT`.
  - Submission period is the same month as current -> `SUBMISSION_PERIOD_SAME_MONTH`.
  - Submission period is in the future -> `SUBMISSION_PERIOD_FUTURE_MONTH`.
  - Submission period is before the configured minimum -> `SUBMISSION_VALIDATION_MINIMUM_PERIOD`.

Priority & scope

- Priority: 10.
- Applies to all submission scopes.

Configuration

- `submission.validation.minimum-period` must be supplied in configuration (application.yml or environment) and is parsed by `DateUtils`.

Example

- If `submission.validation.minimum-period=Jan-2018` then any submission with `submissionPeriod` earlier than Jan-2018 will be rejected with `SUBMISSION_VALIDATION_MINIMUM_PERIOD`.
