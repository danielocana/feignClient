package com.example.spring.configuration;

import feign.Contract;
import feign.Logger;
import feign.RequestInterceptor;
import feign.jaxrs.JAXRSContract;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FeignConfiguration.class);

    @Bean
    public Contract feignContract() {
        return new JAXRSContract();
    }

    @Bean
    @ConditionalOnClass(RequestInterceptor.class)
    public RequestInterceptor loggerInterceptor() {
        return (template -> logger.info("Operation: " + template.method()
                + " | URL " + template.url()
                + " | headers " + template.headers()
                + " | queries" + template.queries()));
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
