package com.backend_happibee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.backend_happibee.persistence")
public class BackendHappibeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendHappibeeApplication.class, args);
	}

}
