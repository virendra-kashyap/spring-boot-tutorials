package com.social.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class VaultConfiguration {

	private String FacebookClientID;
	private String FacebookClientSecret;

	public String getFacebookClientID() {
		return FacebookClientID;
	}

	public void setFacebookClientID(String facebookClientID) {
		FacebookClientID = facebookClientID;
	}

	public String getFacebookClientSecret() {
		return FacebookClientSecret;
	}

	public void setFacebookClientSecret(String facebookClientSecret) {
		FacebookClientSecret = facebookClientSecret;
	}

}
