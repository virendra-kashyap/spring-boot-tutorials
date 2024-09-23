package com.example.sentry;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.sentry.Sentry;

@SpringBootApplication
public class SpringWithSentryApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringWithSentryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			throw new IOException("File not found.");
		} catch (Exception e) {
			Sentry.captureException(e);
		}
	}

}
