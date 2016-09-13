package com.example.spring;

import com.example.spring.configuration.ApplicationConfiguration;
import com.example.spring.configuration.ApplicationConfigurationTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ApplicationConfiguration.class, ApplicationConfigurationTest.class})
@EnableFeignClients(basePackages = "com.example")
@EnableAutoConfiguration
public class DemoFeignApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(DemoFeignApplicationTest.class, args);
    }
}