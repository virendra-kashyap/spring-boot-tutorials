package com.virendra.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaPublisherController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "my_topic";

    @PostMapping("/publish")
    public String sendMessage(@RequestBody String message) {
        for (int i = 0; i < 1000000; i++) {
            System.out.println(i + " " + message);
            kafkaTemplate.send(TOPIC, message);
        }
        return "Message sent to Kafka topic successfully";
    }

}
