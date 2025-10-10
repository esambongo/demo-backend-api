package demo_pagamentos_worker.Producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PagamentoErroProdutor {
	 private @Autowired AmqpTemplate amqpTemplate;
	
	public void getResposta(String mensagem) {
		
		System.err.println(mensagem);
		this.amqpTemplate.convertAndSend("pagamento-response-erro-exchange","pagamento-response-erro-rout-key",mensagem);
	}

}
