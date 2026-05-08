# LAA Claims Validation API

[![Ministry of Justice Repository Compliance Badge](https://github-community.service.justice.gov.uk/repository-standards/api/laa-data-controlled-work-validator/badge)](https://github-community.service.justice.gov.uk/repository-standards/laa-data-controlled-work-validator)

## Overview

Stateless validation core library for LAA Data Claims. This project contains reusable validators and client code to validate claim data against business rules and produce validation issues; it does not itself run as a network service.

### Key Features
- **Core validation library**: Stateless validation logic for claims implemented as a reusable module (`claims-validation-core`). This repository does not include a standalone REST or gRPC server; those adapters are provided in separate projects.
- **Pluggable Validators**: Modular validation rules with priority ordering and scope filtering
- **External Service Integration**: WebClient-based calls to Fee Scheme Platform, Data Claims APIs, and Provider Details API (client modules included)
- **OpenAPI First**: API contracts and model generation are used by consuming services (not included here)
- **Code Quality**: Enforced with Checkstyle and Spotless; pre-commit hooks available
- **Error Monitoring**: Sentry integration used by service modules (configuration present in Gradle plugins)

## Project Structure

```
laa-data-controlled-work-validator/
├── claims-validation-core/         # Core validation library (contains validators, clients, config)
│   ├── build.gradle
│   ├── gradle.properties
│   └── src/
│       ├── main/
│       │   └── java/uk/gov/justice/laa/dstew/payments/claims/validation/core/
│       │       ├── service/                # Validation orchestration (entry points, services)
│       │       ├── validator/              # Validator interfaces and implementations
│       │       │   ├── claim/              # Claim-level validators (rules/*)
│       │       │   ├── submission/         # Submission-level validators (rules/*)
│       │       │   └── duplicate/          # Duplicate claim validation strategies
│       │       ├── client/                 # WebClient-based API clients (FeeScheme, ProviderDetails, DataClaims)
│       │       ├── config/                 # Configuration properties and WebClient setup
│       │       ├── model/                  # Domain models (Claim, ValidationIssue, etc.)
│       │       ├── util/                   # Utilities (DateUtils, parsing helpers)
│       │       └── error/                  # Validation error enums and helpers
│       └── test/                           # Unit and integration tests for the core
├── reference-fee-scheme-platform-api/ # Reference Fee Scheme Platform API client (supporting code + spec)
├── reference-provider-details-api/     # Reference Provider Details API client (supporting code + spec)
├── docs/                          # Documentation for validators and usage (you can find per-validator docs here)
├── config/                        # Checkstyle and other config
├── scripts/                       # Utility scripts (e.g., setup-hooks.sh)
```

claims-validation-core layout

The `claims-validation-core` module is the heart of this repository. Key packages and responsibilities:

- service/: validation orchestration and entry-points. The service layer accepts a `Claim` or `SubmissionResponse`, builds a `*ValidationContext`, and executes the validator pipeline in priority order.
- validator/: validator interfaces and implementations. Split into `claim/` and `submission/` packages; `rules/` contains concrete rule classes (e.g. `ClaimSchemaValidator`, `MandatoryFieldClaimValidator`, `SubmissionSchemaValidator`).
- validator/.../duplicate/: pluggable duplicate-detection strategies implementing `DuplicateClaimValidationStrategy`.
- client/: WebClient-based API clients used by validators (e.g. `FeeSchemeClient`, `HttpProviderDetailsProvider`) to call external systems.
- config/: Spring `@ConfigurationProperties` classes and WebClient configuration (e.g. `FeeSchemeApiConfig`, `ProviderDetailsApiConfig`, `DataClaimsApiConfig`).
- model/: domain models used by the core (`Claim`, `ValidationIssue`, etc.). These are the canonical in-memory representations used by validators and service adapters.
- util/: shared utilities (date handling, parsing, string helpers) such as `DateUtils` and `StringCaseUtil`.
- error/: centralized validation error enums and factory methods (`ClaimValidationError`, `SubmissionValidationError`) used to produce consistent `ValidationIssue` objects.

References to important classes:

- `AbstractSchemaValidator<T>` (validator/schema base class used by schema validators)
- `ClaimValidationContext` / `SubmissionValidationContext` (context objects used to collect issues and provide related claims / fee calculation type)
- `ClaimValidationError` / `SubmissionValidationError` (enums mapping to validation issue codes/messages)


## Version Info

| Component | Version                                        |
|-----------|------------------------------------------------|
| Java | 25                                             |
| Spring Boot | 4.x                                            |
| Gradle | (see gradle/wrapper/gradle-wrapper.properties) |
| Micrometer | Latest                                         |

## Using the validation core

This repository contains the validation core library and reference API clients. It does not ship a standalone REST or gRPC server. To expose the validators over HTTP/gRPC add a small adapter/service module that depends on `claims-validation-core` (examples are maintained in separate repositories within the organisation).

Example (for reference only) — payloads and response format used by typical service adapters:

Request (example claim payload):
```json
{
  "claim": { /* claim fields */ },
  "scope": "fee",
  "relatedClaims": []
}
```

Response (example):
```json
{
  "isValid": false,
  "issues": [ /* validation issues */ ]
}
```
Keep in mind the exact REST/gRPC endpoint and wire format depend on the service adapter you build; the core library provides domain models and validation APIs to produce the `issues` list.

## Validators

Validators run in priority order (lower numbers first):

| Validator | Priority | Description |
|-----------|----------|-------------|
| [ClaimSchemaValidator](docs/validators/claim-schema-validator.md) | 1 | JSON schema validation (patterns, types, required fields) |
| [MandatoryFieldClaimValidator](docs/validators/claim-mandatory-field-validator.md) | 10 | Checks required fields are present (area-of-law driven) |
| [DisbursementClaimStartDateValidator](docs/validators/claim-disbursement-start-date-validator.md) | 10 | Validates disbursements are submitted a minimum number of months after case start |
| [UniqueFileNumberClaimValidator](docs/validators/claim-unique-file-number-validator.md) | 100 | Validates UFN format (DDMMYY/NNN) and that date is in the past |
| [CaseDatesClaimValidator](docs/validators/claim-case-dates-validator.md) | 100 | Validates case-related dates (start, concluded, transfer, representation order) |
| [ClientDateOfBirthClaimValidator](docs/validators/claim-client-dob-validator.md) | 100 | Validates client DOBs are sensible (not future, after 1900) |
| [DisbursementsClaimValidator](docs/validators/claim-disbursements-validator.md) | 100 | Validates disbursement VAT amounts and area-of-law limits |
| [StageReachedClaimValidator](docs/validators/claim-stage-reached-validator.md) | 100 | Validates stage reached code by area of law using regexes |
| [MatterTypeClaimValidator](docs/validators/claim-matter-type-validator.md) | 100 | Validates matter type code format per area of law |
| [OutcomeCodeClaimValidator](docs/validators/claim-outcome-code-validator.md) | 100 | Validates outcome code per area of law using regexes |
| [ScheduleReferenceClaimValidator](docs/validators/claim-schedule-reference-validator.md) | 100 | Validates schedule reference format and allowed characters |
| [EffectiveCategoryOfLawClaimValidator](docs/validators/claim-effective-category-of-law-validator.md) | 1000 | Validates fee code and provider contract via external APIs |
| [DuplicateClaimValidator](docs/validators/claim-duplicate-claim-validator.md) | 10000 | Delegates to area-of-law specific strategies to detect duplicate claims |


### Submission validators

The submission validation pipeline contains schema and business-rule validators. These run when validating a `SubmissionResponse` (e.g. during file-level validation) and are separate from per-claim validators.

| Validator | Priority | Description |
|-----------|----------|-------------|
| [SubmissionSchemaValidator](docs/validators/submission-schema-validator.md) | 1 | JSON schema validation for submissions (structure, types, required fields) |
| [SubmissionStatusValidator](docs/validators/submission-status-validator.md) | 1 | Validates submission status is suitable for validation and reports null/incorrect states |
| [SubmissionPeriodValidator](docs/validators/submission-period-validator.md) | 10 | Validates submission period format and business constraints (min period, not future) |
| [NilSubmissionValidator](docs/validators/submission-nil-validator.md) | 10 | Validates the nil flag is consistent with presence/absence of claims |
| [DuplicateSubmissionValidator](docs/validators/submission-duplicate-submission-validator.md) | 100 | Checks for duplicate submissions for the same Office × Area of Law × Submission Period |


## Configuration

This project exposes configuration via Spring Boot properties (configuration properties classes) and also supports environment variable overrides. Key configurable groups and the corresponding Spring property prefixes are:

- laa.fee-scheme-platform-api.* (Fee Scheme Platform API client)
- laa.data-claims-api.* (Data Claims API client)
- laa.provider-details-api.* (Provider Details API client)
- submission.validation.minimum-period (Submission validation minimum period)

Common properties available on each API client config (see the classes under `claims-validation-core/src/main/java/.../config`):

- url: Base URL for the upstream API (e.g. http://localhost:8082)
- access-token: Optional bearer token used for authentication
- auth-header: Header name to place the token in (defaults to Authorization)
- connect-timeout-ms: Connection timeout in milliseconds (default 5000)
- read-timeout-ms: Read/response timeout in milliseconds (default 10000)

Examples

1) application.yml example (recommended for Spring Boot apps):

```yaml
laa:
  fee-scheme-platform-api:
    url: ${FEE_SCHEME_SERVICE_URL:http://localhost:8082}
    access-token: ${FEE_SCHEME_SERVICE_ACCESS_TOKEN:}
    auth-header: Authorization
    connect-timeout-ms: ${EXTERNAL_CONNECT_TIMEOUT:5000}
    read-timeout-ms: ${EXTERNAL_READ_TIMEOUT:10000}

  data-claims-api:
    url: ${DATA_CLAIMS_SERVICE_URL:http://localhost:8083}
    access-token: ${DATA_CLAIMS_API_ACCESS_TOKEN:}

  provider-details-api:
    url: ${PROVIDER_DETAILS_API_URL:http://localhost:8084}

submission:
  validation:
    minimum-period: ${SUBMISSION_VALIDATION_MINIMUM_PERIOD:Jan-2018}
```

2) Environment variables (useful for containers / CI):

Set the corresponding environment variables used in the application.yml placeholders, e.g.:

```bash
export FEE_SCHEME_SERVICE_URL=http://fee-scheme-api:8082
export DATA_CLAIMS_SERVICE_URL=http://data-claims-api:8083
export EXTERNAL_CONNECT_TIMEOUT=5000
export EXTERNAL_READ_TIMEOUT=10000
export SUBMISSION_VALIDATION_MINIMUM_PERIOD=Jan-2018
```

3) Using the validation module from another Spring Boot project

