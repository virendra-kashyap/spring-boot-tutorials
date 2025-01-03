package com.virendra.kafka.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("my-topic", message);
    }

    public void sendRecords(List<String> records) {
        records.forEach(record -> kafkaTemplate.send("my-topic-batch", record));
    }

    public void sendToDlq(String message, Exception exception) {
        kafkaTemplate.send("my-topic-dlq", message + " | Error: " + exception.getMessage());
    }

}
