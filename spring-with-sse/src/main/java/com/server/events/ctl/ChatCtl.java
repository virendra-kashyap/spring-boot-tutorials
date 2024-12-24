package com.server.events.ctl;

import com.server.events.service.ChatSseService;
import com.server.events.service.SseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class ChatCtl {

    private final ChatSseService sseService;

    public ChatCtl(ChatSseService sseService) {
        this.sseService = sseService;
    }

    @GetMapping(path = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        sseService.addEmitter(emitter);
        return emitter;
    }

    @GetMapping("/send-message")
    public String sendMessage(@RequestParam String message) {
        sseService.sendMessageToAllClients(message);
        return "Message sent";
    }
}