If you want to reuse the validation core in another Spring Boot application, add a dependency on the module (as a published artifact or as a multi-project Gradle dependency). Example using a composite/multi-project Gradle setup:

build.gradle (consumer project):

```gradle
dependencies {
    implementation project(':claims-validation-core')
}
```

Then enable component scanning (usually automatic when you share the same package root) and configure the properties above in your application's `application.yml` or environment.

If the module is published to an internal Maven repository, use a normal Maven coordinate instead:

```gradle
dependencies {
    implementation 'uk.gov.justice:claims-validation-core:1.0.7'
}
```

Notes

- Timeouts are wired via the ApiProperties implementations (`FeeSchemeApiConfig`, `DataClaimsApiConfig`, `ProviderDetailsApiConfig`).
- `submission.validation.minimum-period` controls the earliest allowed submission period; it is parsed as a month-year (e.g. Jan-2018) in `SubmissionPeriodValidator`.
- The project also reads some values via environment variables when used in the standalone service modules; the examples above show common overrides.

## Build & Run

This repository provides a library and reference clients. To build and run the core and its tests:

```bash
# Build everything in this repository
./gradlew build

# Run unit tests
./gradlew test

# Run integration tests (if configured)
./gradlew integrationTest

# Analyze dependencies / project health
./gradlew :claims-validation-core:projectHealth
```

