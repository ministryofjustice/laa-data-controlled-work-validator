package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

/**
 * Central constants for the claims-validation-core configuration property namespace.
 *
 * <p>All {@code @ConfigurationProperties} prefixes and {@code @Value} property keys owned by this
 * library are built from {@link #BASE_PREFIX}. Using this constant in annotation declarations
 * ensures a single source of truth for the root namespace and prevents typo-driven collisions
 * when the library is embedded in other Spring Boot applications.
 *
 * <h2>Usage in annotations</h2>
 * <pre>{@code
 * @ConfigurationProperties(prefix = ValidatorProperties.BASE_PREFIX + ".fee-scheme-platform-api")
 * }</pre>
 *
 * <h2>Full property namespace map</h2>
 * <ul>
 *   <li>{@value #FEE_SCHEME_PREFIX} — Fee Scheme Platform API client config</li>
 *   <li>{@value #DATA_CLAIMS_PREFIX} — Data Claims API client config</li>
 *   <li>{@value #PROVIDER_DETAILS_PREFIX} — Provider Details API client config</li>
 *   <li>{@value #SERVICE_NAME_PROPERTY} — optional service name sent in X-Service-Name header</li>
 *   <li>{@value #SUBMISSION_MINIMUM_PERIOD_PROPERTY} — earliest allowed submission period</li>
 * </ul>
 */
public final class ValidatorProperties {

  /**
   * Root namespace for all claims-validation-core configuration properties.
   * All library-owned property prefixes start with this value.
   */
  public static final String BASE_PREFIX = "laa.dstew.payments.validator";

  /** Full prefix for {@link FeeSchemeApiConfig} configuration properties. */
  public static final String FEE_SCHEME_PREFIX = BASE_PREFIX + ".fee-scheme-platform-api";

  /** Full prefix for {@link DataClaimsApiConfig} configuration properties. */
  public static final String DATA_CLAIMS_PREFIX = BASE_PREFIX + ".data-claims-api";

  /** Full prefix for {@link ProviderDetailsApiConfig} configuration properties. */
  public static final String PROVIDER_DETAILS_PREFIX = BASE_PREFIX + ".provider-details-api";

  /**
   * Property key for the optional service name injected into the {@code X-Service-Name} header
   * on all outbound HTTP calls. Defaults to {@code spring.application.name} when not set.
   */
  public static final String SERVICE_NAME_PROPERTY = BASE_PREFIX + ".service-name";

  /**
   * Property key for the earliest allowed submission period (e.g. {@code "Apr-2013"}).
   * Required by {@code SubmissionPeriodValidator}.
   */
  public static final String SUBMISSION_MINIMUM_PERIOD_PROPERTY =
      BASE_PREFIX + ".submission.minimum-period";

  private ValidatorProperties() {
    // constants-only class
  }
}
