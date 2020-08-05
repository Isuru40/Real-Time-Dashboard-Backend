package com.smart.dahboard.smartdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.smart.dashboard")
@EnableJpaRepositories("com.smart.dashboard.repository")
@EntityScan("com.smart.dashboard.model")
public class SmartDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartDashboardApplication.class, args);
	}

}
