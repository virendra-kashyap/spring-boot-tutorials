package com.virendra.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.TopicForRetryable;

@Configuration
public class KafkaTopicConfig {

//    @Bean
//    public NewTopic topic() {
//        return new NewTopic("my-topic", 1, (short) 1);
//    }

    @Bean
    public NewTopic topic() {
        return new NewTopic("my-topic-batch", 1, (short) 1);
    }

    @Bean
    public NewTopic dlqTopic() {
        return new NewTopic("my-topic-dlq", 1, (short) 1);
    }

}
