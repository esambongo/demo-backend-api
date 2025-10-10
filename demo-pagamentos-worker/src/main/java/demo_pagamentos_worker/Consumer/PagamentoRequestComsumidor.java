package demo_pagamentos_worker.Consumer;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import demo_pagamentos_worker.Producer.PagamentoErroProdutor;
import demo_pagamentos_worker.Producer.PagamentoSucessoProdutor;

@Component
public class PagamentoRequestComsumidor {
    private @Autowired PagamentoErroProdutor erroProdutor;
    private @Autowired PagamentoSucessoProdutor sucessoProdutor;
   
    
    
    @RabbitListener(queues= {"pagamento-request-queue"})
    public void recebeMensagem(@Payload Message message) {
    	    String mensagemRecebida = new String(message.getBody(), StandardCharsets.UTF_8);
		    System.err.println("PROCESSANDO PAGAMENTO: "+mensagemRecebida);
		   if(new Random().nextBoolean()) {
			   this.sucessoProdutor.getResposta(mensagemRecebida);
		   }else {
			   this.erroProdutor.getResposta(mensagemRecebida);
		   }
	   }
}
