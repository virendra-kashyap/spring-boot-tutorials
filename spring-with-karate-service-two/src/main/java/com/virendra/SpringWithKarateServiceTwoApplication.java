package com.virendra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/service2")
public class SpringWithKarateServiceTwoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWithKarateServiceTwoApplication.class, args);
	}

	@GetMapping("/")
	public String test() {
		System.out.println("I am virendra");
		return "Hello, I am Java.";
	}

}
