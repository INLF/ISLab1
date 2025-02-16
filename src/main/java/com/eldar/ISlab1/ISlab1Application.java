package com.eldar.ISlab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ISlab1Application {

	public static void main(String[] args) {
		SpringApplication.run(ISlab1Application.class, args);
	}

}
