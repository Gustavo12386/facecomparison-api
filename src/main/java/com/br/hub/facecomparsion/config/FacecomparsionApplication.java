package com.br.hub.facecomparsion.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.rekognition.RekognitionClient;

@SpringBootApplication
@ComponentScan(basePackages = {"com.br.hub.facecomparsion"})
public class FacecomparsionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FacecomparsionApplication.class, args);
	}
	
	@Bean
	 public RekognitionClient rekognitionClient() {
        return RekognitionClient.builder()
                .region(Region.US_EAST_1) 
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }
}
