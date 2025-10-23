package com.masterdev.sb_kafka_producer.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masterdev.sb_kafka_producer.service.PayPalService;

@RestController
@RequestMapping("/api/paypal")
public class PayPalController {
	
	private final PayPalService service;
	
	public PayPalController(PayPalService service){
		this.service=service;
	}
	
	
	@PostMapping("/orders")
	public Map<?,?> createOrder(@RequestBody Map<String,Object> payload){
		return this.service.createOrder(payload);
	}

}
