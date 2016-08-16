package com.example.spring.configuration;

import com.example.service.CreatePersonUseCase;
import com.example.service.FindAllPersonUseCase;
import com.example.service.FindByIdPersonUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public FindByIdPersonUseCase findByIdPerson (){
        return new FindByIdPersonUseCase();
    }

    @Bean
    public CreatePersonUseCase createPerson (){
        return new CreatePersonUseCase();
    }

    @Bean
    public FindAllPersonUseCase findAllPersonUseCase (){
        return new FindAllPersonUseCase();
    }
}
