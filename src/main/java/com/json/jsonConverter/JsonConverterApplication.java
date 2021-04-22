package com.json.jsonConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class JsonConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonConverterApplication.class, args);
	}

	}
