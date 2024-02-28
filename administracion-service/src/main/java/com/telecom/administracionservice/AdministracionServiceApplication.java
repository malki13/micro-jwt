package com.telecom.administracionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableDiscoveryClient
public class AdministracionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministracionServiceApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder BCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
