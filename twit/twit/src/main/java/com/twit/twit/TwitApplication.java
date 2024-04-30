package com.twit.twit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.twit.twit.repositories")

@SpringBootApplication
public class TwitApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitApplication.class, args);
	}

}
