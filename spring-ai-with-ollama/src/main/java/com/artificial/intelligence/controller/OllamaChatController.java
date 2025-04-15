package com.artificial.intelligence.controller;

import com.artificial.intelligence.domain.OllamaChatMsgRequest;
import com.artificial.intelligence.domain.OllamaChatMsgResponse;
import com.artificial.intelligence.service.OllamaChatServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/ollama")
public class OllamaChatController {

	private final OllamaChatServiceImpl ollamaChatService;

	public OllamaChatController(OllamaChatServiceImpl ollamaChatService) {
		this.ollamaChatService = ollamaChatService;
	}

	@PostMapping("/chat")
	public @ResponseBody OllamaChatMsgResponse chat(@RequestBody OllamaChatMsgRequest request) {
		return ollamaChatService.chat(request.getInputMessage());
	}

	@PostMapping("/chat-with-image")
	public @ResponseBody OllamaChatMsgResponse chatWithImage(
			@RequestPart String inputMessage, @RequestPart MultipartFile imageFile) {
		return ollamaChatService.chatWithImage(inputMessage, imageFile);
	}

	@GetMapping("/chat-with-stream")
	public Flux<String> chatWithStream(@RequestParam("inputMessage") String inputMessage) {
		return ollamaChatService.chatWithStream(inputMessage);
	}

}
