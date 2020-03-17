package com.indi.basicsocialapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(exclude= { SecurityAutoConfiguration.class })
@PropertySource("classpath:config.properties")
public class BasicSocialAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicSocialAppApplication.class, args);
	}

}
