package com.example.Makkajai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author DivyanshiChaturvedi
 *
 */
@EnableSwagger2
@SpringBootApplication
public class MakkajaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MakkajaiApplication.class, args);
	}

}
