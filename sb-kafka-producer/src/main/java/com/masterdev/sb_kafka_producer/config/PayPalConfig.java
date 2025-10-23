package com.masterdev.sb_kafka_producer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayPalConfig {
	@Value("${paypal.api-base-url}")
	private String apiBaseUrl;
	@Value("${paypal.client-id}")
	private String clientId;
	@Value("${paypal.client-secret}")
	private String clientSecret;

	public String getApiBaseUrl() {
		return apiBaseUrl;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}
}
