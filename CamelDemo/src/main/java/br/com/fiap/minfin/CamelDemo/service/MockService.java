package br.com.fiap.minfin.CamelDemo.service;

import org.springframework.stereotype.Service;

@Service
public class MockService {
	
	public String translate(String msg) {
		return msg.toUpperCase();
	}
	
	
	public boolean expensive(String type) {
		return "PREMIUM".equals(type);
	}
	
	
	public void processOrder(String body) {
		System.err.println("Processing order: "+body);
	}

}
