package com.iplacex.mcsv_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class McsvUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(McsvUserApplication.class, args);
	}

}
