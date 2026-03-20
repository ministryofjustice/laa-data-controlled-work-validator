# System Data Flow: Data Claims Event Service

## 1. Main Service Entry Point(s)

### SQS Listener: `SubmissionListener.receiveSubmissionEvent(Message message)`
- **Type:** SQS Event Listener (AWS SQS)
- **Location:** `SubmissionListener.java`
- **Description:** Listens for messages on the `${laa.bulk-claim-queue.name}` SQS queue. Determines event type and dispatches to the appropriate handler.

## 2. Data and Control Flow Trace

### 2.1. SQS Event Reception
- **Step 1:** SQS message received by `SubmissionListener.receiveSubmissionEvent`.
- **Step 2:** Event type extracted from message attributes (`SubmissionEventType`).
- **Step 3:** Branches:
  - **a. `PARSE_BULK_SUBMISSION`:** Calls `handleBulkSubmissionMessage`.
  - **b. `VALIDATE_SUBMISSION`:** Calls `handleSubmissionValidationMessage`.

#### 2.1.a. Bulk Submission Parsing (`PARSE_BULK_SUBMISSION`)
- **Step 4:** `handleBulkSubmissionMessage` parses message to `BulkSubmissionMessage`.
- **Step 5:** For each `submissionId` in the bulk, calls `BulkParsingService.parseData(bulkSubmissionId, submissionId)`.
- **Step 6:** `BulkParsingService.parseData`:
  - Fetches bulk submission from Data Claims API (`DataClaimsRestClient.getBulkSubmission`). **[External Call]**
  - Normalizes data (internal).
  - Maps to `SubmissionPost` and creates submission (`DataClaimsRestClient.createSubmission`). **[External Call]**
  - Maps and creates claims (`DataClaimsRestClient.createClaim`). **[External Call, per claim]**
  - Maps and creates matter starts (`DataClaimsRestClient.createMatterStart`). **[External Call, per matter start]**
  - Updates submission status (`DataClaimsRestClient.updateSubmission`). **[External Call]**
  - Updates bulk submission status (`DataClaimsRestClient.updateBulkSubmission`). **[External Call]**

#### 2.1.b. Submission Validation (`VALIDATE_SUBMISSION`)
- **Step 4:** `handleSubmissionValidationMessage` parses message to `SubmissionValidationMessage`.
- **Step 5:** Calls `SubmissionValidationService.validateSubmission(submissionId)`.
- **Step 6:** `SubmissionValidationService.validateSubmission`:
  - Fetches submission from Data Claims API (`DataClaimsRestClient.getSubmission`). **[External Call]**
  - Initializes validation context (internal).
  - Runs all `SubmissionValidator`s (internal, in-memory).
  - If no submission-level errors, calls `ClaimValidationService.validateAndUpdateClaims`.
    - **ClaimValidationService:**
      - Fetches claims in batches (`DataClaimsRestClient.getClaims`). **[External Call]**
      - For each claim:
        - Runs external validations (category of law, duplicate, fee calculation, etc.).
        - May call Fee Scheme Platform API via `FeeSchemePlatformRestClient.calculateFee`. **[External Call]**
      - Updates claims via `BulkClaimUpdater.updateClaims` (calls `DataClaimsRestClient.updateClaim`). **[External Call, per claim]**
  - Updates submission and bulk submission status (`DataClaimsRestClient.updateSubmission`, `updateBulkSubmission`). **[External Calls]**

## 3. Major Component Summaries

| Component                        | Purpose                                                                 | External Dependencies                |
|----------------------------------|-------------------------------------------------------------------------|--------------------------------------|
| SubmissionListener               | SQS event entry point, dispatches to handlers                            | SQS (AWS), EventServiceMetricService |
| BulkParsingService               | Parses bulk submissions, creates submissions/claims/matter starts         | DataClaimsRestClient                 |
| SubmissionValidationService      | Validates submissions, orchestrates claim validation                     | DataClaimsRestClient, BulkClaimUpdater|
| ClaimValidationService           | Validates claims, runs business and external validations                 | DataClaimsRestClient, FeeSchemePlatformRestClient, ClaimsValidationRestClient |
| BulkClaimUpdater                 | Updates claim statuses and fee results                                   | DataClaimsRestClient, FeeCalculationService |
| FeeCalculationService            | Calls Fee Scheme Platform API for fee calculation                        | FeeSchemePlatformRestClient          |
| DataClaimsRestClient             | REST client for Data Claims API                                          | Data Claims API (external)           |
| FeeSchemePlatformRestClient      | REST client for Fee Scheme Platform API                                  | Fee Scheme Platform API (external)   |

## 3.1. Claim Validation Service: Detailed Data Flow

The `ClaimValidationService` is responsible for orchestrating the validation of all claims within a submission. Its process is as follows:

### Step-by-Step Flow (Externalized Validation)

1. **Batch Fetching:**
   - Claims are fetched in batches from the Data Claims API using `getClaims`, to efficiently handle large submissions.

