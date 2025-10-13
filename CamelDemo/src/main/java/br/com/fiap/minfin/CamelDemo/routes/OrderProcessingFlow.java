package br.com.fiap.minfin.CamelDemo.routes;

import org.apache.camel.builder.RouteBuilder;

public class OrderProcessingFlow extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		/*============================Message Channel principal=========================*/
		from("seda:newOrdersFlow")
		  .routeId("OrderProcessingFlow")
		  .log("[new Order Received] ${body}")
		
		/*========================publish - subscribe Channel========================*/
		
		  .multicast()
		  .parallelProcessing()
		  .to("direct:validationPipe", "direct:monitoringTap");
		
		
		from("direct:validationPipe")
		   .log("Validating order: ${body}")
		   .to("direct:checkFormat")
		   .to("direct:checkAmout");
		
		/*======================Filter 1: valor do pedido========================*/
		from("direct:checkFormat")
		   .filter(simple("${body} contais 'Order-'"))
		   .log("Format OK: ${body}")
		   .to("seda:validationOrders")
		   .end();

		/*======================Filter 2: valor do pedido========================*/
		from("direct:checkAmount")
		   .filter(simple("${body} regex '.*amount=[5-9][0-9][0-9].*'")) //>= 500
		   .to("seda:highValueOrders")
		   .end();

		/*==========================Message Filter==============================*/
		from("seda:validationOrders")
		   .filter(body().contains("amount="))
		   .log("Fowording to valid order: ${body}")
		   .to("seda:rounterInput");
	}
}
