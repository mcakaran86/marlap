package com.marlap.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableAutoConfiguration
@ComponentScan(basePackages="com.marlap")
public class MarlapApplication {
	public static void main(String[] args) {
		SpringApplication.run(MarlapApplication.class, args);
	}
}
