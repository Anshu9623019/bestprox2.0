package com.bestprox;

import static org.mockito.ArgumentMatchers.matches;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bestprox.service.EmailService;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}


	@Autowired
	private EmailService service;

	@Test
	void sendEmailTest(){

		service.sendEmail(
			 "ak9623019@gmail.com",
		 "just sendind email service",
			 "this bestprox email"
		);
	}

}
