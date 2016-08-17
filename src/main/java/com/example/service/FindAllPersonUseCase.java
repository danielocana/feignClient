package com.example.service;

import com.example.domain.person.Person;
import com.example.infrastructure.repository.PersonRepositoryFeign;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FindAllPersonUseCase {

    @Autowired
    PersonRepositoryFeign repository;

    public List<Person> finAll(String offset, String limit){
        String pagination = "?limit="+limit+"&offset="+offset;
        return repository.findAll();
    }
}