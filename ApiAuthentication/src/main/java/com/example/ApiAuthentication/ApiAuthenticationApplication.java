package com.example.ApiAuthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class ApiAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAuthenticationApplication.class, args);
	}

}
