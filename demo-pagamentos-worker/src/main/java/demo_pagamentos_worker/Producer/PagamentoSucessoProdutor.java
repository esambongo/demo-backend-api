package demo_pagamentos_worker.Producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoSucessoProdutor {

	
	private @Autowired AmqpTemplate amqpTemplate;

	
	public void getResposta(String mensagem) {
		this.amqpTemplate.convertAndSend("pagamento-response-sucesso-exchange","pagamento-response-sucesso-rout-key", mensagem);
	}

}
