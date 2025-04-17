package com.artificial.intelligence.service;

import com.artificial.intelligence.domain.OllamaChatMsgResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class OllamaChatServiceImpl implements OllamaChatService {

    private final ChatClient chatClient;

    public OllamaChatServiceImpl(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public OllamaChatMsgResponse chat(String inputMessage) {
        String message;
        try {
            message = chatClient.prompt()
                    .user(inputMessage)
                    .call()
                    .content();
            return new OllamaChatMsgResponse(message);
        } catch (Exception e) {
            return new OllamaChatMsgResponse("Service is currently unavailable");
        }
    }

    @Override
    public OllamaChatMsgResponse chatWithImage(String inputMessage, MultipartFile imageFile) {
        String message;
        try {
            InputStream imageStream = imageFile.getInputStream();
            InputStreamResource imageResource = new InputStreamResource(imageStream);

            message = chatClient.prompt()
                    .user(prompt -> {
                        prompt.text(inputMessage);
                        prompt.media(MimeTypeUtils.IMAGE_JPEG, imageResource);
                    })
                    .call()
                    .content();

        } catch (Exception e) {
            message = "Service is currently unavailable";
        }
        return new OllamaChatMsgResponse(message);
    }
}
