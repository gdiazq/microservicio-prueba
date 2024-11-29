package com.iplacex.mcsv_auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.iplacex.mcsv_auth.config.JwtProperties;

@SpringBootApplication
@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class McsvAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(McsvAuthApplication.class, args);
	}

}
