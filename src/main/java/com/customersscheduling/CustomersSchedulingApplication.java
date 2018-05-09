package com.customersscheduling;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;


@EnableJpaRepositories(basePackages = "com.customersscheduling.Repository")
@SpringBootApplication
public class CustomersSchedulingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomersSchedulingApplication.class, args);

	}


}
