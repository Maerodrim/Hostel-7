package com.ssau.Hostel7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:error_messages.properties"}, encoding = "UTF-8")
public class Hostel7Application {

	public static void main(String[] args) {
		SpringApplication.run(Hostel7Application.class, args);
	}

}
