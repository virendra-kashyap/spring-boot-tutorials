package com.social.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.social.spring.config.VaultConfiguration;

@SpringBootApplication
@EnableConfigurationProperties(VaultConfiguration.class)
public class SocialLoginWithSpringApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(SocialLoginWithSpringApplication.class);

	private final VaultConfiguration vaultConfiguration;

	public SocialLoginWithSpringApplication(VaultConfiguration vaultConfiguration) {
		this.vaultConfiguration = vaultConfiguration;
	}

	public static void main(String[] args) {
		SpringApplication.run(SocialLoginWithSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Inside main method");
		logger.info("Value from vault server");
		logger.info("username: " + vaultConfiguration.getFacebookClientID());
		logger.info("password: " + vaultConfiguration.getFacebookClientSecret());
	}

}
