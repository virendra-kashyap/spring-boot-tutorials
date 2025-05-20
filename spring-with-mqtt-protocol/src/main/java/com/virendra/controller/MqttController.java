package com.virendra.controller;

import com.virendra.service.MqttPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/mqtt")
public class MqttController {

    @Autowired
    private MqttPublisherService mqttPublisherService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String message) {
        mqttPublisherService.sendToMqtt(message);
        return ResponseEntity.ok("Message sent to MQTT: " + message);
    }

}
