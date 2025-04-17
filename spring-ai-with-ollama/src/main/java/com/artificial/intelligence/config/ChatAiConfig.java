package com.artificial.intelligence.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatAiConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
//        return builder.defaultSystem("You are a friendly chat bot that answers question in the voice of a Pirate")
//                .build();
        return builder.build();
    }

}