2. **Per-Claim Processing:**
   - For each claim in the batch, the following occurs:
    ```mermaid
    flowchart TD
      %% Internal Service Logic
      A1([1. SQS Event Received])
      A2([2. Extract Event Type])
      A3{3. Event Type}
      A4([4a. Parse Bulk Submission])
      A5([5a. For each submissionId])
      A6([6a. BulkParsingService.parseData])
      A7([4b. Validate Submission])
      A8([5b. SubmissionValidationService.validateSubmission])
      A9{6b. Submission-level errors?}
      A10([7b. ClaimValidationService.validateAndUpdateClaims])
      A11([8b. For each claim in batch])
      A12([9b. Call Fee Scheme Platform API])
      A13([10b. Call Claims Validation API - with fee data])
      A14{11b. Claim Valid?}
      A15([12b. Mark as Valid, Update Claim])
      A16([13b. Mark as Invalid, Record Errors, Update Claim])
      A17([14b. Next Claim / Batch])
      A18([15b. BulkClaimUpdater.updateClaims])
      A19([16b. Update submission/bulk status])

      %% External Services (inline)
      E1([Data Claims API])
      E2([Fee Scheme Platform API])
      E3([Claims Validation API])

      %% Main flow
      A1 --> A2 --> A3
      A3 -- PARSE_BULK_SUBMISSION --> A4 --> A5 --> A6
      A6 -- getBulkSubmission, createSubmission, createClaim, createMatterStart, updateSubmission, updateBulkSubmission --> E1
      A3 -- VALIDATE_SUBMISSION --> A7 --> A8 --> A9
      A9 -- No errors --> A10 --> A11
      A11 --> A12 --> E2
      A12 --> A13 --> E3
      A13 --> A14
      A14 -- Yes --> A15 --> A17
      A14 -- No --> A16 --> A17
      A17 -- Next claim/batch --> A11
      A11 -. End of batch .-> A18
      A18 --> A19 --> E1
      A9 -- Errors --> A19 --> E1

      %% Color coding
      classDef internal fill:#e0f7fa,stroke:#00796b;
      classDef external fill:#fff3e0,stroke:#e65100;
      classDef decision fill:#fffde7,stroke:#fbc02d;
      class A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19 internal;
      class E1,E2,E3 external;
      class A3,A9,A14 decision;
    ```
    E1([Data Claims API])
    E2([Fee Scheme Platform API])
    E3([Claims Validation API])
  end

  %% Main flow
  A1 --> A2 --> A3
  A3 -- PARSE_BULK_SUBMISSION --> A4 --> A5 --> A6
  A6 -- getBulkSubmission, createSubmission, createClaim, createMatterStart, updateSubmission, updateBulkSubmission --> E1
  A3 -- VALIDATE_SUBMISSION --> A7 --> A8 --> A9
  A9 -- No errors --> A10 --> A11
  A11 --> A12 --> E2
  A12 --> A13 --> E3
  A13 --> A14
  A14 -- Yes --> A15 --> A17
  A14 -- No --> A16 --> A17
  A17 -- Next claim/batch --> A11
  A11 -. End of batch .-> A18
  A18 --> A19 --> E1
  A9 -- Errors --> A19 --> E1

  %% Color coding
  classDef internal fill:#e0f7fa,stroke:#00796b;
  classDef external fill:#fff3e0,stroke:#e65100;
  classDef decision fill:#fffde7,stroke:#fbc02d;
  class A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19 internal;
  class E1,E2,E3 external;
  class A3,A9,A14 decision;
```

## 5. External Interactions (Details)

| Step | Component                  | External Call (Method)                | Endpoint/Service                | Data Sent/Received                | Condition/Order                |
|------|---------------------------|---------------------------------------|---------------------------------|-----------------------------------|-------------------------------|
| 6a   | BulkParsingService        | getBulkSubmission (GET)               | Data Claims API                 | bulkSubmissionId                  | For each bulk submission       |
| 6a   | BulkParsingService        | createSubmission (POST)               | Data Claims API                 | SubmissionPost                    | For each submissionId          |
| 6a   | BulkParsingService        | createClaim (POST)                    | Data Claims API                 | ClaimPost                         | For each claim                 |
| 6a   | BulkParsingService        | createMatterStart (POST)              | Data Claims API                 | MatterStartPost                   | For each matter start          |
| 6a   | BulkParsingService        | updateSubmission (PATCH)              | Data Claims API                 | SubmissionPatch                    | After claims/matter starts     |
| 6a   | BulkParsingService        | updateBulkSubmission (PATCH)          | Data Claims API                 | BulkSubmissionPatch                | After all processing           |
| 6b   | SubmissionValidationService| getSubmission (GET)                   | Data Claims API                 | submissionId                      | For each validation event      |
| 6b   | ClaimValidationService    | getClaims (GET)                       | Data Claims API                 | submissionId, officeCode          | In batches                     |
| 6b   | ClaimValidationService    | calculateFee (POST)                   | Fee Scheme Platform API         | FeeCalculationRequest             | For each claim                 |
| 6b   | ClaimValidationService    | validateClaim (POST)                  | Claims Validation API           | Claim + Fee Data                  | For each claim                 |
| 6b   | BulkClaimUpdater         | updateClaim (PATCH)                   | Data Claims API                 | ClaimPatch                        | For each claim                 |
| 6b   | SubmissionValidationService| updateSubmission (PATCH)              | Data Claims API                 | SubmissionPatch                    | After validation               |
| 6b   | SubmissionValidationService| updateBulkSubmission (PATCH)          | Data Claims API                 | BulkSubmissionPatch                | After validation               |

---
*Generated on 2026-03-20.