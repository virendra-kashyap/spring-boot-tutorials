package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SankeyController {

    @GetMapping("/sankey-data")
    public Map<String, Object> getSankeyData() {
        List<Map<String, String>> nodes = List.of(
                Map.of("name", "Source A"),
                Map.of("name", "Destination B"),
                Map.of("name", "Destination C")
        );

        List<Map<String, Object>> links = List.of(
                Map.of("source", 0, "target", 1, "value", 10),
                Map.of("source", 0, "target", 2, "value", 5)
        );

        return Map.of("nodes", nodes, "links", links);
    }
}