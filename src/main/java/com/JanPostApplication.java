package com;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.Model;

@SpringBootApplication
public class JanPostApplication {

	public static void main(String[] args) {
		SpringApplication.run(JanPostApplication.class, args);
	}


	@Configuration
	public class ModelMapperConfig {
	@Bean
		public ModelMapper modelMapper() {
			return new ModelMapper();
		}
	}
}
