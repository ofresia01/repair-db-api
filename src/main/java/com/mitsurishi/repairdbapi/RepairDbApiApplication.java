/*
 * Java fie that handles configuration and execution of Spring Boot API service.
 */
package com.mitsurishi.repairdbapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RepairDbApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(RepairDbApiApplication.class, args);
	}
}