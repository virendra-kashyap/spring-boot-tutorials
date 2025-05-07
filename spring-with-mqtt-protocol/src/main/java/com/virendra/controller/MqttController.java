//package com.virendra.controller;
//
//import com.virendra.service.MqttPublisher;
//import com.virendra.service.MqttSubscriber;
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/mqtt")
//public class MqttController {
//
//    @Autowired
//    private MqttPublisher publisher;
//
//    @Autowired
//    private MqttSubscriber subscriber;
//
//    @PostMapping("/publish")
//    public String publishMessage(@RequestParam String topic,
//                                 @RequestParam String message) throws MqttException {
//        publisher.publish(topic, message);
//        return "Published to topic '" + topic + "': " + message;
//    }
//
//    @GetMapping("/subscribe")
//    public String subscribeToTopic(@RequestParam String topic) throws MqttException {
//        subscriber.subscribe(topic, (receivedTopic, mqttMessage) -> {
//            System.out.println("Received message on topic " + receivedTopic + ": " +
//                    new String(mqttMessage.getPayload()));
//        });
//        return "Subscribed to topic: " + topic;
//    }
//
//}
