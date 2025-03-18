package com.virendra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class AwsConfig {

    @Value("${aws.access.key}")
    private String accessKey;

    @Value("${aws.secret.key}")
    private String secretKey;

    @Bean
    public SqsClient sqsClient() {
        System.out.println("accessKey # " + accessKey);
        System.out.println("secretKey # " + secretKey);
        return SqsClient.builder().region(Region.AP_SOUTH_1).credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(
                accessKey, secretKey
        ))).build();
    }

}
