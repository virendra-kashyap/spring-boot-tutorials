package com.batch.process.config;

import java.io.File;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.dsl.Files;
import org.springframework.util.Assert;

@Configuration
@EnableIntegration
public class FileIntegrationConfig {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job importUserJob; // Inject your Spring Batch job bean here

	@Autowired
	private ResourceLoader resourceLoader; // Inject Spring's ResourceLoader

	@Bean
	public IntegrationFlow filePollingFlow() {
		Assert.notNull(resourceLoader, "ResourceLoader must not be null");

		Resource resource = resourceLoader.getResource("classpath:sample-data.csv");
		File file;
		try {
			file = resource.getFile();
		} catch (Exception e) {
			throw new RuntimeException("Failed to load file from resource", e);
		}

		return IntegrationFlows
				.from(Files.inboundAdapter(file.getParentFile()).autoCreateDirectory(true).patternFilter("*.csv"),
						e -> e.poller(Pollers.fixedDelay(5000))) // Poll every 5 seconds
				.handle(message -> {
					try {
						jobLauncher.run(importUserJob, new JobParametersBuilder()
								.addString("JobID", String.valueOf(System.currentTimeMillis())).toJobParameters());
					} catch (Exception ex) {
						// Handle job launching exceptions
					}
				}).get();
	}

}
