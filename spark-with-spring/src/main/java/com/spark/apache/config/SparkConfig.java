package com.spark.apache.config;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfig {

	@Bean
	public SparkSession sparkSession() {
		SparkSession spark = SparkSession.builder().appName("SparkWithSpring").master("local[*]") // Use local mode with
																									// all available
																									// cores
				.config("spark.executor.memory", "1g") // Adjust memory as needed
				.config("spark.driver.memory", "1g") // Adjust memory as needed
				.getOrCreate();
		return spark;
	}

}