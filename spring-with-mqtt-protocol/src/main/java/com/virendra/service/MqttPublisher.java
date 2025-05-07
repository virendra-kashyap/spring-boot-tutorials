//package com.virendra.service;
//
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
//import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
//import org.springframework.integration.mqtt.support.MqttHeaders;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MqttPublisher {
//
//    private final MqttPahoMessageHandler mqttHandler;
//
//    @Autowired
//    public MqttPublisher(MqttPahoClientFactory clientFactory) {
//        this.mqttHandler = new MqttPahoMessageHandler("publisher-client", clientFactory);
//        this.mqttHandler.setAsync(true);
//        this.mqttHandler.setDefaultQos(1);
//    }
//
//    public void publish(String topic, String payload) throws MqttException {
//        publish(topic, payload, 1, false);
//    }
//
//    public void publish(String topic, String payload, int qos, boolean retained) throws MqttException {
//        MqttMessage message = new MqttMessage(payload.getBytes());
//        message.setQos(qos);
//        message.setRetained(retained);
//
//        Message<String> springMessage = MessageBuilder
//                .withPayload(payload)
//                .setHeader(MqttHeaders.TOPIC, topic)
//                .setHeader(MqttHeaders.QOS, qos)
//                .setHeader(MqttHeaders.RETAINED, retained)
//                .build();
//
//        mqttHandler.handleMessage(springMessage);
//    }
//
//}
