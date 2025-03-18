package com.virendra.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virendra.model.User;
import com.virendra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

import java.util.List;

@Service
public class SqsConsumerService {

    @Value("${aws.queue.url}")
    private String queueUrl;

    @Autowired
    private SqsClient sqsClient;

    @Autowired
    private UserRepository userRepository;

    @Scheduled(fixedRate = 5000) // Poll every 5 seconds
    public void receiveMessages() {
        ReceiveMessageRequest receiveRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(5)
                .waitTimeSeconds(10)
                .build();

        List<Message> messages = sqsClient.receiveMessage(receiveRequest).messages();

        for (Message msg : messages) {
            System.out.println("msg # " + msg.body());
            if (shouldDelayMessage(msg)) {
                continue; // Message delete nahi hoga, wapas process hoga
            }
            processMessage(msg);
            deleteMessage(msg);
        }
    }

    private boolean shouldDelayMessage(Message msg) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(msg.body());
            String name = jsonNode.get("name").asText();

            if ("virendra kashyap".equalsIgnoreCase(name)) {
                System.out.println("Delaying message processing for 30 seconds: " + name);
                Thread.sleep(5000); // 5 seconds delay
                System.out.println("Message not deleted, it will be reprocessed.");
                return true; // Message delete nahi karna
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void processMessage(Message msg) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(msg.body());
            String name = jsonNode.get("name").asText();
            String email = jsonNode.get("email").asText();

            User user = new User();
            user.setName(name);
            user.setEmail(email);
            userRepository.save(user);

            System.out.println("User saved: " + name + " - " + email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteMessage(Message msg) {
        DeleteMessageRequest deleteRequest = DeleteMessageRequest.builder()
                .queueUrl(queueUrl)
                .receiptHandle(msg.receiptHandle())
                .build();
        sqsClient.deleteMessage(deleteRequest);
        System.out.println("Message deleted from SQS.");
    }

}
