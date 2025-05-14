package com.virendra.service;

import jakarta.annotation.PostConstruct;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttPublisherService {

    @Autowired
    private MqttClient mqttClient;

    @Autowired
    private MqttConnectOptions options;

    @PostConstruct
    public void connect() {
        try {
            if (!mqttClient.isConnected()) {
                mqttClient.connect(options);
                System.out.println("MQTT Connected");
            }
        } catch (MqttException e) {
            System.err.println("MQTT Connection failed: " + e.getMessage());
        }
    }

    public void publish(String topic, String payload) {
        try {
            mqttClient.publish(topic, new MqttMessage(payload.getBytes()));
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

}
