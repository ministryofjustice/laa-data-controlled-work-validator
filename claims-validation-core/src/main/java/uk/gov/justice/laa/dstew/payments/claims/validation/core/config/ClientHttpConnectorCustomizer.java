package uk.gov.justice.laa.dstew.payments.claims.validation.core.config;

import org.springframework.http.client.reactive.ClientHttpConnector;

@FunctionalInterface
public interface ClientHttpConnectorCustomizer {
    String FEE_SCHEME = "fee-scheme";
    String PROVIDER_DETAILS = "provider-details";
    String DATA_CLAIMS = "data-claims";

    ClientHttpConnector customize(String clientName, ClientHttpConnector connector);
}
