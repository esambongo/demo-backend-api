package demo_backend_worker.consumer;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import demo_backend_worker.facade.PagamentoFacade;

@Service
public class PagamentoResponseSucessoConsumidor {

	private @Autowired PagamentoFacade pagamentoFacade;
	
	@RabbitListener(queues = {"pagamento-response-sucesso-queue"})
	public void recive(@Payload Message message) {
		
		
		String payload = new String(message.getBody(), StandardCharsets.UTF_8);
	    System.err.println("RETIRADA DA FILA DE PAGAMENTO COM SUCESSO: "+payload+" - DATA"+LocalDateTime.now());
		this.pagamentoFacade.sucessoPagamento(payload);
	}
}
