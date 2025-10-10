package demo_backend_worker.producer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import demo_backend_worker.dto.PagamentoDTO;

@Service
public class PagamentoRequestProducer {
	
	private @Autowired AmqpTemplate amqpTemplate;
	
	private final ObjectMapper objectMapper= new ObjectMapper();

	public void integrar(PagamentoDTO pagamentoDTO) throws JsonProcessingException, AmqpException {
		this.amqpTemplate.convertAndSend("pagamento-request-exchange","pagamento-request-rout-key",objectMapper.writeValueAsString(pagamentoDTO));
		
	}
}
