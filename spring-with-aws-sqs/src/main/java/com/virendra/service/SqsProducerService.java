package com.virendra.service;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.io.File;
import java.io.FileReader;
import java.util.List;

@Service
public class SqsProducerService {

    @Value("${aws.queue.url}")
    private String queueUrl;

    @Autowired
    private SqsClient sqsClient;

    public void sendCsvToSqs(String filePath) {

        try {
            String basePath = "/data/uploads/";
            File base = new File(basePath).getCanonicalFile();
            File target = new File(basePath + filePath).getCanonicalFile();

            if (!target.getPath().startsWith(base.getPath())) {
                throw new SecurityException("Access to file outside allowed directory.");
            }

        try (CSVReader reader = new CSVReader(new FileReader(target))) {
            List<String[]> records = reader.readAll();
            for (String[] row : records) {
                String message = String.format("{\"name\":\"%s\", \"email\":\"%s\"}", row[0], row[1]);
                SendMessageRequest request = SendMessageRequest.builder()
                        .queueUrl(queueUrl)
                        .messageBody(message)
                        .build();
                sqsClient.sendMessage(request);
            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
