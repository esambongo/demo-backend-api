package demo_pagamentos_worker.Consumer;

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
		   System.err.println(message);
		   
		   if(new Random().nextBoolean()) {
			   this.sucessoProdutor.getResposta("Mensagem de sucesso pagamento: "+message);
		   }else {
			   this.erroProdutor.getResposta("ERRO no pagamento: "+message);
		   }
	   }
   
   /*@RabbitListener(queues= {"pagamento-request-queue"})
   public void recebeMensagem(PagamentoDTO pagamentoDTO,Message message,Channel channel) {
	   System.err.println(pagamentoDTO);
	   
	   if(new Random().nextBoolean()) {
		   this.sucessoProdutor.getResposta("Mensagem de sucesso pagamento: "+message);
	   }else {
		   this.erroProdutor.getResposta("ERRO no pagamento: "+message);
	   }
   }*/
}
