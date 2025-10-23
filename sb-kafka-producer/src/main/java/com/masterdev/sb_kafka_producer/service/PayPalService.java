package com.masterdev.sb_kafka_producer.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.masterdev.sb_kafka_producer.config.PayPalConfig;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

@Service
public class PayPalService {

    private final PayPalConfig paypalConfig;

    public PayPalService(PayPalConfig paypalConfig) {
        this.paypalConfig = paypalConfig;
    }

    private final RestTemplate restTemplate = new RestTemplate();


    public String getAccessToken() {
        String auth = paypalConfig.getClientId() + ":" + paypalConfig.getClientSecret();
        String encoded = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(encoded);
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<String> request = new HttpEntity<>("grant_type=client_credentials", httpHeaders);

        ResponseEntity<Map> response = restTemplate.postForEntity(paypalConfig.getApiBaseUrl() + "/v1/oauth2/token", request, Map.class);

        return (String)response.getBody().get("access_token");
    }

    public Map<?, ?> createOrder(Map<String, Object> payload) {

        String token = getAccessToken();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String,Object>> request = new HttpEntity<>(payload, httpHeaders);

        ResponseEntity<Map> response = restTemplate.postForEntity(paypalConfig.getApiBaseUrl() + "/v2/checkout/orders", request, Map.class);

        return response.getBody();
    }
}