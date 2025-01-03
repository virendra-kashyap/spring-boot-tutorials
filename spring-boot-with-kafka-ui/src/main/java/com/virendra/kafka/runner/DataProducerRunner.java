package com.virendra.kafka.runner;

import com.virendra.kafka.service.KafkaProducerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataProducerRunner implements CommandLineRunner {

    private static final int TOTAL_RECORDS = 1800000;
    private static final int BATCH_SIZE = 10000;

    private final KafkaProducerService kafkaProducerService;

    public DataProducerRunner(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Starting to send " + TOTAL_RECORDS + " records in batches of " + BATCH_SIZE);

        for (int i=0; i < TOTAL_RECORDS; i += BATCH_SIZE) {
            List<String> batch = generateBatch(i, Math.min(i + BATCH_SIZE, TOTAL_RECORDS));
            kafkaProducerService.sendRecords(batch);
            System.out.println("Batch " + (i / BATCH_SIZE+ 1) + " sent successfully.");
        }
        System.out.println("All records sent successfully.");
    }

    private List<String> generateBatch(int start, int end) {
        List<String> list = new ArrayList<>();
        for (int i = start; i < end; i++) {
            list.add("Record " + i);
        }
        return list;
    }
}
