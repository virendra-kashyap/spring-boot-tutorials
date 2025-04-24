package com.artificial.intelligence.service;

import com.artificial.intelligence.domain.OllamaChatMsgResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import reactor.core.publisher.Mono;

public class OllamaChatServiceImplTest {

    private ChatClient chatClient;
    private OllamaChatServiceImpl chatService;

    @BeforeEach
    void setUp() {
        chatClient = Mockito.mock(ChatClient.class);
        chatService = new OllamaChatServiceImpl(chatClient);
    }

    @Test
    void testChatReturnExpectedResponse() {
        String inputMessage = "Tell me a joke!";
        String expectedResponse = "Why don’t scientists trust atoms? Because they make up everything!";

        ChatClient.ChatClientRequest chatClientRequest = Mockito.mock(ChatClient.ChatClientRequest.class);
        ChatClient.ChatClientRequest.CallResponseSpec callResponseSpec = Mockito.mock(ChatClient.ChatClientRequest.CallResponseSpec.class);

        Mockito.when(chatClient.prompt()).thenReturn(chatClientRequest);
        Mockito.when(chatClientRequest.user(inputMessage)).thenReturn(chatClientRequest);
        Mockito.when(chatClientRequest.call()).thenReturn(callResponseSpec);
        Mockito.when(callResponseSpec.content()).thenReturn(expectedResponse);

        OllamaChatMsgResponse chatMsgResponse = chatService.chat(inputMessage);

        Assertions.assertNotNull(chatMsgResponse);
        Assertions.assertEquals(chatMsgResponse.getMessage(), expectedResponse);
    }

    @Test
    void testChatWhenExceptionOccursReturnFallbackResponse() {
        String inputMessage = "Any random message";
        String expectedResponse = "Service is currently unavailable";

        Mockito.when(chatClient.prompt()).thenThrow(new RuntimeException("Simulated failure"));

        OllamaChatMsgResponse response = chatService.chat(inputMessage);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedResponse, response.getMessage());
    }
}
