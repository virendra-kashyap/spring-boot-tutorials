package com.virendra.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

    private static final String BROKER_URL = "tcp://localhost:1883"; // Replace with your Mosquitto broker URL
    private static final String CLIENT_ID = "spring-boot-client";

//    @Bean
//    public MqttClient mqttClient() throws Exception {
//        MqttClient client = new MqttClient(BROKER_URL, CLIENT_ID, new MemoryPersistence());
//
//        MqttConnectOptions connOpts = new MqttConnectOptions();
//        connOpts.setCleanSession(true);
//
//        client.connect(connOpts);
//        return client;
//    }

    @Bean
    public MqttClient mqttClient() throws MqttException {
        return new MqttClient(BROKER_URL, MqttClient.generateClientId(), new MemoryPersistence());
    }

    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        return options;
    }

//    @Bean
//    public MqttClient mqttClient() throws MqttException {
//        MqttClient client = new MqttClient(BROKER_URL, CLIENT_ID, new MemoryPersistence());
//
//        MqttConnectOptions options = new MqttConnectOptions();
//        options.setCleanSession(true);
//        options.setAutomaticReconnect(true);
//        options.setConnectionTimeout(10);
//
//        client.connect(options); // 👈 This line throws "Connection lost" if Mosquitto isn't reachable
//        return client;
//    }

}