If you need a running REST or gRPC service, add a small Spring Boot application that depends on `claims-validation-core` and exposes endpoints that delegate to the core validation service. See the "Using the validation module from another Spring Boot project" section for dependency examples.

## gRPC / adapter examples

This repository does not include a gRPC or HTTP adapter. The core validation library can be exposed over gRPC or HTTP by building a small adapter that depends on `claims-validation-core` and implements the transport layer. The protobuf snippet below is an example of a service contract used by some adapters (example only):

```protobuf
service ClaimValidationService {
    rpc ValidateClaim (ClaimValidationRequest) returns (ClaimValidationResponse);
    rpc ValidateClaimStream (stream ClaimValidationRequest) returns (stream ClaimValidationResponse);
}
```

When you build an adapter you can enable gRPC reflection for local testing and use tools like `grpcurl` to exercise it. The exact port, flags and reflection settings are defined in the adapter project and are not part of this repository.

## Code Quality & Tooling

- **Checkstyle**: Enforced via `config/checkstyle/checkstyle.xml`
- **Spotless**: Code formatting (see build config)
- **Pre-commit hooks**: Run `./scripts/setup-hooks.sh` to install
- **Sentry**: Error monitoring enabled in service modules

## Test Coverage

- **Unit Tests**: 53 tests covering all validators and services
- **Integration Tests**: 7 tests covering full API flow

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

## License

See [LICENSE](LICENSE).
