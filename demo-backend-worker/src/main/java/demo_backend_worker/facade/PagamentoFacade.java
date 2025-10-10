package demo_backend_worker.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import demo_backend_worker.dto.PagamentoDTO;
import demo_backend_worker.producer.PagamentoRequestProducer;

@Service
public class PagamentoFacade {
    private @Autowired PagamentoRequestProducer producer;
	
	public String solicitarPagamento(PagamentoDTO pagamentoDTO) {
		try {
			this.producer.integrar(pagamentoDTO);
			
		}catch(JsonProcessingException e){ 
			return "Ocorreu um erro ao solicitar pagamento..."+e.getLocalizedMessage();
		}
		return "Pagamento aguardando confirmação...";
	}
	
	
	public void erroPagamento(String payload) {
		System.err.println("========== RESPOSTA ERRO ==========");
	}
	
	public void sucessoPagamento(String payload) {
		System.err.println("========== RESPOSTA SUCESSO ==========");
	}
}
