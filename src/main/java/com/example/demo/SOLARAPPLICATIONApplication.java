package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
@Configuration
@ComponentScan({"com.rainiersoft.solar.controller","com.rainiersoft.solar.dao"})
public class SOLARAPPLICATIONApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SOLARAPPLICATIONApplication.class, args);
	}
}

