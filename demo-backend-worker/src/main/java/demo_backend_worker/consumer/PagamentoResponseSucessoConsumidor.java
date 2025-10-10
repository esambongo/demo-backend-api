package demo_backend_worker.consumer;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import demo_backend_worker.facade.PagamentoFacade;

@Service
public class PagamentoResponseSucessoConsumidor {

	private @Autowired PagamentoFacade pagamentoFacade;
	
	@RabbitListener(queues = {"pagamento-response-sucesso-queue"})
	public void recive(@Payload Message message) {
		
		System.err.println("Message "+message + " "+ LocalDateTime.now());
		
		String payload=String.valueOf(message.getPayload());
		
		this.pagamentoFacade.sucessoPagamento(payload);
	}
}
