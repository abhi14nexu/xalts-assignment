package com.xalts.XaltsAssignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class XaltsAssignmentApplication {
	public static void main(String[] args) {
		SpringApplication.run(XaltsAssignmentApplication.class, args);
	}
}
