package demo_pagamentos_worker;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableRabbit
@SpringBootApplication
public class DemoPagamentosWorkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoPagamentosWorkerApplication.class, args);
		System.err.println("Rodando Worker");
	}

}
