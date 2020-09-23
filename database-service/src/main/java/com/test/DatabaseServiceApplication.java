package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEurekaClient
@EnableJpaRepositories(basePackages ={ "com.test.repository"})
@EntityScan(basePackages ={ "com.test.jpa"})
@SpringBootApplication
public class DatabaseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseServiceApplication.class, args);
	}

}
