package com.virendra.kafka;

import com.virendra.kafka.service.KafkaProducerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class SpringBootWithKafkaUiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithKafkaUiApplication.class, args);
	}

	private final KafkaProducerService kafkaProducerService;

	public SpringBootWithKafkaUiApplication(KafkaProducerService kafkaProducerService) {
		this.kafkaProducerService = kafkaProducerService;
	}

	@Override
	public void run(String... args) throws Exception {
		kafkaProducerService.sendMessage("HelloKafka");
	}
}
