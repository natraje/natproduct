package com.nat.product;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
public class ProductApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}



	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
