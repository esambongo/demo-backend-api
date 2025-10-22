package com.masterdev.sb_kafka_producer.producer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masterdev.sb_kafka_producer.DTO.PagamentoDTO;

@Service
public class PagamentoProducer {

	@Value("${topicos.pagamento.request.topic}")
	private String topic;
	
	private final KafkaTemplate<String, String> kafkaTemplate;
	
	private final ObjectMapper objectMapper;
	
	
	public PagamentoProducer(KafkaTemplate<String, String> kafkaTemplate,ObjectMapper objectMapper) {
		this.kafkaTemplate=kafkaTemplate;
		this.objectMapper=objectMapper;
	}
	
	
	
	public String enviar(PagamentoDTO pagamento) throws JsonProcessingException {
	    String mensagem = this.objectMapper.writeValueAsString(pagamento);	
	    this.kafkaTemplate.send(topic,mensagem);
		
		return "Pagamento enviado para o Kafka!";
	}
}
