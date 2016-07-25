package com.example.spring;

import com.example.spring.configuration.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableEurekaClient
@Import({ApplicationConfiguration.class})
@EnableFeignClients(basePackages = "com.example")
@EnableAutoConfiguration
public class DemoFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoFeignApplication.class, args);
	}
}
