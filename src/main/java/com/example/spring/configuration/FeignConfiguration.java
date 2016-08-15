package com.example.spring.configuration;


import feign.Contract;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import feign.jaxrs.JAXRSContract;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(FeignConfiguration.class);

    @Bean
    public Contract feignContract() {
        return new JAXRSContract();
    }

    @Bean
    @ConditionalOnClass(RequestInterceptor.class)
    RequestInterceptor userAgentInterceptor() {
        return new RequestInterceptor() {
            @Value("${spring.application.name}")
            private String serviceName;

            @Override
            public void apply(RequestTemplate template) {
                LOGGER.info("Operation: " + template.method()
                        + " | URL " + template.url());
                template.header("User-Agent", serviceName);
            }
        };
    }
}
