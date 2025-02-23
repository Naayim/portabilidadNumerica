package com.tel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication//(scanBasePackages = "com.tel.mapper")
@EnableScheduling

public class ConectividadApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConectividadApiApplication.class, args);
	}

}
