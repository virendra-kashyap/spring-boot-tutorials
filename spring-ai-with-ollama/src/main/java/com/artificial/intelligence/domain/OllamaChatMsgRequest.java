package com.artificial.intelligence.domain;

public class OllamaChatMsgRequest {

    private String inputMessage;

    public OllamaChatMsgRequest() {}

    public OllamaChatMsgRequest(String inputMessage) {
        this.inputMessage = inputMessage;
    }

    public String getInputMessage() {
        return inputMessage;
    }

    public void setInputMessage(String inputMessage) {
        this.inputMessage = inputMessage;
    }
}
