# LAA Claims Validation API

[![Ministry of Justice Repository Compliance Badge](https://github-community.service.justice.gov.uk/repository-standards/api/claims-validation-api-poc/badge)](https://github-community.service.justice.gov.uk/repository-standards/claims-validation-api-poc)

## Overview

Stateless validation microservice for LAA Data Claims. This service validates claim data against business rules and returns validation issues without persisting any data.

### Key Features
- **Stateless**: No database, no persistence - receives claims via REST, validates, returns results
- **Pluggable Validators**: Modular validation rules with priority ordering and scope filtering
- **External Service Integration**: WebClient-based calls to Fee Scheme Platform and Data Claims APIs
- **OpenAPI First**: API contracts defined in OpenAPI specification

## Project Structure

```
laa-data-claims-validation-api-poc/
├── claims-validation-api/          # OpenAPI spec and generated models (deployed as package)
│   ├── open-api-specification.yml
│   └── generated/                  # Auto-generated API interfaces and models
├── claims-validation-core/         # Core validation logic (deployed as package)
│   └── src/main/java/.../validation/core/
│       ├── service/                # Validation orchestration
│       ├── validator/              # Validator interface and implementations
│       │   ├── rules/              # Individual validation rules
│       │   │   └── duplicate/      # Duplicate claim validation strategies
│       │   └── util/               # Validation utilities
│       ├── client/                 # External service clients
│       ├── config/                 # WebClient configuration
│       └── error/                  # Validation error enums
├── claims-validation-service/      # REST application (pulls in core and api)
│   └── src/main/java/.../validation/service/
│       └── controller/             # REST controller
```

## Version Info

| Component | Version |
|-----------|---------|
| Java | 17+ |
| Spring Boot | 4.0.x |
| Gradle | 9.x |
| Micrometer | Latest |

## API Endpoint

### POST /v1/validation/claim

Validates a claim and returns validation issues.

**Request:**
```json
{
  "claim": {
    "areaOfLaw": "LEGAL_HELP",
    "officeAccountNumber": "1A234B",
    "feeCode": "ABC123",
    "uniqueFileNumber": "010120/001",
    "caseStartDate": "2020-01-15",
    "caseConcludedDate": "2020-06-15"
  },
  "scope": "fee",
  "relatedClaims": []
}
```

**Response:**
```json
{
  "isValid": false,
  "issues": [
    {
      "code": "FEE.MISSING_JUSTIFICATION",
      "message": "Enhancement fee requires a justification.",
      "path": ["fees", 0, "justification"],
      "severity": "ERROR"
    }
  ]
}
```

## Validators

| Validator | Priority | Description |
|-----------|----------|-------------|
| `MandatoryFieldValidator` | 10 | Checks required fields are present |
| `UniqueFileNumberValidator` | 20 | Validates UFN format (DDMMYY/NNN) |
| `CaseDatesValidator` | 30 | Validates case dates |
| `ClientDateOfBirthValidator` | 35 | Validates client DOB |
| `DisbursementsValidator` | 40 | Validates VAT amounts |
| `StageReachedValidator` | 50 | Validates stage reached code |
| `MatterTypeValidator` | 55 | Validates matter type |
| `OutcomeCodeValidator` | 60 | Validates outcome code |
| `ScheduleReferenceValidator` | 65 | Validates schedule reference |
| `CategoryOfLawValidator` | 70 | Validates fee code via external API |
| `DuplicateClaimValidator` | 80 | Checks for duplicate claims |

## Configuration

Environment variables:

| Variable | Default | Description |
|----------|---------|-------------|
| `FEE_SCHEME_SERVICE_URL` | `http://localhost:8082` | Fee Scheme Platform API URL |
| `DATA_CLAIMS_SERVICE_URL` | `http://localhost:8083` | Data Claims API URL |
| `EXTERNAL_CONNECT_TIMEOUT` | `5000` | Connection timeout (ms) |
| `EXTERNAL_READ_TIMEOUT` | `10000` | Read timeout (ms) |
| `LOG_LEVEL` | `INFO` | Application log level |

## Build & Run

```bash
# Build
./gradlew build

# Run locally
./gradlew :claims-validation-service:bootRun

# Run tests
./gradlew test

# Run integration tests
./gradlew integrationTest
```

## Test Coverage

- **Unit Tests**: 53 tests covering all validators and services
- **Integration Tests**: 7 tests covering full API flow

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

## License

See [LICENSE](LICENSE).
