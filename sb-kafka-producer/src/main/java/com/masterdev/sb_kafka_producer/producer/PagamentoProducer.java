package com.masterdev.sb_kafka_producer.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PagamentoProducer {

	@Value("${topicos.pagamentos.request.topic}")
	private String topic;
	
	private final KafkaTemplate<String, String> kafkaTemplate;
	
	private final ObjectMapper objectMapper;
	
	
	public PagamentoProducer(KafkaTemplate<String, String> kafkaTemplate,ObjectMapper objectMapper) {
		this.kafkaTemplate=kafkaTemplate;
		this.objectMapper=objectMapper;
	}
}
