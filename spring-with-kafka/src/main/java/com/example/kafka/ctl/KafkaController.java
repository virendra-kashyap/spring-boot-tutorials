package com.example.kafka.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka.config.MessageProducer;

@RestController
public class KafkaController {

	@Autowired
	private MessageProducer messageProducer;

	@PostMapping("/send")
	public String sendMessage(@RequestParam("message") String message) {
		messageProducer.sendMessage("my-topic", message);
		return "Message sent: " + message;
	}

}
