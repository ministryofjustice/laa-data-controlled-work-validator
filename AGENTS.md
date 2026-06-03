# AGENTS — Onboarding notes for AI coding agents

Purpose: give an AI agent the minimal, concrete knowledge to be productive in this repo.

- Big picture
  - This repository is a library (not a standalone service): the core module is
    `claims-validation-core/`. It exposes a Spring-Boot auto-configuration
    (`ClaimsValidationAutoConfiguration`) that wires a validation pipeline and HTTP
    clients. Inspect `claims-validation-core/src/main/java/.../config/ClaimsValidationAutoConfiguration.java`.
  - Primary façade for callers is `ValidationService` (`service/ValidationService.java`).
    Validators are pluggable and run in priority order; scope filtering is supported.

- Important directories & files to read first
  - `claims-validation-core/src/main/java/.../config/ClaimsValidationAutoConfiguration.java` — how beans are created, bean name prefixes (`core*`) and `@ConditionalOnMissingBean` override strategy.
  - `claims-validation-core/src/main/java/.../service/ValidationService.java` — public API surface (validateClaim / validateSubmission).
  - `claims-validation-core/src/main/java/.../validator/` — validator interfaces (`Validator`, Claim/Submission validators) and rules in `validator/*/rules/`.
  - `claims-validation-core/src/main/java/.../config/WebClientConfig.java` — HTTP client behaviour, timeouts, increased buffer (50MB), request/response logging and X-Service-Name header.
  - `claims-validation-core/src/main/java/.../client/` — generated HTTP client interfaces (FeeSchemeClient, ProviderDetailsClient, DataClaimsClient).
  - `docs/validators/` — human-authored docs that explain many rule behaviours (per-validator markdowns).

- Key runtime & build facts agents must know
  - This is a library module (multi-project Gradle). Build and tests from repo root:
    - ./gradlew build
    - ./gradlew test
    - ./gradlew integrationTest
  - Java 25 and Spring Boot 4.x are used (see `README.md` and `claims-validation-core/gradle.properties`).
  - External model artifacts are fetched from a GitHub Packages Maven repo configured in `build.gradle`. CI/automation must set `GITHUB_ACTOR` and `GITHUB_TOKEN` env vars when publishing or resolving packages.

- Project-specific conventions & patterns
  - Auto-config entry point: all library beans are created in `ClaimsValidationAutoConfiguration` to avoid component-scan collisions when the library is added to other apps. Consumers override behaviour by registering beans of the same type — the core beans are guarded by `@ConditionalOnMissingBean`.
  - Bean names use a `core` prefix (e.g., `coreClaimSchemaValidator`) to reduce name collisions — but overrides are resolved by type, not by name.
  - Validators implement `Validator<T,C>` and must supply `priority()` and `getValidatorCode()`; the default `appliesTo(Set<String> scope)` uses `scope.contains(getValidatorCode())`. Scope is a Set<String>.
  - Validation is stateless at the service layer: mutable state lives inside ValidationContext classes (see `validator/*/ClaimValidationContext.java`).
  - Duplicate-detection is strategy-based: `DuplicateClaimValidator` receives a List<DuplicateClaimValidationStrategy> via constructor injection (see auto-config wiring) — add/remove strategies by providing beans of the strategy type.

- Integration points to watch
  - Fee Scheme Platform, Data Claims API, Provider Details API — clients under `claims-validation-core/src/main/java/.../client`. These use Spring WebClient + `HttpServiceProxyFactory` (see `WebClientConfig`).
  - Configuration properties prefixes: `laa.dstew.payments.validator.fee-scheme-platform-api.*`, `laa.dstew.payments.validator.data-claims-api.*`, `laa.dstew.payments.validator.provider-details-api.*`, and `laa.dstew.payments.validator.submission.minimum-period` (required by `SubmissionPeriodValidator`).
  - All property prefixes and keys are defined as compile-time constants in `ValidatorProperties.java` (see `config/ValidatorProperties.java`). Always reference these constants rather than repeating string literals. See README examples.

- Quick change examples for agents
  - Replace HTTP provider: register your own `ClaimsDataProvider` bean in a consuming Spring Boot app — core `HttpClaimsDataProvider` will be skipped because of `@ConditionalOnMissingBean(ClaimsDataProvider.class)`.
  - Add a validator: implement `ClaimValidator`, register it as a bean in the importing application — it will be picked up when `ClaimValidation` is constructed from the list of `ClaimValidator` beans.

- Where to add tests / how to run them
  - Unit tests: module tests run with JUnitPlatform — `./gradlew :claims-validation-core:test`.
  - Integration tests (if present): `./gradlew :claims-validation-core:integrationTest`.

- Useful local dev commands
  - Install pre-commit hooks: `./scripts/setup-hooks.sh`.
  - Build everything: `./gradlew build` (make sure `GITHUB_TOKEN` present if Maven packages required).

If you need more depth on any of the above (e.g. describe a validator's implementation, or list all validator classes and priorities), tell me which area and I'll expand the notes or produce a diagram.


