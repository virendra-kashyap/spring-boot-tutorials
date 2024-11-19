package com.spring.elastic.stack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringWithElasticStackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWithElasticStackApplication.class, args);
	}

}