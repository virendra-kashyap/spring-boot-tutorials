package com.spring.elastic.stack;

import org.apache.http.HttpHost;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(
            RestClient.builder(new HttpHost("localhost", 9200, "http"))
                .setRequestConfigCallback(requestConfigBuilder -> 
                    requestConfigBuilder
                        .setConnectTimeout(5000) // Connection timeout in ms
                        .setSocketTimeout(60000) // Socket timeout in ms
                )
                .setHttpClientConfigCallback(httpClientBuilder -> 
                    httpClientBuilder.setDefaultIOReactorConfig(
                        IOReactorConfig.custom()
                            .setIoThreadCount(4) // Adjust thread count based on workload
                            .build()
                    )
                )
        );
    }
}
