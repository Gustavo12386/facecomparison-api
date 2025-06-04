package com.br.hub.facecomparsion;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.br.hub.facecomparsion.config.FacecomparsionApplication;

@SpringBootTest(classes = FacecomparsionApplication.class)
@ActiveProfiles(value = "test")
class FacecomparsionApplicationTests {

	@Test
	void contextLoads() {
	}

}
