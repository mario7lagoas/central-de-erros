package com.codenation.centralerrosapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CentralErrosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CentralErrosApiApplication.class, args);
	}

}
