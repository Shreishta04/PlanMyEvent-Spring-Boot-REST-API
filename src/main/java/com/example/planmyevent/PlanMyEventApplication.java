package com.example.planmyevent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PlanMyEventApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlanMyEventApplication.class, args);
	}

}
