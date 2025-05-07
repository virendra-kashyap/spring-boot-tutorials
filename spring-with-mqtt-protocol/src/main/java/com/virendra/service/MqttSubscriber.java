//package com.virendra.service;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.annotation.PreDestroy;
//import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MqttSubscriber {
//
//    @Autowired
//    private MqttPahoClientFactory clientFactory;
//
//    private MqttClient mqttClient;
//
//    @PostConstruct
//    public void init() throws MqttException {
//        mqttClient = (MqttClient) clientFactory.getClientInstance("tcp://localhost:1883", "subscriber-client");
//        mqttClient.connect(clientFactory.getConnectionOptions());
//    }
//
//    public void subscribe(String topic, IMqttMessageListener messageListener) throws MqttException {
//        mqttClient.subscribe(topic, messageListener);
//    }
//
//    public void subscribe(String topic, int qos, IMqttMessageListener messageListener) throws MqttException {
//        mqttClient.subscribe(topic, qos, messageListener);
//    }
//
//    @PreDestroy
//    public void cleanup() throws MqttException {
//        if (mqttClient != null && mqttClient.isConnected()) {
//            mqttClient.disconnect();
//        }
//    }
//
//}
