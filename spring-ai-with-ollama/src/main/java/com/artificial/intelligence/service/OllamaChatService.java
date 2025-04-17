package com.artificial.intelligence.service;

import com.artificial.intelligence.domain.OllamaChatMsgResponse;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

public interface OllamaChatService {

    public OllamaChatMsgResponse chat(String inputMessage);

    public OllamaChatMsgResponse chatWithImage(
            String inputMessage, MultipartFile imageFile);
}
