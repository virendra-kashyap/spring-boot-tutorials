package com.spring.elastic.stack.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.spring.elastic.stack.repository")
public class ClientConfig extends ElasticsearchConfiguration {

	@Override
	public ClientConfiguration clientConfiguration() {
		return ClientConfiguration.builder().connectedTo("localhost:9200").build();
	}
}