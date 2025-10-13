package demo_backend_worker.consumer;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import demo_backend_worker.facade.PagamentoFacade;

@Component
public class PagamentoResponseErroConsumidor {

	
	private @Autowired PagamentoFacade pagamentoFacade;
	
	@RabbitListener(queues = {"pagamento-response-erro-queue"})
	public void recive(@Payload Message message) {
		//System.err.println("Message "+message + " "+ LocalDateTime.now());
		
		String payload = new String(message.getBody(), StandardCharsets.UTF_8);
	    System.err.println("RETIRADA DA FILA DE PAGAMENTO COM ERRO: "+payload);
		this.pagamentoFacade.erroPagamento(payload);
	}
}
