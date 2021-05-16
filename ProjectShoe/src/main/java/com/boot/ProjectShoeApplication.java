package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan({"com.boot","com.boot.model"})

@ComponentScan("com")
public class ProjectShoeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectShoeApplication.class, args);
	}

}
