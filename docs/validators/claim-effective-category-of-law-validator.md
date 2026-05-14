# EffectiveCategoryOfLawClaimValidator

Purpose

- Validates that the claim's fee code maps to a category of law that the provider (office) is contracted to provide for the claim's effective date.

How it works

1. Determine the `effectiveDate` for the claim using `ClaimEffectiveDateUtil.getEffectiveDate(claim)` (may use case dates / submission period etc.).
2. Call the Provider Details API via `HttpProviderDetailsProvider.getProviderFirmSchedules(officeCode, effectiveDate)` to retrieve the provider's schedule lines and extract the categories of law the provider is contracted for.
3. Call the Fee Scheme API via `FeeSchemeClient.getFeeDetails(feeCode)` to retrieve the `FeeDetailsResponse` and read `categoryOfLawCode` from the fee details.
4. If the fee details are missing or `categoryOfLawCode` is null -> add `INVALID_CATEGORY_OF_LAW_AND_FEE_CODE`.
5. If the provider's categories do not contain the fee's categoryOfLaw -> add `INVALID_CATEGORY_OF_LAW_NOT_AUTHORISED_FOR_PROVIDER`.

Priority & scope

- Priority: 1000.
- Applies to scope: `fee` and `all` only.

Error handling & technical issues

- Network or API errors when calling provider details are handled and result in a `TECHNICAL_ERROR_PROVIDER_DETAILS_API` validation issue containing technical details. The validator logs errors with context (officeCode, effectiveDate).

Configuration points

- This validator depends on API client configuration provided by `FeeSchemeApiConfig` and `ProviderDetailsApiConfig` (see README configuration section). Ensure the `laa.fee-scheme-platform-api.url` and `laa.provider-details-api.url` settings are configured correctly for production or tests.

Notes

- This validator performs network calls and therefore can introduce latency; it runs at a later priority so earlier cheap validations filter out bad requests.
- Because it depends on external data, ensure appropriate caching or rate-limiting is in place upstream if high throughput is expected.
