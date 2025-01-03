package com.virendra.kafka.service;

import com.virendra.kafka.entity.Record;
import com.virendra.kafka.repository.RecordRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaConsumerService {

    private final RecordRepository recordRepository;
    private final KafkaProducerService kafkaProducerService;

    public KafkaConsumerService(RecordRepository recordRepository, KafkaProducerService kafkaProducerService) {
        this.recordRepository = recordRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("Received Message: " + record.value());
    }

    @KafkaListener(topics = "my-topic-batch", groupId = "my-group")
    public void listen(List<ConsumerRecord<String, String>> records) {

        records.forEach(record -> {
            try {
                Record entity = new Record(record.value());
                recordRepository.save(entity);
                System.out.println("Processed message: " + record.value());
            } catch (Exception e) {
                System.err.println("Error processing message: " + record.value());
                kafkaProducerService.sendToDlq(record.value(), e);
            }
        });
    }

}
