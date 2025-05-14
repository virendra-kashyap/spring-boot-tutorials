package com.virendra;

import com.virendra.service.MqttPublisherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringWithMqttProtocolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWithMqttProtocolApplication.class, args);
	}

	@Bean
	CommandLineRunner run(MqttPublisherService publisher) {
		return args -> {
			Thread.sleep(2000); // wait for subscription to be ready
			publisher.publish("test/topic", "Hello from Spring Boot via Mosquitto!");
		};
	}

}
