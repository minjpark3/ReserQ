package com.example.mo_activity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableFeignClients
public class MoActivityApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoActivityApplication.class, args);
	}

}
