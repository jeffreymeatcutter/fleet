package com.skillstorm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class PersonnelServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonnelServiceApplication.class, args);
	}

}
