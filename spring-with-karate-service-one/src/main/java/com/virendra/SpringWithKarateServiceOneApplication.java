package com.virendra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@RequestMapping("/service1")
public class SpringWithKarateServiceOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWithKarateServiceOneApplication.class, args);
	}

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/")
	public String test() {
		return restTemplate.getForObject("http://localhost:8081/service2/", String.class);
	}
}
