package com.mystyle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
public class ShoppingUserManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingUserManagementServiceApplication.class, args);
	}

}
