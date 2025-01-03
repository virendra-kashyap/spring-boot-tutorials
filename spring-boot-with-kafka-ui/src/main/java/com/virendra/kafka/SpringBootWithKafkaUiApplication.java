package com.virendra.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class SpringBootWithKafkaUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithKafkaUiApplication.class, args);
	}

}
