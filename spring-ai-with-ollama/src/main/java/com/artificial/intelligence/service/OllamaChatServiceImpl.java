package com.artificial.intelligence.service;

import com.artificial.intelligence.domain.OllamaChatMsgResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.io.InputStream;

@Service
public class OllamaChatServiceImpl implements OllamaChatService {

    @Autowired
    private ChatClient chatClient;

    private static final Logger log = LoggerFactory.getLogger(OllamaChatServiceImpl.class);

    @Override
    public OllamaChatMsgResponse chat(String inputMessage) {
        String message;
        try {
            message = chatClient.prompt()
                    .user(inputMessage)
                    .call()
                    .content();
        } catch (Exception e) {
            log.error("Error occurred while chatting with Ollama", e);
            message = "Service is currently unavailable";
        }
        return new OllamaChatMsgResponse(message);
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
                        prompt.media(MimeTypeUtils.IMAGE_PNG, imageResource);
                    })
                    .call()
                    .content();

        } catch (Exception e) {
            log.error("Error occurred while chatting with image in Ollama", e);
            message = "Service is currently unavailable";
        }

        return new OllamaChatMsgResponse(message);
    }

    @Override
    public Flux<String> chatWithStream(String inputMessage) {
        try {
            return chatClient.prompt().user(inputMessage).stream().content().doOnNext(
                    s -> {
                        System.out.print("Received: " + s);
                    });
        } catch (Exception e) {
            log.error("Error occurred while chatWithStream with Ollama", e);
        }
        return null;
    }
}
