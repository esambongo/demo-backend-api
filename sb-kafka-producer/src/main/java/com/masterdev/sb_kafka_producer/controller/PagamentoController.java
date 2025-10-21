package com.masterdev.sb_kafka_producer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masterdev.sb_kafka_producer.DTO.PagamentoDTO;
import com.masterdev.sb_kafka_producer.service.PagamentoService;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {
	
	private final PagamentoService pagamentoService;
	
	public PagamentoController(PagamentoService service) {
		this.pagamentoService=service;
	}
	
	@PostMapping
	public String enviar(@RequestBody PagamentoDTO pagamentoDTO) {
		return this.pagamentoService.processarPagamento(pagamentoDTO);
	}

}
