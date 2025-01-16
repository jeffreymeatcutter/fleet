package com.skillstorm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayFleetApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayFleetApplication.class, args);
	}

}
