package com.virendra.controller;

import com.virendra.service.SqsProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sqs")
public class SqsController {

    @Autowired
    private SqsProducerService sqsProducerService;

    @PostMapping("/send-csv")
    public String sendCsvToSqs(@RequestParam String filePath) {
        sqsProducerService.sendCsvToSqs(filePath);
        return "CSV processed and messages sent to SQS!";
    }

}
