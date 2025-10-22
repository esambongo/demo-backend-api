package com.masterdev.sb_kafka_producer.service;

import org.springframework.stereotype.Service;

import com.masterdev.sb_kafka_producer.DTO.PagamentoDTO;
import com.masterdev.sb_kafka_producer.producer.PagamentoProducer;


@Service
public class PagamentoService {

	
	              
	private final PagamentoProducer pagamentoProducer;
	
	
	public PagamentoService(PagamentoProducer producer){
		this.pagamentoProducer=producer;
	}
	
	public String processarPagamento(PagamentoDTO pagamentoDTO) {
		try {
			return this.pagamentoProducer.enviar(pagamentoDTO);
		}catch(Exception e) {
			return "======>> Erro ao enviar pagamento: "+e.getMessage();			
		}
		
	}

}
