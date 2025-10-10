package demo_backend_worker.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo_backend_worker.dto.PagamentoDTO;
import demo_backend_worker.facade.PagamentoFacade;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoApi {
	private @Autowired PagamentoFacade pagamentoFacade;
	
	@PostMapping
	public String processar(@RequestBody PagamentoDTO pagamentoDTO) {
		return this.pagamentoFacade.solicitarPagamento(pagamentoDTO);
	}
}
