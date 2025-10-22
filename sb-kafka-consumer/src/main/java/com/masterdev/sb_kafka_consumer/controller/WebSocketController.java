package com.masterdev.sb_kafka_consumer.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
//@CrossOrigin(value = "*")
public class WebSocketController {

	@MessageMapping("/hello")
	@SendTo("/topic/messages")
	public String sendMessage(String message) {
		return message;
	}
}
