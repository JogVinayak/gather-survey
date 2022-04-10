package com.survey.gather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@ComponentScan(basePackages = {"com.survey.gather", "com.survey.gather.onboarding"})
@EntityScan(basePackages = { "com.survey.gather.onboarding"})
@SpringBootApplication
@EnableMongoRepositories

public class GatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatherApplication.class, args);
	}

}
