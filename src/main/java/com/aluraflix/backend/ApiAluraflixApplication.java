package com.aluraflix.backend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApiAluraflixApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiAluraflixApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("12345"));
		System.out.println(new BCryptPasswordEncoder().encode("54321"));
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
