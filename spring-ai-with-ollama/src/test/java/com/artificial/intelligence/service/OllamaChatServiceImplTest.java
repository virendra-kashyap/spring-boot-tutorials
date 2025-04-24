package com.artificial.intelligence.service;

import com.artificial.intelligence.domain.OllamaChatMsgResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

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

    @Test
    void testChatWithImageReturnsExpectedResponse() throws Exception {
        String inputMessage = "Describe this image.";
        String expectedResponse = "It looks like a sunset over mountains.";

        MultipartFile mockImageFile = Mockito.mock(MultipartFile.class);
        InputStream mockStream = new ByteArrayInputStream("dummy image".getBytes());
        Mockito.when(mockImageFile.getInputStream()).thenReturn(mockStream);

        ChatClient.ChatClientRequest chatClientRequest = Mockito.mock(ChatClient.ChatClientRequest.class);
        ChatClient.ChatClientRequest.CallResponseSpec callResponseSpec = Mockito.mock(ChatClient.ChatClientRequest.CallResponseSpec.class);

        Mockito.when(chatClient.prompt()).thenReturn(chatClientRequest);

        Mockito.doAnswer(invocation -> {
            ChatClient.PromptSpec<?> promptSpec = invocation.getArgument(0);
            promptSpec.text(inputMessage);
            return chatClientRequest;
        }).when(chatClientRequest).user(ArgumentMatchers.any());

        Mockito.when(chatClientRequest.call()).thenReturn(callResponseSpec);
        Mockito.when(callResponseSpec.content()).thenReturn(expectedResponse);

        OllamaChatMsgResponse response = chatService.chatWithImage(inputMessage, mockImageFile);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(expectedResponse, response.getMessage());
    }

    @Test
    void testChatWithImageReturnsFallbackOnException() throws Exception {
        MultipartFile mockImageFile = Mockito.mock(MultipartFile.class);
        Mockito.when(mockImageFile.getInputStream()).thenThrow(new RuntimeException("Stream error"));

        String fallbackMessage = "Service is currently unavailable";
        OllamaChatMsgResponse response = chatService.chatWithImage("Test", mockImageFile);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(fallbackMessage, response.getMessage());
    }
}
