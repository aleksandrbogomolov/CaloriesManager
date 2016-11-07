package com.aleksandrbogomolov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CaloriesManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaloriesManagerApplication.class, args);
	}
}
