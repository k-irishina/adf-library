package com.adf.irisina.library.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.adf.irisina.library")
@EntityScan(basePackages = "com.adf.irisina.library")
public class AdfApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdfApplication.class, args);
	}

}
