package com.masterdev.sb_kafka_producer.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.masterdev.sb_kafka_producer.config.PayPalConfig;


@Service
public class PayPalService {

	private final PayPalConfig config;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	public PayPalService(PayPalConfig config) {
		this.config=config;
	}
	
	public String getAccessToken() {
		String auth = this.config.getClientId()+ ":" + this.config.getClientSecret();
		
		String encoded = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setBasicAuth(encoded);
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		HttpEntity<String> request = new HttpEntity<String>("grant_type=client_credentials",headers);
		
		
		ResponseEntity<Map> response = this.restTemplate.postForEntity(config.getApiBaseUrl() + "/v1/oauth2/token", request, Map.class);
		
		
		return (String) response.getBody().get("access_token");
	}
	
	public Map<?, ?> createOrder(Map<String, Object> orderPayload) {
		
		String token = getAccessToken();
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setBasicAuth(token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Map<String, Object>> request=new HttpEntity<>(orderPayload, headers);
		
		
		ResponseEntity<Map> response=this.restTemplate.postForEntity(config.getApiBaseUrl() + "/v2/checkout/orders", request, Map.class);
		
		return response.getBody();
	}

}
