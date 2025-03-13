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
        // Define nodes
        List<Map<String, String>> nodes = List.of(
                Map.of("name", "Source A"),  // Node 0
                Map.of("name", "Type 1"),    // Node 1
                Map.of("name", "Type 2"),    // Node 2
                Map.of("name", "Type 3"),    // Node 3
                Map.of("name", "Type 4"),    // Node 4
                Map.of("name", "Child 1.1"), // Node 5
                Map.of("name", "Child 1.2"), // Node 6
                Map.of("name", "Child 1.1.1"), // Node 7 (Child of Child 1.1)
                Map.of("name", "Child 1.1.2"), // Node 8 (Child of Child 1.1)
                Map.of("name", "Child 1.2.1"), // Node 9 (Child of Child 1.2)
                Map.of("name", "Child 1.2.2"), // Node 10 (Child of Child 1.2)
                Map.of("name", "Child 2.1"), // Node 11
                Map.of("name", "Child 2.2"), // Node 12
                Map.of("name", "Child 2.1.1"), // Node 13 (Child of Child 2.1)
                Map.of("name", "Child 2.1.2"), // Node 14 (Child of Child 2.1)
                Map.of("name", "Child 2.2.1"), // Node 15 (Child of Child 2.2)
                Map.of("name", "Child 2.2.2"), // Node 16 (Child of Child 2.2)
                Map.of("name", "Child 3.1"), // Node 17
                Map.of("name", "Child 3.2"), // Node 18
                Map.of("name", "Child 3.1.1"), // Node 19 (Child of Child 3.1)
                Map.of("name", "Child 3.1.2"), // Node 20 (Child of Child 3.1)
                Map.of("name", "Child 3.2.1"), // Node 21 (Child of Child 3.2)
                Map.of("name", "Child 3.2.2"), // Node 22 (Child of Child 3.2)
                Map.of("name", "Child 4.1"), // Node 23
                Map.of("name", "Child 4.2"), // Node 24
                Map.of("name", "Child 4.1.1"), // Node 25 (Child of Child 4.1)
                Map.of("name", "Child 4.1.2"), // Node 26 (Child of Child 4.1)
                Map.of("name", "Child 4.2.1"), // Node 27 (Child of Child 4.2)
                Map.of("name", "Child 4.2.2")  // Node 28 (Child of Child 4.2)
        );

        // Define links
        List<Map<String, Object>> links = List.of(
                // Links from Source A to Types 1-4
                Map.of("source", 0, "target", 1, "value", 10),
                Map.of("source", 0, "target", 2, "value", 15),
                Map.of("source", 0, "target", 3, "value", 20),
                Map.of("source", 0, "target", 4, "value", 25),

                // Links from Type 1 to its children
                Map.of("source", 1, "target", 5, "value", 5),
                Map.of("source", 1, "target", 6, "value", 5),

                // Links from Child 1.1 to its children
                Map.of("source", 5, "target", 7, "value", 2),
                Map.of("source", 5, "target", 8, "value", 3),

                // Links from Child 1.2 to its children
                Map.of("source", 6, "target", 9, "value", 2),
                Map.of("source", 6, "target", 10, "value", 3),

                // Links from Type 2 to its children
                Map.of("source", 2, "target", 11, "value", 7),
                Map.of("source", 2, "target", 12, "value", 8),

                // Links from Child 2.1 to its children
                Map.of("source", 11, "target", 13, "value", 3),
                Map.of("source", 11, "target", 14, "value", 4),

                // Links from Child 2.2 to its children
                Map.of("source", 12, "target", 15, "value", 4),
                Map.of("source", 12, "target", 16, "value", 4),

                // Links from Type 3 to its children
                Map.of("source", 3, "target", 17, "value", 10),
                Map.of("source", 3, "target", 18, "value", 10),

                // Links from Child 3.1 to its children
                Map.of("source", 17, "target", 19, "value", 5),
                Map.of("source", 17, "target", 20, "value", 5),

                // Links from Child 3.2 to its children
                Map.of("source", 18, "target", 21, "value", 5),
                Map.of("source", 18, "target", 22, "value", 5),

                // Links from Type 4 to its children
                Map.of("source", 4, "target", 23, "value", 12),
                Map.of("source", 4, "target", 24, "value", 13),

                // Links from Child 4.1 to its children
                Map.of("source", 23, "target", 25, "value", 6),
                Map.of("source", 23, "target", 26, "value", 6),

                // Links from Child 4.2 to its children
                Map.of("source", 24, "target", 27, "value", 7),
                Map.of("source", 24, "target", 28, "value", 6)
        );

        return Map.of("nodes", nodes, "links", links);
    }
}