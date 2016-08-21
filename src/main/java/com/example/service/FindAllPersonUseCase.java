package com.example.service;

import com.example.domain.person.Person;
import com.example.infrastructure.repository.PersonRepositoryGetAllFeign;
import feign.Feign;
import feign.jackson.JacksonDecoder;

import java.util.List;

public class FindAllPersonUseCase {

    public List<Person> findAll(String offset, String limit) {
        PersonRepositoryGetAllFeign personRepositoryGetAllFeign = Feign.builder()
                .decoder(new JacksonDecoder())
                .target(PersonRepositoryGetAllFeign.class, "http://localhost:8000");
        return personRepositoryGetAllFeign.getAll(limit, offset);
    }
}
