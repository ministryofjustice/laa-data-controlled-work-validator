# LAA Claims Validation API

[![Ministry of Justice Repository Compliance Badge](https://github-community.service.justice.gov.uk/repository-standards/api/claims-validation-api-poc/badge)](https://github-community.service.justice.gov.uk/repository-standards/claims-validation-api-poc)

## Overview

Stateless validation microservice for LAA Data Claims. This service validates claim data against business rules and returns validation issues without persisting any data.

### Key Features
- **Stateless**: No database, no persistence - receives claims via REST or gRPC, validates, returns results
- **Pluggable Validators**: Modular validation rules with priority ordering and scope filtering
- **External Service Integration**: WebClient-based calls to Fee Scheme Platform, Data Claims APIs, and Reference APIs
- **OpenAPI First**: API contracts defined in OpenAPI specification
- **Dual Protocol Support**: REST (claims-validation-service) and gRPC (claims-validation-grpc)
- **Code Quality**: Enforced with Checkstyle and Spotless; pre-commit hooks available
- **Error Monitoring**: Sentry integration for error tracking

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
├── claims-validation-grpc/         # gRPC application (pulls in core and api)
│   ├── src/main/proto/             # Protocol Buffer definitions
│   └── src/main/java/.../validation/grpc/
│       ├── service/                # gRPC service implementation
│       └── mapper/                 # Proto <-> Domain mappers
├── reference-fee-scheme-platform-api/ # Reference Fee Scheme Platform API client
├── reference-provider-details-api/     # Reference Provider Details API client
├── bruno/                        # API test collections (Bruno)
├── config/                       # Checkstyle and other config
├── scripts/                      # Utility scripts (e.g., setup-hooks.sh)
```

## Version Info

| Component | Version |
|-----------|---------|
| Java | 25 |
| Spring Boot | 3.x |
| Gradle | (see gradle/wrapper/gradle-wrapper.properties) |
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

Validators run in priority order (lower numbers first):

| Validator | Priority | Description |
|-----------|----------|-------------|
| `ClaimSchemaValidator` | 1 | JSON schema validation (patterns, types, required fields) |
| `MandatoryFieldClaimValidator` | 10 | Checks required fields are present |
| `DisbursementClaimStartDateValidator` | 10 | Validates disbursements are submitted 3+ months after case start |
| `UniqueFileNumberClaimValidator` | 100 | Validates UFN format (DDMMYY/NNN) |
| `CaseDatesClaimValidator` | 100 | Validates case dates |
| `ClientDateOfBirthClaimValidator` | 100 | Validates client DOB |
| `DisbursementsClaimValidator` | 100 | Validates VAT amounts |
| `StageReachedClaimValidator` | 100 | Validates stage reached code |
| `MatterTypeClaimValidator` | 100 | Validates matter type |
| `OutcomeCodeClaimValidator` | 100 | Validates outcome code |
| `ScheduleReferenceClaimValidator` | 100 | Validates schedule reference |
| `EffectiveCategoryOfLawClaimValidator` | 1000 | Validates fee code via external API |
| `DuplicateClaimValidator` | 10000 | Checks for duplicate claims |

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
# Build all projects
./gradlew build

# Run REST service locally
./gradlew :claims-validation-service:bootRun

# Run gRPC service locally
./gradlew :claims-validation-grpc:bootRun

# Run tests
./gradlew test

# Run integration tests
./gradlew integrationTest

# Check for dependency updates
./gradlew dependencyUpdates

# Analyze unused dependencies
./gradlew :claims-validation-core:projectHealth
```

## gRPC Service

The gRPC service exposes the same validation functionality on port 9090.

### Service Definition

```protobuf
service ClaimValidationService {
    rpc ValidateClaim (ClaimValidationRequest) returns (ClaimValidationResponse);
    rpc ValidateClaimStream (stream ClaimValidationRequest) returns (stream ClaimValidationResponse);
}
```

### gRPC Configuration

| Variable | Default | Description |
|----------|---------|-------------|
| `GRPC_REFLECTION_ENABLED` | `true` | Enable gRPC reflection (disable in prod) |
| `GRPC_LOG_LEVEL` | `INFO` | gRPC logging level |

### Testing with grpcurl

```bash
# List services (requires reflection enabled)
grpcurl -plaintext localhost:9090 list

# Describe the service
grpcurl -plaintext localhost:9090 describe uk.gov.justice.laa.dstew.payments.claims.validation.grpc.ClaimValidationService

# Validate a claim
grpcurl -plaintext -d '{
  "claim": {
    "area_of_law": "LEGAL HELP",
    "office_account_number": "1A234B"
  },
  "scope": "all"
}' localhost:9090 uk.gov.justice.laa.dstew.payments.claims.validation.grpc.ClaimValidationService/ValidateClaim
```

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
