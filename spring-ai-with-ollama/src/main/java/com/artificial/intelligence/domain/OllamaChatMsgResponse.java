package com.artificial.intelligence.domain;

public class OllamaChatMsgResponse {

    private String message;

    public OllamaChatMsgResponse() {}

    public OllamaChatMsgResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
