# SubmissionStatusValidator

Purpose

- Checks that the submission's status is valid for starting validation and emits an error when status is null or not in an expected state.

How it works

- Reads the `SubmissionResponse.getStatus()` and writes context/log messages based on the status value.
- Handles several cases:
  - `VALIDATION_IN_PROGRESS`: logs that validation is already running for the submission.
  - `READY_FOR_VALIDATION`: logs that validation will start (status transition is handled by the caller/service layer).
  - `null`: adds `SUBMISSION_STATUS_IS_NULL` validation issue to the context.
  - any other unexpected state: logs and adds `INCORRECT_SUBMISSION_STATUS_FOR_VALIDATION` with the current status.

Priority & scope

- Priority: 1.
- Applies to all submission scopes.

Notes

- This class currently contains both status-checking and logging/state-transitions. The codebase contains a TODO noting this may be better expressed as a service that performs state changes while leaving validation as pure checks.
- The validator emits issues to guard against unexpected nulls or incorrect lifecycle states when a submission arrives for validation.
