package com.br.hub.facecomparsion;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.hub.facecomparsion.config.FacecomparsionApplication;

@SpringBootTest(classes = FacecomparsionApplication.class)
class FacecomparsionApplicationTests {

	@BeforeAll
	static void setupProperties() {
		System.setProperty("aws.region", "us-east-1");
		System.setProperty("aws.accessKeyId", "localstack");
		System.setProperty("aws.secretAccessKey", "localstack");
	}
	
	@Test
	void contextLoads() {
	}

}
