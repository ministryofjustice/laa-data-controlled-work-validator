# claims-validation-api-poc
[![Ministry of Justice Repository Compliance Badge](https://github-community.service.justice.gov.uk/repository-standards/api/claims-validation-api-poc/badge)](https://github-community.service.justice.gov.uk/repository-standards/claims-validation-api-poc)

### ⚠️ WORK IN PROGRESS ⚠️
This service is under development and features may be added or subject to change.

## Overview

Stateless validation microservice for LAA Data Claims, built on the Ministry of Justice Spring Boot template.

### Project Structure
Includes the following subprojects:

- `claims-validation-api` - OpenAPI specification for validation endpoints and models.
- `claims-validation-service` - REST API service for claim validation, with outbound REST client and business rule stubs.

## Setup Instructions

### Update README
This README has been updated to reflect the new service name, structure, and version info.

### Update Repository Description
Change the description that appears at the top of your repository's main page to provide an overview of your project.

## Version Info
- Java 17+
- Spring Boot 4
- Micrometer
- No persistence (stateless, no JPA/Hibernate/DB config)

## Endpoints
- All endpoints are under `/v1`.
- Main endpoint: `POST /v1/validation/claim` (see OpenAPI spec for details)

## Build & Run

```sh
gradlew build
```

To run locally:
```sh
gradlew :claims-validation-service:bootRun
```

## Test
```sh
gradlew test
```

## Contributing
See [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

## License
See [LICENSE](LICENSE).

