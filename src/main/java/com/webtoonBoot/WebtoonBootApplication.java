package com.webtoonBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class WebtoonBootApplication  {
	

	public static void main(String[] args) {
		SpringApplication.run(WebtoonBootApplication.class, args);
	}

}
