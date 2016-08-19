package com.example.spring.configuration;

import com.example.service.*;
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

    @Bean
    public DeletePersonUseCase deletePersonUseCase() {
        return new DeletePersonUseCase();
    }

    @Bean
    public UpdatePersonUseCase updatePersonUseCase (){
        return new UpdatePersonUseCase();
    }
}
