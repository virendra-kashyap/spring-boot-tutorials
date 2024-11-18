package com.spring.elastic.stack;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElasticsearchConnectionController {

    @Autowired
    private RestHighLevelClient client;

    @GetMapping("/check-elasticsearch")
    public String checkConnection() {
        try {
            boolean isConnected = client.ping(org.elasticsearch.client.RequestOptions.DEFAULT);
            return isConnected ? "Connected to Elasticsearch!" : "Elasticsearch is not reachable.";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
