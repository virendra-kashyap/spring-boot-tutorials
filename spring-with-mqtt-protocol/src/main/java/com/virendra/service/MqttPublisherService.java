package com.virendra.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MqttPublisherService {

    @Autowired
    private MessageChannel mqttOutboundChannel;

    public void sendToMqtt(String data) {
        Message<String> message = MessageBuilder.withPayload(data).build();
        mqttOutboundChannel.send(message);
    }

}
